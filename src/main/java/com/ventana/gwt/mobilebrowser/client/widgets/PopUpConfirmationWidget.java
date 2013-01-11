package com.ventana.gwt.mobilebrowser.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.Modal;
import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.views.PopUpConfirmationView;

public class PopUpConfirmationWidget extends TestableComposite
    implements PopUpConfirmationView {
  private static final boolean SHOULD_SHOW = true;

  @UiTemplate("PopUpConfirmationWidget.ui.xml")
  public interface PopUpConfirmationWidgetUiBinder
      extends UiBinder<Widget, PopUpConfirmationWidget> {}

  @UiField Modal modal;
  @UiField Heading question;
  @UiField com.github.gwtbootstrap.client.ui.Button cancelButton;

  private Subscriber<Void> confirmedOkSubscriber;

  public PopUpConfirmationWidget() {
    this(GWT.<PopUpConfirmationWidgetUiBinder>
        create(PopUpConfirmationWidgetUiBinder.class));
  }

  @Inject
  public PopUpConfirmationWidget(final PopUpConfirmationWidgetUiBinder binder) {
    initWidget(binder.createAndBindUi(this));
    confirmedOkSubscriber = Subscriber.VOID_EMPTY;
    modal.setTitle("Confirmation");
    question.setText("Are you sure?");
  }

  public void hide() {
    modal.hide();
  }

  @Override
  public void popUpWith(final String title, final String question) {
    isCancelButtonShowing(SHOULD_SHOW);
    showTitle(title);
    showQuestion(question);
    show();
  }

  @Override
  public void showWith(final String message) {
    cancelButton.setVisible(!SHOULD_SHOW);
    showTitle("Message");
    showQuestion(message);
    show();
  }

  public void show() {
    modal.show();
  }

  @Override
  public void whenConfiremdNotify(final Subscriber<Void> subscriber) {
    confirmedOkSubscriber = subscriber;
  }

  @UiHandler("cancelButton") @SuppressWarnings("unused") //unused event param...
  void whenCancelButtonClickedHide(final ClickEvent event) {
    hide();
  }

  @UiHandler("okButton") @SuppressWarnings("unused")  //unused event param...
  void whenSaveButtonClickedNotifySubscriber(final ClickEvent event) {
    confirmedOkSubscriber.notifyWith(null);
    hide();
  }

  private void isCancelButtonShowing(final boolean shouldShow) {
    cancelButton.setVisible(shouldShow);
  }

  private void showQuestion(final String question) {
    this.question.setText(question);
  }

  private void showTitle(final String title) {
    modal.setTitle(title);
  }
}