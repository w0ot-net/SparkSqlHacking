package org.datanucleus.store.fieldmanager;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class ReachabilityFieldManager extends AbstractFieldManager {
   private final ObjectProvider op;
   private Set reachables = null;

   public ReachabilityFieldManager(ObjectProvider op, Set reachables) {
      this.op = op;
      this.reachables = reachables;
   }

   protected void processPersistable(Object obj, AbstractMemberMetaData mmd) {
      ExecutionContext ec = this.op.getExecutionContext();
      ObjectProvider objOP = ec.findObjectProvider(obj);
      if (objOP != null) {
         Object objID = objOP.getInternalObjectId();
         if (!this.reachables.contains(objID) && !objOP.isDeleted()) {
            if (ec.isEnlistedInTransaction(objID)) {
               objOP.loadUnloadedRelationFields();
            }

            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("007000", StringUtils.toJVMIDString(obj), objID, objOP.getLifecycleState()));
            }

            this.reachables.add(objID);
            ReachabilityFieldManager pcFM = new ReachabilityFieldManager(objOP, this.reachables);
            int[] relationFieldNums = objOP.getClassMetaData().getRelationMemberPositions(ec.getClassLoaderResolver(), ec.getMetaDataManager());
            int[] loadedFieldNumbers = ClassUtils.getFlagsSetTo(objOP.getLoadedFields(), relationFieldNums, true);
            if (loadedFieldNumbers != null && loadedFieldNumbers.length > 0) {
               objOP.provideFields(loadedFieldNumbers, pcFM);
            }
         }
      } else if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("007005", this.op.getExecutionContext().getApiAdapter().getIdForObject(obj), mmd.getFullFieldName()));
      }

   }

   public void storeObjectField(int fieldNumber, Object value) {
      AbstractMemberMetaData mmd = this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
      if (value != null) {
         boolean persistCascade = mmd.isCascadePersist();
         RelationType relType = mmd.getRelationType(this.op.getExecutionContext().getClassLoaderResolver());
         ApiAdapter api = this.op.getExecutionContext().getApiAdapter();
         if (persistCascade) {
            if (RelationType.isRelationSingleValued(relType)) {
               if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                  NucleusLogger.PERSISTENCE.debug(Localiser.msg("007004", mmd.getFullFieldName()));
               }

               this.processPersistable(value, mmd);
            } else if (RelationType.isRelationMultiValued(relType)) {
               if (value instanceof Collection) {
                  if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                     NucleusLogger.PERSISTENCE.debug(Localiser.msg("007002", mmd.getFullFieldName()));
                  }

                  for(Object element : (Collection)value) {
                     if (api.isPersistable(element)) {
                        this.processPersistable(element, mmd);
                     }
                  }
               } else if (value instanceof Map) {
                  Map map = (Map)value;
                  if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                     NucleusLogger.PERSISTENCE.debug(Localiser.msg("007002", mmd.getFullFieldName()));
                  }

                  for(Object mapKey : map.keySet()) {
                     if (api.isPersistable(mapKey)) {
                        this.processPersistable(mapKey, mmd);
                     }
                  }

                  for(Object mapValue : map.values()) {
                     if (api.isPersistable(mapValue)) {
                        this.processPersistable(mapValue, mmd);
                     }
                  }
               } else if (value instanceof Object[]) {
                  if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                     NucleusLogger.PERSISTENCE.debug(Localiser.msg("007003", mmd.getFullFieldName()));
                  }

                  Object[] array = value;

                  for(int i = 0; i < array.length; ++i) {
                     Object element = array[i];
                     if (api.isPersistable(element)) {
                        this.processPersistable(element, mmd);
                     }
                  }
               }
            }
         }
      }

   }

   public void storeBooleanField(int fieldNumber, boolean value) {
   }

   public void storeByteField(int fieldNumber, byte value) {
   }

   public void storeCharField(int fieldNumber, char value) {
   }

   public void storeDoubleField(int fieldNumber, double value) {
   }

   public void storeFloatField(int fieldNumber, float value) {
   }

   public void storeIntField(int fieldNumber, int value) {
   }

   public void storeLongField(int fieldNumber, long value) {
   }

   public void storeShortField(int fieldNumber, short value) {
   }

   public void storeStringField(int fieldNumber, String value) {
   }
}
