package shaded.parquet.it.unimi.dsi.fastutil;

import java.util.Iterator;

public interface BidirectionalIterator extends Iterator {
   Object previous();

   boolean hasPrevious();
}
