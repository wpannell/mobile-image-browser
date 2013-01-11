package com.ventana.gwt.mobilebrowser.client.xml;

import com.google.gwt.core.client.GWT;

import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.xml.client.XmlReader;

public class FolderXml {
  interface Reader extends XmlReader<FolderXml> {}
  public static final FolderXml EMPTY = new FolderXml();

  @SuppressWarnings("unused") // "XML" used in generated code by Piriti...
  private static final Reader XML = GWT.create(Reader.class);

  public static FolderXml createWith(final String name) {
    final FolderXml folder = new FolderXml();
    folder.name = name;
    return folder;
  }

  @Path("Name")
  String name;
  public String getName() { return name; }
  public String getTime() { return ""; }
  public String getSize() { return ""; }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (!(obj instanceof FolderXml)) return false;
    final FolderXml other = (FolderXml) obj;
    if (name == null) {
      if (other.name != null) return false;
    }
    else if (!name.equals(other.name)) return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (name == null ? 0 : name.hashCode());
    return result;
  }
}
