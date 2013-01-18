package com.ventana.gwt.mobilebrowser.client.testdoubles;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.views.components.MobileImageViewerComponent;


public class FakeImageViewerComponent implements MobileImageViewerComponent {
  @Override
  @SuppressWarnings("unused")
  public void configure(String protocol, String host, String port, String path,
      String fileName, int[][] qualityLevels,
      int magnification) {
  }

  @Override
  public void deRegisterEvents() {
  }

  @Override
  public Widget getImageViewer() {
    return new Label();
  }

  @Override
  public void registerEvents() {
  }

  @Override
  @SuppressWarnings("unused")
  public void whenZoomChangedNotify(Subscriber<String> subscriber) {
  }

  @Override
  public void refresh() {
  }
}