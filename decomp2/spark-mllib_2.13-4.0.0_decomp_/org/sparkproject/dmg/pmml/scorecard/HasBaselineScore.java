package org.sparkproject.dmg.pmml.scorecard;

import org.sparkproject.dmg.pmml.PMMLObject;

public interface HasBaselineScore {
   Number getBaselineScore();

   PMMLObject setBaselineScore(Number var1);
}
