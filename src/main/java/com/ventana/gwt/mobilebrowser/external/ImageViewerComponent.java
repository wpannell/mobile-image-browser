package com.ventana.gwt.mobilebrowser.external;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Widget;

import com.chrisgammage.gwtjitsu.client.events.PropertyChangeEvent;
import com.chrisgammage.gwtjitsu.client.events.PropertyChangeHandler;
import com.ventana.gwt.mobile_viewer.client.components.mobile_viewer.views.MobileViewerPresenter;
import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.views.components.MobileImageViewerComponent;
import com.ventana.gwt.viewer.client.components.image_source.ImageSourceModel;
import com.ventana.gwt.viewer.client.components.viewer.ViewerModel;
import com.ventana.gwt.viewer.client.components.viewport.ViewportModel;

public class ImageViewerComponent implements MobileImageViewerComponent {
  private static final String SEPARATOR = "/";
  private HandlerRegistration eventRegistration;
  private int magnification;
  private final MobileViewerPresenter mobileViewerPresenter;
  private Subscriber<String> zoomChangedSubscriber;

  public ImageViewerComponent(final MobileViewerPresenter mobileViewerPresenter) {
    this.mobileViewerPresenter = mobileViewerPresenter;
    magnification = 20;
    zoomChangedSubscriber = Subscriber.STRING_EMPTY;
    eventRegistration = new NullHandlerRegistration();
  }

  @Override
  public void configure(final String protocol, final String host,
      final String port, final String path, final String fileName,
      final int[][] qualityLevels, final int magnification) {
    this.magnification = magnification;
    getImageSourceModel().setServerURL(protocol + host + port + path);
    getImageSourceModel().setImagePath(SEPARATOR + fileName);
    getImageSourceModel().setQualityLevels(new QualitityLevelsBuilder(qualityLevels).build());
    getImageSourceModel().setBaseMagnification(this.magnification);
    setImageSourceModel(getImageSourceModel());
  }

  @Override
  public void deRegisterEvents() {
    eventRegistration.removeHandler();
  }

  @Override
  public Widget getImageViewer() {
    return mobileViewerPresenter.getViewerView();
  }

  @Override
  public void registerEvents() {
    eventRegistration = getViewportModel().addPropertyChangeHandler(
        ViewportModel.ZOOM, new PropertyChangeHandler() {
      @Override @SuppressWarnings("unused") // ...unused event paramter...
      public void onPropertyChange(final PropertyChangeEvent event) {
        zoomChangedSubscriber.notifyWith(getZoom());
      }
    });
  }

  @Override
  public void whenZoomChangedNotify(final Subscriber<String> subscriber) {
    zoomChangedSubscriber = subscriber;
  }

  protected String getZoom() {
    return NumberFormat.getFormat("0.##").format(
        magnification * getViewportModel().getZoom()) + "X";
  }

  private ImageSourceModel getImageSourceModel() {
    return mobileViewerPresenter.getImageSourceModel();
  }

  private ViewerModel getViewerModel() {
    return mobileViewerPresenter.getViewerModel();
  }

  private ViewportModel getViewportModel() {
    return getViewerModel().getViewport(0).getModel();
  }

  private void setImageSourceModel(final ImageSourceModel imageSourceModel) {
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        getViewportModel().setImageSource(imageSourceModel);
      }
    });
  }
}
