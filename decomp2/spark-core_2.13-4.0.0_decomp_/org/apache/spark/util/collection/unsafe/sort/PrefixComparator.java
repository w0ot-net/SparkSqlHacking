package org.apache.spark.util.collection.unsafe.sort;

import org.apache.spark.annotation.Private;

@Private
public abstract class PrefixComparator {
   public abstract int compare(long var1, long var3);
}
