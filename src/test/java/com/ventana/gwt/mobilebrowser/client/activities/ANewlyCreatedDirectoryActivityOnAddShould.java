package com.ventana.gwt.mobilebrowser.client.activities;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.RootFolder;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ANewlyCreatedDirectoryActivityOnAddShould extends ADirectoryActivityShould {
  @Captor private ArgumentCaptor<Subscriber<Void>>
      whenAddButtonClickedCaptor;

  @Test
  public void bindToolBar() {
    activity.start(mockContainer, mockEventBus);
    verify(mockToolBar).whenAddButtonClickedNotify(
        whenAddButtonClickedCaptor.capture());
  }

  @Test
  public void editEmptyFolderWhenAddClicked() {
    whenAddButtonClicked();
    verify(mockEditPresenter).show(directory, RootFolder.EMPTY);
  }

  private void whenAddButtonClicked() {
    bindToolBar();
    whenAddButtonClickedCaptor.getValue().notifyWith(null);
  }
}