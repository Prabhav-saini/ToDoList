package org.example.Services;

import org.example.Dao.TaskDao;
import org.example.Entities.Task;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {

    final String taskDoesNotExist = "Task Does Not Exist With Id, Please Try Again With Correct Id";

    private final TaskDao taskDao;
    private final UserService userService;

    public TaskService(TaskDao taskDao, UserService userService) {
        this.taskDao = taskDao;
        this.userService = userService;
    }

    @Transactional // This can manage the transaction for createTask
    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setStatus(Task.Status.TODO);
        return taskDao.createTask(task);
    }

    public Task prepareTaskToCreate(BufferedReader br) throws IOException {
        Task task = new Task();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("Enter Title Of This Task : ");
        task.setTitle(br.readLine());
        System.out.println("Write A Short Description About This Task : ");
        task.setDescription(br.readLine());
        System.out.println("Enter Due Date Of This Task In Given Format (yyyy-MM-dd HH:mm) : ");
        task.setDueDate(LocalDateTime.parse(br.readLine(), formatter));
        System.out.println("Enter Priority Of This Task From Given Priority Options (LOW, MEDIUM, HIGH) : ");
        task.setPriority(Task.Priority.valueOf(br.readLine().toUpperCase()));
        System.out.println("Enter Email_Id Of Assignee Of This Task");
        task.setAssignee(userService.getUserIfExistOrCreate(br.readLine(), br));

        return task;
    }

    public Task prepareTaskToUpdate(Task task, BufferedReader br) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("Update Title Of This Task : ");
        task.setTitle(br.readLine());
        System.out.println("Update Description About This Task : ");
        task.setDescription(br.readLine());
        System.out.println("Update Due Date Of This Task In Given Format (yyyy-MM-dd HH:mm) : ");
        task.setDueDate(LocalDateTime.parse(br.readLine(), formatter));
        System.out.println("Update Priority Of This Task From Given Priority Options (LOW, MEDIUM, HIGH) : ");
        task.setPriority(Task.Priority.valueOf(br.readLine().toUpperCase()));
        System.out.println("Update Assignee Of This Task");
        task.setAssignee(userService.getUserIfExistOrCreate(br.readLine(), br));

        return task;
    }

    @Transactional
    public String updateTask(int id, BufferedReader br) throws IOException {
        Task task = readtask(id);
        if (Objects.nonNull(task)) {
            Task updatedTask = prepareTaskToUpdate(task, br);
            updatedTask.setId(task.getId());
            updatedTask.setUpdatedAt(LocalDateTime.now());
            taskDao.updateTask(updatedTask);
            return String.format("Task Is SuccessFully Updated With Id : %s", updatedTask.getId());
        } else {
            return taskDoesNotExist;
        }
    }

    @Transactional
    public Task readtask(int id) {
        return taskDao.viewTaskById(id);
    }

    public String deleteTask(int id) {
        Task task = readtask(id);
        if (Objects.nonNull(task)) {
            taskDao.deleteTask(task);
            return String.format("Task Deleted Successfully With Id : %s", id);
        } else {
            return taskDoesNotExist;
        }
    }

    public void viewTask(int id) {
        Task task = taskDao.viewTaskById(id);
        System.out.println(task.toString());
    }

    public void viewAllTasks() {
        List<Task> tasks = taskDao.getAllTasks();
        tasks.stream().map(Task::toString)
                .forEach(System.out::println);
    }
}
