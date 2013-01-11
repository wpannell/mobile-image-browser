package com.ventana.gwt.mobilebrowser.client.activities;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.RootFolder;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

public class ANewlyCreatedDirectoryActivityOnDeleteShould
    extends ADirectoryActivityShould {
  @Captor private ArgumentCaptor<Subscriber<Explorer>>
      whenDeleteButtonClickedCaptor;

  @Mock private Directory mockDirectory;
  @Mock protected Explorer folderToDelete;
  @Mock protected RootFolder folderToDeleteAsRootFolder;

  @Override
  public void create() {
    activity = new DirectoryActivity(mockView, mockToolBar, mockDeletePresenter,
        mockEditPresenter, mockPlaceController, mockDirectory);
    given(folderToDelete.asRootFolder()).willReturn(folderToDeleteAsRootFolder);
  }

  @Test
  public void bindView() {
    activity.start(mockContainer, mockEventBus);
    verify(mockView).whenDeleteClickedNotify(
        whenDeleteButtonClickedCaptor.capture());
  }

  @Test
  public void deleteFolder() {
    whenDeleteButtonClicked();
    verify(mockDeletePresenter).popUpFor(mockDirectory, folderToDeleteAsRootFolder);
  }

  private void whenDeleteButtonClicked() {
    bindView();
    whenDeleteButtonClickedCaptor.getValue().notifyWith(folderToDelete);
  }
}