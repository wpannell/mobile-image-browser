package com.ventana.gwt.mobilebrowser.client.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Image;
import com.ventana.gwt.mobilebrowser.client.views.ImageView;
import com.ventana.gwt.mobilebrowser.client.views.ToolBarView;

public class ImageActivity extends AbstractActivity {
  private final Image imagePlace;
  private final ToolBarView toolbar;
  private final ImageView view;

  @Inject
  public ImageActivity(final ImageView view, final ToolBarView toolbar,
      @Assisted final Image imagePlace) {
    this.view = view;
    this.toolbar = toolbar;
    this.imagePlace = imagePlace;
  }

  @Override
  public void start(AcceptsOneWidget container,
      @SuppressWarnings("unused") EventBus eventBus) {
    startWith(container);
    bind();
    registerEvents();
    configureViewState();
    configureView();
  }

  @Override
  public void onStop() {
    view.deRegisterEvents();
  }

  private void bind() {
    view.whenZoomChangedNotify(new Subscriber<String>() {
      @Override
      public void notifyWith(String zoomLevel) {
        toolbar.setInformationText(zoomLevel);
      }
    });
    view.whenTappedNotify(new Subscriber<Void>() {
      @Override @SuppressWarnings("unused") // ...unused event parameter...
      public void notifyWith(Void notification) {
        toolbar.isShowing(!toolbar.isShowing());
      }
    });
  }

  private void configureView() {
    view.configure(imagePlace.getProtocol(), imagePlace.getHost(),
        imagePlace.getPort(), imagePlace.getParentPath(), imagePlace.getName(),
        imagePlace.getQualityLevels(), imagePlace.getMagnification());
  }

  private void configureViewState() {
    toolbar.isShowing(ToolBarView.YES);
    toolbar.isBackButtonShowing(ToolBarView.YES);
    toolbar.isAddButtonShowing(ToolBarView.NO);
    toolbar.isInformationTextShowing(ToolBarView.YES);
  }

  private void registerEvents() {
    view.registerEvents();
  }

  private void startWith(final AcceptsOneWidget container) {
    view.addMeToThis(container);
  }
}
