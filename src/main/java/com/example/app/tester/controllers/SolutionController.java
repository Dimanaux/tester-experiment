package com.example.app.tester.controllers;


import com.example.app.tester.db.entities.Solution;
import com.example.app.tester.services.SpecService;
import com.example.app.tester.services.TestEnvironment;
import com.example.app.tester.services.os.system.BlockingContextCommand;
import com.example.app.tester.services.os.system.SystemCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(path = "/tests")
public class SolutionController {

    @Autowired
    SpecService specService;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String getUploadForm(@PathVariable(name = "id") int specId,
                                ModelMap modelMap) {
        // get spec info from com.example.app.tester.db
        modelMap.put("specId", specId);
        modelMap.put("spec", specService.findById(specId));
        return "uploadSolution";
    }

    @ResponseBody
    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public String uploadSolution(@PathVariable("id") long specId,
                                 @RequestParam("solutionFile") MultipartFile solutionFile,
                                 @RequestParam("username") String username,
                                 HttpServletRequest request) throws IOException {
        String testsPath = request.getRealPath("tests");


        Solution solution = new Solution();
        solution.setAuthorName(username);
        solution.setTestId(specId);

        specService.createSolution(testsPath, solution, solutionFile);

        return String.format("<pre>%s</pre>", solution.getResult());
    }
}
