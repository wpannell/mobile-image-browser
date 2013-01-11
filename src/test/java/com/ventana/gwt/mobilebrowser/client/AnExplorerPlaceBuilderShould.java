package com.ventana.gwt.mobilebrowser.client;

import com.google.gwt.junit.client.GWTTestCase;

import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;
import com.ventana.gwt.mobilebrowser.client.places.Folder;
import com.ventana.gwt.mobilebrowser.client.testhelpers.TestData;
import com.ventana.gwt.mobilebrowser.client.xml.ExplorerXml;
import com.ventana.gwt.mobilebrowser.server.builders.ExplorerBuilder;

public class AnExplorerPlaceBuilderShould extends GWTTestCase {
  private static final String URL = "http://107.22.162.235:85";
  private ExplorerXml rootNode;
  private ExplorerXml childNode;
  private Explorer startPlace;
  private Explorer rootPlace;
  private Explorer childPlace;

  @Override
  protected void gwtSetUp() {
    startPlace = new Folder("107.22.162.235:85");
    rootNode = ExplorerXml.createWith(TestData.ROOT_NODE);
    rootPlace = new ExplorerBuilder(startPlace, rootNode).build();

    childNode = ExplorerXml.createWith(TestData.CHILD_NODE);
    childPlace = new ExplorerBuilder(
        rootPlace.getChild(0), childNode).build();
  }

  public void testBuildRootFolder() {
    assertEquals("http://107.22.162.235:85|0", rootPlace.getHistoryToken());
    assertEquals("",  rootPlace.getSubPath());
    assertEquals("",  rootPlace.getTime());
    assertEquals("",  rootPlace.getSize());
  }

  public void testBuildRootFoldersChildren() {
    assertEquals(1, rootPlace.getChildrenSize());
    Explorer childPlace = rootPlace.getChild(0);
    assertTrue(childPlace instanceof Folder);
  }

  public void testBuildFolderAttribute() {
    Explorer childPlace = rootPlace.getChild(0);

    assertEquals("Jp2Images$" + URL + "/Jp2Images|0", childPlace.getHistoryToken());
    assertEquals("Jp2Images",  childPlace.getName());
    assertEquals("Jp2Images",  childPlace.getSubPath());
    assertEquals("",  childPlace.getTime());
    assertEquals("",  childPlace.getSize());
  }

  public void testBuildChildFolder() {
    assertEquals("Jp2Images$" + URL + "/Jp2Images|0", childPlace.getHistoryToken());
    assertEquals("Jp2Images",  childPlace.getName());
    assertEquals("Jp2Images",  childPlace.getSubPath());
    assertEquals("",  childPlace.getTime());
    assertEquals("",  childPlace.getSize());
  }

  public void testBuildChildFoldersChildren() {
    assertEquals(6, childPlace.getChildrenSize());
    for (int i = 0; i < childPlace.getChildrenSize(); i++)
      assertTrue(childPlace.getChild(i) instanceof Thumbnail);
  }

  public void testBuildFileAttributeForChild0() {
    Explorer childPlace0 = childPlace.getChild(0);

    assertEquals("CD138_bone_marrow.bif$" + URL + "/Jp2Images/CD138_bone_marrow.bif|0",
        childPlace0.getHistoryToken());
    assertEquals("CD138_bone_marrow.bif",  childPlace0.getName());
    assertEquals("CD138_bone_marrow.bif",  childPlace0.getSubPath());
    assertEquals("12/15/2011 10:45 PM",  childPlace0.getTime());
    assertEquals("278392196",  childPlace0.getSize());
  }

  public void testBuildFileAttributeForChild2() {
    Explorer childPlace2 = childPlace.getChild(2);

    assertEquals("PSOR_CD3.jp2$" + URL + "/Jp2Images/PSOR_CD3.jp2|2",
        childPlace2.getHistoryToken());
    assertEquals("PSOR_CD3.jp2",  childPlace2.getSubPath());
    assertEquals("PSOR_CD3.jp2",  childPlace2.getName());
    assertEquals("12/15/2011 09:07 PM",  childPlace2.getTime());
    assertEquals("85750354",  childPlace2.getSize());
  }

  public void testBuildFileAttributeForChild5() {
    Explorer childPlace5 = childPlace.getChild(5);

    assertEquals("test4_1.bif$" + URL + "/Jp2Images/test4_1.bif|5",
        childPlace5.getHistoryToken());
    assertEquals("test4_1.bif",  childPlace5.getName());
    assertEquals("test4_1.bif",  childPlace5.getSubPath());
    assertEquals("12/15/2011 10:45 PM",  childPlace5.getTime());
    assertEquals("53407380",  childPlace5.getSize());
  }

  @Override
  public String getModuleName() {
    return "Integration";
  }
}
