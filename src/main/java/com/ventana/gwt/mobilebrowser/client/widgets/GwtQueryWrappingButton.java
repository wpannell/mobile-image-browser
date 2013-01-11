package com.ventana.gwt.mobilebrowser.client.widgets;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;

public class GwtQueryWrappingButton implements Button {
  public static final Button EMPTY = new GwtQueryWrappingButton();

  private Button button;
  private final String selector;

  public GwtQueryWrappingButton() {
    this("");
  }

  public GwtQueryWrappingButton(final String selector) {
    this.selector = selector;
    button = GwtQueryButton.EMPTY;
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
    return button.isEnabled();
  }

  @Override
  public void isEnabled(final boolean shouldEnable) {
    button.isEnabled(shouldEnable);
  }

  @Override
  public boolean isVisible() {
    return button.isVisible();
  }

  @Override
  public void setDisabled() {
    button.setDisabled();
  }

  @Override
  public void setEnabled() {
    button.setEnabled();
  }

  @Override
  public void show() {
    button.show();
  }

  @Override
  public void subscribeWith(final Subscriber<Void> subscriber) {
    button.subscribeWith(subscriber);
  }

  @Override
  public void unwrap() {
    button.unwrap();
    button = GwtQueryWrappingButton.EMPTY;
  }

  @Override
  public void wrap() {
    button = new GwtQueryButton(selector);
  }
}
