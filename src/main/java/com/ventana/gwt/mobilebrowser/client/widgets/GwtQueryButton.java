package com.ventana.gwt.mobilebrowser.client.widgets;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.query.client.css.CSS;
import com.google.gwt.user.client.Event;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;

public class GwtQueryButton implements Button {
  public static final Button EMPTY = new GwtQueryButton();
  private static final String DISABLED = "disabled";
  private static final String NONE = "none";
  private final GQuery button;
  private Subscriber<Void> subscriber;

  public GwtQueryButton() {
    this("");
  }

  public GwtQueryButton(final String selector) {
    button = $(selector);
    subscriber = Subscriber.VOID_EMPTY;
    wrap();
  }

  public GwtQueryButton(final String selector, final Subscriber<Void> subscriber) {
    this(selector);
    subscribeWith(subscriber);
  }

  @Override
  public void click() {
    button.click();
  }

  @Override
  public void hide() {
    button.hide();
  }

  @Override
  public boolean isEnabled() {
    return !button.hasClass(DISABLED);
  }

  @Override
  public void isEnabled(final boolean shouldEnable) {
    if (shouldEnable) setEnabled(); else setDisabled();
  }

  @Override
  public boolean isVisible() {
    return !button.css(CSS.DISPLAY).equals(NONE);
  }

  @Override
  public void setDisabled() {
    button.addClass(DISABLED);
  }

  @Override
  public void setEnabled() {
    button.removeClass(DISABLED);
  }

  @Override
  public void show() {
    button.show();
  }

  @Override
  public void subscribeWith(final Subscriber<Void> subscriber) {
    this.subscriber = subscriber;
  }

  @Override
  public void unwrap() {
    button.unbind(Event.ONCLICK);
  }

  @Override
  public void wrap() {
    button.click(new Function() {
      @Override
      public void f() {
        if (isEnabled()) subscriber.notifyWith(null);
      }
    });
  }
}