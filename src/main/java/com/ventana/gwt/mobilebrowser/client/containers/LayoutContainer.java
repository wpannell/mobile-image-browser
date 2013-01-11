package com.ventana.gwt.mobilebrowser.client.containers;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.user.client.ui.SimpleLayoutPanel;

public class LayoutContainer extends SimpleLayoutPanel {

  @Override
  protected void onLoad() {
    super.onLoad();
    $(this).attr("style",
        "position: absolute; left: 0px; right: 0px; top: 0px; bottom: 0px; ");
  }
}