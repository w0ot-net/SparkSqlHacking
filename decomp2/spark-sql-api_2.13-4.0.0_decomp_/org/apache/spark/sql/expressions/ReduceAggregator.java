package org.apache.spark.sql.expressions;

import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders$;
import scala.Function2;
import scala.Tuple2;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}a!\u0002\n\u0014\u0001Ui\u0002\u0002\u0003\u001d\u0001\u0005\u0003\u0005\u000b\u0011B\u001d\t\u0011q\u0002!1!Q\u0001\fuBQ!\u0011\u0001\u0005\u0002\tC\u0001b\u0012\u0001\t\u0006\u0004%I\u0001\u0013\u0005\b\u001b\u0002\u0011\r\u0011\"\u0003O\u0011\u0019y\u0005\u0001)A\u0005_!)\u0001\u000b\u0001C!#\")!\u000b\u0001C!'\")Q\u000b\u0001C!\u0011\")a\u000b\u0001C!/\")A\f\u0001C!;\")!\r\u0001C!G\u001e1An\u0005E\u0001+54aAE\n\t\u0002Uq\u0007\"B!\u000f\t\u0003Q\b\"B>\u000f\t\u0003a\b\"CA\b\u001d\u0005\u0005I\u0011BA\t\u0005A\u0011V\rZ;dK\u0006;wM]3hCR|'O\u0003\u0002\u0015+\u0005YQ\r\u001f9sKN\u001c\u0018n\u001c8t\u0015\t1r#A\u0002tc2T!\u0001G\r\u0002\u000bM\u0004\u0018M]6\u000b\u0005iY\u0012AB1qC\u000eDWMC\u0001\u001d\u0003\ry'oZ\u000b\u0003=\u0015\u001a\"\u0001A\u0010\u0011\u000b\u0001\n3EM\u0012\u000e\u0003MI!AI\n\u0003\u0015\u0005;wM]3hCR|'\u000f\u0005\u0002%K1\u0001A!\u0002\u0014\u0001\u0005\u0004A#!\u0001+\u0004\u0001E\u0011\u0011f\f\t\u0003U5j\u0011a\u000b\u0006\u0002Y\u0005)1oY1mC&\u0011af\u000b\u0002\b\u001d>$\b.\u001b8h!\tQ\u0003'\u0003\u00022W\t\u0019\u0011I\\=\u0011\t)\u001aTgI\u0005\u0003i-\u0012a\u0001V;qY\u0016\u0014\u0004C\u0001\u00167\u0013\t94FA\u0004C_>dW-\u00198\u0002\t\u0019,hn\u0019\t\u0006Ui\u001a3eI\u0005\u0003w-\u0012\u0011BR;oGRLwN\u001c\u001a\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002?\u007f\rj\u0011!F\u0005\u0003\u0001V\u0011q!\u00128d_\u0012,'/\u0001\u0004=S:LGO\u0010\u000b\u0003\u0007\u001a#\"\u0001R#\u0011\u0007\u0001\u00021\u0005C\u0003=\u0007\u0001\u000fQ\bC\u00039\u0007\u0001\u0007\u0011(A\u0004f]\u000e|G-\u001a:\u0016\u0003uB#\u0001\u0002&\u0011\u0005)Z\u0015B\u0001',\u0005%!(/\u00198tS\u0016tG/A\u0003`u\u0016\u0014x.F\u00010\u0003\u0019y&0\u001a:pA\u0005!!0\u001a:p+\u0005\u0011\u0014!\u00042vM\u001a,'/\u00128d_\u0012,'/F\u0001U!\rqtHM\u0001\u000e_V$\b/\u001e;F]\u000e|G-\u001a:\u0002\rI,G-^2f)\r\u0011\u0004L\u0017\u0005\u00063*\u0001\rAM\u0001\u0002E\")1L\u0003a\u0001G\u0005\t\u0011-A\u0003nKJ<W\rF\u00023=\u0002DQaX\u0006A\u0002I\n!AY\u0019\t\u000b\u0005\\\u0001\u0019\u0001\u001a\u0002\u0005\t\u0014\u0014A\u00024j]&\u001c\b\u000e\u0006\u0002$I\")Q\r\u0004a\u0001e\u0005I!/\u001a3vGRLwN\u001c\u0015\u0005\u0001\u001dT7\u000e\u0005\u0002+Q&\u0011\u0011n\u000b\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0002B\u0012(YOH&b\u0006U\u0001\u0011%\u0016$WoY3BO\u001e\u0014XmZ1u_J\u0004\"\u0001\t\b\u0014\u00079y'\u000f\u0005\u0002+a&\u0011\u0011o\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005MDX\"\u0001;\u000b\u0005U4\u0018AA5p\u0015\u00059\u0018\u0001\u00026bm\u0006L!!\u001f;\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u00035\fQ!\u00199qYf,2!`A\u0002)\rq\u00181\u0002\u000b\u0004\u007f\u0006\u0015\u0001\u0003\u0002\u0011\u0001\u0003\u0003\u00012\u0001JA\u0002\t\u00151\u0003C1\u0001)\u0011%\t9\u0001EA\u0001\u0002\b\tI!\u0001\u0006fm&$WM\\2fII\u0002BAP \u0002\u0002!1\u0011Q\u0002\tA\u0002=\f\u0011AZ\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003'\u0001B!!\u0006\u0002\u001c5\u0011\u0011q\u0003\u0006\u0004\u000331\u0018\u0001\u00027b]\u001eLA!!\b\u0002\u0018\t1qJ\u00196fGR\u0004"
)
public class ReduceAggregator extends Aggregator {
   private static final long serialVersionUID = 5066084382969966160L;
   private transient Encoder encoder;
   private final Function2 func;
   private final Encoder evidence$1;
   private final Object _zero;
   private transient volatile boolean bitmap$trans$0;

   public static ReduceAggregator apply(final Object f, final Encoder evidence$2) {
      return ReduceAggregator$.MODULE$.apply(f, evidence$2);
   }

   private Encoder encoder$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this.encoder = (Encoder).MODULE$.implicitly(this.evidence$1);
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.encoder;
   }

   private Encoder encoder() {
      return !this.bitmap$trans$0 ? this.encoder$lzycompute() : this.encoder;
   }

   private Object _zero() {
      return this._zero;
   }

   public Tuple2 zero() {
      return new Tuple2(BoxesRunTime.boxToBoolean(false), this._zero());
   }

   public Encoder bufferEncoder() {
      return Encoders$.MODULE$.tuple(Encoders$.MODULE$.scalaBoolean(), this.encoder());
   }

   public Encoder outputEncoder() {
      return this.encoder();
   }

   public Tuple2 reduce(final Tuple2 b, final Object a) {
      return b._1$mcZ$sp() ? new Tuple2(BoxesRunTime.boxToBoolean(true), this.func.apply(b._2(), a)) : new Tuple2(BoxesRunTime.boxToBoolean(true), a);
   }

   public Tuple2 merge(final Tuple2 b1, final Tuple2 b2) {
      if (!b1._1$mcZ$sp()) {
         return b2;
      } else {
         return !b2._1$mcZ$sp() ? b1 : new Tuple2(BoxesRunTime.boxToBoolean(true), this.func.apply(b1._2(), b2._2()));
      }
   }

   public Object finish(final Tuple2 reduction) {
      if (!reduction._1$mcZ$sp()) {
         throw org.apache.spark.SparkException..MODULE$.internalError("ReduceAggregator requires at least one input row");
      } else {
         return reduction._2();
      }
   }

   public ReduceAggregator(final Function2 func, final Encoder evidence$1) {
      Object var18;
      label96: {
         label98: {
            this.func = func;
            this.evidence$1 = evidence$1;
            super();
            Class var4 = this.encoder().clsTag().runtimeClass();
            Class var10001 = Boolean.TYPE;
            if (var10001 == null) {
               if (var4 == null) {
                  break label98;
               }
            } else if (var10001.equals(var4)) {
               break label98;
            }

            label99: {
               var10001 = Byte.TYPE;
               if (var10001 == null) {
                  if (var4 == null) {
                     break label99;
                  }
               } else if (var10001.equals(var4)) {
                  break label99;
               }

               label100: {
                  var10001 = Short.TYPE;
                  if (var10001 == null) {
                     if (var4 == null) {
                        break label100;
                     }
                  } else if (var10001.equals(var4)) {
                     break label100;
                  }

                  label101: {
                     var10001 = Integer.TYPE;
                     if (var10001 == null) {
                        if (var4 == null) {
                           break label101;
                        }
                     } else if (var10001.equals(var4)) {
                        break label101;
                     }

                     label102: {
                        var10001 = Long.TYPE;
                        if (var10001 == null) {
                           if (var4 == null) {
                              break label102;
                           }
                        } else if (var10001.equals(var4)) {
                           break label102;
                        }

                        label103: {
                           var10001 = Float.TYPE;
                           if (var10001 == null) {
                              if (var4 == null) {
                                 break label103;
                              }
                           } else if (var10001.equals(var4)) {
                              break label103;
                           }

                           label53: {
                              var10001 = Double.TYPE;
                              if (var10001 == null) {
                                 if (var4 == null) {
                                    break label53;
                                 }
                              } else if (var10001.equals(var4)) {
                                 break label53;
                              }

                              var18 = null;
                              break label96;
                           }

                           var18 = BoxesRunTime.boxToDouble((double)0.0F);
                           break label96;
                        }

                        var18 = BoxesRunTime.boxToFloat(0.0F);
                        break label96;
                     }

                     var18 = BoxesRunTime.boxToLong(0L);
                     break label96;
                  }

                  var18 = BoxesRunTime.boxToInteger(0);
                  break label96;
               }

               var18 = BoxesRunTime.boxToShort((short)0);
               break label96;
            }

            var18 = BoxesRunTime.boxToByte((byte)0);
            break label96;
         }

         var18 = BoxesRunTime.boxToBoolean(false);
      }

      this._zero = var18;
   }
}
