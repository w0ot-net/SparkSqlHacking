package org.sparkproject.dmg.pmml.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.sparkproject.dmg.pmml.ComplexScoreDistribution;
import org.sparkproject.dmg.pmml.ScoreDistribution;
import org.sparkproject.dmg.pmml.ScoreDistributionTransformer;
import org.sparkproject.dmg.pmml.SimplifyingScoreDistributionTransformer;

public class ScoreDistributionAdapter extends XmlAdapter {
   public static final ThreadLocal SCOREDISTRIBUTION_TRANSFORMER_PROVIDER = new ThreadLocal() {
      public ScoreDistributionTransformer initialValue() {
         return SimplifyingScoreDistributionTransformer.INSTANCE;
      }
   };

   public ScoreDistribution unmarshal(ComplexScoreDistribution value) {
      ScoreDistributionTransformer scoreDistributionTransformer = (ScoreDistributionTransformer)SCOREDISTRIBUTION_TRANSFORMER_PROVIDER.get();
      return (ScoreDistribution)(scoreDistributionTransformer != null ? scoreDistributionTransformer.fromComplexScoreDistribution(value) : value);
   }

   public ComplexScoreDistribution marshal(ScoreDistribution scoreDistribution) {
      ScoreDistributionTransformer scoreDistributionTransformer = (ScoreDistributionTransformer)SCOREDISTRIBUTION_TRANSFORMER_PROVIDER.get();
      return scoreDistributionTransformer != null ? scoreDistributionTransformer.toComplexScoreDistribution(scoreDistribution) : scoreDistribution.toComplexScoreDistribution();
   }
}
