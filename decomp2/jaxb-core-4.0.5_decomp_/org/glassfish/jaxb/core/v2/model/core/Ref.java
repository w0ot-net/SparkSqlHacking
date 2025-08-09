package org.glassfish.jaxb.core.v2.model.core;

import jakarta.xml.bind.annotation.XmlList;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.glassfish.jaxb.core.v2.model.annotation.AnnotationReader;
import org.glassfish.jaxb.core.v2.model.impl.ModelBuilderI;
import org.glassfish.jaxb.core.v2.model.nav.Navigator;

public final class Ref {
   public final Object type;
   public final Adapter adapter;
   public final boolean valueList;

   public Ref(Object type) {
      this(type, (Adapter)null, false);
   }

   public Ref(Object type, Adapter adapter, boolean valueList) {
      this.adapter = adapter;
      if (adapter != null) {
         type = (T)adapter.defaultType;
      }

      this.type = type;
      this.valueList = valueList;
   }

   public Ref(ModelBuilderI builder, Object type, XmlJavaTypeAdapter xjta, XmlList xl) {
      this(builder.getReader(), builder.getNavigator(), type, xjta, xl);
   }

   public Ref(AnnotationReader reader, Navigator nav, Object type, XmlJavaTypeAdapter xjta, XmlList xl) {
      Adapter<T, C> adapter = null;
      if (xjta != null) {
         adapter = new Adapter(xjta, reader, nav);
         type = (T)adapter.defaultType;
      }

      this.type = type;
      this.adapter = adapter;
      this.valueList = xl != null;
   }
}
