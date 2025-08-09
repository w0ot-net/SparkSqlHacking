package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Interval",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("Interval")
@JsonPropertyOrder({"closure", "leftMargin", "rightMargin", "extensions"})
public class Interval extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "closure",
      required = true
   )
   @JsonProperty("closure")
   private Closure closure;
   @XmlAttribute(
      name = "leftMargin"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("leftMargin")
   private Number leftMargin;
   @XmlAttribute(
      name = "rightMargin"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("rightMargin")
   private Number rightMargin;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public Interval() {
   }

   @ValueConstructor
   public Interval(@Property("closure") Closure closure) {
      this.closure = closure;
   }

   public Closure requireClosure() {
      if (this.closure == null) {
         throw new MissingAttributeException(this, PMMLAttributes.INTERVAL_CLOSURE);
      } else {
         return this.closure;
      }
   }

   public Closure getClosure() {
      return this.closure;
   }

   public Interval setClosure(@Property("closure") Closure closure) {
      this.closure = closure;
      return this;
   }

   public Number getLeftMargin() {
      return this.leftMargin;
   }

   public Interval setLeftMargin(@Property("leftMargin") Number leftMargin) {
      this.leftMargin = leftMargin;
      return this;
   }

   public Number getRightMargin() {
      return this.rightMargin;
   }

   public Interval setRightMargin(@Property("rightMargin") Number rightMargin) {
      this.rightMargin = rightMargin;
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

   public Interval addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum Closure implements StringValue {
      @XmlEnumValue("openClosed")
      @JsonProperty("openClosed")
      OPEN_CLOSED("openClosed"),
      @XmlEnumValue("openOpen")
      @JsonProperty("openOpen")
      OPEN_OPEN("openOpen"),
      @XmlEnumValue("closedOpen")
      @JsonProperty("closedOpen")
      CLOSED_OPEN("closedOpen"),
      @XmlEnumValue("closedClosed")
      @JsonProperty("closedClosed")
      CLOSED_CLOSED("closedClosed");

      private final String value;

      private Closure(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Closure fromValue(String v) {
         for(Closure c : values()) {
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
