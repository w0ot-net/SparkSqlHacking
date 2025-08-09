package org.sparkproject.dmg.pmml;

import java.util.List;

public interface HasMixedContent {
   boolean hasContent();

   List getContent();

   PMMLObject addContent(Object... var1);
}
