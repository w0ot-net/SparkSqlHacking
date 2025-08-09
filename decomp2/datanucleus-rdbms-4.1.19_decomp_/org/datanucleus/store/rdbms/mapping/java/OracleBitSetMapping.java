package org.datanucleus.store.rdbms.mapping.java;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.BitSet;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.mapping.MappingCallbacks;
import org.datanucleus.store.rdbms.mapping.datastore.BlobImpl;
import org.datanucleus.store.rdbms.mapping.datastore.OracleBlobRDBMSMapping;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.TypeConversionHelper;

public class OracleBitSetMapping extends BitSetMapping implements MappingCallbacks {
   public void insertPostProcessing(ObjectProvider op) {
      Object value = op.provideField(this.mmd.getAbsoluteFieldNumber());
      if (value != null) {
         byte[] bytes = new byte[0];

         try {
            if (this.mmd.isSerialized()) {
               if (!(value instanceof Serializable)) {
                  throw new NucleusDataStoreException(Localiser.msg("055005", new Object[]{value.getClass().getName()}));
               }

               BlobImpl b = new BlobImpl(value);
               bytes = b.getBytes(0L, (int)b.length());
            } else if (value instanceof BitSet) {
               bytes = TypeConversionHelper.getByteArrayFromBooleanArray(TypeConversionHelper.getBooleanArrayFromBitSet((BitSet)value));
            } else {
               if (!(value instanceof Serializable)) {
                  throw new NucleusDataStoreException(Localiser.msg("055005", new Object[]{value.getClass().getName()}));
               }

               BlobImpl b = new BlobImpl(value);
               bytes = b.getBytes(0L, (int)b.length());
            }
         } catch (SQLException e) {
            throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Object", "" + value, this.mmd, e.getMessage()}), e);
         } catch (IOException var6) {
         }

         OracleBlobRDBMSMapping.updateBlobColumn(op, this.getTable(), this.getDatastoreMapping(0), bytes);
      }
   }

   public void postInsert(ObjectProvider op) {
   }

   public void postUpdate(ObjectProvider op) {
      this.insertPostProcessing(op);
   }

   public void deleteDependent(ObjectProvider op) {
   }

   public void postFetch(ObjectProvider op) {
   }

   public void preDelete(ObjectProvider op) {
   }
}
