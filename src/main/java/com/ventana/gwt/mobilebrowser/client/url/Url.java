package com.ventana.gwt.mobilebrowser.client.url;

public class Url {
  private final String host;
  private final int index;
  private final boolean isRoot;
  private final String name;
  private final String parentPath;
  private final String parentUrl;
  private final UrlParser parser;
  private final String path;
  private final String port;
  private final String protocol;
  private final String query;
  private final String subPath;
  private final String url;

  public Url(final String url) {
    parser = new UrlParser(url);
    name = parser.getName();
    host = parser.getHost();
    path = parser.getPath();
    port = parser.getPort();
    protocol = parser.getProtocol();
    isRoot = parser.isRoot();
    subPath = parser.getSubPath();
    query = parser.getQuery();
    parentUrl = parser.getParentUrl();
    parentPath = parser.getParentPath();
    this.url = parser.getUrl();
    index = parser.getIndex();
  }

  public String getHost() {
    return host;
  }

  public int getIndex() {
    return index;
  }

  public String getName() {
    return name;
  }

  public String getParentPath() {
    return parentPath;
  }

  public String getParentUrl() {
    return parentUrl;
  }

  public String getPath() {
    return path;
  }

  public String getPort() {
    return port;
  }

  public String getProtocol() {
    return protocol;
  }

  public String getQuery() {
    return query;
  }

  public String getSubPath() {
    return subPath;
  }

  public String getUrl() {
    return url;
  }

  public boolean isRoot() {
    return isRoot;
  }

  @Override
  public String toString() {
    return getUrl();
  }
}
