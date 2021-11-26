package com.deepak.magicinet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CustomXmlHandler extends DefaultHandler {

	Boolean currentElement = false;
	String curValue = null;
	private ItemsCD itemsCD;

	public ItemsCD getItemsCD() {
		return itemsCD;
	}

	public void setItemsCD(ItemsCD itemsCD) {
		this.itemsCD = itemsCD;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		if (currentElement) {
			curValue = new String(ch, start, length);
			currentElement = false;
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		currentElement = false;
		if (localName.equalsIgnoreCase("PlaceID")) {
			itemsCD.setTitle(curValue);
		} else if (localName.equalsIgnoreCase("PlaceName")) {
			itemsCD.setArtist(curValue);
		} else if (localName.equalsIgnoreCase("KM")) {
			itemsCD.setCountry(curValue);

		} else if (localName.equalsIgnoreCase("HalfFare")) {
			itemsCD.setHalffare(curValue);

		} else if (localName.equalsIgnoreCase("FullFare")) {
			itemsCD.setFullfare(curValue);

		}

	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		currentElement = true;
		// XML deki root elementtir.
		if (localName.equals("NewDataSet")) {
			itemsCD = new ItemsCD();
		}

	}

}
