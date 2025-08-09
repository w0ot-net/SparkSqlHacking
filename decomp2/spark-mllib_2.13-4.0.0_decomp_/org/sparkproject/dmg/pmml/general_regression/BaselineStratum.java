package org.sparkproject.dmg.pmml.general_regression;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.HasValue;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "BaselineStratum",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "baselineCells"}
)
@JsonRootName("BaselineStratum")
@JsonPropertyOrder({"value", "label", "maxTime", "extensions", "baselineCells"})
@Added(Version.PMML_4_0)
public class BaselineStratum extends PMMLObject implements HasExtensions, HasValue {
   @XmlAttribute(
      name = "value",
      required = true
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("value")
   private Object value;
   @XmlAttribute(
      name = "label"
   )
   @JsonProperty("label")
   private String label;
   @XmlAttribute(
      name = "maxTime",
      required = true
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("maxTime")
   private Number maxTime;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "BaselineCell",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("BaselineCell")
   @CollectionElementType(BaselineCell.class)
   private List baselineCells;
   private static final long serialVersionUID = 67371272L;

   public BaselineStratum() {
   }

   @ValueConstructor
   public BaselineStratum(@Property("value") Object value, @Property("maxTime") Number maxTime) {
      this.value = value;
      this.maxTime = maxTime;
   }

   public Object requireValue() {
      if (this.value == null) {
         throw new MissingAttributeException(this, PMMLAttributes.BASELINESTRATUM_VALUE);
      } else {
         return this.value;
      }
   }

   public Object getValue() {
      return this.value;
   }

   public BaselineStratum setValue(@Property("value") Object value) {
      this.value = value;
      return this;
   }

   public String getLabel() {
      return this.label;
   }

   public BaselineStratum setLabel(@Property("label") String label) {
      this.label = label;
      return this;
   }

   public Number requireMaxTime() {
      if (this.maxTime == null) {
         throw new MissingAttributeException(this, PMMLAttributes.BASELINESTRATUM_MAXTIME);
      } else {
         return this.maxTime;
      }
   }

   public Number getMaxTime() {
      return this.maxTime;
   }

   public BaselineStratum setMaxTime(@Property("maxTime") Number maxTime) {
      this.maxTime = maxTime;
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

   public BaselineStratum addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasBaselineCells() {
      return this.baselineCells != null && !this.baselineCells.isEmpty();
   }

   public List requireBaselineCells() {
      if (this.baselineCells != null && !this.baselineCells.isEmpty()) {
         return this.baselineCells;
      } else {
         throw new MissingElementException(this, PMMLElements.BASELINESTRATUM_BASELINECELLS);
      }
   }

   public List getBaselineCells() {
      if (this.baselineCells == null) {
         this.baselineCells = new ArrayList();
      }

      return this.baselineCells;
   }

   public BaselineStratum addBaselineCells(BaselineCell... baselineCells) {
      this.getBaselineCells().addAll(Arrays.asList(baselineCells));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasBaselineCells()) {
            status = PMMLObject.traverse(visitor, this.getBaselineCells());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
