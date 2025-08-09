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

public class MStorageDescriptor implements Detachable, Persistable {
   private MColumnDescriptor cd;
   private String location;
   private String inputFormat;
   private String outputFormat;
   private boolean isCompressed = false;
   private int numBuckets = 1;
   private MSerDeInfo serDeInfo;
   private List bucketCols;
   private List sortCols;
   private Map parameters;
   private List skewedColNames;
   private List skewedColValues;
   private Map skewedColValueLocationMaps;
   private boolean isStoredAsSubDirectories;
   protected transient StateManager dnStateManager;
   protected transient byte dnFlags;
   protected Object[] dnDetachedState;
   private static final byte[] dnFieldFlags = __dnFieldFlagsInit();
   private static final Class dnPersistableSuperclass = __dnPersistableSuperclassInit();
   private static final Class[] dnFieldTypes = __dnFieldTypesInit();
   private static final String[] dnFieldNames = __dnFieldNamesInit();
   private static final int dnInheritedFieldCount = __dnGetInheritedFieldCount();

   public MStorageDescriptor() {
   }

   public MStorageDescriptor(MColumnDescriptor cd, String location, String inputFormat, String outputFormat, boolean isCompressed, int numBuckets, MSerDeInfo serDeInfo, List bucketCols, List sortOrder, Map parameters, List skewedColNames, List skewedColValues, Map skewedColValueLocationMaps, boolean storedAsSubDirectories) {
      this.cd = cd;
      this.location = location;
      this.inputFormat = inputFormat;
      this.outputFormat = outputFormat;
      this.isCompressed = isCompressed;
      this.numBuckets = numBuckets;
      this.serDeInfo = serDeInfo;
      this.bucketCols = bucketCols;
      this.sortCols = sortOrder;
      this.parameters = parameters;
      this.skewedColNames = skewedColNames;
      this.skewedColValues = skewedColValues;
      this.skewedColValueLocationMaps = skewedColValueLocationMaps;
      this.isStoredAsSubDirectories = storedAsSubDirectories;
   }

   public String getLocation() {
      return dnGetlocation(this);
   }

   public void setLocation(String location) {
      dnSetlocation(this, location);
   }

   public boolean isCompressed() {
      return dnGetisCompressed(this);
   }

   public void setCompressed(boolean isCompressed) {
      dnSetisCompressed(this, isCompressed);
   }

   public int getNumBuckets() {
      return dnGetnumBuckets(this);
   }

   public void setNumBuckets(int numBuckets) {
      dnSetnumBuckets(this, numBuckets);
   }

   public List getBucketCols() {
      return dnGetbucketCols(this);
   }

   public void setBucketCols(List bucketCols) {
      dnSetbucketCols(this, bucketCols);
   }

   public Map getParameters() {
      return dnGetparameters(this);
   }

   public void setParameters(Map parameters) {
      dnSetparameters(this, parameters);
   }

   public String getInputFormat() {
      return dnGetinputFormat(this);
   }

   public void setInputFormat(String inputFormat) {
      dnSetinputFormat(this, inputFormat);
   }

   public String getOutputFormat() {
      return dnGetoutputFormat(this);
   }

   public void setOutputFormat(String outputFormat) {
      dnSetoutputFormat(this, outputFormat);
   }

   public MColumnDescriptor getCD() {
      return dnGetcd(this);
   }

   public void setCD(MColumnDescriptor cd) {
      dnSetcd(this, cd);
   }

   public MSerDeInfo getSerDeInfo() {
      return dnGetserDeInfo(this);
   }

   public void setSerDeInfo(MSerDeInfo serDe) {
      dnSetserDeInfo(this, serDe);
   }

   public void setSortCols(List sortOrder) {
      dnSetsortCols(this, sortOrder);
   }

   public List getSortCols() {
      return dnGetsortCols(this);
   }

   public List getSkewedColNames() {
      return dnGetskewedColNames(this);
   }

   public void setSkewedColNames(List skewedColNames) {
      dnSetskewedColNames(this, skewedColNames);
   }

   public List getSkewedColValues() {
      return dnGetskewedColValues(this);
   }

   public void setSkewedColValues(List skewedColValues) {
      dnSetskewedColValues(this, skewedColValues);
   }

   public Map getSkewedColValueLocationMaps() {
      return dnGetskewedColValueLocationMaps(this);
   }

   public void setSkewedColValueLocationMaps(Map listBucketColValuesMapping) {
      dnSetskewedColValueLocationMaps(this, listBucketColValuesMapping);
   }

   public boolean isStoredAsSubDirectories() {
      return dnGetisStoredAsSubDirectories(this);
   }

   public void setStoredAsSubDirectories(boolean storedAsSubDirectories) {
      dnSetisStoredAsSubDirectories(this, storedAsSubDirectories);
   }

   static {
      EnhancementHelper.registerClass(___dn$loadClass("org.apache.hadoop.hive.metastore.model.MStorageDescriptor"), dnFieldNames, dnFieldTypes, dnFieldFlags, dnPersistableSuperclass, new MStorageDescriptor());
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
      MStorageDescriptor result = new MStorageDescriptor();
      result.dnFlags = 1;
      result.dnStateManager = sm;
      return result;
   }

   public Persistable dnNewInstance(StateManager sm, Object obj) {
      MStorageDescriptor result = new MStorageDescriptor();
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
               this.bucketCols = (List)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 1:
               this.cd = (MColumnDescriptor)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 2:
               this.inputFormat = this.dnStateManager.replacingStringField(this, index);
               break;
            case 3:
               this.isCompressed = this.dnStateManager.replacingBooleanField(this, index);
               break;
            case 4:
               this.isStoredAsSubDirectories = this.dnStateManager.replacingBooleanField(this, index);
               break;
            case 5:
               this.location = this.dnStateManager.replacingStringField(this, index);
               break;
            case 6:
               this.numBuckets = this.dnStateManager.replacingIntField(this, index);
               break;
            case 7:
               this.outputFormat = this.dnStateManager.replacingStringField(this, index);
               break;
            case 8:
               this.parameters = (Map)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 9:
               this.serDeInfo = (MSerDeInfo)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 10:
               this.skewedColNames = (List)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 11:
               this.skewedColValueLocationMaps = (Map)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 12:
               this.skewedColValues = (List)this.dnStateManager.replacingObjectField(this, index);
               break;
            case 13:
               this.sortCols = (List)this.dnStateManager.replacingObjectField(this, index);
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
               this.dnStateManager.providedObjectField(this, index, this.bucketCols);
               break;
            case 1:
               this.dnStateManager.providedObjectField(this, index, this.cd);
               break;
            case 2:
               this.dnStateManager.providedStringField(this, index, this.inputFormat);
               break;
            case 3:
               this.dnStateManager.providedBooleanField(this, index, this.isCompressed);
               break;
            case 4:
               this.dnStateManager.providedBooleanField(this, index, this.isStoredAsSubDirectories);
               break;
            case 5:
               this.dnStateManager.providedStringField(this, index, this.location);
               break;
            case 6:
               this.dnStateManager.providedIntField(this, index, this.numBuckets);
               break;
            case 7:
               this.dnStateManager.providedStringField(this, index, this.outputFormat);
               break;
            case 8:
               this.dnStateManager.providedObjectField(this, index, this.parameters);
               break;
            case 9:
               this.dnStateManager.providedObjectField(this, index, this.serDeInfo);
               break;
            case 10:
               this.dnStateManager.providedObjectField(this, index, this.skewedColNames);
               break;
            case 11:
               this.dnStateManager.providedObjectField(this, index, this.skewedColValueLocationMaps);
               break;
            case 12:
               this.dnStateManager.providedObjectField(this, index, this.skewedColValues);
               break;
            case 13:
               this.dnStateManager.providedObjectField(this, index, this.sortCols);
               break;
            default:
               throw new IllegalArgumentException("out of field index :" + index);
         }

      }
   }

   protected final void dnCopyField(MStorageDescriptor obj, int index) {
      switch (index) {
         case 0:
            this.bucketCols = obj.bucketCols;
            break;
         case 1:
            this.cd = obj.cd;
            break;
         case 2:
            this.inputFormat = obj.inputFormat;
            break;
         case 3:
            this.isCompressed = obj.isCompressed;
            break;
         case 4:
            this.isStoredAsSubDirectories = obj.isStoredAsSubDirectories;
            break;
         case 5:
            this.location = obj.location;
            break;
         case 6:
            this.numBuckets = obj.numBuckets;
            break;
         case 7:
            this.outputFormat = obj.outputFormat;
            break;
         case 8:
            this.parameters = obj.parameters;
            break;
         case 9:
            this.serDeInfo = obj.serDeInfo;
            break;
         case 10:
            this.skewedColNames = obj.skewedColNames;
            break;
         case 11:
            this.skewedColValueLocationMaps = obj.skewedColValueLocationMaps;
            break;
         case 12:
            this.skewedColValues = obj.skewedColValues;
            break;
         case 13:
            this.sortCols = obj.sortCols;
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
      } else if (!(obj instanceof MStorageDescriptor)) {
         throw new IllegalArgumentException("object is not an object of type org.apache.hadoop.hive.metastore.model.MStorageDescriptor");
      } else {
         MStorageDescriptor other = (MStorageDescriptor)obj;
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
      return new String[]{"bucketCols", "cd", "inputFormat", "isCompressed", "isStoredAsSubDirectories", "location", "numBuckets", "outputFormat", "parameters", "serDeInfo", "skewedColNames", "skewedColValueLocationMaps", "skewedColValues", "sortCols"};
   }

   private static final Class[] __dnFieldTypesInit() {
      return new Class[]{___dn$loadClass("java.util.List"), ___dn$loadClass("org.apache.hadoop.hive.metastore.model.MColumnDescriptor"), ___dn$loadClass("java.lang.String"), Boolean.TYPE, Boolean.TYPE, ___dn$loadClass("java.lang.String"), Integer.TYPE, ___dn$loadClass("java.lang.String"), ___dn$loadClass("java.util.Map"), ___dn$loadClass("org.apache.hadoop.hive.metastore.model.MSerDeInfo"), ___dn$loadClass("java.util.List"), ___dn$loadClass("java.util.Map"), ___dn$loadClass("java.util.List"), ___dn$loadClass("java.util.List")};
   }

   private static final byte[] __dnFieldFlagsInit() {
      return new byte[]{10, 10, 21, 21, 21, 21, 21, 21, 10, 10, 10, 10, 10, 10};
   }

   protected static int __dnGetInheritedFieldCount() {
      return 0;
   }

   protected static int dnGetManagedFieldCount() {
      return 14;
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
      MStorageDescriptor o = (MStorageDescriptor)super.clone();
      o.dnFlags = 0;
      o.dnStateManager = null;
      return o;
   }

   private static List dnGetbucketCols(MStorageDescriptor objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 0)) {
         return (List)objPC.dnStateManager.getObjectField(objPC, 0, objPC.bucketCols);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(0) && !((BitSet)objPC.dnDetachedState[3]).get(0)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"bucketCols\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.bucketCols;
      }
   }

   private static void dnSetbucketCols(MStorageDescriptor objPC, List val) {
      if (objPC.dnStateManager == null) {
         objPC.bucketCols = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 0, objPC.bucketCols, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(0);
      }

   }

   private static MColumnDescriptor dnGetcd(MStorageDescriptor objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 1)) {
         return (MColumnDescriptor)objPC.dnStateManager.getObjectField(objPC, 1, objPC.cd);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(1) && !((BitSet)objPC.dnDetachedState[3]).get(1)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"cd\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.cd;
      }
   }

   private static void dnSetcd(MStorageDescriptor objPC, MColumnDescriptor val) {
      if (objPC.dnStateManager == null) {
         objPC.cd = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 1, objPC.cd, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(1);
      }

   }

   private static String dnGetinputFormat(MStorageDescriptor objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 2)) {
         return objPC.dnStateManager.getStringField(objPC, 2, objPC.inputFormat);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(2)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"inputFormat\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.inputFormat;
      }
   }

   private static void dnSetinputFormat(MStorageDescriptor objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 2, objPC.inputFormat, val);
      } else {
         objPC.inputFormat = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(2);
         }
      }

   }

   private static boolean dnGetisCompressed(MStorageDescriptor objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 3)) {
         return objPC.dnStateManager.getBooleanField(objPC, 3, objPC.isCompressed);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(3)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"isCompressed\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.isCompressed;
      }
   }

   private static void dnSetisCompressed(MStorageDescriptor objPC, boolean val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setBooleanField(objPC, 3, objPC.isCompressed, val);
      } else {
         objPC.isCompressed = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(3);
         }
      }

   }

   private static boolean dnGetisStoredAsSubDirectories(MStorageDescriptor objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 4)) {
         return objPC.dnStateManager.getBooleanField(objPC, 4, objPC.isStoredAsSubDirectories);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(4)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"isStoredAsSubDirectories\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.isStoredAsSubDirectories;
      }
   }

   private static void dnSetisStoredAsSubDirectories(MStorageDescriptor objPC, boolean val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setBooleanField(objPC, 4, objPC.isStoredAsSubDirectories, val);
      } else {
         objPC.isStoredAsSubDirectories = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(4);
         }
      }

   }

   private static String dnGetlocation(MStorageDescriptor objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 5)) {
         return objPC.dnStateManager.getStringField(objPC, 5, objPC.location);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(5)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"location\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.location;
      }
   }

   private static void dnSetlocation(MStorageDescriptor objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 5, objPC.location, val);
      } else {
         objPC.location = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(5);
         }
      }

   }

   private static int dnGetnumBuckets(MStorageDescriptor objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 6)) {
         return objPC.dnStateManager.getIntField(objPC, 6, objPC.numBuckets);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(6)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"numBuckets\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.numBuckets;
      }
   }

   private static void dnSetnumBuckets(MStorageDescriptor objPC, int val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setIntField(objPC, 6, objPC.numBuckets, val);
      } else {
         objPC.numBuckets = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(6);
         }
      }

   }

   private static String dnGetoutputFormat(MStorageDescriptor objPC) {
      if (objPC.dnFlags > 0 && objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 7)) {
         return objPC.dnStateManager.getStringField(objPC, 7, objPC.outputFormat);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(7)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"outputFormat\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.outputFormat;
      }
   }

   private static void dnSetoutputFormat(MStorageDescriptor objPC, String val) {
      if (objPC.dnFlags != 0 && objPC.dnStateManager != null) {
         objPC.dnStateManager.setStringField(objPC, 7, objPC.outputFormat, val);
      } else {
         objPC.outputFormat = val;
         if (objPC.dnIsDetached()) {
            ((BitSet)objPC.dnDetachedState[3]).set(7);
         }
      }

   }

   private static Map dnGetparameters(MStorageDescriptor objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 8)) {
         return (Map)objPC.dnStateManager.getObjectField(objPC, 8, objPC.parameters);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(8) && !((BitSet)objPC.dnDetachedState[3]).get(8)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"parameters\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.parameters;
      }
   }

   private static void dnSetparameters(MStorageDescriptor objPC, Map val) {
      if (objPC.dnStateManager == null) {
         objPC.parameters = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 8, objPC.parameters, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(8);
      }

   }

   private static MSerDeInfo dnGetserDeInfo(MStorageDescriptor objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 9)) {
         return (MSerDeInfo)objPC.dnStateManager.getObjectField(objPC, 9, objPC.serDeInfo);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(9) && !((BitSet)objPC.dnDetachedState[3]).get(9)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"serDeInfo\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.serDeInfo;
      }
   }

   private static void dnSetserDeInfo(MStorageDescriptor objPC, MSerDeInfo val) {
      if (objPC.dnStateManager == null) {
         objPC.serDeInfo = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 9, objPC.serDeInfo, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(9);
      }

   }

   private static List dnGetskewedColNames(MStorageDescriptor objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 10)) {
         return (List)objPC.dnStateManager.getObjectField(objPC, 10, objPC.skewedColNames);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(10) && !((BitSet)objPC.dnDetachedState[3]).get(10)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"skewedColNames\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.skewedColNames;
      }
   }

   private static void dnSetskewedColNames(MStorageDescriptor objPC, List val) {
      if (objPC.dnStateManager == null) {
         objPC.skewedColNames = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 10, objPC.skewedColNames, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(10);
      }

   }

   private static Map dnGetskewedColValueLocationMaps(MStorageDescriptor objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 11)) {
         return (Map)objPC.dnStateManager.getObjectField(objPC, 11, objPC.skewedColValueLocationMaps);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(11) && !((BitSet)objPC.dnDetachedState[3]).get(11)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"skewedColValueLocationMaps\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.skewedColValueLocationMaps;
      }
   }

   private static void dnSetskewedColValueLocationMaps(MStorageDescriptor objPC, Map val) {
      if (objPC.dnStateManager == null) {
         objPC.skewedColValueLocationMaps = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 11, objPC.skewedColValueLocationMaps, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(11);
      }

   }

   private static List dnGetskewedColValues(MStorageDescriptor objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 12)) {
         return (List)objPC.dnStateManager.getObjectField(objPC, 12, objPC.skewedColValues);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(12) && !((BitSet)objPC.dnDetachedState[3]).get(12)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"skewedColValues\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.skewedColValues;
      }
   }

   private static void dnSetskewedColValues(MStorageDescriptor objPC, List val) {
      if (objPC.dnStateManager == null) {
         objPC.skewedColValues = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 12, objPC.skewedColValues, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(12);
      }

   }

   private static List dnGetsortCols(MStorageDescriptor objPC) {
      if (objPC.dnStateManager != null && !objPC.dnStateManager.isLoaded(objPC, 13)) {
         return (List)objPC.dnStateManager.getObjectField(objPC, 13, objPC.sortCols);
      } else if (objPC.dnIsDetached() && !((BitSet)objPC.dnDetachedState[2]).get(13) && !((BitSet)objPC.dnDetachedState[3]).get(13)) {
         throw new JDODetachedFieldAccessException("You have just attempted to access field \"sortCols\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
      } else {
         return objPC.sortCols;
      }
   }

   private static void dnSetsortCols(MStorageDescriptor objPC, List val) {
      if (objPC.dnStateManager == null) {
         objPC.sortCols = val;
      } else {
         objPC.dnStateManager.setObjectField(objPC, 13, objPC.sortCols, val);
      }

      if (objPC.dnIsDetached()) {
         ((BitSet)objPC.dnDetachedState[3]).set(13);
      }

   }
}
