import java.util.Scanner;

public class BMI{
    public static void main(String[] args) {
        String classification = "";
        Scanner input = new Scanner(System.in);
        System.out.println("Enter weight in kg: ");
        double weight = input.nextDouble();

        System.out.println("Enter height in meters: ");
        double height = input.nextDouble();

        System.out.println("You BMI is " + BmiCalculate(weight,height));


        if (BmiCalculate(weight,height) < 18.5){
            classification = "Underweight";
        }
        else if ((BmiCalculate(weight, height) >= 18.5) && (BmiCalculate(weight, height) <= 24.9)){
            classification = "Normal";
        }
        else if ((BmiCalculate(weight, height) >= 25.0) && (BmiCalculate(weight, height) <= 29.0)){
            classification = "Overweight";
        }
        else if ((BmiCalculate(weight, height) >= 30.0) && (BmiCalculate(weight, height) <= 40.0)){
            classification = "Obese";
        }
        else{
            classification = "Extreme Obese";
        }

        System.out.println("Your Classification is :" + classification);
    }
    public static double BmiCalculate(double w, double h){
        double BMI;
        BMI = w/(h+h);
        return BMI;
    }
}