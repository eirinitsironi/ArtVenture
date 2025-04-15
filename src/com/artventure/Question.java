import java.util.*;
import java.io.Serializable;

public class Question implements Serializable{
    private static final long serialVersionUID = 1L;
    private int questionID;
    private String text;
    private List<String> options;

    public Question(int questionID, String text, List<String> options) {
        this.questionID = questionID;
        this.text = text;
        this.options = options;
    }

    public int getQuestionID() {
        return questionID;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }
}
