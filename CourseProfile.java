import java.util.ArrayList;

public class CourseProfile {
    private Course course;
    private ArrayList<Double> moduleGrades;
    private double courseGrade;
    private Student student;
    private ArrayList<Module> modules;

    public CourseProfile(Course course, Student student) {
        this.course = course;
        this.student = student;
        this.moduleGrades = new ArrayList<Double>();
    }

    public CourseProfile(Course course, Student student,  ArrayList<Double> moduleGrades) { // from json
        this.course = course;
        this.student = student;
        this.moduleGrades = moduleGrades;
    }

    public double getGrade(){
        return this.courseGrade;
    }

    public ArrayList<Double> getGrades(){
        return this.moduleGrades;
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
        modules = course.getModules();
        String ret =  "Course: " + course.getTitle();
        for(int i = 0; i < modules.size(); ++i) {
            ret += "\n\tModule: " + modules.get(i).getTitle() + "\n\t\tGrade: ";
            if(moduleGrades.size() > i)
                ret += moduleGrades.get(i) + "/100.0";
            else
                ret += "incomplete";
        }
        return ret;
    }
}
