package com.ventana.gwt.mobilebrowser.client.factories.callbacks;

import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.ventana.gwt.mobilebrowser.client.factories.HttpRequestCallbackFactory;
import com.ventana.gwt.mobilebrowser.server.callbacks.ImageInformationHttpCallback;
import com.ventana.gwt.mobilebrowser.server.payloads.ImagesPayload;
import com.ventana.gwt.mobilebrowser.server.responses.ImagesServiceResponse;

public class ImageInformationHttpCallbackFactory implements HttpRequestCallbackFactory {
  @Override
  public RequestCallback createCallbackWith(ImagesPayload payload,
      AsyncCallback<ImagesServiceResponse> callback) {
    return new ImageInformationHttpCallback(payload, callback);
  }
}
