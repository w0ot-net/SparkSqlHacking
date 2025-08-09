package org.apache.arrow.vector.holders;

import org.apache.arrow.vector.complex.reader.FieldReader;

public class ComplexHolder implements ValueHolder {
   public FieldReader reader;
   public int isSet;
}
