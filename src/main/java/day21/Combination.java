package day21;

import java.util.Objects;

public class Combination {
    char x;
    char y;
    int pressesX;
    int pressesY;

    public Combination(char x, char y, int pressesX, int pressesY) {
        this.x = x;
        this.y = y;
        this.pressesX = pressesX;
        this.pressesY = pressesY;
    }

    public char getX() {
        return x;
    }

    public char getY() {
        return y;
    }

    public int getPressesX() {
        return pressesX;
    }

    public int getPressesY() {
        return pressesY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Combination that = (Combination) o;
        return x == that.x && y == that.y && pressesX == that.pressesX && pressesY == that.pressesY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, pressesX, pressesY);
    }
}
