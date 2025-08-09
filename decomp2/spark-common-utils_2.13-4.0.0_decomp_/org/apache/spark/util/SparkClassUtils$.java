package org.apache.spark.util;

import java.util.Random;

public final class SparkClassUtils$ implements SparkClassUtils {
   public static final SparkClassUtils$ MODULE$ = new SparkClassUtils$();
   private static Random random;

   static {
      SparkClassUtils.$init$(MODULE$);
   }

   public ClassLoader getSparkClassLoader() {
      return SparkClassUtils.getSparkClassLoader$(this);
   }

   public ClassLoader getContextOrSparkClassLoader() {
      return SparkClassUtils.getContextOrSparkClassLoader$(this);
   }

   public Class classForName(final String className, final boolean initialize, final boolean noSparkClassLoader) {
      return SparkClassUtils.classForName$(this, className, initialize, noSparkClassLoader);
   }

   public boolean classForName$default$2() {
      return SparkClassUtils.classForName$default$2$(this);
   }

   public boolean classForName$default$3() {
      return SparkClassUtils.classForName$default$3$(this);
   }

   public boolean classIsLoadable(final String clazz) {
      return SparkClassUtils.classIsLoadable$(this, clazz);
   }

   public boolean classIsLoadableAndAssignableFrom(final String clazz, final Class targetClass) {
      return SparkClassUtils.classIsLoadableAndAssignableFrom$(this, clazz, targetClass);
   }

   public String getFormattedClassName(final Object obj) {
      return SparkClassUtils.getFormattedClassName$(this, obj);
   }

   public String getSimpleName(final Class cls) {
      return SparkClassUtils.getSimpleName$(this, cls);
   }

   public final String stripDollars(final String s) {
      return SparkClassUtils.stripDollars$(this, s);
   }

   public Random random() {
      return random;
   }

   public void org$apache$spark$util$SparkClassUtils$_setter_$random_$eq(final Random x$1) {
      random = x$1;
   }

   private SparkClassUtils$() {
   }
}
