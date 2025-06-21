class foreign{
    public double foreignCar(double k){
        return 3.2*k;
    }
}

public class car{
    int seat = 5;
    int x=0;

    public car(int seat){ //static method
        this.seat = seat;
    }

    public int seatOption(int x){
        return this.seat = this.seat-x;
    }

    public static double fuelCost(double x, double y){
        return x*y;
    }

    public static void main(String[] args) {
        double fuel=car.fuelCost(2.03, 50);
        foreign a = new foreign();
        car alza = new car(7);
        System.out.println("The number of seat: "+ alza.seat);
        System.out.println("The new number of seat: " + alza.seatOption(2));
        System.out.println("The fuel cost: Rm" + fuel);
        System.out.println("Foreign car cost: RM" + a.foreignCar(50));
    }
}