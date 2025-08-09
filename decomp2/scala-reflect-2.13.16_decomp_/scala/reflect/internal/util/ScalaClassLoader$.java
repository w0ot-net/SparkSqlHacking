package scala.reflect.internal.util;

import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.util.PropertiesTrait;
import scala.util.Properties.;

public final class ScalaClassLoader$ {
   public static final ScalaClassLoader$ MODULE$ = new ScalaClassLoader$();
   private static final ClassLoader bootClassLoader;

   static {
      bootClassLoader = !PropertiesTrait.isJavaAtLeast$(.MODULE$, "9") ? null : liftedTree1$1();
   }

   public ScalaClassLoader apply(final ClassLoader cl) {
      if (cl instanceof ScalaClassLoader) {
         return (ScalaClassLoader)cl;
      } else if (cl instanceof URLClassLoader) {
         URLClassLoader var2 = (URLClassLoader)cl;
         return new ScalaClassLoader.URLClassLoader(scala.collection.ArrayOps..MODULE$.toIndexedSeq$extension(var2.getURLs()), var2.getParent());
      } else {
         return new ScalaClassLoader(cl) {
            public Object asContext(final Function0 action) {
               return ScalaClassLoader.asContext$(this, action);
            }

            public Option tryToLoadClass(final String path) {
               return ScalaClassLoader.tryToLoadClass$(this, path);
            }

            public Option tryToInitializeClass(final String path) {
               return ScalaClassLoader.tryToInitializeClass$(this, path);
            }

            public Object create(final String path) {
               return ScalaClassLoader.create$(this, path);
            }

            public Object create(final String path, final Function1 errorFn, final Seq args, final ClassTag evidence$2) {
               return ScalaClassLoader.create$(this, path, errorFn, args, evidence$2);
            }

            public byte[] classBytes(final String className) {
               return ScalaClassLoader.classBytes$(this, className);
            }

            public InputStream classAsStream(final String className) {
               return ScalaClassLoader.classAsStream$(this, className);
            }

            public void run(final String objectName, final Seq arguments) {
               ScalaClassLoader.run$(this, objectName, arguments);
            }
         };
      }
   }

   public ScalaClassLoader contextLoader() {
      return this.apply(Thread.currentThread().getContextClassLoader());
   }

   public ScalaClassLoader appLoader() {
      return this.apply(ClassLoader.getSystemClassLoader());
   }

   public void setContext(final ClassLoader cl) {
      Thread.currentThread().setContextClassLoader(cl);
   }

   public ScalaClassLoader.URLClassLoader fromURLs(final Seq urls, final ClassLoader parent) {
      return new ScalaClassLoader.URLClassLoader(urls, parent == null ? bootClassLoader : parent);
   }

   public ClassLoader fromURLs$default$2() {
      return null;
   }

   public URLClassLoader fromURLsParallelCapable(final Seq urls, final ClassLoader parent) {
      return new URLClassLoader((URL[])urls.toArray(scala.reflect.ClassTag..MODULE$.apply(URL.class)), parent == null ? bootClassLoader : parent);
   }

   public ClassLoader fromURLsParallelCapable$default$2() {
      return null;
   }

   public boolean classExists(final Seq urls, final String name) {
      return this.fromURLs(urls, (ClassLoader)null).tryToLoadClass(name).isDefined();
   }

   public Option originOfClass(final Class x) {
      Option var10000 = scala.Option..MODULE$.apply(x.getProtectionDomain().getCodeSource());
      if (var10000 == null) {
         throw null;
      } else {
         Option flatMap_this = var10000;
         return (Option)(flatMap_this.isEmpty() ? scala.None..MODULE$ : $anonfun$originOfClass$1((CodeSource)flatMap_this.get()));
      }
   }

   // $FF: synthetic method
   public static final Option $anonfun$originOfClass$1(final CodeSource x) {
      return scala.Option..MODULE$.apply(x.getLocation());
   }

   // $FF: synthetic method
   private static final ClassLoader liftedTree1$1() {
      try {
         return MethodHandles.lookup().findStatic(ClassLoader.class, "getPlatformClassLoader", MethodType.methodType(ClassLoader.class)).invoke();
      } catch (Throwable var0) {
         return null;
      }
   }

   private ScalaClassLoader$() {
   }
}
