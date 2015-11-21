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
	 * Menu return values.
	 */
	public enum Choice {
		ERROR,
		APPEND_LIST_ITEM,
		DUMP_LIST,
		EXIT_APPLICATION
	}

	/**
	 * Display simple user menu.
	 * 
	 * @return Return the users numerical selection on success, 0 otherwise.
	 */
	public static Choice menu() {
		int choiceIdx;
		Choice choice;
		String str;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("\n");
		System.out.print("Menu\n");
		System.out.print("------\n");
		System.out.print("  " + Choice.APPEND_LIST_ITEM.ordinal() + " Add an item.\n");
		System.out.print("  " + Choice.DUMP_LIST.ordinal() + " Dump the list.\n");
		System.out.print("  " + Choice.EXIT_APPLICATION.ordinal() + " Exit\n");
		System.out.print("\n");
		System.out.print("Choice: ");

        try {
            str = reader.readLine().trim();
        } catch(IOException ioe){
            System.err.println("Read failed. " + ioe.getMessage() + "\n");
			return Choice.ERROR;
		}

		try {
			choiceIdx = Integer.parseInt(str);
		} catch(NumberFormatException nfe) {
			return Choice.ERROR;
		}

		try {
			choice = Choice.values()[choiceIdx];
		} catch (Exception e) {
			choice = Choice.ERROR;
		}

		return choice;
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
	 * Add an item to the end of the list.
	 * 
	 * @param list The list.
	 * @return boolean Return true on success, false otherwise.
	 */
	public static boolean appendItem(LinkedList list) {
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
		Choice choice;

		// create list to store data
		LinkedList ll = new LinkedList();

		// loop prompting the user for an action
		do {
			choice = menu();

			switch (choice) {
				case APPEND_LIST_ITEM:
					appendItem(ll);
					break;
				case DUMP_LIST:
					dumpList(ll);
					break;
				case EXIT_APPLICATION:
					break;
				default:
					System.err.print("Invalid choice.\n");
			}

		} while (choice != Choice.EXIT_APPLICATION);
	}

}

