package scala.reflect.internal.util;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
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
   bytes = "\u0006\u0005\t\u001dc\u0001\u0002\u0010 \u0005!BA\"\f\u0001\u0005\u0002\u0003\u0015)Q1A\u0005\n9B\u0011b\u000e\u0001\u0003\u0006\u0003\u0005\u000b\u0011B\u0018\t\u000ba\u0002A\u0011A\u001d\t\u000by\u0002A\u0011A \t\u000bE\u0003A\u0011\u0001*\t\u000b5\u0004A\u0011\u00018\t\u000bU\u0004A\u0011\u0002<\t\u000f\u0005\u0015\u0001\u0001\"\u0001\u0002\b!9\u0011Q\u0001\u0001\u0005\u0002\u0005-\u0001bBA \u0001\u0011\u0005\u0011\u0011\t\u0005\b\u0003'\u0002A\u0011AA+\u0011\u001d\t)\u0007\u0001C\u0001\u0003OB\u0011\"a \u0001\u0003\u0003%\t%!!\t\u0013\u0005%\u0005!!A\u0005B\u0005-uaBAI?!\u0005\u00111\u0013\u0004\u0007=}A\t!!&\t\ra\u0002B\u0011AAL\u0011\u001d\tI\n\u0005C\u0002\u00037Cq!!)\u0011\t\u000b\t\u0019\u000bC\u0004\u00026B!)!a.\t\u000f\u0005%\u0007\u0003\"\u0002\u0002L\"9\u0011Q\u001c\t\u0005\u0006\u0005}\u0007bBAz!\u0011\u0015\u0011Q\u001f\u0005\b\u0003g\u0004BQAA\u007f\u0011\u001d\u00119\u0002\u0005C\u0003\u00053AqA!\t\u0011\t\u000b\u0011\u0019\u0003C\u0004\u0003,A!)A!\f\t\u0013\t]\u0002#!A\u0005\u0006\te\u0002\"\u0003B\u001f!\u0005\u0005IQ\u0001B \u0005=\u0011\u0016n\u00195DY\u0006\u001c8\u000fT8bI\u0016\u0014(B\u0001\u0011\"\u0003\u0011)H/\u001b7\u000b\u0005\t\u001a\u0013\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005\u0011*\u0013a\u0002:fM2,7\r\u001e\u0006\u0002M\u0005)1oY1mC\u000e\u00011C\u0001\u0001*!\tQ3&D\u0001&\u0013\taSE\u0001\u0004B]f4\u0016\r\\\u00012g\u000e\fG.\u0019\u0013sK\u001adWm\u0019;%S:$XM\u001d8bY\u0012*H/\u001b7%%&\u001c\u0007n\u00117bgNdu.\u00193fe\u0012\"3/\u001a7g+\u0005y\u0003C\u0001\u00196\u001b\u0005\t$B\u0001\u001a4\u0003\u0011a\u0017M\\4\u000b\u0003Q\nAA[1wC&\u0011a'\r\u0002\f\u00072\f7o\u001d'pC\u0012,'/\u0001\u001atG\u0006d\u0017\r\n:fM2,7\r\u001e\u0013j]R,'O\\1mIU$\u0018\u000e\u001c\u0013SS\u000eD7\t\\1tg2{\u0017\rZ3sI\u0011\u001aX\r\u001c4!\u0003\u0019a\u0014N\\5u}Q\u0011!\b\u0010\t\u0003w\u0001i\u0011a\b\u0005\u0006{\r\u0001\raL\u0001\u0005g\u0016dg-A\u0005bg\u000e{g\u000e^3yiV\u0011\u0001i\u0011\u000b\u0003\u00032\u0003\"AQ\"\r\u0001\u0011)A\t\u0002b\u0001\u000b\n\tA+\u0005\u0002G\u0013B\u0011!fR\u0005\u0003\u0011\u0016\u0012qAT8uQ&tw\r\u0005\u0002+\u0015&\u00111*\n\u0002\u0004\u0003:L\bBB'\u0005\t\u0003\u0007a*\u0001\u0004bGRLwN\u001c\t\u0004U=\u000b\u0015B\u0001)&\u0005!a$-\u001f8b[\u0016t\u0014A\u0004;ssR{Gj\\1e\u00072\f7o]\u000b\u0003'\u000e$\"\u0001\u00165\u0011\u0007)*v+\u0003\u0002WK\t1q\n\u001d;j_:\u00042\u0001W0c\u001d\tIV\f\u0005\u0002[K5\t1L\u0003\u0002]O\u00051AH]8pizJ!AX\u0013\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0017MA\u0003DY\u0006\u001c8O\u0003\u0002_KA\u0011!i\u0019\u0003\u0006\t\u0016\u0011\r\u0001Z\t\u0003\r\u0016\u0004\"A\u000b4\n\u0005\u001d,#AB!osJ+g\rC\u0003j\u000b\u0001\u0007!.\u0001\u0003qCRD\u0007C\u0001-l\u0013\ta\u0017M\u0001\u0004TiJLgnZ\u0001\u0015iJLHk\\%oSRL\u0017\r\\5{K\u000ec\u0017m]:\u0016\u0005=\u001cHC\u00019u!\rQS+\u001d\t\u00041~\u0013\bC\u0001\"t\t\u0015!eA1\u0001e\u0011\u0015Ig\u00011\u0001k\u0003!!(/_\"mCN\u001cXCA<|)\rAH0 \t\u0004UUK\bc\u0001-`uB\u0011!i\u001f\u0003\u0006\t\u001e\u0011\r\u0001\u001a\u0005\u0006S\u001e\u0001\rA\u001b\u0005\u0006}\u001e\u0001\ra`\u0001\u000bS:LG/[1mSj,\u0007c\u0001\u0016\u0002\u0002%\u0019\u00111A\u0013\u0003\u000f\t{w\u000e\\3b]\u000611M]3bi\u0016$2!ZA\u0005\u0011\u0015I\u0007\u00021\u0001k+\u0011\ti!!\u0006\u0015\r\u0005=\u0011QFA\u0018)\u0011\t\t\"a\t\u0015\t\u0005M\u0011q\u0003\t\u0004\u0005\u0006UA!\u0002#\n\u0005\u0004!\u0007\"CA\r\u0013\u0005\u0005\t9AA\u000e\u0003))g/\u001b3f]\u000e,G%\r\t\u0007\u0003;\ty\"a\u0005\u000e\u0003\rJ1!!\t$\u0005!\u0019E.Y:t)\u0006<\u0007bBA\u0013\u0013\u0001\u0007\u0011qE\u0001\u0005CJ<7\u000f\u0005\u0003+\u0003SI\u0015bAA\u0016K\tQAH]3qK\u0006$X\r\u001a \t\u000b%L\u0001\u0019\u00016\t\u000f\u0005E\u0012\u00021\u0001\u00024\u00059QM\u001d:pe\u001as\u0007C\u0002\u0016\u00026)\fI$C\u0002\u00028\u0015\u0012\u0011BR;oGRLwN\\\u0019\u0011\u0007)\nY$C\u0002\u0002>\u0015\u0012A!\u00168ji\u0006Q1\r\\1tg\nKH/Z:\u0015\t\u0005\r\u0013q\n\t\u0006U\u0005\u0015\u0013\u0011J\u0005\u0004\u0003\u000f*#!B!se\u0006L\bc\u0001\u0016\u0002L%\u0019\u0011QJ\u0013\u0003\t\tKH/\u001a\u0005\u0007\u0003#R\u0001\u0019\u00016\u0002\u0013\rd\u0017m]:OC6,\u0017!D2mCN\u001c\u0018i]*ue\u0016\fW\u000e\u0006\u0003\u0002X\u0005\r\u0004\u0003BA-\u0003?j!!a\u0017\u000b\u0007\u0005u3'\u0001\u0002j_&!\u0011\u0011MA.\u0005-Ie\u000e];u'R\u0014X-Y7\t\r\u0005E3\u00021\u0001k\u0003\r\u0011XO\u001c\u000b\u0007\u0003s\tI'!\u001c\t\r\u0005-D\u00021\u0001k\u0003)y'M[3di:\u000bW.\u001a\u0005\b\u0003_b\u0001\u0019AA9\u0003%\t'oZ;nK:$8\u000fE\u0003\u0002t\u0005e$ND\u0002+\u0003kJ1!a\u001e&\u0003\u001d\u0001\u0018mY6bO\u0016LA!a\u001f\u0002~\t\u00191+Z9\u000b\u0007\u0005]T%\u0001\u0005iCND7i\u001c3f)\t\t\u0019\tE\u0002+\u0003\u000bK1!a\"&\u0005\rIe\u000e^\u0001\u0007KF,\u0018\r\\:\u0015\u0007}\fi\t\u0003\u0005\u0002\u0010:\t\t\u00111\u0001J\u0003\rAH%M\u0001\u0010%&\u001c\u0007n\u00117bgNdu.\u00193feB\u00111\bE\n\u0003!\u0015$\"!a%\u0002\u001f]\u0014\u0018\r]\"mCN\u001cHj\\1eKJ$2AOAO\u0011\u0019\tyJ\u0005a\u0001_\u00051An\\1eKJ\f1#Y:D_:$X\r\u001f;%Kb$XM\\:j_:,B!!*\u0002,R!\u0011qUAY)\u0011\tI+!,\u0011\u0007\t\u000bY\u000bB\u0003E'\t\u0007Q\tC\u0004N'\u0011\u0005\r!a,\u0011\t)z\u0015\u0011\u0016\u0005\u0007\u0003g\u001b\u0002\u0019\u0001\u001e\u0002\u000b\u0011\"\b.[:\u00021Q\u0014\u0018\u0010V8M_\u0006$7\t\\1tg\u0012*\u0007\u0010^3og&|g.\u0006\u0003\u0002:\u0006\rG\u0003BA^\u0003\u000f$B!!0\u0002FB!!&VA`!\u0011Av,!1\u0011\u0007\t\u000b\u0019\rB\u0003E)\t\u0007A\rC\u0003j)\u0001\u0007!\u000e\u0003\u0004\u00024R\u0001\rAO\u0001\u001fiJLHk\\%oSRL\u0017\r\\5{K\u000ec\u0017m]:%Kb$XM\\:j_:,B!!4\u0002XR!\u0011qZAn)\u0011\t\t.!7\u0011\t)*\u00161\u001b\t\u00051~\u000b)\u000eE\u0002C\u0003/$Q\u0001R\u000bC\u0002\u0011DQ![\u000bA\u0002)Da!a-\u0016\u0001\u0004Q\u0014A\u0005;ss\u000ec\u0017m]:%Kb$XM\\:j_:,B!!9\u0002lR!\u00111]Ay)\u0019\t)/!<\u0002pB!!&VAt!\u0011Av,!;\u0011\u0007\t\u000bY\u000fB\u0003E-\t\u0007A\rC\u0003j-\u0001\u0007!\u000eC\u0003\u007f-\u0001\u0007q\u0010\u0003\u0004\u00024Z\u0001\rAO\u0001\u0011GJ,\u0017\r^3%Kb$XM\\:j_:$B!a>\u0002|R\u0019Q-!?\t\u000b%<\u0002\u0019\u00016\t\r\u0005Mv\u00031\u0001;+\u0011\tyP!\u0003\u0015\t\t\u0005!Q\u0003\u000b\u0007\u0005\u0007\u0011\tBa\u0005\u0015\t\t\u0015!q\u0002\u000b\u0005\u0005\u000f\u0011Y\u0001E\u0002C\u0005\u0013!Q\u0001\u0012\rC\u0002\u0011D\u0011\"!\u0007\u0019\u0003\u0003\u0005\u001dA!\u0004\u0011\r\u0005u\u0011q\u0004B\u0004\u0011\u001d\t)\u0003\u0007a\u0001\u0003OAQ!\u001b\rA\u0002)Dq!!\r\u0019\u0001\u0004\t\u0019\u0004\u0003\u0004\u00024b\u0001\rAO\u0001\u0015G2\f7o\u001d\"zi\u0016\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\tm!q\u0004\u000b\u0005\u0003\u0007\u0012i\u0002\u0003\u0004\u0002Re\u0001\rA\u001b\u0005\u0007\u0003gK\u0002\u0019\u0001\u001e\u0002/\rd\u0017m]:BgN#(/Z1nI\u0015DH/\u001a8tS>tG\u0003\u0002B\u0013\u0005S!B!a\u0016\u0003(!1\u0011\u0011\u000b\u000eA\u0002)Da!a-\u001b\u0001\u0004Q\u0014!\u0004:v]\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u00030\tUBCBA\u001d\u0005c\u0011\u0019\u0004\u0003\u0004\u0002lm\u0001\rA\u001b\u0005\b\u0003_Z\u0002\u0019AA9\u0011\u0019\t\u0019l\u0007a\u0001u\u0005\u0011\u0002.Y:i\u0007>$W\rJ3yi\u0016t7/[8o)\u0011\t\tIa\u000f\t\r\u0005MF\u00041\u0001;\u0003A)\u0017/^1mg\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0003B\t\u0015CcA@\u0003D!A\u0011qR\u000f\u0002\u0002\u0003\u0007\u0011\n\u0003\u0004\u00024v\u0001\rA\u000f"
)
public final class RichClassLoader {
   private final ClassLoader scala$reflect$internal$util$RichClassLoader$$self;

   public static boolean equals$extension(final ClassLoader $this, final Object x$1) {
      return RichClassLoader$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final ClassLoader $this) {
      RichClassLoader$ var10000 = RichClassLoader$.MODULE$;
      return $this.hashCode();
   }

   public static void run$extension(final ClassLoader $this, final String objectName, final Seq arguments) {
      RichClassLoader$.MODULE$.run$extension($this, objectName, arguments);
   }

   public static InputStream classAsStream$extension(final ClassLoader $this, final String className) {
      return RichClassLoader$.MODULE$.classAsStream$extension($this, className);
   }

   public static byte[] classBytes$extension(final ClassLoader $this, final String className) {
      return RichClassLoader$.MODULE$.classBytes$extension($this, className);
   }

   public static Object create$extension(final ClassLoader $this, final String path, final Function1 errorFn, final Seq args, final ClassTag evidence$1) {
      RichClassLoader$ var10000 = RichClassLoader$.MODULE$;

      try {
         Class create$extension_clazz = Class.forName(path, true, $this);
         if (!.MODULE$.classTag(evidence$1).runtimeClass().isAssignableFrom(create$extension_clazz)) {
            errorFn.apply(scala.collection.StringOps..MODULE$.stripMargin$extension((new StringBuilder(54)).append("Loader for ").append(.MODULE$.classTag(evidence$1)).append(":   [").append(ReflectionUtils$.MODULE$.show(.MODULE$.classTag(evidence$1).runtimeClass().getClassLoader())).append("]\n                    |Loader for ").append(create$extension_clazz.getName()).append(": [").append(ReflectionUtils$.MODULE$.show(create$extension_clazz.getClassLoader())).append("]").toString(), '|'));
            String create$extension_fail$1_msg = (new StringBuilder(8)).append("Not a ").append(.MODULE$.classTag(evidence$1)).append(": ").append(path).toString();
            Throwable create$extension_fail$1_error$1_e = new IllegalArgumentException(create$extension_fail$1_msg);
            errorFn.apply(create$extension_fail$1_msg);
            throw create$extension_fail$1_error$1_e;
         } else {
            Object create$extension_filter$extension_$this;
            label306: {
               label312: {
                  Object[] create$extension_refArrayOps_xs = create$extension_clazz.getConstructors();
                  Object var38 = null;
                  create$extension_filter$extension_$this = create$extension_refArrayOps_xs;
                  ArrayBuilder var54 = scala.collection.mutable.ArrayBuilder..MODULE$;
                  ClassTag create$extension_filter$extension_make_evidence$1 = scala.reflect.ClassTag..MODULE$.apply(create$extension_refArrayOps_xs.getClass().getComponentType());
                  Class var19 = create$extension_filter$extension_make_evidence$1.runtimeClass();
                  Class var55 = Byte.TYPE;
                  if (var55 == null) {
                     if (var19 == null) {
                        break label312;
                     }
                  } else if (var55.equals(var19)) {
                     break label312;
                  }

                  label313: {
                     var55 = Short.TYPE;
                     if (var55 == null) {
                        if (var19 == null) {
                           break label313;
                        }
                     } else if (var55.equals(var19)) {
                        break label313;
                     }

                     label314: {
                        var55 = Character.TYPE;
                        if (var55 == null) {
                           if (var19 == null) {
                              break label314;
                           }
                        } else if (var55.equals(var19)) {
                           break label314;
                        }

                        label315: {
                           var55 = Integer.TYPE;
                           if (var55 == null) {
                              if (var19 == null) {
                                 break label315;
                              }
                           } else if (var55.equals(var19)) {
                              break label315;
                           }

                           label316: {
                              var55 = Long.TYPE;
                              if (var55 == null) {
                                 if (var19 == null) {
                                    break label316;
                                 }
                              } else if (var55.equals(var19)) {
                                 break label316;
                              }

                              label317: {
                                 var55 = Float.TYPE;
                                 if (var55 == null) {
                                    if (var19 == null) {
                                       break label317;
                                    }
                                 } else if (var55.equals(var19)) {
                                    break label317;
                                 }

                                 label318: {
                                    var55 = Double.TYPE;
                                    if (var55 == null) {
                                       if (var19 == null) {
                                          break label318;
                                       }
                                    } else if (var55.equals(var19)) {
                                       break label318;
                                    }

                                    label319: {
                                       var55 = Boolean.TYPE;
                                       if (var55 == null) {
                                          if (var19 == null) {
                                             break label319;
                                          }
                                       } else if (var55.equals(var19)) {
                                          break label319;
                                       }

                                       label249: {
                                          var55 = Void.TYPE;
                                          if (var55 == null) {
                                             if (var19 == null) {
                                                break label249;
                                             }
                                          } else if (var55.equals(var19)) {
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

            Object var45 = null;
            Object var46 = null;
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
            Object var44 = null;
            Constructor[] create$extension_bySize = (Constructor[])var10000;
            if (create$extension_bySize.length == 0) {
               StringBuilder var81 = (new StringBuilder(33)).append("No constructor takes ");
               if (args == null) {
                  throw null;
               } else {
                  String create$extension_fail$1_msg = var81.append(args.length()).append(" parameters.").toString();
                  Throwable create$extension_fail$1_error$1_e = new IllegalArgumentException(create$extension_fail$1_msg);
                  errorFn.apply(create$extension_fail$1_msg);
                  throw create$extension_fail$1_error$1_e;
               }
            } else {
               label235: {
                  label321: {
                     ArrayBuilder var66 = scala.collection.mutable.ArrayBuilder..MODULE$;
                     ClassTag create$extension_filter$extension_make_evidence$1 = scala.reflect.ClassTag..MODULE$.apply(create$extension_bySize.getClass().getComponentType());
                     Class var24 = create$extension_filter$extension_make_evidence$1.runtimeClass();
                     Class var67 = Byte.TYPE;
                     if (var67 == null) {
                        if (var24 == null) {
                           break label321;
                        }
                     } else if (var67.equals(var24)) {
                        break label321;
                     }

                     label322: {
                        var67 = Short.TYPE;
                        if (var67 == null) {
                           if (var24 == null) {
                              break label322;
                           }
                        } else if (var67.equals(var24)) {
                           break label322;
                        }

                        label323: {
                           var67 = Character.TYPE;
                           if (var67 == null) {
                              if (var24 == null) {
                                 break label323;
                              }
                           } else if (var67.equals(var24)) {
                              break label323;
                           }

                           label324: {
                              var67 = Integer.TYPE;
                              if (var67 == null) {
                                 if (var24 == null) {
                                    break label324;
                                 }
                              } else if (var67.equals(var24)) {
                                 break label324;
                              }

                              label325: {
                                 var67 = Long.TYPE;
                                 if (var67 == null) {
                                    if (var24 == null) {
                                       break label325;
                                    }
                                 } else if (var67.equals(var24)) {
                                    break label325;
                                 }

                                 label326: {
                                    var67 = Float.TYPE;
                                    if (var67 == null) {
                                       if (var24 == null) {
                                          break label326;
                                       }
                                    } else if (var67.equals(var24)) {
                                       break label326;
                                    }

                                    label327: {
                                       var67 = Double.TYPE;
                                       if (var67 == null) {
                                          if (var24 == null) {
                                             break label327;
                                          }
                                       } else if (var67.equals(var24)) {
                                          break label327;
                                       }

                                       label328: {
                                          var67 = Boolean.TYPE;
                                          if (var67 == null) {
                                             if (var24 == null) {
                                                break label328;
                                             }
                                          } else if (var67.equals(var24)) {
                                             break label328;
                                          }

                                          label178: {
                                             var67 = Void.TYPE;
                                             if (var67 == null) {
                                                if (var24 == null) {
                                                   break label178;
                                                }
                                             } else if (var67.equals(var24)) {
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

               Object var49 = null;
               Object var50 = null;
               ArrayBuilder create$extension_filter$extension_res = var10000;

               for(int create$extension_filter$extension_i = 0; create$extension_filter$extension_i < create$extension_bySize.length; ++create$extension_filter$extension_i) {
                  Object create$extension_filter$extension_x = create$extension_bySize[create$extension_filter$extension_i];
                  if (RichClassLoader$.$anonfun$create$3(args, (Constructor)create$extension_filter$extension_x)) {
                     create$extension_filter$extension_res.addOne(create$extension_filter$extension_x);
                  }
               }

               var10000 = (RichClassLoader$)create$extension_filter$extension_res.result();
               create$extension_filter$extension_res = null;
               Object var48 = null;
               Constructor[] create$extension_maybes = (Constructor[])var10000;
               if (create$extension_maybes.length == 1) {
                  return ((Constructor)scala.collection.ArrayOps..MODULE$.head$extension(create$extension_maybes)).newInstance(scala.runtime.ScalaRunTime..MODULE$.toObjectArray(args.toArray(scala.reflect.ClassTag..MODULE$.Any())));
               } else if (create$extension_bySize.length == 1) {
                  StringBuilder var79 = (new StringBuilder(39)).append("One constructor takes ");
                  if (args == null) {
                     throw null;
                  } else {
                     var79 = var79.append(args.length()).append(" parameters but ");
                     ArraySeq.ofRef var83 = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.collect$extension(scala.collection.ArrayOps..MODULE$.zip$extension(((Constructor)scala.collection.ArrayOps..MODULE$.head$extension(create$extension_bySize)).getParameterTypes(), args), new Serializable() {
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
                     if (var83 == null) {
                        throw null;
                     } else {
                        AbstractIterable create$extension_mkString_this = var83;
                        String mkString_end = "";
                        String mkString_start = "";
                        String var84 = IterableOnceOps.mkString$(create$extension_mkString_this, mkString_start, create$extension_mkString_sep, mkString_end);
                        Object var51 = null;
                        Object var52 = null;
                        create$extension_mkString_this = null;
                        Object var40 = null;
                        String create$extension_fail$1_msg = var79.append(var84).append(".").toString();
                        Throwable create$extension_fail$1_error$1_e = new IllegalArgumentException(create$extension_fail$1_msg);
                        errorFn.apply(create$extension_fail$1_msg);
                        throw create$extension_fail$1_error$1_e;
                     }
                  }
               } else {
                  StringBuilder var78 = (new StringBuilder(37)).append("Constructor must accept arg list (");
                  IterableOnceOps var10001 = (IterableOnceOps)args.map(RichClassLoader$::$anonfun$create$5);
                  String create$extension_mkString_sep = ", ";
                  if (var10001 == null) {
                     throw null;
                  } else {
                     String var82 = var10001.mkString("", create$extension_mkString_sep, "");
                     Object var41 = null;
                     String create$extension_fail$1_msg = var78.append(var82).append("): ").append(path).toString();
                     Throwable create$extension_fail$1_error$1_e = new IllegalArgumentException(create$extension_fail$1_msg);
                     errorFn.apply(create$extension_fail$1_msg);
                     throw create$extension_fail$1_error$1_e;
                  }
               }
            }
         }
      } catch (Throwable var37) {
         if (var37 instanceof ClassNotFoundException) {
            ClassNotFoundException var9 = (ClassNotFoundException)var37;
            String create$extension_error$1_msg = (new StringBuilder(17)).append("Class not found: ").append(path).toString();
            errorFn.apply(create$extension_error$1_msg);
            throw var9;
         } else if (var37 instanceof LinkageError ? true : var37 instanceof ReflectiveOperationException) {
            String create$extension_error$1_msg = (new StringBuilder(29)).append("Unable to create instance: ").append(path).append(": ").append(var37.toString()).toString();
            errorFn.apply(create$extension_error$1_msg);
            throw var37;
         } else {
            throw var37;
         }
      }
   }

   public static Object create$extension(final ClassLoader $this, final String path) {
      return RichClassLoader$.MODULE$.create$extension($this, path);
   }

   public static Option tryClass$extension(final ClassLoader $this, final String path, final boolean initialize) {
      return RichClassLoader$.MODULE$.tryClass$extension($this, path, initialize);
   }

   public static Option tryToInitializeClass$extension(final ClassLoader $this, final String path) {
      return RichClassLoader$.MODULE$.tryClass$extension($this, path, true);
   }

   public static Option tryToLoadClass$extension(final ClassLoader $this, final String path) {
      return RichClassLoader$.MODULE$.tryClass$extension($this, path, false);
   }

   public static Object asContext$extension(final ClassLoader $this, final Function0 action) {
      RichClassLoader$ var10000 = RichClassLoader$.MODULE$;
      ClassLoader asContext$extension_saved = Thread.currentThread().getContextClassLoader();

      try {
         ScalaClassLoader$.MODULE$.setContext($this);
         var10000 = (RichClassLoader$)action.apply();
      } finally {
         ScalaClassLoader$.MODULE$.setContext(asContext$extension_saved);
      }

      return var10000;
   }

   public static ClassLoader wrapClassLoader(final ClassLoader loader) {
      RichClassLoader$ var10000 = RichClassLoader$.MODULE$;
      return loader;
   }

   public ClassLoader scala$reflect$internal$util$RichClassLoader$$self() {
      return this.scala$reflect$internal$util$RichClassLoader$$self;
   }

   public Object asContext(final Function0 action) {
      RichClassLoader$ var10000 = RichClassLoader$.MODULE$;
      ClassLoader asContext$extension_$this = this.scala$reflect$internal$util$RichClassLoader$$self();
      ClassLoader asContext$extension_saved = Thread.currentThread().getContextClassLoader();

      try {
         ScalaClassLoader$.MODULE$.setContext(asContext$extension_$this);
         var10000 = (RichClassLoader$)action.apply();
      } finally {
         ScalaClassLoader$.MODULE$.setContext(asContext$extension_saved);
      }

      return var10000;
   }

   public Option tryToLoadClass(final String path) {
      return RichClassLoader$.MODULE$.tryClass$extension(this.scala$reflect$internal$util$RichClassLoader$$self(), path, false);
   }

   public Option tryToInitializeClass(final String path) {
      return RichClassLoader$.MODULE$.tryClass$extension(this.scala$reflect$internal$util$RichClassLoader$$self(), path, true);
   }

   private Option tryClass(final String path, final boolean initialize) {
      return RichClassLoader$.MODULE$.tryClass$extension(this.scala$reflect$internal$util$RichClassLoader$$self(), path, initialize);
   }

   public Object create(final String path) {
      return RichClassLoader$.MODULE$.create$extension(this.scala$reflect$internal$util$RichClassLoader$$self(), path);
   }

   public Object create(final String path, final Function1 errorFn, final Seq args, final ClassTag evidence$1) {
      RichClassLoader$ var10000 = RichClassLoader$.MODULE$;
      ClassLoader create$extension_$this = this.scala$reflect$internal$util$RichClassLoader$$self();

      try {
         Class create$extension_clazz = Class.forName(path, true, create$extension_$this);
         if (!.MODULE$.classTag(evidence$1).runtimeClass().isAssignableFrom(create$extension_clazz)) {
            errorFn.apply(scala.collection.StringOps..MODULE$.stripMargin$extension((new StringBuilder(54)).append("Loader for ").append(.MODULE$.classTag(evidence$1)).append(":   [").append(ReflectionUtils$.MODULE$.show(.MODULE$.classTag(evidence$1).runtimeClass().getClassLoader())).append("]\n                    |Loader for ").append(create$extension_clazz.getName()).append(": [").append(ReflectionUtils$.MODULE$.show(create$extension_clazz.getClassLoader())).append("]").toString(), '|'));
            String create$extension_fail$1_msg = (new StringBuilder(8)).append("Not a ").append(.MODULE$.classTag(evidence$1)).append(": ").append(path).toString();
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

   public byte[] classBytes(final String className) {
      return RichClassLoader$.MODULE$.classBytes$extension(this.scala$reflect$internal$util$RichClassLoader$$self(), className);
   }

   public InputStream classAsStream(final String className) {
      return RichClassLoader$.MODULE$.classAsStream$extension(this.scala$reflect$internal$util$RichClassLoader$$self(), className);
   }

   public void run(final String objectName, final Seq arguments) {
      RichClassLoader$.MODULE$.run$extension(this.scala$reflect$internal$util$RichClassLoader$$self(), objectName, arguments);
   }

   public int hashCode() {
      RichClassLoader$ var10000 = RichClassLoader$.MODULE$;
      return this.scala$reflect$internal$util$RichClassLoader$$self().hashCode();
   }

   public boolean equals(final Object x$1) {
      return RichClassLoader$.MODULE$.equals$extension(this.scala$reflect$internal$util$RichClassLoader$$self(), x$1);
   }

   public RichClassLoader(final ClassLoader self) {
      this.scala$reflect$internal$util$RichClassLoader$$self = self;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
