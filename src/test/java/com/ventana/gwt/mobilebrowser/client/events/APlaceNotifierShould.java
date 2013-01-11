package com.ventana.gwt.mobilebrowser.client.events;

import com.ventana.gwt.mobilebrowser.GwtTestWithUtils;
import com.ventana.gwt.mobilebrowser.client.places.Folder;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;

import static junit.framework.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class APlaceNotifierShould extends GwtTestWithUtils {
  private static boolean wasNotified = false;
  private static final Explorer EXPECTED_FOLDER = new Folder();
  private static final Explorer EXPECTED_THUMBNAIL = new Thumbnail();

  private Subscriber<Explorer> folderNotifier;
  private Subscriber<Explorer> thumbnailNotifier;

  @Before
  public void createTestFixture() {
    wasNotified = false;
    this.folderNotifier = new Subscriber<Explorer>() {
      @Override
      public void notifyWith(final Explorer place) {
        assertEquals(place, EXPECTED_FOLDER);
        wasNotified = true;
      }
    };
    this.thumbnailNotifier = new Subscriber<Explorer>() {
      @Override
      public void notifyWith(final Explorer place) {
        assertEquals(place, EXPECTED_THUMBNAIL);
        wasNotified = true;
      }
    };
  }

  @Test
  public void notifyWithFolders() {
    wasNotified = false;
    folderNotifier.notifyWith(EXPECTED_FOLDER);
    assertTrue(wasNotified);
  }

  @Test
  public void notifyWithThumbnails() {
    wasNotified = false;
    thumbnailNotifier.notifyWith(EXPECTED_THUMBNAIL);
    assertTrue(wasNotified);
  }

  @Test
  public void notNotifyWithFolder() {
    wasNotified = false;
    folderNotifier = Subscriber.EXPLORER_EMPTY;
    folderNotifier.notifyWith(EXPECTED_FOLDER);
    assertFalse(wasNotified);
  }

  @Test
  public void notNotifyWithFile() {
    wasNotified = false;
    thumbnailNotifier = Subscriber.EXPLORER_EMPTY;
    thumbnailNotifier.notifyWith(EXPECTED_THUMBNAIL);
    assertFalse(wasNotified);
  }
}
