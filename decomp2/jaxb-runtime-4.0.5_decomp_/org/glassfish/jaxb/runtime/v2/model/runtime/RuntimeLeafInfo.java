package org.glassfish.jaxb.runtime.v2.model.runtime;

import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.v2.model.core.LeafInfo;
import org.glassfish.jaxb.runtime.v2.runtime.Transducer;

public interface RuntimeLeafInfo extends LeafInfo, RuntimeNonElement {
   Transducer getTransducer();

   Class getClazz();

   QName[] getTypeNames();
}
