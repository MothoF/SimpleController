package com.prepforfullstack.SimpleController.Tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        String sessionUser = Objects.requireNonNull(model.getAttribute("username")).toString();
        List<Task> userTasks = tasksService.getTaskByUser(sessionUser);
        int idForTask = 0;

        for (Task task : userTasks){
            idForTask += 1;
            task.setTaskNumber(idForTask);
        }

        System.out.println("sessionUser = " + sessionUser);
        model.addAttribute("username",sessionUser);
        model.addAttribute("tasks", userTasks);
        return "tasks_display";
    }

    @RequestMapping(value = "add_task", method =  {RequestMethod.GET, RequestMethod.POST})
    public String addTask(Model model){
        Task task = new Task("Anonymous Description","ManMan",LocalDate.now());
        model.addAttribute("task",task);
        return "add_task";
    }

    @RequestMapping(value = "tasks", method = RequestMethod.POST)
    public String addTask(Model model, Task task){
        String sessionUser = Objects.requireNonNull(model.getAttribute("username")).toString();
        task.setTaskMaster(sessionUser);
        tasksService.addNewTask(task);
        System.out.println("You have successfully added a new task @"+sessionUser);
        return "redirect:/tasks";
    }

//    @RequestMapping(value = "add_task", method =  {RequestMethod.GET, RequestMethod.POST})
//    public String addTask(){
//        return "add_task";
//    }
//
//    @RequestMapping(value = "tasks", method = RequestMethod.POST)
//    public String addTask(@RequestParam String description, @RequestParam String deadline, Model model ){
//        String sessionUser = Objects.requireNonNull(model.getAttribute("username")).toString();
//        LocalDate deadlineDate = LocalDate.parse(deadline);
//        System.out.println("Deadline date = " + deadlineDate);
//        tasksService.addNewTask(new Task(description,sessionUser, LocalDate.parse(deadlineDate.toString())));
//        System.out.println("You have successfully added a new task @"+sessionUser);
//        return "redirect:/tasks";
//    }
}