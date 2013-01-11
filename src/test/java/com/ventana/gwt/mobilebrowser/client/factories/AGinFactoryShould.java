package com.ventana.gwt.mobilebrowser.client.factories;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;

import com.ventana.gwt.mobilebrowser.client.activities.ContentActivityManager;
import com.ventana.gwt.mobilebrowser.client.activities.DirectoryActivity;
import com.ventana.gwt.mobilebrowser.client.activities.ExplorerActivity;
import com.ventana.gwt.mobilebrowser.client.activities.HostPageActivity;
import com.ventana.gwt.mobilebrowser.client.activities.HostPageActivityManager;
import com.ventana.gwt.mobilebrowser.client.activities.ImageActivity;
import com.ventana.gwt.mobilebrowser.client.containers.ContentContainer;
import com.ventana.gwt.mobilebrowser.client.containers.HostPageContainer;
import com.ventana.gwt.mobilebrowser.client.factories.activities.DirectoryActivityFactory;
import com.ventana.gwt.mobilebrowser.client.factories.activities.ExplorerActivityFactory;
import com.ventana.gwt.mobilebrowser.client.factories.activities.ImageActivityFactory;
import com.ventana.gwt.mobilebrowser.client.history.maps.activities.ExplorerActivityMap;
import com.ventana.gwt.mobilebrowser.client.history.maps.activities.HostPageActivityMap;
import com.ventana.gwt.mobilebrowser.client.history.maps.places.PlaceHistoryMap;
import com.ventana.gwt.mobilebrowser.client.history.placecontrollers.MobileBrowserPlaceController;
import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.Image;
import com.ventana.gwt.mobilebrowser.client.places.RootFolder;
import com.ventana.gwt.mobilebrowser.client.presenters.ImageInformationPresenter;
import com.ventana.gwt.mobilebrowser.client.presenters.PopUpDeletePresenter;
import com.ventana.gwt.mobilebrowser.client.presenters.PopUpEditPresenter;
import com.ventana.gwt.mobilebrowser.client.testdoubles.FakeGinFactory;
import com.ventana.gwt.mobilebrowser.client.testdoubles.FakeImageViewerComponent;
import com.ventana.gwt.mobilebrowser.client.testdoubles.GinFactorySpy;
import com.ventana.gwt.mobilebrowser.client.ui.ExplorerUi;
import com.ventana.gwt.mobilebrowser.client.ui.ImageViewerUi;
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
import com.ventana.gwt.mobilebrowser.server.registry.ClientSideServiceDispatcher;
import com.ventana.gwt.mobilebrowser.server.repositories.Repository;
import com.ventana.gwt.mobilebrowser.server.repositories.localstorage.FakeLocalStorage;

public class AGinFactoryShould extends GWTTestCase {
  private FakeGinFactory ginFactory;
  private GinFactorySpy factorySpy;
  private Directory directory;
  private RootFolder rootFolder;
  private Image imagePlace;
  private HistoryHandlerFactory historyHandlerFactory;
  private DirectoryActivityFactory directoryActivityFactory;
  private ExplorerActivityFactory explorerActivityFactory;
  private ImageActivityFactory imageActivityFactory;

  @Override
  protected void gwtSetUp() {
    ginFactory = GWT.<FakeGinFactory> create(FakeGinFactory.class);
    factorySpy = ginFactory.makeSpy();

    directory = Directory.EMPTY;
    rootFolder = new RootFolder("127.0.0.1");
    imagePlace = new Image("myNAME$127.128.129.130/SUBDIR/myNAME");

    historyHandlerFactory = factorySpy.historyHandlerFactory;
    directoryActivityFactory = factorySpy.directoryActivityFactory;
    explorerActivityFactory = factorySpy.explorerActivityFactory;
    imageActivityFactory = factorySpy.imageActivityFactory;
  }

  public void testConfigureSingleEventBus() throws Exception {
    final EventBus eventBus = factorySpy.eventBus;
    assertNotNull(eventBus);
    assertTrue(eventBus instanceof SimpleEventBus);
    assertEquals(eventBus, ginFactory.makeEventBus());
  }

  public void testConfigureRepository() throws Exception {
    final Repository repository = factorySpy.repository;
    assertNotNull(repository);
    assertTrue(repository instanceof FakeLocalStorage);
  }

  public void testConfigurePlaceController() throws Exception {
    final PlaceController placeController = factorySpy.placeController;
    assertNotNull(placeController);
    assertTrue(placeController instanceof MobileBrowserPlaceController);
  }

  public void testConfigureHostPageActivityMap() throws Exception {
    final ActivityMapper activityMap = factorySpy.hostPageActivityMap;
    assertNotNull(activityMap);
    assertTrue(activityMap instanceof HostPageActivityMap);
  }

  public void testConfigureExplorerActivityMap() throws Exception {
    final ActivityMapper activityMap = factorySpy.explorerActivityMap;
    assertNotNull(activityMap);
    assertTrue(activityMap instanceof ExplorerActivityMap);
  }

  public void testConfigureActivityFactory() throws Exception {
    final ActivityFactory activityFactory = factorySpy.activityFactory;
    assertNotNull(activityFactory);
    assertTrue(activityFactory instanceof ContentActivityFactory);
  }

  public void testConfigureHostPageActivityManager() throws Exception {
    final ActivityManager activityManager = factorySpy.hostPageActivityManager;
    assertNotNull(activityManager);
    assertTrue(activityManager instanceof HostPageActivityManager);
  }

  public void testConfigureContentActivityManager() throws Exception {
    final ActivityManager activityManager = factorySpy.contentActivityManager;
    assertNotNull(activityManager);
    assertTrue(activityManager instanceof ContentActivityManager);
  }

  public void testConfigurePlaceHistoryMapper() throws Exception {
    final PlaceHistoryMapper placeHistoryMapper = factorySpy.placeHistoryMapper;
    assertNotNull(placeHistoryMapper);
    assertTrue(placeHistoryMapper instanceof PlaceHistoryMap);
  }

  public void testConfigurePlaceHistoryHandler() throws Exception {
    final PlaceHistoryHandler placeHistoryHandler =
        historyHandlerFactory.createWith(rootFolder);
    assertNotNull(placeHistoryHandler);
  }

  public void testConfigureDirectoryActivity() throws Exception {
    final DirectoryActivity activity =
        directoryActivityFactory.createActivityFor(directory);
    assertNotNull(activity);
  }

  public void testConfigureExplorerActivity() throws Exception {
    final ExplorerActivity activity =
        explorerActivityFactory.createActivityFor(rootFolder);
    assertNotNull(activity);
  }

  public void testConfigureImageActivity() throws Exception {
    final ImageActivity activity =
        imageActivityFactory.createActivityFor(imagePlace);
    assertNotNull(activity);
  }

  public void testConfigurePopUpEditPresenter() throws Exception {
    final PopUpEditPresenter presenter = factorySpy.popUpEditPresenter;
    assertNotNull(presenter);
    assertNotSame(ginFactory.makePopUpEditPresenter(), presenter);
  }

  public void testConfigurePopUpDeletePresenter() throws Exception {
    final PopUpDeletePresenter presenter = factorySpy.popUpDeletePresenter;
    assertNotNull(presenter);
    assertNotSame(ginFactory.makePopUpDeletePresenter(), presenter);
  }

  public void testConfigureImageInformationPresenter() throws Exception {
    final ImageInformationPresenter presenter =
        factorySpy.imageInformationPresenter;
    assertNotNull(presenter);
    assertNotSame(ginFactory.makeImageInformationPresenter(), presenter);
  }

  public void testConfigureHostPageActivity() throws Exception {
    final HostPageActivity activity = factorySpy.hostPageActivity;
    assertNotNull(activity);
  }

  public void testConfigureHostPageContainer() throws Exception {
    final HostPageContainer container = factorySpy.hostPageContainer;
    assertNotNull(container);
    assertEquals(container, ginFactory.makeHostPageContainer());
  }

  public void testConfigureContentContainer() throws Exception {
    final ContentContainer container = factorySpy.contentContainer;
    assertNotNull(container);
    assertEquals(container, ginFactory.makeContentContainer());
  }

  public void testConfigureToolBarWidget() throws Exception {
    final ToolBarWidget toolBarWidget = factorySpy.toolBarWidget;
    assertNotNull(toolBarWidget);
    assertEquals(toolBarWidget, ginFactory.makeToolBarWidget());
  }

  public void testConfigurePopUpConfirmationWidget() throws Exception {
    final PopUpConfirmationWidget popUpConfirmationWidget =
        factorySpy.popUpConfirmationWidget;
    assertNotNull(popUpConfirmationWidget);
    assertEquals(
        popUpConfirmationWidget, ginFactory.makePopUpConfirmationWidget());
  }

  public void testConfigurePopUpEditWidget() throws Exception {
    final PopUpEditWidget popUpEditWidget = factorySpy.popUpEditWidget;
    assertNotNull(popUpEditWidget);
    assertEquals(popUpEditWidget, ginFactory.makePopUpEditWidget());
  }

  public void testConfigureHostPageView() throws Exception {
    final HostPageView hostPageView = factorySpy.hostPageView;
    assertNotNull(hostPageView);
    assertEquals(hostPageView, ginFactory.makeHostPageView());
  }

  public void testConfigureToolBarView() throws Exception {
    final ToolBarView view = factorySpy.toolBarView;
    assertNotNull(view);
    assertTrue(view instanceof ToolBarWidget);
    assertEquals(view, ginFactory.makeToolBarView());
  }

  public void testConfigureExplorerView() throws Exception {
    final ExplorerView view = factorySpy.explorerView;
    assertNotNull(view);
    assertTrue(view instanceof ExplorerUi);
    assertEquals(view, ginFactory.makeExplorerView());
  }

  public void testConfigureImageView() throws Exception {
    final ImageView view = factorySpy.imageView;
    assertNotNull(view);
    assertTrue(view instanceof ImageViewerUi);
    assertNotSame(view, ginFactory.makeImageView());
  }

  public void testConfigurePopUpConfirmationView() throws Exception {
    final PopUpConfirmationView popUpConfirmationView =
        factorySpy.popUpConfirmationView;
    assertNotNull(popUpConfirmationView);
    assertEquals(popUpConfirmationView, ginFactory.makePopUpConfirmationView());
  }

  public void testConfigurePopUpEditView() throws Exception {
    final PopUpEditView popUpEditView = factorySpy.popUpEditView;
    assertNotNull(popUpEditView);
    assertEquals(popUpEditView, ginFactory.makePopUpEditView());
  }

  public void testConfigurePopUpDeleteView() throws Exception {
    final PopUpDeleteView popUpDeleteView = factorySpy.popUpDeleteView;
    assertNotNull(popUpDeleteView);
    assertEquals(popUpDeleteView, ginFactory.makePopUpDeleteView());
  }

  public void testConfigureImageInformationView() throws Exception {
    final ImageInformationView imageInformationPopup =
        factorySpy.imageInformationView;
    assertNotNull(imageInformationPopup);
    assertEquals(imageInformationPopup, ginFactory.makeImageInformationView());
  }

  public void testConfigureMobileImageViewerComponent() throws Exception {
    final MobileImageViewerComponent mobileImageViewerComponent =
        factorySpy.mobileImageViewerComponent;
    assertNotNull(mobileImageViewerComponent);
    assertTrue(mobileImageViewerComponent instanceof FakeImageViewerComponent);
  }

  public void testConfigureServiceDispatcher() throws Exception {
    final AsyncDispatcher dispatcher = factorySpy.clientSideServiceDispatcher;
    assertNotNull(dispatcher);
    assertTrue(dispatcher instanceof ClientSideServiceDispatcher);
    assertEquals(dispatcher, ginFactory.makeDispatcher());
  }

  @Override
  public String getModuleName() {
    return "Integration";
  }
}
