package com.ventana.gwt.mobilebrowser.client.url;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ABase64Should {
  private static final String DECODING = "107.22.162.235/FakeIMS_Web/FakeIMS/Jp2Images/test/test2|9";
  private static final String ENCODING = "MTA3LjIyLjE2Mi4yMzUvRmFrZUlNU19XZWIvRmFrZUlNUy9KcDJJbWFnZXMvdGVzdC90ZXN0Mnw5";

  @Test
  public void encode() {
    assertEquals(DECODING, Base64.decode(ENCODING));
  }

  @Test
  public void decode() {
    assertEquals(ENCODING, Base64.encode(DECODING));
  }
}