package com.ventana.gwt.mobilebrowser.client.presenters;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.RootFolder;
import com.ventana.gwt.mobilebrowser.client.views.PopUpConfirmationView;
import com.ventana.gwt.mobilebrowser.server.dispatcher.AsyncDispatcher;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

public class ANewlyCreatedEditPresenterSavingShould
    extends AnEditPresenterShould {
  @Captor private ArgumentCaptor<Subscriber<Void>> whenSaveClickedCaptor;
  @Captor private ArgumentCaptor<Subscriber<Void>> whenSaveConfirmedCaptor;

  @Mock private AsyncDispatcher mockDispatcher;
  private PopUpEditPresenter popUpEditPresenter;

  @Override
  public void createFixture() {
    super.createFixture();

    popUpEditPresenter = new PopUpEditPresenter(mockView,
        mockConfirmationView, mockDispatcher, mockExplorerView);
  }

  @Test
  public void bind() {
    verify(mockView).whenSaveClickedNotify(whenSaveClickedCaptor.capture());
    verify(mockConfirmationView).whenConfiremdNotify(whenSaveConfirmedCaptor.capture());
  }

  @Test
  public void requestEdit() {
    popUpEditPresenter.show(mockDirectory, mockFolder);
    verify(mockView).openWith(
        mockFolder.getName(), mockFolder.getPrettyProtocol(),
        mockFolder.getFullPath());
  }

  @Test
  public void saveWhenDataValid() {
    requestEdit();

    given(mockView.isDataValid()).willReturn(true);
    whenSaveClicked();
    verify(mockView).isDataValid();

    verifyStoreToRepository();
  }

  @Test
  public void promptForConfirmationWhenDataInvalid() {
    requestEdit();

    given(mockView.isDataValid()).willReturn(false);
    whenSaveClicked();
    verify(mockView).isDataValid();

    verifySavePopUpConfirmationShown();
    verify(mockDirectory, times(0)).
        store(any(RootFolder.class));
    verify(mockEditOkSubscriber, times(0)).notifyWith(null);
  }

  @Test
  public void saveWhenInvalidDataConfirmed() {
    promptForConfirmationWhenDataInvalid();
    whenSaveConfirmed();
    verifyStoreToRepository();
  }

  private void verifyStoreToRepository() {
    verify(mockDirectory).remove(mockFolder);
    verify(mockFolder).createWith(mockView.getName(), mockView.getProtocol() + "://"
        + mockView.getPath());
    verify(mockDirectory).store(mockNewFolder);
    verify(mockDirectory).fetch();
    verify(mockExplorerView).clearExplorerContainer();
    verify(mockDirectory).loadExplorerWithChildrenFor(mockExplorerView);
    verify(mockView).close();
  }

  private void verifySavePopUpConfirmationShown() {
    verify(mockConfirmationView).popUpWith(
        eq(PopUpConfirmationView.SAVE_CONFIRMATION_TITLE),
        eq(PopUpConfirmationView.NOT_ALL_INPUTS_ARE_VALID_SAVE_ANYWAY));
  }

  private void whenSaveClicked() {
    bind();
    whenSaveClickedCaptor.getValue().notifyWith(null);
  }

  private void whenSaveConfirmed() {
    bind();
    whenSaveConfirmedCaptor.getValue().notifyWith(null);
  }
}
