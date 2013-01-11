package com.ventana.gwt.mobilebrowser.client.places;

import com.google.gwt.junit.GWTMockUtilities;

import com.ventana.gwt.mobilebrowser.client.xml.FileXml;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import org.junit.Before;

public class AThumbnailShould {
  private static final String ANYTHING = "ANYTHING";
  protected static final boolean NO = false;
  protected FileXml dummyFileXml;
  protected Thumbnail thumbnail;
  protected Thumbnail nestedThumbnail;

  public AThumbnailShould() {
    super();
  }

  @Before
  public void createFixture() {
    GWTMockUtilities.disarm();

    dummyFileXml = mock(FileXml.class);
    given(dummyFileXml.getName()).willReturn("myNAME");
    given(dummyFileXml.getSize()).willReturn(ANYTHING);
    given(dummyFileXml.getTime()).willReturn(ANYTHING);

    thumbnail = new Thumbnail("127.128.129.130/SUBDIR", dummyFileXml);
    nestedThumbnail = new Thumbnail("127.128.129.130/SUBDIR/test", dummyFileXml);
  }

}