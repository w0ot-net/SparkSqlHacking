package org.slf4j.spi;

import java.util.Deque;
import java.util.Map;

public interface MDCAdapter {
   void put(String var1, String var2);

   String get(String var1);

   void remove(String var1);

   void clear();

   Map getCopyOfContextMap();

   void setContextMap(Map var1);

   void pushByKey(String var1, String var2);

   String popByKey(String var1);

   Deque getCopyOfDequeByKey(String var1);

   void clearDequeByKey(String var1);
}
