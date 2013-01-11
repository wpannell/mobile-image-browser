package com.ventana.gwt.mobilebrowser.server.responses;

import com.ventana.gwt.mobilebrowser.client.views.PopUpEditView;
import com.ventana.gwt.mobilebrowser.server.callbacks.GetImagesServiceResponse;

public class GetExplorerValidationServiceResponse
    extends GetImagesServiceResponse {
  private final PopUpEditView view;

  public GetExplorerValidationServiceResponse(final PopUpEditView view) {
    this.view = view;
  }

  @Override
  public void got(final ImagesServiceResponse serviceResponse) {
    view.isBadPathHelpShowing(!serviceResponse.isValid());
    view.isPathHelpShowing(!serviceResponse.isValid());
  }
}