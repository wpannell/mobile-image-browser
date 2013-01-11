package com.ventana.gwt.mobilebrowser.client.places;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AnImagePlaceShould extends AThumbnailShould {

  private Image image;
  private Image sameImage;
  private Image aDifferentImage;
  private int[][] expected;

  @Override
  public void createFixture() {
    super.createFixture();
    expected = new int[][] {
        {56976, 19088},
        {28488, 9544},
        {14244, 4772},
        {7122, 2386},
        {3561, 1193},
        {1781, 597},
        {891, 299},
        {446, 150},
        {223, 75}};

    image = new Image(thumbnail);
    sameImage = new Image("myNAME$http://127.128.129.130/SUBDIR/myNAME");
    aDifferentImage = new Image(nestedThumbnail);
  }

  @Test
  public void knowEquality() {
    assertThat(image, equalTo(sameImage));
  }

  @Test
  public void notKnowEquality() {
    assertThat(image, not(equalTo(aDifferentImage)));
  }

  @Test
  public void knowHistoryToken() {
    assertThat(image.getHistoryToken(),
        equalTo("myNAME$http://127.128.129.130:80/SUBDIR/myNAME|0"));
  }

  @Test
  public void knowHost() {
    assertThat(image.getHost(), equalTo("127.128.129.130"));
  }

  @Test
  public void knowName() {
    assertThat(image.getName(), equalTo("myNAME"));
  }

  @Test
  public void knowParentPath() {
    assertThat(image.getParentPath(), equalTo("/SUBDIR"));
  }

  @Test
  public void knowPath() {
    assertThat(image.getPath(), equalTo("/SUBDIR/myNAME"));
  }

  @Test
  public void knowPort() {
    assertThat(image.getPort(), equalTo(":80"));
  }

  @Test
  public void knowProtocol() {
    assertThat(image.getProtocol(), equalTo("http://"));
  }

  @Test
  public void knowDefaultQualityLevels() {
    assertArrayEquals(expected, image.getQualityLevels());
  }
}
