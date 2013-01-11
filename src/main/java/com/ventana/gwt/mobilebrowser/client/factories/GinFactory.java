package com.ventana.gwt.mobilebrowser.client.factories;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import com.ventana.gwt.mobilebrowser.client.MobileBrowser;
import com.ventana.gwt.mobilebrowser.client.factories.modules.ExternalModule;
import com.ventana.gwt.mobilebrowser.client.factories.modules.GinModule;
import com.ventana.gwt.mobilebrowser.server.repositories.Repository;
@GinModules({
  GinModule.class,
  ExternalModule.class,
  })

public interface GinFactory extends Ginjector {
  MobileBrowser makeApplication();
  Repository makeRepository();
}
