package tasks.utils;


import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import tasks.model.Task;
import tasks.repository.LinkedTaskList;
import tasks.repository.TaskList;
import tasks.view.Main;

import java.io.*;
import java.util.Date;

public class TaskIO {
    private static final String[] TIME_ENTITY = {" day", " hour", " minute", " second"};
    private static final int SECONDS_IN_DAY = 86400;
    private static final int SECONDS_IN_HOUR = 3600;
    private static final int SECONDS_IN_MIN = 60;

    private static final Logger log = Logger.getLogger(TaskIO.class.getName());

    public static void write(TaskList tasks, OutputStream out) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(out)) {
            dataOutputStream.writeInt(tasks.size());
            for (Task t : tasks) {
                dataOutputStream.writeInt(t.getDescription().length());
                dataOutputStream.writeUTF(t.getDescription());
                dataOutputStream.writeBoolean(t.isActive());
                dataOutputStream.writeInt(t.getRepeatInterval());
                if (t.isRepeated()) {
                    dataOutputStream.writeLong(t.getStartTime().getTime());
                    dataOutputStream.writeLong(t.getEndTime().getTime());
                } else {
                    dataOutputStream.writeLong(t.getTime().getTime());
                }
            }
        }
    }

    public static void read(TaskList tasks, InputStream in) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(in)) {
            int listLength = dataInputStream.readInt();
            for (int i = 0; i < listLength; i++) {
                dataInputStream.readInt();
                String description = dataInputStream.readUTF();
                boolean isActive = dataInputStream.readBoolean();
                int interval = dataInputStream.readInt();
                Date startTime = new Date(dataInputStream.readLong());
                Task taskToAdd;
                if (interval > 0) {
                    Date endTime = new Date(dataInputStream.readLong());
                    taskToAdd = new Task(description, startTime, endTime, interval);
                } else {
                    taskToAdd = new Task(description, startTime);
                }
                taskToAdd.setActive(isActive);
                tasks.add(taskToAdd);
            }
        }
    }

    public static void writeBinary(TaskList tasks, File file) {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            write(tasks, fos);
        } catch (IOException e) {
            log.error("IO exception reading or writing file");
        }
    }

    public static void readBinary(TaskList tasks, File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            read(tasks, fis);
        } catch (IOException e) {
            log.error("IO exception reading or writing file");
        }
    }

    public static String getFormattedInterval(int interval) {
        if (interval <= 0) throw new IllegalArgumentException("Interval <= 0");
        StringBuilder sb = new StringBuilder();

        int days = interval / SECONDS_IN_DAY;
        int hours = (interval - SECONDS_IN_DAY * days) / SECONDS_IN_HOUR;
        int minutes = (interval - (SECONDS_IN_DAY * days + SECONDS_IN_HOUR * hours)) / SECONDS_IN_MIN;
        int seconds = (interval - (SECONDS_IN_DAY * days + SECONDS_IN_HOUR * hours + SECONDS_IN_MIN * minutes));

        int[] time = new int[]{days, hours, minutes, seconds};
        int i = 0;
        int j = time.length - 1;
        while (time[i] == 0 || time[j] == 0) {
            if (time[i] == 0) i++;
            if (time[j] == 0) j--;
        }

        for (int k = i; k <= j; k++) {
            sb.append(time[k]);
            sb.append(time[k] > 1 ? TIME_ENTITY[k] + "s" : TIME_ENTITY[k]);
            sb.append(" ");
        }

        return sb.toString();
    }


    public static void rewriteFile(ObservableList<Task> tasksList) {
        LinkedTaskList taskList = new LinkedTaskList();
        for (Task t : tasksList) {
            taskList.add(t);
        }

        writeBinary(taskList, Main.savedTasksFile);
    }
}
