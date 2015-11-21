/*
 * Linked list test harness.
 */
package linkedlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 *
 * @author gmorgan
 */
public class LinkedListHarness {

	/**
	 * Display simple user menu.
	 * 
	 * @return Return the users numerical selection on success, 0 otherwise.
	 */
	public static int menu() {
		int i;
		String str;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("\n");
		System.out.print("Menu\n");
		System.out.print("------\n");
		System.out.print("  1 Add an item.\n");
		System.out.print("  2 Dump the list.\n");
		System.out.print("  3 Exit\n");
		System.out.print("\n");
		System.out.print("Choice: ");

        try {
            str = reader.readLine().trim();
        } catch(IOException ioe){
            System.err.println("Read failed.\n");
			return 0;
		}

		try {
			i = Integer.parseInt(str);
		} catch(NumberFormatException nfe) {
			return 0;
		}
	
		return i;
	}


	/**
	 * Dump the contents of the list.
	 * 
	 * There is something very inefficient about this method; you should understand what the issue is asap.
	 *
	 * @param list The list.
	 */
	public static void dumpList(LinkedList list) {
		int len = list.size();

		if (len == 0) {
			System.out.print("This list is empty.\n");
			return;
		}

		for (int i = 0;  i < len; i++) {
			System.out.print(i + ": " + list.get(i) + "\n");
		}
	}	

	/**
	 * Add an item to the list.
	 * 
	 * @param list The list.
	 * @return boolean Return true on success, false otherwise.
	 */
	public static boolean addItem(LinkedList list) {
		boolean stat;
		String str;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Enter your item: ");

        try {
            str = reader.readLine().trim();
        } catch(IOException ioe){
            System.err.println("Read failed.\n");
			return false;
		}		

		if (str.trim().equals("")) {
			System.err.println("Empty strings are not valid.\n");
			return false;
		}

		// Preserve type. If a user enters 7, they expect the number 7 to be stored not "7".
		// This is import because 7 + 0 = 7, not "70".

		try {
			stat = list.add(Integer.parseInt(str));
		} catch(NumberFormatException nfe) {
			stat = list.add(str); // do nothing, just treat the input as a string
		}

		return stat;
	}
	
	/**
	 * Main
	 * 
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {

		int choice;

		// create list to store data
		LinkedList ll = new LinkedList();

		// loop prompting the user for an action
		do {
			choice = menu();
			
			switch (choice) {
				case 1:
					addItem(ll);
					break;
				case 2:
					dumpList(ll);
					break;
				case 3:
					break;
				default:
					System.err.print("Invalid choice.\n");
			}

		} while (choice != 3);

	}

}

