package com.ventana.gwt.mobilebrowser.client.commands;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;

public class BrowserBackCommand implements Command {
  @Override
  public void execute() {
    History.back();
  }
}
