package com.ventana.gwt.mobilebrowser.server.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.ventana.gwt.mobilebrowser.server.payloads.Payload;
import com.ventana.gwt.mobilebrowser.server.responses.Response;

public interface Service<P extends Payload<R>, R extends Response> {
  void execute(final P payload, final AsyncCallback<R> callback);
  Class<P> getType();
}
