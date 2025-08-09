package com.univocity.parsers.common.processor;

import java.util.Collections;
import java.util.List;

public class MasterDetailRecord implements Cloneable {
   private Object[] masterRow = null;
   private List detailRows = Collections.emptyList();

   public Object[] getMasterRow() {
      return this.masterRow;
   }

   public void setMasterRow(Object[] masterRow) {
      this.masterRow = masterRow;
   }

   public List getDetailRows() {
      return this.detailRows;
   }

   public void setDetailRows(List detailRows) {
      this.detailRows = detailRows;
   }

   public void clear() {
      this.detailRows = Collections.emptyList();
      this.masterRow = null;
   }

   public MasterDetailRecord clone() {
      try {
         return (MasterDetailRecord)super.clone();
      } catch (CloneNotSupportedException e) {
         throw new InternalError(e.getMessage());
      }
   }
}
