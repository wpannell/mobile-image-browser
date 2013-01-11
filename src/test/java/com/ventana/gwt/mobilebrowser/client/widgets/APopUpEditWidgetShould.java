package com.ventana.gwt.mobilebrowser.client.widgets;

import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.github.gwtbootstrap.client.ui.constants.ControlGroupType;
import com.gwtplatform.tester.MockingBinder;
import com.ventana.gwt.mobilebrowser.client.testdoubles.MockitoMockFactory;
import com.ventana.gwt.mobilebrowser.client.views.PopUpEditView;
import com.ventana.gwt.mobilebrowser.client.widgets.PopUpEditWidget.PopUpEditWidgetUiBinder;

import static org.mockito.Mockito.verify;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JukitoRunner.class)
public class APopUpEditWidgetShould {
  private PopUpEditWidget popUpEditWidget;
  private PopUpEditView popUpEditView;

  @Before
  @SuppressWarnings("unused") //fake uibinder not used outside of di...
  public void createFixtureWith(
      PopUpEditWidgetUiBinder fakePopUpEditWidgetUiBinder,
      PopUpEditWidget popUpEditWidget) {
    this.popUpEditWidget = popUpEditWidget;
    popUpEditView = popUpEditWidget;
  }

  @Test
  public void clearValidations() {
    popUpEditView.clearValidations();
    verify(popUpEditWidget.dataUnchangedValidator).setType(
        ControlGroupType.NONE);
    verify(popUpEditWidget.nameValidator).setType(
        ControlGroupType.NONE);
    verify(popUpEditWidget.protocolValidator).setType(
        ControlGroupType.NONE);
    verify(popUpEditWidget.pathValidator).setType(
        ControlGroupType.NONE);
    verify(popUpEditWidget.badPathHelp).setVisible(!PopUpEditView.SHOULD_SHOW);
  }

  @AfterClass
  public static void cleanup() {
    GWTMockUtilities.restore();
  }

  public static class Module extends JukitoModule {
    public static class FakePopUpEditWidgetUiBinder
        extends MockingBinder<Widget, PopUpEditWidget>
        implements PopUpEditWidgetUiBinder {
      @Inject
      public FakePopUpEditWidgetUiBinder(MockitoMockFactory mockitoMockFactory) {
        super(Widget.class, mockitoMockFactory);
      }
    }

    @Override
    protected void configureTest() {
      GWTMockUtilities.disarm();
      bind(PopUpEditWidgetUiBinder.class).
          to(FakePopUpEditWidgetUiBinder.class);
    }
  }
}
