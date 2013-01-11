package com.ventana.gwt.mobilebrowser.client.widgets;

import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.constants.ControlGroupType;

public class InterrogatingControlGroup extends ControlGroup {
  private ControlGroupType type = ControlGroupType.NONE;

  public InterrogatingControlGroup() {
    super("");
  }

  public InterrogatingControlGroup(final String html) {
    super(html);
  }

  public ControlGroupType getType() {
    return type;
  }

  @Override
  public void setType(final ControlGroupType type) {
    this.type = type;
    super.setType(type);
  }

}
