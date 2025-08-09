package com.sun.research.ws.wadl;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"doc", "include", "any"}
)
@XmlRootElement(
   name = "grammars"
)
public class Grammars {
   protected List doc;
   protected List include;
   @XmlAnyElement(
      lax = true
   )
   protected List any;

   public List getDoc() {
      if (this.doc == null) {
         this.doc = new ArrayList();
      }

      return this.doc;
   }

   public List getInclude() {
      if (this.include == null) {
         this.include = new ArrayList();
      }

      return this.include;
   }

   public List getAny() {
      if (this.any == null) {
         this.any = new ArrayList();
      }

      return this.any;
   }
}
