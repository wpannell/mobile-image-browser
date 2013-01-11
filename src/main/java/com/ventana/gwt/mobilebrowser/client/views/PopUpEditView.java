package com.ventana.gwt.mobilebrowser.client.views;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;

public interface PopUpEditView {
  public static final boolean SHOULD_SHOW = true;
  void clearValidations();
  void close();
  String getName();
  String getPath();
  String getProtocol();

  void isBadPathHelpShowing(boolean shouldShow);
  void isDataUnchangedHelpShowing(boolean shouldShow);
  boolean isDataValid();
  void isDuplicateNameHelpShowing(boolean shouldShow);
  void isDuplicatePathHelpShowing(boolean shouldShow);
  void isEmptyNameHelpShowing(boolean shouldShow);
  void isEmptyPathHelpShowing(boolean shouldShow);
  void isNameHelpShowing(boolean shouldShow);
  void isPathHelpShowing(boolean shouldShow);

  void openWith(final String name, final String protocol,
      final String path);

  void whenCancelClickedNotify(final Subscriber<Void> subscriber);
  void whenSaveClickedNotify(final Subscriber<Void> subscriber);
  void whenValidateClickedNotify(final Subscriber<Void> subscriber);
}
