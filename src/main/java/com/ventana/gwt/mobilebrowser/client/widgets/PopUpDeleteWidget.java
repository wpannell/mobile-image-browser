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
import com.ventana.gwt.mobilebrowser.client.views.PopUpDeleteView;

public class PopUpDeleteWidget extends TestableComposite
    implements PopUpDeleteView {
  @UiTemplate("PopUpDeleteWidget.ui.xml")
  public interface PopUpDeleteWidgetUiBinder
      extends UiBinder<Widget, PopUpDeleteWidget> {}

  @UiField Modal modal;
  @UiField Heading question;

  private Subscriber<Void> canceledSubscriber = Subscriber.VOID_EMPTY;
  private Subscriber<Void> confirmedSubscriber = Subscriber.VOID_EMPTY;

  public PopUpDeleteWidget() {
    this(GWT.<PopUpDeleteWidgetUiBinder> create(PopUpDeleteWidgetUiBinder.class));
  }

  @Inject
  public PopUpDeleteWidget(final PopUpDeleteWidgetUiBinder binder) {
    initWidget(binder.createAndBindUi(this));
  }

  @Override
  public void close() {
    hide();
  }

  @Override
  public void whenCanceled(final Subscriber<Void> subscriber) {
    this.canceledSubscriber = subscriber;
  }

  @Override
  public void whenConfirmed(final Subscriber<Void> subscriber) {
    this.confirmedSubscriber = subscriber;
  }

  @Override
  public void popUpWith(final String title, final String question) {
    showTitle(title);
    showQuestion(question);
    show();
  }

  @UiHandler("cancelButton") @SuppressWarnings("unused")
  void whenCancelClicked(final ClickEvent event) {
    canceledSubscriber.notifyWith(null);
  }

  @UiHandler("okButton") @SuppressWarnings("unused")
  void whenOkClicked(final ClickEvent event) {
    confirmedSubscriber.notifyWith(null);
  }

  private void hide() {
    modal.hide();
  }

  private void show() {
    modal.show();
  }

  private void showQuestion(final String question) {
    this.question.setText(question);
  }

  private void showTitle(final String title) {
    modal.setTitle(title);
  }
}