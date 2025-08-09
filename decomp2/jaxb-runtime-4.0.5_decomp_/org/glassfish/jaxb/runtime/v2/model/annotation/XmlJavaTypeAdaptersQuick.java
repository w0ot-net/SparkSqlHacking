package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlJavaTypeAdaptersQuick extends Quick implements XmlJavaTypeAdapters {
   private final XmlJavaTypeAdapters core;

   public XmlJavaTypeAdaptersQuick(Locatable upstream, XmlJavaTypeAdapters core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlJavaTypeAdaptersQuick(upstream, (XmlJavaTypeAdapters)core);
   }

   public Class annotationType() {
      return XmlJavaTypeAdapters.class;
   }

   public XmlJavaTypeAdapter[] value() {
      return this.core.value();
   }
}
