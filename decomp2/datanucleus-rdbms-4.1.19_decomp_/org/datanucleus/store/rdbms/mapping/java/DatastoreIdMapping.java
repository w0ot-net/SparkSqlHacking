package org.datanucleus.store.rdbms.mapping.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.identity.DatastoreId;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.exceptions.NotYetFlushedException;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class DatastoreIdMapping extends SingleFieldMapping {
   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] param, Object value) {
      if (value == null) {
         this.getDatastoreMapping(0).setObject(ps, param[0], (Object)null);
      } else {
         ApiAdapter api = ec.getApiAdapter();
         Object id;
         if (api.isPersistable(value)) {
            id = api.getIdForObject(value);
            if (id == null) {
               if (ec.isInserting(value)) {
                  this.getDatastoreMapping(0).setObject(ps, param[0], (Object)null);
                  throw new NotYetFlushedException(value);
               }

               ec.persistObjectInternal(value, (ObjectProvider)null, -1, 0);
               ec.flushInternal(false);
            }

            id = api.getIdForObject(value);
         } else {
            id = value;
         }

         Object idKey = IdentityUtils.getTargetKeyForDatastoreIdentity(id);

         try {
            this.getDatastoreMapping(0).setObject(ps, param[0], idKey);
         } catch (Exception var9) {
            this.getDatastoreMapping(0).setObject(ps, param[0], idKey.toString());
         }
      }

   }

   public Object getObject(ExecutionContext ec, ResultSet rs, int[] param) {
      Object value;
      if (this.getNumberOfDatastoreMappings() > 0) {
         value = this.getDatastoreMapping(0).getObject(rs, param[0]);
      } else {
         if (this.referenceMapping != null) {
            return this.referenceMapping.getObject(ec, rs, param);
         }

         Class fieldType = this.mmd.getType();
         JavaTypeMapping referenceMapping = this.storeMgr.getDatastoreClass(fieldType.getName(), ec.getClassLoaderResolver()).getIdMapping();
         value = referenceMapping.getDatastoreMapping(0).getObject(rs, param[0]);
      }

      if (value != null) {
         value = ec.getNucleusContext().getIdentityManager().getDatastoreId(this.getType(), value);
         if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
            NucleusLogger.PERSISTENCE.debug(Localiser.msg("041034", new Object[]{value}));
         }
      }

      return value;
   }

   public Class getJavaType() {
      return DatastoreId.class;
   }
}
