package com.ventana.gwt.mobilebrowser.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.gwtplatform.tester.MockingBinder;
import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.testdoubles.MockitoMockFactory;
import com.ventana.gwt.mobilebrowser.client.views.PopUpConfirmationView;
import com.ventana.gwt.mobilebrowser.client.widgets.PopUpConfirmationWidget.PopUpConfirmationWidgetUiBinder;

import static org.mockito.Mockito.verify;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JukitoRunner.class)
public class APopUpConfirmationWidgetShould {
  private PopUpConfirmationWidget popUpConfirmationWidget;
  private PopUpConfirmationView popUpConfirmationView;

  @Before
  @SuppressWarnings("unused") //fake uibinder not used outside of di...
  public void createFixtureWith(
      PopUpConfirmationWidgetUiBinder fakePopUpConfirmationWidgetUiBinder,
      PopUpConfirmationWidget popUpConfirmationWidget) {
    this.popUpConfirmationWidget = popUpConfirmationWidget;
    popUpConfirmationView = popUpConfirmationWidget;
  }

  @Test
  public void poUpWith(String title, String question) {
    popUpConfirmationView.popUpWith(title, question);
    verify(popUpConfirmationWidget.cancelButton).setVisible(true);
    verify(popUpConfirmationWidget.modal).setTitle(title);
    verify(popUpConfirmationWidget.question).setText(question);
    verify(popUpConfirmationWidget.modal).show();
  }

  @Test
  public void showWith(String message) {
    popUpConfirmationView.showWith(message);
    verify(popUpConfirmationWidget.cancelButton).setVisible(false);
    verify(popUpConfirmationWidget.modal).setTitle("Message");
    verify(popUpConfirmationWidget.question).setText(message);
    verify(popUpConfirmationWidget.modal).show();
  }

  @Test
  public void whenConfiremdNotify(Subscriber<Void> subscriber,
      ClickEvent mockClickEvent) {
    popUpConfirmationView.whenConfiremdNotify(subscriber);
    popUpConfirmationWidget.whenSaveButtonClickedNotifySubscriber(mockClickEvent);

    verify(subscriber).notifyWith(null);
    verify(popUpConfirmationWidget.modal).hide();
  }

  @Test
  public void cancel(ClickEvent mockClickEvent) {
    popUpConfirmationWidget.whenSaveButtonClickedNotifySubscriber(mockClickEvent);
    verify(popUpConfirmationWidget.modal).hide();
  }

  @AfterClass
  public static void cleanup() {
    GWTMockUtilities.restore();
  }

  public static class Module extends JukitoModule {
    public static class FakePopUpConfirmationWidgetUiBinder
        extends MockingBinder<Widget, PopUpConfirmationWidget>
        implements PopUpConfirmationWidgetUiBinder {
      @Inject
      public FakePopUpConfirmationWidgetUiBinder(MockitoMockFactory mockitoMockFactory) {
        super(Widget.class, mockitoMockFactory);
      }
    }

    @Override
    protected void configureTest() {
      GWTMockUtilities.disarm();
      bind(PopUpConfirmationWidgetUiBinder.class).
          to(FakePopUpConfirmationWidgetUiBinder.class);
    }
  }
}
