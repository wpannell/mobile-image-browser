package com.ventana.gwt.mobilebrowser.server.responses;

import com.ventana.gwt.mobilebrowser.client.presenters.PopUpEditPresenter;
import com.ventana.gwt.mobilebrowser.client.views.PopUpEditView;
import com.ventana.gwt.mobilebrowser.server.callbacks.GetImagesServiceResponse;
import com.ventana.gwt.mobilebrowser.server.responses.GetExplorerValidationServiceResponse;
import com.ventana.gwt.mobilebrowser.server.responses.ImagesServiceResponse;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AGetExplorerValidationServiceResponseShould {
  @Mock private PopUpEditView mockView;
  @Mock private ImagesServiceResponse mockResponse;
  private GetImagesServiceResponse callback;

  @Before
  public void createFixture() {
    callback = new GetExplorerValidationServiceResponse(mockView);
  }

  @Test
  public void showUrlValidationOk() {
    given(mockResponse.isValid()).willReturn(PopUpEditPresenter.YES);
    callback.got(mockResponse);
    verify(mockView).isBadPathHelpShowing(PopUpEditPresenter.NO);
    verify(mockView).isPathHelpShowing(PopUpEditPresenter.NO);
  }

  @Test
  public void showBadUrlValidationError() {
    given(mockResponse.isValid()).willReturn(PopUpEditPresenter.NO);
    callback.got(mockResponse);
    verify(mockView).isBadPathHelpShowing(PopUpEditPresenter.YES);
    verify(mockView).isPathHelpShowing(PopUpEditPresenter.YES);
  }
}