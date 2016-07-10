package sample;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    // defining input options
    private static final int FIRST_NAME = 1;
    private static final int LAST_NAME = 2;
    private static final int ADDRESS = 3;
    private static final int PHONE_NUMBER = 4;

    // variables
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private Stage currentStage;
    private int stepNumber;

    @FXML
    private Text instruction;
    @FXML
    private TextField textFieldInput;
    @FXML
    private Button nextButton;
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // basic start of application
        stepNumber = FIRST_NAME;
        manageSteps();
    }

    // inserting values
    private void insertFirstName() {
        instruction.setText("Enter your first name:");
        stepNumber = FIRST_NAME;
        textFieldInput.setText(firstName);
    }

    private void insertLastName() {
        instruction.setText("Enter your last name:");
        stepNumber = LAST_NAME;
        textFieldInput.setText(lastName);
    }

    private void insertAddress() {
        instruction.setText("Enter your address:");
        stepNumber = ADDRESS;
        textFieldInput.setText(address);
    }

    private void insertPhoneNumber() {
        instruction.setText("Enter your phone number:");
        stepNumber = PHONE_NUMBER;
        textFieldInput.setText(phoneNumber);
    }

    private void manageButtons() {
        // disabling back button at step number 1
        if (stepNumber == FIRST_NAME) {
            backButton.setDisable(true);
        } else {
            backButton.setDisable(false);
        }

        // changing name of the next button to 'Finish' at step number 4
        if (stepNumber == PHONE_NUMBER) {
            nextButton.setText("Finish");
        } else {
            nextButton.setText("Next");
        }
    }

    // navigating through steps
    private void manageSteps() {
        if (stepNumber == FIRST_NAME) {
            insertFirstName();
        }
        if (stepNumber == LAST_NAME) {
            insertLastName();
        }
        if (stepNumber == ADDRESS) {
            insertAddress();
        }
        if (stepNumber == PHONE_NUMBER) {
            insertPhoneNumber();
        }
        manageButtons();
    }

    // saving input in the current step number
    private void save(int stepNumber) {
        if (stepNumber == FIRST_NAME) {
            firstName = textFieldInput.getText();
        }
        if (stepNumber == LAST_NAME) {
            lastName = textFieldInput.getText();
        }
        if (stepNumber == ADDRESS) {
            address = textFieldInput.getText();
        }
        if (stepNumber == PHONE_NUMBER) {
            phoneNumber = textFieldInput.getText();
        }
    }

    private void showResults() {
        // getting our current stage so we can show
        // results in the same window
        currentStage = (Stage) nextButton.getScene().getWindow();
        // creating VBox to store results
        VBox resultsBox = new VBox(20);
        Insets insets = new Insets(20,20,20,20);
        resultsBox.setPadding(insets);

        // our results
        Text results = new Text("Results:");
        results.setStyle("-fx-font: bold 20.0 Helvetica");
        resultsBox.getChildren().add(results);
        resultsBox.getChildren().add(new Text("First name: " + firstName));
        resultsBox.getChildren().add(new Text("Last name: " + lastName));
        resultsBox.getChildren().add(new Text("Address: " + address));
        resultsBox.getChildren().add(new Text("Phone number: " + phoneNumber));

        // display
        Scene dialogScene = new Scene(resultsBox,400,325);
        currentStage.setResizable(false);
        currentStage.setScene(dialogScene);
        currentStage.show();
    }

    @FXML
    private void handleBackButton(ActionEvent event) {
        // saving input
        save(stepNumber);

        // previous step
        if(stepNumber > FIRST_NAME) {
            stepNumber--;
            manageSteps();
        }
    }

    @FXML
    private void handleNextButton(ActionEvent event) {
        // saving input
        save(stepNumber);

        // next step
        if(stepNumber < PHONE_NUMBER) {
            stepNumber++;
            manageSteps();
        } else {
            // showing results
            showResults();
        }
    }
}