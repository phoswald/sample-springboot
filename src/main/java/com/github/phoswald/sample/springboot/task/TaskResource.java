package com.github.phoswald.sample.springboot.task;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/tasks")
@Transactional
public class TaskResource {

    @Autowired
    private TaskRepository repository;

    @GetMapping(produces = "application/json")
    public List<TaskEntity> getTasks() {
        List<TaskEntity> entities = repository.selectAllTasks();
        return entities;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public TaskEntity postTasks(@RequestBody TaskEntity request) {
        TaskEntity entity = new TaskEntity();
        entity.setNewTaskId();
        entity.setUserId("guest");
        entity.setTimestamp(Instant.now());
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setDone(request.isDone());
        repository.createTask(entity);
        return entity;
    }
}
