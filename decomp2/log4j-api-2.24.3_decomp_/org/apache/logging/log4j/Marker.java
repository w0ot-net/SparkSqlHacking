package org.apache.logging.log4j;

import java.io.Serializable;

public interface Marker extends Serializable {
   Marker addParents(Marker... markers);

   boolean equals(Object obj);

   String getName();

   Marker[] getParents();

   int hashCode();

   boolean hasParents();

   boolean isInstanceOf(Marker m);

   boolean isInstanceOf(String name);

   boolean remove(Marker marker);

   Marker setParents(Marker... markers);
}
