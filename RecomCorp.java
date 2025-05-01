
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
		manager.loadCSV("Routers.csv", manager.getRoutersList());
		manager.loadCSV("Switches.csv", manager.getSwitchesList());
		manager.loadCSV("Firewalls.csv", manager.getFirewallsList());
		
		System.out.println("Welcome to RecomCorp device guidance system!");
		
		//First the user will be asked to pick a budget for their Switch
		String switchBudget = getUserBudgetSelection(scanner);
		List<Map<String, String>> matchingSwitches = filterByCostLevel(manager.getSwitchesList(), switchBudget);
		System.out.println("Switches that match your budget:");
		displayOptions("Switches", matchingSwitches);
		
		//Then the user will be asked to pick a budget for their Firewall
		String firewallBudget = getUserBudgetSelection(scanner);
		List<Map<String, String>> matchingFirewalls = filterByCostLevel(manager.getFirewallsList(), firewallBudget);
		System.out.println("Firewalls that match your budget:");
		displayOptions("Firewalls", matchingFirewalls);

		//Then the user will be asked to pick a budget for their Router
		String routerBudget = getUserBudgetSelection(scanner);
		List<Map<String, String>> matchingRouters = filterByCostLevel(manager.getRoutersList(), routerBudget);
		System.out.println("Routers that match your budget:");
		displayOptions("Routers", matchingRouters);

		//Then the user will be asked to pick a budget for their Cable
		String cableBudget = getUserBudgetSelection(scanner);
		List<Map<String, String>> matchingCables = filterByCostLevel(manager.getCablesList(), cableBudget);
		System.out.println("Cables that match your budget:");
		displayOptions("Cables", matchingCables);
	}

	// This method will be used to get the user's budget selection
	private static String getUserBudgetSelection(Scanner scanner) {
		System.out.println("a. High\nb. Mid\nc. Low");
		String input = scanner.nextLine().trim().toLowerCase();
		return switch (input){
			case "a" -> "High";
			case "b" -> "Mid";
			case "c" -> "Low";
			default -> {
				System.out.println("Invalid input. Deaulting to 'Mid'");
				yield"Mid";
			}
		};

	}

	// This portion of the algorithm is used to fileter the devices by cost level
	private static List<Map<String, String>> filterByCostLevel(List<Map<String, String>> items, String costLevel){
		return items.stream().filter(map-> costLevel.equalsIgnoreCase(map.get("Cost_Level")))
				.collect(Collectors.toList());
	}

	// This portion of the algorithm is used to display the options that match the user's budget
	private static void displayOptions(String category, List<Map<String, String>> options){
		System.out.println("\nRecommended " + category +":");
		//if there is no match the user will be notified
		if (options.isEmpty()){
			System.out.println("No matching options found.");
		}
		else{
			for(Map<String, String> item : options){
				System.out.println("-------------------------");
				for(Map.Entry<String, String> entry : item.entrySet()) {
					System.out.println(entry.getKey() + ": " + entry.getValue());
				}

			}

		}
	}

}