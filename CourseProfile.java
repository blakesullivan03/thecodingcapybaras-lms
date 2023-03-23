import java.util.ArrayList;

public class CourseProfile {
    private Course course;
    private ArrayList<Double> moduleGrades;
    private double courseGrade;
    private Student student;

    public CourseProfile(Course course, Student student) {
        this.course = course;
        this.moduleGrades = new ArrayList<Double>();
        this.student = student;
    }
    public CourseProfile(Course course, ArrayList<Double> moduleGrades, Student student) { 
        this.course = course;
        this.moduleGrades = moduleGrades;
        this.student = student;
    }

    public double getGrade(){
        return this.courseGrade;
    }

    public Student getStudent(){
        return this.student;
    }

    public Course getCourse() {
        return this.course;
    }

    public void enterGrade(double grade){ // do we need moduleIndex?
        moduleGrades.add(grade);
        
        courseGrade = courseGrade + ((grade-courseGrade)/moduleGrades.size()); // avg(new) = avg(old) + ((val - avg(old))/size(new))

    }
}
