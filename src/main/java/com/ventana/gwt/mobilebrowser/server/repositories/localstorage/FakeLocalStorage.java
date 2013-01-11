package com.ventana.gwt.mobilebrowser.server.repositories.localstorage;

import com.ventana.gwt.mobilebrowser.client.places.RootFolder;
import com.ventana.gwt.mobilebrowser.server.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class FakeLocalStorage implements Repository {
  private List<RootFolder> folders;

  @Override
  public List<RootFolder> fetch() {
    folders = new ArrayList<RootFolder>();
    return folders;
  }

  @Override
  public void remove(RootFolder folder) {
    folders.remove(folder);
  }

  @Override
  public void store(RootFolder folder) {
    folders.add(folder);
  }
}
