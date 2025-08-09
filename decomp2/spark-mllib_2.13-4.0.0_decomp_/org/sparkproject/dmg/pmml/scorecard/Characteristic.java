package org.sparkproject.dmg.pmml.scorecard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.HasName;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Characteristic",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "attributes"}
)
@JsonRootName("Characteristic")
@JsonPropertyOrder({"name", "reasonCode", "baselineScore", "extensions", "attributes"})
@Added(Version.PMML_4_1)
public class Characteristic extends PMMLObject implements HasExtensions, HasName, HasBaselineScore, HasReasonCode {
   @XmlAttribute(
      name = "name"
   )
   @JsonProperty("name")
   private String name;
   @XmlAttribute(
      name = "reasonCode"
   )
   @JsonProperty("reasonCode")
   private String reasonCode;
   @XmlAttribute(
      name = "baselineScore"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("baselineScore")
   private Number baselineScore;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Attribute",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Attribute")
   @CollectionElementType(Attribute.class)
   private List attributes;
   private static final long serialVersionUID = 67371272L;

   public Characteristic() {
   }

   @ValueConstructor
   public Characteristic(@Property("attributes") List attributes) {
      this.attributes = attributes;
   }

   public String getName() {
      return this.name;
   }

   public Characteristic setName(@Property("name") String name) {
      this.name = name;
      return this;
   }

   public String getReasonCode() {
      return this.reasonCode;
   }

   public Characteristic setReasonCode(@Property("reasonCode") String reasonCode) {
      this.reasonCode = reasonCode;
      return this;
   }

   public Number getBaselineScore() {
      return this.baselineScore;
   }

   public Characteristic setBaselineScore(@Property("baselineScore") Number baselineScore) {
      this.baselineScore = baselineScore;
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

   public Characteristic addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasAttributes() {
      return this.attributes != null && !this.attributes.isEmpty();
   }

   public List requireAttributes() {
      if (this.attributes != null && !this.attributes.isEmpty()) {
         return this.attributes;
      } else {
         throw new MissingElementException(this, PMMLElements.CHARACTERISTIC_ATTRIBUTES);
      }
   }

   public List getAttributes() {
      if (this.attributes == null) {
         this.attributes = new ArrayList();
      }

      return this.attributes;
   }

   public Characteristic addAttributes(Attribute... attributes) {
      this.getAttributes().addAll(Arrays.asList(attributes));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasAttributes()) {
            status = PMMLObject.traverse(visitor, this.getAttributes());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
