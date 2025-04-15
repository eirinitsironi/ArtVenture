import java.util.*;
import java.io.*;

public class Quiz implements Serializable {
    private static final long serialVersionUID = 1L;
    private int quizID;
    private User user;
    private List<Question> questions;
    private List<String> answers;
    private List<Museum> recommendedMuseums;

    public Quiz(int quizID, User user, List<Question> questions) {
        this.quizID = quizID;
        this.user = user;
        this.questions = questions;
        this.answers = new ArrayList<>();   //ready empty list for the user's answers
        this.recommendedMuseums = new ArrayList<>();    //ready empty list for the recommended museums
    }

    public void generateQuiz() {
        System.out.println("\nWelcome to the Preferences Quiz!");
        
        try (Scanner scanner = new Scanner(System.in)) {    //with GUI we need buttons instead 
            for (int i = answers.size(); i < questions.size(); i++) {
                Question q = questions.get(i);
                System.out.println("\nQuestion " + (i + 1) + ": " + q.getText());
                List<String> options = q.getOptions();
                
                for (int j = 0; j < options.size(); j++) {
                    System.out.println((j + 1) + ". " + options.get(j));
                }
    
                System.out.print("Choice 1-" + options.size() + ": ");
                int choice = scanner.nextInt();
                
                if (choice >= 1 && choice <= options.size()) {
                    recordAnswer(options.get(choice - 1)); 
                } else {
                    System.out.println("\nInvalid choice. Saving progress...\n");
                    saveProgress();
                    return;
                }
            }
            
            new File("progress.ser").delete();
            System.out.println("\nQuiz is completed successfully!\n");
        }
    }

    private void recordAnswer(String answer) {
        answers.add(answer);
    }

    public void saveProgress() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("progress.ser"))) {
            out.writeObject(this);      //save the entire Quiz item
            System.out.println("Your progress has been saved!\n");
        } catch (IOException e) {
            System.out.println("Error saving progress: " + e.getMessage());
        }
    }

    public static Quiz loadProgress() {
        File file = new File("progress.ser");
        if (!file.exists()) {
            return null;
        }
        
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Quiz loadedQuiz = (Quiz) in.readObject();
            System.out.println("\nQuiz is uncompleted!");
            System.out.println("You have answered " + loadedQuiz.answers.size() + " questions so far.");
            return loadedQuiz;
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading progress - starting new quiz");
            return null;
        }
    }

    public void analyzePreferences() {

    }

    public void generateRecommendations() {

    }
}