package org.apache.hadoop.hive.metastore.model;

import java.util.BitSet;
import java.util.Map;
import javax.jdo.JDODetachedFieldAccessException;
import org.datanucleus.enhancement.Detachable;
import org.datanucleus.enhancement.ExecutionContextReference;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.enhancement.StateManager;
import org.datanucleus.enhancer.EnhancementHelper;

public class MIndex implements Detachable, Persistable {
   private String indexName;
   private MTable origTable;
   private int createTime;
   private int lastAccessTime;
   private Map parameters;
   private MTable indexTable;
   private MStorageDescriptor sd;
   private String indexHandlerClass;
   private boolean deferredRebuild;
   protected transient StateManager dnStateManager;
   protected transient byte dnFlags;
   protected Object[] dnDetachedState;
   private static final byte[] dnFieldFlags = __dnFieldFlagsInit();
   private static final Class dnPersistableSuperclass = __dnPersistableSuperclassInit();
   private static final Class[] dnFieldTypes = __dnFieldTypesInit();
   private static final String[] dnFieldNames = __dnFieldNamesInit();
   private static final int dnInheritedFieldCount = __dnGetInheritedFieldCount();

   public MIndex() {
   }

   public MIndex(String indexName, MTable baseTable, int createTime, int lastAccessTime, Map parameters, MTable indexTable, MStorageDescriptor sd, String indexHandlerClass, boolean deferredRebuild) {
      this.indexName = indexName;
      this.origTable = baseTable;
      this.createTime = createTime;
      this.lastAccessTime = lastAccessTime;
      this.parameters = parameters;
      this.indexTable = indexTable;
      this.sd = sd;
      this.indexHandlerClass = indexHandlerClass;
      this.deferredRebuild = deferredRebuild;
   }

   public String getIndexName() {
      return dnGetindexName(this);
   }

   public void setIndexName(String indexName) {
      dnSetindexName(this, indexName);
   }

   public int getCreateTime() {
      return dnGetcreateTime(this);
   }

   public void setCreateTime(int createTime) {
      dnSetcreateTime(this, createTime);
   }

   public int getLastAccessTime() {
      return dnGetlastAccessTime(this);
   }

   public void setLastAccessTime(int lastAccessTime) {
      dnSetlastAccessTime(this, lastAccessTime);
   }

   public Map getParameters() {
      return dnGetparameters(this);
   }

   public void setParameters(Map parameters) {
      dnSetparameters(this, parameters);
   }

   public MTable getOrigTable() {
      return dnGetorigTable(this);
   }

   public void setOrigTable(MTable origTable) {
      dnSetorigTable(this, origTable);
   }

   public MTable getIndexTable() {
      return dnGetindexTable(this);
   }

   public void setIndexTable(MTable indexTable) {
      dnSetindexTable(this, indexTable);
   }

   public MStorageDescriptor getSd() {
      return dnGetsd(this);
   }

   public void setSd(MStorageDescriptor sd) {
      dnSetsd(this, sd);
   }

   public String getIndexHandlerClass() {
      return dnGetindexHandlerClass(this);
   }

   public void setIndexHandlerClass(String indexHandlerClass) {
      dnSetindexHandlerClass(this, indexHandlerClass);
   }

   public boolean isDeferredRebuild() {
      return dnGetdeferredRebuild(this);
   }

   public boolean getDeferredRebuild() {
      return dnGetdeferredRebuild(this);
   }

   public void setDeferredRebuild(boolean deferredRebuild) {
      dnSetdeferredRebuild(this, deferredRebuild);
   }

   static {
      EnhancementHelper.registerClass(___dn$loadClass("org.apache.hadoop.hive.metastore.model.MIndex"), dnFieldNames, dnFieldTypes, dnFieldFlags, dnPersistableSuperclass, new MIndex());
   }

   public void dnCopyKeyFieldsFromObjectId(Persistable.ObjectIdFieldConsumer fc, Object oid) {
   }

   protected void dnCopyKeyFieldsFromObjectId(Object oid) {
   }

   public void dnCopyKeyFieldsToObjectId(Object oid) {
   }

   public void dnCopyKeyFieldsToObjectId(Persistable.ObjectIdFieldSupplier fs, Object oid) {
   }

   public final Object dnGetObjectId() {
      if (this.dnStateManager != null) {
         return this.dnStateManager.getObjectId(this);
      } else {
         return !this.dnIsDetached() ? null : this.dnDetachedState[0];
      }
   }

   public final Object dnGetVersion() {
      if (this.dnStateManager != null) {
         return this.dnStateManager.getVersion(this);
      } else {
         return !this.dnIsDetached() ? null : this.dnDetachedState[1];
      }
   }

   protected final void dnPreSerialize() {
      if (this.dnStateManager != null) {
         this.dnStateManager.preSerialize(this);
      }

   }

   public final ExecutionContextReference dnGetExecutionContext() {
      return this.dnStateManager != null ? this.dnStateManager.getExecutionContext(this) : null;
   }

   public final Object dnGetTransactionalObjectId() {
      return this.dnStateManager != null ? this.dnStateManager.getTransactionalObjectId(this) : null;
   }

   public final boolean dnIsDeleted() {
      return this.dnStateManager != null ? this.dnStateManager.isDeleted(this) : false;
   }

   public final boolean dnIsDirty() {
      if (this.dnStateManager != null) {
         return this.dnStateManager.isDirty(this);
      } else if (!this.dnIsDetached()) {
         return false;
      } else {
         return ((BitSet)this.dnDetachedState[3]).length() > 0;
      }
   }

   public final boolean dnIsNew() {
      return this.dnStateManager != null ? this.dnStateManager.isNew(this) : false;
   }

   public final boolean dnIsPersistent() {
      return this.dnStateManager != null ? this.dnStateManager.isPersistent(this) : false;
   }

   public final boolean dnIsTransactional() {
      return this.dnStateManager != null ? this.dnStateManager.isTransactional(this) : false;
   }

   public void dnMakeDirty(String fieldName) {
      if (this.dnStateManager != null) {
         this.dnStateManager.makeDirty(this, fieldName);
      }

      if (this.dnIsDetached() && fieldName != null) {
         String fldName = null;
         if (fieldName.indexOf(46) >= 0) {
            fldName = fieldName.substring(fieldName.lastIndexOf(46) + 1);
         } else {
            fldName = fieldName;
         }

         for(int i = 0; i < dnFieldNames.length; ++i) {
            if (dnFieldNames[i].equals(fldName)) {
               if (((BitSet)this.dnDetachedState[2]).get(i + dnInheritedFieldCount)) {
                  ((BitSet)this.dnDetachedState[3]).set(i + dnInheritedFieldCount);
                  return;
               }

               throw new JDODetachedFieldAccessException("You have just attempted to access a field/property that hasn't been detached. Please detach it first before performing this operation");
            }
         }
      }

   }

   public Object dnNewObjectIdInstance() {
      return null;
   }

   public Object dnNewObjectIdInstance(Object key) {
      return null;
   }

   public final void dnProvideFields(int[] indices) {
      if (indices == null) {
         throw new IllegalArgumentException("argment is null");
      } else {
         int i = indices.length - 1;
         if (i >= 0) {
            do {
               this.dnProvideField(indices[i]);
               --i;
            } while(i >= 0);
         }

      }
   }

   public final void dnReplaceFields(int[] indices) {
      if (indices == null) {
         throw new IllegalArgumentException("argument is null");
      } else {
         int i = indices.length;
         if (i > 0) {
            int j = 0;

            do {
               this.dnReplaceField(indices[j]);
               ++j;
            } while(j < i);
         }

      }
   }

   public final void dnReplaceFlags() {
      if (this.dnStateManager != null) {
         this.dnFlags = this.dnStateManager.replacingFlags(this);
      }

   }

   public final synchronized void dnReplaceStateManager(StateManager sm) {
      if (this.dnStateManager != null) {
         this.dnStateManager = this.dnStateManager.replacingStateManager(this, sm);
      } else {
         EnhancementHelper.checkAuthorizedStateManager(sm);
         this.dnStateManager = sm;
         this.dnFlags = 1;
      }

   }

   public final synchronized void dnReplaceDetachedState() {
      if (this.dnStateManager == null) {
         throw new IllegalStateException("state manager is null");
      } else {
         this.dnDetachedState = this.dnStateManager.replacingDetachedState(this, this.dnDetachedState);
      }
   }

   public boolean dnIsDetached() {
      return this.dnStateManager == null && this.dnDetachedState != null;
   }

   public Persistable dnNewInstance(StateManager sm) {
      MIndex result = new MIndex();
      result.dnFlags = 1;
      result.dnStateManager = sm;
      return result;
   }

   public Persistable dnNewInstance(StateManager sm, Object obj) {
      MIndex result = new MIndex();
      result.dnFlags = 1;
      result.dnStateManager = sm;
      result.dnCopyKeyFieldsFromObjectId(obj);
      return result;
   }

   public void dnReplaceField(int index) {
      if (this.dnStateManager == null) {
         throw new IllegalStateException("state manager is null");
      } else {
         switch (index) {
            case 0:
               this.createTime = this.dnStateManager.replacingIntField(this, index);
               break;
            case 1:
               this.deferredRebuild = this.dnStateManager.replacingBooleanField(this, index);
               break;
            case 2:
               this.indexHandlerClass = this.dnStateManager.replacingStringField(this, index);
               break;
            case 3:
               this.indexName = this.dnStateManager.replacingStringField(this, index);
               break;
            case 4:
               this.indexTable = (MTable)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 5:
               this.lastAccessTime = this.dnStateManager.replacingIntField(this, index);
               break;
            case 6:
               this.origTable = (MTable)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 7:
               this.parameters = (Map)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 8:
               this.sd = (MStorageDescriptor)this.dnStateManager.replacingObjectField(this, index);
               break;
            default:
               throw new IllegalArgumentException("out of field index :" + index);
         }

      }
   }

   public void dnProvideField(int index) {
      if (this.dnStateManager == null) {
         throw new IllegalStateException("state manager is null");
      } else {
         switch (index) {
            case 0:
               this.dnStateManager.providedIntField(this, index, this.createTime);
               break;
            case 1:
               this.dnStateManager.providedBooleanField(this, index, this.deferredRebuild);
               break;
            case 2:
               this.dnStateManager.providedStringField(this, index, this.indexHandlerClass);
               break;
            case 3:
               this.dnStateManager.providedStringField(this, index, this.indexName);
               break;
            case 4:
               this.dnStateManager.providedObjectField(this, index, this.indexTable);
               break;
            case 5:
               this.dnStateManager.providedIntField(this, index, this.lastAccessTime);
               break;
            case 6:
               this.dnStateManager.providedObjectField(this, index, this.origTable);
               break;
            case 7:
               this.dnStateManager.providedObjectField(this, index, this.parameters);
               break;
            case 8:
               this.dnStateManager.providedObjectField(this, index, this.sd);
               break;
            default:
               throw new IllegalArgumentException("out of field index :" + index);
         }

      }
   }

   protected final void dnCopyField(MIndex obj, int index) {
      switch (index) {
         case 0:
            this.createTime = obj.createTime;
            break;
         case 1:
            this.deferredRebuild = obj.deferredRebuild;
            break;
         case 2:
            this.indexHandlerClass = obj.indexHandlerClass;
            break;
         case 3:
            this.indexName = obj.indexName;
            break;
         case 4:
            this.indexTable = obj.indexTable;
            break;
         case 5:
            this.lastAccessTime = obj.lastAccessTime;
            break;
         case 6:
            this.origTable = obj.origTable;
            break;
         case 7:
            this.parameters = obj.parameters;
            break;
         case 8:
            this.sd = obj.sd;
            break;
         default:
            throw new IllegalArgumentException("out of field index :" + index);
      }

   }

   public void dnCopyFields(Object obj, int[] indices) {
      if (this.dnStateManager == null) {
         throw new IllegalStateException("state manager is null");
      } else if (indices == null) {
         throw new IllegalStateException("fieldNumbers is null");
      } else if (!(obj instanceof MIndex)) {
         throw new IllegalArgumentException("object is not an object of type org.apache.hadoop.hive.metastore.model.MIndex");
      } else {
         MIndex other = (MIndex)obj;
         if (this.dnStateManager != other.dnStateManager) {
            throw new IllegalArgumentException("state managers do not match");
         } else {
            int i = indices.length - 1;
            if (i >= 0) {
               do {
                  this.dnCopyField(other, indices[i]);
                  --i;
               } while(i >= 0);
            }

         }
      }
   }

   private static final String[] __dnFieldNamesInit() {
      return new String[]{"createTime", "deferredRebuild", "indexHandlerClass", "indexName", "indexTable", "lastAccessTime", "origTable", "parameters", "sd"};
   }

   private static final Class[] __dnFieldTypesInit() {
      return new Class[]{Integer.TYPE, Boolean.TYPE, ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.lang.String"), ___dn$loadClass("org.apache.hadoop.hive.metastore.model.MTable"), Integer.TYPE, ___dn$loadClass("org.apache.hadoop.hive.metastore.model.MTable"), ___dn$loadClass("java.util.Map"), ___dn$loadClass("org.apache.hadoop.hive.metastore.model.MStorageDescriptor")};
   }

   private static final byte[] __dnFieldFlagsInit() {
      return new byte[]{21, 21, 21, 21, 10, 21, 10, 10, 10};
   }

   protected static int __dnGetInheritedFieldCount() {
      return 0;
   }

   protected static int dnGetManagedFieldCount() {
      return 9;
   }

   private static Class __dnPersistableSuperclassInit() {
      return null;
   }

   public static Class ___dn$loadClass(String className) {
      try {
         return Class.forName(className);
      } catch (ClassNotFoundException e) {
         throw new NoClassDefFoundError(e.getMessage());
      }
   }

   private Object dnSuperClone() throws CloneNotSupportedException {
      MIndex o = (MIndex)super.clone();
      o.dnFlags = 0;
      o.dnStateManager = null;
      return o;
   }

   private static int dnGetcreateTime(MIndex objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 0)) {
         return objPC.dnStateManager.getIntField(objPC, 0, objPC.createTime);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(0)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"createTime\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.createTime;
      }
   }

   private static void dnSetcreateTime(MIndex objPC, int val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setIntField(objPC, 0, objPC.createTime, val);
      } else {
         objPC.createTime = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(0);
         }
      }

   }

   private static boolean dnGetdeferredRebuild(MIndex objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 1)) {
         return objPC.dnStateManager.getBooleanField(objPC, 1, objPC.deferredRebuild);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(1)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"deferredRebuild\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.deferredRebuild;
      }
   }

   private static void dnSetdeferredRebuild(MIndex objPC, boolean val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setBooleanField(objPC, 1, objPC.deferredRebuild, val);
      } else {
         objPC.deferredRebuild = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(1);
         }
      }

   }

   private static String dnGetindexHandlerClass(MIndex objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 2)) {
         return objPC.dnStateManager.getStringField(objPC, 2, objPC.indexHandlerClass);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(2)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"indexHandlerClass\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.indexHandlerClass;
      }
   }

   private static void dnSetindexHandlerClass(MIndex objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 2, objPC.indexHandlerClass, val);
      } else {
         objPC.indexHandlerClass = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(2);
         }
      }

   }

   private static String dnGetindexName(MIndex objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 3)) {
         return objPC.dnStateManager.getStringField(objPC, 3, objPC.indexName);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(3)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"indexName\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.indexName;
      }
   }

   private static void dnSetindexName(MIndex objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 3, objPC.indexName, val);
      } else {
         objPC.indexName = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(3);
         }
      }

   }

   private static MTable dnGetindexTable(MIndex objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 4)) {
         return (MTable)objPC.dnStateManager.getObjectField(objPC, 4, objPC.indexTable);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(4) && !((BitSet)objPC.dnDetachedState[3]).get(4)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"indexTable\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.indexTable;
      }
   }

   private static void dnSetindexTable(MIndex objPC, MTable val) {
      if (objPC.dnStateManager == null) {
         objPC.indexTable = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 4, objPC.indexTable, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(4);
      }

   }

   private static int dnGetlastAccessTime(MIndex objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 5)) {
         return objPC.dnStateManager.getIntField(objPC, 5, objPC.lastAccessTime);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(5)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"lastAccessTime\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.lastAccessTime;
      }
   }

   private static void dnSetlastAccessTime(MIndex objPC, int val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setIntField(objPC, 5, objPC.lastAccessTime, val);
      } else {
         objPC.lastAccessTime = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(5);
         }
      }

   }

   private static MTable dnGetorigTable(MIndex objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 6)) {
         return (MTable)objPC.dnStateManager.getObjectField(objPC, 6, objPC.origTable);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(6) && !((BitSet)objPC.dnDetachedState[3]).get(6)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"origTable\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.origTable;
      }
   }

   private static void dnSetorigTable(MIndex objPC, MTable val) {
      if (objPC.dnStateManager == null) {
         objPC.origTable = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 6, objPC.origTable, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(6);
      }

   }

   private static Map dnGetparameters(MIndex objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 7)) {
         return (Map)objPC.dnStateManager.getObjectField(objPC, 7, objPC.parameters);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(7) && !((BitSet)objPC.dnDetachedState[3]).get(7)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"parameters\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.parameters;
      }
   }

   private static void dnSetparameters(MIndex objPC, Map val) {
      if (objPC.dnStateManager == null) {
         objPC.parameters = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 7, objPC.parameters, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(7);
      }

   }

   private static MStorageDescriptor dnGetsd(MIndex objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 8)) {
         return (MStorageDescriptor)objPC.dnStateManager.getObjectField(objPC, 8, objPC.sd);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(8) && !((BitSet)objPC.dnDetachedState[3]).get(8)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"sd\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.sd;
      }
   }

   private static void dnSetsd(MIndex objPC, MStorageDescriptor val) {
      if (objPC.dnStateManager == null) {
         objPC.sd = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 8, objPC.sd, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(8);
      }

   }
}
