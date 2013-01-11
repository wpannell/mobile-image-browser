package com.ventana.gwt.mobilebrowser.server.responses;

import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;
import com.ventana.gwt.mobilebrowser.client.views.ImageInformationView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpConfirmationView;
import com.ventana.gwt.mobilebrowser.server.callbacks.GetImagesServiceResponse;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AGetImageInformationServiceResponseShould {
  @Mock private ImageInformationView mockView;
  @Mock private PopUpConfirmationView mockPopupView;
  @Mock private ImagesServiceResponse mockResponse;
  @Mock private Thumbnail mockThumbnail;
  private GetImagesServiceResponse callback;

  @Before
  public void createFixture() {
    callback = new GetImageInformationServiceResponse(mockView, mockPopupView);
  }

  @Test
  public void showInformationForFile() {
    given(mockResponse.isValid()).willReturn(true);
    given(mockResponse.getImageExplorerPlace()).willReturn(mockThumbnail);
    callback.got(mockResponse);
    verify(mockThumbnail).loadImageInformationFor(mockView);
  }

  @Test
  public void notShowInformationForFile() {
    given(mockResponse.isValid()).willReturn(false);
    given(mockResponse.getErrorMessage()).willReturn("some error");
    callback.got(mockResponse);
    verify(mockPopupView).showWith("some error");
  }
}