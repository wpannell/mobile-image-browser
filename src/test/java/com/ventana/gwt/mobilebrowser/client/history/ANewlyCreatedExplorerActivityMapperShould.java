package com.ventana.gwt.mobilebrowser.client.history;

import com.google.gwt.activity.shared.ActivityMapper;

import com.ventana.gwt.mobilebrowser.client.factories.ActivityFactory;
import com.ventana.gwt.mobilebrowser.client.history.maps.activities.ExplorerActivityMap;
import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Image;
import com.ventana.gwt.mobilebrowser.client.places.RootFolder;
import com.ventana.gwt.mobilebrowser.server.repositories.Repository;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ANewlyCreatedExplorerActivityMapperShould {
  @Mock private ActivityFactory mockActivityFactory;
  @Mock protected Repository mockRepository;

  private Directory fakeDirectoryPlace;
  private Explorer fakeExplorerPlace;
  private Image fakeImagePlace;

  private ActivityMapper activityMapper;

  @Before
  public void createFixture() {
    activityMapper = new ExplorerActivityMap(mockActivityFactory);
    fakeExplorerPlace = new RootFolder("127.0.0.1");
    fakeDirectoryPlace = new Directory(mockRepository);
    fakeImagePlace = new Image("myNAME$127.128.129.130/SUBDIR/myNAME");
  }

  @Test
  public void getDirectoryActivity() {
    activityMapper.getActivity(fakeDirectoryPlace);
    verify(mockActivityFactory).createWith(fakeDirectoryPlace);
  }

  @Test
  public void getExplorerActivity() {
    activityMapper.getActivity(fakeExplorerPlace);
    verify(mockActivityFactory).createWith(fakeExplorerPlace);
  }

  @Test
  public void getImageActivity() {
    activityMapper.getActivity(fakeImagePlace);
    verify(mockActivityFactory).createWith(fakeImagePlace);
  }
}
