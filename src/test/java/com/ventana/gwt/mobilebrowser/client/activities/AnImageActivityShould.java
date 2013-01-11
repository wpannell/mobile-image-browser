package com.ventana.gwt.mobilebrowser.client.activities;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Image;
import com.ventana.gwt.mobilebrowser.client.views.ImageView;
import com.ventana.gwt.mobilebrowser.client.views.ToolBarView;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AnImageActivityShould {
  @Captor private ArgumentCaptor<Subscriber<String>>
      whenZoomChangedCaptor;

  @Captor private ArgumentCaptor<Subscriber<Void>>
      whenTappedCaptor;

  @Mock protected ImageView mockView;
  @Mock protected ToolBarView mockToolBar;
  @Mock protected Image mockImagePlace;
  @Mock private EventBus mockEventBus;

  @Mock protected AcceptsOneWidget mockContainer;

  protected ImageActivity activity;

  private int[][] qualityLevelsStub;

  public AnImageActivityShould() {
    super();
  }

  @Before
  public void create() {
    GWTMockUtilities.disarm();
    qualityLevelsStub = new int[][] {
        {56976, 19088},
        {28488, 9544},
        {14244, 4772},
        {7122, 2386},
        {3561, 1193},
        {1781, 597},
        {891, 299},
        {446, 150},
        {223, 75}};

    activity = new ImageActivity(mockView, mockToolBar, mockImagePlace);
  }


  @Test
  public void bind() {
    activity.start(mockContainer, mockEventBus);
    verify(mockView).whenZoomChangedNotify(whenZoomChangedCaptor.capture());
    verify(mockView).whenTappedNotify(whenTappedCaptor.capture());
  }

  @Test
  public void registerEvents() {
    activity.start(mockContainer, mockEventBus);
    verify(mockView).registerEvents();
  }

  @Test
  public void deRegisterEvents() {
    activity.onStop();
    verify(mockView).deRegisterEvents();
  }

  @Test
  public void configureView() {
    activity.start(mockContainer, mockEventBus);
    verify(mockView).addMeToThis(mockContainer);
  }

  @Test
  public void showToolBar() {
    activity.start(mockContainer, mockEventBus);
    verify(mockToolBar).isShowing(ToolBarView.YES);
  }

  @Test
  public void hideInformationText() {
    activity.start(mockContainer, mockEventBus);
    verify(mockToolBar).isInformationTextShowing(ToolBarView.YES);
  }

  @Test
  public void showBackButton() {
    activity.start(mockContainer, mockEventBus);
    verify(mockToolBar).isBackButtonShowing(ToolBarView.YES);
  }

  @Test
  public void hideAddButton() {
    activity.start(mockContainer, mockEventBus);
    verify(mockToolBar).isAddButtonShowing(ToolBarView.NO);
  }

  @Test
  public void configureImage() {
    given(mockImagePlace.getProtocol()).willReturn("http://");
    given(mockImagePlace.getHost()).willReturn("127.0.0.0");
    given(mockImagePlace.getPort()).willReturn(":85");
    given(mockImagePlace.getParentPath()).willReturn("/path");
    given(mockImagePlace.getName()).willReturn("image.tiff");
    given(mockImagePlace.getQualityLevels()).willReturn(qualityLevelsStub);
    given(mockImagePlace.getMagnification()).willReturn(20);
    activity.start(mockContainer, mockEventBus);
    verify(mockView).configure("http://",
        "127.0.0.0", ":85",
        mockImagePlace.getParentPath(), mockImagePlace.getName(),
        mockImagePlace.getQualityLevels(), mockImagePlace.getMagnification());
  }

  @Test
  public void showToolBarWhenTapped() {
    given(mockToolBar.isShowing()).willReturn(ToolBarView.NO);
    whenTapped();
    verify(mockToolBar, times(2)).isShowing(ToolBarView.YES);
    //...times(1) on startup...times(2) when tapped...
  }

  @Test
  public void hideToolBarWhenTapped() {
    given(mockToolBar.isShowing()).willReturn(ToolBarView.YES);
    whenTapped();
    verify(mockToolBar).isShowing(ToolBarView.NO);
  }

  @Test
  public void updateZoom() {
    whenZoomChanged();
    verify(mockToolBar).setInformationText("10000X");
  }

  private void whenTapped() {
    bind();
    whenTappedCaptor.getValue().notifyWith(null);
  }

  private void whenZoomChanged() {
    bind();
    whenZoomChangedCaptor.getValue().notifyWith("10000X");
  }
}