package com.univocity.parsers.common;

public abstract class RetryableErrorHandler implements ProcessorErrorHandler {
   private Object defaultValue;
   private boolean skipRecord = true;

   public final void setDefaultValue(Object defaultValue) {
      this.defaultValue = defaultValue;
      this.keepRecord();
   }

   public final void keepRecord() {
      this.skipRecord = false;
   }

   public final Object getDefaultValue() {
      return this.defaultValue;
   }

   final void prepareToRun() {
      this.skipRecord = true;
      this.defaultValue = null;
   }

   public final boolean isRecordSkipped() {
      return this.skipRecord;
   }
}
