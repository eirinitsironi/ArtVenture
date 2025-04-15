import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to ArtVenture!");
        User user = new User(1,"eirini");

        List<Question> questions = List.of(
            new Question(1,"What kind of art do you prefer?",Arrays.asList("Modern", "Classic", "Abstract", "Realistic")),
            new Question(2,"Which historical period are you interested in?",Arrays.asList("Ancient Greece", "Middle Ages", "Renaissance", "Modern Era"))
        );

        Quiz quiz = new Quiz(1, user, questions);
        quiz.generateQuiz();
    }
}
