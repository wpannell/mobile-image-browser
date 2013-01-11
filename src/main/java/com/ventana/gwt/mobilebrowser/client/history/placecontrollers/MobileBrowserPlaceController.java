package com.ventana.gwt.mobilebrowser.client.history.placecontrollers;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

public class MobileBrowserPlaceController extends PlaceController {
  @SuppressWarnings("deprecation") // activities require deprecated eventbus...
  @Inject
  public MobileBrowserPlaceController(final EventBus eventBus) {
    super(eventBus);
  }
}
