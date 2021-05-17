package tasks.repository;

import tasks.model.Task;

import java.io.Serializable;
import java.util.List;

public interface TaskList extends Iterable<Task>, Serializable {
    void add(Task task);
    void remove(Task task);
    int size();
    Task getTask(int index);
    List<Task> getAll();
}
