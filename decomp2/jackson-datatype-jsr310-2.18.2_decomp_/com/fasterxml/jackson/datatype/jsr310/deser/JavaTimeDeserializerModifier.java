package com.fasterxml.jackson.datatype.jsr310.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import java.time.Month;

public class JavaTimeDeserializerModifier extends BeanDeserializerModifier {
   private static final long serialVersionUID = 1L;
   private final boolean _oneBaseMonths;

   public JavaTimeDeserializerModifier(boolean oneBaseMonths) {
      this._oneBaseMonths = oneBaseMonths;
   }

   public JsonDeserializer modifyEnumDeserializer(DeserializationConfig config, JavaType type, BeanDescription beanDesc, JsonDeserializer defaultDeserializer) {
      return (JsonDeserializer)(this._oneBaseMonths && type.hasRawClass(Month.class) ? new OneBasedMonthDeserializer(defaultDeserializer) : defaultDeserializer);
   }
}
