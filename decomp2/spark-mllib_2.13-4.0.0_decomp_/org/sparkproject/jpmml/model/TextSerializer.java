package org.sparkproject.jpmml.model;

import java.io.OutputStream;
import org.sparkproject.dmg.pmml.PMMLObject;

public interface TextSerializer extends Serializer {
   default void serializePretty(PMMLObject object, OutputStream os) throws Exception {
      this.serialize(object, os);
   }
}
