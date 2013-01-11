package com.ventana.gwt.mobilebrowser.server.responses;

import com.ventana.gwt.mobilebrowser.client.places.Folder;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.server.responses.GetExplorerServiceResponse;
import com.ventana.gwt.mobilebrowser.server.responses.ImagesServiceResponse;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ANewlyCreatedGetExplorerServiceResponseShould {
  @Mock private ExplorerView mockView;
  @Mock private Folder mockChildFolder;
  @Mock private Thumbnail mockChildThumbnail;
  private Explorer fetchedImages;

  private ImagesServiceResponse response;
  private GetExplorerServiceResponse callback;

  @Before
  public void createFixture() {
    fetchedImages = new Folder();
    fetchedImages.adopt(mockChildFolder);
    fetchedImages.adopt(mockChildThumbnail);

    response = new ImagesServiceResponse(fetchedImages);
    callback = new GetExplorerServiceResponse(mockView) {
      @Override protected void initializeLazilyLoadedImages() {}
    };
  }

  @Test
  public void loadExplorerWithChildImages() {
    callback.got(response);
    verify(mockChildFolder).loadExplorerFor(mockView);
    verify(mockChildThumbnail).loadExplorerFor(mockView);
  }

  @Test
  public void showErrorMessage() {
    callback.got(new ImagesServiceResponse("service failed"));
    verify(mockView).popUp("service failed");
    verify(mockChildFolder, times(0)).loadExplorerFor(mockView);
    verify(mockChildThumbnail, times(0)).loadExplorerFor(mockView);
  }
}