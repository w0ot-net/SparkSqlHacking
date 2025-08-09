package org.datanucleus.store.query;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.QueryResultMetaData;
import org.datanucleus.metadata.StoredProcQueryParameterMode;
import org.datanucleus.store.Extent;
import org.datanucleus.store.StoreManager;

public abstract class AbstractStoredProcedureQuery extends Query {
   private static final long serialVersionUID = 6944783614104829182L;
   protected String procedureName;
   protected Set storedProcParams;
   protected int resultSetNumber;
   protected QueryResultMetaData[] resultMetaDatas;
   protected Class[] resultClasses;
   protected Map outputParamValues;

   public AbstractStoredProcedureQuery(StoreManager storeMgr, ExecutionContext ec, AbstractStoredProcedureQuery query) {
      this(storeMgr, ec, query.procedureName);
   }

   public AbstractStoredProcedureQuery(StoreManager storeMgr, ExecutionContext ec, String procName) {
      super(storeMgr, ec);
      this.storedProcParams = null;
      this.resultSetNumber = 0;
      this.resultMetaDatas = null;
      this.resultClasses = null;
      this.outputParamValues = null;
      this.procedureName = procName;
   }

   public String getLanguage() {
      return "STOREDPROC";
   }

   public void setCandidates(Extent pcs) {
      throw new NucleusUserException("Not supported for stored procedures");
   }

   public void setCandidates(Collection pcs) {
      throw new NucleusUserException("Not supported for stored procedures");
   }

   public void setResultMetaData(QueryResultMetaData[] qrmds) {
      this.resultMetaDatas = qrmds;
      this.resultClasses = null;
   }

   public void setResultClasses(Class[] resultClasses) {
      this.resultClasses = resultClasses;
      this.resultMetaDatas = null;
   }

   public void registerParameter(int pos, Class type, StoredProcQueryParameterMode mode) {
      if (this.storedProcParams == null) {
         this.storedProcParams = new HashSet();
      }

      StoredProcedureParameter param = new StoredProcedureParameter(mode, pos, type);
      this.storedProcParams.add(param);
   }

   public void registerParameter(String name, Class type, StoredProcQueryParameterMode mode) {
      if (this.storedProcParams == null) {
         this.storedProcParams = new HashSet();
      }

      StoredProcedureParameter param = new StoredProcedureParameter(mode, name, type);
      this.storedProcParams.add(param);
   }

   public abstract boolean hasMoreResults();

   public abstract Object getNextResults();

   public abstract int getUpdateCount();

   public Object getOutputParameterValue(int pos) {
      return this.outputParamValues != null ? this.outputParamValues.get(pos) : null;
   }

   public Object getOutputParameterValue(String name) {
      return this.outputParamValues != null ? this.outputParamValues.get(name) : null;
   }

   public static class StoredProcedureParameter {
      StoredProcQueryParameterMode mode;
      Integer position;
      String name;
      Class type;

      public StoredProcedureParameter(StoredProcQueryParameterMode mode, int pos, Class type) {
         this.mode = mode;
         this.position = pos;
         this.type = type;
      }

      public StoredProcedureParameter(StoredProcQueryParameterMode mode, String name, Class type) {
         this.mode = mode;
         this.name = name;
         this.type = type;
      }

      public String getName() {
         return this.name;
      }

      public Integer getPosition() {
         return this.position;
      }

      public StoredProcQueryParameterMode getMode() {
         return this.mode;
      }

      public Class getType() {
         return this.type;
      }
   }
}
