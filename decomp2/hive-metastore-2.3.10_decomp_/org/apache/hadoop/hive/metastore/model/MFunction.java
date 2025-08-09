package org.apache.hadoop.hive.metastore.model;

import java.util.BitSet;
import java.util.List;
import javax.jdo.JDODetachedFieldAccessException;
import org.datanucleus.enhancement.Detachable;
import org.datanucleus.enhancement.ExecutionContextReference;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.enhancement.StateManager;
import org.datanucleus.enhancer.EnhancementHelper;

public class MFunction implements Detachable, Persistable {
   private String functionName;
   private MDatabase database;
   private String className;
   private String ownerName;
   private String ownerType;
   private int createTime;
   private int functionType;
   private List resourceUris;
   protected transient StateManager dnStateManager;
   protected transient byte dnFlags;
   protected Object[] dnDetachedState;
   private static final byte[] dnFieldFlags = __dnFieldFlagsInit();
   private static final Class dnPersistableSuperclass = __dnPersistableSuperclassInit();
   private static final Class[] dnFieldTypes = __dnFieldTypesInit();
   private static final String[] dnFieldNames = __dnFieldNamesInit();
   private static final int dnInheritedFieldCount = __dnGetInheritedFieldCount();

   public MFunction() {
   }

   public MFunction(String functionName, MDatabase database, String className, String ownerName, String ownerType, int createTime, int functionType, List resourceUris) {
      this.setFunctionName(functionName);
      this.setDatabase(database);
      this.setFunctionType(functionType);
      this.setClassName(className);
      this.setOwnerName(ownerName);
      this.setOwnerType(ownerType);
      this.setCreateTime(createTime);
      this.setResourceUris(resourceUris);
   }

   public String getFunctionName() {
      return dnGetfunctionName(this);
   }

   public void setFunctionName(String functionName) {
      dnSetfunctionName(this, functionName);
   }

   public MDatabase getDatabase() {
      return dnGetdatabase(this);
   }

   public void setDatabase(MDatabase database) {
      dnSetdatabase(this, database);
   }

   public String getClassName() {
      return dnGetclassName(this);
   }

   public void setClassName(String className) {
      dnSetclassName(this, className);
   }

   public String getOwnerName() {
      return dnGetownerName(this);
   }

   public void setOwnerName(String owner) {
      dnSetownerName(this, owner);
   }

   public String getOwnerType() {
      return dnGetownerType(this);
   }

   public void setOwnerType(String ownerType) {
      dnSetownerType(this, ownerType);
   }

   public int getCreateTime() {
      return dnGetcreateTime(this);
   }

   public void setCreateTime(int createTime) {
      dnSetcreateTime(this, createTime);
   }

   public int getFunctionType() {
      return dnGetfunctionType(this);
   }

   public void setFunctionType(int functionType) {
      dnSetfunctionType(this, functionType);
   }

   public List getResourceUris() {
      return dnGetresourceUris(this);
   }

   public void setResourceUris(List resourceUris) {
      dnSetresourceUris(this, resourceUris);
   }

   static {
      EnhancementHelper.registerClass(___dn$loadClass("org.apache.hadoop.hive.metastore.model.MFunction"), dnFieldNames, dnFieldTypes, dnFieldFlags, dnPersistableSuperclass, new MFunction());
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
      MFunction result = new MFunction();
      result.dnFlags = 1;
      result.dnStateManager = sm;
      return result;
   }

   public Persistable dnNewInstance(StateManager sm, Object obj) {
      MFunction result = new MFunction();
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
               this.className = this.dnStateManager.replacingStringField(this, index);
               break;
            case 1:
               this.createTime = this.dnStateManager.replacingIntField(this, index);
               break;
            case 2:
               this.database = (MDatabase)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 3:
               this.functionName = this.dnStateManager.replacingStringField(this, index);
               break;
            case 4:
               this.functionType = this.dnStateManager.replacingIntField(this, index);
               break;
            case 5:
               this.ownerName = this.dnStateManager.replacingStringField(this, index);
               break;
            case 6:
               this.ownerType = this.dnStateManager.replacingStringField(this, index);
               break;
            case 7:
               this.resourceUris = (List)this.dnStateManager.replacingObjectField(this, index);
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
               this.dnStateManager.providedStringField(this, index, this.className);
               break;
            case 1:
               this.dnStateManager.providedIntField(this, index, this.createTime);
               break;
            case 2:
               this.dnStateManager.providedObjectField(this, index, this.database);
               break;
            case 3:
               this.dnStateManager.providedStringField(this, index, this.functionName);
               break;
            case 4:
               this.dnStateManager.providedIntField(this, index, this.functionType);
               break;
            case 5:
               this.dnStateManager.providedStringField(this, index, this.ownerName);
               break;
            case 6:
               this.dnStateManager.providedStringField(this, index, this.ownerType);
               break;
            case 7:
               this.dnStateManager.providedObjectField(this, index, this.resourceUris);
               break;
            default:
               throw new IllegalArgumentException("out of field index :" + index);
         }

      }
   }

   protected final void dnCopyField(MFunction obj, int index) {
      switch (index) {
         case 0:
            this.className = obj.className;
            break;
         case 1:
            this.createTime = obj.createTime;
            break;
         case 2:
            this.database = obj.database;
            break;
         case 3:
            this.functionName = obj.functionName;
            break;
         case 4:
            this.functionType = obj.functionType;
            break;
         case 5:
            this.ownerName = obj.ownerName;
            break;
         case 6:
            this.ownerType = obj.ownerType;
            break;
         case 7:
            this.resourceUris = obj.resourceUris;
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
      } else if (!(obj instanceof MFunction)) {
         throw new IllegalArgumentException("object is not an object of type org.apache.hadoop.hive.metastore.model.MFunction");
      } else {
         MFunction other = (MFunction)obj;
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
      return new String[]{"className", "createTime", "database", "functionName", "functionType", "ownerName", "ownerType", "resourceUris"};
   }

   private static final Class[] __dnFieldTypesInit() {
      return new Class[]{___dn$loadClass("java.lang.String"), Integer.TYPE, ___dn$loadClass("org.apache.hadoop.hive.metastore.model.MDatabase"), ___dn$loadClass("java.lang.String"), Integer.TYPE, ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.util.List")};
   }

   private static final byte[] __dnFieldFlagsInit() {
      return new byte[]{21, 21, 10, 21, 21, 21, 21, 10};
   }

   protected static int __dnGetInheritedFieldCount() {
      return 0;
   }

   protected static int dnGetManagedFieldCount() {
      return 8;
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
      MFunction o = (MFunction)super.clone();
      o.dnFlags = 0;
      o.dnStateManager = null;
      return o;
   }

   private static String dnGetclassName(MFunction objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 0)) {
         return objPC.dnStateManager.getStringField(objPC, 0, objPC.className);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(0)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"className\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.className;
      }
   }

   private static void dnSetclassName(MFunction objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 0, objPC.className, val);
      } else {
         objPC.className = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(0);
         }
      }

   }

   private static int dnGetcreateTime(MFunction objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 1)) {
         return objPC.dnStateManager.getIntField(objPC, 1, objPC.createTime);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(1)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"createTime\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.createTime;
      }
   }

   private static void dnSetcreateTime(MFunction objPC, int val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setIntField(objPC, 1, objPC.createTime, val);
      } else {
         objPC.createTime = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(1);
         }
      }

   }

   private static MDatabase dnGetdatabase(MFunction objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 2)) {
         return (MDatabase)objPC.dnStateManager.getObjectField(objPC, 2, objPC.database);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(2) && !((BitSet)objPC.dnDetachedState[3]).get(2)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"database\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.database;
      }
   }

   private static void dnSetdatabase(MFunction objPC, MDatabase val) {
      if (objPC.dnStateManager == null) {
         objPC.database = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 2, objPC.database, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(2);
      }

   }

   private static String dnGetfunctionName(MFunction objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 3)) {
         return objPC.dnStateManager.getStringField(objPC, 3, objPC.functionName);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(3)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"functionName\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.functionName;
      }
   }

   private static void dnSetfunctionName(MFunction objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 3, objPC.functionName, val);
      } else {
         objPC.functionName = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(3);
         }
      }

   }

   private static int dnGetfunctionType(MFunction objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 4)) {
         return objPC.dnStateManager.getIntField(objPC, 4, objPC.functionType);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(4)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"functionType\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.functionType;
      }
   }

   private static void dnSetfunctionType(MFunction objPC, int val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setIntField(objPC, 4, objPC.functionType, val);
      } else {
         objPC.functionType = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(4);
         }
      }

   }

   private static String dnGetownerName(MFunction objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 5)) {
         return objPC.dnStateManager.getStringField(objPC, 5, objPC.ownerName);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(5)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"ownerName\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.ownerName;
      }
   }

   private static void dnSetownerName(MFunction objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 5, objPC.ownerName, val);
      } else {
         objPC.ownerName = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(5);
         }
      }

   }

   private static String dnGetownerType(MFunction objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 6)) {
         return objPC.dnStateManager.getStringField(objPC, 6, objPC.ownerType);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(6)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"ownerType\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.ownerType;
      }
   }

   private static void dnSetownerType(MFunction objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 6, objPC.ownerType, val);
      } else {
         objPC.ownerType = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(6);
         }
      }

   }

   private static List dnGetresourceUris(MFunction objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 7)) {
         return (List)objPC.dnStateManager.getObjectField(objPC, 7, objPC.resourceUris);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(7) && !((BitSet)objPC.dnDetachedState[3]).get(7)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"resourceUris\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.resourceUris;
      }
   }

   private static void dnSetresourceUris(MFunction objPC, List val) {
      if (objPC.dnStateManager == null) {
         objPC.resourceUris = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 7, objPC.resourceUris, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(7);
      }

   }
}
