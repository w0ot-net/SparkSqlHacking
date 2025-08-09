package org.datanucleus.store.rdbms.mapping.java;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.types.converters.MultiColumnConverter;
import org.datanucleus.store.types.converters.TypeConverter;
import org.datanucleus.util.Localiser;

public class TypeConverterMultiMapping extends SingleFieldMultiMapping {
   TypeConverter converter;

   public void initialize(RDBMSStoreManager storeMgr, String type) {
      super.initialize(storeMgr, type);
      ClassLoaderResolver clr = storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
      Class fieldType = clr.classForName(type);
      this.converter = storeMgr.getNucleusContext().getTypeManager().getDefaultTypeConverterForType(fieldType);
      if (this.converter == null) {
         throw new NucleusUserException("Unable to find TypeConverter for converting " + fieldType + " to String");
      } else if (!(this.converter instanceof MultiColumnConverter)) {
         throw new NucleusUserException("Not able to use " + this.getClass().getName() + " for java type " + type + " since provided TypeConverter " + this.converter + " does not implement MultiColumnConverter");
      } else {
         MultiColumnConverter multiConv = (MultiColumnConverter)this.converter;
         Class[] colTypes = multiConv.getDatastoreColumnTypes();

         for(int i = 0; i < colTypes.length; ++i) {
            this.addColumns(colTypes[i].getName());
         }

      }
   }

   public void initialize(AbstractMemberMetaData mmd, Table table, ClassLoaderResolver clr) {
      this.initialize(mmd, table, clr, (TypeConverter)null);
   }

   public void initialize(AbstractMemberMetaData mmd, Table table, ClassLoaderResolver clr, TypeConverter conv) {
      super.initialize(mmd, table, clr);
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

      if (!(this.converter instanceof MultiColumnConverter)) {
         throw new NucleusUserException("Not able to use " + this.getClass().getName() + " for field " + mmd.getFullFieldName() + " since provided TypeConverter " + this.converter + " does not implement MultiColumnConverter");
      } else {
         MultiColumnConverter multiConv = (MultiColumnConverter)this.converter;
         Class[] colTypes = multiConv.getDatastoreColumnTypes();

         for(int i = 0; i < colTypes.length; ++i) {
            this.addColumns(colTypes[i].getName());
         }

      }
   }

   public TypeConverter getTypeConverter() {
      return this.converter;
   }

   public Class getJavaType() {
      return this.mmd != null ? this.mmd.getType() : this.storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null).classForName(this.type);
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, Object value) {
      if (exprIndex != null) {
         Object colArray = this.converter.toDatastoreType(value);
         if (colArray == null) {
            for(int i = 0; i < exprIndex.length; ++i) {
               this.getDatastoreMapping(i).setObject(ps, exprIndex[i], (Object)null);
            }
         } else {
            for(int i = 0; i < exprIndex.length; ++i) {
               Object colValue = Array.get(colArray, i);
               if (colValue == null) {
                  this.getDatastoreMapping(i).setObject(ps, exprIndex[i], (Object)null);
               } else {
                  Class colValCls = colValue.getClass();
                  if (colValCls != Integer.TYPE && colValCls != Integer.class) {
                     if (colValCls != Long.TYPE && colValCls != Long.class) {
                        if (colValCls != Double.TYPE && colValCls != Double.class) {
                           if (colValCls != Float.TYPE && colValCls != Float.class) {
                              if (colValCls == String.class) {
                                 this.getDatastoreMapping(i).setString(ps, exprIndex[i], (String)colValue);
                              } else {
                                 this.getDatastoreMapping(i).setObject(ps, exprIndex[i], colValue);
                              }
                           } else {
                              this.getDatastoreMapping(i).setFloat(ps, exprIndex[i], (Float)colValue);
                           }
                        } else {
                           this.getDatastoreMapping(i).setDouble(ps, exprIndex[i], (Double)colValue);
                        }
                     } else {
                        this.getDatastoreMapping(i).setLong(ps, exprIndex[i], (Long)colValue);
                     }
                  } else {
                     this.getDatastoreMapping(i).setInt(ps, exprIndex[i], (Integer)colValue);
                  }
               }
            }
         }

      }
   }

   public Object getObject(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      if (exprIndex == null) {
         return null;
      } else {
         Object valuesArr = null;
         Class[] colTypes = ((MultiColumnConverter)this.converter).getDatastoreColumnTypes();
         if (colTypes[0] == Integer.TYPE) {
            valuesArr = new int[exprIndex.length];
         } else if (colTypes[0] == Long.TYPE) {
            valuesArr = new long[exprIndex.length];
         } else if (colTypes[0] == Double.TYPE) {
            valuesArr = new double[exprIndex.length];
         } else if (colTypes[0] == Float.TYPE) {
            valuesArr = new double[exprIndex.length];
         } else if (colTypes[0] == String.class) {
            valuesArr = new String[exprIndex.length];
         } else {
            valuesArr = new Object[exprIndex.length];
         }

         boolean isNull = true;

         for(int i = 0; i < exprIndex.length; ++i) {
            String colJavaType = this.getJavaTypeForDatastoreMapping(i);
            if (!colJavaType.equals("int") && !colJavaType.equals("java.lang.Integer")) {
               if (!colJavaType.equals("long") && !colJavaType.equals("java.lang.Long")) {
                  if (!colJavaType.equals("double") && !colJavaType.equals("java.lang.Double")) {
                     if (!colJavaType.equals("float") && !colJavaType.equals("java.lang.Float")) {
                        if (colJavaType.equals("java.lang.String")) {
                           Array.set(valuesArr, i, this.getDatastoreMapping(i).getString(resultSet, exprIndex[i]));
                        } else {
                           Array.set(valuesArr, i, this.getDatastoreMapping(i).getObject(resultSet, exprIndex[i]));
                        }
                     } else {
                        Array.set(valuesArr, i, this.getDatastoreMapping(i).getFloat(resultSet, exprIndex[i]));
                     }
                  } else {
                     Array.set(valuesArr, i, this.getDatastoreMapping(i).getDouble(resultSet, exprIndex[i]));
                  }
               } else {
                  Array.set(valuesArr, i, this.getDatastoreMapping(i).getLong(resultSet, exprIndex[i]));
               }
            } else {
               Array.set(valuesArr, i, this.getDatastoreMapping(i).getInt(resultSet, exprIndex[i]));
            }

            if (isNull && Array.get(valuesArr, i) != null) {
               isNull = false;
            }
         }

         if (isNull) {
            return null;
         } else {
            return this.converter.toMemberType(valuesArr);
         }
      }
   }
}
