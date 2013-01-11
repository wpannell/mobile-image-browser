package com.ventana.gwt.mobilebrowser.client.history.maps.places;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.Folder;
import com.ventana.gwt.mobilebrowser.client.places.Image;
import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;

@WithTokenizers({
  Directory.Tokenizer.class,
  Folder.Tokenizer.class,
  Thumbnail.Tokenizer.class,
  Image.Tokenizer.class,
  })
public interface PlaceHistoryMap extends PlaceHistoryMapper {}
