package com.ventana.gwt.mobilebrowser.client.activities;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

public class HostPageActivityManager extends ActivityManager {
  @Inject
  public HostPageActivityManager(final EventBus eventBus,
      final ActivityMapper mapper) {
    super(mapper, eventBus);
  }
}