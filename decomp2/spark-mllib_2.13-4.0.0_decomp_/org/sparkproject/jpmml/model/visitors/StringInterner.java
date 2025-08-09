package org.sparkproject.jpmml.model.visitors;

public class StringInterner extends AttributeInterner {
   public StringInterner() {
      super(String.class);
   }

   public String intern(String string) {
      return string.intern();
   }
}
