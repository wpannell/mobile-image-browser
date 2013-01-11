package com.ventana.gwt.mobilebrowser.client.places;

import com.google.gwt.junit.GWTMockUtilities;

import com.ventana.gwt.mobilebrowser.server.repositories.Repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ADirectoryPlaceShould {
  private static final String EMPTY = "";
  private static final boolean NO = false;
  private static final boolean YES = true;

  @Mock private Repository mockRepository;
  @Mock private Repository anotherMockRepository;

  private Directory directory;
  private RootFolder folder0;
  private RootFolder folder1;

  private List<RootFolder> fakeFolders;

  @Before
  public void createFixture() {
    GWTMockUtilities.disarm();

    directory = new Directory(mockRepository);
    folder0 = new RootFolder("folder0$127.0.0.1");
    folder1 = new RootFolder("folder1$127.0.0.2");
    directory.store(folder0);
    directory.store(folder1);

    fakeFolders = new ArrayList<RootFolder>();
    fakeFolders.add(folder0);
    fakeFolders.add(folder1);
  }

  @Test
  public void knowItsHistoryToken() {
    assertThat(directory.getHistoryToken(), equalTo(EMPTY));
  }

  @Test
  public void knowItsName() {
    assertThat(directory.getName(), equalTo("DIRECTORY"));
  }

  @Test
  public void knowsChildNames() {
    assertThat(directory.isNameDuplicated("folder0", folder0), equalTo(NO));
    assertThat(directory.isNameDuplicated("folder", folder0), equalTo(NO));
    assertThat(directory.isNameDuplicated("folder1", folder0), equalTo(YES));

    assertThat(directory.isNameDuplicated("folder0", folder1), equalTo(YES));
    assertThat(directory.isNameDuplicated("folder", folder1), equalTo(NO));
    assertThat(directory.isNameDuplicated("folder1", folder1), equalTo(NO));
  }

  @Test
  public void knowsChildUrls() {
    assertThat(directory.isUrlDuplicated("http://127.0.0.1:80", folder0), equalTo(NO));
    assertThat(directory.isUrlDuplicated("http://127.0.0.0:80", folder0), equalTo(NO));
    assertThat(directory.isUrlDuplicated("http://127.0.0.2:80", folder0), equalTo(YES));

    assertThat(directory.isUrlDuplicated("http://127.0.0.1:80", folder1), equalTo(YES));
    assertThat(directory.isUrlDuplicated("http://127.0.0.0:80", folder1), equalTo(NO));
    assertThat(directory.isUrlDuplicated("http://127.0.0.2:80", folder1), equalTo(NO));
  }

  @Test
  public void notCareAboutOtherHistoryTokens() {
    directory = new Directory("ANY_OLD_HISTORY_TOKEN", mockRepository);
    assertThat(directory.getHistoryToken(), equalTo(EMPTY));
  }

  @Test
  public void startEmpty() {
    Directory directory = new Directory(mockRepository);
    assertThat(directory.isEmpty(), equalTo(YES));
  }

  @Test
  public void adopt() {
    assertThat(directory.getChildenSize(), equalTo(2));
    assertThat(directory.getChild(0), equalTo(folder0));
    assertThat(directory.getChild(1), equalTo(folder1));
  }

  @Test
  public void store() {
    Directory anotherDirectory = new Directory(anotherMockRepository);
    anotherDirectory.store(folder0);
    assertThat(anotherDirectory.getChildenSize(), equalTo(1));

    anotherDirectory.store(folder1);
    verify(anotherMockRepository).store(folder1);
    assertThat(anotherDirectory.getChildenSize(), equalTo(2));
  }

  @Test
  public void notStore() {
    assertThat(directory.getChildenSize(), equalTo(2));
    directory.store(folder1);
    assertThat(directory.getChildenSize(), equalTo(2));
  }

  @Test
  public void remove() {
    assertThat(directory.getChildenSize(), equalTo(2));
    directory.remove(folder1);
    verify(mockRepository).remove(folder1);
    assertThat(directory.getChildenSize(), equalTo(1));
  }

  @Test
  public void notRemove() {
    Directory directory = new Directory(mockRepository);
    directory.adopt(folder0);
    assertThat(directory.getChildenSize(), equalTo(1));

    directory.remove(folder1);

    assertThat(directory.getChildenSize(), equalTo(1));
  }
}
