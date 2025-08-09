package org.apache.spark;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.invoke.SerializedLambda;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.EnumSet;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.tools.JavaFileObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender.Target;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.util.SparkTestUtils;
import org.apache.spark.util.Utils$;
import org.json4s.JValue;
import org.sparkproject.guava.io.ByteStreams;
import org.sparkproject.guava.io.FileWriteMode;
import org.sparkproject.jetty.server.Handler;
import org.sparkproject.jetty.server.Server;
import org.sparkproject.jetty.server.handler.DefaultHandler;
import org.sparkproject.jetty.server.handler.HandlerList;
import org.sparkproject.jetty.server.handler.ResourceHandler;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.StringOps.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;

public final class TestUtils$ implements SparkTestUtils {
   public static final TestUtils$ MODULE$ = new TestUtils$();
   private static final String minimumPythonSupportedVersion;
   private static JavaFileObject.Kind org$apache$spark$util$SparkTestUtils$$SOURCE;

   static {
      SparkTestUtils.$init$(MODULE$);
      minimumPythonSupportedVersion = "3.7.0";
   }

   public File createCompiledClass(final String className, final File destDir, final SparkTestUtils.JavaSourceFromString sourceFile, final Seq classpathUrls) {
      return SparkTestUtils.createCompiledClass$(this, className, destDir, sourceFile, classpathUrls);
   }

   public File createCompiledClass(final String className, final File destDir, final String toStringValue, final String baseClass, final Seq classpathUrls, final Seq implementsClasses, final String extraCodeBody, final Option packageName) {
      return SparkTestUtils.createCompiledClass$(this, className, destDir, toStringValue, baseClass, classpathUrls, implementsClasses, extraCodeBody, packageName);
   }

   public String createCompiledClass$default$3() {
      return SparkTestUtils.createCompiledClass$default$3$(this);
   }

   public String createCompiledClass$default$4() {
      return SparkTestUtils.createCompiledClass$default$4$(this);
   }

   public Seq createCompiledClass$default$5() {
      return SparkTestUtils.createCompiledClass$default$5$(this);
   }

   public Seq createCompiledClass$default$6() {
      return SparkTestUtils.createCompiledClass$default$6$(this);
   }

   public String createCompiledClass$default$7() {
      return SparkTestUtils.createCompiledClass$default$7$(this);
   }

   public Option createCompiledClass$default$8() {
      return SparkTestUtils.createCompiledClass$default$8$(this);
   }

   public JavaFileObject.Kind org$apache$spark$util$SparkTestUtils$$SOURCE() {
      return org$apache$spark$util$SparkTestUtils$$SOURCE;
   }

   public final void org$apache$spark$util$SparkTestUtils$_setter_$org$apache$spark$util$SparkTestUtils$$SOURCE_$eq(final JavaFileObject.Kind x$1) {
      org$apache$spark$util$SparkTestUtils$$SOURCE = x$1;
   }

   public URL createJarWithClasses(final Seq classNames, final String toStringValue, final Seq classNamesWithBase, final Seq classpathUrls) {
      File tempDir = Utils$.MODULE$.createTempDir();
      Seq files1 = (Seq)classNames.map((name) -> {
         String x$5 = MODULE$.createCompiledClass$default$4();
         Seq x$6 = MODULE$.createCompiledClass$default$6();
         String x$7 = MODULE$.createCompiledClass$default$7();
         Option x$8 = MODULE$.createCompiledClass$default$8();
         return MODULE$.createCompiledClass(name, tempDir, toStringValue, x$5, classpathUrls, x$6, x$7, x$8);
      });
      Seq files2 = (Seq)classNamesWithBase.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$createJarWithClasses$2(check$ifrefutable$1))).map((x$1) -> {
         if (x$1 != null) {
            String childName = (String)x$1._1();
            String baseName = (String)x$1._2();
            return MODULE$.createCompiledClass(childName, tempDir, toStringValue, baseName, classpathUrls, MODULE$.createCompiledClass$default$6(), MODULE$.createCompiledClass$default$7(), MODULE$.createCompiledClass$default$8());
         } else {
            throw new MatchError(x$1);
         }
      });
      File jarFile = new File(tempDir, .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("testJar-%s.jar"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToLong(System.currentTimeMillis())})));
      return this.createJar((Seq)files1.$plus$plus(files2), jarFile, this.createJar$default$3(), this.createJar$default$4());
   }

   public URL createJarWithFiles(final Map files, final File dir) {
      File tempDir = (File)scala.Option..MODULE$.apply(dir).getOrElse(() -> Utils$.MODULE$.createTempDir());
      File jarFile = File.createTempFile("testJar", ".jar", tempDir);
      JarOutputStream jarStream = new JarOutputStream(new FileOutputStream(jarFile));
      files.foreach((x0$1) -> BoxesRunTime.boxToLong($anonfun$createJarWithFiles$2(jarStream, x0$1)));
      jarStream.close();
      return jarFile.toURI().toURL();
   }

   public URL createJar(final Seq files, final File jarFile, final Option directoryPrefix, final Option mainClass) {
      Manifest var10000;
      if (mainClass instanceof Some var8) {
         String mc = (String)var8.value();
         Manifest m = new Manifest();
         m.getMainAttributes().putValue("Manifest-Version", "1.0");
         m.getMainAttributes().putValue("Main-Class", mc);
         var10000 = m;
      } else {
         if (!scala.None..MODULE$.equals(mainClass)) {
            throw new MatchError(mainClass);
         }

         var10000 = new Manifest();
      }

      Manifest manifest = var10000;
      FileOutputStream jarFileStream = new FileOutputStream(jarFile);
      JarOutputStream jarStream = new JarOutputStream(jarFileStream, manifest);
      files.foreach((file) -> {
         $anonfun$createJar$1(directoryPrefix, jarStream, file);
         return BoxedUnit.UNIT;
      });
      jarStream.close();
      jarFileStream.close();
      return jarFile.toURI().toURL();
   }

   public String createJarWithClasses$default$2() {
      return "";
   }

   public Seq createJarWithClasses$default$3() {
      return (Seq)scala.package..MODULE$.Seq().empty();
   }

   public Seq createJarWithClasses$default$4() {
      return (Seq)scala.package..MODULE$.Seq().empty();
   }

   public File createJarWithFiles$default$2() {
      return null;
   }

   public Option createJar$default$3() {
      return scala.None..MODULE$;
   }

   public Option createJar$default$4() {
      return scala.None..MODULE$;
   }

   public void assertSpilled(final SparkContext sc, final String identifier, final Function0 body) {
      SpillListener listener = new SpillListener();
      this.withListener(sc, listener, (x$2) -> {
         $anonfun$assertSpilled$1(body, x$2);
         return BoxedUnit.UNIT;
      });
      scala.Predef..MODULE$.assert(listener.numSpilledStages() > 0, () -> "expected " + identifier + " to spill, but did not");
   }

   public void assertNotSpilled(final SparkContext sc, final String identifier, final Function0 body) {
      SpillListener listener = new SpillListener();
      this.withListener(sc, listener, (x$3) -> {
         $anonfun$assertNotSpilled$1(body, x$3);
         return BoxedUnit.UNIT;
      });
      scala.Predef..MODULE$.assert(listener.numSpilledStages() == 0, () -> "expected " + identifier + " to not spill, but did");
   }

   public void assertExceptionMsg(final Throwable exception, final String msg, final boolean ignoreCase, final ClassTag evidence$1) {
      Tuple2 var16;
      label35: {
         label34: {
            ClassTag var10000 = scala.reflect.package..MODULE$.classTag(evidence$1);
            ClassTag var8 = scala.reflect.package..MODULE$.classTag(scala.reflect.ClassTag..MODULE$.Nothing());
            if (var10000 == null) {
               if (var8 == null) {
                  break label34;
               }
            } else if (var10000.equals(var8)) {
               break label34;
            }

            Class clazz = scala.reflect.package..MODULE$.classTag(evidence$1).runtimeClass();
            var16 = new Tuple2("of type " + clazz.getName() + " ", (Function1)(ex) -> BoxesRunTime.boxToBoolean($anonfun$assertExceptionMsg$2(clazz, ex)));
            break label35;
         }

         var16 = new Tuple2("", (Function1)(x$4) -> BoxesRunTime.boxToBoolean($anonfun$assertExceptionMsg$1(x$4)));
      }

      Tuple2 var7 = var16;
      if (var7 == null) {
         throw new MatchError(var7);
      } else {
         String typeMsg = (String)var7._1();
         Function1 typeCheck = (Function1)var7._2();
         Tuple2 var6 = new Tuple2(typeMsg, typeCheck);
         String typeMsg = (String)var6._1();
         Function1 typeCheck = (Function1)var6._2();
         ObjectRef e = ObjectRef.create(exception);

         boolean contains;
         for(contains = contain$1((Throwable)e.elem, msg, ignoreCase, typeCheck); ((Throwable)e.elem).getCause() != null && !contains; contains = contain$1((Throwable)e.elem, msg, ignoreCase, typeCheck)) {
            e.elem = ((Throwable)e.elem).getCause();
         }

         scala.Predef..MODULE$.assert(contains, () -> "Exception tree doesn't contain the expected exception " + typeMsg + "with message: " + msg + "\n" + Utils$.MODULE$.exceptionString((Throwable)e.elem));
      }
   }

   public boolean assertExceptionMsg$default$3() {
      return false;
   }

   public boolean testCommandAvailable(final String command) {
      return Utils$.MODULE$.checkCommandAvailable(command);
   }

   public String minimumPythonSupportedVersion() {
      return minimumPythonSupportedVersion;
   }

   public boolean isPythonVersionAvailable() {
      int[] version = (int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.split$extension(scala.Predef..MODULE$.augmentString(this.minimumPythonSupportedVersion()), '.')), (x$6) -> BoxesRunTime.boxToInteger($anonfun$isPythonVersionAvailable$1(x$6)), scala.reflect.ClassTag..MODULE$.Int());
      scala.Predef..MODULE$.assert(version.length == 3);
      return this.isPythonVersionAtLeast(version[0], version[1], version[2]);
   }

   private boolean isPythonVersionAtLeast(final int major, final int minor, final int reversion) {
      Seq cmdSeq = Utils$.MODULE$.isWindows() ? new scala.collection.immutable..colon.colon("cmd.exe", new scala.collection.immutable..colon.colon("/C", scala.collection.immutable.Nil..MODULE$)) : new scala.collection.immutable..colon.colon("sh", new scala.collection.immutable..colon.colon("-c", scala.collection.immutable.Nil..MODULE$));
      String pythonSnippet = "import sys; sys.exit(sys.version_info < (" + major + ", " + minor + ", " + reversion + "))";
      return BoxesRunTime.unboxToBoolean(scala.util.Try..MODULE$.apply((JFunction0.mcZ.sp)() -> scala.sys.process.Process..MODULE$.apply((scala.collection.Seq)cmdSeq.$colon$plus("python3 -c '" + pythonSnippet + "'")).$bang() == 0).getOrElse((JFunction0.mcZ.sp)() -> false));
   }

   public Option getAbsolutePathFromExecutable(final String executable) {
      String command = Utils$.MODULE$.isWindows() ? executable + ".exe" : executable;
      return (Option)(command.split(File.separator, 2).length == 1 && Files.isRegularFile(Paths.get(command), new LinkOption[0]) && Files.isExecutable(Paths.get(command)) ? new Some(Paths.get(command).toAbsolutePath().toString()) : scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])((String)scala.sys.package..MODULE$.env().apply("PATH")).split(Pattern.quote(File.pathSeparator))), (path) -> Paths.get(StringUtils.strip(path, "\"") + File.separator + command), scala.reflect.ClassTag..MODULE$.apply(Path.class))), (p) -> BoxesRunTime.boxToBoolean($anonfun$getAbsolutePathFromExecutable$2(p))).map((x$7) -> x$7.toString()));
   }

   public int httpResponseCode(final URL url, final String method, final Seq headers) {
      return BoxesRunTime.unboxToInt(this.withHttpConnection(url, method, headers, (connection) -> BoxesRunTime.boxToInteger($anonfun$httpResponseCode$1(connection))));
   }

   public String httpResponseCode$default$2() {
      return "GET";
   }

   public Seq httpResponseCode$default$3() {
      return scala.collection.immutable.Nil..MODULE$;
   }

   public String redirectUrl(final URL url, final String method, final Seq headers) {
      return (String)this.withHttpConnection(url, method, headers, (connection) -> connection.getHeaderField("Location"));
   }

   public String redirectUrl$default$2() {
      return "GET";
   }

   public Seq redirectUrl$default$3() {
      return scala.collection.immutable.Nil..MODULE$;
   }

   public String httpResponseMessage(final URL url, final String method, final Seq headers) {
      return (String)this.withHttpConnection(url, method, headers, (connection) -> scala.io.Source..MODULE$.fromInputStream(connection.getInputStream(), "utf-8").getLines().mkString("\n"));
   }

   public String httpResponseMessage$default$2() {
      return "GET";
   }

   public Seq httpResponseMessage$default$3() {
      return scala.collection.immutable.Nil..MODULE$;
   }

   public Object withHttpConnection(final URL url, final String method, final Seq headers, final Function1 fn) {
      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod(method);
      headers.foreach((x0$1) -> {
         $anonfun$withHttpConnection$1(connection, x0$1);
         return BoxedUnit.UNIT;
      });
      if (connection instanceof HttpsURLConnection var8) {
         SSLContext sslCtx = SSLContext.getInstance("SSL");
         X509TrustManager trustManager = new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
               return null;
            }

            public void checkClientTrusted(final X509Certificate[] x509Certificates, final String s) {
            }

            public void checkServerTrusted(final X509Certificate[] x509Certificates, final String s) {
            }
         };
         HostnameVerifier verifier = new HostnameVerifier() {
            public boolean verify(final String hostname, final SSLSession session) {
               return true;
            }
         };
         sslCtx.init((KeyManager[])null, (TrustManager[])((Object[])(new TrustManager[]{trustManager})), new SecureRandom());
         var8.setSSLSocketFactory(sslCtx.getSocketFactory());
         var8.setHostnameVerifier(verifier);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var15 = BoxedUnit.UNIT;
      }

      Object var16;
      try {
         connection.connect();
         var16 = fn.apply(connection);
      } finally {
         connection.disconnect();
      }

      return var16;
   }

   public String withHttpConnection$default$2() {
      return "GET";
   }

   public Seq withHttpConnection$default$3() {
      return scala.collection.immutable.Nil..MODULE$;
   }

   public void withListener(final SparkContext sc, final SparkListener listener, final Function1 body) {
      sc.addSparkListener(listener);

      try {
         body.apply(listener);
      } finally {
         sc.listenerBus().waitUntilEmpty();
         sc.listenerBus().removeListener(listener);
      }

   }

   public void withHttpServer(final String resBaseDir, final Function1 body) {
      Server server = new Server(new InetSocketAddress(Utils$.MODULE$.localCanonicalHostName(), 0));
      ResourceHandler resHandler = new ResourceHandler();
      resHandler.setResourceBase(resBaseDir);
      HandlerList handlers = new HandlerList();
      handlers.setHandlers((Handler[])(new Handler[]{resHandler, new DefaultHandler()}));
      server.setHandler(handlers);
      server.start();

      try {
         body.apply(server.getURI().toURL());
      } finally {
         server.stop();
      }

   }

   public String withHttpServer$default$1() {
      return ".";
   }

   public void waitUntilExecutorsUp(final SparkContext sc, final int numExecutors, final long timeout) {
      long finishTime = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(timeout);

      while(System.nanoTime() < finishTime) {
         if (sc.statusTracker().getExecutorInfos().length > numExecutors) {
            return;
         }

         Thread.sleep(10L);
      }

      throw new TimeoutException("Can't find " + numExecutors + " executors before " + timeout + " milliseconds elapsed");
   }

   public void configTestLog4j2(final String level) {
      ConfigurationBuilder builder = ConfigurationBuilderFactory.newConfigurationBuilder();
      AppenderComponentBuilder appenderBuilder = (AppenderComponentBuilder)builder.newAppender("console", "CONSOLE").addAttribute("target", Target.SYSTEM_ERR);
      appenderBuilder.add((LayoutComponentBuilder)builder.newLayout("PatternLayout").addAttribute("pattern", "%d{yy/MM/dd HH:mm:ss} %p %c{1}: %m%n%ex"));
      builder.add(appenderBuilder);
      builder.add((RootLoggerComponentBuilder)builder.newRootLogger(level).add(builder.newAppenderRef("console")));
      BuiltConfiguration configuration = (BuiltConfiguration)builder.build();
      ((LoggerContext)LogManager.getContext(false)).reconfigure(configuration);
   }

   public File[] recursiveList(final File f) {
      scala.Predef..MODULE$.require(f.isDirectory());
      File[] current = f.listFiles();
      return (File[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])current), scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])current), (x$8) -> BoxesRunTime.boxToBoolean($anonfun$recursiveList$1(x$8)))), (fx) -> MODULE$.recursiveList(fx), (xs) -> scala.Predef..MODULE$.wrapRefArray((Object[])xs), scala.reflect.ClassTag..MODULE$.apply(File.class)), scala.reflect.ClassTag..MODULE$.apply(File.class));
   }

   public String[] listDirectory(final File path) {
      ArrayBuffer result = scala.collection.mutable.ArrayBuffer..MODULE$.empty();
      if (path.isDirectory()) {
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])path.listFiles()), (f) -> (ArrayBuffer)result.appendAll(scala.Predef..MODULE$.wrapRefArray((Object[])MODULE$.listDirectory(f))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         char c = path.getName().charAt(0);
         if (c != '.' && c != '_') {
            result.append(path.getAbsolutePath());
         } else {
            BoxedUnit var4 = BoxedUnit.UNIT;
         }
      }

      return (String[])result.toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public String createTempJsonFile(final File dir, final String prefix, final JValue jsonValue) {
      File file = File.createTempFile(prefix, ".json", dir);
      Files.write(file.toPath(), org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(jsonValue, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3())).getBytes(), new OpenOption[0]);
      return file.getPath();
   }

   public String createTempScriptWithExpectedOutput(final File dir, final String prefix, final String output) {
      File file = File.createTempFile(prefix, ".sh", dir);
      String script = "cat <<EOF\n" + output + "\nEOF\n";
      org.sparkproject.guava.io.Files.asCharSink(file, StandardCharsets.UTF_8, new FileWriteMode[0]).write(script);
      Files.setPosixFilePermissions(file.toPath(), EnumSet.of(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_EXECUTE, PosixFilePermission.OWNER_WRITE));
      return file.getPath();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createJarWithClasses$2(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final long $anonfun$createJarWithFiles$2(final JarOutputStream jarStream$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         String v = (String)x0$1._2();
         JarEntry entry = new JarEntry(k);
         jarStream$1.putNextEntry(entry);
         return ByteStreams.copy(new ByteArrayInputStream(v.getBytes(StandardCharsets.UTF_8)), jarStream$1);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$createJar$1(final Option directoryPrefix$1, final JarOutputStream jarStream$2, final File file) {
      String prefix = (String)directoryPrefix$1.map((d) -> d + "/").getOrElse(() -> "");
      JarEntry jarEntry = new JarEntry(prefix + file.getName());
      jarStream$2.putNextEntry(jarEntry);
      FileInputStream in = new FileInputStream(file);
      ByteStreams.copy(in, jarStream$2);
      in.close();
   }

   // $FF: synthetic method
   public static final void $anonfun$assertSpilled$1(final Function0 body$1, final SpillListener x$2) {
      body$1.apply$mcV$sp();
   }

   // $FF: synthetic method
   public static final void $anonfun$assertNotSpilled$1(final Function0 body$2, final SpillListener x$3) {
      body$2.apply$mcV$sp();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$assertExceptionMsg$1(final Throwable x$4) {
      return true;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$assertExceptionMsg$2(final Class clazz$1, final Throwable e) {
      return clazz$1.isAssignableFrom(e.getClass());
   }

   private static final boolean contain$1(final Throwable e, final String msg, final boolean ignoreCase$1, final Function1 typeCheck$1) {
      if (ignoreCase$1) {
         return e.getMessage().toLowerCase(Locale.ROOT).contains(msg.toLowerCase(Locale.ROOT));
      } else {
         return e.getMessage().contains(msg) && BoxesRunTime.unboxToBoolean(typeCheck$1.apply(e));
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$isPythonVersionAvailable$1(final String x$6) {
      return .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$6));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getAbsolutePathFromExecutable$2(final Path p) {
      return Files.isRegularFile(p, new LinkOption[0]) && Files.isExecutable(p);
   }

   // $FF: synthetic method
   public static final int $anonfun$httpResponseCode$1(final HttpURLConnection connection) {
      return connection.getResponseCode();
   }

   // $FF: synthetic method
   public static final void $anonfun$withHttpConnection$1(final HttpURLConnection connection$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         String v = (String)x0$1._2();
         connection$1.setRequestProperty(k, v);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$recursiveList$1(final File x$8) {
      return x$8.isDirectory();
   }

   private TestUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
