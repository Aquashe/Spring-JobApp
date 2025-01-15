package com.thomas.JobApp;

import com.thomas.JobApp.model.JobPost;
import com.thomas.JobApp.service.JobService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JobController {

    public JobService getService() {
        return service;
    }

    @Autowired
    public void setService(JobService service) {
        this.service = service;
    }

    private JobService service;

    /*
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
    */

    @GetMapping("load")
    public List<JobPost> load(){
        service.load();
        return service.getAllJobs();
    }

    @GetMapping("jobPosts")
    public List<JobPost> getAllJobs(){
        System.out.println("All jobs showed");
        return  service.getAllJobs();
    }

    @GetMapping("jobPost/{postId}")
    public JobPost getJob(@PathVariable("postId") int postId){
        return  service.getJob(postId);
    }

    @PostMapping("jobPost")
    public JobPost addJob(@RequestBody JobPost jobPost){
        service.addJob(jobPost);
        return  service.getJob(jobPost.getPostId());
    }

    @PutMapping ("jobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost){
        service.updateJob(jobPost);
        return  service.getJob(jobPost.getPostId());
    }

    @DeleteMapping("jobPost/{postId}")
    public String deleteJob(@PathVariable int postId){
        service.deleteJob(postId);
        return  ("job with postId "+postId+" deleted");
    }

    @GetMapping("jobPost/keyword/{keyword}")
    public List<JobPost> searchKeyword(@PathVariable("keyword") String keyword){
        return service.search(keyword);
    }
}
