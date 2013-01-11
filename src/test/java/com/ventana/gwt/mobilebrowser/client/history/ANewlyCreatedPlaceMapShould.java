package com.ventana.gwt.mobilebrowser.client.history;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;

import com.ventana.gwt.mobilebrowser.client.history.maps.places.PlaceHistoryMap;
import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.Folder;
import com.ventana.gwt.mobilebrowser.client.places.Image;
import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;

public class ANewlyCreatedPlaceMapShould extends GWTTestCase {
  private static final String URL = "http://127.128.129.130:80";
  private static final String URL2 = "http://127.0.0.0:80";

  private static final String ROOT_FOLDER_HISTORY_TOKEN =
      "FolderExplorer:Y%http://127.0.0.0:80|11";

  private static final String FOLDER_HISTORY_TOKEN =
      "FolderExplorer:http://127.0.0.0:80|11";

  private static final String THUMBNAIL_HISTORY_TOKEN =
      "ThumbnailExplorer:http://127.0.0.0:80|2233";

  private static final String IMAGE_HISTORY_TOKEN =
      "ImageViewer:myNAME$http://127.128.129.130:80/SUBDIR/myNAME|0";

  private static final String HOME_HISTORY_TOKEN =
      "Directory:";

  private PlaceHistoryMap placeMap;

  @Override
  protected void gwtSetUp() {
    placeMap = GWT.create(PlaceHistoryMap.class);
  }

  public void testKnowImagePlace() {
    final Image place = (Image)placeMap.getPlace(IMAGE_HISTORY_TOKEN);
    String historyToken = place.getHistoryToken();
    assertEquals("myNAME$" + URL + "/SUBDIR/myNAME|0", historyToken);
  }

  public void testKnowImageHistoryToken() {
    final Image place = new Image("myNAME$127.128.129.130/SUBDIR/myNAME");
    String token = placeMap.getToken(place);
    assertEquals(IMAGE_HISTORY_TOKEN, token);
  }

  public void testKnowDirectoryPlace() {
    final Directory place = (Directory)placeMap.getPlace(HOME_HISTORY_TOKEN);
    String historyToken = place.getHistoryToken();
    assertEquals("", historyToken);
  }

  public void testKnowDirectoryHistoryToken() {
    final Directory place = new Directory("127.0.0.0");
    String token = placeMap.getToken(place);
    assertEquals(HOME_HISTORY_TOKEN, token);
  }

  public void testKnowRootFolderPlace() {
    final Folder folder = (Folder)placeMap.getPlace(ROOT_FOLDER_HISTORY_TOKEN);
    String historyToken = folder.getHistoryToken();
    assertEquals("Y%" + URL2 + "|11", historyToken);
    assertEquals(11, folder.getSelectedIndex());
    assertTrue(folder.isRoot());
  }

  public void testKnowRootFolderHistoryToken() {
    final Folder folder = new Folder("Y%127.0.0.0|11");
    String token = placeMap.getToken(folder);
    assertEquals(ROOT_FOLDER_HISTORY_TOKEN, token);
  }

  public void testKnowFolderPlace() {
    final Folder folder = (Folder)placeMap.getPlace(FOLDER_HISTORY_TOKEN);
    String historyToken = folder.getHistoryToken();
    assertEquals(URL2 + "|11", historyToken);
    assertEquals(11, folder.getSelectedIndex());
    assertFalse(folder.isRoot());
  }

  public void testKnowFolderHistoryToken() {
    final Folder folder = new Folder("127.0.0.0|11");
    String token = placeMap.getToken(folder);
    assertEquals(FOLDER_HISTORY_TOKEN, token);
  }

  public void testKnowThumbnailPlace() {
    final Thumbnail thumbnail = (Thumbnail)placeMap.getPlace(THUMBNAIL_HISTORY_TOKEN);
    String historyToken = thumbnail.getHistoryToken();
    assertEquals(URL2 + "|2233", historyToken);
    assertEquals(2233, thumbnail.getSelectedIndex());
    assertFalse(thumbnail.isRoot());
  }

  public void testKnowThumbnailHistoryToken() {
    final Thumbnail thumbnail = new Thumbnail("http://127.0.0.0:80|2233");
    String token = placeMap.getToken(thumbnail);
    assertEquals(THUMBNAIL_HISTORY_TOKEN, token);
  }

  @Override
  public String getModuleName() {
    return "Integration";
  }
}
