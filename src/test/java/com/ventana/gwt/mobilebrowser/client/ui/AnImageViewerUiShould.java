package com.ventana.gwt.mobilebrowser.client.ui;

import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.gwtplatform.tester.MockingBinder;
import com.ventana.gwt.mobilebrowser.client.containers.EventContainer;
import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.testdoubles.MockitoMockFactory;
import com.ventana.gwt.mobilebrowser.client.ui.ImageViewerUi.ImageViewerUiBinder;
import com.ventana.gwt.mobilebrowser.client.views.ImageView;
import com.ventana.gwt.mobilebrowser.client.views.components.MobileImageViewerComponent;

import static org.mockito.Mockito.verify;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JukitoRunner.class)
public class AnImageViewerUiShould {
  private ImageViewerUi ui;
  private ImageView view;

  @Before
  @SuppressWarnings("unused") //fake uibinder not used outside of di...
  public void createFixtureWith(ImageViewerUiBinder fakeImageViewerUiBinder,
      ImageViewerUi imageViewerUi) {
    this.ui = imageViewerUi;
    view = imageViewerUi;
  }

  @Test
  public void addMeToContainerPassedInByActivityManager(
      AcceptsOneWidget containerPassedByActivityManager) {
    view.addMeToThis(containerPassedByActivityManager);
    verify(containerPassedByActivityManager).setWidget(ui);
  }

  @Test
  public void configureViewer() {
    int[][] qualityLevels = new int[][]{{1,2}, {3,4}};
    view.configure("http", "127.0.0.0", ":85", "/PATH/SUBPATH", "IMAGE.TIFF",
        qualityLevels, 20);
    verify(ui.imageComponent).configure("http", "127.0.0.0", ":85",
        "/PATH/SUBPATH", "IMAGE.TIFF", qualityLevels, 20);
  }

  @Test
  public void deRegisterEvents() {
    view.deRegisterEvents();
    verify(ui.imageComponent).deRegisterEvents();
  }

  @Test
  public void registerEvents() {
    view.registerEvents();
    verify(ui.imageComponent).registerEvents();
  }

  @Test
  public void notifyWhenContainerTapped(Subscriber<Void> subscriber) {
    view.whenTappedNotify(subscriber);
    verify(ui.eventContainer).whenTappedNotify(ui, subscriber);
  }

  @Test
  public void notifyWhenZoomChanged(Subscriber<String>  subscriber) {
    view.whenZoomChangedNotify(subscriber);
    verify(ui.imageComponent).whenZoomChangedNotify(subscriber);
  }

  @AfterClass
  public static void cleanup() {
    GWTMockUtilities.restore();
  }

  public static class Module extends JukitoModule {
    public static class FakeImageViewerUiBinder
        extends MockingBinder<Widget, ImageViewerUi>
        implements ImageViewerUiBinder {
      @Inject
      public FakeImageViewerUiBinder(MockitoMockFactory mockitoMockFactory) {
        super(Widget.class, mockitoMockFactory);
      }
    }

    @Override
    protected void configureTest() {
      GWTMockUtilities.disarm();
      forceMock(MobileImageViewerComponent.class);
      forceMock(EventContainer.class);
      bind(ImageViewerUiBinder.class).to(FakeImageViewerUiBinder.class);
    }
  }
}
