package com.ventana.gwt.mobilebrowser.client.presenters;

import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.place.shared.PlaceController;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Folder;
import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;
import com.ventana.gwt.mobilebrowser.client.views.ImageInformationView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpConfirmationView;
import com.ventana.gwt.mobilebrowser.server.dispatcher.AsyncDispatcher;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AnImageInformationPresenterWhichCantGoPreviousOrNextShould {
  @Captor private ArgumentCaptor<Subscriber<Explorer>>
      whenNextClickedCaptor;
  @Captor private ArgumentCaptor<Subscriber<Explorer>>
      whenPreviousClickedCaptor;

  protected static final boolean SHOULD_SUCCEED = true;
  protected static final String MOCK_SERVICE_FAILED = "service failed";

  @Mock protected ImageInformationView mockView;
  @Mock protected PopUpConfirmationView mockPopUpConfirmationView;
  @Mock private PlaceController mockPlaceController;
  @Mock private AsyncDispatcher mockDispatcher;

  private Folder parent;
  private Thumbnail thumbnail;

  @Before
  public void createFixture() {
    GWTMockUtilities.disarm();

    thumbnail = new Thumbnail("http://127.0.0.0/path/image.jp2");
    parent = new Folder();
    parent.adopt(thumbnail);

    new ImageInformationPresenter(mockView, mockPopUpConfirmationView,
        mockDispatcher, mockPlaceController);
  }

  @Test
  public void bind() {
    verify(mockView).whenNextClickedNotify(whenNextClickedCaptor.capture());
    verify(mockView).whenPreviousClickedNotify(whenPreviousClickedCaptor.capture());
  }

  @Test
  public void notGotoNext() {
    whenNextClicked();
    verify(mockView).disableNext();
  }

  @Test
  public void notGotoPrevious() {
    whenPreviousClicked();
    verify(mockView).disablePrevious();
  }

  private void whenNextClicked() {
    bind();
    whenNextClickedCaptor.getValue().notifyWith(thumbnail);
  }

  private void whenPreviousClicked() {
    bind();
    whenPreviousClickedCaptor.getValue().notifyWith(thumbnail);
  }
}