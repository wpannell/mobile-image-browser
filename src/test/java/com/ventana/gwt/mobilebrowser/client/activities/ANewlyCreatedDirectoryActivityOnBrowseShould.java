package com.ventana.gwt.mobilebrowser.client.activities;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

public class ANewlyCreatedDirectoryActivityOnBrowseShould extends ADirectoryActivityShould {
  @Captor private ArgumentCaptor<Subscriber<Explorer>>
      whenBrowseButtonClickedCaptor;

  @Test
  public void bindView() {
    activity.start(mockContainer, mockEventBus);
    verify(mockView).whenBrowseClickedNotify(
        whenBrowseButtonClickedCaptor.capture());
  }

  @Test
  public void gotoExplorerWhenBrowseButtonClicked() {
    activity.start(mockContainer, mockEventBus);
    whenBrowseButtonClicked();
    verify(mockPlaceController).goTo(mockFolder1);
  }

  private void whenBrowseButtonClicked() {
    bindView();
    whenBrowseButtonClickedCaptor.getValue().notifyWith(mockFolder1);
  }
}