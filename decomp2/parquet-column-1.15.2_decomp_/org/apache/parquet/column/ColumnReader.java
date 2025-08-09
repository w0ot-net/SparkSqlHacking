package org.apache.parquet.column;

import org.apache.parquet.io.api.Binary;

public interface ColumnReader {
   /** @deprecated */
   @Deprecated
   long getTotalValueCount();

   void consume();

   int getCurrentRepetitionLevel();

   int getCurrentDefinitionLevel();

   void writeCurrentValueToConverter();

   void skip();

   int getCurrentValueDictionaryID();

   int getInteger();

   boolean getBoolean();

   long getLong();

   Binary getBinary();

   float getFloat();

   double getDouble();

   ColumnDescriptor getDescriptor();
}
