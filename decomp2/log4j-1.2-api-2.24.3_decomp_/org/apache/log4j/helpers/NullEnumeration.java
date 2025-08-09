package org.apache.log4j.helpers;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public final class NullEnumeration implements Enumeration {
   private static final NullEnumeration INSTANCE = new NullEnumeration();

   private NullEnumeration() {
   }

   public static NullEnumeration getInstance() {
      return INSTANCE;
   }

   public boolean hasMoreElements() {
      return false;
   }

   public Object nextElement() {
      throw new NoSuchElementException();
   }
}
