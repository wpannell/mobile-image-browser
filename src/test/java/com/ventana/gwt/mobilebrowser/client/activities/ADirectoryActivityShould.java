package com.ventana.gwt.mobilebrowser.client.activities;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.RootFolder;
import com.ventana.gwt.mobilebrowser.client.presenters.PopUpDeletePresenter;
import com.ventana.gwt.mobilebrowser.client.presenters.PopUpEditPresenter;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.client.views.ToolBarView;
import com.ventana.gwt.mobilebrowser.server.repositories.Repository;

import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ADirectoryActivityShould {
  @Mock protected ExplorerView mockView;
  @Mock protected ToolBarView mockToolBar;
  @Mock protected PopUpDeletePresenter mockDeletePresenter;
  @Mock protected PopUpEditPresenter mockEditPresenter;
  @Mock protected EventBus mockEventBus;
  @Mock protected PlaceController mockPlaceController;
  @Mock protected Repository mockRepository;

  @Mock protected AcceptsOneWidget mockContainer;
  @Mock protected RootFolder mockFolder1;
  @Mock protected RootFolder mockFolder2;

  @Mock protected RootFolder mockFolder3;

  protected Directory directory;
  protected DirectoryActivity activity;

  public ADirectoryActivityShould() {
    super();
  }

  @Before
  public void create() {
    GWTMockUtilities.disarm();
    given(mockFolder1.asRootFolder()).willReturn(mockFolder1);
    directory = new Directory(mockRepository);

    activity = new DirectoryActivity(mockView, mockToolBar, mockDeletePresenter,
        mockEditPresenter, mockPlaceController, directory);
  }
}