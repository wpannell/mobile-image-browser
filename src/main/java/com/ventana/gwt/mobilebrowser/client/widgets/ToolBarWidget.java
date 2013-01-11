package com.ventana.gwt.mobilebrowser.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.ventana.gwt.mobilebrowser.client.commands.BrowserBackCommand;
import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.views.ToolBarView;

public class ToolBarWidget extends TestableComposite implements ToolBarView {
  @UiTemplate("ToolBarWidget.ui.xml")
  public interface ToolBarWidgetUiBinder
      extends UiBinder<Widget, ToolBarWidget> {}

  @UiField com.github.gwtbootstrap.client.ui.Button addButton;
  private Subscriber<Void> addButtonClickedSubscriber;
  @UiField com.github.gwtbootstrap.client.ui.Button backButton;
  final BrowserBackCommand browserBackCommand;
  @UiField HTMLPanel container;
  @UiField com.github.gwtbootstrap.client.ui.Button informationText;

  public ToolBarWidget() {
    this(new BrowserBackCommand(),
        GWT.<ToolBarWidgetUiBinder> create(ToolBarWidgetUiBinder.class));
  }

  @Inject
  public ToolBarWidget(final BrowserBackCommand browserBackCommand,
      final ToolBarWidgetUiBinder binder) {
    this.browserBackCommand = browserBackCommand;
    initWidget(binder.createAndBindUi(this));
  }

  @Override
  public void goBrowserBack() {
    browserBackCommand.execute();
  }

  @Override
  public void isAddButtonShowing(final boolean shouldShow) {
    addButton.setVisible(shouldShow);
  }

  @Override
  public void isBackButtonShowing(final boolean shouldShow) {
    backButton.setVisible(shouldShow);
  }

  @Override
  public void isInformationTextShowing(final boolean shouldShow) {
    informationText.setVisible(shouldShow);
    informationText.setEnabled(!shouldShow);
  }

  @Override
  public boolean isShowing() {
    return container.isVisible();
  }

  @Override
  public void isShowing(final boolean shouldShow) {
    container.setVisible(shouldShow);
  }

  @Override
  public void setInformationText(final String zoom) {
    informationText.setText(zoom);
  }

  @Override
  public void whenAddButtonClickedNotify(final Subscriber<Void> subscriber) {
    addButtonClickedSubscriber = subscriber;
  }

  @UiHandler("addButton") @SuppressWarnings("unused") //...unused event param...
  void whenAddButtonClickedNotifySubscriber(final ClickEvent event) {
    addButtonClickedSubscriber.notifyWith(null);
  }

  @UiHandler("backButton") @SuppressWarnings("unused") //...unused event param...
  void whenBackButtonClickedNotifySubscriber(final ClickEvent event) {
    goBrowserBack();
  }
}
