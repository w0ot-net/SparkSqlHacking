package org.glassfish.jersey.server.wadl.internal.generators;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "applicationDocs",
   propOrder = {"docs"}
)
@XmlRootElement(
   name = "applicationDocs"
)
public class ApplicationDocs {
   @XmlElement(
      name = "doc"
   )
   protected List docs;

   public List getDocs() {
      if (this.docs == null) {
         this.docs = new ArrayList();
      }

      return this.docs;
   }
}
