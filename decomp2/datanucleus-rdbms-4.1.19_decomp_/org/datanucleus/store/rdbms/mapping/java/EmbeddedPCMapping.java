package org.datanucleus.store.rdbms.mapping.java;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.EmbeddedMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.mapping.MappingCallbacks;
import org.datanucleus.store.rdbms.table.Table;

public class EmbeddedPCMapping extends EmbeddedMapping implements MappingCallbacks {
   public void initialize(AbstractMemberMetaData mmd, Table table, ClassLoaderResolver clr) {
      this.initialize(mmd, table, clr, mmd.getEmbeddedMetaData(), mmd.getTypeName(), 1);
   }

   public void insertPostProcessing(ObjectProvider op) {
   }

   public void postFetch(ObjectProvider op) {
      if (this.mmd.getAbsoluteFieldNumber() >= 0) {
         ObjectProvider thisOP = this.getObjectProviderForEmbeddedObject(op);
         if (thisOP != null) {
            for(int i = 0; i < this.getNumberOfJavaTypeMappings(); ++i) {
               JavaTypeMapping m = this.getJavaTypeMapping(i);
               if (m instanceof MappingCallbacks) {
                  ((MappingCallbacks)m).postFetch(thisOP);
               }
            }

         }
      }
   }

   public void postInsert(ObjectProvider op) {
      if (this.mmd.getAbsoluteFieldNumber() >= 0) {
         ObjectProvider thisOP = this.getObjectProviderForEmbeddedObject(op);
         if (thisOP != null) {
            for(int i = 0; i < this.getNumberOfJavaTypeMappings(); ++i) {
               JavaTypeMapping m = this.getJavaTypeMapping(i);
               if (m instanceof MappingCallbacks) {
                  ((MappingCallbacks)m).postInsert(thisOP);
               }
            }

         }
      }
   }

   public void postUpdate(ObjectProvider op) {
      if (this.mmd.getAbsoluteFieldNumber() >= 0) {
         ObjectProvider thisOP = this.getObjectProviderForEmbeddedObject(op);
         if (thisOP != null) {
            for(int i = 0; i < this.getNumberOfJavaTypeMappings(); ++i) {
               JavaTypeMapping m = this.getJavaTypeMapping(i);
               if (m instanceof MappingCallbacks) {
                  ((MappingCallbacks)m).postUpdate(thisOP);
               }
            }

         }
      }
   }

   public void preDelete(ObjectProvider op) {
      if (this.mmd.getAbsoluteFieldNumber() >= 0) {
         ObjectProvider thisOP = this.getObjectProviderForEmbeddedObject(op);
         if (thisOP != null) {
            for(int i = 0; i < this.getNumberOfJavaTypeMappings(); ++i) {
               JavaTypeMapping m = this.getJavaTypeMapping(i);
               if (m instanceof MappingCallbacks) {
                  ((MappingCallbacks)m).preDelete(thisOP);
               }
            }

         }
      }
   }

   private ObjectProvider getObjectProviderForEmbeddedObject(ObjectProvider ownerOP) {
      ExecutionContext ec = ownerOP.getExecutionContext();
      AbstractMemberMetaData theMmd = this.mmd;
      if (this.mmd.getParent() instanceof EmbeddedMetaData) {
         AbstractClassMetaData cmd = ec.getMetaDataManager().getMetaDataForClass(this.mmd.getClassName(), this.clr);
         theMmd = cmd.getMetaDataForMember(this.mmd.getName());
      }

      Object value = ownerOP.provideField(theMmd.getAbsoluteFieldNumber());
      if (value == null) {
         return null;
      } else {
         ObjectProvider thisOP = ec.findObjectProvider(value);
         if (thisOP == null) {
            thisOP = ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, value, false, ownerOP, theMmd.getAbsoluteFieldNumber());
            thisOP.setPcObjectType(this.objectType);
         }

         return thisOP;
      }
   }
}
