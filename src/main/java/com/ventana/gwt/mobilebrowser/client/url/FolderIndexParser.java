package com.ventana.gwt.mobilebrowser.client.url;

public class FolderIndexParser {
  public static final String FRONT_DELIMITER = "\\%";
  public static final String NAME_DELIMITER = "\\$";
  public static final String REAR_DELIMITER = "\\|";
  private final int index;
  private final boolean isRoot;
  private final String name;
  private final String url;

  public FolderIndexParser(final String encoding) {
    if (encoding == null)
      throw new IllegalArgumentException("cannot pass null");

    final String[] nameTokens = parseNameTokens(encoding);
    name = parseNameFrom(nameTokens);

    final String[] indexTokens = parseIndexTokens(nameTokens);
    index = parseIndexFrom(indexTokens);

    final String[] folderTokens = parseFolderTokens(indexTokens);
    isRoot = parseRootFrom(folderTokens);
    url = parseUrlFrom(folderTokens);
  }

  public int getIndex() {
    return index;
  }

  public String getName() {
    return name;
  }

  public String getUrl() {
    return url;
  }

  public boolean isRoot() {
    return isRoot;
  }

  private boolean isEncoded(final String[] tokens) {
    return tokens.length == 2;
  }

  private String[] parseFolderTokens(final String[] indexTokens) {
    final String[] folderTokens = indexTokens[0].split(FRONT_DELIMITER);
    return folderTokens;
  }

  private int parseIndex(final String[] indexTokens) {
    return Integer.parseInt(indexTokens[1]);
  }

  private int parseIndexFrom(final String[] indexTokens) {
    return isEncoded(indexTokens) ? parseIndex(indexTokens) : 0;
  }

  private String[] parseIndexTokens(final String[] nameTokens) {
    final String encoding =
        isEncoded(nameTokens) ? nameTokens[1] : nameTokens[0];
    final String[] indexTokens = encoding.split(REAR_DELIMITER);
    return indexTokens;
  }

  private String parseNameFrom(final String[] nameTokens) {
    return isEncoded(nameTokens) ? nameTokens[0] : "";
  }

  private String[] parseNameTokens(final String encoding) {
    final String[] nameTokens = encoding.split(NAME_DELIMITER);
    return nameTokens;
  }

  private boolean parseRootFrom(final String[] folderTokens) {
    return isEncoded(folderTokens)
        ? folderTokens[0].toUpperCase().equals("Y") : false;
  }

  private String parseUrlFrom(final String[] folderTokens) {
    return folderTokens[folderTokens.length - 1];
  }
}