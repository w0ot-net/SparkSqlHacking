package org.apache.derby.impl.sql.execute;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.util.ArrayUtil;

abstract class WriteCursorConstantAction implements ConstantAction, Formatable {
   long conglomId;
   StaticCompiledOpenConglomInfo heapSCOCI;
   IndexRowGenerator[] irgs;
   long[] indexCIDS;
   StaticCompiledOpenConglomInfo[] indexSCOCIs;
   String[] indexNames;
   boolean deferred;
   private Properties targetProperties;
   UUID targetUUID;
   int lockMode;
   private FKInfo[] fkInfo;
   private TriggerInfo triggerInfo;
   private FormatableBitSet baseRowReadList;
   private int[] baseRowReadMap;
   private int[] streamStorableHeapColIds;
   boolean singleRowSource;
   private boolean underMerge;

   public WriteCursorConstantAction() {
   }

   public WriteCursorConstantAction(long var1, StaticCompiledOpenConglomInfo var3, IndexRowGenerator[] var4, long[] var5, StaticCompiledOpenConglomInfo[] var6, String[] var7, boolean var8, Properties var9, UUID var10, int var11, FKInfo[] var12, TriggerInfo var13, FormatableBitSet var14, int[] var15, int[] var16, boolean var17, boolean var18) {
      this.conglomId = var1;
      this.heapSCOCI = var3;
      this.irgs = var4;
      this.indexSCOCIs = var6;
      this.indexCIDS = var5;
      this.indexSCOCIs = var6;
      this.deferred = var8;
      this.targetProperties = var9;
      this.targetUUID = var10;
      this.lockMode = var11;
      this.fkInfo = var12;
      this.triggerInfo = var13;
      this.baseRowReadList = var14;
      this.baseRowReadMap = var15;
      this.streamStorableHeapColIds = var16;
      this.singleRowSource = var17;
      this.indexNames = var7;
      this.underMerge = var18;
   }

   final FKInfo[] getFKInfo() {
      return this.fkInfo;
   }

   TriggerInfo getTriggerInfo() {
      return this.triggerInfo;
   }

   public final void executeConstantAction(Activation var1) throws StandardException {
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.conglomId = var1.readLong();
      this.heapSCOCI = (StaticCompiledOpenConglomInfo)var1.readObject();
      this.irgs = new IndexRowGenerator[ArrayUtil.readArrayLength(var1)];
      ArrayUtil.readArrayItems(var1, this.irgs);
      this.indexCIDS = ArrayUtil.readLongArray(var1);
      this.indexSCOCIs = new StaticCompiledOpenConglomInfo[ArrayUtil.readArrayLength(var1)];
      ArrayUtil.readArrayItems(var1, this.indexSCOCIs);
      this.deferred = var1.readBoolean();
      this.targetProperties = (Properties)var1.readObject();
      this.targetUUID = (UUID)var1.readObject();
      this.lockMode = var1.readInt();
      this.fkInfo = new FKInfo[ArrayUtil.readArrayLength(var1)];
      ArrayUtil.readArrayItems(var1, this.fkInfo);
      this.triggerInfo = (TriggerInfo)var1.readObject();
      this.baseRowReadList = (FormatableBitSet)var1.readObject();
      this.baseRowReadMap = ArrayUtil.readIntArray(var1);
      this.streamStorableHeapColIds = ArrayUtil.readIntArray(var1);
      this.singleRowSource = var1.readBoolean();
      this.indexNames = ArrayUtil.readStringArray(var1);
      this.underMerge = var1.readBoolean();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeLong(this.conglomId);
      var1.writeObject(this.heapSCOCI);
      ArrayUtil.writeArray(var1, this.irgs);
      ArrayUtil.writeLongArray(var1, this.indexCIDS);
      ArrayUtil.writeArray(var1, this.indexSCOCIs);
      var1.writeBoolean(this.deferred);
      var1.writeObject(this.targetProperties);
      var1.writeObject(this.targetUUID);
      var1.writeInt(this.lockMode);
      ArrayUtil.writeArray(var1, this.fkInfo);
      var1.writeObject(this.triggerInfo);
      var1.writeObject(this.baseRowReadList);
      ArrayUtil.writeIntArray(var1, this.baseRowReadMap);
      ArrayUtil.writeIntArray(var1, this.streamStorableHeapColIds);
      var1.writeBoolean(this.singleRowSource);
      ArrayUtil.writeArray(var1, this.indexNames);
      var1.writeBoolean(this.underMerge);
   }

   public boolean underMerge() {
      return this.underMerge;
   }

   public long getConglomerateId() {
      return this.conglomId;
   }

   public Properties getTargetProperties() {
      return this.targetProperties;
   }

   public String getProperty(String var1) {
      return this.targetProperties == null ? null : this.targetProperties.getProperty(var1);
   }

   public FormatableBitSet getBaseRowReadList() {
      return this.baseRowReadList;
   }

   public int[] getBaseRowReadMap() {
      return this.baseRowReadMap;
   }

   public int[] getStreamStorableHeapColIds() {
      return this.streamStorableHeapColIds;
   }

   public String getIndexNameFromCID(long var1) {
      int var3 = this.indexCIDS.length;
      if (this.indexNames == null) {
         return null;
      } else {
         for(int var4 = 0; var4 < var3; ++var4) {
            if (this.indexCIDS[var4] == var1) {
               return this.indexNames[var4];
            }
         }

         return null;
      }
   }

   public String[] getIndexNames() {
      return this.indexNames;
   }
}
