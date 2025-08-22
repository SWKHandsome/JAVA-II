import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;  
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class FirstPage2 extends Application{  

    private TableView<Person> tableView = new TableView<>();
    private ObservableList<Person> data  = FXCollections.observableArrayList();
    private Label dbStatusLabel = new Label("Checking databse....");
  
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

        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        TextField searchField = new TextField();

        toolBar.getItems().add(saveButton);
        toolBar.getItems().add(updateButton);
        toolBar.getItems().add(deleteButton);
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

        Label firstNameLabel = new Label("First Name");
        Label lastNameLabel = new Label("Last Name");
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();


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

        gridPane.add(firstNameLabel,0,5);
        gridPane.add(lastNameLabel,0,6);
        gridPane.add(firstNameField,1,5);
        gridPane.add(lastNameField,1,6);

        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        tableView.setItems(data);
        tableView.getColumns().addAll(firstNameCol,lastNameCol);

        VBox rightContent = new VBox();
        rightContent.setPadding(new Insets(10));
        rightContent.getChildren().addAll(tableView);

        VBox centerContent = new VBox();
        centerContent.setPadding(new Insets(10,10,10,50));
        centerContent.getChildren().add(titleLabel);
        centerContent.getChildren().add(gridPane);

        saveButton.setOnAction(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            saveToDatabase(firstName, lastName);

            retriveDataFromDatabase();


            firstNameField.clear();
            lastNameField.clear();
        });

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

        deleteButton.setOnAction(e -> {

            Person selectedPerson =(Person) tableView.getSelectionModel().getSelectedItem();
            if(selectedPerson != null){
                deleteFromDatabase(selectedPerson.getFirstName(), selectedPerson.getLastName());
                System.out.println("Selected Item");
            }

            else{
                System.out.println("No Selected Item");
            }
            retriveDataFromDatabase();

        });

        searchButton.setOnAction(e -> {
            if (searchField.getText() != null) {
                String Keyword = searchField.getText();
                searchFromDatabase(Keyword);
            }
            else {
                System.out.println("No Search Keyword");
            }
        });

        retriveDataFromDatabase();

        checkDatabaseConnection();

        borderPane.setBottom(dbStatusLabel); // Add status label at bottom

        tableView.setOnMouseClicked(event -> {
            Person selectedPerson = tableView.getSelectionModel().getSelectedItem();
            if (selectedPerson != null) {
                firstNameField.setText(selectedPerson.getFirstName());
                lastNameField.setText(selectedPerson.getLastName());
            }
        });

        updateButton.setOnAction(e ->{
           Person selectedPerson = tableView.getSelectionModel().getSelectedItem(); 
           if (selectedPerson != null) {
                String newFirstName = firstNameField.getText();
                String newLastName = lastNameField.getText();
                updateDatabase(selectedPerson.getFirstName(), selectedPerson.getLastName(), newFirstName, newLastName);
                retriveDataFromDatabase();
                firstNameField.clear();
                lastNameField.clear();
           }
           else {
            System.out.println("No row selected for update");
           }
        });

        borderPane.setTop(topContainer);
        borderPane.setLeft(leftContent);
        borderPane.setCenter(centerContent);
        borderPane.setRight(rightContent);
        Scene scene = new Scene(borderPane , 800, 600);
        stage.setTitle("Material Input");
        stage.setScene(scene);
        stage.show();      
   }
   
        private void saveToDatabase(String firstName, String lastName){
            String jdbcUrl = "jdbc:mysql://localhost:3306/java-ii";
            String ussername = "root";
            String password = "";
            Connection connection = null;
            PreparedStatement preparedStatement = null;
                try{
                    connection=DriverManager.getConnection(jdbcUrl, ussername, password);
                    //create a statement
                    String sql = "INSERT INTO persons(first_name, last_name) VALUES(?,?)";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1,firstName);//第一个问号
                    preparedStatement.setString(2, lastName);//第二个问号
                    preparedStatement.executeUpdate();
                    System.out.println("Data inserted");
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
        }
    
        private void retriveDataFromDatabase(){
            String jdbcUrl = "jdbc:mysql://localhost:3306/java-ii";
            String ussername = "root";
            String password = "";
            Connection connection = null;
            Statement preparedStatement = null;
            ResultSet resultSet = null;

                try{
                    connection=DriverManager.getConnection(jdbcUrl, ussername, password);
                    //create a statement
                    preparedStatement = connection.createStatement();
                    String sql = "SELECT * FROM persons";
                    resultSet = preparedStatement.executeQuery(sql);

                    data.clear();
                    while (resultSet.next()) {
                        String firstName = resultSet.getString("first_name");
                        String lastName = resultSet.getString("last_name");

                        data.add(new Person(firstName, lastName));
                    }
                    System.out.println("Data retrieved successfully");
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
        }

        private void deleteFromDatabase(String firstName, String lastName){
            String jdbcUrl = "jdbc:mysql://localhost:3306/java-ii";
            String ussername = "root";
            String password = "";
            Connection connection = null;
            PreparedStatement preparedStatement = null;

                try{
                    connection=DriverManager.getConnection(jdbcUrl, ussername, password);
                    //create a statement
                    String sql = "DELETE FROM persons WHERE first_name = ? and last_name = ?";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, firstName);
                    preparedStatement.setString(2, lastName);
                    preparedStatement.executeUpdate();

                    System.out.println("Data retrieved successfully");
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
        }

        private void updateDatabase(String oldFirstName, String oldLastName, String newFirstName, String newLastName){
            String jdbcUrl = "jdbc:mysql://localhost:3306/java-ii";
            String ussername = "root";
            String password = "";
            Connection connection = null;
            PreparedStatement preparedStatement = null;

                try{
                    connection=DriverManager.getConnection(jdbcUrl, ussername, password);
                    //create a statement
                    String sql = "UPDATE persons SET first_name = ?, last_name = ? WHERE first_name = ? AND last_name = ?";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, newFirstName);
                    preparedStatement.setString(2, newLastName);
                    preparedStatement.setString(3, oldFirstName);
                    preparedStatement.setString(4, oldLastName);

                    int rowsUpdated = preparedStatement.executeUpdate();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }                   
        }

        private void checkDatabaseConnection(){
            String jdbcUrl = "jdbc:mysql://localhost:3306/java-ii";
            String ussername = "root";
            String password = "";
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            
            try{
                connection = DriverManager.getConnection(jdbcUrl, ussername, password);
                dbStatusLabel.setText("Database Connected");
                dbStatusLabel.setTextFill(Color.GREEN);
            }
            catch (SQLException e) {
                dbStatusLabel.setText("Database Not Connected");
                dbStatusLabel.setTextFill(Color.RED);
            }
        }

        private void searchFromDatabase(String Keyword){
            String jdbcUrl = "jdbc:mysql://localhost:3306/java-ii";
            String ussername = "root";
            String password = "";
            Connection connection = null;
            Statement preparedStatement = null;
            ResultSet resultSet = null;
            try{ 
                connection = DriverManager.getConnection(jdbcUrl, ussername, password);
                preparedStatement = connection.createStatement();
                String sql = "SELECT * FROM persons WHERE first_name like '%" + Keyword + "%' " + "or last_name like '%" + Keyword + "%'";
                resultSet = preparedStatement.executeQuery(sql);
                data.clear();
                while (resultSet.next()) {
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    data.add(new Person(firstName, lastName));
                }
                System.out.println("Data retrieved successfully.");
            }
            catch(SQLException e){
                    e.printStackTrace();
                }
        }
            
        

   public static void main (String[] args)  
    {  
        launch(args);   
    } 

    public class Person{
        private String firstName;
        private String lastName;

        public Person (String firstName, String lastName){
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName(){
            return firstName;
        }

        public String getLastName(){
            return lastName;
        }
    }
}

