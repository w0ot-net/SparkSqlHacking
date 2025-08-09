package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlInlineBinaryData;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlInlineBinaryDataQuick extends Quick implements XmlInlineBinaryData {
   private final XmlInlineBinaryData core;

   public XmlInlineBinaryDataQuick(Locatable upstream, XmlInlineBinaryData core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlInlineBinaryDataQuick(upstream, (XmlInlineBinaryData)core);
   }

   public Class annotationType() {
      return XmlInlineBinaryData.class;
   }
}
