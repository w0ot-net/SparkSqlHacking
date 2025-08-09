package org.datanucleus.identity;

public interface DatastoreId {
   Object getKeyAsObject();

   String getTargetClassName();

   boolean equals(Object var1);

   int hashCode();

   String toString();
}
