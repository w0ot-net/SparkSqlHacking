package org.sparkproject.dmg.pmml;

import jakarta.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class ComparisonField extends PMMLObject implements HasFieldReference {
   public abstract Number getFieldWeight();

   public abstract ComparisonField setFieldWeight(Number var1);

   public abstract CompareFunction getCompareFunction();

   public abstract ComparisonField setCompareFunction(CompareFunction var1);

   public Number getSimilarityScale() {
      return null;
   }

   public ComparisonField setSimilarityScale(Number similarityScale) {
      throw new UnsupportedOperationException();
   }
}
