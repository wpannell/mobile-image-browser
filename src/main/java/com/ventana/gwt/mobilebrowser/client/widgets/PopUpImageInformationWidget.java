package com.ventana.gwt.mobilebrowser.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.Strong;
import com.ventana.gwt.mobilebrowser.client.commands.ConfigureImageUrlCommand;
import com.ventana.gwt.mobilebrowser.client.containers.FilesContainer;
import com.ventana.gwt.mobilebrowser.client.domain.ImageInformation;
import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Folder;
import com.ventana.gwt.mobilebrowser.client.views.ImageInformationView;

public class PopUpImageInformationWidget extends TestableComposite
    implements ImageInformationView {
  @UiTemplate("PopUpImageInformationWidget.ui.xml")
  public interface PopUpImageInformationWidgetUiBinder
      extends UiBinder<Widget, PopUpImageInformationWidget> {}

  @UiField Strong buildDateStrong;
  @UiField Strong buildVersionStrong;
  @UiField Strong dateTimeStrong;
  @UiField Strong dimensionsStrong;
  @UiField Strong focusModeStrong;
  @UiField Strong focusQualityStrong;
  @UiField Strong magnificationStrong;
  @UiField Modal modal;
  @UiField Anchor next;
  @UiField Anchor previous;
  @UiField Strong scanModeStrong;
  @UiField Strong unitNumberStrong;
  @UiField Strong scanResolutionStrong;
  @UiField Strong zLayersStrong;
  @UiField Strong zSpacingStrong;

  final FilesContainer container;
  final ConfigureImageUrlCommand configureImageUrlCommand;

  private Subscriber<Void> cancelClickedSubscriber;
  private Subscriber<Explorer> nextClickedSubscriber;
  private Subscriber<Explorer> previousClickedSubscriber;
  private Explorer thumbnail = Folder.EMPTY;
  private Subscriber<Void> viewClickedSubscriber;

  public PopUpImageInformationWidget() {
    this(new FilesContainer(), new ConfigureImageUrlCommand(),
        GWT.<PopUpImageInformationWidgetUiBinder> create(
            PopUpImageInformationWidgetUiBinder.class));
  }

  @Inject
  public PopUpImageInformationWidget(final FilesContainer container,
      final ConfigureImageUrlCommand configureImageUrlCommand,
      final PopUpImageInformationWidgetUiBinder binder) {
    initWidget(binder.createAndBindUi(this));
    this.container = container;
    this.configureImageUrlCommand = configureImageUrlCommand;
    cancelClickedSubscriber = Subscriber.VOID_EMPTY;
    nextClickedSubscriber = Subscriber.EXPLORER_EMPTY;
    previousClickedSubscriber = Subscriber.EXPLORER_EMPTY;
    viewClickedSubscriber = Subscriber.VOID_EMPTY;
  }

  @Override
  public void close() {
    container.clear();
    modal.hide();
  }

  @Override
  public void disableNext() {
    next.setEnabled(false);
    configureImageUrlCommand.execute(
        next,
        ImageInformationView.IMG_SELECTOR,
        ImageInformationView.SRC_ATTRIBUTE,
        ImageInformationView.NEXT_IMAGE_DISABLED_URL);
  }

  @Override
  public void disablePrevious() {
    previous.setEnabled(false);
    configureImageUrlCommand.execute(
        previous,
        ImageInformationView.IMG_SELECTOR,
        ImageInformationView.SRC_ATTRIBUTE,
        ImageInformationView.PREVIOUS_IMAGE_DISABLED_URL);
  }

  @Override
  public void enableNext() {
    next.setEnabled(true);
    configureImageUrlCommand.execute(
        next,
        ImageInformationView.IMG_SELECTOR,
        ImageInformationView.SRC_ATTRIBUTE,
        ImageInformationView.NEXT_IMAGE_URL);
  }

  @Override
  public void enablePrevious() {
    previous.setEnabled(true);
    configureImageUrlCommand.execute(
        previous, 
        ImageInformationView.IMG_SELECTOR, 
        ImageInformationView.SRC_ATTRIBUTE, 
        ImageInformationView.PREVIOUS_IMAGE_URL);
  }

  @Override
  public void open() {
    modal.show();
  }

  @Override
  public void showWith(
      final Explorer thumbnail, final ImageInformation imageInformation) {
    this.thumbnail = thumbnail;
    modal.setTitle(imageInformation.getFilename());
    set(imageInformation);
    container.clear();
    container.append(thumbnail);
  }

  private void set(final ImageInformation imageInformation) {
    dateTimeStrong.setText(imageInformation.getDateTime());
    magnificationStrong.setText(imageInformation.getMagnification());
    zLayersStrong.setText(imageInformation.getZlayers());
    zSpacingStrong.setText(imageInformation.getZspacing());
    dimensionsStrong.setText(imageInformation.getDimensions());
    focusModeStrong.setText(imageInformation.getFocusMode());
    focusQualityStrong.setText(imageInformation.getFocusQuality());
    scanModeStrong.setText(imageInformation.getScanMode());
    scanResolutionStrong.setText(imageInformation.getScanResolution());
    unitNumberStrong.setText(imageInformation.getUnitNumber());
    buildVersionStrong.setText(imageInformation.getBuildVersion());
    buildDateStrong.setText(imageInformation.getBuildDate());
  }

  @Override
  public void whenCancelClickedNotify(final Subscriber<Void> subscriber) {
    cancelClickedSubscriber = subscriber;
  }

  @Override
  public void whenNextClickedNotify(final Subscriber<Explorer> subscriber) {
    nextClickedSubscriber = subscriber;
  }

  @Override
  public void whenPreviousClickedNotify(final Subscriber<Explorer> subscriber) {
    previousClickedSubscriber = subscriber;
  }

  @Override
  public void whenViewClickedNotify(final Subscriber<Void> subscriber) {
    viewClickedSubscriber = subscriber;
  }

  @UiHandler("cancelButton") @SuppressWarnings("unused")  //...unused event...
  void whenCancelButtonClickedNotifySubscriber(final ClickEvent event) {
    cancelClickedSubscriber.notifyWith(null);
  }

  @UiHandler("next") @SuppressWarnings("unused")  //...unused event...
  void whenNextLinkClickedNotifySubscriber(final ClickEvent event) {
    nextClickedSubscriber.notifyWith(thumbnail);
  }

  @UiHandler("previous") @SuppressWarnings("unused")  //...unused event...
  void whenPreviousLinkClickedNotifySubscriber(final ClickEvent event) {
    previousClickedSubscriber.notifyWith(thumbnail);
  }

  @UiHandler("viewButton") @SuppressWarnings("unused")  //...unused event...
  void whenViewButtonClickedNotifySubscriber(final ClickEvent event) {
    viewClickedSubscriber.notifyWith(null);
  }
  
  @Override
  protected void onLoad() {
    super.onLoad();
    container.setSelector(ImageInformationView.IMAGE_NAVIGATOR_THUMB_SELECTOR);
  }

  @Override
  protected void onUnload() {
    container.clear();
    super.onLoad();
  }
}