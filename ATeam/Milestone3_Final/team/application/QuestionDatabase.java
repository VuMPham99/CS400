package application;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title: QuestionDatabase class
//Files: Main.java, Quesiton.java, Choice.java
//Course: (CS 400, Spring, 2019)
//
//Authors: A-team 76
//Lecturer's Name: Debra Deppeler
//Due Date: 2nd, May, Thursday
//

/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * This class is the the database of questions, store questions and load and save
 * @author A-team 76
 *
 */
public class QuestionDatabase {
  private HashMap<String, List<Question>> topics;
  private int num_questions;
  
  /**
   * No-para constructor of Database
   */
  public QuestionDatabase() {
    this.topics= new HashMap<String, List<Question>>();
    this.num_questions=0;
  }
  
  /**
   * Add a question into the database
   * @param t is the topic
   * @param q it the question
   */
  public void add_question(String t, Question q) {
    this.num_questions++;
    if (topics.containsKey(t)==true) {
      List<Question> old= topics.get(t);
      old.add(q);
      topics.replace(t, old);
    }
    else {
      List<Question> n= new ArrayList<Question>();
      n.add(q);
      topics.put(t, n);
    }
  }
  
  /**
   * Get the total number of questions in the database
   * @return int n
   */
  public int get_num_questions() {
    return this.num_questions;
  }
  
  /**
   * Save the current databse to a json file
   * @param f
   */
  public void save_to_json(File f) {
    System.out.println("Trying to write "+this.get_num_questions()+" q into the json");
    List<Question> All_questions= new ArrayList<Question>();
    for (String topic: this.get_topics()) {
      for (Question q: this.get_questions(topic)) {
        All_questions.add(q);
      }
    }
    JSONObject Big= new JSONObject();
    JSONArray Question_list = new JSONArray();
    for (Question q: All_questions) {
      JSONObject Q_Details = new JSONObject();
      Q_Details.put("meta-data", q.get_metadata());
      Q_Details.put("questionText", q.get_question());
      Q_Details.put("topic", q.get_topic());
      Q_Details.put("image", q.get_image());
      
      JSONArray choice_array= new JSONArray();
      for (Choice c: q.get_choices()) {
        JSONObject choice_details= new JSONObject();
        if (c.get_IsCorrect()) {
          choice_details.put("isCorrect", "T");
        }
        else {
          choice_details.put("isCorrect", "F");
        }
        choice_details.put("choice", c.get_choice());
        choice_array.add(choice_details);
      }
      Q_Details.put("choiceArray", choice_array);
      Question_list.add(Q_Details);
    }
    Big.put("questionArray", Question_list);
    try (FileWriter file = new FileWriter(f)) {
      System.out.println("Trying to write to:"+f.toPath());
      file.write(Big.toJSONString());
      file.flush();

  } catch (IOException e) {
      e.printStackTrace();
  }
  }
  
  /**
   * Get all the topics from this database
   * @return
   */
  public List<String> get_topics() {
    List<String> a= new ArrayList<String>();
    for (HashMap.Entry<String, List<Question>> entry : this.topics.entrySet()) {
      a.add(entry.getKey());
    }
    return a;
  }
  
  /**
   * Get all the question of that topic
   * @param key is the topic 
   * @return list of all questions
   */
  public List<Question> get_questions(String key){
    return this.topics.get(key);
  }
  
  /**
   * Load questions from another file and add it into the current databse
   * @param f is the file
   * @throws Exception
   */
  public void loadQuestions(File f) throws Exception {
    Object obj = new JSONParser().parse(new FileReader(f));
    JSONObject jo = (JSONObject) obj;
    JSONArray packages = (JSONArray) jo.get("questionArray");
    System.out.println(packages.size() + " questions in this file");
    
    for (int i = 0; i < packages.size(); i++) {
      JSONObject Pkg = (JSONObject) packages.get(i);
      String meta_data= (String) Pkg.get("meta-data");
      String question_text = (String) Pkg.get("questionText");
      String topic= (String) Pkg.get("topic");
      String image= (String) Pkg.get("image");
      JSONArray choices = (JSONArray) (Pkg.get("choiceArray"));
      
      List<Choice> choice_list= new ArrayList<Choice>();
      for (int j = 0; j < choices.size(); j++) {
        JSONObject choice= (JSONObject) choices.get(j);
        String TRUE = (String) choice.get("isCorrect");
        String choice_text = (String) choice.get("choice");
        if (TRUE.equals("T")) {
          choice_list.add(new Choice(true, choice_text));
        }
        else {
          choice_list.add(new Choice(false, choice_text));
        }
      }
      Question q= new Question(meta_data, question_text, topic, image, choice_list);
      this.add_question(topic, q);

    }
    
  }
}
