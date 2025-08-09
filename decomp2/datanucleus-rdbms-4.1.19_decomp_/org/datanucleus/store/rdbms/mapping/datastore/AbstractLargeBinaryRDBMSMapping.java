package org.datanucleus.store.rdbms.mapping.datastore;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.BitSet;
import javax.imageio.ImageIO;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.TypeConverterMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.types.converters.TypeConverter;
import org.datanucleus.store.types.converters.TypeConverterHelper;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.TypeConversionHelper;

public abstract class AbstractLargeBinaryRDBMSMapping extends AbstractDatastoreMapping {
   public AbstractLargeBinaryRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(storeMgr, mapping);
      this.column = col;
      this.initialize();
   }

   protected void initialize() {
      this.initTypeInfo();
   }

   public void setObject(PreparedStatement ps, int param, Object value) {
      if (value == null) {
         try {
            if (this.column != null && this.column.isDefaultable() && this.column.getDefaultValue() != null) {
               ps.setBytes(param, this.column.getDefaultValue().toString().trim().getBytes());
            } else {
               ps.setNull(param, this.getJDBCType());
            }
         } catch (SQLException sqle) {
            throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Object", "" + param, this.column, sqle.getMessage()}), sqle);
         }
      } else {
         try {
            if (this.getJavaTypeMapping().isSerialised()) {
               if (!(value instanceof Serializable)) {
                  throw new NucleusDataStoreException(Localiser.msg("055005", new Object[]{value.getClass().getName()}));
               }

               BlobImpl b = new BlobImpl(value);
               ps.setBytes(param, b.getBytes(0L, (int)b.length()));
            } else if (value instanceof boolean[]) {
               byte[] data = TypeConversionHelper.getByteArrayFromBooleanArray(value);
               ps.setBytes(param, data);
            } else if (value instanceof char[]) {
               byte[] data = TypeConversionHelper.getByteArrayFromCharArray(value);
               ps.setBytes(param, data);
            } else if (value instanceof double[]) {
               byte[] data = TypeConversionHelper.getByteArrayFromDoubleArray(value);
               ps.setBytes(param, data);
            } else if (value instanceof float[]) {
               byte[] data = TypeConversionHelper.getByteArrayFromFloatArray(value);
               ps.setBytes(param, data);
            } else if (value instanceof int[]) {
               byte[] data = TypeConversionHelper.getByteArrayFromIntArray(value);
               ps.setBytes(param, data);
            } else if (value instanceof long[]) {
               byte[] data = TypeConversionHelper.getByteArrayFromLongArray(value);
               ps.setBytes(param, data);
            } else if (value instanceof short[]) {
               byte[] data = TypeConversionHelper.getByteArrayFromShortArray(value);
               ps.setBytes(param, data);
            } else if (value instanceof Boolean[]) {
               byte[] data = TypeConversionHelper.getByteArrayFromBooleanObjectArray(value);
               ps.setBytes(param, data);
            } else if (value instanceof Byte[]) {
               byte[] data = TypeConversionHelper.getByteArrayFromByteObjectArray(value);
               ps.setBytes(param, data);
            } else if (value instanceof Character[]) {
               byte[] data = TypeConversionHelper.getByteArrayFromCharObjectArray(value);
               ps.setBytes(param, data);
            } else if (value instanceof Double[]) {
               byte[] data = TypeConversionHelper.getByteArrayFromDoubleObjectArray(value);
               ps.setBytes(param, data);
            } else if (value instanceof Float[]) {
               byte[] data = TypeConversionHelper.getByteArrayFromFloatObjectArray(value);
               ps.setBytes(param, data);
            } else if (value instanceof Integer[]) {
               byte[] data = TypeConversionHelper.getByteArrayFromIntObjectArray(value);
               ps.setBytes(param, data);
            } else if (value instanceof Long[]) {
               byte[] data = TypeConversionHelper.getByteArrayFromLongObjectArray(value);
               ps.setBytes(param, data);
            } else if (value instanceof Short[]) {
               byte[] data = TypeConversionHelper.getByteArrayFromShortObjectArray(value);
               ps.setBytes(param, data);
            } else if (value instanceof BigDecimal[]) {
               byte[] data = TypeConversionHelper.getByteArrayFromBigDecimalArray(value);
               ps.setBytes(param, data);
            } else if (value instanceof BigInteger[]) {
               byte[] data = TypeConversionHelper.getByteArrayFromBigIntegerArray(value);
               ps.setBytes(param, data);
            } else if (value instanceof byte[]) {
               ps.setBytes(param, (byte[])value);
            } else if (value instanceof BitSet) {
               byte[] data = TypeConversionHelper.getByteArrayFromBooleanArray(TypeConversionHelper.getBooleanArrayFromBitSet((BitSet)value));
               ps.setBytes(param, data);
            } else if (value instanceof BufferedImage) {
               ByteArrayOutputStream baos = new ByteArrayOutputStream(8192);
               ImageIO.write((BufferedImage)value, "jpg", baos);
               byte[] buffer = baos.toByteArray();
               baos.close();
               ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
               ps.setBytes(param, buffer);
               bais.close();
            } else {
               if (!(value instanceof Serializable)) {
                  throw new NucleusDataStoreException(Localiser.msg("055005", new Object[]{value.getClass().getName()}));
               }

               BlobImpl b = new BlobImpl(value);
               ps.setBytes(param, b.getBytes(0L, (int)b.length()));
            }
         } catch (Exception e) {
            throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Object", "" + value, this.column, e.getMessage()}), e);
         }
      }

   }

   protected Object getObjectForBytes(byte[] bytes, int param) {
      String typeName = this.getJavaTypeMapping().getType();
      if (this.getJavaTypeMapping() instanceof TypeConverterMapping) {
         TypeConverter conv = ((TypeConverterMapping)this.getJavaTypeMapping()).getTypeConverter();
         Class datastoreType = TypeConverterHelper.getDatastoreTypeForTypeConverter(conv, this.getJavaTypeMapping().getJavaType());
         typeName = datastoreType.getName();
      }

      if (this.getJavaTypeMapping().isSerialised()) {
         try {
            BlobImpl blob = new BlobImpl(bytes);
            return blob.getObject();
         } catch (SQLException var6) {
            return null;
         }
      } else if (typeName.equals(ClassNameConstants.BOOLEAN_ARRAY)) {
         return TypeConversionHelper.getBooleanArrayFromByteArray(bytes);
      } else if (typeName.equals(ClassNameConstants.BYTE_ARRAY)) {
         return bytes;
      } else if (typeName.equals(ClassNameConstants.CHAR_ARRAY)) {
         return TypeConversionHelper.getCharArrayFromByteArray(bytes);
      } else if (typeName.equals(ClassNameConstants.DOUBLE_ARRAY)) {
         return TypeConversionHelper.getDoubleArrayFromByteArray(bytes);
      } else if (typeName.equals(ClassNameConstants.FLOAT_ARRAY)) {
         return TypeConversionHelper.getFloatArrayFromByteArray(bytes);
      } else if (typeName.equals(ClassNameConstants.INT_ARRAY)) {
         return TypeConversionHelper.getIntArrayFromByteArray(bytes);
      } else if (typeName.equals(ClassNameConstants.LONG_ARRAY)) {
         return TypeConversionHelper.getLongArrayFromByteArray(bytes);
      } else if (typeName.equals(ClassNameConstants.SHORT_ARRAY)) {
         return TypeConversionHelper.getShortArrayFromByteArray(bytes);
      } else if (typeName.equals(ClassNameConstants.JAVA_LANG_BOOLEAN_ARRAY)) {
         return TypeConversionHelper.getBooleanObjectArrayFromByteArray(bytes);
      } else if (typeName.equals(ClassNameConstants.JAVA_LANG_BYTE_ARRAY)) {
         return TypeConversionHelper.getByteObjectArrayFromByteArray(bytes);
      } else if (typeName.equals(ClassNameConstants.JAVA_LANG_CHARACTER_ARRAY)) {
         return TypeConversionHelper.getCharObjectArrayFromByteArray(bytes);
      } else if (typeName.equals(ClassNameConstants.JAVA_LANG_DOUBLE_ARRAY)) {
         return TypeConversionHelper.getDoubleObjectArrayFromByteArray(bytes);
      } else if (typeName.equals(ClassNameConstants.JAVA_LANG_FLOAT_ARRAY)) {
         return TypeConversionHelper.getFloatObjectArrayFromByteArray(bytes);
      } else if (typeName.equals(ClassNameConstants.JAVA_LANG_INTEGER_ARRAY)) {
         return TypeConversionHelper.getIntObjectArrayFromByteArray(bytes);
      } else if (typeName.equals(ClassNameConstants.JAVA_LANG_LONG_ARRAY)) {
         return TypeConversionHelper.getLongObjectArrayFromByteArray(bytes);
      } else if (typeName.equals(ClassNameConstants.JAVA_LANG_SHORT_ARRAY)) {
         return TypeConversionHelper.getShortObjectArrayFromByteArray(bytes);
      } else if (typeName.equals(BigDecimal[].class.getName())) {
         return TypeConversionHelper.getBigDecimalArrayFromByteArray(bytes);
      } else if (typeName.equals(BigInteger[].class.getName())) {
         return TypeConversionHelper.getBigIntegerArrayFromByteArray(bytes);
      } else if (this.getJavaTypeMapping().getJavaType() != null && this.getJavaTypeMapping().getJavaType().getName().equals(BitSet.class.getName())) {
         return TypeConversionHelper.getBitSetFromBooleanArray(TypeConversionHelper.getBooleanArrayFromByteArray(bytes));
      } else if (this.getJavaTypeMapping().getJavaType() != null && this.getJavaTypeMapping().getJavaType().getName().equals(BufferedImage.class.getName())) {
         try {
            return ImageIO.read(new ByteArrayInputStream(bytes));
         } catch (IOException e) {
            throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Object", "" + param, this.column, e.getMessage()}), e);
         }
      } else {
         try {
            BlobImpl blob = new BlobImpl(bytes);
            return blob.getObject();
         } catch (SQLException var8) {
            return null;
         }
      }
   }

   public Object getObject(ResultSet rs, int param) {
      byte[] bytes = null;

      try {
         bytes = rs.getBytes(param);
      } catch (SQLException sqle) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Object", "" + param, this.column, sqle.getMessage()}), sqle);
      }

      return bytes == null ? null : this.getObjectForBytes(bytes, param);
   }

   public void setString(PreparedStatement ps, int exprIndex, String value) {
      this.setObject(ps, exprIndex, value);
   }

   public String getString(ResultSet resultSet, int exprIndex) {
      Object obj = this.getObject(resultSet, exprIndex);
      return (String)obj;
   }
}
