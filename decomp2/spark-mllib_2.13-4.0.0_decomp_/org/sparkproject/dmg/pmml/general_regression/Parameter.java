package org.sparkproject.dmg.pmml.general_regression;

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
import org.sparkproject.dmg.pmml.HasRequiredName;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Parameter",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("Parameter")
@JsonPropertyOrder({"name", "label", "referencePoint", "extensions"})
public class Parameter extends PMMLObject implements HasExtensions, HasRequiredName {
   @XmlAttribute(
      name = "name",
      required = true
   )
   @JsonProperty("name")
   private String name;
   @XmlAttribute(
      name = "label"
   )
   @JsonProperty("label")
   private String label;
   @XmlAttribute(
      name = "referencePoint"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("referencePoint")
   @Added(Version.PMML_4_0)
   private Number referencePoint;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final Number DEFAULT_REFERENCE_POINT = (new RealNumberAdapter()).unmarshal("0");
   private static final long serialVersionUID = 67371272L;

   public Parameter() {
   }

   @ValueConstructor
   public Parameter(@Property("name") String name) {
      this.name = name;
   }

   public String requireName() {
      if (this.name == null) {
         throw new MissingAttributeException(this, PMMLAttributes.PARAMETER_NAME);
      } else {
         return this.name;
      }
   }

   public String getName() {
      return this.name;
   }

   public Parameter setName(@Property("name") String name) {
      this.name = name;
      return this;
   }

   public String getLabel() {
      return this.label;
   }

   public Parameter setLabel(@Property("label") String label) {
      this.label = label;
      return this;
   }

   public Number getReferencePoint() {
      return this.referencePoint == null ? DEFAULT_REFERENCE_POINT : this.referencePoint;
   }

   public Parameter setReferencePoint(@Property("referencePoint") Number referencePoint) {
      this.referencePoint = referencePoint;
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

   public Parameter addExtensions(Extension... extensions) {
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
}
