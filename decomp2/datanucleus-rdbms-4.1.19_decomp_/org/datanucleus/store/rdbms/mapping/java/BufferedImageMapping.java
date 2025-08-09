package org.datanucleus.store.rdbms.mapping.java;

import java.awt.image.BufferedImage;
import org.datanucleus.ClassNameConstants;

public class BufferedImageMapping extends SingleFieldMapping {
   public Class getJavaType() {
      return BufferedImage.class;
   }

   public String getJavaTypeForDatastoreMapping(int index) {
      return ClassNameConstants.JAVA_IO_SERIALIZABLE;
   }
}
