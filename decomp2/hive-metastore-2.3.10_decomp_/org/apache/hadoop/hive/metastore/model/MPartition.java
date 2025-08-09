package org.apache.hadoop.hive.metastore.model;

import java.util.BitSet;
import java.util.List;
import java.util.Map;
import javax.jdo.JDODetachedFieldAccessException;
import org.datanucleus.enhancement.Detachable;
import org.datanucleus.enhancement.ExecutionContextReference;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.enhancement.StateManager;
import org.datanucleus.enhancer.EnhancementHelper;

public class MPartition implements Detachable, Persistable {
   private String partitionName;
   private MTable table;
   private List values;
   private int createTime;
   private int lastAccessTime;
   private MStorageDescriptor sd;
   private Map parameters;
   protected transient StateManager dnStateManager;
   protected transient byte dnFlags;
   protected Object[] dnDetachedState;
   private static final byte[] dnFieldFlags = __dnFieldFlagsInit();
   private static final Class dnPersistableSuperclass = __dnPersistableSuperclassInit();
   private static final Class[] dnFieldTypes = __dnFieldTypesInit();
   private static final String[] dnFieldNames = __dnFieldNamesInit();
   private static final int dnInheritedFieldCount = __dnGetInheritedFieldCount();

   public MPartition() {
   }

   public MPartition(String partitionName, MTable table, List values, int createTime, int lastAccessTime, MStorageDescriptor sd, Map parameters) {
      this.partitionName = partitionName;
      this.table = table;
      this.values = values;
      this.createTime = createTime;
      this.lastAccessTime = lastAccessTime;
      this.sd = sd;
      this.parameters = parameters;
   }

   public int getLastAccessTime() {
      return dnGetlastAccessTime(this);
   }

   public void setLastAccessTime(int lastAccessTime) {
      dnSetlastAccessTime(this, lastAccessTime);
   }

   public List getValues() {
      return dnGetvalues(this);
   }

   public void setValues(List values) {
      dnSetvalues(this, values);
   }

   public MTable getTable() {
      return dnGettable(this);
   }

   public void setTable(MTable table) {
      dnSettable(this, table);
   }

   public MStorageDescriptor getSd() {
      return dnGetsd(this);
   }

   public void setSd(MStorageDescriptor sd) {
      dnSetsd(this, sd);
   }

   public Map getParameters() {
      return dnGetparameters(this);
   }

   public void setParameters(Map parameters) {
      dnSetparameters(this, parameters);
   }

   public String getPartitionName() {
      return dnGetpartitionName(this);
   }

   public void setPartitionName(String partitionName) {
      dnSetpartitionName(this, partitionName);
   }

   public int getCreateTime() {
      return dnGetcreateTime(this);
   }

   public void setCreateTime(int createTime) {
      dnSetcreateTime(this, createTime);
   }

   static {
      EnhancementHelper.registerClass(___dn$loadClass("org.apache.hadoop.hive.metastore.model.MPartition"), dnFieldNames, dnFieldTypes, dnFieldFlags, dnPersistableSuperclass, new MPartition());
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
      MPartition result = new MPartition();
      result.dnFlags = 1;
      result.dnStateManager = sm;
      return result;
   }

   public Persistable dnNewInstance(StateManager sm, Object obj) {
      MPartition result = new MPartition();
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
               this.lastAccessTime = this.dnStateManager.replacingIntField(this, index);
               break;
            case 2:
               this.parameters = (Map)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 3:
               this.partitionName = this.dnStateManager.replacingStringField(this, index);
               break;
            case 4:
               this.sd = (MStorageDescriptor)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 5:
               this.table = (MTable)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 6:
               this.values = (List)this.dnStateManager.replacingObjectField(this, index);
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
               this.dnStateManager.providedIntField(this, index, this.lastAccessTime);
               break;
            case 2:
               this.dnStateManager.providedObjectField(this, index, this.parameters);
               break;
            case 3:
               this.dnStateManager.providedStringField(this, index, this.partitionName);
               break;
            case 4:
               this.dnStateManager.providedObjectField(this, index, this.sd);
               break;
            case 5:
               this.dnStateManager.providedObjectField(this, index, this.table);
               break;
            case 6:
               this.dnStateManager.providedObjectField(this, index, this.values);
               break;
            default:
               throw new IllegalArgumentException("out of field index :" + index);
         }

      }
   }

   protected final void dnCopyField(MPartition obj, int index) {
      switch (index) {
         case 0:
            this.createTime = obj.createTime;
            break;
         case 1:
            this.lastAccessTime = obj.lastAccessTime;
            break;
         case 2:
            this.parameters = obj.parameters;
            break;
         case 3:
            this.partitionName = obj.partitionName;
            break;
         case 4:
            this.sd = obj.sd;
            break;
         case 5:
            this.table = obj.table;
            break;
         case 6:
            this.values = obj.values;
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
      } else if (!(obj instanceof MPartition)) {
         throw new IllegalArgumentException("object is not an object of type org.apache.hadoop.hive.metastore.model.MPartition");
      } else {
         MPartition other = (MPartition)obj;
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
      return new String[]{"createTime", "lastAccessTime", "parameters", "partitionName", "sd", "table", "values"};
   }

   private static final Class[] __dnFieldTypesInit() {
      return new Class[]{Integer.TYPE, Integer.TYPE, ___dn$loadClass("java.util.Map"), ___dn$loadClass("java.lang.String"), ___dn$loadClass("org.apache.hadoop.hive.metastore.model.MStorageDescriptor"), ___dn$loadClass("org.apache.hadoop.hive.metastore.model.MTable"), ___dn$loadClass("java.util.List")};
   }

   private static final byte[] __dnFieldFlagsInit() {
      return new byte[]{21, 21, 10, 21, 10, 10, 10};
   }

   protected static int __dnGetInheritedFieldCount() {
      return 0;
   }

   protected static int dnGetManagedFieldCount() {
      return 7;
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
      MPartition o = (MPartition)super.clone();
      o.dnFlags = 0;
      o.dnStateManager = null;
      return o;
   }

   private static int dnGetcreateTime(MPartition objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 0)) {
         return objPC.dnStateManager.getIntField(objPC, 0, objPC.createTime);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(0)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"createTime\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.createTime;
      }
   }

   private static void dnSetcreateTime(MPartition objPC, int val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setIntField(objPC, 0, objPC.createTime, val);
      } else {
         objPC.createTime = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(0);
         }
      }

   }

   private static int dnGetlastAccessTime(MPartition objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 1)) {
         return objPC.dnStateManager.getIntField(objPC, 1, objPC.lastAccessTime);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(1)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"lastAccessTime\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.lastAccessTime;
      }
   }

   private static void dnSetlastAccessTime(MPartition objPC, int val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setIntField(objPC, 1, objPC.lastAccessTime, val);
      } else {
         objPC.lastAccessTime = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(1);
         }
      }

   }

   private static Map dnGetparameters(MPartition objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 2)) {
         return (Map)objPC.dnStateManager.getObjectField(objPC, 2, objPC.parameters);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(2) && !((BitSet)objPC.dnDetachedState[3]).get(2)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"parameters\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.parameters;
      }
   }

   private static void dnSetparameters(MPartition objPC, Map val) {
      if (objPC.dnStateManager == null) {
         objPC.parameters = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 2, objPC.parameters, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(2);
      }

   }

   private static String dnGetpartitionName(MPartition objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 3)) {
         return objPC.dnStateManager.getStringField(objPC, 3, objPC.partitionName);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(3)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"partitionName\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.partitionName;
      }
   }

   private static void dnSetpartitionName(MPartition objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 3, objPC.partitionName, val);
      } else {
         objPC.partitionName = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(3);
         }
      }

   }

   private static MStorageDescriptor dnGetsd(MPartition objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 4)) {
         return (MStorageDescriptor)objPC.dnStateManager.getObjectField(objPC, 4, objPC.sd);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(4) && !((BitSet)objPC.dnDetachedState[3]).get(4)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"sd\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.sd;
      }
   }

   private static void dnSetsd(MPartition objPC, MStorageDescriptor val) {
      if (objPC.dnStateManager == null) {
         objPC.sd = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 4, objPC.sd, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(4);
      }

   }

   private static MTable dnGettable(MPartition objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 5)) {
         return (MTable)objPC.dnStateManager.getObjectField(objPC, 5, objPC.table);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(5) && !((BitSet)objPC.dnDetachedState[3]).get(5)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"table\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.table;
      }
   }

   private static void dnSettable(MPartition objPC, MTable val) {
      if (objPC.dnStateManager == null) {
         objPC.table = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 5, objPC.table, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(5);
      }

   }

   private static List dnGetvalues(MPartition objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 6)) {
         return (List)objPC.dnStateManager.getObjectField(objPC, 6, objPC.values);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(6) && !((BitSet)objPC.dnDetachedState[3]).get(6)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"values\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.values;
      }
   }

   private static void dnSetvalues(MPartition objPC, List val) {
      if (objPC.dnStateManager == null) {
         objPC.values = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 6, objPC.values, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(6);
      }

   }
}
