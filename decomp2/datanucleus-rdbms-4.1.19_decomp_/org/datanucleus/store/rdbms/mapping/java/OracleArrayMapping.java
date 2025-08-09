package org.datanucleus.store.rdbms.mapping.java;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.BitSet;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.mapping.datastore.BlobImpl;
import org.datanucleus.store.rdbms.mapping.datastore.OracleBlobRDBMSMapping;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.TypeConversionHelper;

public class OracleArrayMapping extends ArrayMapping {
   public void postInsert(ObjectProvider ownerOP) {
      if (this.containerIsStoredInSingleColumn()) {
         Object value = ownerOP.provideField(this.mmd.getAbsoluteFieldNumber());
         if (value == null) {
            return;
         }

         ExecutionContext ec = ownerOP.getExecutionContext();
         if (this.mmd.getArray().elementIsPersistent()) {
            Object[] arrElements = value;

            for(Object elem : arrElements) {
               if (elem != null) {
                  ObjectProvider elemOP = ec.findObjectProvider(elem);
                  if (elemOP == null || ec.getApiAdapter().getExecutionContext(elem) == null) {
                     ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, elem, false, ownerOP, this.mmd.getAbsoluteFieldNumber());
                  }
               }
            }
         }

         byte[] bytes = new byte[0];

         try {
            if (this.mmd.isSerialized()) {
               if (!(value instanceof Serializable)) {
                  throw new NucleusDataStoreException(Localiser.msg("055005", new Object[]{value.getClass().getName()}));
               }

               BlobImpl b = new BlobImpl(value);
               bytes = b.getBytes(0L, (int)b.length());
            } else if (value instanceof boolean[]) {
               bytes = TypeConversionHelper.getByteArrayFromBooleanArray(value);
            } else if (value instanceof char[]) {
               bytes = TypeConversionHelper.getByteArrayFromCharArray(value);
            } else if (value instanceof double[]) {
               bytes = TypeConversionHelper.getByteArrayFromDoubleArray(value);
            } else if (value instanceof float[]) {
               bytes = TypeConversionHelper.getByteArrayFromFloatArray(value);
            } else if (value instanceof int[]) {
               bytes = TypeConversionHelper.getByteArrayFromIntArray(value);
            } else if (value instanceof long[]) {
               bytes = TypeConversionHelper.getByteArrayFromLongArray(value);
            } else if (value instanceof short[]) {
               bytes = TypeConversionHelper.getByteArrayFromShortArray(value);
            } else if (value instanceof Boolean[]) {
               bytes = TypeConversionHelper.getByteArrayFromBooleanObjectArray(value);
            } else if (value instanceof Byte[]) {
               bytes = TypeConversionHelper.getByteArrayFromByteObjectArray(value);
            } else if (value instanceof Character[]) {
               bytes = TypeConversionHelper.getByteArrayFromCharObjectArray(value);
            } else if (value instanceof Double[]) {
               bytes = TypeConversionHelper.getByteArrayFromDoubleObjectArray(value);
            } else if (value instanceof Float[]) {
               bytes = TypeConversionHelper.getByteArrayFromFloatObjectArray(value);
            } else if (value instanceof Integer[]) {
               bytes = TypeConversionHelper.getByteArrayFromIntObjectArray(value);
            } else if (value instanceof Long[]) {
               bytes = TypeConversionHelper.getByteArrayFromLongObjectArray(value);
            } else if (value instanceof Short[]) {
               bytes = TypeConversionHelper.getByteArrayFromShortObjectArray(value);
            } else if (value instanceof BigDecimal[]) {
               bytes = TypeConversionHelper.getByteArrayFromBigDecimalArray(value);
            } else if (value instanceof BigInteger[]) {
               bytes = TypeConversionHelper.getByteArrayFromBigIntegerArray(value);
            } else if (value instanceof byte[]) {
               bytes = (byte[])value;
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
         } catch (IOException var11) {
         }

         OracleBlobRDBMSMapping.updateBlobColumn(ownerOP, this.getTable(), this.getDatastoreMapping(0), bytes);
      } else {
         super.postInsert(ownerOP);
      }

   }

   public void postUpdate(ObjectProvider sm) {
      if (this.containerIsStoredInSingleColumn()) {
         this.postInsert(sm);
      } else {
         super.postUpdate(sm);
      }

   }
}
