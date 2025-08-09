package org.json4s.reflect;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.json4s.scalap.scalasig.AliasSymbol;
import org.json4s.scalap.scalasig.ClassSymbol;
import org.json4s.scalap.scalasig.MethodSymbol;
import org.json4s.scalap.scalasig.NullaryMethodType;
import org.json4s.scalap.scalasig.ScalaSig;
import org.json4s.scalap.scalasig.Symbol;
import org.json4s.scalap.scalasig.SymbolInfoSymbol;
import org.json4s.scalap.scalasig.ThisType;
import org.json4s.scalap.scalasig.Type;
import org.json4s.scalap.scalasig.TypeBoundsType;
import org.json4s.scalap.scalasig.TypeRefType;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Vector;
import scala.package.;
import scala.runtime.BoxesRunTime;

public final class ScalaSigReader$ {
   public static final ScalaSigReader$ MODULE$ = new ScalaSigReader$();
   private static final Memo localPathMemo = new Memo();
   private static final Memo remotePathMemo = new Memo();
   private static final String ModuleFieldName = "MODULE$";
   private static final String OuterFieldName = "$outer";
   private static final Vector ClassLoaders;

   static {
      ClassLoaders = (Vector).MODULE$.Vector().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new ClassLoader[]{MODULE$.getClass().getClassLoader(), Thread.currentThread().getContextClassLoader()})));
   }

   public Class readConstructor(final String argName, final Class clazz, final int typeArgIndex, final List argNames) {
      ClassSymbol cl = this.findClass(clazz);
      MethodSymbol cstr = (MethodSymbol)this.findConstructor(cl, argNames).getOrElse(() -> package$.MODULE$.fail((new StringBuilder(27)).append("Can't find constructor for ").append(clazz).toString(), package$.MODULE$.fail$default$2()));
      return this.findArgType(cstr, argNames.indexOf(argName), typeArgIndex);
   }

   public Class readConstructor(final String argName, final Class clazz, final List typeArgIndexes, final List argNames) {
      ClassSymbol cl = this.findClass(clazz);
      MethodSymbol cstr = (MethodSymbol)this.findConstructor(cl, argNames).getOrElse(() -> package$.MODULE$.fail((new StringBuilder(27)).append("Can't find constructor for ").append(clazz).toString(), package$.MODULE$.fail$default$2()));
      return this.findArgType(cstr, argNames.indexOf(argName), typeArgIndexes);
   }

   public Class readConstructor(final String argName, final ScalaType clazz, final int typeArgIndex, final List argNames) {
      ClassSymbol cl = this.findClass(clazz.erasure());
      MethodSymbol cstr = (MethodSymbol)this.findConstructor(cl, argNames).orElse(() -> {
         ClassSymbol companionClass = MODULE$.findCompanionObject(clazz.erasure());
         return ((IterableOnceOps)companionClass.children().collect(new Serializable() {
            private static final long serialVersionUID = 0L;

            public final Object applyOrElse(final Symbol x1, final Function1 default) {
               Object var3;
               MethodSymbol var5;
               label27: {
                  if (x1 instanceof MethodSymbol) {
                     var5 = (MethodSymbol)x1;
                     String var10000 = var5.name();
                     String var6 = "apply";
                     if (var10000 == null) {
                        if (var6 == null) {
                           break label27;
                        }
                     } else if (var10000.equals(var6)) {
                        break label27;
                     }
                  }

                  var3 = default.apply(x1);
                  return var3;
               }

               var3 = var5;
               return var3;
            }

            public final boolean isDefinedAt(final Symbol x1) {
               boolean var2;
               label27: {
                  if (x1 instanceof MethodSymbol) {
                     MethodSymbol var4 = (MethodSymbol)x1;
                     String var10000 = var4.name();
                     String var5 = "apply";
                     if (var10000 == null) {
                        if (var5 == null) {
                           break label27;
                        }
                     } else if (var10000.equals(var5)) {
                        break label27;
                     }
                  }

                  var2 = false;
                  return var2;
               }

               var2 = true;
               return var2;
            }
         })).find((x$1) -> BoxesRunTime.boxToBoolean($anonfun$readConstructor$4(argNames, x$1)));
      }).getOrElse(() -> package$.MODULE$.fail((new StringBuilder(27)).append("Can't find constructor for ").append(clazz).toString(), package$.MODULE$.fail$default$2()));
      return this.findArgType(cstr, argNames.indexOf(argName), typeArgIndex);
   }

   public Class readConstructor(final String argName, final ScalaType clazz, final List typeArgIndexes, final List argNames) {
      ClassSymbol cl = this.findClass(clazz.erasure());
      Option cstr = this.findConstructor(cl, argNames);
      Option maybeArgType = cstr.map((x$2) -> MODULE$.findArgType((MethodSymbol)cstr.get(), argNames.indexOf(argName), typeArgIndexes)).orElse(() -> {
         ClassSymbol companionClass = MODULE$.findCompanionObject(clazz.erasure());
         return MODULE$.findApply(companionClass, argNames).map((methodSymbol) -> MODULE$.findArgType(methodSymbol, argNames.indexOf(argName), typeArgIndexes));
      });
      return (Class)maybeArgType.getOrElse(() -> package$.MODULE$.fail((new StringBuilder(27)).append("Can't find constructor for ").append(clazz).toString(), package$.MODULE$.fail$default$2()));
   }

   public Class readField(final String name, final Class clazz, final int typeArgIndex) {
      return this.findArgTypeForField(this.read$1(clazz, name, clazz), typeArgIndex);
   }

   public ClassSymbol findClass(final Class clazz) {
      ScalaSig sig = (ScalaSig)this.findScalaSig(clazz).getOrElse(() -> package$.MODULE$.fail((new StringBuilder(24)).append("Can't find ScalaSig for ").append(clazz).toString(), package$.MODULE$.fail$default$2()));
      return (ClassSymbol)this.findClass(sig, clazz).getOrElse(() -> package$.MODULE$.fail((new StringBuilder(32)).append("Can't find ").append(clazz).append(" from parsed ScalaSig").toString(), package$.MODULE$.fail$default$2()));
   }

   public Option findClass(final ScalaSig sig, final Class clazz) {
      String name = package$.MODULE$.safeSimpleName(clazz);
      return ((IterableOnceOps)sig.symbols().collect(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final Symbol x1, final Function1 default) {
            Object var3;
            if (x1 instanceof ClassSymbol) {
               ClassSymbol var5 = (ClassSymbol)x1;
               if (!var5.isModule()) {
                  var3 = var5;
                  return var3;
               }
            }

            var3 = default.apply(x1);
            return var3;
         }

         public final boolean isDefinedAt(final Symbol x1) {
            boolean var2;
            if (x1 instanceof ClassSymbol) {
               ClassSymbol var4 = (ClassSymbol)x1;
               if (!var4.isModule()) {
                  var2 = true;
                  return var2;
               }
            }

            var2 = false;
            return var2;
         }
      })).find((x$5) -> BoxesRunTime.boxToBoolean($anonfun$findClass$4(name, x$5))).orElse(() -> sig.topLevelClasses().find((x$6) -> BoxesRunTime.boxToBoolean($anonfun$findClass$6(name, x$6))).orElse(() -> (Option)sig.topLevelObjects().map((obj) -> {
               TypeRefType t = (TypeRefType)obj.infoType();
               return ((IterableOnceOps)t.symbol().children().collect(new Serializable() {
                  private static final long serialVersionUID = 0L;

                  public final Object applyOrElse(final Symbol x2, final Function1 default) {
                     Object var3;
                     if (x2 instanceof ClassSymbol) {
                        ClassSymbol var5 = (ClassSymbol)x2;
                        var3 = var5;
                     } else {
                        var3 = default.apply(x2);
                     }

                     return var3;
                  }

                  public final boolean isDefinedAt(final Symbol x2) {
                     boolean var2;
                     if (x2 instanceof ClassSymbol) {
                        var2 = true;
                     } else {
                        var2 = false;
                     }

                     return var2;
                  }
               })).find((x$7) -> BoxesRunTime.boxToBoolean($anonfun$findClass$9(name, x$7)));
            }).head()));
   }

   public ClassSymbol findCompanionObject(final Class clazz) {
      ScalaSig sig = (ScalaSig)this.findScalaSig(clazz).getOrElse(() -> package$.MODULE$.fail((new StringBuilder(24)).append("Can't find ScalaSig for ").append(clazz).toString(), package$.MODULE$.fail$default$2()));
      return (ClassSymbol)this.findCompanionObject(sig, clazz).getOrElse(() -> package$.MODULE$.fail((new StringBuilder(32)).append("Can't find ").append(clazz).append(" from parsed ScalaSig").toString(), package$.MODULE$.fail$default$2()));
   }

   public Option findCompanionObject(final ScalaSig sig, final Class clazz) {
      String name = package$.MODULE$.safeSimpleName(clazz);
      return ((IterableOnceOps)sig.symbols().collect(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final Symbol x1, final Function1 default) {
            Object var3;
            if (x1 instanceof ClassSymbol) {
               ClassSymbol var5 = (ClassSymbol)x1;
               if (var5.isModule()) {
                  var3 = var5;
                  return var3;
               }
            }

            var3 = default.apply(x1);
            return var3;
         }

         public final boolean isDefinedAt(final Symbol x1) {
            boolean var2;
            if (x1 instanceof ClassSymbol) {
               ClassSymbol var4 = (ClassSymbol)x1;
               if (var4.isModule()) {
                  var2 = true;
                  return var2;
               }
            }

            var2 = false;
            return var2;
         }
      })).find((x$8) -> BoxesRunTime.boxToBoolean($anonfun$findCompanionObject$4(name, x$8)));
   }

   public Option findConstructor(final ClassSymbol c, final List argNames) {
      Seq ms = (Seq)c.children().collect(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final Symbol x1, final Function1 default) {
            Object var3;
            MethodSymbol var5;
            label27: {
               if (x1 instanceof MethodSymbol) {
                  var5 = (MethodSymbol)x1;
                  String var10000 = var5.name();
                  String var6 = "<init>";
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label27;
                     }
                  } else if (var10000.equals(var6)) {
                     break label27;
                  }
               }

               var3 = default.apply(x1);
               return var3;
            }

            var3 = var5;
            return var3;
         }

         public final boolean isDefinedAt(final Symbol x1) {
            boolean var2;
            label27: {
               if (x1 instanceof MethodSymbol) {
                  MethodSymbol var4 = (MethodSymbol)x1;
                  String var10000 = var4.name();
                  String var5 = "<init>";
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label27;
                     }
                  } else if (var10000.equals(var5)) {
                     break label27;
                  }
               }

               var2 = false;
               return var2;
            }

            var2 = true;
            return var2;
         }
      });
      return ms.find((m) -> BoxesRunTime.boxToBoolean($anonfun$findConstructor$1(argNames, m)));
   }

   public Option findApply(final ClassSymbol c, final List argNames) {
      Seq ms = (Seq)c.children().collect(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final Symbol x1, final Function1 default) {
            Object var3;
            MethodSymbol var5;
            label27: {
               if (x1 instanceof MethodSymbol) {
                  var5 = (MethodSymbol)x1;
                  String var10000 = var5.name();
                  String var6 = "apply";
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label27;
                     }
                  } else if (var10000.equals(var6)) {
                     break label27;
                  }
               }

               var3 = default.apply(x1);
               return var3;
            }

            var3 = var5;
            return var3;
         }

         public final boolean isDefinedAt(final Symbol x1) {
            boolean var2;
            label27: {
               if (x1 instanceof MethodSymbol) {
                  MethodSymbol var4 = (MethodSymbol)x1;
                  String var10000 = var4.name();
                  String var5 = "apply";
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label27;
                     }
                  } else if (var10000.equals(var5)) {
                     break label27;
                  }
               }

               var2 = false;
               return var2;
            }

            var2 = true;
            return var2;
         }
      });
      return ms.find((m) -> BoxesRunTime.boxToBoolean($anonfun$findApply$1(argNames, m)));
   }

   public Seq findFields(final ClassSymbol c) {
      return (Seq)c.children().collect(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final Symbol x1, final Function1 default) {
            Object var3;
            if (x1 instanceof MethodSymbol) {
               MethodSymbol var5 = (MethodSymbol)x1;
               if (var5.infoType() instanceof NullaryMethodType && !var5.isSynthetic()) {
                  var3 = var5;
                  return var3;
               }
            }

            var3 = default.apply(x1);
            return var3;
         }

         public final boolean isDefinedAt(final Symbol x1) {
            boolean var2;
            if (x1 instanceof MethodSymbol) {
               MethodSymbol var4 = (MethodSymbol)x1;
               if (var4.infoType() instanceof NullaryMethodType && !var4.isSynthetic()) {
                  var2 = true;
                  return var2;
               }
            }

            var2 = false;
            return var2;
         }
      });
   }

   private Option findField(final Class clazz, final String name) {
      return this.findField(this.findClass(clazz), name);
   }

   private Option findField(final ClassSymbol c, final String name) {
      return c.children().collectFirst(new Serializable(name) {
         private static final long serialVersionUID = 0L;
         private final String name$4;

         public final Object applyOrElse(final Symbol x1, final Function1 default) {
            Object var3;
            MethodSymbol var5;
            label27: {
               if (x1 instanceof MethodSymbol) {
                  var5 = (MethodSymbol)x1;
                  String var10000 = var5.name();
                  String var6 = this.name$4;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label27;
                     }
                  } else if (var10000.equals(var6)) {
                     break label27;
                  }
               }

               var3 = default.apply(x1);
               return var3;
            }

            var3 = var5;
            return var3;
         }

         public final boolean isDefinedAt(final Symbol x1) {
            boolean var2;
            label27: {
               if (x1 instanceof MethodSymbol) {
                  MethodSymbol var4 = (MethodSymbol)x1;
                  String var10000 = var4.name();
                  String var5 = this.name$4;
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label27;
                     }
                  } else if (var10000.equals(var5)) {
                     break label27;
                  }
               }

               var2 = false;
               return var2;
            }

            var2 = true;
            return var2;
         }

         public {
            this.name$4 = name$4;
         }
      });
   }

   public Class findArgType(final MethodSymbol s, final int argIdx, final int typeArgIndex) {
      return (Class)this.findPrimitive$1(((SymbolInfoSymbol)s.children().apply(argIdx)).infoType(), typeArgIndex).map((sx) -> MODULE$.toClass(sx)).getOrElse(() -> Object.class);
   }

   public Class findArgType(final MethodSymbol s, final int argIdx, final List typeArgIndexes) {
      return this.toClass(this.findPrimitive$2(((SymbolInfoSymbol)s.children().apply(argIdx)).infoType(), 0, typeArgIndexes));
   }

   private Class findArgTypeForField(final MethodSymbol s, final int typeArgIdx) {
      Type t = this.getType$1(s, typeArgIdx);
      return this.toClass(findPrimitive$3(t));
   }

   private Class toClass(final Symbol s) {
      String var3 = s.path();
      Class var2;
      switch (var3 == null ? 0 : var3.hashCode()) {
         case -1882783961:
            if ("scala.Int".equals(var3)) {
               var2 = Integer.TYPE;
               return var2;
            }
            break;
         case -1176986732:
            if ("scala.Float".equals(var3)) {
               var2 = Float.TYPE;
               return var2;
            }
            break;
         case -1165099596:
            if ("scala.Short".equals(var3)) {
               var2 = Short.TYPE;
               return var2;
            }
            break;
         case -676694176:
            if ("scala.Boolean".equals(var3)) {
               var2 = Boolean.TYPE;
               return var2;
            }
            break;
         case 1763041488:
            if ("scala.Byte".equals(var3)) {
               var2 = Byte.TYPE;
               return var2;
            }
            break;
         case 1763329604:
            if ("scala.Long".equals(var3)) {
               var2 = Long.TYPE;
               return var2;
            }
            break;
         case 2113808793:
            if ("scala.Double".equals(var3)) {
               var2 = Double.TYPE;
               return var2;
            }
      }

      var2 = Object.class;
      return var2;
   }

   private boolean isPrimitive(final Symbol s) {
      boolean var3;
      label23: {
         Class var10000 = this.toClass(s);
         Class var2 = Object.class;
         if (var10000 == null) {
            if (var2 != null) {
               break label23;
            }
         } else if (!var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   public Option findScalaSig(final Class clazz) {
      Object var10000;
      try {
         var10000 = this.parseClassFileFromByteCode(clazz).orElse(() -> MODULE$.findScalaSig(clazz.getDeclaringClass()));
      } catch (NullPointerException var2) {
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   private Option parseClassFileFromByteCode(final Class clazz) {
      return scala.Option..MODULE$.apply(org.json4s.scalap.scalasig.ClassFileParser..MODULE$.parse(org.json4s.scalap.scalasig.ByteCode..MODULE$.forClass(clazz))).flatMap((classFile) -> org.json4s.scalap.scalasig.ScalaSigParser..MODULE$.parse(classFile));
   }

   public String ModuleFieldName() {
      return ModuleFieldName;
   }

   public String OuterFieldName() {
      return OuterFieldName;
   }

   public Vector ClassLoaders() {
      return ClassLoaders;
   }

   public Option companions(final String t, final Option companion, final Iterable classLoaders) {
      Option cc = this.resolveClass(path$1(t), classLoaders).flatMap((c) -> MODULE$.resolveClass(path$1(Reflector$.MODULE$.rawClassOf(c).getName()), classLoaders));
      return cc.map((ccc) -> new Tuple2(ccc, this.safeField$1(ccc, companion)));
   }

   public Option companions$default$2() {
      return scala.None..MODULE$;
   }

   public Iterable companions$default$3() {
      return this.ClassLoaders();
   }

   public Option resolveClass(final String c, final Iterable classLoaders) {
      return classLoaders == this.ClassLoaders() ? (Option)localPathMemo.apply(c, (cx) -> MODULE$.resolveClassCached(cx, classLoaders)) : (Option)remotePathMemo.apply(new Tuple2(c, classLoaders), (tuple) -> MODULE$.resolveClassCached((String)tuple._1(), (Iterable)tuple._2()));
   }

   public Iterable resolveClass$default$2() {
      return this.ClassLoaders();
   }

   private Option resolveClassCached(final String c, final Iterable classLoaders) {
      Object var10000;
      try {
         Class clazz = null;
         Iterator iter = classLoaders.iterator().$plus$plus(() -> .MODULE$.Iterator().single(Thread.currentThread().getContextClassLoader()));

         while(clazz == null && iter.hasNext()) {
            try {
               clazz = Class.forName(c, true, (ClassLoader)iter.next());
            } catch (ClassNotFoundException var5) {
            }
         }

         var10000 = clazz != null ? new Some(clazz) : scala.None..MODULE$;
      } catch (Throwable var6) {
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$readConstructor$4(final List argNames$1, final MethodSymbol x$1) {
      boolean var3;
      label23: {
         Object var10000 = x$1.children().collect(new Serializable() {
            private static final long serialVersionUID = 0L;

            public final Object applyOrElse(final Symbol x2, final Function1 default) {
               Object var3;
               if (x2 instanceof MethodSymbol) {
                  MethodSymbol var5 = (MethodSymbol)x2;
                  var3 = var5.name();
               } else {
                  var3 = default.apply(x2);
               }

               return var3;
            }

            public final boolean isDefinedAt(final Symbol x2) {
               boolean var2;
               if (x2 instanceof MethodSymbol) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               return var2;
            }
         });
         if (var10000 == null) {
            if (argNames$1 == null) {
               break label23;
            }
         } else if (var10000.equals(argNames$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$readField$2(final Class x$3) {
      boolean var10000;
      label23: {
         Class var1 = Serializable.class;
         if (x$3 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (x$3.equals(var1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   private final MethodSymbol read$1(final Class current, final String name$1, final Class clazz$5) {
      Class var4 = Object.class;
      if (current == null) {
         if (var4 == null) {
            throw package$.MODULE$.fail((new StringBuilder(23)).append("Can't find field ").append(name$1).append(" from ").append(clazz$5).toString(), package$.MODULE$.fail$default$2());
         }
      } else if (current.equals(var4)) {
         throw package$.MODULE$.fail((new StringBuilder(23)).append("Can't find field ").append(name$1).append(" from ").append(clazz$5).toString(), package$.MODULE$.fail$default$2());
      }

      return (MethodSymbol)this.findField(current, name$1).orElse(() -> scala.collection.ArrayOps..MODULE$.headOption$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filterNot$extension(scala.Predef..MODULE$.refArrayOps((Object[])current.getInterfaces()), (x$3) -> BoxesRunTime.boxToBoolean($anonfun$readField$2(x$3)))), (x$4) -> MODULE$.findField(x$4, name$1), scala.reflect.ClassTag..MODULE$.apply(MethodSymbol.class))))).getOrElse(() -> this.read$1(current.getSuperclass(), name$1, clazz$5));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findClass$4(final String name$2, final ClassSymbol x$5) {
      boolean var3;
      label23: {
         String var10000 = x$5.name();
         if (var10000 == null) {
            if (name$2 == null) {
               break label23;
            }
         } else if (var10000.equals(name$2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findClass$6(final String name$2, final ClassSymbol x$6) {
      boolean var3;
      label23: {
         String var10000 = x$6.symbolInfo().name();
         if (var10000 == null) {
            if (name$2 == null) {
               break label23;
            }
         } else if (var10000.equals(name$2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findClass$9(final String name$2, final ClassSymbol x$7) {
      boolean var3;
      label23: {
         String var10000 = x$7.symbolInfo().name();
         if (var10000 == null) {
            if (name$2 == null) {
               break label23;
            }
         } else if (var10000.equals(name$2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findCompanionObject$4(final String name$3, final ClassSymbol x$8) {
      boolean var3;
      label23: {
         String var10000 = x$8.name();
         if (var10000 == null) {
            if (name$3 == null) {
               break label23;
            }
         } else if (var10000.equals(name$3)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findConstructor$1(final List argNames$3, final MethodSymbol m) {
      boolean var3;
      label23: {
         Object var10000 = m.children().map((x$9) -> x$9.name());
         if (var10000 == null) {
            if (argNames$3 == null) {
               break label23;
            }
         } else if (var10000.equals(argNames$3)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findApply$1(final List argNames$4, final MethodSymbol m) {
      boolean var3;
      label23: {
         Object var10000 = m.children().map((x$10) -> x$10.name());
         if (var10000 == null) {
            if (argNames$4 == null) {
               break label23;
            }
         } else if (var10000.equals(argNames$4)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   private final Option findPrimitive$1(final Type t, final int typeArgIndex$1) {
      Object var4;
      while(true) {
         boolean var6 = false;
         TypeRefType var7 = null;
         if (t instanceof TypeRefType) {
            var6 = true;
            var7 = (TypeRefType)t;
            Type var9 = var7.prefix();
            Symbol symbol = var7.symbol();
            if (var9 instanceof ThisType && this.isPrimitive(symbol)) {
               var4 = new Some(symbol);
               break;
            }
         }

         if (var6) {
            Seq var11 = var7.typeArgs();
            if (var11 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var12 = (scala.collection.immutable..colon.colon)var11;
               Type var13 = (Type)var12.head();
               if (var13 instanceof TypeRefType) {
                  TypeRefType var14 = (TypeRefType)var13;
                  Type var15 = var14.prefix();
                  Symbol symbol = var14.symbol();
                  if (var15 instanceof ThisType) {
                     var4 = new Some(symbol);
                     break;
                  }
               }
            }
         }

         Symbol symbol;
         label74: {
            if (var6) {
               symbol = var7.symbol();
               Seq var18 = var7.typeArgs();
               Nil var10000 = .MODULE$.Nil();
               if (var10000 == null) {
                  if (var18 == null) {
                     break label74;
                  }
               } else if (var10000.equals(var18)) {
                  break label74;
               }
            }

            if (var6) {
               Seq args = var7.typeArgs();
               if (typeArgIndex$1 >= args.length()) {
                  t = (Type)args.apply(0);
                  continue;
               }
            }

            if (var6) {
               Seq args = var7.typeArgs();
               Type ta = (Type)args.apply(typeArgIndex$1);
               if (ta instanceof TypeRefType) {
                  TypeRefType var24 = (TypeRefType)ta;
                  t = var24;
                  continue;
               }

               throw package$.MODULE$.fail((new StringBuilder(21)).append("Unexpected type info ").append(ta).toString(), package$.MODULE$.fail$default$2());
            }

            if (!(t instanceof TypeBoundsType)) {
               throw package$.MODULE$.fail((new StringBuilder(21)).append("Unexpected type info ").append(t).toString(), package$.MODULE$.fail$default$2());
            }

            var4 = scala.None..MODULE$;
            break;
         }

         var4 = new Some(symbol);
         break;
      }

      return (Option)var4;
   }

   private final Symbol findPrimitive$2(final Type t, final int curr, final List typeArgIndexes$2) {
      Symbol var5;
      while(true) {
         int ii = scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(typeArgIndexes$2.length() - 1), curr);
         boolean var8 = false;
         TypeRefType var9 = null;
         if (t instanceof TypeRefType) {
            var8 = true;
            var9 = (TypeRefType)t;
            Type var11 = var9.prefix();
            Symbol symbol = var9.symbol();
            if (var11 instanceof ThisType && this.isPrimitive(symbol)) {
               var5 = symbol;
               break;
            }
         }

         Symbol symbol;
         label54: {
            if (var8) {
               symbol = var9.symbol();
               Seq var14 = var9.typeArgs();
               Nil var10000 = .MODULE$.Nil();
               if (var10000 == null) {
                  if (var14 == null) {
                     break label54;
                  }
               } else if (var10000.equals(var14)) {
                  break label54;
               }
            }

            if (var8) {
               Seq args = var9.typeArgs();
               if (BoxesRunTime.unboxToInt(typeArgIndexes$2.apply(ii)) >= args.length()) {
                  Type var21 = (Type)args.apply(scala.runtime.RichInt..MODULE$.max$extension(scala.Predef..MODULE$.intWrapper(0), args.length() - 1));
                  ++curr;
                  t = var21;
                  continue;
               }
            }

            if (var8) {
               Seq args = var9.typeArgs();
               Type ta = (Type)args.apply(BoxesRunTime.unboxToInt(typeArgIndexes$2.apply(ii)));
               if (ta instanceof TypeRefType) {
                  TypeRefType var20 = (TypeRefType)ta;
                  ++curr;
                  t = var20;
                  continue;
               }

               throw package$.MODULE$.fail((new StringBuilder(21)).append("Unexpected type info ").append(ta).toString(), package$.MODULE$.fail$default$2());
            }

            throw package$.MODULE$.fail((new StringBuilder(21)).append("Unexpected type info ").append(t).toString(), package$.MODULE$.fail$default$2());
         }

         var5 = symbol;
         break;
      }

      return var5;
   }

   private final Type getType$1(final SymbolInfoSymbol symbol, final int typeArgIdx$1) {
      while(true) {
         boolean var5 = false;
         NullaryMethodType var6 = null;
         boolean var7 = false;
         TypeRefType var8 = null;
         Type var9 = symbol.infoType();
         if (var9 instanceof NullaryMethodType) {
            var5 = true;
            var6 = (NullaryMethodType)var9;
            Type var10 = var6.resultType();
            if (var10 instanceof TypeRefType) {
               TypeRefType var11 = (TypeRefType)var10;
               Symbol alias = var11.symbol();
               if (alias instanceof AliasSymbol) {
                  AliasSymbol var13 = (AliasSymbol)alias;
                  symbol = var13;
                  continue;
               }
            }
         }

         Type var4;
         if (var5) {
            Type var14 = var6.resultType();
            if (var14 instanceof TypeRefType) {
               TypeRefType var15 = (TypeRefType)var14;
               Seq args = var15.typeArgs();
               var4 = (Type)args.apply(typeArgIdx$1);
               return var4;
            }
         }

         if (var9 instanceof TypeRefType) {
            var7 = true;
            var8 = (TypeRefType)var9;
            Symbol alias = var8.symbol();
            if (alias instanceof AliasSymbol) {
               AliasSymbol var18 = (AliasSymbol)alias;
               symbol = var18;
               continue;
            }
         }

         if (!var7) {
            throw new MatchError(var9);
         }

         Seq args = var8.typeArgs();
         var4 = (Type)args.apply(typeArgIdx$1);
         return var4;
      }
   }

   private static final Symbol findPrimitive$3(final Type t) {
      if (t instanceof TypeRefType) {
         TypeRefType var3 = (TypeRefType)t;
         Type var4 = var3.prefix();
         Symbol symbol = var3.symbol();
         if (var4 instanceof ThisType) {
            return symbol;
         }
      }

      throw package$.MODULE$.fail((new StringBuilder(21)).append("Unexpected type info ").append(t).toString(), package$.MODULE$.fail$default$2());
   }

   private static final String path$1(final String tt) {
      return tt.endsWith("$") ? tt : (new StringBuilder(1)).append(tt).append("$").toString();
   }

   private final Option safeField$1(final Class ccc, final Option companion$1) {
      Object var10000;
      try {
         var10000 = scala.Option..MODULE$.apply(ccc.getField(this.ModuleFieldName())).map((x$11) -> x$11.get(companion$1.orNull(scala..less.colon.less..MODULE$.refl())));
      } catch (Throwable var3) {
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   private ScalaSigReader$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
