package com.fasterxml.jackson.datatype.jsr310.deser.key;

/** @deprecated */
@Deprecated
public class YearMothKeyDeserializer extends YearMonthKeyDeserializer {
   public static final YearMothKeyDeserializer INSTANCE = new YearMothKeyDeserializer();

   private YearMothKeyDeserializer() {
   }
}
