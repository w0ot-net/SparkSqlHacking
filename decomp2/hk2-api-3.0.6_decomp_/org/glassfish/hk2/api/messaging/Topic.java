package org.glassfish.hk2.api.messaging;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

public interface Topic {
   void publish(Object var1);

   Topic named(String var1);

   Topic ofType(Type var1);

   Topic qualifiedWith(Annotation... var1);

   Type getTopicType();

   Set getTopicQualifiers();
}
