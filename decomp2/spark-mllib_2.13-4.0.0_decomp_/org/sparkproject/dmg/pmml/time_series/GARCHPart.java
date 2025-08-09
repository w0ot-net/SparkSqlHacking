package org.sparkproject.dmg.pmml.time_series;

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
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "GARCHPart",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "residualSquareCoefficients", "varianceCoefficients"}
)
@JsonRootName("GARCHPart")
@JsonPropertyOrder({"constant", "gp", "gq", "extensions", "residualSquareCoefficients", "varianceCoefficients"})
@Added(Version.PMML_4_4)
public class GARCHPart extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "constant"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("constant")
   private Number constant;
   @XmlAttribute(
      name = "gp",
      required = true
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("gp")
   private Integer gp;
   @XmlAttribute(
      name = "gq",
      required = true
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("gq")
   private Integer gq;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "ResidualSquareCoefficients",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("ResidualSquareCoefficients")
   private ResidualSquareCoefficients residualSquareCoefficients;
   @XmlElement(
      name = "VarianceCoefficients",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("VarianceCoefficients")
   private VarianceCoefficients varianceCoefficients;
   private static final Number DEFAULT_CONSTANT = (new RealNumberAdapter()).unmarshal("0");
   private static final long serialVersionUID = 67371272L;

   public GARCHPart() {
   }

   @ValueConstructor
   public GARCHPart(@Property("gp") Integer gp, @Property("gq") Integer gq, @Property("residualSquareCoefficients") ResidualSquareCoefficients residualSquareCoefficients, @Property("varianceCoefficients") VarianceCoefficients varianceCoefficients) {
      this.gp = gp;
      this.gq = gq;
      this.residualSquareCoefficients = residualSquareCoefficients;
      this.varianceCoefficients = varianceCoefficients;
   }

   public Number getConstant() {
      return this.constant == null ? DEFAULT_CONSTANT : this.constant;
   }

   public GARCHPart setConstant(@Property("constant") Number constant) {
      this.constant = constant;
      return this;
   }

   public Integer requireGp() {
      if (this.gp == null) {
         throw new MissingAttributeException(this, PMMLAttributes.GARCHPART_GP);
      } else {
         return this.gp;
      }
   }

   public Integer getGp() {
      return this.gp;
   }

   public GARCHPart setGp(@Property("gp") Integer gp) {
      this.gp = gp;
      return this;
   }

   public Integer requireGq() {
      if (this.gq == null) {
         throw new MissingAttributeException(this, PMMLAttributes.GARCHPART_GQ);
      } else {
         return this.gq;
      }
   }

   public Integer getGq() {
      return this.gq;
   }

   public GARCHPart setGq(@Property("gq") Integer gq) {
      this.gq = gq;
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

   public GARCHPart addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public ResidualSquareCoefficients requireResidualSquareCoefficients() {
      if (this.residualSquareCoefficients == null) {
         throw new MissingElementException(this, PMMLElements.GARCHPART_RESIDUALSQUARECOEFFICIENTS);
      } else {
         return this.residualSquareCoefficients;
      }
   }

   public ResidualSquareCoefficients getResidualSquareCoefficients() {
      return this.residualSquareCoefficients;
   }

   public GARCHPart setResidualSquareCoefficients(@Property("residualSquareCoefficients") ResidualSquareCoefficients residualSquareCoefficients) {
      this.residualSquareCoefficients = residualSquareCoefficients;
      return this;
   }

   public VarianceCoefficients requireVarianceCoefficients() {
      if (this.varianceCoefficients == null) {
         throw new MissingElementException(this, PMMLElements.GARCHPART_VARIANCECOEFFICIENTS);
      } else {
         return this.varianceCoefficients;
      }
   }

   public VarianceCoefficients getVarianceCoefficients() {
      return this.varianceCoefficients;
   }

   public GARCHPart setVarianceCoefficients(@Property("varianceCoefficients") VarianceCoefficients varianceCoefficients) {
      this.varianceCoefficients = varianceCoefficients;
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
            status = PMMLObject.traverse(visitor, this.getResidualSquareCoefficients(), this.getVarianceCoefficients());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
