import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    private String question;
    private List<String> options;
    private int correctAnswer;

    public Question(String question, List<String> options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

class Quiz {
    private List<Question> questions;
    private int score;

    public Quiz() {
        this.questions = new ArrayList<>();
        this.score = 0;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void start(String username) {
        Scanner scanner = new Scanner(System.in);
        score = 0;
        
        System.out.println("\nWelcome, " + username + "! Let's start the quiz.");

        for (Question question : questions) {
            System.out.println("\n" + question.getQuestion());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            int answer = -1;
            while (true) {
                System.out.print("Enter your answer (1-" + options.size() + "): ");
                if (scanner.hasNextInt()) {
                    answer = scanner.nextInt();
                    if (answer >= 1 && answer <= options.size()) {
                        break;
                    } else {
                        System.out.println("Please enter a number between 1 and " + options.size());
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Clear invalid input
                }
            }

            if (answer == question.getCorrectAnswer()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer was option " + question.getCorrectAnswer());
            }
        }
        
        System.out.println("\nQuiz finished! " + username + ", your score is: " + score + "/" + questions.size());
    }

    public int getScore() {
        return score;
    }
}

public class OnlineQuizApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Quiz quiz = new Quiz();

        // Sample questions
        List<String> options1 = List.of("Java", "Python", "C++", "JavaScript");
        quiz.addQuestion(new Question("Which programming language is known as the 'write once, run anywhere' language?", options1, 1));

        List<String> options2 = List.of("JVM", "JRE", "JDK", "JAR");
        quiz.addQuestion(new Question("Which component of Java is responsible for compiling code?", options2, 3));

        List<String> options3 = List.of("1995", "1996", "2000", "2001");
        quiz.addQuestion(new Question("When was Java first released?", options3, 1));

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        boolean playAgain = true;
        while (playAgain) {
            quiz.start(username);

            System.out.print("\nDo you want to play again? (yes/no): ");
            String response = scanner.next();
            if (!response.equalsIgnoreCase("yes")) {
                playAgain = false;
                System.out.println("Thank you for playing, " + username + "! Your final score was: " + quiz.getScore());
            }
        }

        System.out.println("Exiting the quiz. Goodbye!");
        scanner.close();
    }
}
