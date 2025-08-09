package org.sparkproject.dmg.pmml;

public interface ScoreDistributionTransformer {
   ScoreDistribution fromComplexScoreDistribution(ComplexScoreDistribution var1);

   ComplexScoreDistribution toComplexScoreDistribution(ScoreDistribution var1);
}
