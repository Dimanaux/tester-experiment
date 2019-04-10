package com.example.app.tester.services.tests;

/**
 * Simple test interface.
 */
public interface Test {
    /**
     * Runs the test.
     */
    void run();

    /**
     * @return results of the test.
     */
    String getOutput();
}
