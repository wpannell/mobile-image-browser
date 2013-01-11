package com.ventana.gwt.mobilebrowser.client.activities;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import com.ventana.gwt.mobilebrowser.client.views.HostPageView;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ANewlyCreatedHostPageActivityShould {
  @Mock private EventBus mockEventBus;
  @Mock private AcceptsOneWidget mockContainer;
  @Mock private HostPageView mockView;

  private HostPageActivity activity;

  @Before
  public void createFixture() {
    activity = new HostPageActivity(mockView);
  }

  @Test
  public void configureView() {
    activity.start(mockContainer, mockEventBus);
    verify(mockView).addMeToThis(mockContainer);
  }
}
