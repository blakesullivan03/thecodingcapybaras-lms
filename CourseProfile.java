import java.util.ArrayList;

public class CourseProfile {
    private Course course;
    private ArrayList<Double> moduleGrades;
    private double courseGrade;
    private Student student;

    public CourseProfile(Course course, Student student) {
        this.course = course;
        this.student = student;
        this.moduleGrades = new ArrayList<Double>();
    }

    public CourseProfile(Course course, Student student,  ArrayList<Double> moduleGrades) { 
        this.course = course;
        this.student = student;
        this.moduleGrades = moduleGrades;
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

    public void enterGrade(double grade){
        moduleGrades.add(grade);
        
        courseGrade = courseGrade + ((grade-courseGrade)/moduleGrades.size()); // avg(new) = avg(old) + ((val - avg(old))/size(new))

    }
    public String toString() {
        ArrayList<Module> modules = course.getModules();
        String ret =  "Course: " + course;
        for(int i = 0; i < modules.size(); ++i) {
            ret += "\nModule: " + modules.get(i).getTitle() + " Grade: ";
            if(moduleGrades.size() < i)
                ret += moduleGrades.get(i);
            else
                ret += "incomplete";
        }
        return ret;
    }
}
