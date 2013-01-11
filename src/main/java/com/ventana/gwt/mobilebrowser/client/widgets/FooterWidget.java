package com.ventana.gwt.mobilebrowser.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class FooterWidget extends Composite {
  @UiTemplate("FooterWidget.ui.xml")
  public interface FooterWidgetUiBinder extends UiBinder<Widget, FooterWidget> {}

  public FooterWidget() {
    this(GWT.<FooterWidgetUiBinder> create(FooterWidgetUiBinder.class));
  }

  @Inject
  public FooterWidget(final FooterWidgetUiBinder binder) {
    initWidget(binder.createAndBindUi(this));
  }
}