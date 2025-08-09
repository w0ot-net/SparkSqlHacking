package org.datanucleus.api.jdo.query;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.jdo.FetchPlan;
import javax.jdo.JDOException;
import javax.jdo.JDOQLTypedQuery;
import javax.jdo.JDOQLTypedSubquery;
import javax.jdo.JDOUnsupportedOptionException;
import javax.jdo.JDOUserException;
import javax.jdo.PersistenceManager;
import javax.jdo.query.BooleanExpression;
import javax.jdo.query.CharacterExpression;
import javax.jdo.query.CollectionExpression;
import javax.jdo.query.DateExpression;
import javax.jdo.query.DateTimeExpression;
import javax.jdo.query.Expression;
import javax.jdo.query.ListExpression;
import javax.jdo.query.MapExpression;
import javax.jdo.query.NumericExpression;
import javax.jdo.query.OrderExpression;
import javax.jdo.query.PersistableExpression;
import javax.jdo.query.StringExpression;
import javax.jdo.query.TimeExpression;
import javax.jdo.spi.JDOPermission;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.api.jdo.JDOFetchPlan;
import org.datanucleus.api.jdo.JDOPersistenceManagerFactory;
import org.datanucleus.api.jdo.NucleusJDOHelper;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.QueryMetaData;
import org.datanucleus.query.compiler.QueryCompilation;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.VariableExpression;
import org.datanucleus.store.query.NoQueryResultsException;
import org.datanucleus.store.query.Query;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.StringUtils;

public class JDOQLTypedQueryImpl extends AbstractJDOQLTypedQuery implements JDOQLTypedQuery {
   private static final long serialVersionUID = -8359479260893321900L;
   protected JDOFetchPlan fetchPlan;
   protected boolean ignoreCache = false;
   protected Boolean serializeRead = null;
   protected Integer datastoreReadTimeout = null;
   protected Integer datastoreWriteTimeout = null;
   protected Map extensions = null;
   protected Collection candidates = null;
   boolean unmodifiable = false;
   protected Map parameterExprByName = null;
   protected Map parameterValuesByName = null;
   protected transient Set subqueries = null;
   protected transient Set internalQueries = null;

   public JDOQLTypedQueryImpl(PersistenceManager pm, Class candidateClass) {
      super(pm, candidateClass, "this");
   }

   public PersistableExpression candidate() {
      String candName = this.candidateCls.getName();
      int pos = candName.lastIndexOf(46);
      String qName = candName.substring(0, pos + 1) + getQueryClassNameForClassName(candName.substring(pos + 1));

      try {
         Class qClass = this.ec.getClassLoaderResolver().classForName(qName);
         Method method = qClass.getMethod("candidate");
         Object candObj = method.invoke((Object)null, (Object[])null);
         if (candObj != null && candObj instanceof PersistableExpression) {
            return (PersistableExpression)candObj;
         } else {
            throw new JDOException("Class " + this.candidateCls.getName() + " has a Query class but the candidate is invalid");
         }
      } catch (NoSuchMethodException var7) {
         throw new JDOException("Class " + this.candidateCls.getName() + " has a Query class but the candidate is invalid");
      } catch (InvocationTargetException var8) {
         throw new JDOException("Class " + this.candidateCls.getName() + " has a Query class but the candidate is invalid");
      } catch (IllegalAccessException var9) {
         throw new JDOException("Class " + this.candidateCls.getName() + " has a Query class but the candidate is invalid");
      }
   }

   public Expression parameter(String name, Class type) {
      this.discardCompiled();
      ExpressionImpl paramExpr = null;
      Object var14;
      if (type != Boolean.class && type != Boolean.TYPE) {
         if (type != Byte.class && type != Byte.TYPE) {
            if (type != Character.class && type != Character.TYPE) {
               if (type != Double.class && type != Double.TYPE) {
                  if (type != Float.class && type != Float.TYPE) {
                     if (type != Integer.class && type != Integer.TYPE) {
                        if (type != Long.class && type != Long.TYPE) {
                           if (type != Short.class && type != Short.TYPE) {
                              if (type == String.class) {
                                 var14 = new StringExpressionImpl(type, name, ExpressionType.PARAMETER);
                              } else if (Time.class.isAssignableFrom(type)) {
                                 var14 = new TimeExpressionImpl(type, name, ExpressionType.PARAMETER);
                              } else if (Date.class.isAssignableFrom(type)) {
                                 var14 = new DateExpressionImpl(type, name, ExpressionType.PARAMETER);
                              } else if (java.util.Date.class.isAssignableFrom(type)) {
                                 var14 = new DateTimeExpressionImpl(type, name, ExpressionType.PARAMETER);
                              } else if (this.ec.getApiAdapter().isPersistable(type)) {
                                 String typeName = type.getName();
                                 int pos = typeName.lastIndexOf(46);
                                 String qName = typeName.substring(0, pos + 1) + getQueryClassNameForClassName(typeName.substring(pos + 1));

                                 try {
                                    Class qClass = this.ec.getClassLoaderResolver().classForName(qName);
                                    Constructor ctr = qClass.getConstructor(Class.class, String.class, ExpressionType.class);
                                    Object candObj = ctr.newInstance(type, name, ExpressionType.PARAMETER);
                                    var14 = (ExpressionImpl)candObj;
                                 } catch (NoSuchMethodException var10) {
                                    throw new JDOException("Class " + typeName + " has a Query class but has no constructor for parameters");
                                 } catch (IllegalAccessException var11) {
                                    throw new JDOException("Class " + typeName + " has a Query class but has no constructor for parameters");
                                 } catch (InvocationTargetException var12) {
                                    throw new JDOException("Class " + typeName + " has a Query class but has no constructor for parameters");
                                 } catch (InstantiationException var13) {
                                    throw new JDOException("Class " + typeName + " has a Query class but has no constructor for parameters");
                                 }
                              } else {
                                 var14 = new ObjectExpressionImpl(type, name, ExpressionType.PARAMETER);
                              }
                           } else {
                              var14 = new NumericExpressionImpl(type, name, ExpressionType.PARAMETER);
                           }
                        } else {
                           var14 = new NumericExpressionImpl(type, name, ExpressionType.PARAMETER);
                        }
                     } else {
                        var14 = new NumericExpressionImpl(type, name, ExpressionType.PARAMETER);
                     }
                  } else {
                     var14 = new NumericExpressionImpl(type, name, ExpressionType.PARAMETER);
                  }
               } else {
                  var14 = new NumericExpressionImpl(type, name, ExpressionType.PARAMETER);
               }
            } else {
               var14 = new CharacterExpressionImpl(type, name, ExpressionType.PARAMETER);
            }
         } else {
            var14 = new ByteExpressionImpl(type, name, ExpressionType.PARAMETER);
         }
      } else {
         var14 = new BooleanExpressionImpl(type, name, ExpressionType.PARAMETER);
      }

      if (this.parameterExprByName == null) {
         this.parameterExprByName = new HashMap();
      }

      this.parameterExprByName.put(name, var14);
      return (Expression)var14;
   }

   public StringExpression stringParameter(String name) {
      StringExpressionImpl paramExpr = new StringExpressionImpl(String.class, name, ExpressionType.PARAMETER);
      if (this.parameterExprByName == null) {
         this.parameterExprByName = new HashMap();
      }

      this.parameterExprByName.put(name, paramExpr);
      return paramExpr;
   }

   public CharacterExpression characterParameter(String name) {
      CharacterExpressionImpl paramExpr = new CharacterExpressionImpl(Character.class, name, ExpressionType.PARAMETER);
      if (this.parameterExprByName == null) {
         this.parameterExprByName = new HashMap();
      }

      this.parameterExprByName.put(name, paramExpr);
      return paramExpr;
   }

   public NumericExpression numericParameter(String name) {
      NumericExpressionImpl<Float> paramExpr = new NumericExpressionImpl(Number.class, name, ExpressionType.PARAMETER);
      if (this.parameterExprByName == null) {
         this.parameterExprByName = new HashMap();
      }

      this.parameterExprByName.put(name, paramExpr);
      return paramExpr;
   }

   public DateExpression dateParameter(String name) {
      DateExpressionImpl paramExpr = new DateExpressionImpl(Date.class, name, ExpressionType.PARAMETER);
      if (this.parameterExprByName == null) {
         this.parameterExprByName = new HashMap();
      }

      this.parameterExprByName.put(name, paramExpr);
      return paramExpr;
   }

   public TimeExpression timeParameter(String name) {
      TimeExpressionImpl paramExpr = new TimeExpressionImpl(Time.class, name, ExpressionType.PARAMETER);
      if (this.parameterExprByName == null) {
         this.parameterExprByName = new HashMap();
      }

      this.parameterExprByName.put(name, paramExpr);
      return paramExpr;
   }

   public DateTimeExpression datetimeParameter(String name) {
      DateTimeExpressionImpl paramExpr = new DateTimeExpressionImpl(java.util.Date.class, name, ExpressionType.PARAMETER);
      if (this.parameterExprByName == null) {
         this.parameterExprByName = new HashMap();
      }

      this.parameterExprByName.put(name, paramExpr);
      return paramExpr;
   }

   public CollectionExpression collectionParameter(String name) {
      CollectionExpressionImpl paramExpr = new CollectionExpressionImpl(Collection.class, name, ExpressionType.PARAMETER);
      if (this.parameterExprByName == null) {
         this.parameterExprByName = new HashMap();
      }

      this.parameterExprByName.put(name, paramExpr);
      return paramExpr;
   }

   public MapExpression mapParameter(String name) {
      MapExpressionImpl paramExpr = new MapExpressionImpl(Map.class, name, ExpressionType.PARAMETER);
      if (this.parameterExprByName == null) {
         this.parameterExprByName = new HashMap();
      }

      this.parameterExprByName.put(name, paramExpr);
      return paramExpr;
   }

   public ListExpression listParameter(String name) {
      ListExpressionImpl paramExpr = new ListExpressionImpl(List.class, name, ExpressionType.PARAMETER);
      if (this.parameterExprByName == null) {
         this.parameterExprByName = new HashMap();
      }

      this.parameterExprByName.put(name, paramExpr);
      return paramExpr;
   }

   public Expression variable(String name, Class type) {
      this.discardCompiled();
      Expression varExpr = null;
      if (this.ec.getApiAdapter().isPersistable(type)) {
         String typeName = type.getName();
         int pos = typeName.lastIndexOf(46);
         String qName = typeName.substring(0, pos + 1) + getQueryClassNameForClassName(typeName.substring(pos + 1));

         try {
            Class qClass = this.ec.getClassLoaderResolver().classForName(qName);
            Constructor ctr = qClass.getConstructor(Class.class, String.class, ExpressionType.class);
            Object candObj = ctr.newInstance(type, name, ExpressionType.VARIABLE);
            varExpr = (Expression)candObj;
         } catch (NoSuchMethodException var10) {
            throw new JDOException("Class " + typeName + " has a Query class but has no constructor for variables");
         } catch (IllegalAccessException var11) {
            throw new JDOException("Class " + typeName + " has a Query class but has no constructor for variables");
         } catch (InvocationTargetException var12) {
            throw new JDOException("Class " + typeName + " has a Query class but has no constructor for variables");
         } catch (InstantiationException var13) {
            throw new JDOException("Class " + typeName + " has a Query class but has no constructor for variables");
         }
      } else if (type != Boolean.class && type != Boolean.TYPE) {
         if (type != Byte.class && type != Byte.TYPE) {
            if (type != Character.class && type != Character.TYPE) {
               if (type != Double.class && type != Double.TYPE) {
                  if (type != Float.class && type != Float.TYPE) {
                     if (type != Integer.class && type != Integer.TYPE) {
                        if (type != Long.class && type != Long.TYPE) {
                           if (type != Short.class && type != Short.TYPE) {
                              if (type == String.class) {
                                 varExpr = new StringExpressionImpl(type, name, ExpressionType.VARIABLE);
                              } else if (Time.class.isAssignableFrom(type)) {
                                 varExpr = new TimeExpressionImpl(type, name, ExpressionType.VARIABLE);
                              } else if (Date.class.isAssignableFrom(type)) {
                                 varExpr = new DateExpressionImpl(type, name, ExpressionType.VARIABLE);
                              } else if (java.util.Date.class.isAssignableFrom(type)) {
                                 varExpr = new DateTimeExpressionImpl(type, name, ExpressionType.VARIABLE);
                              } else {
                                 varExpr = new ObjectExpressionImpl(type, name, ExpressionType.VARIABLE);
                              }
                           } else {
                              varExpr = new NumericExpressionImpl(type, name, ExpressionType.VARIABLE);
                           }
                        } else {
                           varExpr = new NumericExpressionImpl(type, name, ExpressionType.VARIABLE);
                        }
                     } else {
                        varExpr = new NumericExpressionImpl(type, name, ExpressionType.VARIABLE);
                     }
                  } else {
                     varExpr = new NumericExpressionImpl(type, name, ExpressionType.VARIABLE);
                  }
               } else {
                  varExpr = new NumericExpressionImpl(type, name, ExpressionType.VARIABLE);
               }
            } else {
               varExpr = new CharacterExpressionImpl(type, name, ExpressionType.VARIABLE);
            }
         } else {
            varExpr = new ByteExpressionImpl(type, name, ExpressionType.VARIABLE);
         }
      } else {
         varExpr = new BooleanExpressionImpl(type, name, ExpressionType.VARIABLE);
      }

      return varExpr;
   }

   public JDOQLTypedQuery excludeSubclasses() {
      this.assertIsModifiable();
      this.discardCompiled();
      this.subclasses = false;
      return this;
   }

   public JDOQLTypedQuery includeSubclasses() {
      this.assertIsModifiable();
      this.discardCompiled();
      this.subclasses = true;
      return this;
   }

   public JDOQLTypedQuery filter(BooleanExpression expr) {
      this.assertIsModifiable();
      this.discardCompiled();
      this.filter = (BooleanExpressionImpl)expr;
      return this;
   }

   public JDOQLTypedQuery groupBy(Expression... exprs) {
      this.assertIsModifiable();
      this.discardCompiled();
      if (exprs != null && exprs.length > 0) {
         this.grouping = new ArrayList();

         for(int i = 0; i < exprs.length; ++i) {
            this.grouping.add((ExpressionImpl)exprs[i]);
         }
      }

      return this;
   }

   public JDOQLTypedQuery having(Expression expr) {
      this.assertIsModifiable();
      this.discardCompiled();
      this.having = (ExpressionImpl)expr;
      return this;
   }

   public JDOQLTypedQuery orderBy(OrderExpression... exprs) {
      this.assertIsModifiable();
      this.discardCompiled();
      if (exprs != null && exprs.length > 0) {
         this.ordering = new ArrayList();

         for(int i = 0; i < exprs.length; ++i) {
            this.ordering.add((OrderExpressionImpl)exprs[i]);
         }
      }

      return this;
   }

   public JDOQLTypedQuery range(long lowerIncl, long upperExcl) {
      this.discardCompiled();
      this.rangeLowerExpr = new NumericExpressionImpl(new Literal(lowerIncl));
      this.rangeUpperExpr = new NumericExpressionImpl(new Literal(upperExcl));
      return this;
   }

   public JDOQLTypedQuery range(NumericExpression lowerInclExpr, NumericExpression upperExclExpr) {
      this.discardCompiled();
      this.rangeLowerExpr = (ExpressionImpl)lowerInclExpr;
      this.rangeUpperExpr = (ExpressionImpl)upperExclExpr;
      return this;
   }

   public JDOQLTypedQuery range(Expression paramLowerInclExpr, Expression paramUpperExclExpr) {
      this.discardCompiled();
      if (!((ExpressionImpl)paramLowerInclExpr).isParameter()) {
         throw new JDOUserException("lower inclusive expression should be a parameter");
      } else if (!((ExpressionImpl)paramUpperExclExpr).isParameter()) {
         throw new JDOUserException("upper exclusive expression should be a parameter");
      } else {
         this.rangeLowerExpr = (ExpressionImpl)paramLowerInclExpr;
         this.rangeUpperExpr = (ExpressionImpl)paramUpperExclExpr;
         return this;
      }
   }

   public JDOQLTypedSubquery subquery(Class candidateClass, String candidateAlias) {
      this.discardCompiled();
      JDOQLTypedSubqueryImpl<S> subquery = new JDOQLTypedSubqueryImpl(this.pm, candidateClass, candidateAlias, this);
      if (this.subqueries == null) {
         this.subqueries = new HashSet();
      }

      this.subqueries.add(subquery);
      return subquery;
   }

   public JDOQLTypedSubquery subquery(String candidateAlias) {
      this.discardCompiled();
      JDOQLTypedSubqueryImpl<T> subquery = new JDOQLTypedSubqueryImpl(this.pm, this.candidateCls, candidateAlias, this);
      if (this.subqueries == null) {
         this.subqueries = new HashSet();
      }

      this.subqueries.add(subquery);
      return subquery;
   }

   public JDOQLTypedQuery setParameters(Map namedParamMap) {
      this.discardCompiled();
      if (namedParamMap != null && !namedParamMap.isEmpty()) {
         if (this.parameterValuesByName == null) {
            this.parameterValuesByName = new HashMap();
         }

         for(Map.Entry entry : namedParamMap.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            if (key instanceof String) {
               if (this.parameterExprByName == null || this.parameterExprByName != null && !this.parameterExprByName.containsKey(key)) {
                  throw new JDOUserException("Parameter with name " + key + " doesnt exist for this query");
               }

               this.parameterValuesByName.put((String)key, val);
            } else if (key instanceof Expression) {
               ParameterExpression internalParamExpr = (ParameterExpression)((ExpressionImpl)key).getQueryExpression();
               if (this.parameterExprByName == null || this.parameterExprByName != null && !this.parameterExprByName.containsKey(internalParamExpr.getAlias())) {
                  throw new JDOUserException("Parameter with name " + internalParamExpr.getAlias() + " doesnt exist for this query");
               }

               this.parameterValuesByName.put(internalParamExpr.getAlias(), val);
            }
         }

         return this;
      } else {
         this.parameterValuesByName = null;
         return this;
      }
   }

   public JDOQLTypedQuery setParameter(Expression paramExpr, Object value) {
      this.discardCompiled();
      ParameterExpression internalParamExpr = (ParameterExpression)((ExpressionImpl)paramExpr).getQueryExpression();
      if (this.parameterExprByName != null && (this.parameterExprByName == null || this.parameterExprByName.containsKey(internalParamExpr.getAlias()))) {
         if (this.parameterValuesByName == null) {
            this.parameterValuesByName = new HashMap();
         }

         this.parameterValuesByName.put(internalParamExpr.getAlias(), value);
         return this;
      } else {
         throw new JDOUserException("Parameter with name " + internalParamExpr.getAlias() + " doesnt exist for this query");
      }
   }

   public JDOQLTypedQuery setParameter(String paramName, Object value) {
      this.discardCompiled();
      if (this.parameterExprByName != null && (this.parameterExprByName == null || this.parameterExprByName.containsKey(paramName))) {
         if (this.parameterValuesByName == null) {
            this.parameterValuesByName = new HashMap();
         }

         this.parameterValuesByName.put(paramName, value);
         return this;
      } else {
         throw new JDOUserException("Parameter with name " + paramName + " doesnt exist for this query");
      }
   }

   public JDOQLTypedQuery setCandidates(Collection candidates) {
      if (candidates != null) {
         this.candidates = new ArrayList(candidates);
      } else {
         this.candidates = null;
      }

      return null;
   }

   public JDOQLTypedQuery result(boolean distinct, Expression... exprs) {
      this.assertIsModifiable();
      this.discardCompiled();
      this.result = null;
      if (exprs != null && exprs.length > 0) {
         this.result = new ArrayList();

         for(int i = 0; i < exprs.length; ++i) {
            this.result.add((ExpressionImpl)exprs[i]);
         }
      }

      this.resultDistinct = distinct;
      return this;
   }

   public List executeResultList(Class resultCls) {
      if (this.result == null) {
         throw new JDOUserException("Cannot call executeResultList method when query has result unset. Call executeList instead.");
      } else {
         this.type = AbstractJDOQLTypedQuery.QueryType.SELECT;
         this.updateExprs = null;
         this.updateVals = null;
         this.unique = false;
         this.resultClass = resultCls;
         return (List)this.executeInternalQuery(this.getInternalQuery());
      }
   }

   public Object executeResultUnique(Class resultCls) {
      if (this.result == null) {
         throw new JDOUserException("Cannot call executeResultUnique method when query has result unset. Call executeUnique instead.");
      } else {
         this.type = AbstractJDOQLTypedQuery.QueryType.SELECT;
         this.updateExprs = null;
         this.updateVals = null;
         this.unique = true;
         this.resultClass = resultCls;
         return this.executeInternalQuery(this.getInternalQuery());
      }
   }

   public List executeResultList() {
      if (this.result == null) {
         throw new JDOUserException("Cannot call executeResultList method when query has result unset. Call executeList instead.");
      } else {
         this.type = AbstractJDOQLTypedQuery.QueryType.SELECT;
         this.updateExprs = null;
         this.updateVals = null;
         this.unique = false;
         this.resultClass = null;
         return (List)this.executeInternalQuery(this.getInternalQuery());
      }
   }

   public Object executeResultUnique() {
      if (this.result == null) {
         throw new JDOUserException("Cannot call executeResultUnique method when query has result unset. Call executeUnique instead.");
      } else {
         this.type = AbstractJDOQLTypedQuery.QueryType.SELECT;
         this.updateExprs = null;
         this.updateVals = null;
         this.unique = true;
         this.resultClass = null;
         return this.executeInternalQuery(this.getInternalQuery());
      }
   }

   public List executeList() {
      if (this.result != null) {
         throw new JDOUserException("Cannot call executeList method when query has result set to " + StringUtils.collectionToString(this.result) + ". Call executeResultList instead.");
      } else {
         this.type = AbstractJDOQLTypedQuery.QueryType.SELECT;
         this.updateExprs = null;
         this.updateVals = null;
         this.unique = false;
         return (List)this.executeInternalQuery(this.getInternalQuery());
      }
   }

   public Object executeUnique() {
      if (this.result != null) {
         throw new JDOUserException("Cannot call executeUnique method when query has result set to " + StringUtils.collectionToString(this.result) + ". Call executeResultUnique instead.");
      } else {
         this.type = AbstractJDOQLTypedQuery.QueryType.SELECT;
         this.updateExprs = null;
         this.updateVals = null;
         this.unique = true;
         return this.executeInternalQuery(this.getInternalQuery());
      }
   }

   protected Query getInternalQuery() {
      Query internalQuery = this.ec.getStoreManager().getQueryManager().newQuery("JDOQL", this.ec, this.toString());
      internalQuery.setIgnoreCache(this.ignoreCache);
      if (this.extensions != null) {
         internalQuery.setExtensions(this.extensions);
      }

      if (this.fetchPlan != null) {
         internalQuery.setFetchPlan(this.fetchPlan.getInternalFetchPlan());
      }

      if (this.serializeRead != null) {
         internalQuery.setSerializeRead(this.serializeRead);
      }

      if (this.datastoreReadTimeout != null) {
         internalQuery.setDatastoreReadTimeoutMillis(this.datastoreReadTimeout);
      }

      if (this.datastoreWriteTimeout != null) {
         internalQuery.setDatastoreWriteTimeoutMillis(this.datastoreWriteTimeout);
      }

      if (!this.subclasses) {
         internalQuery.setSubclasses(false);
      }

      if (this.type == AbstractJDOQLTypedQuery.QueryType.SELECT) {
         internalQuery.setType((short)0);
         if (this.resultDistinct != null) {
            internalQuery.setResultDistinct(this.resultDistinct);
         }

         internalQuery.setResultClass(this.resultClass);
         internalQuery.setUnique(this.unique);
         if (this.candidates != null) {
            internalQuery.setCandidates(this.candidates);
         }
      } else if (this.type == AbstractJDOQLTypedQuery.QueryType.BULK_UPDATE) {
         internalQuery.setType((short)1);
      } else if (this.type == AbstractJDOQLTypedQuery.QueryType.BULK_DELETE) {
         internalQuery.setType((short)2);
      }

      QueryCompilation compilation = this.getCompilation();
      internalQuery.setCompilation(compilation);
      return internalQuery;
   }

   protected Object executeInternalQuery(Query internalQuery) {
      if (this.internalQueries == null) {
         this.internalQueries = new HashSet();
      }

      this.internalQueries.add(internalQuery);

      Object var2;
      try {
         if (this.parameterValuesByName == null && this.parameterExprByName == null) {
            var2 = internalQuery.execute();
            return var2;
         }

         this.validateParameters();
         var2 = internalQuery.executeWithMap(this.parameterValuesByName);
      } catch (NoQueryResultsException var8) {
         Object var3 = null;
         return var3;
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      } finally {
         this.parameterValuesByName = null;
      }

      return var2;
   }

   public long deletePersistentAll() {
      if (this.result == null && this.resultClass == null) {
         this.type = AbstractJDOQLTypedQuery.QueryType.SELECT;
         this.updateExprs = null;
         this.updateVals = null;
         this.unique = false;

         long var2;
         try {
            Query internalQuery = this.getInternalQuery();
            if (this.parameterValuesByName == null && this.parameterExprByName == null) {
               var2 = internalQuery.deletePersistentAll();
               return var2;
            }

            this.validateParameters();
            var2 = internalQuery.deletePersistentAll(this.parameterValuesByName);
         } catch (NucleusException jpe) {
            throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
         } finally {
            this.parameterValuesByName = null;
         }

         return var2;
      } else {
         throw new JDOUserException("Cannot call deletePersistentAll method when query has result or resultClass set. Remove the result setting.");
      }
   }

   private void validateParameters() {
      int numParams = this.parameterExprByName != null ? this.parameterExprByName.size() : 0;
      int numValues = this.parameterValuesByName != null ? this.parameterValuesByName.size() : 0;
      if (numParams != 0 || numValues != 0) {
         if (numParams != numValues) {
            throw new JDOUserException("Query has " + numParams + " but " + numValues + " values have been provided");
         } else {
            for(String paramName : this.parameterExprByName.keySet()) {
               if (!this.parameterValuesByName.containsKey(paramName)) {
                  throw new JDOUserException("Query has a parameter " + paramName + " defined but no value supplied");
               }
            }

         }
      }
   }

   public JDOQLTypedQuery set(Expression expr, Object val) {
      this.type = AbstractJDOQLTypedQuery.QueryType.BULK_UPDATE;
      if (this.updateExprs == null) {
         this.updateExprs = new ArrayList();
         this.updateVals = new ArrayList();
      }

      ExpressionImpl valExpr = null;
      org.datanucleus.query.expression.Expression literalExpr = new Literal(val);
      if (val instanceof String) {
         valExpr = new StringExpressionImpl(literalExpr);
      } else if (val instanceof Number) {
         valExpr = new NumericExpressionImpl(literalExpr);
      } else if (val instanceof Time) {
         valExpr = new TimeExpressionImpl(literalExpr);
      } else if (val instanceof Date) {
         valExpr = new DateExpressionImpl(literalExpr);
      } else if (val instanceof java.util.Date) {
         valExpr = new DateTimeExpressionImpl(literalExpr);
      } else if (val instanceof Boolean) {
         valExpr = new BooleanExpressionImpl(literalExpr);
      } else if (val instanceof Byte) {
         valExpr = new ByteExpressionImpl(literalExpr);
      } else if (val instanceof Enum) {
         valExpr = new EnumExpressionImpl(literalExpr);
      }

      this.updateExprs.add((ExpressionImpl)expr);
      this.updateVals.add(valExpr);
      return this;
   }

   public long update() {
      this.type = AbstractJDOQLTypedQuery.QueryType.BULK_UPDATE;
      if (this.updateExprs != null && this.updateExprs.size() != 0) {
         return (Long)this.executeInternalQuery(this.getInternalQuery());
      } else {
         throw new JDOUserException("No update expressions defined. Use set() method");
      }
   }

   public long delete() {
      this.type = AbstractJDOQLTypedQuery.QueryType.BULK_DELETE;
      this.updateExprs = null;
      this.updateVals = null;
      return (Long)this.executeInternalQuery(this.getInternalQuery());
   }

   public FetchPlan getFetchPlan() {
      if (this.fetchPlan == null) {
         this.fetchPlan = new JDOFetchPlan(this.ec.getFetchPlan().getCopy());
      }

      return this.fetchPlan;
   }

   public PersistenceManager getPersistenceManager() {
      return this.pm;
   }

   public void close(Object result) {
      if (this.internalQueries != null) {
         for(Query query : this.internalQueries) {
            query.close(result);
         }
      }

   }

   public void closeAll() {
      if (this.internalQueries != null) {
         for(Query query : this.internalQueries) {
            query.closeAll();
         }

         this.internalQueries.clear();
         this.internalQueries = null;
      }

   }

   public void close() throws IOException {
      this.closeAll();
      if (this.fetchPlan != null) {
         this.fetchPlan.clearGroups();
         this.fetchPlan = null;
      }

   }

   public QueryCompilation compile(MetaDataManager mmgr, ClassLoaderResolver clr) {
      QueryCompilation compilation = super.compile(mmgr, clr);
      if (this.subqueries != null && !this.subqueries.isEmpty()) {
         for(JDOQLTypedSubqueryImpl subquery : this.subqueries) {
            QueryCompilation subqueryCompilation = subquery.getCompilation();
            compilation.addSubqueryCompilation(subquery.getAlias(), subqueryCompilation);
         }
      }

      return compilation;
   }

   public static String getQueryClassNameForClassName(String name) {
      return "Q" + name;
   }

   public Integer getDatastoreReadTimeoutMillis() {
      return this.datastoreReadTimeout;
   }

   public JDOQLTypedQuery datastoreReadTimeoutMillis(Integer interval) {
      this.datastoreReadTimeout = interval;
      return this;
   }

   public Integer getDatastoreWriteTimeoutMillis() {
      return this.datastoreWriteTimeout;
   }

   public JDOQLTypedQuery datastoreWriteTimeoutMillis(Integer interval) {
      this.datastoreWriteTimeout = interval;
      return this;
   }

   public Boolean getSerializeRead() {
      return this.serializeRead;
   }

   public JDOQLTypedQuery serializeRead(Boolean serialize) {
      this.serializeRead = serialize;
      return this;
   }

   public boolean isUnmodifiable() {
      return this.unmodifiable;
   }

   public JDOQLTypedQuery unmodifiable() {
      this.unmodifiable = true;
      return this;
   }

   protected void assertIsModifiable() {
      if (this.unmodifiable) {
         throw new NucleusUserException(Localiser.msg("021014"));
      }
   }

   public boolean getIgnoreCache() {
      return this.ignoreCache;
   }

   public JDOQLTypedQuery ignoreCache(boolean flag) {
      this.ignoreCache = flag;
      return this;
   }

   public JDOQLTypedQuery extension(String key, Object value) {
      if (this.extensions == null) {
         this.extensions = new HashMap();
      }

      this.extensions.put(key, value);
      return this;
   }

   public JDOQLTypedQuery extensions(Map values) {
      this.extensions = new HashMap(this.extensions);
      return this;
   }

   public void cancelAll() {
      if (this.internalQueries != null && !this.internalQueries.isEmpty()) {
         try {
            for(Query query : this.internalQueries) {
               query.cancel();
            }

         } catch (NucleusException ne) {
            throw new JDOException("Error in calling Query.cancelAll. See the nested exception", ne);
         } catch (UnsupportedOperationException var4) {
            throw new JDOUnsupportedOptionException();
         }
      }
   }

   public void cancel(Thread thread) {
      if (this.internalQueries != null && !this.internalQueries.isEmpty()) {
         try {
            for(Query query : this.internalQueries) {
               query.cancel(thread);
            }

         } catch (NucleusException ne) {
            throw new JDOException("Error in calling Query.cancelAll. See the nested exception", ne);
         } catch (UnsupportedOperationException var5) {
            throw new JDOUnsupportedOptionException();
         }
      }
   }

   public JDOQLTypedQuery saveAsNamedQuery(String name) {
      JDOPersistenceManagerFactory.checkJDOPermission(JDOPermission.GET_METADATA);
      QueryMetaData qmd = new QueryMetaData(name);
      qmd.setLanguage("JDOQL");
      Query query = this.getInternalQuery();
      qmd.setQuery(query.toString());
      qmd.setResultClass(query.getResultClassName());
      qmd.setUnique(query.isUnique());
      Map<String, Object> queryExts = query.getExtensions();
      if (queryExts != null && !queryExts.isEmpty()) {
         for(Map.Entry queryExtEntry : queryExts.entrySet()) {
            qmd.addExtension((String)queryExtEntry.getKey(), "" + queryExtEntry.getValue());
         }
      }

      query.getExecutionContext().getMetaDataManager().registerNamedQuery(qmd);
      return this;
   }

   public String getJDOQLForExpression(org.datanucleus.query.expression.Expression expr) {
      if (expr instanceof VariableExpression) {
         VariableExpression varExpr = (VariableExpression)expr;
         if (this.subqueries != null) {
            for(JDOQLTypedSubqueryImpl subq : this.subqueries) {
               if (varExpr.getId().equals(subq.getAlias())) {
                  return "(" + subq.toString() + ")";
               }
            }
         }
      }

      return super.getJDOQLForExpression(expr);
   }
}
