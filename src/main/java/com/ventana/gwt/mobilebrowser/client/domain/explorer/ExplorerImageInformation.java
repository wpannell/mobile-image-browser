package com.ventana.gwt.mobilebrowser.client.domain.explorer;

import com.ventana.gwt.mobilebrowser.client.domain.ImageInformation;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.xml.IScanXml;

public class ExplorerImageInformation implements ImageInformation {
  private final IScanXml iscan;
  private final Explorer place;

  public ExplorerImageInformation(final Explorer place, final IScanXml iscan) {
    this.place = place;
    this.iscan = iscan;
  }

  @Override
  public String asHtml() {
    return place.asHtml();
  }

  @Override
  public String getBuildDate() {
    return "Scanner " + iscan.getBuildDate();
  }

  @Override
  public String getBuildVersion() {
    return "Scanner " + iscan.getBuildVersion();
  }

  @Override
  public String getDateTime() {
    return place.getTime();
  }

  @Override
  public String getDimensions() {
    return iscan.getDimensions() + " px";
  }

  @Override
  public String getFilename() {
    return place.getName();
  }

  @Override
  public String getFocusMode() {
    return iscan.getFocusMode();
  }

  @Override
  public String getFocusQuality() {
    return iscan.getFocusQuality();
  }

  @Override
  public String getMagnification() {
    return iscan.getMagnification();
  }

  @Override
  public String getScanMode() {
    return iscan.getScanMode();
  }

  @Override
  public String getScanResolution() {
    return iscan.getScanRes() + " mpp";
  }

  @Override
  public String getUnitNumber() {
    return iscan.getUnitNumber();
  }

  @Override
  public String getZlayers() {
    return iscan.getZlayers();
  }

  @Override
  public String getZspacing() {
    return iscan.getZspacing();
  }
}
