package org.datanucleus.store.rdbms.query;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.StoredProcQueryParameterMode;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.connection.ManagedConnectionResourceListener;
import org.datanucleus.store.query.AbstractStoredProcedureQuery;
import org.datanucleus.store.query.NoQueryResultsException;
import org.datanucleus.store.query.QueryNotUniqueException;
import org.datanucleus.store.query.QueryResult;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class StoredProcedureQuery extends AbstractStoredProcedureQuery {
   private static final long serialVersionUID = 4167789892047598591L;
   CallableStatement stmt;

   public StoredProcedureQuery(StoreManager storeMgr, ExecutionContext ec, StoredProcedureQuery query) {
      super(storeMgr, ec, query);
   }

   public StoredProcedureQuery(StoreManager storeMgr, ExecutionContext ec) {
      super(storeMgr, ec, (String)null);
   }

   public StoredProcedureQuery(StoreManager storeMgr, ExecutionContext ec, String procName) {
      super(storeMgr, ec, procName);
   }

   protected void compileInternal(Map parameterValues) {
      DatastoreAdapter dba = ((RDBMSStoreManager)this.storeMgr).getDatastoreAdapter();
      if (!dba.supportsOption("StoredProcs")) {
         throw new NucleusUserException("This RDBMS does not support stored procedures!");
      }
   }

   public boolean processesRangeInDatastoreQuery() {
      return true;
   }

   protected Object executeQuery(Map parameters) {
      this.inputParameters = new HashMap();
      if (this.implicitParameters != null) {
         this.inputParameters.putAll(this.implicitParameters);
      }

      if (parameters != null) {
         this.inputParameters.putAll(parameters);
      }

      this.prepareDatastore();
      boolean failed = true;
      long start = 0L;
      if (this.ec.getStatistics() != null) {
         start = System.currentTimeMillis();
         this.ec.getStatistics().queryBegin();
      }

      Object var5;
      try {
         var5 = this.performExecute(this.inputParameters);
      } finally {
         if (this.ec.getStatistics() != null) {
            if (failed) {
               this.ec.getStatistics().queryExecutedWithError();
            } else {
               this.ec.getStatistics().queryExecuted(System.currentTimeMillis() - start);
            }
         }

      }

      return var5;
   }

   protected Object performExecute(Map parameters) {
      try {
         RDBMSStoreManager storeMgr = (RDBMSStoreManager)this.getStoreManager();
         ManagedConnection mconn = storeMgr.getConnection(this.ec);

         Boolean var19;
         try {
            Connection conn = (Connection)mconn.getConnection();
            StringBuilder stmtStr = new StringBuilder("CALL " + this.procedureName);
            stmtStr.append("(");
            if (this.storedProcParams != null && !this.storedProcParams.isEmpty()) {
               Iterator<AbstractStoredProcedureQuery.StoredProcedureParameter> paramIter = this.storedProcParams.iterator();

               while(paramIter.hasNext()) {
                  paramIter.next();
                  stmtStr.append("?");
                  if (paramIter.hasNext()) {
                     stmtStr.append(",");
                  }
               }
            }

            stmtStr.append(")");
            this.stmt = conn.prepareCall(stmtStr.toString());
            boolean hasOutputParams = false;
            if (this.storedProcParams != null && !this.storedProcParams.isEmpty()) {
               for(AbstractStoredProcedureQuery.StoredProcedureParameter param : this.storedProcParams) {
                  if (param.getMode() == StoredProcQueryParameterMode.IN || param.getMode() == StoredProcQueryParameterMode.INOUT) {
                     if (param.getType() == Integer.class) {
                        if (param.getName() != null) {
                           this.stmt.setInt(param.getName(), (Integer)parameters.get(param.getName()));
                        } else {
                           this.stmt.setInt(param.getPosition(), (Integer)parameters.get(param.getPosition()));
                        }
                     } else if (param.getType() == Long.class) {
                        if (param.getName() != null) {
                           this.stmt.setLong(param.getName(), (Long)parameters.get(param.getName()));
                        } else {
                           this.stmt.setLong(param.getPosition(), (Long)parameters.get(param.getPosition()));
                        }
                     } else if (param.getType() == Short.class) {
                        if (param.getName() != null) {
                           this.stmt.setShort(param.getName(), (Short)parameters.get(param.getName()));
                        } else {
                           this.stmt.setShort(param.getPosition(), (Short)parameters.get(param.getPosition()));
                        }
                     } else if (param.getType() == Double.class) {
                        if (param.getName() != null) {
                           this.stmt.setDouble(param.getName(), (Double)parameters.get(param.getName()));
                        } else {
                           this.stmt.setDouble(param.getPosition(), (Double)parameters.get(param.getPosition()));
                        }
                     } else if (param.getType() == Float.class) {
                        if (param.getName() != null) {
                           this.stmt.setFloat(param.getName(), (Float)parameters.get(param.getName()));
                        } else {
                           this.stmt.setFloat(param.getPosition(), (Float)parameters.get(param.getPosition()));
                        }
                     } else if (param.getType() == Boolean.class) {
                        if (param.getName() != null) {
                           this.stmt.setBoolean(param.getName(), (Boolean)parameters.get(param.getName()));
                        } else {
                           this.stmt.setBoolean(param.getPosition(), (Boolean)parameters.get(param.getPosition()));
                        }
                     } else if (param.getType() == String.class) {
                        if (param.getName() != null) {
                           this.stmt.setString(param.getName(), (String)parameters.get(param.getName()));
                        } else {
                           this.stmt.setString(param.getPosition(), (String)parameters.get(param.getPosition()));
                        }
                     } else if (param.getType() == Date.class) {
                        if (param.getName() != null) {
                           this.stmt.setDate(param.getName(), (java.sql.Date)parameters.get(param.getName()));
                        } else {
                           this.stmt.setDate(param.getPosition(), (java.sql.Date)parameters.get(param.getPosition()));
                        }
                     } else if (param.getType() == BigInteger.class) {
                        if (param.getName() != null) {
                           this.stmt.setLong(param.getName(), ((BigInteger)parameters.get(param.getName())).longValue());
                        } else {
                           this.stmt.setLong(param.getPosition(), ((BigInteger)parameters.get(param.getPosition())).longValue());
                        }
                     } else {
                        if (param.getType() != BigDecimal.class) {
                           throw new NucleusException("Dont currently support stored proc input params of type " + param.getType());
                        }

                        if (param.getName() != null) {
                           this.stmt.setDouble(param.getName(), ((BigDecimal)parameters.get(param.getName())).doubleValue());
                        } else {
                           this.stmt.setDouble(param.getPosition(), ((BigDecimal)parameters.get(param.getPosition())).doubleValue());
                        }
                     }
                  }

                  if (param.getMode() == StoredProcQueryParameterMode.OUT || param.getMode() == StoredProcQueryParameterMode.INOUT) {
                     if (param.getType() == Integer.class) {
                        if (param.getName() != null) {
                           this.stmt.registerOutParameter(param.getName(), 4);
                        } else {
                           this.stmt.registerOutParameter(param.getPosition(), 4);
                        }
                     } else if (param.getType() == Long.class) {
                        if (param.getName() != null) {
                           this.stmt.registerOutParameter(param.getName(), 4);
                        } else {
                           this.stmt.registerOutParameter(param.getPosition(), 4);
                        }
                     } else if (param.getType() == Short.class) {
                        if (param.getName() != null) {
                           this.stmt.registerOutParameter(param.getName(), 4);
                        } else {
                           this.stmt.registerOutParameter(param.getPosition(), 4);
                        }
                     } else if (param.getType() == Double.class) {
                        if (param.getName() != null) {
                           this.stmt.registerOutParameter(param.getName(), 8);
                        } else {
                           this.stmt.registerOutParameter(param.getPosition(), 8);
                        }
                     } else if (param.getType() == Float.class) {
                        if (param.getName() != null) {
                           this.stmt.registerOutParameter(param.getName(), 6);
                        } else {
                           this.stmt.registerOutParameter(param.getPosition(), 6);
                        }
                     } else if (param.getType() == Boolean.class) {
                        if (param.getName() != null) {
                           this.stmt.registerOutParameter(param.getName(), 16);
                        } else {
                           this.stmt.registerOutParameter(param.getPosition(), 16);
                        }
                     } else if (param.getType() == String.class) {
                        if (param.getName() != null) {
                           this.stmt.registerOutParameter(param.getName(), 12);
                        } else {
                           this.stmt.registerOutParameter(param.getPosition(), 12);
                        }
                     } else if (param.getType() == Date.class) {
                        if (param.getName() != null) {
                           this.stmt.registerOutParameter(param.getName(), 91);
                        } else {
                           this.stmt.registerOutParameter(param.getPosition(), 91);
                        }
                     } else if (param.getType() == BigInteger.class) {
                        if (param.getName() != null) {
                           this.stmt.registerOutParameter(param.getName(), -5);
                        } else {
                           this.stmt.registerOutParameter(param.getPosition(), -5);
                        }
                     } else {
                        if (param.getType() != BigDecimal.class) {
                           throw new NucleusException("Dont currently support stored proc output params of type " + param.getType());
                        }

                        if (param.getName() != null) {
                           this.stmt.registerOutParameter(param.getName(), 8);
                        } else {
                           this.stmt.registerOutParameter(param.getPosition(), 8);
                        }
                     }

                     hasOutputParams = true;
                  }
               }
            }

            if (NucleusLogger.DATASTORE_NATIVE.isDebugEnabled()) {
               NucleusLogger.DATASTORE_NATIVE.debug(stmtStr.toString());
            }

            boolean hasResultSet = this.stmt.execute();
            if (hasOutputParams) {
               for(AbstractStoredProcedureQuery.StoredProcedureParameter param : this.storedProcParams) {
                  if (param.getMode() == StoredProcQueryParameterMode.OUT || param.getMode() == StoredProcQueryParameterMode.INOUT) {
                     Object value = null;
                     if (param.getType() == Integer.class) {
                        if (param.getName() != null) {
                           value = this.stmt.getInt(param.getName());
                        } else {
                           value = this.stmt.getInt(param.getPosition());
                        }
                     } else if (param.getType() == Long.class) {
                        if (param.getName() != null) {
                           value = this.stmt.getLong(param.getName());
                        } else {
                           value = this.stmt.getLong(param.getPosition());
                        }
                     } else if (param.getType() == Short.class) {
                        if (param.getName() != null) {
                           value = this.stmt.getShort(param.getName());
                        } else {
                           value = this.stmt.getShort(param.getPosition());
                        }
                     } else if (param.getType() == Double.class) {
                        if (param.getName() != null) {
                           value = this.stmt.getDouble(param.getName());
                        } else {
                           value = this.stmt.getDouble(param.getPosition());
                        }
                     } else if (param.getType() == Float.class) {
                        if (param.getName() != null) {
                           value = this.stmt.getFloat(param.getName());
                        } else {
                           value = this.stmt.getFloat(param.getPosition());
                        }
                     } else if (param.getType() == Boolean.class) {
                        if (param.getName() != null) {
                           value = this.stmt.getBoolean(param.getName());
                        } else {
                           value = this.stmt.getBoolean(param.getPosition());
                        }
                     } else if (param.getType() == String.class) {
                        if (param.getName() != null) {
                           value = this.stmt.getString(param.getName());
                        } else {
                           value = this.stmt.getString(param.getPosition());
                        }
                     } else if (param.getType() == Date.class) {
                        if (param.getName() != null) {
                           value = this.stmt.getDate(param.getName());
                        } else {
                           value = this.stmt.getDate(param.getPosition());
                        }
                     } else if (param.getType() == BigInteger.class) {
                        if (param.getName() != null) {
                           value = this.stmt.getLong(param.getName());
                        } else {
                           value = this.stmt.getLong(param.getPosition());
                        }
                     } else {
                        if (param.getType() != BigDecimal.class) {
                           throw new NucleusUserException("Dont currently support output parameters of type=" + param.getType());
                        }

                        if (param.getName() != null) {
                           value = this.stmt.getDouble(param.getName());
                        } else {
                           value = this.stmt.getDouble(param.getPosition());
                        }
                     }

                     if (this.outputParamValues == null) {
                        this.outputParamValues = new HashMap();
                     }

                     if (param.getName() != null) {
                        this.outputParamValues.put(param.getName(), value);
                     } else {
                        this.outputParamValues.put(param.getPosition(), value);
                     }
                  }
               }
            }

            var19 = hasResultSet;
         } finally {
            mconn.release();
         }

         return var19;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("059027", new Object[]{this.procedureName}), e);
      }
   }

   public boolean hasMoreResults() {
      if (this.stmt == null) {
         throw new NucleusUserException("Cannot check for more results until the stored procedure has been executed");
      } else {
         try {
            return this.stmt.getMoreResults();
         } catch (SQLException var2) {
            return false;
         }
      }
   }

   public int getUpdateCount() {
      if (this.stmt == null) {
         throw new NucleusUserException("Cannot check for update count until the stored procedure has been executed");
      } else {
         ManagedConnection mconn = this.storeMgr.getConnection(this.ec);

         int var2;
         try {
            ++this.resultSetNumber;
            var2 = this.stmt.getUpdateCount();
         } catch (SQLException sqle) {
            throw new NucleusDataStoreException("Exception from CallableStatement.getUpdateCount", sqle);
         } finally {
            mconn.release();
         }

         return var2;
      }
   }

   public Object getNextResults() {
      if (this.stmt == null) {
         throw new NucleusUserException("Cannot check for more results until the stored procedure has been executed");
      } else {
         ManagedConnection mconn = this.storeMgr.getConnection(this.ec);

         Object var6;
         try {
            ++this.resultSetNumber;
            ResultSet rs = this.stmt.getResultSet();
            QueryResult qr = this.getResultsForResultSet((RDBMSStoreManager)this.storeMgr, rs, mconn);
            if (!this.shouldReturnSingleRow()) {
               QueryResult var18 = qr;
               return var18;
            }

            try {
               if (qr == null || qr.size() == 0) {
                  throw new NoQueryResultsException("No query results were returned");
               }

               Iterator qrIter = qr.iterator();
               Object firstRow = qrIter.next();
               if (qrIter.hasNext()) {
                  throw new QueryNotUniqueException();
               }

               var6 = firstRow;
            } finally {
               this.close(qr);
            }
         } catch (SQLException sqle) {
            throw new NucleusDataStoreException("Exception from CallableStatement.getResultSet", sqle);
         } finally {
            mconn.release();
         }

         return var6;
      }
   }

   protected QueryResult getResultsForResultSet(RDBMSStoreManager storeMgr, ResultSet rs, final ManagedConnection mconn) throws SQLException {
      ResultObjectFactory rof = null;
      if (this.resultMetaDatas != null) {
         rof = new ResultMetaDataROF(storeMgr, this.resultMetaDatas[this.resultSetNumber]);
      } else {
         rof = RDBMSQueryUtils.getResultObjectFactoryForNoCandidateClass(storeMgr, rs, this.resultClasses != null ? this.resultClasses[this.resultSetNumber] : null);
      }

      String resultSetType = RDBMSQueryUtils.getResultSetTypeForQuery(this);
      final AbstractRDBMSQueryResult qr = null;
      if (!resultSetType.equals("scroll-insensitive") && !resultSetType.equals("scroll-sensitive")) {
         qr = new ForwardQueryResult(this, rof, rs, (Collection)null);
         ((ForwardQueryResult)qr).setCloseStatementWithResultSet(false);
      } else {
         qr = new ScrollableQueryResult(this, rof, rs, (Collection)null);
      }

      qr.setCloseStatementWithResultSet(false);
      qr.initialise();
      ManagedConnectionResourceListener listener = new ManagedConnectionResourceListener() {
         public void transactionFlushed() {
         }

         public void transactionPreClose() {
            qr.disconnect();

            try {
               if (StoredProcedureQuery.this.stmt != null) {
                  StoredProcedureQuery.this.stmt.close();
               }
            } catch (SQLException var2) {
            }

         }

         public void managedConnectionPreClose() {
            if (!StoredProcedureQuery.this.ec.getTransaction().isActive()) {
               qr.disconnect();

               try {
                  if (StoredProcedureQuery.this.stmt != null) {
                     StoredProcedureQuery.this.stmt.close();
                  }
               } catch (SQLException var2) {
               }
            }

         }

         public void managedConnectionPostClose() {
         }

         public void resourcePostClose() {
            mconn.removeListener(this);
         }
      };
      mconn.addListener(listener);
      qr.addConnectionListener(listener);
      return qr;
   }
}
