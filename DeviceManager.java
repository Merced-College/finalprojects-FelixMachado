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
    public void loadCSV (String filePath, List<Map<String, String>> deviceList){
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            String[] headers = br.readLine().split(",");

            while ((line = br.readLine()) !=null){
                String[] values = line.split(",");
                //add parsing for string
                Map<String, String> row = new HashMap<>();
                //for(int i = 0; i< headers.length && i <values.length; i++){
                for(int i = 0; i< headers.length && i < 3; i++){
                    row.put(headers[i].trim(), values[i].trim());
                }
                String fourthColumn = line.substring(line.indexOf("/"")),/////////////////////////////////////////////////////////////////////////////////////////////////////////////
                deviceList.add(row);
            }
        }
        //This will help determine where the file is trying to read from
        catch (IOException e){
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