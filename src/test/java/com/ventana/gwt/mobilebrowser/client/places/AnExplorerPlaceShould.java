package com.ventana.gwt.mobilebrowser.client.places;

import com.google.gwt.junit.GWTMockUtilities;

import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.client.xml.FileXml;
import com.ventana.gwt.mobilebrowser.client.xml.FolderXml;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AnExplorerPlaceShould {
  @Mock private ExplorerView mockView;
  private FileXml mockFileXml;
  private FolderXml mockFolderXml;

  private Explorer thumbnail;
  private Explorer folder;

  @Before
  public void createFixture() {
    GWTMockUtilities.disarm();
    mockFileXml = mock(FileXml.class);
    mockFolderXml = mock(FolderXml.class);
    given(mockFolderXml.getName()).willReturn("myNAME");
    given(mockFileXml.getName()).willReturn("myNAME");

    thumbnail = new Thumbnail("127.0.0.0", mockFileXml);
    folder = new Folder("127.0.0.0", mockFolderXml);
  }

  @Test
  public void loadNavigatorWithFolder() {
    folder.loadNavigatorFor(mockView);
    verify(mockView).loadNavigatorWith(folder);
  }

  @Test
  public void notLoadNavigatorWithThumbnail() {
    thumbnail.loadNavigatorFor(mockView);
    verify(mockView, times(0)).loadNavigatorWith(folder);
  }
}