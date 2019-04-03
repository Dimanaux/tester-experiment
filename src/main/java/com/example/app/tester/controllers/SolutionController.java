package com.example.app.tester.controllers;


import com.example.app.tester.services.ContextCommand;
import com.example.app.tester.services.TestEnvironment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(path = "/tests")
public class SolutionController {
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String getUploadForm(@PathVariable(name = "id") int specId,
                                ModelMap modelMap) {
        // get spec info from db
        modelMap.put("specId", specId);
        return "uploadSolution";
    }

    // TODO REFACTOR

    @ResponseBody
    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public String uploadSolution(@PathVariable(name = "id") int specId,
                                 @RequestParam MultipartFile solutionFile,
                                 HttpServletRequest request) throws IOException {
        // get next solution id
        // 2
        int solutionId = 2;

        String testsPath = request.getRealPath("tests");

        ContextCommand env = new ContextCommand(new File(testsPath), "env.sh");
        env.run(
                String.valueOf(specId),
                String.valueOf(solutionId)
        );

        TestEnvironment testEnvironment = new TestEnvironment(testsPath, specId, solutionId, solutionFile);
        testEnvironment.setUp();

        ContextCommand junitRunner = new ContextCommand(
                new File(testEnvironment.getRootPath()),
                "../../junit.sh"
        );
        junitRunner.run();


        return junitRunner.getOutput();
    }
}
