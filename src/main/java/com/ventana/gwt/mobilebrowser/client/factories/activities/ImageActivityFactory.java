package com.ventana.gwt.mobilebrowser.client.factories.activities;

import com.ventana.gwt.mobilebrowser.client.activities.ImageActivity;
import com.ventana.gwt.mobilebrowser.client.places.Image;

public interface ImageActivityFactory {
  ImageActivity createActivityFor(final Image place);
}
