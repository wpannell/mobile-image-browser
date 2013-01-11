package com.ventana.gwt.mobilebrowser.client.widgets;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.RootPanel;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.testdoubles.FakeUi;

public class ANewlyCreatedGwtQueryButtonShould extends GWTTestCase {
  private static final String SELECTOR = "a.to_nav";
  private static final int TIMEOUT = 500;
  private GwtQueryButton button;

  @Override
  protected void gwtSetUp() {
    RootPanel.get().add(new FakeUi());
    button = new GwtQueryButton(SELECTOR);
  }

  public void testClick() {
    delayTestFinish(TIMEOUT);
    button.subscribeWith(new Subscriber<Void>() {
      @Override
      public void notifyWith(@SuppressWarnings("unused") Void notification) {
        finishTest();
      }
    });
    button.click();
  }

  public void testKnowEnabledState() {
    assertTrue(button.isEnabled());
  }

  public void testSetDisabledState() {
    button.setDisabled();
    assertFalse(button.isEnabled());
    button.isEnabled(true);
    assertTrue(button.isEnabled());
  }

  public void testSetEnabledState() {
    button.setEnabled();
    assertTrue(button.isEnabled());
    button.isEnabled(false);
    assertFalse(button.isEnabled());
  }

  public void testKnowVisibleState() {
    assertTrue(button.isVisible());
  }

  public void testShow() {
    button.show();
    assertTrue(button.isVisible());
  }

  public void testHide() {
    button.hide();
    assertFalse(button.isVisible());
  }

  @Override
  public String getModuleName() {
    return "Integration";
  }
}