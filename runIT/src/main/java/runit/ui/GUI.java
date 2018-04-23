package runit.ui;

import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import runit.domain.Exercise;
import runit.domain.Logic;

public class GUI extends Application {

    private Logic logic;

    private Scene runitScene;
    private Scene newUserScene;
    private Scene loginScene;

    private VBox exerciseNodes;
    private Label menuLabel = new Label();

    @Override
    public void init() throws Exception {

        this.logic = new Logic();

    }

    public Node createTodoNode(Exercise exercise) {

HBox box = new HBox(10);
        Label label = new Label(exercise.toString());
        label.setMinHeight(28);
        Button button = new Button("delete");
        button.setOnAction(e -> {
//            logic.deleteExercise(exercise);     # not done
            redrawExerciseList();
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(0, 5, 0, 5));

        box.getChildren().addAll(label, spacer, button);
        return box;
    }

    public void redrawExerciseList() {

        exerciseNodes.getChildren().clear();

        List<Exercise> exercises = logic.getHistory();
        exercises.forEach(oneExercise -> {
            exerciseNodes.getChildren().add(createTodoNode(oneExercise));
        });
    }

    @Override
    public void start(Stage primaryStage) {
        // login scene

        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label userLabel = new Label("username");
        TextField userInput = new TextField();
        Label passLabel = new Label("password");
        TextField passInput = new TextField();

        inputPane.getChildren().addAll(userLabel, userInput, passLabel, passInput);
        Label loginMessage = new Label();

        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");
        loginButton.setOnAction(e -> {
            String username = userInput.getText();
            String password = passInput.getText();
            menuLabel.setText(username + " logged in...");
            if (logic.loginUser(username, password).equals("Login successful")) {
                loginMessage.setText("");
                redrawExerciseList();
                primaryStage.setScene(runitScene);
                userInput.setText("");
            } else {
                loginMessage.setText("User does not exist");
                loginMessage.setTextFill(Color.RED);
            }
        });

        createButton.setOnAction(e -> {
            userInput.setText("");
            primaryStage.setScene(newUserScene);
        });

        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton);

        loginScene = new Scene(loginPane, 300, 250);

        // new createNewUserScene
        VBox newUserPane = new VBox(10);

        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setPadding(new Insets(10));
        TextField newUsernameInput = new TextField();
        Label newUsernameLabel = new Label("username");
        newUsernameLabel.setPrefWidth(100);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);

        HBox newPassPane = new HBox(10);
        newPassPane.setPadding(new Insets(10));
        TextField newPassInput = new TextField();
        Label newPassLabel = new Label("password");
        newPassLabel.setPrefWidth(100);
        newPassPane.getChildren().addAll(newPassLabel, newPassInput);

        Label userCreationMessage = new Label();

        Button createNewUserButton = new Button("create");
        createNewUserButton.setPadding(new Insets(10));

        createNewUserButton.setOnAction(e -> {
            String username = newUsernameInput.getText();
            String pass = newPassInput.getText();

            if (username.length() <= 2 || pass.length() < 2) {
                userCreationMessage.setText("username or password too short");
                userCreationMessage.setTextFill(Color.RED);
            } else if (username.length() > 32 || pass.length() > 32) {
                userCreationMessage.setText("username or password too long");
                userCreationMessage.setTextFill(Color.RED);
            } else if (logic.signupUser(username, pass).equals("Login successful")) {
                userCreationMessage.setText("");
                loginMessage.setText("new user created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);
            } else {
                userCreationMessage.setText("username has to be unique");
                userCreationMessage.setTextFill(Color.RED);
            }
        });

        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newPassPane, createNewUserButton);

        newUserScene = new Scene(newUserPane, 300, 250);

        // main scene
        ScrollPane exerciseScrollbar = new ScrollPane();
        BorderPane mainPane = new BorderPane(exerciseScrollbar);
        runitScene = new Scene(mainPane, 300, 250);

        HBox menuPane = new HBox(10);
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("logout");
        menuPane.getChildren().addAll(menuLabel, menuSpacer, logoutButton);
        logoutButton.setOnAction(e -> {
            logic.logout();
            primaryStage.setScene(loginScene);
        });

        HBox createForm = new HBox(10);
        Button createTodo = new Button("add");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField timeInput = new TextField();
        createForm.getChildren().addAll(timeInput, spacer, createTodo);

        exerciseNodes = new VBox(10);
        exerciseNodes.setMaxWidth(280);
        exerciseNodes.setMinWidth(280);
        redrawExerciseList();

        exerciseScrollbar.setContent(exerciseNodes);
        mainPane.setBottom(createForm);
        mainPane.setTop(menuPane);

        createTodo.setOnAction(e -> {
            todoService.createTodo(timeInput.getText());
            timeInput.setText("");
            redrawExerciseList();
        });

        // seutp primary stage
        primaryStage.setTitle("Todos");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            System.out.println("closing");
            System.out.println(todoService.getLoggedUser());
            if (todoService.getLoggedUser() != null) {
                e.consume();
            }

        });
    }

}
