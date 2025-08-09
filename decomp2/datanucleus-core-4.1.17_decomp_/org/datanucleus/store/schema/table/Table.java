package org.datanucleus.store.schema.table;

import java.util.List;
import java.util.Set;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.StoreManager;

public interface Table {
   StoreManager getStoreManager();

   String getName();

   String getSchemaName();

   String getCatalogName();

   AbstractClassMetaData getClassMetaData();

   int getNumberOfColumns();

   List getColumns();

   Column getColumnForPosition(int var1);

   Column getDatastoreIdColumn();

   Column getVersionColumn();

   Column getDiscriminatorColumn();

   Column getMultitenancyColumn();

   Column getColumnForName(String var1);

   MemberColumnMapping getMemberColumnMappingForMember(AbstractMemberMetaData var1);

   MemberColumnMapping getMemberColumnMappingForEmbeddedMember(List var1);

   Set getMemberColumnMappings();
}
