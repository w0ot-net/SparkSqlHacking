package org.datanucleus.store.rdbms.sql.expression;

import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.InheritanceStrategy;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.BigDecimalMapping;
import org.datanucleus.store.rdbms.mapping.java.BigIntegerMapping;
import org.datanucleus.store.rdbms.mapping.java.BooleanMapping;
import org.datanucleus.store.rdbms.mapping.java.ByteMapping;
import org.datanucleus.store.rdbms.mapping.java.CharacterMapping;
import org.datanucleus.store.rdbms.mapping.java.DateMapping;
import org.datanucleus.store.rdbms.mapping.java.DiscriminatorMapping;
import org.datanucleus.store.rdbms.mapping.java.DoubleMapping;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedMapping;
import org.datanucleus.store.rdbms.mapping.java.FloatMapping;
import org.datanucleus.store.rdbms.mapping.java.IntegerMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.LongMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableIdMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.mapping.java.ShortMapping;
import org.datanucleus.store.rdbms.mapping.java.SqlDateMapping;
import org.datanucleus.store.rdbms.mapping.java.SqlTimeMapping;
import org.datanucleus.store.rdbms.mapping.java.SqlTimestampMapping;
import org.datanucleus.store.rdbms.mapping.java.StringMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLStatementHelper;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class ObjectExpression extends SQLExpression {
   public ObjectExpression(SQLStatement stmt, SQLTable table, JavaTypeMapping mapping) {
      super(stmt, table, mapping);
   }

   public ObjectExpression(SQLStatement stmt, JavaTypeMapping mapping, String functionName, List args) {
      super(stmt, mapping, functionName, args, (List)null);
   }

   public ObjectExpression(SQLStatement stmt, JavaTypeMapping mapping, String functionName, List args, List types) {
      super(stmt, mapping, functionName, args, types);
   }

   public void useFirstColumnOnly() {
      if (this.mapping.getNumberOfDatastoreMappings() > 1) {
         this.subExprs = new SQLExpression.ColumnExpressionList();
         ColumnExpression colExpr = new ColumnExpression(this.stmt, this.table, this.mapping.getDatastoreMapping(0).getColumn());
         this.subExprs.addExpression(colExpr);
         this.st.clearStatement();
         this.st.append(this.subExprs.toString());
      }
   }

   public BooleanExpression eq(SQLExpression expr) {
      this.addSubexpressionsToRelatedExpression(expr);
      if (this.mapping instanceof PersistableIdMapping && expr instanceof StringLiteral) {
         String oidString = (String)((StringLiteral)expr).getValue();
         if (oidString != null) {
            AbstractClassMetaData cmd = this.stmt.getRDBMSManager().getMetaDataManager().getMetaDataForClass(this.mapping.getType(), this.stmt.getQueryGenerator().getClassLoaderResolver());
            if (cmd.getIdentityType() == IdentityType.DATASTORE) {
               try {
                  Object id = this.stmt.getRDBMSManager().getNucleusContext().getIdentityManager().getDatastoreId(oidString);
                  if (id == null) {
                  }
               } catch (IllegalArgumentException var7) {
                  NucleusLogger.QUERY.info("Attempted comparison of " + this + " and " + expr + " where the former is a datastore-identity and the latter is of incorrect form (" + oidString + ")");
                  SQLExpressionFactory exprFactory = this.stmt.getSQLExpressionFactory();
                  JavaTypeMapping m = exprFactory.getMappingForType(Boolean.TYPE, true);
                  return exprFactory.newLiteral(this.stmt, m, false).eq(exprFactory.newLiteral(this.stmt, m, true));
               }
            } else if (cmd.getIdentityType() == IdentityType.APPLICATION) {
            }
         }
      }

      if (this.mapping instanceof ReferenceMapping && expr.mapping instanceof PersistableMapping) {
         return this.processComparisonOfImplementationWithReference(this, expr, false);
      } else if (this.mapping instanceof PersistableMapping && expr.mapping instanceof ReferenceMapping) {
         return this.processComparisonOfImplementationWithReference(expr, this, false);
      } else {
         BooleanExpression bExpr = null;
         if (!this.isParameter() && !expr.isParameter()) {
            if (expr instanceof NullLiteral) {
               for(int i = 0; i < this.subExprs.size(); ++i) {
                  BooleanExpression subexpr = expr.eq(this.subExprs.getExpression(i));
                  bExpr = bExpr == null ? subexpr : bExpr.and(subexpr);
               }

               return bExpr;
            } else if (this.literalIsValidForSimpleComparison(expr)) {
               return this.subExprs.size() > 1 ? super.eq(expr) : new BooleanExpression(this, Expression.OP_EQ, expr);
            } else if (expr instanceof ObjectExpression) {
               return ExpressionUtils.getEqualityExpressionForObjectExpressions(this, (ObjectExpression)expr, true);
            } else {
               return this.subExprs == null ? new BooleanExpression(this, Expression.OP_EQ, expr) : super.eq(expr);
            }
         } else if (this.subExprs.size() > 1) {
            for(int i = 0; i < this.subExprs.size(); ++i) {
               BooleanExpression subexpr = this.subExprs.getExpression(i).eq(((ObjectExpression)expr).subExprs.getExpression(i));
               bExpr = bExpr == null ? subexpr : bExpr.and(subexpr);
            }

            return bExpr;
         } else {
            return new BooleanExpression(this, Expression.OP_EQ, expr);
         }
      }
   }

   protected BooleanExpression processComparisonOfImplementationWithReference(SQLExpression refExpr, SQLExpression implExpr, boolean negate) {
      ReferenceMapping refMapping = (ReferenceMapping)refExpr.mapping;
      JavaTypeMapping[] implMappings = refMapping.getJavaTypeMapping();
      int subExprStart = 0;
      int subExprEnd = 0;

      for(int i = 0; i < implMappings.length; ++i) {
         if (implMappings[i].getType().equals(implExpr.mapping.getType())) {
            subExprEnd = subExprStart + implMappings[i].getNumberOfDatastoreMappings();
            break;
         }

         subExprStart += implMappings[i].getNumberOfDatastoreMappings();
      }

      BooleanExpression bExpr = null;
      int implMappingNum = 0;

      for(int i = subExprStart; i < subExprEnd; ++i) {
         BooleanExpression subexpr = refExpr.subExprs.getExpression(i).eq(implExpr.subExprs.getExpression(implMappingNum++));
         bExpr = bExpr == null ? subexpr : bExpr.and(subexpr);
      }

      if (bExpr == null) {
         return ExpressionUtils.getEqualityExpressionForObjectExpressions((ObjectExpression)refExpr, (ObjectExpression)implExpr, true);
      } else {
         return negate ? new BooleanExpression(Expression.OP_NOT, bExpr.encloseInParentheses()) : bExpr;
      }
   }

   public BooleanExpression ne(SQLExpression expr) {
      this.addSubexpressionsToRelatedExpression(expr);
      if (this.mapping instanceof ReferenceMapping && expr.mapping instanceof PersistableMapping) {
         return this.processComparisonOfImplementationWithReference(this, expr, true);
      } else if (this.mapping instanceof PersistableMapping && expr.mapping instanceof ReferenceMapping) {
         return this.processComparisonOfImplementationWithReference(expr, this, true);
      } else {
         BooleanExpression bExpr = null;
         if (!this.isParameter() && !expr.isParameter()) {
            if (expr instanceof NullLiteral) {
               for(int i = 0; i < this.subExprs.size(); ++i) {
                  BooleanExpression subexpr = expr.eq(this.subExprs.getExpression(i));
                  bExpr = bExpr == null ? subexpr : bExpr.and(subexpr);
               }

               return new BooleanExpression(Expression.OP_NOT, bExpr != null ? bExpr.encloseInParentheses() : null);
            } else if (this.literalIsValidForSimpleComparison(expr)) {
               return this.subExprs.size() > 1 ? super.ne(expr) : new BooleanExpression(this, Expression.OP_NOTEQ, expr);
            } else if (expr instanceof ObjectExpression) {
               return ExpressionUtils.getEqualityExpressionForObjectExpressions(this, (ObjectExpression)expr, false);
            } else {
               return this.subExprs == null ? new BooleanExpression(this, Expression.OP_NOTEQ, expr) : super.ne(expr);
            }
         } else if (this.subExprs.size() > 1) {
            for(int i = 0; i < this.subExprs.size(); ++i) {
               BooleanExpression subexpr = this.subExprs.getExpression(i).eq(((ObjectExpression)expr).subExprs.getExpression(i));
               bExpr = bExpr == null ? subexpr : bExpr.and(subexpr);
            }

            return new BooleanExpression(Expression.OP_NOT, bExpr != null ? bExpr.encloseInParentheses() : null);
         } else {
            return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
         }
      }
   }

   protected void addSubexpressionsToRelatedExpression(SQLExpression expr) {
      if (expr.subExprs == null && this.subExprs != null) {
         expr.subExprs = new SQLExpression.ColumnExpressionList();

         for(int i = 0; i < this.subExprs.size(); ++i) {
            expr.subExprs.addExpression(new ColumnExpression(this.stmt, expr.parameterName, expr.mapping, (Object)null, i));
         }
      }

   }

   private boolean literalIsValidForSimpleComparison(SQLExpression expr) {
      return expr instanceof BooleanLiteral && this.mapping instanceof BooleanMapping || expr instanceof ByteLiteral && this.mapping instanceof ByteMapping || expr instanceof CharacterLiteral && this.mapping instanceof CharacterMapping || expr instanceof FloatingPointLiteral && (this.mapping instanceof FloatMapping || this.mapping instanceof DoubleMapping || this.mapping instanceof BigDecimalMapping) || expr instanceof IntegerLiteral && (this.mapping instanceof IntegerMapping || this.mapping instanceof LongMapping || this.mapping instanceof BigIntegerMapping) || this.mapping instanceof ShortMapping || expr instanceof TemporalLiteral && (this.mapping instanceof DateMapping || this.mapping instanceof SqlDateMapping || this.mapping instanceof SqlTimeMapping || this.mapping instanceof SqlTimestampMapping) || expr instanceof StringLiteral && (this.mapping instanceof StringMapping || this.mapping instanceof CharacterMapping);
   }

   public BooleanExpression in(SQLExpression expr, boolean not) {
      return new BooleanExpression(this, not ? Expression.OP_NOTIN : Expression.OP_IN, expr);
   }

   public BooleanExpression lt(SQLExpression expr) {
      return this.subExprs == null ? new BooleanExpression(this, Expression.OP_LT, expr) : super.lt(expr);
   }

   public BooleanExpression le(SQLExpression expr) {
      return this.subExprs == null ? new BooleanExpression(this, Expression.OP_LTEQ, expr) : super.le(expr);
   }

   public BooleanExpression gt(SQLExpression expr) {
      return this.subExprs == null ? new BooleanExpression(this, Expression.OP_GT, expr) : super.gt(expr);
   }

   public BooleanExpression ge(SQLExpression expr) {
      return this.subExprs == null ? new BooleanExpression(this, Expression.OP_GTEQ, expr) : super.ge(expr);
   }

   public SQLExpression cast(SQLExpression expr) {
      RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
      ClassLoaderResolver clr = this.stmt.getClassLoaderResolver();
      String castClassName = (String)((StringLiteral)expr).getValue();
      Class type = null;

      try {
         type = this.stmt.getQueryGenerator().resolveClass(castClassName);
      } catch (ClassNotResolvedException var14) {
         type = null;
      }

      if (type == null) {
         throw new NucleusUserException(Localiser.msg("037017", new Object[]{castClassName}));
      } else {
         SQLExpressionFactory exprFactory = this.stmt.getSQLExpressionFactory();
         Class memberType = clr.classForName(this.mapping.getType());
         if (!memberType.isAssignableFrom(type) && !type.isAssignableFrom(memberType)) {
            JavaTypeMapping m = exprFactory.getMappingForType(Boolean.TYPE, true);
            return exprFactory.newLiteral(this.stmt, m, false).eq(exprFactory.newLiteral(this.stmt, m, true));
         } else if (memberType == type) {
            return this;
         } else if (this.mapping instanceof EmbeddedMapping) {
            JavaTypeMapping m = exprFactory.getMappingForType(Boolean.TYPE, true);
            return exprFactory.newLiteral(this.stmt, m, false).eq(exprFactory.newLiteral(this.stmt, m, true));
         } else if (this.mapping instanceof ReferenceMapping) {
            ReferenceMapping refMapping = (ReferenceMapping)this.mapping;
            if (refMapping.getMappingStrategy() != 0) {
               throw new NucleusUserException("Impossible to do cast of interface to " + type.getName() + " since interface is persisted as embedded String. Use per-implementation mapping to allow this query");
            } else {
               JavaTypeMapping[] implMappings = refMapping.getJavaTypeMapping();

               for(int i = 0; i < implMappings.length; ++i) {
                  Class implType = clr.classForName(implMappings[i].getType());
                  if (type.isAssignableFrom(implType)) {
                     DatastoreClass castTable = storeMgr.getDatastoreClass(type.getName(), clr);
                     SQLTable castSqlTbl = this.stmt.leftOuterJoin(this.table, implMappings[i], refMapping, castTable, (String)null, castTable.getIdMapping(), (JavaTypeMapping)null, (Object[])null, (String)null);
                     return exprFactory.newExpression(this.stmt, castSqlTbl, castTable.getIdMapping());
                  }
               }

               NucleusLogger.QUERY.warn("Unable to process cast of interface field to " + type.getName() + " since it has no implementations that match that type");
               JavaTypeMapping m = exprFactory.getMappingForType(Boolean.TYPE, true);
               return exprFactory.newLiteral(this.stmt, m, false).eq(exprFactory.newLiteral(this.stmt, m, true));
            }
         } else if (this.mapping instanceof PersistableMapping) {
            DatastoreClass castTable = storeMgr.getDatastoreClass(type.getName(), clr);
            SQLTable castSqlTbl = this.stmt.getTable(castTable, this.table.getGroupName());
            if (castSqlTbl == null) {
               castSqlTbl = this.stmt.leftOuterJoin(this.table, this.table.getTable().getIdMapping(), castTable, (String)null, castTable.getIdMapping(), (Object[])null, this.table.getGroupName());
            }

            return exprFactory.newExpression(this.stmt, castSqlTbl, castTable.getIdMapping());
         } else {
            throw new NucleusUserException("Dont currently support ObjectExpression.cast(" + type + ")");
         }
      }
   }

   public BooleanExpression is(SQLExpression expr, boolean not) {
      RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
      ClassLoaderResolver clr = this.stmt.getClassLoaderResolver();
      String instanceofClassName = null;
      SQLExpression classExpr = expr;
      if (expr instanceof TypeConverterLiteral) {
         classExpr = ((TypeConverterLiteral)expr).getDelegate();
      }

      if (!(classExpr instanceof StringLiteral)) {
         throw new NucleusUserException("Do not currently support `instanceof` with class expression of type " + classExpr);
      } else {
         instanceofClassName = (String)((StringLiteral)classExpr).getValue();
         Object var7 = null;

         try {
            var24 = this.stmt.getQueryGenerator().resolveClass(instanceofClassName);
         } catch (ClassNotResolvedException var22) {
            var24 = null;
         }

         if (var24 == null) {
            throw new NucleusUserException(Localiser.msg("037016", new Object[]{instanceofClassName}));
         } else {
            SQLExpressionFactory exprFactory = this.stmt.getSQLExpressionFactory();
            Class memberType = clr.classForName(this.mapping.getType());
            if (!memberType.isAssignableFrom(var24) && !var24.isAssignableFrom(memberType)) {
               JavaTypeMapping m = exprFactory.getMappingForType(Boolean.TYPE, true);
               return exprFactory.newLiteral(this.stmt, m, true).eq(exprFactory.newLiteral(this.stmt, m, not));
            } else if (memberType == var24) {
               JavaTypeMapping m = exprFactory.getMappingForType(Boolean.TYPE, true);
               return exprFactory.newLiteral(this.stmt, m, true).eq(exprFactory.newLiteral(this.stmt, m, !not));
            } else if (this.mapping instanceof EmbeddedMapping) {
               AbstractClassMetaData fieldCmd = storeMgr.getMetaDataManager().getMetaDataForClass(this.mapping.getType(), clr);
               if (fieldCmd.hasDiscriminatorStrategy()) {
                  JavaTypeMapping discMapping = ((EmbeddedMapping)this.mapping).getDiscriminatorMapping();
                  DiscriminatorMetaData dismd = fieldCmd.getDiscriminatorMetaDataRoot();
                  AbstractClassMetaData typeCmd = storeMgr.getMetaDataManager().getMetaDataForClass(var24, clr);
                  SQLExpression discExpr = this.stmt.getSQLExpressionFactory().newExpression(this.stmt, this.table, discMapping);
                  SQLExpression discVal = null;
                  if (dismd.getStrategy() == DiscriminatorStrategy.CLASS_NAME) {
                     discVal = this.stmt.getSQLExpressionFactory().newLiteral(this.stmt, discMapping, typeCmd.getFullClassName());
                  } else {
                     discVal = this.stmt.getSQLExpressionFactory().newLiteral(this.stmt, discMapping, typeCmd.getDiscriminatorMetaData().getValue());
                  }

                  BooleanExpression typeExpr = not ? discExpr.ne(discVal) : discExpr.eq(discVal);

                  for(String subclassName : storeMgr.getSubClassesForClass(var24.getName(), true, clr)) {
                     AbstractClassMetaData subtypeCmd = storeMgr.getMetaDataManager().getMetaDataForClass(subclassName, clr);
                     if (dismd.getStrategy() == DiscriminatorStrategy.CLASS_NAME) {
                        discVal = this.stmt.getSQLExpressionFactory().newLiteral(this.stmt, discMapping, subtypeCmd.getFullClassName());
                     } else {
                        discVal = this.stmt.getSQLExpressionFactory().newLiteral(this.stmt, discMapping, subtypeCmd.getDiscriminatorMetaData().getValue());
                     }

                     BooleanExpression subtypeExpr = not ? discExpr.ne(discVal) : discExpr.eq(discVal);
                     if (not) {
                        typeExpr = typeExpr.and(subtypeExpr);
                     } else {
                        typeExpr = typeExpr.ior(subtypeExpr);
                     }
                  }

                  return typeExpr;
               } else {
                  JavaTypeMapping m = exprFactory.getMappingForType(Boolean.TYPE, true);
                  return exprFactory.newLiteral(this.stmt, m, true).eq(exprFactory.newLiteral(this.stmt, m, not));
               }
            } else if (!(this.mapping instanceof PersistableMapping) && !(this.mapping instanceof ReferenceMapping)) {
               throw new NucleusException("Dont currently support " + this + " instanceof " + var24.getName());
            } else {
               AbstractClassMetaData memberCmd = storeMgr.getMetaDataManager().getMetaDataForClass(this.mapping.getType(), clr);
               DatastoreClass memberTable = null;
               if (memberCmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUBCLASS_TABLE) {
                  AbstractClassMetaData[] cmds = storeMgr.getClassesManagingTableForClass(memberCmd, clr);
                  if (cmds == null) {
                     throw new NucleusUserException(Localiser.msg("037005", new Object[]{this.mapping.getMemberMetaData().getFullFieldName()}));
                  }

                  if (cmds.length > 1) {
                     NucleusLogger.QUERY.warn(Localiser.msg("037006", new Object[]{this.mapping.getMemberMetaData().getFullFieldName(), cmds[0].getFullClassName()}));
                  }

                  memberTable = storeMgr.getDatastoreClass(cmds[0].getFullClassName(), clr);
               } else {
                  memberTable = storeMgr.getDatastoreClass(this.mapping.getType(), clr);
               }

               DiscriminatorMetaData dismd = memberTable.getDiscriminatorMetaData();
               DiscriminatorMapping discMapping = (DiscriminatorMapping)memberTable.getDiscriminatorMapping(false);
               if (discMapping == null) {
                  DatastoreClass table = null;
                  if (memberCmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUBCLASS_TABLE) {
                     AbstractClassMetaData[] cmds = storeMgr.getClassesManagingTableForClass(memberCmd, clr);
                     if (cmds == null) {
                        throw new NucleusUserException(Localiser.msg("037005", new Object[]{this.mapping.getMemberMetaData().getFullFieldName()}));
                     }

                     if (cmds.length > 1) {
                        NucleusLogger.QUERY.warn(Localiser.msg("037006", new Object[]{this.mapping.getMemberMetaData().getFullFieldName(), cmds[0].getFullClassName()}));
                     }

                     table = storeMgr.getDatastoreClass(cmds[0].getFullClassName(), clr);
                  } else {
                     table = storeMgr.getDatastoreClass(this.mapping.getType(), clr);
                  }

                  if (table.managesClass(var24.getName())) {
                     JavaTypeMapping m = exprFactory.getMappingForType(Boolean.TYPE, true);
                     return exprFactory.newLiteral(this.stmt, m, true).eq(exprFactory.newLiteral(this.stmt, m, !not));
                  } else if (table == this.stmt.getPrimaryTable().getTable()) {
                     JavaTypeMapping m = exprFactory.getMappingForType(Boolean.TYPE, true);
                     if (this.stmt.getNumberOfUnions() > 0) {
                        Class mainCandidateCls = clr.classForName(this.stmt.getCandidateClassName());
                        if (var24.isAssignableFrom(mainCandidateCls) == not) {
                           SQLExpression unionClauseExpr = exprFactory.newLiteral(this.stmt, m, true).eq(exprFactory.newLiteral(this.stmt, m, false));
                           this.stmt.whereAnd((BooleanExpression)unionClauseExpr, false);
                        }

                        for(SQLStatement unionStmt : this.stmt.getUnions()) {
                           Class unionCandidateCls = clr.classForName(unionStmt.getCandidateClassName());
                           if (var24.isAssignableFrom(unionCandidateCls) == not) {
                              SQLExpression unionClauseExpr = exprFactory.newLiteral(unionStmt, m, true).eq(exprFactory.newLiteral(unionStmt, m, false));
                              unionStmt.whereAnd((BooleanExpression)unionClauseExpr, false);
                           }
                        }

                        SQLExpression returnExpr = exprFactory.newLiteral(this.stmt, m, true).eq(exprFactory.newLiteral(this.stmt, m, true));
                        return (BooleanExpression)returnExpr;
                     } else {
                        DatastoreClass instanceofTable = storeMgr.getDatastoreClass(var24.getName(), clr);
                        this.stmt.innerJoin(this.table, this.table.getTable().getIdMapping(), instanceofTable, (String)null, instanceofTable.getIdMapping(), (Object[])null, this.table.getGroupName());
                        return exprFactory.newLiteral(this.stmt, m, true).eq(exprFactory.newLiteral(this.stmt, m, !not));
                     }
                  } else {
                     DatastoreClass instanceofTable = storeMgr.getDatastoreClass(var24.getName(), clr);
                     if (this.stmt.getNumberOfUnions() > 0) {
                        NucleusLogger.QUERY.debug("InstanceOf for " + table + " but no discriminator so adding inner join to " + instanceofTable + " : in some cases with UNIONs this may fail");
                     }

                     this.stmt.innerJoin(this.table, this.table.getTable().getIdMapping(), instanceofTable, (String)null, instanceofTable.getIdMapping(), (Object[])null, this.table.getGroupName());
                     JavaTypeMapping m = exprFactory.getMappingForType(Boolean.TYPE, true);
                     return exprFactory.newLiteral(this.stmt, m, true).eq(exprFactory.newLiteral(this.stmt, m, !not));
                  }
               } else {
                  SQLTable targetSqlTbl = null;
                  if (this.mapping.getTable() != memberTable) {
                     targetSqlTbl = this.stmt.getTable(memberTable, (String)null);
                     if (targetSqlTbl == null) {
                        targetSqlTbl = this.stmt.innerJoin(this.getSQLTable(), this.mapping, memberTable, (String)null, memberTable.getIdMapping(), (Object[])null, (String)null);
                     }
                  } else {
                     targetSqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(this.stmt, this.getSQLTable(), discMapping);
                  }

                  SQLTable discSqlTbl = targetSqlTbl;
                  BooleanExpression discExpr = null;
                  if (!Modifier.isAbstract(var24.getModifiers())) {
                     discExpr = SQLStatementHelper.getExpressionForDiscriminatorForClass(this.stmt, var24.getName(), dismd, discMapping, targetSqlTbl, clr);
                  }

                  Iterator subclassIter = storeMgr.getSubClassesForClass(var24.getName(), true, clr).iterator();
                  boolean multiplePossibles = false;

                  while(subclassIter.hasNext()) {
                     String subclassName = (String)subclassIter.next();
                     Class subclass = clr.classForName(subclassName);
                     if (!Modifier.isAbstract(subclass.getModifiers())) {
                        BooleanExpression discExprSub = SQLStatementHelper.getExpressionForDiscriminatorForClass(this.stmt, subclassName, dismd, discMapping, discSqlTbl, clr);
                        if (discExpr != null) {
                           multiplePossibles = true;
                           discExpr = discExpr.ior(discExprSub);
                        } else {
                           discExpr = discExprSub;
                        }
                     }
                  }

                  if (multiplePossibles && discExpr != null) {
                     discExpr.encloseInParentheses();
                  }

                  return not && discExpr != null ? discExpr.not() : discExpr;
               }
            }
         }
      }
   }

   public SQLExpression invoke(String methodName, List args) {
      return this.stmt.getRDBMSManager().getSQLExpressionFactory().invokeMethod(this.stmt, Object.class.getName(), methodName, this, args);
   }
}
