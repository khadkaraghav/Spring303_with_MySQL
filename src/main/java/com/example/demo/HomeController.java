package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

@Autowired
    CourseRepository courseRepository;      // autowired - no need to create object of this and use in methods


@RequestMapping("/")
    public String courseList(Model model){    // main homepage

        model.addAttribute("courses", courseRepository.findAll());
        return "joblist";
    }



    @GetMapping("/add")                              // adding the attributes and putting values to h2 database
    public String addCourseForm(Model model){

        model.addAttribute("course", new Course());
        return "courseform";
    }


    @PostMapping("/process")                        // Validation operation
    public String processForm(@Valid Course course, BindingResult result){

if (result.hasErrors()){
    return "courseform";
}

courseRepository.save(course);
return "redirect:/";
    }


    @RequestMapping("/detail/{id}")                             // display details
    public String showCourse(@PathVariable("id") long id, Model model){

        model.addAttribute("course", courseRepository.findById(id).get());
        return "show";
    }


    @RequestMapping("/update/{id}")                             // update
    public String updateCourse(@PathVariable("id") long id, Model model){

        model.addAttribute("course", courseRepository.findById(id));
        return "courseform";
    }


    @RequestMapping("/delete/{id}")                             // update
    public String delCourse(@PathVariable("id") long id){

courseRepository.deleteById(id);
return "redirect:/";
    }
}
