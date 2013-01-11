package com.ventana.gwt.mobilebrowser.server.responses;

import com.ventana.gwt.mobilebrowser.client.views.ImageInformationView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpConfirmationView;
import com.ventana.gwt.mobilebrowser.server.callbacks.GetImagesServiceResponse;

public class GetImageInformationServiceResponse
    extends GetImagesServiceResponse {
  private final ImageInformationView view;
  private final PopUpConfirmationView popupView;

  public GetImageInformationServiceResponse(
      final ImageInformationView view, final PopUpConfirmationView popupView) {
    this.view = view;
    this.popupView = popupView;
  }

  @Override
  public void got(final ImagesServiceResponse serviceResponse) {
    if(!serviceResponse.isValid()) {
      view.close();
      popupView.showWith(serviceResponse.getErrorMessage());
      return;
    }
    serviceResponse.getImageExplorerPlace().loadImageInformationFor(view);
  }
}