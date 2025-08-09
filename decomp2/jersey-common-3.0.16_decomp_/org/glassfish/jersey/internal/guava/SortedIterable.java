package org.glassfish.jersey.internal.guava;

import java.util.Comparator;
import java.util.Iterator;

interface SortedIterable extends Iterable {
   Comparator comparator();

   Iterator iterator();
}
