package com.ventana.gwt.mobilebrowser.server.responses;

import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Folder;

public class ImagesServiceResponse implements Response {
  private final String errorMessage;
  private final Explorer imagePlace;
  private final Explorer selectedPlace;

  public ImagesServiceResponse() {
    this("");
  }

  public ImagesServiceResponse(final Explorer imagePlace) {
    this(imagePlace, "");
  }

  public ImagesServiceResponse(final Explorer place,
      final Explorer selectedFolder) {
    this(place, selectedFolder, "");
  }

  public ImagesServiceResponse(final Explorer place,
      final Explorer selectedFolder, final String errorMessage) {
    imagePlace = place;
    selectedPlace = selectedFolder;
    this.errorMessage = errorMessage;
  }

  public ImagesServiceResponse(final Explorer place, final String errorMessage) {
    this(place, place, errorMessage);
  }

  public ImagesServiceResponse(final String errorMessage) {
    this(Folder.EMPTY, errorMessage);
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public Explorer getImageExplorerPlace() {
    return imagePlace;
  }

  public Explorer getSelectedPlace() {
    return selectedPlace;
  }

  public boolean isValid() {
    return errorMessage.equals("");
  }
}