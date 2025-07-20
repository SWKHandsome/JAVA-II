import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application{

    @Override
    public void start(Stage stage){
        BorderPane borderPane = new BorderPane();
        
        Label amountLabel = new Label("Principal Amount:");
        Label interestRateLabel = new Label("Annual Interest Rate(%):");
        Label timeCompoundedLabel = new Label("Time Compounded Per Year:");
        Label durationLabel = new Label("Duration (Years):");
        Label resultLabel = new Label();

        TextField amountTextField = new TextField();
        TextField interestRateTextField = new TextField();
        TextField timeCompoundedTextField = new TextField();
        TextField durationTextField = new TextField();

        Button calButton = new Button("Calculate");
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(amountLabel,0,0);
        gridPane.add(interestRateLabel,0,1);
        gridPane.add(timeCompoundedLabel,0,2);
        gridPane.add(durationLabel,0,3);
        gridPane.add(amountTextField,1,0);
        gridPane.add(interestRateTextField,1,1);
        gridPane.add(timeCompoundedTextField,1,2);
        gridPane.add(durationTextField,1,3);
        gridPane.add(calButton,1,4);
        gridPane.add(resultLabel,1,5);

        calButton.setOnAction(e ->{
            try{
                double amount = Double.parseDouble(amountTextField.getText());
                double interestRate = Double.parseDouble(interestRateTextField.getText());
                double timeCompounded = Double.parseDouble(timeCompoundedTextField.getText());
                double duration = Double.parseDouble(durationTextField.getText());

                double middle = 1 + ((interestRate/100)/timeCompounded);
                double power = timeCompounded*duration;
                double resultMiddle = Math.pow(middle,power);
                double result = amount*resultMiddle;

                resultLabel.setText(String.format("Future Valur :$%.2f", result));
            }
            catch(NumberFormatException ex){
                resultLabel.setText("Invalid Input. Please enter numerical value!");
            }
        });

        VBox content = new VBox();
        content.setPadding(new Insets(10));
        content.getChildren().add(gridPane);
    
        borderPane.setCenter(content);
        Scene scene = new Scene(borderPane, 800, 600);
        stage.setTitle("Compound Interest Calculator");
        stage.setScene(scene);
        stage.show();

    }

    public static void main (String[] args){
        launch(args);
    }
}