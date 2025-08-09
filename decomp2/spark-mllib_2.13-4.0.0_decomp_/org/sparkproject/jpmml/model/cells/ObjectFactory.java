package org.sparkproject.jpmml.model.cells;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public InputCell createInputCell() {
      return new InputCell();
   }

   public OutputCell createOutputCell() {
      return new OutputCell();
   }
}
