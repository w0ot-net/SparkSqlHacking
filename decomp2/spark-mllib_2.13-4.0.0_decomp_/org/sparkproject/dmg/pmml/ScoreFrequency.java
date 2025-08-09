package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.sparkproject.jpmml.model.annotations.CopyConstructor;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "ScoreDistribution",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = ""
)
@JsonRootName("ScoreDistribution")
@JsonPropertyOrder({"value", "recordCount"})
public class ScoreFrequency extends SimpleScoreDistribution {
   public ScoreFrequency() {
   }

   @ValueConstructor
   public ScoreFrequency(@Property("value") Object value, @Property("recordCount") Number recordCount) {
      super(value, recordCount);
   }

   @CopyConstructor
   public ScoreFrequency(ScoreDistribution scoreDistribution) {
      super(scoreDistribution);
   }
}
