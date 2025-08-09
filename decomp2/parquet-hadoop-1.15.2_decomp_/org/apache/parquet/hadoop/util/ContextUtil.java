package org.apache.parquet.hadoop.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.JobID;
import org.apache.hadoop.mapreduce.MapContext;
import org.apache.hadoop.mapreduce.OutputCommitter;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.StatusReporter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.TaskAttemptID;

public class ContextUtil {
   private static final boolean useV21;
   private static final Constructor JOB_CONTEXT_CONSTRUCTOR;
   private static final Constructor TASK_CONTEXT_CONSTRUCTOR;
   private static final Constructor MAP_CONTEXT_CONSTRUCTOR;
   private static final Constructor MAP_CONTEXT_IMPL_CONSTRUCTOR;
   private static final Constructor GENERIC_COUNTER_CONSTRUCTOR;
   private static final Field READER_FIELD;
   private static final Field WRITER_FIELD;
   private static final Field OUTER_MAP_FIELD;
   private static final Field WRAPPED_CONTEXT_FIELD;
   private static final Method GET_CONFIGURATION_METHOD;
   private static final Method INCREMENT_COUNTER_METHOD;
   private static final Map COUNTER_METHODS_BY_CLASS = new HashMap();

   public static JobContext newJobContext(Configuration conf, JobID jobId) {
      try {
         return (JobContext)JOB_CONTEXT_CONSTRUCTOR.newInstance(conf, jobId);
      } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
         throw new IllegalArgumentException("Can't instantiate JobContext", e);
      }
   }

   public static TaskAttemptContext newTaskAttemptContext(Configuration conf, TaskAttemptID taskAttemptId) {
      try {
         return (TaskAttemptContext)TASK_CONTEXT_CONSTRUCTOR.newInstance(conf, taskAttemptId);
      } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
         throw new IllegalArgumentException("Can't instantiate TaskAttemptContext", e);
      }
   }

   public static Counter newGenericCounter(String name, String displayName, long value) {
      try {
         return (Counter)GENERIC_COUNTER_CONSTRUCTOR.newInstance(name, displayName, value);
      } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
         throw new IllegalArgumentException("Can't instantiate Counter", e);
      }
   }

   public static Configuration getConfiguration(JobContext context) {
      try {
         return (Configuration)GET_CONFIGURATION_METHOD.invoke(context);
      } catch (InvocationTargetException | IllegalAccessException e) {
         throw new IllegalArgumentException("Can't invoke method", e);
      }
   }

   public static Counter getCounter(TaskAttemptContext context, String groupName, String counterName) {
      Method counterMethod = findCounterMethod(context);
      return (Counter)invoke(counterMethod, context, groupName, counterName);
   }

   public static boolean hasCounterMethod(TaskAttemptContext context) {
      return findCounterMethod(context) != null;
   }

   private static Method findCounterMethod(TaskAttemptContext context) {
      if (context != null) {
         if (COUNTER_METHODS_BY_CLASS.containsKey(context.getClass())) {
            return (Method)COUNTER_METHODS_BY_CLASS.get(context.getClass());
         }

         try {
            Method method = context.getClass().getMethod("getCounter", String.class, String.class);
            if (method.getReturnType().isAssignableFrom(Counter.class)) {
               COUNTER_METHODS_BY_CLASS.put(context.getClass(), method);
               return method;
            }
         } catch (NoSuchMethodException var2) {
            return null;
         }
      }

      return null;
   }

   private static Object invoke(Method method, Object obj, Object... args) {
      try {
         return method.invoke(obj, args);
      } catch (InvocationTargetException | IllegalAccessException e) {
         throw new IllegalArgumentException("Can't invoke method " + method.getName(), e);
      }
   }

   public static void incrementCounter(Counter counter, long increment) {
      invoke(INCREMENT_COUNTER_METHOD, counter, increment);
   }

   static {
      boolean v21 = true;
      String PACKAGE = "org.apache.hadoop.mapreduce";

      try {
         Class.forName("org.apache.hadoop.mapreduce.task.JobContextImpl");
      } catch (ClassNotFoundException var18) {
         v21 = false;
      }

      useV21 = v21;

      Class<?> jobContextCls;
      Class<?> taskContextCls;
      Class<?> taskIOContextCls;
      Class<?> mapCls;
      Class<?> mapContextCls;
      Class<?> innerMapContextCls;
      Class<?> genericCounterCls;
      try {
         if (v21) {
            jobContextCls = Class.forName("org.apache.hadoop.mapreduce.task.JobContextImpl");
            taskContextCls = Class.forName("org.apache.hadoop.mapreduce.task.TaskAttemptContextImpl");
            taskIOContextCls = Class.forName("org.apache.hadoop.mapreduce.task.TaskInputOutputContextImpl");
            mapContextCls = Class.forName("org.apache.hadoop.mapreduce.task.MapContextImpl");
            mapCls = Class.forName("org.apache.hadoop.mapreduce.lib.map.WrappedMapper");
            innerMapContextCls = Class.forName("org.apache.hadoop.mapreduce.lib.map.WrappedMapper$Context");
            genericCounterCls = Class.forName("org.apache.hadoop.mapreduce.counters.GenericCounter");
         } else {
            jobContextCls = Class.forName("org.apache.hadoop.mapreduce.JobContext");
            taskContextCls = Class.forName("org.apache.hadoop.mapreduce.TaskAttemptContext");
            taskIOContextCls = Class.forName("org.apache.hadoop.mapreduce.TaskInputOutputContext");
            mapContextCls = Class.forName("org.apache.hadoop.mapreduce.MapContext");
            mapCls = Class.forName("org.apache.hadoop.mapreduce.Mapper");
            innerMapContextCls = Class.forName("org.apache.hadoop.mapreduce.Mapper$Context");
            genericCounterCls = Class.forName("org.apache.hadoop.mapred.Counters$Counter");
         }
      } catch (ClassNotFoundException e) {
         throw new IllegalArgumentException("Can't find class", e);
      }

      try {
         JOB_CONTEXT_CONSTRUCTOR = jobContextCls.getConstructor(Configuration.class, JobID.class);
         JOB_CONTEXT_CONSTRUCTOR.setAccessible(true);
         TASK_CONTEXT_CONSTRUCTOR = taskContextCls.getConstructor(Configuration.class, TaskAttemptID.class);
         TASK_CONTEXT_CONSTRUCTOR.setAccessible(true);
         GENERIC_COUNTER_CONSTRUCTOR = genericCounterCls.getDeclaredConstructor(String.class, String.class, Long.TYPE);
         GENERIC_COUNTER_CONSTRUCTOR.setAccessible(true);
         if (useV21) {
            MAP_CONTEXT_CONSTRUCTOR = innerMapContextCls.getConstructor(mapCls, MapContext.class);
            MAP_CONTEXT_IMPL_CONSTRUCTOR = mapContextCls.getDeclaredConstructor(Configuration.class, TaskAttemptID.class, RecordReader.class, RecordWriter.class, OutputCommitter.class, StatusReporter.class, InputSplit.class);
            MAP_CONTEXT_IMPL_CONSTRUCTOR.setAccessible(true);
            WRAPPED_CONTEXT_FIELD = innerMapContextCls.getDeclaredField("mapContext");
            WRAPPED_CONTEXT_FIELD.setAccessible(true);

            try {
               Class<?> taskAttemptContextClass = Class.forName("org.apache.hadoop.mapreduce.TaskAttemptContext");
               Method getCounterMethodForTaskAttemptContext = taskAttemptContextClass.getMethod("getCounter", String.class, String.class);
               COUNTER_METHODS_BY_CLASS.put(taskAttemptContextClass, getCounterMethodForTaskAttemptContext);
            } catch (ClassNotFoundException var12) {
               Class<?> taskInputOutputContextClass = Class.forName("org.apache.hadoop.mapreduce.TaskInputOutputContext");
               Method getCounterMethodForTaskInputOutputContextClass = taskInputOutputContextClass.getMethod("getCounter", String.class, String.class);
               COUNTER_METHODS_BY_CLASS.put(taskInputOutputContextClass, getCounterMethodForTaskInputOutputContextClass);
            }
         } else {
            MAP_CONTEXT_CONSTRUCTOR = innerMapContextCls.getConstructor(mapCls, Configuration.class, TaskAttemptID.class, RecordReader.class, RecordWriter.class, OutputCommitter.class, StatusReporter.class, InputSplit.class);
            MAP_CONTEXT_IMPL_CONSTRUCTOR = null;
            WRAPPED_CONTEXT_FIELD = null;
            COUNTER_METHODS_BY_CLASS.put(taskIOContextCls, taskIOContextCls.getMethod("getCounter", String.class, String.class));
         }

         MAP_CONTEXT_CONSTRUCTOR.setAccessible(true);
         READER_FIELD = mapContextCls.getDeclaredField("reader");
         READER_FIELD.setAccessible(true);
         WRITER_FIELD = taskIOContextCls.getDeclaredField("output");
         WRITER_FIELD.setAccessible(true);
         OUTER_MAP_FIELD = innerMapContextCls.getDeclaredField("this$0");
         OUTER_MAP_FIELD.setAccessible(true);
         GET_CONFIGURATION_METHOD = Class.forName("org.apache.hadoop.mapreduce.JobContext").getMethod("getConfiguration");
         INCREMENT_COUNTER_METHOD = Class.forName("org.apache.hadoop.mapreduce.Counter").getMethod("increment", Long.TYPE);
      } catch (SecurityException e) {
         throw new IllegalArgumentException("Can't run constructor ", e);
      } catch (NoSuchMethodException e) {
         throw new IllegalArgumentException("Can't find constructor ", e);
      } catch (NoSuchFieldException e) {
         throw new IllegalArgumentException("Can't find field ", e);
      } catch (ClassNotFoundException e) {
         throw new IllegalArgumentException("Can't find class", e);
      }
   }
}
