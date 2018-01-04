package com.article.controller;

import com.article.entity.User;
import com.article.services.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableCircuitBreaker
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {


    private UserService userService;
    private JobLauncher jobLauncher;
    @Qualifier("/helloUser")
    private Job job;


    @Autowired
    public UserController(UserService userService ,JobLauncher jobLauncher ,Job job) {
        this.userService = userService;
        this.job = job;
        this.jobLauncher = jobLauncher;
    }

    @RequestMapping("/users")
    public List<User> getUsers() {
        return userService.getUserList();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    @HystrixCommand(fallbackMethod = "emptyUserReturn")
    public User getUser(@RequestBody User user) {
        return userService.getUser(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    public User emptyUserReturn(User user) {
        return user;
    }

    @RequestMapping("/helloUser")
    public String helloUser() {
        return "Hello World";
    }

    public void executeAddBatch(String jobName) {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("jobName", jobName).toJobParameters();
        try {
            jobLauncher.run(job, jobParameters).getExitStatus().getExitCode();
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
}
