package org.sparkproject.dmg.pmml.baseline;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.sparkproject.dmg.pmml.AnyDistribution;
import org.sparkproject.dmg.pmml.ContinuousDistribution;
import org.sparkproject.dmg.pmml.GaussianDistribution;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.PoissonDistribution;
import org.sparkproject.dmg.pmml.UniformDistribution;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Alternate",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"continuousDistribution"}
)
@JsonRootName("Alternate")
@JsonPropertyOrder({"continuousDistribution"})
@Added(Version.PMML_4_1)
public class Alternate extends PMMLObject {
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

   public Alternate() {
   }

   @ValueConstructor
   public Alternate(@Property("continuousDistribution") ContinuousDistribution continuousDistribution) {
      this.continuousDistribution = continuousDistribution;
   }

   public ContinuousDistribution requireContinuousDistribution() {
      if (this.continuousDistribution == null) {
         throw new MissingElementException(this, PMMLElements.ALTERNATE_CONTINUOUSDISTRIBUTION);
      } else {
         return this.continuousDistribution;
      }
   }

   public ContinuousDistribution getContinuousDistribution() {
      return this.continuousDistribution;
   }

   public Alternate setContinuousDistribution(@Property("continuousDistribution") ContinuousDistribution continuousDistribution) {
      this.continuousDistribution = continuousDistribution;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, (Visitable)this.getContinuousDistribution());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
