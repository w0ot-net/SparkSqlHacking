package org.sparkproject.dmg.pmml.support_vector_machine;

import jakarta.xml.bind.annotation.XmlTransient;
import org.sparkproject.dmg.pmml.PMMLObject;

@XmlTransient
public abstract class Kernel extends PMMLObject {
   public abstract String getDescription();

   public abstract Kernel setDescription(String var1);
}
