package com.ventana.gwt.mobilebrowser.client.factories;

import com.google.inject.Inject;

import com.ventana.gwt.mobilebrowser.client.activities.DirectoryActivity;
import com.ventana.gwt.mobilebrowser.client.activities.ExplorerActivity;
import com.ventana.gwt.mobilebrowser.client.activities.ImageActivity;
import com.ventana.gwt.mobilebrowser.client.factories.activities.DirectoryActivityFactory;
import com.ventana.gwt.mobilebrowser.client.factories.activities.ExplorerActivityFactory;
import com.ventana.gwt.mobilebrowser.client.factories.activities.ImageActivityFactory;
import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Image;

public class ContentActivityFactory implements ActivityFactory {
  private final DirectoryActivityFactory directoryActivityFactory;
  private final ExplorerActivityFactory explorerActivityFactory;
  private final ImageActivityFactory imageActivityFactory;

  @Inject
  public ContentActivityFactory(
      final ExplorerActivityFactory explorerActivityFactory,
      final DirectoryActivityFactory directoryActivityFactory,
      final ImageActivityFactory imageActivityFactory
      ) {
    this.explorerActivityFactory = explorerActivityFactory;
    this.directoryActivityFactory = directoryActivityFactory;
    this.imageActivityFactory = imageActivityFactory;
  }

  @Override
  public DirectoryActivity createWith(final Directory place) {
    return directoryActivityFactory.createActivityFor(place);
  }

  @Override
  public ExplorerActivity createWith(final Explorer place) {
    return explorerActivityFactory.createActivityFor(place);
  }

  @Override
  public ImageActivity createWith(final Image place) {
    return imageActivityFactory.createActivityFor(place);
  }
}
