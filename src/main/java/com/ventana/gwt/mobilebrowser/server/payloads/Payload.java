package com.ventana.gwt.mobilebrowser.server.payloads;

import com.google.gwt.user.client.rpc.IsSerializable;

import com.ventana.gwt.mobilebrowser.server.responses.Response;

public interface Payload<R extends Response> extends IsSerializable {
  String DEFAULT_SERVICE_NAME = "gwt.disptach";
}