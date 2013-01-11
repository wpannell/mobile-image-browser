package com.ventana.gwt.mobilebrowser.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import com.ventana.gwt.mobilebrowser.client.factories.GinFactory;
import com.ventana.gwt.mobilebrowser.client.places.Directory;

public class Main implements EntryPoint {
  @Override
  public void onModuleLoad() {
    GWT.<GinFactory>create(GinFactory.class).makeApplication().runWith(
        RootLayoutPanel.get(), new Directory());
  }
}
