package com.ventana.gwt.mobilebrowser.client.url;

public class UrlParser {
  private static final String QUERY_SEPARATOR = "\\?";
  private static final String SEPARATOR = "/";
  private final String encoding;
  private String host;
  private final int index;
  private final boolean isRoot;
  private final String name;
  private final String query;
  private final String path;
  private final String port;
  private final String protocol;
  private String subPath;

  public UrlParser(final String url) {
    final FolderIndexParser folderParser = new FolderIndexParser(url);
    index = folderParser.getIndex();
    name = folderParser.getName();
    isRoot = folderParser.isRoot();
    encoding = folderParser.getUrl();
    final HttpProtocolParser protocolparser = new HttpProtocolParser(encoding);
    query = protocolparser.getQuery();
    protocol = protocolparser.getProtocol();
    host = protocolparser.getHost();
    port = protocolparser.getPort();
    path = protocolparser.getPath();
    parse(host + port + path);
  }

  public String getPath() {
    return path;
  }

  public String getProtocol() {
    return protocol;
  }

  public String getPort() {
    return port;
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
    return (getSubPath().equals("")) ?
        getPath() : trim(getPath(), SEPARATOR + getSubPath());
  }

  public String getParentUrl() {
    final String urlSansQuery = buildUrl();
    return (getSubPath().equals("")) ?
        urlSansQuery : trim(urlSansQuery, SEPARATOR + getSubPath());
  }

  public String getQuery() {
    return query;
  }

  public String getSubPath() {
    return subPath;
  }

  public String getUrl() {
    return buildUrl() + buildQuery();
  }

  public boolean isRoot() {
    return isRoot;
  }

  private String buildUrl() {
    return protocol + host + port + path;
  }

  private String buildQuery() {
    return (query.equals("") ? "" : "?") + query;
  }

  private String buildLastToken(final String[] tokens) {
    if (tokens.length == 1) return "";
    return tokens[tokens.length - 1];
  }

  private void parse(final String url) {
    final String[] queryTokens = url.split(QUERY_SEPARATOR);
    final String[] hostTokens = queryTokens[0].split(SEPARATOR);
    subPath = buildLastToken(hostTokens);
  }

  private String trim(final String source, final String substring) {
    return source.substring(0, source.lastIndexOf(substring));
  }
}