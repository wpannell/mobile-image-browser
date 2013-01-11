package com.ventana.gwt.mobilebrowser.client.activities;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

public class ANewlyCreatedDirectoryActivityOnEditShould
    extends ADirectoryActivityShould {

  @Captor private ArgumentCaptor<Subscriber<Explorer>>
      whenEditButtonClickedCaptor;

  @Test
  public void bindView() {
    activity.start(mockContainer, mockEventBus);
    verify(mockView).whenEditClickedNotify(
        whenEditButtonClickedCaptor.capture());
  }

  @Test
  public void editFolderWhenEditButtonClicked() {
    whenEditButtonClicked();
    verify(mockEditPresenter).show(directory, mockFolder1);
  }

  private void whenEditButtonClicked() {
    bindView();
    whenEditButtonClickedCaptor.getValue().notifyWith(mockFolder1);
  }
}