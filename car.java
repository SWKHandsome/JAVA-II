// class foreign{
//     public double foreignCar(double k){
//         return 3.2*k;
//     }
// }

public class car{
    int seat = 5;
    int x=0;
    double cost = 2.03;

    public car(int seat){ //static method
        this.seat = seat;
    }

    public double foreignCar(double k){
        return 3.2*k;
    }

    public int seatOption(int x){
        return this.seat = this.seat-x;
    }

    // public static double fuelCost(double x, double y){
    //     return x*y;
    // }

    public double fuelcost(double x){
        return this.cost = this.cost*x;
    }

    public static void main(String[] args) {
        // double fuel= fuelCost(2.03, 50);
        // foreign a = new foreign();
        car alza = new car(7);
        System.out.println("The number of seat: "+ alza.seat);
        System.out.println("The new number of seat: " + alza.seatOption(2));
        // System.out.println("The fuel cost: Rm" + fuel);
        System.out.println("The fuel cost: RM" + alza.fuelcost(50));
        System.out.println("Foreign car cost: RM" + alza.foreignCar(50));
    }
}