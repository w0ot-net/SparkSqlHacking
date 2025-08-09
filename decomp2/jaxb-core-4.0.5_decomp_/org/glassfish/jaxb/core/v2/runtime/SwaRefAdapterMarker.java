package org.glassfish.jaxb.core.v2.runtime;

import jakarta.activation.DataHandler;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class SwaRefAdapterMarker extends XmlAdapter {
   public DataHandler unmarshal(String v) throws Exception {
      throw new IllegalStateException("Not implemented");
   }

   public String marshal(DataHandler v) throws Exception {
      throw new IllegalStateException("Not implemented");
   }
}
