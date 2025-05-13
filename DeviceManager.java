/**
* Felix Oliveira-Machado
* 04/29/2025
* FinalProject
**/


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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


    //This portion of the algorithm is used to load the data from the CSV files
    public void loadCSV(String filePath, List<Map<String, String>> deviceList) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] headers = br.readLine().split(",");

            while ((line = br.readLine()) != null) {
                // Use a regex to split the line while respecting quoted fields
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (values.length < headers.length) {
                    // Skip lines that do not have enough columns
                    continue;
                }

                Map<String, String> row = new HashMap<>();

                // Process the first three columns
                for (int i = 0; i < headers.length && i < 3; i++) {
                    row.put(headers[i].trim(), values[i].trim());
                }

                // Handle the fourth column (substring between quotes)
                if (values.length > 3) {
                    String fourthColumn = values[3];
                    if (fourthColumn.contains("\"")) {
                        int startIndex = fourthColumn.indexOf("\"") + 1;
                        int endIndex = fourthColumn.lastIndexOf("\"");
                        if (startIndex < endIndex) {
                            fourthColumn = fourthColumn.substring(startIndex, endIndex);
                        }
                    }
                    row.put(headers[3].trim(), fourthColumn.trim());
                }

                deviceList.add(row);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
    }
    // Here the the algorithm is filitering the devices by cost, as you can see it is referencing one of the columns in the CSV
    public List<Map<String, String>> getDevicesByCost(List<Map<String, String>> deviceList, String costLevel){
        return deviceList.stream()
        .filter(device -> device.get("Cost_Level") != null && device.get("Cost_Level").equalsIgnoreCase(costLevel))
        .collect(Collectors.toList());
    }
    public List<Map<String, String>> getRoutersList(){
        return routersList;
    }

    public List<Map<String, String>> getSwitchesList(){
        return switchesList;
    }

    public List<Map<String, String>> getFirewallsList(){
        return firewallsList;
    }

    public List<Map<String, String>> getCablesList(){
        return cablesList;
    }

    public static void main(String[] args){
        //This will be used for testing
        //String firewallsFilePath = "Firewalls.csv";
        //String routersFilePath = "Routers.csv";
       // String switchesFilePath = "Switches.csv";
        //String cablesFilePath = "Cables.csv";

        //This is used to show the file paths
       // System.out.println("Firewalls file path: "+ firewallsFilePath);
       // System.out.println("Routers file path: "+ routersFilePath);
       // System.out.println("Switches file path: "+ switchesFilePath);
       // System.out.println("Cables file path: "+ cablesFilePath);
    

    } 
}