package org.apache.hadoop.hive.metastore.model;

import java.util.BitSet;
import javax.jdo.JDODetachedFieldAccessException;
import org.datanucleus.enhancement.Detachable;
import org.datanucleus.enhancement.ExecutionContextReference;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.enhancement.StateManager;
import org.datanucleus.enhancer.EnhancementHelper;

public class MTableColumnStatistics implements Detachable, Persistable {
   private MTable table;
   private String dbName;
   private String tableName;
   private String colName;
   private String colType;
   private Long longLowValue;
   private Long longHighValue;
   private Double doubleLowValue;
   private Double doubleHighValue;
   private String decimalLowValue;
   private String decimalHighValue;
   private Long numNulls;
   private Long numDVs;
   private Double avgColLen;
   private Long maxColLen;
   private Long numTrues;
   private Long numFalses;
   private long lastAnalyzed;
   protected transient StateManager dnStateManager;
   protected transient byte dnFlags;
   protected Object[] dnDetachedState;
   private static final byte[] dnFieldFlags = __dnFieldFlagsInit();
   private static final Class dnPersistableSuperclass = __dnPersistableSuperclassInit();
   private static final Class[] dnFieldTypes = __dnFieldTypesInit();
   private static final String[] dnFieldNames = __dnFieldNamesInit();
   private static final int dnInheritedFieldCount = __dnGetInheritedFieldCount();

   public MTable getTable() {
      return dnGettable(this);
   }

   public void setTable(MTable table) {
      dnSettable(this, table);
   }

   public String getTableName() {
      return dnGettableName(this);
   }

   public void setTableName(String tableName) {
      dnSettableName(this, tableName);
   }

   public String getColName() {
      return dnGetcolName(this);
   }

   public void setColName(String colName) {
      dnSetcolName(this, colName);
   }

   public String getColType() {
      return dnGetcolType(this);
   }

   public void setColType(String colType) {
      dnSetcolType(this, colType);
   }

   public Long getNumNulls() {
      return dnGetnumNulls(this);
   }

   public void setNumNulls(long numNulls) {
      dnSetnumNulls(this, numNulls);
   }

   public Long getNumDVs() {
      return dnGetnumDVs(this);
   }

   public void setNumDVs(long numDVs) {
      dnSetnumDVs(this, numDVs);
   }

   public Double getAvgColLen() {
      return dnGetavgColLen(this);
   }

   public void setAvgColLen(double avgColLen) {
      dnSetavgColLen(this, avgColLen);
   }

   public Long getMaxColLen() {
      return dnGetmaxColLen(this);
   }

   public void setMaxColLen(long maxColLen) {
      dnSetmaxColLen(this, maxColLen);
   }

   public Long getNumTrues() {
      return dnGetnumTrues(this);
   }

   public void setNumTrues(long numTrues) {
      dnSetnumTrues(this, numTrues);
   }

   public Long getNumFalses() {
      return dnGetnumFalses(this);
   }

   public void setNumFalses(long numFalses) {
      dnSetnumFalses(this, numFalses);
   }

   public long getLastAnalyzed() {
      return dnGetlastAnalyzed(this);
   }

   public void setLastAnalyzed(long lastAnalyzed) {
      dnSetlastAnalyzed(this, lastAnalyzed);
   }

   public String getDbName() {
      return dnGetdbName(this);
   }

   public void setDbName(String dbName) {
      dnSetdbName(this, dbName);
   }

   public void setBooleanStats(Long numTrues, Long numFalses, Long numNulls) {
      dnSetnumTrues(this, numTrues);
      dnSetnumFalses(this, numFalses);
      dnSetnumNulls(this, numNulls);
   }

   public void setLongStats(Long numNulls, Long numNDVs, Long lowValue, Long highValue) {
      dnSetnumNulls(this, numNulls);
      dnSetnumDVs(this, numNDVs);
      dnSetlongLowValue(this, lowValue);
      dnSetlongHighValue(this, highValue);
   }

   public void setDoubleStats(Long numNulls, Long numNDVs, Double lowValue, Double highValue) {
      dnSetnumNulls(this, numNulls);
      dnSetnumDVs(this, numNDVs);
      dnSetdoubleLowValue(this, lowValue);
      dnSetdoubleHighValue(this, highValue);
   }

   public void setDecimalStats(Long numNulls, Long numNDVs, String lowValue, String highValue) {
      dnSetnumNulls(this, numNulls);
      dnSetnumDVs(this, numNDVs);
      dnSetdecimalLowValue(this, lowValue);
      dnSetdecimalHighValue(this, highValue);
   }

   public void setStringStats(Long numNulls, Long numNDVs, Long maxColLen, Double avgColLen) {
      dnSetnumNulls(this, numNulls);
      dnSetnumDVs(this, numNDVs);
      dnSetmaxColLen(this, maxColLen);
      dnSetavgColLen(this, avgColLen);
   }

   public void setBinaryStats(Long numNulls, Long maxColLen, Double avgColLen) {
      dnSetnumNulls(this, numNulls);
      dnSetmaxColLen(this, maxColLen);
      dnSetavgColLen(this, avgColLen);
   }

   public void setDateStats(Long numNulls, Long numNDVs, Long lowValue, Long highValue) {
      dnSetnumNulls(this, numNulls);
      dnSetnumDVs(this, numNDVs);
      dnSetlongLowValue(this, lowValue);
      dnSetlongHighValue(this, highValue);
   }

   public Long getLongLowValue() {
      return dnGetlongLowValue(this);
   }

   public void setLongLowValue(long longLowValue) {
      dnSetlongLowValue(this, longLowValue);
   }

   public Long getLongHighValue() {
      return dnGetlongHighValue(this);
   }

   public void setLongHighValue(long longHighValue) {
      dnSetlongHighValue(this, longHighValue);
   }

   public Double getDoubleLowValue() {
      return dnGetdoubleLowValue(this);
   }

   public void setDoubleLowValue(double doubleLowValue) {
      dnSetdoubleLowValue(this, doubleLowValue);
   }

   public Double getDoubleHighValue() {
      return dnGetdoubleHighValue(this);
   }

   public void setDoubleHighValue(double doubleHighValue) {
      dnSetdoubleHighValue(this, doubleHighValue);
   }

   public String getDecimalLowValue() {
      return dnGetdecimalLowValue(this);
   }

   public void setDecimalLowValue(String decimalLowValue) {
      dnSetdecimalLowValue(this, decimalLowValue);
   }

   public String getDecimalHighValue() {
      return dnGetdecimalHighValue(this);
   }

   public void setDecimalHighValue(String decimalHighValue) {
      dnSetdecimalHighValue(this, decimalHighValue);
   }

   static {
      EnhancementHelper.registerClass(___dn$loadClass("org.apache.hadoop.hive.metastore.model.MTableColumnStatistics"), dnFieldNames, dnFieldTypes, dnFieldFlags, dnPersistableSuperclass, new MTableColumnStatistics());
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
      MTableColumnStatistics result = new MTableColumnStatistics();
      result.dnFlags = 1;
      result.dnStateManager = sm;
      return result;
   }

   public Persistable dnNewInstance(StateManager sm, Object obj) {
      MTableColumnStatistics result = new MTableColumnStatistics();
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
               this.avgColLen = (Double)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 1:
               this.colName = this.dnStateManager.replacingStringField(this, index);
               break;
            case 2:
               this.colType = this.dnStateManager.replacingStringField(this, index);
               break;
            case 3:
               this.dbName = this.dnStateManager.replacingStringField(this, index);
               break;
            case 4:
               this.decimalHighValue = this.dnStateManager.replacingStringField(this, index);
               break;
            case 5:
               this.decimalLowValue = this.dnStateManager.replacingStringField(this, index);
               break;
            case 6:
               this.doubleHighValue = (Double)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 7:
               this.doubleLowValue = (Double)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 8:
               this.lastAnalyzed = this.dnStateManager.replacingLongField(this, index);
               break;
            case 9:
               this.longHighValue = (Long)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 10:
               this.longLowValue = (Long)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 11:
               this.maxColLen = (Long)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 12:
               this.numDVs = (Long)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 13:
               this.numFalses = (Long)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 14:
               this.numNulls = (Long)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 15:
               this.numTrues = (Long)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 16:
               this.table = (MTable)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 17:
               this.tableName = this.dnStateManager.replacingStringField(this, index);
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
               this.dnStateManager.providedObjectField(this, index, this.avgColLen);
               break;
            case 1:
               this.dnStateManager.providedStringField(this, index, this.colName);
               break;
            case 2:
               this.dnStateManager.providedStringField(this, index, this.colType);
               break;
            case 3:
               this.dnStateManager.providedStringField(this, index, this.dbName);
               break;
            case 4:
               this.dnStateManager.providedStringField(this, index, this.decimalHighValue);
               break;
            case 5:
               this.dnStateManager.providedStringField(this, index, this.decimalLowValue);
               break;
            case 6:
               this.dnStateManager.providedObjectField(this, index, this.doubleHighValue);
               break;
            case 7:
               this.dnStateManager.providedObjectField(this, index, this.doubleLowValue);
               break;
            case 8:
               this.dnStateManager.providedLongField(this, index, this.lastAnalyzed);
               break;
            case 9:
               this.dnStateManager.providedObjectField(this, index, this.longHighValue);
               break;
            case 10:
               this.dnStateManager.providedObjectField(this, index, this.longLowValue);
               break;
            case 11:
               this.dnStateManager.providedObjectField(this, index, this.maxColLen);
               break;
            case 12:
               this.dnStateManager.providedObjectField(this, index, this.numDVs);
               break;
            case 13:
               this.dnStateManager.providedObjectField(this, index, this.numFalses);
               break;
            case 14:
               this.dnStateManager.providedObjectField(this, index, this.numNulls);
               break;
            case 15:
               this.dnStateManager.providedObjectField(this, index, this.numTrues);
               break;
            case 16:
               this.dnStateManager.providedObjectField(this, index, this.table);
               break;
            case 17:
               this.dnStateManager.providedStringField(this, index, this.tableName);
               break;
            default:
               throw new IllegalArgumentException("out of field index :" + index);
         }

      }
   }

   protected final void dnCopyField(MTableColumnStatistics obj, int index) {
      switch (index) {
         case 0:
            this.avgColLen = obj.avgColLen;
            break;
         case 1:
            this.colName = obj.colName;
            break;
         case 2:
            this.colType = obj.colType;
            break;
         case 3:
            this.dbName = obj.dbName;
            break;
         case 4:
            this.decimalHighValue = obj.decimalHighValue;
            break;
         case 5:
            this.decimalLowValue = obj.decimalLowValue;
            break;
         case 6:
            this.doubleHighValue = obj.doubleHighValue;
            break;
         case 7:
            this.doubleLowValue = obj.doubleLowValue;
            break;
         case 8:
            this.lastAnalyzed = obj.lastAnalyzed;
            break;
         case 9:
            this.longHighValue = obj.longHighValue;
            break;
         case 10:
            this.longLowValue = obj.longLowValue;
            break;
         case 11:
            this.maxColLen = obj.maxColLen;
            break;
         case 12:
            this.numDVs = obj.numDVs;
            break;
         case 13:
            this.numFalses = obj.numFalses;
            break;
         case 14:
            this.numNulls = obj.numNulls;
            break;
         case 15:
            this.numTrues = obj.numTrues;
            break;
         case 16:
            this.table = obj.table;
            break;
         case 17:
            this.tableName = obj.tableName;
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
      } else if (!(obj instanceof MTableColumnStatistics)) {
         throw new IllegalArgumentException("object is not an object of type org.apache.hadoop.hive.metastore.model.MTableColumnStatistics");
      } else {
         MTableColumnStatistics other = (MTableColumnStatistics)obj;
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
      return new String[]{"avgColLen", "colName", "colType", "dbName", "decimalHighValue", "decimalLowValue", "doubleHighValue", "doubleLowValue", "lastAnalyzed", "longHighValue", "longLowValue", "maxColLen", "numDVs", "numFalses", "numNulls", "numTrues", "table", "tableName"};
   }

   private static final Class[] __dnFieldTypesInit() {
      return new Class[]{___dn$loadClass("java.lang.Double"), ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.lang.Double"), ___dn$loadClass("java.lang.Double"), Long.TYPE, ___dn$loadClass("java.lang.Long"), ___dn$loadClass("java.lang.Long"), ___dn$loadClass("java.lang.Long"), ___dn$loadClass("java.lang.Long"), ___dn$loadClass("java.lang.Long"), ___dn$loadClass("java.lang.Long"), ___dn$loadClass("java.lang.Long"), ___dn$loadClass("org.apache.hadoop.hive.metastore.model.MTable"), ___dn$loadClass("java.lang.String")};
   }

   private static final byte[] __dnFieldFlagsInit() {
      return new byte[]{21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 10, 21};
   }

   protected static int __dnGetInheritedFieldCount() {
      return 0;
   }

   protected static int dnGetManagedFieldCount() {
      return 18;
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
      MTableColumnStatistics o = (MTableColumnStatistics)super.clone();
      o.dnFlags = 0;
      o.dnStateManager = null;
      return o;
   }

   private static Double dnGetavgColLen(MTableColumnStatistics objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 0)) {
         return (Double)objPC.dnStateManager.getObjectField(objPC, 0, objPC.avgColLen);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(0)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"avgColLen\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.avgColLen;
      }
   }

   private static void dnSetavgColLen(MTableColumnStatistics objPC, Double val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setObjectField(objPC, 0, objPC.avgColLen, val);
      } else {
         objPC.avgColLen = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(0);
         }
      }

   }

   private static String dnGetcolName(MTableColumnStatistics objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 1)) {
         return objPC.dnStateManager.getStringField(objPC, 1, objPC.colName);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(1)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"colName\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.colName;
      }
   }

   private static void dnSetcolName(MTableColumnStatistics objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 1, objPC.colName, val);
      } else {
         objPC.colName = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(1);
         }
      }

   }

   private static String dnGetcolType(MTableColumnStatistics objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 2)) {
         return objPC.dnStateManager.getStringField(objPC, 2, objPC.colType);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(2)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"colType\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.colType;
      }
   }

   private static void dnSetcolType(MTableColumnStatistics objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 2, objPC.colType, val);
      } else {
         objPC.colType = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(2);
         }
      }

   }

   private static String dnGetdbName(MTableColumnStatistics objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 3)) {
         return objPC.dnStateManager.getStringField(objPC, 3, objPC.dbName);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(3)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"dbName\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.dbName;
      }
   }

   private static void dnSetdbName(MTableColumnStatistics objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 3, objPC.dbName, val);
      } else {
         objPC.dbName = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(3);
         }
      }

   }

   private static String dnGetdecimalHighValue(MTableColumnStatistics objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 4)) {
         return objPC.dnStateManager.getStringField(objPC, 4, objPC.decimalHighValue);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(4)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"decimalHighValue\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.decimalHighValue;
      }
   }

   private static void dnSetdecimalHighValue(MTableColumnStatistics objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 4, objPC.decimalHighValue, val);
      } else {
         objPC.decimalHighValue = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(4);
         }
      }

   }

   private static String dnGetdecimalLowValue(MTableColumnStatistics objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 5)) {
         return objPC.dnStateManager.getStringField(objPC, 5, objPC.decimalLowValue);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(5)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"decimalLowValue\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.decimalLowValue;
      }
   }

   private static void dnSetdecimalLowValue(MTableColumnStatistics objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 5, objPC.decimalLowValue, val);
      } else {
         objPC.decimalLowValue = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(5);
         }
      }

   }

   private static Double dnGetdoubleHighValue(MTableColumnStatistics objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 6)) {
         return (Double)objPC.dnStateManager.getObjectField(objPC, 6, objPC.doubleHighValue);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(6)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"doubleHighValue\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.doubleHighValue;
      }
   }

   private static void dnSetdoubleHighValue(MTableColumnStatistics objPC, Double val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setObjectField(objPC, 6, objPC.doubleHighValue, val);
      } else {
         objPC.doubleHighValue = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(6);
         }
      }

   }

   private static Double dnGetdoubleLowValue(MTableColumnStatistics objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 7)) {
         return (Double)objPC.dnStateManager.getObjectField(objPC, 7, objPC.doubleLowValue);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(7)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"doubleLowValue\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.doubleLowValue;
      }
   }

   private static void dnSetdoubleLowValue(MTableColumnStatistics objPC, Double val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setObjectField(objPC, 7, objPC.doubleLowValue, val);
      } else {
         objPC.doubleLowValue = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(7);
         }
      }

   }

   private static long dnGetlastAnalyzed(MTableColumnStatistics objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 8)) {
         return objPC.dnStateManager.getLongField(objPC, 8, objPC.lastAnalyzed);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(8)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"lastAnalyzed\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.lastAnalyzed;
      }
   }

   private static void dnSetlastAnalyzed(MTableColumnStatistics objPC, long val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setLongField(objPC, 8, objPC.lastAnalyzed, val);
      } else {
         objPC.lastAnalyzed = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(8);
         }
      }

   }

   private static Long dnGetlongHighValue(MTableColumnStatistics objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 9)) {
         return (Long)objPC.dnStateManager.getObjectField(objPC, 9, objPC.longHighValue);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(9)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"longHighValue\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.longHighValue;
      }
   }

   private static void dnSetlongHighValue(MTableColumnStatistics objPC, Long val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setObjectField(objPC, 9, objPC.longHighValue, val);
      } else {
         objPC.longHighValue = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(9);
         }
      }

   }

   private static Long dnGetlongLowValue(MTableColumnStatistics objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 10)) {
         return (Long)objPC.dnStateManager.getObjectField(objPC, 10, objPC.longLowValue);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(10)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"longLowValue\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.longLowValue;
      }
   }

   private static void dnSetlongLowValue(MTableColumnStatistics objPC, Long val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setObjectField(objPC, 10, objPC.longLowValue, val);
      } else {
         objPC.longLowValue = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(10);
         }
      }

   }

   private static Long dnGetmaxColLen(MTableColumnStatistics objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 11)) {
         return (Long)objPC.dnStateManager.getObjectField(objPC, 11, objPC.maxColLen);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(11)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"maxColLen\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.maxColLen;
      }
   }

   private static void dnSetmaxColLen(MTableColumnStatistics objPC, Long val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setObjectField(objPC, 11, objPC.maxColLen, val);
      } else {
         objPC.maxColLen = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(11);
         }
      }

   }

   private static Long dnGetnumDVs(MTableColumnStatistics objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 12)) {
         return (Long)objPC.dnStateManager.getObjectField(objPC, 12, objPC.numDVs);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(12)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"numDVs\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.numDVs;
      }
   }

   private static void dnSetnumDVs(MTableColumnStatistics objPC, Long val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setObjectField(objPC, 12, objPC.numDVs, val);
      } else {
         objPC.numDVs = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(12);
         }
      }

   }

   private static Long dnGetnumFalses(MTableColumnStatistics objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 13)) {
         return (Long)objPC.dnStateManager.getObjectField(objPC, 13, objPC.numFalses);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(13)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"numFalses\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.numFalses;
      }
   }

   private static void dnSetnumFalses(MTableColumnStatistics objPC, Long val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setObjectField(objPC, 13, objPC.numFalses, val);
      } else {
         objPC.numFalses = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(13);
         }
      }

   }

   private static Long dnGetnumNulls(MTableColumnStatistics objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 14)) {
         return (Long)objPC.dnStateManager.getObjectField(objPC, 14, objPC.numNulls);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(14)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"numNulls\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.numNulls;
      }
   }

   private static void dnSetnumNulls(MTableColumnStatistics objPC, Long val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setObjectField(objPC, 14, objPC.numNulls, val);
      } else {
         objPC.numNulls = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(14);
         }
      }

   }

   private static Long dnGetnumTrues(MTableColumnStatistics objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 15)) {
         return (Long)objPC.dnStateManager.getObjectField(objPC, 15, objPC.numTrues);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(15)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"numTrues\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.numTrues;
      }
   }

   private static void dnSetnumTrues(MTableColumnStatistics objPC, Long val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setObjectField(objPC, 15, objPC.numTrues, val);
      } else {
         objPC.numTrues = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(15);
         }
      }

   }

   private static MTable dnGettable(MTableColumnStatistics objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 16)) {
         return (MTable)objPC.dnStateManager.getObjectField(objPC, 16, objPC.table);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(16) && !((BitSet)objPC.dnDetachedState[3]).get(16)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"table\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.table;
      }
   }

   private static void dnSettable(MTableColumnStatistics objPC, MTable val) {
      if (objPC.dnStateManager == null) {
         objPC.table = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 16, objPC.table, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(16);
      }

   }

   private static String dnGettableName(MTableColumnStatistics objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 17)) {
         return objPC.dnStateManager.getStringField(objPC, 17, objPC.tableName);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(17)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"tableName\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.tableName;
      }
   }

   private static void dnSettableName(MTableColumnStatistics objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 17, objPC.tableName, val);
      } else {
         objPC.tableName = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(17);
         }
      }

   }
}
