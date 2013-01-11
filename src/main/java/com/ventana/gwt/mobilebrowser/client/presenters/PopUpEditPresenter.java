package com.ventana.gwt.mobilebrowser.client.presenters;

import com.google.inject.Inject;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.RootFolder;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpConfirmationView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpEditView;
import com.ventana.gwt.mobilebrowser.server.dispatcher.AsyncDispatcher;
import com.ventana.gwt.mobilebrowser.server.payloads.ImagesPayload;
import com.ventana.gwt.mobilebrowser.server.responses.GetExplorerValidationServiceResponse;

public class PopUpEditPresenter {
  public static final boolean NO = false;
  public static final boolean YES = true;
  private final PopUpConfirmationView confirmationView;
  private Directory directory = Directory.EMPTY;
  private final AsyncDispatcher dispatcher;
  private RootFolder folder = RootFolder.EMPTY;
  private final PopUpEditView view;
  private final ExplorerView explorerView;

  @Inject
  public PopUpEditPresenter(final PopUpEditView view,
      final PopUpConfirmationView confirmationView,
      final AsyncDispatcher dispatcher, final ExplorerView explorerView) {
    this.view = view;
    this.confirmationView = confirmationView;
    this.dispatcher = dispatcher;
    this.explorerView = explorerView;
    bind();
  }

  public void show(Directory directory, RootFolder rootFolder) {
    this.directory = directory;
    folder = rootFolder;
    clearValidations();
    view.openWith(folder.getName(), folder.getPrettyProtocol(),
        folder.getFullPath());
  }

  private boolean areEntriesUnchanged() {
    return isNameUnchanged() && isUrlUnchanged() && isProtocolUnchanged();
  }

  private void bind() {
    confirmationView.whenConfiremdNotify(new Subscriber<Void>() {
      @Override @SuppressWarnings("unused")  // ... unused event parameter ...
      public void notifyWith(final Void notification) {
        store();
      }
    });
    view.whenCancelClickedNotify(new Subscriber<Void>() {
      @Override @SuppressWarnings("unused")  // ... unused event parameter ...
      public void notifyWith(final Void notification) {
        close();
      }
    });
    view.whenValidateClickedNotify(new Subscriber<Void>() {
      @Override @SuppressWarnings("unused")  // ... unused event parameter ...
      public void notifyWith(final Void notification) {
        showValidation();
      }
    });
    view.whenSaveClickedNotify(new Subscriber<Void>() {
      @Override @SuppressWarnings("unused")  // ... unused event parameter ...
      public void notifyWith(final Void notification) {
        save();
      }
    });
  }

  private void clearValidations() {
    view.clearValidations();
  }

  private void close() {
    view.close();
  }

  private boolean isNameDuplicated() {
    return directory.isNameDuplicated(view.getName(), folder);
  }

  private boolean isNameEmpty() {
    return view.getName().equals("");
  }

  private boolean isNameUnchanged() {
    return view.getName().equalsIgnoreCase(folder.getName());
  }

  private boolean isPathDuplicated() {
    return directory.isUrlDuplicated(makeUrl(), folder);
  }

  private boolean isPathEmpty() {
    return view.getPath().equals("");
  }

  private boolean isProtocolUnchanged() {
    return view.getProtocol().equals(folder.getPrettyProtocol());
  }

  private boolean isUrlUnchanged() {
    return makeUrl().equals(folder.getUrl());
  }

  private String makeUrl() {
    return view.getProtocol() + "://" + view.getPath();
  }

  private void save() {
    if (!view.isDataValid()) {
      confirmationView.popUpWith("Save Confirmation",
          PopUpConfirmationView.NOT_ALL_INPUTS_ARE_VALID_SAVE_ANYWAY);
      return;
    }
    store();
  }

  private void showValidation() {
    clearValidations();
    validateName();
    validateUrl();
    validateDataUnchanged();
  }

  private void store() {
    directory.remove(folder);
    directory.store(folder.createWith(view.getName(), makeUrl()));
    directory.fetch();
    explorerView.clearExplorerContainer();
    directory.loadExplorerWithChildrenFor(explorerView);
    view.close();
  }

  private void validateDataUnchanged() {
    view.isDataUnchangedHelpShowing(areEntriesUnchanged());
  }

  private void validateName() {
    view.isEmptyNameHelpShowing(isNameEmpty());
    view.isDuplicateNameHelpShowing(isNameDuplicated());
    view.isNameHelpShowing(isNameEmpty() || isNameDuplicated());
  }

  private void validateOverHttp() {
    dispatcher.execute(
        new ImagesPayload(folder.createWith(view.getName(), makeUrl())),
        new GetExplorerValidationServiceResponse(view));
  }

  private void validateUrl() {
    view.isEmptyPathHelpShowing(isPathEmpty());
    view.isDuplicatePathHelpShowing(isPathDuplicated());
    if (!isPathDuplicated() && !isPathEmpty()) validateOverHttp();
    else view.isPathHelpShowing(isPathEmpty() || isPathDuplicated());
  }
}
