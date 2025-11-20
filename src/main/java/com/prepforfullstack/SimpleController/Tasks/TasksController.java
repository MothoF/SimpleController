package com.prepforfullstack.SimpleController.Tasks;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
@SessionAttributes("username")
public class TasksController {
    private TasksService tasksService;

    @Autowired
    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @RequestMapping(value = "tasks",method = RequestMethod.GET)
    public String getTasksList(Model model){
        String sessionUser;
        try{
            sessionUser = Objects.requireNonNull(model.getAttribute("username")).toString();
        } catch (NullPointerException e){
            sessionUser = getLoggedInUser();
        }
//        String sessionUser = "Mothofeela";
        List<Task> userTasks = tasksService.getTaskByUser(sessionUser);

        System.out.println("sessionUser = " + sessionUser);
        model.addAttribute("username",sessionUser);
        model.addAttribute("tasks", userTasks);
        return "tasks_display";
    }

    @RequestMapping(value = "add_task", method = {RequestMethod.GET})
    public String addTask(Model model){
        Task task = new Task("Anonymous Description","ManMan",LocalDate.now());
        model.addAttribute("task",task);
        return "add_task";
    }

    @RequestMapping(value = "tasks", method = RequestMethod.POST)
    public String addTask(Model model, @Valid Task task, BindingResult result){
        if (result.hasErrors()){
            return "add_task";
        }

        String sessionUser;
        try{
            sessionUser = Objects.requireNonNull(model.getAttribute("username")).toString();
        } catch (NullPointerException e){
            sessionUser = getLoggedInUser();
        }
//        String sessionUser = "Mothofeela";
        task.setTaskMaster(sessionUser);
        System.out.println("Task master has been set to: " + task.getTaskMaster());
        tasksService.addNewTask(task);
        System.out.println("You have successfully added a new task @"+sessionUser);
        return "redirect:/tasks";
    }

    @RequestMapping(value = "task", method = RequestMethod.GET)
    public String getTaskById(@RequestParam String id, Model model){
        int taskId =  Integer.parseInt(id);
        Task task = tasksService.getTaskById(taskId);
        model.addAttribute("task",task);
        return "view_task";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteTaskById(@RequestParam String id){
        int taskId =  Integer.parseInt(id);
        tasksService.deleteTaskById(taskId);
        return "redirect:/tasks";
    }

    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String getTaskUpdateForm(@RequestParam String id, Model model){
        int taskId =  Integer.parseInt(id);
        Task task = tasksService.getTaskById(taskId);
        model.addAttribute("task",task);
        return "update_task";
    }

    @RequestMapping(value = "task", method = RequestMethod.POST)
    public String updateTask(@RequestParam int id, @Valid Task task, BindingResult result){
        if (result.hasErrors()){
            return "update_task";
        }

        Task oldTask = tasksService.getTaskById(id);
        assert oldTask != null;

        tasksService.updateTask(oldTask,task);

        return "redirect:/tasks";
    }

    private String getLoggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assert auth != null;
        return auth.getName();
    }
}