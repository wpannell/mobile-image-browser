package com.ventana.gwt.mobilebrowser.server.payloads;

import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.ventana.gwt.mobilebrowser.client.factories.HttpRequestCallbackFactory;
import com.ventana.gwt.mobilebrowser.client.factories.callbacks.ExplorerHttpCallbackFactory;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Folder;
import com.ventana.gwt.mobilebrowser.server.responses.ImagesServiceResponse;

public class ImagesPayload implements Payload<ImagesServiceResponse> {
  private static final String ISCAN_QUERY = "?iScanXml";
  private final Explorer place;
  private final Explorer selectedPlace;
  private final String url;
  private final HttpRequestCallbackFactory command;

  public ImagesPayload() {
    this("");
  }

  public ImagesPayload(final Explorer place) {
    this(place, place.getUrl());
  }

  public ImagesPayload(final Explorer place, final HttpRequestCallbackFactory command) {
    this(place, place, place.getUrl() + ISCAN_QUERY, command);
  }

  public ImagesPayload(final Explorer place, final Explorer selectedPlace) {
    this(place, selectedPlace, place.getUrl());
  }

  public ImagesPayload(final Explorer place, final Explorer selectedPlace,
      final String url) {
    this(place, selectedPlace, url, new ExplorerHttpCallbackFactory());
  }

  public ImagesPayload(final Explorer place, final Explorer selectedPlace,
      final String url, final HttpRequestCallbackFactory command) {
    this.place = place;
    this.selectedPlace = selectedPlace;
    this.url = url;
    this.command = command;
  }

  public ImagesPayload(final Explorer place, final String url) {
    this(place, place, url);
  }

  public ImagesPayload(final String url) {
    this(Folder.EMPTY, url);
  }

  public ImagesPayload asImagesPayload() {
    return this;
  }

  public Explorer getPlace() {
    return place;
  }

  public Explorer getSelectedFolder() {
    return selectedPlace;
  }

  public String getUrl() {
    return url;
  }

  public RequestCallback createRequestCallbackWith(
      AsyncCallback<ImagesServiceResponse> callback) {
    return command.createCallbackWith(this, callback);
  }
}
