public class Animal {

    public static void main (String[] args) {
        pets dog = new Dog();
        dog.sound();
        pets cat = new Cat();
        cat.sound();
        pets ryan = new Ryan();
        ryan.sound();
    }
}

class pets{
    void sound(){
        System.out.println("pets make sound.");
    }
}

class Dog extends pets {
    @Override
    void sound() {
        super.sound();
        System.out.println("Dog barks");
    }
}

class Cat extends pets {
    @Override
    void sound() {
        super.sound();
        System.out.println("Cat miao");
    }
}

class Ryan extends pets{
    @Override
    void sound() {
        super.sound();
        System.out.println("ei zhihong, what we need do now");
    }
}