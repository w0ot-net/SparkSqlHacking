package org.sparkproject.jpmml.model.cells;

import jakarta.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import org.sparkproject.dmg.pmml.Cell;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "input",
   namespace = "http://jpmml.org/jpmml-model/InlineTable"
)
public class InputCell extends Cell {
   public static final QName QNAME = new QName("http://jpmml.org/jpmml-model/InlineTable", "input", "data");

   public InputCell() {
   }

   @ValueConstructor
   public InputCell(@Property("value") Object value) {
      super(value);
   }

   public QName getName() {
      return QNAME;
   }
}
