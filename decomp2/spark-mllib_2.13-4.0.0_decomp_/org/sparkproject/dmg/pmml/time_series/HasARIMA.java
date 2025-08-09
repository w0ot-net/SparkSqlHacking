package org.sparkproject.dmg.pmml.time_series;

import org.sparkproject.dmg.pmml.PMMLObject;

public interface HasARIMA extends HasARMA {
   Integer getD();

   PMMLObject setD(Integer var1);
}
