package org.apache.hadoop.hive.metastore.model;

import java.util.BitSet;
import javax.jdo.JDODetachedFieldAccessException;
import org.datanucleus.enhancement.Detachable;
import org.datanucleus.enhancement.ExecutionContextReference;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.enhancement.StateManager;
import org.datanucleus.enhancer.EnhancementHelper;

public class MPartitionEvent implements Detachable, Persistable {
   private String dbName;
   private String tblName;
   private String partName;
   private long eventTime;
   private int eventType;
   protected transient StateManager dnStateManager;
   protected transient byte dnFlags;
   protected Object[] dnDetachedState;
   private static final byte[] dnFieldFlags = __dnFieldFlagsInit();
   private static final Class dnPersistableSuperclass = __dnPersistableSuperclassInit();
   private static final Class[] dnFieldTypes = __dnFieldTypesInit();
   private static final String[] dnFieldNames = __dnFieldNamesInit();
   private static final int dnInheritedFieldCount = __dnGetInheritedFieldCount();

   public MPartitionEvent(String dbName, String tblName, String partitionName, int eventType) {
      this.dbName = dbName;
      this.tblName = tblName;
      this.partName = partitionName;
      this.eventType = eventType;
      this.eventTime = System.currentTimeMillis();
   }

   public MPartitionEvent() {
   }

   public void setDbName(String dbName) {
      dnSetdbName(this, dbName);
   }

   public void setTblName(String tblName) {
      dnSettblName(this, tblName);
   }

   public void setPartName(String partName) {
      dnSetpartName(this, partName);
   }

   public void setEventTime(long createTime) {
      dnSeteventTime(this, createTime);
   }

   public void setEventType(int eventType) {
      dnSeteventType(this, eventType);
   }

   public String toString() {
      return "MPartitionEvent [dbName=" + dnGetdbName(this) + ", tblName=" + dnGettblName(this) + ", partName=" + dnGetpartName(this) + ", eventTime=" + dnGeteventTime(this) + ", EventType=" + dnGeteventType(this) + "]";
   }

   static {
      EnhancementHelper.registerClass(___dn$loadClass("org.apache.hadoop.hive.metastore.model.MPartitionEvent"), dnFieldNames, dnFieldTypes, dnFieldFlags, dnPersistableSuperclass, new MPartitionEvent());
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
      MPartitionEvent result = new MPartitionEvent();
      result.dnFlags = 1;
      result.dnStateManager = sm;
      return result;
   }

   public Persistable dnNewInstance(StateManager sm, Object obj) {
      MPartitionEvent result = new MPartitionEvent();
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
               this.dbName = this.dnStateManager.replacingStringField(this, index);
               break;
            case 1:
               this.eventTime = this.dnStateManager.replacingLongField(this, index);
               break;
            case 2:
               this.eventType = this.dnStateManager.replacingIntField(this, index);
               break;
            case 3:
               this.partName = this.dnStateManager.replacingStringField(this, index);
               break;
            case 4:
               this.tblName = this.dnStateManager.replacingStringField(this, index);
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
               this.dnStateManager.providedStringField(this, index, this.dbName);
               break;
            case 1:
               this.dnStateManager.providedLongField(this, index, this.eventTime);
               break;
            case 2:
               this.dnStateManager.providedIntField(this, index, this.eventType);
               break;
            case 3:
               this.dnStateManager.providedStringField(this, index, this.partName);
               break;
            case 4:
               this.dnStateManager.providedStringField(this, index, this.tblName);
               break;
            default:
               throw new IllegalArgumentException("out of field index :" + index);
         }

      }
   }

   protected final void dnCopyField(MPartitionEvent obj, int index) {
      switch (index) {
         case 0:
            this.dbName = obj.dbName;
            break;
         case 1:
            this.eventTime = obj.eventTime;
            break;
         case 2:
            this.eventType = obj.eventType;
            break;
         case 3:
            this.partName = obj.partName;
            break;
         case 4:
            this.tblName = obj.tblName;
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
      } else if (!(obj instanceof MPartitionEvent)) {
         throw new IllegalArgumentException("object is not an object of type org.apache.hadoop.hive.metastore.model.MPartitionEvent");
      } else {
         MPartitionEvent other = (MPartitionEvent)obj;
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
      return new String[]{"dbName", "eventTime", "eventType", "partName", "tblName"};
   }

   private static final Class[] __dnFieldTypesInit() {
      return new Class[]{___dn$loadClass("java.lang.String"), Long.TYPE, Integer.TYPE, ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.lang.String")};
   }

   private static final byte[] __dnFieldFlagsInit() {
      return new byte[]{21, 21, 21, 21, 21};
   }

   protected static int __dnGetInheritedFieldCount() {
      return 0;
   }

   protected static int dnGetManagedFieldCount() {
      return 5;
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
      MPartitionEvent o = (MPartitionEvent)super.clone();
      o.dnFlags = 0;
      o.dnStateManager = null;
      return o;
   }

   private static String dnGetdbName(MPartitionEvent objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 0)) {
         return objPC.dnStateManager.getStringField(objPC, 0, objPC.dbName);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(0)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"dbName\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.dbName;
      }
   }

   private static void dnSetdbName(MPartitionEvent objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 0, objPC.dbName, val);
      } else {
         objPC.dbName = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(0);
         }
      }

   }

   private static long dnGeteventTime(MPartitionEvent objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 1)) {
         return objPC.dnStateManager.getLongField(objPC, 1, objPC.eventTime);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(1)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"eventTime\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.eventTime;
      }
   }

   private static void dnSeteventTime(MPartitionEvent objPC, long val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setLongField(objPC, 1, objPC.eventTime, val);
      } else {
         objPC.eventTime = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(1);
         }
      }

   }

   private static int dnGeteventType(MPartitionEvent objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 2)) {
         return objPC.dnStateManager.getIntField(objPC, 2, objPC.eventType);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(2)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"eventType\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.eventType;
      }
   }

   private static void dnSeteventType(MPartitionEvent objPC, int val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setIntField(objPC, 2, objPC.eventType, val);
      } else {
         objPC.eventType = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(2);
         }
      }

   }

   private static String dnGetpartName(MPartitionEvent objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 3)) {
         return objPC.dnStateManager.getStringField(objPC, 3, objPC.partName);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(3)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"partName\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.partName;
      }
   }

   private static void dnSetpartName(MPartitionEvent objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 3, objPC.partName, val);
      } else {
         objPC.partName = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(3);
         }
      }

   }

   private static String dnGettblName(MPartitionEvent objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 4)) {
         return objPC.dnStateManager.getStringField(objPC, 4, objPC.tblName);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(4)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"tblName\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.tblName;
      }
   }

   private static void dnSettblName(MPartitionEvent objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 4, objPC.tblName, val);
      } else {
         objPC.tblName = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(4);
         }
      }

   }
}
