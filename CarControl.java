import java.io.IOException;
import java.util.ArrayList;

public class CarControl {
    public static void main(String[] args){
        CarTable carTable;
        try {
            //start with "F0001,15000,25,5" written in file
            carTable = new CarTable("CarDatabase.csv");

            System.out.println(carTable.getNumElementsInDatabase()); //should print 1

            ArrayList<Car> myCarList = new ArrayList<>();
            myCarList.add(new Car("F0002", 13000, 20, 20));
            myCarList.add(new Car("F0004", 10000, 15, 12));
            myCarList.add(new Car("F0003", 17000, 20, 16));
            int i = carTable.createAll(myCarList);
            System.out.println(i); //should print 0 for no errors

            boolean j = carTable.create(new Car("F0001", 12000, 20, 20)); 
            System.out.println(j); //should return false, finding a match and stopping the creation

            carTable.save(); //should fill next three lines of database for a total of 4 items

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
