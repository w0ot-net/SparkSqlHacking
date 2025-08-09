package org.json4s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u001dqA\u0002\u001e<\u0011\u0003YtH\u0002\u0004Bw!\u00051H\u0011\u0005\u0006\u0013\u0006!\ta\u0013\u0005\u0007\u0019\u0006\u0001\u000b\u0011B'\t\u0011A\u000b\u0001\u0019!C\u0001wEC\u0001\"V\u0001A\u0002\u0013\u00051H\u0016\u0005\u00079\u0006\u0001\u000b\u0015\u0002*\t\ru\u000b\u0001\u0015!\u0003S\u0011\u0019q\u0016\u0001)A\u0005?\"11.\u0001Q\u0001\n1Daa]\u0001\u0005\u0002m\"\b\"B;\u0002\t\u00031\bBB<\u0002A\u0013%\u0001\u0010C\u0003z\u0003\u0011\u0005!P\u0002\u0004~\u0003\u0001\u0006iI \u0005\u000b\u0003;q!Q3A\u0005\u0002\u0005}\u0001BCA\u0017\u001d\tE\t\u0015!\u0003\u0002\"!1\u0011J\u0004C\u0001\u0003_A\u0011\"a\u000e\u000f\u0003\u0003%\t!!\u000f\t\u0013\u0005ub\"%A\u0005\u0002\u0005}\u0002\"CA+\u001d\u0005\u0005I\u0011IA,\u0011!\t)GDA\u0001\n\u0003\t\u0006\"CA4\u001d\u0005\u0005I\u0011AA5\u0011%\t\u0019HDA\u0001\n\u0003\n)\bC\u0005\u0002\u0004:\t\t\u0011\"\u0001\u0002\u0006\"I\u0011\u0011\u0012\b\u0002\u0002\u0013\u0005\u00131\u0012\u0005\n\u0003\u001fs\u0011\u0011!C!\u0003#C\u0011\"a%\u000f\u0003\u0003%\t%!&\t\u0013\u0005]e\"!A\u0005B\u0005eu!CAO\u0003\u0005\u0005\u000b\u0012BAP\r!i\u0018!!Q\t\n\u0005\u0005\u0006BB%\u001f\t\u0003\tI\fC\u0005\u0002\u0014z\t\t\u0011\"\u0012\u0002\u0016\"AQOHA\u0001\n\u0003\u000bY\fC\u0005\u0002@z\t\t\u0011\"!\u0002B\"I\u0011Q\u001a\u0010\u0002\u0002\u0013%\u0011q\u001a\u0004\t\u0003/\f\u0001\u0015!$\u0002Z\"Q\u0011Q\u0004\u0013\u0003\u0016\u0004%\t!a\b\t\u0015\u00055BE!E!\u0002\u0013\t\t\u0003\u0003\u0004JI\u0011\u0005\u00111\u001c\u0005\n\u0003o!\u0013\u0011!C\u0001\u0003CD\u0011\"!\u0010%#\u0003%\t!a\u0010\t\u0013\u0005UC%!A\u0005B\u0005]\u0003\u0002CA3I\u0005\u0005I\u0011A)\t\u0013\u0005\u001dD%!A\u0005\u0002\u0005\u0015\b\"CA:I\u0005\u0005I\u0011IA;\u0011%\t\u0019\tJA\u0001\n\u0003\tI\u000fC\u0005\u0002\n\u0012\n\t\u0011\"\u0011\u0002n\"I\u0011q\u0012\u0013\u0002\u0002\u0013\u0005\u0013\u0011\u0013\u0005\n\u0003'#\u0013\u0011!C!\u0003+C\u0011\"a&%\u0003\u0003%\t%!=\b\u0013\u0005U\u0018!!Q\t\n\u0005]h!CAl\u0003\u0005\u0005\u000b\u0012BA}\u0011\u0019IE\u0007\"\u0001\u0002~\"I\u00111\u0013\u001b\u0002\u0002\u0013\u0015\u0013Q\u0013\u0005\tkR\n\t\u0011\"!\u0002\u0000\"I\u0011q\u0018\u001b\u0002\u0002\u0013\u0005%1\u0001\u0005\n\u0003\u001b$\u0014\u0011!C\u0005\u0003\u001f\f\u0001bU3h[\u0016tGo\u001d\u0006\u0003yu\naA[:p]R\u001a(\"\u0001 \u0002\u0007=\u0014x\r\u0005\u0002A\u00035\t1H\u0001\u0005TK\u001elWM\u001c;t'\t\t1\t\u0005\u0002E\u000f6\tQIC\u0001G\u0003\u0015\u00198-\u00197b\u0013\tAUI\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tq(\u0001\bf]\u0006\u0014G.Z*fO6,g\u000e^:\u0011\u0005\u0011s\u0015BA(F\u0005\u001d\u0011un\u001c7fC:\f1b]3h[\u0016tGoU5{KV\t!\u000b\u0005\u0002E'&\u0011A+\u0012\u0002\u0004\u0013:$\u0018aD:fO6,g\u000e^*ju\u0016|F%Z9\u0015\u0005]S\u0006C\u0001#Y\u0013\tIVI\u0001\u0003V]&$\bbB.\u0006\u0003\u0003\u0005\rAU\u0001\u0004q\u0012\n\u0014\u0001D:fO6,g\u000e^*ju\u0016\u0004\u0013\u0001E7bq:+Xn\u00144TK\u001elWM\u001c;t\u00031\u0019XmZ7f]R\u001cu.\u001e8u!\t\u0001\u0017.D\u0001b\u0015\t\u00117-\u0001\u0004bi>l\u0017n\u0019\u0006\u0003I\u0016\f!bY8oGV\u0014(/\u001a8u\u0015\t1w-\u0001\u0003vi&d'\"\u00015\u0002\t)\fg/Y\u0005\u0003U\u0006\u0014Q\"\u0011;p[&\u001c\u0017J\u001c;fO\u0016\u0014\u0018\u0001C:fO6,g\u000e^:\u0011\u00075t\u0007/D\u0001d\u0013\ty7M\u0001\nBeJ\f\u0017P\u00117pG.LgnZ)vKV,\u0007C\u0001!r\u0013\t\u00118HA\u0004TK\u001elWM\u001c;\u0002\u000b\rdW-\u0019:\u0015\u0003]\u000bQ!\u00199qYf$\u0012\u0001]\u0001\bC\u000e\fX/\u001b:f+\u0005\u0001\u0018a\u0002:fY\u0016\f7/\u001a\u000b\u0003/nDQ\u0001`\u0007A\u0002A\f\u0011a\u001d\u0002\u0010%\u0016\u001c\u0017p\u00197fIN+w-\\3oiN)a\u0002]@\u0002\u0006A\u0019A)!\u0001\n\u0007\u0005\rQIA\u0004Qe>$Wo\u0019;\u0011\t\u0005\u001d\u0011q\u0003\b\u0005\u0003\u0013\t\u0019B\u0004\u0003\u0002\f\u0005EQBAA\u0007\u0015\r\tyAS\u0001\u0007yI|w\u000e\u001e \n\u0003\u0019K1!!\u0006F\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u0007\u0002\u001c\ta1+\u001a:jC2L'0\u00192mK*\u0019\u0011QC#\u0002\u0007M,w-\u0006\u0002\u0002\"A)A)a\t\u0002(%\u0019\u0011QE#\u0003\u000b\u0005\u0013(/Y=\u0011\u0007\u0011\u000bI#C\u0002\u0002,\u0015\u0013Aa\u00115be\u0006!1/Z4!)\u0011\t\t$!\u000e\u0011\u0007\u0005Mb\"D\u0001\u0002\u0011\u001d\ti\"\u0005a\u0001\u0003C\tAaY8qsR!\u0011\u0011GA\u001e\u0011%\tiB\u0005I\u0001\u0002\u0004\t\t#\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005\u0005#\u0006BA\u0011\u0003\u0007Z#!!\u0012\u0011\t\u0005\u001d\u0013\u0011K\u0007\u0003\u0003\u0013RA!a\u0013\u0002N\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003\u001f*\u0015AC1o]>$\u0018\r^5p]&!\u00111KA%\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005e\u0003\u0003BA.\u0003Cj!!!\u0018\u000b\u0007\u0005}s-\u0001\u0003mC:<\u0017\u0002BA2\u0003;\u0012aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003W\n\t\bE\u0002E\u0003[J1!a\u001cF\u0005\r\te.\u001f\u0005\b7Z\t\t\u00111\u0001S\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA<!\u0019\tI(a \u0002l5\u0011\u00111\u0010\u0006\u0004\u0003{*\u0015AC2pY2,7\r^5p]&!\u0011\u0011QA>\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u00075\u000b9\t\u0003\u0005\\1\u0005\u0005\t\u0019AA6\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005e\u0013Q\u0012\u0005\b7f\t\t\u00111\u0001S\u0003!A\u0017m\u001d5D_\u0012,G#\u0001*\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\u0017\u0002\r\u0015\fX/\u00197t)\ri\u00151\u0014\u0005\t7r\t\t\u00111\u0001\u0002l\u0005y!+Z2zG2,GmU3h[\u0016tG\u000fE\u0002\u00024y\u0019RAHAR\u0003_\u0003\u0002\"!*\u0002,\u0006\u0005\u0012\u0011G\u0007\u0003\u0003OS1!!+F\u0003\u001d\u0011XO\u001c;j[\u0016LA!!,\u0002(\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\u0005E\u0016qW\u0007\u0003\u0003gS1!!.h\u0003\tIw.\u0003\u0003\u0002\u001a\u0005MFCAAP)\u0011\t\t$!0\t\u000f\u0005u\u0011\u00051\u0001\u0002\"\u00059QO\\1qa2LH\u0003BAb\u0003\u0013\u0004R\u0001RAc\u0003CI1!a2F\u0005\u0019y\u0005\u000f^5p]\"I\u00111\u001a\u0012\u0002\u0002\u0003\u0007\u0011\u0011G\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAi!\u0011\tY&a5\n\t\u0005U\u0017Q\f\u0002\u0007\u001f\nTWm\u0019;\u0003#\u0011K7\u000f]8tC\ndWmU3h[\u0016tGoE\u0003%a~\f)\u0001\u0006\u0003\u0002^\u0006}\u0007cAA\u001aI!9\u0011QD\u0014A\u0002\u0005\u0005B\u0003BAo\u0003GD\u0011\"!\b)!\u0003\u0005\r!!\t\u0015\t\u0005-\u0014q\u001d\u0005\b72\n\t\u00111\u0001S)\ri\u00151\u001e\u0005\t7:\n\t\u00111\u0001\u0002lQ!\u0011\u0011LAx\u0011\u001dYv&!AA\u0002I#2!TAz\u0011!Y&'!AA\u0002\u0005-\u0014!\u0005#jgB|7/\u00192mKN+w-\\3oiB\u0019\u00111\u0007\u001b\u0014\u000bQ\nY0a,\u0011\u0011\u0005\u0015\u00161VA\u0011\u0003;$\"!a>\u0015\t\u0005u'\u0011\u0001\u0005\b\u0003;9\u0004\u0019AA\u0011)\u0011\t\u0019M!\u0002\t\u0013\u0005-\u0007(!AA\u0002\u0005u\u0007"
)
public final class Segments {
   public static void release(final Segment s) {
      Segments$.MODULE$.release(s);
   }

   public static Segment apply() {
      return Segments$.MODULE$.apply();
   }

   private static final class RecycledSegment extends Segment {
      private final char[] seg;

      public char[] seg() {
         return this.seg;
      }

      public RecycledSegment copy(final char[] seg) {
         return new RecycledSegment(seg);
      }

      public char[] copy$default$1() {
         return this.seg();
      }

      public String productPrefix() {
         return "RecycledSegment";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.seg();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof RecycledSegment;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "seg";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label39: {
               boolean var2;
               if (x$1 instanceof RecycledSegment) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  RecycledSegment var4 = (RecycledSegment)x$1;
                  if (this.seg() == var4.seg()) {
                     break label39;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public RecycledSegment(final char[] seg) {
         this.seg = seg;
      }
   }

   private static class RecycledSegment$ extends AbstractFunction1 implements Serializable {
      public static final RecycledSegment$ MODULE$ = new RecycledSegment$();

      public final String toString() {
         return "RecycledSegment";
      }

      public RecycledSegment apply(final char[] seg) {
         return new RecycledSegment(seg);
      }

      public Option unapply(final RecycledSegment x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.seg()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(RecycledSegment$.class);
      }

      public RecycledSegment$() {
      }
   }

   private static final class DisposableSegment extends Segment {
      private final char[] seg;

      public char[] seg() {
         return this.seg;
      }

      public DisposableSegment copy(final char[] seg) {
         return new DisposableSegment(seg);
      }

      public char[] copy$default$1() {
         return this.seg();
      }

      public String productPrefix() {
         return "DisposableSegment";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.seg();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof DisposableSegment;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "seg";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label39: {
               boolean var2;
               if (x$1 instanceof DisposableSegment) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  DisposableSegment var4 = (DisposableSegment)x$1;
                  if (this.seg() == var4.seg()) {
                     break label39;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public DisposableSegment(final char[] seg) {
         this.seg = seg;
      }
   }

   private static class DisposableSegment$ extends AbstractFunction1 implements Serializable {
      public static final DisposableSegment$ MODULE$ = new DisposableSegment$();

      public final String toString() {
         return "DisposableSegment";
      }

      public DisposableSegment apply(final char[] seg) {
         return new DisposableSegment(seg);
      }

      public Option unapply(final DisposableSegment x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.seg()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(DisposableSegment$.class);
      }

      public DisposableSegment$() {
      }
   }
}
