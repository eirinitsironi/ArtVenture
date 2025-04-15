import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n=== Welcome to ArtVenture! ===");

        Quiz quiz = Quiz.loadProgress();
        if (quiz != null) {
            quiz.generateQuiz();
        }
        else {
            User user = new User(1,"eirini");
            List<Question> questions = List.of(
                new Question(1,"\nWhat kind of art do you prefer?",Arrays.asList("Modern", "Classic", "Abstract", "Realistic")),
                new Question(2,"\nWhich historical period are you interested in?",Arrays.asList("Ancient Greece", "Middle Ages", "Renaissance", "Modern Era"))
            );
            quiz = new Quiz(1, user, questions);
            quiz.generateQuiz();
        }
    }
}
