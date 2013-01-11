package com.ventana.gwt.mobilebrowser.client.places;

import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;

public class RootFolder extends Folder {
  @SuppressWarnings("hiding")
  public static final RootFolder EMPTY = new RootFolder(){
    @Override public String getUrl() { return ""; }
    @Override public String getProtocol() { return "http://"; }
    @Override public String getPort() { return ""; }
  };

  private static final String EXPLORER_FOLDER_SUFFIX = "</p>" +
      "<a class='deleteLink' href='javascript:void(0)'>&nbsp;</a>" +
      "<a class='editLink' href='javascript:void(0)'>&nbsp;</a>" +
      "</div></li>";

  public RootFolder() {
    this("");
  }

  public RootFolder(final String historyToken) {
    super(historyToken);
  }

  public RootFolder(final String name, final String url) {
    this(name + "$" + url);
  }

  @Override
  public String asHtml() {
    return ExplorerView.EXPLORER_PREFIX
        + getName() + EXPLORER_FOLDER_SUFFIX;
  }

  @Override
  public RootFolder asRootFolder() {
    return this;
  }

  @Override
  public RootFolder createWith(final String name, final String url) {
    return new RootFolder(name, url);
  }

  @Override
  public String getParentUrl() {
    return getUrl();
  }

  public String getPrettyProtocol() {
    if (getProtocol().equals("")) return "";
    return getProtocol().substring(0, getProtocol().length() - 3);
  }

  @Override
  public Boolean isRoot() {
    return true;
  }

  @Override
  public void loadExplorerFor(final ExplorerView view) {
    view.loadExplorerWith(this);
  }

  @Override
  public void removeMeFrom(final Directory parent) {
    parent.remove(this);
  }
}
