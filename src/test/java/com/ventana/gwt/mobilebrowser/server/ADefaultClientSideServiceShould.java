package com.ventana.gwt.mobilebrowser.server;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.ventana.gwt.mobilebrowser.server.payloads.Payload;
import com.ventana.gwt.mobilebrowser.server.registry.ClientSideServiceDispatcher;
import com.ventana.gwt.mobilebrowser.server.responses.Response;
import com.ventana.gwt.mobilebrowser.server.services.Service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ADefaultClientSideServiceShould {
  private static final boolean SHOULD_SUCCEED = true;
  private static final boolean HAS_SUCCEEDED = true;
  private ClientSideServiceDispatcher fakeDispatcher;
  private FakeCallback callback;

  @Before
  public void createFixture() {
    fakeDispatcher = new ClientSideServiceDispatcher();
    fakeDispatcher.register(new FakeClientSideService());
    callback = new FakeCallback();
  }

  @Test
  public void executeService() {
    assertThat(callback.result.hasPassed, not(equalTo(HAS_SUCCEEDED)));

    fakeDispatcher.execute(new FakePayload(SHOULD_SUCCEED), callback);

    assertThat(callback.result.hasPassed, equalTo(HAS_SUCCEEDED));
    assertThat(callback.message, equalTo(""));
  }

  @Test
  public void failService() {
    assertThat(callback.result.hasPassed, not(equalTo(HAS_SUCCEEDED)));

    fakeDispatcher.execute(new FakePayload(!SHOULD_SUCCEED), callback);

    assertThat(callback.result.hasPassed, not(equalTo(HAS_SUCCEEDED)));
    assertThat(callback.message, equalTo("service failed"));
  }

  private class FakeResponse implements Response {
    boolean hasPassed;
    FakeResponse(final boolean hasPassed) { this.hasPassed = hasPassed;}
  }

  private class FakePayload implements Payload<FakeResponse> {
    boolean shouldSucceed;
    FakePayload(final boolean shouldSucceed) { this.shouldSucceed = shouldSucceed; }
  }

  private class FakeCallback implements AsyncCallback<FakeResponse> {
    FakeResponse result = new FakeResponse(!HAS_SUCCEEDED);
    String message = "";
    @Override public void onFailure(final Throwable caught) {
      message = caught.getMessage();
    }
    @Override public void onSuccess(final FakeResponse result) {
      this.result  = result;
    }
  }

  private class FakeClientSideService implements Service<FakePayload, FakeResponse> {
    @Override
    public void execute(final FakePayload payload,
        final AsyncCallback<FakeResponse> callback) {
      if(payload.shouldSucceed) {
        callback.onSuccess(new FakeResponse(payload.shouldSucceed));
        return;
      }
      callback.onFailure(new Exception("service failed"));
    }
    @Override public Class<FakePayload> getType() { return FakePayload.class; }
  }
}