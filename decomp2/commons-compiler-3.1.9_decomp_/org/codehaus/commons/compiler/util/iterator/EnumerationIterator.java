package org.codehaus.commons.compiler.util.iterator;

import java.util.Enumeration;
import java.util.Iterator;

public class EnumerationIterator implements Iterator {
   private final Enumeration e;

   public EnumerationIterator(Enumeration e) {
      this.e = e;
   }

   public boolean hasNext() {
      return this.e.hasMoreElements();
   }

   public Object next() {
      return this.e.nextElement();
   }

   public void remove() {
      throw new UnsupportedOperationException("remove");
   }
}
