package com.ventana.gwt.mobilebrowser.client.places;

import com.ventana.gwt.mobilebrowser.client.domain.ImageInformation;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;

public class ImageInformationDecorator extends Explorer {
  private static final String PREFIX =
      "<div><a href='javascript:void(0)'><img src='" ;

  public static final String THUMBNAIL_SUFFIX =
      "?label&rotate=270&scale=30&rdims=150";

  public static final String SUFFIX =
      "'></a></div>" ;

  private Explorer thumbnail;
  public ImageInformationDecorator(final Explorer explorer) {
    this.thumbnail = explorer;
  }

  @Override
  public String asHtml() {
    return PREFIX + thumbnail.getUrl() + THUMBNAIL_SUFFIX + SUFFIX;
  }

  @Override
  public boolean equals(final Object obj) {
    return thumbnail.equals(obj);
  }

  @Override
  public ImageInformation getImageInformation() {
    return thumbnail.getImageInformation();
  }

  @Override
  public int hashCode() {
    return thumbnail.hashCode();
  }

  @Override @SuppressWarnings("unused") // ... template method...
  public void loadExplorerFor(ExplorerView view) {}

  @Override @SuppressWarnings("unused") // ... template method...
  public void loadNavigatorFor(ExplorerView view) {}
}
