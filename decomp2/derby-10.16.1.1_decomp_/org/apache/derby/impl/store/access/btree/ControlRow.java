package org.apache.derby.impl.store.access.btree;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.TypedFormat;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.raw.AuxObject;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.FetchDescriptor;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.SQLLongint;
import org.apache.derby.impl.store.access.StorableFormatId;
import org.apache.derby.shared.common.error.StandardException;

public abstract class ControlRow implements AuxObject, TypedFormat {
   private StorableFormatId version = null;
   private SQLLongint leftSiblingPageNumber;
   private SQLLongint rightSiblingPageNumber;
   private SQLLongint parentPageNumber;
   private SQLLongint level;
   private SQLLongint isRoot = null;
   private BTree btree = null;
   protected Page page;
   protected DataValueDescriptor[] row;
   protected DataValueDescriptor[] scratch_row;
   protected FetchDescriptor fetchDesc;
   protected transient boolean use_last_search_result_hint = false;
   protected transient int last_search_result = 0;
   protected static final int CR_COLID_FIRST = 0;
   protected static final int CR_VERSION_COLID = 0;
   protected static final int CR_LEFTSIB_COLID = 1;
   protected static final int CR_RIGHTSIB_COLID = 2;
   protected static final int CR_PARENT_COLID = 3;
   protected static final int CR_LEVEL_COLID = 4;
   protected static final int CR_ISROOT_COLID = 5;
   protected static final int CR_CONGLOM_COLID = 6;
   protected static final int CR_COLID_LAST = 6;
   protected static final int CR_NCOLUMNS = 7;
   protected static final FormatableBitSet CR_VERSION_BITSET = new FormatableBitSet(1);
   protected static final FormatableBitSet CR_LEFTSIB_BITSET = new FormatableBitSet(2);
   protected static final FormatableBitSet CR_RIGHTSIB_BITSET = new FormatableBitSet(3);
   protected static final FormatableBitSet CR_PARENT_BITSET = new FormatableBitSet(4);
   protected static final FormatableBitSet CR_LEVEL_BITSET = new FormatableBitSet(5);
   protected static final FormatableBitSet CR_ISROOT_BITSET = new FormatableBitSet(6);
   protected static final FormatableBitSet CR_CONGLOM_BITSET = new FormatableBitSet(7);
   public static final int SPLIT_FLAG_LAST_ON_PAGE = 1;
   public static final int SPLIT_FLAG_LAST_IN_TABLE = 2;
   public static final int SPLIT_FLAG_FIRST_ON_PAGE = 4;
   public static final int SPLIT_FLAG_FIRST_IN_TABLE = 8;
   protected static final int CR_SLOT = 0;

   protected ControlRow() {
      this.scratch_row = new DataValueDescriptor[this.getNumberOfControlRowColumns()];
      this.fetchDesc = new FetchDescriptor(this.scratch_row.length, (FormatableBitSet)null, (Qualifier[][])null);
   }

   protected ControlRow(OpenBTree var1, Page var2, int var3, ControlRow var4, boolean var5) throws StandardException {
      this.page = var2;
      this.leftSiblingPageNumber = new SQLLongint(-1L);
      this.rightSiblingPageNumber = new SQLLongint(-1L);
      this.parentPageNumber = new SQLLongint(var4 == null ? -1L : var4.page.getPageNumber());
      this.isRoot = new SQLLongint(var5 ? 1L : 0L);
      this.level = new SQLLongint((long)var3);
      this.version = new StorableFormatId(this.getTypeFormatId());
      this.btree = var5 ? var1.getConglomerate() : (BTree)Monitor.newInstanceFromIdentifier(var1.getConglomerate().getTypeFormatId());
      this.row = new DataValueDescriptor[this.getNumberOfControlRowColumns()];
      this.row[0] = this.version;
      this.row[1] = this.leftSiblingPageNumber;
      this.row[2] = this.rightSiblingPageNumber;
      this.row[3] = this.parentPageNumber;
      this.row[4] = this.level;
      this.row[5] = this.isRoot;
      this.row[6] = this.btree;
      var2.setAuxObject(this);
   }

   protected ControlRow(ContainerHandle var1, Page var2) throws StandardException {
      System.out.println("ControlRow construct 2.");
      this.page = var2;
   }

   protected int getVersion() throws StandardException {
      if (this.version == null) {
         this.version = new StorableFormatId();
         this.scratch_row[0] = this.version;
         this.fetchDesc.setValidColumns(CR_VERSION_BITSET);
         this.page.fetchFromSlot((RecordHandle)null, 0, this.scratch_row, this.fetchDesc, false);
      }

      return this.version.getValue();
   }

   protected void setVersion(int var1) throws StandardException {
      if (this.version == null) {
         this.version = new StorableFormatId();
      }

      this.version.setValue(var1);
      this.page.updateFieldAtSlot(0, 0, this.version, (LogicalUndo)null);
   }

   public ControlRow getLeftSibling(OpenBTree var1) throws StandardException, WaitError {
      long var3 = this.getleftSiblingPageNumber();
      if (var3 == -1L) {
         return null;
      } else {
         ControlRow var2 = getNoWait(var1, var3);
         if (var2 == null) {
            throw new WaitError();
         } else {
            return var2;
         }
      }
   }

   protected void setLeftSibling(ControlRow var1) throws StandardException {
      long var2 = var1 == null ? -1L : var1.page.getPageNumber();
      if (this.leftSiblingPageNumber == null) {
         this.leftSiblingPageNumber = new SQLLongint(var2);
      } else {
         this.leftSiblingPageNumber.setValue(var2);
      }

      try {
         this.page.updateFieldAtSlot(0, 1, this.leftSiblingPageNumber, (LogicalUndo)null);
      } catch (StandardException var5) {
         throw var5;
      }
   }

   protected ControlRow getRightSibling(OpenBTree var1) throws StandardException {
      long var2 = this.getrightSiblingPageNumber();
      return var2 == -1L ? null : get(var1, var2);
   }

   protected void setRightSibling(ControlRow var1) throws StandardException {
      long var2 = var1 == null ? -1L : var1.page.getPageNumber();
      if (this.rightSiblingPageNumber == null) {
         this.rightSiblingPageNumber = new SQLLongint(var2);
      } else {
         this.rightSiblingPageNumber.setValue(var2);
      }

      try {
         this.page.updateFieldAtSlot(0, 2, this.rightSiblingPageNumber, (LogicalUndo)null);
      } catch (StandardException var5) {
         throw var5;
      }
   }

   public long getleftSiblingPageNumber() throws StandardException {
      if (this.leftSiblingPageNumber == null) {
         this.leftSiblingPageNumber = new SQLLongint();
         this.scratch_row[1] = this.leftSiblingPageNumber;
         this.fetchDesc.setValidColumns(CR_LEFTSIB_BITSET);
         this.page.fetchFromSlot((RecordHandle)null, 0, this.scratch_row, this.fetchDesc, false);
      }

      return this.leftSiblingPageNumber.getLong();
   }

   protected long getrightSiblingPageNumber() throws StandardException {
      if (this.rightSiblingPageNumber == null) {
         this.rightSiblingPageNumber = new SQLLongint();
         this.scratch_row[2] = this.rightSiblingPageNumber;
         this.fetchDesc.setValidColumns(CR_RIGHTSIB_BITSET);
         this.page.fetchFromSlot((RecordHandle)null, 0, this.scratch_row, this.fetchDesc, false);
      }

      return this.rightSiblingPageNumber.getLong();
   }

   protected long getParentPageNumber() throws StandardException {
      if (this.parentPageNumber == null) {
         this.parentPageNumber = new SQLLongint();
         this.scratch_row[3] = this.parentPageNumber;
         this.fetchDesc.setValidColumns(CR_PARENT_BITSET);
         this.page.fetchFromSlot((RecordHandle)null, 0, this.scratch_row, this.fetchDesc, false);
      }

      long var1 = this.parentPageNumber.getLong();
      return var1;
   }

   void setParent(long var1) throws StandardException {
      if (this.parentPageNumber == null) {
         this.parentPageNumber = new SQLLongint();
      }

      this.parentPageNumber.setValue(var1);

      try {
         this.page.updateFieldAtSlot(0, 3, this.parentPageNumber, (LogicalUndo)null);
      } catch (StandardException var4) {
         throw var4;
      }
   }

   protected int getLevel() throws StandardException {
      if (this.level == null) {
         this.level = new SQLLongint();
         this.scratch_row[4] = this.level;
         this.fetchDesc.setValidColumns(CR_LEVEL_BITSET);
         this.page.fetchFromSlot((RecordHandle)null, 0, this.scratch_row, this.fetchDesc, false);
      }

      return (int)this.level.getLong();
   }

   protected void setLevel(int var1) throws StandardException {
      if (this.level == null) {
         this.level = new SQLLongint();
      }

      this.level.setValue((long)var1);
      this.page.updateFieldAtSlot(0, 4, this.level, (LogicalUndo)null);
   }

   protected boolean getIsRoot() throws StandardException {
      if (this.isRoot == null) {
         this.isRoot = new SQLLongint();
         this.scratch_row[5] = this.isRoot;
         this.fetchDesc.setValidColumns(CR_ISROOT_BITSET);
         this.page.fetchFromSlot((RecordHandle)null, 0, this.scratch_row, this.fetchDesc, false);
      }

      return this.isRoot.getLong() == 1L;
   }

   protected void setIsRoot(boolean var1) throws StandardException {
      if (this.isRoot == null) {
         this.isRoot = new SQLLongint();
      }

      this.isRoot.setValue(var1 ? 1 : 0);
      this.page.updateFieldAtSlot(0, 5, this.isRoot, (LogicalUndo)null);
   }

   public BTree getConglom(int var1) throws StandardException {
      if (this.btree == null) {
         this.btree = (BTree)Monitor.newInstanceFromIdentifier(var1);
         this.scratch_row[6] = this.btree;
         this.fetchDesc.setValidColumns(CR_CONGLOM_BITSET);
         this.page.fetchFromSlot((RecordHandle)null, 0, this.scratch_row, this.fetchDesc, false);
      }

      return this.btree;
   }

   public static ControlRow get(OpenBTree var0, long var1) throws StandardException {
      return get(var0.container, var1);
   }

   public static ControlRow get(ContainerHandle var0, long var1) throws StandardException {
      Page var3 = var0.getPage(var1);
      return getControlRowForPage(var0, var3);
   }

   public static ControlRow getNoWait(OpenBTree var0, long var1) throws StandardException {
      Page var3 = var0.container.getUserPageNoWait(var1);
      return var3 == null ? null : getControlRowForPage(var0.container, var3);
   }

   protected static ControlRow getControlRowForPage(ContainerHandle var0, Page var1) throws StandardException {
      ControlRow var2 = null;
      AuxObject var3 = var1.getAuxObject();
      if (var3 != null) {
         return (ControlRow)var3;
      } else {
         StorableFormatId var4 = new StorableFormatId();
         DataValueDescriptor[] var5 = new DataValueDescriptor[]{var4};
         var1.fetchFromSlot((RecordHandle)null, 0, var5, new FetchDescriptor(1, CR_VERSION_BITSET, (Qualifier[][])null), false);
         var2 = (ControlRow)Monitor.newInstanceFromIdentifier(var4.getValue());
         var2.page = var1;
         var2.controlRowInit();
         var1.setAuxObject(var2);
         return var2;
      }
   }

   public void release() {
      if (this.page != null) {
         this.page.unlatch();
      }

   }

   protected void searchForEntry(SearchParameters var1) throws StandardException {
      int var2 = 1;
      int var3 = this.page.recordCount() - 1;
      int var4 = 0;
      int var5 = var3 + 1;
      int var6;
      if (this.use_last_search_result_hint) {
         var6 = this.last_search_result == 0 ? 1 : this.last_search_result;
         if (var6 > var3) {
            var6 = var3;
         }
      } else {
         var6 = (var2 + var3) / 2;
      }

      for(; var4 != var5 - 1; var6 = (var2 + var3) / 2) {
         int var7 = compareIndexRowFromPageToKey(this, var6, var1.template, var1.searchKey, var1.btree.getConglomerate().nUniqueColumns, var1.partial_key_match_op, var1.btree.getConglomerate().ascDescInfo);
         if (var7 == 0) {
            var1.resultSlot = var6;
            var1.resultExact = true;
            this.use_last_search_result_hint = var6 == this.last_search_result;
            this.last_search_result = var6;
            return;
         }

         if (var7 > 0) {
            var5 = var6;
            var3 = var6 - 1;
         } else {
            var4 = var6;
            var2 = var6 + 1;
         }
      }

      this.use_last_search_result_hint = var4 == this.last_search_result;
      this.last_search_result = var4;
      var1.resultSlot = var4;
      var1.resultExact = false;
   }

   protected void searchForEntryBackward(SearchParameters var1) throws StandardException {
      int var2 = 1;
      int var3 = this.page.recordCount() - 1;
      int var4 = 0;
      int var5 = var3 + 1;
      int var6;
      if (this.use_last_search_result_hint) {
         var6 = this.last_search_result == 0 ? 1 : this.last_search_result;
         if (var6 > var3) {
            var6 = var3;
         }
      } else {
         var6 = (var2 + var3) / 2;
      }

      for(; var4 != var5 - 1; var6 = (var2 + var3) / 2) {
         int var7 = compareIndexRowFromPageToKey(this, var6, var1.template, var1.searchKey, var1.btree.getConglomerate().nUniqueColumns, var1.partial_key_match_op, var1.btree.getConglomerate().ascDescInfo);
         if (var7 == 0) {
            var1.resultSlot = var6;
            var1.resultExact = true;
            this.use_last_search_result_hint = var6 == this.last_search_result;
            this.last_search_result = var6;
            return;
         }

         if (var7 > 0) {
            var5 = var6;
            var3 = var6 - 1;
         } else {
            var4 = var6;
            var2 = var6 + 1;
         }
      }

      this.use_last_search_result_hint = var4 == this.last_search_result;
      this.last_search_result = var4;
      var1.resultSlot = var4;
      var1.resultExact = false;
   }

   public static int compareIndexRowFromPageToKey(ControlRow var0, int var1, DataValueDescriptor[] var2, DataValueDescriptor[] var3, int var4, int var5, boolean[] var6) throws StandardException {
      int var8 = var3.length;
      var0.page.fetchFromSlot((RecordHandle)null, var1, var2, (FetchDescriptor)null, true);

      for(int var9 = 0; var9 < var4; ++var9) {
         if (var9 >= var8) {
            return var5;
         }

         int var10 = var2[var9].compare(var3[var9]);
         if (var10 != 0) {
            if (var6[var9]) {
               return var10;
            }

            return -var10;
         }
      }

      return 0;
   }

   public static int compareIndexRowToKey(DataValueDescriptor[] var0, DataValueDescriptor[] var1, int var2, int var3, boolean[] var4) throws StandardException {
      int var5 = var1.length;

      for(int var6 = 0; var6 < var2; ++var6) {
         if (var6 >= var5) {
            return var3;
         }

         DataValueDescriptor var7 = var0[var6];
         DataValueDescriptor var8 = var1[var6];
         int var9 = var7.compare(var8);
         if (var9 != 0) {
            if (var4[var6]) {
               return var9;
            }

            return -var9;
         }
      }

      return 0;
   }

   protected void checkGeneric(OpenBTree var1, ControlRow var2, boolean var3) throws StandardException {
   }

   protected boolean checkRowOrder(OpenBTree var1, ControlRow var2) throws StandardException {
      return true;
   }

   protected boolean compareRowsOnSiblings(OpenBTree var1, ControlRow var2, ControlRow var3) throws StandardException {
      return true;
   }

   protected void checkSiblings(OpenBTree var1) throws StandardException {
   }

   void linkRight(OpenBTree var1, ControlRow var2) throws StandardException {
      ControlRow var3 = null;

      try {
         var3 = var2.getRightSibling(var1);
         this.setRightSibling(var3);
         this.setLeftSibling(var2);
         if (var3 != null) {
            var3.setLeftSibling(this);
         }

         var2.setRightSibling(this);
      } finally {
         if (var3 != null) {
            var3.release();
         }

      }

   }

   boolean unlink(OpenBTree var1) throws StandardException {
      ControlRow var2 = null;
      ControlRow var3 = null;

      try {
         try {
            var2 = this.getLeftSibling(var1);
         } catch (WaitError var9) {
            boolean var5 = false;
            return var5;
         }

         var3 = this.getRightSibling(var1);
         if (var2 != null) {
            var2.setRightSibling(var3);
         }

         if (var3 != null) {
            var3.setLeftSibling(var2);
         }

         var1.container.removePage(this.page);
         boolean var4 = true;
         return var4;
      } finally {
         if (var2 != null) {
            var2.release();
         }

         if (var3 != null) {
            var3.release();
         }

      }
   }

   public Page getPage() {
      return this.page;
   }

   protected final DataValueDescriptor[] getRow() {
      return this.row;
   }

   protected abstract int checkConsistency(OpenBTree var1, ControlRow var2, boolean var3) throws StandardException;

   protected abstract ControlRow getLeftChild(OpenBTree var1) throws StandardException;

   protected abstract ControlRow getRightChild(OpenBTree var1) throws StandardException;

   protected abstract void controlRowInit();

   public abstract boolean isLeftmostLeaf() throws StandardException;

   public abstract boolean isRightmostLeaf() throws StandardException;

   public abstract ControlRow search(SearchParameters var1) throws StandardException;

   protected abstract int getNumberOfControlRowColumns();

   protected abstract ControlRow searchLeft(OpenBTree var1) throws StandardException;

   protected abstract ControlRow searchRight(OpenBTree var1) throws StandardException;

   protected abstract boolean shrinkFor(OpenBTree var1, DataValueDescriptor[] var2) throws StandardException;

   protected abstract long splitFor(OpenBTree var1, DataValueDescriptor[] var2, BranchControlRow var3, DataValueDescriptor[] var4, int var5) throws StandardException;

   public abstract void printTree(OpenBTree var1) throws StandardException;

   public void auxObjectInvalidated() {
      this.version = null;
      this.leftSiblingPageNumber = null;
      this.rightSiblingPageNumber = null;
      this.parentPageNumber = null;
      this.level = null;
      this.isRoot = null;
      this.page = null;
   }

   public DataValueDescriptor[] getRowTemplate(OpenBTree var1) throws StandardException {
      return var1.getConglomerate().createTemplate(var1.getRawTran());
   }

   public String debugPage(OpenBTree var1) throws StandardException {
      Object var2 = null;
      return (String)var2;
   }

   public String toString() {
      return null;
   }

   static {
      CR_VERSION_BITSET.set(0);
      CR_LEFTSIB_BITSET.set(1);
      CR_RIGHTSIB_BITSET.set(2);
      CR_PARENT_BITSET.set(3);
      CR_LEVEL_BITSET.set(4);
      CR_ISROOT_BITSET.set(5);
      CR_CONGLOM_BITSET.set(6);
   }
}
