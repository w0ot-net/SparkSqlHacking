package org.glassfish.jaxb.core.v2.model.core;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.glassfish.jaxb.core.v2.model.annotation.AnnotationReader;
import org.glassfish.jaxb.core.v2.model.nav.Navigator;

public class Adapter {
   public final Object adapterType;
   public final Object defaultType;
   public final Object customType;

   public Adapter(XmlJavaTypeAdapter spec, AnnotationReader reader, Navigator nav) {
      this(nav.asDecl(reader.getClassValue(spec, "value")), nav);
   }

   public Adapter(Object adapterType, Navigator nav) {
      this.adapterType = adapterType;
      TypeT baseClass = (TypeT)nav.getBaseClass(nav.use(adapterType), nav.asDecl(XmlAdapter.class));

      assert baseClass != null;

      if (nav.isParameterizedType(baseClass)) {
         this.defaultType = nav.getTypeArgument(baseClass, 0);
      } else {
         this.defaultType = nav.ref(Object.class);
      }

      if (nav.isParameterizedType(baseClass)) {
         this.customType = nav.getTypeArgument(baseClass, 1);
      } else {
         this.customType = nav.ref(Object.class);
      }

   }
}
