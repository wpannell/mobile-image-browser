package com.ventana.gwt.mobilebrowser.client.events;

import com.google.gwt.event.shared.EventHandler;

import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;

@SuppressWarnings("unused") // ... unused event parameters in null objects ...
public interface Subscriber<T> extends EventHandler {
  public static final Subscriber<Boolean> BOOLEAN_EMPTY = new Subscriber<Boolean>() {
    @Override public void notifyWith(final Boolean notification) {}
    };
  public static final Subscriber<Explorer> EXPLORER_EMPTY = new Subscriber<Explorer>() {
    @Override public void notifyWith(final Explorer notification) {}
    };
  public static final Subscriber<String> STRING_EMPTY = new Subscriber<String>() {
      @Override public void notifyWith(String notification) {}
    };
  public static final Subscriber<Thumbnail> THUMBNAIL_EMPTY = new Subscriber<Thumbnail>() {
    @Override public void notifyWith(final Thumbnail notification) {}
    };
  public static final Subscriber<Void> VOID_EMPTY = new Subscriber<Void>() {
    @Override public void notifyWith(Void notification) {}
    };

  void notifyWith(final T notification);
}
