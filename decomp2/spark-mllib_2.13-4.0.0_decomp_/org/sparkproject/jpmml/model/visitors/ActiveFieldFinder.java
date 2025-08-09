package org.sparkproject.jpmml.model.visitors;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.sparkproject.dmg.pmml.Aggregate;
import org.sparkproject.dmg.pmml.BlockIndicator;
import org.sparkproject.dmg.pmml.Discretize;
import org.sparkproject.dmg.pmml.FieldColumnPair;
import org.sparkproject.dmg.pmml.FieldRef;
import org.sparkproject.dmg.pmml.Lag;
import org.sparkproject.dmg.pmml.Model;
import org.sparkproject.dmg.pmml.NormContinuous;
import org.sparkproject.dmg.pmml.NormDiscrete;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.SimplePredicate;
import org.sparkproject.dmg.pmml.SimpleSetPredicate;
import org.sparkproject.dmg.pmml.TextIndex;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.association.Item;
import org.sparkproject.dmg.pmml.baseline.FieldValue;
import org.sparkproject.dmg.pmml.baseline.FieldValueCount;
import org.sparkproject.dmg.pmml.baseline.TestDistributions;
import org.sparkproject.dmg.pmml.bayesian_network.ContinuousNode;
import org.sparkproject.dmg.pmml.bayesian_network.DiscreteNode;
import org.sparkproject.dmg.pmml.bayesian_network.ParentValue;
import org.sparkproject.dmg.pmml.clustering.ClusteringField;
import org.sparkproject.dmg.pmml.general_regression.GeneralRegressionModel;
import org.sparkproject.dmg.pmml.general_regression.PPCell;
import org.sparkproject.dmg.pmml.general_regression.Predictor;
import org.sparkproject.dmg.pmml.mining.VariableWeight;
import org.sparkproject.dmg.pmml.naive_bayes.BayesInput;
import org.sparkproject.dmg.pmml.nearest_neighbor.KNNInput;
import org.sparkproject.dmg.pmml.regression.CategoricalPredictor;
import org.sparkproject.dmg.pmml.regression.NumericPredictor;
import org.sparkproject.dmg.pmml.sequence.SetPredicate;

public class ActiveFieldFinder extends AbstractVisitor implements Resettable {
   private Set names = null;

   public void reset() {
      if (this.names != null) {
         if (this.names.size() == 1) {
            this.names = null;
            return;
         }

         this.names.clear();
      }

   }

   public VisitorAction visit(Aggregate aggregate) {
      this.process(aggregate.getField());
      this.process(aggregate.getGroupField());
      return super.visit(aggregate);
   }

   public VisitorAction visit(BayesInput bayesInput) {
      this.process(bayesInput.getField());
      return super.visit(bayesInput);
   }

   public VisitorAction visit(BlockIndicator blockIndicator) {
      this.process(blockIndicator.getField());
      return super.visit(blockIndicator);
   }

   public VisitorAction visit(CategoricalPredictor categoricalPredictor) {
      this.process(categoricalPredictor.getField());
      return super.visit(categoricalPredictor);
   }

   public VisitorAction visit(ClusteringField clusteringField) {
      this.process(clusteringField.getField());
      return super.visit(clusteringField);
   }

   public VisitorAction visit(ContinuousNode continuousNode) {
      throw new UnsupportedOperationException();
   }

   public VisitorAction visit(DiscreteNode discreteNode) {
      throw new UnsupportedOperationException();
   }

   public VisitorAction visit(Discretize discretize) {
      this.process(discretize.getField());
      return super.visit(discretize);
   }

   public VisitorAction visit(FieldColumnPair fieldColumnPair) {
      this.process(fieldColumnPair.getField());
      return super.visit(fieldColumnPair);
   }

   public VisitorAction visit(FieldRef fieldRef) {
      this.process(fieldRef.getField());
      return super.visit(fieldRef);
   }

   public VisitorAction visit(FieldValue fieldValue) {
      this.process(fieldValue.getField());
      return super.visit(fieldValue);
   }

   public VisitorAction visit(FieldValueCount fieldValueCount) {
      this.process(fieldValueCount.getField());
      return super.visit(fieldValueCount);
   }

   public VisitorAction visit(GeneralRegressionModel generalRegressionModel) {
      GeneralRegressionModel.ModelType modelType = generalRegressionModel.requireModelType();
      switch (modelType) {
         case COX_REGRESSION:
            this.process(generalRegressionModel.getBaselineStrataVariable());
            this.process(generalRegressionModel.getEndTimeVariable());
            this.process(generalRegressionModel.getStartTimeVariable());
            this.process(generalRegressionModel.getStatusVariable());
            this.process(generalRegressionModel.getSubjectIDVariable());
         default:
            this.process(generalRegressionModel.getOffsetVariable());
            this.process(generalRegressionModel.getTrialsVariable());
            return super.visit(generalRegressionModel);
      }
   }

   public VisitorAction visit(Item item) {
      this.process(item.getField());
      return super.visit(item);
   }

   public VisitorAction visit(KNNInput knnInput) {
      this.process(knnInput.getField());
      return super.visit(knnInput);
   }

   public VisitorAction visit(Lag lag) {
      this.process(lag.getField());
      return super.visit(lag);
   }

   public VisitorAction visit(Model model) {
      if (model instanceof HasActiveFields) {
         HasActiveFields hasActiveFields = (HasActiveFields)model;

         for(String name : hasActiveFields.getActiveFields()) {
            this.process(name);
         }
      }

      return super.visit(model);
   }

   public VisitorAction visit(NormContinuous normContinuous) {
      this.process(normContinuous.getField());
      return super.visit(normContinuous);
   }

   public VisitorAction visit(NormDiscrete normDiscrete) {
      this.process(normDiscrete.getField());
      return super.visit(normDiscrete);
   }

   public VisitorAction visit(NumericPredictor numericPredictor) {
      this.process(numericPredictor.getField());
      return super.visit(numericPredictor);
   }

   public VisitorAction visit(ParentValue parentValue) {
      throw new UnsupportedOperationException();
   }

   public VisitorAction visit(PPCell ppCell) {
      this.process(ppCell.getField());
      return super.visit(ppCell);
   }

   public VisitorAction visit(Predictor predictor) {
      this.process(predictor.getField());
      return super.visit(predictor);
   }

   public VisitorAction visit(SetPredicate setPredicate) {
      this.process(setPredicate.getField());
      return super.visit(setPredicate);
   }

   public VisitorAction visit(SimplePredicate simplePredicate) {
      this.process(simplePredicate.getField());
      return super.visit(simplePredicate);
   }

   public VisitorAction visit(SimpleSetPredicate simpleSetPredicate) {
      this.process(simpleSetPredicate.getField());
      return super.visit(simpleSetPredicate);
   }

   public VisitorAction visit(TestDistributions testDistributions) {
      this.process(testDistributions.getField());
      this.process(testDistributions.getWeightField());
      return super.visit(testDistributions);
   }

   public VisitorAction visit(TextIndex textIndex) {
      this.process(textIndex.getTextField());
      return super.visit(textIndex);
   }

   public VisitorAction visit(VariableWeight variableWeight) {
      this.process(variableWeight.getField());
      return super.visit(variableWeight);
   }

   public Set getFieldNames() {
      return this.names == null ? Collections.emptySet() : Collections.unmodifiableSet(this.names);
   }

   private void process(String name) {
      if (name != null) {
         if (this.names != null) {
            if (this.names.size() == 1) {
               if (this.names.contains(name)) {
                  return;
               }

               this.names = new HashSet(this.names);
            }

            this.names.add(name);
         } else {
            this.names = Collections.singleton(name);
         }

      }
   }

   public static Set getFieldNames(PMMLObject... objects) {
      return getFieldNames(new ActiveFieldFinder(), objects);
   }

   public static Set getFieldNames(ActiveFieldFinder activeFieldFinder, PMMLObject... objects) {
      for(PMMLObject object : objects) {
         activeFieldFinder.applyTo(object);
      }

      return activeFieldFinder.getFieldNames();
   }
}
