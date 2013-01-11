package com.ventana.gwt.mobilebrowser.client.history;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;

import com.ventana.gwt.mobilebrowser.client.activities.HostPageActivity;
import com.ventana.gwt.mobilebrowser.client.history.maps.activities.HostPageActivityMap;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.RootFolder;
import com.ventana.gwt.mobilebrowser.client.testdoubles.FakeGinFactory;
import com.ventana.gwt.mobilebrowser.client.testdoubles.GinFactorySpy;

public class ANewlyCreatedHostPageActivityShould extends GWTTestCase {
  private GinFactorySpy factorySpy;
  private Explorer explorerPlace;
  private Directory directoryPlace;
  private FakeGinFactory ginFactory;
  private HostPageActivityMap activityMap;
  private HostPageActivity hostPageActivity;

  @Override
  protected void gwtSetUp() {
    directoryPlace = new Directory();
    explorerPlace = new RootFolder("127.0.0.1");
    ginFactory = GWT.<FakeGinFactory>create(FakeGinFactory.class);
    factorySpy = ginFactory.makeSpy();
    hostPageActivity = factorySpy.hostPageActivity;
    activityMap = new HostPageActivityMap(hostPageActivity);
  }

  public void testReturnHostPageWhenPlaceIsHome() {
    assertEquals(hostPageActivity, activityMap.getActivity(directoryPlace));
  }

  public void testReturnHostPageWhenPlaceIsExplorer() {
    assertEquals(hostPageActivity, activityMap.getActivity(explorerPlace));
  }

  @Override
  public String getModuleName() {
    return "Integration";
  }
}
