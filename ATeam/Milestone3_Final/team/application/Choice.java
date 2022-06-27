package application;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title: Choice class
//Files: Main.java, Quesiton.java, Choice.java
//Course: (CS 400, Spring, 2019)
//
//Authors: A-team 76
//Lecturer's Name: Debra Deppeler
//Due Date: 2nd, May, Thursday
//

/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * Choice class for questions.
 * @author A-team 76
 *
 */
public class Choice {
  private boolean isCorrect;
  private String choice;
  
  /**
   * Constructor of the choice class
   * @param b
   * @param s
   */
  public Choice(boolean b, String s) {
    this.isCorrect=b;
    this.choice=s;
  }
  
  /**
   * Getter for the choice_content
   * @return
   */
  public String get_choice() {
    return this.choice;
  }
  
  public boolean get_IsCorrect() {
    return this.isCorrect;
  }

}
