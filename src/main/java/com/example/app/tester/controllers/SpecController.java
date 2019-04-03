package com.example.app.tester.controllers;

import com.example.app.tester.services.SpecEnvironment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping(path = "/specs")
public class SpecController {
    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public String getUploadForm(ModelMap modelMap) {
        return "createTest";
    }

    // TODO REFACTOR

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public String uploadSpec(@RequestParam("solutionFile") MultipartFile file,
                                 HttpServletRequest request) {
        String testsPath = request.getRealPath("tests");

        // get next test id from db
        int id = 1;

        SpecEnvironment specEnvironment = new SpecEnvironment(testsPath, id, file);
        try {
            specEnvironment.setUp();
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
