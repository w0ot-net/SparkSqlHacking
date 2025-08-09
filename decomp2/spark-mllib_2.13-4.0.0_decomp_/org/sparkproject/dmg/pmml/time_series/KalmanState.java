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
   name = "KalmanState",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"finalOmega", "finalStateVector", "hVector"}
)
@JsonRootName("KalmanState")
@JsonPropertyOrder({"finalOmega", "finalStateVector", "hVector"})
@Added(Version.PMML_4_4)
public class KalmanState extends PMMLObject {
   @XmlElement(
      name = "FinalOmega",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("FinalOmega")
   private FinalOmega finalOmega;
   @XmlElement(
      name = "FinalStateVector",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("FinalStateVector")
   private FinalStateVector finalStateVector;
   @XmlElement(
      name = "HVector",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("HVector")
   private HVector hVector;
   private static final long serialVersionUID = 67371272L;

   public KalmanState() {
   }

   @ValueConstructor
   public KalmanState(@Property("finalOmega") FinalOmega finalOmega, @Property("finalStateVector") FinalStateVector finalStateVector) {
      this.finalOmega = finalOmega;
      this.finalStateVector = finalStateVector;
   }

   public FinalOmega requireFinalOmega() {
      if (this.finalOmega == null) {
         throw new MissingElementException(this, PMMLElements.KALMANSTATE_FINALOMEGA);
      } else {
         return this.finalOmega;
      }
   }

   public FinalOmega getFinalOmega() {
      return this.finalOmega;
   }

   public KalmanState setFinalOmega(@Property("finalOmega") FinalOmega finalOmega) {
      this.finalOmega = finalOmega;
      return this;
   }

   public FinalStateVector requireFinalStateVector() {
      if (this.finalStateVector == null) {
         throw new MissingElementException(this, PMMLElements.KALMANSTATE_FINALSTATEVECTOR);
      } else {
         return this.finalStateVector;
      }
   }

   public FinalStateVector getFinalStateVector() {
      return this.finalStateVector;
   }

   public KalmanState setFinalStateVector(@Property("finalStateVector") FinalStateVector finalStateVector) {
      this.finalStateVector = finalStateVector;
      return this;
   }

   public HVector getHVector() {
      return this.hVector;
   }

   public KalmanState setHVector(@Property("hVector") HVector hVector) {
      this.hVector = hVector;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, this.getFinalOmega(), this.getFinalStateVector(), this.getHVector());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
