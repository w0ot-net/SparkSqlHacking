package org.sparkproject.dmg.pmml.time_series;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
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
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "StateSpaceModel",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "stateVector", "transitionMatrix", "measurementMatrix", "interceptVector", "predictedStateCovarianceMatrix", "selectedStateCovarianceMatrix", "observationVarianceMatrix", "psiVector", "dynamicRegressors"}
)
@JsonRootName("StateSpaceModel")
@JsonPropertyOrder({"variance", "period", "intercept", "extensions", "stateVector", "transitionMatrix", "measurementMatrix", "interceptVector", "predictedStateCovarianceMatrix", "selectedStateCovarianceMatrix", "observationVarianceMatrix", "psiVector", "dynamicRegressors"})
@Added(Version.PMML_4_4)
public class StateSpaceModel extends Algorithm implements HasExtensions, HasDynamicRegressors {
   @XmlAttribute(
      name = "variance"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("variance")
   private Number variance;
   @XmlAttribute(
      name = "period"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("period")
   private Object period;
   @XmlAttribute(
      name = "intercept"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("intercept")
   private Number intercept;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "StateVector",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("StateVector")
   private StateVector stateVector;
   @XmlElement(
      name = "TransitionMatrix",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("TransitionMatrix")
   private TransitionMatrix transitionMatrix;
   @XmlElement(
      name = "MeasurementMatrix",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("MeasurementMatrix")
   private MeasurementMatrix measurementMatrix;
   @XmlElement(
      name = "InterceptVector",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("InterceptVector")
   private InterceptVector interceptVector;
   @XmlElement(
      name = "PredictedStateCovarianceMatrix",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("PredictedStateCovarianceMatrix")
   private PredictedStateCovarianceMatrix predictedStateCovarianceMatrix;
   @XmlElement(
      name = "SelectedStateCovarianceMatrix",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("SelectedStateCovarianceMatrix")
   private SelectedStateCovarianceMatrix selectedStateCovarianceMatrix;
   @XmlElement(
      name = "ObservationVarianceMatrix",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ObservationVarianceMatrix")
   private ObservationVarianceMatrix observationVarianceMatrix;
   @XmlElement(
      name = "PsiVector",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("PsiVector")
   private PsiVector psiVector;
   @XmlElement(
      name = "DynamicRegressor",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("DynamicRegressor")
   @CollectionElementType(DynamicRegressor.class)
   private List dynamicRegressors;
   private static final Object DEFAULT_PERIOD = (new ObjectAdapter()).unmarshal("none");
   private static final Number DEFAULT_INTERCEPT = (new RealNumberAdapter()).unmarshal("0");
   private static final long serialVersionUID = 67371272L;

   public Number getVariance() {
      return this.variance;
   }

   public StateSpaceModel setVariance(@Property("variance") Number variance) {
      this.variance = variance;
      return this;
   }

   public Object getPeriod() {
      return this.period == null ? DEFAULT_PERIOD : this.period;
   }

   public StateSpaceModel setPeriod(@Property("period") Object period) {
      this.period = period;
      return this;
   }

   public Number getIntercept() {
      return this.intercept == null ? DEFAULT_INTERCEPT : this.intercept;
   }

   public StateSpaceModel setIntercept(@Property("intercept") Number intercept) {
      this.intercept = intercept;
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

   public StateSpaceModel addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public StateVector requireStateVector() {
      if (this.stateVector == null) {
         throw new MissingElementException(this, PMMLElements.STATESPACEMODEL_STATEVECTOR);
      } else {
         return this.stateVector;
      }
   }

   public StateVector getStateVector() {
      return this.stateVector;
   }

   public StateSpaceModel setStateVector(@Property("stateVector") StateVector stateVector) {
      this.stateVector = stateVector;
      return this;
   }

   public TransitionMatrix requireTransitionMatrix() {
      if (this.transitionMatrix == null) {
         throw new MissingElementException(this, PMMLElements.STATESPACEMODEL_TRANSITIONMATRIX);
      } else {
         return this.transitionMatrix;
      }
   }

   public TransitionMatrix getTransitionMatrix() {
      return this.transitionMatrix;
   }

   public StateSpaceModel setTransitionMatrix(@Property("transitionMatrix") TransitionMatrix transitionMatrix) {
      this.transitionMatrix = transitionMatrix;
      return this;
   }

   public MeasurementMatrix requireMeasurementMatrix() {
      if (this.measurementMatrix == null) {
         throw new MissingElementException(this, PMMLElements.STATESPACEMODEL_MEASUREMENTMATRIX);
      } else {
         return this.measurementMatrix;
      }
   }

   public MeasurementMatrix getMeasurementMatrix() {
      return this.measurementMatrix;
   }

   public StateSpaceModel setMeasurementMatrix(@Property("measurementMatrix") MeasurementMatrix measurementMatrix) {
      this.measurementMatrix = measurementMatrix;
      return this;
   }

   public InterceptVector getInterceptVector() {
      return this.interceptVector;
   }

   public StateSpaceModel setInterceptVector(@Property("interceptVector") InterceptVector interceptVector) {
      this.interceptVector = interceptVector;
      return this;
   }

   public PredictedStateCovarianceMatrix getPredictedStateCovarianceMatrix() {
      return this.predictedStateCovarianceMatrix;
   }

   public StateSpaceModel setPredictedStateCovarianceMatrix(@Property("predictedStateCovarianceMatrix") PredictedStateCovarianceMatrix predictedStateCovarianceMatrix) {
      this.predictedStateCovarianceMatrix = predictedStateCovarianceMatrix;
      return this;
   }

   public SelectedStateCovarianceMatrix getSelectedStateCovarianceMatrix() {
      return this.selectedStateCovarianceMatrix;
   }

   public StateSpaceModel setSelectedStateCovarianceMatrix(@Property("selectedStateCovarianceMatrix") SelectedStateCovarianceMatrix selectedStateCovarianceMatrix) {
      this.selectedStateCovarianceMatrix = selectedStateCovarianceMatrix;
      return this;
   }

   public ObservationVarianceMatrix getObservationVarianceMatrix() {
      return this.observationVarianceMatrix;
   }

   public StateSpaceModel setObservationVarianceMatrix(@Property("observationVarianceMatrix") ObservationVarianceMatrix observationVarianceMatrix) {
      this.observationVarianceMatrix = observationVarianceMatrix;
      return this;
   }

   public PsiVector getPsiVector() {
      return this.psiVector;
   }

   public StateSpaceModel setPsiVector(@Property("psiVector") PsiVector psiVector) {
      this.psiVector = psiVector;
      return this;
   }

   public boolean hasDynamicRegressors() {
      return this.dynamicRegressors != null && !this.dynamicRegressors.isEmpty();
   }

   public List getDynamicRegressors() {
      if (this.dynamicRegressors == null) {
         this.dynamicRegressors = new ArrayList();
      }

      return this.dynamicRegressors;
   }

   public StateSpaceModel addDynamicRegressors(DynamicRegressor... dynamicRegressors) {
      this.getDynamicRegressors().addAll(Arrays.asList(dynamicRegressors));
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
            status = PMMLObject.traverse(visitor, this.getStateVector(), this.getTransitionMatrix(), this.getMeasurementMatrix(), this.getInterceptVector(), this.getPredictedStateCovarianceMatrix(), this.getSelectedStateCovarianceMatrix(), this.getObservationVarianceMatrix(), this.getPsiVector());
         }

         if (status == VisitorAction.CONTINUE && this.hasDynamicRegressors()) {
            status = PMMLObject.traverse(visitor, this.getDynamicRegressors());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
