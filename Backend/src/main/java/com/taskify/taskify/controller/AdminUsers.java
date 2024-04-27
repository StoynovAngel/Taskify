package com.taskify.taskify.controller;

import com.taskify.taskify.dto.ReqRes;
import com.taskify.taskify.model.Task;
import com.taskify.taskify.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUsers {
    @Autowired
    private  TaskRepository taskRepository;
    @GetMapping("/public/task")
    public ResponseEntity<Object> getAllTasks(){
        return ResponseEntity.ok(taskRepository.findAll());
    }
    @PostMapping("/admin/savetask")
    public ResponseEntity<Object> signUp(@RequestBody ReqRes taskRequest){
        Task taskToSave = new Task();
        taskToSave.setTitle(taskRequest.getName());
        return ResponseEntity.ok(taskRepository.save(taskToSave));
    }
    @GetMapping("/user/alone")
    public ResponseEntity<Object> userAlone(){
        return ResponseEntity.ok("Users alone can access this API.");
    }
    @GetMapping("/admin-user/both")
    public ResponseEntity<Object> bothAdminAndUserAPI(){
        return ResponseEntity.ok("Both admin and users can access this API.");
    }
}
