package com.ventana.gwt.mobilebrowser.client.ui;

import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.gwtplatform.tester.MockingBinder;
import com.ventana.gwt.mobilebrowser.client.containers.ExplorerContainer;
import com.ventana.gwt.mobilebrowser.client.containers.NavigatorContainer;
import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Folder;
import com.ventana.gwt.mobilebrowser.client.places.RootFolder;
import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;
import com.ventana.gwt.mobilebrowser.client.testdoubles.MockitoMockFactory;
import com.ventana.gwt.mobilebrowser.client.ui.ExplorerUi.ExplorerUiBinder;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JukitoRunner.class)
public class AnExplorerUiShould {
  private ExplorerUi ui;
  private ExplorerView view;

  @Before
  @SuppressWarnings("unused") //fake uibinder not used outside of di...
  public void createFixtureWith(ExplorerUiBinder fakeExplorerUiBinder,
      ExplorerUi explorerUi) {
    this.ui = explorerUi;
    view = explorerUi;
  }

  @Test
  public void addMeToContainerPassedInByActivityManager(
      AcceptsOneWidget containerPassedByActivityManager) {
    view.addMeToThis(containerPassedByActivityManager);
    verify(containerPassedByActivityManager).setWidget(ui);
  }

  @Test
  public void configureContainersOnStart() {
    view.configureExplorerContainer();
    view.configureNavigatorContainer();
    verifyContainersAreConfigured();
  }

  @Test
  public void delegateTouchesOnStart() {
    view.delegateTouches();
    verify(ui.touchDelgator).delegateTo(ui);
  }

  @Test
  public void undelegateTouchesOnStart() {
    view.undelegateTouches();
    verify(ui.touchDelgator).resetWith(ui);
  }

  @Test
  public void clearExplorerContainer() {
    view.clearExplorerContainer();
    verify(ui.explorerContainer).clear();
  }

  @Test
  public void clearNavigatorContainer() {
    view.clearNavigatorContainer();
    verify(ui.navigatorContainer).clear();
  }

  @Test
  public void knowNumberOfFoldersOrFilesInExplorer() {
    view.numberOfFoldersOrFilesInExplorer();
    verify(ui.explorerContainer).size();
  }

  @Test
  public void loadExplorerWith(Thumbnail thumbnail,
      Subscriber<Explorer> thumbnailClickedSubscriber,
      Subscriber<Thumbnail> thumbnailInformationSubscriber) {
    view.whenThumbnailClickedNotify(thumbnailClickedSubscriber);
    view.whenThumbnailInformationClickedNotify(thumbnailInformationSubscriber);
    view.loadExplorerWith(thumbnail);

    verify(ui.explorerContainer).append(
        thumbnail, thumbnailClickedSubscriber, thumbnailInformationSubscriber);
  }

  @Test
  public void loadExplorerWith(Folder folder,
      Subscriber<Explorer> folderClickedSubscriber) {
    view.whenFolderClickedNotify(folderClickedSubscriber);
    view.loadExplorerWith(folder);

    verify(ui.explorerContainer).append(folder, folderClickedSubscriber);
  }

  @Test
  public void loadExplorerWith(RootFolder folder,
      Subscriber<Explorer> folderClickedSubscriber,
      Subscriber<Explorer> browseClickedSubscriber,
      Subscriber<Explorer> deleteClickedSubscriber,
      Subscriber<Explorer> editClickedSubscriber) {
    view.whenFolderClickedNotify(folderClickedSubscriber);
    view.whenBrowseClickedNotify(deleteClickedSubscriber);
    view.whenDeleteClickedNotify(deleteClickedSubscriber);
    view.whenEditClickedNotify(editClickedSubscriber);
    view.loadExplorerWith(folder);

    verify(ui.explorerContainer).append(folder, browseClickedSubscriber,
        deleteClickedSubscriber, editClickedSubscriber);
  }

  @Test
  public void loadNavigatorWith(Folder folder,
      Subscriber<Explorer> navigatorClickedSubscriber) {
    view.whenNavigatorClickedNotify(navigatorClickedSubscriber);
    view.loadNavigatorWith(folder);

    verify(ui.navigatorContainer).append(folder, navigatorClickedSubscriber);
  }

  @Test
  public void loadNavigatorWith() {
    Directory directory = mock(Directory.class);
    view.loadNavigatorWith(directory);
    verify(ui.navigatorContainer).append(directory);
  }

  @Test
  public void selectNavigatorFolder(int i) {
    view.selectNavigatorFolder(i);
    verify(ui.navigatorContainer).select(i);
  }

  @SuppressWarnings("static-access")
  private void verifyContainersAreConfigured() {
    verify(ui.navigatorContainer).setSelector(view.NAVIGATOR_SELECTOR);
    verify(ui.navigatorContainer).setPrefix(view.NAVIGATOR_PREFIX);
    verify(ui.navigatorContainer).setSuffix(view.NAVIGATOR_SUFFIX);
    verify(ui.explorerContainer).setSelector(view.EXPLORER_SELECTOR);
    verify(ui.explorerContainer).setPrefix(view.EXPLORER_PREFIX);
    verify(ui.explorerContainer).setSuffix(view.EXPLORER_SUFFIX);
  }

  @AfterClass
  public static void cleanup() {
    GWTMockUtilities.restore();
  }

  public static class Module extends JukitoModule {
    public static class FakeExplorerUiBinder
        extends MockingBinder<Widget, ExplorerUi> implements ExplorerUiBinder {
      @Inject
      public FakeExplorerUiBinder(MockitoMockFactory mockitoMockFactory) {
        super(Widget.class, mockitoMockFactory);
      }
    }

    @Override
    protected void configureTest() {
      GWTMockUtilities.disarm();
      forceMock(NavigatorContainer.class);
      forceMock(ExplorerContainer.class);
      bind(ExplorerUiBinder.class).to(FakeExplorerUiBinder.class);
    }
  }
}
