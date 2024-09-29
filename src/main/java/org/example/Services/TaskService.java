package org.example.Services;

import org.example.Dao.TaskDao;
import org.example.Entities.Task;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TaskService {

    private final TaskDao taskDao;

    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Transactional // This can manage the transaction for createTask
    public int createTask(Task task) {
        return taskDao.createTask(task);
    }
}
