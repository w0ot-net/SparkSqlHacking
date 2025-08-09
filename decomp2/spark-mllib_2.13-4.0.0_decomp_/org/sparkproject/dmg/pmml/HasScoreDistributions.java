package org.sparkproject.dmg.pmml;

import java.util.List;

public interface HasScoreDistributions extends HasScore {
   Object requireScore();

   boolean hasScoreDistributions();

   List getScoreDistributions();

   PMMLObject addScoreDistributions(ScoreDistribution... var1);
}
