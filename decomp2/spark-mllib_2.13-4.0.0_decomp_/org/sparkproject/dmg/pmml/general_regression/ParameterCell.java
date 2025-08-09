package org.sparkproject.dmg.pmml.general_regression;

import jakarta.xml.bind.annotation.XmlTransient;
import org.sparkproject.dmg.pmml.PMMLObject;

@XmlTransient
public abstract class ParameterCell extends PMMLObject {
   public abstract String requireParameterName();

   public abstract String getParameterName();

   public abstract ParameterCell setParameterName(String var1);

   public abstract Object getTargetCategory();

   public abstract ParameterCell setTargetCategory(Object var1);
}
