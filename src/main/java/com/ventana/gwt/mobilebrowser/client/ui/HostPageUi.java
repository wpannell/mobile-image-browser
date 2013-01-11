package com.ventana.gwt.mobilebrowser.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.ventana.gwt.mobilebrowser.client.containers.ContentContainer;
import com.ventana.gwt.mobilebrowser.client.views.HostPageView;
import com.ventana.gwt.mobilebrowser.client.widgets.FooterWidget;
import com.ventana.gwt.mobilebrowser.client.widgets.PopUpConfirmationWidget;
import com.ventana.gwt.mobilebrowser.client.widgets.PopUpDeleteWidget;
import com.ventana.gwt.mobilebrowser.client.widgets.PopUpEditWidget;
import com.ventana.gwt.mobilebrowser.client.widgets.PopUpImageInformationWidget;
import com.ventana.gwt.mobilebrowser.client.widgets.TestableComposite;
import com.ventana.gwt.mobilebrowser.client.widgets.ToolBarWidget;

public class HostPageUi extends TestableComposite implements HostPageView {
  @UiTemplate("HostPage.ui.xml")
  public interface HostPageUiBinder extends UiBinder<Widget, HostPageUi> {}

  @UiField(provided = true)  ContentContainer container;
  @UiField FooterWidget footer;
  @UiField(provided = true) PopUpConfirmationWidget popUpConfirmation;
  @UiField(provided = true) PopUpEditWidget popUpEdit;
  @UiField(provided = true) PopUpDeleteWidget popUpDelete;
  @UiField(provided = true) PopUpImageInformationWidget popUpImageInformation;
  @UiField(provided = true) ToolBarWidget toolBar;

  public HostPageUi() {
    this(
        new ToolBarWidget(),
        new ContentContainer(),
        new PopUpConfirmationWidget(),
        new PopUpEditWidget(),
        new PopUpDeleteWidget(),
        new PopUpImageInformationWidget(),
        GWT.<HostPageUiBinder> create(HostPageUiBinder.class));
  }

  @Inject
  public HostPageUi(
      final ToolBarWidget toolbar,
      final ContentContainer container,
      final PopUpConfirmationWidget popUpConfirmation,
      final PopUpEditWidget popUpEdit,
      final PopUpDeleteWidget popUpDelete,
      final PopUpImageInformationWidget popUpImageInformation,
      final HostPageUiBinder binder) {
    toolBar = toolbar;
    this.container = container;
    this.popUpConfirmation = popUpConfirmation;
    this.popUpEdit = popUpEdit;
    this.popUpDelete = popUpDelete;
    this.popUpImageInformation = popUpImageInformation;
    initWidget(binder.createAndBindUi(this));
  }

  @Override
  public void addMeToThis(final AcceptsOneWidget container) {
    container.setWidget(this);
  }
}