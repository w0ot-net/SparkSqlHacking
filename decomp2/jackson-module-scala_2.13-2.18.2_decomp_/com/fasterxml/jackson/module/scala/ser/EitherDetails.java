package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005f\u0001B\u000f\u001f\t.B\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005\u0005\"AA\n\u0001BK\u0002\u0013\u0005Q\n\u0003\u0005V\u0001\tE\t\u0015!\u0003O\u0011!1\u0006A!f\u0001\n\u00039\u0006\u0002\u0003/\u0001\u0005#\u0005\u000b\u0011\u0002-\t\u000bu\u0003A\u0011\u00010\t\u000b\u0011\u0004A\u0011A3\t\u000f)\u0004\u0011\u0011!C\u0001W\"9q\u000eAI\u0001\n\u0003\u0001\bbB>\u0001#\u0003%\t\u0001 \u0005\b}\u0002\t\n\u0011\"\u0001\u0000\u0011%\t\u0019\u0001AA\u0001\n\u0003\n)\u0001C\u0005\u0002\u0018\u0001\t\t\u0011\"\u0001\u0002\u001a!I\u0011\u0011\u0005\u0001\u0002\u0002\u0013\u0005\u00111\u0005\u0005\n\u0003_\u0001\u0011\u0011!C!\u0003cA\u0011\"a\u0010\u0001\u0003\u0003%\t!!\u0011\t\u0013\u0005-\u0003!!A\u0005B\u00055\u0003\"CA)\u0001\u0005\u0005I\u0011IA*\u0011%\t)\u0006AA\u0001\n\u0003\n9\u0006C\u0005\u0002Z\u0001\t\t\u0011\"\u0011\u0002\\\u001dI\u0011q\f\u0010\u0002\u0002#%\u0011\u0011\r\u0004\t;y\t\t\u0011#\u0003\u0002d!1Ql\u0006C\u0001\u0003wB\u0011\"!\u0016\u0018\u0003\u0003%)%a\u0016\t\u0013\u0005ut#!A\u0005\u0002\u0006}\u0004\"CAD/\u0005\u0005I\u0011QAE\u0011%\t9jFA\u0001\n\u0013\tIJA\u0007FSRDWM\u001d#fi\u0006LGn\u001d\u0006\u0003?\u0001\n1a]3s\u0015\t\t#%A\u0003tG\u0006d\u0017M\u0003\u0002$I\u00051Qn\u001c3vY\u0016T!!\n\u0014\u0002\u000f)\f7m[:p]*\u0011q\u0005K\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011!K\u0001\u0004G>l7\u0001A\n\u0005\u00011\nD\u0007\u0005\u0002._5\taFC\u0001\"\u0013\t\u0001dF\u0001\u0004B]f\u0014VM\u001a\t\u0003[IJ!a\r\u0018\u0003\u000fA\u0013x\u000eZ;diB\u0011Q'\u0010\b\u0003mmr!a\u000e\u001e\u000e\u0003aR!!\u000f\u0016\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0013B\u0001\u001f/\u0003\u001d\u0001\u0018mY6bO\u0016L!AP \u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005qr\u0013a\u0001;zaV\t!\tE\u0002.\u0007\u0016K!\u0001\u0012\u0018\u0003\r=\u0003H/[8o!\t1\u0015*D\u0001H\u0015\tAE%\u0001\u0005eCR\f'-\u001b8e\u0013\tQuI\u0001\u0005KCZ\fG+\u001f9f\u0003\u0011!\u0018\u0010\u001d\u0011\u0002'Y\fG.^3UsB,7+\u001a:jC2L'0\u001a:\u0016\u00039\u00032!L\"P!\t\u00016+D\u0001R\u0015\t\u0011v)\u0001\u0005kg>tG/\u001f9f\u0013\t!\u0016K\u0001\bUsB,7+\u001a:jC2L'0\u001a:\u0002)Y\fG.^3UsB,7+\u001a:jC2L'0\u001a:!\u0003=1\u0018\r\\;f'\u0016\u0014\u0018.\u00197ju\u0016\u0014X#\u0001-\u0011\u00075\u001a\u0015\fE\u0002G52J!aW$\u0003\u001d)\u001bxN\\*fe&\fG.\u001b>fe\u0006\u0001b/\u00197vKN+'/[1mSj,'\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\t}\u000b'm\u0019\t\u0003A\u0002i\u0011A\b\u0005\u0006\u0001\u001e\u0001\rA\u0011\u0005\u0006\u0019\u001e\u0001\rA\u0014\u0005\u0006-\u001e\u0001\r\u0001W\u0001\ro&$\b\u000eS1oI2,'o\u001d\u000b\u0004?\u001aD\u0007\"B4\t\u0001\u0004q\u0015A\u0002<ug>\u0003H\u000fC\u0003j\u0011\u0001\u0007\u0001,\u0001\u0004tKJ|\u0005\u000f^\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003`Y6t\u0007b\u0002!\n!\u0003\u0005\rA\u0011\u0005\b\u0019&\u0001\n\u00111\u0001O\u0011\u001d1\u0016\u0002%AA\u0002a\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001rU\t\u0011%oK\u0001t!\t!\u00180D\u0001v\u0015\t1x/A\u0005v]\u000eDWmY6fI*\u0011\u0001PL\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001>v\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005i(F\u0001(s\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"!!\u0001+\u0005a\u0013\u0018!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002\bA!\u0011\u0011BA\n\u001b\t\tYA\u0003\u0003\u0002\u000e\u0005=\u0011\u0001\u00027b]\u001eT!!!\u0005\u0002\t)\fg/Y\u0005\u0005\u0003+\tYA\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u00037\u00012!LA\u000f\u0013\r\tyB\f\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003K\tY\u0003E\u0002.\u0003OI1!!\u000b/\u0005\r\te.\u001f\u0005\n\u0003[y\u0011\u0011!a\u0001\u00037\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u001a!\u0019\t)$a\u000f\u0002&5\u0011\u0011q\u0007\u0006\u0004\u0003sq\u0013AC2pY2,7\r^5p]&!\u0011QHA\u001c\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005\r\u0013\u0011\n\t\u0004[\u0005\u0015\u0013bAA$]\t9!i\\8mK\u0006t\u0007\"CA\u0017#\u0005\u0005\t\u0019AA\u0013\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005\u001d\u0011q\n\u0005\n\u0003[\u0011\u0012\u0011!a\u0001\u00037\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u00037\t\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003\u000f\ta!Z9vC2\u001cH\u0003BA\"\u0003;B\u0011\"!\f\u0016\u0003\u0003\u0005\r!!\n\u0002\u001b\u0015KG\u000f[3s\t\u0016$\u0018-\u001b7t!\t\u0001wcE\u0003\u0018\u0003K\n\t\b\u0005\u0005\u0002h\u00055$I\u0014-`\u001b\t\tIGC\u0002\u0002l9\nqA];oi&lW-\u0003\u0003\u0002p\u0005%$!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ogA!\u00111OA=\u001b\t\t)H\u0003\u0003\u0002x\u0005=\u0011AA5p\u0013\rq\u0014Q\u000f\u000b\u0003\u0003C\nQ!\u00199qYf$raXAA\u0003\u0007\u000b)\tC\u0003A5\u0001\u0007!\tC\u0003M5\u0001\u0007a\nC\u0003W5\u0001\u0007\u0001,A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005-\u00151\u0013\t\u0005[\r\u000bi\t\u0005\u0004.\u0003\u001f\u0013e\nW\u0005\u0004\u0003#s#A\u0002+va2,7\u0007\u0003\u0005\u0002\u0016n\t\t\u00111\u0001`\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u00037\u0003B!!\u0003\u0002\u001e&!\u0011qTA\u0006\u0005\u0019y%M[3di\u0002"
)
public class EitherDetails implements Product, Serializable {
   private final Option typ;
   private final Option valueTypeSerializer;
   private final Option valueSerializer;

   public static Option unapply(final EitherDetails x$0) {
      return EitherDetails$.MODULE$.unapply(x$0);
   }

   public static EitherDetails apply(final Option typ, final Option valueTypeSerializer, final Option valueSerializer) {
      return EitherDetails$.MODULE$.apply(typ, valueTypeSerializer, valueSerializer);
   }

   public static Function1 tupled() {
      return EitherDetails$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return EitherDetails$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Option typ() {
      return this.typ;
   }

   public Option valueTypeSerializer() {
      return this.valueTypeSerializer;
   }

   public Option valueSerializer() {
      return this.valueSerializer;
   }

   public EitherDetails withHandlers(final Option vtsOpt, final Option serOpt) {
      ObjectRef newType = ObjectRef.create(this.typ());
      vtsOpt.foreach((vts) -> {
         $anonfun$withHandlers$1(newType, vts);
         return BoxedUnit.UNIT;
      });
      serOpt.foreach((ser) -> {
         $anonfun$withHandlers$3(newType, ser);
         return BoxedUnit.UNIT;
      });
      return this.copy((Option)newType.elem, this.copy$default$2(), this.copy$default$3());
   }

   public EitherDetails copy(final Option typ, final Option valueTypeSerializer, final Option valueSerializer) {
      return new EitherDetails(typ, valueTypeSerializer, valueSerializer);
   }

   public Option copy$default$1() {
      return this.typ();
   }

   public Option copy$default$2() {
      return this.valueTypeSerializer();
   }

   public Option copy$default$3() {
      return this.valueSerializer();
   }

   public String productPrefix() {
      return "EitherDetails";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.typ();
         case 1:
            return this.valueTypeSerializer();
         case 2:
            return this.valueSerializer();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof EitherDetails;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "typ";
         case 1:
            return "valueTypeSerializer";
         case 2:
            return "valueSerializer";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof EitherDetails) {
               label56: {
                  EitherDetails var4 = (EitherDetails)x$1;
                  Option var10000 = this.typ();
                  Option var5 = var4.typ();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  var10000 = this.valueTypeSerializer();
                  Option var6 = var4.valueTypeSerializer();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label56;
                  }

                  var10000 = this.valueSerializer();
                  Option var7 = var4.valueSerializer();
                  if (var10000 == null) {
                     if (var7 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var7)) {
                     break label56;
                  }

                  if (var4.canEqual(this)) {
                     break label63;
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   // $FF: synthetic method
   public static final void $anonfun$withHandlers$1(final ObjectRef newType$1, final TypeSerializer vts) {
      newType$1.elem = ((Option)newType$1.elem).map((x$1) -> x$1.withTypeHandler(vts));
   }

   // $FF: synthetic method
   public static final void $anonfun$withHandlers$3(final ObjectRef newType$1, final JsonSerializer ser) {
      newType$1.elem = ((Option)newType$1.elem).map((x$2) -> x$2.withValueHandler(ser));
   }

   public EitherDetails(final Option typ, final Option valueTypeSerializer, final Option valueSerializer) {
      this.typ = typ;
      this.valueTypeSerializer = valueTypeSerializer;
      this.valueSerializer = valueSerializer;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
