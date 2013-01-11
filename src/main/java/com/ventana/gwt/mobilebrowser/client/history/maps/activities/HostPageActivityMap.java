package com.ventana.gwt.mobilebrowser.client.history.maps.activities;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

import com.ventana.gwt.mobilebrowser.client.activities.HostPageActivity;

public class HostPageActivityMap implements ActivityMapper {
  private final HostPageActivity homeActivity;

  @Inject
  public HostPageActivityMap(final HostPageActivity homeActivity) {
    this.homeActivity = homeActivity;
  }

  @Override
  public Activity getActivity(@SuppressWarnings("unused") final Place place) {
    return homeActivity;
  }
}
