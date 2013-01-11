package com.ventana.gwt.mobilebrowser.client.presenters;

import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Folder;
import com.ventana.gwt.mobilebrowser.client.places.Image;
import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;
import com.ventana.gwt.mobilebrowser.client.views.ImageInformationView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpConfirmationView;
import com.ventana.gwt.mobilebrowser.server.dispatcher.AsyncDispatcher;

public class ImageInformationPresenter {
  private final AsyncDispatcher dispatcher;
  private Explorer parent = Folder.EMPTY;
  private final PlaceController placeController;
  private final PopUpConfirmationView popUpMessageView;
  private Explorer thumbnail = Folder.EMPTY;
  private final ImageInformationView view;

  @Inject
  public ImageInformationPresenter(final ImageInformationView view,
      final PopUpConfirmationView popUpMessageView,
      final AsyncDispatcher dispatcher, final PlaceController placeController) {
    this.view = view;
    this.popUpMessageView = popUpMessageView;
    this.dispatcher = dispatcher;
    this.placeController = placeController;
    bind();
  }

  public void show(Thumbnail thumbnail, Explorer parent) {
    this.parent = parent;
    this.thumbnail = thumbnail;
    this.thumbnail.showNextImageInformationWith(dispatcher, view, popUpMessageView);
    view.open();
    view.enableNext();
  }

  private void bind() {
    view.whenCancelClickedNotify(new Subscriber<Void>() {
      @Override @SuppressWarnings("unused") // ... unused event parameter ...
      public void notifyWith(final Void notification) {
        view.close();
      }
    });
    view.whenViewClickedNotify(new Subscriber<Void>() {
      @Override @SuppressWarnings("unused") // ... unused event parameter ...
      public void notifyWith(final Void notification) {
        view.close();
        placeController.goTo(new Image(thumbnail));
      }
    });
    view.whenNextClickedNotify(new Subscriber<Explorer>() {
      @Override
      public void notifyWith(final Explorer thumbnail) {
        final Explorer next = parent.getNextChildAfter(thumbnail);
        next.showNextImageInformationWith(dispatcher, view, popUpMessageView);
      }
    });
    view.whenPreviousClickedNotify(new Subscriber<Explorer>() {
      @Override
      public void notifyWith(final Explorer thumbnail) {
        final Explorer next = parent.getPreviousChildAfter(thumbnail);
        next.showPreviousImageInformationWith(dispatcher, view, popUpMessageView);
      }
    });
  }
}
