package com.ventana.gwt.mobilebrowser.client.places;

import com.google.gwt.junit.GWTMockUtilities;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ARootFolderPlaceShould {
  private static final String HISTORY_TOKEN =
      "GIVEN_NAME$Y%http://107.22.162.235:80/FakeIMS_Web/FakeIMS|0";

  private static final String URL = "http://107.22.162.235:80/FakeIMS_Web/FakeIMS";

  private static final boolean YES = true;
  private Folder rootFolder;
  private Folder otherRootFolder;

  @Before
  public void createFixture() {
    GWTMockUtilities.disarm();
    rootFolder = new RootFolder(HISTORY_TOKEN);
    otherRootFolder = new RootFolder(HISTORY_TOKEN);
  }

  @Test
  public void convertToHtml() {
    assertThat(rootFolder.asHtml(), equalTo(
        "<li class='ImageFolder'>" +
          "<div>" +
            "<a href='javascript:void(0)'>" +
              "<img src='mobilebrowser/img/folderThumbnail.png'>" +
            "</a>" +
            "<p>GIVEN_NAME</p>" +
            "<a class='deleteLink' href='javascript:void(0)'>&nbsp;</a>" +
            "<a class='editLink' href='javascript:void(0)'>&nbsp;</a>" +
          "</div>" +
        "</li>"
    ));
  }

  @Test
  public void beEqual() {
    assertThat(otherRootFolder, equalTo(rootFolder));
  }

  @Test
  public void beRootOfHierarchy() {
    assertThat(rootFolder.isRoot(), is(YES));
  }

  @Test
  public void knowItsHistoryToken() {
    assertThat(rootFolder.getHistoryToken(), equalTo(HISTORY_TOKEN));
  }

  @Test
  public void knowItsUrl() {
    assertThat(rootFolder.getUrl(), equalTo(URL));
  }

  @Test
  public void haveParentUrlEqualToUrl() {
    assertThat(rootFolder.getUrl(), equalTo(rootFolder.getParentUrl()));
  }

  @Test
  public void knowItsName() {
    assertThat(rootFolder.getName(), equalTo("GIVEN_NAME"));
  }

  @Test
  public void haveNameDefaultToSubPath() {
    otherRootFolder = new RootFolder(URL);

    assertThat(otherRootFolder.getName(), equalTo(otherRootFolder.getSubPath()));
  }

  @Test
  public void knowRealRootFolderName() {
    assertThat(otherRootFolder.getSubPath(), equalTo("FakeIMS"));
  }

  @Test
  public void knowItsDefaultSelectedIndex() {
    assertThat(rootFolder.getSelectedIndex(), equalTo(0));
  }

  @Test
  public void knowRealRootFolderDefaultSelectedIndex() {
    assertThat(otherRootFolder.getSelectedIndex(), equalTo(0));
  }
}
