package org.datanucleus.store.rdbms.sql.expression;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.fieldmanager.FieldManager;
import org.datanucleus.store.fieldmanager.SingleValueFieldManager;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.mapping.datastore.DatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class ExpressionUtils {
   public static NumericExpression getNumericExpression(SQLExpression expr) {
      RDBMSStoreManager storeMgr = expr.getSQLStatement().getRDBMSManager();
      SQLExpressionFactory factory = storeMgr.getSQLExpressionFactory();
      DatastoreAdapter dba = expr.getSQLStatement().getDatastoreAdapter();
      if (expr instanceof CharacterLiteral) {
         char c = (Character)((CharacterLiteral)expr).getValue();
         BigInteger value = new BigInteger("" + c);
         return (NumericExpression)factory.newLiteral(expr.getSQLStatement(), storeMgr.getMappingManager().getMapping(value.getClass()), value);
      } else if (expr instanceof SQLLiteral) {
         BigInteger value = new BigInteger((String)((SQLLiteral)expr).getValue());
         return (NumericExpression)factory.newLiteral(expr.getSQLStatement(), storeMgr.getMappingManager().getMapping(value.getClass()), value);
      } else {
         ArrayList args = new ArrayList();
         args.add(expr);
         return new NumericExpression(expr.getSQLStatement(), expr.getJavaTypeMapping(), dba.getNumericConversionFunction(), args);
      }
   }

   public static SQLExpression getLiteralForOne(SQLStatement stmt) {
      RDBMSStoreManager storeMgr = stmt.getRDBMSManager();
      JavaTypeMapping mapping = storeMgr.getMappingManager().getMapping(BigInteger.class);
      return storeMgr.getSQLExpressionFactory().newLiteral(stmt, mapping, BigInteger.ONE);
   }

   public static SQLExpression getEscapedPatternExpression(SQLExpression patternExpr) {
      if (patternExpr instanceof StringLiteral) {
         String value = (String)((StringLiteral)patternExpr).getValue();
         SQLExpressionFactory exprFactory = patternExpr.getSQLStatement().getSQLExpressionFactory();
         JavaTypeMapping m = exprFactory.getMappingForType(String.class, false);
         if (value != null) {
            value = value.replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_");
         }

         return exprFactory.newLiteral(patternExpr.getSQLStatement(), m, value);
      } else {
         return patternExpr;
      }
   }

   public static int populatePrimaryKeyMappingsValuesForPCMapping(JavaTypeMapping[] pkMappings, Object[] pkFieldValues, int position, PersistableMapping pcMapping, AbstractClassMetaData cmd, AbstractMemberMetaData mmd, Object fieldValue, RDBMSStoreManager storeMgr, ClassLoaderResolver clr) {
      ExecutionContext ec = storeMgr.getApiAdapter().getExecutionContext(fieldValue);
      JavaTypeMapping[] subMappings = pcMapping.getJavaTypeMapping();
      if (subMappings.length == 0) {
         DatastoreClass table = storeMgr.getDatastoreClass(cmd.getFullClassName(), clr);
         JavaTypeMapping ownerMapping = table.getMemberMapping(mmd);
         EmbeddedMapping embMapping = (EmbeddedMapping)ownerMapping;

         for(int k = 0; k < embMapping.getNumberOfJavaTypeMappings(); ++k) {
            JavaTypeMapping subMapping = embMapping.getJavaTypeMapping(k);
            pkMappings[position] = subMapping;
            pkFieldValues[position] = getValueForMemberOfObject(ec, subMapping.getMemberMetaData(), fieldValue);
            ++position;
         }
      } else {
         AbstractClassMetaData pcCmd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(pcMapping.getType(), clr);
         int[] pcPkPositions = pcCmd.getPKMemberPositions();

         for(int k = 0; k < subMappings.length; ++k) {
            AbstractMemberMetaData pcMmd = pcCmd.getMetaDataForManagedMemberAtAbsolutePosition(pcPkPositions[k]);
            if (subMappings[k] instanceof PersistableMapping) {
               Object val = getValueForMemberOfObject(ec, pcMmd, fieldValue);
               position = populatePrimaryKeyMappingsValuesForPCMapping(pkMappings, pkFieldValues, position, (PersistableMapping)subMappings[k], pcCmd, pcMmd, val, storeMgr, clr);
            } else {
               Object val = getValueForMemberOfObject(ec, pcMmd, fieldValue);
               pkMappings[position] = subMappings[k];
               pkFieldValues[position] = val;
               ++position;
            }
         }
      }

      return position;
   }

   public static Object getValueForMemberOfObject(ExecutionContext ec, AbstractMemberMetaData mmd, Object object) {
      if (ec == null) {
         return ClassUtils.getValueOfFieldByReflection(object, mmd.getName());
      } else {
         ObjectProvider sm = ec.findObjectProvider(object);
         if (!mmd.isPrimaryKey()) {
            sm.isLoaded(mmd.getAbsoluteFieldNumber());
         }

         FieldManager fm = new SingleValueFieldManager();
         sm.provideFields(new int[]{mmd.getAbsoluteFieldNumber()}, fm);
         return fm.fetchObjectField(mmd.getAbsoluteFieldNumber());
      }
   }

   public static BooleanExpression getAppIdEqualityExpression(Object id, SQLExpression expr, RDBMSStoreManager storeMgr, ClassLoaderResolver clr, AbstractClassMetaData acmd, Integer index, BooleanExpression bExpr) {
      if (index == null) {
         index = 0;
      }

      String[] pkFieldNames = acmd.getPrimaryKeyMemberNames();

      for(int i = 0; i < pkFieldNames.length; ++i) {
         Object value = ClassUtils.getValueOfFieldByReflection(id, pkFieldNames[i]);
         String pcClassName = storeMgr.getClassNameForObjectID(value, clr, (ExecutionContext)null);
         if (pcClassName != null) {
            AbstractClassMetaData scmd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(pcClassName, clr);
            if (bExpr == null) {
               bExpr = getAppIdEqualityExpression(value, expr, storeMgr, clr, scmd, index, (BooleanExpression)null);
            } else {
               bExpr = bExpr.and(getAppIdEqualityExpression(value, expr, storeMgr, clr, scmd, index, bExpr));
            }
         } else {
            SQLExpression source = expr.subExprs.getExpression(index);
            JavaTypeMapping mapping = storeMgr.getMappingManager().getMappingWithDatastoreMapping(value.getClass(), false, false, clr);
            SQLExpression target = expr.getSQLStatement().getSQLExpressionFactory().newLiteral(expr.getSQLStatement(), mapping, value);
            if (bExpr == null) {
               bExpr = source.eq(target);
            } else {
               bExpr = bExpr.and(source.eq(target));
            }

            if (target.subExprs.size() == 0) {
               index = index + 1;
            } else {
               index = index + target.subExprs.size();
            }
         }
      }

      return bExpr;
   }

   public static BooleanExpression getEqualityExpressionForObjectExpressions(ObjectExpression expr1, ObjectExpression expr2, boolean equals) {
      SQLStatement stmt = expr1.stmt;
      RDBMSStoreManager storeMgr = stmt.getRDBMSManager();
      SQLExpressionFactory exprFactory = storeMgr.getSQLExpressionFactory();
      ClassLoaderResolver clr = stmt.getClassLoaderResolver();
      ApiAdapter api = storeMgr.getApiAdapter();
      if (expr1 instanceof ObjectLiteral && expr2 instanceof ObjectLiteral) {
         ObjectLiteral lit1 = (ObjectLiteral)expr1;
         ObjectLiteral lit2 = (ObjectLiteral)expr2;
         return new BooleanLiteral(stmt, expr1.mapping, equals ? lit1.getValue().equals(lit2.getValue()) : !lit1.getValue().equals(lit2.getValue()));
      } else if (!(expr1 instanceof ObjectLiteral) && !(expr2 instanceof ObjectLiteral)) {
         BooleanExpression resultExpr = null;

         for(int i = 0; i < expr1.subExprs.size(); ++i) {
            SQLExpression sourceExpr = expr1.subExprs.getExpression(i);
            SQLExpression targetExpr = expr2.subExprs.getExpression(i);
            if (resultExpr == null) {
               resultExpr = sourceExpr.eq(targetExpr);
            } else {
               resultExpr = resultExpr.and(sourceExpr.eq(targetExpr));
            }
         }

         if (!equals) {
            resultExpr = new BooleanExpression(Expression.OP_NOT, resultExpr.encloseInParentheses());
         }

         return resultExpr;
      } else {
         BooleanExpression bExpr = null;
         boolean secondIsLiteral = expr2 instanceof ObjectLiteral;
         Object value = !secondIsLiteral ? ((ObjectLiteral)expr1).getValue() : ((ObjectLiteral)expr2).getValue();
         if (IdentityUtils.isDatastoreIdentity(value)) {
            Object valueKey = IdentityUtils.getTargetKeyForDatastoreIdentity(value);
            JavaTypeMapping m = storeMgr.getSQLExpressionFactory().getMappingForType(valueKey.getClass(), false);
            SQLExpression oidLit = exprFactory.newLiteral(stmt, m, valueKey);
            if (equals) {
               return secondIsLiteral ? expr1.subExprs.getExpression(0).eq(oidLit) : expr2.subExprs.getExpression(0).eq(oidLit);
            } else {
               return secondIsLiteral ? expr1.subExprs.getExpression(0).ne(oidLit) : expr2.subExprs.getExpression(0).ne(oidLit);
            }
         } else if (IdentityUtils.isSingleFieldIdentity(value)) {
            Object valueKey = IdentityUtils.getTargetKeyForSingleFieldIdentity(value);
            JavaTypeMapping m = storeMgr.getSQLExpressionFactory().getMappingForType(valueKey.getClass(), false);
            SQLExpression oidLit = exprFactory.newLiteral(stmt, m, valueKey);
            if (equals) {
               return secondIsLiteral ? expr1.subExprs.getExpression(0).eq(oidLit) : expr2.subExprs.getExpression(0).eq(oidLit);
            } else {
               return secondIsLiteral ? expr1.subExprs.getExpression(0).ne(oidLit) : expr2.subExprs.getExpression(0).ne(oidLit);
            }
         } else {
            AbstractClassMetaData cmd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(value.getClass(), clr);
            if (cmd != null) {
               if (cmd.getIdentityType() != IdentityType.APPLICATION) {
                  if (cmd.getIdentityType() == IdentityType.DATASTORE) {
                     SQLExpression source = secondIsLiteral ? expr1.subExprs.getExpression(0) : expr2.subExprs.getExpression(0);
                     JavaTypeMapping mapping = secondIsLiteral ? expr1.mapping : expr2.mapping;
                     Object objectId = api.getIdForObject(value);
                     if (objectId == null) {
                        NucleusLogger.QUERY.warn(Localiser.msg("037003", new Object[]{value}));
                        return exprFactory.newLiteral(stmt, mapping, false).eq(exprFactory.newLiteral(stmt, mapping, true));
                     } else {
                        Object objectIdKey = IdentityUtils.getTargetKeyForDatastoreIdentity(objectId);
                        JavaTypeMapping m = storeMgr.getSQLExpressionFactory().getMappingForType(objectIdKey.getClass(), false);
                        SQLExpression oidExpr = exprFactory.newLiteral(stmt, m, objectIdKey);
                        return equals ? source.eq(oidExpr) : source.ne(oidExpr);
                     }
                  } else {
                     return null;
                  }
               } else {
                  if (api.getIdForObject(value) != null) {
                     ObjectExpression expr = secondIsLiteral ? expr1 : expr2;
                     JavaTypeMapping[] pkMappingsApp = new JavaTypeMapping[expr.subExprs.size()];
                     Object[] pkFieldValues = new Object[expr.subExprs.size()];
                     int position = 0;
                     ExecutionContext ec = api.getExecutionContext(value);
                     JavaTypeMapping thisMapping = expr.mapping;
                     if (expr.mapping instanceof ReferenceMapping) {
                        thisMapping = null;
                        ReferenceMapping refMapping = (ReferenceMapping)expr.mapping;
                        JavaTypeMapping[] implMappings = refMapping.getJavaTypeMapping();

                        for(int i = 0; i < implMappings.length; ++i) {
                           Class implType = clr.classForName(implMappings[i].getType());
                           if (implType.isAssignableFrom(value.getClass())) {
                              thisMapping = implMappings[i];
                              break;
                           }
                        }
                     }

                     if (thisMapping == null) {
                        return exprFactory.newLiteral(stmt, expr1.mapping, false).eq(exprFactory.newLiteral(stmt, expr1.mapping, true));
                     }

                     for(int i = 0; i < cmd.getNoOfPrimaryKeyMembers(); ++i) {
                        AbstractMemberMetaData mmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(cmd.getPKMemberPositions()[i]);
                        Object fieldValue = getValueForMemberOfObject(ec, mmd, value);
                        JavaTypeMapping mapping = ((PersistableMapping)thisMapping).getJavaTypeMapping()[i];
                        if (mapping instanceof PersistableMapping) {
                           position = populatePrimaryKeyMappingsValuesForPCMapping(pkMappingsApp, pkFieldValues, position, (PersistableMapping)mapping, cmd, mmd, fieldValue, storeMgr, clr);
                        } else {
                           pkMappingsApp[position] = mapping;
                           pkFieldValues[position] = fieldValue;
                           ++position;
                        }
                     }

                     for(int i = 0; i < expr.subExprs.size(); ++i) {
                        SQLExpression source = expr.subExprs.getExpression(i);
                        SQLExpression target = exprFactory.newLiteral(stmt, pkMappingsApp[i], pkFieldValues[i]);
                        BooleanExpression subExpr = secondIsLiteral ? source.eq(target) : target.eq(source);
                        if (bExpr == null) {
                           bExpr = subExpr;
                        } else {
                           bExpr = bExpr.and(subExpr);
                        }
                     }
                  } else if (secondIsLiteral) {
                     for(int i = 0; i < expr1.subExprs.size(); ++i) {
                        NucleusLogger.QUERY.warn(Localiser.msg("037003", new Object[]{value}));
                        bExpr = exprFactory.newLiteral(stmt, expr1.mapping, false).eq(exprFactory.newLiteral(stmt, expr1.mapping, true));
                     }
                  } else {
                     for(int i = 0; i < expr2.subExprs.size(); ++i) {
                        NucleusLogger.QUERY.warn(Localiser.msg("037003", new Object[]{value}));
                        bExpr = exprFactory.newLiteral(stmt, expr2.mapping, false).eq(exprFactory.newLiteral(stmt, expr2.mapping, true));
                     }
                  }

                  return bExpr;
               }
            } else {
               String pcClassName = storeMgr.getClassNameForObjectID(value, clr, (ExecutionContext)null);
               if (pcClassName != null) {
                  cmd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(pcClassName, clr);
                  return secondIsLiteral ? getAppIdEqualityExpression(value, expr1, storeMgr, clr, cmd, (Integer)null, (BooleanExpression)null) : getAppIdEqualityExpression(value, expr2, storeMgr, clr, cmd, (Integer)null, (BooleanExpression)null);
               } else {
                  return exprFactory.newLiteral(stmt, expr1.mapping, false).eq(exprFactory.newLiteral(stmt, expr1.mapping, true));
               }
            }
         }
      }
   }

   public static void checkAndCorrectExpressionMappingsForBooleanComparison(SQLExpression expr1, SQLExpression expr2) {
      if (expr1.isParameter() && expr2.isParameter()) {
         if (expr1 instanceof SQLLiteral && ((SQLLiteral)expr2).getValue() != null) {
            expr1.getSQLStatement().getQueryGenerator().useParameterExpressionAsLiteral((SQLLiteral)expr2);
         } else if (expr2 instanceof SQLLiteral && ((SQLLiteral)expr2).getValue() != null) {
            expr1.getSQLStatement().getQueryGenerator().useParameterExpressionAsLiteral((SQLLiteral)expr2);
         }
      }

      if (expr1 instanceof SQLLiteral) {
         checkAndCorrectLiteralForConsistentMappingsForBooleanComparison((SQLLiteral)expr1, expr2);
      } else if (expr2 instanceof SQLLiteral) {
         checkAndCorrectLiteralForConsistentMappingsForBooleanComparison((SQLLiteral)expr2, expr1);
      }

   }

   protected static void checkAndCorrectLiteralForConsistentMappingsForBooleanComparison(SQLLiteral lit, SQLExpression expr) {
      JavaTypeMapping litMapping = ((SQLExpression)lit).getJavaTypeMapping();
      JavaTypeMapping exprMapping = expr.getJavaTypeMapping();
      if (exprMapping != null && exprMapping.getNumberOfDatastoreMappings() != 0) {
         if (!(litMapping instanceof PersistableMapping) || !(exprMapping instanceof ReferenceMapping)) {
            boolean needsUpdating = false;
            if (litMapping.getNumberOfDatastoreMappings() != exprMapping.getNumberOfDatastoreMappings()) {
               needsUpdating = true;
            } else {
               for(int i = 0; i < litMapping.getNumberOfDatastoreMappings(); ++i) {
                  DatastoreMapping colMapping = litMapping.getDatastoreMapping(i);
                  if (colMapping == null || colMapping.getClass() != exprMapping.getDatastoreMapping(i).getClass()) {
                     needsUpdating = true;
                     break;
                  }
               }
            }

            if (needsUpdating) {
               Class litMappingCls = litMapping.getJavaType();
               Class mappingCls = exprMapping.getJavaType();
               if ((litMappingCls == Double.class || litMappingCls == Float.class || litMappingCls == BigDecimal.class) && (mappingCls == Integer.class || mappingCls == Long.class || mappingCls == Short.class || mappingCls == BigInteger.class || mappingCls == Byte.class)) {
                  if (litMappingCls == BigDecimal.class) {
                     expr.getSQLStatement().getQueryGenerator().useParameterExpressionAsLiteral(lit);
                  }

                  needsUpdating = false;
               }

               if (litMappingCls == Byte.class && mappingCls != Byte.class) {
                  needsUpdating = false;
               }
            }

            if (needsUpdating) {
               NucleusLogger.QUERY.debug("Updating mapping of " + lit + " to be " + expr.getJavaTypeMapping());
               ((SQLExpression)lit).setJavaTypeMapping(expr.getJavaTypeMapping());
            }

         }
      }
   }
}
