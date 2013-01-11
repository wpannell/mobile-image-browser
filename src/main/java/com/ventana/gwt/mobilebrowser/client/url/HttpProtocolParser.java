package com.ventana.gwt.mobilebrowser.client.url;

public class HttpProtocolParser {
  private static final String PORT_SEPARATOR = ":";
  private static final String PROTOCOL_SEPARATOR = "//";
  private static final String SEPARATOR = "/";
  private final String host;
  private final String path;
  private final String port;
  private final String protocol;
  private final String query;

  public HttpProtocolParser(final String url) {
    final QueryParser parser = new QueryParser(url);
    query = parser.getQuery();
    protocol = extractProtocolFrom(parser.getEncoding());
    port = extractPortFrom(parser.getEncoding());
    path = extractPathFrom(parser.getEncoding());
    host = extractHostFrom(parser.getEncoding());
  }

  public String getHost() { return host; }
  public String getPath() { return path; }
  public String getPort() { return port; }
  public String getProtocol() { return protocol; }
  public String getQuery() { return query; }

  private String[] buildProtocolTokens(final String url) {
    final String[] protocolTokens = url.split(PROTOCOL_SEPARATOR);
    return protocolTokens;
  }

  private String extractHostFrom(final String url) {
    final String rawPath = extractRawPathFrom(url);
    final String path = rawPath.split(port)[0];
    final int offset = path.indexOf(SEPARATOR);
    return offset < 0 ? path : path.substring(0, offset);
  }

  private String extractPathFrom(final String url) {
    final String rawPath = extractRawPathFrom(url);
    final int offset = rawPath.indexOf(SEPARATOR);
    return offset < 0 ? "" : rawPath.substring(offset);
  }

  private String extractPortFrom(final String url) {
    final String[] protocolTokens = buildProtocolTokens(url);
    final String path = protocolTokens.length == 1 ? url : protocolTokens[1];
    final String[] portTokens = path.split(PORT_SEPARATOR);
    if (portTokens.length == 1)
      return ":80";
    final String[] split = portTokens[1].split(SEPARATOR);
    return PORT_SEPARATOR + (split.length == 0 ? portTokens[0] : split[0]);
  }

  private String extractProtocolFrom(final String url) {
    final String[] tokens = buildProtocolTokens(url);
    return tokens.length == 1 ? "http://" : tokens[0] + PROTOCOL_SEPARATOR;
  }

  private String extractRawPathFrom(final String url) {
    final String[] tokens = url.split(protocol);
    final String rawPath = tokens[tokens.length - 1];
    return rawPath;
  }
}
