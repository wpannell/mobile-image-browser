package com.ventana.gwt.mobilebrowser.server.callbacks;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.xml.ExplorerXml;
import com.ventana.gwt.mobilebrowser.server.builders.ExplorerBuilder;
import com.ventana.gwt.mobilebrowser.server.payloads.ImagesPayload;
import com.ventana.gwt.mobilebrowser.server.responses.ImagesServiceResponse;

public class ExplorerHttpCallback implements RequestCallback {
  private static final String CANNOT_ACCESS_URL = "cannot access url";
  private static final String CANNOT_PARSE_URL = "cannot parse url";
  private final AsyncCallback<ImagesServiceResponse> callback;
  private final ImagesPayload payload;

  public ExplorerHttpCallback(final ImagesPayload payload,
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
      callBackWithNewExplorerNode(xml, payload, callback);
    }
    catch (Exception e) {
      callback.onFailure(new RuntimeException(CANNOT_PARSE_URL));
    }
  }

  private void callBackWithNewExplorerNode(final String xml,
      final ImagesPayload payload,
      final AsyncCallback<ImagesServiceResponse> callback) {
    final ExplorerXml node = ExplorerXml.createWith(xml);
    final Explorer place =
        new ExplorerBuilder(payload.getPlace(), node).build();
    callback.onSuccess(new ImagesServiceResponse(place, payload.getSelectedFolder()));
  }

  private void failWith(final Response response) {
    callback.onFailure(new RuntimeException(CANNOT_ACCESS_URL
        + " : status code - " + response.getStatusCode()
        + " : status message - " + response.getStatusText()
        ));
  }

  private boolean isValid(final Response response) {
    return response.getStatusCode() == 200;
  }
}