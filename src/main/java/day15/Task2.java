package day15;

import java.io.*;
import java.util.*;

public class Task2 {

    private static final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // up, right, down, left

    private static List<Integer> parseInts(String s) {
        List<Integer> ints = new ArrayList<>();
        Scanner scanner = new Scanner(s);
        while (scanner.findInLine("-?\\d+") != null) {
            ints.add(Integer.parseInt(scanner.match().group()));
        }
        scanner.close();
        return ints;
    }

    private static void printAndCopy(String s) {
        System.out.println(s);
        // You can use a clipboard library if needed to copy `s` to clipboard
    }

    public static int solve(List<String> grid, String instrs, boolean part2) {
        int R = grid.size();
        int C = grid.get(0).length();
        char[][] G = new char[R][C];
        for (int r = 0; r < R; r++) {
            G[r] = grid.get(r).toCharArray();
        }

        if (part2) {
            List<char[]> bigGrid = new ArrayList<>();
            for (int r = 0; r < R; r++) {
                char[] row = new char[C * 2];
                int idx = 0;
                for (int c = 0; c < C; c++) {
                    if (G[r][c] == '#') {
                        row[idx++] = '#';
                        row[idx++] = '#';
                    } else if (G[r][c] == 'O') {
                        row[idx++] = '[';
                        row[idx++] = ']';
                    } else if (G[r][c] == '.') {
                        row[idx++] = '.';
                        row[idx++] = '.';
                    } else if (G[r][c] == '@') {
                        row[idx++] = '@';
                        row[idx++] = '.';
                    }
                }
                bigGrid.add(row);
            }
            G = bigGrid.toArray(new char[0][0]);
            C *= 2;
        }

        int sr = 0, sc = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (G[r][c] == '@') {
                    sr = r;
                    sc = c;
                    G[r][c] = '.';
                }
            }
        }

        int r = sr, c = sc;
        for (char inst : instrs.toCharArray()) {
            if (inst == '\n') continue;

            int[] dir = new int[2];
            if (inst == '^') {
                dir = DIRS[0];
            }
            else if (inst == '>') {
                dir = DIRS[1];
            }
            else if (inst == '<') {
                dir = DIRS[3];
            }
            else if (inst == 'v') {
                dir = DIRS[2];
            }

            int dr = dir[0], dc = dir[1];
            int rr = r + dr, cc = c + dc;

            if (G[rr][cc] == '#') {
                continue;
            } else if (G[rr][cc] == '.') {
                r = rr;
                c = cc;
            } else if (G[rr][cc] == '[' || G[rr][cc] == ']' || G[rr][cc] == 'O') {
                Queue<int[]> queue = new ArrayDeque<>();
                Set<String> seen = new HashSet<>();
                boolean ok = true;

                queue.add(new int[]{r, c});

                while (!queue.isEmpty()) {
                    int[] pos = queue.poll();
                    int qr = pos[0], qc = pos[1];
                    String key = qr + "," + qc;
                    if (seen.contains(key)) continue;
                    seen.add(key);

                    int rrr = qr + dr, ccc = qc + dc;
                    if (G[rrr][ccc] == '#') {
                        ok = false;
                        break;
                    }
                    if (G[rrr][ccc] == 'O' || G[rrr][ccc] == '[' || G[rrr][ccc] == ']') {
                        queue.add(new int[]{rrr, ccc});
                        if (G[rrr][ccc] == '[') {
                            queue.add(new int[]{rrr, ccc + 1});
                        } else if (G[rrr][ccc] == ']') {
                            queue.add(new int[]{rrr, ccc - 1});
                        }
                    }
                }

                if (!ok) continue;

                for (String s : seen) {
                    String[] parts = s.split(",");
                    int rr2 = Integer.parseInt(parts[0]);
                    int cc2 = Integer.parseInt(parts[1]);
                    int rrr = rr2 + dr, ccc = cc2 + dc;
                    if (!seen.contains(rrr + "," + ccc)) {
                        if (G[rrr][ccc] != '.') throw new AssertionError();
                        G[rrr][ccc] = G[rr2][cc2];
                        G[rr2][cc2] = '.';
                    }
                }

                r += dr;
                c += dc;
            }
        }

        int ans = 0;
        for (int rr = 0; rr < R; rr++) {
            for (int cc = 0; cc < C; cc++) {
                if (G[rr][cc] == '[' || G[rr][cc] == 'O') {
                    ans += 100 * rr + cc;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        String infile = args.length >= 1 ? args[0] : "src/main/resources/day15.txt";
        BufferedReader br = new BufferedReader(new FileReader(infile));
        String[] parts = br.lines().reduce("", (a, b) -> a + "\n" + b).split("\n\n");
        br.close();

        List<String> G = Arrays.asList(parts[0].split("\n"));
        String instrs = parts[1];

        printAndCopy(String.valueOf(solve(G, instrs, true)));
    }
}

