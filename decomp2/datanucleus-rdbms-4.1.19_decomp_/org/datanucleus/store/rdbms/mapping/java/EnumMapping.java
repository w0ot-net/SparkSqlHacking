package org.datanucleus.store.rdbms.mapping.java;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.MetaDataUtils;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;
import org.datanucleus.util.TypeConversionHelper;

public class EnumMapping extends SingleFieldMapping {
   protected String datastoreJavaType;

   public EnumMapping() {
      this.datastoreJavaType = ClassNameConstants.JAVA_LANG_STRING;
   }

   public void initialize(AbstractMemberMetaData mmd, Table table, ClassLoaderResolver clr) {
      if (mmd != null && mmd.isSerialized()) {
         this.datastoreJavaType = ClassNameConstants.JAVA_IO_SERIALIZABLE;
      } else if (mmd != null) {
         ColumnMetaData[] colmds = getColumnMetaDataForMember(mmd, this.roleForMember);
         if (colmds != null && colmds.length > 0 && MetaDataUtils.isJdbcTypeNumeric(colmds[0].getJdbcType())) {
            this.datastoreJavaType = ClassNameConstants.JAVA_LANG_INTEGER;
         }
      }

      super.initialize(mmd, table, clr);
   }

   public Object[] getValidValues(int index) {
      if (this.mmd != null && this.mmd.getColumnMetaData() != null && this.mmd.getColumnMetaData().length > 0 && this.mmd.getColumnMetaData()[0].hasExtension("enum-check-constraint") && this.mmd.getColumnMetaData()[0].getValueForExtension("enum-check-constraint").equalsIgnoreCase("true")) {
         try {
            Enum[] values = (Enum[])this.mmd.getType().getMethod("values", (Class[])null).invoke((Object)null, (Object[])null);
            if (!this.datastoreJavaType.equals(ClassNameConstants.JAVA_LANG_STRING)) {
               Integer[] valueInts = new Integer[values.length];

               for(int i = 0; i < values.length; ++i) {
                  valueInts[i] = (int)TypeConversionHelper.getValueFromEnum(this.mmd, this.roleForMember, values[i]);
               }

               return valueInts;
            }

            String[] valueStrings = new String[values.length];

            for(int i = 0; i < values.length; ++i) {
               valueStrings[i] = values[i].toString();
            }

            return valueStrings;
         } catch (Exception e) {
            NucleusLogger.PERSISTENCE.warn(StringUtils.getStringFromStackTrace(e));
         }
      }

      return super.getValidValues(index);
   }

   public String getJavaTypeForDatastoreMapping(int index) {
      return this.datastoreJavaType;
   }

   public Class getJavaType() {
      return Enum.class;
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, Object value) {
      if (value == null) {
         this.getDatastoreMapping(0).setObject(ps, exprIndex[0], (Object)null);
      } else if (this.datastoreJavaType.equals(ClassNameConstants.JAVA_LANG_INTEGER)) {
         if (value instanceof Enum) {
            int intVal = (int)TypeConversionHelper.getValueFromEnum(this.mmd, this.roleForMember, (Enum)value);
            this.getDatastoreMapping(0).setInt(ps, exprIndex[0], intVal);
         } else if (value instanceof BigInteger) {
            this.getDatastoreMapping(0).setInt(ps, exprIndex[0], ((BigInteger)value).intValue());
         }
      } else if (this.datastoreJavaType.equals(ClassNameConstants.JAVA_LANG_STRING)) {
         String stringVal;
         if (value instanceof String) {
            stringVal = (String)value;
         } else {
            stringVal = ((Enum)value).name();
         }

         this.getDatastoreMapping(0).setString(ps, exprIndex[0], stringVal);
      } else {
         super.setObject(ec, ps, exprIndex, value);
      }

   }

   public Object getObject(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      if (exprIndex == null) {
         return null;
      } else if (this.getDatastoreMapping(0).getObject(resultSet, exprIndex[0]) == null) {
         return null;
      } else if (this.datastoreJavaType.equals(ClassNameConstants.JAVA_LANG_INTEGER)) {
         long longVal = this.getDatastoreMapping(0).getLong(resultSet, exprIndex[0]);
         Class enumType = null;
         if (this.mmd == null) {
            enumType = ec.getClassLoaderResolver().classForName(this.type);
            return enumType.getEnumConstants()[(int)longVal];
         } else {
            return TypeConversionHelper.getEnumFromValue(this.mmd, this.roleForMember, ec, longVal);
         }
      } else if (this.datastoreJavaType.equals(ClassNameConstants.JAVA_LANG_STRING)) {
         String stringVal = this.getDatastoreMapping(0).getString(resultSet, exprIndex[0]);
         Class enumType = null;
         if (this.mmd == null) {
            enumType = ec.getClassLoaderResolver().classForName(this.type);
         } else {
            enumType = this.mmd.getType();
            if (this.roleForMember == FieldRole.ROLE_COLLECTION_ELEMENT) {
               enumType = ec.getClassLoaderResolver().classForName(this.mmd.getCollection().getElementType());
            } else if (this.roleForMember == FieldRole.ROLE_ARRAY_ELEMENT) {
               enumType = ec.getClassLoaderResolver().classForName(this.mmd.getArray().getElementType());
            } else if (this.roleForMember == FieldRole.ROLE_MAP_KEY) {
               enumType = ec.getClassLoaderResolver().classForName(this.mmd.getMap().getKeyType());
            } else if (this.roleForMember == FieldRole.ROLE_MAP_VALUE) {
               enumType = ec.getClassLoaderResolver().classForName(this.mmd.getMap().getValueType());
            }
         }

         return Enum.valueOf(enumType, stringVal);
      } else {
         return super.getObject(ec, resultSet, exprIndex);
      }
   }
}
