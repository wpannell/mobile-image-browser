package com.ventana.gwt.mobilebrowser.client.testdoubles;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;

import com.ventana.gwt.mobilebrowser.client.views.components.MobileImageViewerComponent;
import com.ventana.gwt.mobilebrowser.server.repositories.Repository;
import com.ventana.gwt.mobilebrowser.server.repositories.localstorage.FakeLocalStorage;

public class FakeUntestableModule extends AbstractGinModule {
  @Override
  protected void configure() {
    bind(Repository.class).to(FakeLocalStorage.class);
  }

  @Provides
  protected MobileImageViewerComponent provideMobileImagePresenter() {
    return new FakeImageViewerComponent();
  }
}
