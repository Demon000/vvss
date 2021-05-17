package tasks.repository;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tasks.model.Task;
import tasks.services.TasksService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ArrayTaskListIntegrationTest {
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

        startDate = new Date(2020, Calendar.DECEMBER, 15);
        midDate = new Date(2020, Calendar.DECEMBER, 20);
        endDate = new Date(2020, Calendar.DECEMBER, 25);

        task1 = mock(Task.class);
        Mockito.when(task1.nextTimeAfter(startDate)).thenReturn(null);

        task2 = mock(Task.class);
        Mockito.when(task2.nextTimeAfter(startDate)).thenReturn(midDate);

        task3 = mock(Task.class);
        Mockito.when(task3.nextTimeAfter(startDate)).thenReturn(midDate);

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
