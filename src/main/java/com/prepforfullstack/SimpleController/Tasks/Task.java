package com.prepforfullstack.SimpleController.Tasks;

import java.time.LocalDate;

public class Task {
    private int id;
    private int task_number;
    private String task_description;
    private String task_master;
    private LocalDate task_deadline;

    public Task(String description, String master, LocalDate deadline){
        task_description = description;
        task_master = master;
        task_deadline = deadline;
    }

    public Task(int id, String description, String master, LocalDate deadline){
        this(description,master,deadline);
        this.id = id;
    }

    public Task(){}

    public int getTaskNumber() {
        return task_number;
    }

    public String getTaskMaster(){
        return task_master;
    }

    public void setTaskMaster(String name){
        task_master = name;
    }

    public String getTaskDescription(){
        return task_description;
    }

    public LocalDate getTaskDeadline(){
        return task_deadline;
    }

    public boolean canUpdateTaskDescription(String user, String newDescription){
        return user.equalsIgnoreCase(task_master) && !task_description.equals(newDescription);
    }

    public void updateTaskDescription(String user, String newDescription){
        if (canUpdateTaskDescription(user,newDescription)){
            task_description = newDescription;
        }
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setTaskNumber(int number){
        task_number = number;
    }

    public void setTaskDeadline(LocalDate deadline){
        task_deadline = deadline;
    }

    public void setTaskDescription(String description){
        task_description = description;
    }
}
