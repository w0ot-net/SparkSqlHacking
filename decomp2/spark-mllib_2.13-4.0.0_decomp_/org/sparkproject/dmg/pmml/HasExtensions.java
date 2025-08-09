package org.sparkproject.dmg.pmml;

import java.util.List;

public interface HasExtensions {
   boolean hasExtensions();

   List getExtensions();

   PMMLObject addExtensions(Extension... var1);
}
