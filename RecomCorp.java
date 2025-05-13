/**
* Felix Oliveira-Machado
* 04/29/2025
* FinalProject
**/

import java.util.*;
import java.util.stream.Collectors;


public class RecomCorp {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner (System.in);
		DeviceManager manager = new DeviceManager();
		
		// This portion of the algo is used to load all CSVs
		manager.loadCSV("Cables.csv", manager.getCablesList());
		checkDataLoaded("Cables", manager.getCablesList());
		manager.loadCSV("Routers.csv", manager.getRoutersList());
		checkDataLoaded("Routers", manager.getRoutersList());
		manager.loadCSV("Switches.csv", manager.getSwitchesList());
		checkDataLoaded("Switches", manager.getSwitchesList());
		manager.loadCSV("Firewalls.csv", manager.getFirewallsList());
		checkDataLoaded("Firewalls", manager.getFirewallsList());
		
		System.out.println("Welcome to RecomCorp device guidance system!");
		
		String[] categories = {"Switches", "Firewalls", "Routers", "Cables"};
		for (String category : categories) {
		    String budget = getUserBudgetSelection(scanner, category);
		    List<Map<String, String>> matchingItems = filterByCostLevel(
		        switch (category) {
		            case "Switches" -> manager.getSwitchesList();
		            case "Firewalls" -> manager.getFirewallsList();
		            case "Routers" -> manager.getRoutersList();
		            case "Cables" -> manager.getCablesList();
		            default -> new ArrayList<>();
		        }, budget);
		    System.out.println(category + " that match your budget:");
		    displayOptions(category, matchingItems);
		}
	}

	// This method will be used to get the user's budget selection
	private static String getUserBudgetSelection(Scanner scanner, String category) {
	    while (true) {
	        System.out.println("Select your budget for " + category + ":\na. High\nb. Mid\nc. Low");
	        String input = scanner.nextLine().trim().toLowerCase();
	        switch (input) {
	            case "a": return "High";
	            case "b": return "Mid";
	            case "c": return "Low";
	            default:
	                System.out.println("Invalid input. Please try again.");
	        }
	    }
	}

	// This portion of the algorithm is used to filter the devices by cost level
	private static List<Map<String, String>> filterByCostLevel(List<Map<String, String>> items, String costLevel){
		return items.stream().filter(map-> costLevel.equalsIgnoreCase(map.get("Cost_Level")))
				.collect(Collectors.toList());
	}

	// This portion of the algorithm is used to display the options that match the user's budget
	private static void displayOptions(String category, List<Map<String, String>> options) {
	    System.out.println("\nRecommended " + category + ":");

	    // If there is no match, notify the user
	    if (options.isEmpty()) {
	        System.out.println("No matching options found.");
	    } else {
	        for (Map<String, String> item : options) {
	            System.out.println("-------------------------");
	            for (Map.Entry<String, String> entry : item.entrySet()) {
	                System.out.println(entry.getKey() + ": " + entry.getValue());
	            }

	            // Check and display Security_Features if present
	            if (item.containsKey("Security_Features")) {
	                System.out.println("Security Features: " + item.get("Security_Features"));
	            }
	        }
	    }
	}

	// Add a method to check if the lists are empty after loading
	private static void checkDataLoaded(String category, List<Map<String, String>> list) {
	    if (list.isEmpty()) {
	        System.out.println("Warning: No data loaded for " + category + ".");
	    }
	}

}