import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class CarTable {
    private String carDatabaseFile;
    private ArrayList<Car> carDatabase;

    //constructor
    public CarTable(String cardb) throws IOException {
        carDatabaseFile = cardb;
        carDatabase = readAll();
    }


    public int createAll(ArrayList<Car> carList){
        Collections.sort(carList);
        sort();
        int numFailed = 0;
        boolean success;
        for(Car car : carList){
            success = create(car);
            if(success == false){numFailed++;}
        }
        return numFailed;
    }

    public boolean create(Car newCar){
        //search for matching newCar.getCarID. If it exists, then return false. Else insert into arraylist
        if (search(newCar.getCarID()) != -1){
            return false;
        }
        carDatabase.add(newCar);
        return true;
    }


    public ArrayList<Car> readAll() throws IOException{
        ArrayList<Car> databaseList = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(carDatabaseFile));
            String lineString = reader.readLine();
            while (lineString != null){
                String[] carStringArr = lineString.split(",");
                databaseList.add(new Car(carStringArr));
                lineString = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new IOException("Error reading the database file: " + e.getMessage());
        }

        return databaseList;
    }

    public Car get(int index){
        return carDatabase.get(index);
    }

    public Car readMatch(String carID){
        int index = search(carID);
        Car returnCar = get(index);
        return returnCar;
    }

    public void updateAll(ArrayList<Car> carList){
        Collections.sort(carList); //sort Arraylist of Cars
        sort(); //sorts data in file
        //replace lines in database only where carIDs match
        for(Car car : carList){
            update(car);
        }
    }

    public void update(Car updateCar){
        int index = search(updateCar.getCarID());
        carDatabase.set(index, updateCar);
    }

    public void delete(Car deleteCar){
        int index = search(deleteCar.getCarID());
        carDatabase.remove(index);
    }

    public void save() throws IOException{
        sort();
        FileOutputStream fileByStream = new FileOutputStream(carDatabaseFile, false);
        PrintWriter outFS = new PrintWriter(fileByStream);

        System.out.println("Saving to file ("+ carDatabase.size() + " items)");
        for(Car car : carDatabase){
            outFS.write(car.toString() + "\n");
        }
        
        outFS.close();

        fileByStream.close();
    }

    
    private int binarySearch(ArrayList<Car> car, String ID){
        // Returns index of x if it is present in arr[],
        // else return -1
        int left = 0;
        int right = car.size() - 1;
       
        while (left <= right){
            int mid = left + (right - left) / 2;
   
            if (car.get(mid).getCarID().compareTo(ID) == 0) // Check if x is present at mid
                return mid;
   
            if (car.get(mid).getCarID().compareTo(ID) < 0) // If x greater, ignore left half
                left = mid + 1;
   
            else // If x is smaller, ignore right half
                right = mid - 1;
        }
   
        return -1; // if we reach here, then element was not present
    }
    public int search(String carID){
        //return index of match
        sort();
        int result;
        result = binarySearch(carDatabase, carID);
        return result;
    }
    
    public void sort(){
        //sort all items in carDatabaseFile
        //check if sorted. If not sorted, then continue to next step.
        boolean isSorted = true;
        for(int i = 0; i < carDatabase.size()-1; i++){
            if(carDatabase.get(i).compareTo(carDatabase.get(i+1)) > 0){
                isSorted = false;
                break;
            }
        }
        //sort the carDatabase Arraylist
        if(isSorted == false){
            Collections.sort(carDatabase);
        }
    }

    public int getNumElementsInDatabase(){return carDatabase.size();}

}
