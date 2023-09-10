public class Car implements Comparable<Car>{
    private String carID;
    private int mileage;
    private int tankSize;
    private int fuelLevel;

    //constructors
    public Car(String carIDString){
        carID = carIDString;
    }
    public Car(String carID, int mileage, int tankSize, int fuelLevel){
        this.carID = carID;
        this.mileage = mileage;
        this.tankSize = tankSize;
        this.fuelLevel = fuelLevel;
    }
    public Car(String[] carString){
        carID = carString[0];
        mileage = Integer.parseInt(carString[1]);
        tankSize = Integer.parseInt(carString[2]);
        fuelLevel = Integer.parseInt(carString[3]);
    }

    //get and set methods
    public void setCarID(String carID) {this.carID = carID;}
    public void setFuelLevel(int fuelLevel) {this.fuelLevel = fuelLevel;}
    public void setMileage(int mileage) {this.mileage = mileage;}
    public void setTankSize(int tankSize) {this.tankSize = tankSize;}

    public String getCarID() {return carID;}
    public int getFuelLevel() {return fuelLevel;}
    public int getMileage() {return mileage;}
    public int getTankSize() {return tankSize;}
    

    @Override
    public int compareTo(Car otherCar){
        return this.carID.compareTo(otherCar.getCarID());
    }

    @Override
    public String toString(){
        return carID + "," + mileage + "," + tankSize + "," + fuelLevel;
    }
    
}
