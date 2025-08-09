package org.sparkproject.dmg.pmml.time_series;

public interface HasRequiredAR extends HasAR {
   Integer requireP();

   AR requireAR();
}
