package org.apache.avro.generic;

public interface IndexedRecord extends GenericContainer {
   void put(int i, Object v);

   Object get(int i);
}
