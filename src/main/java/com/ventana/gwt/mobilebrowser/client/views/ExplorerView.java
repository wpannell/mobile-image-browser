package com.ventana.gwt.mobilebrowser.client.views;

import com.google.gwt.user.client.ui.AcceptsOneWidget;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Folder;
import com.ventana.gwt.mobilebrowser.client.places.RootFolder;
import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;

public interface ExplorerView {
  public static final String EXPLORER_PREFIX =
      "<li class='ImageFolder'><div><a href='javascript:void(0)'><img src='mobilebrowser/img/folderThumbnail.png'></a><p>";

  public static final String EXPLORER_SUFFIX = "</p></div></li>";
  public static final String EXPLORER_SELECTOR = ".content .imageBrowser ul";

  public static final String NAVIGATOR_PREFIX =
      "<li><div><a class='folderLink' href='javascript:void(0)'>";

  public static final String NAVIGATOR_SUFFIX = "</a></div></li>";
  public static final String NAVIGATOR_SELECTOR = ".leftNav section ul";

  void addMeToThis(final AcceptsOneWidget container);
  void clearExplorerContainer();
  void clearNavigatorContainer();
  void configureExplorerContainer();
  void configureNavigatorContainer();
  void delegateTouches();
  int numberOfFoldersOrFilesInExplorer();
  void loadExplorerWith(final Folder folder);
  void loadExplorerWith(final RootFolder folder);
  void loadExplorerWith(final Thumbnail thumbnail);
  void loadNavigatorWith(final Directory directory);
  void loadNavigatorWith(final Explorer folder);
  void popUp(final String message);
  void selectNavigatorFolder(final int i);
  void undelegateTouches();
  void whenBrowseClickedNotify(final Subscriber<Explorer> subscriber);
  void whenDeleteClickedNotify(final Subscriber<Explorer> subscriber);
  void whenEditClickedNotify(final Subscriber<Explorer> subscriber);
  void whenFolderClickedNotify(final Subscriber<Explorer> subscriber);
  void whenThumbnailClickedNotify(final Subscriber<Explorer> subscriber);
  void whenThumbnailInformationClickedNotify(final Subscriber<Thumbnail> subscriber);
  void whenNavigatorClickedNotify(final Subscriber<Explorer> subscriber);
  void whenRightToLeftSwipedNotify(final Subscriber<Void> subscriber);
}
