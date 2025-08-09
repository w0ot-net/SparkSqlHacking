package org.sparkproject.dmg.pmml.time_series;

import org.sparkproject.dmg.pmml.PMMLObject;

interface HasMA {
   Integer getQ();

   PMMLObject setQ(Integer var1);

   MA getMA();

   PMMLObject setMA(MA var1);
}
