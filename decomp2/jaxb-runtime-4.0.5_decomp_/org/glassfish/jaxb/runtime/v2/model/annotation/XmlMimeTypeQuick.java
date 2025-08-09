package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlMimeType;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlMimeTypeQuick extends Quick implements XmlMimeType {
   private final XmlMimeType core;

   public XmlMimeTypeQuick(Locatable upstream, XmlMimeType core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlMimeTypeQuick(upstream, (XmlMimeType)core);
   }

   public Class annotationType() {
      return XmlMimeType.class;
   }

   public String value() {
      return this.core.value();
   }
}
