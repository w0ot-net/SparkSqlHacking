package com.univocity.parsers.common;

public abstract class ContextSnapshot extends ContextWrapper {
   private final int currentColumn;
   private final long currentRecord;

   public ContextSnapshot(Context context) {
      super(context);
      this.currentColumn = context.currentColumn();
      this.currentRecord = context.currentRecord();
   }

   public int currentColumn() {
      return this.currentColumn;
   }

   public long currentRecord() {
      return this.currentRecord;
   }
}
