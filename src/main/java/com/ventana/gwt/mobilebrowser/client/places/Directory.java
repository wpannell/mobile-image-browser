package com.ventana.gwt.mobilebrowser.client.places;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

import com.ventana.gwt.mobilebrowser.client.factories.ActivityFactory;
import com.ventana.gwt.mobilebrowser.client.factories.GinFactory;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.server.repositories.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Directory extends MobileBrowserPlace {
  @Prefix(value = "Directory")
  public static class Tokenizer implements PlaceTokenizer<Directory> {
    @Override
    public Directory getPlace(final String historyToken) {
      return new Directory(historyToken);
    }
    @Override
    public String getToken(final Directory place) {
      return place.getHistoryToken();
    }
  }

  public static final Directory EMPTY = new Directory(Repository.EMPTY);
  private final List<RootFolder> children;
  private final String historyToken;
  private final Set<String> names;
  private final Repository repository;
  private final Set<String> urls;

  public Directory() {
    this("");
  }

  public Directory(final Repository repository) {
    this("", repository);
  }

  @SuppressWarnings("unused") // ...unused history token due to default...
  public Directory(final String historyToken) {
    this("", GWT.<GinFactory> create(GinFactory.class).makeRepository());
  }

  @SuppressWarnings("unused") // ...unused history token due to default...
  public Directory(final String historyToken, final Repository repository) {
    this.historyToken = "";
    this.repository = repository;
    children = new ArrayList<RootFolder>();
    names = new HashSet<String>();
    urls = new HashSet<String>();
  }

  public void adopt(final RootFolder folder) {
    children.add(folder);
    folder.setSelectedIndex(getChildenSize() - 1);
  }

  public void fetch() {
    clearCollections();
    final List<RootFolder> fetched = repository.fetch();
    for (int i = 0; i < fetched.size(); i++) {
      final RootFolder folder = fetched.get(i);
      names.add(folder.getName());
      urls.add(folder.getUrl());
      adopt(folder);
    }
  }

  @Override
  public Activity getActivityFrom(final ActivityFactory factory) {
    return factory.createWith(this);
  }

  public RootFolder getChild(final int i) {
    return children.get(i);
  }

  public int getChildIndexOf(final Explorer place) {
    return children.indexOf(place);
  }

  public int getChildenSize() {
    return children.size();
  }

  public String getHistoryToken() {
    return historyToken;
  }

  public String getName() {
    return "DIRECTORY";
  }

  public boolean isEmpty() {
    return getChildenSize() == 0;
  }

  public Boolean isNameDuplicated(final String name, final Explorer folder) {
    if (name.equals(folder.getName())) return false;
    return names.contains(name);
  }

  public Boolean isUrlDuplicated(final String url, final Explorer folder) {
    if (url.equals(folder.getUrl())) return false;
    return urls.contains(url);
  }

  public void loadExplorerWithChildrenFor(final ExplorerView view) {
    for (int i = 0; i < getChildenSize(); i++)
      view.loadExplorerWith(getChild(i));
  }

  public void loadNavigatorFor(final ExplorerView view) {
    view.loadNavigatorWith(this);
  }

  public void loadNavigatorWithChildrenFor(final ExplorerView view) {
    for (int i = 0; i < getChildenSize(); i++)
      view.loadNavigatorWith(getChild(i));
  }

  public void orphan(final RootFolder rootFolder) {
    children.remove(rootFolder);
  }

  public void remove(final RootFolder folder) {
    names.remove(folder.getName());
    urls.remove(folder.getUrl());
    orphan(folder);
    repository.remove(folder);
  }

  public void store(final RootFolder folder) {
    if (!containsName(folder) && !containsUrl(folder) && !contains(folder)) {
      repository.store(folder);
      adopt(folder);
      urls.add(folder.getUrl());
      names.add(folder.getName());
    }
  }

  private void clearCollections() {
    children.clear();
    names.clear();
    urls.clear();
  }

  private boolean contains(final RootFolder folder) {
    return children.contains(folder);
  }

  private boolean containsName(final RootFolder folder) {
    return names.contains(folder.getName());
  }

  private boolean containsUrl(final RootFolder folder) {
    return urls.contains(folder.getUrl());
  }
}
