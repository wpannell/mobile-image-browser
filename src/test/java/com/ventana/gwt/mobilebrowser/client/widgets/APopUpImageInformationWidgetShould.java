package com.ventana.gwt.mobilebrowser.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.gwtplatform.tester.MockingBinder;
import com.ventana.gwt.mobilebrowser.client.commands.ConfigureImageUrlCommand;
import com.ventana.gwt.mobilebrowser.client.containers.FilesContainer;
import com.ventana.gwt.mobilebrowser.client.domain.ImageInformation;
import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.testdoubles.MockitoMockFactory;
import com.ventana.gwt.mobilebrowser.client.views.ImageInformationView;
import com.ventana.gwt.mobilebrowser.client.widgets.PopUpImageInformationWidget.PopUpImageInformationWidgetUiBinder;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JukitoRunner.class)
public class APopUpImageInformationWidgetShould {
  private PopUpImageInformationWidget popUpImageInformationWidget;
  private ImageInformationView imageInformationView;

  @Before
  @SuppressWarnings("unused") //fake uibinder not used outside of di...
  public void createFixtureWith(
      PopUpImageInformationWidgetUiBinder fakePopUpImageInformationWidgetUiBinder,
      PopUpImageInformationWidget popUpImageInformationWidget) {
    this.popUpImageInformationWidget = popUpImageInformationWidget;
    imageInformationView = popUpImageInformationWidget;
  }

  @Test
  public void load() {
    popUpImageInformationWidget.onLoad();
    verify(popUpImageInformationWidget.container).setSelector(
        ImageInformationView.IMAGE_NAVIGATOR_THUMB_SELECTOR);
  }

  @Test
  public void unload() {
    popUpImageInformationWidget.onUnload();
    verify(popUpImageInformationWidget.container).clear();
  }

  @Test
  public void close() {
    imageInformationView.close();
    verify(popUpImageInformationWidget.container).clear();
    verify(popUpImageInformationWidget.modal).hide();
  }

  @Test
  public void disableNext() {
    imageInformationView.disableNext();
    verify(popUpImageInformationWidget.next).setEnabled(false);
    verify(popUpImageInformationWidget.configureImageUrlCommand).execute(
        popUpImageInformationWidget.next,
        ImageInformationView.IMG_SELECTOR,
        ImageInformationView.SRC_ATTRIBUTE,
        ImageInformationView.NEXT_IMAGE_DISABLED_URL);
  }

  @Test
  public void disablePrevious() {
    imageInformationView.disablePrevious();
    verify(popUpImageInformationWidget.previous).setEnabled(false);
    verify(popUpImageInformationWidget.configureImageUrlCommand).execute(
        popUpImageInformationWidget.previous,
        ImageInformationView.IMG_SELECTOR,
        ImageInformationView.SRC_ATTRIBUTE,
        ImageInformationView.PREVIOUS_IMAGE_DISABLED_URL);
  }

  @Test
  public void enableNext() {
    imageInformationView.enableNext();
    verify(popUpImageInformationWidget.next).setEnabled(true);
    verify(popUpImageInformationWidget.configureImageUrlCommand).execute(
        popUpImageInformationWidget.next,
        ImageInformationView.IMG_SELECTOR,
        ImageInformationView.SRC_ATTRIBUTE,
        ImageInformationView.NEXT_IMAGE_URL);
  }

  @Test
  public void enablePrevious() {
    imageInformationView.enablePrevious();
    verify(popUpImageInformationWidget.previous).setEnabled(true);
    verify(popUpImageInformationWidget.configureImageUrlCommand).execute(
        popUpImageInformationWidget.previous,
        ImageInformationView.IMG_SELECTOR,
        ImageInformationView.SRC_ATTRIBUTE,
        ImageInformationView.PREVIOUS_IMAGE_URL);
  }

  @Test
  public void open() {
    imageInformationView.open();
    verify(popUpImageInformationWidget.modal).show();
  }

  @Test
  public void showWith(final Explorer mockThumbnail,
      final ImageInformation mockImageInformation) {
    stub(mockImageInformation);
    imageInformationView.showWith(mockThumbnail, mockImageInformation);
    verify(popUpImageInformationWidget.modal).setTitle(mockImageInformation.getFilename());
    verify(popUpImageInformationWidget.container).clear();
    verify(popUpImageInformationWidget.container).append(mockThumbnail);
    verify(popUpImageInformationWidget.dateTimeStrong).setText(
        mockImageInformation.getDateTime());
    verify(popUpImageInformationWidget.magnificationStrong).setText(
        mockImageInformation.getMagnification());
    verify(popUpImageInformationWidget.zLayersStrong).setText(
        mockImageInformation.getZlayers());
    verify(popUpImageInformationWidget.zSpacingStrong).setText(
        mockImageInformation.getZspacing());
    verify(popUpImageInformationWidget.dimensionsStrong).setText(
        mockImageInformation.getDimensions());
    verify(popUpImageInformationWidget.focusModeStrong).setText(
        mockImageInformation.getFocusMode());
    verify(popUpImageInformationWidget.focusQualityStrong).setText(
        mockImageInformation.getFocusQuality());
    verify(popUpImageInformationWidget.scanModeStrong).setText(
        mockImageInformation.getScanMode());
    verify(popUpImageInformationWidget.scanResolutionStrong).setText(
        mockImageInformation.getScanResolution());
    verify(popUpImageInformationWidget.unitNumberStrong).setText(
        mockImageInformation.getUnitNumber());
    verify(popUpImageInformationWidget.buildVersionStrong).setText(
        mockImageInformation.getBuildVersion());
    verify(popUpImageInformationWidget.buildDateStrong).setText(
        mockImageInformation.getBuildDate());
  }

  @Test
  public void whenCancelClicked(Subscriber<Void> subscriber,
      ClickEvent mockClickEvent) {
    imageInformationView.whenCancelClickedNotify(subscriber);
    popUpImageInformationWidget.whenCancelButtonClickedNotifySubscriber(mockClickEvent);
    verify(subscriber).notifyWith(null);
  }

  @Test
  public void whenNextClicked(
      Subscriber<Explorer> subscriber,
      ClickEvent mockClickEvent,
      Explorer mockThumbnail,
      ImageInformation mockImageInformation) {
    imageInformationView.showWith(mockThumbnail, mockImageInformation);
    imageInformationView.whenNextClickedNotify(subscriber);
    popUpImageInformationWidget.whenNextLinkClickedNotifySubscriber(mockClickEvent);
    verify(subscriber).notifyWith(mockThumbnail);
  }


  @Test
  public void whenPreviousClicked(
      Subscriber<Explorer> subscriber,
      ClickEvent mockClickEvent,
      Explorer mockThumbnail,
      ImageInformation mockImageInformation) {
    imageInformationView.showWith(mockThumbnail, mockImageInformation);
    imageInformationView.whenPreviousClickedNotify(subscriber);
    popUpImageInformationWidget.whenPreviousLinkClickedNotifySubscriber(mockClickEvent);
    verify(subscriber).notifyWith(mockThumbnail);
  }

  @Test
  public void whenViewClicked(Subscriber<Void> subscriber,
      ClickEvent mockClickEvent) {
    imageInformationView.whenViewClickedNotify(subscriber);
    popUpImageInformationWidget.whenViewButtonClickedNotifySubscriber(mockClickEvent);
    verify(subscriber).notifyWith(null);
  }

  private void stub(ImageInformation mockImageInformation) {
    given(mockImageInformation.getBuildDate()).willReturn("getBuildDate");
    given(mockImageInformation.getBuildVersion()).willReturn("getBuildVersion");
    given(mockImageInformation.getDateTime()).willReturn("getDateTime");
    given(mockImageInformation.getDimensions()).willReturn("getDimensions");
    given(mockImageInformation.getFilename()).willReturn("getFilename");
    given(mockImageInformation.getFocusMode()).willReturn("getFocusMode");
    given(mockImageInformation.getFocusQuality()).willReturn("getFocusQuality");
    given(mockImageInformation.getMagnification()).willReturn("getMagnification");
    given(mockImageInformation.getScanMode()).willReturn("getScanMode");
    given(mockImageInformation.getScanResolution()).willReturn("getScanResolution");
    given(mockImageInformation.getUnitNumber()).willReturn("getUnitNumber");
    given(mockImageInformation.getZlayers()).willReturn("getZlayers");
    given(mockImageInformation.getZspacing()).willReturn("getZspacing");
  }

  @AfterClass
  public static void cleanup() {
    GWTMockUtilities.restore();
  }

  public static class Module extends JukitoModule {
    public static class FakePopUpImageInformationWidgetUiBinder
        extends MockingBinder<Widget, PopUpImageInformationWidget>
        implements PopUpImageInformationWidgetUiBinder {
      @Inject
      public FakePopUpImageInformationWidgetUiBinder(
          MockitoMockFactory mockitoMockFactory) {
        super(Widget.class, mockitoMockFactory);
      }
    }

    @Override
    protected void configureTest() {
      GWTMockUtilities.disarm();
      forceMock(FilesContainer.class);
      forceMock(ConfigureImageUrlCommand.class);
      bind(PopUpImageInformationWidgetUiBinder.class).
          to(FakePopUpImageInformationWidgetUiBinder.class);
    }
  }
}
