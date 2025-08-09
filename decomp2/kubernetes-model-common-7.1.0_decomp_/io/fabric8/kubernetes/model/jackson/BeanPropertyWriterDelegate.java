package io.fabric8.kubernetes.model.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import java.util.Map;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanPropertyWriterDelegate extends BeanPropertyWriter {
   private static final Logger logger = LoggerFactory.getLogger(BeanPropertyWriterDelegate.class);
   private final BeanPropertyWriter delegate;
   private final AnnotatedMember anyGetter;
   private final transient Supplier logDuplicateWarning;

   BeanPropertyWriterDelegate(BeanPropertyWriter delegate, AnnotatedMember anyGetter, Supplier logDuplicateWarning) {
      super(delegate);
      this.delegate = delegate;
      this.anyGetter = anyGetter;
      this.logDuplicateWarning = logDuplicateWarning;
   }

   public void serializeAsField(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
      Object valueInAnyGetter = null;
      if (this.anyGetter != null) {
         Object anyGetterValue = this.anyGetter.getValue(bean);
         if (anyGetterValue != null) {
            valueInAnyGetter = ((Map)anyGetterValue).get(this.delegate.getName());
         }
      }

      if (valueInAnyGetter == null) {
         this.delegate.serializeAsField(bean, gen, prov);
      } else if (Boolean.TRUE.equals(this.logDuplicateWarning.get())) {
         logger.warn("Value in field '{}' ignored in favor of value in additionalProperties ({}) for {}", new Object[]{this.delegate.getName(), valueInAnyGetter, bean.getClass().getName()});
      }

   }

   public void assignNullSerializer(JsonSerializer nullSer) {
      this.delegate.assignNullSerializer(nullSer);
   }

   public void assignSerializer(JsonSerializer ser) {
      this.delegate.assignSerializer(ser);
   }

   public void assignTypeSerializer(TypeSerializer typeSer) {
      this.delegate.assignTypeSerializer(typeSer);
   }
}
