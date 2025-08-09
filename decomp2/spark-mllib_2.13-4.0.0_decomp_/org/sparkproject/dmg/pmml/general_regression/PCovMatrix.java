package org.sparkproject.dmg.pmml.general_regression;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.StringValue;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "PCovMatrix",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "pCovCells"}
)
@JsonRootName("PCovMatrix")
@JsonPropertyOrder({"type", "extensions", "pCovCells"})
public class PCovMatrix extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "type"
   )
   @JsonProperty("type")
   @Added(Version.PMML_3_2)
   private Type type;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "PCovCell",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("PCovCell")
   @CollectionElementType(PCovCell.class)
   private List pCovCells;
   private static final long serialVersionUID = 67371272L;

   public PCovMatrix() {
   }

   @ValueConstructor
   public PCovMatrix(@Property("pCovCells") List pCovCells) {
      this.pCovCells = pCovCells;
   }

   public Type getType() {
      return this.type;
   }

   public PCovMatrix setType(@Property("type") Type type) {
      this.type = type;
      return this;
   }

   public boolean hasExtensions() {
      return this.extensions != null && !this.extensions.isEmpty();
   }

   public List getExtensions() {
      if (this.extensions == null) {
         this.extensions = new ArrayList();
      }

      return this.extensions;
   }

   public PCovMatrix addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasPCovCells() {
      return this.pCovCells != null && !this.pCovCells.isEmpty();
   }

   public List requirePCovCells() {
      if (this.pCovCells != null && !this.pCovCells.isEmpty()) {
         return this.pCovCells;
      } else {
         throw new MissingElementException(this, PMMLElements.PCOVMATRIX_PCOVCELLS);
      }
   }

   public List getPCovCells() {
      if (this.pCovCells == null) {
         this.pCovCells = new ArrayList();
      }

      return this.pCovCells;
   }

   public PCovMatrix addPCovCells(PCovCell... pCovCells) {
      this.getPCovCells().addAll(Arrays.asList(pCovCells));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasPCovCells()) {
            status = PMMLObject.traverse(visitor, this.getPCovCells());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum Type implements StringValue {
      @XmlEnumValue("model")
      @JsonProperty("model")
      MODEL("model"),
      @XmlEnumValue("robust")
      @JsonProperty("robust")
      ROBUST("robust");

      private final String value;

      private Type(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Type fromValue(String v) {
         for(Type c : values()) {
            if (c.value.equals(v)) {
               return c;
            }
         }

         throw new IllegalArgumentException(v);
      }

      public String toString() {
         return this.value();
      }
   }
}
