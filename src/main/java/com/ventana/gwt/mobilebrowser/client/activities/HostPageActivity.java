package com.ventana.gwt.mobilebrowser.client.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import com.ventana.gwt.mobilebrowser.client.views.HostPageView;

public class HostPageActivity extends AbstractActivity {
  private final HostPageView view;

  @Inject
  public HostPageActivity(final HostPageView view) {
    this.view = view;
  }

  @Override
  public void start(final AcceptsOneWidget container,
      @SuppressWarnings("unused") final EventBus eventBus) {
    view.addMeToThis(container);
  }
}
