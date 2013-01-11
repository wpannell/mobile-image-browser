package com.ventana.gwt.mobilebrowser.server.services;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.ventana.gwt.mobilebrowser.server.payloads.ImagesPayload;
import com.ventana.gwt.mobilebrowser.server.responses.ImagesServiceResponse;

public class FetchImagesService
    implements Service<ImagesPayload, ImagesServiceResponse> {
  private static final String EMPTY_REQUEST_DATA = "";

  @Override
  public void execute(final ImagesPayload payload,
      final AsyncCallback<ImagesServiceResponse> callback) {
    if(payload.getPlace().hasChildren()) {
      callback.onSuccess(new ImagesServiceResponse(
          payload.getPlace(), payload.getSelectedFolder()));
      return;
    }
    final RequestBuilder requestBuilder =
        new RequestBuilder(RequestBuilder.GET, payload.getUrl());
    fetchWith(requestBuilder, payload, callback);
  }

  @Override
  public Class<ImagesPayload> getType() {
    return ImagesPayload.class;
  }

  private void fetchWith(final RequestBuilder requestBuilder,
      final ImagesPayload payload,
      final AsyncCallback<ImagesServiceResponse> imagesServiceResponse) {
    try {
      requestBuilder.sendRequest(
          EMPTY_REQUEST_DATA, payload.createRequestCallbackWith(imagesServiceResponse));
    }
    catch (final RequestException e) {
      imagesServiceResponse.onFailure(e);
    }
  }
}
