package com.ventana.gwt.mobilebrowser.client.ui;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.ventana.gwt.mobilebrowser.client.containers.ExplorerContainer;
import com.ventana.gwt.mobilebrowser.client.containers.NavigatorContainer;
import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Folder;
import com.ventana.gwt.mobilebrowser.client.places.RootFolder;
import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.client.views.TouchDelegator;
import com.ventana.gwt.mobilebrowser.client.widgets.TestableComposite;

public class ExplorerUi extends TestableComposite implements ExplorerView {
  @UiTemplate("Explorer.ui.xml")
  public interface ExplorerUiBinder extends UiBinder<Widget, ExplorerUi> {}

  final ExplorerContainer explorerContainer;
  final NavigatorContainer navigatorContainer;
  final TouchDelegator touchDelgator;

  private Subscriber<Explorer> fileClickedSubscriber = Subscriber.EXPLORER_EMPTY;
  private Subscriber<Thumbnail> thumbnailInformationClickedSubscriber = Subscriber.THUMBNAIL_EMPTY;
  private Subscriber<Explorer> folderClickedSubscriber = Subscriber.EXPLORER_EMPTY;
  private Subscriber<Explorer> navigatorClickedSubscriber = Subscriber.EXPLORER_EMPTY;
  private Subscriber<Explorer> browseClickedSubscriber = Subscriber.EXPLORER_EMPTY;
  private Subscriber<Explorer> editClickedSubscriber = Subscriber.EXPLORER_EMPTY;
  private Subscriber<Explorer> deleteClickedSubscriber = Subscriber.EXPLORER_EMPTY;

  @Inject
  public ExplorerUi(final ExplorerUiBinder binder,
      final NavigatorContainer navigatorContainer,
      final ExplorerContainer explorerContainer,
      final TouchDelegator touchDelgator) {
    this.navigatorContainer = navigatorContainer;
    this.explorerContainer = explorerContainer;
    this.touchDelgator = touchDelgator;
    this.initWidget(binder.createAndBindUi(this));
  }

  @Override
  public void addMeToThis(final AcceptsOneWidget container) {
    container.setWidget(this);
  }

  @Override
  public void clearExplorerContainer() {
    explorerContainer.clear();
  }

  @Override
  public void clearNavigatorContainer() {
    navigatorContainer.clear();
  }

  @Override
  public void configureExplorerContainer() {
    explorerContainer.setSelector(ExplorerView.EXPLORER_SELECTOR);
    explorerContainer.setPrefix(ExplorerView.EXPLORER_PREFIX);
    explorerContainer.setSuffix(ExplorerView.EXPLORER_SUFFIX);
  }

  @Override
  public void configureNavigatorContainer() {
    navigatorContainer.setSelector(ExplorerView.NAVIGATOR_SELECTOR);
    navigatorContainer.setPrefix(ExplorerView.NAVIGATOR_PREFIX);
    navigatorContainer.setSuffix(ExplorerView.NAVIGATOR_SUFFIX);
  }

  @Override
  public void delegateTouches() {
    this.touchDelgator.delegateTo(this);
  }

  @Override
  public void undelegateTouches() {
    this.touchDelgator.resetWith(this);
  }

  @Override
  public int numberOfFoldersOrFilesInExplorer() {
    return explorerContainer.size();
  }

  @Override
  public void loadExplorerWith(final Folder folder) {
    explorerContainer.append(folder, folderClickedSubscriber);
  }

  @Override
  public void loadExplorerWith(final Thumbnail thumbnail) {
    explorerContainer.append(
        thumbnail, fileClickedSubscriber, thumbnailInformationClickedSubscriber);
  }

  @Override
  public void loadExplorerWith(final RootFolder folder) {
    explorerContainer.append(folder, browseClickedSubscriber,
        deleteClickedSubscriber, editClickedSubscriber);
  }

  @Override
  public void loadNavigatorWith(final Explorer folder) {
    navigatorContainer.append(folder, navigatorClickedSubscriber);
  }

  @Override
  public void loadNavigatorWith(Directory directory) {
    navigatorContainer.append(directory);
  }

  @Override
  public void popUp(final String message) {
    Window.alert(message);
  }

  @Override
  public void selectNavigatorFolder(final int i) {
    navigatorContainer.select(i);
  }

  @Override
  public void whenBrowseClickedNotify(final Subscriber<Explorer> subscriber) {
    this.browseClickedSubscriber = subscriber;
  }

  @Override
  public void whenDeleteClickedNotify(final Subscriber<Explorer> subscriber) {
    this.deleteClickedSubscriber = subscriber;
  }

  @Override
  public void whenEditClickedNotify(final Subscriber<Explorer> subscriber) {
    this.editClickedSubscriber = subscriber;
  }

  @Override
  public void whenThumbnailClickedNotify(final Subscriber<Explorer> subscriber) {
    fileClickedSubscriber = subscriber;
  }

  @Override
  public void whenFolderClickedNotify(final Subscriber<Explorer> subscriber) {
    folderClickedSubscriber = subscriber;
  }

  @Override
  public void whenThumbnailInformationClickedNotify(Subscriber<Thumbnail> subscriber) {
    thumbnailInformationClickedSubscriber = subscriber;
  }

  @Override
  public void whenNavigatorClickedNotify(final Subscriber<Explorer> subscriber) {
    navigatorClickedSubscriber = subscriber;
  }

  @Override
  public void whenRightToLeftSwipedNotify(final Subscriber<Void> subscriber) {
    touchDelgator.whenRightToLeftSwipedNotify(subscriber);
  }
}