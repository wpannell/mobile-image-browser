package com.ventana.gwt.mobilebrowser.client.factories;

import com.ventana.gwt.mobilebrowser.client.factories.activities.DirectoryActivityFactory;
import com.ventana.gwt.mobilebrowser.client.factories.activities.ExplorerActivityFactory;
import com.ventana.gwt.mobilebrowser.client.factories.activities.ImageActivityFactory;
import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Image;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ANewlyCreatedActivityFactoryShould {
  @Mock private DirectoryActivityFactory mockDirectoryActivityFactory;
  @Mock private ExplorerActivityFactory mockExlorerActivityFactory;
  @Mock private ImageActivityFactory mockImageActivityFactory;
  @Mock private Explorer mockExplorerPlace;
  @Mock private Directory mockDirectoryPlace;
  @Mock private Image mockImagePlace;

  private ActivityFactory activityFactory;

  @Before
  public void createFixture() {
    activityFactory = new ContentActivityFactory(
        mockExlorerActivityFactory, mockDirectoryActivityFactory,
        mockImageActivityFactory);
  }

  @Test
  public void getDirectoryActivity() {
    activityFactory.createWith(mockExplorerPlace);
    verify(mockExlorerActivityFactory).createActivityFor(mockExplorerPlace);
  }

  @Test
  public void getExplorerActivity() {
    activityFactory.createWith(mockDirectoryPlace);
    verify(mockDirectoryActivityFactory).createActivityFor(mockDirectoryPlace);
  }

  @Test
  public void getImageActivity() {
    activityFactory.createWith(mockImagePlace);
    verify(mockImageActivityFactory).createActivityFor(mockImagePlace);
  }
}

