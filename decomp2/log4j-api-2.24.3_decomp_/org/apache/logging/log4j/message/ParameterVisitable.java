package org.apache.logging.log4j.message;

import org.apache.logging.log4j.util.PerformanceSensitive;

@PerformanceSensitive({"allocation"})
public interface ParameterVisitable {
   void forEachParameter(ParameterConsumer action, Object state);
}
