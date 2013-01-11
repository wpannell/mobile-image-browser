package com.ventana.gwt.mobilebrowser.client.views;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;

public interface PopUpDeleteView {
  public static final String DELETE_FOLDER = "Delete Folder";
  void close();
  void popUpWith(String title, String question);
  void whenCanceled(Subscriber<Void> subscriber);
  void whenConfirmed(Subscriber<Void> subscriber);
}
