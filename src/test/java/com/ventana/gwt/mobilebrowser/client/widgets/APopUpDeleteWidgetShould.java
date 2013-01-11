package com.ventana.gwt.mobilebrowser.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.gwtplatform.tester.MockingBinder;
import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.testdoubles.MockitoMockFactory;
import com.ventana.gwt.mobilebrowser.client.views.PopUpDeleteView;
import com.ventana.gwt.mobilebrowser.client.widgets.PopUpDeleteWidget.PopUpDeleteWidgetUiBinder;

import static org.mockito.Mockito.verify;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JukitoRunner.class)
public class APopUpDeleteWidgetShould {
  private PopUpDeleteWidget popUpDeleteWidget;
  private PopUpDeleteView popUpDeleteView;

  @Before
  @SuppressWarnings("unused") //fake uibinder not used outside of di...
  public void createFixtureWith(
      PopUpDeleteWidgetUiBinder fakePopUpEditWidgetUiBinder,
      PopUpDeleteWidget popUpDeleteWidget) {
    this.popUpDeleteWidget = popUpDeleteWidget;
    popUpDeleteView = popUpDeleteWidget;
  }

  @Test
  public void close() {
    popUpDeleteView.close();
    verify(popUpDeleteWidget.modal).hide();
  }

  @Test
  public void notifyWhenDeleteClicked(Subscriber<Void> cancelSubscriber, ClickEvent event) {
    popUpDeleteView.whenCanceled(cancelSubscriber);
    popUpDeleteWidget.whenCancelClicked(event);
    verify(cancelSubscriber).notifyWith(null);
  }

  @Test
  public void notifyWhenOkClicked(Subscriber<Void> deleteSubscriber, ClickEvent event) {
    popUpDeleteView.whenConfirmed(deleteSubscriber);
    popUpDeleteWidget.whenOkClicked(event);
    verify(deleteSubscriber).notifyWith(null);
  }

  @Test
  public void popUp() {
    popUpDeleteView.popUpWith("title", "question");
    verify(popUpDeleteWidget.modal).setTitle("title");
    verify(popUpDeleteWidget.question).setText("question");
    verify(popUpDeleteWidget.modal).show();
  }

  @AfterClass
  public static void cleanup() {
    GWTMockUtilities.restore();
  }

  public static class Module extends JukitoModule {
    public static class FakePopUpDeleteWidgetUiBinder
        extends MockingBinder<Widget, PopUpDeleteWidget>
        implements PopUpDeleteWidgetUiBinder {
      @Inject
      public FakePopUpDeleteWidgetUiBinder(MockitoMockFactory mockitoMockFactory) {
        super(Widget.class, mockitoMockFactory);
      }
    }

    @Override
    protected void configureTest() {
      GWTMockUtilities.disarm();
      bind(PopUpDeleteWidgetUiBinder.class).
          to(FakePopUpDeleteWidgetUiBinder.class);
    }
  }
}
