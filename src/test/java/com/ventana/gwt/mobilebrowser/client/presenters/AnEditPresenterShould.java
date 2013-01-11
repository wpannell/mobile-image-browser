package com.ventana.gwt.mobilebrowser.client.presenters;

import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.ventana.gwt.mobilebrowser.client.events.Subscriber;
import com.ventana.gwt.mobilebrowser.client.places.Directory;
import com.ventana.gwt.mobilebrowser.client.places.RootFolder;
import com.ventana.gwt.mobilebrowser.client.views.ExplorerView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpConfirmationView;
import com.ventana.gwt.mobilebrowser.client.views.PopUpEditView;
import com.ventana.gwt.mobilebrowser.server.payloads.ImagesPayload;
import com.ventana.gwt.mobilebrowser.server.payloads.Payload;
import com.ventana.gwt.mobilebrowser.server.registry.ClientSideServiceDispatcher;
import com.ventana.gwt.mobilebrowser.server.responses.ImagesServiceResponse;
import com.ventana.gwt.mobilebrowser.server.responses.Response;
import com.ventana.gwt.mobilebrowser.server.services.FetchImagesService;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
abstract public class AnEditPresenterShould {
  protected static final String NAME = "name";
  protected static final String PRETTY_PROTOCOL = "http";
  protected static final String PATH = "127.0.0.0:81/path/subpath";
  protected static final String PRETTY_SECURE_PROTOCOL = "https";

  protected static final String ANY_STRING = "any string";
  protected static final String EMPTY = "";

  protected static final boolean SHOULD_SUCCEED = true;
  protected static final String MOCK_SERVICE_FAILED = "mock service failed";

  @Mock protected PopUpEditView mockView;
  @Mock protected RootFolder mockFolder;
  @Mock protected Directory mockDirectory;
  @Mock protected Subscriber<Void> mockEditOkSubscriber;
  @Mock protected RootFolder mockNewFolder;
  @Mock protected PopUpConfirmationView mockConfirmationView;
  @Mock protected ExplorerView mockExplorerView;

  public AnEditPresenterShould() {
    super();
  }

  @Before
  public void createFixture() {
    GWTMockUtilities.disarm();

    given(mockFolder.getName()).willReturn(NAME);
    given(mockFolder.getPrettyProtocol()).willReturn(PRETTY_PROTOCOL);
    given(mockFolder.getPath()).willReturn(PATH);
    given(mockFolder.getUrl()).willReturn(PRETTY_PROTOCOL + "://" + PATH);


    given(mockView.getName()).willReturn(NAME);
    given(mockView.getProtocol()).willReturn(PRETTY_PROTOCOL);
    given(mockView.getPath()).willReturn(PATH);

    given(mockFolder.createWith(anyString(), anyString())).
        willReturn(mockNewFolder);
    given(mockFolder.getUrl()).willReturn("http://127.0.0.0:81/path/subpath");
  }

  class FakeDispatcher extends ClientSideServiceDispatcher {
    @Override
    public <P extends Payload<R>, R extends Response> void execute(P payload,
        AsyncCallback<R> callback) {
      super.execute(payload, callback);
    }
  }

  class FakeImagesService extends FetchImagesService {
    ImagesPayload payload;
    boolean shouldSucceed;

    FakeImagesService(boolean shouldSucceed) {
      this.shouldSucceed = shouldSucceed;
    }

    @Override
    public void execute(ImagesPayload payload,
        AsyncCallback<ImagesServiceResponse> callback) {
      this.payload = payload;
      if(shouldSucceed) {
        callback.onSuccess(new ImagesServiceResponse(mockFolder));
        return;
      }
      callback.onFailure(new RuntimeException(MOCK_SERVICE_FAILED));
    }
  }
}