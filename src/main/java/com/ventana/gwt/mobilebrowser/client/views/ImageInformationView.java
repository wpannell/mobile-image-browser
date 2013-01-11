package com.ventana.gwt.mobilebrowser.client.views;

import com.ventana.gwt.mobilebrowser.client.domain.ImageInformation;
import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;

public interface ImageInformationView {
  public static final String IMAGE_NAVIGATOR_THUMB_SELECTOR =
      ".image-navigator.thumb";

  public static final String IMG_SELECTOR = "img";

  public static final String NEXT_IMAGE_DISABLED_URL =
      "mobilebrowser/img/right-disabled.png";

  public static final String NEXT_IMAGE_URL = "mobilebrowser/img/right.png";

  public static final String PREVIOUS_IMAGE_DISABLED_URL =
      "mobilebrowser/img/left-disabled.png";

  public static final String PREVIOUS_IMAGE_URL = "mobilebrowser/img/left.png";
  public static final String SRC_ATTRIBUTE = "src";

  void close();
  void disableNext();
  void disablePrevious();
  void enableNext();
  void enablePrevious();
  void open();
  void showWith(Explorer thumbnail, ImageInformation imageInformation);
  void whenCancelClickedNotify(Subscriber<Void> subscriber);
  void whenNextClickedNotify(Subscriber<Explorer> subscriber);
  void whenPreviousClickedNotify(Subscriber<Explorer> subscriber);
  void whenViewClickedNotify(Subscriber<Void> subscriber);
}
