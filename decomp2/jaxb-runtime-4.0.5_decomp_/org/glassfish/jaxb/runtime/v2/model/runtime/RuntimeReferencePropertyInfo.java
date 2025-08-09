package org.glassfish.jaxb.runtime.v2.model.runtime;

import java.util.Set;
import org.glassfish.jaxb.core.v2.model.core.ReferencePropertyInfo;

public interface RuntimeReferencePropertyInfo extends ReferencePropertyInfo, RuntimePropertyInfo {
   Set getElements();
}
