package tasks.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.log4j.Logger;
import tasks.model.Task;


public class TaskInfoController {

    private static final Logger log = Logger.getLogger(TaskInfoController.class.getName());
    @FXML
    private Label labelDescription;
    @FXML
    private Label labelStart;
    @FXML
    private Label labelEnd;
    @FXML
    private Label labelInterval;
    @FXML
    private Label labelIsActive;

    @FXML
    public void initialize() {
        log.info("task info window initializing");
        Task currentTask = Controller.mainTable.getSelectionModel().getSelectedItem();
        labelDescription.setText("Title: " + currentTask.getDescription());
        labelStart.setText("Start time: " + currentTask.getFormattedDateStart());
        labelEnd.setText("End time: " + currentTask.getFormattedDateEnd());
        labelInterval.setText("Interval: " + currentTask.getFormattedRepeated());
        labelIsActive.setText("Is active: " + (currentTask.isActive() ? "Yes" : "No"));
    }

    @FXML
    public void closeWindow() {
        Controller.infoStage.close();
    }

}
