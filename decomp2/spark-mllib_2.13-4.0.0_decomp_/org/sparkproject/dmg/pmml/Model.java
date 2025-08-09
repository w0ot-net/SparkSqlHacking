package org.sparkproject.dmg.pmml;

import jakarta.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class Model extends PMMLObject {
   public abstract String getModelName();

   public abstract Model setModelName(String var1);

   public abstract MiningFunction requireMiningFunction();

   public abstract MiningFunction getMiningFunction();

   public abstract Model setMiningFunction(MiningFunction var1);

   public abstract String getAlgorithmName();

   public abstract Model setAlgorithmName(String var1);

   public abstract boolean isScorable();

   public abstract Model setScorable(Boolean var1);

   public abstract MathContext getMathContext();

   public abstract Model setMathContext(MathContext var1);

   public abstract MiningSchema requireMiningSchema();

   public abstract MiningSchema getMiningSchema();

   public abstract Model setMiningSchema(MiningSchema var1);

   public abstract LocalTransformations getLocalTransformations();

   public abstract Model setLocalTransformations(LocalTransformations var1);

   public Targets getTargets() {
      return null;
   }

   public Model setTargets(Targets targets) {
      throw new UnsupportedOperationException();
   }

   public Output getOutput() {
      return null;
   }

   public Model setOutput(Output output) {
      throw new UnsupportedOperationException();
   }

   public ModelStats getModelStats() {
      return null;
   }

   public Model setModelStats(ModelStats modelStats) {
      throw new UnsupportedOperationException();
   }

   public ModelExplanation getModelExplanation() {
      return null;
   }

   public Model setModelExplanation(ModelExplanation modelExplanation) {
      throw new UnsupportedOperationException();
   }

   public ModelVerification getModelVerification() {
      return null;
   }

   public Model setModelVerification(ModelVerification modelVerification) {
      throw new UnsupportedOperationException();
   }
}
