package com.ventana.gwt.mobilebrowser.client;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;

import com.ventana.gwt.mobilebrowser.client.activities.ContentActivityManager;
import com.ventana.gwt.mobilebrowser.client.activities.HostPageActivityManager;
import com.ventana.gwt.mobilebrowser.client.containers.ContentContainer;
import com.ventana.gwt.mobilebrowser.client.containers.HostPageContainer;
import com.ventana.gwt.mobilebrowser.client.factories.HistoryHandlerFactory;

public class MobileBrowser {
  private final HistoryHandlerFactory historyHandlerFactory;
  private final HostPageActivityManager hostPageActivityManager;
  private final HostPageContainer hostPageContainer;
  private final ContentActivityManager contentActivityManager;
  private final ContentContainer contentContainer;

  @Inject
  public MobileBrowser(
      final HostPageActivityManager hostPageActivityManager,
      final HostPageContainer hostPageContainer,
      final ContentActivityManager contentActivityManager,
      final ContentContainer contentContainer,
      final HistoryHandlerFactory historyHandlerFactory) {
    this.hostPageActivityManager = hostPageActivityManager;
    this.hostPageContainer = hostPageContainer;
    this.contentActivityManager = contentActivityManager;
    this.contentContainer = contentContainer;
    this.historyHandlerFactory = historyHandlerFactory;
  }

  public void runWith(final RootLayoutPanel rootLayoutPanel,
      final Place defaultPlace) {
    rootLayoutPanel.add(hostPageContainer);
    hostPageActivityManager.setDisplay(hostPageContainer);
    contentActivityManager.setDisplay(contentContainer);
    historyHandlerFactory.createWith(defaultPlace).handleCurrentHistory();
  }
}
