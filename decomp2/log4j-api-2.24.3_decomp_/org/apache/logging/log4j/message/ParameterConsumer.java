package org.apache.logging.log4j.message;

public interface ParameterConsumer {
   void accept(Object parameter, int parameterIndex, Object state);
}
