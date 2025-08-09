package org.joda.time;

public class IllegalFieldValueException extends IllegalArgumentException {
   private static final long serialVersionUID = 6305711765985447737L;
   private final DateTimeFieldType iDateTimeFieldType;
   private final DurationFieldType iDurationFieldType;
   private final String iFieldName;
   private final Number iNumberValue;
   private final String iStringValue;
   private final Number iLowerBound;
   private final Number iUpperBound;
   private String iMessage;

   private static String createMessage(String var0, Number var1, Number var2, Number var3, String var4) {
      StringBuilder var5 = (new StringBuilder()).append("Value ").append(var1).append(" for ").append(var0).append(' ');
      if (var2 == null) {
         if (var3 == null) {
            var5.append("is not supported");
         } else {
            var5.append("must not be larger than ").append(var3);
         }
      } else if (var3 == null) {
         var5.append("must not be smaller than ").append(var2);
      } else {
         var5.append("must be in the range [").append(var2).append(',').append(var3).append(']');
      }

      if (var4 != null) {
         var5.append(": ").append(var4);
      }

      return var5.toString();
   }

   private static String createMessage(String var0, String var1) {
      StringBuffer var2 = (new StringBuffer()).append("Value ");
      if (var1 == null) {
         var2.append("null");
      } else {
         var2.append('"');
         var2.append(var1);
         var2.append('"');
      }

      var2.append(" for ").append(var0).append(' ').append("is not supported");
      return var2.toString();
   }

   public IllegalFieldValueException(DateTimeFieldType var1, Number var2, Number var3, Number var4) {
      super(createMessage(var1.getName(), var2, var3, var4, (String)null));
      this.iDateTimeFieldType = var1;
      this.iDurationFieldType = null;
      this.iFieldName = var1.getName();
      this.iNumberValue = var2;
      this.iStringValue = null;
      this.iLowerBound = var3;
      this.iUpperBound = var4;
      this.iMessage = super.getMessage();
   }

   public IllegalFieldValueException(DateTimeFieldType var1, Number var2, Number var3, Number var4, String var5) {
      super(createMessage(var1.getName(), var2, var3, var4, var5));
      this.iDateTimeFieldType = var1;
      this.iDurationFieldType = null;
      this.iFieldName = var1.getName();
      this.iNumberValue = var2;
      this.iStringValue = null;
      this.iLowerBound = var3;
      this.iUpperBound = var4;
      this.iMessage = super.getMessage();
   }

   public IllegalFieldValueException(DateTimeFieldType var1, Number var2, String var3) {
      super(createMessage(var1.getName(), var2, (Number)null, (Number)null, var3));
      this.iDateTimeFieldType = var1;
      this.iDurationFieldType = null;
      this.iFieldName = var1.getName();
      this.iNumberValue = var2;
      this.iStringValue = null;
      this.iLowerBound = null;
      this.iUpperBound = null;
      this.iMessage = super.getMessage();
   }

   public IllegalFieldValueException(DurationFieldType var1, Number var2, Number var3, Number var4) {
      super(createMessage(var1.getName(), var2, var3, var4, (String)null));
      this.iDateTimeFieldType = null;
      this.iDurationFieldType = var1;
      this.iFieldName = var1.getName();
      this.iNumberValue = var2;
      this.iStringValue = null;
      this.iLowerBound = var3;
      this.iUpperBound = var4;
      this.iMessage = super.getMessage();
   }

   public IllegalFieldValueException(String var1, Number var2, Number var3, Number var4) {
      super(createMessage(var1, var2, var3, var4, (String)null));
      this.iDateTimeFieldType = null;
      this.iDurationFieldType = null;
      this.iFieldName = var1;
      this.iNumberValue = var2;
      this.iStringValue = null;
      this.iLowerBound = var3;
      this.iUpperBound = var4;
      this.iMessage = super.getMessage();
   }

   public IllegalFieldValueException(DateTimeFieldType var1, String var2) {
      super(createMessage(var1.getName(), var2));
      this.iDateTimeFieldType = var1;
      this.iDurationFieldType = null;
      this.iFieldName = var1.getName();
      this.iStringValue = var2;
      this.iNumberValue = null;
      this.iLowerBound = null;
      this.iUpperBound = null;
      this.iMessage = super.getMessage();
   }

   public IllegalFieldValueException(DurationFieldType var1, String var2) {
      super(createMessage(var1.getName(), var2));
      this.iDateTimeFieldType = null;
      this.iDurationFieldType = var1;
      this.iFieldName = var1.getName();
      this.iStringValue = var2;
      this.iNumberValue = null;
      this.iLowerBound = null;
      this.iUpperBound = null;
      this.iMessage = super.getMessage();
   }

   public IllegalFieldValueException(String var1, String var2) {
      super(createMessage(var1, var2));
      this.iDateTimeFieldType = null;
      this.iDurationFieldType = null;
      this.iFieldName = var1;
      this.iStringValue = var2;
      this.iNumberValue = null;
      this.iLowerBound = null;
      this.iUpperBound = null;
      this.iMessage = super.getMessage();
   }

   public DateTimeFieldType getDateTimeFieldType() {
      return this.iDateTimeFieldType;
   }

   public DurationFieldType getDurationFieldType() {
      return this.iDurationFieldType;
   }

   public String getFieldName() {
      return this.iFieldName;
   }

   public Number getIllegalNumberValue() {
      return this.iNumberValue;
   }

   public String getIllegalStringValue() {
      return this.iStringValue;
   }

   public String getIllegalValueAsString() {
      String var1 = this.iStringValue;
      if (var1 == null) {
         var1 = String.valueOf(this.iNumberValue);
      }

      return var1;
   }

   public Number getLowerBound() {
      return this.iLowerBound;
   }

   public Number getUpperBound() {
      return this.iUpperBound;
   }

   public String getMessage() {
      return this.iMessage;
   }

   public void prependMessage(String var1) {
      if (this.iMessage == null) {
         this.iMessage = var1;
      } else if (var1 != null) {
         this.iMessage = var1 + ": " + this.iMessage;
      }

   }
}
