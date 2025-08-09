package io.fabric8.kubernetes.client.utils;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
   private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);
   private static final String ALL_CHARS = "abcdefghijklmnopqrstuvwxyz0123456789";
   public static final String WINDOWS = "win";
   public static final String OS_NAME = "os.name";
   public static final String PATH_WINDOWS = "Path";
   public static final String PATH_UNIX = "PATH";
   private static final Random random = new Random();
   private static final AtomicLong leastSigBits = new AtomicLong();
   private static final CachedSingleThreadScheduler SHARED_SCHEDULER = new CachedSingleThreadScheduler();

   private Utils() {
   }

   public static Object checkNotNull(Object ref, String message) {
      if (ref == null) {
         throw new NullPointerException(message);
      } else {
         return ref;
      }
   }

   public static String getSystemPropertyOrEnvVar(String systemPropertyName, String envVarName, String defaultValue) {
      String answer = System.getProperty(systemPropertyName);
      if (isNotNullOrEmpty(answer)) {
         return answer;
      } else {
         answer = System.getenv(envVarName);
         return isNotNullOrEmpty(answer) ? answer : defaultValue;
      }
   }

   public static String convertSystemPropertyNameToEnvVar(String systemPropertyName) {
      return systemPropertyName.toUpperCase(Locale.ROOT).replaceAll("[.-]", "_");
   }

   public static String getEnvVar(String envVarName, String defaultValue) {
      String answer = System.getenv(envVarName);
      return isNotNullOrEmpty(answer) ? answer : defaultValue;
   }

   public static String getSystemPropertyOrEnvVar(String systemPropertyName, String defaultValue) {
      return getSystemPropertyOrEnvVar(systemPropertyName, convertSystemPropertyNameToEnvVar(systemPropertyName), defaultValue);
   }

   public static String getSystemPropertyOrEnvVar(String systemPropertyName) {
      return getSystemPropertyOrEnvVar(systemPropertyName, (String)null);
   }

   public static boolean getSystemPropertyOrEnvVar(String systemPropertyName, Boolean defaultValue) {
      String result = getSystemPropertyOrEnvVar(systemPropertyName, defaultValue.toString());
      return Boolean.parseBoolean(result);
   }

   public static int getSystemPropertyOrEnvVar(String systemPropertyName, int defaultValue) {
      String result = getSystemPropertyOrEnvVar(systemPropertyName, Integer.toString(defaultValue));
      return Integer.parseInt(result);
   }

   public static String join(Object[] array) {
      return join(array, ',');
   }

   public static String join(Object[] array, char separator) {
      if (array == null) {
         return null;
      } else if (array.length == 0) {
         return "";
      } else {
         StringBuilder buf = new StringBuilder();

         for(int i = 0; i < array.length; ++i) {
            if (i > 0) {
               buf.append(separator);
            }

            if (array[i] != null) {
               buf.append(array[i]);
            }
         }

         return buf.toString();
      }
   }

   public static boolean waitUntilReady(Future future, long amount, TimeUnit timeUnit) {
      try {
         if (amount < 0L) {
            future.get();
         } else {
            future.get(amount, timeUnit);
         }

         return true;
      } catch (TimeoutException var6) {
         return false;
      } catch (ExecutionException e) {
         Throwable t = e;
         if (e.getCause() != null) {
            t = e.getCause();
         }

         t.addSuppressed(new Throwable("waiting here"));
         throw KubernetesClientException.launderThrowable(t);
      } catch (Exception e) {
         throw KubernetesClientException.launderThrowable(e);
      }
   }

   public static void waitUntilReadyOrFail(Future future, long amount, TimeUnit timeUnit) {
      if (!waitUntilReady(future, amount, timeUnit)) {
         throw KubernetesClientException.launderThrowable(new TimeoutException("not ready after " + amount + " " + timeUnit));
      }
   }

   public static void closeQuietly(Iterable closeables) {
      for(Closeable c : closeables) {
         try {
            if (c instanceof Flushable) {
               ((Flushable)c).flush();
            }

            if (c != null) {
               c.close();
            }
         } catch (IOException var4) {
            LOGGER.debug("Error closing: {}", c);
         }
      }

   }

   public static void closeQuietly(Closeable... closeables) {
      closeQuietly((Iterable)Arrays.asList(closeables));
   }

   public static String coalesce(String... items) {
      for(String str : items) {
         if (str != null) {
            return str;
         }
      }

      return null;
   }

   public static UUID generateId() {
      return new UUID(0L, leastSigBits.incrementAndGet());
   }

   public static String randomString(int length) {
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < length; ++i) {
         int index = random.nextInt("abcdefghijklmnopqrstuvwxyz0123456789".length());
         sb.append("abcdefghijklmnopqrstuvwxyz0123456789".charAt(index));
      }

      return sb.toString();
   }

   public static String filePath(URL path) {
      if (path == null) {
         throw new KubernetesClientException("Path is required");
      } else {
         try {
            return Paths.get(path.toURI()).toString();
         } catch (URISyntaxException e) {
            throw KubernetesClientException.launderThrowable(e);
         }
      }
   }

   public static String replaceAllWithoutRegex(String text, String from, String to) {
      if (text == null) {
         return null;
      } else {
         int idx = 0;

         while(true) {
            idx = text.indexOf(from, idx);
            if (idx < 0) {
               return text;
            }

            text = text.substring(0, idx) + to + text.substring(idx + from.length());
            idx += to.length();
         }
      }
   }

   public static boolean isNullOrEmpty(String str) {
      return str == null || str.isEmpty();
   }

   public static boolean isNotNullOrEmpty(Map map) {
      return map != null && !map.isEmpty();
   }

   public static boolean isNotNullOrEmpty(String str) {
      return !isNullOrEmpty(str);
   }

   public static boolean isNotNullOrEmpty(String[] array) {
      return array != null && array.length != 0;
   }

   public static boolean isNotNull(Object... refList) {
      return (Boolean)Optional.ofNullable(refList).map((refs) -> Stream.of(refs).allMatch(Objects::nonNull)).orElse(false);
   }

   public static Object getNonNullOrElse(Object obj, Object defaultObj) {
      return obj != null ? obj : defaultObj;
   }

   public static String getProperty(Map properties, String propertyName, String defaultValue) {
      String answer = (String)properties.get(propertyName);
      return !isNullOrEmpty(answer) ? answer : getSystemPropertyOrEnvVar(propertyName, defaultValue);
   }

   public static String getProperty(Map properties, String propertyName) {
      return getProperty(properties, propertyName, (String)null);
   }

   public static String toUrlEncoded(String str) {
      try {
         return URLEncoder.encode(str, StandardCharsets.UTF_8.displayName());
      } catch (UnsupportedEncodingException var2) {
         return null;
      }
   }

   public static boolean isResourceNamespaced(Class kubernetesResourceType) {
      return Namespaced.class.isAssignableFrom(kubernetesResourceType);
   }

   public static String getAnnotationValue(Class kubernetesResourceType, Class annotationClass) {
      Annotation annotation = kubernetesResourceType.getAnnotation(annotationClass);
      if (annotation instanceof Group) {
         return ((Group)annotation).value();
      } else {
         return annotation instanceof Version ? ((Version)annotation).value() : null;
      }
   }

   public static String interpolateString(String templateInput, Map valuesMap) {
      return (String)((Function)((Map)Optional.ofNullable(valuesMap).orElse(Collections.emptyMap())).entrySet().stream().filter((entry) -> entry.getKey() != null).filter((entry) -> entry.getValue() != null).flatMap((entry) -> {
         String key = (String)entry.getKey();
         String value = (String)entry.getValue();
         return Stream.of(new AbstractMap.SimpleEntry("${" + key + "}", value), new AbstractMap.SimpleEntry("\"${{" + key + "}}\"", value), new AbstractMap.SimpleEntry("${{" + key + "}}", value));
      }).map((explodedParam) -> (s) -> s.replace((CharSequence)explodedParam.getKey(), (CharSequence)explodedParam.getValue())).reduce(Function.identity(), Function::andThen)).apply((String)Objects.requireNonNull(templateInput, "templateInput is required"));
   }

   public static boolean isWindowsOperatingSystem() {
      return getOperatingSystemFromSystemProperty().toLowerCase().contains("win");
   }

   public static String getSystemPathVariable() {
      return System.getenv(isWindowsOperatingSystem() ? "Path" : "PATH");
   }

   public static List getCommandPlatformPrefix() {
      List<String> platformPrefixParts = new ArrayList();
      if (isWindowsOperatingSystem()) {
         platformPrefixParts.add("cmd.exe");
         platformPrefixParts.add("/c");
      } else {
         platformPrefixParts.add("sh");
         platformPrefixParts.add("-c");
      }

      return platformPrefixParts;
   }

   private static String getOperatingSystemFromSystemProperty() {
      return System.getProperty("os.name");
   }

   public static ThreadFactory daemonThreadFactory(Object forObject) {
      String var10000 = forObject.getClass().getSimpleName();
      String name = var10000 + "-" + System.identityHashCode(forObject);
      return daemonThreadFactory(name);
   }

   static ThreadFactory daemonThreadFactory(final String name) {
      return new ThreadFactory() {
         ThreadFactory threadFactory = Executors.defaultThreadFactory();

         public Thread newThread(Runnable r) {
            Thread ret = this.threadFactory.newThread(r);
            String var10001 = name;
            ret.setName(var10001 + "-" + ret.getName());
            ret.setDaemon(true);
            return ret;
         }
      };
   }

   public static CompletableFuture schedule(Executor executor, Runnable command, long delay, TimeUnit unit) {
      CompletableFuture<Void> result = new CompletableFuture();
      ScheduledFuture<?> scheduledFuture = SHARED_SCHEDULER.schedule(() -> {
         try {
            executor.execute(command);
            result.complete((Object)null);
         } catch (Throwable t) {
            result.completeExceptionally(t);
         }

      }, delay, unit);
      result.whenComplete((v, t) -> scheduledFuture.cancel(true));
      return result;
   }

   public static CompletableFuture scheduleAtFixedRate(Executor executor, Runnable command, long initialDelay, long delay, TimeUnit unit) {
      CompletableFuture<Void> completion = new CompletableFuture();
      scheduleWithVariableRate(completion, executor, command, initialDelay, () -> delay, unit);
      return completion;
   }

   public static void scheduleWithVariableRate(CompletableFuture completion, Executor executor, Runnable command, long initialDelay, LongSupplier nextDelay, TimeUnit unit) {
      AtomicReference<ScheduledFuture<?>> currentScheduledFuture = new AtomicReference();
      AtomicLong next = new AtomicLong(unit.convert(System.nanoTime(), TimeUnit.NANOSECONDS) + Math.max(0L, initialDelay));
      schedule(() -> CompletableFuture.runAsync(command, executor), initialDelay, unit, completion, nextDelay, next, currentScheduledFuture);
      completion.whenComplete((v, t) -> Optional.ofNullable((ScheduledFuture)currentScheduledFuture.get()).ifPresent((s) -> s.cancel(true)));
   }

   private static void schedule(Supplier runner, long delay, TimeUnit unit, CompletableFuture completion, LongSupplier nextDelay, AtomicLong next, AtomicReference currentScheduledFuture) {
      currentScheduledFuture.set(SHARED_SCHEDULER.schedule(() -> {
         if (!completion.isDone()) {
            CompletableFuture<?> runAsync = (CompletableFuture)runner.get();
            runAsync.whenComplete((v, t) -> {
               if (t != null) {
                  completion.completeExceptionally(t);
               } else if (!completion.isDone()) {
                  schedule(runner, next.addAndGet(nextDelay.getAsLong()) - unit.convert(System.nanoTime(), TimeUnit.NANOSECONDS), unit, completion, nextDelay, next, currentScheduledFuture);
               }

            });
         }
      }, delay, unit));
   }
}
