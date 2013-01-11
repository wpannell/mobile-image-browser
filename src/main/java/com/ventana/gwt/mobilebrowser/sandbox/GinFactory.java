package com.ventana.gwt.mobilebrowser.sandbox;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import com.ventana.gwt.mobilebrowser.client.factories.modules.ExternalModule;
import com.ventana.gwt.mobilebrowser.client.factories.modules.GinModule;
import com.ventana.gwt.mobilebrowser.client.ui.ImageViewerUi;
@GinModules({
  GinModule.class,
  ExternalModule.class,
  })

public interface GinFactory extends Ginjector {
  ImageViewerUi makeImageViewerUi();
}
