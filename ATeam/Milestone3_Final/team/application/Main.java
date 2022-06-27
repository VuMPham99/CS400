package application;
////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title: Main class
//Files: Main.java, Quesiton.java, Choice.java
//Course: (CS 400, Spring, 2019)
//
//Authors: A-team 76
//Lecturer's Name: Debra Deppeler
//Due Date: 2nd, May, Thursday
//

/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;


/**
 * This class is the main class of the application, that start the Main user interface
 * @author A-team 76
 *
 */
public class Main extends Application {
  
  /**
   * this method start the whole GUI, deal with all the user intreraction.
   */
  @Override
  public void start(Stage primaryStage) {
    
   
    try {
      BorderPane root = new BorderPane();

      Label greetingLabel = new Label("Welcome to the Quiz Generator");
      greetingLabel.setFont(new Font("Arial", 30));
      greetingLabel.setTextFill(Color.web("#0076a3"));
      Label secondLabel = new Label("Please choose topics: ");
      QuestionDatabase QD= new QuestionDatabase();

      // Do the topics button
      ListView<String> list = new ListView<String>();
      ObservableList<String> items =
          FXCollections.observableArrayList("Computer Science 400", "Computer Science 252", "Math 320: Differential","Math 340: Linear Algebra");
      list.setItems(items);
      list.setPrefWidth(200);
      list.setPrefHeight(100);
      list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

      root.setRight(list);
      
      // Add the label of total number in database
      int total_num= 0;
      Label total_number_lable = new Label("(Total number:" + total_num);
      root.setRight(total_number_lable);

      // Display number of questions on the right
      Label ask_num = new Label("Number of questions: ");
      TextField num_input = new TextField(); // Needs to be smaller than n
      num_input.setPromptText("int");
      num_input.setMaxWidth(50);
      Button done_choosing = new Button("Generate Quiz");// Change to quiz scene
      
      done_choosing.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e0) {
          if (list.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Look, a Error Dialog");
            alert.setContentText("You must select at least one topic to generate a quiz");
            alert.showAndWait();
          }
          else if (list.getSelectionModel().isEmpty()==false){
           try {
             int n= Integer.parseInt(num_input.getText());
             List<String> chosen_topics= list.getSelectionModel().getSelectedItems();
             List<Question> All_questions= new ArrayList<Question>();
             List<Question> Quiz_list= new ArrayList<Question>();
             
             final Stage dialog = new Stage();
             dialog.initModality(Modality.APPLICATION_MODAL);
             dialog.initOwner(primaryStage);
             
             for (String topic: chosen_topics) {
               for (Question q: QD.get_questions(topic)) {
                 All_questions.add(q);
               }
             }
             Collections.shuffle(All_questions);
             if (n<=0) {
               throw new Exception("It should be non-negative");
             }
             else if (n<All_questions.size()) {
               for (int i=0;i<n;i++) {
                 Quiz_list.add(All_questions.get(i)); 
               }  
             }
             else {
               for (Question q: All_questions) {
                 Quiz_list.add(q);
               }
             }
             int Max_n = Quiz_list.size();
             
             // Then it should generate a quiz
             BorderPane form = new BorderPane();
             Question curr_q= Quiz_list.remove(0);
             Label line1= new Label("Quiz started");
             line1.setFont(new Font("Arial", 25));
             line1.setTextFill(Color.web("rgb(204, 51, 255)"));  // Purple
             
             Label question_num= new Label("Number of question left:"+Quiz_list.size());
             Label question_text= new Label ("Question: "+curr_q.get_question());
             question_text.setWrapText(true);
             question_text.setPrefHeight(50);
             
             if (curr_q.get_image().equals("none")==false) {
               FileInputStream input = new FileInputStream(curr_q.get_image());
               Image image = new Image(input);
               ImageView imageView = new ImageView(image);
               imageView.setFitHeight(200);
               imageView.setFitWidth(200);
               form.setRight(imageView);
             }
             List<Choice> choice_list= curr_q.get_choices();
             Collections.shuffle(choice_list);

             ToggleGroup group = new ToggleGroup();
             VBox choices= new VBox();
             choices.setSpacing(15);

             for (Choice c: choice_list) {
               ToggleButton tb= new ToggleButton(c.get_choice());
               tb.setToggleGroup(group);
               choices.getChildren().add(tb);
             }
             
             Button next= new Button("Next question");
             if (Quiz_list.size()==0) {
               next.setText("Finish");
             }
             Label total_correct= new Label("0");
             next.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent e) {
                 ToggleButton selected = (ToggleButton) group.getSelectedToggle();
                 
                 try {
                   if (selected.getText().equals(curr_q.get_answer())) {
                     line1.setText("Previous answer was correct");
                     line1.setTextFill(Color.web("rgb(0, 153, 0)"));  // Green
                     System.out.println("Correct: You chose:"+selected.getText()+" right answer:"+curr_q.get_answer());
                     int nnn= Integer.parseInt(total_correct.getText())+1;
                     String nn_string= String.valueOf(nnn);
                     total_correct.setText(nn_string);
                   }
                   else {
                     System.out.println("Wrong: You chose:"+selected.getText()+" right answer:"+curr_q.get_answer());
                     line1.setText("Previous answer was wrong");
                     line1.setTextFill(Color.web("rgb(255, 0, 0)"));   // Red
                   }
                   
                   if (Quiz_list.size()>0) {
                     if (Quiz_list.size()==1) {
                       next.setText("Finsh");
                     }
                     curr_q.set_question(Quiz_list.remove(0));
                     question_num.setText("Number of question left:"+Quiz_list.size());
                     question_text.setText(curr_q.get_question());
                     if (curr_q.get_image().equals("none")==false) {
                       System.out.println("There is an image for this quetsion");
                       FileInputStream input = new FileInputStream(curr_q.get_image());
                       Image image = new Image(input);
                       ImageView imageView = new ImageView(image);
                       imageView.setFitHeight(200);
                       imageView.setFitWidth(200);
                       System.out.println("I set it right");
                       form.setRight(imageView);
                     }
                     else {
                       System.out.println("No image");
                       form.setRight(null);
                     }
                     List<Choice> choice_list= curr_q.get_choices();
                     Collections.shuffle(choice_list);
                     choices.getChildren().clear();
                     group.getToggles().clear();
                     for (Choice c: choice_list) {
                       ToggleButton tb= new ToggleButton(c.get_choice());
                       tb.setToggleGroup(group);
                       choices.getChildren().add(tb);
                     }
                   }
                   else {
                     dialog.close();
                     Alert alert = new Alert(AlertType.INFORMATION);
                     alert.setTitle("Quiz result");
                     alert.setHeaderText("Here is your quiz result");          
                     alert.setContentText("Your score is "+100* Integer.parseInt(total_correct.getText())/Max_n+"%");
                     alert.showAndWait();
                   }
                 }
                 catch(Exception exc) {
                   Alert alert = new Alert(AlertType.WARNING);
                   alert.setTitle("Warning");
                   alert.setHeaderText("Something is wrong");          
                   alert.setContentText("You must choose one of the choice");
                   alert.showAndWait();
                 }
               }
             }); 
             next.setFont(new Font("Arial", 18));
             VBox all= new VBox(line1, question_num, question_text,choices);
             all.setSpacing(18);
             form.setBottom(next);
             
             Scene newScene = new Scene(form, 800, 600);
             dialog.setScene(newScene);
             dialog.setTitle("Quiz time");
             form.setTop(all);
             dialog.show();
           }
           catch(Exception e) {
             Alert alert = new Alert(AlertType.ERROR);
             alert.setTitle("Warning Dialog");
             alert.setHeaderText("You gotta chill");
             alert.setContentText("You must input a postive integer in the number field, exception is "+e.getClass());
             alert.showAndWait();       
           }        
          }
          
        }        
      });

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
          ToggleGroup choice_group= new ToggleGroup();
          List<ToggleButton> choice_list= new ArrayList<ToggleButton>();
          
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
              if (choice_field.getText().isEmpty()==false) {
                ToggleButton To= new ToggleButton(choice_field.getText());
                To.setToggleGroup(choice_group);
                choice_list.add(To);
                choices.getChildren().add(To);
                choice_field.clear();   
              }
            }
          });
        
    
          
          Label line0= new Label("Please enter the information about the new question");
          line0.setFont(new Font("Arial",15));
          HBox line1= new HBox(new Label("                    Enter the topic:"), topic_field);
          HBox line2= new HBox(new Label("Question content:"),question_field);
          Button image_selector= new Button("Select image for this question");
          Label line3success= new Label();

          Label file_name_label= new Label("none");
          image_selector.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e5) {
              FileChooser fileChooser = new FileChooser();
              fileChooser.setTitle("Open Resource File");
              image_selector.setText("Reselect");
              File file = fileChooser.showOpenDialog(primaryStage);
              if (file!=null) {
                file_name_label.setText(file.getName());
                File dest = new File(System.getProperty("user.dir"));
                Path Source_path = file.toPath();
                Path Dest_path = dest.toPath();
                Path projects = Paths.get(Dest_path.toString(), file.getName());
                try{
                  System.out.println("Copy from:"+Source_path.toString()+ " to "+projects.toString());
                  Files.copy(Source_path, projects);
                }
                catch(Exception e){
                  System.out.println("Exception of type:"+e.getClass());
                }
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
            public void handle(ActionEvent e10) {
              boolean something_wrong=false;
              String S= "";
              if (topic_field.getText().isEmpty()) {
                 something_wrong=true;
                 S+="No topic ";
              }
              if (question_field.getText().isEmpty()) {
                something_wrong=true;
                S+="No question_content ";
              }
              if(choice_list.size()==0) {
                something_wrong=true;
                S+="No choices";
              }
              else {
                boolean all_not_chosen=true;
                for (ToggleButton t: choice_list) {
                  if (t.isSelected()==true) {
                    System.out.println(t.getText()+" is selected");
                    all_not_chosen=false;
                  }
                }
                if (all_not_chosen==true) {
                  something_wrong=true;
                  S+="No right answer";
                }
                
              }
              if (something_wrong==true) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("You gotta chill");
                alert.setContentText(S);
                alert.showAndWait();   
              }
              else {
                dialog.close();
                List<Choice> class_choice_list= new ArrayList<Choice>();
                for (ToggleButton TB: choice_list) {
                  class_choice_list.add(new Choice(TB.isSelected(),TB.getText()));
                }
                Question NQ= new Question ("unused", question_field.getText(),topic_field.getText(),file_name_label.getText(),class_choice_list);
                QD.add_question(topic_field.getText(), NQ);
               
                total_number_lable.setText("(Total number:" + QD.get_num_questions());
                ObservableList<String> items = FXCollections.observableArrayList();
                for (String s: QD.get_topics()) {
                  items.add(s);
                }
                list.setItems(items);
                greetingLabel.setText("Question successfully added!");
              }
            }
          });
        }
      });

      // Add them to the top
      HBox hb1 = new HBox(secondLabel, list, ask_num, num_input, done_choosing);
      hb1.setSpacing(15);
      VBox vb1 = new VBox(greetingLabel, hb1, new_question_button);
      vb1.setSpacing(20);
      root.setTop(vb1);

      // Add the import another file at the bottom
      Button import_another = new Button("Load additional question from another file"); // Change to the import scene
      import_another.setFont(new Font("Arial", 20));
      import_another.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
          
          FileChooser fileChooser = new FileChooser();
          fileChooser.setTitle("Open Resource File");
          File file = fileChooser.showOpenDialog(primaryStage);
          try{
            QD.loadQuestions(file);
          }
          catch(Exception ex) {
            System.out.println("That file is invalid");
          }
          greetingLabel.setText("File successfully imported!");
          total_number_lable.setText("(Total number:" + QD.get_num_questions());
          ObservableList<String> items = FXCollections.observableArrayList();
          for (String s: QD.get_topics()) {
            items.add(s);
          }
          list.setItems(items);
          
          
        }
          
      });
      
      Button Save_button= new Button("Save to file");
      Save_button.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
          TextInputDialog dialog = new TextInputDialog("yours.json");
          dialog.setTitle("Text Input Dialog");
          dialog.setHeaderText("Look, a Text Input Dialog");
          dialog.setContentText("Please enter file name:");

          // Traditional way to get the response value.
          Optional<String> result = dialog.showAndWait();
          if (result.isPresent()){
            File dest = new File(System.getProperty("user.dir"));
            Path Dest_path = dest.toPath();
            Path projects = Paths.get(Dest_path.toString(), result.get());
            System.out.println("Trying to write to:"+projects.toString());
            try{
              QD.save_to_json(projects.toFile());
            }
            catch(Exception exe) {
              System.out.println("Expetion thrown when trying to save to file,type:"+exe.getClass());
            }
          }
        }
      });
      
      Button Exit_button = new Button ("Exit without save");
      
      
      HBox bottom = new HBox(import_another, Save_button, Exit_button);
      bottom.setSpacing(50);
      root.setBottom(bottom);


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
      
      Exit_button.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
          Alert alert = new Alert(AlertType.CONFIRMATION);
          alert.setTitle("Confirmation Dialog");
          alert.setHeaderText("Look, a Confirmation Dialog");
          alert.setContentText("Are you sure you want to exit without saving?");

          Optional<ButtonType> result = alert.showAndWait();
          if (result.get() == ButtonType.OK){
              primaryStage.close();
          } else {
              alert.close();
          }
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Run the application in this main method
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
