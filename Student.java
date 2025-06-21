//Name: Soo Wei Kang
//Student ID: B240150B

public class Student {
    int id;
    String name;
    double grade;
     
    public Student(int id, String name, double grade){
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
   
    public static void main(String[] args) {
        Student Albert = new Student(305, "Albert", 80.25);
        Student John = new Student(306, "John", 65.5);


        System.out.println("Student ID: " + Albert.id);
        System.out.println("Name: " + Albert.name);
        System.out.println("Grade: " + Albert.grade);
        System.out.println("");
        System.out.println("--------------------------");
        System.out.println("");

        System.out.println("Student ID: " + John.id);
        System.out.println("Name: " + John.name);
        System.out.println("Grade: " + John.grade);

    }
}
