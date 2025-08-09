package org.sparkproject.dmg.pmml;

import java.util.List;

public interface HasDerivedFields {
   boolean hasDerivedFields();

   List getDerivedFields();

   PMMLObject addDerivedFields(DerivedField... var1);
}
