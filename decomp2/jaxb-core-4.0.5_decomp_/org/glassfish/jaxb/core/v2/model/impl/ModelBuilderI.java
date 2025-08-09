package org.glassfish.jaxb.core.v2.model.impl;

import org.glassfish.jaxb.core.v2.model.annotation.AnnotationReader;
import org.glassfish.jaxb.core.v2.model.nav.Navigator;

public interface ModelBuilderI {
   Navigator getNavigator();

   AnnotationReader getReader();
}
