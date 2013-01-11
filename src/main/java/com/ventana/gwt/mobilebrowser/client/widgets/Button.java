package com.ventana.gwt.mobilebrowser.client.widgets;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;

public interface Button {
  public abstract void click();
  public abstract void hide();
  public abstract boolean isEnabled();
  public abstract boolean isVisible();
  public abstract void setDisabled();
  public abstract void setEnabled();
  public abstract void isEnabled(final boolean shouldEnable);
  public abstract void show();
  public abstract void subscribeWith(final Subscriber<Void> subscriber);
  public abstract void wrap();
  public abstract void unwrap();
}