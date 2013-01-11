package com.ventana.gwt.mobilebrowser.client.factories;

import com.ventana.gwt.mobilebrowser.client.activities.DirectoryActivity;
import com.ventana.gwt.mobilebrowser.client.activities.ExplorerActivity;
import com.ventana.gwt.mobilebrowser.client.activities.ImageActivity;
import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Image;

public interface ActivityFactory {
  ExplorerActivity createWith(final Explorer place);
  DirectoryActivity createWith(final Directory place);
  ImageActivity createWith(final Image place);
}
