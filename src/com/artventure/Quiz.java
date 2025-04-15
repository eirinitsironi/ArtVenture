import java.util.*;

public class Quiz {
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
        System.out.println("Welcome to the Preferences Quiz!");
        Scanner scanner = new Scanner(System.in);   //with GUI we need buttons instead 
       
        for (Question q : questions) {
            System.out.println(q.getText());
            List<String> options = q.getOptions();
            
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            System.out.print("Choice 1-4: ");
            int choice = scanner.nextInt();
            if (choice >= 1 && choice <= 4) {
                recordAnswer(options.get(choice -1)); 
            }
            else {
                System.out.println("Invalid choice. Quitting Quiz.");
                saveProgress();
                return;
            }
            scanner.close();
        }
    }

    private void recordAnswer(String answer) {
        answers.add(answer);
    }

    public void saveProgress() {

    }

    public void analyzePreferences() {

    }

    public void generateRecommendations() {

    }
}