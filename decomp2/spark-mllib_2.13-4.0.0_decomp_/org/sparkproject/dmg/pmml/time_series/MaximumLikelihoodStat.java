package org.sparkproject.dmg.pmml.time_series;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.StringValue;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "MaximumLikelihoodStat",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"kalmanState", "thetaRecursionState"}
)
@JsonRootName("MaximumLikelihoodStat")
@JsonPropertyOrder({"method", "periodDeficit", "kalmanState", "thetaRecursionState"})
@Added(Version.PMML_4_4)
public class MaximumLikelihoodStat extends PMMLObject {
   @XmlAttribute(
      name = "method",
      required = true
   )
   @JsonProperty("method")
   private Method method;
   @XmlAttribute(
      name = "periodDeficit"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("periodDeficit")
   private Integer periodDeficit;
   @XmlElement(
      name = "KalmanState",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("KalmanState")
   private KalmanState kalmanState;
   @XmlElement(
      name = "ThetaRecursionState",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ThetaRecursionState")
   private ThetaRecursionState thetaRecursionState;
   private static final Integer DEFAULT_PERIOD_DEFICIT = (new IntegerAdapter()).unmarshal("0");
   private static final long serialVersionUID = 67371272L;

   public MaximumLikelihoodStat() {
   }

   @ValueConstructor
   public MaximumLikelihoodStat(@Property("method") Method method, @Property("kalmanState") KalmanState kalmanState, @Property("thetaRecursionState") ThetaRecursionState thetaRecursionState) {
      this.method = method;
      this.kalmanState = kalmanState;
      this.thetaRecursionState = thetaRecursionState;
   }

   public Method requireMethod() {
      if (this.method == null) {
         throw new MissingAttributeException(this, PMMLAttributes.MAXIMUMLIKELIHOODSTAT_METHOD);
      } else {
         return this.method;
      }
   }

   public Method getMethod() {
      return this.method;
   }

   public MaximumLikelihoodStat setMethod(@Property("method") Method method) {
      this.method = method;
      return this;
   }

   public Integer getPeriodDeficit() {
      return this.periodDeficit == null ? DEFAULT_PERIOD_DEFICIT : this.periodDeficit;
   }

   public MaximumLikelihoodStat setPeriodDeficit(@Property("periodDeficit") Integer periodDeficit) {
      this.periodDeficit = periodDeficit;
      return this;
   }

   public KalmanState requireKalmanState() {
      if (this.kalmanState == null) {
         throw new MissingElementException(this, PMMLElements.MAXIMUMLIKELIHOODSTAT_KALMANSTATE);
      } else {
         return this.kalmanState;
      }
   }

   public KalmanState getKalmanState() {
      return this.kalmanState;
   }

   public MaximumLikelihoodStat setKalmanState(@Property("kalmanState") KalmanState kalmanState) {
      this.kalmanState = kalmanState;
      return this;
   }

   public ThetaRecursionState requireThetaRecursionState() {
      if (this.thetaRecursionState == null) {
         throw new MissingElementException(this, PMMLElements.MAXIMUMLIKELIHOODSTAT_THETARECURSIONSTATE);
      } else {
         return this.thetaRecursionState;
      }
   }

   public ThetaRecursionState getThetaRecursionState() {
      return this.thetaRecursionState;
   }

   public MaximumLikelihoodStat setThetaRecursionState(@Property("thetaRecursionState") ThetaRecursionState thetaRecursionState) {
      this.thetaRecursionState = thetaRecursionState;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, this.getKalmanState(), this.getThetaRecursionState());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum Method implements StringValue {
      @XmlEnumValue("kalman")
      @JsonProperty("kalman")
      KALMAN("kalman"),
      @XmlEnumValue("thetaRecursion")
      @JsonProperty("thetaRecursion")
      THETA_RECURSION("thetaRecursion");

      private final String value;

      private Method(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Method fromValue(String v) {
         for(Method c : values()) {
            if (c.value.equals(v)) {
               return c;
            }
         }

         throw new IllegalArgumentException(v);
      }

      public String toString() {
         return this.value();
      }
   }
}
