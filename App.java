import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;  
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class App extends Application {

    @Override
    public void start(Stage stage){
        BorderPane borderPane = new BorderPane();

        Label amountLabel = new Label("Principal Amount:");
        Label rateLabel = new Label("Annual Interest Rate(%):");
        Label timeLabel = new Label("Time Compounded Per Year:");
        Label durationLabel = new Label("Duration(Years):");
        Label resultLabel = new Label();

        TextField amountField = new TextField();
        TextField rateField = new TextField();
        TextField timeField = new TextField();
        TextField durationField = new TextField();

        Button calButton = new Button("Calculate"); 

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(amountLabel,0,0);
        gridPane.add(rateLabel,0,1);
        gridPane.add(timeLabel,0,2);
        gridPane.add(durationLabel,0,3);
        gridPane.add(amountField,1,0);
        gridPane.add(rateField,1,1);
        gridPane.add(timeField,1,2);
        gridPane.add(durationField,1,3);
        gridPane.add(calButton,1,4);
        gridPane.add(resultLabel,1,5);

        VBox content = new VBox();
        content.setPadding(new Insets(10));
        content.getChildren().add(gridPane);
        
        calButton.setOnAction(e ->{
            try{
                double amount = Double.parseDouble(amountField.getText());
                double rate = Double.parseDouble(rateField.getText());
                double time = Double.parseDouble(timeField.getText());
                double duration = Double.parseDouble(durationField.getText());

                double powerOf = time*duration;
                double middle = 1+((rate/100)/time);
                double middleResult = Math.pow(middle, powerOf);
                double result = amount*middleResult;
                resultLabel.setText(String.format("Future Value: $%.2f",result));
            }
            catch(NumberFormatException ex){
                resultLabel.setText("Invalid Input. Please enter numerical value!");
            }
        });
        
        borderPane.setCenter(content);
        Scene scene = new Scene(borderPane , 400, 250);
        stage.setTitle("Compound Interest Calculator");
        stage.setScene(scene);
        stage.show();      

    }
    public static void main (String[] args) {
        launch(args);
    }
}