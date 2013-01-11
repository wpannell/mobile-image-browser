package com.ventana.gwt.mobilebrowser.client.factories.callbacks;

import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.ventana.gwt.mobilebrowser.client.factories.HttpRequestCallbackFactory;
import com.ventana.gwt.mobilebrowser.server.callbacks.ExplorerHttpCallback;
import com.ventana.gwt.mobilebrowser.server.payloads.ImagesPayload;
import com.ventana.gwt.mobilebrowser.server.responses.ImagesServiceResponse;

public class ExplorerHttpCallbackFactory implements HttpRequestCallbackFactory {
  @Override
  public RequestCallback createCallbackWith(ImagesPayload payload,
      AsyncCallback<ImagesServiceResponse> callback) {
    return new ExplorerHttpCallback(payload, callback);
  }
}
