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


public class FirstPage2 extends Application{  
  
    @Override  
    public void start(Stage stage) {
	    BorderPane borderPane = new BorderPane();
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu ("File");
        MenuItem openMenuItem = new MenuItem("Open");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(event -> Platform.exit());
        fileMenu.getItems().add(openMenuItem);
        fileMenu.getItems().add(exitMenuItem);

        Menu editMenu = new Menu("Edit");
        MenuItem copyMenuItem = new MenuItem("Copy");
        MenuItem pasteMenuItem = new MenuItem("Paste");
        editMenu.getItems().add(copyMenuItem);
        editMenu.getItems().add(pasteMenuItem);
        menuBar.getMenus().addAll(fileMenu, editMenu);

        ToolBar toolBar = new ToolBar();
        Image saveIcon = new Image("PNG\\save.png");
        ImageView saveImageView = new ImageView(saveIcon);
        saveImageView.setFitHeight(20);
        saveImageView.setFitWidth(20);
        Button saveButton = new Button("Save");
        saveButton.setGraphic(saveImageView);

        Image searchIcon = new Image("PNG\\logo 1.png");
        ImageView searchImageView = new ImageView(searchIcon);
        searchImageView.setFitHeight(20);
        searchImageView.setFitWidth(20);
        Button searchButton = new Button("Search");
        searchButton.setGraphic(searchImageView);

        TextField searchField = new TextField();

        toolBar.getItems().add(saveButton);
        toolBar.getItems().add(searchField);
        toolBar.getItems().add(searchButton);
        VBox topContainer = new VBox();
        topContainer.getChildren().addAll(menuBar,toolBar);

        Label menuLabel =  new Label("Category");
        menuLabel.setFont(Font.font("Arial",FontWeight.BOLD, 20));
        menuLabel.setTextFill(Color.GREEN);
        menuLabel.setPadding(new Insets(10,10,20,10));

        Button ironButton = new Button("Iron");
        Button aluminiumButton = new Button("Aliminium");
        Button copperButton = new Button("Copper");
        ironButton.setPrefWidth(100);
        aluminiumButton.setPrefWidth(100);
        copperButton.setPrefWidth(100);

        VBox leftContent = new VBox();
        leftContent.setPadding(new Insets(10));
        leftContent.getChildren().addAll(menuLabel,ironButton,aluminiumButton,copperButton);

        Label titleLabel = new Label("Cost Calculator");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD,20));
        titleLabel.setTextFill(Color.BLUE);
        titleLabel.setPadding(new Insets(10));

        Label categoryLabel = new Label("Category");
        Label priceLabel = new Label("Price");
        Label quantityLabel = new Label("Quantity");
        Label resultLabel = new Label();

        TextField categoryField = new TextField();
        TextField priceField = new TextField();
        TextField quantityField = new TextField();

        Button calButton = new Button("Calculate");
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(categoryLabel,0,0); 
        gridPane.add(priceLabel,0,1);
        gridPane.add(quantityLabel,0,2);
        gridPane.add(categoryField,1,0);
        gridPane.add(priceField,1,1);
        gridPane.add(quantityField,1,2);
        gridPane.add(resultLabel,1,3);
        gridPane.add(calButton,1,4);

        VBox centerContent = new VBox();
        centerContent.setPadding(new Insets(10,10,10,50));
        centerContent.getChildren().add(titleLabel);
        centerContent.getChildren().add(gridPane);

        calButton.setOnAction(e ->{
            try{
                double price = Double.parseDouble(priceField.getText());
                double quantity = Double.parseDouble(quantityField.getText());
                double result = price*quantity;
                resultLabel.setText(String.format("RM%.2f", result));
            }
            catch(NumberFormatException ex){
                resultLabel.setText("Invalid Input. Please enter numerical value!");
            }
        });

        borderPane.setTop(topContainer);
        borderPane.setLeft(leftContent);
        borderPane.setCenter(centerContent);
        Scene scene = new Scene(borderPane , 800, 600);
        stage.setTitle("Material Input");
        stage.setScene(scene);
        stage.show();      
   }
   public static void main (String[] args)  
    {  
        launch(args);  
    } 
}
