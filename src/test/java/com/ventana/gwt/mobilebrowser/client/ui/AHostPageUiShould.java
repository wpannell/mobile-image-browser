package com.ventana.gwt.mobilebrowser.client.ui;

import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.gwtplatform.tester.MockingBinder;
import com.ventana.gwt.mobilebrowser.client.containers.ContentContainer;
import com.ventana.gwt.mobilebrowser.client.testdoubles.MockitoMockFactory;
import com.ventana.gwt.mobilebrowser.client.ui.HostPageUi.HostPageUiBinder;
import com.ventana.gwt.mobilebrowser.client.views.HostPageView;
import com.ventana.gwt.mobilebrowser.client.widgets.PopUpConfirmationWidget;
import com.ventana.gwt.mobilebrowser.client.widgets.PopUpEditWidget;
import com.ventana.gwt.mobilebrowser.client.widgets.PopUpImageInformationWidget;
import com.ventana.gwt.mobilebrowser.client.widgets.ToolBarWidget;

import static org.mockito.Mockito.verify;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JukitoRunner.class)
public class AHostPageUiShould {
  private HostPageUi hostPageUi;
  private HostPageView hostPageView;

  @Before
  @SuppressWarnings("unused") //fake uibinder not used outside of di...
  public void createFixtureWith(HostPageUiBinder fakeHostPageUiBinder,
      HostPageUi hostPageUi) {
    this.hostPageUi = hostPageUi;
    hostPageView = hostPageUi;
  }

  @Test
  public void addMeToThis(AcceptsOneWidget mockContainer) {
    hostPageView.addMeToThis(mockContainer);
    verify(mockContainer).setWidget(hostPageUi);
  }

  @AfterClass
  public static void cleanup() {
    GWTMockUtilities.restore();
  }

  public static class Module extends JukitoModule {
    public static class FakeHostPageUiBinder
        extends MockingBinder<Widget, HostPageUi> implements HostPageUiBinder {
      @Inject
      public FakeHostPageUiBinder(MockitoMockFactory mockitoMockFactory) {
        super(Widget.class, mockitoMockFactory);
      }
    }

    @Override
    protected void configureTest() {
      GWTMockUtilities.disarm();
      forceMock(ToolBarWidget.class);
      forceMock(ContentContainer.class);
      forceMock(PopUpConfirmationWidget.class);
      forceMock(PopUpEditWidget.class);
      forceMock(PopUpImageInformationWidget.class);

      bind(HostPageUiBinder.class).to(FakeHostPageUiBinder.class);
    }
  }
}
