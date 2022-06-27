package application;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;


public class Main extends Application {
  @Override
  public void start(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();

      Label greetingLabel = new Label("Welcome to the Quiz Generator");
      greetingLabel.setFont(new Font("Arial", 30));
      greetingLabel.setTextFill(Color.web("#0076a3"));
      Label secondLabel = new Label("Please choose topics: ");

      // Do the topics button
      ListView<String> list = new ListView<String>();
      ObservableList<String> items =
          FXCollections.observableArrayList("Computer Science 400", "Computer Science 252", "Math 320: Differential","Math 340: Linear Algebra");
      list.setItems(items);
      list.setPrefWidth(200);
      list.setPrefHeight(100);
      list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

      root.setRight(list);

      // Dispaly number of questions on the right
      Label ask_num = new Label("Number of questions: ");
      TextField num_input = new TextField(); // Needs to be smaller than n
      num_input.setPromptText("int");
      num_input.setMaxWidth(50);
      Button done_choosing = new Button("Generate Quiz");// Change to quiz scene

      // Need to add the requirement of only able to click when topic is chosen and number is
      // entered

      // Add a new Question button
      Button new_question_button = new Button("Add a new Question"); // Change to adding question
                                                                     // scene
      new_question_button.setFont(new Font("Arial", 20));
      new_question_button.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
          BorderPane form = new BorderPane();
          TextField topic_field= new TextField();
          TextArea question_field = new TextArea();
          question_field.setPrefSize(400, 65);
          TextField choice_field= new TextField();
          
          HBox choices= new HBox(new Label("Current choices: "));
          choices.setSpacing(10);
          
          //Bottom button including done and cancel
          Button done= new Button("Done");
          Button cancel= new Button("Cancel");
          HBox bottom= new HBox(done, cancel);
          bottom.setSpacing(488);
          form.setBottom(bottom);
          
          choice_field.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
              choices.getChildren().addAll(new ToggleButton(choice_field.getText()));
              choice_field.clear();
            }
          });
        
    
          
          Label line0= new Label("Please enter the information about the new question");
          line0.setFont(new Font("Arial",15));
          HBox line1= new HBox(new Label("                    Enter the topic:"), topic_field);
          HBox line2= new HBox(new Label("Question content:"),question_field);
          Button image_selector= new Button("Select image for this question");
          Label line3success= new Label();
          image_selector.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e5) {
              FileChooser fileChooser = new FileChooser();
              fileChooser.setTitle("Open Resource File");
              image_selector.setText("Reselect");
              File file = fileChooser.showOpenDialog(primaryStage);
              if (file!=null) {
                line3success.setText("Image sucessfully loaded!");
                
              }
            }
            
          });
          HBox line3= new HBox(image_selector, line3success);
          line3.setSpacing(15);
          HBox line4= new HBox(new Label("    Enter choice one by one:"), choice_field);
          Label line6= new Label("Chose the right answer by clicking it");
          
          
          VBox all =new VBox(line0, line1, line2, line3, line4, choices, line6);
          all.setSpacing(10);
          form.setTop(all);
          Scene newScene = new Scene(form, 600, 400);
          final Stage dialog = new Stage();
          dialog.initModality(Modality.APPLICATION_MODAL);
          dialog.initOwner(primaryStage);
          dialog.setScene(newScene);
          dialog.setTitle("New Question form");
          dialog.show();
          
          // Setting up for close the window
          cancel.setOnAction(e3 -> dialog.close());
          done.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
              dialog.close();
              greetingLabel.setText("Question successfully added!");
            }
          });
        }
      });

      // Add the label of total number in database
      Label total_number_lable = new Label("(Total number:" + " n) ");
      root.setRight(total_number_lable);

      // Add them to the top
      HBox hb1 = new HBox(secondLabel, list, ask_num, num_input, done_choosing);

      hb1.setSpacing(15);
      VBox vb1 = new VBox(greetingLabel, hb1, new_question_button);
      vb1.setSpacing(20);
      root.setTop(vb1);

      // Add the import another file at the bottom
      Button import_another = new Button("Import another file"); // Change to the import scene
      import_another.setFont(new Font("Arial", 20));
      import_another.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
          
          FileChooser fileChooser = new FileChooser();
          fileChooser.setTitle("Open Resource File");
          File file = fileChooser.showOpenDialog(primaryStage);
          greetingLabel.setText("File successfully imported!");
          
        }
          
      });
      root.setBottom(import_another);


      // lambda expression
      Button button = new Button("0");
      button.setOnAction((event) -> {
        int n = Integer.parseInt(button.getText());
        n++;
        button.setText(Integer.toString(n));

      });


      FileInputStream input = new FileInputStream("quiz.jpg");
      Image image = new Image(input);
      ImageView imageView = new ImageView(image);
      imageView.setFitHeight(350);
      imageView.setFitWidth(560);
      root.setCenter(imageView);

      Scene scene = new Scene(root, 800, 600);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.setTitle("Quiz Generator");
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }



  public static void main(String[] args) {
    launch(args);
  }
}
