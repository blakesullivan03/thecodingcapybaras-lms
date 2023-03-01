import java.util.ArrayList;

public class CourseProfile {
    private ArrayList<Double> moduleGrades;
    private double courseGrade;
    private Student student;

    public CourseProfile(Course course, ArrayList<Double> moduleGrades, Student student) { //Why is course in the constructor but not a member?
        // if we're creating a new course profile, 
        // wouldn't we only need the student and set everything else to 0?
    }

    public double getGrade(){
        return this.courseGrade;
    }

    public Student getStudent(){
        return this.student;
    }

    public void enterGrade(int moduleIndex, double grade){ // do we need moduleIndex?
        moduleGrades.add(grade);
        courseGrade = courseGrade + ((grade-courseGrade)/moduleGrades.size()); // avg(new) = avg(old) + ((val - avg(old))/size(new))

    }
}
