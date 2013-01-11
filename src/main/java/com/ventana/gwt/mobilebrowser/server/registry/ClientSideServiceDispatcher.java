package com.ventana.gwt.mobilebrowser.server.registry;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.ventana.gwt.mobilebrowser.server.dispatcher.AsyncDispatcher;
import com.ventana.gwt.mobilebrowser.server.payloads.Payload;
import com.ventana.gwt.mobilebrowser.server.responses.Response;
import com.ventana.gwt.mobilebrowser.server.services.Service;

import java.util.HashMap;
import java.util.Map;

public class ClientSideServiceDispatcher implements AsyncDispatcher {
  private final Map<Class<?>, Service<?, ?>> registry =
      new HashMap<Class<?>, Service<?, ?>>();

  @SuppressWarnings({"cast"}) // ok bc the key to the map is an instance...
  public <P extends Payload<R>, R extends Response> void register(
      final Service<P, R> handler) {
    registry.put((Class<P>) handler.getType(), handler);
  }

  @SuppressWarnings("unchecked") // ok bc the key to the map is an instance...
  @Override
  public <P extends Payload<R>, R extends Response> void execute(
      final P payload, final AsyncCallback<R> callback) {

    final Class<P> type = (Class<P>) payload.getClass();
    final Service<P, R> service = (Service<P, R>)registry.get(type);

    if(service == null)
      throw new IllegalArgumentException("not registered command: " + type);
    service.execute(payload, callback);
  }
}

