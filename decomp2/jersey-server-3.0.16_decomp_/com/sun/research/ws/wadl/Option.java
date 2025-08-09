package com.sun.research.ws.wadl;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"doc", "any"}
)
@XmlRootElement(
   name = "option"
)
public class Option {
   protected List doc;
   @XmlAnyElement(
      lax = true
   )
   protected List any;
   @XmlAttribute(
      name = "value",
      required = true
   )
   protected String value;
   @XmlAttribute(
      name = "mediaType"
   )
   protected String mediaType;
   @XmlAnyAttribute
   private Map otherAttributes = new HashMap();

   public List getDoc() {
      if (this.doc == null) {
         this.doc = new ArrayList();
      }

      return this.doc;
   }

   public List getAny() {
      if (this.any == null) {
         this.any = new ArrayList();
      }

      return this.any;
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getMediaType() {
      return this.mediaType;
   }

   public void setMediaType(String value) {
      this.mediaType = value;
   }

   public Map getOtherAttributes() {
      return this.otherAttributes;
   }
}
