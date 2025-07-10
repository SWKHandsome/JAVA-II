import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;  
import javafx.scene.control.*;  
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;  

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
        menuBar.getMenus().addAll(fileMenu);
        borderPane.setTop(menuBar);
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
