package com.ventana.gwt.mobilebrowser.external;

import com.ventana.gwt.viewer.shared.QualityLevel;

import java.util.ArrayList;

public class QualitityLevelsBuilder {
  private final int[][] levels;
  private final ArrayList<QualityLevel> qualityLevels;
  public QualitityLevelsBuilder(int[][] levels) {
    this.levels = levels;
    qualityLevels = new ArrayList<QualityLevel>();
  }

  public ArrayList<QualityLevel> build() {
    for (int i = 0; i < levels.length; i++)
      qualityLevels.add(new QualityLevel(levels[i][0],levels[i][1]));
    return qualityLevels;
  }
}
