package com.ventana.gwt.mobilebrowser.server;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;

import com.ventana.gwt.mobilebrowser.client.places.Folder;
import com.ventana.gwt.mobilebrowser.client.places.Explorer;
import com.ventana.gwt.mobilebrowser.client.places.Thumbnail;
import com.ventana.gwt.mobilebrowser.client.testdoubles.FakeGinFactory;
import com.ventana.gwt.mobilebrowser.client.testdoubles.GinFactorySpy;
import com.ventana.gwt.mobilebrowser.server.callbacks.GetImagesServiceResponse;
import com.ventana.gwt.mobilebrowser.server.dispatcher.AsyncDispatcher;
import com.ventana.gwt.mobilebrowser.server.payloads.ImagesPayload;
import com.ventana.gwt.mobilebrowser.server.responses.ImagesServiceResponse;

public class AnImagesServiceShould extends GWTTestCase {
  public static final int TIME_OUT = 1000;
  private GinFactorySpy factorySpy;
  private FakeGinFactory ginFactory;
  private AsyncDispatcher dispatcher;
  private Explorer rootFolder;
  private ImagesPayload startPayload;

  @Override
  protected void gwtSetUp() {
    ginFactory = GWT.<FakeGinFactory>create(FakeGinFactory.class);
    factorySpy = ginFactory.makeSpy();
    dispatcher = factorySpy.clientSideServiceDispatcher;
    startPayload = new ImagesPayload(
        new Folder("107.22.162.235:85"));
  }

  public void testRetrieveRootNode() {
    delayTestFinish(TIME_OUT);
    dispatcher.execute(startPayload, new GetImagesServiceResponse() {
      @Override
      public void got(ImagesServiceResponse serviceResponse) {
        rootFolder = serviceResponse.getImageExplorerPlace();

        assertEquals("107.22.162.235:85",
            rootFolder.getHistoryToken());
        assertEquals("",  rootFolder.getSubPath());
        assertEquals("",  rootFolder.getTime());
        assertEquals("",  rootFolder.getSize());

        finishTest();
      }
    });
  }

  public void testRetrieveRootFoldersChildren() {
    delayTestFinish(TIME_OUT);
    dispatcher.execute(startPayload, new GetImagesServiceResponse() {
      @Override
      public void got(ImagesServiceResponse serviceResponse) {
        rootFolder = serviceResponse.getImageExplorerPlace();

        assertEquals(1, rootFolder.getChildrenSize());
        Explorer childPlace = rootFolder.getChild(0);
        assertTrue(childPlace instanceof Folder);

        finishTest();
      }
    });
  }

  public void testRetrieveFolderAttribute() {
    delayTestFinish(TIME_OUT);
    dispatcher.execute(startPayload, new GetImagesServiceResponse() {
      @Override
      public void got(ImagesServiceResponse serviceResponse) {
        rootFolder = serviceResponse.getImageExplorerPlace();
        Explorer childPlace = rootFolder.getChild(0);

        assertEquals("107.22.162.235:85/Jp2Images", childPlace.getHistoryToken());
        assertEquals("Jp2Images",  childPlace.getSubPath());
        assertEquals("",  childPlace.getTime());
        assertEquals("",  childPlace.getSize());
        finishTest();
      }
    });
  }

  public void testRetrieveChildFolder() {
    delayTestFinish(TIME_OUT);
    dispatcher.execute(startPayload, new GetImagesServiceResponse() {
      @Override
      public void got(ImagesServiceResponse serviceResponse) {
        rootFolder = serviceResponse.getImageExplorerPlace();
        Explorer childPlace = rootFolder.getChild(0);

        dispatcher.execute(
            new ImagesPayload(childPlace),
            new GetImagesServiceResponse() {
          @Override
          public void got(ImagesServiceResponse serviceResponse) {
            Explorer childFolder =
                serviceResponse.getImageExplorerPlace();

            assertEquals(6, childFolder.getChildrenSize());
            for (int i = 0; i < childFolder.getChildrenSize(); i++)
              assertTrue(childFolder.getChild(i) instanceof Thumbnail);

            Explorer childPlace5 = childFolder.getChild(5);

            assertEquals("107.22.162.235:85/Jp2Images/test4_1.bif",
                childPlace5.getHistoryToken());
            assertEquals("test4_1.bif",  childPlace5.getSubPath());
            assertEquals("12/15/2011 10:45 PM",  childPlace5.getTime());
            assertEquals("53407380",  childPlace5.getSize());

            finishTest();
          }
        });


      }
    });
  }

  @Override
  public String getModuleName() {
    return "Integration";
  }
}
