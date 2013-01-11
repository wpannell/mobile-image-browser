package com.ventana.gwt.mobilebrowser.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.gwtplatform.tester.MockingBinder;
import com.ventana.gwt.mobilebrowser.client.commands.BrowserBackCommand;
import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.testdoubles.MockitoMockFactory;
import com.ventana.gwt.mobilebrowser.client.views.ToolBarView;
import com.ventana.gwt.mobilebrowser.client.widgets.ToolBarWidget.ToolBarWidgetUiBinder;

import static org.mockito.Mockito.verify;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JukitoRunner.class)
public class AToolbarWidgetShould {
  private ToolBarWidget toolBarWidget;
  private ToolBarView toolBarView;

  @Before
  @SuppressWarnings("unused") //fake uibinder not used outside of di...
  public void createFixtureWith(ToolBarWidgetUiBinder fakeToolBarWidgetUiBinder,
      ToolBarWidget toolBarWidget) {
    this.toolBarWidget = toolBarWidget;
    toolBarView = toolBarWidget;
  }

  @Test
  public void showAddButton() {
    toolBarView.isAddButtonShowing(ToolBarView.YES);
    verify(toolBarWidget.addButton).setVisible(ToolBarView.YES);
  }

  @Test
  public void hideAddButton() {
    toolBarView.isAddButtonShowing(ToolBarView.NO);
    verify(toolBarWidget.addButton).setVisible(ToolBarView.NO);
  }

  @Test
  public void showBackButton() {
    toolBarView.isBackButtonShowing(ToolBarView.YES);
    verify(toolBarWidget.backButton).setVisible(ToolBarView.YES);
  }

  @Test
  public void hideBackButton() {
    toolBarView.isBackButtonShowing(!ToolBarView.YES);
    verify(toolBarWidget.backButton).setVisible(!ToolBarView.YES);
  }

  @Test
  public void showInformationText() {
    toolBarView.isInformationTextShowing(ToolBarView.YES);
    verify(toolBarWidget.informationText).setVisible(ToolBarView.YES);
  }

  @Test
  public void hideInformationText() {
    toolBarView.isInformationTextShowing(!ToolBarView.YES);
    verify(toolBarWidget.informationText).setVisible(!ToolBarView.YES);
  }

  @Test
  public void tellIfShowing() {
    toolBarView.isShowing();
    verify(toolBarWidget.container).isVisible();
  }

  @Test
  public void show() {
    toolBarView.isShowing(ToolBarView.YES);
    verify(toolBarWidget.container).setVisible(ToolBarView.YES);
  }

  @Test
  public void hide() {
    toolBarView.isShowing(!ToolBarView.YES);
    verify(toolBarWidget.container).setVisible(!ToolBarView.YES);
  }

  @Test
  public void displayInformation(String text) {
    toolBarView.setInformationText(text);
    verify(toolBarWidget.informationText).setText(text);
  }

  @Test
  public void whenAddButtonClickedNotifiy(Subscriber<Void> subscriber,
      ClickEvent mockClickEvent) {
    toolBarView.whenAddButtonClickedNotify(subscriber);
    toolBarWidget.whenAddButtonClickedNotifySubscriber(mockClickEvent);

    verify(subscriber).notifyWith(null);
  }

  @Test
  public void goBack(ClickEvent mockClickEvent) {
    toolBarWidget.whenBackButtonClickedNotifySubscriber(mockClickEvent);
    verify(toolBarWidget.browserBackCommand).execute();
  }

  @AfterClass
  public static void cleanup() {
    GWTMockUtilities.restore();
  }

  public static class Module extends JukitoModule {
    public static class FakeToolBarWidgetUiBinder
        extends MockingBinder<Widget, ToolBarWidget>
        implements ToolBarWidgetUiBinder {
      @Inject
      public FakeToolBarWidgetUiBinder(MockitoMockFactory mockitoMockFactory) {
        super(Widget.class, mockitoMockFactory);
      }
    }

    @Override
    protected void configureTest() {
      GWTMockUtilities.disarm();
      forceMock(BrowserBackCommand.class);
      bind(ToolBarWidgetUiBinder.class).to(FakeToolBarWidgetUiBinder.class);
    }
  }
}
