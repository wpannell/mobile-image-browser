package com.ventana.gwt.mobilebrowser.client.places;

import com.google.gwt.activity.shared.Activity;

import com.ventana.gwt.mobilebrowser.client.domain.ImageInformation;
import com.ventana.gwt.mobilebrowser.client.factories.ActivityFactory;
import com.ventana.gwt.mobilebrowser.client.url.Url;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.client.views.ImageInformationView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpConfirmationView;
import com.ventana.gwt.mobilebrowser.server.dispatcher.AsyncDispatcher;

import java.util.ArrayList;
import java.util.List;

abstract public class Explorer extends MobileBrowserPlace {
  protected static final String DELIMITER = "|";
  protected static final String SEPARATOR = "/";
  private final List<Explorer> children;
  private Explorer parent;
  private final String host;
  private final String name;
  private int selectedIndex;
  private String size;
  private String subPath;
  private String time;
  private final Url url;

  public abstract String asHtml();
  public abstract void loadNavigatorFor(ExplorerView view);
  public abstract void loadExplorerFor(ExplorerView view);

  public Explorer() {
    this("127.0.0.1");
  }

  public Explorer(final String historyToken) {
    url = new Url(historyToken);
    host = url.getHost();
    name = url.getName();
    selectedIndex = url.getIndex();
    subPath = url.getSubPath();
    time = "";
    size = "";
    children = new ArrayList<Explorer>();
  }

  public Explorer(final String historyToken, final String subPath,
      final String time, final String size) {
    this(historyToken);
    this.subPath = subPath;
    this.time = time;
    this.size = size;
  }

  public void adopt(final Explorer place) {
    children.add(place);
    place.setParent(this);
    place.setSelectedIndex(getChildrenSize() - 1);

  }

  public void orphan(final Explorer place) {
    children.remove(place);
  }

  public RootFolder asRootFolder() {
    return createWith(getName(), getUrl());
  }

  public RootFolder createWith(final String name, final String url) {
    return new RootFolder().createWith(name, url);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (!(obj instanceof Explorer)) return false;
    final Explorer other = (Explorer) obj;
    if (getName() == null) {
      if (other.getName() != null) return false;
    }
    else if (!getName().equals(other.getName())) return false;
    if (getUrl() == null) {
      if (other.getUrl() != null) return false;
    }
    else if (!getUrl().equals(other.getUrl())) return false;
    return true;
  }

  @Override
  public Activity getActivityFrom(final ActivityFactory factory) {
    return factory.createWith(this);
  }

  public Explorer getChild(final int i) {
    return children.get(i);
  }

  public int getChildIndexOf(final Explorer place) {
    return children.indexOf(place);
  }

  public int getChildrenSize() {
    return children.size();
  }

  public String getFullPath() {
    return getHost() + getPort() + getPath();
  }

  public String getHistoryToken() {
    return prefix() + getUrl() + DELIMITER + selectedIndex;
  }

  public String getHost() {
    return host;
  }

  public Explorer getParent() {
    if(parent == null) return new Folder(url.getParentUrl());
    return parent;
  }

  public ImageInformation getImageInformation() {
    return ImageInformation.EMPTY;
  }

  public String getName() {
    return name.isEmpty() ? getSubPath() : name;
  }

  public Explorer getNextChildAfter(final Explorer thumbnail) {
    final int i = children.indexOf(thumbnail) + 1;
    if (i == children.size()) return new Folder();
    return children.get(i);
  }

  public String getParentPath() {
    return url.getParentPath();
  }

  public String getParentUrl() {
    return url.getParentUrl();
  }

  public String getPath() {
    return url.getPath();
  }

  public String getPort() {
    return url.getPort();
  }

  public Explorer getPreviousChildAfter(final Explorer thumbnail) {
    final int i = children.indexOf(thumbnail) - 1;
    if (i < 0) return new Folder();
    return children.get(i);
  }

  public String getProtocol() {
    return url.getProtocol();
  }

  public int getSelectedIndex() {
    return selectedIndex;
  }

  public String getSize() {
    return size;
  }

  public String getSubPath() {
    return subPath;
  }

  public String getTime() {
    return time;
  }

  public String getUrl() {
    return url.getUrl();
  }

  public boolean hasChildren() {
    return children.size() != 0;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (getName() == null ? 0 : getName().hashCode());
    result = prime * result + (getUrl() == null ? 0 : getUrl().hashCode());
    return result;
  }

  public Boolean isRoot() {
    return url.isRoot();
  }

  @SuppressWarnings("unused") // ...template method
  public void loadImageInformationFor(final ImageInformationView view) {}


  public void removeMeFrom(@SuppressWarnings("unused") final Directory parent) {}

  @SuppressWarnings("unused") // ...template method
  public void setImageInformation(final ImageInformation imageInformation) {}

  public void setParent(Explorer parent) {
    this.parent = parent;
  }

  public void setSelectedIndex(final int i) {
    selectedIndex = i;
  }

  public void showNextImageInformationWith(
      @SuppressWarnings("unused") final AsyncDispatcher dispatcher,
      final ImageInformationView view,
      @SuppressWarnings("unused") final PopUpConfirmationView popUpMessageView) {
    view.disableNext();
  }

  public void showPreviousImageInformationWith(
      @SuppressWarnings("unused") final AsyncDispatcher dispatcher,
      final ImageInformationView view,
      @SuppressWarnings("unused") final PopUpConfirmationView popUpMessageView) {
    view.disablePrevious();
  }

  @Override
  public String toString() {
    return getHistoryToken() + " : " + time + " : " + size;
  }

  private String encodeIsRoot() {
    return isRoot() ? "Y" + "%" : "";
  }

  private String encodeName() {
    return getName().isEmpty() ? "" : getName() + "$";
  }

  private String prefix() {
    return encodeName() + encodeIsRoot();
  }
  public void loadNavigatorWithSiblingsIn(ExplorerView view) {
    for (int i = 0; i < parent.getChildrenSize(); i++)
      parent.getChild(i).loadNavigatorFor(view);
  }
}