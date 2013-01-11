package com.ventana.gwt.mobilebrowser.server.repositories.localstorage;

import com.google.gwt.storage.client.Storage;

import com.ventana.gwt.mobilebrowser.client.places.RootFolder;
import com.ventana.gwt.mobilebrowser.server.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class LocalStorageRepository implements Repository {
  private static final Storage localStorage =
      Storage.getLocalStorageIfSupported();
  private final List<RootFolder> folders;

  public LocalStorageRepository() {
    folders = new ArrayList<RootFolder>();
  }

  @Override
  public List<RootFolder> fetch() {
    folders.clear();
    for (int i = 0; i < localStorage.getLength(); i++)
      folders.add(new RootFolder(localStorage.getItem(localStorage.key(i))));
    return folders;
  }

  @Override
  public void remove(final RootFolder folder) {
    localStorage.removeItem(folder.getName());
  }

  @Override
  public void store(final RootFolder folder) {
    localStorage.setItem(folder.getName(), folder.getHistoryToken());
  }
}
