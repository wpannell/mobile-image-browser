package com.ventana.gwt.mobilebrowser.external;

import com.ventana.gwt.viewer.shared.QualityLevel;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class AQualityLevelsBuilderShould {
  private ArrayList<QualityLevel> expected;
  private ArrayList<QualityLevel> actual;

  @Before
  public void create() {
    expected = new ArrayList<QualityLevel>(Arrays.asList(new QualityLevel[]{
        new QualityLevel(56976, 19088), new QualityLevel(28488, 9544),
        new QualityLevel(14244, 4772), new QualityLevel(7122, 2386),
        new QualityLevel(3561, 1193), new QualityLevel(1781, 597),
        new QualityLevel(891, 299), new QualityLevel(446, 150),
        new QualityLevel(223, 75)}));

  }

  @Test
  public void buildHardCodedNewlyCreatedQualityLevels() {
    actual = new QualitityLevelsBuilder(new int[][] {
        {56976, 19088},
        {28488, 9544},
        {14244, 4772},
        {7122, 2386},
        {3561, 1193},
        {1781, 597},
        {891, 299},
        {446, 150},
        {223, 75}}).build();
    for (int i = 0; i < expected.size(); i++) {
      assertThat(actual.get(i).getHeight(), equalTo(expected.get(i).getHeight()));
      assertThat(actual.get(i).getWidth(), equalTo(expected.get(i).getWidth()));
    }
  }
}


