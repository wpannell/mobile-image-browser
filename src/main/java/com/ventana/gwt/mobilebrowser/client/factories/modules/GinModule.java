package com.ventana.gwt.mobilebrowser.client.factories.modules;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import com.ventana.gwt.mobilebrowser.client.activities.ContentActivityManager;
import com.ventana.gwt.mobilebrowser.client.activities.DirectoryActivity;
import com.ventana.gwt.mobilebrowser.client.activities.ExplorerActivity;
import com.ventana.gwt.mobilebrowser.client.activities.HostPageActivityManager;
import com.ventana.gwt.mobilebrowser.client.activities.ImageActivity;
import com.ventana.gwt.mobilebrowser.client.containers.ContentContainer;
import com.ventana.gwt.mobilebrowser.client.containers.HostPageContainer;
import com.ventana.gwt.mobilebrowser.client.factories.ActivityFactory;
import com.ventana.gwt.mobilebrowser.client.factories.ContentActivityFactory;
import com.ventana.gwt.mobilebrowser.client.factories.HistoryHandlerFactory;
import com.ventana.gwt.mobilebrowser.client.factories.activities.DirectoryActivityFactory;
import com.ventana.gwt.mobilebrowser.client.factories.activities.ExplorerActivityFactory;
import com.ventana.gwt.mobilebrowser.client.factories.activities.ImageActivityFactory;
import com.ventana.gwt.mobilebrowser.client.history.HistoryHandler;
import com.ventana.gwt.mobilebrowser.client.history.maps.activities.ExplorerActivityMap;
import com.ventana.gwt.mobilebrowser.client.history.maps.activities.HostPageActivityMap;
import com.ventana.gwt.mobilebrowser.client.history.maps.places.PlaceHistoryMap;
import com.ventana.gwt.mobilebrowser.client.history.placecontrollers.MobileBrowserPlaceController;
import com.ventana.gwt.mobilebrowser.client.ui.ExplorerUi;
import com.ventana.gwt.mobilebrowser.client.ui.HostPageUi;
import com.ventana.gwt.mobilebrowser.client.ui.ImageViewerUi;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.client.views.HostPageView;
import com.ventana.gwt.mobilebrowser.client.views.ImageInformationView;
import com.ventana.gwt.mobilebrowser.client.views.ImageView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpConfirmationView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpDeleteView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpEditView;
import com.ventana.gwt.mobilebrowser.client.views.ToolBarView;
import com.ventana.gwt.mobilebrowser.client.views.TouchDelegator;
import com.ventana.gwt.mobilebrowser.client.widgets.PopUpConfirmationWidget;
import com.ventana.gwt.mobilebrowser.client.widgets.PopUpDeleteWidget;
import com.ventana.gwt.mobilebrowser.client.widgets.PopUpEditWidget;
import com.ventana.gwt.mobilebrowser.client.widgets.PopUpImageInformationWidget;
import com.ventana.gwt.mobilebrowser.client.widgets.ToolBarWidget;
import com.ventana.gwt.mobilebrowser.client.widgets.WidgetTouchDelegator;
import com.ventana.gwt.mobilebrowser.server.dispatcher.AsyncDispatcher;
import com.ventana.gwt.mobilebrowser.server.registry.ClientSideServiceDispatcher;
import com.ventana.gwt.mobilebrowser.server.services.FetchImagesService;

public class GinModule extends AbstractGinModule {
  @Override
  protected void configure() {
    bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);

    bind(PlaceController.class).
        to(MobileBrowserPlaceController.class).in(Singleton.class);

    bind(ActivityFactory.class).to(ContentActivityFactory.class);

    bind(ActivityMapper.class).to(HostPageActivityMap.class);
    bind(ActivityManager.class).to(HostPageActivityManager.class);

    bind(ActivityMapper.class).annotatedWith(Names.named(ContentContainer.NAME)).
        to(ExplorerActivityMap.class);

    bind(ActivityManager.class).annotatedWith(Names.named(ContentContainer.NAME)).
        to(ContentActivityManager.class);

    bind(PlaceHistoryMapper.class).to(PlaceHistoryMap.class);

    this.install(new GinFactoryModuleBuilder().implement(
        PlaceHistoryHandler.class, HistoryHandler.class).build(
            HistoryHandlerFactory.class));

    install(new GinFactoryModuleBuilder().implement(
        DirectoryActivity.class, DirectoryActivity.class).build(
            DirectoryActivityFactory.class));

    install(new GinFactoryModuleBuilder().implement(
        ExplorerActivity.class, ExplorerActivity.class).build(
            ExplorerActivityFactory.class));

    install(new GinFactoryModuleBuilder().implement(
        ImageActivity.class, ImageActivity.class).build(
            ImageActivityFactory.class));

    bind(HostPageContainer.class).in(Singleton.class);
    bind(ContentContainer.class).in(Singleton.class);

    bind(ToolBarWidget.class).in(Singleton.class);
    bind(PopUpConfirmationWidget.class).in(Singleton.class);
    bind(PopUpEditWidget.class).in(Singleton.class);
    bind(PopUpDeleteWidget.class).in(Singleton.class);
    bind(PopUpImageInformationWidget.class).in(Singleton.class);

    bind(HostPageView.class).to(HostPageUi.class).in(Singleton.class);
    bind(ToolBarView.class).to(ToolBarWidget.class).in(Singleton.class);
    bind(ExplorerView.class).to(ExplorerUi.class).in(Singleton.class);
    bind(ImageView.class).to(ImageViewerUi.class);

    bind(PopUpConfirmationView.class).
        to(PopUpConfirmationWidget.class).in(Singleton.class);

    bind(PopUpEditView.class).
        to(PopUpEditWidget.class).in(Singleton.class);

    bind(PopUpDeleteView.class).
        to(PopUpDeleteWidget.class).in(Singleton.class);

    bind(ImageInformationView.class).
        to(PopUpImageInformationWidget.class).in(Singleton.class);

    bind(TouchDelegator.class).
        to(WidgetTouchDelegator.class).in(Singleton.class);
  }

  @Provides
  @Singleton
  protected AsyncDispatcher provideClientSideAsyncDispatcher() {
    final ClientSideServiceDispatcher registry =
        new ClientSideServiceDispatcher();
    registry.register(new FetchImagesService());
    return registry;
  }
}
