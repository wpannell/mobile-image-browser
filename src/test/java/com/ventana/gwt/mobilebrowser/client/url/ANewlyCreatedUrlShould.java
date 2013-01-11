package com.ventana.gwt.mobilebrowser.client.url;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ANewlyCreatedUrlShould {
  private static final String BASE_URL = "http://127.0.0.0:80";
  private static final boolean YES = true;
  private static final boolean NO = false;
  private Url url;

  @Test(expected = IllegalArgumentException.class)
  public void throwException() {
    new Url(null);
  }

  @Test
  public void parseHost() {
    url = new Url("127.0.0.0");
    assertThat(url.getHost(), equalTo("127.0.0.0"));
  }

  @Test
  public void parseUrl() {
    url = new Url("127.0.0.0");
    assertThat(url.toString(), equalTo(BASE_URL));
  }

  @Test
  public void parsePath() {
    url = new Url("127.0.0.0");
    assertThat(url.getPath(), equalTo(""));
  }

  @Test
  public void parseName() {
    url = new Url("127.0.0.0");
    assertThat(url.getSubPath(), equalTo(""));
  }

  @Test
  public void parseQuery() {
    url = new Url("127.0.0.0");
    assertThat(url.getQuery(), equalTo(""));
  }

  @Test
  public void parseFolder() {
    url = new Url("127.0.0.0");
    assertThat(url.isRoot(), equalTo(NO));
  }

  @Test
  public void parseEncodedFolder() {
    url = new Url("n%127.0.0.0");
    assertThat(url.isRoot(), equalTo(NO));
  }

  @Test
  public void parseEncodedRootFolder() {
    url = new Url("Y%127.0.0.0");
    assertThat(url.isRoot(), equalTo(YES));
  }

  @Test
  public void parseHostWithQuery() {
    url = new Url("127.0.0.0?query");
    assertThat(url.getHost(), equalTo("127.0.0.0"));
  }

  @Test
  public void parseUrlWithQuery() {
    url = new Url("127.0.0.0?query");
    assertThat(url.toString(), equalTo(BASE_URL + "?query"));
  }

  @Test
  public void parsePathWithQuery() {
    url = new Url("127.0.0.0?query");
    assertThat(url.getPath(), equalTo(""));
  }

  @Test
  public void parseNameWithQuery() {
    url = new Url("127.0.0.0?query");
    assertThat(url.getSubPath(), equalTo(""));
  }

  @Test
  public void parseQueryWithQuery() {
    url = new Url("127.0.0.0?query");
    assertThat(url.getQuery(), equalTo("query"));
  }

  @Test
  public void parseFolderWithQuery() {
    url = new Url("127.0.0.0?query");
    assertThat(url.isRoot(), equalTo(NO));
  }

  @Test
  public void parseEncodedFolderWithQuery() {
    url = new Url("N%127.0.0.0?query");
    assertThat(url.isRoot(), equalTo(NO));
  }

  @Test
  public void parseEncodedRootFolderWithQuery() {
    url = new Url("y%127.0.0.0?query");
    assertThat(url.isRoot(), equalTo(YES));
  }

  @Test
  public void parseHostWithPath() {
    url = new Url("127.0.0.0/name");
    assertThat(url.getHost(), equalTo("127.0.0.0"));
  }

  @Test
  public void parseUrlWithPath() {
    url = new Url("127.0.0.0/name");
    assertThat(url.toString(), equalTo(BASE_URL + "/name"));
  }

  @Test
  public void parsePathWithPath() {
    url = new Url("127.0.0.0/name");
    assertThat(url.getPath(), equalTo("/name"));
  }

  @Test
  public void parseNameWithPath() {
    url = new Url("127.0.0.0/name");
    assertThat(url.getSubPath(), equalTo("name"));
  }

  @Test
  public void parseQueryWithPath() {
    url = new Url("127.0.0.0/name");
    assertThat(url.getQuery(), equalTo(""));
  }

  @Test
  public void parseFolderWithPath() {
    url = new Url("127.0.0.0/name");
    assertThat(url.isRoot(), equalTo(NO));
  }

  @Test
  public void parseEncodedFolderWithPath() {
    url = new Url("N%127.0.0.0/name");
    assertThat(url.isRoot(), equalTo(NO));
  }

  @Test
  public void parseEncodedRootFolderWithPath() {
    url = new Url("y%127.0.0.0/name");
    assertThat(url.isRoot(), equalTo(YES));
  }

  @Test
  public void parseHostWithPathAndQuery() {
    url = new Url("127.0.0.0/name?query");
    assertThat(url.getHost(), equalTo("127.0.0.0"));
  }

  @Test
  public void parseUrlWithPathAndQuery() {
    url = new Url("127.0.0.0/name?query");
    assertThat(url.toString(), equalTo(BASE_URL + "/name?query"));
  }

  @Test
  public void parsePathWithPathAndQuery() {
    url = new Url("127.0.0.0/name?query");
    assertThat(url.getPath(), equalTo("/name"));
  }

  @Test
  public void parseNameWithPathAndQuery() {
    url = new Url("127.0.0.0/name?query");
    assertThat(url.getSubPath(), equalTo("name"));
  }

  @Test
  public void parseQueryWithPathAndQuery() {
    url = new Url("127.0.0.0/name?query");
    assertThat(url.getQuery(), equalTo("query"));
  }

  @Test
  public void parseFolderWithPathAndQuery() {
    url = new Url("127.0.0.0/name?query");
    assertThat(url.isRoot(), equalTo(NO));
  }

  @Test
  public void parseEncodedFolderWithPathAndQuery() {
    url = new Url("N%127.0.0.0/name?query");
    assertThat(url.isRoot(), equalTo(NO));
  }

  @Test
  public void parseEncodedRootFolderWithPathAndQuery() {
    url = new Url("y%127.0.0.0/name?query");
    assertThat(url.isRoot(), equalTo(YES));
  }

  @Test
  public void parseHostWithComplexPath() {
    url = new Url("127.0.0.0/a/b/c");
    assertThat(url.getHost(), equalTo("127.0.0.0"));
  }

  @Test
  public void parseUrlWithComplexPath() {
    url = new Url("127.0.0.0/a/b/c");
    assertThat(url.toString(), equalTo(BASE_URL + "/a/b/c"));
  }

  @Test
  public void parsePathWithComplexPath() {
    url = new Url("127.0.0.0/a/b/c");
    assertThat(url.getPath(), equalTo("/a/b/c"));
  }

  @Test
  public void parseNameWithComplexPath() {
    url = new Url("127.0.0.0/a/b/c");
    assertThat(url.getSubPath(), equalTo("c"));
  }

  @Test
  public void parseQueryWithComplexPath() {
    url = new Url("127.0.0.0/a/b/c");
    assertThat(url.getQuery(), equalTo(""));
  }

  @Test
  public void parseFolderWithComplexPath() {
    url = new Url("127.0.0.0/a/b/c");
    assertThat(url.isRoot(), equalTo(NO));
  }

  @Test
  public void parseEncodedFolderWithComplexPath() {
    url = new Url("N%127.0.0.0/a/b/c");
    assertThat(url.isRoot(), equalTo(NO));
  }

  @Test
  public void parseEncodedRootFolderWithComplexPath() {
    url = new Url("y%127.0.0.0/a/b/c");
    assertThat(url.isRoot(), equalTo(YES));
  }

  @Test
  public void parseHostWithComplexPathAndQuery() {
    url = new Url("127.0.0.0/a/b/c?query");
    assertThat(url.getHost(), equalTo("127.0.0.0"));
  }

  @Test
  public void parseUrlWithComplexPathAndQuery() {
    url = new Url("127.0.0.0/a/b/c?query");
    assertThat(url.toString(), equalTo(BASE_URL + "/a/b/c?query"));
  }

  @Test
  public void parsePathWithComplexPathAndQuery() {
    url = new Url("127.0.0.0/a/b/c?query");
    assertThat(url.getPath(), equalTo("/a/b/c"));
  }

  @Test
  public void parseNameWithComplexPathAndQuery() {
    url = new Url("127.0.0.0/a/b/c?query");
    assertThat(url.getSubPath(), equalTo("c"));
  }

  @Test
  public void parseQueryWithComplexPathAndQuery() {
    url = new Url("127.0.0.0/a/b/c?query");
    assertThat(url.getQuery(), equalTo("query"));
  }

  @Test
  public void parseFolderWithComplexPathAndQuery() {
    url = new Url("127.0.0.0/a/b/c?query");
    assertThat(url.isRoot(), equalTo(NO));
  }

  @Test
  public void parseEncodedFolderWithComplexPathAndQuery() {
    url = new Url("N%127.0.0.0/a/b/c?query");
    assertThat(url.isRoot(), equalTo(NO));
  }

  @Test
  public void parseEncodedRootFolderWithComplexPathAndQuery() {
    url = new Url("y%127.0.0.0/a/b/c?query");
    assertThat(url.isRoot(), equalTo(YES));
  }

  @Test
  public void getParentUrl() {
    url = new Url("127.0.0.0/a/b/b?query");
    assertThat(url.getUrl(), equalTo(BASE_URL + "/a/b/b?query"));
    assertThat(url.getParentUrl(), equalTo(BASE_URL + "/a/b"));
  }

  @Test
  public void getParentUrlWhenNoParentExists() {
    url = new Url("127.0.0.0");
    assertThat(url.getParentUrl(), equalTo(url.getUrl()));
  }
}