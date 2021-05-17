package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tasks.model.Task;
import tasks.repository.TaskList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TasksService {
    private final TaskList tasks;

    public TasksService(TaskList tasks) {
        this.tasks = tasks;
    }

    public ObservableList<Task> getObservableList() {
        return FXCollections.observableArrayList(tasks.getAll());
    }

    public List<Task> filterTasks(Date start, Date end) {
        ArrayList<Task> incomingTasks = new ArrayList<>();
        for (Task t : getObservableList()) {
            Date nextTime = t.nextTimeAfter(start);
            if (nextTime != null && (nextTime.before(end) || nextTime.equals(end))) {
                incomingTasks.add(t);
            }
        }
        return incomingTasks;
    }
}
