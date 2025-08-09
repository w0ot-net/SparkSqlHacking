package org.codehaus.janino.util;

import java.util.Map;

public interface Annotatable {
   ClassFile.Annotation[] getAnnotations(boolean var1);

   void addAnnotationsAttributeEntry(boolean var1, String var2, Map var3);
}
