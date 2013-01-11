package com.ventana.gwt.mobilebrowser.client.factories.activities;

import com.ventana.gwt.mobilebrowser.client.activities.DirectoryActivity;
import com.ventana.gwt.mobilebrowser.client.places.Directory;

public interface DirectoryActivityFactory {
  DirectoryActivity createActivityFor(final Directory place);
}
