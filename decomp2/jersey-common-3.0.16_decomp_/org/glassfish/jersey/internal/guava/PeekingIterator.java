package org.glassfish.jersey.internal.guava;

import java.util.Iterator;

public interface PeekingIterator extends Iterator {
   Object peek();

   Object next();

   void remove();
}
