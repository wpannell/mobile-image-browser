package com.ventana.gwt.mobilebrowser.client.presenters;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.server.registry.ClientSideServiceDispatcher;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

public class ANewlyCreatedEditPresenterNotValidatingOverHTTPShould
    extends AnEditPresenterShould {

  @Captor private ArgumentCaptor<Subscriber<Void>>
      whenValidateClickedCaptor;

  private ClientSideServiceDispatcher fakeFailingDispatcher;
  private FakeImagesService fakeFailingImagesService;

  @Override
  public void createFixture() {
    super.createFixture();

    fakeFailingDispatcher = new FakeDispatcher();
    fakeFailingImagesService = new FakeImagesService(!SHOULD_SUCCEED);
    fakeFailingDispatcher.register(fakeFailingImagesService);

    new PopUpEditPresenter(mockView,
        mockConfirmationView, fakeFailingDispatcher, mockExplorerView);
  }

  @Test
  public void bind() {
    verify(mockView).whenValidateClickedNotify(whenValidateClickedCaptor.capture());
  }

  @Test
  public void notValidateOverHTTP() {
    whenValidateClicked();
    verify(mockView).isBadPathHelpShowing(PopUpEditPresenter.YES);
    verify(mockView).isPathHelpShowing(PopUpEditPresenter.YES);

  }

  private void whenValidateClicked() {
    bind();
    whenValidateClickedCaptor.getValue().notifyWith(null);
  }
}
