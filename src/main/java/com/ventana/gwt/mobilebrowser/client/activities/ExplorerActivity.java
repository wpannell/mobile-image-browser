package com.ventana.gwt.mobilebrowser.client.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Image;
import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;
import com.ventana.gwt.mobilebrowser.client.presenters.ImageInformationPresenter;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.client.views.ToolBarView;
import com.ventana.gwt.mobilebrowser.server.dispatcher.AsyncDispatcher;
import com.ventana.gwt.mobilebrowser.server.payloads.ImagesPayload;
import com.ventana.gwt.mobilebrowser.server.responses.GetExplorerServiceResponse;
import com.ventana.gwt.mobilebrowser.server.responses.GetNavigatorSiblingsServiceResponse;

public class ExplorerActivity extends AbstractActivity {
  private GetExplorerServiceResponse callback;
  private final Directory directory;
  private final AsyncDispatcher dispatcher;
  private final ImageInformationPresenter imageInformationPresenter;
  private final Explorer parent;
  private final PlaceController placeController;
  private final ToolBarView toolBar;
  private final ExplorerView view;

  @Inject
  public ExplorerActivity(final ExplorerView view, final ToolBarView toolBar,
      final ImageInformationPresenter imageInformationPresenter,
      final AsyncDispatcher dispatcher, final PlaceController placeController,
      final Directory directory, GetExplorerServiceResponse callback,
      @Assisted final Explorer parent) {
    this.view = view;
    this.toolBar = toolBar;
    this.imageInformationPresenter = imageInformationPresenter;
    this.dispatcher = dispatcher;
    this.placeController = placeController;
    this.directory = directory;
    this.callback = callback;
    this.parent = parent;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;

    if (!(obj instanceof ExplorerActivity)) return false;
    final ExplorerActivity other = (ExplorerActivity) obj;
    if (parent == null) {
      if (other.parent != null) return false;
    }
    else if (!parent.equals(other.parent)) return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (parent == null ? 0 : parent.hashCode());
    return result;
  }

  @Override
  public void start(final AcceptsOneWidget container,
      @SuppressWarnings("unused") final EventBus eventBus) {
    bind();
    startWith(container);
    delegateTouches();
    configureContainers();
    updateViewWith(parent);
    configureViewState();
  }

  @Override
  public void onStop() {
    undelegateTouches();
    clearContainers();
  }

  private void bind() {
    view.whenFolderClickedNotify(new Subscriber<Explorer>() {
      @Override public void notifyWith(final Explorer folder) {
        placeController.goTo(folder);
      }
    });
    view.whenThumbnailClickedNotify(new Subscriber<Explorer>() {
      @Override public void notifyWith(final Explorer file) {
        placeController.goTo(new Image(file));
      }
    });
    view.whenNavigatorClickedNotify(new Subscriber<Explorer>() {
      @Override public void notifyWith(final Explorer navigatorFolder) {
        placeController.goTo(navigatorFolder);
      }
    });
    view.whenThumbnailInformationClickedNotify(new Subscriber<Thumbnail>() {
      @Override public void notifyWith(final Thumbnail thumbnail) {
        popUpImageInformationFor(thumbnail);
      }
    });
    view.whenRightToLeftSwipedNotify(new Subscriber<Void>() {
      @Override @SuppressWarnings("unused") // ...unused void notification...
      public void notifyWith(final Void notification) {
        toolBar.goBrowserBack();
      }
    });
  }

  private void clearContainers() {
    view.clearExplorerContainer();
    view.clearNavigatorContainer();
  }

  private void configureContainers() {
    view.configureExplorerContainer();
    view.configureNavigatorContainer();
  }

  private void configureViewState() {
    toolBar.isShowing(ToolBarView.YES);
    toolBar.isBackButtonShowing(ToolBarView.YES);
    toolBar.isAddButtonShowing(ToolBarView.NO);
    toolBar.isInformationTextShowing(ToolBarView.NO);
  }

  private void delegateTouches() {
    view.delegateTouches();
  }

  private void loadExplorerWithDetailsFrom(final Explorer folder) {
    view.clearExplorerContainer();
    dispatcher.execute(
        new ImagesPayload(folder), callback);
  }

  private void loadNavigatorWithSiblingsFor(final Explorer folder) {
    view.clearNavigatorContainer();
    if (folder.isRoot()) {
      directory.fetch();
      directory.loadNavigatorWithChildrenFor(view);
      folder.setSelectedIndex(directory.getChildIndexOf(folder));
      view.selectNavigatorFolder(folder.getSelectedIndex());
      return;
    }
    dispatcher.execute(
        new ImagesPayload(folder.getParent(), folder),
        new GetNavigatorSiblingsServiceResponse(view));
  }

  private void popUpImageInformationFor(final Thumbnail thumbnail) {
    imageInformationPresenter.show(thumbnail, parent);
  }

  private void startWith(final AcceptsOneWidget container) {
    view.addMeToThis(container);
  }

  private void undelegateTouches() {
    view.undelegateTouches();
  }

  private void updateViewWith(final Explorer folder) {
    loadNavigatorWithSiblingsFor(folder);
    loadExplorerWithDetailsFrom(folder);
  }
}
