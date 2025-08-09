package org.apache.hadoop.hive.metastore.model;

import java.io.Serializable;
import java.util.BitSet;
import javax.jdo.JDODetachedFieldAccessException;
import org.datanucleus.enhancement.Detachable;
import org.datanucleus.enhancement.ExecutionContextReference;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.enhancement.StateManager;
import org.datanucleus.enhancer.EnhancementHelper;

public class MConstraint implements Detachable, Persistable {
   String constraintName;
   int constraintType;
   int position;
   Integer deleteRule;
   Integer updateRule;
   MTable parentTable;
   MTable childTable;
   MColumnDescriptor parentColumn;
   MColumnDescriptor childColumn;
   Integer childIntegerIndex;
   Integer parentIntegerIndex;
   int enableValidateRely;
   public static final int PRIMARY_KEY_CONSTRAINT = 0;
   public static final int FOREIGN_KEY_CONSTRAINT = 1;
   protected transient StateManager dnStateManager;
   protected transient byte dnFlags;
   protected Object[] dnDetachedState;
   private static final byte[] dnFieldFlags = __dnFieldFlagsInit();
   private static final Class dnPersistableSuperclass = __dnPersistableSuperclassInit();
   private static final Class[] dnFieldTypes = __dnFieldTypesInit();
   private static final String[] dnFieldNames = __dnFieldNamesInit();
   private static final int dnInheritedFieldCount = __dnGetInheritedFieldCount();

   public MConstraint() {
   }

   public MConstraint(String constraintName, int constraintType, int position, Integer deleteRule, Integer updateRule, int enableRelyValidate, MTable parentTable, MTable childTable, MColumnDescriptor parentColumn, MColumnDescriptor childColumn, Integer childIntegerIndex, Integer parentIntegerIndex) {
      this.constraintName = constraintName;
      this.constraintType = constraintType;
      this.parentTable = parentTable;
      this.childTable = childTable;
      this.parentColumn = parentColumn;
      this.childColumn = childColumn;
      this.position = position;
      this.deleteRule = deleteRule;
      this.updateRule = updateRule;
      this.enableValidateRely = enableRelyValidate;
      this.childIntegerIndex = childIntegerIndex;
      this.parentIntegerIndex = parentIntegerIndex;
   }

   public String getConstraintName() {
      return dnGetconstraintName(this);
   }

   public void setConstraintName(String fkName) {
      dnSetconstraintName(this, fkName);
   }

   public int getConstraintType() {
      return dnGetconstraintType(this);
   }

   public void setConstraintType(int ct) {
      dnSetconstraintType(this, ct);
   }

   public int getPosition() {
      return dnGetposition(this);
   }

   public void setPosition(int po) {
      dnSetposition(this, po);
   }

   public Integer getDeleteRule() {
      return dnGetdeleteRule(this);
   }

   public void setDeleteRule(Integer de) {
      dnSetdeleteRule(this, de);
   }

   public int getEnableValidateRely() {
      return dnGetenableValidateRely(this);
   }

   public void setEnableValidateRely(int enableValidateRely) {
      dnSetenableValidateRely(this, enableValidateRely);
   }

   public Integer getChildIntegerIndex() {
      return dnGetchildIntegerIndex(this);
   }

   public void setChildIntegerIndex(Integer childIntegerIndex) {
      dnSetchildIntegerIndex(this, childIntegerIndex);
   }

   public Integer getParentIntegerIndex() {
      return dnGetchildIntegerIndex(this);
   }

   public void setParentIntegerIndex(Integer parentIntegerIndex) {
      dnSetparentIntegerIndex(this, parentIntegerIndex);
   }

   public Integer getUpdateRule() {
      return dnGetupdateRule(this);
   }

   public void setUpdateRule(Integer ur) {
      dnSetupdateRule(this, ur);
   }

   public MTable getChildTable() {
      return dnGetchildTable(this);
   }

   public void setChildTable(MTable ft) {
      dnSetchildTable(this, ft);
   }

   public MTable getParentTable() {
      return dnGetparentTable(this);
   }

   public void setParentTable(MTable pt) {
      dnSetparentTable(this, pt);
   }

   public MColumnDescriptor getParentColumn() {
      return dnGetparentColumn(this);
   }

   public void setParentColumn(MColumnDescriptor name) {
      dnSetparentColumn(this, name);
   }

   public MColumnDescriptor getChildColumn() {
      return dnGetchildColumn(this);
   }

   public void setChildColumn(MColumnDescriptor name) {
      dnSetchildColumn(this, name);
   }

   static {
      EnhancementHelper.registerClass(___dn$loadClass("org.apache.hadoop.hive.metastore.model.MConstraint"), dnFieldNames, dnFieldTypes, dnFieldFlags, dnPersistableSuperclass, new MConstraint());
   }

   public void dnCopyKeyFieldsFromObjectId(Persistable.ObjectIdFieldConsumer fc, Object oid) {
      if (fc == null) {
         throw new IllegalArgumentException("ObjectIdFieldConsumer is null");
      } else if (!(oid instanceof PK)) {
         throw new ClassCastException("oid is not instanceof org.apache.hadoop.hive.metastore.model.MConstraint$PK");
      } else {
         PK o = (PK)oid;

         try {
            fc.storeStringField(3, o.constraintName);
            fc.storeIntField(10, o.position);
         } catch (Exception var5) {
         }

      }
   }

   protected void dnCopyKeyFieldsFromObjectId(Object oid) {
      if (!(oid instanceof PK)) {
         throw new ClassCastException("key class is not org.apache.hadoop.hive.metastore.model.MConstraint$PK or null");
      } else {
         PK o = (PK)oid;

         try {
            this.constraintName = o.constraintName;
            this.position = o.position;
         } catch (Exception var4) {
         }

      }
   }

   public void dnCopyKeyFieldsToObjectId(Object oid) {
      if (!(oid instanceof PK)) {
         throw new ClassCastException("key class is not org.apache.hadoop.hive.metastore.model.MConstraint$PK or null");
      } else {
         PK o = (PK)oid;

         try {
            o.constraintName = this.constraintName;
            o.position = this.position;
         } catch (Exception var4) {
         }

      }
   }

   public void dnCopyKeyFieldsToObjectId(Persistable.ObjectIdFieldSupplier fs, Object oid) {
      if (fs == null) {
         throw new IllegalArgumentException("ObjectIdFieldSupplier is null");
      } else if (!(oid instanceof PK)) {
         throw new ClassCastException("oid is not instanceof org.apache.hadoop.hive.metastore.model.MConstraint$PK");
      } else {
         PK o = (PK)oid;

         try {
            o.constraintName = fs.fetchStringField(3);
            o.position = fs.fetchIntField(10);
         } catch (Exception var5) {
         }

      }
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
      return new PK();
   }

   public Object dnNewObjectIdInstance(Object key) {
      // $FF: Couldn't be decompiled
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
      MConstraint result = new MConstraint();
      result.dnFlags = 1;
      result.dnStateManager = sm;
      return result;
   }

   public Persistable dnNewInstance(StateManager sm, Object obj) {
      MConstraint result = new MConstraint();
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
               this.childColumn = (MColumnDescriptor)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 1:
               this.childIntegerIndex = (Integer)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 2:
               this.childTable = (MTable)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 3:
               this.constraintName = this.dnStateManager.replacingStringField(this, index);
               break;
            case 4:
               this.constraintType = this.dnStateManager.replacingIntField(this, index);
               break;
            case 5:
               this.deleteRule = (Integer)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 6:
               this.enableValidateRely = this.dnStateManager.replacingIntField(this, index);
               break;
            case 7:
               this.parentColumn = (MColumnDescriptor)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 8:
               this.parentIntegerIndex = (Integer)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 9:
               this.parentTable = (MTable)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 10:
               this.position = this.dnStateManager.replacingIntField(this, index);
               break;
            case 11:
               this.updateRule = (Integer)this.dnStateManager.replacingObjectField(this, index);
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
               this.dnStateManager.providedObjectField(this, index, this.childColumn);
               break;
            case 1:
               this.dnStateManager.providedObjectField(this, index, this.childIntegerIndex);
               break;
            case 2:
               this.dnStateManager.providedObjectField(this, index, this.childTable);
               break;
            case 3:
               this.dnStateManager.providedStringField(this, index, this.constraintName);
               break;
            case 4:
               this.dnStateManager.providedIntField(this, index, this.constraintType);
               break;
            case 5:
               this.dnStateManager.providedObjectField(this, index, this.deleteRule);
               break;
            case 6:
               this.dnStateManager.providedIntField(this, index, this.enableValidateRely);
               break;
            case 7:
               this.dnStateManager.providedObjectField(this, index, this.parentColumn);
               break;
            case 8:
               this.dnStateManager.providedObjectField(this, index, this.parentIntegerIndex);
               break;
            case 9:
               this.dnStateManager.providedObjectField(this, index, this.parentTable);
               break;
            case 10:
               this.dnStateManager.providedIntField(this, index, this.position);
               break;
            case 11:
               this.dnStateManager.providedObjectField(this, index, this.updateRule);
               break;
            default:
               throw new IllegalArgumentException("out of field index :" + index);
         }

      }
   }

   protected final void dnCopyField(MConstraint obj, int index) {
      switch (index) {
         case 0:
            this.childColumn = obj.childColumn;
            break;
         case 1:
            this.childIntegerIndex = obj.childIntegerIndex;
            break;
         case 2:
            this.childTable = obj.childTable;
            break;
         case 3:
            this.constraintName = obj.constraintName;
            break;
         case 4:
            this.constraintType = obj.constraintType;
            break;
         case 5:
            this.deleteRule = obj.deleteRule;
            break;
         case 6:
            this.enableValidateRely = obj.enableValidateRely;
            break;
         case 7:
            this.parentColumn = obj.parentColumn;
            break;
         case 8:
            this.parentIntegerIndex = obj.parentIntegerIndex;
            break;
         case 9:
            this.parentTable = obj.parentTable;
            break;
         case 10:
            this.position = obj.position;
            break;
         case 11:
            this.updateRule = obj.updateRule;
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
      } else if (!(obj instanceof MConstraint)) {
         throw new IllegalArgumentException("object is not an object of type org.apache.hadoop.hive.metastore.model.MConstraint");
      } else {
         MConstraint other = (MConstraint)obj;
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
      return new String[]{"childColumn", "childIntegerIndex", "childTable", "constraintName", "constraintType", "deleteRule", "enableValidateRely", "parentColumn", "parentIntegerIndex", "parentTable", "position", "updateRule"};
   }

   private static final Class[] __dnFieldTypesInit() {
      return new Class[]{___dn$loadClass("org.apache.hadoop.hive.metastore.model.MColumnDescriptor"), ___dn$loadClass("java.lang.Integer"), ___dn$loadClass("org.apache.hadoop.hive.metastore.model.MTable"), ___dn$loadClass("java.lang.String"), Integer.TYPE, ___dn$loadClass("java.lang.Integer"), Integer.TYPE, ___dn$loadClass("org.apache.hadoop.hive.metastore.model.MColumnDescriptor"), ___dn$loadClass("java.lang.Integer"), ___dn$loadClass("org.apache.hadoop.hive.metastore.model.MTable"), Integer.TYPE, ___dn$loadClass("java.lang.Integer")};
   }

   private static final byte[] __dnFieldFlagsInit() {
      return new byte[]{10, 21, 10, 24, 21, 21, 21, 10, 21, 10, 24, 21};
   }

   protected static int __dnGetInheritedFieldCount() {
      return 0;
   }

   protected static int dnGetManagedFieldCount() {
      return 12;
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
      MConstraint o = (MConstraint)super.clone();
      o.dnFlags = 0;
      o.dnStateManager = null;
      return o;
   }

   static MColumnDescriptor dnGetchildColumn(MConstraint objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 0)) {
         return (MColumnDescriptor)objPC.dnStateManager.getObjectField(objPC, 0, objPC.childColumn);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(0) && !((BitSet)objPC.dnDetachedState[3]).get(0)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"childColumn\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.childColumn;
      }
   }

   static void dnSetchildColumn(MConstraint objPC, MColumnDescriptor val) {
      if (objPC.dnStateManager == null) {
         objPC.childColumn = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 0, objPC.childColumn, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(0);
      }

   }

   static Integer dnGetchildIntegerIndex(MConstraint objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 1)) {
         return (Integer)objPC.dnStateManager.getObjectField(objPC, 1, objPC.childIntegerIndex);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(1)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"childIntegerIndex\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.childIntegerIndex;
      }
   }

   static void dnSetchildIntegerIndex(MConstraint objPC, Integer val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setObjectField(objPC, 1, objPC.childIntegerIndex, val);
      } else {
         objPC.childIntegerIndex = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(1);
         }
      }

   }

   static MTable dnGetchildTable(MConstraint objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 2)) {
         return (MTable)objPC.dnStateManager.getObjectField(objPC, 2, objPC.childTable);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(2) && !((BitSet)objPC.dnDetachedState[3]).get(2)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"childTable\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.childTable;
      }
   }

   static void dnSetchildTable(MConstraint objPC, MTable val) {
      if (objPC.dnStateManager == null) {
         objPC.childTable = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 2, objPC.childTable, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(2);
      }

   }

   static String dnGetconstraintName(MConstraint objPC) {
      return objPC.constraintName;
   }

   static void dnSetconstraintName(MConstraint objPC, String val) {
      if (objPC.dnStateManager == null) {
         objPC.constraintName = val;
      } else {
         objPC.dnStateManager.setStringField(objPC, 3, objPC.constraintName, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(3);
      }

   }

   static int dnGetconstraintType(MConstraint objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 4)) {
         return objPC.dnStateManager.getIntField(objPC, 4, objPC.constraintType);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(4)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"constraintType\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.constraintType;
      }
   }

   static void dnSetconstraintType(MConstraint objPC, int val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setIntField(objPC, 4, objPC.constraintType, val);
      } else {
         objPC.constraintType = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(4);
         }
      }

   }

   static Integer dnGetdeleteRule(MConstraint objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 5)) {
         return (Integer)objPC.dnStateManager.getObjectField(objPC, 5, objPC.deleteRule);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(5)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"deleteRule\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.deleteRule;
      }
   }

   static void dnSetdeleteRule(MConstraint objPC, Integer val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setObjectField(objPC, 5, objPC.deleteRule, val);
      } else {
         objPC.deleteRule = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(5);
         }
      }

   }

   static int dnGetenableValidateRely(MConstraint objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 6)) {
         return objPC.dnStateManager.getIntField(objPC, 6, objPC.enableValidateRely);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(6)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"enableValidateRely\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.enableValidateRely;
      }
   }

   static void dnSetenableValidateRely(MConstraint objPC, int val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setIntField(objPC, 6, objPC.enableValidateRely, val);
      } else {
         objPC.enableValidateRely = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(6);
         }
      }

   }

   static MColumnDescriptor dnGetparentColumn(MConstraint objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 7)) {
         return (MColumnDescriptor)objPC.dnStateManager.getObjectField(objPC, 7, objPC.parentColumn);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(7) && !((BitSet)objPC.dnDetachedState[3]).get(7)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"parentColumn\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.parentColumn;
      }
   }

   static void dnSetparentColumn(MConstraint objPC, MColumnDescriptor val) {
      if (objPC.dnStateManager == null) {
         objPC.parentColumn = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 7, objPC.parentColumn, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(7);
      }

   }

   static Integer dnGetparentIntegerIndex(MConstraint objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 8)) {
         return (Integer)objPC.dnStateManager.getObjectField(objPC, 8, objPC.parentIntegerIndex);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(8)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"parentIntegerIndex\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.parentIntegerIndex;
      }
   }

   static void dnSetparentIntegerIndex(MConstraint objPC, Integer val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setObjectField(objPC, 8, objPC.parentIntegerIndex, val);
      } else {
         objPC.parentIntegerIndex = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(8);
         }
      }

   }

   static MTable dnGetparentTable(MConstraint objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 9)) {
         return (MTable)objPC.dnStateManager.getObjectField(objPC, 9, objPC.parentTable);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(9) && !((BitSet)objPC.dnDetachedState[3]).get(9)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"parentTable\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.parentTable;
      }
   }

   static void dnSetparentTable(MConstraint objPC, MTable val) {
      if (objPC.dnStateManager == null) {
         objPC.parentTable = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 9, objPC.parentTable, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(9);
      }

   }

   static int dnGetposition(MConstraint objPC) {
      return objPC.position;
   }

   static void dnSetposition(MConstraint objPC, int val) {
      if (objPC.dnStateManager == null) {
         objPC.position = val;
      } else {
         objPC.dnStateManager.setIntField(objPC, 10, objPC.position, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(10);
      }

   }

   static Integer dnGetupdateRule(MConstraint objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 11)) {
         return (Integer)objPC.dnStateManager.getObjectField(objPC, 11, objPC.updateRule);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(11)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"updateRule\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.updateRule;
      }
   }

   static void dnSetupdateRule(MConstraint objPC, Integer val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setObjectField(objPC, 11, objPC.updateRule, val);
      } else {
         objPC.updateRule = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(11);
         }
      }

   }

   public static class PK implements Serializable {
      public String constraintName;
      public int position;

      public PK() {
      }

      public PK(String constraintName, int position) {
         this.constraintName = constraintName;
         this.position = position;
      }

      public String toString() {
         return this.constraintName + ":" + this.position;
      }

      public int hashCode() {
         return this.toString().hashCode();
      }

      public boolean equals(Object other) {
         if (other != null && other instanceof PK) {
            PK otherPK = (PK)other;
            return otherPK.constraintName.equals(this.constraintName) && otherPK.position == this.position;
         } else {
            return false;
         }
      }
   }
}
