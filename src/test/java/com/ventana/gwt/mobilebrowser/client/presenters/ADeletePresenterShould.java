package com.ventana.gwt.mobilebrowser.client.presenters;

import com.google.gwt.junit.GWTMockUtilities;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.RootFolder;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpConfirmationView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpDeleteView;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ADeletePresenterShould {
  @Captor private ArgumentCaptor<Subscriber<Void>> whenDeletedCaptor;
  @Captor private ArgumentCaptor<Subscriber<Void>> whenCanceledCaptor;

  @Mock protected PopUpDeleteView mockView;
  @Mock protected ExplorerView mockExplorerView;
  @Mock protected RootFolder mockFolder;
  @Mock protected Directory mockDirectory;
  @Mock protected RootFolder mockNewFolder;

  private PopUpDeletePresenter presenter;

  @Before
  public void createFixture() {
    GWTMockUtilities.disarm();
    presenter = new PopUpDeletePresenter(mockView, mockExplorerView);
  }

  @Test
  public void bind() {
    verify(mockView).whenConfirmed(whenDeletedCaptor.capture());
    verify(mockView).whenCanceled(whenCanceledCaptor.capture());
  }

  @Test
  public void cancel() {
    whenCanceled();
    verify(mockView).close();
  }

  @Test
  public void popUp() {
    presenter.popUpFor(mockDirectory, mockFolder);
    verify(mockView).popUpWith(PopUpDeleteView.DELETE_FOLDER,
        PopUpConfirmationView.ARE_YOU_SURE);
  }

  @Test
  public void delete() {
    popUp();
    whenDeleted();
    verify(mockFolder).removeMeFrom(mockDirectory);
    verify(mockDirectory).fetch();
    verify(mockDirectory).loadExplorerWithChildrenFor(mockExplorerView);
    verify(mockView).close();

  }

  private void whenCanceled() {
    bind();
    whenCanceledCaptor.getValue().notifyWith(null);
  }

  private void whenDeleted() {
    bind();
    whenDeletedCaptor.getValue().notifyWith(null);
  }
}