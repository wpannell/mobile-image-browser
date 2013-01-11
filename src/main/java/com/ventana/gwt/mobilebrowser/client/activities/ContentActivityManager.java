package com.ventana.gwt.mobilebrowser.client.activities;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import com.ventana.gwt.mobilebrowser.client.containers.ContentContainer;

public class ContentActivityManager extends ActivityManager {
  @Inject
  public ContentActivityManager(final EventBus eventBus,
      @Named(ContentContainer.NAME) final ActivityMapper mapper) {
    super(mapper, eventBus);
  }
}