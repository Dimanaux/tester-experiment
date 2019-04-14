package com.example.app.tester.services;

import com.example.app.tester.db.entities.Solution;
import com.example.app.tester.db.entities.Test;
import com.example.app.tester.db.repositories.SolutionRepository;
import com.example.app.tester.db.repositories.TestRepository;
import com.example.app.tester.services.os.system.BlockingContextCommand;
import com.example.app.tester.services.os.system.SystemCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class SpecService {
    private final TestRepository testRepository;
    private final SolutionRepository solutionRepository;

    @Autowired
    public SpecService(TestRepository testRepository, SolutionRepository solutionRepository) {
        this.testRepository = testRepository;
        this.solutionRepository = solutionRepository;
    }

    public void createTest(String testsPath, MultipartFile specFile, Test test) throws IOException {
        testRepository.save(test);

        SpecEnvironment specEnvironment = new SpecEnvironment(testsPath, test.getId(), specFile);
        specEnvironment.setUp();
    }

    public void createSolution(String testsPath, Solution solution, MultipartFile solutionFile) throws IOException {
        solutionRepository.save(solution);

        SystemCommand env = new BlockingContextCommand(new File(testsPath), "./env.sh");
        env.run(
                String.valueOf(solution.getTestId()),
                String.valueOf(solution.getId())
        );

        TestEnvironment testEnvironment = new TestEnvironment(testsPath, solution.getTestId(), solution.getId(), solutionFile);
        testEnvironment.setUp();

        SystemCommand junitRunner = new BlockingContextCommand(
                new File(testEnvironment.getRootPath()),
                "../../junit.sh"
        );
        junitRunner.run();

        solution.setResult(junitRunner.getOutput());
        solutionRepository.save(solution);
    }

    public Test findTestById(long testId) {
        return testRepository.findById(testId).orElse(new Test("something went wrong"));
    }

    public List<Solution> findBySpecId(long specId) {
        return solutionRepository.findAllByTestId(specId);
    }

    public List<Test> findAll() {
        return testRepository.findAll();
    }

    public Test findById(long specId) {
        return testRepository.findById(specId).orElse(new Test() {{
            setDescription("no description");
        }});
    }
}
