package org.apache.logging.log4j.layout.template.json.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Supplier;
import org.apache.logging.log4j.core.util.Constants;
import org.apache.logging.log4j.core.util.Integers;
import org.apache.logging.log4j.util.LoaderUtil;
import org.jctools.queues.MpmcArrayQueue;

public final class RecyclerFactories {
   private static final String JCTOOLS_QUEUE_CLASS_SUPPLIER_PATH = "org.jctools.queues.MpmcArrayQueue.new";
   private static final boolean JCTOOLS_QUEUE_CLASS_AVAILABLE = isJctoolsQueueClassAvailable();

   private RecyclerFactories() {
   }

   private static boolean isJctoolsQueueClassAvailable() {
      try {
         String className = "org.jctools.queues.MpmcArrayQueue.new".replaceAll("\\.new$", "");
         LoaderUtil.loadClass(className);
         return true;
      } catch (ClassNotFoundException var1) {
         return false;
      }
   }

   public static RecyclerFactory ofSpec(final String recyclerFactorySpec) {
      int defaultCapacity = Math.max(2 * Runtime.getRuntime().availableProcessors() + 1, 8);
      if (recyclerFactorySpec == null) {
         if (Constants.ENABLE_THREADLOCALS) {
            return ThreadLocalRecyclerFactory.getInstance();
         } else {
            Supplier<Queue<Object>> queueSupplier = JCTOOLS_QUEUE_CLASS_AVAILABLE ? () -> new MpmcArrayQueue(defaultCapacity) : () -> new ArrayBlockingQueue(defaultCapacity);
            return new QueueingRecyclerFactory(queueSupplier);
         }
      } else if (recyclerFactorySpec.equals("dummy")) {
         return DummyRecyclerFactory.getInstance();
      } else if (recyclerFactorySpec.equals("threadLocal")) {
         return ThreadLocalRecyclerFactory.getInstance();
      } else if (recyclerFactorySpec.startsWith("queue")) {
         return readQueueingRecyclerFactory(recyclerFactorySpec, defaultCapacity);
      } else {
         throw new IllegalArgumentException("invalid recycler factory: " + recyclerFactorySpec);
      }
   }

   private static RecyclerFactory readQueueingRecyclerFactory(final String recyclerFactorySpec, final int defaultCapacity) {
      String queueFactorySpec = recyclerFactorySpec.substring("queue".length() + (recyclerFactorySpec.startsWith("queue:") ? 1 : 0));
      Map<String, StringParameterParser.Value> parsedValues = StringParameterParser.parse(queueFactorySpec, new LinkedHashSet(Arrays.asList("supplier", "capacity")));
      StringParameterParser.Value supplierValue = (StringParameterParser.Value)parsedValues.get("supplier");
      String supplierPath;
      if (supplierValue != null && !(supplierValue instanceof StringParameterParser.NullValue)) {
         supplierPath = supplierValue.toString();
      } else {
         supplierPath = JCTOOLS_QUEUE_CLASS_AVAILABLE ? "org.jctools.queues.MpmcArrayQueue.new" : "java.util.concurrent.ArrayBlockingQueue.new";
      }

      StringParameterParser.Value capacityValue = (StringParameterParser.Value)parsedValues.get("capacity");
      int capacity;
      if (capacityValue != null && !(capacityValue instanceof StringParameterParser.NullValue)) {
         try {
            capacity = Integers.parseInt(capacityValue.toString());
         } catch (NumberFormatException error) {
            throw new IllegalArgumentException("failed reading capacity in queueing recycler factory: " + queueFactorySpec, error);
         }
      } else {
         capacity = defaultCapacity;
      }

      return createRecyclerFactory(queueFactorySpec, supplierPath, capacity);
   }

   private static RecyclerFactory createRecyclerFactory(final String queueFactorySpec, final String supplierPath, final int capacity) {
      int supplierPathSplitterIndex = supplierPath.lastIndexOf(46);
      if (supplierPathSplitterIndex < 0) {
         throw new IllegalArgumentException("invalid supplier in queueing recycler factory: " + queueFactorySpec);
      } else {
         String supplierClassName = supplierPath.substring(0, supplierPathSplitterIndex);
         String supplierMethodName = supplierPath.substring(supplierPathSplitterIndex + 1);

         try {
            Class<?> supplierClass = LoaderUtil.loadClass(supplierClassName);
            Supplier<Queue<Object>> queueSupplier;
            if ("new".equals(supplierMethodName)) {
               Constructor<?> supplierCtor = supplierClass.getDeclaredConstructor(Integer.TYPE);
               queueSupplier = () -> {
                  try {
                     Queue<Object> typedQueue = (Queue)supplierCtor.newInstance(capacity);
                     return typedQueue;
                  } catch (Exception error) {
                     throw new RuntimeException("recycler queue construction failed for factory: " + queueFactorySpec, error);
                  }
               };
            } else {
               Method supplierMethod = supplierClass.getMethod(supplierMethodName, Integer.TYPE);
               queueSupplier = () -> {
                  try {
                     Queue<Object> typedQueue = (Queue)supplierMethod.invoke((Object)null, capacity);
                     return typedQueue;
                  } catch (Exception error) {
                     throw new RuntimeException("recycler queue construction failed for factory: " + queueFactorySpec, error);
                  }
               };
            }

            return new QueueingRecyclerFactory(queueSupplier);
         } catch (Exception error) {
            throw new RuntimeException("failed executing queueing recycler factory: " + queueFactorySpec, error);
         }
      }
   }
}
