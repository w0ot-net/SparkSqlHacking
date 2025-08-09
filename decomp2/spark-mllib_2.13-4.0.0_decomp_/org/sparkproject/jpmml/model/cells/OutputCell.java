package org.sparkproject.jpmml.model.cells;

import jakarta.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import org.sparkproject.dmg.pmml.Cell;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "output",
   namespace = "http://jpmml.org/jpmml-model/InlineTable"
)
public class OutputCell extends Cell {
   public static final QName QNAME = new QName("http://jpmml.org/jpmml-model/InlineTable", "output", "data");

   public OutputCell() {
   }

   @ValueConstructor
   public OutputCell(@Property("value") Object value) {
      super(value);
   }

   public QName getName() {
      return QNAME;
   }
}
