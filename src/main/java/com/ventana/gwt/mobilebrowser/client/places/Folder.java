package com.ventana.gwt.mobilebrowser.client.places;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.client.xml.FolderXml;

public class Folder extends Explorer {
  @Prefix(value = "FolderExplorer")
  public static class Tokenizer implements PlaceTokenizer<Folder> {
    @Override
    public Folder getPlace(final String historyToken) {
      return new Folder(historyToken);
    }
    @Override
    public String getToken(final Folder place) {
      return place.getHistoryToken();
    }
  }

  public static final Folder EMPTY = new Folder() {
    @Override public String getPort() { return ""; }
    @Override public String getProtocol() { return ""; }
    @Override public String getUrl() { return ""; }
  };

  public Folder() {
    super();
  }

  public Folder(final String historyToken) {
    super(historyToken);
  }

  public Folder(final String historyToken, final FolderXml folder) {
    super(historyToken + SEPARATOR + folder.getName(), folder.getName(), folder
        .getTime(), folder.getSize());
  }

  @Override
  public String asHtml() {
    return ExplorerView.EXPLORER_PREFIX
        + getName() + ExplorerView.EXPLORER_SUFFIX;
  }

  @Override
  public void loadExplorerFor(final ExplorerView view) {
    view.loadExplorerWith(this);
  }

  @Override
  public void loadNavigatorFor(final ExplorerView view) {
    view.loadNavigatorWith(this);
  }
}
