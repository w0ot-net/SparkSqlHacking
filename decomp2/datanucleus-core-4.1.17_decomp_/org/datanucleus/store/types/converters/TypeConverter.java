package org.datanucleus.store.types.converters;

import java.io.Serializable;

public interface TypeConverter extends Serializable {
   Object toDatastoreType(Object var1);

   Object toMemberType(Object var1);
}
