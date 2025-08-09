package org.glassfish.jaxb.runtime;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public final class AnyTypeAdapter extends XmlAdapter {
   public Object unmarshal(Object v) {
      return v;
   }

   public Object marshal(Object v) {
      return v;
   }
}
