package org.apache.hadoop.hive.metastore.model;

import java.util.BitSet;
import javax.jdo.JDODetachedFieldAccessException;
import javax.jdo.JDOFatalInternalException;
import org.datanucleus.enhancement.Detachable;
import org.datanucleus.enhancement.ExecutionContextReference;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.enhancement.StateManager;
import org.datanucleus.enhancer.EnhancementHelper;
import org.datanucleus.identity.IntId;

public class MMasterKey implements Detachable, Persistable {
   private int keyId;
   private String masterKey;
   protected transient StateManager dnStateManager;
   protected transient byte dnFlags;
   protected Object[] dnDetachedState;
   private static final byte[] dnFieldFlags = __dnFieldFlagsInit();
   private static final Class dnPersistableSuperclass = __dnPersistableSuperclassInit();
   private static final Class[] dnFieldTypes = __dnFieldTypesInit();
   private static final String[] dnFieldNames = __dnFieldNamesInit();
   private static final int dnInheritedFieldCount = __dnGetInheritedFieldCount();

   public MMasterKey(int keyId, String masterKey) {
      this.keyId = keyId;
      this.masterKey = masterKey;
   }

   public MMasterKey(String masterKey) {
      this.masterKey = masterKey;
   }

   public MMasterKey(int keyId) {
      this.keyId = keyId;
   }

   public String getMasterKey() {
      return dnGetmasterKey(this);
   }

   public void setMasterKey(String masterKey) {
      dnSetmasterKey(this, masterKey);
   }

   public int getKeyId() {
      return dnGetkeyId(this);
   }

   public void setKeyId(int keyId) {
      dnSetkeyId(this, keyId);
   }

   static {
      EnhancementHelper.registerClass(___dn$loadClass("org.apache.hadoop.hive.metastore.model.MMasterKey"), dnFieldNames, dnFieldTypes, dnFieldFlags, dnPersistableSuperclass, new MMasterKey());
   }

   protected MMasterKey() {
   }

   public void dnCopyKeyFieldsFromObjectId(Persistable.ObjectIdFieldConsumer fc, Object oid) {
      if (fc == null) {
         throw new IllegalArgumentException("ObjectIdFieldConsumer is null");
      } else if (!(oid instanceof IntId)) {
         throw new ClassCastException("oid is not instanceof org.datanucleus.identity.IntId");
      } else {
         IntId o = (IntId)oid;
         fc.storeIntField(0, o.getKey());
      }
   }

   protected void dnCopyKeyFieldsFromObjectId(Object oid) {
      if (!(oid instanceof IntId)) {
         throw new ClassCastException("key class is not org.datanucleus.identity.IntId or null");
      } else {
         IntId o = (IntId)oid;
         this.keyId = o.getKey();
      }
   }

   public void dnCopyKeyFieldsToObjectId(Object oid) {
      throw new JDOFatalInternalException("It's illegal to call dnCopyKeyFieldsToObjectId for a class with single-field identity.");
   }

   public void dnCopyKeyFieldsToObjectId(Persistable.ObjectIdFieldSupplier fs, Object paramObject) {
      throw new JDOFatalInternalException("It's illegal to call dnCopyKeyFieldsToObjectId for a class with single-field identity.");
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
      return new IntId(this.getClass(), this.keyId);
   }

   public Object dnNewObjectIdInstance(Object key) {
      if (key == null) {
         throw new IllegalArgumentException("key is null");
      } else {
         return !(key instanceof String) ? new IntId(this.getClass(), (Integer)key) : new IntId(this.getClass(), (String)key);
      }
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
      MMasterKey result = new MMasterKey();
      result.dnFlags = 1;
      result.dnStateManager = sm;
      return result;
   }

   public Persistable dnNewInstance(StateManager sm, Object obj) {
      MMasterKey result = new MMasterKey();
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
               this.keyId = this.dnStateManager.replacingIntField(this, index);
               break;
            case 1:
               this.masterKey = this.dnStateManager.replacingStringField(this, index);
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
               this.dnStateManager.providedIntField(this, index, this.keyId);
               break;
            case 1:
               this.dnStateManager.providedStringField(this, index, this.masterKey);
               break;
            default:
               throw new IllegalArgumentException("out of field index :" + index);
         }

      }
   }

   protected final void dnCopyField(MMasterKey obj, int index) {
      switch (index) {
         case 0:
            this.keyId = obj.keyId;
            break;
         case 1:
            this.masterKey = obj.masterKey;
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
      } else if (!(obj instanceof MMasterKey)) {
         throw new IllegalArgumentException("object is not an object of type org.apache.hadoop.hive.metastore.model.MMasterKey");
      } else {
         MMasterKey other = (MMasterKey)obj;
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
      return new String[]{"keyId", "masterKey"};
   }

   private static final Class[] __dnFieldTypesInit() {
      return new Class[]{Integer.TYPE, ___dn$loadClass("java.lang.String")};
   }

   private static final byte[] __dnFieldFlagsInit() {
      return new byte[]{24, 21};
   }

   protected static int __dnGetInheritedFieldCount() {
      return 0;
   }

   protected static int dnGetManagedFieldCount() {
      return 2;
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
      MMasterKey o = (MMasterKey)super.clone();
      o.dnFlags = 0;
      o.dnStateManager = null;
      return o;
   }

   private static int dnGetkeyId(MMasterKey objPC) {
      return objPC.keyId;
   }

   private static void dnSetkeyId(MMasterKey objPC, int val) {
      if (objPC.dnStateManager == null) {
         objPC.keyId = val;
      } else {
         objPC.dnStateManager.setIntField(objPC, 0, objPC.keyId, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(0);
      }

   }

   private static String dnGetmasterKey(MMasterKey objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 1)) {
         return objPC.dnStateManager.getStringField(objPC, 1, objPC.masterKey);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(1)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"masterKey\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.masterKey;
      }
   }

   private static void dnSetmasterKey(MMasterKey objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 1, objPC.masterKey, val);
      } else {
         objPC.masterKey = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(1);
         }
      }

   }
}
