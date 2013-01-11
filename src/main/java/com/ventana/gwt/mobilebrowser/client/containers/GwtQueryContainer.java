package com.ventana.gwt.mobilebrowser.client.containers;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.query.client.GQuery;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.publishers.ClickedPublisher;

import java.util.ArrayList;
import java.util.List;

public class GwtQueryContainer {
  protected static final boolean SHOULD_BUBBLE_UP = true;
  private final List<GQuery> elements;
  private GQuery container = $();
  private String prefix = "";
  private String selector = "";
  private String suffix = "";

  public GwtQueryContainer() {
    elements = new ArrayList<GQuery>();
  }

  public GQuery append(final Directory directory) {
    final GQuery element =
        $(prefix + directory.getName() + suffix).appendTo(container);
    elements.add(element);
    return element;
  }

  public GQuery append(final Explorer folder) {
    final GQuery element = buildListElement(folder).appendTo(container);
    elements.add(element);
    configureSelectedIndexFor(folder, elements.size() - 1);
    return element;
  }

  public GQuery append(final Explorer folder,
      final Subscriber<Explorer> clickedSubscriber) {
    final GQuery element = append(folder);
    element.click(new ClickedPublisher<Explorer>(folder, clickedSubscriber, SHOULD_BUBBLE_UP));
    return element;
  }

  public void clear() {
    $(container).empty();
    elements.clear();
  }

  public void select(final int i) {
    $(container).find(".selected").removeClass("selected");
    elements.get(i).addClass("selected");
  }

  public GwtQueryContainer setPrefix(final String prefix) {
    this.prefix = prefix;
    return this;
  }

  public GwtQueryContainer setSelector(final String selector) {
    if (container != null) clear();
    this.selector = selector;
    container = $(this.selector);
    return this;
  }

  public GwtQueryContainer setSuffix(final String suffix) {
    this.suffix = suffix;
    return this;
  }

  public int size() {
    return elements.size();
  }

  protected GQuery buildListElement(final Explorer place) {
    return $(place.asHtml());
  }

  protected void configureSelectedIndexFor(final Explorer place, final int i) {
    place.setSelectedIndex(i);
  }

  protected String getPrefix() {
    return prefix;
  }

  protected String getSuffix() {
    return suffix;
  }
}
