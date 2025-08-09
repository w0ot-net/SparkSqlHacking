package org.sparkproject.dmg.pmml.general_regression;

import jakarta.xml.bind.annotation.XmlTransient;
import java.util.List;
import org.sparkproject.dmg.pmml.PMMLObject;

@XmlTransient
public abstract class PredictorList extends PMMLObject {
   public abstract boolean hasPredictors();

   public abstract List getPredictors();

   public abstract PredictorList addPredictors(Predictor... var1);
}
