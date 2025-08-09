package org.json4s;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.Seq;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005EeaB\u000b\u0017!\u0003\r\ta\u0007\u0005\u0006E\u0001!\ta\t\u0004\u0007O\u0001\u0001\u000b\u0011\u0003\u0015\t\u0011e\u0012!\u0011!Q\u0001\niBQ\u0001\u0011\u0002\u0005\u0002\u0005CQ!\u0012\u0002\u0005\u0002\u0019Cq!\u0013\u0001C\u0002\u0013\r!\nC\u0004P\u0001\t\u0007I1\u0001)\t\u000fU\u0003!\u0019!C\u0002-\"91\f\u0001b\u0001\n\u0007a\u0006bB1\u0001\u0005\u0004%\u0019A\u0019\u0005\ba\u0002\u0011\r\u0011b\u0001r\u0011\u001d1\bA1A\u0005\u0004]Dq!a\u0001\u0001\t\u0007\t)\u0001C\u0004\u0002\u001c\u0001!\u0019!!\b\t\u000f\u0005e\u0002\u0001b\u0001\u0002<!I\u0011q\r\u0001C\u0002\u0013\r\u0011\u0011\u000e\u0005\b\u0003[\u0002A1AA8\u000f\u001d\t\u0019I\u0006E\u0001\u0003\u000b3a!\u0006\f\t\u0002\u0005\u001d\u0005B\u0002!\u0014\t\u0003\tyI\u0001\bEK\u001a\fW\u000f\u001c;Xe&$XM]:\u000b\u0005]A\u0012A\u00026t_:$4OC\u0001\u001a\u0003\ry'oZ\u0002\u0001'\t\u0001A\u0004\u0005\u0002\u001eA5\taDC\u0001 \u0003\u0015\u00198-\u00197b\u0013\t\tcD\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u0011\u0002\"!H\u0013\n\u0005\u0019r\"\u0001B+oSR\u0014\u0011aV\u000b\u0003SA\u001a2A\u0001\u000f+!\rYCFL\u0007\u0002-%\u0011QF\u0006\u0002\u0007/JLG/\u001a:\u0011\u0005=\u0002D\u0002\u0001\u0003\u0007c\tA)\u0019\u0001\u001a\u0003\u0003Q\u000b\"a\r\u001c\u0011\u0005u!\u0014BA\u001b\u001f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!H\u001c\n\u0005ar\"aA!os\u0006\u0011aM\u001c\t\u0005;mrS(\u0003\u0002==\tIa)\u001e8di&|g.\r\t\u0003WyJ!a\u0010\f\u0003\r)3\u0016\r\\;f\u0003\u0019a\u0014N\\5u}Q\u0011!\t\u0012\t\u0004\u0007\nqS\"\u0001\u0001\t\u000be\"\u0001\u0019\u0001\u001e\u0002\u000b]\u0014\u0018\u000e^3\u0015\u0005u:\u0005\"\u0002%\u0006\u0001\u0004q\u0013aA8cU\u0006I\u0011J\u001c;Xe&$XM]\u000b\u0002\u0017B\u00191\u0006\f'\u0011\u0005ui\u0015B\u0001(\u001f\u0005\rIe\u000e^\u0001\u000b\u0005f$Xm\u0016:ji\u0016\u0014X#A)\u0011\u0007-b#\u000b\u0005\u0002\u001e'&\u0011AK\b\u0002\u0005\u0005f$X-A\u0006TQ>\u0014Ho\u0016:ji\u0016\u0014X#A,\u0011\u0007-b\u0003\f\u0005\u0002\u001e3&\u0011!L\b\u0002\u0006'\"|'\u000f^\u0001\u000b\u0019>twm\u0016:ji\u0016\u0014X#A/\u0011\u0007-bc\f\u0005\u0002\u001e?&\u0011\u0001M\b\u0002\u0005\u0019>tw-\u0001\u0007CS\u001eLe\u000e^,sSR,'/F\u0001d!\rYC\u0006\u001a\t\u0003K6t!AZ6\u000f\u0005\u001dTW\"\u00015\u000b\u0005%T\u0012A\u0002\u001fs_>$h(C\u0001 \u0013\tag$A\u0004qC\u000e\\\u0017mZ3\n\u00059|'A\u0002\"jO&sGO\u0003\u0002m=\u0005i!i\\8mK\u0006twK]5uKJ,\u0012A\u001d\t\u0004W1\u001a\bCA\u000fu\u0013\t)hDA\u0004C_>dW-\u00198\u0002\u0019M#(/\u001b8h/JLG/\u001a:\u0016\u0003a\u00042a\u000b\u0017z!\tQhP\u0004\u0002|yB\u0011qMH\u0005\u0003{z\ta\u0001\u0015:fI\u00164\u0017bA@\u0002\u0002\t11\u000b\u001e:j]\u001eT!! \u0010\u0002\u0017\u0005\u0014(/Y=Xe&$XM]\u000b\u0005\u0003\u000f\t\u0019\u0002\u0006\u0003\u0002\n\u0005U\u0001\u0003B\u0016-\u0003\u0017\u0001R!HA\u0007\u0003#I1!a\u0004\u001f\u0005\u0015\t%O]1z!\ry\u00131\u0003\u0003\u0006c5\u0011\rA\r\u0005\b\u0003/i\u00019AA\r\u0003-1\u0018\r\\;f/JLG/\u001a:\u0011\t-b\u0013\u0011C\u0001\ng\u0016\fxK]5uKJ,B!a\b\u00022Q!\u0011\u0011EA\u001a!\u0011YC&a\t\u0011\r\u0005\u0015\u00121FA\u0018\u001b\t\t9CC\u0002\u0002*y\t!bY8mY\u0016\u001cG/[8o\u0013\u0011\ti#a\n\u0003\u0007M+\u0017\u000fE\u00020\u0003c!Q!\r\bC\u0002IB\u0011\"!\u000e\u000f\u0003\u0003\u0005\u001d!a\u000e\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0003,Y\u0005=\u0012!C7ba^\u0013\u0018\u000e^3s+\u0019\ti$a\u0014\u0002VQ1\u0011qHA-\u0003G\u0002Ba\u000b\u0017\u0002BAA\u00111IA%\u0003\u001b\n\u0019&\u0004\u0002\u0002F)!\u0011qIA\u0014\u0003%IW.\\;uC\ndW-\u0003\u0003\u0002L\u0005\u0015#aA'baB\u0019q&a\u0014\u0005\r\u0005EsB1\u00013\u0005\u0005Y\u0005cA\u0018\u0002V\u00111\u0011qK\bC\u0002I\u0012\u0011A\u0016\u0005\b\u00037z\u00019AA/\u0003%YW-_,sSR,'\u000fE\u0003,\u0003?\ni%C\u0002\u0002bY\u0011QBS:p].+\u0017p\u0016:ji\u0016\u0014\bbBA\f\u001f\u0001\u000f\u0011Q\r\t\u0005W1\n\u0019&\u0001\u0007K-\u0006dW/Z,sSR,'/\u0006\u0002\u0002lA\u00191\u0006L\u001f\u0002\u0019=\u0003H/[8o/JLG/\u001a:\u0016\t\u0005E\u0014Q\u0010\u000b\u0005\u0003g\ny\b\u0005\u0003,Y\u0005U\u0004#B\u000f\u0002x\u0005m\u0014bAA==\t1q\n\u001d;j_:\u00042aLA?\t\u0015\t\u0014C1\u00013\u0011\u001d\t9\"\u0005a\u0002\u0003\u0003\u0003Ba\u000b\u0017\u0002|\u0005qA)\u001a4bk2$xK]5uKJ\u001c\bCA\u0016\u0014'\u0011\u0019B$!#\u0011\u0007-\nY)C\u0002\u0002\u000eZ\u0011Q\u0002R8vE2,wK]5uKJ\u001cHCAAC\u0001"
)
public interface DefaultWriters {
   static Writer BigDecimalWriter() {
      return DefaultWriters$.MODULE$.BigDecimalWriter();
   }

   static Writer DoubleWriter() {
      return DefaultWriters$.MODULE$.DoubleWriter();
   }

   static Writer FloatWriter() {
      return DefaultWriters$.MODULE$.FloatWriter();
   }

   void org$json4s$DefaultWriters$_setter_$IntWriter_$eq(final Writer x$1);

   void org$json4s$DefaultWriters$_setter_$ByteWriter_$eq(final Writer x$1);

   void org$json4s$DefaultWriters$_setter_$ShortWriter_$eq(final Writer x$1);

   void org$json4s$DefaultWriters$_setter_$LongWriter_$eq(final Writer x$1);

   void org$json4s$DefaultWriters$_setter_$BigIntWriter_$eq(final Writer x$1);

   void org$json4s$DefaultWriters$_setter_$BooleanWriter_$eq(final Writer x$1);

   void org$json4s$DefaultWriters$_setter_$StringWriter_$eq(final Writer x$1);

   void org$json4s$DefaultWriters$_setter_$JValueWriter_$eq(final Writer x$1);

   Writer IntWriter();

   Writer ByteWriter();

   Writer ShortWriter();

   Writer LongWriter();

   Writer BigIntWriter();

   Writer BooleanWriter();

   Writer StringWriter();

   // $FF: synthetic method
   static Writer arrayWriter$(final DefaultWriters $this, final Writer valueWriter) {
      return $this.arrayWriter(valueWriter);
   }

   default Writer arrayWriter(final Writer valueWriter) {
      return new Writer(valueWriter) {
         private final Writer valueWriter$1;

         public Writer contramap(final Function1 f) {
            return Writer.contramap$(this, f);
         }

         public JValue write(final Object obj) {
            return new JArray(.MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.genericArrayOps(obj), (x$6) -> this.valueWriter$1.write(x$6), scala.reflect.ClassTag..MODULE$.apply(JValue.class))).toList());
         }

         public {
            this.valueWriter$1 = valueWriter$1;
            Writer.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Writer seqWriter$(final DefaultWriters $this, final Writer evidence$1) {
      return $this.seqWriter(evidence$1);
   }

   default Writer seqWriter(final Writer evidence$1) {
      return new Writer(evidence$1) {
         private final Writer evidence$1$1;

         public Writer contramap(final Function1 f) {
            return Writer.contramap$(this, f);
         }

         public JArray write(final Seq a) {
            return new JArray(((IterableOnceOps)a.map((x$7) -> Writer$.MODULE$.apply(this.evidence$1$1).write(x$7))).toList());
         }

         public {
            this.evidence$1$1 = evidence$1$1;
            Writer.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Writer mapWriter$(final DefaultWriters $this, final JsonKeyWriter keyWriter, final Writer valueWriter) {
      return $this.mapWriter(keyWriter, valueWriter);
   }

   default Writer mapWriter(final JsonKeyWriter keyWriter, final Writer valueWriter) {
      return new Writer(keyWriter, valueWriter) {
         private final JsonKeyWriter keyWriter$1;
         private final Writer valueWriter$2;

         public Writer contramap(final Function1 f) {
            return Writer.contramap$(this, f);
         }

         public JValue write(final Map obj) {
            return new JObject(obj.map((x0$1) -> {
               if (x0$1 != null) {
                  Object k = x0$1._1();
                  Object v = x0$1._2();
                  Tuple2 var2 = scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(this.keyWriter$1.write(k)), this.valueWriter$2.write(v));
                  return var2;
               } else {
                  throw new MatchError(x0$1);
               }
            }).toList());
         }

         public {
            this.keyWriter$1 = keyWriter$1;
            this.valueWriter$2 = valueWriter$2;
            Writer.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   Writer JValueWriter();

   // $FF: synthetic method
   static Writer OptionWriter$(final DefaultWriters $this, final Writer valueWriter) {
      return $this.OptionWriter(valueWriter);
   }

   default Writer OptionWriter(final Writer valueWriter) {
      return new Writer(valueWriter) {
         private final Writer valueWriter$3;

         public Writer contramap(final Function1 f) {
            return Writer.contramap$(this, f);
         }

         public JValue write(final Option obj) {
            Object var2;
            if (obj instanceof Some) {
               Some var4 = (Some)obj;
               Object v = var4.value();
               var2 = this.valueWriter$3.write(v);
            } else {
               var2 = JNull$.MODULE$;
            }

            return (JValue)var2;
         }

         public {
            this.valueWriter$3 = valueWriter$3;
            Writer.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static JInt $anonfun$IntWriter$1(final int x$1) {
      return new JInt(scala.math.BigInt..MODULE$.int2bigInt(x$1));
   }

   // $FF: synthetic method
   static JInt $anonfun$ByteWriter$1(final byte x) {
      return new JInt(scala.math.BigInt..MODULE$.long2bigInt((long)x));
   }

   // $FF: synthetic method
   static JInt $anonfun$ShortWriter$1(final short x) {
      return new JInt(scala.math.BigInt..MODULE$.long2bigInt((long)x));
   }

   // $FF: synthetic method
   static JInt $anonfun$LongWriter$1(final long x$2) {
      return new JInt(scala.math.BigInt..MODULE$.long2bigInt(x$2));
   }

   // $FF: synthetic method
   static JBool $anonfun$BooleanWriter$1(final boolean x$4) {
      return JBool$.MODULE$.apply(x$4);
   }

   static void $init$(final DefaultWriters $this) {
      $this.org$json4s$DefaultWriters$_setter_$IntWriter_$eq($this.new W((x$1) -> $anonfun$IntWriter$1(BoxesRunTime.unboxToInt(x$1))));
      $this.org$json4s$DefaultWriters$_setter_$ByteWriter_$eq($this.new W((x) -> $anonfun$ByteWriter$1(BoxesRunTime.unboxToByte(x))));
      $this.org$json4s$DefaultWriters$_setter_$ShortWriter_$eq($this.new W((x) -> $anonfun$ShortWriter$1(BoxesRunTime.unboxToShort(x))));
      $this.org$json4s$DefaultWriters$_setter_$LongWriter_$eq($this.new W((x$2) -> $anonfun$LongWriter$1(BoxesRunTime.unboxToLong(x$2))));
      $this.org$json4s$DefaultWriters$_setter_$BigIntWriter_$eq($this.new W((x$3) -> new JInt(x$3)));
      $this.org$json4s$DefaultWriters$_setter_$BooleanWriter_$eq($this.new W((x$4) -> $anonfun$BooleanWriter$1(BoxesRunTime.unboxToBoolean(x$4))));
      $this.org$json4s$DefaultWriters$_setter_$StringWriter_$eq($this.new W((x$5) -> new JString(x$5)));
      $this.org$json4s$DefaultWriters$_setter_$JValueWriter_$eq($this.new W((x) -> (JValue).MODULE$.identity(x)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class W implements Writer {
      private final Function1 fn;
      // $FF: synthetic field
      public final DefaultWriters $outer;

      public Writer contramap(final Function1 f) {
         return Writer.contramap$(this, f);
      }

      public JValue write(final Object obj) {
         return (JValue)this.fn.apply(obj);
      }

      // $FF: synthetic method
      public DefaultWriters org$json4s$DefaultWriters$W$$$outer() {
         return this.$outer;
      }

      public W(final Function1 fn) {
         this.fn = fn;
         if (DefaultWriters.this == null) {
            throw null;
         } else {
            this.$outer = DefaultWriters.this;
            super();
            Writer.$init$(this);
         }
      }
   }
}
