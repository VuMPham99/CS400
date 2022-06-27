package application;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title: Question class
//Files: Main.java, Quesiton.java, Choice.java
//Course: (CS 400, Spring, 2019)
//
//Authors: A-team 76
//Lecturer's Name: Debra Deppeler
//Due Date: 2nd, May, Thursday
//

/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.List;

/**
 * This class is the question class, in charge of all the function of a question.
 * @author A-team 76
 *
 */
public class Question {
  private String metadata;
  private String question;
  private String topic;
  private String image;
  private String answer;
  private List<Choice> choices;
  
  /**
   * Constructor of question
   * @param meta is the metadata
   * @param question is the quesiton_text
   * @param topic is the topic of that question
   * @param image is the image of this question
   * @param choices contain all the choices of this question
   */
  public Question(String meta, String question, String topic, String image, List<Choice> choices) {
    this.metadata=meta;
    this.question=question;
    this.topic= topic;
    this.image=image;
    this.choices=choices;
    for (Choice c: choices) {
      if (c.get_IsCorrect()==true) {
        this.answer=c.get_choice();
      }
    }
  }
  
  /**
   * Set the current question to another question
   * @param q
   */
  public void set_question(Question q) {
    this.metadata = q.get_metadata();
    this.question=q.get_question();
    this.topic= q.get_topic();
    this.image= q.get_image();
    this.choices=q.get_choices();
    for (Choice c: this.choices) {
      if (c.get_IsCorrect()==true) {
        this.answer=c.get_choice();
      }
    }
  }
  
  public String get_question() {
    return this.question;
  }
  
  public String get_answer() {
    return this.answer;
  }
  
  public String get_metadata() {
    return this.metadata;
  }
  
  public String get_image() {
    return this.image;
  }
  
  public List<Choice> get_choices(){
    return this.choices;
  }
  public String get_topic() {
    return this.topic;
  }

}
