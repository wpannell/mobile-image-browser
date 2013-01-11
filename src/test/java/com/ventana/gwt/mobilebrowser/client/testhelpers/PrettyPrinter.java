package com.ventana.gwt.mobilebrowser.client.testhelpers;

import static junit.framework.Assert.assertEquals;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.StringReader;
import java.util.StringTokenizer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class PrettyPrinter extends DefaultHandler {
  private boolean charFlags = false;
  private int count;
  private boolean firstItem = false;
  private String result;
  private boolean startElement;

  public PrettyPrinter() {}

  public void adjustWithPreviousText() {
    if (charFlags == true) {
      charFlags = false;
      result += "\n";
    }
  }

  public void assertXMLEquals(final String expected, final String result) {
    assertEquals(format(expected), format(result));
  }

  @Override
  public void characters(final char[] buf, final int start, final int length) {
    String resultData = new String(buf, start, length);
    if (firstItem) {
      insertTabs();
      charFlags = true;
      firstItem = false;
    }
    resultData = resultData.replaceAll("&", "&amp;");
    result += resultData;
  }

  @Override
  public void endElement(@SuppressWarnings("unused") final String uri,
      final String localName,
      @SuppressWarnings("unused") final String qName) {
    adjustWithPreviousText();
    if (!startElement) {
      count--;
    }

    insertTabs();
    startElement = false;
    result += "</" + localName + ">\n";
  }

  public String format(String inputXML) {
    result = "\n";
    count = 0;
    startElement = false;
    inputXML = stripTabs(inputXML).replaceAll("& ", "&amp; ");

    final SAXParserFactory spf = SAXParserFactory.newInstance();
    try {
      final SAXParser parser = spf.newSAXParser();
      final InputSource source = new InputSource(new StringReader(inputXML));
      parser.parse(source, this);
    } catch (final Exception ignore) {}
    return result;
  }

  @Override
  public void startElement(@SuppressWarnings("unused") final String uri,
      final String localName,
      @SuppressWarnings("unused") final String qName,
      final Attributes attributes) throws SAXException {
    firstItem = true;
    adjustWithPreviousText();
    if (startElement) {
      count++;
    }
    insertTabs();
    result += "<" + localName;
    int len;
    if ((len = attributes.getLength()) > 0) {
      result += " ";
      for (int i = 0; i < len; i++) {
        String value = attributes.getValue(i);
        value = value.replaceAll("& ", "&amp; ");
        result += attributes.getLocalName(i) + "='" + value + "'";
        if (i < len - 1) {
          result += " ";
        }
      }
    }
    startElement = true;
    result += ">\n";
  }

  private void insertTabs() {
    for (int x = 0; x < count; x++) {
      result += "\t";
    }
  }

  private String stripTabs(final String rawText) {
    final StringTokenizer tok = new StringTokenizer(rawText, "\t");
    final StringBuffer result = new StringBuffer();
    while (tok.hasMoreElements()) {
      result.append(tok.nextToken());
    }
    return result.toString();
  }
}
