package scala.reflect.internal.util;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import java.net.URL;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.collection.AbstractIterable;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.ArraySeq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.package.;
import scala.reflect.runtime.ReflectionUtils$;

@ScalaSignature(
   bytes = "\u0006\u0005\t5aaB\u0011#!\u0003\r\ta\u000b\u0005\u0006i\u0001!\t!\u000e\u0005\u0006u\u0001!Ia\u000f\u0005\u0006\u0001\u0002!\t!\u0011\u0005\u0006'\u0002!\t\u0001\u0016\u0005\u0006_\u0002!\t\u0001\u001d\u0005\u0006o\u0002!\t\u0001\u001f\u0005\u0006o\u0002!\tA\u001f\u0005\b\u0003G\u0001A\u0011AA\u0013\u0011\u001d\t9\u0004\u0001C\u0001\u0003sAq!!\u0013\u0001\t\u0003\tYeB\u0004\u0002d\tB\t!!\u001a\u0007\r\u0005\u0012\u0003\u0012AA4\u0011\u001d\tI\u0007\u0004C\u0001\u0003WBq!!\u001c\r\t\u0007\ty\u0007C\u0004\u0002x1!\t!!\u001f\t\u000f\u0005mD\u0002\"\u0001\u0002z!9\u0011Q\u0010\u0007\u0005\u0002\u0005}dABAB\u0019\u0001\t)\t\u0003\u0006\u0002\u0018J\u0011\t\u0011)A\u0005\u00033C\u0011\"!)\u0013\u0005\u0003\u0005\u000b\u0011\u0002\u0017\t\u000f\u0005%$\u0003\"\u0001\u0002$\"A\u0011Q\u0016\n!B\u0013\tI\nC\u0004\u00020J!\t!!-\t\u000f\u0005M&\u0003\"\u0011\u00026\"1\u00111\u0018\n\u0005BUBq!!0\r\t\u0003\ty\fC\u0005\u0002F2\t\n\u0011\"\u0001\u0002H\"9\u0011Q\u001c\u0007\u0005\u0002\u0005}\u0007\"CAs\u0019E\u0005I\u0011AAd\u0011\u001d\t9\u000f\u0004C\u0001\u0003SDq!a>\r\t\u0003\tI\u0010C\u0004\u0003\f1\u0001\u000b\u0011\u0002\u0017\u0003!M\u001b\u0017\r\\1DY\u0006\u001c8\u000fT8bI\u0016\u0014(BA\u0012%\u0003\u0011)H/\u001b7\u000b\u0005\u00152\u0013\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005\u001dB\u0013a\u0002:fM2,7\r\u001e\u0006\u0002S\u0005)1oY1mC\u000e\u00011C\u0001\u0001-!\ti#'D\u0001/\u0015\ty\u0003'\u0001\u0003mC:<'\"A\u0019\u0002\t)\fg/Y\u0005\u0003g9\u00121b\u00117bgNdu.\u00193fe\u00061A%\u001b8ji\u0012\"\u0012A\u000e\t\u0003oaj\u0011\u0001K\u0005\u0003s!\u0012A!\u00168ji\u0006!qO]1q+\u0005a\u0004CA\u001f?\u001b\u0005\u0011\u0013BA #\u0005=\u0011\u0016n\u00195DY\u0006\u001c8\u000fT8bI\u0016\u0014\u0018!C1t\u0007>tG/\u001a=u+\t\u0011U\t\u0006\u0002D\u001dB\u0011A)\u0012\u0007\u0001\t\u001515A1\u0001H\u0005\u0005!\u0016C\u0001%L!\t9\u0014*\u0003\u0002KQ\t9aj\u001c;iS:<\u0007CA\u001cM\u0013\ti\u0005FA\u0002B]fDaaT\u0002\u0005\u0002\u0004\u0001\u0016AB1di&|g\u000eE\u00028#\u000eK!A\u0015\u0015\u0003\u0011q\u0012\u0017P\\1nKz\na\u0002\u001e:z)>du.\u00193DY\u0006\u001c8/\u0006\u0002VKR\u0011aK\u001b\t\u0004o]K\u0016B\u0001-)\u0005\u0019y\u0005\u000f^5p]B\u0019!,\u00193\u000f\u0005m{\u0006C\u0001/)\u001b\u0005i&B\u00010+\u0003\u0019a$o\\8u}%\u0011\u0001\rK\u0001\u0007!J,G-\u001a4\n\u0005\t\u001c'!B\"mCN\u001c(B\u00011)!\t!U\rB\u0003G\t\t\u0007a-\u0005\u0002IOB\u0011q\u0007[\u0005\u0003S\"\u0012a!\u00118z%\u00164\u0007\"B6\u0005\u0001\u0004a\u0017\u0001\u00029bi\"\u0004\"AW7\n\u00059\u001c'AB*ue&tw-\u0001\u000buef$v.\u00138ji&\fG.\u001b>f\u00072\f7o]\u000b\u0003cV$\"A\u001d<\u0011\u0007]:6\u000fE\u0002[CR\u0004\"\u0001R;\u0005\u000b\u0019+!\u0019\u00014\t\u000b-,\u0001\u0019\u00017\u0002\r\r\u0014X-\u0019;f)\t9\u0017\u0010C\u0003l\r\u0001\u0007A.\u0006\u0002|\u007fR)A0a\u0006\u0002\u001aQ\u0019Q0!\u0004\u0015\u0007y\f\t\u0001\u0005\u0002E\u007f\u0012)ai\u0002b\u0001M\"I\u00111A\u0004\u0002\u0002\u0003\u000f\u0011QA\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004#BA\u0004\u0003\u0013qX\"\u0001\u0014\n\u0007\u0005-aE\u0001\u0005DY\u0006\u001c8\u000fV1h\u0011\u001d\tya\u0002a\u0001\u0003#\tA!\u0019:hgB!q'a\u0005h\u0013\r\t)\u0002\u000b\u0002\u000byI,\u0007/Z1uK\u0012t\u0004\"B6\b\u0001\u0004a\u0007bBA\u000e\u000f\u0001\u0007\u0011QD\u0001\bKJ\u0014xN\u001d$o!\u00159\u0014q\u000477\u0013\r\t\t\u0003\u000b\u0002\n\rVt7\r^5p]F\n!b\u00197bgN\u0014\u0015\u0010^3t)\u0011\t9#a\r\u0011\u000b]\nI#!\f\n\u0007\u0005-\u0002FA\u0003BeJ\f\u0017\u0010E\u00028\u0003_I1!!\r)\u0005\u0011\u0011\u0015\u0010^3\t\r\u0005U\u0002\u00021\u0001m\u0003%\u0019G.Y:t\u001d\u0006lW-A\u0007dY\u0006\u001c8/Q:TiJ,\u0017-\u001c\u000b\u0005\u0003w\t9\u0005\u0005\u0003\u0002>\u0005\rSBAA \u0015\r\t\t\u0005M\u0001\u0003S>LA!!\u0012\u0002@\tY\u0011J\u001c9viN#(/Z1n\u0011\u0019\t)$\u0003a\u0001Y\u0006\u0019!/\u001e8\u0015\u000bY\ni%!\u0015\t\r\u0005=#\u00021\u0001m\u0003)y'M[3di:\u000bW.\u001a\u0005\b\u0003'R\u0001\u0019AA+\u0003%\t'oZ;nK:$8\u000fE\u0003\u0002X\u0005uCND\u00028\u00033J1!a\u0017)\u0003\u001d\u0001\u0018mY6bO\u0016LA!a\u0018\u0002b\t\u00191+Z9\u000b\u0007\u0005m\u0003&\u0001\tTG\u0006d\u0017m\u00117bgNdu.\u00193feB\u0011Q\bD\n\u0003\u0019\u001d\fa\u0001P5oSRtDCAA3\u0003\u0015\t\u0007\u000f\u001d7z)\u0011\t\t(a\u001d\u0011\u0005u\u0002\u0001BBA;\u001d\u0001\u0007A&\u0001\u0002dY\u0006i1m\u001c8uKb$Hj\\1eKJ,\"!!\u001d\u0002\u0013\u0005\u0004\b\u000fT8bI\u0016\u0014\u0018AC:fi\u000e{g\u000e^3yiR\u0019a'!!\t\r\u0005U\u0014\u00031\u0001-\u00059)&\u000bT\"mCN\u001cHj\\1eKJ\u001crAEAD\u0003c\n\t\n\u0005\u0003\u0002\n\u0006=UBAAF\u0015\r\ti\tM\u0001\u0004]\u0016$\u0018\u0002BAB\u0003\u0017\u00032!PAJ\u0013\r\t)J\t\u0002\r\u0011\u0006\u001c8\t\\1tgB\u000bG\u000f[\u0001\u0005kJd7\u000f\u0005\u0004\u0002X\u0005u\u00131\u0014\t\u0005\u0003\u0013\u000bi*\u0003\u0003\u0002 \u0006-%aA+S\u0019\u00061\u0001/\u0019:f]R$b!!*\u0002*\u0006-\u0006cAAT%5\tA\u0002C\u0004\u0002\u0018V\u0001\r!!'\t\r\u0005\u0005V\u00031\u0001-\u0003=\u0019G.Y:tY>\fG-\u001a:V%2\u001b\u0018!D2mCN\u001c\b+\u0019;i+Jc5/\u0006\u0002\u0002\u001a\u00061\u0011\r\u001a3V%2#2ANA\\\u0011\u001d\tI\f\u0007a\u0001\u00037\u000b1!\u001e:m\u0003\u0015\u0019Gn\\:f\u0003!1'o\\7V%2\u001bHCBAS\u0003\u0003\f\u0019\rC\u0004\u0002\u0018j\u0001\r!!'\t\u0011\u0005\u0005&\u0004%AA\u00021\n!C\u001a:p[V\u0013Fj\u001d\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011\u0011\u001a\u0016\u0004Y\u0005-7FAAg!\u0011\ty-!7\u000e\u0005\u0005E'\u0002BAj\u0003+\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005]\u0007&\u0001\u0006b]:|G/\u0019;j_:LA!a7\u0002R\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002/\u0019\u0014x.\\+S\u0019N\u0004\u0016M]1mY\u0016d7)\u00199bE2,GCBAD\u0003C\f\u0019\u000fC\u0004\u0002\u0018r\u0001\r!!'\t\u0011\u0005\u0005F\u0004%AA\u00021\n\u0011E\u001a:p[V\u0013Fj\u001d)be\u0006dG.\u001a7DCB\f'\r\\3%I\u00164\u0017-\u001e7uII\n1b\u00197bgN,\u00050[:ugR1\u00111^Ay\u0003g\u00042aNAw\u0013\r\ty\u000f\u000b\u0002\b\u0005>|G.Z1o\u0011\u001d\t9J\ba\u0001\u00033Ca!!>\u001f\u0001\u0004a\u0017\u0001\u00028b[\u0016\fQb\u001c:jO&twJZ\"mCN\u001cH\u0003BA~\u0003{\u0004BaN,\u0002\u001c\"9\u0011q`\u0010A\u0002\t\u0005\u0011!\u0001=1\t\t\r!q\u0001\t\u00055\u0006\u0014)\u0001E\u0002E\u0005\u000f!1B!\u0003\u0002~\u0006\u0005\t\u0011!B\u0001\u000f\n\u0019q\fJ\u0019\u0002\u001f\t|w\u000e^\"mCN\u001cHj\\1eKJ\u0004"
)
public interface ScalaClassLoader {
   static Option originOfClass(final Class x) {
      return ScalaClassLoader$.MODULE$.originOfClass(x);
   }

   static boolean classExists(final Seq urls, final String name) {
      return ScalaClassLoader$.MODULE$.classExists(urls, name);
   }

   static ClassLoader fromURLsParallelCapable$default$2() {
      ScalaClassLoader$ var10000 = ScalaClassLoader$.MODULE$;
      return null;
   }

   static java.net.URLClassLoader fromURLsParallelCapable(final Seq urls, final ClassLoader parent) {
      return ScalaClassLoader$.MODULE$.fromURLsParallelCapable(urls, parent);
   }

   static ClassLoader fromURLs$default$2() {
      ScalaClassLoader$ var10000 = ScalaClassLoader$.MODULE$;
      return null;
   }

   static URLClassLoader fromURLs(final Seq urls, final ClassLoader parent) {
      return ScalaClassLoader$.MODULE$.fromURLs(urls, parent);
   }

   static void setContext(final ClassLoader cl) {
      ScalaClassLoader$.MODULE$.setContext(cl);
   }

   static ScalaClassLoader appLoader() {
      return ScalaClassLoader$.MODULE$.appLoader();
   }

   static ScalaClassLoader contextLoader() {
      return ScalaClassLoader$.MODULE$.contextLoader();
   }

   static ScalaClassLoader apply(final ClassLoader cl) {
      return ScalaClassLoader$.MODULE$.apply(cl);
   }

   private ClassLoader wrap() {
      return (ClassLoader)this;
   }

   default Object asContext(final Function0 action) {
      RichClassLoader$ var10000 = RichClassLoader$.MODULE$;
      ClassLoader asContext$extension_$this = (ClassLoader)this;
      ClassLoader asContext$extension_saved = Thread.currentThread().getContextClassLoader();

      try {
         ScalaClassLoader$.MODULE$.setContext(asContext$extension_$this);
         var10000 = (RichClassLoader$)action.apply();
      } finally {
         ScalaClassLoader$.MODULE$.setContext(asContext$extension_saved);
      }

      return var10000;
   }

   default Option tryToLoadClass(final String path) {
      return RichClassLoader$.MODULE$.tryClass$extension((ClassLoader)this, path, false);
   }

   default Option tryToInitializeClass(final String path) {
      return RichClassLoader$.MODULE$.tryClass$extension((ClassLoader)this, path, true);
   }

   default Object create(final String path) {
      return RichClassLoader$.MODULE$.create$extension((ClassLoader)this, path);
   }

   default Object create(final String path, final Function1 errorFn, final Seq args, final ClassTag evidence$2) {
      RichClassLoader$ var10000 = RichClassLoader$.MODULE$;
      ClassLoader create$extension_$this = this.wrap();

      try {
         Class create$extension_clazz = Class.forName(path, true, create$extension_$this);
         if (!.MODULE$.classTag(evidence$2).runtimeClass().isAssignableFrom(create$extension_clazz)) {
            errorFn.apply(scala.collection.StringOps..MODULE$.stripMargin$extension((new StringBuilder(54)).append("Loader for ").append(.MODULE$.classTag(evidence$2)).append(":   [").append(ReflectionUtils$.MODULE$.show(.MODULE$.classTag(evidence$2).runtimeClass().getClassLoader())).append("]\n                    |Loader for ").append(create$extension_clazz.getName()).append(": [").append(ReflectionUtils$.MODULE$.show(create$extension_clazz.getClassLoader())).append("]").toString(), '|'));
            String create$extension_fail$1_msg = (new StringBuilder(8)).append("Not a ").append(.MODULE$.classTag(evidence$2)).append(": ").append(path).toString();
            Throwable create$extension_fail$1_error$1_e = new IllegalArgumentException(create$extension_fail$1_msg);
            errorFn.apply(create$extension_fail$1_msg);
            throw create$extension_fail$1_error$1_e;
         } else {
            Object create$extension_filter$extension_$this;
            label306: {
               label312: {
                  Object[] create$extension_refArrayOps_xs = create$extension_clazz.getConstructors();
                  Object var39 = null;
                  create$extension_filter$extension_$this = create$extension_refArrayOps_xs;
                  ArrayBuilder var55 = scala.collection.mutable.ArrayBuilder..MODULE$;
                  ClassTag create$extension_filter$extension_make_evidence$1 = scala.reflect.ClassTag..MODULE$.apply(create$extension_refArrayOps_xs.getClass().getComponentType());
                  Class var20 = create$extension_filter$extension_make_evidence$1.runtimeClass();
                  Class var56 = Byte.TYPE;
                  if (var56 == null) {
                     if (var20 == null) {
                        break label312;
                     }
                  } else if (var56.equals(var20)) {
                     break label312;
                  }

                  label313: {
                     var56 = Short.TYPE;
                     if (var56 == null) {
                        if (var20 == null) {
                           break label313;
                        }
                     } else if (var56.equals(var20)) {
                        break label313;
                     }

                     label314: {
                        var56 = Character.TYPE;
                        if (var56 == null) {
                           if (var20 == null) {
                              break label314;
                           }
                        } else if (var56.equals(var20)) {
                           break label314;
                        }

                        label315: {
                           var56 = Integer.TYPE;
                           if (var56 == null) {
                              if (var20 == null) {
                                 break label315;
                              }
                           } else if (var56.equals(var20)) {
                              break label315;
                           }

                           label316: {
                              var56 = Long.TYPE;
                              if (var56 == null) {
                                 if (var20 == null) {
                                    break label316;
                                 }
                              } else if (var56.equals(var20)) {
                                 break label316;
                              }

                              label317: {
                                 var56 = Float.TYPE;
                                 if (var56 == null) {
                                    if (var20 == null) {
                                       break label317;
                                    }
                                 } else if (var56.equals(var20)) {
                                    break label317;
                                 }

                                 label318: {
                                    var56 = Double.TYPE;
                                    if (var56 == null) {
                                       if (var20 == null) {
                                          break label318;
                                       }
                                    } else if (var56.equals(var20)) {
                                       break label318;
                                    }

                                    label319: {
                                       var56 = Boolean.TYPE;
                                       if (var56 == null) {
                                          if (var20 == null) {
                                             break label319;
                                          }
                                       } else if (var56.equals(var20)) {
                                          break label319;
                                       }

                                       label249: {
                                          var56 = Void.TYPE;
                                          if (var56 == null) {
                                             if (var20 == null) {
                                                break label249;
                                             }
                                          } else if (var56.equals(var20)) {
                                             break label249;
                                          }

                                          var10000 = new ArrayBuilder.ofRef(create$extension_filter$extension_make_evidence$1);
                                          break label306;
                                       }

                                       var10000 = new ArrayBuilder.ofUnit();
                                       break label306;
                                    }

                                    var10000 = new ArrayBuilder.ofBoolean();
                                    break label306;
                                 }

                                 var10000 = new ArrayBuilder.ofDouble();
                                 break label306;
                              }

                              var10000 = new ArrayBuilder.ofFloat();
                              break label306;
                           }

                           var10000 = new ArrayBuilder.ofLong();
                           break label306;
                        }

                        var10000 = new ArrayBuilder.ofInt();
                        break label306;
                     }

                     var10000 = new ArrayBuilder.ofChar();
                     break label306;
                  }

                  var10000 = new ArrayBuilder.ofShort();
                  break label306;
               }

               var10000 = new ArrayBuilder.ofByte();
            }

            Object var46 = null;
            Object var47 = null;
            ArrayBuilder create$extension_filter$extension_res = var10000;

            for(int create$extension_filter$extension_i = 0; create$extension_filter$extension_i < ((Object[])create$extension_filter$extension_$this).length; ++create$extension_filter$extension_i) {
               Object create$extension_filter$extension_x = ((Object[])create$extension_filter$extension_$this)[create$extension_filter$extension_i];
               if (RichClassLoader$.$anonfun$create$2(args, (Constructor)create$extension_filter$extension_x)) {
                  create$extension_filter$extension_res.addOne(create$extension_filter$extension_x);
               }
            }

            var10000 = (RichClassLoader$)create$extension_filter$extension_res.result();
            create$extension_filter$extension_$this = null;
            create$extension_filter$extension_res = null;
            Object var45 = null;
            Constructor[] create$extension_bySize = (Constructor[])var10000;
            if (create$extension_bySize.length == 0) {
               StringBuilder var82 = (new StringBuilder(33)).append("No constructor takes ");
               if (args == null) {
                  throw null;
               } else {
                  String create$extension_fail$1_msg = var82.append(args.length()).append(" parameters.").toString();
                  Throwable create$extension_fail$1_error$1_e = new IllegalArgumentException(create$extension_fail$1_msg);
                  errorFn.apply(create$extension_fail$1_msg);
                  throw create$extension_fail$1_error$1_e;
               }
            } else {
               label235: {
                  label321: {
                     ArrayBuilder var67 = scala.collection.mutable.ArrayBuilder..MODULE$;
                     ClassTag create$extension_filter$extension_make_evidence$1 = scala.reflect.ClassTag..MODULE$.apply(create$extension_bySize.getClass().getComponentType());
                     Class var25 = create$extension_filter$extension_make_evidence$1.runtimeClass();
                     Class var68 = Byte.TYPE;
                     if (var68 == null) {
                        if (var25 == null) {
                           break label321;
                        }
                     } else if (var68.equals(var25)) {
                        break label321;
                     }

                     label322: {
                        var68 = Short.TYPE;
                        if (var68 == null) {
                           if (var25 == null) {
                              break label322;
                           }
                        } else if (var68.equals(var25)) {
                           break label322;
                        }

                        label323: {
                           var68 = Character.TYPE;
                           if (var68 == null) {
                              if (var25 == null) {
                                 break label323;
                              }
                           } else if (var68.equals(var25)) {
                              break label323;
                           }

                           label324: {
                              var68 = Integer.TYPE;
                              if (var68 == null) {
                                 if (var25 == null) {
                                    break label324;
                                 }
                              } else if (var68.equals(var25)) {
                                 break label324;
                              }

                              label325: {
                                 var68 = Long.TYPE;
                                 if (var68 == null) {
                                    if (var25 == null) {
                                       break label325;
                                    }
                                 } else if (var68.equals(var25)) {
                                    break label325;
                                 }

                                 label326: {
                                    var68 = Float.TYPE;
                                    if (var68 == null) {
                                       if (var25 == null) {
                                          break label326;
                                       }
                                    } else if (var68.equals(var25)) {
                                       break label326;
                                    }

                                    label327: {
                                       var68 = Double.TYPE;
                                       if (var68 == null) {
                                          if (var25 == null) {
                                             break label327;
                                          }
                                       } else if (var68.equals(var25)) {
                                          break label327;
                                       }

                                       label328: {
                                          var68 = Boolean.TYPE;
                                          if (var68 == null) {
                                             if (var25 == null) {
                                                break label328;
                                             }
                                          } else if (var68.equals(var25)) {
                                             break label328;
                                          }

                                          label178: {
                                             var68 = Void.TYPE;
                                             if (var68 == null) {
                                                if (var25 == null) {
                                                   break label178;
                                                }
                                             } else if (var68.equals(var25)) {
                                                break label178;
                                             }

                                             var10000 = new ArrayBuilder.ofRef(create$extension_filter$extension_make_evidence$1);
                                             break label235;
                                          }

                                          var10000 = new ArrayBuilder.ofUnit();
                                          break label235;
                                       }

                                       var10000 = new ArrayBuilder.ofBoolean();
                                       break label235;
                                    }

                                    var10000 = new ArrayBuilder.ofDouble();
                                    break label235;
                                 }

                                 var10000 = new ArrayBuilder.ofFloat();
                                 break label235;
                              }

                              var10000 = new ArrayBuilder.ofLong();
                              break label235;
                           }

                           var10000 = new ArrayBuilder.ofInt();
                           break label235;
                        }

                        var10000 = new ArrayBuilder.ofChar();
                        break label235;
                     }

                     var10000 = new ArrayBuilder.ofShort();
                     break label235;
                  }

                  var10000 = new ArrayBuilder.ofByte();
               }

               Object var50 = null;
               Object var51 = null;
               ArrayBuilder create$extension_filter$extension_res = var10000;

               for(int create$extension_filter$extension_i = 0; create$extension_filter$extension_i < create$extension_bySize.length; ++create$extension_filter$extension_i) {
                  Object create$extension_filter$extension_x = create$extension_bySize[create$extension_filter$extension_i];
                  if (RichClassLoader$.$anonfun$create$3(args, (Constructor)create$extension_filter$extension_x)) {
                     create$extension_filter$extension_res.addOne(create$extension_filter$extension_x);
                  }
               }

               var10000 = (RichClassLoader$)create$extension_filter$extension_res.result();
               create$extension_filter$extension_res = null;
               Object var49 = null;
               Constructor[] create$extension_maybes = (Constructor[])var10000;
               if (create$extension_maybes.length == 1) {
                  return ((Constructor)scala.collection.ArrayOps..MODULE$.head$extension(create$extension_maybes)).newInstance(scala.runtime.ScalaRunTime..MODULE$.toObjectArray(args.toArray(scala.reflect.ClassTag..MODULE$.Any())));
               } else if (create$extension_bySize.length == 1) {
                  StringBuilder var80 = (new StringBuilder(39)).append("One constructor takes ");
                  if (args == null) {
                     throw null;
                  } else {
                     var80 = var80.append(args.length()).append(" parameters but ");
                     ArraySeq.ofRef var84 = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.collect$extension(scala.collection.ArrayOps..MODULE$.zip$extension(((Constructor)scala.collection.ArrayOps..MODULE$.head$extension(create$extension_bySize)).getParameterTypes(), args), new Serializable() {
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
                     String create$extension_mkString_sep = "; ";
                     if (var84 == null) {
                        throw null;
                     } else {
                        AbstractIterable create$extension_mkString_this = var84;
                        String mkString_end = "";
                        String mkString_start = "";
                        String var85 = IterableOnceOps.mkString$(create$extension_mkString_this, mkString_start, create$extension_mkString_sep, mkString_end);
                        Object var52 = null;
                        Object var53 = null;
                        create$extension_mkString_this = null;
                        Object var41 = null;
                        String create$extension_fail$1_msg = var80.append(var85).append(".").toString();
                        Throwable create$extension_fail$1_error$1_e = new IllegalArgumentException(create$extension_fail$1_msg);
                        errorFn.apply(create$extension_fail$1_msg);
                        throw create$extension_fail$1_error$1_e;
                     }
                  }
               } else {
                  StringBuilder var79 = (new StringBuilder(37)).append("Constructor must accept arg list (");
                  IterableOnceOps var10001 = (IterableOnceOps)args.map(RichClassLoader$::$anonfun$create$5);
                  String create$extension_mkString_sep = ", ";
                  if (var10001 == null) {
                     throw null;
                  } else {
                     String var83 = var10001.mkString("", create$extension_mkString_sep, "");
                     Object var42 = null;
                     String create$extension_fail$1_msg = var79.append(var83).append("): ").append(path).toString();
                     Throwable create$extension_fail$1_error$1_e = new IllegalArgumentException(create$extension_fail$1_msg);
                     errorFn.apply(create$extension_fail$1_msg);
                     throw create$extension_fail$1_error$1_e;
                  }
               }
            }
         }
      } catch (Throwable var38) {
         if (var38 instanceof ClassNotFoundException) {
            ClassNotFoundException var10 = (ClassNotFoundException)var38;
            String create$extension_error$1_msg = (new StringBuilder(17)).append("Class not found: ").append(path).toString();
            errorFn.apply(create$extension_error$1_msg);
            throw var10;
         } else if (var38 instanceof LinkageError ? true : var38 instanceof ReflectiveOperationException) {
            String create$extension_error$1_msg = (new StringBuilder(29)).append("Unable to create instance: ").append(path).append(": ").append(var38.toString()).toString();
            errorFn.apply(create$extension_error$1_msg);
            throw var38;
         } else {
            throw var38;
         }
      }
   }

   default byte[] classBytes(final String className) {
      return RichClassLoader$.MODULE$.classBytes$extension((ClassLoader)this, className);
   }

   default InputStream classAsStream(final String className) {
      return RichClassLoader$.MODULE$.classAsStream$extension((ClassLoader)this, className);
   }

   default void run(final String objectName, final Seq arguments) {
      RichClassLoader$.MODULE$.run$extension((ClassLoader)this, objectName, arguments);
   }

   static void $init$(final ScalaClassLoader $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class URLClassLoader extends java.net.URLClassLoader implements ScalaClassLoader, HasClassPath {
      private Seq classloaderURLs;

      public Object asContext(final Function0 action) {
         return ScalaClassLoader.super.asContext(action);
      }

      public Option tryToLoadClass(final String path) {
         return ScalaClassLoader.super.tryToLoadClass(path);
      }

      public Option tryToInitializeClass(final String path) {
         return ScalaClassLoader.super.tryToInitializeClass(path);
      }

      public Object create(final String path) {
         return ScalaClassLoader.super.create(path);
      }

      public Object create(final String path, final Function1 errorFn, final Seq args, final ClassTag evidence$2) {
         return ScalaClassLoader.super.create(path, errorFn, args, evidence$2);
      }

      public byte[] classBytes(final String className) {
         return ScalaClassLoader.super.classBytes(className);
      }

      public InputStream classAsStream(final String className) {
         return ScalaClassLoader.super.classAsStream(className);
      }

      public void run(final String objectName, final Seq arguments) {
         ScalaClassLoader.super.run(objectName, arguments);
      }

      public Seq classPathURLs() {
         return this.classloaderURLs;
      }

      public void addURL(final URL url) {
         Seq var10001 = this.classloaderURLs;
         if (var10001 == null) {
            throw null;
         } else {
            this.classloaderURLs = (Seq)var10001.appended(url);
            super.addURL(url);
         }
      }

      public void close() {
         super.close();
         this.classloaderURLs = null;
      }

      public URLClassLoader(final Seq urls, final ClassLoader parent) {
         super((URL[])urls.toArray(scala.reflect.ClassTag..MODULE$.apply(URL.class)), parent);
         this.classloaderURLs = urls;
      }
   }
}
