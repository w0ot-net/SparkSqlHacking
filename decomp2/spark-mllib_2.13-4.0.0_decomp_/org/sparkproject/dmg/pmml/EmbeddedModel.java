package org.sparkproject.dmg.pmml;

import jakarta.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class EmbeddedModel extends PMMLObject {
   public abstract String getModelName();

   public abstract EmbeddedModel setModelName(String var1);

   public abstract MiningFunction requireMiningFunction();

   public abstract MiningFunction getMiningFunction();

   public abstract EmbeddedModel setMiningFunction(MiningFunction var1);

   public abstract String getAlgorithmName();

   public abstract EmbeddedModel setAlgorithmName(String var1);

   public abstract LocalTransformations getLocalTransformations();

   public abstract EmbeddedModel setLocalTransformations(LocalTransformations var1);

   public abstract Targets getTargets();

   public abstract EmbeddedModel setTargets(Targets var1);

   public abstract Output getOutput();

   public abstract EmbeddedModel setOutput(Output var1);

   public abstract ModelStats getModelStats();

   public abstract EmbeddedModel setModelStats(ModelStats var1);
}
