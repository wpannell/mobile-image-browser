package com.ventana.gwt.mobilebrowser.client.factories.activities;

import com.ventana.gwt.mobilebrowser.client.activities.ExplorerActivity;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;

public interface ExplorerActivityFactory {
  ExplorerActivity createActivityFor(final Explorer place);
}
