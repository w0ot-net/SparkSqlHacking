package com.fasterxml.jackson.databind.introspect;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

public final class AnnotatedMethodMap implements Iterable {
   protected Map _methods;

   public AnnotatedMethodMap() {
   }

   public AnnotatedMethodMap(Map m) {
      this._methods = m;
   }

   public int size() {
      return this._methods == null ? 0 : this._methods.size();
   }

   public AnnotatedMethod find(String name, Class[] paramTypes) {
      return this._methods == null ? null : (AnnotatedMethod)this._methods.get(new MemberKey(name, paramTypes));
   }

   public AnnotatedMethod find(Method m) {
      return this._methods == null ? null : (AnnotatedMethod)this._methods.get(new MemberKey(m));
   }

   public Iterator iterator() {
      return this._methods == null ? Collections.emptyIterator() : this._methods.values().iterator();
   }
}
