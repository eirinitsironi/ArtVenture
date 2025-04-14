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

}
