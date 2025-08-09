package org.datanucleus.metadata.annotations;

import java.util.Map;

public class AnnotationObject {
   String name;
   Map nameValueMap;

   public AnnotationObject(String name, Map map) {
      this.name = name;
      this.nameValueMap = map;
   }

   public String getName() {
      return this.name;
   }

   public Map getNameValueMap() {
      return this.nameValueMap;
   }
}
