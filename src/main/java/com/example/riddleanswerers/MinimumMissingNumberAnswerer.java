package com.example.riddleanswerers;

import lombok.Getter;
import lombok.NonNull;

import java.util.*;

@Getter
public class MinimumMissingNumberAnswerer implements RiddleAnswerer {
    @NonNull
    NavigableSet<Integer> numbers;

    public MinimumMissingNumberAnswerer(@NonNull NavigableSet<Integer> originalSet) {
        if (originalSet.isEmpty()) {
            throw new AnswererException("Expected set to be non-empty, but an empty set is provided");
        }

        numbers = originalSet;
        omitNonPositiveNumbers();
    }

    @Override
    public int getAnswer() {
        if (theSetContainsOnlyOneEntity()) {
            throw new NoNumbersMissingException();
        }

        if (!isGapBetweenMinAndMaxBigEnough()) {
            throw new NoNumbersMissingException();
        }

        Iterator<Integer> iterator = numbers.iterator();
        Integer prev = iterator.next();

        while (iterator.hasNext()) {
            int current = iterator.next();

            if (isBigGap(current, prev)) {
                return prev + 1; // The next number at the start of the gap
            }

            prev = current;
        }

        throw new NoNumbersMissingException();
    }

    protected void omitNonPositiveNumbers() {
        numbers.removeIf(number -> number <= 0);
    }

    protected boolean isBigGap(int a, int b) {
        int absA = Math.abs(a);
        int absB = Math.abs(b);

        return Math.abs(absA - absB) >= Settings.REQUIRED_MIN_GAP;
    }

    private boolean theSetContainsOnlyOneEntity() {
        return numbers.size() == 1;
    }

    private boolean isGapBetweenMinAndMaxBigEnough() {
        Integer min = numbers.first();
        Integer max = numbers.last();

        return isBigGap(min, max);
    }
}
