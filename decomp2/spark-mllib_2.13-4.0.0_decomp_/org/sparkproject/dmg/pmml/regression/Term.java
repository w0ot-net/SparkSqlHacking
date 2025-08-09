package org.sparkproject.dmg.pmml.regression;

import jakarta.xml.bind.annotation.XmlTransient;
import org.sparkproject.dmg.pmml.PMMLObject;

@XmlTransient
public abstract class Term extends PMMLObject {
   public abstract Number requireCoefficient();

   public abstract Number getCoefficient();

   public abstract Term setCoefficient(Number var1);
}
