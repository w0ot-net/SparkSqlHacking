package org.sparkproject.dmg.pmml;

import jakarta.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class ModelQuality extends PMMLObject {
   public abstract String getDataName();

   public abstract ModelQuality setDataName(String var1);
}
