package org.sparkproject.dmg.pmml;

import java.util.List;

public interface HasContinuousDomain {
   boolean hasIntervals();

   List getIntervals();

   Field addIntervals(Interval... var1);
}
