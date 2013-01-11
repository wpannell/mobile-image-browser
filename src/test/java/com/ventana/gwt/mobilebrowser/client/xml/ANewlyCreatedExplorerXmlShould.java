package com.ventana.gwt.mobilebrowser.client.xml;

import com.google.gwt.junit.client.GWTTestCase;

import com.ventana.gwt.mobilebrowser.client.testhelpers.TestData;

public class ANewlyCreatedExplorerXmlShould extends GWTTestCase {
  private ExplorerXml rootNode;
  private ExplorerXml childNode;

  private FileXml expectedFile0;
  private FileXml expectedFile5;
  private FileXml expectedFile3;

  @Override
  protected void gwtSetUp() {
    rootNode = ExplorerXml.createWith(TestData.ROOT_NODE);
    childNode = ExplorerXml.createWith(TestData.CHILD_NODE);

    expectedFile0 =
        FileXml.createWith("CD138_bone_marrow.bif", "12/15/2011 10:45 PM", "278392196");
    expectedFile3 =
        FileXml.createWith("PSOR_CD3_1.jp2", "12/15/2011 10:45 PM", "85750354");
    expectedFile5 =
        FileXml.createWith("test4_1.bif", "12/15/2011 10:45 PM", "53407380");
}

  public void testConstructParent() {
    assertEquals(1, (rootNode.getNumberOfFolders()));
    assertEquals(0, (rootNode.getNumberOfFiles()));
  }

  public void testKnowParentFolderNames() {
    FolderXml expectedFolder = FolderXml.createWith("Jp2Images");
    assertEquals(expectedFolder, (rootNode.getFolder(0)));
    assertEquals(expectedFolder.name, (rootNode.getFolder(0).name));
  }

  public void testConstructChild() {
    assertEquals(0, (childNode.getNumberOfFolders()));
    assertEquals(6, (childNode.getNumberOfFiles()));
  }

  public void testKnowChildFiles() {
    assertEquals(expectedFile0, childNode.getFile(0));
    assertEquals(expectedFile5, childNode.getFile(5));
    assertEquals(expectedFile3, childNode.getFile(3));
  }

  public void testKnowChildFileAttributes() {
    assertEquals(expectedFile0.name, childNode.getFile(0).name);
    assertEquals(expectedFile5.size, childNode.getFile(5).size);
    assertEquals(expectedFile3.time, childNode.getFile(3).time);
  }

  @Override
  public String getModuleName() {
    return "Integration";
  }
}
