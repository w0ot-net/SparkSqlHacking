package org.apache.spark.metrics.source;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

public final class CodegenMetrics$ implements Source {
   public static final CodegenMetrics$ MODULE$ = new CodegenMetrics$();
   private static final String sourceName = "CodeGenerator";
   private static final MetricRegistry metricRegistry = new MetricRegistry();
   private static final Histogram METRIC_SOURCE_CODE_SIZE;
   private static final Histogram METRIC_COMPILATION_TIME;
   private static final Histogram METRIC_GENERATED_CLASS_BYTECODE_SIZE;
   private static final Histogram METRIC_GENERATED_METHOD_BYTECODE_SIZE;

   static {
      METRIC_SOURCE_CODE_SIZE = MODULE$.metricRegistry().histogram(MetricRegistry.name("sourceCodeSize", new String[0]));
      METRIC_COMPILATION_TIME = MODULE$.metricRegistry().histogram(MetricRegistry.name("compilationTime", new String[0]));
      METRIC_GENERATED_CLASS_BYTECODE_SIZE = MODULE$.metricRegistry().histogram(MetricRegistry.name("generatedClassSize", new String[0]));
      METRIC_GENERATED_METHOD_BYTECODE_SIZE = MODULE$.metricRegistry().histogram(MetricRegistry.name("generatedMethodSize", new String[0]));
   }

   public String sourceName() {
      return sourceName;
   }

   public MetricRegistry metricRegistry() {
      return metricRegistry;
   }

   public Histogram METRIC_SOURCE_CODE_SIZE() {
      return METRIC_SOURCE_CODE_SIZE;
   }

   public Histogram METRIC_COMPILATION_TIME() {
      return METRIC_COMPILATION_TIME;
   }

   public Histogram METRIC_GENERATED_CLASS_BYTECODE_SIZE() {
      return METRIC_GENERATED_CLASS_BYTECODE_SIZE;
   }

   public Histogram METRIC_GENERATED_METHOD_BYTECODE_SIZE() {
      return METRIC_GENERATED_METHOD_BYTECODE_SIZE;
   }

   private CodegenMetrics$() {
   }
}
