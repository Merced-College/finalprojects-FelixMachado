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

    public void loadCSV (String filePath, List<Map<String, String>> deviceList){
        try(bufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            String[] headers = br.readerLine().split(",");

            while ((line = br.readLine()) !=null){
                String[] values = line.split(",");
                Map<String, String> row = new HashMap<>();
                for(int i = 0; i< headers.length && i <values.length; i++){
                    row.put(headers[i].trim(), values[i].trim());
                }
                deviceList.add(row);
            }
        }
        //This will help determine where the file is trying to read from
        catch (IOException e){
            System.out.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
    }
    public List<Map<String, String>> getDevicesByCost(List<Map<String, String>> deviceList, String costLevel){
        return deviceList.stream()
        .filter(device -> device.get("Cost_Level") != null && device.get("Cost_Level").equalsIgnoreCase(costLevel))
        .collection(Collectors.toList());
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
/////////////////
/// ////////////////////
/// 
    

    }

    
}