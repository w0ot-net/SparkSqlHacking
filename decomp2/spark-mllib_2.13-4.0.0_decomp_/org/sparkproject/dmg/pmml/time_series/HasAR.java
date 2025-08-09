package org.sparkproject.dmg.pmml.time_series;

import org.sparkproject.dmg.pmml.PMMLObject;

public interface HasAR {
   Integer getP();

   PMMLObject setP(Integer var1);

   AR getAR();

   PMMLObject setAR(AR var1);
}
