package org.datanucleus.store.rdbms.mapping.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.types.converters.ColumnLengthDefiningTypeConverter;
import org.datanucleus.store.types.converters.TypeConverter;
import org.datanucleus.store.types.converters.TypeConverterHelper;
import org.datanucleus.util.Localiser;

public class TypeConverterMapping extends SingleFieldMapping {
   TypeConverter converter;

   public void initialize(RDBMSStoreManager storeMgr, String type) {
      ClassLoaderResolver clr = storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
      Class fieldType = clr.classForName(type);
      this.converter = storeMgr.getNucleusContext().getTypeManager().getDefaultTypeConverterForType(fieldType);
      if (this.converter == null) {
         throw new NucleusUserException("Unable to find TypeConverter for converting " + fieldType + " to String");
      } else {
         super.initialize(storeMgr, type);
      }
   }

   public void initialize(AbstractMemberMetaData mmd, Table table, ClassLoaderResolver clr) {
      this.initialize(mmd, table, clr, (TypeConverter)null);
   }

   public void initialize(AbstractMemberMetaData mmd, Table table, ClassLoaderResolver clr, TypeConverter conv) {
      if (mmd.getTypeConverterName() != null) {
         this.converter = table.getStoreManager().getNucleusContext().getTypeManager().getTypeConverterForName(mmd.getTypeConverterName());
         if (this.converter == null) {
            throw new NucleusUserException(Localiser.msg("044062", new Object[]{mmd.getFullFieldName(), mmd.getTypeConverterName()}));
         }
      } else {
         if (conv == null) {
            throw new NucleusUserException("Unable to initialise mapping of type " + this.getClass().getName() + " for field " + mmd.getFullFieldName() + " since no TypeConverter was provided");
         }

         this.converter = conv;
      }

      super.initialize(mmd, table, clr);
   }

   public TypeConverter getTypeConverter() {
      return this.converter;
   }

   public int getDefaultLength(int index) {
      return this.converter instanceof ColumnLengthDefiningTypeConverter ? ((ColumnLengthDefiningTypeConverter)this.converter).getDefaultColumnLength(index) : super.getDefaultLength(index);
   }

   public String getJavaTypeForDatastoreMapping(int index) {
      return TypeConverterHelper.getDatastoreTypeForTypeConverter(this.converter, this.getJavaType()).getName();
   }

   public Class getJavaType() {
      ClassLoaderResolver clr = this.storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
      if (this.mmd != null) {
         if (this.roleForMember == FieldRole.ROLE_COLLECTION_ELEMENT) {
            return clr.classForName(this.mmd.getCollection().getElementType());
         } else if (this.roleForMember == FieldRole.ROLE_ARRAY_ELEMENT) {
            return clr.classForName(this.mmd.getArray().getElementType());
         } else if (this.roleForMember == FieldRole.ROLE_MAP_KEY) {
            return clr.classForName(this.mmd.getMap().getKeyType());
         } else {
            return this.roleForMember == FieldRole.ROLE_MAP_VALUE ? clr.classForName(this.mmd.getMap().getValueType()) : this.mmd.getType();
         }
      } else {
         return clr.classForName(this.type);
      }
   }

   protected void setDatastoreFromMemberValue(PreparedStatement ps, int[] exprIndex, Object memberValue) {
      Object convertedValue = this.converter.toDatastoreType(memberValue);
      if (convertedValue == null) {
         this.getDatastoreMapping(0).setObject(ps, exprIndex[0], (Object)null);
      } else if (convertedValue instanceof Boolean) {
         this.getDatastoreMapping(0).setBoolean(ps, exprIndex[0], (Boolean)convertedValue);
      } else if (convertedValue instanceof Byte) {
         this.getDatastoreMapping(0).setByte(ps, exprIndex[0], (Byte)convertedValue);
      } else if (convertedValue instanceof Character) {
         this.getDatastoreMapping(0).setChar(ps, exprIndex[0], (Character)convertedValue);
      } else if (convertedValue instanceof Double) {
         this.getDatastoreMapping(0).setDouble(ps, exprIndex[0], (Double)convertedValue);
      } else if (convertedValue instanceof Float) {
         this.getDatastoreMapping(0).setFloat(ps, exprIndex[0], (Float)convertedValue);
      } else if (convertedValue instanceof Integer) {
         this.getDatastoreMapping(0).setInt(ps, exprIndex[0], (Integer)convertedValue);
      } else if (convertedValue instanceof Long) {
         this.getDatastoreMapping(0).setLong(ps, exprIndex[0], (Long)convertedValue);
      } else if (convertedValue instanceof Short) {
         this.getDatastoreMapping(0).setShort(ps, exprIndex[0], (Short)convertedValue);
      } else if (convertedValue instanceof String) {
         this.getDatastoreMapping(0).setString(ps, exprIndex[0], (String)convertedValue);
      } else {
         this.getDatastoreMapping(0).setObject(ps, exprIndex[0], convertedValue);
      }

   }

   protected Object getMemberValueFromDatastore(ResultSet resultSet, int[] exprIndex) {
      Class datastoreType = TypeConverterHelper.getDatastoreTypeForTypeConverter(this.converter, String.class);
      Object datastoreValue = null;
      if (Boolean.class.isAssignableFrom(datastoreType)) {
         datastoreValue = this.getDatastoreMapping(0).getBoolean(resultSet, exprIndex[0]);
      } else if (Byte.class.isAssignableFrom(datastoreType)) {
         datastoreValue = this.getDatastoreMapping(0).getByte(resultSet, exprIndex[0]);
      } else if (Character.class.isAssignableFrom(datastoreType)) {
         datastoreValue = this.getDatastoreMapping(0).getChar(resultSet, exprIndex[0]);
      } else if (Double.class.isAssignableFrom(datastoreType)) {
         datastoreValue = this.getDatastoreMapping(0).getDouble(resultSet, exprIndex[0]);
      } else if (Float.class.isAssignableFrom(datastoreType)) {
         datastoreValue = this.getDatastoreMapping(0).getFloat(resultSet, exprIndex[0]);
      } else if (Integer.class.isAssignableFrom(datastoreType)) {
         datastoreValue = this.getDatastoreMapping(0).getInt(resultSet, exprIndex[0]);
      } else if (Long.class.isAssignableFrom(datastoreType)) {
         datastoreValue = this.getDatastoreMapping(0).getLong(resultSet, exprIndex[0]);
      } else if (Short.class.isAssignableFrom(datastoreType)) {
         datastoreValue = this.getDatastoreMapping(0).getShort(resultSet, exprIndex[0]);
      } else if (String.class.isAssignableFrom(datastoreType)) {
         datastoreValue = this.getDatastoreMapping(0).getString(resultSet, exprIndex[0]);
      } else {
         datastoreValue = this.getDatastoreMapping(0).getObject(resultSet, exprIndex[0]);
      }

      return this.converter.toMemberType(datastoreValue);
   }

   public void setBoolean(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, boolean value) {
      if (exprIndex != null) {
         this.setDatastoreFromMemberValue(ps, exprIndex, value);
      }
   }

   public boolean getBoolean(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return exprIndex == null ? false : (Boolean)this.getMemberValueFromDatastore(resultSet, exprIndex);
   }

   public void setByte(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, byte value) {
      if (exprIndex != null) {
         this.setDatastoreFromMemberValue(ps, exprIndex, value);
      }
   }

   public byte getByte(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return exprIndex == null ? 0 : (Byte)this.getMemberValueFromDatastore(resultSet, exprIndex);
   }

   public void setChar(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, char value) {
      if (exprIndex != null) {
         this.setDatastoreFromMemberValue(ps, exprIndex, value);
      }
   }

   public char getChar(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return exprIndex == null ? '\u0000' : (Character)this.getMemberValueFromDatastore(resultSet, exprIndex);
   }

   public void setDouble(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, double value) {
      if (exprIndex != null) {
         this.setDatastoreFromMemberValue(ps, exprIndex, value);
      }
   }

   public double getDouble(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return exprIndex == null ? (double)0.0F : (Double)this.getMemberValueFromDatastore(resultSet, exprIndex);
   }

   public void setFloat(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, float value) {
      if (exprIndex != null) {
         this.setDatastoreFromMemberValue(ps, exprIndex, value);
      }
   }

   public float getFloat(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return exprIndex == null ? 0.0F : (Float)this.getMemberValueFromDatastore(resultSet, exprIndex);
   }

   public void setInt(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, int value) {
      if (exprIndex != null) {
         this.setDatastoreFromMemberValue(ps, exprIndex, value);
      }
   }

   public int getInt(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return exprIndex == null ? 0 : (Integer)this.getMemberValueFromDatastore(resultSet, exprIndex);
   }

   public void setLong(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, long value) {
      if (exprIndex != null) {
         this.setDatastoreFromMemberValue(ps, exprIndex, value);
      }
   }

   public long getLong(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return exprIndex == null ? 0L : (Long)this.getMemberValueFromDatastore(resultSet, exprIndex);
   }

   public void setShort(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, short value) {
      if (exprIndex != null) {
         this.setDatastoreFromMemberValue(ps, exprIndex, value);
      }
   }

   public short getShort(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return exprIndex == null ? 0 : (Short)this.getMemberValueFromDatastore(resultSet, exprIndex);
   }

   public void setString(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, String value) {
      if (exprIndex != null) {
         this.setDatastoreFromMemberValue(ps, exprIndex, value);
      }
   }

   public String getString(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return exprIndex == null ? null : (String)this.getMemberValueFromDatastore(resultSet, exprIndex);
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, Object value) {
      if (exprIndex != null) {
         this.setDatastoreFromMemberValue(ps, exprIndex, value);
      }
   }

   public Object getObject(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return exprIndex == null ? null : this.getMemberValueFromDatastore(resultSet, exprIndex);
   }
}
