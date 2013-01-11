package com.ventana.gwt.mobilebrowser.client.containers;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.query.client.GQuery;

import com.ventana.gwt.mobilebrowser.client.places.Explorer;

  public class NavigatorContainer extends GwtQueryContainer {
  public NavigatorContainer() {
    super();
  }

  @Override
  protected GQuery buildListElement(final Explorer place) {
    return $(getPrefix() + place.getName() + getSuffix());
  }
}
