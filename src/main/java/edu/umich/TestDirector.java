package edu.umich;

import edu.umich.runner.TestResultReporter;
import edu.umich.tests.LookAndFeelTests;
import edu.umich.runner.FunctionalTestRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDirector implements CommandLineRunner {

    @Autowired
    private FunctionalTestRunner runner;
    
    @Override
    public void run(String... args) throws Exception {
        runner.runForClass(LookAndFeelTests.class);
        TestResultReporter.getInstance().report();
        System.err.println("run() completed and leaving context");
    }
}
