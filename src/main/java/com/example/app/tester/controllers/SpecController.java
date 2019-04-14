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
    public String getUploadForm() {
        return "createTest";
    }

    // TODO REFACTOR
    @RequestMapping(method = RequestMethod.POST)
    public String uploadSpec(@RequestParam("specFile") MultipartFile file,
                             ModelMap modelMap,
                             HttpServletRequest request) {
        String testsPath = request.getRealPath("tests");

        // get next spec/test id from com.example.app.tester.db
        int id = 32;

        SpecEnvironment specEnvironment = new SpecEnvironment(testsPath, id, file);
        try {
            specEnvironment.setUp();
            modelMap.put("specId", id);
            return "shareSpec";
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/specs/new";
        }
    }
}
