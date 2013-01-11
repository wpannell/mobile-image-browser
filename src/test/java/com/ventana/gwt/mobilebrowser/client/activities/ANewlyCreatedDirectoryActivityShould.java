package com.ventana.gwt.mobilebrowser.client.activities;

import com.google.gwt.junit.GWTMockUtilities;

import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.RootFolder;
import com.ventana.gwt.mobilebrowser.client.views.ToolBarView;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

public class ANewlyCreatedDirectoryActivityShould  extends ADirectoryActivityShould {
  @Mock private Directory mockDirectory;

  private List<RootFolder> fetched;
  @Override
  public void create() {
    GWTMockUtilities.disarm();
    fetched = new ArrayList<RootFolder>();
    directory = new Directory(mockRepository);
    activity = new DirectoryActivity(mockView, mockToolBar, mockDeletePresenter,
        mockEditPresenter, mockPlaceController, mockDirectory);
  }

  @Test
  public void configureViewOnStart() {
    activity.start(mockContainer, mockEventBus);
    verify(mockView).addMeToThis(mockContainer);
  }

  @Test
  public void clearContainersOnStop() {
    activity.onStop();
    verify(mockView).clearExplorerContainer();
    verify(mockView).clearNavigatorContainer();
  }


  @Test
  public void configureContainersOnStart() {
    activity.start(mockContainer, mockEventBus);
    verify(mockView).configureExplorerContainer();
    verify(mockView).configureNavigatorContainer();
  }

  @Test
  public void showToolBar() {
    activity.start(mockContainer, mockEventBus);
    verify(mockToolBar).isShowing(ToolBarView.YES);
  }

  @Test
  public void hideInformationText() {
    activity.start(mockContainer, mockEventBus);
    verify(mockToolBar).isInformationTextShowing(ToolBarView.NO);
  }

  @Test
  public void hideBackButton() {
    activity.start(mockContainer, mockEventBus);
    verify(mockToolBar).isBackButtonShowing(ToolBarView.NO);
  }

  @Test
  public void showAddButton() {
    activity.start(mockContainer, mockEventBus);
    verify(mockToolBar).isAddButtonShowing(ToolBarView.YES);
  }

  @Test
  public void loadNavigator() {
    activity.start(mockContainer, mockEventBus);
    verify(mockDirectory).loadNavigatorFor(mockView);
  }

  @Test
  public void loadExplorer() {
    fetched.add(mockFolder1);
    fetched.add(mockFolder2);
    given(mockRepository.fetch()).willReturn(fetched);

    DirectoryActivity activity = new DirectoryActivity(mockView, mockToolBar,
        mockDeletePresenter, mockEditPresenter, mockPlaceController,
        directory);
    activity.start(mockContainer, mockEventBus);
    verify(mockFolder1).loadExplorerFor(mockView);
    verify(mockFolder2).loadExplorerFor(mockView);
  }
}