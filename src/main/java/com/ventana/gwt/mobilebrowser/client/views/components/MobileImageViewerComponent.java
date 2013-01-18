package com.ventana.gwt.mobilebrowser.client.views.components;

import com.google.gwt.user.client.ui.Widget;
import com.ventana.gwt.mobilebrowser.client.events.Subscriber;

public interface MobileImageViewerComponent {
  void configure(final String protocol, final String host,
      final String port, final String path, final String fileName,
      final int[][] qualityLevels, final int magnification);

  void deRegisterEvents();
  Widget getImageViewer();
  void registerEvents();
  void refresh();
  void whenZoomChangedNotify(final Subscriber<String> subscriber);
}