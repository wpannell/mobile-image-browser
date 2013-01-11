package com.ventana.gwt.mobilebrowser.client.places;

import com.google.gwt.junit.GWTMockUtilities;

import com.ventana.gwt.mobilebrowser.client.xml.FolderXml;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AFolderPlaceShould {
  private static final String URL = "127.128.129.130";
  private static final boolean NO = false;
  private FolderXml dummyFolderXml;
  private Folder folder;
  private Folder nestedFolder;

  @Before
  public void createFixture() {
    GWTMockUtilities.disarm();
    dummyFolderXml = mock(FolderXml.class);
    given(dummyFolderXml.getName()).willReturn("myNAME");
    folder = new Folder(URL, dummyFolderXml);
    nestedFolder = new Folder("127.128.129.130/test", dummyFolderXml);
  }

  @Test
  public void knowEquality() {
    assertThat(new Folder(URL, dummyFolderXml), equalTo(folder));
    assertThat(folder, not(equalTo(nestedFolder)));
  }

  @Test
  public void convertToHtml() {
    assertThat(folder.asHtml(), equalTo(
        "<li class='ImageFolder'>" +
          "<div>" +
            "<a href='javascript:void(0)'>" +
              "<img src='mobilebrowser/img/folderThumbnail.png'>" +
            "</a>" +
            "<p>myNAME</p>" +
          "</div>" +
        "</li>"
    ));
  }

  @Test
  public void getHistoryToken() {
    assertThat(folder.getHistoryToken(), equalTo(
        "myNAME$http://127.128.129.130:80/myNAME|0"));
  }

  @Test
  public void getParentUrl() {
    assertThat(folder.getParentUrl(), equalTo("http://127.128.129.130:80"));
  }

  @Test
  public void getNestedParentUrl() {
    assertThat(nestedFolder.getParentUrl(), equalTo("http://127.128.129.130:80/test" ));
  }

  @Test
  public void notBeHierarchyRoot() {
    assertThat(folder.isRoot(), equalTo(NO));
    assertThat(nestedFolder.isRoot(), equalTo(NO));
  }
}
