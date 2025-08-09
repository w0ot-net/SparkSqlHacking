package org.sparkproject.jpmml.model.visitors;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.agent.InstrumentationProvider;
import org.sparkproject.jpmml.model.ReflectionUtil;

public class MemoryMeasurer extends AbstractVisitor implements Resettable {
   private Instrumentation instrumentation = InstrumentationProvider.getInstrumentation();
   private long size = 0L;
   private Set objects = Collections.newSetFromMap(new IdentityHashMap());

   public void reset() {
      this.size = 0L;
      this.objects.clear();
   }

   public VisitorAction visit(PMMLObject object) {
      this.measure(object);
      return super.visit(object);
   }

   public long getSize() {
      return this.size;
   }

   public Set getObjects() {
      return this.objects;
   }

   private void measure(Object object) {
      boolean status = this.objects.add(object);
      if (status) {
         this.size += this.instrumentation.getObjectSize(object);
         Class<?> clazz = object.getClass();
         if (!ReflectionUtil.isPrimitiveWrapper(clazz)) {
            for(Field field : ReflectionUtil.getFields(clazz)) {
               Class<?> type = field.getType();
               if (!type.isPrimitive()) {
                  Object value = ReflectionUtil.getFieldValue(field, object);
                  if (shouldMeasure(value)) {
                     this.measure(value);
                  }
               }
            }

            if (object instanceof Object[]) {
               Object[] values = object;

               for(int i = 0; i < values.length; ++i) {
                  Object value = values[i];
                  if (shouldMeasure(value)) {
                     this.measure(value);
                  }
               }
            }

         }
      }
   }

   private static boolean shouldMeasure(Object object) {
      if (object != null) {
         if (object instanceof Enum) {
            return false;
         } else {
            return !(object instanceof PMMLObject);
         }
      } else {
         return false;
      }
   }
}
