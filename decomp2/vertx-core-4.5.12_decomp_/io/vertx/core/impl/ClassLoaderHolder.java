package io.vertx.core.impl;

class ClassLoaderHolder {
   final String group;
   final ClassLoader loader;
   int refCount;

   ClassLoaderHolder(String group, ClassLoader loader) {
      this.group = group;
      this.loader = loader;
   }
}
