package com.ventana.gwt.mobilebrowser.client.views;

import com.google.gwt.user.client.ui.AcceptsOneWidget;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;

public interface ImageView {
  void addMeToThis(final AcceptsOneWidget container);

  void configure(final String protocol, final String host, final String port,
      final String path, final String fileName,
      final int[][] qualityLevels, final int magnification);

  void deRegisterEvents();
  void refresh();
  void registerEvents();
  void whenTappedNotify(final Subscriber<Void> subscriber);
  void whenZoomChangedNotify(final Subscriber<String> imageZoomSubscriber);

}
