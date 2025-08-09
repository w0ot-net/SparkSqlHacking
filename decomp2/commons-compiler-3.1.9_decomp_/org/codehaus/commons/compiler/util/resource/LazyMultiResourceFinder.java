package org.codehaus.commons.compiler.util.resource;

import java.util.Iterator;
import org.codehaus.commons.compiler.util.iterator.IteratorCollection;

public class LazyMultiResourceFinder extends MultiResourceFinder {
   public LazyMultiResourceFinder(Iterator resourceFinders) {
      super((Iterable)(new IteratorCollection(resourceFinders)));
   }
}
