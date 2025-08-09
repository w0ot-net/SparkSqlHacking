package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlList;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlListQuick extends Quick implements XmlList {
   private final XmlList core;

   public XmlListQuick(Locatable upstream, XmlList core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlListQuick(upstream, (XmlList)core);
   }

   public Class annotationType() {
      return XmlList.class;
   }
}
