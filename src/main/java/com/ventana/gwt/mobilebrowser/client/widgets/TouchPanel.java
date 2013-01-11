package com.ventana.gwt.mobilebrowser.client.widgets;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.GestureStartEvent;
import com.google.gwt.event.dom.client.GestureStartHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.SimplePanel;

public class TouchPanel extends SimplePanel {
  public TouchPanel(final Element element) {
    super(element);
    sinkEvents(Event.GESTUREEVENTS);
  }

  public void addGestureStartHandler(
      final GestureStartHandler gestureStartHandler) {
    this.addHandler(gestureStartHandler, GestureStartEvent.getType());
  }
}
