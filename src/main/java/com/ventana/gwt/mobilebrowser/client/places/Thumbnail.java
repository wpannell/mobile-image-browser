package com.ventana.gwt.mobilebrowser.client.places;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

import com.ventana.gwt.mobilebrowser.client.domain.ImageInformation;
import com.ventana.gwt.mobilebrowser.client.factories.callbacks.ImageInformationHttpCallbackFactory;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.client.views.ImageInformationView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpConfirmationView;
import com.ventana.gwt.mobilebrowser.client.xml.FileXml;
import com.ventana.gwt.mobilebrowser.server.dispatcher.AsyncDispatcher;
import com.ventana.gwt.mobilebrowser.server.payloads.ImagesPayload;
import com.ventana.gwt.mobilebrowser.server.responses.GetImageInformationServiceResponse;

public class Thumbnail extends Explorer {
  @Prefix(value = "ThumbnailExplorer")
  public static class Tokenizer implements PlaceTokenizer<Thumbnail> {
    @Override
    public Thumbnail getPlace(final String historyToken) {
      return new Thumbnail(historyToken);
    }
    @Override
    public String getToken(final Thumbnail place) {
      return place.getHistoryToken();
    }
  }
  public static final String PREFIX = "<li><div><a href='javascript:void(0)'>" +
      "<img class='lazy' src='' data-original='" ;
  public static final String INFIX = "'></a><p>" ;
  public static final String SUFFIX =
      "</p><a class='imageInfoLink' href='javascript:void(0)'>&nbsp;</a></div></li>" ;
  public static final String THUMBNAIL_SUFFIX =
      "?label&rotate=270&scale=30&rdims=150";

  private ImageInformation imageInformation = ImageInformation.EMPTY;

  public Thumbnail() {
    super();
  }

  public Thumbnail(final String historyToken) {
    super(historyToken);
  }

  public Thumbnail(final String historyToken, final FileXml file) {
    super(historyToken + SEPARATOR + file.getName(), file.getName(),
        file.getTime(), file.getSize());
  }

  @Override
  public String asHtml() {
    return PREFIX + getUrl() + THUMBNAIL_SUFFIX + INFIX +
        getName() + SUFFIX;
  }

  public Explorer createImageInformationDecorator() {
    return new ImageInformationDecorator(this);
  }

  @Override
  public ImageInformation getImageInformation() {
    return imageInformation;
  }

  @Override
  public void loadExplorerFor(final ExplorerView view) {
    view.loadExplorerWith(this);
  }

  @Override
  public Boolean isRoot() {
    return false;
  }

  @Override
  public void loadImageInformationFor(final ImageInformationView view) {
    view.showWith(createImageInformationDecorator(), this.getImageInformation());
  }

  @Override @SuppressWarnings("unused") // ... template method ...
  public void loadNavigatorFor(final ExplorerView view) {}

  @Override
  public void setImageInformation(final ImageInformation imageInformation) {
    this.imageInformation = imageInformation;
  }

  @Override
  public void showNextImageInformationWith(final AsyncDispatcher dispatcher,
      final ImageInformationView view,
      final PopUpConfirmationView popUpMessageView) {
    view.enablePrevious();
    dispatcher.execute(
        new ImagesPayload(this, new ImageInformationHttpCallbackFactory()),
        new GetImageInformationServiceResponse(view, popUpMessageView));
  }

  @Override
  public void showPreviousImageInformationWith(final AsyncDispatcher dispatcher,
      final ImageInformationView view,
      final PopUpConfirmationView popUpMessageView) {
    view.enableNext();
    dispatcher.execute(
        new ImagesPayload(this, new ImageInformationHttpCallbackFactory()),
        new GetImageInformationServiceResponse(view, popUpMessageView));
  }
}
