package com.ventana.gwt.mobilebrowser.client.factories.modules;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;

import com.ventana.gwt.mobile_viewer.client.components.MobileViewerPresenterProvider;
import com.ventana.gwt.mobilebrowser.client.views.components.MobileImageViewerComponent;
import com.ventana.gwt.mobilebrowser.external.ImageViewerComponent;
import com.ventana.gwt.mobilebrowser.server.repositories.Repository;
import com.ventana.gwt.mobilebrowser.server.repositories.localstorage.LocalStorageRepository;

public class ExternalModule extends AbstractGinModule {
  @Override
  protected void configure() {
    bind(Repository.class).to(LocalStorageRepository.class);
  }

  @Provides
  protected MobileImageViewerComponent provideMobileImagePresenter() {
    return new ImageViewerComponent(new MobileViewerPresenterProvider().get());
  }
}
