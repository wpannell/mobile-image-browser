package com.ventana.gwt.mobilebrowser.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.DropdownButton;
import com.github.gwtbootstrap.client.ui.HelpBlock;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.constants.ControlGroupType;
import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.views.PopUpEditView;

public class PopUpEditWidget extends TestableComposite
    implements PopUpEditView {
  @UiTemplate("PopUpEditWidget.ui.xml")
  public interface PopUpEditWidgetUiBinder
      extends UiBinder<Widget, PopUpEditWidget> {}

  @UiField HelpBlock badPathHelp;
  @UiField HelpBlock dataUnchangedHelp;
  @UiField InterrogatingControlGroup dataUnchangedValidator;
  @UiField HelpBlock duplicateNameHelp;
  @UiField HelpBlock duplicatePathHelp;
  @UiField HelpBlock emptyNameHelp;
  @UiField HelpBlock emptyPathHelp;
  @UiField DropdownButton httpDropDown;
  @UiField Modal modal;
  @UiField TextBox nameTextBox;
  @UiField InterrogatingControlGroup nameValidator;
  @UiField TextBox pathTextBox;
  @UiField InterrogatingControlGroup pathValidator;
  @UiField InterrogatingControlGroup protocolValidator;

  private Subscriber<Void> cancelClickedSubscriber;
  private Subscriber<Void> saveClickedSubscriber;
  private Subscriber<Void> validateClickedSubscriber;

  public PopUpEditWidget() {
    this(GWT.<PopUpEditWidgetUiBinder> create(PopUpEditWidgetUiBinder.class));
  }

  @Inject
  public PopUpEditWidget(final PopUpEditWidgetUiBinder binder) {
    initWidget(binder.createAndBindUi(this));
  }

  @Override
  public void clearValidations() {
    displayHelpFor(dataUnchangedValidator, ControlGroupType.NONE);
    displayHelpFor(nameValidator, ControlGroupType.NONE);
    displayHelpFor(protocolValidator, ControlGroupType.NONE);
    displayHelpFor(pathValidator, ControlGroupType.NONE);
    show(badPathHelp, !PopUpEditView.SHOULD_SHOW);
    show(dataUnchangedHelp, !PopUpEditView.SHOULD_SHOW);
    show(duplicateNameHelp, !PopUpEditView.SHOULD_SHOW);
    show(duplicatePathHelp, !PopUpEditView.SHOULD_SHOW);
    show(emptyNameHelp, !PopUpEditView.SHOULD_SHOW);
    show(emptyPathHelp, !PopUpEditView.SHOULD_SHOW);
  }

  @Override
  public void close() {
    modal.hide();
  }

  @Override
  public String getName() {
    return nameTextBox.getText();
  }

  @Override
  public String getPath() {
    return pathTextBox.getText();
  }

  @Override
  public String getProtocol() {
    return httpDropDown.getText().trim();
  }

  @Override
  public void isBadPathHelpShowing(final boolean shouldShow) {
    show(badPathHelp, shouldShow);
  }

  @Override
  public void isDataUnchangedHelpShowing(final boolean shouldShow) {
    show(dataUnchangedHelp, shouldShow);
    displayHelpFor(dataUnchangedValidator, shouldShow
        ? ControlGroupType.ERROR : ControlGroupType.NONE);
  }

  @Override
  public boolean isDataValid() {
    return dataUnchangedValidator.getType().equals(ControlGroupType.NONE)
        && nameValidator.getType().equals(ControlGroupType.NONE)
        && protocolValidator.getType().equals(ControlGroupType.NONE)
        && !nameTextBox.equals("")
        && !pathTextBox.equals("")
        && pathValidator.getType().equals(ControlGroupType.NONE);
  }

  @Override
  public void isDuplicateNameHelpShowing(final boolean shouldShow) {
    show(duplicateNameHelp, shouldShow);
  }

  @Override
  public void isDuplicatePathHelpShowing(final boolean shouldShow) {
    show(duplicatePathHelp, shouldShow);
  }

  @Override
  public void isEmptyNameHelpShowing(final boolean shouldShow) {
    show(emptyNameHelp, shouldShow);
  }

  @Override
  public void isEmptyPathHelpShowing(final boolean shouldShow) {
    show(emptyPathHelp, shouldShow);
  }

  @Override
  public void isNameHelpShowing(final boolean shouldShow) {
    displayHelpFor(nameValidator, shouldShow
        ? ControlGroupType.ERROR : ControlGroupType.NONE);
  }

  @Override
  public void isPathHelpShowing(final boolean shouldShow) {
    displayHelpFor(pathValidator, shouldShow
        ? ControlGroupType.ERROR : ControlGroupType.NONE);
  }

  @Override
  public void openWith(final String name, final String protocol,
      final String path) {
    nameTextBox.setText(name);
    httpDropDown.setText(protocol);
    pathTextBox.setText(path);
    show();
  }

  public void show() {
    modal.show();
  }

  @Override
  public void whenCancelClickedNotify(final Subscriber<Void> subscriber) {
    cancelClickedSubscriber = subscriber;
  }

  @Override
  public void whenSaveClickedNotify(final Subscriber<Void> subscriber) {
    saveClickedSubscriber = subscriber;
  }

  @Override
  public void whenValidateClickedNotify(final Subscriber<Void> subscriber) {
    validateClickedSubscriber = subscriber;
  }

  @UiHandler("cancelButton") @SuppressWarnings("unused") // ... unused event ...
  void whenCancelButtonClickedNotifySubscriber(final ClickEvent event) {
    cancelClickedSubscriber.notifyWith(null);
  }

  @UiHandler("httpLink")  @SuppressWarnings("unused") // ... unused event ...
  void whenHttpProtocolLinkClickedSetProtocol(final ClickEvent event) {
    httpDropDown.setText("http");
  }

  @UiHandler("httpsLink")  @SuppressWarnings("unused") // ... unused event ...
  void whenHttpsProtocolLinkClickedSetProtocol(final ClickEvent event) {
    httpDropDown.setText("https");
  }

  @UiHandler("saveButton")  @SuppressWarnings("unused") // ... unused event ...
  void whenSaveButtonClickedNotifySubscriber(final ClickEvent event) {
    saveClickedSubscriber.notifyWith(null);
  }

  @UiHandler("validateButton")  @SuppressWarnings("unused") // ... unused event ...
  void whenValidateButtonClickedNotifySubscriber(final ClickEvent event) {
    validateClickedSubscriber.notifyWith(null);
  }

  private void displayHelpFor(final ControlGroup field,
      final ControlGroupType controlGroupType) {
    field.setType(controlGroupType);
  }

  private void show(final HelpBlock help, final boolean shouldShow) {
    help.setVisible(shouldShow);
  }
}