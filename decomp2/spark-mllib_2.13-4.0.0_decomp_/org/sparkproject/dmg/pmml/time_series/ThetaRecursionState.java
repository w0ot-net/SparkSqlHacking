package org.sparkproject.dmg.pmml.time_series;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "ThetaRecursionState",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"finalNoise", "finalPredictedNoise", "finalTheta", "finalNu"}
)
@JsonRootName("ThetaRecursionState")
@JsonPropertyOrder({"finalNoise", "finalPredictedNoise", "finalTheta", "finalNu"})
@Added(Version.PMML_4_4)
public class ThetaRecursionState extends PMMLObject {
   @XmlElement(
      name = "FinalNoise",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("FinalNoise")
   private FinalNoise finalNoise;
   @XmlElement(
      name = "FinalPredictedNoise",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("FinalPredictedNoise")
   private FinalPredictedNoise finalPredictedNoise;
   @XmlElement(
      name = "FinalTheta",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("FinalTheta")
   private FinalTheta finalTheta;
   @XmlElement(
      name = "FinalNu",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("FinalNu")
   private FinalNu finalNu;
   private static final long serialVersionUID = 67371272L;

   public ThetaRecursionState() {
   }

   @ValueConstructor
   public ThetaRecursionState(@Property("finalNoise") FinalNoise finalNoise, @Property("finalPredictedNoise") FinalPredictedNoise finalPredictedNoise, @Property("finalTheta") FinalTheta finalTheta, @Property("finalNu") FinalNu finalNu) {
      this.finalNoise = finalNoise;
      this.finalPredictedNoise = finalPredictedNoise;
      this.finalTheta = finalTheta;
      this.finalNu = finalNu;
   }

   public FinalNoise requireFinalNoise() {
      if (this.finalNoise == null) {
         throw new MissingElementException(this, PMMLElements.THETARECURSIONSTATE_FINALNOISE);
      } else {
         return this.finalNoise;
      }
   }

   public FinalNoise getFinalNoise() {
      return this.finalNoise;
   }

   public ThetaRecursionState setFinalNoise(@Property("finalNoise") FinalNoise finalNoise) {
      this.finalNoise = finalNoise;
      return this;
   }

   public FinalPredictedNoise requireFinalPredictedNoise() {
      if (this.finalPredictedNoise == null) {
         throw new MissingElementException(this, PMMLElements.THETARECURSIONSTATE_FINALPREDICTEDNOISE);
      } else {
         return this.finalPredictedNoise;
      }
   }

   public FinalPredictedNoise getFinalPredictedNoise() {
      return this.finalPredictedNoise;
   }

   public ThetaRecursionState setFinalPredictedNoise(@Property("finalPredictedNoise") FinalPredictedNoise finalPredictedNoise) {
      this.finalPredictedNoise = finalPredictedNoise;
      return this;
   }

   public FinalTheta requireFinalTheta() {
      if (this.finalTheta == null) {
         throw new MissingElementException(this, PMMLElements.THETARECURSIONSTATE_FINALTHETA);
      } else {
         return this.finalTheta;
      }
   }

   public FinalTheta getFinalTheta() {
      return this.finalTheta;
   }

   public ThetaRecursionState setFinalTheta(@Property("finalTheta") FinalTheta finalTheta) {
      this.finalTheta = finalTheta;
      return this;
   }

   public FinalNu requireFinalNu() {
      if (this.finalNu == null) {
         throw new MissingElementException(this, PMMLElements.THETARECURSIONSTATE_FINALNU);
      } else {
         return this.finalNu;
      }
   }

   public FinalNu getFinalNu() {
      return this.finalNu;
   }

   public ThetaRecursionState setFinalNu(@Property("finalNu") FinalNu finalNu) {
      this.finalNu = finalNu;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, this.getFinalNoise(), this.getFinalPredictedNoise(), this.getFinalTheta(), this.getFinalNu());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
