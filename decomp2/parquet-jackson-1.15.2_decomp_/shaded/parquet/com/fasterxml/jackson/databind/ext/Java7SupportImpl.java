package shaded.parquet.com.fasterxml.jackson.databind.ext;

import java.beans.ConstructorProperties;
import java.beans.Transient;
import shaded.parquet.com.fasterxml.jackson.databind.PropertyName;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.Annotated;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;

public class Java7SupportImpl extends Java7Support {
   private final Class _bogus;

   public Java7SupportImpl() {
      Class<?> cls = Transient.class;
      cls = ConstructorProperties.class;
      this._bogus = cls;
   }

   public Boolean findTransient(Annotated a) {
      Transient t = (Transient)a.getAnnotation(Transient.class);
      return t != null ? t.value() : null;
   }

   public Boolean hasCreatorAnnotation(Annotated a) {
      ConstructorProperties props = (ConstructorProperties)a.getAnnotation(ConstructorProperties.class);
      return props != null ? Boolean.TRUE : null;
   }

   public PropertyName findConstructorName(AnnotatedParameter p) {
      AnnotatedWithParams ctor = p.getOwner();
      if (ctor != null) {
         ConstructorProperties props = (ConstructorProperties)ctor.getAnnotation(ConstructorProperties.class);
         if (props != null) {
            String[] names = props.value();
            int ix = p.getIndex();
            if (ix < names.length) {
               return PropertyName.construct(names[ix]);
            }
         }
      }

      return null;
   }
}
