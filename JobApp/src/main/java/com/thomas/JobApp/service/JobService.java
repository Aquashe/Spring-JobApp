package com.thomas.JobApp.service;
import com.thomas.JobApp.repo.JobRepo;
import com.thomas.JobApp.model.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    public JobRepo getRepo() {
        return repo;
    }

    @Autowired
    public void setRepo(JobRepo repo) {
        this.repo = repo;
    }

    private JobRepo repo;

    public  void addJob(JobPost job){
        repo.addJob(job);
    }

    public  List<JobPost> getAllJobs()
    {
        return  repo.getAllJobs();
    }



}
