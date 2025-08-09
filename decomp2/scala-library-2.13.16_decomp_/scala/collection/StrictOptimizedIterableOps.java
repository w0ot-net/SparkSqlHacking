package scala.collection;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(
   bytes = "\u0006\u0005\teea\u0002\u000e\u001c!\u0003\r\t\u0001\t\u0005\u0006w\u0001!\t\u0001\u0010\u0005\u0006\u0001\u0002!\t%\u0011\u0005\u0006\u001b\u0002!\tE\u0014\u0005\u0006!\u0002!\t%\u0015\u0005\u0006A\u0002!\t%\u0019\u0005\u0006i\u0002!\t%\u001e\u0005\u0007}\u0002\u0001KQC@\t\u000f\u0005%\u0002\u0001\"\u0011\u0002,!A\u0011q\b\u0001!\n+\t\t\u0005\u0003\u0005\u0002Z\u0001\u0001KQCA.\u0011\u001d\t)\b\u0001C!\u0003oB\u0001\"a#\u0001A\u0013U\u0011Q\u0012\u0005\b\u0003G\u0003A\u0011IAS\u0011!\t9\f\u0001Q\u0005\u0016\u0005e\u0006bBAj\u0001\u0011\u0005\u0013Q\u001b\u0005\t\u0003s\u0004\u0001\u0015\"\u0006\u0002|\"9!1\u0003\u0001\u0005B\tU\u0001b\u0002B\u0011\u0001\u0011\u0005#1\u0005\u0005\b\u0005{\u0001A\u0011\tB \u0011\u001d\u0011)\u0005\u0001C!\u0005\u000fB\u0001Ba\u0013\u0001\t#Y\"Q\n\u0005\b\u0005+\u0002A\u0011\tB,\u0011\u001d\u0011Y\b\u0001C!\u0005{BqAa#\u0001\t\u0003\u0012i\tC\u0004\u0003\u0014\u0002!\tE!&\u00035M#(/[2u\u001fB$\u0018.\\5{K\u0012LE/\u001a:bE2,w\n]:\u000b\u0005qi\u0012AC2pY2,7\r^5p]*\ta$A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\t\u0005b3'O\n\u0004\u0001\t2\u0003CA\u0012%\u001b\u0005i\u0012BA\u0013\u001e\u0005\r\te.\u001f\t\u0006O!R#\u0007O\u0007\u00027%\u0011\u0011f\u0007\u0002\f\u0013R,'/\u00192mK>\u00038\u000f\u0005\u0002,Y1\u0001AAB\u0017\u0001\t\u000b\u0007aFA\u0001B#\ty#\u0005\u0005\u0002$a%\u0011\u0011'\b\u0002\b\u001d>$\b.\u001b8h!\tY3\u0007\u0002\u00045\u0001\u0011\u0015\r!\u000e\u0002\u0003\u0007\u000e+\"A\f\u001c\u0005\u000b]\u001a$\u0019\u0001\u0018\u0003\t}#C%\r\t\u0003We\"aA\u000f\u0001\u0005\u0006\u0004q#!A\"\u0002\r\u0011Jg.\u001b;%)\u0005i\u0004CA\u0012?\u0013\tyTD\u0001\u0003V]&$\u0018!\u00039beRLG/[8o)\t\u0011U\t\u0005\u0003$\u0007bB\u0014B\u0001#\u001e\u0005\u0019!V\u000f\u001d7fe!)aI\u0001a\u0001\u000f\u0006\t\u0001\u000f\u0005\u0003$\u0011*R\u0015BA%\u001e\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002$\u0017&\u0011A*\b\u0002\b\u0005>|G.Z1o\u0003\u0011\u0019\b/\u00198\u0015\u0005\t{\u0005\"\u0002$\u0004\u0001\u00049\u0015!B;ou&\u0004Xc\u0001*W5R\u00111\u000b\u0018\t\u0005G\r#\u0006\fE\u0002,gU\u0003\"a\u000b,\u0005\u000b]#!\u0019\u0001\u0018\u0003\u0005\u0005\u000b\u0004cA\u001643B\u00111F\u0017\u0003\u00067\u0012\u0011\rA\f\u0002\u0003\u0003JBQ!\u0018\u0003A\u0004y\u000ba!Y:QC&\u0014\b\u0003B\u0012IU}\u0003BaI\"V3\u00061QO\u001c>jaN*BA\u00195l]R\u00111\r\u001d\t\u0006G\u00114\u0017\u000e\\\u0005\u0003Kv\u0011a\u0001V;qY\u0016\u001c\u0004cA\u00164OB\u00111\u0006\u001b\u0003\u0006/\u0016\u0011\rA\f\t\u0004WMR\u0007CA\u0016l\t\u0015YVA1\u0001/!\rY3'\u001c\t\u0003W9$Qa\\\u0003C\u00029\u0012!!Q\u001a\t\u000bE,\u00019\u0001:\u0002\u0011\u0005\u001cHK]5qY\u0016\u0004Ba\t%+gB)1\u0005Z4k[\u0006\u0019Q.\u00199\u0016\u0005YLHCA<|!\rY3\u0007\u001f\t\u0003We$QA\u001f\u0004C\u00029\u0012\u0011A\u0011\u0005\u0006y\u001a\u0001\r!`\u0001\u0002MB!1\u0005\u0013\u0016y\u0003I\u0019HO]5di>\u0003H/[7ju\u0016$W*\u00199\u0016\r\u0005\u0005\u00111DA\u0003)\u0019\t\u0019!!\u0003\u0002\u001eA\u00191&!\u0002\u0005\r\u0005\u001dqA1\u0001/\u0005\t\u0019%\u0007C\u0004\u0002\f\u001d\u0001\r!!\u0004\u0002\u0003\t\u0004\u0002\"a\u0004\u0002\u0016\u0005e\u00111A\u0007\u0003\u0003#Q1!a\u0005\u001c\u0003\u001diW\u000f^1cY\u0016LA!a\u0006\u0002\u0012\t9!)^5mI\u0016\u0014\bcA\u0016\u0002\u001c\u0011)!p\u0002b\u0001]!1Ap\u0002a\u0001\u0003?\u0001Ra\t%+\u00033A3aBA\u0012!\r\u0019\u0013QE\u0005\u0004\u0003Oi\"AB5oY&tW-A\u0004gY\u0006$X*\u00199\u0016\t\u00055\u00121\u0007\u000b\u0005\u0003_\t)\u0004\u0005\u0003,g\u0005E\u0002cA\u0016\u00024\u0011)!\u0010\u0003b\u0001]!1A\u0010\u0003a\u0001\u0003o\u0001Ra\t%+\u0003s\u0001RaJA\u001e\u0003cI1!!\u0010\u001c\u00051IE/\u001a:bE2,wJ\\2f\u0003Y\u0019HO]5di>\u0003H/[7ju\u0016$g\t\\1u\u001b\u0006\u0004XCBA\"\u0003\u001f\n9\u0005\u0006\u0004\u0002F\u0005%\u0013\u0011\u000b\t\u0004W\u0005\u001dCABA\u0004\u0013\t\u0007a\u0006C\u0004\u0002\f%\u0001\r!a\u0013\u0011\u0011\u0005=\u0011QCA'\u0003\u000b\u00022aKA(\t\u0015Q\u0018B1\u0001/\u0011\u0019a\u0018\u00021\u0001\u0002TA)1\u0005\u0013\u0016\u0002VA)q%a\u000f\u0002N!\u001a\u0011\"a\t\u0002+M$(/[2u\u001fB$\u0018.\\5{K\u0012\u001cuN\\2biV1\u0011QLA6\u0003C\"b!a\u0018\u0002d\u0005=\u0004cA\u0016\u0002b\u00111\u0011q\u0001\u0006C\u00029Bq!!\u001a\u000b\u0001\u0004\t9'\u0001\u0003uQ\u0006$\b#B\u0014\u0002<\u0005%\u0004cA\u0016\u0002l\u00111!P\u0003b\u0001\u0003[\n\"A\u000b\u0012\t\u000f\u0005-!\u00021\u0001\u0002rAA\u0011qBA\u000b\u0003S\ny\u0006K\u0002\u000b\u0003G\tqaY8mY\u0016\u001cG/\u0006\u0003\u0002z\u0005}D\u0003BA>\u0003\u0003\u0003BaK\u001a\u0002~A\u00191&a \u0005\u000bi\\!\u0019\u0001\u0018\t\u000f\u0005\r5\u00021\u0001\u0002\u0006\u0006\u0011\u0001O\u001a\t\u0007G\u0005\u001d%&! \n\u0007\u0005%UDA\bQCJ$\u0018.\u00197Gk:\u001cG/[8o\u0003Y\u0019HO]5di>\u0003H/[7ju\u0016$7i\u001c7mK\u000e$XCBAH\u00037\u000b\u0019\n\u0006\u0004\u0002\u0012\u0006U\u0015Q\u0014\t\u0004W\u0005MEABA\u0004\u0019\t\u0007a\u0006C\u0004\u0002\f1\u0001\r!a&\u0011\u0011\u0005=\u0011QCAM\u0003#\u00032aKAN\t\u0015QHB1\u0001/\u0011\u001d\t\u0019\t\u0004a\u0001\u0003?\u0003baIADU\u0005e\u0005f\u0001\u0007\u0002$\u00059a\r\\1ui\u0016tW\u0003BAT\u0003[#B!!+\u00020B!1fMAV!\rY\u0013Q\u0016\u0003\u0006u6\u0011\rA\f\u0005\b\u0003ck\u00019AAZ\u00039!x.\u0013;fe\u0006\u0014G.Z(oG\u0016\u0004Ra\t%+\u0003k\u0003RaJA\u001e\u0003W\u000bac\u001d;sS\u000e$x\n\u001d;j[&TX\r\u001a$mCR$XM\\\u000b\u0007\u0003w\u000bY-!1\u0015\t\u0005u\u0016Q\u001a\u000b\u0005\u0003\u007f\u000b\u0019\rE\u0002,\u0003\u0003$a!a\u0002\u000f\u0005\u0004q\u0003bBAY\u001d\u0001\u000f\u0011Q\u0019\t\u0006G!S\u0013q\u0019\t\u0006O\u0005m\u0012\u0011\u001a\t\u0004W\u0005-G!\u0002>\u000f\u0005\u0004q\u0003bBA\u0006\u001d\u0001\u0007\u0011q\u001a\t\t\u0003\u001f\t)\"!3\u0002@\"\u001aa\"a\t\u0002\u0007iL\u0007/\u0006\u0003\u0002X\u0006MH\u0003BAm\u0003k\u0004BaK\u001a\u0002\\B11eQAo\u0003cT3AKApW\t\t\t\u000f\u0005\u0003\u0002d\u00065XBAAs\u0015\u0011\t9/!;\u0002\u0013Ut7\r[3dW\u0016$'bAAv;\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005=\u0018Q\u001d\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007cA\u0016\u0002t\u0012)!p\u0004b\u0001]!9\u0011QM\bA\u0002\u0005]\b#B\u0014\u0002<\u0005E\u0018AE:ue&\u001cGo\u00149uS6L'0\u001a3[SB,b!!@\u0003\n\t\u0005ACBA\u0000\u0005\u0007\u0011Y\u0001E\u0002,\u0005\u0003!a!a\u0002\u0011\u0005\u0004q\u0003bBA3!\u0001\u0007!Q\u0001\t\u0006O\u0005m\"q\u0001\t\u0004W\t%A!\u0002>\u0011\u0005\u0004q\u0003bBA\u0006!\u0001\u0007!Q\u0002\t\t\u0003\u001f\t)Ba\u0004\u0002\u0000B)1e\u0011\u0016\u0003\b!\u001a\u0001#a\t\u0002\u0019iL\u0007oV5uQ&sG-\u001a=\u0016\u0005\t]\u0001\u0003B\u00164\u00053\u0001baI\"\u0002^\nm\u0001cA\u0012\u0003\u001e%\u0019!qD\u000f\u0003\u0007%sG/\u0001\u0005tG\u0006tG*\u001a4u+\u0011\u0011)C!\f\u0015\t\t\u001d\"\u0011\b\u000b\u0005\u0005S\u0011y\u0003\u0005\u0003,g\t-\u0002cA\u0016\u0003.\u0011)!P\u0005b\u0001]!9!\u0011\u0007\nA\u0002\tM\u0012AA8q!!\u0019#Q\u0007B\u0016U\t-\u0012b\u0001B\u001c;\tIa)\u001e8di&|gN\r\u0005\b\u0005w\u0011\u0002\u0019\u0001B\u0016\u0003\u0005Q\u0018A\u00024jYR,'\u000fF\u00029\u0005\u0003BaAa\u0011\u0014\u0001\u00049\u0015\u0001\u00029sK\u0012\f\u0011BZ5mi\u0016\u0014hj\u001c;\u0015\u0007a\u0012I\u0005\u0003\u0004\u0003DQ\u0001\raR\u0001\u000bM&dG/\u001a:J[BdG#\u0002\u001d\u0003P\tE\u0003B\u0002B\"+\u0001\u0007q\t\u0003\u0004\u0003TU\u0001\rAS\u0001\nSN4E.\u001b9qK\u0012\fA\u0002]1si&$\u0018n\u001c8NCB,bA!\u0017\u0003b\t\u001dD\u0003\u0002B.\u0005S\u0002baI\"\u0003^\t\r\u0004\u0003B\u00164\u0005?\u00022a\u000bB1\t\u00159fC1\u0001/!\u0011Y3G!\u001a\u0011\u0007-\u00129\u0007B\u0003\\-\t\u0007a\u0006\u0003\u0004}-\u0001\u0007!1\u000e\t\u0006G!S#Q\u000e\t\t\u0005_\u0012)Ha\u0018\u0003f9\u00191E!\u001d\n\u0007\tMT$A\u0004qC\u000e\\\u0017mZ3\n\t\t]$\u0011\u0010\u0002\u0007\u000b&$\b.\u001a:\u000b\u0007\tMT$A\u0004uCB,\u0015m\u00195\u0016\t\t}$q\u0011\u000b\u0004q\t\u0005\u0005B\u0002?\u0018\u0001\u0004\u0011\u0019\tE\u0003$\u0011*\u0012)\tE\u0002,\u0005\u000f#aA!#\u0018\u0005\u0004q#!A+\u0002\u0013Q\f7.\u001a*jO\"$Hc\u0001\u001d\u0003\u0010\"9!\u0011\u0013\rA\u0002\tm\u0011!\u00018\u0002\u0013\u0011\u0014x\u000e\u001d*jO\"$Hc\u0001\u001d\u0003\u0018\"9!\u0011S\rA\u0002\tm\u0001"
)
public interface StrictOptimizedIterableOps extends IterableOps {
   // $FF: synthetic method
   static Tuple2 partition$(final StrictOptimizedIterableOps $this, final Function1 p) {
      return $this.partition(p);
   }

   default Tuple2 partition(final Function1 p) {
      Builder l = this.newSpecificBuilder();
      Builder r = this.newSpecificBuilder();
      this.iterator().foreach((x) -> {
         Builder var10000 = BoxesRunTime.unboxToBoolean(p.apply(x)) ? l : r;
         if (var10000 == null) {
            throw null;
         } else {
            return (Builder)var10000.addOne(x);
         }
      });
      return new Tuple2(l.result(), r.result());
   }

   // $FF: synthetic method
   static Tuple2 span$(final StrictOptimizedIterableOps $this, final Function1 p) {
      return $this.span(p);
   }

   default Tuple2 span(final Function1 p) {
      Builder first = this.newSpecificBuilder();
      Builder second = this.newSpecificBuilder();
      Iterator it = this.iterator();
      boolean inFirst = true;

      while(it.hasNext() && inFirst) {
         Object a = it.next();
         if (BoxesRunTime.unboxToBoolean(p.apply(a))) {
            if (first == null) {
               throw null;
            }

            first.addOne(a);
         } else {
            if (second == null) {
               throw null;
            }

            second.addOne(a);
            inFirst = false;
         }
      }

      while(it.hasNext()) {
         Object $plus$eq_elem = it.next();
         if (second == null) {
            throw null;
         }

         second.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      }

      return new Tuple2(first.result(), second.result());
   }

   // $FF: synthetic method
   static Tuple2 unzip$(final StrictOptimizedIterableOps $this, final Function1 asPair) {
      return $this.unzip(asPair);
   }

   default Tuple2 unzip(final Function1 asPair) {
      Builder first = this.iterableFactory().newBuilder();
      Builder second = this.iterableFactory().newBuilder();
      this.foreach((a) -> {
         Tuple2 pair = (Tuple2)asPair.apply(a);
         Object $plus$eq_elemx = pair._1();
         if (first == null) {
            throw null;
         } else {
            first.addOne($plus$eq_elemx);
            $plus$eq_elemx = null;
            Object $plus$eq_elem = pair._2();
            if (second == null) {
               throw null;
            } else {
               return (Builder)second.addOne($plus$eq_elem);
            }
         }
      });
      return new Tuple2(first.result(), second.result());
   }

   // $FF: synthetic method
   static Tuple3 unzip3$(final StrictOptimizedIterableOps $this, final Function1 asTriple) {
      return $this.unzip3(asTriple);
   }

   default Tuple3 unzip3(final Function1 asTriple) {
      Builder b1 = this.iterableFactory().newBuilder();
      Builder b2 = this.iterableFactory().newBuilder();
      Builder b3 = this.iterableFactory().newBuilder();
      this.foreach((xyz) -> {
         Tuple3 triple = (Tuple3)asTriple.apply(xyz);
         Object $plus$eq_elem = triple._1();
         if (b1 == null) {
            throw null;
         } else {
            b1.addOne($plus$eq_elem);
            $plus$eq_elem = null;
            Object $plus$eq_elemx = triple._2();
            if (b2 == null) {
               throw null;
            } else {
               b2.addOne($plus$eq_elemx);
               $plus$eq_elemx = null;
               Object $plus$eq_elemxx = triple._3();
               if (b3 == null) {
                  throw null;
               } else {
                  return (Builder)b3.addOne($plus$eq_elemxx);
               }
            }
         }
      });
      return new Tuple3(b1.result(), b2.result(), b3.result());
   }

   // $FF: synthetic method
   static Object map$(final StrictOptimizedIterableOps $this, final Function1 f) {
      return $this.map(f);
   }

   default Object map(final Function1 f) {
      Builder strictOptimizedMap_b = this.iterableFactory().newBuilder();

      Object var5;
      for(Iterator strictOptimizedMap_it = this.iterator(); strictOptimizedMap_it.hasNext(); var5 = null) {
         var5 = f.apply(strictOptimizedMap_it.next());
         if (strictOptimizedMap_b == null) {
            throw null;
         }

         strictOptimizedMap_b.addOne(var5);
      }

      return strictOptimizedMap_b.result();
   }

   // $FF: synthetic method
   static Object strictOptimizedMap$(final StrictOptimizedIterableOps $this, final Builder b, final Function1 f) {
      return $this.strictOptimizedMap(b, f);
   }

   default Object strictOptimizedMap(final Builder b, final Function1 f) {
      Object var5;
      for(Iterator it = this.iterator(); it.hasNext(); var5 = null) {
         var5 = f.apply(it.next());
         if (b == null) {
            throw null;
         }

         b.addOne(var5);
      }

      return b.result();
   }

   // $FF: synthetic method
   static Object flatMap$(final StrictOptimizedIterableOps $this, final Function1 f) {
      return $this.flatMap(f);
   }

   default Object flatMap(final Function1 f) {
      Builder strictOptimizedFlatMap_b = this.iterableFactory().newBuilder();

      Object var5;
      for(Iterator strictOptimizedFlatMap_it = this.iterator(); strictOptimizedFlatMap_it.hasNext(); var5 = null) {
         IterableOnce strictOptimizedFlatMap_$plus$plus$eq_elems = (IterableOnce)f.apply(strictOptimizedFlatMap_it.next());
         if (strictOptimizedFlatMap_b == null) {
            throw null;
         }

         strictOptimizedFlatMap_b.addAll(strictOptimizedFlatMap_$plus$plus$eq_elems);
      }

      return strictOptimizedFlatMap_b.result();
   }

   // $FF: synthetic method
   static Object strictOptimizedFlatMap$(final StrictOptimizedIterableOps $this, final Builder b, final Function1 f) {
      return $this.strictOptimizedFlatMap(b, f);
   }

   default Object strictOptimizedFlatMap(final Builder b, final Function1 f) {
      Object var5;
      for(Iterator it = this.iterator(); it.hasNext(); var5 = null) {
         IterableOnce $plus$plus$eq_elems = (IterableOnce)f.apply(it.next());
         if (b == null) {
            throw null;
         }

         b.addAll($plus$plus$eq_elems);
      }

      return b.result();
   }

   // $FF: synthetic method
   static Object strictOptimizedConcat$(final StrictOptimizedIterableOps $this, final IterableOnce that, final Builder b) {
      return $this.strictOptimizedConcat(that, b);
   }

   default Object strictOptimizedConcat(final IterableOnce that, final Builder b) {
      if (b == null) {
         throw null;
      } else {
         b.addAll(this);
         b.addAll(that);
         return b.result();
      }
   }

   // $FF: synthetic method
   static Object collect$(final StrictOptimizedIterableOps $this, final PartialFunction pf) {
      return $this.collect(pf);
   }

   default Object collect(final PartialFunction pf) {
      Builder strictOptimizedCollect_b = this.iterableFactory().newBuilder();
      Object strictOptimizedCollect_marker = Statics.pfMarker;
      Iterator strictOptimizedCollect_it = this.iterator();

      while(strictOptimizedCollect_it.hasNext()) {
         Object strictOptimizedCollect_elem = strictOptimizedCollect_it.next();
         Object strictOptimizedCollect_v = pf.applyOrElse(strictOptimizedCollect_elem, (x) -> marker);
         if (strictOptimizedCollect_marker != strictOptimizedCollect_v) {
            if (strictOptimizedCollect_b == null) {
               throw null;
            }

            strictOptimizedCollect_b.addOne(strictOptimizedCollect_v);
         }
      }

      return strictOptimizedCollect_b.result();
   }

   // $FF: synthetic method
   static Object strictOptimizedCollect$(final StrictOptimizedIterableOps $this, final Builder b, final PartialFunction pf) {
      return $this.strictOptimizedCollect(b, pf);
   }

   default Object strictOptimizedCollect(final Builder b, final PartialFunction pf) {
      Object marker = Statics.pfMarker;
      Iterator it = this.iterator();

      while(it.hasNext()) {
         Object elem = it.next();
         Object v = pf.applyOrElse(elem, (x) -> marker);
         if (marker != v) {
            if (b == null) {
               throw null;
            }

            b.addOne(v);
         }
      }

      return b.result();
   }

   // $FF: synthetic method
   static Object flatten$(final StrictOptimizedIterableOps $this, final Function1 toIterableOnce) {
      return $this.flatten(toIterableOnce);
   }

   default Object flatten(final Function1 toIterableOnce) {
      Builder strictOptimizedFlatten_b = this.iterableFactory().newBuilder();

      Object var5;
      for(Iterator strictOptimizedFlatten_it = this.iterator(); strictOptimizedFlatten_it.hasNext(); var5 = null) {
         IterableOnce strictOptimizedFlatten_$plus$plus$eq_elems = (IterableOnce)toIterableOnce.apply(strictOptimizedFlatten_it.next());
         if (strictOptimizedFlatten_b == null) {
            throw null;
         }

         strictOptimizedFlatten_b.addAll(strictOptimizedFlatten_$plus$plus$eq_elems);
      }

      return strictOptimizedFlatten_b.result();
   }

   // $FF: synthetic method
   static Object strictOptimizedFlatten$(final StrictOptimizedIterableOps $this, final Builder b, final Function1 toIterableOnce) {
      return $this.strictOptimizedFlatten(b, toIterableOnce);
   }

   default Object strictOptimizedFlatten(final Builder b, final Function1 toIterableOnce) {
      Object var5;
      for(Iterator it = this.iterator(); it.hasNext(); var5 = null) {
         IterableOnce $plus$plus$eq_elems = (IterableOnce)toIterableOnce.apply(it.next());
         if (b == null) {
            throw null;
         }

         b.addAll($plus$plus$eq_elems);
      }

      return b.result();
   }

   // $FF: synthetic method
   static Object zip$(final StrictOptimizedIterableOps $this, final IterableOnce that) {
      return $this.zip(that);
   }

   default Object zip(final IterableOnce that) {
      Builder strictOptimizedZip_b = this.iterableFactory().newBuilder();
      Iterator strictOptimizedZip_it1 = this.iterator();

      Object var6;
      for(Iterator strictOptimizedZip_it2 = that.iterator(); strictOptimizedZip_it1.hasNext() && strictOptimizedZip_it2.hasNext(); var6 = null) {
         Tuple2 strictOptimizedZip_$plus$eq_elem = new Tuple2(strictOptimizedZip_it1.next(), strictOptimizedZip_it2.next());
         if (strictOptimizedZip_b == null) {
            throw null;
         }

         strictOptimizedZip_b.addOne(strictOptimizedZip_$plus$eq_elem);
      }

      return strictOptimizedZip_b.result();
   }

   // $FF: synthetic method
   static Object strictOptimizedZip$(final StrictOptimizedIterableOps $this, final IterableOnce that, final Builder b) {
      return $this.strictOptimizedZip(that, b);
   }

   default Object strictOptimizedZip(final IterableOnce that, final Builder b) {
      Iterator it1 = this.iterator();

      Object var6;
      for(Iterator it2 = that.iterator(); it1.hasNext() && it2.hasNext(); var6 = null) {
         Tuple2 $plus$eq_elem = new Tuple2(it1.next(), it2.next());
         if (b == null) {
            throw null;
         }

         b.addOne($plus$eq_elem);
      }

      return b.result();
   }

   // $FF: synthetic method
   static Object zipWithIndex$(final StrictOptimizedIterableOps $this) {
      return $this.zipWithIndex();
   }

   default Object zipWithIndex() {
      Builder b = this.iterableFactory().newBuilder();
      int i = 0;

      for(Iterator it = this.iterator(); it.hasNext(); ++i) {
         Tuple2 $plus$eq_elem = new Tuple2(it.next(), i);
         if (b == null) {
            throw null;
         }

         b.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      }

      return b.result();
   }

   // $FF: synthetic method
   static Object scanLeft$(final StrictOptimizedIterableOps $this, final Object z, final Function2 op) {
      return $this.scanLeft(z, op);
   }

   default Object scanLeft(final Object z, final Function2 op) {
      Builder b = this.iterableFactory().newBuilder();
      b.sizeHint(this, 0);
      Object acc = z;
      b.addOne(z);
      Iterator it = this.iterator();

      while(it.hasNext()) {
         acc = op.apply(acc, it.next());
         b.addOne(acc);
      }

      return b.result();
   }

   // $FF: synthetic method
   static Object filter$(final StrictOptimizedIterableOps $this, final Function1 pred) {
      return $this.filter(pred);
   }

   default Object filter(final Function1 pred) {
      return this.filterImpl(pred, false);
   }

   // $FF: synthetic method
   static Object filterNot$(final StrictOptimizedIterableOps $this, final Function1 pred) {
      return $this.filterNot(pred);
   }

   default Object filterNot(final Function1 pred) {
      return this.filterImpl(pred, true);
   }

   // $FF: synthetic method
   static Object filterImpl$(final StrictOptimizedIterableOps $this, final Function1 pred, final boolean isFlipped) {
      return $this.filterImpl(pred, isFlipped);
   }

   default Object filterImpl(final Function1 pred, final boolean isFlipped) {
      Builder b = this.newSpecificBuilder();
      Iterator it = this.iterator();

      while(it.hasNext()) {
         Object elem = it.next();
         if (BoxesRunTime.unboxToBoolean(pred.apply(elem)) != isFlipped) {
            if (b == null) {
               throw null;
            }

            b.addOne(elem);
         }
      }

      return b.result();
   }

   // $FF: synthetic method
   static Tuple2 partitionMap$(final StrictOptimizedIterableOps $this, final Function1 f) {
      return $this.partitionMap(f);
   }

   default Tuple2 partitionMap(final Function1 f) {
      Builder l = this.iterableFactory().newBuilder();
      Builder r = this.iterableFactory().newBuilder();
      this.foreach((x) -> {
         Either var4 = (Either)f.apply(x);
         if (var4 instanceof Left) {
            Object x1 = ((Left)var4).value();
            if (l == null) {
               throw null;
            } else {
               return (Builder)l.addOne(x1);
            }
         } else if (var4 instanceof Right) {
            Object x2 = ((Right)var4).value();
            if (r == null) {
               throw null;
            } else {
               return (Builder)r.addOne(x2);
            }
         } else {
            throw new MatchError(var4);
         }
      });
      return new Tuple2(l.result(), r.result());
   }

   // $FF: synthetic method
   static Object tapEach$(final StrictOptimizedIterableOps $this, final Function1 f) {
      return $this.tapEach(f);
   }

   default Object tapEach(final Function1 f) {
      this.foreach(f);
      return this.coll();
   }

   // $FF: synthetic method
   static Object takeRight$(final StrictOptimizedIterableOps $this, final int n) {
      return $this.takeRight(n);
   }

   default Object takeRight(final int n) {
      Builder b = this.newSpecificBuilder();
      b.sizeHintBounded(n, this.toIterable());
      Iterator lead = this.iterator().drop(n);
      Iterator it = this.iterator();

      while(lead.hasNext()) {
         lead.next();
         it.next();
      }

      while(it.hasNext()) {
         Object $plus$eq_elem = it.next();
         b.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      }

      return b.result();
   }

   // $FF: synthetic method
   static Object dropRight$(final StrictOptimizedIterableOps $this, final int n) {
      return $this.dropRight(n);
   }

   default Object dropRight(final int n) {
      Builder b = this.newSpecificBuilder();
      if (n >= 0) {
         b.sizeHint(this, -n);
      }

      Iterator lead = this.iterator().drop(n);
      Iterator it = this.iterator();

      while(lead.hasNext()) {
         Object $plus$eq_elem = it.next();
         if (b == null) {
            throw null;
         }

         b.addOne($plus$eq_elem);
         $plus$eq_elem = null;
         lead.next();
      }

      return b.result();
   }

   static void $init$(final StrictOptimizedIterableOps $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
