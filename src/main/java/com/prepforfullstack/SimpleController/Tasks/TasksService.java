package com.prepforfullstack.SimpleController.Tasks;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public Task getTaskById(int id){
        return allTasks.get(id-1);
    }

    public void deleteTaskById(int id){
        allTasks.remove(id-1);

        for (Task task : allTasks){
            if(task.getId() > id){
                int taskId = task.getId();
                task.setId(taskId-1);
            }
        }
    }

    public void updateTask(Task outdatedTask,  Task updatedTask){
        if (!updatedTask.getTaskDescription().equals(outdatedTask.getTaskDescription()) && !updatedTask.getTaskDeadline().equals(outdatedTask.getTaskDeadline())){
            outdatedTask.setTaskDescription(updatedTask.getTaskDescription());
            outdatedTask.setTaskDeadline(updatedTask.getTaskDeadline());
        } else if (!updatedTask.getTaskDescription().equals(outdatedTask.getTaskDescription())){
            outdatedTask.setTaskDescription(updatedTask.getTaskDescription());
        } else if (!updatedTask.getTaskDeadline().equals(outdatedTask.getTaskDeadline())){
            outdatedTask.setTaskDeadline(updatedTask.getTaskDeadline());
        }
    }

    public List<Task> getTaskByUser(String userName){
        List<Task> tasks = new ArrayList<>();
        for(Task task : allTasks){
            if (task.getTaskMaster().equals(userName)){
                task.setTaskNumber(tasks.size()+1);
                tasks.add(task);
            }
        }
        return tasks;
    }
}
