package org.sparkproject.dmg.pmml.bayesian_network;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "ContinuousDistribution",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "triangularDistributionForBN", "normalDistributionForBN", "lognormalDistributionForBN", "uniformDistributionForBN"}
)
@JsonRootName("ContinuousDistribution")
@JsonPropertyOrder({"extensions", "triangularDistributionForBN", "normalDistributionForBN", "lognormalDistributionForBN", "uniformDistributionForBN"})
@Added(Version.PMML_4_3)
public class ContinuousDistribution extends PMMLObject implements HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "TriangularDistributionForBN",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("TriangularDistributionForBN")
   private TriangularDistribution triangularDistributionForBN;
   @XmlElement(
      name = "NormalDistributionForBN",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("NormalDistributionForBN")
   private NormalDistribution normalDistributionForBN;
   @XmlElement(
      name = "LognormalDistributionForBN",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("LognormalDistributionForBN")
   private LognormalDistribution lognormalDistributionForBN;
   @XmlElement(
      name = "UniformDistributionForBN",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("UniformDistributionForBN")
   private UniformDistribution uniformDistributionForBN;
   private static final long serialVersionUID = 67371272L;

   public ContinuousDistribution() {
   }

   @ValueConstructor
   public ContinuousDistribution(@Property("triangularDistributionForBN") TriangularDistribution triangularDistributionForBN, @Property("normalDistributionForBN") NormalDistribution normalDistributionForBN, @Property("lognormalDistributionForBN") LognormalDistribution lognormalDistributionForBN, @Property("uniformDistributionForBN") UniformDistribution uniformDistributionForBN) {
      this.triangularDistributionForBN = triangularDistributionForBN;
      this.normalDistributionForBN = normalDistributionForBN;
      this.lognormalDistributionForBN = lognormalDistributionForBN;
      this.uniformDistributionForBN = uniformDistributionForBN;
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

   public ContinuousDistribution addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public TriangularDistribution requireTriangularDistributionForBN() {
      if (this.triangularDistributionForBN == null) {
         throw new MissingElementException(this, PMMLElements.CONTINUOUSDISTRIBUTION_TRIANGULARDISTRIBUTIONFORBN);
      } else {
         return this.triangularDistributionForBN;
      }
   }

   public TriangularDistribution getTriangularDistributionForBN() {
      return this.triangularDistributionForBN;
   }

   public ContinuousDistribution setTriangularDistributionForBN(@Property("triangularDistributionForBN") TriangularDistribution triangularDistributionForBN) {
      this.triangularDistributionForBN = triangularDistributionForBN;
      return this;
   }

   public NormalDistribution requireNormalDistributionForBN() {
      if (this.normalDistributionForBN == null) {
         throw new MissingElementException(this, PMMLElements.CONTINUOUSDISTRIBUTION_NORMALDISTRIBUTIONFORBN);
      } else {
         return this.normalDistributionForBN;
      }
   }

   public NormalDistribution getNormalDistributionForBN() {
      return this.normalDistributionForBN;
   }

   public ContinuousDistribution setNormalDistributionForBN(@Property("normalDistributionForBN") NormalDistribution normalDistributionForBN) {
      this.normalDistributionForBN = normalDistributionForBN;
      return this;
   }

   public LognormalDistribution requireLognormalDistributionForBN() {
      if (this.lognormalDistributionForBN == null) {
         throw new MissingElementException(this, PMMLElements.CONTINUOUSDISTRIBUTION_LOGNORMALDISTRIBUTIONFORBN);
      } else {
         return this.lognormalDistributionForBN;
      }
   }

   public LognormalDistribution getLognormalDistributionForBN() {
      return this.lognormalDistributionForBN;
   }

   public ContinuousDistribution setLognormalDistributionForBN(@Property("lognormalDistributionForBN") LognormalDistribution lognormalDistributionForBN) {
      this.lognormalDistributionForBN = lognormalDistributionForBN;
      return this;
   }

   public UniformDistribution requireUniformDistributionForBN() {
      if (this.uniformDistributionForBN == null) {
         throw new MissingElementException(this, PMMLElements.CONTINUOUSDISTRIBUTION_UNIFORMDISTRIBUTIONFORBN);
      } else {
         return this.uniformDistributionForBN;
      }
   }

   public UniformDistribution getUniformDistributionForBN() {
      return this.uniformDistributionForBN;
   }

   public ContinuousDistribution setUniformDistributionForBN(@Property("uniformDistributionForBN") UniformDistribution uniformDistributionForBN) {
      this.uniformDistributionForBN = uniformDistributionForBN;
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
            status = PMMLObject.traverse(visitor, this.getTriangularDistributionForBN(), this.getNormalDistributionForBN(), this.getLognormalDistributionForBN(), this.getUniformDistributionForBN());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
