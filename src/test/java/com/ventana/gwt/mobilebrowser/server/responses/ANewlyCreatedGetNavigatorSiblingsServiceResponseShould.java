package com.ventana.gwt.mobilebrowser.server.responses;

import com.ventana.gwt.mobilebrowser.client.places.Folder;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.server.responses.GetNavigatorSiblingsServiceResponse;
import com.ventana.gwt.mobilebrowser.server.responses.ImagesServiceResponse;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ANewlyCreatedGetNavigatorSiblingsServiceResponseShould {
  @Mock private ExplorerView mockView;
  @Mock private Folder mockSelectedFolder;
  @Mock private Thumbnail mockThumbnail;
  @Mock private Explorer mockParent;

  private ImagesServiceResponse response;
  private GetNavigatorSiblingsServiceResponse callback;

  @Before
  public void createFixture() {
    given(mockSelectedFolder.getParent()).willReturn(mockParent);
    given(mockParent.getChildrenSize()).willReturn(2);
    given(mockParent.getChild(0)).willReturn(mockSelectedFolder);
    given(mockParent.getChild(1)).willReturn(mockThumbnail);

    response = new ImagesServiceResponse(mockParent, mockSelectedFolder);
    callback = new GetNavigatorSiblingsServiceResponse(mockView);
  }

  @Test
  public void loadNavigatorWithSiblingFolder() {
    callback.got(response);
    verify(mockSelectedFolder).loadNavigatorFor(mockView);
  }

  @Test
  public void callNoOpWithSiblingFile() {
    callback.got(response);
    verify(mockThumbnail).loadNavigatorFor(mockView);
  }

  @Test
  public void showSelectedFolderInNavigatorWhenExplorerFolderClicked() {
    callback.got(response);
    verify(mockParent).getChildIndexOf(mockSelectedFolder);
    verify(mockView).selectNavigatorFolder(anyInt());
  }

  @Test
  public void notPopUpErrorMessage() {
    callback.got(response);
    verify(mockView,times(0)).popUp(anyString());
  }

  @Test
  public void showErrorMessage() {
    callback.got(new ImagesServiceResponse("service failed"));
    verify(mockView).popUp("service failed");
    verify(mockSelectedFolder, times(0)).loadNavigatorFor(mockView);
    verify(mockThumbnail, times(0)).loadNavigatorFor(mockView);
    verify(mockParent, times(0)).getChildIndexOf(mockSelectedFolder);
    verify(mockView, times(0)).selectNavigatorFolder(anyInt());
    verify(mockView).popUp(anyString());
  }
}