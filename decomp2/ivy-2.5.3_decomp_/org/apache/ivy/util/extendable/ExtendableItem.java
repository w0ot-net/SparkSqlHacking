package org.apache.ivy.util.extendable;

import java.util.Map;

public interface ExtendableItem {
   String getAttribute(String var1);

   String getExtraAttribute(String var1);

   Map getAttributes();

   Map getExtraAttributes();

   Map getQualifiedExtraAttributes();
}
