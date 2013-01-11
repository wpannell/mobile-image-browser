package com.ventana.gwt.mobilebrowser.client.presenters;

import com.google.inject.Inject;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.RootFolder;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpConfirmationView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpDeleteView;

public class PopUpDeletePresenter {
  private final PopUpDeleteView view;
  private Directory directory = Directory.EMPTY;
  private RootFolder folder = RootFolder.EMPTY;
  private final ExplorerView explorerView;

  @Inject
  public PopUpDeletePresenter(final PopUpDeleteView view,
      final ExplorerView explorerView) {
    this.view = view;
    this.explorerView = explorerView;
    bind();
  }

  public void popUpFor(final Directory directory, final RootFolder folder) {
    this.directory = directory;
    this.folder = folder;
    view.popUpWith(PopUpDeleteView.DELETE_FOLDER,
        PopUpConfirmationView.ARE_YOU_SURE);
  }

  private void bind() {
    view.whenConfirmed(new Subscriber<Void>() {
      @Override @SuppressWarnings("unused") //...unused event param...
      public void notifyWith(Void notification) {
        delete();
      }
    });
    view.whenCanceled(new Subscriber<Void>() {
      @Override @SuppressWarnings("unused") //...unused event param...
      public void notifyWith(Void notification) {
        close();
      }
    });
  }

  private void close() {
    view.close();
  }

  private void delete() {
    folder.removeMeFrom(directory);
    directory.fetch();
    explorerView.clearExplorerContainer();
    directory.loadExplorerWithChildrenFor(explorerView);
    close();
  }
}
