package com.ventana.gwt.mobilebrowser.server.repositories;

import com.ventana.gwt.mobilebrowser.client.places.RootFolder;

import java.util.ArrayList;
import java.util.List;

public interface Repository {
  public static final Repository EMPTY = new Repository() {
    @Override public void store(@SuppressWarnings("unused") RootFolder folder) {}
    @Override public void remove(@SuppressWarnings("unused") RootFolder folder) {}
    @Override public List<RootFolder> fetch() {
      return new ArrayList<RootFolder>(); }
  };
  List<RootFolder> fetch();
  void remove(final RootFolder folder);
  void store(final RootFolder folder);
}
