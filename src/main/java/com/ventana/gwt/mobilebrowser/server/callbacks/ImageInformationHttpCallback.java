package com.ventana.gwt.mobilebrowser.server.callbacks;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.ventana.gwt.mobilebrowser.client.domain.explorer.ExplorerImageInformation;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.xml.IScanXml;
import com.ventana.gwt.mobilebrowser.server.payloads.ImagesPayload;
import com.ventana.gwt.mobilebrowser.server.responses.ImagesServiceResponse;

public class ImageInformationHttpCallback implements RequestCallback {
  private static final String CANNOT_ACCESS_FILE = "cannot access file";
  private final AsyncCallback<ImagesServiceResponse> callback;
  private final ImagesPayload payload;

  public ImageInformationHttpCallback(final ImagesPayload payload,
      final AsyncCallback<ImagesServiceResponse> callback) {
    this.payload = payload;
    this.callback = callback;
  }

  @Override
  public void onError(@SuppressWarnings("unused") final Request request,
      final Throwable exception) {
    callback.onFailure(exception);
  }

  @Override
  public void onResponseReceived(
      @SuppressWarnings("unused") final Request request,
      final Response response) {
    if (!isValid(response)) {
      failWith(response);
      return;
    }
    buildNewExplorerPlaceWith(response.getText(), payload, callback);
  }

  private void buildNewExplorerPlaceWith(final String xml,
      final ImagesPayload payload,
      final AsyncCallback<ImagesServiceResponse> callback) {
    try {
      callbackWithImageInformation(xml, payload, callback);
    }
    catch (Exception e) {
      callback.onFailure(new RuntimeException(CANNOT_ACCESS_FILE));
    }
  }

  private void callbackWithImageInformation(final String xml,
      final ImagesPayload payload,
      final AsyncCallback<ImagesServiceResponse> callback) {
    Explorer place = payload.getPlace();
    place.setImageInformation(new ExplorerImageInformation(place,
        IScanXml.createWith(xml)));
    callback.onSuccess(new ImagesServiceResponse(
        place));
  }

  private void failWith(final Response response) {
    callback.onFailure(new RuntimeException(response.getStatusText()));
  }

  private boolean isValid(final Response response) {
    return response.getStatusCode() == 200;
  }
}