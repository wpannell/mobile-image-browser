package com.ventana.gwt.mobilebrowser.client.publishers;

import com.google.gwt.query.client.Function;
import com.google.gwt.user.client.Event;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;

public class ClickedPublisher<T> extends Function {
  private final Subscriber<T> clickedSubscriber;
  private final T notification;
  private final boolean shouldBubbleUp;

  public ClickedPublisher(final T notification,
      final Subscriber<T> clickedSubscriber, boolean shouldBubbleUp) {
    this.notification = notification;
    this.clickedSubscriber = clickedSubscriber;
    this.shouldBubbleUp = shouldBubbleUp;
  }

  @Override
  public boolean f(@SuppressWarnings("unused") Event e) {
    clickedSubscriber.notifyWith(notification);
    return shouldBubbleUp;
  }
}
