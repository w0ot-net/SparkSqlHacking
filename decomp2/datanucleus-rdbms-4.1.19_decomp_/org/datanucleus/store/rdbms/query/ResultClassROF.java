package org.datanucleus.store.rdbms.query;

import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class ResultClassROF implements ResultObjectFactory {
   private final RDBMSStoreManager storeMgr;
   private final Class resultClass;
   private final StatementMappingIndex[] stmtMappings;
   private StatementResultMapping resultDefinition;
   private final String[] resultFieldNames;
   private final Class[] resultFieldTypes;
   private final Map resultClassFieldsByName = new HashMap();
   private static Map resultSetGetters = new HashMap(20);

   public ResultClassROF(RDBMSStoreManager storeMgr, Class cls, StatementResultMapping resultDefinition) {
      this.storeMgr = storeMgr;
      Class tmpClass = null;
      if (cls != null && cls.getName().equals("java.util.Map")) {
         tmpClass = HashMap.class;
      } else if (cls == null) {
         if (resultDefinition.getNumberOfResultExpressions() == 1) {
            tmpClass = Object.class;
         } else {
            tmpClass = Object[].class;
         }
      } else {
         tmpClass = cls;
      }

      this.resultClass = tmpClass;
      this.resultDefinition = resultDefinition;
      this.stmtMappings = null;
      if (resultDefinition != null) {
         this.resultFieldNames = new String[resultDefinition.getNumberOfResultExpressions()];
         this.resultFieldTypes = new Class[resultDefinition.getNumberOfResultExpressions()];

         for(int i = 0; i < this.resultFieldNames.length; ++i) {
            Object stmtMap = resultDefinition.getMappingForResultExpression(i);
            if (stmtMap instanceof StatementMappingIndex) {
               StatementMappingIndex idx = (StatementMappingIndex)stmtMap;
               this.resultFieldNames[i] = idx.getColumnAlias();
               this.resultFieldTypes[i] = idx.getMapping().getJavaType();
            } else if (!(stmtMap instanceof StatementNewObjectMapping) && !(stmtMap instanceof StatementClassMapping)) {
               throw new NucleusUserException("Unsupported component " + stmtMap.getClass().getName() + " found in results");
            }
         }
      } else {
         this.resultFieldNames = null;
         this.resultFieldTypes = null;
      }

   }

   public ResultClassROF(RDBMSStoreManager storeMgr, Class cls, StatementClassMapping classDefinition) {
      this.storeMgr = storeMgr;
      Class tmpClass = null;
      if (cls != null && cls.getName().equals("java.util.Map")) {
         tmpClass = HashMap.class;
      } else {
         tmpClass = cls;
      }

      this.resultClass = tmpClass;
      this.resultDefinition = null;
      int[] memberNumbers = classDefinition.getMemberNumbers();
      this.stmtMappings = new StatementMappingIndex[memberNumbers.length];
      this.resultFieldNames = new String[this.stmtMappings.length];
      this.resultFieldTypes = new Class[this.stmtMappings.length];

      for(int i = 0; i < this.stmtMappings.length; ++i) {
         this.stmtMappings[i] = classDefinition.getMappingForMemberPosition(memberNumbers[i]);
         this.resultFieldNames[i] = this.stmtMappings[i].getMapping().getMemberMetaData().getName();
         this.resultFieldTypes[i] = this.stmtMappings[i].getMapping().getJavaType();
      }

   }

   public ResultClassROF(RDBMSStoreManager storeMgr, Class cls, String[] resultFieldNames) {
      this.storeMgr = storeMgr;
      Class tmpClass = null;
      if (cls != null && cls.getName().equals("java.util.Map")) {
         tmpClass = HashMap.class;
      } else {
         tmpClass = cls;
      }

      this.resultClass = tmpClass;
      if (QueryUtils.resultClassIsUserType(this.resultClass.getName())) {
         AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
               ResultClassROF.this.populateDeclaredFieldsForUserType(ResultClassROF.this.resultClass);
               return null;
            }
         });
      }

      this.stmtMappings = null;
      this.resultFieldTypes = null;
      if (resultFieldNames == null) {
         this.resultFieldNames = new String[0];
      } else {
         this.resultFieldNames = resultFieldNames;
      }

   }

   public Object getObject(ExecutionContext ec, ResultSet rs) {
      Object[] fieldValues = null;
      if (this.resultDefinition != null) {
         fieldValues = new Object[this.resultDefinition.getNumberOfResultExpressions()];

         for(int i = 0; i < this.resultDefinition.getNumberOfResultExpressions(); ++i) {
            Object stmtMap = this.resultDefinition.getMappingForResultExpression(i);
            if (stmtMap instanceof StatementMappingIndex) {
               StatementMappingIndex idx = (StatementMappingIndex)stmtMap;
               fieldValues[i] = idx.getMapping().getObject(ec, rs, idx.getColumnPositions());
            } else if (stmtMap instanceof StatementNewObjectMapping) {
               StatementNewObjectMapping newIdx = (StatementNewObjectMapping)stmtMap;
               fieldValues[i] = this.getValueForNewObject(newIdx, ec, rs);
            } else if (stmtMap instanceof StatementClassMapping) {
               StatementClassMapping classMap = (StatementClassMapping)stmtMap;
               Class cls = ec.getClassLoaderResolver().classForName(classMap.getClassName());
               AbstractClassMetaData acmd = ec.getMetaDataManager().getMetaDataForClass(cls, ec.getClassLoaderResolver());
               PersistentClassROF rof = new PersistentClassROF(this.storeMgr, acmd, classMap, false, ec.getFetchPlan(), cls);
               fieldValues[i] = rof.getObject(ec, rs);
            }
         }
      } else if (this.stmtMappings != null) {
         fieldValues = new Object[this.stmtMappings.length];

         for(int i = 0; i < this.stmtMappings.length; ++i) {
            if (this.stmtMappings[i] != null) {
               fieldValues[i] = this.stmtMappings[i].getMapping().getObject(ec, rs, this.stmtMappings[i].getColumnPositions());
            } else {
               fieldValues[i] = null;
            }
         }
      } else {
         try {
            fieldValues = new Object[this.resultFieldNames.length];

            for(int i = 0; i < fieldValues.length; ++i) {
               fieldValues[i] = this.getResultObject(rs, i + 1);
            }
         } catch (SQLException sqe) {
            String msg = Localiser.msg("021043", new Object[]{sqe.getMessage()});
            NucleusLogger.QUERY.error(msg);
            throw new NucleusUserException(msg);
         }
      }

      if (this.resultClass == Object[].class) {
         return fieldValues;
      } else if (QueryUtils.resultClassIsSimple(this.resultClass.getName())) {
         if (fieldValues.length != 1 || fieldValues[0] != null && !this.resultClass.isAssignableFrom(fieldValues[0].getClass())) {
            if (fieldValues.length == 1 && !this.resultClass.isAssignableFrom(fieldValues[0].getClass())) {
               String msg = Localiser.msg("021202", new Object[]{this.resultClass.getName(), fieldValues[0].getClass().getName()});
               NucleusLogger.QUERY.error(msg);
               throw new NucleusUserException(msg);
            } else {
               String msg = Localiser.msg("021203", new Object[]{this.resultClass.getName()});
               NucleusLogger.QUERY.error(msg);
               throw new NucleusUserException(msg);
            }
         } else {
            return fieldValues[0];
         }
      } else if (fieldValues.length == 1 && fieldValues[0] != null && this.resultClass.isAssignableFrom(fieldValues[0].getClass())) {
         return fieldValues[0];
      } else {
         Object obj = QueryUtils.createResultObjectUsingArgumentedConstructor(this.resultClass, fieldValues, this.resultFieldTypes);
         if (obj != null) {
            return obj;
         } else {
            if (NucleusLogger.QUERY.isDebugEnabled()) {
               if (this.resultFieldNames != null) {
                  Class[] ctr_arg_types = new Class[this.resultFieldNames.length];

                  for(int i = 0; i < this.resultFieldNames.length; ++i) {
                     if (fieldValues[i] != null) {
                        ctr_arg_types[i] = fieldValues[i].getClass();
                     } else {
                        ctr_arg_types[i] = null;
                     }
                  }

                  NucleusLogger.QUERY.debug(Localiser.msg("021206", new Object[]{this.resultClass.getName(), StringUtils.objectArrayToString(ctr_arg_types)}));
               } else {
                  StringBuilder str = new StringBuilder();

                  for(int i = 0; i < this.stmtMappings.length; ++i) {
                     if (i > 0) {
                        str.append(",");
                     }

                     Class javaType = this.stmtMappings[i].getMapping().getJavaType();
                     str.append(javaType.getName());
                  }

                  NucleusLogger.QUERY.debug(Localiser.msg("021206", new Object[]{this.resultClass.getName(), str.toString()}));
               }
            }

            obj = QueryUtils.createResultObjectUsingDefaultConstructorAndSetters(this.resultClass, this.resultFieldNames, this.resultClassFieldsByName, fieldValues);
            return obj;
         }
      }
   }

   protected Object getValueForNewObject(StatementNewObjectMapping newMap, ExecutionContext ec, ResultSet rs) {
      Object value = null;
      if (newMap.getNumberOfConstructorArgMappings() == 0) {
         try {
            value = newMap.getObjectClass().newInstance();
         } catch (Exception e) {
            throw new NucleusException("Attempt to create object for query result row of type " + newMap.getObjectClass().getName() + " threw an exception", e);
         }
      } else {
         int numArgs = newMap.getNumberOfConstructorArgMappings();
         Class[] ctrArgTypes = new Class[numArgs];
         Object[] ctrArgValues = new Object[numArgs];

         for(int i = 0; i < numArgs; ++i) {
            Object obj = newMap.getConstructorArgMapping(i);
            if (obj instanceof StatementMappingIndex) {
               StatementMappingIndex idx = (StatementMappingIndex)obj;
               ctrArgValues[i] = idx.getMapping().getObject(ec, rs, idx.getColumnPositions());
            } else if (obj instanceof StatementNewObjectMapping) {
               ctrArgValues[i] = this.getValueForNewObject((StatementNewObjectMapping)obj, ec, rs);
            } else {
               ctrArgValues[i] = obj;
            }

            if (ctrArgValues[i] != null) {
               ctrArgTypes[i] = ctrArgValues[i].getClass();
            } else {
               ctrArgTypes[i] = null;
            }
         }

         Constructor ctr = ClassUtils.getConstructorWithArguments(newMap.getObjectClass(), ctrArgTypes);
         if (ctr == null) {
            StringBuilder str = new StringBuilder(newMap.getObjectClass().getName() + "(");

            for(int i = 0; i < ctrArgTypes.length; ++i) {
               str.append(ctrArgTypes[i].getName());
               if (i != ctrArgTypes.length - 1) {
                  str.append(',');
               }
            }

            str.append(")");
            throw new NucleusUserException(Localiser.msg("037013", new Object[]{str.toString()}));
         }

         try {
            value = ctr.newInstance(ctrArgValues);
         } catch (Exception e) {
            throw new NucleusUserException(Localiser.msg("037015", new Object[]{newMap.getObjectClass().getName(), e}));
         }
      }

      return value;
   }

   private void populateDeclaredFieldsForUserType(Class cls) {
      Field[] declaredFields = cls.getDeclaredFields();

      for(int i = 0; i < declaredFields.length; ++i) {
         Field field = declaredFields[i];
         if (!field.isSynthetic() && this.resultClassFieldsByName.put(field.getName().toUpperCase(), field) != null && !field.getName().startsWith(this.storeMgr.getMetaDataManager().getEnhancedMethodNamePrefix())) {
            throw new NucleusUserException(Localiser.msg("021210", new Object[]{field.getName()}));
         }
      }

      if (cls.getSuperclass() != null) {
         this.populateDeclaredFieldsForUserType(cls.getSuperclass());
      }

   }

   private Object getResultObject(ResultSet rs, int columnNumber) throws SQLException {
      ResultSetGetter getter = (ResultSetGetter)resultSetGetters.get(this.resultClass);
      return getter != null ? getter.getValue(rs, columnNumber) : rs.getObject(columnNumber);
   }

   static {
      resultSetGetters.put(Boolean.class, new ResultSetGetter() {
         public Object getValue(ResultSet rs, int i) throws SQLException {
            return rs.getBoolean(i);
         }
      });
      resultSetGetters.put(Byte.class, new ResultSetGetter() {
         public Object getValue(ResultSet rs, int i) throws SQLException {
            return rs.getByte(i);
         }
      });
      resultSetGetters.put(Short.class, new ResultSetGetter() {
         public Object getValue(ResultSet rs, int i) throws SQLException {
            return rs.getShort(i);
         }
      });
      resultSetGetters.put(Integer.class, new ResultSetGetter() {
         public Object getValue(ResultSet rs, int i) throws SQLException {
            return rs.getInt(i);
         }
      });
      resultSetGetters.put(Long.class, new ResultSetGetter() {
         public Object getValue(ResultSet rs, int i) throws SQLException {
            return rs.getLong(i);
         }
      });
      resultSetGetters.put(Float.class, new ResultSetGetter() {
         public Object getValue(ResultSet rs, int i) throws SQLException {
            return rs.getFloat(i);
         }
      });
      resultSetGetters.put(Double.class, new ResultSetGetter() {
         public Object getValue(ResultSet rs, int i) throws SQLException {
            return rs.getDouble(i);
         }
      });
      resultSetGetters.put(BigDecimal.class, new ResultSetGetter() {
         public Object getValue(ResultSet rs, int i) throws SQLException {
            return rs.getBigDecimal(i);
         }
      });
      resultSetGetters.put(byte[].class, new ResultSetGetter() {
         public Object getValue(ResultSet rs, int i) throws SQLException {
            return rs.getBytes(i);
         }
      });
      ResultSetGetter timestampGetter = new ResultSetGetter() {
         public Object getValue(ResultSet rs, int i) throws SQLException {
            return rs.getTimestamp(i);
         }
      };
      resultSetGetters.put(Timestamp.class, timestampGetter);
      resultSetGetters.put(Date.class, timestampGetter);
      resultSetGetters.put(java.sql.Date.class, new ResultSetGetter() {
         public Object getValue(ResultSet rs, int i) throws SQLException {
            return rs.getDate(i);
         }
      });
      resultSetGetters.put(String.class, new ResultSetGetter() {
         public Object getValue(ResultSet rs, int i) throws SQLException {
            return rs.getString(i);
         }
      });
      resultSetGetters.put(Reader.class, new ResultSetGetter() {
         public Object getValue(ResultSet rs, int i) throws SQLException {
            return rs.getCharacterStream(i);
         }
      });
      resultSetGetters.put(Array.class, new ResultSetGetter() {
         public Object getValue(ResultSet rs, int i) throws SQLException {
            return rs.getArray(i);
         }
      });
   }

   private interface ResultSetGetter {
      Object getValue(ResultSet var1, int var2) throws SQLException;
   }
}
