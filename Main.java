# Big-Bank-System
import java.util.Scanner;

public class Main {
	static Scanner scan = new Scanner(System.in);
	static Bank b1 = new Bank();
	static int option = 0;
	static int quit = 0;

	public static void main(String[] args) {
		
		while(quit == 0 ){
			System.out.println("1. to Create a New Branch Location");
			System.out.println("2. to Add a new Customer to the specific branch");
			System.out.println("3. to Deposit money for customer without branch knowledge");
			System.out.println("4. to Deposit money for customer with knowledge of branch(Slow)");

			System.out.println("4. to Withdraw money for customer at specific branch");
			System.out.println("5. to Print All branch locations");
			option = scan.nextInt();

			switch (option) {
			case 1:
				openNewLocation();
				break;
			case 2:
				System.out.println("Enter your location");
				String location = scan.next();
				newCustomer(location);
				break;
			case 3:
				System.out.println("Enter amount to be deposited");
				String nameIn = scan.next();
				System.out.println("Enter amount to be deposited");
				double pay = scan.nextDouble();
				depositToCustomer(nameIn, pay);
				break;
				
			case 4:
				System.out.println("What is your bank location?");
				String locationIn = scan.next();

			case 6:
				quit();
				break;
			
				

			}
		}

		
		 
		

		
		
//		Bank bank1 = new Bank();
//		bank1.printBranchAddressesWithinBank();
//
//		bank1.createNewBranch();
//		bank1.createNewBranch();
//		bank1.createNewBranch();
//		
//		bank1.printBranchAddressesWithinBank();
//
//		System.out.println("Enter bank address you want to register to");
//		String addresstoSearch = scan.next();
//		bank1.addNewCustomerToBranch(bank1.searchBranchAddress(addresstoSearch));
//		bank1.addNewCustomerToBranch(bank1.searchBranchAddress(addresstoSearch));
		
//		Branch b1 = new Branch();
//		b1.newCustomer();
//		b1.newCustomer();
//		b1.newCustomer();
//
//		b1.branchDeposit(b1.branchCustomerSearch("jane"), 250);
//		b1.printCustomerListAtBranch();

//		Customer cust1 = new Customer("Tunwa", 55.2);
//		cust1.deposit(44.8);
//		cust1.spend(5);
//		System.out.println(cust1.getTransactions());
//		cust1.setName(scan.next());

	}

	private static void depositToCustomer(String nameIn, double pay) {
		b1.CustomerDeopsit(nameIn,  pay);
	}

	private static void quit() {
		quit = 1;
	}
	public static void newCustomer(String locationIn) {
		b1.addNewCustomerToBranch(b1.searchBranchAddress(locationIn));
	}
	
	public static void openNewLocation(){
		b1.createNewBranch();
	}

}
