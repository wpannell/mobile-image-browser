package com.ventana.gwt.mobilebrowser.client.xml;

import com.google.gwt.core.client.GWT;

import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.totoe.xml.client.XmlParser;

public class IScanXml  {
  interface Reader extends XmlReader<IScanXml> {}
  public static final IScanXml EMPTY = new IScanXml();

  private static final Reader XML = GWT.create(Reader.class);

  public static IScanXml createWith(final String xml) {
    return IScanXml.XML.read(new XmlParser().parse(xml));
  }

  @Path("@BuildDate")
  String buildDate;
  @Path("@BuildVersion")
  String buildVersion;
  @Path("@FocusMode")
  String focusMode;
  @Path("@FocusQuality")
  String focusQuality;
  @Path("@LabelBoundary")
  String labelBoundary;
  @Path("@Magnification")
  String magnification;
  @Path("@ScanMode")
  String scanMode;
  @Path("@ScanRes")
  String scanRes;
  @Path("@ShowLabel")
  String showLabel;
  @Path("@SlideAnnotation")
  String slideAnnotation;
  @Path("@UnitNumber")
  String unitNumber;
  @Path("@UserName")
  String userName;
  @Path("@Z-layers")
  String zlayers;
  @Path("@Z-spacing")
  String zspacing;

  @Path("AOI0")
  Aoi0Xml aoioXml;

  public String getBuildDate() {
    return buildDate;
  }

  public String getBuildVersion() {
    return buildVersion;
  }

  public String getDimensions() {
    return Integer.toString(parse(getBottom()) - parse(getTop())) + " X " +
        Integer.toString(parse(getRight()) - parse(getLeft()));
  }

  public String getFocusMode() {
    return focusMode;
  }

  public String getFocusQuality() {
    return focusQuality;
  }

  public String getLabelBoundary() {
    return labelBoundary;
  }

  public String getMagnification() {
    return magnification;
  }

  public String getScanMode() {
    return scanMode;
  }

  public String getScanRes() {
    return scanRes;
  }

  public String getShowLabel() {
    return showLabel;
  }

  public String getSlideAnnotation() {
    return slideAnnotation;
  }

  public String getUnitNumber() {
    return unitNumber;
  }

  public String getUserName() {
    return userName;
  }

  public String getZlayers() {
    return zlayers;
  }

  public String getZspacing() {
    return zspacing;
  }

  private String getBottom() {
    return aoioXml.getBottom();
  }

  private String getLeft() {
    return aoioXml.getLeft();
  }

  private String getRight() {
    return aoioXml.getRight();
  }

  private String getTop() {
    return aoioXml.getTop();
  }

  private int parse(String intAsString) {
    try {
      return Integer.parseInt(intAsString);
    }
    catch (Exception e) {
      return 0;
    }
  }

}
