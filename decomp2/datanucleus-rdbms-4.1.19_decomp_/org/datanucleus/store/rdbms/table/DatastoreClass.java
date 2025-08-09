package org.datanucleus.store.rdbms.table;

import java.util.Collection;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.store.rdbms.mapping.MappingConsumer;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;

public interface DatastoreClass extends Table {
   String getType();

   IdentityType getIdentityType();

   boolean isObjectIdDatastoreAttributed();

   boolean isBaseDatastoreClass();

   DatastoreClass getBaseDatastoreClass();

   DatastoreClass getBaseDatastoreClassWithMember(AbstractMemberMetaData var1);

   boolean isSuperDatastoreClass(DatastoreClass var1);

   DatastoreClass getSuperDatastoreClass();

   Collection getSecondaryDatastoreClasses();

   boolean managesClass(String var1);

   String[] getManagedClasses();

   boolean managesMapping(JavaTypeMapping var1);

   String toString();

   JavaTypeMapping getDatastoreIdMapping();

   JavaTypeMapping getMemberMapping(String var1);

   JavaTypeMapping getMemberMapping(AbstractMemberMetaData var1);

   JavaTypeMapping getMemberMappingInDatastoreClass(AbstractMemberMetaData var1);

   void provideDatastoreIdMappings(MappingConsumer var1);

   void providePrimaryKeyMappings(MappingConsumer var1);

   void provideNonPrimaryKeyMappings(MappingConsumer var1);

   void provideMappingsForMembers(MappingConsumer var1, AbstractMemberMetaData[] var2, boolean var3);

   void provideVersionMappings(MappingConsumer var1);

   void provideDiscriminatorMappings(MappingConsumer var1);

   void provideMultitenancyMapping(MappingConsumer var1);

   void provideUnmappedColumns(MappingConsumer var1);

   void provideExternalMappings(MappingConsumer var1, int var2);

   JavaTypeMapping getExternalMapping(AbstractMemberMetaData var1, int var2);

   AbstractMemberMetaData getMetaDataForExternalMapping(JavaTypeMapping var1, int var2);
}
