package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlJavaTypeAdapterQuick extends Quick implements XmlJavaTypeAdapter {
   private final XmlJavaTypeAdapter core;

   public XmlJavaTypeAdapterQuick(Locatable upstream, XmlJavaTypeAdapter core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlJavaTypeAdapterQuick(upstream, (XmlJavaTypeAdapter)core);
   }

   public Class annotationType() {
      return XmlJavaTypeAdapter.class;
   }

   public Class type() {
      return this.core.type();
   }

   public Class value() {
      return this.core.value();
   }
}
