package scala.reflect.runtime;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.URLClassLoader;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.collection.AbstractIterable;
import scala.collection.IterableOnceOps;
import scala.collection.mutable.ArraySeq;
import scala.reflect.internal.util.AbstractFileClassLoader;
import scala.reflect.io.AbstractFile;
import scala.reflect.io.NoAbstractFile$;
import scala.reflect.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;
import scala.runtime.StructuralCallSite;
import scala.util.PropertiesTrait;

public final class ReflectionUtils$ {
   public static final ReflectionUtils$ MODULE$ = new ReflectionUtils$();

   public static Method reflMethod$Method1(final Class x$1) {
      StructuralCallSite methodCache1 = apply<invokedynamic>();
      Method method1 = methodCache1.find(x$1);
      if (method1 != null) {
         return method1;
      } else {
         Method ensureAccessible_m = x$1.getMethod("root", methodCache1.parameterTypes());
         Method var10000 = (Method).MODULE$.ensureAccessible(ensureAccessible_m);
         Object var5 = null;
         method1 = var10000;
         methodCache1.add(x$1, method1);
         return method1;
      }
   }

   public Throwable unwrapThrowable(final Throwable x) {
      while((x instanceof InvocationTargetException ? true : (x instanceof ExceptionInInitializerError ? true : (x instanceof UndeclaredThrowableException ? true : (x instanceof ClassNotFoundException ? true : x instanceof NoClassDefFoundError)))) && x.getCause() != null) {
         x = x.getCause();
      }

      return x;
   }

   public PartialFunction unwrapHandler(final PartialFunction pf) {
      return pf.compose(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final Throwable x1, final Function1 default) {
            return ReflectionUtils$.MODULE$.unwrapThrowable(x1);
         }

         public final boolean isDefinedAt(final Throwable x1) {
            return true;
         }
      });
   }

   public String show(final ClassLoader cl) {
      return cl == null ? (new StringBuilder(45)).append("primordial classloader with boot classpath [").append(this.inferClasspath$1((ClassLoader)null)).append("]").toString() : (new StringBuilder(45)).append(cl).append(" of type ").append(cl.getClass()).append(" with classpath [").append(this.inferClasspath$1(cl)).append("] and parent being ").append(this.show(cl.getParent())).toString();
   }

   public Object staticSingletonInstance(final ClassLoader cl, final String className) {
      Class clazz = Class.forName(className.endsWith("$") ? className : (new StringBuilder(1)).append(className).append("$").toString(), true, cl);
      return this.staticSingletonInstance(clazz);
   }

   public Object staticSingletonInstance(final Class clazz) {
      return clazz.getField("MODULE$").get((Object)null);
   }

   public Object innerSingletonInstance(final Object outer, final String className) {
      String accessorName = className.endsWith("$") ? className.substring(0, className.length() - 1) : className;
      Option var10000 = singletonAccessor$1(outer.getClass(), accessorName);
      if (var10000 == null) {
         throw null;
      } else {
         Option getOrElse_this = var10000;
         if (getOrElse_this.isEmpty()) {
            throw $anonfun$innerSingletonInstance$3(outer, accessorName);
         } else {
            Object var7 = getOrElse_this.get();
            Object var6 = null;
            Method accessor = (Method)var7;
            accessor.setAccessible(true);
            return accessor.invoke(outer);
         }
      }
   }

   public AbstractFile associatedFile(final Class clazz) {
      return NoAbstractFile$.MODULE$;
   }

   private final boolean isAbstractFileClassLoader$1(final Class clazz) {
      while(clazz != null) {
         if (clazz.equals(AbstractFileClassLoader.class)) {
            return true;
         }

         clazz = clazz.getSuperclass();
      }

      return false;
   }

   // $FF: synthetic method
   public static final Option $anonfun$show$1(final String flavor) {
      return PropertiesTrait.propOrNone$(scala.util.Properties..MODULE$, (new StringBuilder(16)).append(flavor).append(".boot.class.path").toString());
   }

   // $FF: synthetic method
   public static final Option $anonfun$show$2(final Function1 loadBootCp$1) {
      return (Option)loadBootCp$1.apply("java");
   }

   // $FF: synthetic method
   public static final String $anonfun$show$3() {
      return "<unknown>";
   }

   private final String inferClasspath$1(final ClassLoader cl) {
      if (cl instanceof URLClassLoader) {
         URLClassLoader var2 = (URLClassLoader)cl;
         if (var2.getURLs() != null) {
            ArraySeq.ofRef var13 = scala.Predef..MODULE$.wrapRefArray(var2.getURLs());
            String mkString_sep = ",";
            if (var13 == null) {
               throw null;
            }

            AbstractIterable mkString_this = var13;
            String mkString_end = "";
            String mkString_start = "";
            return IterableOnceOps.mkString$(mkString_this, mkString_start, mkString_sep, mkString_end);
         }
      }

      if (cl != null && this.isAbstractFileClassLoader$1(cl.getClass())) {
         Object var12;
         try {
            var12 = reflMethod$Method1(cl.getClass()).invoke(cl);
         } catch (InvocationTargetException var9) {
            throw var9.getCause();
         }

         return ((AbstractFile)var12).canonicalPath();
      } else if (cl == null) {
         Option var10000 = $anonfun$show$1("sun");
         if (var10000 == null) {
            throw null;
         } else {
            Option orElse_this = var10000;
            var10000 = orElse_this.isEmpty() ? $anonfun$show$1("java") : orElse_this;
            Object var10 = null;
            if (var10000 == null) {
               throw null;
            } else {
               Option getOrElse_this = var10000;
               return (String)(getOrElse_this.isEmpty() ? "<unknown>" : getOrElse_this.get());
            }
         }
      } else {
         return "<unknown>";
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$innerSingletonInstance$1(final String accessorName$1, final Method x$1) {
      String var10000 = x$1.getName();
      if (var10000 == null) {
         if (accessorName$1 == null) {
            return true;
         }
      } else if (var10000.equals(accessorName$1)) {
         return true;
      }

      return false;
   }

   // $FF: synthetic method
   public static final Option $anonfun$innerSingletonInstance$2(final Class clazz$1, final String accessorName$1) {
      return singletonAccessor$1(clazz$1.getSuperclass(), accessorName$1);
   }

   private static final Option singletonAccessor$1(final Class clazz, final String accessorName$1) {
      if (clazz == null) {
         return scala.None..MODULE$;
      } else {
         Object[] refArrayOps_xs = clazz.getDeclaredMethods();
         Object var8 = null;
         Object find$extension_$this = refArrayOps_xs;
         int find$extension_indexWhere$extension_i = 0;

         int var10;
         while(true) {
            if (find$extension_indexWhere$extension_i >= ((Object[])find$extension_$this).length) {
               var10 = -1;
               break;
            }

            Object var7 = ((Object[])find$extension_$this)[find$extension_indexWhere$extension_i];
            if ($anonfun$innerSingletonInstance$1(accessorName$1, (Method)var7)) {
               var10 = find$extension_indexWhere$extension_i;
               break;
            }

            ++find$extension_indexWhere$extension_i;
         }

         int find$extension_idx = var10;
         Object var11 = find$extension_idx == -1 ? scala.None..MODULE$ : new Some(((Object[])find$extension_$this)[find$extension_idx]);
         find$extension_$this = null;
         Option declaredAccessor = (Option)var11;
         return declaredAccessor.isEmpty() ? $anonfun$innerSingletonInstance$2(clazz, accessorName$1) : declaredAccessor;
      }
   }

   // $FF: synthetic method
   public static final Nothing $anonfun$innerSingletonInstance$3(final Object outer$1, final String accessorName$1) {
      throw new NoSuchMethodException((new StringBuilder(1)).append(outer$1.getClass().getName()).append(".").append(accessorName$1).toString());
   }

   private ReflectionUtils$() {
   }

   // $FF: synthetic method
   public static final Object $anonfun$innerSingletonInstance$1$adapted(final String accessorName$1, final Method x$1) {
      return BoxesRunTime.boxToBoolean($anonfun$innerSingletonInstance$1(accessorName$1, x$1));
   }
}
