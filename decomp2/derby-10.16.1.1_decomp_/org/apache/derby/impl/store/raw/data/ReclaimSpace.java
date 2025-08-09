package org.apache.derby.impl.store.raw.data;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.PageTimeStamp;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.data.DataFactory;
import org.apache.derby.shared.common.error.StandardException;

public final class ReclaimSpace implements Serviceable {
   private boolean serviceASAP;
   private ContainerKey containerId;
   private PageKey pageId;
   private RecordHandle headRowHandle;
   private int columnId;
   private long columnPageId;
   private int columnRecordId;
   private PageTimeStamp timeStamp;
   private int attempts;
   private DataFactory processor;
   private int reclaim;
   public static final int CONTAINER = 1;
   public static final int PAGE = 2;
   public static final int ROW_RESERVE = 3;
   public static final int COLUMN_CHAIN = 4;

   private void initContainerInfo(ContainerKey var1, int var2, DataFactory var3, boolean var4) {
      this.containerId = var1;
      this.reclaim = var2;
      this.attempts = 0;
      this.processor = var3;
      this.serviceASAP = var4;
   }

   public ReclaimSpace(int var1, ContainerKey var2, DataFactory var3, boolean var4) {
      this.initContainerInfo(var2, var1, var3, var4);
   }

   public ReclaimSpace(int var1, PageKey var2, DataFactory var3, boolean var4) {
      this.initContainerInfo(var2.getContainerId(), var1, var3, var4);
      this.pageId = var2;
   }

   public ReclaimSpace(int var1, RecordHandle var2, DataFactory var3, boolean var4) {
      this.initContainerInfo(var2.getContainerId(), var1, var3, var4);
      this.headRowHandle = var2;
   }

   public ReclaimSpace(int var1, RecordHandle var2, int var3, long var4, int var6, PageTimeStamp var7, DataFactory var8, boolean var9) {
      this.initContainerInfo(var2.getContainerId(), var1, var8, var9);
      this.headRowHandle = var2;
      this.columnId = var3;
      this.columnPageId = var4;
      this.columnRecordId = var6;
      this.timeStamp = var7;
   }

   public boolean serviceASAP() {
      return this.serviceASAP;
   }

   public int performWork(ContextManager var1) throws StandardException {
      return this.processor.reclaimSpace(this, var1);
   }

   public boolean serviceImmediately() {
      return true;
   }

   public final ContainerKey getContainerId() {
      return this.containerId;
   }

   public final PageKey getPageId() {
      return this.pageId;
   }

   public final RecordHandle getHeadRowHandle() {
      return this.headRowHandle;
   }

   public final int getColumnId() {
      return this.columnId;
   }

   public final long getColumnPageId() {
      return this.columnPageId;
   }

   public final int getColumnRecordId() {
      return this.columnRecordId;
   }

   public final PageTimeStamp getPageTimeStamp() {
      return this.timeStamp;
   }

   public final int reclaimWhat() {
      return this.reclaim;
   }

   public final int incrAttempts() {
      return ++this.attempts;
   }

   public String toString() {
      return null;
   }
}
