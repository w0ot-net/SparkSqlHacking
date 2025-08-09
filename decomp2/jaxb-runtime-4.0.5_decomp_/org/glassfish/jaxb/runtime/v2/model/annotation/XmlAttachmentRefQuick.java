package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlAttachmentRef;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlAttachmentRefQuick extends Quick implements XmlAttachmentRef {
   private final XmlAttachmentRef core;

   public XmlAttachmentRefQuick(Locatable upstream, XmlAttachmentRef core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlAttachmentRefQuick(upstream, (XmlAttachmentRef)core);
   }

   public Class annotationType() {
      return XmlAttachmentRef.class;
   }
}
