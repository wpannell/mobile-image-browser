package com.ventana.gwt.mobilebrowser.client.places;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;

import com.ventana.gwt.mobilebrowser.client.factories.ActivityFactory;

public abstract class MobileBrowserPlace extends Place {
  public MobileBrowserPlace() {
    super();
  }
  public abstract Activity getActivityFrom(final ActivityFactory factory);
}
