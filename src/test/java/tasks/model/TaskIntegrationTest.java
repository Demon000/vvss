package tasks.model;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.repository.ArrayTaskList;
import tasks.services.TasksService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskIntegrationTest {
    private Date beforeStartDate;
    private Date startDate;
    private Date midDate;
    private Date endDate;
    private Task task1;
    private Task task2;
    private Task task3;
    private TasksService tasksService;

    @BeforeEach
    void setUp() {
        ArrayTaskList arrayTaskList = new ArrayTaskList();

        beforeStartDate = new Date(2020, Calendar.DECEMBER, 10);
        startDate = new Date(2020, Calendar.DECEMBER, 15);
        midDate = new Date(2020, Calendar.DECEMBER, 20);
        endDate = new Date(2020, Calendar.DECEMBER, 25);

        task1 = new Task("test 1", beforeStartDate);
        task2 = new Task("test 2", midDate);
        task3 = new Task("test 3", midDate);

        task1.setActive(true);
        task2.setActive(true);
        task3.setActive(true);

        arrayTaskList.add(task1);
        arrayTaskList.add(task2);
        arrayTaskList.add(task3);

        tasksService = new TasksService(arrayTaskList);
    }

    @Test
    void getObservableList() {
        ObservableList<Task> tasksList = tasksService.getObservableList();
        assertEquals(tasksList.get(0), task1);
        assertEquals(tasksList.get(1), task2);
        assertEquals(tasksList.get(2), task3);
        assertEquals(3, tasksList.size());
    }

    @Test
    void filterTasks() {
        List<Task> tasksList = tasksService.filterTasks(startDate, endDate);
        assertEquals(tasksList.get(0), task2);
        assertEquals(tasksList.get(1), task3);
    }
}
