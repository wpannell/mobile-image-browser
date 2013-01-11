package com.ventana.gwt.mobilebrowser.server.callbacks;

import com.ventana.gwt.mobilebrowser.server.responses.ImagesServiceResponse;

public abstract class GetImagesServiceResponse
    extends GetServiceResponse<ImagesServiceResponse> {
  private static final String UNKNOWN_ERROR_CALL_SUPPORT =
      "unknown error: call support";

  @Override
  public void onFailure(final Throwable caught) {
    got(new ImagesServiceResponse(caught.getMessage() == null
        ? UNKNOWN_ERROR_CALL_SUPPORT : caught.getMessage()));
  }
}
