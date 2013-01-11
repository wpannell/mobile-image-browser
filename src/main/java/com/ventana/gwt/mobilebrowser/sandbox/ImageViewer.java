package com.ventana.gwt.mobilebrowser.sandbox;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.ui.ImageViewerUi;

public class ImageViewer implements EntryPoint {
  @Override
  public void onModuleLoad() {
    SimpleLayoutPanel simpleLayoutPanel = new SimpleLayoutPanel();
    RootLayoutPanel.get().add(simpleLayoutPanel);
    final ImageViewerUi imageViewerUi = GWT.<GinFactory> create(GinFactory.class).makeImageViewerUi();
    simpleLayoutPanel.add(imageViewerUi);
    configureImageSourceModelFor(imageViewerUi);
    imageViewerUi.whenZoomChangedNotify(new Subscriber<String>() {
      @Override
      public void notifyWith(String zoomLevel) {
        GWT.log(zoomLevel);
      }
    });
    imageViewerUi.registerEvents();
  }

  private void configureImageSourceModelFor(final ImageViewerUi ui) {
    final String protocol = "http://";
    final String host = "107.22.162.235";
    final String port = ":85";
    final String path = "";
    final String fileName = "17.jp2";
    final int magnification = 20;
    ui.configure(protocol, host, port, path, fileName, buildQualityLevels(),
        magnification);
  }

  private int[][] buildQualityLevels() {
    return new int[][] {
        {56976, 19088},
        {28488, 9544},
        {14244, 4772},
        {7122, 2386},
        {3561, 1193},
        {1781, 597},
        {891, 299},
        {446, 150},
        {223, 75}};
  }
}
