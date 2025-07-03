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

public class f {
    public static void main(String[] args) {
        Fruit f = new Apple();
        Apple a = (Apple)f;
        a.appleType();
        a.describe();

        Fruit B = new Fruit();
        B.describe();
    }
}
