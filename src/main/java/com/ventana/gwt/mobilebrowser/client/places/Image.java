package com.ventana.gwt.mobilebrowser.client.places;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

import com.ventana.gwt.mobilebrowser.client.factories.ActivityFactory;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;

public class Image extends Explorer {
  @Prefix(value = "ImageViewer")
  public static class Tokenizer implements PlaceTokenizer<Image> {
    @Override
    public Image getPlace(final String historyToken) {
      return new Image(historyToken);
    }
    @Override
    public String getToken(final Image place) {
      return place.getHistoryToken();
    }
  }

  public static final int DEFAULT_MAGNIFICATION = 20;
  public static final int[][] DEFAULT_QUALITY_LEVELS =
      new int[][]{
          {56976, 19088}, {28488, 9544}, {14244, 4772}, {7122, 2386},
          {3561, 1193}, {1781, 597}, {891, 299}, {446, 150}, {223, 75}};

  private final Explorer thumbnail;
  public Image(final Explorer thumbnail) {
    this.thumbnail = thumbnail;
  }

  public Image(final String historyToken) {
    this(new Thumbnail(historyToken));
  }

  @Override
  public Explorer getParent() {
    return thumbnail.getParent();
  }

  @Override
  public String asHtml() {
    return "";
  }

  @Override
  public Activity getActivityFrom(final ActivityFactory factory) {
    return factory.createWith(this);
  }

  @Override
  public String getHistoryToken() {
    return thumbnail.getHistoryToken();
  }

  @Override
  public String getHost() {
    return thumbnail.getHost();
  }

  public int getMagnification() {
    return DEFAULT_MAGNIFICATION;
  }

  @Override
  public String getName() {
    return thumbnail.getName();
  }

  @Override
  public String getPath() {
    return thumbnail.getPath();
  }

  @Override
  public String getParentPath() {
    return thumbnail.getParentPath();
  }

  @Override
  public String getPort() {
    return thumbnail.getPort();
  }

  @Override
  public String getProtocol() {
    return thumbnail.getProtocol();
  }

  public int[][] getQualityLevels() {
    return DEFAULT_QUALITY_LEVELS;
  }

  @Override @SuppressWarnings("unused") // ...template method...
  public void loadExplorerFor(final ExplorerView view) {}

  @Override @SuppressWarnings("unused") // ...template method...
  public void loadNavigatorFor(final ExplorerView view) {}

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (!(obj instanceof Image)) return false;
    final Image other = (Image) obj;
    if (thumbnail == null) {
      if (other.thumbnail != null) return false;
    }
    else if (!thumbnail.equals(other.thumbnail)) return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (thumbnail == null ? 0 : thumbnail.hashCode());
    return result;
  }

  @Override
  public String toString() {
    return getHistoryToken();
  }
}
