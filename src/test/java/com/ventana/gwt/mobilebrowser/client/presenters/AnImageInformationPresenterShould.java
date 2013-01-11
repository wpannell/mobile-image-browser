package com.ventana.gwt.mobilebrowser.client.presenters;

import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.ventana.gwt.mobilebrowser.client.domain.ImageInformation;
import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Folder;
import com.ventana.gwt.mobilebrowser.client.places.Image;
import com.ventana.gwt.mobilebrowser.client.places.ImageInformationDecorator;
import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;
import com.ventana.gwt.mobilebrowser.client.views.ImageInformationView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpConfirmationView;
import com.ventana.gwt.mobilebrowser.server.payloads.ImagesPayload;
import com.ventana.gwt.mobilebrowser.server.payloads.Payload;
import com.ventana.gwt.mobilebrowser.server.registry.ClientSideServiceDispatcher;
import com.ventana.gwt.mobilebrowser.server.responses.ImagesServiceResponse;
import com.ventana.gwt.mobilebrowser.server.responses.Response;
import com.ventana.gwt.mobilebrowser.server.services.FetchImagesService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AnImageInformationPresenterShould {
  @Captor private ArgumentCaptor<Subscriber<Void>>
      whenCancelClickedCaptor;
  @Captor private ArgumentCaptor<Subscriber<Void>>
      whenViewClickedCaptor;
  @Captor private ArgumentCaptor<Subscriber<Explorer>>
      whenNextClickedCaptor;
  @Captor private ArgumentCaptor<Subscriber<Explorer>>
      whenPreviousClickedCaptor;

  protected static final String URL = "url";
  protected static final String NAME = "name";
  protected static final String ANY_STRING = "any string";
  protected static final boolean SHOULD_SUCCEED = true;
  protected static final String MOCK_SERVICE_FAILED = "service failed";

  @Mock protected ImageInformationView mockView;
  @Mock protected PopUpConfirmationView mockPopUpConfirmationView;
  @Mock private PlaceController mockPlaceController;

  private ClientSideServiceDispatcher fakeDispatcher;
  private FakeImagesService fakeImagesService;
  private ImageInformationPresenter presenter;

  private Folder parent;
  private Thumbnail thumbnail;
  private Thumbnail nextThumbnail;
  private Thumbnail previousThumbnail;

  @Before
  public void createFixture() {
    GWTMockUtilities.disarm();

    previousThumbnail = new Thumbnail("http://127.0.0.2/previouspath/previousimage.jp2");
    nextThumbnail = new Thumbnail("http://127.0.0.1/nextpath/nextimage.jp2");
    thumbnail = new Thumbnail("http://127.0.0.0/path/image.jp2");
    parent = new Folder();
    parent.adopt(previousThumbnail);
    parent.adopt(thumbnail);
    parent.adopt(nextThumbnail);

    fakeDispatcher = new FakeDispatcher();
    fakeImagesService = new FakeImagesService(SHOULD_SUCCEED);
    fakeDispatcher.register(fakeImagesService);


    presenter = new ImageInformationPresenter(
        mockView, mockPopUpConfirmationView, fakeDispatcher,
        mockPlaceController);
  }

  @Test
  public void bind() {
    verify(mockView).whenCancelClickedNotify(whenCancelClickedCaptor.capture());
    verify(mockView).whenViewClickedNotify(whenViewClickedCaptor.capture());
    verify(mockView).whenNextClickedNotify(whenNextClickedCaptor.capture());
    verify(mockView).whenPreviousClickedNotify(whenPreviousClickedCaptor.capture());
  }

  @Test
  public void addQueryToUrl() {
    presenter.show(thumbnail, parent);
    assertThat(fakeImagesService.payload.getUrl(),
        equalTo("http://127.0.0.0:80/path/image.jp2?iScanXml"));
  }

  @Test
  public void displayImageInformation() {
    presenter.show(thumbnail, parent);
    verify(mockView).enablePrevious();
    verify(mockView).enableNext();
    verify(mockView).open();
    verify(mockView).showWith(any(ImageInformationDecorator.class),
        eq(thumbnail.getImageInformation()));
  }

  @Test
  public void popupFetchError() {
    fakeImagesService.shouldSucceed = !SHOULD_SUCCEED;
    presenter.show(thumbnail, parent);
    verify(mockView).close();
    verify(mockPopUpConfirmationView).showWith(MOCK_SERVICE_FAILED);
  }

  @Test
  public void cancel() {
    whenCancelClicked();
    verify(mockView).close();
  }

  @Test
  public void gotoImageWhenViewClicked() {
    presenter.show(thumbnail, parent);
    whenViewClicked();
    verify(mockView).close();
    verify(mockPlaceController).goTo(new Image(thumbnail));
  }

  @Test
  public void gotoNext() {
    presenter.show(thumbnail, parent);

    verify(mockView).enablePrevious();
    verify(mockView).showWith(
        any(ImageInformationDecorator.class), eq(thumbnail.getImageInformation()));

    whenNextClicked();

    assertThat(fakeImagesService.payload.getPlace(),
        equalTo((Explorer)nextThumbnail));
    verify(mockView, times(2)).enablePrevious();
    verify(mockView).showWith(
        any(ImageInformationDecorator.class), eq(nextThumbnail.getImageInformation()));
  }

  @Test
  public void gotoPrevious() {
    presenter.show(thumbnail, parent);

    verify(mockView).enableNext();
    verify(mockView).showWith(
        any(ImageInformationDecorator.class), eq(thumbnail.getImageInformation()));

    whenPreviousClicked();

    assertThat(fakeImagesService.payload.getPlace(),
        equalTo((Explorer)previousThumbnail));
    verify(mockView, times(2)).enableNext();
    verify(mockView).showWith(
        any(ImageInformationDecorator.class), eq(previousThumbnail.getImageInformation()));
  }

  private void whenCancelClicked() {
    bind();
    whenCancelClickedCaptor.getValue().notifyWith(null);
  }

  private void whenViewClicked() {
    bind();
    whenViewClickedCaptor.getValue().notifyWith(null);
  }

  private void whenNextClicked() {
    bind();
    whenNextClickedCaptor.getValue().notifyWith(thumbnail);
  }

  private void whenPreviousClicked() {
    bind();
    whenPreviousClickedCaptor.getValue().notifyWith(thumbnail);
  }

  class FakeDispatcher extends ClientSideServiceDispatcher {
    @Override
    public <P extends Payload<R>, R extends Response> void execute(P payload,
        AsyncCallback<R> callback) {
      super.execute(payload, callback);
    }
  }

  class FakeImagesService extends FetchImagesService {
    ImagesPayload payload;
    boolean shouldSucceed;

    FakeImagesService(boolean shouldSucceed) {
      this.shouldSucceed = shouldSucceed;
    }

    @Override
    public void execute(ImagesPayload payload,
        AsyncCallback<ImagesServiceResponse> callback) {
      this.payload = payload;
      if(shouldSucceed) {
        payload.getPlace().setImageInformation(mock(ImageInformation.class));
        callback.onSuccess(new ImagesServiceResponse(payload.getPlace()));
        return;
      }
      callback.onFailure(new RuntimeException(MOCK_SERVICE_FAILED));
    }
  }
}