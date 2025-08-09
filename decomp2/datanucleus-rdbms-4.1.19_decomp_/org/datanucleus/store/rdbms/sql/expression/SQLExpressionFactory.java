package org.datanucleus.store.rdbms.sql.expression;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.plugin.ConfigurationElement;
import org.datanucleus.plugin.PluginManager;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLStatementHelper;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.method.SQLMethod;
import org.datanucleus.store.rdbms.sql.operation.SQLOperation;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class SQLExpressionFactory {
   RDBMSStoreManager storeMgr;
   ClassLoaderResolver clr;
   private final Class[] EXPR_CREATION_ARG_TYPES = new Class[]{SQLStatement.class, SQLTable.class, JavaTypeMapping.class};
   private final Class[] LIT_CREATION_ARG_TYPES = new Class[]{SQLStatement.class, JavaTypeMapping.class, Object.class, String.class};
   Map expressionClassByMappingName = new HashMap();
   Map literalClassByMappingName = new HashMap();
   Set methodNamesSupported = new HashSet();
   Map methodByClassMethodName = new HashMap();
   Set operationNamesSupported = new HashSet();
   Map operationByOperationName = new HashMap();
   Map mappingByClass = new HashMap();

   public SQLExpressionFactory(RDBMSStoreManager storeMgr) {
      this.storeMgr = storeMgr;
      this.clr = storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
      PluginManager pluginMgr = storeMgr.getNucleusContext().getPluginManager();
      ConfigurationElement[] methodElems = pluginMgr.getConfigurationElementsForExtension("org.datanucleus.store.rdbms.sql_method", (String)null, (String)null);
      if (methodElems != null) {
         for(int i = 0; i < methodElems.length; ++i) {
            String datastoreName = methodElems[i].getAttribute("datastore");
            String className = methodElems[i].getAttribute("class");
            String methodName = methodElems[i].getAttribute("method").trim();
            MethodKey key = this.getSQLMethodKey(datastoreName, className, methodName);
            this.methodNamesSupported.add(key);
         }
      }

      ConfigurationElement[] operationElems = pluginMgr.getConfigurationElementsForExtension("org.datanucleus.store.rdbms.sql_operation", (String)null, (String)null);
      if (operationElems != null) {
         for(int i = 0; i < operationElems.length; ++i) {
            String datastoreName = operationElems[i].getAttribute("datastore");
            String name = operationElems[i].getAttribute("name").trim();
            String key = this.getSQLOperationKey(datastoreName, name);
            this.operationNamesSupported.add(key);
         }
      }

   }

   public SQLExpression newExpression(SQLStatement stmt, SQLTable sqlTbl, JavaTypeMapping mapping) {
      return this.newExpression(stmt, sqlTbl, mapping, (JavaTypeMapping)null);
   }

   public SQLExpression newExpression(SQLStatement stmt, SQLTable sqlTbl, JavaTypeMapping mapping, JavaTypeMapping parentMapping) {
      SQLTable exprSqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(stmt, sqlTbl, parentMapping == null ? mapping : parentMapping);
      Object[] args = new Object[]{stmt, exprSqlTbl, mapping};
      Class expressionClass = (Class)this.expressionClassByMappingName.get(mapping.getClass().getName());
      if (expressionClass != null) {
         return (SQLExpression)ClassUtils.newInstance(expressionClass, this.EXPR_CREATION_ARG_TYPES, new Object[]{stmt, exprSqlTbl, mapping});
      } else {
         try {
            SQLExpression sqlExpr = (SQLExpression)this.storeMgr.getNucleusContext().getPluginManager().createExecutableExtension("org.datanucleus.store.rdbms.sql_expression", "mapping-class", mapping.getClass().getName(), "expression-class", this.EXPR_CREATION_ARG_TYPES, args);
            if (sqlExpr == null) {
               throw new NucleusException(Localiser.msg("060004", new Object[]{mapping.getClass().getName()}));
            } else {
               this.expressionClassByMappingName.put(mapping.getClass().getName(), sqlExpr.getClass());
               return sqlExpr;
            }
         } catch (Exception e) {
            String msg = Localiser.msg("060005", new Object[]{mapping.getClass().getName()});
            NucleusLogger.QUERY.error(msg, e);
            throw new NucleusException(msg, e);
         }
      }
   }

   public SQLExpression newLiteral(SQLStatement stmt, JavaTypeMapping mapping, Object value) {
      Object[] args = new Object[]{stmt, mapping, value, null};
      if (mapping != null) {
         Class literalClass = (Class)this.literalClassByMappingName.get(mapping.getClass().getName());
         if (literalClass != null) {
            return (SQLExpression)ClassUtils.newInstance(literalClass, this.LIT_CREATION_ARG_TYPES, args);
         }
      }

      try {
         if (mapping == null) {
            return (SQLExpression)ClassUtils.newInstance(NullLiteral.class, this.LIT_CREATION_ARG_TYPES, args);
         } else {
            SQLExpression sqlExpr = (SQLExpression)this.storeMgr.getNucleusContext().getPluginManager().createExecutableExtension("org.datanucleus.store.rdbms.sql_expression", "mapping-class", mapping.getClass().getName(), "literal-class", this.LIT_CREATION_ARG_TYPES, args);
            if (sqlExpr == null) {
               throw new NucleusException(Localiser.msg("060006", new Object[]{mapping.getClass().getName()}));
            } else {
               this.literalClassByMappingName.put(mapping.getClass().getName(), sqlExpr.getClass());
               return sqlExpr;
            }
         }
      } catch (Exception e) {
         NucleusLogger.QUERY.error("Exception creating SQLLiteral for mapping " + mapping.getClass().getName(), e);
         throw new NucleusException(Localiser.msg("060007", new Object[]{mapping.getClass().getName()}));
      }
   }

   public SQLExpression newLiteralParameter(SQLStatement stmt, JavaTypeMapping mapping, Object value, String paramName) {
      try {
         Object[] args = new Object[]{stmt, mapping, value, paramName};
         if (mapping == null) {
            return (SQLExpression)ClassUtils.newInstance(ParameterLiteral.class, this.LIT_CREATION_ARG_TYPES, args);
         } else {
            SQLExpression sqlExpr = (SQLExpression)this.storeMgr.getNucleusContext().getPluginManager().createExecutableExtension("org.datanucleus.store.rdbms.sql_expression", "mapping-class", mapping.getClass().getName(), "literal-class", this.LIT_CREATION_ARG_TYPES, args);
            if (sqlExpr == null) {
               throw new NucleusException(Localiser.msg("060006", new Object[]{mapping.getClass().getName()}));
            } else {
               return sqlExpr;
            }
         }
      } catch (Exception e) {
         NucleusLogger.QUERY.error("Exception creating SQLLiteral for mapping " + mapping.getClass().getName(), e);
         throw new NucleusException(Localiser.msg("060007", new Object[]{mapping.getClass().getName()}));
      }
   }

   public SQLExpression invokeMethod(SQLStatement stmt, String className, String methodName, SQLExpression expr, List args) {
      SQLMethod method = this.getMethod(className, methodName, args);
      if (method != null) {
         synchronized(method) {
            method.setStatement(stmt);
            return method.getExpression(expr, args);
         }
      } else {
         return null;
      }
   }

   public boolean isMethodRegistered(String className, String methodName) {
      MethodKey methodKey = this.getSQLMethodKey(this.storeMgr.getDatastoreAdapter().getVendorID(), className, methodName);
      if (this.methodNamesSupported.contains(methodKey)) {
         return true;
      } else {
         methodKey = this.getSQLMethodKey((String)null, className, methodName);
         return this.methodNamesSupported.contains(methodKey);
      }
   }

   public void registerMethod(String className, String methodName, SQLMethod method, boolean datastoreDependent) {
      String datastoreId = this.storeMgr.getDatastoreAdapter().getVendorID();
      MethodKey methodKey = this.getSQLMethodKey(datastoreDependent ? null : datastoreId, className, methodName);
      if (this.methodNamesSupported.contains(methodKey)) {
         throw new NucleusUserException("SQLMethod already defined for class=" + className + " method=" + methodName);
      } else {
         this.methodNamesSupported.add(methodKey);
         this.methodByClassMethodName.put(methodKey, method);
      }
   }

   public SQLMethod getMethod(String className, String methodName, List args) {
      String datastoreId = this.storeMgr.getDatastoreAdapter().getVendorID();
      MethodKey methodKey1 = this.getSQLMethodKey(datastoreId, className, methodName);
      MethodKey methodKey2 = null;
      SQLMethod method = (SQLMethod)this.methodByClassMethodName.get(methodKey1);
      if (method == null) {
         methodKey2 = this.getSQLMethodKey((String)null, className, methodName);
         method = (SQLMethod)this.methodByClassMethodName.get(methodKey2);
      }

      if (method != null) {
         return method;
      } else {
         boolean datastoreDependent = true;
         if (!this.methodNamesSupported.contains(methodKey1)) {
            datastoreDependent = false;
            if (!this.methodNamesSupported.contains(methodKey2)) {
               boolean unsupported = true;
               if (!StringUtils.isWhitespace(className)) {
                  Class cls = this.clr.classForName(className);

                  for(MethodKey methodKey : this.methodNamesSupported) {
                     if (methodKey.methodName.equals(methodName) && methodKey.datastoreName.equals(datastoreId)) {
                        Class methodCls = null;

                        try {
                           methodCls = this.clr.classForName(methodKey.clsName);
                        } catch (ClassNotResolvedException var17) {
                        }

                        if (methodCls != null && methodCls.isAssignableFrom(cls)) {
                           method = (SQLMethod)this.methodByClassMethodName.get(methodKey);
                           if (method != null) {
                              return method;
                           }

                           className = methodKey.clsName;
                           datastoreId = methodKey.datastoreName;
                           datastoreDependent = true;
                           unsupported = false;
                           break;
                        }
                     }
                  }

                  if (unsupported) {
                     for(MethodKey methodKey : this.methodNamesSupported) {
                        if (methodKey.methodName.equals(methodName) && methodKey.datastoreName.equals("ALL")) {
                           Class methodCls = null;

                           try {
                              methodCls = this.clr.classForName(methodKey.clsName);
                           } catch (ClassNotResolvedException var16) {
                           }

                           if (methodCls != null && methodCls.isAssignableFrom(cls)) {
                              method = (SQLMethod)this.methodByClassMethodName.get(methodKey);
                              if (method != null) {
                                 return method;
                              }

                              className = methodKey.clsName;
                              datastoreId = methodKey.datastoreName;
                              datastoreDependent = false;
                              unsupported = false;
                              break;
                           }
                        }
                     }
                  }
               }

               if (unsupported) {
                  if (className != null) {
                     throw new NucleusUserException(Localiser.msg("060008", new Object[]{methodName, className}));
                  }

                  throw new NucleusUserException(Localiser.msg("060009", new Object[]{methodName}));
               }
            }
         }

         PluginManager pluginMgr = this.storeMgr.getNucleusContext().getPluginManager();
         String[] attrNames = datastoreDependent ? new String[]{"class", "method", "datastore"} : new String[]{"class", "method"};
         String[] attrValues = datastoreDependent ? new String[]{className, methodName, datastoreId} : new String[]{className, methodName};

         try {
            method = (SQLMethod)pluginMgr.createExecutableExtension("org.datanucleus.store.rdbms.sql_method", attrNames, attrValues, "evaluator", new Class[0], new Object[0]);
            MethodKey key = this.getSQLMethodKey(datastoreDependent ? datastoreId : null, className, methodName);
            this.methodByClassMethodName.put(key, method);
            return method;
         } catch (Exception e) {
            throw new NucleusUserException(Localiser.msg("060011", new Object[]{"class=" + className + " method=" + methodName}), e);
         }
      }
   }

   public SQLExpression invokeOperation(String name, SQLExpression expr, SQLExpression expr2) {
      SQLOperation operation = (SQLOperation)this.operationByOperationName.get(name);
      if (operation != null) {
         return operation.getExpression(expr, expr2);
      } else {
         String datastoreId = this.storeMgr.getDatastoreAdapter().getVendorID();
         String key = this.getSQLOperationKey(datastoreId, name);
         boolean datastoreDependent = true;
         if (!this.operationNamesSupported.contains(key)) {
            key = this.getSQLOperationKey((String)null, name);
            datastoreDependent = false;
            if (!this.operationNamesSupported.contains(key)) {
               throw new UnsupportedOperationException("Operation " + name + " datastore=" + datastoreId + " not supported");
            }
         }

         PluginManager pluginMgr = this.storeMgr.getNucleusContext().getPluginManager();
         String[] attrNames = datastoreDependent ? new String[]{"name", "datastore"} : new String[]{"name"};
         String[] attrValues = datastoreDependent ? new String[]{name, datastoreId} : new String[]{name};

         try {
            operation = (SQLOperation)pluginMgr.createExecutableExtension("org.datanucleus.store.rdbms.sql_operation", attrNames, attrValues, "evaluator", (Class[])null, (Object[])null);
            operation.setExpressionFactory(this);
            synchronized(operation) {
               this.operationByOperationName.put(key, operation);
               return operation.getExpression(expr, expr2);
            }
         } catch (Exception e) {
            throw new NucleusUserException(Localiser.msg("060011", new Object[]{"operation=" + name}), e);
         }
      }
   }

   private MethodKey getSQLMethodKey(String datastoreName, String className, String methodName) {
      MethodKey key = new MethodKey();
      key.clsName = className != null ? className.trim() : "";
      key.methodName = methodName;
      key.datastoreName = datastoreName != null ? datastoreName.trim() : "ALL";
      return key;
   }

   private String getSQLOperationKey(String datastoreName, String name) {
      return (datastoreName != null ? datastoreName.trim() : "ALL") + "#" + name;
   }

   public JavaTypeMapping getMappingForType(Class cls, boolean useCached) {
      JavaTypeMapping mapping = null;
      if (useCached) {
         mapping = (JavaTypeMapping)this.mappingByClass.get(cls);
         if (mapping != null) {
            return mapping;
         }
      }

      mapping = this.storeMgr.getMappingManager().getMappingWithDatastoreMapping(cls, false, false, this.clr);
      this.mappingByClass.put(cls, mapping);
      return mapping;
   }

   private class MethodKey {
      String clsName;
      String methodName;
      String datastoreName;

      private MethodKey() {
      }

      public int hashCode() {
         return (this.clsName + this.methodName + this.datastoreName).hashCode();
      }

      public boolean equals(Object other) {
         if (other != null && other instanceof MethodKey) {
            MethodKey otherKey = (MethodKey)other;
            return otherKey.clsName.equals(this.clsName) && otherKey.methodName.equals(this.methodName) && otherKey.datastoreName.equals(this.datastoreName);
         } else {
            return false;
         }
      }
   }
}
