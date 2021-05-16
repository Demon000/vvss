package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tasks.model.Task;
import tasks.repository.ArrayTaskList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TasksService {
    private final ArrayTaskList tasks;

    public TasksService(ArrayTaskList tasks) {
        this.tasks = tasks;
    }

    public ObservableList<Task> getObservableList() {
        return FXCollections.observableArrayList(tasks.getAll());
    }

    public String getIntervalInHours(Task task) {
        int seconds = task.getRepeatInterval();
        int minutes = seconds / DateService.SECONDS_IN_MINUTE;
        int hours = minutes / DateService.MINUTES_IN_HOUR;
        minutes = minutes % DateService.MINUTES_IN_HOUR;
        return formTimeUnit(hours) + ":" + formTimeUnit(minutes);//hh:MM
    }

    public String formTimeUnit(int timeUnit) {
        StringBuilder sb = new StringBuilder();
        if (timeUnit < 10) sb.append("0");
        if (timeUnit == 0) sb.append("0");
        else {
            sb.append(timeUnit);
        }
        return sb.toString();
    }


    public int parseFromStringToSeconds(String stringTime) {//hh:MM
        String[] units = stringTime.split(":");
        int hours = Integer.parseInt(units[0]);
        int minutes = Integer.parseInt(units[1]);
        int totalMinuets = hours * DateService.MINUTES_IN_HOUR + minutes;
        return totalMinuets * DateService.SECONDS_IN_MINUTE;
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
