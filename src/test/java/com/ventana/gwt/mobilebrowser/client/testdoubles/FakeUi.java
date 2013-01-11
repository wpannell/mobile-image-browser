package com.ventana.gwt.mobilebrowser.client.testdoubles;

import com.google.gwt.core.client.GWT;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.ventana.gwt.mobilebrowser.client.containers.ExplorerContainer;
import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Folder;
import com.ventana.gwt.mobilebrowser.client.places.RootFolder;
import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;

public class FakeUi extends Composite implements ExplorerView {
  @UiTemplate("Fake.ui.xml")
  public interface ExplorerUiBinder
      extends UiBinder<Widget, FakeUi> {}

  private ExplorerContainer explorerContainer;
  private ExplorerContainer navigatorContainer;
  private Subscriber<Explorer> fileClickedSubscriber = Subscriber.EXPLORER_EMPTY;
  private Subscriber<Explorer> folderClickedSubscriber = Subscriber.EXPLORER_EMPTY;
  private Subscriber<Explorer> navigatorClickedSubscriber = Subscriber.EXPLORER_EMPTY;
  private Subscriber<Thumbnail> fileInformationSubscriber = Subscriber.THUMBNAIL_EMPTY;

  public FakeUi() {
    this(GWT.<ExplorerUiBinder>create(ExplorerUiBinder.class));
  }

  @Inject
  public FakeUi(ExplorerUiBinder binder) {
    initWidget(binder.createAndBindUi(this));
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
  public int numberOfFoldersOrFilesInExplorer() {
    return explorerContainer.size();
  }

  @Override
  public void loadExplorerWith(final Folder folder) {
    explorerContainer.append(folder).click(new Function() {
      @Override public void f() {folderClickedSubscriber.notifyWith(folder);}
    });
  }

  @Override
  public void loadExplorerWith(final Thumbnail thumbnail) {
    explorerContainer.append(
        thumbnail, fileClickedSubscriber, fileInformationSubscriber);
  }

  @Override
  public void loadNavigatorWith(final Explorer folder) {
    navigatorContainer.append(folder).click(new Function() {
      @Override public boolean f(@SuppressWarnings("unused") Event e) {
        navigatorClickedSubscriber.notifyWith(folder);
        return false;
      }
    });
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
  public void whenThumbnailClickedNotify(final Subscriber<Explorer> subscriber) {
    fileClickedSubscriber = subscriber;
  }

  @Override
  public void whenFolderClickedNotify(final Subscriber<Explorer> subscriber) {
    folderClickedSubscriber = subscriber;
  }

  @Override
  public void whenNavigatorClickedNotify(final Subscriber<Explorer> subscriber) {
    navigatorClickedSubscriber = subscriber;
  }

  @Override
  protected void onLoad() {
    super.onLoad();
    navigatorContainer = new ExplorerContainer();
    explorerContainer = new ExplorerContainer();

    navigatorContainer.setSelector(ExplorerView.NAVIGATOR_SELECTOR);
    navigatorContainer.setPrefix(ExplorerView.NAVIGATOR_PREFIX);
    navigatorContainer.setSuffix(ExplorerView.NAVIGATOR_SUFFIX);

    explorerContainer.setSelector(ExplorerView.EXPLORER_SELECTOR);
    explorerContainer.setPrefix(ExplorerView.EXPLORER_PREFIX);
    explorerContainer.setSuffix(ExplorerView.EXPLORER_SUFFIX);
  }

  @Override
  public void loadNavigatorWith(Directory directory) {
    navigatorContainer.append(directory);
  }

  @Override @SuppressWarnings("unused")
  public void loadExplorerWith(RootFolder folder) {
  }

  @Override  @SuppressWarnings("unused")
  public void whenBrowseClickedNotify(Subscriber<Explorer> subscriber) {
  }

  @Override  @SuppressWarnings("unused")
  public void whenEditClickedNotify(Subscriber<Explorer> subscriber) {
  }

  @Override  @SuppressWarnings("unused")
  public void whenDeleteClickedNotify(Subscriber<Explorer> subscriber) {
  }

  @Override  @SuppressWarnings("unused")
  public void whenThumbnailInformationClickedNotify(Subscriber<Thumbnail> subscriber) {
  }

  @Override  @SuppressWarnings("unused")
  public void whenRightToLeftSwipedNotify(Subscriber<Void> capture) {
  }

  @Override
  public void configureExplorerContainer() {
  }

  @Override
  public void configureNavigatorContainer() {
  }

  @Override
  public void delegateTouches() {
  }

  @Override
  public void undelegateTouches() {
  }
}