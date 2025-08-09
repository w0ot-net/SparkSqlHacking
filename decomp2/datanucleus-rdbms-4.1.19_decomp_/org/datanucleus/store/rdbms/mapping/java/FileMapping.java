package org.datanucleus.store.rdbms.mapping.java;

import java.io.File;

public class FileMapping extends SingleFieldMapping {
   public Class getJavaType() {
      return File.class;
   }
}
