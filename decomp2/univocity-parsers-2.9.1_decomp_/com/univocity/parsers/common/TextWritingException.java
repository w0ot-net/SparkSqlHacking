package com.univocity.parsers.common;

public class TextWritingException extends AbstractException {
   private static final long serialVersionUID = 7198462597717255519L;
   private final long recordCount;
   private final Object[] recordData;
   private final String recordCharacters;

   private TextWritingException(String message, long recordCount, Object[] row, String recordCharacters, Throwable cause) {
      super(message, cause);
      this.recordCount = recordCount;
      this.recordData = row;
      this.recordCharacters = recordCharacters;
   }

   public TextWritingException(String message, long recordCount, String recordCharacters, Throwable cause) {
      this(message, recordCount, (Object[])null, recordCharacters, cause);
   }

   public TextWritingException(String message, long recordCount, Object[] row, Throwable cause) {
      this(message, recordCount, row, (String)null, cause);
   }

   public TextWritingException(String message) {
      this(message, 0L, (Object[])null, (String)null, (Throwable)null);
   }

   public TextWritingException(Throwable cause) {
      this(cause != null ? cause.getMessage() : null, 0L, (Object[])null, (String)null, cause);
   }

   public TextWritingException(String message, long line, Object[] row) {
      this(message, line, (Object[])row, (Throwable)null);
   }

   public TextWritingException(String message, long line, String recordCharacters) {
      this(message, line, (Object[])null, recordCharacters, (Throwable)null);
   }

   public long getRecordCount() {
      return this.recordCount;
   }

   public Object[] getRecordData() {
      return this.restrictContent(this.recordData);
   }

   public String getRecordCharacters() {
      return this.errorContentLength == 0 ? null : this.recordCharacters;
   }

   protected String getDetails() {
      String details = "";
      details = printIfNotEmpty(details, "recordCount", this.recordCount);
      details = printIfNotEmpty(details, "recordData", this.restrictContent(this.recordData));
      details = printIfNotEmpty(details, "recordCharacters", this.restrictContent(this.recordCharacters));
      return details;
   }

   protected String getErrorDescription() {
      return "Error writing data";
   }
}
