package com.ventana.gwt.mobilebrowser.client.url;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AHttpProtocolParserShould {
  private HttpProtocolParser parser;

  @Test
  public void parseHost() {
    parser = new HttpProtocolParser("https://127.0.0.0?query");
    assertThat(parser.getProtocol(), equalTo("https://"));
    assertThat(parser.getPort(), equalTo(":80"));
    assertThat(parser.getHost(), equalTo("127.0.0.0"));
    assertThat(parser.getPath(), equalTo(""));
    assertThat(parser.getQuery(), equalTo("query"));
  }

  @Test
  public void parseHostWithMultipleSeparators() {
    parser = new HttpProtocolParser("https://127.0.0.0/a/b/c?query");
    assertThat(parser.getProtocol(), equalTo("https://"));
    assertThat(parser.getPort(), equalTo(":80"));
    assertThat(parser.getHost(), equalTo("127.0.0.0"));
    assertThat(parser.getPath(), equalTo("/a/b/c"));
    assertThat(parser.getQuery(), equalTo("query"));
  }

  @Test
  public void parseDefaultHost() {
    parser = new HttpProtocolParser("127.0.0.0?query");
    assertThat(parser.getProtocol(), equalTo("http://"));
    assertThat(parser.getPort(), equalTo(":80"));
    assertThat(parser.getHost(), equalTo("127.0.0.0"));
    assertThat(parser.getPath(), equalTo(""));
    assertThat(parser.getQuery(), equalTo("query"));
  }

  @Test
  public void parseDefaultHostWithMultipleSeparators() {
    parser = new HttpProtocolParser("127.0.0.0/a/b/c?query");
    assertThat(parser.getProtocol(), equalTo("http://"));
    assertThat(parser.getPort(), equalTo(":80"));
    assertThat(parser.getHost(), equalTo("127.0.0.0"));
    assertThat(parser.getPath(), equalTo("/a/b/c"));
    assertThat(parser.getQuery(), equalTo("query"));
  }

  @Test
  public void parsePort() {
    parser = new HttpProtocolParser("https://127.0.0.0:85?query");
    assertThat(parser.getProtocol(), equalTo("https://"));
    assertThat(parser.getPort(), equalTo(":85"));
    assertThat(parser.getHost(), equalTo("127.0.0.0"));
    assertThat(parser.getPath(), equalTo(""));
    assertThat(parser.getQuery(), equalTo("query"));
  }

  @Test
  public void parsePortWithMultipleSeparators() {
    parser = new HttpProtocolParser("https://127.0.0.0:85/a/b/c?query");
    assertThat(parser.getProtocol(), equalTo("https://"));
    assertThat(parser.getPort(), equalTo(":85"));
    assertThat(parser.getHost(), equalTo("127.0.0.0"));
    assertThat(parser.getPath(), equalTo("/a/b/c"));
    assertThat(parser.getQuery(), equalTo("query"));
  }

  @Test
  public void parseDefaultPort() {
    parser = new HttpProtocolParser("127.0.0.0:85?query");
    assertThat(parser.getProtocol(), equalTo("http://"));
    assertThat(parser.getPort(), equalTo(":85"));
    assertThat(parser.getHost(), equalTo("127.0.0.0"));
    assertThat(parser.getPath(), equalTo(""));
    assertThat(parser.getQuery(), equalTo("query"));
  }

  @Test
  public void parseDefaultPortWithMultipleSeparators() {
    parser = new HttpProtocolParser("127.0.0.0:85/a/b/c?query");
    assertThat(parser.getProtocol(), equalTo("http://"));
    assertThat(parser.getPort(), equalTo(":85"));
    assertThat(parser.getHost(), equalTo("127.0.0.0"));
    assertThat(parser.getPath(), equalTo("/a/b/c"));
    assertThat(parser.getQuery(), equalTo("query"));
  }
}