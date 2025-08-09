package org.datanucleus.store.rdbms.mapping.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.types.converters.TypeConverter;
import org.datanucleus.store.types.converters.TypeConverterHelper;
import org.datanucleus.util.Localiser;

public class UUIDMapping extends SingleFieldMapping {
   TypeConverter converter;

   public void initialize(RDBMSStoreManager storeMgr, String type) {
      boolean useConverter = true;
      if (this.mmd != null) {
         ColumnMetaData[] colmds = this.mmd.getColumnMetaData();
         if (colmds != null && colmds.length == 1) {
            ColumnMetaData colmd = colmds[0];
            if (colmd.getSqlType() != null) {
               useConverter = false;
            }
         }
      }

      if (useConverter) {
         ClassLoaderResolver clr = storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
         Class fieldType = clr.classForName(type);
         this.converter = storeMgr.getNucleusContext().getTypeManager().getDefaultTypeConverterForType(fieldType);
         if (this.converter == null) {
            throw new NucleusUserException("Unable to find TypeConverter for converting " + fieldType + " to String");
         }
      }

      super.initialize(storeMgr, type);
   }

   public void initialize(AbstractMemberMetaData mmd, Table table, ClassLoaderResolver clr) {
      boolean useConverter = true;
      if (mmd != null) {
         ColumnMetaData[] colmds = mmd.getColumnMetaData();
         if (colmds != null && colmds.length == 1) {
            ColumnMetaData colmd = colmds[0];
            if (colmd.getSqlType() != null) {
               useConverter = false;
            }
         }
      }

      if (useConverter) {
         if (mmd.getTypeConverterName() != null) {
            this.converter = table.getStoreManager().getNucleusContext().getTypeManager().getTypeConverterForName(mmd.getTypeConverterName());
            if (this.converter == null) {
               throw new NucleusUserException(Localiser.msg("044062", new Object[]{mmd.getFullFieldName(), mmd.getTypeConverterName()}));
            }
         } else {
            this.converter = table.getStoreManager().getNucleusContext().getTypeManager().getTypeConverterForType(mmd.getType(), String.class);
         }
      }

      super.initialize(mmd, table, clr);
   }

   public Class getJavaType() {
      return UUID.class;
   }

   public String getJavaTypeForDatastoreMapping(int index) {
      return this.converter == null ? UUID.class.getName() : TypeConverterHelper.getDatastoreTypeForTypeConverter(this.converter, this.getJavaType()).getName();
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, Object value) {
      if (exprIndex != null) {
         if (this.converter == null) {
            super.setObject(ec, ps, exprIndex, value);
         } else {
            this.getDatastoreMapping(0).setObject(ps, exprIndex[0], this.converter.toDatastoreType(value));
         }

      }
   }

   public Object getObject(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      if (exprIndex == null) {
         return null;
      } else if (this.converter == null) {
         return super.getObject(ec, resultSet, exprIndex);
      } else {
         Object datastoreValue = this.getDatastoreMapping(0).getObject(resultSet, exprIndex[0]);
         return datastoreValue != null ? this.converter.toMemberType(datastoreValue) : null;
      }
   }
}
