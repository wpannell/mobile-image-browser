package com.ventana.gwt.mobilebrowser.client.presenters;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.server.registry.ClientSideServiceDispatcher;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

public class ANewlyCreatedEditPresenterValidatingOverHTTPShould
    extends AnEditPresenterShould {

  @Captor private ArgumentCaptor<Subscriber<Void>>
      whenValidateClickedCaptor;

  protected ClientSideServiceDispatcher fakeSuccessfulDispatcher;
  protected FakeImagesService fakeSuccessfulImagesService;

  @Override
  public void createFixture() {
    super.createFixture();

    fakeSuccessfulDispatcher = new FakeDispatcher();
    fakeSuccessfulImagesService = new FakeImagesService(SHOULD_SUCCEED);
    fakeSuccessfulDispatcher.register(fakeSuccessfulImagesService);

    new PopUpEditPresenter(mockView, mockConfirmationView,
        fakeSuccessfulDispatcher, mockExplorerView);
  }

  @Test
  public void bind() {
    verify(mockView).whenValidateClickedNotify(whenValidateClickedCaptor.capture());
  }

  @Test
  public void validateOverHTTP() {
    given(mockFolder.getUrl()).willReturn("ANY_OTHER_URL");
    whenValidateClicked();
    verify(mockView).isBadPathHelpShowing(PopUpEditPresenter.NO);
    verify(mockView).isPathHelpShowing(PopUpEditPresenter.NO);
  }

  private void whenValidateClicked() {
    bind();
    whenValidateClickedCaptor.getValue().notifyWith(null);
  }
}
