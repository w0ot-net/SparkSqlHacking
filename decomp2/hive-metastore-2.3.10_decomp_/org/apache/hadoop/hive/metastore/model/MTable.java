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

public class MTable implements Detachable, Persistable {
   private String tableName;
   private MDatabase database;
   private MStorageDescriptor sd;
   private String owner;
   private int createTime;
   private int lastAccessTime;
   private int retention;
   private List partitionKeys;
   private Map parameters;
   private String viewOriginalText;
   private String viewExpandedText;
   private boolean rewriteEnabled;
   private String tableType;
   protected transient StateManager dnStateManager;
   protected transient byte dnFlags;
   protected Object[] dnDetachedState;
   private static final byte[] dnFieldFlags = __dnFieldFlagsInit();
   private static final Class dnPersistableSuperclass = __dnPersistableSuperclassInit();
   private static final Class[] dnFieldTypes = __dnFieldTypesInit();
   private static final String[] dnFieldNames = __dnFieldNamesInit();
   private static final int dnInheritedFieldCount = __dnGetInheritedFieldCount();

   public MTable() {
   }

   public MTable(String tableName, MDatabase database, MStorageDescriptor sd, String owner, int createTime, int lastAccessTime, int retention, List partitionKeys, Map parameters, String viewOriginalText, String viewExpandedText, boolean rewriteEnabled, String tableType) {
      this.tableName = tableName;
      this.database = database;
      this.sd = sd;
      this.owner = owner;
      this.createTime = createTime;
      this.setLastAccessTime(lastAccessTime);
      this.retention = retention;
      this.partitionKeys = partitionKeys;
      this.parameters = parameters;
      this.viewOriginalText = viewOriginalText;
      this.viewExpandedText = viewExpandedText;
      this.rewriteEnabled = rewriteEnabled;
      this.tableType = tableType;
   }

   public String getTableName() {
      return dnGettableName(this);
   }

   public void setTableName(String tableName) {
      dnSettableName(this, tableName);
   }

   public MStorageDescriptor getSd() {
      return dnGetsd(this);
   }

   public void setSd(MStorageDescriptor sd) {
      dnSetsd(this, sd);
   }

   public List getPartitionKeys() {
      return dnGetpartitionKeys(this);
   }

   public void setPartitionKeys(List partKeys) {
      dnSetpartitionKeys(this, partKeys);
   }

   public Map getParameters() {
      return dnGetparameters(this);
   }

   public void setParameters(Map parameters) {
      dnSetparameters(this, parameters);
   }

   public String getViewOriginalText() {
      return dnGetviewOriginalText(this);
   }

   public void setViewOriginalText(String viewOriginalText) {
      dnSetviewOriginalText(this, viewOriginalText);
   }

   public String getViewExpandedText() {
      return dnGetviewExpandedText(this);
   }

   public void setViewExpandedText(String viewExpandedText) {
      dnSetviewExpandedText(this, viewExpandedText);
   }

   public boolean isRewriteEnabled() {
      return dnGetrewriteEnabled(this);
   }

   public void setRewriteEnabled(boolean rewriteEnabled) {
      dnSetrewriteEnabled(this, rewriteEnabled);
   }

   public String getOwner() {
      return dnGetowner(this);
   }

   public void setOwner(String owner) {
      dnSetowner(this, owner);
   }

   public int getCreateTime() {
      return dnGetcreateTime(this);
   }

   public void setCreateTime(int createTime) {
      dnSetcreateTime(this, createTime);
   }

   public MDatabase getDatabase() {
      return dnGetdatabase(this);
   }

   public void setDatabase(MDatabase database) {
      dnSetdatabase(this, database);
   }

   public int getRetention() {
      return dnGetretention(this);
   }

   public void setRetention(int retention) {
      dnSetretention(this, retention);
   }

   public void setLastAccessTime(int lastAccessTime) {
      dnSetlastAccessTime(this, lastAccessTime);
   }

   public int getLastAccessTime() {
      return dnGetlastAccessTime(this);
   }

   public void setTableType(String tableType) {
      dnSettableType(this, tableType);
   }

   public String getTableType() {
      return dnGettableType(this);
   }

   static {
      EnhancementHelper.registerClass(___dn$loadClass("org.apache.hadoop.hive.metastore.model.MTable"), dnFieldNames, dnFieldTypes, dnFieldFlags, dnPersistableSuperclass, new MTable());
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
      MTable result = new MTable();
      result.dnFlags = 1;
      result.dnStateManager = sm;
      return result;
   }

   public Persistable dnNewInstance(StateManager sm, Object obj) {
      MTable result = new MTable();
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
               this.database = (MDatabase)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 2:
               this.lastAccessTime = this.dnStateManager.replacingIntField(this, index);
               break;
            case 3:
               this.owner = this.dnStateManager.replacingStringField(this, index);
               break;
            case 4:
               this.parameters = (Map)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 5:
               this.partitionKeys = (List)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 6:
               this.retention = this.dnStateManager.replacingIntField(this, index);
               break;
            case 7:
               this.rewriteEnabled = this.dnStateManager.replacingBooleanField(this, index);
               break;
            case 8:
               this.sd = (MStorageDescriptor)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 9:
               this.tableName = this.dnStateManager.replacingStringField(this, index);
               break;
            case 10:
               this.tableType = this.dnStateManager.replacingStringField(this, index);
               break;
            case 11:
               this.viewExpandedText = this.dnStateManager.replacingStringField(this, index);
               break;
            case 12:
               this.viewOriginalText = this.dnStateManager.replacingStringField(this, index);
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
               this.dnStateManager.providedObjectField(this, index, this.database);
               break;
            case 2:
               this.dnStateManager.providedIntField(this, index, this.lastAccessTime);
               break;
            case 3:
               this.dnStateManager.providedStringField(this, index, this.owner);
               break;
            case 4:
               this.dnStateManager.providedObjectField(this, index, this.parameters);
               break;
            case 5:
               this.dnStateManager.providedObjectField(this, index, this.partitionKeys);
               break;
            case 6:
               this.dnStateManager.providedIntField(this, index, this.retention);
               break;
            case 7:
               this.dnStateManager.providedBooleanField(this, index, this.rewriteEnabled);
               break;
            case 8:
               this.dnStateManager.providedObjectField(this, index, this.sd);
               break;
            case 9:
               this.dnStateManager.providedStringField(this, index, this.tableName);
               break;
            case 10:
               this.dnStateManager.providedStringField(this, index, this.tableType);
               break;
            case 11:
               this.dnStateManager.providedStringField(this, index, this.viewExpandedText);
               break;
            case 12:
               this.dnStateManager.providedStringField(this, index, this.viewOriginalText);
               break;
            default:
               throw new IllegalArgumentException("out of field index :" + index);
         }

      }
   }

   protected final void dnCopyField(MTable obj, int index) {
      switch (index) {
         case 0:
            this.createTime = obj.createTime;
            break;
         case 1:
            this.database = obj.database;
            break;
         case 2:
            this.lastAccessTime = obj.lastAccessTime;
            break;
         case 3:
            this.owner = obj.owner;
            break;
         case 4:
            this.parameters = obj.parameters;
            break;
         case 5:
            this.partitionKeys = obj.partitionKeys;
            break;
         case 6:
            this.retention = obj.retention;
            break;
         case 7:
            this.rewriteEnabled = obj.rewriteEnabled;
            break;
         case 8:
            this.sd = obj.sd;
            break;
         case 9:
            this.tableName = obj.tableName;
            break;
         case 10:
            this.tableType = obj.tableType;
            break;
         case 11:
            this.viewExpandedText = obj.viewExpandedText;
            break;
         case 12:
            this.viewOriginalText = obj.viewOriginalText;
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
      } else if (!(obj instanceof MTable)) {
         throw new IllegalArgumentException("object is not an object of type org.apache.hadoop.hive.metastore.model.MTable");
      } else {
         MTable other = (MTable)obj;
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
      return new String[]{"createTime", "database", "lastAccessTime", "owner", "parameters", "partitionKeys", "retention", "rewriteEnabled", "sd", "tableName", "tableType", "viewExpandedText", "viewOriginalText"};
   }

   private static final Class[] __dnFieldTypesInit() {
      return new Class[]{Integer.TYPE, ___dn$loadClass("org.apache.hadoop.hive.metastore.model.MDatabase"), Integer.TYPE, ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.util.Map"), ___dn$loadClass("java.util.List"), Integer.TYPE, Boolean.TYPE, ___dn$loadClass("org.apache.hadoop.hive.metastore.model.MStorageDescriptor"), ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.lang.String")};
   }

   private static final byte[] __dnFieldFlagsInit() {
      return new byte[]{21, 10, 21, 21, 10, 10, 21, 21, 10, 21, 21, 26, 26};
   }

   protected static int __dnGetInheritedFieldCount() {
      return 0;
   }

   protected static int dnGetManagedFieldCount() {
      return 13;
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
      MTable o = (MTable)super.clone();
      o.dnFlags = 0;
      o.dnStateManager = null;
      return o;
   }

   private static int dnGetcreateTime(MTable objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 0)) {
         return objPC.dnStateManager.getIntField(objPC, 0, objPC.createTime);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(0)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"createTime\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.createTime;
      }
   }

   private static void dnSetcreateTime(MTable objPC, int val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setIntField(objPC, 0, objPC.createTime, val);
      } else {
         objPC.createTime = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(0);
         }
      }

   }

   private static MDatabase dnGetdatabase(MTable objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 1)) {
         return (MDatabase)objPC.dnStateManager.getObjectField(objPC, 1, objPC.database);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(1) && !((BitSet)objPC.dnDetachedState[3]).get(1)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"database\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.database;
      }
   }

   private static void dnSetdatabase(MTable objPC, MDatabase val) {
      if (objPC.dnStateManager == null) {
         objPC.database = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 1, objPC.database, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(1);
      }

   }

   private static int dnGetlastAccessTime(MTable objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 2)) {
         return objPC.dnStateManager.getIntField(objPC, 2, objPC.lastAccessTime);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(2)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"lastAccessTime\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.lastAccessTime;
      }
   }

   private static void dnSetlastAccessTime(MTable objPC, int val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setIntField(objPC, 2, objPC.lastAccessTime, val);
      } else {
         objPC.lastAccessTime = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(2);
         }
      }

   }

   private static String dnGetowner(MTable objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 3)) {
         return objPC.dnStateManager.getStringField(objPC, 3, objPC.owner);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(3)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"owner\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.owner;
      }
   }

   private static void dnSetowner(MTable objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 3, objPC.owner, val);
      } else {
         objPC.owner = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(3);
         }
      }

   }

   private static Map dnGetparameters(MTable objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 4)) {
         return (Map)objPC.dnStateManager.getObjectField(objPC, 4, objPC.parameters);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(4) && !((BitSet)objPC.dnDetachedState[3]).get(4)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"parameters\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.parameters;
      }
   }

   private static void dnSetparameters(MTable objPC, Map val) {
      if (objPC.dnStateManager == null) {
         objPC.parameters = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 4, objPC.parameters, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(4);
      }

   }

   private static List dnGetpartitionKeys(MTable objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 5)) {
         return (List)objPC.dnStateManager.getObjectField(objPC, 5, objPC.partitionKeys);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(5) && !((BitSet)objPC.dnDetachedState[3]).get(5)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"partitionKeys\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.partitionKeys;
      }
   }

   private static void dnSetpartitionKeys(MTable objPC, List val) {
      if (objPC.dnStateManager == null) {
         objPC.partitionKeys = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 5, objPC.partitionKeys, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(5);
      }

   }

   private static int dnGetretention(MTable objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 6)) {
         return objPC.dnStateManager.getIntField(objPC, 6, objPC.retention);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(6)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"retention\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.retention;
      }
   }

   private static void dnSetretention(MTable objPC, int val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setIntField(objPC, 6, objPC.retention, val);
      } else {
         objPC.retention = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(6);
         }
      }

   }

   private static boolean dnGetrewriteEnabled(MTable objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 7)) {
         return objPC.dnStateManager.getBooleanField(objPC, 7, objPC.rewriteEnabled);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(7)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"rewriteEnabled\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.rewriteEnabled;
      }
   }

   private static void dnSetrewriteEnabled(MTable objPC, boolean val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setBooleanField(objPC, 7, objPC.rewriteEnabled, val);
      } else {
         objPC.rewriteEnabled = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(7);
         }
      }

   }

   private static MStorageDescriptor dnGetsd(MTable objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 8)) {
         return (MStorageDescriptor)objPC.dnStateManager.getObjectField(objPC, 8, objPC.sd);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(8) && !((BitSet)objPC.dnDetachedState[3]).get(8)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"sd\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.sd;
      }
   }

   private static void dnSetsd(MTable objPC, MStorageDescriptor val) {
      if (objPC.dnStateManager == null) {
         objPC.sd = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 8, objPC.sd, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(8);
      }

   }

   private static String dnGettableName(MTable objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 9)) {
         return objPC.dnStateManager.getStringField(objPC, 9, objPC.tableName);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(9)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"tableName\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.tableName;
      }
   }

   private static void dnSettableName(MTable objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 9, objPC.tableName, val);
      } else {
         objPC.tableName = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(9);
         }
      }

   }

   private static String dnGettableType(MTable objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 10)) {
         return objPC.dnStateManager.getStringField(objPC, 10, objPC.tableType);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(10)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"tableType\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.tableType;
      }
   }

   private static void dnSettableType(MTable objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 10, objPC.tableType, val);
      } else {
         objPC.tableType = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(10);
         }
      }

   }

   private static String dnGetviewExpandedText(MTable objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 11)) {
         return objPC.dnStateManager.getStringField(objPC, 11, objPC.viewExpandedText);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(11) && !((BitSet)objPC.dnDetachedState[3]).get(11)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"viewExpandedText\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.viewExpandedText;
      }
   }

   private static void dnSetviewExpandedText(MTable objPC, String val) {
      if (objPC.dnStateManager == null) {
         objPC.viewExpandedText = val;
      } else {
         objPC.dnStateManager.setStringField(objPC, 11, objPC.viewExpandedText, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(11);
      }

   }

   private static String dnGetviewOriginalText(MTable objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 12)) {
         return objPC.dnStateManager.getStringField(objPC, 12, objPC.viewOriginalText);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(12) && !((BitSet)objPC.dnDetachedState[3]).get(12)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"viewOriginalText\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.viewOriginalText;
      }
   }

   private static void dnSetviewOriginalText(MTable objPC, String val) {
      if (objPC.dnStateManager == null) {
         objPC.viewOriginalText = val;
      } else {
         objPC.dnStateManager.setStringField(objPC, 12, objPC.viewOriginalText, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(12);
      }

   }
}
