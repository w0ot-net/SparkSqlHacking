package org.sparkproject.dmg.pmml.time_series;

import java.util.List;
import org.sparkproject.dmg.pmml.PMMLObject;

public interface HasDynamicRegressors {
   boolean hasDynamicRegressors();

   List getDynamicRegressors();

   PMMLObject addDynamicRegressors(DynamicRegressor... var1);
}
