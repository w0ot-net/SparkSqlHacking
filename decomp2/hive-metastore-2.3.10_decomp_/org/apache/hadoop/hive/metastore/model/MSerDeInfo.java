package org.apache.hadoop.hive.metastore.model;

import java.util.BitSet;
import java.util.Map;
import javax.jdo.JDODetachedFieldAccessException;
import org.datanucleus.enhancement.Detachable;
import org.datanucleus.enhancement.ExecutionContextReference;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.enhancement.StateManager;
import org.datanucleus.enhancer.EnhancementHelper;

public class MSerDeInfo implements Detachable, Persistable {
   private String name;
   private String serializationLib;
   private Map parameters;
   protected transient StateManager dnStateManager;
   protected transient byte dnFlags;
   protected Object[] dnDetachedState;
   private static final byte[] dnFieldFlags = __dnFieldFlagsInit();
   private static final Class dnPersistableSuperclass = __dnPersistableSuperclassInit();
   private static final Class[] dnFieldTypes = __dnFieldTypesInit();
   private static final String[] dnFieldNames = __dnFieldNamesInit();
   private static final int dnInheritedFieldCount = __dnGetInheritedFieldCount();

   public MSerDeInfo(String name, String serializationLib, Map parameters) {
      this.name = name;
      this.serializationLib = serializationLib;
      this.parameters = parameters;
   }

   public String getSerializationLib() {
      return dnGetserializationLib(this);
   }

   public void setSerializationLib(String serializationLib) {
      dnSetserializationLib(this, serializationLib);
   }

   public String getName() {
      return dnGetname(this);
   }

   public void setName(String name) {
      dnSetname(this, name);
   }

   public Map getParameters() {
      return dnGetparameters(this);
   }

   public void setParameters(Map parameters) {
      dnSetparameters(this, parameters);
   }

   static {
      EnhancementHelper.registerClass(___dn$loadClass("org.apache.hadoop.hive.metastore.model.MSerDeInfo"), dnFieldNames, dnFieldTypes, dnFieldFlags, dnPersistableSuperclass, new MSerDeInfo());
   }

   protected MSerDeInfo() {
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
      MSerDeInfo result = new MSerDeInfo();
      result.dnFlags = 1;
      result.dnStateManager = sm;
      return result;
   }

   public Persistable dnNewInstance(StateManager sm, Object obj) {
      MSerDeInfo result = new MSerDeInfo();
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
               this.name = this.dnStateManager.replacingStringField(this, index);
               break;
            case 1:
               this.parameters = (Map)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 2:
               this.serializationLib = this.dnStateManager.replacingStringField(this, index);
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
               this.dnStateManager.providedStringField(this, index, this.name);
               break;
            case 1:
               this.dnStateManager.providedObjectField(this, index, this.parameters);
               break;
            case 2:
               this.dnStateManager.providedStringField(this, index, this.serializationLib);
               break;
            default:
               throw new IllegalArgumentException("out of field index :" + index);
         }

      }
   }

   protected final void dnCopyField(MSerDeInfo obj, int index) {
      switch (index) {
         case 0:
            this.name = obj.name;
            break;
         case 1:
            this.parameters = obj.parameters;
            break;
         case 2:
            this.serializationLib = obj.serializationLib;
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
      } else if (!(obj instanceof MSerDeInfo)) {
         throw new IllegalArgumentException("object is not an object of type org.apache.hadoop.hive.metastore.model.MSerDeInfo");
      } else {
         MSerDeInfo other = (MSerDeInfo)obj;
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
      return new String[]{"name", "parameters", "serializationLib"};
   }

   private static final Class[] __dnFieldTypesInit() {
      return new Class[]{___dn$loadClass("java.lang.String"), ___dn$loadClass("java.util.Map"), ___dn$loadClass("java.lang.String")};
   }

   private static final byte[] __dnFieldFlagsInit() {
      return new byte[]{21, 10, 21};
   }

   protected static int __dnGetInheritedFieldCount() {
      return 0;
   }

   protected static int dnGetManagedFieldCount() {
      return 3;
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
      MSerDeInfo o = (MSerDeInfo)super.clone();
      o.dnFlags = 0;
      o.dnStateManager = null;
      return o;
   }

   private static String dnGetname(MSerDeInfo objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 0)) {
         return objPC.dnStateManager.getStringField(objPC, 0, objPC.name);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(0)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"name\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.name;
      }
   }

   private static void dnSetname(MSerDeInfo objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 0, objPC.name, val);
      } else {
         objPC.name = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(0);
         }
      }

   }

   private static Map dnGetparameters(MSerDeInfo objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 1)) {
         return (Map)objPC.dnStateManager.getObjectField(objPC, 1, objPC.parameters);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(1) && !((BitSet)objPC.dnDetachedState[3]).get(1)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"parameters\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.parameters;
      }
   }

   private static void dnSetparameters(MSerDeInfo objPC, Map val) {
      if (objPC.dnStateManager == null) {
         objPC.parameters = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 1, objPC.parameters, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(1);
      }

   }

   private static String dnGetserializationLib(MSerDeInfo objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 2)) {
         return objPC.dnStateManager.getStringField(objPC, 2, objPC.serializationLib);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(2)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"serializationLib\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.serializationLib;
      }
   }

   private static void dnSetserializationLib(MSerDeInfo objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 2, objPC.serializationLib, val);
      } else {
         objPC.serializationLib = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(2);
         }
      }

   }
}
