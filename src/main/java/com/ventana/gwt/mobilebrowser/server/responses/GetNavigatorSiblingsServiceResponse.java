package com.ventana.gwt.mobilebrowser.server.responses;

import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.server.callbacks.GetImagesServiceResponse;

public class GetNavigatorSiblingsServiceResponse
    extends GetImagesServiceResponse {
  private final ExplorerView view;

  public GetNavigatorSiblingsServiceResponse(final ExplorerView view) {
    this.view = view;
  }

  @Override
  public void got(final ImagesServiceResponse serviceResponse) {
    if (!serviceResponse.isValid()) {
      view.popUp(serviceResponse.getErrorMessage());
      return;
    }
    final Explorer parent = serviceResponse.getImageExplorerPlace();
    for (int i = 0; i < parent.getChildrenSize(); i++)
      parent.getChild(i).loadNavigatorFor(view);

    view.selectNavigatorFolder(parent.getChildIndexOf(serviceResponse.getSelectedPlace()));
  }
}