package tasks.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.Task;
import tasks.repository.ArrayTaskList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class DateServiceTest {
    private ArrayTaskList arrayTaskList;
    private TasksService tasksService;

    @BeforeEach
    void setUp() {
        arrayTaskList = new ArrayTaskList();
        tasksService = new TasksService(arrayTaskList);
    }

    @AfterEach
    void tearDown() {
        tasksService = null;
        arrayTaskList = null;
    }

    @Test
    void test_1() {
        assertEquals(tasksService.filterTasks(new Date(), new Date()), new ArrayList<Task>());
    }

    @Test
    void test_2() {
        Task t = new Task("Task 1", new Date(2020, Calendar.DECEMBER, 10), new Date(2020, Calendar.DECEMBER, 30), 30);
        t.setActive(true);
        arrayTaskList.add(t);
        t = new Task("Task 2", new Date(2020, Calendar.DECEMBER, 15));
        t.setActive(true);
        arrayTaskList.add(t);
        t = new Task("Task 3", new Date(2020, Calendar.DECEMBER, 25));
        t.setActive(true);
        arrayTaskList.add(t);
        assertEquals(tasksService.filterTasks(new Date(2020, Calendar.DECEMBER, 17), new Date(2020, Calendar.DECEMBER, 27)).size(), 2);
    }

    @Test
    void test_3() {
        Task t = new Task("Task 1", new Date(2020, Calendar.DECEMBER, 10), new Date(2020, Calendar.DECEMBER, 30), 30);
        t.setActive(true);
        arrayTaskList.add(t);
        t = new Task("Task 3", new Date(2020, Calendar.DECEMBER, 25));
        t.setActive(true);
        arrayTaskList.add(t);
        assertEquals(tasksService.filterTasks(new Date(2020, Calendar.DECEMBER, 17), new Date(2020, Calendar.DECEMBER, 27)).size(), 2);
    }

    @Test
    void test_4() {
        Task t = new Task("Task 1", new Date(2020, Calendar.OCTOBER, 10), new Date(2020, Calendar.OCTOBER, 30), 30);
        t.setActive(true);
        arrayTaskList.add(t);
        t = new Task("Task 2", new Date(2020, Calendar.DECEMBER, 15));
        t.setActive(true);
        arrayTaskList.add(t);
        t = new Task("Task 3", new Date(2020, Calendar.DECEMBER, 16));
        t.setActive(true);
        arrayTaskList.add(t);
        assertEquals(tasksService.filterTasks(new Date(2020, Calendar.DECEMBER, 17), new Date(2020, Calendar.DECEMBER, 27)).size(), 0);
    }
}
