package com.ventana.gwt.mobilebrowser.client.views;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;

public interface PopUpConfirmationView {
  public static final String ARE_YOU_SURE = "Are you sure?";
  public static final String NOT_ALL_INPUTS_ARE_VALID_SAVE_ANYWAY =
      "not all inputs are valid: save anyway";

  public static final String SAVE_CONFIRMATION_TITLE = "Save Confirmation";

  void popUpWith(String title, String question);
  void showWith(String message);
  void whenConfiremdNotify(Subscriber<Void> subscriber);
}