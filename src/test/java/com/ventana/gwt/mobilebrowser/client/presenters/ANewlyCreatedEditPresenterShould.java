package com.ventana.gwt.mobilebrowser.client.presenters;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.server.dispatcher.AsyncDispatcher;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ANewlyCreatedEditPresenterShould extends AnEditPresenterShould {
  @Captor private ArgumentCaptor<Subscriber<Void>> whenValidateClickedCaptor;
  @Captor private ArgumentCaptor<Subscriber<Void>> whenCancelClickedCaptor;
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
    verify(mockView).whenValidateClickedNotify(whenValidateClickedCaptor.capture());
    verify(mockView).whenCancelClickedNotify(whenCancelClickedCaptor.capture());
  }

  @Test
  public void clearValidations() {
    popUpEditPresenter.show(mockDirectory, mockFolder);
    verify(mockView).clearValidations();
  }

  @Test
  public void notValidateEmptyName() {
    given(mockView.getName()).willReturn(EMPTY);

    given(mockView.getPath()).willReturn(ANY_STRING);
    given(mockView.getProtocol()).willReturn(ANY_STRING);

    popUpEditPresenter.show(mockDirectory, mockFolder);

    whenValidateClicked();
    verify(mockView, times(2)).clearValidations();
    verify(mockView).isEmptyNameHelpShowing(PopUpEditPresenter.YES);
    verify(mockView).isNameHelpShowing(PopUpEditPresenter.YES);
  }

  @Test
  public void notValidateDuplicateName() {
    given(mockView.getName()).willReturn(ANY_STRING);
    given(mockView.getProtocol()).willReturn(ANY_STRING);
    given(mockView.getPath()).willReturn(ANY_STRING);

    given(mockDirectory.isNameDuplicated(mockView.getName(), mockFolder)).
        willReturn(PopUpEditPresenter.YES);

    popUpEditPresenter.show(mockDirectory, mockFolder);

    whenValidateClicked();
    verify(mockView).isDuplicateNameHelpShowing(PopUpEditPresenter.YES);
    verify(mockView).isNameHelpShowing(PopUpEditPresenter.YES);
  }

  @Test
  public void notValidateDuplicateUrl() {
    given(mockDirectory.isUrlDuplicated(PRETTY_PROTOCOL + "://" + PATH, mockFolder)).
        willReturn(PopUpEditPresenter.YES);

    popUpEditPresenter.show(mockDirectory, mockFolder);

    whenValidateClicked();
    verify(mockView, times(2)).clearValidations();
    verify(mockView).isDuplicatePathHelpShowing(PopUpEditPresenter.YES);
    verify(mockView).isPathHelpShowing(PopUpEditPresenter.YES);
  }

  @Test
  public void notValidateDataUnchanged() {
    popUpEditPresenter.show(mockDirectory, mockFolder);
    whenValidateClicked();
    verify(mockView, times(2)).clearValidations();
    verify(mockView).isDataUnchangedHelpShowing(PopUpEditPresenter.YES);
  }

  @Test
  public void validateProtocolChanged() {
    given(mockView.getProtocol()).willReturn(PRETTY_SECURE_PROTOCOL);
    popUpEditPresenter.show(mockDirectory, mockFolder);

    whenValidateClicked();
    verify(mockView, times(2)).clearValidations();
    verify(mockView).isDataUnchangedHelpShowing(PopUpEditPresenter.NO);
  }

  @Test
  public void validateNameChanged() {
    given(mockView.getName()).willReturn("another name");
    popUpEditPresenter.show(mockDirectory, mockFolder);

    whenValidateClicked();
    verify(mockView, times(2)).clearValidations();
    verify(mockView).isDataUnchangedHelpShowing(PopUpEditPresenter.NO);
  }

  @Test
  public void validateUrlChanged() {
    given(mockView.getPath()).willReturn("another url");
    popUpEditPresenter.show(mockDirectory, mockFolder);

    whenValidateClicked();
    verify(mockView, times(2)).clearValidations();
    verify(mockView).isDataUnchangedHelpShowing(PopUpEditPresenter.NO);
  }

  @Test
  public void validateName() {
    given(mockView.getName()).willReturn(ANY_STRING);
    popUpEditPresenter.show(mockDirectory, mockFolder);

    whenValidateClicked();
    verify(mockView, times(2)).clearValidations();
    verify(mockView).isEmptyNameHelpShowing(PopUpEditPresenter.NO);
    verify(mockView).isDuplicateNameHelpShowing(PopUpEditPresenter.NO);
    verify(mockView).isNameHelpShowing(PopUpEditPresenter.NO);
  }

  @Test
  public void validateUrl() {
    given(mockView.getPath()).willReturn(ANY_STRING);
    popUpEditPresenter.show(mockDirectory, mockFolder);

    whenValidateClicked();
    verify(mockView, times(2)).clearValidations();
    verify(mockView).isEmptyPathHelpShowing(PopUpEditPresenter.NO);
    verify(mockView).isDuplicatePathHelpShowing(PopUpEditPresenter.NO);
  }

  @Test
  public void cancel() {
    popUpEditPresenter.show(mockDirectory, mockFolder);
    whenCancelClicked();
    verify(mockView).close();
  }

  private void whenCancelClicked() {
    bind();
    whenCancelClickedCaptor.getValue().notifyWith(null);
  }

  private void whenValidateClicked() {
    bind();
    whenValidateClickedCaptor.getValue().notifyWith(null);
  }
}
