package org.apache.spark.internal;

import org.slf4j.LoggerFactory;

public class SparkLoggerFactory {
   public static SparkLogger getLogger(String name) {
      return new SparkLogger(LoggerFactory.getLogger(name));
   }

   public static SparkLogger getLogger(Class clazz) {
      return new SparkLogger(LoggerFactory.getLogger(clazz));
   }
}
