package com.ventana.gwt.mobilebrowser.client.containers;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.query.client.GQuery;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.publishers.ClickedPublisher;

import java.util.ArrayList;
import java.util.List;

public class EventContainer {
  private static final boolean SHOULD_BUBBLE_UP = true;
  private final List<GQuery> elements;

  public EventContainer() {
    elements = new ArrayList<GQuery>();
  }

  public void clear() {
    for (GQuery element : elements)
      element.unbind(
          Event.TOUCHEVENTS | Event.GESTUREEVENTS | Event.ONCLICK | Event.ONDBLCLICK);
    elements.clear();
  }

  public void whenTappedNotify(Widget widget, Subscriber<Void> tappedSubscriber) {
    elements.add($(widget).click(
        new ClickedPublisher<Void>(null, tappedSubscriber, SHOULD_BUBBLE_UP)));
  }
}
