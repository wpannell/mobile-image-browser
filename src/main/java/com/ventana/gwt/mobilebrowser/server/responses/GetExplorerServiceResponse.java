package com.ventana.gwt.mobilebrowser.server.responses;

import com.google.inject.Inject;

import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.server.callbacks.GetImagesServiceResponse;

public class GetExplorerServiceResponse extends GetImagesServiceResponse {
  private final ExplorerView view;

  @Inject
  public GetExplorerServiceResponse(final ExplorerView view) {
    this.view = view;
  }

  @Override
  public void got(final ImagesServiceResponse serviceResponse) {
    if (!serviceResponse.isValid()) {
      view.popUp(serviceResponse.getErrorMessage());
      return;
    }
    final Explorer imagePlace = serviceResponse.getImageExplorerPlace();
    for (int i = 0; i < imagePlace.getChildrenSize(); i++)
      imagePlace.getChild(i).loadExplorerFor(view);
    initializeLazilyLoadedImages();
  }

  protected void initializeLazilyLoadedImages() {
    initializeLazyLoadedImages();
  }

  private native void initializeLazyLoadedImages() /*-{
    $wnd.$("img.lazy").show().lazyload({
      event: "scrollstop",
      container: $wnd.$("section.imageBrowser")
    });
  }-*/;
}