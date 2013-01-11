package com.ventana.gwt.mobilebrowser.client.history;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class HistoryHandler extends PlaceHistoryHandler {
  @SuppressWarnings("deprecation") // activities require deprecated eventbus...
  @Inject
  public HistoryHandler(final PlaceHistoryMapper placeMap,
      final PlaceController placeController, final EventBus eventBus,
      @Assisted final Place defaultPlace) {
    super(placeMap);
    register(placeController, eventBus, defaultPlace);
  }
}
