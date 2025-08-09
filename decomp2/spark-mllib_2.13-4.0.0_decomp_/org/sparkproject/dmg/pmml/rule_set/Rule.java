package org.sparkproject.dmg.pmml.rule_set;

import jakarta.xml.bind.annotation.XmlTransient;
import org.sparkproject.dmg.pmml.Entity;
import org.sparkproject.dmg.pmml.HasPredicate;

@XmlTransient
public abstract class Rule extends Entity implements HasPredicate {
   public String getId() {
      throw new UnsupportedOperationException();
   }

   public Rule setId(String id) {
      throw new UnsupportedOperationException();
   }
}
