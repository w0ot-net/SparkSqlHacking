package org.datanucleus.store.rdbms.mapping;

import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;

public interface MappingConsumer {
   int MAPPING_TYPE_VERSION = 1;
   int MAPPING_TYPE_DATASTORE_ID = 2;
   int MAPPING_TYPE_DISCRIMINATOR = 3;
   int MAPPING_TYPE_EXTERNAL_INDEX = 4;
   int MAPPING_TYPE_EXTERNAL_FK = 5;
   int MAPPING_TYPE_EXTERNAL_FK_DISCRIM = 6;
   int MAPPING_TYPE_MULTITENANCY = 7;

   void preConsumeMapping(int var1);

   void consumeMapping(JavaTypeMapping var1, AbstractMemberMetaData var2);

   void consumeMapping(JavaTypeMapping var1, int var2);

   void consumeUnmappedColumn(Column var1);
}
