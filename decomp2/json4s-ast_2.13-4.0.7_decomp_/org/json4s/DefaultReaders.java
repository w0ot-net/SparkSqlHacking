package org.json4s;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.List;
import scala.collection.mutable.Builder;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mu!B\u000b\u0017\u0011\u0003Yb!B\u000f\u0017\u0011\u0003q\u0002bBAH\u0003\u0011\u0005\u0011\u0011\u0013\u0004\b;Y\u0001\n1!\u0001'\u0011\u0015Q3\u0001\"\u0001,\u0011\u001dy3A1A\u0005\u0004ABqaN\u0002C\u0002\u0013\r\u0001\bC\u0004G\u0007\t\u0007I1A$\t\u000f1\u001b!\u0019!C\u0002\u001b\"9!k\u0001b\u0001\n\u0007\u0019\u0006b\u0002-\u0004\u0005\u0004%\u0019!\u0017\u0005\b=\u000e\u0011\r\u0011b\u0001`\u0011\u001d!7A1A\u0005\u0004\u0015DqA[\u0002C\u0002\u0013\r1\u000eC\u0004q\u0007\t\u0007I1A9\t\u000bm\u001cA1\u0001?\t\u000f\u0005-2\u0001b\u0001\u0002.!I\u0011QK\u0002C\u0002\u0013\r\u0011q\u000b\u0005\n\u0003C\u001a!\u0019!C\u0002\u0003GB\u0011\"!\u001c\u0004\u0005\u0004%\u0019!a\u001c\t\u000f\u0005e4\u0001b\u0001\u0002|\u0005qA)\u001a4bk2$(+Z1eKJ\u001c(BA\f\u0019\u0003\u0019Q7o\u001c85g*\t\u0011$A\u0002pe\u001e\u001c\u0001\u0001\u0005\u0002\u001d\u00035\taC\u0001\bEK\u001a\fW\u000f\u001c;SK\u0006$WM]:\u0014\u0007\u0005yR\u0005\u0005\u0002!G5\t\u0011EC\u0001#\u0003\u0015\u00198-\u00197b\u0013\t!\u0013E\u0001\u0004B]f\u0014VM\u001a\t\u00039\r\u00192aA\u0010(!\ta\u0002&\u0003\u0002*-\tyA)\u001a4bk2$(+Z1eKJ\u001c\b'\u0001\u0004%S:LG\u000f\n\u000b\u0002YA\u0011\u0001%L\u0005\u0003]\u0005\u0012A!\u00168ji\u0006I\u0011J\u001c;SK\u0006$WM]\u000b\u0002cA\u0019AD\r\u001b\n\u0005M2\"A\u0002*fC\u0012,'\u000f\u0005\u0002!k%\u0011a'\t\u0002\u0004\u0013:$\u0018\u0001\u0004\"jO&sGOU3bI\u0016\u0014X#A\u001d\u0011\u0007q\u0011$\b\u0005\u0002<\u0007:\u0011A(\u0011\b\u0003{\u0001k\u0011A\u0010\u0006\u0003\u007fi\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0012\n\u0005\t\u000b\u0013a\u00029bG.\fw-Z\u0005\u0003\t\u0016\u0013aAQ5h\u0013:$(B\u0001\"\"\u0003)auN\\4SK\u0006$WM]\u000b\u0002\u0011B\u0019ADM%\u0011\u0005\u0001R\u0015BA&\"\u0005\u0011auN\\4\u0002\u0017MCwN\u001d;SK\u0006$WM]\u000b\u0002\u001dB\u0019ADM(\u0011\u0005\u0001\u0002\u0016BA)\"\u0005\u0015\u0019\u0006n\u001c:u\u0003)\u0011\u0015\u0010^3SK\u0006$WM]\u000b\u0002)B\u0019ADM+\u0011\u0005\u00012\u0016BA,\"\u0005\u0011\u0011\u0015\u0010^3\u0002\u0017\u0019cw.\u0019;SK\u0006$WM]\u000b\u00025B\u0019ADM.\u0011\u0005\u0001b\u0016BA/\"\u0005\u00151En\\1u\u00031!u.\u001e2mKJ+\u0017\rZ3s+\u0005\u0001\u0007c\u0001\u000f3CB\u0011\u0001EY\u0005\u0003G\u0006\u0012a\u0001R8vE2,\u0017\u0001\u0005\"jO\u0012+7-[7bYJ+\u0017\rZ3s+\u00051\u0007c\u0001\u000f3OB\u00111\b[\u0005\u0003S\u0016\u0013!BQ5h\t\u0016\u001c\u0017.\\1m\u00035\u0011un\u001c7fC:\u0014V-\u00193feV\tA\u000eE\u0002\u001de5\u0004\"\u0001\t8\n\u0005=\f#a\u0002\"p_2,\u0017M\\\u0001\r'R\u0014\u0018N\\4SK\u0006$WM]\u000b\u0002eB\u0019ADM:\u0011\u0005QDhBA;w!\ti\u0014%\u0003\u0002xC\u00051\u0001K]3eK\u001aL!!\u001f>\u0003\rM#(/\u001b8h\u0015\t9\u0018%A\u0005nCB\u0014V-\u00193feV\u0019Q0a\u0005\u0015\u0007y\f)\u0003E\u0002\u001de}\u0004r!!\u0001\u0002\fM\fy!\u0004\u0002\u0002\u0004)!\u0011QAA\u0004\u0003%IW.\\;uC\ndWMC\u0002\u0002\n\u0005\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\ti!a\u0001\u0003\u00075\u000b\u0007\u000f\u0005\u0003\u0002\u0012\u0005MA\u0002\u0001\u0003\b\u0003+y!\u0019AA\f\u0005\u00051\u0016\u0003BA\r\u0003?\u00012\u0001IA\u000e\u0013\r\ti\"\t\u0002\b\u001d>$\b.\u001b8h!\r\u0001\u0013\u0011E\u0005\u0004\u0003G\t#aA!os\"9\u0011qE\bA\u0004\u0005%\u0012a\u0003<bYV,'+Z1eKJ\u0004B\u0001\b\u001a\u0002\u0010\u0005Y\u0011M\u001d:bsJ+\u0017\rZ3s+\u0011\ty#a\u000f\u0015\r\u0005E\u0012qHA(!\u0011a\"'a\r\u0011\u000b\u0001\n)$!\u000f\n\u0007\u0005]\u0012EA\u0003BeJ\f\u0017\u0010\u0005\u0003\u0002\u0012\u0005mBaBA\u001f!\t\u0007\u0011q\u0003\u0002\u0002)\"I\u0011\u0011\t\t\u0002\u0002\u0003\u000f\u00111I\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004CBA#\u0003\u0017\nI$\u0004\u0002\u0002H)\u0019\u0011\u0011J\u0011\u0002\u000fI,g\r\\3di&!\u0011QJA$\u0005!\u0019E.Y:t)\u0006<\u0007\"CA)!\u0005\u0005\t9AA*\u0003))g/\u001b3f]\u000e,GE\r\t\u00059I\nI$\u0001\u0007K-\u0006dW/\u001a*fC\u0012,'/\u0006\u0002\u0002ZA!ADMA.!\ra\u0012QL\u0005\u0004\u0003?2\"A\u0002&WC2,X-A\u0007K\u001f\nTWm\u0019;SK\u0006$WM]\u000b\u0003\u0003K\u0002B\u0001\b\u001a\u0002hA\u0019A$!\u001b\n\u0007\u0005-dCA\u0004K\u001f\nTWm\u0019;\u0002\u0019)\u000b%O]1z%\u0016\fG-\u001a:\u0016\u0005\u0005E\u0004\u0003\u0002\u000f3\u0003g\u00022\u0001HA;\u0013\r\t9H\u0006\u0002\u0007\u0015\u0006\u0013(/Y=\u0002\u0019=\u0003H/[8o%\u0016\fG-\u001a:\u0016\t\u0005u\u0014\u0011\u0012\u000b\u0005\u0003\u007f\nY\t\u0005\u0003\u001de\u0005\u0005\u0005#\u0002\u0011\u0002\u0004\u0006\u001d\u0015bAACC\t1q\n\u001d;j_:\u0004B!!\u0005\u0002\n\u00129\u0011Q\b\u000bC\u0002\u0005]\u0001bBA\u0014)\u0001\u000f\u0011Q\u0012\t\u00059I\n9)\u0001\u0004=S:LGO\u0010\u000b\u00027\u0001"
)
public interface DefaultReaders extends DefaultReaders0 {
   void org$json4s$DefaultReaders$_setter_$IntReader_$eq(final Reader x$1);

   void org$json4s$DefaultReaders$_setter_$BigIntReader_$eq(final Reader x$1);

   void org$json4s$DefaultReaders$_setter_$LongReader_$eq(final Reader x$1);

   void org$json4s$DefaultReaders$_setter_$ShortReader_$eq(final Reader x$1);

   void org$json4s$DefaultReaders$_setter_$ByteReader_$eq(final Reader x$1);

   void org$json4s$DefaultReaders$_setter_$FloatReader_$eq(final Reader x$1);

   void org$json4s$DefaultReaders$_setter_$DoubleReader_$eq(final Reader x$1);

   void org$json4s$DefaultReaders$_setter_$BigDecimalReader_$eq(final Reader x$1);

   void org$json4s$DefaultReaders$_setter_$BooleanReader_$eq(final Reader x$1);

   void org$json4s$DefaultReaders$_setter_$StringReader_$eq(final Reader x$1);

   void org$json4s$DefaultReaders$_setter_$JValueReader_$eq(final Reader x$1);

   void org$json4s$DefaultReaders$_setter_$JObjectReader_$eq(final Reader x$1);

   void org$json4s$DefaultReaders$_setter_$JArrayReader_$eq(final Reader x$1);

   Reader IntReader();

   Reader BigIntReader();

   Reader LongReader();

   Reader ShortReader();

   Reader ByteReader();

   Reader FloatReader();

   Reader DoubleReader();

   Reader BigDecimalReader();

   Reader BooleanReader();

   Reader StringReader();

   // $FF: synthetic method
   static Reader mapReader$(final DefaultReaders $this, final Reader valueReader) {
      return $this.mapReader(valueReader);
   }

   default Reader mapReader(final Reader valueReader) {
      return Reader$.MODULE$.from((x0$1) -> {
         Object var2;
         if (x0$1 instanceof JObject) {
            JObject var4 = (JObject)x0$1;
            List values = var4.obj();
            Builder rights = .MODULE$.Map().newBuilder();
            Builder lefts = scala.package..MODULE$.List().newBuilder();
            values.foreach((x0$2) -> {
               if (x0$2 != null) {
                  Tuple2 var7 = JField$.MODULE$.unapply(x0$2);
                  if (!SomeValue$.MODULE$.isEmpty$extension(var7)) {
                     String k = (String)var7._1();
                     JValue v = (JValue)var7._2();
                     Either var10 = valueReader.readEither(v);
                     Builder var5;
                     if (var10 instanceof Right) {
                        Right var11 = (Right)var10;
                        Object a = var11.value();
                        var5 = (Builder)rights.$plus$eq(new Tuple2(k, a));
                     } else {
                        if (!(var10 instanceof Left)) {
                           throw new MatchError(var10);
                        }

                        Left var13 = (Left)var10;
                        MappingException a = (MappingException)var13.value();
                        var5 = (Builder)lefts.$plus$eq(a);
                     }

                     return var5;
                  }
               }

               throw new MatchError(x0$2);
            });
            List l = (List)lefts.result();
            var2 = l.isEmpty() ? scala.package..MODULE$.Right().apply(rights.result()) : scala.package..MODULE$.Left().apply(new MappingException.Multi(l));
         } else {
            var2 = scala.package..MODULE$.Left().apply(new MappingException((new StringBuilder(22)).append("Can't convert ").append(x0$1).append(" to Map.").toString()));
         }

         return (Either)var2;
      });
   }

   // $FF: synthetic method
   static Reader arrayReader$(final DefaultReaders $this, final ClassTag evidence$1, final Reader evidence$2) {
      return $this.arrayReader(evidence$1, evidence$2);
   }

   default Reader arrayReader(final ClassTag evidence$1, final Reader evidence$2) {
      return Reader$.MODULE$.apply(this.iterableReader(scala.collection.immutable.List..MODULE$.iterableFactory(), evidence$2)).map((x$1) -> x$1.toArray(evidence$1));
   }

   Reader JValueReader();

   Reader JObjectReader();

   Reader JArrayReader();

   // $FF: synthetic method
   static Reader OptionReader$(final DefaultReaders $this, final Reader valueReader) {
      return $this.OptionReader(valueReader);
   }

   default Reader OptionReader(final Reader valueReader) {
      return new Reader(valueReader) {
         private final Reader valueReader$2;

         public Reader map(final Function1 f) {
            return Reader.map$(this, f);
         }

         public Either readEither(final JValue value) {
            Either var3 = this.valueReader$2.readEither(value);
            Right var2;
            if (var3 instanceof Right) {
               Right var4 = (Right)var3;
               Object x = var4.value();
               var2 = scala.package..MODULE$.Right().apply(new Some(x));
            } else {
               if (!(var3 instanceof Left)) {
                  throw new MatchError(var3);
               }

               var2 = scala.package..MODULE$.Right().apply(scala.None..MODULE$);
            }

            return var2;
         }

         public {
            this.valueReader$2 = valueReader$2;
            Reader.$init$(this);
         }
      };
   }

   static void $init$(final DefaultReaders $this) {
      $this.org$json4s$DefaultReaders$_setter_$IntReader_$eq(Reader$.MODULE$.fromPartialFunction(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final JValue x1, final Function1 default) {
            Object var3;
            if (x1 instanceof JInt) {
               JInt var5 = (JInt)x1;
               BigInt x = var5.num();
               var3 = BoxesRunTime.boxToInteger(x.intValue());
            } else if (x1 instanceof JLong) {
               JLong var7 = (JLong)x1;
               long x = var7.num();
               var3 = BoxesRunTime.boxToInteger((int)x);
            } else if (x1 instanceof JDouble) {
               JDouble var10 = (JDouble)x1;
               double x = var10.num();
               var3 = BoxesRunTime.boxToInteger((int)x);
            } else if (x1 instanceof JDecimal) {
               JDecimal var13 = (JDecimal)x1;
               BigDecimal x = var13.num();
               var3 = BoxesRunTime.boxToInteger(x.intValue());
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final JValue x1) {
            boolean var2;
            if (x1 instanceof JInt) {
               var2 = true;
            } else if (x1 instanceof JLong) {
               var2 = true;
            } else if (x1 instanceof JDouble) {
               var2 = true;
            } else if (x1 instanceof JDecimal) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }
      }, (x) -> new MappingException((new StringBuilder(22)).append("Can't convert ").append(x).append(" to Int.").toString())));
      $this.org$json4s$DefaultReaders$_setter_$BigIntReader_$eq(Reader$.MODULE$.fromPartialFunction(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final JValue x1, final Function1 default) {
            Object var3;
            if (x1 instanceof JInt) {
               JInt var5 = (JInt)x1;
               BigInt x = var5.num();
               var3 = x;
            } else if (x1 instanceof JLong) {
               JLong var7 = (JLong)x1;
               long x = var7.num();
               var3 = scala.package..MODULE$.BigInt().apply(x);
            } else if (x1 instanceof JDouble) {
               JDouble var10 = (JDouble)x1;
               double x = var10.num();
               var3 = scala.package..MODULE$.BigInt().apply((long)x);
            } else if (x1 instanceof JDecimal) {
               JDecimal var13 = (JDecimal)x1;
               BigDecimal x = var13.num();
               var3 = x.toBigInt();
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final JValue x1) {
            boolean var2;
            if (x1 instanceof JInt) {
               var2 = true;
            } else if (x1 instanceof JLong) {
               var2 = true;
            } else if (x1 instanceof JDouble) {
               var2 = true;
            } else if (x1 instanceof JDecimal) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }
      }, (x) -> new MappingException((new StringBuilder(25)).append("Can't convert ").append(x).append(" to BigInt.").toString())));
      $this.org$json4s$DefaultReaders$_setter_$LongReader_$eq(Reader$.MODULE$.fromPartialFunction(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final JValue x1, final Function1 default) {
            Object var3;
            if (x1 instanceof JInt) {
               JInt var5 = (JInt)x1;
               BigInt x = var5.num();
               var3 = BoxesRunTime.boxToLong(x.longValue());
            } else if (x1 instanceof JLong) {
               JLong var7 = (JLong)x1;
               long x = var7.num();
               var3 = BoxesRunTime.boxToLong(x);
            } else if (x1 instanceof JDouble) {
               JDouble var10 = (JDouble)x1;
               double x = var10.num();
               var3 = BoxesRunTime.boxToLong((long)x);
            } else if (x1 instanceof JDecimal) {
               JDecimal var13 = (JDecimal)x1;
               BigDecimal x = var13.num();
               var3 = BoxesRunTime.boxToLong(x.longValue());
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final JValue x1) {
            boolean var2;
            if (x1 instanceof JInt) {
               var2 = true;
            } else if (x1 instanceof JLong) {
               var2 = true;
            } else if (x1 instanceof JDouble) {
               var2 = true;
            } else if (x1 instanceof JDecimal) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }
      }, (x) -> new MappingException((new StringBuilder(23)).append("Can't convert ").append(x).append(" to Long.").toString())));
      $this.org$json4s$DefaultReaders$_setter_$ShortReader_$eq(Reader$.MODULE$.fromPartialFunction(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final JValue x1, final Function1 default) {
            Object var3;
            if (x1 instanceof JInt) {
               JInt var5 = (JInt)x1;
               BigInt x = var5.num();
               var3 = BoxesRunTime.boxToShort(x.shortValue());
            } else if (x1 instanceof JLong) {
               JLong var7 = (JLong)x1;
               long x = var7.num();
               var3 = BoxesRunTime.boxToShort((short)((int)x));
            } else if (x1 instanceof JDouble) {
               JDouble var10 = (JDouble)x1;
               double x = var10.num();
               var3 = BoxesRunTime.boxToShort((short)((int)x));
            } else if (x1 instanceof JDecimal) {
               JDecimal var13 = (JDecimal)x1;
               BigDecimal x = var13.num();
               var3 = BoxesRunTime.boxToShort(x.shortValue());
            } else if (JNull$.MODULE$.equals(x1)) {
               var3 = BoxesRunTime.boxToShort((short)0);
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final JValue x1) {
            boolean var2;
            if (x1 instanceof JInt) {
               var2 = true;
            } else if (x1 instanceof JLong) {
               var2 = true;
            } else if (x1 instanceof JDouble) {
               var2 = true;
            } else if (x1 instanceof JDecimal) {
               var2 = true;
            } else if (JNull$.MODULE$.equals(x1)) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }
      }, (x) -> new MappingException((new StringBuilder(24)).append("Can't convert ").append(x).append(" to Short.").toString())));
      $this.org$json4s$DefaultReaders$_setter_$ByteReader_$eq(Reader$.MODULE$.fromPartialFunction(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final JValue x1, final Function1 default) {
            Object var3;
            if (x1 instanceof JInt) {
               JInt var5 = (JInt)x1;
               BigInt x = var5.num();
               var3 = BoxesRunTime.boxToByte(x.byteValue());
            } else if (x1 instanceof JLong) {
               JLong var7 = (JLong)x1;
               long x = var7.num();
               var3 = BoxesRunTime.boxToByte((byte)((int)x));
            } else if (x1 instanceof JDouble) {
               JDouble var10 = (JDouble)x1;
               double x = var10.num();
               var3 = BoxesRunTime.boxToByte((byte)((int)x));
            } else if (x1 instanceof JDecimal) {
               JDecimal var13 = (JDecimal)x1;
               BigDecimal x = var13.num();
               var3 = BoxesRunTime.boxToByte(x.byteValue());
            } else if (JNull$.MODULE$.equals(x1)) {
               var3 = BoxesRunTime.boxToByte((byte)0);
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final JValue x1) {
            boolean var2;
            if (x1 instanceof JInt) {
               var2 = true;
            } else if (x1 instanceof JLong) {
               var2 = true;
            } else if (x1 instanceof JDouble) {
               var2 = true;
            } else if (x1 instanceof JDecimal) {
               var2 = true;
            } else if (JNull$.MODULE$.equals(x1)) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }
      }, (x) -> new MappingException((new StringBuilder(23)).append("Can't convert ").append(x).append(" to Byte.").toString())));
      $this.org$json4s$DefaultReaders$_setter_$FloatReader_$eq(Reader$.MODULE$.fromPartialFunction(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final JValue x1, final Function1 default) {
            Object var3;
            if (x1 instanceof JInt) {
               JInt var5 = (JInt)x1;
               BigInt x = var5.num();
               var3 = BoxesRunTime.boxToFloat(x.floatValue());
            } else if (x1 instanceof JLong) {
               JLong var7 = (JLong)x1;
               long x = var7.num();
               var3 = BoxesRunTime.boxToFloat((float)x);
            } else if (x1 instanceof JDouble) {
               JDouble var10 = (JDouble)x1;
               double x = var10.num();
               var3 = BoxesRunTime.boxToFloat((float)x);
            } else if (x1 instanceof JDecimal) {
               JDecimal var13 = (JDecimal)x1;
               BigDecimal x = var13.num();
               var3 = BoxesRunTime.boxToFloat(x.floatValue());
            } else if (JNull$.MODULE$.equals(x1)) {
               var3 = BoxesRunTime.boxToFloat(0.0F);
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final JValue x1) {
            boolean var2;
            if (x1 instanceof JInt) {
               var2 = true;
            } else if (x1 instanceof JLong) {
               var2 = true;
            } else if (x1 instanceof JDouble) {
               var2 = true;
            } else if (x1 instanceof JDecimal) {
               var2 = true;
            } else if (JNull$.MODULE$.equals(x1)) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }
      }, (x) -> new MappingException((new StringBuilder(24)).append("Can't convert ").append(x).append(" to Float.").toString())));
      $this.org$json4s$DefaultReaders$_setter_$DoubleReader_$eq(Reader$.MODULE$.fromPartialFunction(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final JValue x1, final Function1 default) {
            Object var3;
            if (x1 instanceof JInt) {
               JInt var5 = (JInt)x1;
               BigInt x = var5.num();
               var3 = BoxesRunTime.boxToDouble(x.doubleValue());
            } else if (x1 instanceof JLong) {
               JLong var7 = (JLong)x1;
               long x = var7.num();
               var3 = BoxesRunTime.boxToDouble((double)x);
            } else if (x1 instanceof JDouble) {
               JDouble var10 = (JDouble)x1;
               double x = var10.num();
               var3 = BoxesRunTime.boxToDouble(x);
            } else if (x1 instanceof JDecimal) {
               JDecimal var13 = (JDecimal)x1;
               BigDecimal x = var13.num();
               var3 = BoxesRunTime.boxToDouble(x.doubleValue());
            } else if (JNull$.MODULE$.equals(x1)) {
               var3 = BoxesRunTime.boxToDouble((double)0.0F);
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final JValue x1) {
            boolean var2;
            if (x1 instanceof JInt) {
               var2 = true;
            } else if (x1 instanceof JLong) {
               var2 = true;
            } else if (x1 instanceof JDouble) {
               var2 = true;
            } else if (x1 instanceof JDecimal) {
               var2 = true;
            } else if (JNull$.MODULE$.equals(x1)) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }
      }, (x) -> new MappingException((new StringBuilder(25)).append("Can't convert ").append(x).append(" to Double.").toString())));
      $this.org$json4s$DefaultReaders$_setter_$BigDecimalReader_$eq(Reader$.MODULE$.fromPartialFunction(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final JValue x1, final Function1 default) {
            Object var3;
            if (x1 instanceof JInt) {
               JInt var5 = (JInt)x1;
               BigInt x = var5.num();
               var3 = scala.package..MODULE$.BigDecimal().apply(x);
            } else if (x1 instanceof JLong) {
               JLong var7 = (JLong)x1;
               long x = var7.num();
               var3 = scala.package..MODULE$.BigDecimal().apply(x);
            } else if (x1 instanceof JDouble) {
               JDouble var10 = (JDouble)x1;
               double x = var10.num();
               var3 = scala.package..MODULE$.BigDecimal().apply(x);
            } else if (x1 instanceof JDecimal) {
               JDecimal var13 = (JDecimal)x1;
               BigDecimal x = var13.num();
               var3 = x;
            } else if (JNull$.MODULE$.equals(x1)) {
               var3 = scala.math.BigDecimal..MODULE$.int2bigDecimal(0);
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final JValue x1) {
            boolean var2;
            if (x1 instanceof JInt) {
               var2 = true;
            } else if (x1 instanceof JLong) {
               var2 = true;
            } else if (x1 instanceof JDouble) {
               var2 = true;
            } else if (x1 instanceof JDecimal) {
               var2 = true;
            } else if (JNull$.MODULE$.equals(x1)) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }
      }, (x) -> new MappingException((new StringBuilder(29)).append("Can't convert ").append(x).append(" to BigDecimal.").toString())));
      $this.org$json4s$DefaultReaders$_setter_$BooleanReader_$eq(Reader$.MODULE$.fromPartialFunction(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final JValue x1, final Function1 default) {
            Object var3;
            if (x1 instanceof JBool) {
               JBool var5 = (JBool)x1;
               boolean v = var5.value();
               var3 = BoxesRunTime.boxToBoolean(v);
            } else if (JNull$.MODULE$.equals(x1)) {
               var3 = BoxesRunTime.boxToBoolean(false);
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final JValue x1) {
            boolean var2;
            if (x1 instanceof JBool) {
               var2 = true;
            } else if (JNull$.MODULE$.equals(x1)) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }
      }, (x) -> new MappingException((new StringBuilder(26)).append("Can't convert ").append(x).append(" to Boolean.").toString())));
      $this.org$json4s$DefaultReaders$_setter_$StringReader_$eq(Reader$.MODULE$.fromPartialFunction(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final JValue x1, final Function1 default) {
            Object var3;
            if (x1 instanceof JInt) {
               JInt var5 = (JInt)x1;
               BigInt x = var5.num();
               var3 = x.toString();
            } else if (x1 instanceof JLong) {
               JLong var7 = (JLong)x1;
               long x = var7.num();
               var3 = Long.toString(x);
            } else if (x1 instanceof JDecimal) {
               JDecimal var10 = (JDecimal)x1;
               BigDecimal x = var10.num();
               var3 = x.toString();
            } else if (x1 instanceof JDouble) {
               JDouble var12 = (JDouble)x1;
               double x = var12.num();
               var3 = Double.toString(x);
            } else if (x1 instanceof JBool) {
               JBool var15 = (JBool)x1;
               boolean x = var15.value();
               var3 = Boolean.toString(x);
            } else if (x1 instanceof JString) {
               JString var17 = (JString)x1;
               String s = var17.s();
               var3 = s;
            } else if (JNull$.MODULE$.equals(x1)) {
               var3 = null;
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final JValue x1) {
            boolean var2;
            if (x1 instanceof JInt) {
               var2 = true;
            } else if (x1 instanceof JLong) {
               var2 = true;
            } else if (x1 instanceof JDecimal) {
               var2 = true;
            } else if (x1 instanceof JDouble) {
               var2 = true;
            } else if (x1 instanceof JBool) {
               var2 = true;
            } else if (x1 instanceof JString) {
               var2 = true;
            } else if (JNull$.MODULE$.equals(x1)) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }
      }, (x) -> new MappingException((new StringBuilder(25)).append("Can't convert ").append(x).append(" to String.").toString())));
      $this.org$json4s$DefaultReaders$_setter_$JValueReader_$eq(new Reader() {
         public Reader map(final Function1 f) {
            return Reader.map$(this, f);
         }

         private JValue read(final JValue value) {
            return value;
         }

         public Right readEither(final JValue value) {
            return scala.package..MODULE$.Right().apply(value);
         }

         public {
            Reader.$init$(this);
         }
      });
      $this.org$json4s$DefaultReaders$_setter_$JObjectReader_$eq(Reader$.MODULE$.from((x0$1) -> {
         Object var1;
         if (x0$1 instanceof JObject) {
            JObject var3 = (JObject)x0$1;
            var1 = scala.package..MODULE$.Right().apply(var3);
         } else {
            var1 = scala.package..MODULE$.Left().apply(new MappingException((new StringBuilder(27)).append("JObject expected, but got ").append(x0$1).append(".").toString()));
         }

         return (Either)var1;
      }));
      $this.org$json4s$DefaultReaders$_setter_$JArrayReader_$eq(Reader$.MODULE$.from((x0$1) -> {
         Object var1;
         if (x0$1 instanceof JArray) {
            JArray var3 = (JArray)x0$1;
            var1 = scala.package..MODULE$.Right().apply(var3);
         } else {
            var1 = scala.package..MODULE$.Left().apply(new MappingException((new StringBuilder(26)).append("JArray expected, but got ").append(x0$1).append(".").toString()));
         }

         return (Either)var1;
      }));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
