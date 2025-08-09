package org.datanucleus.store.rdbms.adapter;

import java.sql.DatabaseMetaData;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.key.CandidateKey;
import org.datanucleus.store.rdbms.key.ForeignKey;
import org.datanucleus.store.rdbms.key.Index;
import org.datanucleus.store.rdbms.key.PrimaryKey;
import org.datanucleus.util.Localiser;

public class SAPDBAdapter extends BaseDatastoreAdapter {
   public SAPDBAdapter(DatabaseMetaData metadata) {
      super(metadata);
      this.supportedOptions.add("BooleanExpression");
      this.supportedOptions.add("LockWithSelectForUpdate");
      this.supportedOptions.add("Sequences");
      this.supportedOptions.remove("AlterTableDropConstraint_Syntax");
      this.supportedOptions.remove("DeferredConstraints");
      this.supportedOptions.add("BitIsReallyBoolean");
      this.supportedOptions.add("OrderByUsingSelectColumnIndex");
      this.supportedOptions.remove("FkUpdateActionCascade");
      this.supportedOptions.remove("FkUpdateActionDefault");
      this.supportedOptions.remove("FkUpdateActionNull");
      this.supportedOptions.remove("FkUpdateActionRestrict");
   }

   public String getVendorID() {
      return "sapdb";
   }

   public String getSelectWithLockOption() {
      return "EXCLUSIVE LOCK";
   }

   public String getAddPrimaryKeyStatement(PrimaryKey pk, IdentifierFactory factory) {
      return "ALTER TABLE " + pk.getTable().toString() + " ADD " + pk;
   }

   public String getAddCandidateKeyStatement(CandidateKey ck, IdentifierFactory factory) {
      Index idx = new Index(ck);
      idx.setName(ck.getName());
      return this.getCreateIndexStatement(idx, factory);
   }

   public String getAddForeignKeyStatement(ForeignKey fk, IdentifierFactory factory) {
      return "ALTER TABLE " + fk.getTable().toString() + " ADD " + fk;
   }

   public String getSequenceCreateStmt(String sequence_name, Integer min, Integer max, Integer start, Integer increment, Integer cache_size) {
      if (sequence_name == null) {
         throw new NucleusUserException(Localiser.msg("051028"));
      } else {
         StringBuilder stmt = new StringBuilder("CREATE SEQUENCE ");
         stmt.append(sequence_name);
         if (min != null) {
            stmt.append(" MINVALUE " + min);
         }

         if (max != null) {
            stmt.append(" MAXVALUE " + max);
         }

         if (start != null) {
            stmt.append(" START WITH " + start);
         }

         if (increment != null) {
            stmt.append(" INCREMENT BY " + increment);
         }

         if (cache_size != null) {
            stmt.append(" CACHE " + cache_size);
         } else {
            stmt.append(" NOCACHE");
         }

         return stmt.toString();
      }
   }

   public String getSequenceNextStmt(String sequence_name) {
      if (sequence_name == null) {
         throw new NucleusUserException(Localiser.msg("051028"));
      } else {
         StringBuilder stmt = new StringBuilder("SELECT ");
         stmt.append(sequence_name);
         stmt.append(".nextval FROM dual");
         return stmt.toString();
      }
   }
}
