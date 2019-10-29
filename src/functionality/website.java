package functionality;

import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class website {

	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private URL url;
	private InputStream stream;
	private InputSource is;
	private Element element;
	private NodeList nodes;
	private Document doc;
	
	public website() {
		this.factory = DocumentBuilderFactory.newInstance();
		try {
			this.builder = factory.newDocumentBuilder();
			this.url = new URL("http://www.floatrates.com/daily/usd.xml");
			this.stream = url.openStream();
			this.is = new InputSource(stream);
	        this.doc = builder.parse(is);
	        this.element = doc.getDocumentElement();
	        this.nodes = element.getChildNodes();
		} catch (Exception ex) {
	         ex.printStackTrace();
	    }
	}
	public NodeList getNodes() {
		return this.nodes;
	}
	public String toString(){
		String s = "";
		for(int i = 0; i < this.nodes.getLength(); i++) {
			if (this.nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element el = (Element) this.nodes.item(i);
				if (el.getNodeName().contains("item")) {
					String main = el.getElementsByTagName("baseCurrency").item(0).getTextContent();
					String to = el.getElementsByTagName("targetCurrency").item(0).getTextContent();
					String rateFrom = el.getElementsByTagName("exchangeRate").item(0).getTextContent();
					String rateTo = el.getElementsByTagName("inverseRate").item(0).getTextContent();
					String desc = el.getElementsByTagName("inverseDescription").item(0).getTextContent();
					s += main + " -> "+ to + " : " + rateFrom + ". Vice-versa: " + rateTo + ". Overall description: " + desc + "\n";
				}
			}
		}
		return s;
	}
}