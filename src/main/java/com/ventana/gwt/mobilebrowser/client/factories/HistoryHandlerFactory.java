package com.ventana.gwt.mobilebrowser.client.factories;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryHandler;

public interface HistoryHandlerFactory {
  PlaceHistoryHandler createWith(final Place defaultPlace);
}
