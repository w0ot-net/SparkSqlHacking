package org.apache.hadoop.hive.metastore.model;

import java.util.BitSet;
import javax.jdo.JDODetachedFieldAccessException;
import org.datanucleus.enhancement.Detachable;
import org.datanucleus.enhancement.ExecutionContextReference;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.enhancement.StateManager;
import org.datanucleus.enhancer.EnhancementHelper;

public class MRoleMap implements Detachable, Persistable {
   private String principalName;
   private String principalType;
   private MRole role;
   private int addTime;
   private String grantor;
   private String grantorType;
   private boolean grantOption;
   protected transient StateManager dnStateManager;
   protected transient byte dnFlags;
   protected Object[] dnDetachedState;
   private static final byte[] dnFieldFlags = __dnFieldFlagsInit();
   private static final Class dnPersistableSuperclass = __dnPersistableSuperclassInit();
   private static final Class[] dnFieldTypes = __dnFieldTypesInit();
   private static final String[] dnFieldNames = __dnFieldNamesInit();
   private static final int dnInheritedFieldCount = __dnGetInheritedFieldCount();

   public MRoleMap() {
   }

   public MRoleMap(String principalName, String principalType, MRole role, int addTime, String grantor, String grantorType, boolean grantOption) {
      this.principalName = principalName;
      this.principalType = principalType;
      this.role = role;
      this.addTime = addTime;
      this.grantor = grantor;
      this.grantorType = grantorType;
      this.grantOption = grantOption;
   }

   public String getPrincipalName() {
      return dnGetprincipalName(this);
   }

   public void setPrincipalName(String userName) {
      dnSetprincipalName(this, userName);
   }

   public String getPrincipalType() {
      return dnGetprincipalType(this);
   }

   public void setPrincipalType(String principalType) {
      dnSetprincipalType(this, principalType);
   }

   public int getAddTime() {
      return dnGetaddTime(this);
   }

   public void setAddTime(int addTime) {
      dnSetaddTime(this, addTime);
   }

   public MRole getRole() {
      return dnGetrole(this);
   }

   public void setRole(MRole role) {
      dnSetrole(this, role);
   }

   public boolean getGrantOption() {
      return dnGetgrantOption(this);
   }

   public void setGrantOption(boolean grantOption) {
      dnSetgrantOption(this, grantOption);
   }

   public String getGrantor() {
      return dnGetgrantor(this);
   }

   public void setGrantor(String grantor) {
      dnSetgrantor(this, grantor);
   }

   public String getGrantorType() {
      return dnGetgrantorType(this);
   }

   public void setGrantorType(String grantorType) {
      dnSetgrantorType(this, grantorType);
   }

   static {
      EnhancementHelper.registerClass(___dn$loadClass("org.apache.hadoop.hive.metastore.model.MRoleMap"), dnFieldNames, dnFieldTypes, dnFieldFlags, dnPersistableSuperclass, new MRoleMap());
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
      MRoleMap result = new MRoleMap();
      result.dnFlags = 1;
      result.dnStateManager = sm;
      return result;
   }

   public Persistable dnNewInstance(StateManager sm, Object obj) {
      MRoleMap result = new MRoleMap();
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
               this.addTime = this.dnStateManager.replacingIntField(this, index);
               break;
            case 1:
               this.grantOption = this.dnStateManager.replacingBooleanField(this, index);
               break;
            case 2:
               this.grantor = this.dnStateManager.replacingStringField(this, index);
               break;
            case 3:
               this.grantorType = this.dnStateManager.replacingStringField(this, index);
               break;
            case 4:
               this.principalName = this.dnStateManager.replacingStringField(this, index);
               break;
            case 5:
               this.principalType = this.dnStateManager.replacingStringField(this, index);
               break;
            case 6:
               this.role = (MRole)this.dnStateManager.replacingObjectField(this, index);
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
               this.dnStateManager.providedIntField(this, index, this.addTime);
               break;
            case 1:
               this.dnStateManager.providedBooleanField(this, index, this.grantOption);
               break;
            case 2:
               this.dnStateManager.providedStringField(this, index, this.grantor);
               break;
            case 3:
               this.dnStateManager.providedStringField(this, index, this.grantorType);
               break;
            case 4:
               this.dnStateManager.providedStringField(this, index, this.principalName);
               break;
            case 5:
               this.dnStateManager.providedStringField(this, index, this.principalType);
               break;
            case 6:
               this.dnStateManager.providedObjectField(this, index, this.role);
               break;
            default:
               throw new IllegalArgumentException("out of field index :" + index);
         }

      }
   }

   protected final void dnCopyField(MRoleMap obj, int index) {
      switch (index) {
         case 0:
            this.addTime = obj.addTime;
            break;
         case 1:
            this.grantOption = obj.grantOption;
            break;
         case 2:
            this.grantor = obj.grantor;
            break;
         case 3:
            this.grantorType = obj.grantorType;
            break;
         case 4:
            this.principalName = obj.principalName;
            break;
         case 5:
            this.principalType = obj.principalType;
            break;
         case 6:
            this.role = obj.role;
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
      } else if (!(obj instanceof MRoleMap)) {
         throw new IllegalArgumentException("object is not an object of type org.apache.hadoop.hive.metastore.model.MRoleMap");
      } else {
         MRoleMap other = (MRoleMap)obj;
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
      return new String[]{"addTime", "grantOption", "grantor", "grantorType", "principalName", "principalType", "role"};
   }

   private static final Class[] __dnFieldTypesInit() {
      return new Class[]{Integer.TYPE, Boolean.TYPE, ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.lang.String"), ___dn$loadClass("org.apache.hadoop.hive.metastore.model.MRole")};
   }

   private static final byte[] __dnFieldFlagsInit() {
      return new byte[]{21, 21, 21, 21, 21, 21, 10};
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
      MRoleMap o = (MRoleMap)super.clone();
      o.dnFlags = 0;
      o.dnStateManager = null;
      return o;
   }

   private static int dnGetaddTime(MRoleMap objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 0)) {
         return objPC.dnStateManager.getIntField(objPC, 0, objPC.addTime);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(0)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"addTime\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.addTime;
      }
   }

   private static void dnSetaddTime(MRoleMap objPC, int val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setIntField(objPC, 0, objPC.addTime, val);
      } else {
         objPC.addTime = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(0);
         }
      }

   }

   private static boolean dnGetgrantOption(MRoleMap objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 1)) {
         return objPC.dnStateManager.getBooleanField(objPC, 1, objPC.grantOption);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(1)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"grantOption\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.grantOption;
      }
   }

   private static void dnSetgrantOption(MRoleMap objPC, boolean val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setBooleanField(objPC, 1, objPC.grantOption, val);
      } else {
         objPC.grantOption = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(1);
         }
      }

   }

   private static String dnGetgrantor(MRoleMap objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 2)) {
         return objPC.dnStateManager.getStringField(objPC, 2, objPC.grantor);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(2)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"grantor\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.grantor;
      }
   }

   private static void dnSetgrantor(MRoleMap objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 2, objPC.grantor, val);
      } else {
         objPC.grantor = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(2);
         }
      }

   }

   private static String dnGetgrantorType(MRoleMap objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 3)) {
         return objPC.dnStateManager.getStringField(objPC, 3, objPC.grantorType);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(3)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"grantorType\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.grantorType;
      }
   }

   private static void dnSetgrantorType(MRoleMap objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 3, objPC.grantorType, val);
      } else {
         objPC.grantorType = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(3);
         }
      }

   }

   private static String dnGetprincipalName(MRoleMap objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 4)) {
         return objPC.dnStateManager.getStringField(objPC, 4, objPC.principalName);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(4)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"principalName\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.principalName;
      }
   }

   private static void dnSetprincipalName(MRoleMap objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 4, objPC.principalName, val);
      } else {
         objPC.principalName = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(4);
         }
      }

   }

   private static String dnGetprincipalType(MRoleMap objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 5)) {
         return objPC.dnStateManager.getStringField(objPC, 5, objPC.principalType);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(5)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"principalType\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.principalType;
      }
   }

   private static void dnSetprincipalType(MRoleMap objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 5, objPC.principalType, val);
      } else {
         objPC.principalType = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(5);
         }
      }

   }

   private static MRole dnGetrole(MRoleMap objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 6)) {
         return (MRole)objPC.dnStateManager.getObjectField(objPC, 6, objPC.role);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(6) && !((BitSet)objPC.dnDetachedState[3]).get(6)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"role\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.role;
      }
   }

   private static void dnSetrole(MRoleMap objPC, MRole val) {
      if (objPC.dnStateManager == null) {
         objPC.role = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 6, objPC.role, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(6);
      }

   }
}
