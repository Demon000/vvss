package tasks.model;

import javafx.collections.ObservableList;

import java.util.*;

public class TasksOperations {

    public ArrayList<Task> tasks;

    public TasksOperations(ObservableList<Task> tasksList){
        tasks=new ArrayList<>();
        tasks.addAll(tasksList);
    }

    public Iterable<Task> incoming(Date start, Date end){
        System.out.println(start);
        System.out.println(end);
        ArrayList<Task> incomingTasks = new ArrayList<>();
        for (Task t : tasks) {
            Date nextTime = t.nextTimeAfter(start);
            if (nextTime != null && (nextTime.before(end) || nextTime.equals(end))) {
                incomingTasks.add(t);
                System.out.println(t.getDescription());
            }
        }
        return incomingTasks;
    }
}
