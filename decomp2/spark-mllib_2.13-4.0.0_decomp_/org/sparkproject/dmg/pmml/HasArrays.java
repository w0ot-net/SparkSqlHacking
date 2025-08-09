package org.sparkproject.dmg.pmml;

import java.util.List;

public interface HasArrays {
   boolean hasArrays();

   List getArrays();

   PMMLObject addArrays(Array... var1);
}
