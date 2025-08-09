package org.slf4j.helpers;

import java.util.Deque;
import java.util.Map;
import org.slf4j.spi.MDCAdapter;

public class NOPMDCAdapter implements MDCAdapter {
   public void clear() {
   }

   public String get(String key) {
      return null;
   }

   public void put(String key, String val) {
   }

   public void remove(String key) {
   }

   public Map getCopyOfContextMap() {
      return null;
   }

   public void setContextMap(Map contextMap) {
   }

   public void pushByKey(String key, String value) {
   }

   public String popByKey(String key) {
      return null;
   }

   public Deque getCopyOfDequeByKey(String key) {
      return null;
   }

   public void clearDequeByKey(String key) {
   }
}
