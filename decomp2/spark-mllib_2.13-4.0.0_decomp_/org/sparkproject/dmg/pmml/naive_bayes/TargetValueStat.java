package org.sparkproject.dmg.pmml.naive_bayes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.AnyDistribution;
import org.sparkproject.dmg.pmml.ContinuousDistribution;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.GaussianDistribution;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.PoissonDistribution;
import org.sparkproject.dmg.pmml.UniformDistribution;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "TargetValueStat",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "continuousDistribution"}
)
@JsonRootName("TargetValueStat")
@JsonPropertyOrder({"value", "extensions", "continuousDistribution"})
@Added(Version.PMML_4_2)
public class TargetValueStat extends PMMLObject implements HasExtensions {
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
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElements({@XmlElement(
   name = "AnyDistribution",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = AnyDistribution.class
), @XmlElement(
   name = "GaussianDistribution",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = GaussianDistribution.class
), @XmlElement(
   name = "PoissonDistribution",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = PoissonDistribution.class
), @XmlElement(
   name = "UniformDistribution",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = UniformDistribution.class
)})
   @JsonProperty("ContinuousDistribution")
   @JsonTypeInfo(
      include = As.WRAPPER_OBJECT,
      use = Id.NAME
   )
   @JsonSubTypes({@Type(
   name = "AnyDistribution",
   value = AnyDistribution.class
), @Type(
   name = "GaussianDistribution",
   value = GaussianDistribution.class
), @Type(
   name = "PoissonDistribution",
   value = PoissonDistribution.class
), @Type(
   name = "UniformDistribution",
   value = UniformDistribution.class
)})
   private ContinuousDistribution continuousDistribution;
   private static final long serialVersionUID = 67371272L;

   public TargetValueStat() {
   }

   @ValueConstructor
   public TargetValueStat(@Property("value") Object value, @Property("continuousDistribution") ContinuousDistribution continuousDistribution) {
      this.value = value;
      this.continuousDistribution = continuousDistribution;
   }

   public Object requireValue() {
      if (this.value == null) {
         throw new MissingAttributeException(this, PMMLAttributes.TARGETVALUESTAT_VALUE);
      } else {
         return this.value;
      }
   }

   public Object getValue() {
      return this.value;
   }

   public TargetValueStat setValue(@Property("value") Object value) {
      this.value = value;
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

   public TargetValueStat addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public ContinuousDistribution requireContinuousDistribution() {
      if (this.continuousDistribution == null) {
         throw new MissingElementException(this, PMMLElements.TARGETVALUESTAT_CONTINUOUSDISTRIBUTION);
      } else {
         return this.continuousDistribution;
      }
   }

   public ContinuousDistribution getContinuousDistribution() {
      return this.continuousDistribution;
   }

   public TargetValueStat setContinuousDistribution(@Property("continuousDistribution") ContinuousDistribution continuousDistribution) {
      this.continuousDistribution = continuousDistribution;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, (Visitable)this.getContinuousDistribution());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
