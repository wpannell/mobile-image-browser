package com.ventana.gwt.mobilebrowser.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public abstract class TestableComposite extends Composite {

  public TestableComposite() {
    super();
  }

  @Override
  protected void initWidget(final Widget widget) { //...make class junit-testable...
    if (GWT.isClient())
      super.initWidget(widget);
  }
}