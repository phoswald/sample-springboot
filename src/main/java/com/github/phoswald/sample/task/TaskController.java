package com.github.phoswald.sample.task;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/rest/pages/tasks")
@Transactional
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private HttpServletResponse response;

    @GetMapping(produces = "text/html")
    public ModelAndView getTasksPage() {
        List<TaskEntity> entities = repository.selectAllTasks();
        List<TaskViewModel> viewModel = TaskViewModel.newList(entities);
        return new ModelAndView("task-list", "tasks", viewModel);
    }

    @PostMapping(consumes = "application/x-www-form-urlencoded", produces = "text/html")
    public ModelAndView postTasksPage( //
            @RequestParam(name = "title", required = false) String title, //
            @RequestParam(name = "description", required = false) String description) {
        TaskEntity entity = new TaskEntity();
        entity.setNewTaskId();
        entity.setUserId("guest");
        entity.setTimestamp(Instant.now());
        entity.setTitle(title);
        entity.setDescription(description);
        entity.setDone(false);
        repository.createTask(entity);
        return getTasksPage();
    }

    @GetMapping(path = "/{id}", produces = "text/html")
    public ModelAndView getTaskPage( //
            @PathVariable("id") String id, //
            @RequestParam(name = "action", required = false) String action) {
        TaskEntity entity = repository.selectTaskById(id);
        TaskViewModel viewModel = new TaskViewModel(entity);
        if (Objects.equals(action, "edit")) {
            return new ModelAndView("task-edit", "task", viewModel);
        } else {
            return new ModelAndView("task", "task", viewModel);
        }
    }

    @PostMapping(path = "/{id}", produces = "text/html")
    public ModelAndView postTaskPage( //
            @PathVariable("id") String id, //
            @RequestParam(name = "action", required = false) String action, //
            @RequestParam(name = "title", required = false) String title, //
            @RequestParam(name = "description", required = false) String description, //
            @RequestParam(name = "done", required = false) String done) throws URISyntaxException, IOException {
        TaskEntity entity = repository.selectTaskById(id);
        if (Objects.equals(action, "delete")) {
            repository.deleteTask(entity);
            response.sendRedirect("/rest/pages/tasks");
            return null;
        }
        if (Objects.equals(action, "store")) {
            entity.setTimestamp(Instant.now());
            entity.setTitle(title);
            entity.setDescription(description);
            entity.setDone(Objects.equals(done, "on"));
            repository.updateChanges();
        }
        return getTaskPage(id, null);
    }
}
