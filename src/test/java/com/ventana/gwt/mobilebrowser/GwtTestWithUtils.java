package com.ventana.gwt.mobilebrowser;

import com.googlecode.gwt.test.GwtTestWithMockito;

public abstract class GwtTestWithUtils extends GwtTestWithMockito {
  public GwtTestWithUtils() {
    super();
  }
  @Override
  public String getModuleName() {
    return GwtSuite.TEST_MODULE_NAME;
  }
}