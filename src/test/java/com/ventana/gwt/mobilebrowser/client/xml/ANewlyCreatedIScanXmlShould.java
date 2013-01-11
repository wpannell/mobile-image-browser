package com.ventana.gwt.mobilebrowser.client.xml;

import com.google.gwt.junit.client.GWTTestCase;

import com.ventana.gwt.mobilebrowser.client.testhelpers.TestData;

public class ANewlyCreatedIScanXmlShould extends GWTTestCase {
  private IScanXml iscan;

  @Override
  protected void gwtSetUp() {
    iscan = IScanXml.createWith(TestData.ISCAN);
  }

  public void testKnowBuildDate() {
    assertEquals("June, 17 2010", iscan.getBuildDate());
  }

  public void testKnowBuildVersion() {
    assertEquals("3.1.1.1", iscan.getBuildVersion());
  }

  public void testKnowFocusMode() {
    assertEquals("0", iscan.getFocusMode());
  }

  public void testKnowFocusQuality() {
    assertEquals("1", iscan.getFocusQuality());
  }

  public void testKnowLabelBoundary() {
    assertEquals("1000", iscan.getLabelBoundary());
  }

  public void testKnowMagnification() {
    assertEquals("20", iscan.getMagnification());
  }

  public void testKnowScanMode() {
    assertEquals("1", iscan.getScanMode());
  }

  public void testKnowScanRes() {
    assertEquals("0.46500", iscan.getScanRes());
  }

  public void testKnowShowLabel() {
    assertEquals("1", iscan.getShowLabel());
  }

  public void testKnowSlideAnnotation() {
    assertEquals("", iscan.getSlideAnnotation());
  }

  public void testKnowUnitNumber() {
    assertEquals("BI09N0242", iscan.getUnitNumber());
  }

  public void testKnowUserName() {
    assertEquals("admin", iscan.getUserName());
  }

  public void testKnowZlayers() {
    assertEquals("1", iscan.getZlayers());
  }

  public void testKnowZspacing() {
    assertEquals("1", iscan.getZspacing());
  }

  public void testKnowDimensions() {
    assertEquals("1030 X 330", iscan.getDimensions());
  }

  @Override
  public String getModuleName() {
    return "Integration";
  }
}
