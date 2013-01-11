package com.ventana.gwt.mobilebrowser.client.xml;

import com.google.gwt.core.client.GWT;

import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.xml.client.XmlReader;

public class FileXml {
  interface Reader extends XmlReader<FileXml> {}
  public static final FileXml EMPTY = new FileXml();


  @SuppressWarnings("unused") // "XML" used in generated code by Piriti...
  private static final Reader XML = GWT.create(Reader.class);

  public static FileXml createWith(final String name, final String time,
      final String size) {
    final FileXml file = new FileXml();
    file.name = name;
    file.time = time;
    file.size = size;
    return file;
  }

  @Path("Name")
  String name;
  @Path("Size")
  String size;
  @Path("Time")
  String time;

  public String getName() { return name; }
  public String getSize() { return size; }
  public String getTime() { return time; }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (!(obj instanceof FileXml)) return false;
    final FileXml other = (FileXml) obj;
    if (name == null) {
      if (other.name != null) return false;
    }
    else if (!name.equals(other.name)) return false;
    if (size == null) {
      if (other.size != null) return false;
    }
    else if (!size.equals(other.size)) return false;
    if (time == null) {
      if (other.time != null) return false;
    }
    else if (!time.equals(other.time)) return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (name == null ? 0 : name.hashCode());
    result = prime * result + (size == null ? 0 : size.hashCode());
    result = prime * result + (time == null ? 0 : time.hashCode());
    return result;
  }
}
