package com.ventana.gwt.mobilebrowser.client.ui;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.ventana.gwt.mobilebrowser.client.containers.EventContainer;
import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.views.ImageView;
import com.ventana.gwt.mobilebrowser.client.views.components.MobileImageViewerComponent;
import com.ventana.gwt.mobilebrowser.client.widgets.TestableComposite;

public class ImageViewerUi extends TestableComposite implements ImageView {
  @UiTemplate("ImageViewer.ui.xml")
  public interface ImageViewerUiBinder extends UiBinder<Widget, ImageViewerUi> {}

  @UiField SimpleLayoutPanel container;

  final MobileImageViewerComponent imageComponent;
  final EventContainer eventContainer;

  @Inject
  public ImageViewerUi(final MobileImageViewerComponent imageComponent,
      final EventContainer eventContainer, final ImageViewerUiBinder binder) {
    this.imageComponent = imageComponent;
    this.eventContainer = eventContainer;
    initWidget(binder.createAndBindUi(this));
    container.add(this.imageComponent.getImageViewer());
  }

  @Override
  public void addMeToThis(final AcceptsOneWidget container) {
    container.setWidget(this);
  }

  @Override
  public void configure(final String protocol, final String host,
      final String port, final String path, final String fileName,
      final int[][] qualityLevels, final int magnification) {
    imageComponent.configure(protocol, host, port, path, fileName,
        qualityLevels, magnification);
  }

  @Override
  public void deRegisterEvents() {
    eventContainer.clear();
    imageComponent.deRegisterEvents();
  }

  @Override
  public void registerEvents() {
    imageComponent.registerEvents();
  }

  @Override
  public void whenTappedNotify(final Subscriber<Void> subscriber) {
    eventContainer.whenTappedNotify(this, subscriber);
  }

  @Override
  public void whenZoomChangedNotify(final Subscriber<String> imageZoomSubscriber) {
    imageComponent.whenZoomChangedNotify(imageZoomSubscriber);
  }
}