package org.sparkproject.jpmml.model;

import java.util.concurrent.ConcurrentHashMap;
import org.sparkproject.dmg.pmml.PMMLObject;

public class PMMLObjectCache extends ConcurrentHashMap {
   public PMMLObject intern(PMMLObject object) {
      PMMLObjectKey key = new PMMLObjectKey(object);
      E internedObject = (E)((PMMLObject)this.putIfAbsent(key, object));
      return internedObject == null ? object : internedObject;
   }
}
