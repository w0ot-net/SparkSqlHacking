package org.datanucleus.store.rdbms.table;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.FieldPersistenceModifier;
import org.datanucleus.metadata.PrimaryKeyMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.key.PrimaryKey;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.util.Localiser;

public abstract class JoinTable extends TableImpl {
   protected final AbstractMemberMetaData mmd;
   protected JavaTypeMapping ownerMapping;
   protected final String ownerType;

   protected JoinTable(DatastoreIdentifier tableName, AbstractMemberMetaData mmd, RDBMSStoreManager storeMgr) {
      super(tableName, storeMgr);
      this.mmd = mmd;
      this.ownerType = mmd.getClassName(true);
      if (mmd.getPersistenceModifier() == FieldPersistenceModifier.NONE) {
         throw (new NucleusException(Localiser.msg("057006", new Object[]{mmd.getName()}))).setFatal();
      }
   }

   public PrimaryKey getPrimaryKey() {
      PrimaryKey pk = super.getPrimaryKey();
      if (this.mmd.getJoinMetaData() != null) {
         PrimaryKeyMetaData pkmd = this.mmd.getJoinMetaData().getPrimaryKeyMetaData();
         if (pkmd != null && pkmd.getName() != null) {
            pk.setName(pkmd.getName());
         }
      }

      return pk;
   }

   protected boolean requiresPrimaryKey() {
      boolean pkRequired = true;
      if (this.mmd.getJoinMetaData() != null && this.mmd.getJoinMetaData().hasExtension("primary-key") && this.mmd.getJoinMetaData().getValueForExtension("primary-key").equalsIgnoreCase("false")) {
         pkRequired = false;
      }

      return pkRequired;
   }

   public JavaTypeMapping getOwnerMapping() {
      this.assertIsInitialized();
      return this.ownerMapping;
   }

   public AbstractMemberMetaData getOwnerMemberMetaData() {
      return this.mmd;
   }

   public JavaTypeMapping getIdMapping() {
      throw (new NucleusException("Unsupported ID mapping in join table")).setFatal();
   }
}
