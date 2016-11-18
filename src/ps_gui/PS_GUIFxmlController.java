package ps_gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import jdbctemplate.PSJDBCTemplate;
import jdbctemplate.Employee;
import jdbctemplate.RFID;
import jdbctemplate.RFIDTimestamp;
import jdbctemplate.SignedInException;
import jdbctemplate.SignedOutException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

/**
 *
 * @author albhja15
 */
public class PS_GUIFxmlController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label timeLabel;
    @FXML
    private TextField rfidTextField;
    @FXML
    private Label msgLabel, welcomeLabel;
    @FXML
    private Button inButton, outButton;

    //Communicationinterface for the database.
    private PSJDBCTemplate empTemp;
    
    //Variables to keep track off the counting-cycles in
    //The delay functions.
    static int cycCnt = 0;
    static int cycCnt2 = 0;
    
    //Screensaver related objects.
    private int inactivityTime = 0;             //seconds without screensaver.
    private final int screenSaveTime = 30;
    private Screensaver screensaver;

    /**
     * Callback function for the IN button.
     */
    @FXML
    public void inButtonPressed() {

        Runnable timeUpdate = new Runnable() {
            @Override
            public void run() {
                try {
                    muteAll();
                    String RFID = rfidTextField.getText();
                    Employee emp = buttonHandler(RFID);
                    if (emp != null) {
                        setTime(emp, "IN");
                        welcomeLabel.setText("Welcome " + emp.getForename() + ".");
                    }
                } catch (SignedInException ex) {
                    msgLabel.setText("You are already signed in!, did you forget to sign out?");
                } catch (SignedOutException ex) {
                    msgLabel.setText("You are already signed out!, did you forget to sign in?");
                } catch (CannotGetJdbcConnectionException ex) {
                    msgLabel.setText("Unable to establish connection to database, try again later.");
                    resolveConnection();
                }
                waitForTxt();
            }
        };

        Platform.runLater(timeUpdate);
        screensaverDisable();
    }

    /**
     * Callback function for the OUT button.
     */
    @FXML
    public void outButtonPressed() {
        Runnable timeUpdate = new Runnable() {
            @Override
            public void run() {
                try {
                    muteAll();
                    String RFID = rfidTextField.getText();
                    Employee emp = buttonHandler(RFID);
                    if (emp != null) {
                        setTime(emp, "OUT");
                        welcomeLabel.setText("Goodbye " + emp.getForename() + ", have a nice day!");
                    }
                } catch (SignedInException ex) {
                    msgLabel.setText("You are already signed in!, did you forget to sign out?");
                } catch (SignedOutException ex) {
                    msgLabel.setText("You are already signed out!, did you forget to sign in?");
                } catch (CannotGetJdbcConnectionException ex) {
                    msgLabel.setText("Unable to establish connection to database, try again later.");
                    resolveConnection();
                }
                waitForTxt();
            }
        };

        Platform.runLater(timeUpdate);
        screensaverDisable();
    }
    
    /**
     * Method called when a rfid-tag has been read.
     * 
     */
    @FXML
    public void rfidAction() {
        screensaverDisable();
    }
    
    /**
     * Checks if the RFID-number entered has 10 character length else it 
     * will prompt the user to contact the system admin.
     * @param url
     * @param rb 
     */
    @FXML
    public void rfidEnterAction() {
        if(rfidTextField.getText().length() != 10) {
            rfidTextField.setText("");
            msgLabel.setText("You entered the RFID tag to early! please try again");
        }
    }

    /**
     * Initializes the gui instance variables.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set up connection to database
        empTemp = new PSJDBCTemplate();

        //Set up continous running time display
        bindToTime();

        //Disable screensaver
        screensaver = new Screensaver();
        screensaverDisable();
    }

    /**
     * Updates the timeLabel with the current date and time.
     */
    private void bindToTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Calendar time = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YY-MM-dd           HH:mm:ss");
                        timeLabel.setText(simpleDateFormat.format(time.getTime()));

                        //Select RFID textfield if it's not selected
                        if (!rfidTextField.focusedProperty().get()) {
                            rfidTextField.requestFocus();
                        }

                        //Sign out all employees by midnight
                        if (time.get(Calendar.MINUTE) == 58 && time.get(Calendar.HOUR_OF_DAY) == 23) {
                            List<RFID> list = empTemp.getAllSignedIn();
                            for (RFID rfid : list) {
                                try {
                                    empTemp.addRFIDTimeStamp(new RFIDTimestamp(rfid, "OUT"));
                                } catch (SignedInException ex) {
                                } catch (SignedOutException ex) {
                                }
                            }
                        }
                        
                        //count seconds of screen saver off-state without activity
                        if(!screensaver.getState()) {
                            inactivityTime++;
                        }
                        
                        //Enable if iactivitytimer has reashed the screenSave time
                        if(inactivityTime == screenSaveTime) {
                            screensaverEnable();
                        }
                    }
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Method to handle the pressing of either IN or OUT buttons.
     *
     * @param RFID The RFID that was read previously to pressing button.
     * @return Employee corresponding to the RFID number.
     */
    private Employee buttonHandler(String RFID) {
        if (RFID.length() == 0) {
            msgLabel.setText("You have to register the RFID tag!");
            return null;
        } else {
            msgLabel.setText("");
            rfidTextField.clear();
            Employee emp = empTemp.getEmployee(RFID);
            if (emp == null) {
                msgLabel.setText("Cannot find RFID in database, please "
                        + "contact system admin.");
            }

            return emp;
        }
    }

    /**
     * Update database with time.
     *
     * @param emp
     * @param inout
     */
    private void setTime(Employee emp, String inout) throws SignedInException, SignedOutException {
        RFIDTimestamp stamp = new RFIDTimestamp(new RFID(emp.getRFID()), inout);
        empTemp.addRFIDTimeStamp(stamp);
    }

    /**
     * Wait for a few seconds before enabling input controls and blanking the
     * text fields.
     */
    private void waitForTxt() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        cycCnt++;
                        if (cycCnt == 2) {
                            welcomeLabel.setText("");
                            msgLabel.setText("");
                            unmuteAll();
                            cycCnt = 0;
                        }
                    }
                }),
                new KeyFrame(Duration.seconds(2))
        );
        timeline.setCycleCount(2);
        timeline.play();
    }

    /**
     * Mutes the input controls.
     */
    private void muteAll() {
        inButton.disableProperty().set(true);
        outButton.disableProperty().set(true);
        rfidTextField.disableProperty().set(true);
    }

    /**
     * Un mutes the input controls.
     */
    private void unmuteAll() {
        inButton.disableProperty().set(false);
        outButton.disableProperty().set(false);
        rfidTextField.disableProperty().set(false);
    }

    /**
     * Tries to reconnect to database.
     */
    private void resolveConnection() {
        empTemp = new PSJDBCTemplate();
    }
    
    /**
     * Disable screensaver.
     */
    private void screensaverDisable() {
        screensaver.off();
        inactivityTime = 0;
    }
    
    /**
     * Enable screensaver.
     */
    private void screensaverEnable() {
        screensaver.on();
        inactivityTime = 0;
    }
    
    /**
     * Finalize this object.
     * @throws Throwable 
     */
    @Override
    protected void finalize() throws Throwable {
        try {
            screensaver.off();
            screensaver.close();
        } finally {
            super.finalize();
        }
    }
}
