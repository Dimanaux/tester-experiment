package com.example.app.tester.controllers;

import com.example.app.tester.db.entities.Test;
import com.example.app.tester.services.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping(path = "/specs")
public class SpecController {
    private final SpecService specService;

    @Autowired
    public SpecController(SpecService specService) {
        this.specService = specService;
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public String getUploadForm() {
        return "createTest";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String uploadSpec(@RequestParam("specFile") MultipartFile specFile,
                             @RequestParam("title") String title,
                             @RequestParam("description") String description,
                             ModelMap modelMap,
                             HttpServletRequest request) {
        String testsPath = request.getRealPath("tests");

        Test test = new Test();
        test.setTitle(title);
        test.setDescription(description);

        try {
            specService.createTest(testsPath, specFile, test);
            modelMap.put("specId", test.getId());
            return "shareSpec";
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/specs/new?error=something-went-wrong";
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String specs(ModelMap modelMap) {
        modelMap.put("specs", specService.findAll());
        return "specs";
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String results(@PathVariable("id") long specId, ModelMap modelMap) {
        modelMap.put("solutions", specService.findBySpecId(specId));
        return "results";
    }
}
