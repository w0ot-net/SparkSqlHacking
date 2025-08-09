package org.sparkproject.jpmml.model.temporals;

import java.io.Serializable;
import java.util.IllegalFormatException;
import org.sparkproject.dmg.pmml.ComplexValue;
import org.sparkproject.dmg.pmml.DataType;

public abstract class Instant implements ComplexValue, Comparable, Serializable {
   Instant() {
   }

   public abstract DataType getDataType();

   public abstract String format(String var1) throws IllegalFormatException;
}
