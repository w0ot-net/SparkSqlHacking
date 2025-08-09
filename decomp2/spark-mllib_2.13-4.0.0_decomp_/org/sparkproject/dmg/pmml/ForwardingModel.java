package org.sparkproject.dmg.pmml;

import jakarta.xml.bind.annotation.XmlTransient;
import java.util.Objects;

@XmlTransient
public class ForwardingModel extends Model {
   private Model model = null;

   private ForwardingModel() {
   }

   public ForwardingModel(Model model) {
      this.setModel(model);
   }

   public String getModelName() {
      return this.getModel().getModelName();
   }

   public ForwardingModel setModelName(String modelName) {
      this.getModel().setModelName(modelName);
      return this;
   }

   public MiningFunction requireMiningFunction() {
      return this.getModel().requireMiningFunction();
   }

   public MiningFunction getMiningFunction() {
      return this.getModel().getMiningFunction();
   }

   public ForwardingModel setMiningFunction(MiningFunction miningFunction) {
      this.getModel().setMiningFunction(miningFunction);
      return this;
   }

   public String getAlgorithmName() {
      return this.getModel().getAlgorithmName();
   }

   public ForwardingModel setAlgorithmName(String algorithmName) {
      this.getModel().setAlgorithmName(algorithmName);
      return this;
   }

   public boolean isScorable() {
      return this.getModel().isScorable();
   }

   public ForwardingModel setScorable(Boolean scorable) {
      this.getModel().setScorable(scorable);
      return this;
   }

   public MathContext getMathContext() {
      return this.getModel().getMathContext();
   }

   public ForwardingModel setMathContext(MathContext mathContext) {
      this.getModel().setMathContext(mathContext);
      return this;
   }

   public MiningSchema requireMiningSchema() {
      return this.getModel().requireMiningSchema();
   }

   public MiningSchema getMiningSchema() {
      return this.getModel().getMiningSchema();
   }

   public ForwardingModel setMiningSchema(MiningSchema miningSchema) {
      this.getModel().setMiningSchema(miningSchema);
      return this;
   }

   public LocalTransformations getLocalTransformations() {
      return this.getModel().getLocalTransformations();
   }

   public ForwardingModel setLocalTransformations(LocalTransformations localTransformations) {
      this.getModel().setLocalTransformations(localTransformations);
      return this;
   }

   public Targets getTargets() {
      return this.getModel().getTargets();
   }

   public ForwardingModel setTargets(Targets targets) {
      this.getModel().setTargets(targets);
      return this;
   }

   public Output getOutput() {
      return this.getModel().getOutput();
   }

   public ForwardingModel setOutput(Output output) {
      this.getModel().setOutput(output);
      return this;
   }

   public ModelStats getModelStats() {
      return this.getModel().getModelStats();
   }

   public ForwardingModel setModelStats(ModelStats modelStats) {
      this.getModel().setModelStats(modelStats);
      return this;
   }

   public ModelExplanation getModelExplanation() {
      return this.getModel().getModelExplanation();
   }

   public ForwardingModel setModelExplanation(ModelExplanation modelExplanation) {
      this.getModel().setModelExplanation(modelExplanation);
      return this;
   }

   public ModelVerification getModelVerification() {
      return this.getModel().getModelVerification();
   }

   public ForwardingModel setModelVerification(ModelVerification modelVerification) {
      this.getModel().setModelVerification(modelVerification);
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      return this.getModel().accept(visitor);
   }

   public Model getModel() {
      return this.model;
   }

   private void setModel(Model model) {
      this.model = (Model)Objects.requireNonNull(model);
   }
}
