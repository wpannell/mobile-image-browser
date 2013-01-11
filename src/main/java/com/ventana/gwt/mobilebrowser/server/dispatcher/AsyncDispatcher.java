package com.ventana.gwt.mobilebrowser.server.dispatcher;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.ventana.gwt.mobilebrowser.server.payloads.Payload;
import com.ventana.gwt.mobilebrowser.server.responses.Response;

public interface AsyncDispatcher {
  <P extends Payload<R>, R extends Response> void execute(P payload,
      AsyncCallback<R> callback);
}
