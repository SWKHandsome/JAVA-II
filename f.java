class Fruit {
    public void describe(){
        System.out.println("This is a fruit");
    }
}

class Apple extends Fruit{
    public void appleType(){
        System.out.println("This is a apple pen");
    }
}

class Banana extends Fruit{
    public void bananaType(){
        System.out.println(("This is a banana"));
    }
}

public class f {
    public static void main(String[] args) {
        Fruit f = new Apple();
        Apple a = (Apple)f;
        a.appleType();
        a.describe();

        Fruit B = new Fruit();
        B.describe();

        Fruit C = new Banana();
        Banana D = (Banana)C;
        D.describe();
        D.bananaType();
    }
}
