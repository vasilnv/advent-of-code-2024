package day21;

import java.util.Objects;

public class ComboPair {
    Combination combination;
    int robotsLeft;
    Task2.Coordinate begin;

    public ComboPair(Combination combination, int robotsLeft, Task2.Coordinate begin) {
        this.combination = combination;
        this.robotsLeft = robotsLeft;
        this.begin = begin;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComboPair comboPair = (ComboPair) o;
        return robotsLeft == comboPair.robotsLeft
                && Objects.equals(combination, comboPair.combination)
                && Objects.equals(begin, comboPair.begin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(combination, robotsLeft, begin);
    }
}
