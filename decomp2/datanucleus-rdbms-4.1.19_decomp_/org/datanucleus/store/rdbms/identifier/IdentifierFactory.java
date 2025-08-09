package org.datanucleus.store.rdbms.identifier;

import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.schema.naming.NamingCase;

public interface IdentifierFactory {
   DatastoreAdapter getDatastoreAdapter();

   NamingCase getNamingCase();

   String getIdentifierInAdapterCase(String var1);

   DatastoreIdentifier newIdentifier(IdentifierType var1, String var2);

   DatastoreIdentifier newTableIdentifier(String var1);

   DatastoreIdentifier newTableIdentifier(String var1, String var2, String var3);

   DatastoreIdentifier newTableIdentifier(AbstractClassMetaData var1);

   DatastoreIdentifier newTableIdentifier(AbstractMemberMetaData var1);

   DatastoreIdentifier newColumnIdentifier(String var1);

   DatastoreIdentifier newColumnIdentifier(String var1, boolean var2, FieldRole var3, boolean var4);

   DatastoreIdentifier newReferenceFieldIdentifier(AbstractMemberMetaData var1, AbstractClassMetaData var2, DatastoreIdentifier var3, boolean var4, FieldRole var5);

   DatastoreIdentifier newDiscriminatorFieldIdentifier();

   DatastoreIdentifier newVersionFieldIdentifier();

   DatastoreIdentifier newIdentifier(DatastoreIdentifier var1, String var2);

   DatastoreIdentifier newJoinTableFieldIdentifier(AbstractMemberMetaData var1, AbstractMemberMetaData var2, DatastoreIdentifier var3, boolean var4, FieldRole var5);

   DatastoreIdentifier newForeignKeyFieldIdentifier(AbstractMemberMetaData var1, AbstractMemberMetaData var2, DatastoreIdentifier var3, boolean var4, FieldRole var5);

   DatastoreIdentifier newIndexFieldIdentifier(AbstractMemberMetaData var1);

   DatastoreIdentifier newAdapterIndexFieldIdentifier();

   DatastoreIdentifier newSequenceIdentifier(String var1);

   DatastoreIdentifier newPrimaryKeyIdentifier(Table var1);

   DatastoreIdentifier newIndexIdentifier(Table var1, boolean var2, int var3);

   DatastoreIdentifier newCandidateKeyIdentifier(Table var1, int var2);

   DatastoreIdentifier newForeignKeyIdentifier(Table var1, int var2);
}
