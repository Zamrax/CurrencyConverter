package functionality;

public class Currency {
	private String currency;
	private String country;
	private double rate;
	
	public Currency(String currency, String country, double rateNumber) {
		this.currency = currency;
		this.country = country;
		this.rate = rateNumber;
	}
	
	public Currency(Currency anotherCurrency) {
		this.currency = anotherCurrency.currency;
		this.country = anotherCurrency.country;
		this.rate = anotherCurrency.rate;
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	public double getRate() {
		return this.rate;
	}
	
	public String getCurrency() {
		return this.currency;
	}
	
	public String getCountry() {
		return this.country;
	}
}
