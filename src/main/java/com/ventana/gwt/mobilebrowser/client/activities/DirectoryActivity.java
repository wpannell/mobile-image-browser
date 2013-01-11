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
import com.ventana.gwt.mobilebrowser.client.places.RootFolder;
import com.ventana.gwt.mobilebrowser.client.presenters.PopUpDeletePresenter;
import com.ventana.gwt.mobilebrowser.client.presenters.PopUpEditPresenter;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.client.views.ToolBarView;

public class DirectoryActivity extends AbstractActivity {
  private final Directory directory;
  private final PlaceController placeController;
  private final PopUpDeletePresenter popUpDeletePresenter;
  private final PopUpEditPresenter popUpEditPresenter;

  private final ToolBarView toolbar;
  private final ExplorerView view;

  @Inject
  public DirectoryActivity(final ExplorerView view, final ToolBarView toolbar,
      final PopUpDeletePresenter popUpDeletePresenter,
      final PopUpEditPresenter popUpEditPresenterFactory,
      final PlaceController placeController, @Assisted final Directory directory) {
    this.view = view;
    this.toolbar = toolbar;
    this.popUpDeletePresenter = popUpDeletePresenter;
    this.popUpEditPresenter = popUpEditPresenterFactory;
    this.placeController = placeController;
    this.directory = directory;
    fetch();
    bind();
  }

  @Override
  public void start(final AcceptsOneWidget container,
      @SuppressWarnings("unused") final EventBus eventBus) {
    startWith(container);
    configureContainers();
    loadViewWith(directory);
    configureViewState();
  }

  @Override
  public void onStop() {
    clearContainers();
  }

  private void bind() {
    toolbar.whenAddButtonClickedNotify(new Subscriber<Void>() {
      @Override @SuppressWarnings("unused") // ...unused event parameter...
      public void notifyWith(final Void notification) {
        popUpEditPresenter.show(directory, RootFolder.EMPTY);
      };
    });
    view.whenBrowseClickedNotify(new Subscriber<Explorer>() {
      @Override
      public void notifyWith(final Explorer rootFolder) {
        placeController.goTo(rootFolder);
      }
    });
    view.whenDeleteClickedNotify(new Subscriber<Explorer>() {
      @Override
      public void notifyWith(final Explorer folder) {
        popUpDeletePresenter.popUpFor(directory, folder.asRootFolder());
      }
    });
    view.whenEditClickedNotify(new Subscriber<Explorer>() {
      @Override
      public void notifyWith(final Explorer rootFolder) {
        popUpEditPresenter.show(
            directory, rootFolder.asRootFolder());
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
    toolbar.isShowing(ToolBarView.YES);
    toolbar.isBackButtonShowing(ToolBarView.NO);
    toolbar.isAddButtonShowing(ToolBarView.YES);
    toolbar.isInformationTextShowing(ToolBarView.NO);
  }

  private void fetch() {
    directory.fetch();
  }

  private void loadExplorerWithChildrenOf(final Directory directory) {
    view.clearExplorerContainer();
    for (int i = 0; i < directory.getChildenSize(); i++) {
      final RootFolder child = directory.getChild(i);
      child.setSelectedIndex(i);
      child.loadExplorerFor(view);
    }
  }

  private void loadNavigatorWith(final Directory directory) {
    view.clearNavigatorContainer();
    directory.loadNavigatorFor(view);
    view.selectNavigatorFolder(0);
  }

  private void loadViewWith(final Directory directory) {
    loadNavigatorWith(directory);
    loadExplorerWithChildrenOf(directory);
  }

  private void startWith(final AcceptsOneWidget container) {
    view.addMeToThis(container);
  }
}
