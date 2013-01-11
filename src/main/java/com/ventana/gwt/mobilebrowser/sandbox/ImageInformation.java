package com.ventana.gwt.mobilebrowser.sandbox;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import com.ventana.gwt.mobilebrowser.client.widgets.PopUpImageInformationWidget;

public class ImageInformation implements EntryPoint {
  @Override
  public void onModuleLoad() {
    PopUpImageInformationWidget popup = new PopUpImageInformationWidget();
    RootPanel.get().add(popup);
    popup.open();
  }
}
