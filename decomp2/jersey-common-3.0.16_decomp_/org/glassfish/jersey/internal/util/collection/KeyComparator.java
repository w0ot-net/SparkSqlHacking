package org.glassfish.jersey.internal.util.collection;

import java.io.Serializable;

public interface KeyComparator extends Serializable {
   boolean equals(Object var1, Object var2);

   int hash(Object var1);
}
