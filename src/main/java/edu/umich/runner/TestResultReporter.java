package edu.umich.runner;

import java.util.HashSet;
import java.util.Set;

public class TestResultReporter {
    private static TestResultReporter instance;
    private Set<Result> testCases;

    private TestResultReporter() {
        testCases = new HashSet<>();
    }

    public static TestResultReporter getInstance() {
        if (instance == null) {
            instance = new TestResultReporter();
        }
        return instance;
    }

    public void addResults(Result result) {
        testCases.add(result);
    }

    public void report() {
        System.out.println("<testsuite tests=\"" + testCases.size() + "\">");
        for (Result testCase : testCases) {
            System.out.println("<testcase classname=\"" + testCase.getClassName() + "\" name=\"" + testCase.getName() + "\">");
            System.out.println("  <failure type=\"failure\">" + testCase.getFailure() + "</failure>");
            System.out.println("<testcase/>");
        }
        System.out.println("<testsuite>");
    }
}
