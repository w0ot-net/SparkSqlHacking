package com.univocity.parsers.conversions;

public class ToStringConversion extends NullConversion {
   public ToStringConversion() {
   }

   public ToStringConversion(Object valueOnNullInput, Object valueOnNullOutput) {
      super(valueOnNullInput, valueOnNullOutput);
   }

   protected Object fromInput(Object input) {
      return input != null ? input.toString() : null;
   }

   protected Object undo(Object input) {
      return this.execute(input);
   }
}
