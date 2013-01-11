package com.ventana.gwt.mobilebrowser.client.places;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AThumbnailPlaceShould extends AThumbnailShould {
  private static final String BASE_URL = "http://127.128.129.130:80";

  @Test
  public void knowEquality() {
    assertThat(new Thumbnail("127.128.129.130/SUBDIR", dummyFileXml),
        equalTo(thumbnail));
    assertThat(thumbnail, not(equalTo(nestedThumbnail)));
  }

  @Test
  public void convertToHtml() {
    assertThat(thumbnail.asHtml(), equalTo(
        Thumbnail.PREFIX + BASE_URL + "/SUBDIR/myNAME" +
        Thumbnail.THUMBNAIL_SUFFIX + Thumbnail.INFIX + "myNAME" +
        Thumbnail.SUFFIX));
  }

  @Test
  public void knowHistoryToken() {
    assertThat(thumbnail.getHistoryToken(),
        equalTo("myNAME$" + BASE_URL + "/SUBDIR/myNAME|0"));
  }

  @Test
  public void knowParentUrl() {
    assertThat(thumbnail.getParentUrl(), equalTo(BASE_URL + "/SUBDIR"));
  }

  @Test
  public void knowParentUrlWhenNested() {
    assertThat(nestedThumbnail.getHistoryToken(),
        equalTo("myNAME$" + BASE_URL + "/SUBDIR/test/myNAME|0"));
    assertThat(nestedThumbnail.getParentUrl(), equalTo(BASE_URL + "/SUBDIR/test"));
  }

  @Test
  public void notBeRootHierarchy() {
    assertThat(thumbnail.isRoot(), equalTo(NO));
  }

  @Test
  public void neverBeHierarchyRoot() {
    Thumbnail thumb = new Thumbnail("127.128.129.130");
    assertThat(thumb.getHistoryToken(), equalTo(BASE_URL + "|0"));
    assertThat(thumb.getParentUrl(), equalTo(BASE_URL ));
    assertThat(thumb.isRoot(), equalTo(NO));
  }
}
