/**
* Felix Oliveira-Machado
* 04/29/2025
* FinalProject
**/


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**This class will be used to import data from the CSV files
 * and create the objects that will be referenced by the main class
 * using an Arraylist.
 * */


public class DeviceManager {
    
    //Creates the ArrayLists that will be used to store the objects
    private List<Map<String, String>>cablesList = new ArrayList<>();
    private List<Map<String, String>>firewallsList = new ArrayList<>();
    private List<Map<String, String>>switchesList = new ArrayList<>();
    private List<Map<String, String>>routersList = new ArrayList<>();

    public static void main(String[] args) {
        // Define file paths relative to the project root created with the help of Copilot
        String firewallsFilePath = "Firewalls.csv";
        String switchesFilePath = "Switches.csv";
        String routersFilePath = "Routers.csv";
        String cablesFilePath = "Cables.csv";

        
     
    }
}