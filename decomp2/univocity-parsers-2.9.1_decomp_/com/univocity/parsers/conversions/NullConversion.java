package com.univocity.parsers.conversions;

public abstract class NullConversion implements Conversion {
   private Object valueOnNullInput;
   private Object valueOnNullOutput;

   public NullConversion() {
      this((Object)null, (Object)null);
   }

   public NullConversion(Object valueOnNullInput, Object valueOnNullOutput) {
      this.valueOnNullInput = valueOnNullInput;
      this.valueOnNullOutput = valueOnNullOutput;
   }

   public Object execute(Object input) {
      return input == null ? this.valueOnNullInput : this.fromInput(input);
   }

   protected abstract Object fromInput(Object var1);

   public Object revert(Object input) {
      return input == null ? this.valueOnNullOutput : this.undo(input);
   }

   protected abstract Object undo(Object var1);

   public Object getValueOnNullInput() {
      return this.valueOnNullInput;
   }

   public Object getValueOnNullOutput() {
      return this.valueOnNullOutput;
   }

   public void setValueOnNullInput(Object valueOnNullInput) {
      this.valueOnNullInput = valueOnNullInput;
   }

   public void setValueOnNullOutput(Object valueOnNullOutput) {
      this.valueOnNullOutput = valueOnNullOutput;
   }
}
