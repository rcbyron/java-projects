/* CRITTERS OldMain.java
 * EE422C Project 4 submission by
 * Robert (Connor) Byron
 * rcb2746
 * 76550
 * Joel Guo
 * jg55475
 * 76550
 * Slip days used: 1
 * Spring 2016
 */
package project5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class OldMain {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		//Scanner sc = new Scanner(new File("project4/sim2.txt"));
		System.out.print("critter> ");
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			
			try {
				String[] cmd_args = line.split("\\s+");
				
				switch(cmd_args[0].toLowerCase()) {
				case "quit":  	if (cmd_args.length > 1) throw new Exception();
								System.exit(0);
								break;
								
				case "show":  	if (cmd_args.length > 1) throw new Exception();
								Critter.displayWorld();
								break;
								
				case "step":  	if (cmd_args.length > 2) throw new Exception();
								int steps = cmd_args.length > 1 ? Integer.parseInt(cmd_args[1]) : 1;
				              	for (int i = 0; i < steps; i++)
				              		Critter.worldTimeStep();
				              	break;
				              	
				case "seed":  	if (cmd_args.length > 2) throw new Exception();
								Critter.setSeed(Long.parseLong(cmd_args[1]));
								break;
								
				case "make":  	if (cmd_args.length > 3) throw new Exception();
								int amt = cmd_args.length > 2 ? Integer.parseInt(cmd_args[2]) : 1;
							  	for (int i = 0; i < amt; i++)
							  		Critter.makeCritter(cmd_args[1]);
							  	break;
							  	
				case "stats": 	if (cmd_args.length > 2) throw new Exception();
								List<Critter> bugs = Critter.getInstances(cmd_args[1]);
								Class<?> cls = Class.forName(cmd_args[1]);
								cls.getMethod("runStats", java.util.List.class).invoke(null, bugs);
								break;
								
				default:        System.out.println("invalid command: "+line);
								
				}
			} catch (Exception e) {
				System.out.println("error processing: "+line);
				//e.printStackTrace();
			}
			System.out.print("critter> ");
		}
		sc.close();
	}
}
