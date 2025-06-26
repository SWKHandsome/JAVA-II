public class Animal {

    public static void main (String[] args) {
        Dog dog = new Dog();
        dog.sound();
        Cat cat = new Cat();
        cat.sound();
        Ryan ryan = new Ryan();
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
        System.out.println("欸zhihong, 现在我们要做什么");
    }
}