package org.sparkproject.dmg.pmml;

import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.adapters.ScoreDistributionAdapter;

@XmlTransient
@XmlJavaTypeAdapter(ScoreDistributionAdapter.class)
public abstract class ScoreDistribution extends PMMLObject implements HasRecordCount {
   public ComplexScoreDistribution toComplexScoreDistribution() {
      return new ComplexScoreDistribution(this);
   }

   public Object requireValue() {
      throw new UnsupportedOperationException();
   }

   public Object getValue() {
      return null;
   }

   public ScoreDistribution setValue(Object value) {
      throw new UnsupportedOperationException();
   }

   public Number requireRecordCount() {
      throw new UnsupportedOperationException();
   }

   public Number getRecordCount() {
      return null;
   }

   public ScoreDistribution setRecordCount(Number recordCount) {
      throw new UnsupportedOperationException();
   }

   public Number getConfidence() {
      return null;
   }

   public ScoreDistribution setConfidence(Number confidence) {
      throw new UnsupportedOperationException();
   }

   public Number requireProbability() {
      throw new UnsupportedOperationException();
   }

   public Number getProbability() {
      return null;
   }

   public ScoreDistribution setProbability(Number probability) {
      throw new UnsupportedOperationException();
   }

   public boolean hasExtensions() {
      return false;
   }

   public List getExtensions() {
      throw new UnsupportedOperationException();
   }

   public ScoreDistribution addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }
}
