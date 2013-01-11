package com.ventana.gwt.mobilebrowser.client.views;

import com.google.gwt.user.client.ui.Widget;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;

public interface TouchDelegator {
  void delegateTo(final Widget widget);
  void resetWith(final Widget widget);
  void whenRightToLeftSwipedNotify(final Subscriber<Void> subscriber);
}
