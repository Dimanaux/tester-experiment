package main;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class Spec {
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testSum() {
        assertTrue(Solution.max(1, 3, 4) == 4);
        assertTrue(Solution.sum(2, 2, 4) == 4);
    }
}
