package com.ventana.gwt.mobilebrowser.client.widgets;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.views.TouchDelegator;

public class WidgetTouchDelegator implements TouchDelegator {
  protected static final boolean SHOULD_BUBBLE_UP = true;
  private GQuery element = $("");
  private Subscriber<Void> rightToLeftSwipedSubscriber = Subscriber.VOID_EMPTY;
  private int x = 0;

  public WidgetTouchDelegator() {}

  @Override
  public void delegateTo(final Widget widget) {
    resetWith(widget);
    bindTouchStart();
    bindTouchEnd();
  }

  @Override
  public void resetWith(final Widget widget) {
    element.unbind(Event.ONTOUCHSTART | Event.ONTOUCHEND);
    element = $(widget);
  }

  @Override
  public void whenRightToLeftSwipedNotify(final Subscriber<Void> subscriber) {
    rightToLeftSwipedSubscriber = subscriber;
  }

  private void bindTouchEnd() {
    element.bind(Event.ONTOUCHEND, new Function() {
      @Override public boolean f(final Event e) {
      if (e.getTouches().get(0).getPageX() < x)
        rightToLeftSwipedSubscriber.notifyWith(null);
      return SHOULD_BUBBLE_UP;
      }
    });
  }

  private void bindTouchStart() {
    element.bind(Event.ONTOUCHSTART, new Function() {
      @Override
      public boolean f(final Event e) {
        x = e.getTouches().get(0).getPageX();
        return SHOULD_BUBBLE_UP;
      }
    });
  }
}
