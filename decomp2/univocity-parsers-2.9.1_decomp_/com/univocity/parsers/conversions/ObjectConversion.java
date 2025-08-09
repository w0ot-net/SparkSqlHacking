package com.univocity.parsers.conversions;

public abstract class ObjectConversion extends NullConversion {
   public ObjectConversion() {
      super((Object)null, (Object)null);
   }

   public ObjectConversion(Object valueIfStringIsNull, String valueIfObjectIsNull) {
      super(valueIfStringIsNull, valueIfObjectIsNull);
   }

   public Object execute(String input) {
      return super.execute(input);
   }

   protected final Object fromInput(String input) {
      return this.fromString(input);
   }

   protected abstract Object fromString(String var1);

   public String revert(Object input) {
      return (String)super.revert(input);
   }

   protected final String undo(Object input) {
      return String.valueOf(input);
   }

   public Object getValueIfStringIsNull() {
      return this.getValueOnNullInput();
   }

   public String getValueIfObjectIsNull() {
      return (String)this.getValueOnNullOutput();
   }

   public void setValueIfStringIsNull(Object valueIfStringIsNull) {
      this.setValueOnNullInput(valueIfStringIsNull);
   }

   public void setValueIfObjectIsNull(String valueIfObjectIsNull) {
      this.setValueOnNullOutput(valueIfObjectIsNull);
   }
}
