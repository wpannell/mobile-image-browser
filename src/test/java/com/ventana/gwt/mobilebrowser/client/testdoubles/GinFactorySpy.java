package com.ventana.gwt.mobilebrowser.client.testdoubles;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import com.ventana.gwt.mobilebrowser.client.activities.HostPageActivity;
import com.ventana.gwt.mobilebrowser.client.containers.ContentContainer;
import com.ventana.gwt.mobilebrowser.client.containers.HostPageContainer;
import com.ventana.gwt.mobilebrowser.client.factories.ActivityFactory;
import com.ventana.gwt.mobilebrowser.client.factories.HistoryHandlerFactory;
import com.ventana.gwt.mobilebrowser.client.factories.activities.DirectoryActivityFactory;
import com.ventana.gwt.mobilebrowser.client.factories.activities.ExplorerActivityFactory;
import com.ventana.gwt.mobilebrowser.client.factories.activities.ImageActivityFactory;
import com.ventana.gwt.mobilebrowser.client.presenters.ImageInformationPresenter;
import com.ventana.gwt.mobilebrowser.client.presenters.PopUpDeletePresenter;
import com.ventana.gwt.mobilebrowser.client.presenters.PopUpEditPresenter;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.client.views.HostPageView;
import com.ventana.gwt.mobilebrowser.client.views.ImageInformationView;
import com.ventana.gwt.mobilebrowser.client.views.ImageView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpConfirmationView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpDeleteView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpEditView;
import com.ventana.gwt.mobilebrowser.client.views.ToolBarView;
import com.ventana.gwt.mobilebrowser.client.views.components.MobileImageViewerComponent;
import com.ventana.gwt.mobilebrowser.client.widgets.PopUpConfirmationWidget;
import com.ventana.gwt.mobilebrowser.client.widgets.PopUpEditWidget;
import com.ventana.gwt.mobilebrowser.client.widgets.ToolBarWidget;
import com.ventana.gwt.mobilebrowser.server.dispatcher.AsyncDispatcher;
import com.ventana.gwt.mobilebrowser.server.repositories.Repository;

public class GinFactorySpy {
  @Inject public EventBus eventBus;
  @Inject public Repository repository;
  @Inject public PlaceController placeController;

  @Inject public ActivityMapper hostPageActivityMap;
  @Inject @Named(ContentContainer.NAME) public ActivityMapper explorerActivityMap;

  @Inject public ActivityFactory activityFactory;

  @Inject public ActivityManager hostPageActivityManager;
  @Inject @Named(ContentContainer.NAME) public ActivityManager contentActivityManager;

  @Inject public PlaceHistoryMapper placeHistoryMapper;

  @Inject public HistoryHandlerFactory historyHandlerFactory;
  @Inject public DirectoryActivityFactory directoryActivityFactory;
  @Inject public ExplorerActivityFactory explorerActivityFactory;
  @Inject public ImageActivityFactory imageActivityFactory;
  @Inject public PopUpEditPresenter popUpEditPresenter;
  @Inject public PopUpDeletePresenter popUpDeletePresenter;
  @Inject public ImageInformationPresenter imageInformationPresenter;

  @Inject public HostPageActivity hostPageActivity;
  @Inject public HostPageContainer hostPageContainer;
  @Inject public ContentContainer contentContainer;
  @Inject public ToolBarWidget toolBarWidget;
  @Inject public PopUpConfirmationWidget popUpConfirmationWidget;
  @Inject public PopUpEditWidget popUpEditWidget;
  @Inject public HostPageView hostPageView;
  @Inject public ToolBarView toolBarView;
  @Inject public ExplorerView explorerView;
  @Inject public ImageView imageView;
  @Inject public PopUpConfirmationView popUpConfirmationView;
  @Inject public PopUpEditView popUpEditView;
  @Inject public PopUpDeleteView popUpDeleteView;
  @Inject public ImageInformationView imageInformationView;
  @Inject public AsyncDispatcher clientSideServiceDispatcher;
  @Inject public MobileImageViewerComponent mobileImageViewerComponent;
}
