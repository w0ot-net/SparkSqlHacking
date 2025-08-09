package org.apache.arrow.memory;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class CheckAllocator {
   private static final Logger logger = LoggerFactory.getLogger(CheckAllocator.class);
   private static final String ALLOCATOR_PATH_CORE = "org/apache/arrow/memory/DefaultAllocationManagerFactory.class";
   private static final String ALLOCATOR_PATH_UNSAFE = "org/apache/arrow/memory/unsafe/DefaultAllocationManagerFactory.class";
   private static final String ALLOCATOR_PATH_NETTY = "org/apache/arrow/memory/netty/DefaultAllocationManagerFactory.class";

   private CheckAllocator() {
   }

   static String check() {
      Set<URL> urls = scanClasspath();
      URL rootAllocator = assertOnlyOne(urls);
      reportResult(rootAllocator);
      if (!rootAllocator.getPath().contains("memory-core") && !rootAllocator.getPath().contains("/org/apache/arrow/memory/core/")) {
         if (!rootAllocator.getPath().contains("memory-unsafe") && !rootAllocator.getPath().contains("/org/apache/arrow/memory/unsafe/")) {
            if (!rootAllocator.getPath().contains("memory-netty") && !rootAllocator.getPath().contains("/org/apache/arrow/memory/netty/")) {
               throw new IllegalStateException("Unknown allocation manager type to infer. Current: " + rootAllocator.getPath());
            } else {
               return "org.apache.arrow.memory.netty.DefaultAllocationManagerFactory";
            }
         } else {
            return "org.apache.arrow.memory.unsafe.DefaultAllocationManagerFactory";
         }
      } else {
         return "org.apache.arrow.memory.DefaultAllocationManagerFactory";
      }
   }

   private static Set scanClasspath() {
      Set<URL> allocatorPathSet = new LinkedHashSet();

      try {
         ClassLoader allocatorClassLoader = CheckAllocator.class.getClassLoader();
         Enumeration<URL> paths;
         if (allocatorClassLoader == null) {
            paths = ClassLoader.getSystemResources("org/apache/arrow/memory/DefaultAllocationManagerFactory.class");
            if (!paths.hasMoreElements()) {
               paths = ClassLoader.getSystemResources("org/apache/arrow/memory/unsafe/DefaultAllocationManagerFactory.class");
            }

            if (!paths.hasMoreElements()) {
               paths = ClassLoader.getSystemResources("org/apache/arrow/memory/netty/DefaultAllocationManagerFactory.class");
            }
         } else {
            paths = allocatorClassLoader.getResources("org/apache/arrow/memory/DefaultAllocationManagerFactory.class");
            if (!paths.hasMoreElements()) {
               paths = allocatorClassLoader.getResources("org/apache/arrow/memory/unsafe/DefaultAllocationManagerFactory.class");
            }

            if (!paths.hasMoreElements()) {
               paths = allocatorClassLoader.getResources("org/apache/arrow/memory/netty/DefaultAllocationManagerFactory.class");
            }
         }

         while(paths.hasMoreElements()) {
            URL path = (URL)paths.nextElement();
            allocatorPathSet.add(path);
         }
      } catch (IOException ioe) {
         logger.error("Error getting resources from path", ioe);
      }

      return allocatorPathSet;
   }

   private static void reportResult(URL rootAllocator) {
      String path = rootAllocator.getPath();
      String subPath = path.substring(path.indexOf("memory"));
      logger.info("Using DefaultAllocationManager at {}", subPath);
   }

   private static URL assertOnlyOne(Set urls) {
      if (urls.size() > 1) {
         logger.warn("More than one DefaultAllocationManager on classpath. Choosing first found");
      }

      if (urls.isEmpty()) {
         throw new RuntimeException("No DefaultAllocationManager found on classpath. Can't allocate Arrow buffers. Please consider adding arrow-memory-netty or arrow-memory-unsafe as a dependency.");
      } else {
         return (URL)urls.iterator().next();
      }
   }
}
