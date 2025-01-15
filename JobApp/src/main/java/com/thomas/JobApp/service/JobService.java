package com.thomas.JobApp.service;
import com.thomas.JobApp.repo.JobRepo;
import com.thomas.JobApp.model.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {


    List<JobPost> jobs = new ArrayList<JobPost>();


    public JobRepo getRepo() {
        return repo;
    }

    @Autowired
    public void setRepo(JobRepo repo) {
        this.repo = repo;
    }

    private JobRepo repo;

    public  void addJob(JobPost job){
        System.out.println("Job with postId "+job.getPostId()+" added");
        repo.save(job);
    }

    public  List<JobPost> getAllJobs()
    {
        return  repo.findAll();
    }

    public JobPost getJob(int postId)
    {
        return repo.findById(postId).orElse( new JobPost());
    }

    public void updateJob(JobPost jobPost)
    {
        System.out.println("Job with postId "+jobPost.getPostId()+" updated");
         repo.save(jobPost);
    }

    public void deleteJob(int postId){
        System.out.println("Job with postId "+postId+" deleted");
        repo.deleteById(postId);
    }

    public void load(){
        jobs.add(new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2,
                List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")));
        // Frontend Developer Job Post
        jobs.add(
                new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React",
                        3, List.of("HTML", "CSS", "JavaScript", "React")));

        // Data Scientist Job Post
        jobs.add(new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4,
                List.of("Python", "Machine Learning", "Data Analysis")));

        // Network Engineer Job Post
        jobs.add(new JobPost(4, "Network Engineer",
                "Design and implement computer networks for efficient data communication", 5,
                List.of("Networking", "Cisco", "Routing", "Switching")));

        // Mobile App Developer Job Post
        jobs
                .add(new JobPost(5, "Mobile App Developer", "Experience in mobile app development for iOS and Android",
                        3, List.of("iOS Development", "Android Development", "Mobile App")));

        // DevOps Engineer Job Post
        jobs.add(
                new JobPost(6, "DevOps Engineer", "Implement and manage continuous integration and delivery pipelines",
                        4, List.of("DevOps", "CI/CD", "Docker", "Kubernetes")));


        repo.saveAll(jobs);


    }


    public List<JobPost> search(String keyword) {
        return repo.findByPostProfileContainingOrPostDescContaining(keyword ,keyword);
    }
}
