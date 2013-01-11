package com.ventana.gwt.mobilebrowser.client.url;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ANewlyCreatedUrlParserShould {
  private static final String URL = "http://127.0.0.0:80";
  private UrlParser parser;

  @Test(expected = IllegalArgumentException.class)
  public void throwException() {
    new UrlParser(null);
  }

  @Test
  public void parseHost() {
    parser = new UrlParser("127.0.0.0");
    assertThat(parser.getProtocol(), equalTo("http://"));
    assertThat(parser.getPort(), equalTo(":80"));
    assertThat(parser.getHost(), equalTo("127.0.0.0"));
    assertThat(parser.getUrl(), equalTo(URL));
    assertThat(parser.getIndex(), equalTo(0));
  }

  @Test
  public void parseHostWtihIndex() {
    parser = new UrlParser("127.0.0.0:85|999");
    assertThat(parser.getProtocol(), equalTo("http://"));
    assertThat(parser.getPort(), equalTo(":85"));
    assertThat(parser.getHost(), equalTo("127.0.0.0"));
    assertThat(parser.getUrl(), equalTo("http://127.0.0.0:85"));
    assertThat(parser.getIndex(), equalTo(999));
  }

  @Test
  public void parseHostWithMultipleSeparators() {
    parser = new UrlParser("127.0.0.0/a/b/c");
    assertThat(parser.getProtocol(), equalTo("http://"));
    assertThat(parser.getPort(), equalTo(":80"));
    assertThat(parser.getHost(), equalTo("127.0.0.0"));
    assertThat(parser.getUrl(), equalTo(URL + "/a/b/c"));
    assertThat(parser.getIndex(), equalTo(0));
  }

  @Test
  public void parseHostWithMultipleSeparatorsWithIndex() {
    parser = new UrlParser("https://127.0.0.0:85/a/b/c|999");
    assertThat(parser.getProtocol(), equalTo("https://"));
    assertThat(parser.getPort(), equalTo(":85"));
    assertThat(parser.getHost(), equalTo("127.0.0.0"));
    assertThat(parser.getUrl(), equalTo("https://127.0.0.0:85/a/b/c"));
    assertThat(parser.getIndex(), equalTo(999));
  }

  @Test
  public void parseEmptyName() {
    parser = new UrlParser("https://127.0.0.0");
    assertThat(parser.getSubPath(), equalTo(""));
    assertThat(parser.getUrl(), equalTo("https://127.0.0.0:80"));
    assertThat(parser.getIndex(), equalTo(0));
  }

  @Test
  public void parseEmptyNameWithIndex() {
    parser = new UrlParser("127.0.0.0:85|999");
    assertThat(parser.getSubPath(), equalTo(""));
    assertThat(parser.getUrl(), equalTo("http://127.0.0.0:85"));
    assertThat(parser.getIndex(), equalTo(999));
  }

  @Test
  public void parseName() {
    parser = new UrlParser("127.0.0.0/name");
    assertThat(parser.getSubPath(), equalTo("name"));
    assertThat(parser.getUrl(), equalTo(URL + "/name"));
    assertThat(parser.getIndex(), equalTo(0));
  }

  @Test
  public void parseNameWithIndex() {
    parser = new UrlParser("127.0.0.0/name|999");
    assertThat(parser.getSubPath(), equalTo("name"));
    assertThat(parser.getUrl(), equalTo(URL + "/name"));
    assertThat(parser.getIndex(), equalTo(999));
  }

  @Test
  public void parseNameWithMultipleSeparators() {
    parser = new UrlParser("127.0.0.0/a/b/c/name");
    assertThat(parser.getSubPath(), equalTo("name"));
    assertThat(parser.getUrl(), equalTo(URL + "/a/b/c/name"));
    assertThat(parser.getIndex(), equalTo(0));
  }

  @Test
  public void parseNameWithMultipleSeparatorsWithIndex() {
    parser = new UrlParser("127.0.0.0/a/b/c/name|999");
    assertThat(parser.getUrl(), equalTo(URL + "/a/b/c/name"));
    assertThat(parser.getSubPath(), equalTo("name"));
    assertThat(parser.getIndex(), equalTo(999));
  }

  @Test
  public void parseQuery() {
    parser = new UrlParser("127.0.0.0?query");
    assertThat(parser.getQuery(), equalTo("query"));
    assertThat(parser.getUrl(), equalTo(URL + "?query"));
    assertThat(parser.getIndex(), equalTo(0));
  }

  @Test
  public void parseQueryWithIndex() {
    parser = new UrlParser("127.0.0.0?query|999");
    assertThat(parser.getQuery(), equalTo("query"));
    assertThat(parser.getUrl(), equalTo(URL + "?query"));
    assertThat(parser.getIndex(), equalTo(999));
  }

  @Test
  public void parseQueryWithMultipleSeparators() {
    parser = new UrlParser("127.0.0.0/a/b/name?query");
    assertThat(parser.getHost(), equalTo("127.0.0.0"));
    assertThat(parser.getSubPath(), equalTo("name"));
    assertThat(parser.getQuery(), equalTo("query"));
    assertThat(parser.getUrl(), equalTo(URL + "/a/b/name?query"));
    assertThat(parser.getIndex(), equalTo(0));
  }

  @Test
  public void parseQueryWithMultipleSeparatorsWithIndex() {
    parser = new UrlParser("127.0.0.0/a/b/name?query|999");
    assertThat(parser.getHost(), equalTo("127.0.0.0"));
    assertThat(parser.getSubPath(), equalTo("name"));
    assertThat(parser.getQuery(), equalTo("query"));
    assertThat(parser.getUrl(), equalTo(URL + "/a/b/name?query"));
    assertThat(parser.getIndex(), equalTo(999));
  }

  @Test
  public void getParentUrl() {
    parser = new UrlParser("127.0.0.0/a/b/name/name?query");
    assertThat(parser.getParentUrl(), equalTo("http://127.0.0.0:80/a/b/name"));
    assertThat(parser.getUrl(), equalTo(URL + "/a/b/name/name?query"));
    assertThat(parser.getIndex(), equalTo(0));
  }

  @Test
  public void getParentUrlWithIndex() {
    parser = new UrlParser("127.0.0.0/a/b/name?query|999");
    assertThat(parser.getParentUrl(), equalTo("http://127.0.0.0:80/a/b"));
    assertThat(parser.getUrl(), equalTo(URL + "/a/b/name?query"));
    assertThat(parser.getIndex(), equalTo(999));
  }

  @Test
  public void getParentUrlWhenNoParentExists() {
    parser = new UrlParser("127.0.0.0");
    assertThat(parser.getParentUrl(), equalTo(URL));
    assertThat(parser.getUrl(), equalTo(URL));
    assertThat(parser.getIndex(), equalTo(0));
  }

  @Test
  public void getParentUrlWhenNoParentExistsWithIndex() {
    parser = new UrlParser("127.0.0.0|999");
    assertThat(parser.getParentUrl(), equalTo(URL));
    assertThat(parser.getUrl(), equalTo(URL));
    assertThat(parser.getIndex(), equalTo(999));
  }
}