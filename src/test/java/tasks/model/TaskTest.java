package tasks.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private String description;
    private Date startDate;
    private Date endDate;
    private int repeatInterval;
    private Task task;

    @BeforeEach
    void setUp() {
        description = "Task 2";
        startDate = new Date(2020, Calendar.DECEMBER, 15);
        endDate = new Date(2020, Calendar.DECEMBER, 20);
        repeatInterval = 10;
        task = new Task(description, startDate, endDate, repeatInterval);
    }

    @Test
    void getDescription() {
        assertEquals(description, task.getDescription());
    }

    @Test
    void isActive() {
        assertFalse(task.isActive());
    }

    @Test
    void setActive() {
        assertFalse(task.isActive());
        task.setActive(true);
        assertTrue(task.isActive());
    }

    @Test
    void getTime() {
        assertEquals(task.getTime(), startDate);
    }

    @Test
    void setTime() {
        Date newDate = new Date(2021, Calendar.DECEMBER, 15);
        task.setTime(newDate);
        assertEquals(task.getTime(), newDate);
    }

    @Test
    void getStartTime() {
        assertEquals(task.getStartTime(), startDate);
    }

    @Test
    void getEndTime() {
        assertEquals(task.getEndTime(), endDate);
    }

    @Test
    void getRepeatInterval() {
        assertEquals(task.getRepeatInterval(), repeatInterval);
    }

    @Test
    void isRepeated() {
        assertTrue(task.isRepeated());
    }
}
