package com.ventana.gwt.mobilebrowser.client.testdoubles;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import com.ventana.gwt.mobilebrowser.client.containers.ContentContainer;
import com.ventana.gwt.mobilebrowser.client.containers.HostPageContainer;
import com.ventana.gwt.mobilebrowser.client.factories.modules.GinModule;
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
import com.ventana.gwt.mobilebrowser.client.widgets.PopUpConfirmationWidget;
import com.ventana.gwt.mobilebrowser.client.widgets.PopUpEditWidget;
import com.ventana.gwt.mobilebrowser.client.widgets.ToolBarWidget;
import com.ventana.gwt.mobilebrowser.server.dispatcher.AsyncDispatcher;

@GinModules({
  GinModule.class,
  FakeUntestableModule.class,
  })

public interface FakeGinFactory extends Ginjector {
  GinFactorySpy makeSpy();
  EventBus makeEventBus();

  HostPageContainer makeHostPageContainer();
  ContentContainer makeContentContainer();

  ToolBarWidget makeToolBarWidget();
  PopUpConfirmationWidget makePopUpConfirmationWidget();
  PopUpEditWidget makePopUpEditWidget();

  HostPageView makeHostPageView();
  ToolBarView makeToolBarView();
  ExplorerView makeExplorerView();
  PopUpConfirmationView makePopUpConfirmationView();
  PopUpEditView makePopUpEditView();
  PopUpDeleteView makePopUpDeleteView();
  AsyncDispatcher makeDispatcher();
  ImageView makeImageView();
  ImageInformationView makeImageInformationView();
  PopUpEditPresenter makePopUpEditPresenter();
  PopUpDeletePresenter makePopUpDeletePresenter();
  ImageInformationPresenter makeImageInformationPresenter();
}
