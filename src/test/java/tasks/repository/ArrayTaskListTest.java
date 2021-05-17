package tasks.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.Task;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ArrayTaskListTest {
    private ArrayTaskList taskList;
    private Task task1;
    private Task task2;
    private Task task3;
    private Task task4;

    @BeforeEach
    void setUp() {
        taskList = new ArrayTaskList();
        task1 = mock(Task.class);
        task2 = mock(Task.class);
        task3 = mock(Task.class);
        task4 = mock(Task.class);
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
    }

    @Test
    void iterator() {
        Iterator<Task> iterator = taskList.iterator();
        assertEquals(task1, iterator.next());
        assertEquals(task2, iterator.next());
        assertEquals(task3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void add() {
        taskList.add(task4);
        assertEquals(task4, taskList.getTask(3));
    }

    @Test
    void remove() {
        Task task = taskList.getTask(2);
        taskList.remove(task);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.getTask(2);
        });
    }

    @Test
    void size() {
        assertEquals(3, taskList.size());
    }

    @Test
    void getTask() {
        assertEquals(task1, taskList.getTask(0));
    }

    @Test
    void getAll() {
        List<Task> tasks = taskList.getAll();
        assertEquals(task1, tasks.get(0));
        assertEquals(task2, tasks.get(1));
        assertEquals(task3, tasks.get(2));
    }
}
