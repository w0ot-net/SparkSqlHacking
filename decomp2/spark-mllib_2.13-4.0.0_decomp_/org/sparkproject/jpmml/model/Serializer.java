package org.sparkproject.jpmml.model;

import java.io.InputStream;
import java.io.OutputStream;
import org.sparkproject.dmg.pmml.PMMLObject;

public interface Serializer {
   PMMLObject deserialize(InputStream var1) throws Exception;

   void serialize(PMMLObject var1, OutputStream var2) throws Exception;
}
