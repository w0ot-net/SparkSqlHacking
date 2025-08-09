package scala.reflect.internal.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractIterable;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.ArraySeq;
import scala.reflect.ClassTag;
import scala.reflect.io.Streamable;
import scala.reflect.io.Streamable$;
import scala.reflect.runtime.ReflectionUtils$;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;
import scala.util.control.Exception.;

public final class RichClassLoader$ {
   public static final RichClassLoader$ MODULE$ = new RichClassLoader$();

   public ClassLoader wrapClassLoader(final ClassLoader loader) {
      return loader;
   }

   public final Object asContext$extension(final ClassLoader $this, final Function0 action) {
      ClassLoader saved = Thread.currentThread().getContextClassLoader();

      Object var10000;
      try {
         ScalaClassLoader$.MODULE$.setContext($this);
         var10000 = action.apply();
      } finally {
         ScalaClassLoader$.MODULE$.setContext(saved);
      }

      return var10000;
   }

   public final Option tryToLoadClass$extension(final ClassLoader $this, final String path) {
      return this.tryClass$extension($this, path, false);
   }

   public final Option tryToInitializeClass$extension(final ClassLoader $this, final String path) {
      return this.tryClass$extension($this, path, true);
   }

   public final Option tryClass$extension(final ClassLoader $this, final String path, final boolean initialize) {
      return .MODULE$.catching(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Class[]{ClassFormatError.class, ClassNotFoundException.class, SecurityException.class})).opt(() -> Class.forName(path, initialize, $this));
   }

   public final Object create$extension(final ClassLoader $this, final String path) {
      Option var10000 = this.tryClass$extension($this, path, true);
      if (var10000 == null) {
         throw null;
      } else {
         Option map_this = var10000;
         Object var7 = map_this.isEmpty() ? scala.None..MODULE$ : new Some($anonfun$create$1((Class)map_this.get()));
         Object var6 = null;
         scala..eq.colon.eq orNull_ev = scala..less.colon.less..MODULE$.refl();
         Option orNull_this = (Option)var7;
         return orNull_this.isEmpty() ? ((scala..less.colon.less)orNull_ev).apply((Object)null) : orNull_this.get();
      }
   }

   public final Object create$extension(final ClassLoader $this, final String path, final Function1 errorFn, final Seq args, final ClassTag evidence$1) {
      try {
         Class clazz = Class.forName(path, true, $this);
         if (!scala.reflect.package..MODULE$.classTag(evidence$1).runtimeClass().isAssignableFrom(clazz)) {
            errorFn.apply(scala.collection.StringOps..MODULE$.stripMargin$extension((new StringBuilder(54)).append("Loader for ").append(scala.reflect.package..MODULE$.classTag(evidence$1)).append(":   [").append(ReflectionUtils$.MODULE$.show(scala.reflect.package..MODULE$.classTag(evidence$1).runtimeClass().getClassLoader())).append("]\n                    |Loader for ").append(clazz.getName()).append(": [").append(ReflectionUtils$.MODULE$.show(clazz.getClassLoader())).append("]").toString(), '|'));
            String fail$1_msg = (new StringBuilder(8)).append("Not a ").append(scala.reflect.package..MODULE$.classTag(evidence$1)).append(": ").append(path).toString();
            Throwable fail$1_error$1_e = new IllegalArgumentException(fail$1_msg);
            errorFn.apply(fail$1_msg);
            throw fail$1_error$1_e;
         } else {
            Object filter$extension_$this;
            Object var64;
            label305: {
               label310: {
                  Object[] refArrayOps_xs = clazz.getConstructors();
                  Object var39 = null;
                  filter$extension_$this = refArrayOps_xs;
                  ArrayBuilder var54 = scala.collection.mutable.ArrayBuilder..MODULE$;
                  ClassTag filter$extension_make_evidence$1 = scala.reflect.ClassTag..MODULE$.apply(refArrayOps_xs.getClass().getComponentType());
                  Class var20 = filter$extension_make_evidence$1.runtimeClass();
                  Class var55 = Byte.TYPE;
                  if (var55 == null) {
                     if (var20 == null) {
                        break label310;
                     }
                  } else if (var55.equals(var20)) {
                     break label310;
                  }

                  label311: {
                     var55 = Short.TYPE;
                     if (var55 == null) {
                        if (var20 == null) {
                           break label311;
                        }
                     } else if (var55.equals(var20)) {
                        break label311;
                     }

                     label312: {
                        var55 = Character.TYPE;
                        if (var55 == null) {
                           if (var20 == null) {
                              break label312;
                           }
                        } else if (var55.equals(var20)) {
                           break label312;
                        }

                        label313: {
                           var55 = Integer.TYPE;
                           if (var55 == null) {
                              if (var20 == null) {
                                 break label313;
                              }
                           } else if (var55.equals(var20)) {
                              break label313;
                           }

                           label314: {
                              var55 = Long.TYPE;
                              if (var55 == null) {
                                 if (var20 == null) {
                                    break label314;
                                 }
                              } else if (var55.equals(var20)) {
                                 break label314;
                              }

                              label315: {
                                 var55 = Float.TYPE;
                                 if (var55 == null) {
                                    if (var20 == null) {
                                       break label315;
                                    }
                                 } else if (var55.equals(var20)) {
                                    break label315;
                                 }

                                 label316: {
                                    var55 = Double.TYPE;
                                    if (var55 == null) {
                                       if (var20 == null) {
                                          break label316;
                                       }
                                    } else if (var55.equals(var20)) {
                                       break label316;
                                    }

                                    label317: {
                                       var55 = Boolean.TYPE;
                                       if (var55 == null) {
                                          if (var20 == null) {
                                             break label317;
                                          }
                                       } else if (var55.equals(var20)) {
                                          break label317;
                                       }

                                       label248: {
                                          var55 = Void.TYPE;
                                          if (var55 == null) {
                                             if (var20 == null) {
                                                break label248;
                                             }
                                          } else if (var55.equals(var20)) {
                                             break label248;
                                          }

                                          var64 = new ArrayBuilder.ofRef(filter$extension_make_evidence$1);
                                          break label305;
                                       }

                                       var64 = new ArrayBuilder.ofUnit();
                                       break label305;
                                    }

                                    var64 = new ArrayBuilder.ofBoolean();
                                    break label305;
                                 }

                                 var64 = new ArrayBuilder.ofDouble();
                                 break label305;
                              }

                              var64 = new ArrayBuilder.ofFloat();
                              break label305;
                           }

                           var64 = new ArrayBuilder.ofLong();
                           break label305;
                        }

                        var64 = new ArrayBuilder.ofInt();
                        break label305;
                     }

                     var64 = new ArrayBuilder.ofChar();
                     break label305;
                  }

                  var64 = new ArrayBuilder.ofShort();
                  break label305;
               }

               var64 = new ArrayBuilder.ofByte();
            }

            Object var46 = null;
            Object var47 = null;
            ArrayBuilder filter$extension_res = (ArrayBuilder)var64;

            for(int filter$extension_i = 0; filter$extension_i < ((Object[])filter$extension_$this).length; ++filter$extension_i) {
               Object filter$extension_x = ((Object[])filter$extension_$this)[filter$extension_i];
               if ($anonfun$create$2(args, (Constructor)filter$extension_x)) {
                  filter$extension_res.addOne(filter$extension_x);
               }
            }

            var64 = filter$extension_res.result();
            filter$extension_$this = null;
            filter$extension_res = null;
            Object var45 = null;
            Constructor[] bySize = (Constructor[])var64;
            if (bySize.length == 0) {
               StringBuilder var81 = (new StringBuilder(33)).append("No constructor takes ");
               if (args == null) {
                  throw null;
               } else {
                  String fail$1_msg = var81.append(args.length()).append(" parameters.").toString();
                  Throwable fail$1_error$1_e = new IllegalArgumentException(fail$1_msg);
                  errorFn.apply(fail$1_msg);
                  throw fail$1_error$1_e;
               }
            } else {
               label234: {
                  label319: {
                     ArrayBuilder var66 = scala.collection.mutable.ArrayBuilder..MODULE$;
                     ClassTag filter$extension_make_evidence$1 = scala.reflect.ClassTag..MODULE$.apply(bySize.getClass().getComponentType());
                     Class var25 = filter$extension_make_evidence$1.runtimeClass();
                     Class var67 = Byte.TYPE;
                     if (var67 == null) {
                        if (var25 == null) {
                           break label319;
                        }
                     } else if (var67.equals(var25)) {
                        break label319;
                     }

                     label320: {
                        var67 = Short.TYPE;
                        if (var67 == null) {
                           if (var25 == null) {
                              break label320;
                           }
                        } else if (var67.equals(var25)) {
                           break label320;
                        }

                        label321: {
                           var67 = Character.TYPE;
                           if (var67 == null) {
                              if (var25 == null) {
                                 break label321;
                              }
                           } else if (var67.equals(var25)) {
                              break label321;
                           }

                           label322: {
                              var67 = Integer.TYPE;
                              if (var67 == null) {
                                 if (var25 == null) {
                                    break label322;
                                 }
                              } else if (var67.equals(var25)) {
                                 break label322;
                              }

                              label323: {
                                 var67 = Long.TYPE;
                                 if (var67 == null) {
                                    if (var25 == null) {
                                       break label323;
                                    }
                                 } else if (var67.equals(var25)) {
                                    break label323;
                                 }

                                 label324: {
                                    var67 = Float.TYPE;
                                    if (var67 == null) {
                                       if (var25 == null) {
                                          break label324;
                                       }
                                    } else if (var67.equals(var25)) {
                                       break label324;
                                    }

                                    label325: {
                                       var67 = Double.TYPE;
                                       if (var67 == null) {
                                          if (var25 == null) {
                                             break label325;
                                          }
                                       } else if (var67.equals(var25)) {
                                          break label325;
                                       }

                                       label326: {
                                          var67 = Boolean.TYPE;
                                          if (var67 == null) {
                                             if (var25 == null) {
                                                break label326;
                                             }
                                          } else if (var67.equals(var25)) {
                                             break label326;
                                          }

                                          label177: {
                                             var67 = Void.TYPE;
                                             if (var67 == null) {
                                                if (var25 == null) {
                                                   break label177;
                                                }
                                             } else if (var67.equals(var25)) {
                                                break label177;
                                             }

                                             var64 = new ArrayBuilder.ofRef(filter$extension_make_evidence$1);
                                             break label234;
                                          }

                                          var64 = new ArrayBuilder.ofUnit();
                                          break label234;
                                       }

                                       var64 = new ArrayBuilder.ofBoolean();
                                       break label234;
                                    }

                                    var64 = new ArrayBuilder.ofDouble();
                                    break label234;
                                 }

                                 var64 = new ArrayBuilder.ofFloat();
                                 break label234;
                              }

                              var64 = new ArrayBuilder.ofLong();
                              break label234;
                           }

                           var64 = new ArrayBuilder.ofInt();
                           break label234;
                        }

                        var64 = new ArrayBuilder.ofChar();
                        break label234;
                     }

                     var64 = new ArrayBuilder.ofShort();
                     break label234;
                  }

                  var64 = new ArrayBuilder.ofByte();
               }

               Object var50 = null;
               Object var51 = null;
               ArrayBuilder filter$extension_res = (ArrayBuilder)var64;

               for(int filter$extension_i = 0; filter$extension_i < bySize.length; ++filter$extension_i) {
                  Object filter$extension_x = bySize[filter$extension_i];
                  if ($anonfun$create$3(args, (Constructor)filter$extension_x)) {
                     filter$extension_res.addOne(filter$extension_x);
                  }
               }

               var64 = filter$extension_res.result();
               filter$extension_res = null;
               Object var49 = null;
               Constructor[] maybes = (Constructor[])var64;
               if (maybes.length == 1) {
                  return ((Constructor)scala.collection.ArrayOps..MODULE$.head$extension(maybes)).newInstance(scala.runtime.ScalaRunTime..MODULE$.toObjectArray(args.toArray(scala.reflect.ClassTag..MODULE$.Any())));
               } else if (bySize.length == 1) {
                  StringBuilder var79 = (new StringBuilder(39)).append("One constructor takes ");
                  if (args == null) {
                     throw null;
                  } else {
                     var79 = var79.append(args.length()).append(" parameters but ");
                     ArraySeq.ofRef var83 = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.collect$extension(scala.collection.ArrayOps..MODULE$.zip$extension(((Constructor)scala.collection.ArrayOps..MODULE$.head$extension(bySize)).getParameterTypes(), args), new Serializable() {
                        private static final long serialVersionUID = 0L;

                        public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
                           if (x1 != null) {
                              Class k = (Class)x1._1();
                              Object a = x1._2();
                              if (!RichClassLoader$.scala$reflect$internal$util$RichClassLoader$$isAssignable$1(k, a)) {
                                 return (new StringBuilder(4)).append(k).append(" != ").append(a.getClass()).toString();
                              }
                           }

                           return default.apply(x1);
                        }

                        public final boolean isDefinedAt(final Tuple2 x1) {
                           if (x1 != null) {
                              Class k = (Class)x1._1();
                              Object a = x1._2();
                              if (!RichClassLoader$.scala$reflect$internal$util$RichClassLoader$$isAssignable$1(k, a)) {
                                 return true;
                              }
                           }

                           return false;
                        }
                     }, scala.reflect.ClassTag..MODULE$.apply(String.class)));
                     String mkString_sep = "; ";
                     if (var83 == null) {
                        throw null;
                     } else {
                        AbstractIterable mkString_this = var83;
                        String mkString_end = "";
                        String mkString_start = "";
                        String var84 = IterableOnceOps.mkString$(mkString_this, mkString_start, mkString_sep, mkString_end);
                        Object var52 = null;
                        Object var53 = null;
                        mkString_this = null;
                        Object var41 = null;
                        String fail$1_msg = var79.append(var84).append(".").toString();
                        Throwable fail$1_error$1_e = new IllegalArgumentException(fail$1_msg);
                        errorFn.apply(fail$1_msg);
                        throw fail$1_error$1_e;
                     }
                  }
               } else {
                  StringBuilder var78 = (new StringBuilder(37)).append("Constructor must accept arg list (");
                  IterableOnceOps var10001 = (IterableOnceOps)args.map((x$3) -> x$3.getClass().getName());
                  String mkString_sep = ", ";
                  if (var10001 == null) {
                     throw null;
                  } else {
                     String var82 = var10001.mkString("", mkString_sep, "");
                     Object var42 = null;
                     String fail$1_msg = var78.append(var82).append("): ").append(path).toString();
                     Throwable fail$1_error$1_e = new IllegalArgumentException(fail$1_msg);
                     errorFn.apply(fail$1_msg);
                     throw fail$1_error$1_e;
                  }
               }
            }
         }
      } catch (Throwable var38) {
         if (var38 instanceof ClassNotFoundException) {
            ClassNotFoundException var10 = (ClassNotFoundException)var38;
            String error$1_msg = (new StringBuilder(17)).append("Class not found: ").append(path).toString();
            errorFn.apply(error$1_msg);
            throw var10;
         } else if (var38 instanceof LinkageError ? true : var38 instanceof ReflectiveOperationException) {
            String error$1_msg = (new StringBuilder(29)).append("Unable to create instance: ").append(path).append(": ").append(var38.toString()).toString();
            errorFn.apply(error$1_msg);
            throw var38;
         } else {
            throw var38;
         }
      }
   }

   public final byte[] classBytes$extension(final ClassLoader $this, final String className) {
      InputStream var3 = this.classAsStream$extension($this, className);
      if (var3 == null) {
         return (byte[])scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Byte());
      } else {
         Streamable$ var10000 = Streamable$.MODULE$;
         Function0 bytes_is = () -> var3;
         return Streamable.Bytes.toByteArray$(new Streamable.Bytes(bytes_is) {
            private final Function0 is$1;

            public long length() {
               return Streamable.Bytes.length$(this);
            }

            public BufferedInputStream bufferedInput() {
               return Streamable.Bytes.bufferedInput$(this);
            }

            public Iterator bytes() {
               return Streamable.Bytes.bytes$(this);
            }

            public Iterator bytesAsInts() {
               return Streamable.Bytes.bytesAsInts$(this);
            }

            public byte[] toByteArray() {
               return Streamable.Bytes.toByteArray$(this);
            }

            public InputStream inputStream() {
               return (InputStream)this.is$1.apply();
            }

            public {
               this.is$1 = is$1;
            }
         });
      }
   }

   public final InputStream classAsStream$extension(final ClassLoader $this, final String className) {
      return $this.getResourceAsStream(className.endsWith(".class") ? className : (new StringBuilder(6)).append(className.replace('.', '/')).append(".class").toString());
   }

   public final void run$extension(final ClassLoader $this, final String objectName, final Seq arguments) {
      Option var10000 = this.tryClass$extension($this, objectName, true);
      if (var10000 == null) {
         throw null;
      } else {
         Option getOrElse_this = var10000;
         if (getOrElse_this.isEmpty()) {
            throw new ClassNotFoundException(objectName);
         } else {
            Object var15 = getOrElse_this.get();
            Object var14 = null;
            Method method = ((Class)var15).getMethod("main", String[].class);
            if (!Modifier.isStatic(method.getModifiers())) {
               throw new NoSuchMethodException((new StringBuilder(19)).append(objectName).append(".main is not static").toString());
            } else {
               try {
                  ClassLoader asContext$extension_saved = Thread.currentThread().getContextClassLoader();

                  try {
                     ScalaClassLoader$.MODULE$.setContext($this);
                     $anonfun$run$extension$1(method, arguments);
                  } finally {
                     ScalaClassLoader$.MODULE$.setContext(asContext$extension_saved);
                  }

               } catch (Throwable var13) {
                  PartialFunction catchExpr$1 = ReflectionUtils$.MODULE$.unwrapHandler(new Serializable() {
                     private static final long serialVersionUID = 0L;

                     public final Object applyOrElse(final Throwable x1, final Function1 default) {
                        throw x1;
                     }

                     public final boolean isDefinedAt(final Throwable x1) {
                        return true;
                     }
                  });
                  if (catchExpr$1.isDefinedAt(var13)) {
                     catchExpr$1.apply(var13);
                  } else {
                     throw var13;
                  }
               }
            }
         }
      }
   }

   public final int hashCode$extension(final ClassLoader $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final ClassLoader $this, final Object x$1) {
      if (x$1 instanceof RichClassLoader) {
         ClassLoader var3 = x$1 == null ? null : ((RichClassLoader)x$1).scala$reflect$internal$util$RichClassLoader$$self();
         if ($this == null) {
            if (var3 == null) {
               return true;
            }
         } else if ($this.equals(var3)) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final Object $anonfun$create$1(final Class x$1) {
      return x$1.getConstructor().newInstance();
   }

   private static final Nothing fail$1(final String msg, final Function1 errorFn$1) {
      Throwable error$1_e = new IllegalArgumentException(msg);
      errorFn$1.apply(msg);
      throw error$1_e;
   }

   private static final Nothing error$1(final String msg, final Throwable e, final Function1 errorFn$1) {
      errorFn$1.apply(msg);
      throw e;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$create$2(final Seq args$1, final Constructor x$2) {
      return x$2.getParameterCount() == args$1.length();
   }

   public static final boolean scala$reflect$internal$util$RichClassLoader$$isAssignable$1(final Class k, final Object a) {
      Class var2 = Integer.TYPE;
      if (k == null) {
         if (var2 == null) {
            return a instanceof Integer;
         }
      } else if (k.equals(var2)) {
         return a instanceof Integer;
      }

      Class var3 = Boolean.TYPE;
      if (k == null) {
         if (var3 == null) {
            return a instanceof Boolean;
         }
      } else if (k.equals(var3)) {
         return a instanceof Boolean;
      }

      Class var4 = Long.TYPE;
      if (k == null) {
         if (var4 == null) {
            return a instanceof Long;
         }
      } else if (k.equals(var4)) {
         return a instanceof Long;
      }

      return k.isAssignableFrom(a.getClass());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$create$4(final Tuple2 x0$1) {
      if (x0$1 != null) {
         Class k = (Class)x0$1._1();
         Object a = x0$1._2();
         return scala$reflect$internal$util$RichClassLoader$$isAssignable$1(k, a);
      } else {
         throw new MatchError((Object)null);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$create$3(final Seq args$1, final Constructor c) {
      Object[] refArrayOps_xs = scala.collection.ArrayOps..MODULE$.zip$extension(c.getParameterTypes(), args$1);
      Object var5 = null;
      Object forall$extension_$this = refArrayOps_xs;

      for(int forall$extension_i = 0; forall$extension_i < ((Object[])forall$extension_$this).length; ++forall$extension_i) {
         if (!$anonfun$create$4((Tuple2)((Object[])forall$extension_$this)[forall$extension_i])) {
            return false;
         }
      }

      return true;
   }

   // $FF: synthetic method
   public static final Nothing $anonfun$run$1(final String objectName$1) {
      throw new ClassNotFoundException(objectName$1);
   }

   // $FF: synthetic method
   public static final Object $anonfun$run$extension$1(final Method method$1, final Seq arguments$1) {
      return method$1.invoke((Object)null, arguments$1.toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)));
   }

   private RichClassLoader$() {
   }

   // $FF: synthetic method
   public static final Object $anonfun$create$2$adapted(final Seq args$1, final Constructor x$2) {
      return BoxesRunTime.boxToBoolean($anonfun$create$2(args$1, x$2));
   }

   // $FF: synthetic method
   public static final Object $anonfun$create$3$adapted(final Seq args$1, final Constructor c) {
      return BoxesRunTime.boxToBoolean($anonfun$create$3(args$1, c));
   }

   // $FF: synthetic method
   public static final Object $anonfun$create$4$adapted(final Tuple2 x0$1) {
      return BoxesRunTime.boxToBoolean($anonfun$create$4(x0$1));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
