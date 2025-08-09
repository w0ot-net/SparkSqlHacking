package org.datanucleus.store.fieldmanager;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.types.SCO;
import org.datanucleus.store.types.SCOContainer;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class AttachFieldManager extends AbstractFieldManager {
   private final ObjectProvider attachedOP;
   private final boolean[] secondClassMutableFields;
   private final boolean[] dirtyFields;
   private final boolean persistent;
   private final boolean cascadeAttach;
   boolean copy = true;

   public AttachFieldManager(ObjectProvider attachedOP, boolean[] secondClassMutableFields, boolean[] dirtyFields, boolean persistent, boolean cascadeAttach, boolean copy) {
      this.attachedOP = attachedOP;
      this.secondClassMutableFields = secondClassMutableFields;
      this.dirtyFields = dirtyFields;
      this.persistent = persistent;
      this.cascadeAttach = cascadeAttach;
      this.copy = copy;
   }

   public void storeObjectField(int fieldNumber, Object value) {
      AbstractClassMetaData cmd = this.attachedOP.getClassMetaData();
      AbstractMemberMetaData mmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
      ExecutionContext ec = this.attachedOP.getExecutionContext();
      RelationType relationType = mmd.getRelationType(ec.getClassLoaderResolver());
      if (mmd.hasExtension("attach") && mmd.getValueForExtension("attach").equalsIgnoreCase("never")) {
         this.attachedOP.replaceFieldMakeDirty(fieldNumber, (Object)null);
      } else {
         ApiAdapter api = ec.getApiAdapter();
         if (value == null) {
            Object oldValue = null;
            if (mmd.isDependent() && this.persistent) {
               try {
                  this.attachedOP.loadFieldFromDatastore(fieldNumber);
               } catch (Exception var13) {
               }

               oldValue = this.attachedOP.provideField(fieldNumber);
            }

            this.attachedOP.replaceField(fieldNumber, (Object)null);
            if (this.dirtyFields[fieldNumber] || !this.persistent) {
               this.attachedOP.makeDirty(fieldNumber);
            }

            if (mmd.isDependent() && !mmd.isEmbedded() && oldValue != null && api.isPersistable(oldValue)) {
               this.attachedOP.flush();
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("026026", oldValue, mmd.getFullFieldName()));
               ec.deleteObjectInternal(oldValue);
            }
         } else if (this.secondClassMutableFields[fieldNumber]) {
            if (mmd.isSerialized() && !RelationType.isRelationMultiValued(relationType)) {
               this.attachedOP.replaceFieldMakeDirty(fieldNumber, value);
               this.attachedOP.makeDirty(fieldNumber);
            } else {
               Object oldValue = null;
               if (this.persistent && !this.attachedOP.isFieldLoaded(fieldNumber)) {
                  this.attachedOP.loadField(fieldNumber);
               }

               oldValue = this.attachedOP.provideField(fieldNumber);
               boolean changed = this.dirtyFields[fieldNumber];
               if (!changed) {
                  if (oldValue == null) {
                     changed = true;
                  } else if (mmd.hasCollection() && relationType != RelationType.NONE) {
                     boolean collsEqual = SCOUtils.collectionsAreEqual(api, (Collection)oldValue, (Collection)value);
                     changed = !collsEqual;
                  } else {
                     changed = !oldValue.equals(value);
                  }
               }

               SCO sco;
               if (oldValue != null && oldValue instanceof SCO) {
                  sco = (SCO)oldValue;
               } else {
                  if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                     NucleusLogger.PERSISTENCE.debug(Localiser.msg("026029", StringUtils.toJVMIDString(this.attachedOP.getObject()), this.attachedOP.getInternalObjectId(), mmd.getName()));
                  }

                  sco = SCOUtils.newSCOInstance(this.attachedOP, mmd, (Class)null, (Object)null, false);
                  if (sco instanceof SCOContainer) {
                     ((SCOContainer)sco).load();
                  }

                  this.attachedOP.replaceFieldMakeDirty(fieldNumber, sco);
               }

               if (this.cascadeAttach) {
                  if (this.copy) {
                     sco.attachCopy(value);
                  } else if (sco instanceof Collection) {
                     SCOUtils.attachForCollection(this.attachedOP, ((Collection)value).toArray(), SCOUtils.collectionHasElementsWithoutIdentity(mmd));
                  } else if (sco instanceof Map) {
                     SCOUtils.attachForMap(this.attachedOP, ((Map)value).entrySet(), SCOUtils.mapHasKeysWithoutIdentity(mmd), SCOUtils.mapHasValuesWithoutIdentity(mmd));
                  } else {
                     sco.initialise(value);
                  }
               }

               if (changed || !this.persistent) {
                  this.attachedOP.makeDirty(fieldNumber);
               }
            }
         } else if (mmd.getType().isArray() && RelationType.isRelationMultiValued(relationType)) {
            if (!mmd.isSerialized() && !mmd.isEmbedded()) {
               Object oldValue = this.attachedOP.provideField(fieldNumber);
               if (oldValue == null && !this.attachedOP.getLoadedFields()[fieldNumber] && this.persistent) {
                  this.attachedOP.loadField(fieldNumber);
                  this.attachedOP.provideField(fieldNumber);
               }

               if (this.cascadeAttach) {
                  Object arr = Array.newInstance(mmd.getType().getComponentType(), Array.getLength(value));

                  for(int i = 0; i < Array.getLength(value); ++i) {
                     Object elem = Array.get(value, i);
                     if (this.copy) {
                        Object elemAttached = ec.attachObjectCopy(this.attachedOP, elem, false);
                        Array.set(arr, i, elemAttached);
                     } else {
                        ec.attachObject(this.attachedOP, elem, false);
                        Array.set(arr, i, elem);
                     }
                  }

                  this.attachedOP.replaceFieldMakeDirty(fieldNumber, arr);
               }

               if (this.dirtyFields[fieldNumber] || !this.persistent) {
                  this.attachedOP.makeDirty(fieldNumber);
               }
            } else {
               this.attachedOP.replaceField(fieldNumber, value);
               if (this.dirtyFields[fieldNumber] || !this.persistent) {
                  this.attachedOP.makeDirty(fieldNumber);
               }
            }
         } else if (RelationType.isRelationSingleValued(relationType)) {
            ObjectProvider valueSM = ec.findObjectProvider(value);
            if (valueSM != null && valueSM.getReferencedPC() != null && !api.isPersistent(value)) {
               if (this.dirtyFields[fieldNumber]) {
                  this.attachedOP.replaceFieldMakeDirty(fieldNumber, valueSM.getReferencedPC());
               } else {
                  this.attachedOP.replaceField(fieldNumber, valueSM.getReferencedPC());
               }
            }

            if (this.cascadeAttach) {
               boolean sco = mmd.getEmbeddedMetaData() != null || mmd.isSerialized() || mmd.isEmbedded();
               if (this.copy) {
                  value = ec.attachObjectCopy(this.attachedOP, value, sco);
                  if (!sco && !this.dirtyFields[fieldNumber]) {
                     this.attachedOP.replaceField(fieldNumber, value);
                  } else {
                     this.attachedOP.replaceFieldMakeDirty(fieldNumber, value);
                  }
               } else {
                  ec.attachObject(this.attachedOP, value, sco);
               }

               if (!this.dirtyFields[fieldNumber] && this.persistent) {
                  if (sco && value != null && api.isDirty(value)) {
                     this.attachedOP.makeDirty(fieldNumber);
                  }
               } else {
                  this.attachedOP.makeDirty(fieldNumber);
               }
            } else if (this.dirtyFields[fieldNumber] || !this.persistent) {
               this.attachedOP.makeDirty(fieldNumber);
            }
         } else {
            this.attachedOP.replaceField(fieldNumber, value);
            if (this.dirtyFields[fieldNumber] || !this.persistent) {
               this.attachedOP.makeDirty(fieldNumber);
            }
         }

      }
   }

   public void storeBooleanField(int fieldNumber, boolean value) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      sfv.storeBooleanField(fieldNumber, value);
      this.attachedOP.replaceFields(new int[]{fieldNumber}, sfv);
      if (this.dirtyFields[fieldNumber] || !this.persistent) {
         this.attachedOP.makeDirty(fieldNumber);
      }

   }

   public void storeByteField(int fieldNumber, byte value) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      sfv.storeByteField(fieldNumber, value);
      this.attachedOP.replaceFields(new int[]{fieldNumber}, sfv);
      if (this.dirtyFields[fieldNumber] || !this.persistent) {
         this.attachedOP.makeDirty(fieldNumber);
      }

   }

   public void storeCharField(int fieldNumber, char value) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      sfv.storeCharField(fieldNumber, value);
      this.attachedOP.replaceFields(new int[]{fieldNumber}, sfv);
      if (this.dirtyFields[fieldNumber] || !this.persistent) {
         this.attachedOP.makeDirty(fieldNumber);
      }

   }

   public void storeDoubleField(int fieldNumber, double value) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      sfv.storeDoubleField(fieldNumber, value);
      this.attachedOP.replaceFields(new int[]{fieldNumber}, sfv);
      if (this.dirtyFields[fieldNumber] || !this.persistent) {
         this.attachedOP.makeDirty(fieldNumber);
      }

   }

   public void storeFloatField(int fieldNumber, float value) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      sfv.storeFloatField(fieldNumber, value);
      this.attachedOP.replaceFields(new int[]{fieldNumber}, sfv);
      if (this.dirtyFields[fieldNumber] || !this.persistent) {
         this.attachedOP.makeDirty(fieldNumber);
      }

   }

   public void storeIntField(int fieldNumber, int value) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      sfv.storeIntField(fieldNumber, value);
      this.attachedOP.replaceFields(new int[]{fieldNumber}, sfv);
      if (this.dirtyFields[fieldNumber] || !this.persistent) {
         this.attachedOP.makeDirty(fieldNumber);
      }

   }

   public void storeLongField(int fieldNumber, long value) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      sfv.storeLongField(fieldNumber, value);
      this.attachedOP.replaceFields(new int[]{fieldNumber}, sfv);
      if (this.dirtyFields[fieldNumber] || !this.persistent) {
         this.attachedOP.makeDirty(fieldNumber);
      }

   }

   public void storeShortField(int fieldNumber, short value) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      sfv.storeShortField(fieldNumber, value);
      this.attachedOP.replaceFields(new int[]{fieldNumber}, sfv);
      if (this.dirtyFields[fieldNumber] || !this.persistent) {
         this.attachedOP.makeDirty(fieldNumber);
      }

   }

   public void storeStringField(int fieldNumber, String value) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      sfv.storeStringField(fieldNumber, value);
      this.attachedOP.replaceFields(new int[]{fieldNumber}, sfv);
      if (this.dirtyFields[fieldNumber] || !this.persistent) {
         this.attachedOP.makeDirty(fieldNumber);
      }

   }
}
