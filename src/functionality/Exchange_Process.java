package functionality;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Exchange_Process {
	private Currency [] ca;
	private Currency currency_from;
	private Currency currency_to;
	private website website;
	private NodeList currencies;
	

	public Exchange_Process(String currency_from, String currency_to) {
		this.website = new website();
		this.currencies = this.website.getNodes();
		this.ca = new Currency[149];
		this.ca[0] = new Currency("USD", "U.S.A", 1.0);
		String[] listCurrency = new String[148];
		String[] listNames = new String[148];
		String[] listRates = new String[148];
		int q = 0;
		for(int i = 0; i < this.currencies.getLength(); i++) {
			if (this.currencies.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element el = (Element) this.currencies.item(i);
				if (el.getNodeName().contains("item")) {
					String currency = el.getElementsByTagName("targetCurrency").item(0).getTextContent();
					String name = el.getElementsByTagName("targetName").item(0).getTextContent();
					String rate = el.getElementsByTagName("exchangeRate").item(0).getTextContent();
					if(rate.contains(",")) {
						String[] parts = rate.split(",");
						rate = parts[0] + parts[1];
					}
					listCurrency[q] = currency;
					listNames[q] = name;
					listRates[q] = rate;
					q++;
				}
			}
		}
		
		
		for(int i = 0; i < listCurrency.length; i++){
			
			double rate = Double.parseDouble(listRates[i]);
			this.ca[i+1] = new Currency(listCurrency[i], listNames[i], rate);
		}
		
		for(int i = 0; i < this.ca.length; i++) {
			if(currency_from.equals(ca[i].getCurrency())) {
				this.currency_from = new Currency(ca[i]);
			}else if(currency_to.equals(ca[i].getCurrency())){
				this.currency_to = new Currency(ca[i]);
			}
		}
	}
	
	public double calculateValue(double value_from) {
		if(!this.currency_from.getCurrency().equals("USD")) {
			this.currency_to.setRate(((ca[0].getRate()/this.currency_from.getRate())*this.currency_to.getRate()));
			this.currency_from.setRate(1);
		}
		double calculation = value_from*this.currency_to.getRate();
		return(calculation);
	}
	
	public Currency getCurrencyFrom() {
		return this.currency_from;
	}
	
	public Currency getCurrencyTo() {
		return this.currency_to;
	}
}
