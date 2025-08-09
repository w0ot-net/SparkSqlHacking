package org.apache.derby.agg;

import java.io.Serializable;

public interface Aggregator extends Serializable {
   void init();

   void accumulate(Object var1);

   void merge(Aggregator var1);

   Object terminate();
}
