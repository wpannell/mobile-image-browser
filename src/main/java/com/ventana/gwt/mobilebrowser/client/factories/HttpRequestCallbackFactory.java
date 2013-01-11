package com.ventana.gwt.mobilebrowser.client.factories;

import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.ventana.gwt.mobilebrowser.server.payloads.ImagesPayload;
import com.ventana.gwt.mobilebrowser.server.responses.ImagesServiceResponse;

public interface HttpRequestCallbackFactory {
  public RequestCallback createCallbackWith(final ImagesPayload payload,
      final AsyncCallback<ImagesServiceResponse> callback);
}
