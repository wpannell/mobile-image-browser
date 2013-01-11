package com.ventana.gwt.mobilebrowser.server.callbacks;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.ventana.gwt.mobilebrowser.server.responses.Response;

public abstract class GetServiceResponse<T extends Response>
    implements AsyncCallback<T> {
  public abstract void got(final T serviceResponse);

  @Override
  public void onSuccess(final T response) { got(response); }
}
