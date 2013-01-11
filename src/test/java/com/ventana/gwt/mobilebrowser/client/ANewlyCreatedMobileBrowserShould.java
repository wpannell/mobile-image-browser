package com.ventana.gwt.mobilebrowser.client;

import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import com.ventana.gwt.mobilebrowser.client.activities.ContentActivityManager;
import com.ventana.gwt.mobilebrowser.client.activities.HostPageActivityManager;
import com.ventana.gwt.mobilebrowser.client.containers.ContentContainer;
import com.ventana.gwt.mobilebrowser.client.containers.HostPageContainer;
import com.ventana.gwt.mobilebrowser.client.factories.HistoryHandlerFactory;
import com.ventana.gwt.mobilebrowser.client.history.HistoryHandler;
import com.ventana.gwt.mobilebrowser.client.places.Folder;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ANewlyCreatedMobileBrowserShould {
  @Mock private HostPageActivityManager mockHostPageActivityManager;
  @Mock private ContentActivityManager mockContentActivityManager;
  @Mock private HistoryHandlerFactory mockHistoryHandlerFactory;
  @Mock private HistoryHandler mockHistoryHandler;
  @Mock private Folder mockDefaultPlace;
  private HostPageContainer mockHostPageContainer;
  private ContentContainer mockContentContainer;
  private RootLayoutPanel mockRootPanel;

  private MobileBrowser application;

  @Before
  public void createFixture() {
    GWTMockUtilities.disarm();
    mockHostPageContainer = mock(HostPageContainer.class);
    mockContentContainer = mock(ContentContainer.class);
    mockRootPanel = mock(RootLayoutPanel.class);

    application = new MobileBrowser(mockHostPageActivityManager,
        mockHostPageContainer, mockContentActivityManager, mockContentContainer, mockHistoryHandlerFactory);
  }

  @Test
  public void runApp() {
    given(mockHistoryHandlerFactory.createWith(mockDefaultPlace)).
        willReturn(mockHistoryHandler);
    application.runWith(mockRootPanel, mockDefaultPlace);
    verify(mockHostPageActivityManager).setDisplay(mockHostPageContainer);
    verify(mockContentActivityManager).setDisplay(mockContentContainer);
    verify(mockRootPanel).add(mockHostPageContainer);
    verify(mockHistoryHandlerFactory).createWith(mockDefaultPlace);
  }
}