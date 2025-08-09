package org.sparkproject.dmg.pmml;

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
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "UniformDistribution",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("UniformDistribution")
@JsonPropertyOrder({"lower", "upper", "extensions"})
@Added(Version.PMML_4_1)
public class UniformDistribution extends ContinuousDistribution implements HasExtensions {
   @XmlAttribute(
      name = "lower",
      required = true
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("lower")
   private Number lower;
   @XmlAttribute(
      name = "upper",
      required = true
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("upper")
   private Number upper;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public UniformDistribution() {
   }

   @ValueConstructor
   public UniformDistribution(@Property("lower") Number lower, @Property("upper") Number upper) {
      this.lower = lower;
      this.upper = upper;
   }

   public Number requireLower() {
      if (this.lower == null) {
         throw new MissingAttributeException(this, PMMLAttributes.UNIFORMDISTRIBUTION_LOWER);
      } else {
         return this.lower;
      }
   }

   public Number getLower() {
      return this.lower;
   }

   public UniformDistribution setLower(@Property("lower") Number lower) {
      this.lower = lower;
      return this;
   }

   public Number requireUpper() {
      if (this.upper == null) {
         throw new MissingAttributeException(this, PMMLAttributes.UNIFORMDISTRIBUTION_UPPER);
      } else {
         return this.upper;
      }
   }

   public Number getUpper() {
      return this.upper;
   }

   public UniformDistribution setUpper(@Property("upper") Number upper) {
      this.upper = upper;
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

   public UniformDistribution addExtensions(Extension... extensions) {
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
