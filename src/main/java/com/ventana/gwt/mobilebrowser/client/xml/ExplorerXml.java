package com.ventana.gwt.mobilebrowser.client.xml;

import com.google.gwt.core.client.GWT;

import java.util.List;

import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.totoe.xml.client.XmlParser;

public class ExplorerXml {
  interface Reader extends XmlReader<ExplorerXml> {}
  private static final Reader XML = GWT.create(Reader.class);

  public static ExplorerXml createWith(final String xml) {
    return ExplorerXml.XML.read(new XmlParser().parse(xml));
  }

  @Path("Files/File")
  List<FileXml> files;

  @Path("Folders/Folder")
  List<FolderXml> folders;

  public FileXml getFile(final int i) {
    return files.get(i);
  }

  public FolderXml getFolder(final int i) {
    return folders.get(i);
  }

  public int getNumberOfFiles() {
    if (files == null) return 0;
    return files.size();
  }

  public int getNumberOfFolders() {
    if (folders == null) return 0;
    return folders.size();
  }
}