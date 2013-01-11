package com.ventana.gwt.mobilebrowser.client.domain;

public interface ImageInformation {
  ImageInformation EMPTY = new ImageInformation() {
    @Override public String asHtml() { return ""; }
    @Override public String getBuildDate() { return ""; }
    @Override public String getBuildVersion() { return ""; }
    @Override public String getDateTime() { return ""; }
    @Override public String getDimensions() { return "0 X 0"; }
    @Override public String getFilename() { return ""; }
    @Override public String getFocusMode() { return ""; }
    @Override public String getFocusQuality() { return ""; }
    @Override public String getMagnification() { return ""; }
    @Override public String getScanMode() { return ""; }
    @Override public String getScanResolution() { return ""; }
    @Override public String getUnitNumber() { return ""; }
    @Override public String getZlayers() { return ""; }
    @Override public String getZspacing() { return ""; }
  };
  String asHtml();
  String getBuildDate();
  String getBuildVersion();
  String getDateTime();
  String getDimensions();
  String getFilename();
  String getFocusMode();
  String getFocusQuality();
  String getMagnification();
  String getScanMode();
  String getScanResolution();
  String getZlayers();
  String getZspacing();
  String getUnitNumber();
}
