package com.ventana.gwt.mobilebrowser.client.activities;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Folder;
import com.ventana.gwt.mobilebrowser.client.places.Image;
import com.ventana.gwt.mobilebrowser.client.places.RootFolder;
import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;
import com.ventana.gwt.mobilebrowser.client.presenters.ImageInformationPresenter;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.client.views.ToolBarView;
import com.ventana.gwt.mobilebrowser.server.payloads.ImagesPayload;
import com.ventana.gwt.mobilebrowser.server.payloads.Payload;
import com.ventana.gwt.mobilebrowser.server.registry.ClientSideServiceDispatcher;
import com.ventana.gwt.mobilebrowser.server.responses.GetExplorerServiceResponse;
import com.ventana.gwt.mobilebrowser.server.responses.ImagesServiceResponse;
import com.ventana.gwt.mobilebrowser.server.responses.Response;
import com.ventana.gwt.mobilebrowser.server.services.FetchImagesService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ANewlyCreatedExplorerActivityShould {
  private static final boolean SHOULD_SUCCEED = true;
  private static final String MOCK_SERVICE_FAILED = "mock service failed";

  @Captor private ArgumentCaptor<Subscriber<Explorer>>
      whenNavigatorFolderClickedCaptor;
  @Captor private ArgumentCaptor<Subscriber<Explorer>>
      whenExplorerThumbnailClickedCaptor;
  @Captor private ArgumentCaptor<Subscriber<Explorer>>
      whenExplorerFolderClickedCaptor;
  @Captor private ArgumentCaptor<Subscriber<Thumbnail>>
      whenFileInformationClickedCaptor;
  @Captor private ArgumentCaptor<Subscriber<Void>>
      whenRightToLeftSwipedCaptor;

  @Mock private EventBus mockEventBus;
  @Mock private AcceptsOneWidget mockContainer;
  @Mock private ExplorerView mockView;
  @Mock private ToolBarView mockToolBar;
  @Mock private Folder mockFolder;
  @Mock private RootFolder parentFolder;
  @Mock private Thumbnail mockThumbnail;
  @Mock private PlaceController mockPlaceController;
  @Mock private Directory mockDirectory;
  @Mock private ImageInformationPresenter mockImageInformationPresenter;

  private Explorer aFetchedFolder;
  private ClientSideServiceDispatcher fakeDispatcher;
  private FakeImagesService fakeImagesService;

  private ExplorerActivity activity;
  private GetExplorerServiceResponse fakeCallback;

  @Before
  public void createFixture() {
    GWTMockUtilities.disarm();

    aFetchedFolder = new Folder();
    aFetchedFolder.adopt(mockFolder);
    aFetchedFolder.adopt(mockThumbnail);

    fakeDispatcher = new FakeDispatcher();
    fakeImagesService = new FakeImagesService(SHOULD_SUCCEED);
    fakeDispatcher.register(fakeImagesService);

    fakeCallback = new GetExplorerServiceResponse(mockView) {
      @Override protected void initializeLazilyLoadedImages() {}
    };

    given(parentFolder.isRoot()).willReturn(true);

    activity = new ExplorerActivity(mockView, mockToolBar,
        mockImageInformationPresenter, fakeDispatcher,
        mockPlaceController, mockDirectory, fakeCallback, parentFolder);
  }

  @Test
  public void bind() {
    activity.start(mockContainer, mockEventBus);
    verify(mockView).whenNavigatorClickedNotify(
        whenNavigatorFolderClickedCaptor.capture());
    verify(mockView).whenThumbnailClickedNotify(
        whenExplorerThumbnailClickedCaptor.capture());
    verify(mockView).whenFolderClickedNotify(
        whenExplorerFolderClickedCaptor.capture());
    verify(mockView).whenThumbnailInformationClickedNotify(
        whenFileInformationClickedCaptor.capture());
    verify(mockView).whenRightToLeftSwipedNotify(
        whenRightToLeftSwipedCaptor.capture());
  }

  @Test
  public void configureViewOnStart() {
    activity.start(mockContainer, mockEventBus);
    verify(mockView).addMeToThis(mockContainer);
  }

  @Test
  public void configureContainersOnStart() {
    activity.start(mockContainer, mockEventBus);
    verify(mockView).configureExplorerContainer();
    verify(mockView).configureNavigatorContainer();
  }

  @Test
  public void clearContainersOnStop() {
    activity.onStop();
    verify(mockView).clearExplorerContainer();
    verify(mockView).clearNavigatorContainer();
  }

  @Test
  public void delegateTouchesOnStart() {
    activity.start(mockContainer, mockEventBus);
    verify(mockView).delegateTouches();
  }

  @Test
  public void undelegateTouchesOnStop() {
    activity.onStop();
    verify(mockView).undelegateTouches();
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
  public void showBackButton() {
    activity.start(mockContainer, mockEventBus);
    verify(mockToolBar).isBackButtonShowing(ToolBarView.YES);
  }

  @Test
  public void hideAddButton() {
    activity.start(mockContainer, mockEventBus);
    verify(mockToolBar).isAddButtonShowing(ToolBarView.NO);
  }

  @Test
  public void loadNavigatorWithDirectoryChildren() {
    given(parentFolder.isRoot()).willReturn(true);
    given(parentFolder.getSelectedIndex()).willReturn(10);

    activity.start(mockContainer, mockEventBus);
    verify(mockDirectory).loadNavigatorWithChildrenFor(mockView);
    verify(mockView).selectNavigatorFolder(10);
  }

  @Test
  public void loadNavigatorWithSiblings() {
    given(parentFolder.isRoot()).willReturn(false);
    given(parentFolder.getParent()).willReturn(aFetchedFolder);
    activity.start(mockContainer, mockEventBus);
    verify(mockFolder).loadNavigatorFor(mockView);
  }

  @Test
  public void loadExplorer() {
    activity.start(mockContainer, mockEventBus);
    verify(mockFolder).loadExplorerFor(mockView);
    verify(mockThumbnail).loadExplorerFor(mockView);
  }

  @Test
  public void sendPayloadToService() {
    activity.start(mockContainer, mockEventBus);
    assertThat(fakeImagesService.payload.getUrl(),
        equalTo(parentFolder.getHistoryToken()));

    assertThat(fakeImagesService.payload.getPlace(),
        equalTo((Explorer)parentFolder));
  }

  @Test
  public void showErrorWhenServiceFails() {
    fakeImagesService.shouldSucceed = !SHOULD_SUCCEED;
    activity.start(mockContainer, mockEventBus);
    verify(mockView).popUp(MOCK_SERVICE_FAILED);
  }

  @Test
  public void popupImageInformation() {
    whenFileInformationClickedWith(mockThumbnail);
    verify(mockImageInformationPresenter).show(mockThumbnail, parentFolder);
  }

  @Test
  public void gotoFolderWhenNavigatorFolderClicked() {
    whenNavigatorFolderClickedWith(mockFolder);
    verify(mockPlaceController).goTo(mockFolder);
  }

  @Test
  public void gotoImageWhenExplorerThumbnailClicked() {
    whenExploprerThumbnailClickedWith(mockThumbnail);
    verify(mockPlaceController).goTo(new Image(mockThumbnail));
  }

  @Test
  public void recurseToFolderWhenExplorerFolderClicked() {
    whenExploprerFolderClickedWith(mockFolder);
    verify(mockPlaceController).goTo(mockFolder);
  }

  @Test
  public void goBackWhenRightToLeftSwiped() {
    whenRightToLeftSwiped();
    verify(mockToolBar).goBrowserBack();
  }

  private void whenNavigatorFolderClickedWith(Explorer folder) {
    bind();
    whenNavigatorFolderClickedCaptor.getValue().notifyWith(folder);
  }

  private void whenExploprerThumbnailClickedWith(Explorer thumbnail) {
    bind();
    whenExplorerThumbnailClickedCaptor.getValue().notifyWith(thumbnail);
  }

  private void whenExploprerFolderClickedWith(Explorer folder) {
    bind();
    whenExplorerFolderClickedCaptor.getValue().notifyWith(folder);
  }

  private void whenFileInformationClickedWith(Thumbnail thumbnail) {
    bind();
    whenFileInformationClickedCaptor.getValue().notifyWith(thumbnail);
  }

  private void whenRightToLeftSwiped() {
    bind();
    whenRightToLeftSwipedCaptor.getValue().notifyWith(null);
  }

  private class FakeDispatcher extends ClientSideServiceDispatcher {
    @Override
    public <P extends Payload<R>, R extends Response> void execute(P payload,
        AsyncCallback<R> callback) {
      super.execute(payload, callback);
    }
  }

  private class FakeImagesService extends FetchImagesService {
    // fake designed to return aFetchedFolder upon passing arbitrary payloads...
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
        callback.onSuccess(new ImagesServiceResponse(aFetchedFolder));
        return;
      }
      callback.onFailure(new RuntimeException(MOCK_SERVICE_FAILED));
    }
  }
}
