package org.apache.hadoop.hive.metastore.model;

import java.util.BitSet;
import java.util.Map;
import javax.jdo.JDODetachedFieldAccessException;
import org.datanucleus.enhancement.Detachable;
import org.datanucleus.enhancement.ExecutionContextReference;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.enhancement.StateManager;
import org.datanucleus.enhancer.EnhancementHelper;

public class MDatabase implements Detachable, Persistable {
   private String name;
   private String locationUri;
   private String description;
   private Map parameters;
   private String ownerName;
   private String ownerType;
   protected transient StateManager dnStateManager;
   protected transient byte dnFlags;
   protected Object[] dnDetachedState;
   private static final byte[] dnFieldFlags = __dnFieldFlagsInit();
   private static final Class dnPersistableSuperclass = __dnPersistableSuperclassInit();
   private static final Class[] dnFieldTypes = __dnFieldTypesInit();
   private static final String[] dnFieldNames = __dnFieldNamesInit();
   private static final int dnInheritedFieldCount = __dnGetInheritedFieldCount();

   public MDatabase() {
   }

   public MDatabase(String name, String locationUri, String description, Map parameters) {
      this.name = name;
      this.locationUri = locationUri;
      this.description = description;
      this.parameters = parameters;
   }

   public String getName() {
      return dnGetname(this);
   }

   public void setName(String name) {
      dnSetname(this, name);
   }

   public String getLocationUri() {
      return dnGetlocationUri(this);
   }

   public void setLocationUri(String locationUri) {
      dnSetlocationUri(this, locationUri);
   }

   public String getDescription() {
      return dnGetdescription(this);
   }

   public void setDescription(String description) {
      dnSetdescription(this, description);
   }

   public Map getParameters() {
      return dnGetparameters(this);
   }

   public void setParameters(Map parameters) {
      dnSetparameters(this, parameters);
   }

   public String getOwnerName() {
      return dnGetownerName(this);
   }

   public void setOwnerName(String ownerName) {
      dnSetownerName(this, ownerName);
   }

   public String getOwnerType() {
      return dnGetownerType(this);
   }

   public void setOwnerType(String ownerType) {
      dnSetownerType(this, ownerType);
   }

   static {
      EnhancementHelper.registerClass(___dn$loadClass("org.apache.hadoop.hive.metastore.model.MDatabase"), dnFieldNames, dnFieldTypes, dnFieldFlags, dnPersistableSuperclass, new MDatabase());
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
      MDatabase result = new MDatabase();
      result.dnFlags = 1;
      result.dnStateManager = sm;
      return result;
   }

   public Persistable dnNewInstance(StateManager sm, Object obj) {
      MDatabase result = new MDatabase();
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
               this.description = this.dnStateManager.replacingStringField(this, index);
               break;
            case 1:
               this.locationUri = this.dnStateManager.replacingStringField(this, index);
               break;
            case 2:
               this.name = this.dnStateManager.replacingStringField(this, index);
               break;
            case 3:
               this.ownerName = this.dnStateManager.replacingStringField(this, index);
               break;
            case 4:
               this.ownerType = this.dnStateManager.replacingStringField(this, index);
               break;
            case 5:
               this.parameters = (Map)this.dnStateManager.replacingObjectField(this, index);
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
               this.dnStateManager.providedStringField(this, index, this.description);
               break;
            case 1:
               this.dnStateManager.providedStringField(this, index, this.locationUri);
               break;
            case 2:
               this.dnStateManager.providedStringField(this, index, this.name);
               break;
            case 3:
               this.dnStateManager.providedStringField(this, index, this.ownerName);
               break;
            case 4:
               this.dnStateManager.providedStringField(this, index, this.ownerType);
               break;
            case 5:
               this.dnStateManager.providedObjectField(this, index, this.parameters);
               break;
            default:
               throw new IllegalArgumentException("out of field index :" + index);
         }

      }
   }

   protected final void dnCopyField(MDatabase obj, int index) {
      switch (index) {
         case 0:
            this.description = obj.description;
            break;
         case 1:
            this.locationUri = obj.locationUri;
            break;
         case 2:
            this.name = obj.name;
            break;
         case 3:
            this.ownerName = obj.ownerName;
            break;
         case 4:
            this.ownerType = obj.ownerType;
            break;
         case 5:
            this.parameters = obj.parameters;
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
      } else if (!(obj instanceof MDatabase)) {
         throw new IllegalArgumentException("object is not an object of type org.apache.hadoop.hive.metastore.model.MDatabase");
      } else {
         MDatabase other = (MDatabase)obj;
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
      return new String[]{"description", "locationUri", "name", "ownerName", "ownerType", "parameters"};
   }

   private static final Class[] __dnFieldTypesInit() {
      return new Class[]{___dn$loadClass("java.lang.String"), ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.util.Map")};
   }

   private static final byte[] __dnFieldFlagsInit() {
      return new byte[]{21, 21, 21, 21, 21, 10};
   }

   protected static int __dnGetInheritedFieldCount() {
      return 0;
   }

   protected static int dnGetManagedFieldCount() {
      return 6;
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
      MDatabase o = (MDatabase)super.clone();
      o.dnFlags = 0;
      o.dnStateManager = null;
      return o;
   }

   private static String dnGetdescription(MDatabase objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 0)) {
         return objPC.dnStateManager.getStringField(objPC, 0, objPC.description);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(0)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"description\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.description;
      }
   }

   private static void dnSetdescription(MDatabase objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 0, objPC.description, val);
      } else {
         objPC.description = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(0);
         }
      }

   }

   private static String dnGetlocationUri(MDatabase objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 1)) {
         return objPC.dnStateManager.getStringField(objPC, 1, objPC.locationUri);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(1)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"locationUri\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.locationUri;
      }
   }

   private static void dnSetlocationUri(MDatabase objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 1, objPC.locationUri, val);
      } else {
         objPC.locationUri = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(1);
         }
      }

   }

   private static String dnGetname(MDatabase objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 2)) {
         return objPC.dnStateManager.getStringField(objPC, 2, objPC.name);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(2)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"name\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.name;
      }
   }

   private static void dnSetname(MDatabase objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 2, objPC.name, val);
      } else {
         objPC.name = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(2);
         }
      }

   }

   private static String dnGetownerName(MDatabase objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 3)) {
         return objPC.dnStateManager.getStringField(objPC, 3, objPC.ownerName);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(3)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"ownerName\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.ownerName;
      }
   }

   private static void dnSetownerName(MDatabase objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 3, objPC.ownerName, val);
      } else {
         objPC.ownerName = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(3);
         }
      }

   }

   private static String dnGetownerType(MDatabase objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 4)) {
         return objPC.dnStateManager.getStringField(objPC, 4, objPC.ownerType);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(4)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"ownerType\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.ownerType;
      }
   }

   private static void dnSetownerType(MDatabase objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 4, objPC.ownerType, val);
      } else {
         objPC.ownerType = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(4);
         }
      }

   }

   private static Map dnGetparameters(MDatabase objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 5)) {
         return (Map)objPC.dnStateManager.getObjectField(objPC, 5, objPC.parameters);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(5) && !((BitSet)objPC.dnDetachedState[3]).get(5)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"parameters\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.parameters;
      }
   }

   private static void dnSetparameters(MDatabase objPC, Map val) {
      if (objPC.dnStateManager == null) {
         objPC.parameters = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 5, objPC.parameters, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(5);
      }

   }
}
