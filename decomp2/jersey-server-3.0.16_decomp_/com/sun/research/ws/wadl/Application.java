package com.sun.research.ws.wadl;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"doc", "grammars", "resources", "resourceTypeOrMethodOrRepresentation", "any"}
)
@XmlRootElement(
   name = "application"
)
public class Application {
   protected List doc;
   protected Grammars grammars;
   protected List resources;
   @XmlElements({@XmlElement(
   name = "resource_type",
   type = ResourceType.class
), @XmlElement(
   name = "method",
   type = Method.class
), @XmlElement(
   name = "representation",
   type = Representation.class
), @XmlElement(
   name = "param",
   type = Param.class
)})
   protected List resourceTypeOrMethodOrRepresentation;
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

   public Grammars getGrammars() {
      return this.grammars;
   }

   public void setGrammars(Grammars value) {
      this.grammars = value;
   }

   public List getResources() {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      return this.resources;
   }

   public List getResourceTypeOrMethodOrRepresentation() {
      if (this.resourceTypeOrMethodOrRepresentation == null) {
         this.resourceTypeOrMethodOrRepresentation = new ArrayList();
      }

      return this.resourceTypeOrMethodOrRepresentation;
   }

   public List getAny() {
      if (this.any == null) {
         this.any = new ArrayList();
      }

      return this.any;
   }
}
