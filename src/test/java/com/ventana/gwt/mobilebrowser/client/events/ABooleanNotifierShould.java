package com.ventana.gwt.mobilebrowser.client.events;

import com.ventana.gwt.mobilebrowser.GwtTestWithUtils;
import com.ventana.gwt.mobilebrowser.client.events.Subscriber;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ABooleanNotifierShould extends GwtTestWithUtils {
  private static boolean wasNotified = false;
  private static final boolean EXPECTED_OK = true;

  private Subscriber<Boolean> booleanNotifierOk;
  private Subscriber<Boolean> booleanNotifierNotOk;

  @Before
  public void createTestFixture() {
    wasNotified = false;
    this.booleanNotifierOk = new Subscriber<Boolean>() {
      @Override
      public void notifyWith(final Boolean isOk) {
        assertTrue(isOk);
        wasNotified = true;
      }
    };

    this.booleanNotifierNotOk = new Subscriber<Boolean>() {
      @Override
      public void notifyWith(final Boolean isOk) {
        assertFalse(isOk);
        wasNotified = true;
      }
    };
  }

  @Test
  public void notifyOk() {
    booleanNotifierOk.notifyWith(EXPECTED_OK);
    assertTrue(wasNotified);
  }

  @Test
  public void notifyNotOk() {
    booleanNotifierNotOk.notifyWith(!EXPECTED_OK);
    assertTrue(wasNotified);
  }

  @Test
  public void notNotify() {
    booleanNotifierOk = Subscriber.BOOLEAN_EMPTY;
    booleanNotifierOk.notifyWith(EXPECTED_OK);
        assertFalse(wasNotified);
  }
}
