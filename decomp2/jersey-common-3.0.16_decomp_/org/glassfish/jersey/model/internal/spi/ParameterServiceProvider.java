package org.glassfish.jersey.model.internal.spi;

import java.util.Map;
import org.glassfish.jersey.model.Parameter;

public interface ParameterServiceProvider {
   Map getParameterAnnotationHelperMap();

   Parameter.ParamCreationFactory getParameterCreationFactory();
}
