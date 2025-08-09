package org.sparkproject.dmg.pmml;

import jakarta.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class Entity extends PMMLObject implements HasId {
   public boolean hasId() {
      V id = (V)this.getId();
      return id != null;
   }
}
