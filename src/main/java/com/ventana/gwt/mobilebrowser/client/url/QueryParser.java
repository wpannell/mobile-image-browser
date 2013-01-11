package com.ventana.gwt.mobilebrowser.client.url;

public class QueryParser {
  private static final String QUERY_SEPARATOR = "\\?";
  private final String encoding;
  private final String query;

  public QueryParser(final String url) {
    final String[] queryTokens = url.split(QUERY_SEPARATOR);
    query = buildLastToken(queryTokens);
    encoding = buildFirstToken(queryTokens);
  }

  public String getEncoding() {
    return encoding;
  }

  public String getQuery() {
    return query;
  }

  private String buildFirstToken(final String[] tokens) {
    return tokens[0];
  }

  private String buildLastToken(final String[] tokens) {
    if (tokens.length == 1) return "";
    return tokens[tokens.length - 1];
  }
}