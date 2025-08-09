package org.datanucleus.store.rdbms.mapping.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.state.ObjectProvider;

public class SerialisedReferenceMapping extends SerialisedMapping {
   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, Object value) {
      if (this.mmd != null) {
         this.setObject(ec, ps, exprIndex, value, (ObjectProvider)null, this.mmd.getAbsoluteFieldNumber());
      } else {
         super.setObject(ec, ps, exprIndex, value);
      }

   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, Object value, ObjectProvider ownerOP, int fieldNumber) {
      ApiAdapter api = ec.getApiAdapter();
      if (api.isPersistable(value)) {
         ObjectProvider embSM = ec.findObjectProvider(value);
         if (embSM == null || api.getExecutionContext(value) == null) {
            ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, value, false, ownerOP, fieldNumber);
         }
      }

      ObjectProvider sm = null;
      if (api.isPersistable(value)) {
         sm = ec.findObjectProvider(value);
      }

      if (sm != null) {
         sm.setStoringPC();
      }

      this.getDatastoreMapping(0).setObject(ps, exprIndex[0], value);
      if (sm != null) {
         sm.unsetStoringPC();
      }

   }

   public Object getObject(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return this.mmd != null ? this.getObject(ec, resultSet, exprIndex, (ObjectProvider)null, this.mmd.getAbsoluteFieldNumber()) : super.getObject(ec, resultSet, exprIndex);
   }

   public Object getObject(ExecutionContext ec, ResultSet resultSet, int[] exprIndex, ObjectProvider ownerOP, int fieldNumber) {
      Object obj = this.getDatastoreMapping(0).getObject(resultSet, exprIndex[0]);
      ApiAdapter api = ec.getApiAdapter();
      if (api.isPersistable(obj)) {
         ObjectProvider embSM = ec.findObjectProvider(obj);
         if (embSM == null || api.getExecutionContext(obj) == null) {
            ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, obj, false, ownerOP, fieldNumber);
         }
      }

      return obj;
   }
}
