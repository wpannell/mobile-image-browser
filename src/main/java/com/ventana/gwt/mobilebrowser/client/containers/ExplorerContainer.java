package com.ventana.gwt.mobilebrowser.client.containers;

import com.google.gwt.query.client.GQuery;
import com.google.gwt.user.client.Event;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.RootFolder;
import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;
import com.ventana.gwt.mobilebrowser.client.publishers.ClickedPublisher;

import java.util.ArrayList;
import java.util.List;

public class ExplorerContainer extends GwtQueryContainer {
  private final List<GQuery> elements;
  private final GwtQueryContainer files;

  public ExplorerContainer() {
    super();
    files = new FilesContainer();
    elements = new ArrayList<GQuery>();
  }

  public void append(final RootFolder folder,
      final Subscriber<Explorer> browseClickedSubscriber,
      final Subscriber<Explorer> deleteClickedSubscriber,
      final Subscriber<Explorer> editClickedSubscriber) {

    final GQuery element = append(folder, browseClickedSubscriber);

    selectSubElementContainedIn(element, "a.deleteLink").click(
        new ClickedPublisher<Explorer>(
            folder, deleteClickedSubscriber, !SHOULD_BUBBLE_UP));

    selectSubElementContainedIn(element, "a.editLink").click(
        new ClickedPublisher<Explorer>(
            folder, editClickedSubscriber, !SHOULD_BUBBLE_UP));
  }

  public void append(final Thumbnail thumbnail,
      final Subscriber<Explorer> fileClickedSubscriber,
      final Subscriber<Thumbnail> fileInformationSubscriber) {

    final GQuery fileElement = files.append(thumbnail);

    selectSubElementContainedIn(fileElement, "img").click(
        new ClickedPublisher<Explorer>(
            thumbnail, fileClickedSubscriber, SHOULD_BUBBLE_UP));

    selectSubElementContainedIn(fileElement, "a.imageInfoLink").click(
        new ClickedPublisher<Thumbnail>(
            thumbnail, fileInformationSubscriber, !SHOULD_BUBBLE_UP));
  }

  @Override
  public void clear() {
    super.clear();
    files.clear();
    clear(elements);
  }

  public GQuery selectSubElementContainedIn(final GQuery element,
      final String selector) {
    final GQuery anchor = element.find(selector);
    elements.add(anchor);
    return anchor;
  }

  @Override
  public GwtQueryContainer setSelector(final String selector) {
    files.setSelector(selector);
    return super.setSelector(selector);
  }

  @Override
  @SuppressWarnings("unused")
  protected void configureSelectedIndexFor(final Explorer place, final int i) {}

  private void clear(final List<GQuery> elements) {
    for (final GQuery element : elements)
      element.unbind(
          Event.TOUCHEVENTS | Event.GESTUREEVENTS | Event.ONCLICK | Event.ONDBLCLICK);
    elements.clear();
  }
}
