package com.example.riddleanswerers;


import lombok.NonNull;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MinimumMissingNumberAnswererTest {
    private int getAnswerFor(@NonNull List<Integer> numbers) {
        NavigableSet<Integer> set = new TreeSet<>(numbers);
        return new MinimumMissingNumberAnswerer(set).getAnswer();
    }

    @Test
    public void givenSetWithGaps_whenGetAnswer_thenReturnAppropriateInteger() {
        assertEquals(2, getAnswerFor(List.of(3, 4, -1, 1)));
        assertEquals(4, getAnswerFor(List.of(2, 3, 5)));
        assertEquals(8, getAnswerFor(List.of(9, 7, 6, 5, 4, 3, 2, 1)));
    }

    @Test
    public void givenEmptyInitialData_whenGetAnswer_thenThrowWithAnswererException() {
        assertThrows(AnswererException.class, () -> getAnswerFor(List.of()));
        assertThrows(NullPointerException.class, () -> getAnswerFor(null));
    }

    @Test
    public void givenOneEntityInSet_whenGetAnswer_thenThrowWithNoNumbersMissingException() {
        assertThrows(NoNumbersMissingException.class, () -> getAnswerFor(List.of(1)));
        assertThrows(NoNumbersMissingException.class, () -> getAnswerFor(List.of(5)));
        assertThrows(NoNumbersMissingException.class, () -> getAnswerFor(List.of(123)));
        assertThrows(NoNumbersMissingException.class, () -> getAnswerFor(List.of(2, 3, 4, 5, 6)));
        assertThrows(NoNumbersMissingException.class, () -> getAnswerFor(List.of(-4, 2, -8, -12, -4, 4, 0, 3)));

    }
}
