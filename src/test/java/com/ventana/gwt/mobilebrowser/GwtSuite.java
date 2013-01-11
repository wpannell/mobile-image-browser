package com.ventana.gwt.mobilebrowser;

import com.google.gwt.junit.tools.GWTTestSuite;

import com.ventana.gwt.mobilebrowser.client.AnExplorerPlaceBuilderShould;
import com.ventana.gwt.mobilebrowser.client.factories.AGinFactoryShould;
import com.ventana.gwt.mobilebrowser.client.history.ANewlyCreatedHostPageActivityShould;
import com.ventana.gwt.mobilebrowser.client.history.ANewlyCreatedPlaceMapShould;
import com.ventana.gwt.mobilebrowser.client.xml.ANewlyCreatedExplorerXmlShould;
import com.ventana.gwt.mobilebrowser.client.xml.ANewlyCreatedIScanXmlShould;

import junit.framework.Test;
import junit.framework.TestSuite;

public class GwtSuite extends TestSuite {
  public static final String TEST_MODULE_NAME = "Integration";
  public static Test suite() {
    final TestSuite suite = new GWTTestSuite("GwtTestCases");
    suite.addTestSuite(AGinFactoryShould.class);
    suite.addTestSuite(ANewlyCreatedExplorerXmlShould.class);
    suite.addTestSuite(ANewlyCreatedHostPageActivityShould.class);
    suite.addTestSuite(ANewlyCreatedIScanXmlShould.class);
    suite.addTestSuite(ANewlyCreatedPlaceMapShould.class);
    suite.addTestSuite(AnExplorerPlaceBuilderShould.class);
    return suite;
  }
}
