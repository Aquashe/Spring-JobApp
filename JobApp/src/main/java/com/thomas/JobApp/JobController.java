package com.thomas.JobApp;

import com.thomas.JobApp.model.JobPost;
import com.thomas.JobApp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JobController {

    public JobService getService() {
        return service;
    }

    @Autowired
    public void setService(JobService service) {
        this.service = service;
    }

    private JobService service;

    @RequestMapping({"/","home"})
    public String home(){
        System.out.println("Home called");
        return  "home";
    }

    @GetMapping("addjob")
    public String addJob(){
        System.out.println( "add job called ");
        return  "addjob";
    }

    @PostMapping("handleForm")
    public  String handleForm(JobPost jobPost){
        System.out.println("Job post called ");
        service.addJob(jobPost);
        return  "success";
    }

    @GetMapping("viewalljobs")
    public  ModelAndView viewalljobs(ModelAndView mv){
        mv.addObject("jobPosts",service.getAllJobs());
        mv.setViewName("viewalljobs");
        return mv;
    }
}
