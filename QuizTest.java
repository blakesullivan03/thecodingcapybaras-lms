import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Stack;
class QuizTest {
    @Test
    public void testGetNextQuestion() {
        // Create a list of questions
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<String> answers = new ArrayList<>();
        answers.add("Answer A");
        answers.add("Answer B");
        answers.add("Answer C");
        Question question1 = new Question("Question 1", answers, 1L);
        Question question2 = new Question("Question 2", answers, 2L);
        Question question3 = new Question("Question 3", answers, 0L);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        
        // Create a new quiz with the list of questions
        Quiz quiz = new Quiz(questions);
        
        // Test that the next question is returned correctly
        Question nextQuestion = quiz.getNextQuestion();
        assertEquals(question3, nextQuestion);
        
        // Test that the correct question is returned after calling getNextQuestion() again
        nextQuestion = quiz.getNextQuestion();
        assertEquals(question2, nextQuestion);
    }
    @Test
    public void testQuestionOrderError() {
        // Create a list of questions
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<String> answers = new ArrayList<>();
        answers.add("Answer A");
        answers.add("Answer B");
        answers.add("Answer C");
        Question question1 = new Question("Question 1", answers, 1L);
        Question question2 = new Question("Question 2", answers, 2L);
        Question question3 = new Question("Question 3", answers, 0L);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
    
        // Create a new quiz with the list of questions
        Quiz quiz = new Quiz(questions);
    
        // Test that the next question is returned correctly
        Question nextQuestion = quiz.getNextQuestion();
        assertEquals(question1, nextQuestion); // this assertion will fail
    
        // Test that the correct question is returned after calling getNextQuestion() again
        nextQuestion = quiz.getNextQuestion();
        assertEquals(question2, nextQuestion);
    }
    @Test
    public void testQuizEndsAfterAllQuestionsAsked() {
        // Create a list of questions
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<String> answers = new ArrayList<>();
        answers.add("Answer A");
        answers.add("Answer B");
        answers.add("Answer C");
        Question question1 = new Question("Question 1", answers, 1L);
        Question question2 = new Question("Question 2", answers, 2L);
        questions.add(question1);
        questions.add(question2);
    
        // Create a new quiz with the list of questions
        Quiz quiz = new Quiz(questions);
    
        // Ask both questions
        quiz.getNextQuestion();
        quiz.getNextQuestion();
    
        // Try to ask another question - should return null to indicate the quiz has ended
        Question nextQuestion = quiz.getNextQuestion();
        assertNull(nextQuestion);
    }
    @Test
    public void testEmptyQuiz() {
        // Create an empty list of questions
        ArrayList<Question> questions = new ArrayList<>();
    
        // Create a new quiz with the empty list of questions
        Quiz quiz = new Quiz(questions);
    
        // Test that calling getNextQuestion() on an empty quiz returns null
        assertNull(quiz.getNextQuestion());
    }
    @Test
    public void testQuizHandlesNullQuestionsList() {
        Quiz quiz = new Quiz(null);
        Question nextQuestion = quiz.getNextQuestion();
        assertNull(nextQuestion);
}
    @Test
    public void testNullAnswers() {
        // Create a list of questions with null answers
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<String> answers1 = new ArrayList<>();
        answers1.add(null);
        answers1.add("Answer B");
        answers1.add("Answer C");
        Question question1 = new Question("Question 1", answers1, 1L);
        ArrayList<String> answers2 = new ArrayList<>();
        answers2.add("Answer D");
        answers2.add(null);
        answers2.add("Answer F");
        Question question2 = new Question("Question 2", answers2, 2L);
        questions.add(question1);
        questions.add(question2);

        // Create a new quiz with the list of questions
        Quiz quiz = new Quiz(questions);

        // Test that the first question is returned
        Question nextQuestion = quiz.getNextQuestion();
        assertEquals(question1, nextQuestion);
}
    @Test
    public void testQuizHandlesNullQuestions() {
     // Create a list of questions that contains a null question
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<String> answers1 = new ArrayList<>();
        answers1.add("Answer A");
        answers1.add("Answer B");
        answers1.add(null);
        Question question1 = new Question("Question 1", answers1, 1L);
        Question question2 = new Question("Question 2", answers1, 2L);
        questions.add(question1);
        questions.add(question2);
    
        // Create a new quiz with the list of questions
        Quiz quiz = new Quiz(questions);
    
        // Test that the quiz handles null questions
        Question nextQuestion = quiz.getNextQuestion();
        assertNotNull(nextQuestion);
    
        nextQuestion = quiz.getNextQuestion();
        assertNull(nextQuestion);
}
}
