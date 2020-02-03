package project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EventLoop {
	
	String[] tableNames = {"Book", "Author", "Publisher"};
	String[] tablePaths = {"src/project1/resources/Book.csv", "src/project1/resources/Author.csv", "src/project1/resources/Publisher.csv"};
	ArrayList<ArrayList<String[]>> tables ;
			
	Scanner sc;
	
	final int VIEW_RECORDS = 0;
	final int REMOVE_RECORD = 1;
	final int CREATE_RECORD = 2;
	final int UPDATE_RECORD = 3;
	final int GO_BACK = 4;
	
	
	public EventLoop(){
		this.sc = new Scanner(System.in);
		loadTables();
	}
	
	public void start() {
		System.out.println("Welcome to McSpanky's Library Management System. Choose a Table to Perform an Operation on and Then Press 'Enter'");
		System.out.println("");
		System.out.println("0) Book");
		System.out.println("1) Author");
		System.out.println("2) Publisher");
		System.out.println("3) Exit");
		System.out.println("");
		System.out.println("Enter here:");
		
		//add exist option

		String line1;
		int menuOption1 = -1;

		while (true) {
			line1 = sc.nextLine();
			try {
				menuOption1 = Integer.parseInt(line1);
				if (menuOption1 > -1 && menuOption1 < 4) {
					break;
				}
				
				else {
					System.out.println("Please Enter a Correct Input here:");
				}
				
			}
			catch (NumberFormatException e) {
				System.out.println("Please Enter a Correct Input Here:");
			}
		}
		
		if(menuOption1 == 3) {
			return;
		}
		
		else {
			mainMenuPart2(menuOption1);
		}
		
		
//		try (BufferedReader br = new BufferedReader(new FileReader("src/resources/Author.csv"))) {
//			System.out.println(br.readLine());
//		}
//		
//		catch(IOException e) {
//			System.out.println(e);
//		}
//		
//		
//		try (FileWriter databaseFile = new FileWriter("src/resources/Author.csv", true)) {
//			databaseFile.append("3");
//			databaseFile.append(",");
//			databaseFile.append("David Foster Wallace");
//			databaseFile.append("\n");
//			
//			databaseFile.flush();
//			databaseFile.close();
//		}
//		
//		catch(IOException e) {
//			System.out.println(e);
//		}
	
		
	}
	
	public void mainMenuPart2 (int menuOption1) {
		
		System.out.println("What Operation would you like to Perform on the " + tableNames[menuOption1] + " Table . Choose an option by pressing a number and then pressing 'Enter'");
		System.out.println("");
		System.out.println("0) View Records from a Table");
		System.out.println("1) Remove Record from a Table ");
		System.out.println("2) Insert New Record into Table");
		System.out.println("3) Update Record from a Table");
		System.out.println("4) Go Back");
		System.out.println("");
		System.out.println("Enter here:");
		
		String line2;
		int menuOption2 = -1;

		while (true) {
			line2 = sc.nextLine();
			try {
				menuOption2 = Integer.parseInt(line2);
				if (menuOption2 > -1 && menuOption2 < 5) {
					break;
				}		
				else {
					System.out.println("Please Enter a Correct Input here:");
				}
			}
			catch (NumberFormatException e) {
				System.out.println("Please Enter a Correct Input Here:");
			}
		} 
	
		switch (menuOption2) {

		case VIEW_RECORDS:
			viewRecords(menuOption1);
			break;

		case REMOVE_RECORD:

			break;

		case CREATE_RECORD:

			break;

		case UPDATE_RECORD:

			break;

		case GO_BACK:
			start();
			break;

		} // end of switch
			
	}
	
	public void loadTables() {
		
		this.tables = new ArrayList<ArrayList<String[]>>();
		

		
		for (String tablePath : tablePaths) {
			
			ArrayList<String[]> table = new ArrayList<String[]>();
			tables.add(table);
			
			try (BufferedReader br = new BufferedReader(new FileReader(tablePath))) {
				
				String line = br.readLine();
				
				while (line != null) {
				
					String[] columns = line.split(",");
					table.add(columns);
					line = br.readLine();	
				}
			}

			catch (IOException e) {
				System.out.println(e);
			}
			
	
		}
			
	}

	
	public void viewRecords(int tableOption) {
		
		ArrayList<String[]> table = tables.get(tableOption);
		
		System.out.println(tableNames[tableOption] + " Table");

		for (String[] record: table) {
			
			for(String s: record) {
				System.out.print(s + ", ");
			}
			
			System.out.println();
			
		}

	}
	
	public void createRecord(int tableOption) {
			
	}
	
	public void updateRecord(int tableOption) {
		
	}
	
	public void removeRecord(int tableOption) {
		
	}
	
	public void cascadeOnDelete() {
		
	}
	
	public void saveAndExit() {
		
	}
	
	

	public static void main(String[] args) {
		EventLoop eventLoop = new EventLoop();
		eventLoop.start();
	}
	
	
	

} //end of class definition
