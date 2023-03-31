import java.util.ArrayList;

/**
 * A profile for a user's courses
 */
public class CourseProfile {
    private Course course;
    private ArrayList<Double> moduleGrades;
    private double courseGrade;
    private Student student;
    private ArrayList<Module> modules;

    /**
     * Creating a new course profile with overloaded parameters for later use
     * @param course the course the student is in
     * @param student the student taking the course
     */
    public CourseProfile(Course course, Student student) {
        this.course = course;
        this.student = student;
        this.moduleGrades = new ArrayList<Double>();
    }

    /**
     * Creating a new course profile
     * @param course the course the student is in
     * @param student the student taking the course
     * @param moduleGrades the grades the student gets on the modules
     */
    public CourseProfile(Course course, Student student,  ArrayList<Double> moduleGrades) { // from json
        this.course = course;
        this.student = student;
        this.moduleGrades = moduleGrades;
    }

    /**
     * Getting the grade for the course
     * @return the course grade
     */
    public double getGrade(){
        return this.courseGrade;
    }

    /**
     * Getting the list of grades for the different modules
     * @return the module grades
     */
    public ArrayList<Double> getGrades(){
        return this.moduleGrades;
    }

    /**
     * Getting the student
     * @return the student
     */
    public Student getStudent(){
        return this.student;
    }

    /**
     * Getting the course
     * @return the course
     */
    public Course getCourse() {
        return this.course;
    }

    /**
     * Entering the overall grade to the course profile
     * @param grade the overall grade
     */
    public void enterGrade(double grade){
        
        moduleGrades.add(grade);

        courseGrade = courseGrade + ((grade-courseGrade)/moduleGrades.size()); // avg(new) = avg(old) + ((val - avg(old))/size(new))

    }

    /**
     * The certificate for users who complete a course 
     * with above an 80%
     */
    public static void certificate(){
        DataWriter.certificateFile();
    }

    /**
     * The course profile display
     * @return the information for the course profile if there is information
     * or incomplete if the course is not complete.
     */
    public String toString() {
        modules = course.getModules();
        String ret =  "Course: " + course.getTitle() + " - Overall Grade: " + courseGrade + "\nModule Grades:  ";
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
