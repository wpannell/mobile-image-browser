package com.ventana.gwt.mobilebrowser.client.history.maps.activities;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

import com.ventana.gwt.mobilebrowser.client.factories.ActivityFactory;
import com.ventana.gwt.mobilebrowser.client.places.MobileBrowserPlace;

public class ExplorerActivityMap implements ActivityMapper {
  private final ActivityFactory activityFactory;

  @Inject
  public ExplorerActivityMap(final ActivityFactory activityFactory) {
    this.activityFactory = activityFactory;
  }

  @Override
  public Activity getActivity(final Place place) {
    return ((MobileBrowserPlace) place).getActivityFrom(activityFactory);
  }
}
