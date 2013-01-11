package com.ventana.gwt.mobilebrowser.server.builders;

import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Folder;
import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;
import com.ventana.gwt.mobilebrowser.client.xml.ExplorerXml;

public class ExplorerBuilder {
  private final ExplorerXml node;
  private final Explorer rootPlace;

  public ExplorerBuilder(final Explorer rootPlace, final ExplorerXml node) {
    this.rootPlace = rootPlace;
    this.node = node;
  }

  public Explorer build() {
    for (int i = 0; i < node.getNumberOfFolders(); i++)
      rootPlace.adopt(new Folder(rootPlace.getUrl(), node.getFolder(i)));
    for (int i = 0; i < node.getNumberOfFiles(); i++)
      rootPlace.adopt(new Thumbnail(rootPlace.getUrl(), node.getFile(i)));
    return rootPlace;
  }
}
