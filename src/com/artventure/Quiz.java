import java.io.*;
import java.util.*;

public class Quiz implements Serializable {
    private static final long serialVersionUID = 1L;
    private int quizID;
    private User user;
    private List<Question> questions;
    private List<Integer> answers;
    private List<Museum> recommendedMuseums;

    public Quiz(int quizID, User user, List<Question> questions) {
        this.quizID = quizID;
        this.user = user;
        this.questions = questions;
        this.answers = new ArrayList<>();   //ready empty list for the user's answers
        this.recommendedMuseums = new ArrayList<>();    //ready empty list for the recommended museums
    }

    public void generateQuiz(Scanner scanner) {
        System.out.println("\nWelcome to the Preferences Quiz!"); 
        System.out.println("\nType 'exit' at any time to save and quit.");
        
        for (int i = answers.size(); i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": \n" + q.getText());
            List<String> options = q.getOptions();
                
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }
    
            while(true) {
                System.out.print("\nChoice 1-" + options.size() + ": ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    saveProgress();
                    System.out.println("\nProgress saved. You can continue later!");
                    return;
                }

                try {
                    int choice = Integer.parseInt(input);
                    if (choice >= 1 && choice <= options.size()) {
                        recordAnswer(choice);       //record answer
                        break;      //continue to the next question
                    } 
                    else {
                        System.out.println("\nInvalid choice. Please enter a number between 1 and " + options.size() + ".");
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number or 'exit'.");
                }
            }
        }
        new File("progress.ser").delete();
        System.out.println("\nQuiz is completed successfully!");        
        analyzePreferences();
        user.earnPointsFromQuiz();
    }

    private void recordAnswer(int choiceIndex) {
        answers.add(choiceIndex);   //keep the index of each answer
    }

    public void saveProgress() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("progress.ser"))) {
            out.writeObject(this);      //save the entire Quiz item
            System.out.println("Progress for user " + user.getUsername() + " (Quiz ID: " + quizID + ") has been saved!");
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
        Map<Integer, Integer> countMap = new HashMap<>();       //Integer: answers, Integer: answer count

        for (int answerIndex : answers) {
            countMap.put(answerIndex, countMap.getOrDefault(answerIndex, 0) + 1);       //count how many times each option is selected
        }

        int maxCount = Collections.max(countMap.values());      //max number of answers

        List<Integer> dominantChoices = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == maxCount) {
                dominantChoices.add(entry.getKey());
            }
        }

        generateRecommendations(dominantChoices);
    }

    public void generateRecommendations(List<Integer> dominantChoices) {
        recommendedMuseums.clear();

        for (int choice : dominantChoices) {
            switch (choice) {
                case 1:
                    recommendedMuseums.add(new Museum(1, "Goulandris Museum of Modern Art", "A vibrant museum full of modern and contemporary pieces."));
                    break;
                case 2:
                    recommendedMuseums.add(new Museum(2, "National Gallery - Alexandros Soutzos", "Explore timeless classics and renaissance art."));
                    break;
                case 3:
                    recommendedMuseums.add(new Museum(3, "B&M Theocharakis Foundation", "A deep dive into abstract thougt and expression."));
                    break;
                case 4:
                    recommendedMuseums.add(new Museum(4, "Benaki Museum", "A mix of religious, folk, and tranditional art."));
                    break;
            }
        }

        System.out.println("\nRecommended museum(s) based on your preferences: ");
        for (Museum museum : recommendedMuseums) {
            System.out.println("- " + museum.getName() + ": " + museum.getDescription());
        }
    }    
}