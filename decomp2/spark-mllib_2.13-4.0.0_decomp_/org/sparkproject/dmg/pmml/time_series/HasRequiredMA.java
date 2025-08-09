package org.sparkproject.dmg.pmml.time_series;

public interface HasRequiredMA extends HasMA {
   Integer requireQ();

   MA requireMA();
}
