package com.ventana.gwt.mobilebrowser.client.commands;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Widget;

public class ConfigureImageUrlCommand implements Command {
  private Widget widget;
  private String selector;
  private String sourceAttribute;
  private String url;

  public void execute(Widget widget, String selector,
      String sourceAttribute, String url) {
    this.widget = widget;
    this.selector = selector;
    this.sourceAttribute = sourceAttribute;
    this.url = url;
    execute();
  }

  @Override
  public void execute() {
    $(widget).find(selector).attr(sourceAttribute, url);
  }
}
