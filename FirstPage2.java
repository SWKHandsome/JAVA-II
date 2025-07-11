import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;  
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;  

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

        borderPane.setTop(topContainer);
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
