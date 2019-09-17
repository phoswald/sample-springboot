package com.github.phoswald.sample.springboot.task;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskRepository {

    @Autowired
    @PersistenceContext(unitName="taskDS")
    private EntityManager em;

    public List<TaskEntity> selectAllTasks() {
        TypedQuery<TaskEntity> query = em.createNamedQuery(TaskEntity.SELECT_ALL, TaskEntity.class);
        query.setMaxResults(100);
        return query.getResultList();
    }

    public TaskEntity selectTaskById(String taskId) {
        return em.find(TaskEntity.class, taskId);
    }

    public void createTask(TaskEntity entity) {
        em.persist(entity);
    }

    public void deleteTask(TaskEntity entity) {
        em.remove(entity);
    }

    public void updateChanges() {
        em.flush();
    }
}
