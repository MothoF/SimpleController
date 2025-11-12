package com.prepforfullstack.SimpleController.Tasks;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class TasksService {
    private List<Task> allTasks = new ArrayList<>();

    public TasksService() {
        allTasks.add(new Task(1,"Resume spring boot studies","Mothofeela", LocalDate.of(2025,12,31)));
        allTasks.add(new Task(2,"Begin studying React","Mothofeela", LocalDate.of(2026,1,10)));
        allTasks.add(new Task(3,"Revise C#","Lakabane", LocalDate.of(2026,2,15)));
        allTasks.add(new Task(4,"Improve my JavaScript","Lakabane", LocalDate.of(2026,3,9)));
    }

    public void addNewTask(Task task){
        int taskId = task.getId();
        int id = taskId == 0 ? allTasks.size() + 1 : taskId;
        task.setId(id);
        allTasks.add(task);
    }

//    public List<Task> getAllTasks(){
//        return allTasks;
//    }

    public List<Task> getTaskByUser(String userName){
        List<Task> tasks = new ArrayList<>();
        for(Task task : allTasks){
            if (task.getTaskMaster().equals(userName)){
                tasks.add(task);
            }
        }
        return tasks;
    }
}
