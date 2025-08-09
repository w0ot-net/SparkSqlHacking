package com.fasterxml.jackson.datatype.jsr310.ser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import java.time.Month;

public class JavaTimeSerializerModifier extends BeanSerializerModifier {
   private static final long serialVersionUID = 1L;
   private final boolean _oneBaseMonths;

   public JavaTimeSerializerModifier(boolean oneBaseMonths) {
      this._oneBaseMonths = oneBaseMonths;
   }

   public JsonSerializer modifyEnumSerializer(SerializationConfig config, JavaType valueType, BeanDescription beanDesc, JsonSerializer serializer) {
      return (JsonSerializer)(this._oneBaseMonths && valueType.hasRawClass(Month.class) ? new OneBasedMonthSerializer(serializer) : serializer);
   }
}
