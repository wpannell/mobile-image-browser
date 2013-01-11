package com.ventana.gwt.mobilebrowser.client.url;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ANewlyCreatedFolderIndexParserShould {
  private static final boolean YES = true;
  private static final boolean NO = false;
  private FolderIndexParser parser;

  @Test(expected = IllegalArgumentException.class)
  public void throwExceptionOnNull() {
    new FolderIndexParser(null);
  }

  @Test
  public void parseRootFolder() {
    parser = new FolderIndexParser("Y%127.0.0.0|12");
    assertThat(parser.isRoot(), equalTo(YES));
    assertThat(parser.getUrl(), equalTo("127.0.0.0"));
    assertThat(parser.getIndex(), equalTo(12));
  }

  @Test
  public void parseFolder() {
    parser = new FolderIndexParser("n%127.0.0.0|33");
    assertThat(parser.isRoot(), equalTo(NO));
    assertThat(parser.getUrl(), equalTo("127.0.0.0"));
    assertThat(parser.getIndex(), equalTo(33));
  }

  @Test
  public void parseDefaultFolder() {
    parser = new FolderIndexParser("127.0.0.0|33");
    assertThat(parser.isRoot(), equalTo(NO));
    assertThat(parser.getUrl(), equalTo("127.0.0.0"));
    assertThat(parser.getIndex(), equalTo(33));
  }

  @Test
  public void parseDefaultIndex() {
    parser = new FolderIndexParser("y%127.0.0.0");
    assertThat(parser.isRoot(), equalTo(YES));
    assertThat(parser.getUrl(), equalTo("127.0.0.0"));
    assertThat(parser.getIndex(), equalTo(0));
  }

  @Test
  public void parseDefaultFolderAndIndex() {
    parser = new FolderIndexParser("127.0.0.0");
    assertThat(parser.isRoot(), equalTo(NO));
    assertThat(parser.getUrl(), equalTo("127.0.0.0"));
    assertThat(parser.getIndex(), equalTo(0));
  }
}