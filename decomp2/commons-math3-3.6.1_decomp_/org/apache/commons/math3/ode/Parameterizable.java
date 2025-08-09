package org.apache.commons.math3.ode;

import java.util.Collection;

public interface Parameterizable {
   Collection getParametersNames();

   boolean isSupported(String var1);
}
