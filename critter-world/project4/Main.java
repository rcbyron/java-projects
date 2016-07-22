/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Summer 2016
 */
package project4;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InvalidCritterException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.print("critter> ");
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] cmd_args = line.split("\\s+");
			
			switch(cmd_args[0].toLowerCase()) {
			case "quit":  	System.exit(0);         break;
			case "show":  	Critter.displayWorld(); break;
			case "step":  	int steps = cmd_args.length > 1 ? Integer.parseInt(cmd_args[1]) : 1;
			              	for (int i = 0; i < steps; i++)
			              		Critter.worldTimeStep();
			              	break;
			case "seed":  	Critter.setSeed(Long.parseLong(cmd_args[1]));
			case "make":  	int amt = cmd_args.length > 2 ? Integer.parseInt(cmd_args[2]) : 1;
						  	for (int i = 0; i < amt; i++)
						  		Critter.makeCritter(cmd_args[1]);
						  	break;
			case "stats": 	List<Critter> bugs = Critter.getInstances(cmd_args[1]);
							Class<?> cls = Class.forName(cmd_args[1]);
							((Critter)cls).runStats(bugs);
							
			}
			System.out.print("critter> ");
		}
	}
}
