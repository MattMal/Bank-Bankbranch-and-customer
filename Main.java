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

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
	
	String bankName;
	Scanner scan = new Scanner(System.in);
	ArrayList<Branch> branchList = new ArrayList<Branch>();
	
	public Bank(){
		System.out.println("Enter bank Name");
		this.bankName = scan.next();
	}
	
	public void createNewBranch(){
		branchList.add(new Branch());
	}
	
	public void addNewCustomerToBranch(Branch branch){
		if(branch == null){
			System.out.println("That branch does not existt");
			return;
		}
		branch.newCustomer();
	}
	
	public void addDepositToCustomerAtBranch(Branch branch, Customer customerIn, double pay){
		if(branch == null || customerIn == null){
			System.out.println("That branch or customer does not existt");
			return;
		}
		branch.branchDeposit(customerIn, pay);
	}
	
	public void addWithdrawalToCustomerAtBranch(Branch branch, Customer customerIn, double pay){
		if(branch == null || customerIn == null){
			System.out.println("That branch or customer does not existt");
			return;
		}
		branch.branchWithdrawal(customerIn, pay);
	}
	
	public Branch searchBranchAddress(String address){
		if(branchList.size() == 0){
			System.out.println("There are no branches");
			return null;
		}
		for(int i = 0; i<branchList.size(); i++){
			if(address.equals(branchList.get(i).getAddress())){
				return branchList.get(i);
			}
		}
		System.out.println("Branch with Address does not exist");
		return null;
		
	}
	public void CustomerDeopsit(String nameIn, double payIn){
		searchCustomerThroughEntireSystem(nameIn).deposit(payIn);
	}
	
	public Customer searchCustomerThroughEntireSystem(String nameIn){
		for(int i = 0; i<branchList.size(); i++){
			for(int j = 0; j<branchList.get(j).customerList.size(); j++){
				if(nameIn.equals(branchList.get(i).customerList.get(j).getName())){
					return branchList.get(i).customerList.get(j);
				}
			}
			
		}
		System.out.println("There is no customer with that name");
		return null;
		
	}	
	
	public void printAllBranchAddresses(){
		System.out.println("The locations of " +bankName+" bank branches are: ");
		for (int i = 0; i<branchList.size(); i++){
			System.out.println(branchList.get(i).getAddress());
		}
	}
	
}


import java.util.ArrayList;
import java.util.Scanner;

public class Branch {
	String address;
	
	ArrayList<Customer> customerList = new ArrayList<Customer>();
	int customerNumber = 0;
	int branchTransactionNumber = 0;
	Scanner scan = new Scanner(System.in);
	
	public Branch(){
		System.out.println("Enter your Branch Address");
		this.address = scan.next();
	}
	
	
	

	public Customer newCustomer() {
		System.out.println("Please enter the name of the Customer\r");
		String cusName = scan.next();
		System.out.println("Please enter the initial deposit amount\r");
		double initDep = scan.nextDouble();
		
		if(branchCustomerSearch(cusName) == null){
			customerList.add(new Customer(cusName, initDep));
			return customerList.get(customerList.size()-1);		

		}
		System.out.println("Customer already exists");
		
		return null;		
	}
	
	public void branchDeposit(Customer customerIn, double pay){
		
		if(branchCustomerSearch(customerIn.getName()) != null ){
			customerIn.deposit(pay);
			branchTransactionNumber++;
			return;
		}
		
	}
	
	
	
	public String getAddress() {
		return address;
	}

	public void branchWithdrawal(Customer customerIn, double pay){
		if(customerIn == null){
			return;
		}
		branchCustomerSearch(customerIn.getName()).spend(pay);
		branchCustomerSearch(customerIn.getName()).getTransactions().add(pay);
		branchTransactionNumber++;
	}
	
	public void removeCustomer(Customer customerIn){
		if(customerIn == null){
			return;
		}
		customerList.remove(customerIn);
	}
	
	public Customer branchCustomerSearch(String nameIn){
		
		for(int i = 0; i<customerList.size(); i++){
			if(customerList.get(i).getName().equalsIgnoreCase(nameIn)){
				return customerList.get(i);
			}
		}
		System.out.println("That customer does not exist at this branch");
		return null;
	}
	
	public void printCustomerListAtBranch(){
		for(int i = 0; i<customerList.size(); i++){
			System.out.println(customerList.get(i).getName() +": "+ customerList.get(i).getCurrentBalance());
		}
	}

}

import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
	String name;
	double initialDeposit;
	ArrayList<Double> transactions;
	double currentBalance;
	Scanner scan = new Scanner(System.in);


	public Customer(String nameIn, double initialDepositIn) {
		this.name = nameIn;
		this.initialDeposit = initialDepositIn;
		this.currentBalance = initialDepositIn;
		this.transactions = new ArrayList<Double>();
		this.transactions.add(initialDeposit);
	}
	
		
	public String getName() {
		return name;
	}

	public void setName(String nameIn) {
		this.name = nameIn;
	}

	
	public ArrayList<Double> getTransactions() {
		return transactions;
	}

	public void setInitialDeposit(double initialDepositIn) {
		this.initialDeposit = initialDepositIn;
	}
	
	public void spend(double pay){
		currentBalance = currentBalance - pay;
		transactions.add(pay);
	}
	
	public void deposit(double pay){
		currentBalance = currentBalance + pay;
		transactions.add(pay);
	}
	
	public double balance(){
		return currentBalance;
	}
	

	public double getCurrentBalance() {
		return currentBalance;
	}


	public void printList(){
		System.out.println(getName());
		for(int i  = 0; i<transactions.size(); i++){
			System.out.println(transactions.get(i));

		}
	}
}
