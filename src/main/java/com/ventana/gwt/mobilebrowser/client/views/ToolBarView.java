package com.ventana.gwt.mobilebrowser.client.views;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;

public interface ToolBarView {
  public static final boolean YES = true;
  public static final boolean NO = false;

  void goBrowserBack();
  void isAddButtonShowing(final boolean shouldShow);
  void isBackButtonShowing(final boolean shouldShow);
  void isInformationTextShowing(final boolean shouldShow);
  boolean isShowing();
  void isShowing(final boolean shouldShow);
  void setInformationText(final String zoom);
  void whenAddButtonClickedNotify(final Subscriber<Void> subscriber);
}
