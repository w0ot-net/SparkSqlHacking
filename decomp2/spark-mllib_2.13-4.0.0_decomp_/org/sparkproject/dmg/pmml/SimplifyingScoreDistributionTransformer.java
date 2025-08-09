package org.sparkproject.dmg.pmml;

public class SimplifyingScoreDistributionTransformer implements ScoreDistributionTransformer {
   public static final SimplifyingScoreDistributionTransformer INSTANCE = new SimplifyingScoreDistributionTransformer();

   public ScoreDistribution fromComplexScoreDistribution(ComplexScoreDistribution complexScoreDistribution) {
      return this.simplify(complexScoreDistribution);
   }

   public ComplexScoreDistribution toComplexScoreDistribution(ScoreDistribution scoreDistribution) {
      return scoreDistribution.toComplexScoreDistribution();
   }

   private ScoreDistribution simplify(ScoreDistribution scoreDistribution) {
      if (scoreDistribution.hasExtensions()) {
         return scoreDistribution;
      } else if (scoreDistribution.getConfidence() != null) {
         return scoreDistribution;
      } else {
         return (ScoreDistribution)(scoreDistribution.getProbability() != null ? new ScoreProbability(scoreDistribution) : new ScoreFrequency(scoreDistribution));
      }
   }
}
