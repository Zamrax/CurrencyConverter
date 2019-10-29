package functionality;

import java.util.Scanner;

public class Working_Exchange {
	private Exchange_Process exchangeProcess;
	
	public Working_Exchange(String currency_from, String currency_to) {
		this.exchangeProcess = new Exchange_Process(currency_from, currency_to);
	}
	
	public void work(double value_from) {
		double finalValue = this.exchangeProcess.calculateValue(value_from);
		System.out.println("You have converted from " + exchangeProcess.getCurrencyFrom().getCurrency() + 
				" to " + exchangeProcess.getCurrencyTo().getCurrency() + " with exchange rate of " +
				exchangeProcess.getCurrencyFrom().getRate() + ":" + exchangeProcess.getCurrencyTo().getRate() + ", which in the end gives you " + 
				finalValue + " in " + exchangeProcess.getCurrencyTo().getCurrency());
	}
	
	public static void main(String [] args){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter currency, which you want to exchange: ");
		String from = scanner.nextLine();
		System.out.println("Enter currency, which you want to exchange to: ");
		String to = scanner.nextLine();
		System.out.println("Enter amount you want to exchange: ");
		String amountString = scanner.nextLine();
		scanner.close();
		Working_Exchange exchange = new Working_Exchange(from, to);
		double amount = Double.parseDouble(amountString);
		exchange.work(amount);
	}
}
