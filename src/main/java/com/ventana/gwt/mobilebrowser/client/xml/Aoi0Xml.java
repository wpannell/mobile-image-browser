package com.ventana.gwt.mobilebrowser.client.xml;

import com.google.gwt.core.client.GWT;

import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.xml.client.XmlReader;

public class Aoi0Xml {
  interface Reader extends XmlReader<Aoi0Xml> {}
  public static final Aoi0Xml EMPTY = new Aoi0Xml();

  @SuppressWarnings("unused") // "XML" used in generated code by Piriti...
  private static final Reader XML = GWT.create(Reader.class);

  public static Aoi0Xml createWith(final String aoiScanned, final String bottom,
      final String left, final String right, final String top) {
    final Aoi0Xml aoiXml = new Aoi0Xml();
    aoiXml.aoiScanned = aoiScanned;
    aoiXml.bottom = bottom;
    aoiXml.left = left;
    aoiXml.right = right;
    aoiXml.top = top;
    return aoiXml;
  }

  @Path("@AOIScanned")
  String aoiScanned;
  @Path("@Bottom")
  String bottom;
  @Path("@Left")
  String left;
  @Path("@Right")
  String right;
  @Path("@Top")
  String top;

  public String getAoiScanned() {
    return aoiScanned;
  }

  public String getBottom() {
    return bottom;
  }

  public String getLeft() {
    return left;
  }

  public String getRight() {
    return right;
  }

  public String getTop() {
    return top;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result =
        prime * result + ((aoiScanned == null) ? 0 : aoiScanned.hashCode());
    result = prime * result + ((bottom == null) ? 0 : bottom.hashCode());
    result = prime * result + ((left == null) ? 0 : left.hashCode());
    result = prime * result + ((right == null) ? 0 : right.hashCode());
    result = prime * result + ((top == null) ? 0 : top.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (!(obj instanceof Aoi0Xml)) return false;
    Aoi0Xml other = (Aoi0Xml) obj;
    if (aoiScanned == null) {
      if (other.aoiScanned != null) return false;
    }
    else if (!aoiScanned.equals(other.aoiScanned)) return false;
    if (bottom == null) {
      if (other.bottom != null) return false;
    }
    else if (!bottom.equals(other.bottom)) return false;
    if (left == null) {
      if (other.left != null) return false;
    }
    else if (!left.equals(other.left)) return false;
    if (right == null) {
      if (other.right != null) return false;
    }
    else if (!right.equals(other.right)) return false;
    if (top == null) {
      if (other.top != null) return false;
    }
    else if (!top.equals(other.top)) return false;
    return true;
  }
}
