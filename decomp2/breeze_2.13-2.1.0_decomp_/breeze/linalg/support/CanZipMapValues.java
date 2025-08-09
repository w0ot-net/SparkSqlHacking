package breeze.linalg.support;

import breeze.math.Complex;
import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t%ca\u0002\u0016,!\u0003\r\nA\r\u0005\u0006u\u00011\taO\u0004\u0007\u007f.B\t!!\u0001\u0007\r)Z\u0003\u0012AA\u0003\u0011\u001d\t9a\u0001C\u0001\u0003\u0013Aq!a\u0003\u0004\t\u0003\ti!\u0002\u0004\u0002\u001a\r\u0001\u00111\u0004\u0004\u0007\u0003_\u0019\u0001!!\r\t\u0015\u00055tAaA!\u0002\u0017\ty\u0007C\u0004\u0002\b\u001d!\t!a\u001f\t\ri:A\u0011AAB\u0011\u001d\tii\u0001C\u0002\u0003\u001f;q!a*\u0004\u0011\u0007\tIKB\u0004\u0002,\u000eA\t!!,\t\u000f\u0005\u001dQ\u0002\"\u0001\u00028\u001e9\u0011\u0011X\u0002\t\u0004\u0005mfaBA_\u0007!\u0005\u0011q\u0018\u0005\b\u0003\u000f\u0001B\u0011AAe\u000f\u001d\tYm\u0001E\u0002\u0003\u001b4q!a4\u0004\u0011\u0003\t\t\u000eC\u0004\u0002\bM!\t!a7\b\u000f\u0005u7\u0001c\u0001\u0002`\u001a9\u0011\u0011]\u0002\t\u0002\u0005\r\bbBA\u0004-\u0011\u0005\u0011Q^\u0004\b\u0003_\u001c\u00012AAy\r\u001d\t\u0019p\u0001E\u0001\u0003kDq!a\u0002\u001a\t\u0003\typB\u0004\u0003\u0002\rA\u0019Aa\u0001\u0007\u000f\t\u00151\u0001#\u0001\u0003\b!9\u0011q\u0001\u000f\u0005\u0002\t]qa\u0002B\r\u0007!\r!1\u0004\u0004\b\u0005;\u0019\u0001\u0012\u0001B\u0010\u0011\u001d\t9a\bC\u0001\u0005G9qA!\n\u0004\u0011\u0007\u00119CB\u0004\u0003*\rA\tAa\u000b\t\u000f\u0005\u001d!\u0005\"\u0001\u00030\u001d9!\u0011G\u0002\t\u0004\tMba\u0002B\u001b\u0007!\u0005!q\u0007\u0005\b\u0003\u000f)C\u0011\u0001B\u001e\u000f\u001d\u0011id\u0001E\u0002\u0005\u007f1qA!\u0011\u0004\u0011\u0003\u0011\u0019\u0005C\u0004\u0002\b!\"\tAa\u0012\u0003\u001f\r\u000bgNW5q\u001b\u0006\u0004h+\u00197vKNT!\u0001L\u0017\u0002\u000fM,\b\u000f]8si*\u0011afL\u0001\u0007Y&t\u0017\r\\4\u000b\u0003A\naA\u0019:fKj,7\u0001A\u000b\u0006g)#FOP\n\u0003\u0001Q\u0002\"!\u000e\u001d\u000e\u0003YR\u0011aN\u0001\u0006g\u000e\fG.Y\u0005\u0003sY\u0012a!\u00118z%\u00164\u0017aA7baR!Ah\u0012'O!\tid\b\u0004\u0001\u0005\r}\u0002AQ1\u0001A\u0005\t!v.\u0005\u0002B\tB\u0011QGQ\u0005\u0003\u0007Z\u0012qAT8uQ&tw\r\u0005\u00026\u000b&\u0011aI\u000e\u0002\u0004\u0003:L\b\"\u0002%\u0002\u0001\u0004I\u0015\u0001\u00024s_6\u0004\"!\u0010&\u0005\u000b-\u0003!\u0019\u0001!\u0003\t\u0019\u0013x.\u001c\u0005\u0006\u001b\u0006\u0001\r!S\u0001\u0006MJ|WN\r\u0005\u0006\u001f\u0006\u0001\r\u0001U\u0001\u0003M:\u0004R!N)T'NL!A\u0015\u001c\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004CA\u001fU\t%)\u0006\u0001)A\u0001\u0002\u000b\u0007\u0001IA\u0001WQ\u0019!vK\u00173j]B\u0011Q\u0007W\u0005\u00033Z\u00121b\u001d9fG&\fG.\u001b>fIF*1e\u0017/_;:\u0011Q\u0007X\u0005\u0003;Z\na\u0001R8vE2,\u0017\u0007\u0002\u0013`G^r!\u0001Y2\u000e\u0003\u0005T!AY\u0019\u0002\rq\u0012xn\u001c;?\u0013\u00059\u0014'B\u0012fM\"<gBA\u001bg\u0013\t9g'A\u0002J]R\fD\u0001J0doE*1E[6nY:\u0011Qg[\u0005\u0003YZ\nQA\u00127pCR\fD\u0001J0doE*1e\u001c9sc:\u0011Q\u0007]\u0005\u0003cZ\nA\u0001T8oOF\"AeX28!\tiD\u000fB\u0005v\u0001\u0001\u0006\t\u0011!b\u0001\u0001\n\u0011!K\u0016\u0015\u0007i^;\u0018p_?2\u000b\rZF\f_/2\t\u0011z6mN\u0019\u0006G\u00154'pZ\u0019\u0005I}\u001bw'M\u0003$U.dH.\r\u0003%?\u000e<\u0014'B\u0012paz\f\u0018\u0007\u0002\u0013`G^\nqbQ1o5&\u0004X*\u00199WC2,Xm\u001d\t\u0004\u0003\u0007\u0019Q\"A\u0016\u0014\u0005\r!\u0014A\u0002\u001fj]&$h\b\u0006\u0002\u0002\u0002\u0005i1-\u00198[SBl\u0015\r]*fY\u001a,B!a\u0004\u0002\u0016U\u0011\u0011\u0011\u0003\t\f\u0003\u0007\u0001\u00111CA\n\u0003'\t\u0019\u0002E\u0002>\u0003+!a!a\u0006\u0006\u0005\u0004\u0001%!A*\u0003\u0005=\u0003XCCA\u000f\u0003C\t)#!\u000b\u0002.AY\u00111\u0001\u0001\u0002 \u0005\r\u0012qEA\u0016!\ri\u0014\u0011\u0005\u0003\u0006\u0017\u001a\u0011\r\u0001\u0011\t\u0004{\u0005\u0015B!B+\u0007\u0005\u0004\u0001\u0005cA\u001f\u0002*\u0011)QO\u0002b\u0001\u0001B\u0019Q(!\f\u0005\u000b}2!\u0019\u0001!\u0003\u000f=\u0003\u0018I\u001d:bsV1\u00111GA!\u0003/\u001aBa\u0002\u001b\u00026AY\u0011q\u0007\u0004\u0002:\u0005}\u0012QKA6\u001b\u0005\u0019\u0001#B\u001b\u0002<\u0005}\u0012bAA\u001fm\t)\u0011I\u001d:bsB\u0019Q(!\u0011\u0005\u0013U;\u0001\u0015!A\u0001\u0006\u0004\u0001\u0005fCA!/\u0006\u0015\u0013\u0011JA'\u0003#\ndaI.]\u0003\u000fj\u0016\u0007\u0002\u0013`G^\ndaI3g\u0003\u0017:\u0017\u0007\u0002\u0013`G^\nda\t6l\u0003\u001fb\u0017\u0007\u0002\u0013`G^\ndaI8q\u0003'\n\u0018\u0007\u0002\u0013`G^\u00022!PA,\t%)x\u0001)A\u0001\u0002\u000b\u0007\u0001\tK\u0006\u0002X]\u000bY&a\u0018\u0002d\u0005\u001d\u0014GB\u0012\\9\u0006uS,\r\u0003%?\u000e<\u0014GB\u0012fM\u0006\u0005t-\r\u0003%?\u000e<\u0014GB\u0012kW\u0006\u0015D.\r\u0003%?\u000e<\u0014GB\u0012pa\u0006%\u0014/\r\u0003%?\u000e<\u0004#B\u001b\u0002<\u0005U\u0013AC3wS\u0012,gnY3%cA1\u0011\u0011OA<\u0003+j!!a\u001d\u000b\u0007\u0005Ud'A\u0004sK\u001adWm\u0019;\n\t\u0005e\u00141\u000f\u0002\t\u00072\f7o\u001d+bOR\u0011\u0011Q\u0010\u000b\u0005\u0003\u007f\n\t\tE\u0004\u00028\u001d\ty$!\u0016\t\u000f\u00055\u0014\u0002q\u0001\u0002pQA\u00111NAC\u0003\u000f\u000bI\t\u0003\u0004I\u0015\u0001\u0007\u0011\u0011\b\u0005\u0007\u001b*\u0001\r!!\u000f\t\r=S\u0001\u0019AAF!!)\u0014+a\u0010\u0002@\u0005U\u0013aB8q\u0003J\u0014\u0018-_\u000b\u0007\u0003#\u000b9*!(\u0015\t\u0005M\u0015\u0011\u0015\t\b\u0003o9\u0011QSAN!\ri\u0014q\u0013\u0003\n+.\u0001\u000b\u0011!AC\u0002\u0001C3!a&X!\ri\u0014Q\u0014\u0003\nk.\u0001\u000b\u0011!AC\u0002\u0001C3!!(X\u0011%\t\u0019kCA\u0001\u0002\b\t)+\u0001\u0006fm&$WM\\2fII\u0002b!!\u001d\u0002x\u0005m\u0015!C(q\u0003J\u0014\u0018-_%J!\r\t9$\u0004\u0002\n\u001fB\f%O]1z\u0013&\u001b2!DAX!\u001d\t9dBAY\u0003c\u00032!NAZ\u0013\r\t)L\u000e\u0002\u0004\u0013:$HCAAU\u0003%y\u0005/\u0011:sCf\u001c6\u000bE\u0002\u00028A\u0011\u0011b\u00149BeJ\f\u0017pU*\u0014\u0007A\t\t\rE\u0004\u00028\u001d\t\u0019-a1\u0011\u0007U\n)-C\u0002\u0002HZ\u0012Qa\u00155peR$\"!a/\u0002\u0013=\u0003\u0018I\u001d:bs2c\u0005cAA\u001c'\tIq\n]!se\u0006LH\nT\n\u0004'\u0005M\u0007cBA\u001c\u000f\u0005U\u0017Q\u001b\t\u0004k\u0005]\u0017bAAmm\t!Aj\u001c8h)\t\ti-A\u0005Pa\u0006\u0013(/Y=G\rB\u0019\u0011q\u0007\f\u0003\u0013=\u0003\u0018I\u001d:bs\u001a35c\u0001\f\u0002fB9\u0011qG\u0004\u0002h\u0006\u001d\bcA\u001b\u0002j&\u0019\u00111\u001e\u001c\u0003\u000b\u0019cw.\u0019;\u0015\u0005\u0005}\u0017!C(q\u0003J\u0014\u0018-\u001f#E!\r\t9$\u0007\u0002\n\u001fB\f%O]1z\t\u0012\u001b2!GA|!\u001d\t9dBA}\u0003s\u00042!NA~\u0013\r\tiP\u000e\u0002\u0007\t>,(\r\\3\u0015\u0005\u0005E\u0018!C(q\u0003J\u0014\u0018-_\"D!\r\t9\u0004\b\u0002\n\u001fB\f%O]1z\u0007\u000e\u001b2\u0001\bB\u0005!\u001d\t9d\u0002B\u0006\u0005\u0017\u0001BA!\u0004\u0003\u00145\u0011!q\u0002\u0006\u0004\u0005#y\u0013\u0001B7bi\"LAA!\u0006\u0003\u0010\t91i\\7qY\u0016DHC\u0001B\u0002\u0003%y\u0005/\u0011:sCfLE\tE\u0002\u00028}\u0011\u0011b\u00149BeJ\f\u00170\u0013#\u0014\u0007}\u0011\t\u0003E\u0004\u00028\u001d\t\t,!?\u0015\u0005\tm\u0011!C(q\u0003J\u0014\u0018-_*E!\r\t9D\t\u0002\n\u001fB\f%O]1z'\u0012\u001b2A\tB\u0017!\u001d\t9dBAb\u0003s$\"Aa\n\u0002\u0013=\u0003\u0018I\u001d:bs2#\u0005cAA\u001cK\tIq\n]!se\u0006LH\nR\n\u0004K\te\u0002cBA\u001c\u000f\u0005U\u0017\u0011 \u000b\u0003\u0005g\t\u0011b\u00149BeJ\f\u0017P\u0012#\u0011\u0007\u0005]\u0002FA\u0005Pa\u0006\u0013(/Y=G\tN\u0019\u0001F!\u0012\u0011\u000f\u0005]r!a:\u0002zR\u0011!q\b"
)
public interface CanZipMapValues {
   static OpArray opArray(final ClassTag evidence$2) {
      return CanZipMapValues$.MODULE$.opArray(evidence$2);
   }

   static CanZipMapValues canZipMapSelf() {
      return CanZipMapValues$.MODULE$.canZipMapSelf();
   }

   Object map(final Object from, final Object from2, final Function2 fn);

   default Object map$mcDD$sp(final Object from, final Object from2, final Function2 fn) {
      return this.map(from, from2, fn);
   }

   default Object map$mcFD$sp(final Object from, final Object from2, final Function2 fn) {
      return this.map(from, from2, fn);
   }

   default Object map$mcID$sp(final Object from, final Object from2, final Function2 fn) {
      return this.map(from, from2, fn);
   }

   default Object map$mcJD$sp(final Object from, final Object from2, final Function2 fn) {
      return this.map(from, from2, fn);
   }

   default Object map$mcDF$sp(final Object from, final Object from2, final Function2 fn) {
      return this.map(from, from2, fn);
   }

   default Object map$mcFF$sp(final Object from, final Object from2, final Function2 fn) {
      return this.map(from, from2, fn);
   }

   default Object map$mcIF$sp(final Object from, final Object from2, final Function2 fn) {
      return this.map(from, from2, fn);
   }

   default Object map$mcJF$sp(final Object from, final Object from2, final Function2 fn) {
      return this.map(from, from2, fn);
   }

   default Object map$mcDI$sp(final Object from, final Object from2, final Function2 fn) {
      return this.map(from, from2, fn);
   }

   default Object map$mcFI$sp(final Object from, final Object from2, final Function2 fn) {
      return this.map(from, from2, fn);
   }

   default Object map$mcII$sp(final Object from, final Object from2, final Function2 fn) {
      return this.map(from, from2, fn);
   }

   default Object map$mcJI$sp(final Object from, final Object from2, final Function2 fn) {
      return this.map(from, from2, fn);
   }

   default Object map$mcDJ$sp(final Object from, final Object from2, final Function2 fn) {
      return this.map(from, from2, fn);
   }

   default Object map$mcFJ$sp(final Object from, final Object from2, final Function2 fn) {
      return this.map(from, from2, fn);
   }

   default Object map$mcIJ$sp(final Object from, final Object from2, final Function2 fn) {
      return this.map(from, from2, fn);
   }

   default Object map$mcJJ$sp(final Object from, final Object from2, final Function2 fn) {
      return this.map(from, from2, fn);
   }

   public static class OpArray implements CanZipMapValues {
      public final ClassTag breeze$linalg$support$CanZipMapValues$OpArray$$evidence$1;

      public Object map$mcDD$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.super.map$mcDD$sp(from, from2, fn);
      }

      public Object map$mcFD$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.super.map$mcFD$sp(from, from2, fn);
      }

      public Object map$mcID$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.super.map$mcID$sp(from, from2, fn);
      }

      public Object map$mcJD$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.super.map$mcJD$sp(from, from2, fn);
      }

      public Object map$mcDF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.super.map$mcDF$sp(from, from2, fn);
      }

      public Object map$mcFF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.super.map$mcFF$sp(from, from2, fn);
      }

      public Object map$mcIF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.super.map$mcIF$sp(from, from2, fn);
      }

      public Object map$mcJF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.super.map$mcJF$sp(from, from2, fn);
      }

      public Object map$mcDI$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.super.map$mcDI$sp(from, from2, fn);
      }

      public Object map$mcFI$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.super.map$mcFI$sp(from, from2, fn);
      }

      public Object map$mcII$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.super.map$mcII$sp(from, from2, fn);
      }

      public Object map$mcJI$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.super.map$mcJI$sp(from, from2, fn);
      }

      public Object map$mcDJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.super.map$mcDJ$sp(from, from2, fn);
      }

      public Object map$mcFJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.super.map$mcFJ$sp(from, from2, fn);
      }

      public Object map$mcIJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.super.map$mcIJ$sp(from, from2, fn);
      }

      public Object map$mcJJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.super.map$mcJJ$sp(from, from2, fn);
      }

      public Object map(final Object from, final Object from2, final Function2 fn) {
         .MODULE$.require(scala.runtime.ScalaRunTime..MODULE$.array_length(from) == scala.runtime.ScalaRunTime..MODULE$.array_length(from2), () -> "Array lengths don't match!");
         Object arr = this.breeze$linalg$support$CanZipMapValues$OpArray$$evidence$1.newArray(scala.runtime.ScalaRunTime..MODULE$.array_length(from));
         scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), scala.runtime.ScalaRunTime..MODULE$.array_length(from)).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> scala.runtime.ScalaRunTime..MODULE$.array_update(arr, i, fn.apply(scala.runtime.ScalaRunTime..MODULE$.array_apply(from, i), scala.runtime.ScalaRunTime..MODULE$.array_apply(from2, i))));
         return arr;
      }

      public double[] map$mcDD$sp(final double[] from, final double[] from2, final Function2 fn) {
         return (double[])this.map(from, from2, fn);
      }

      public float[] map$mcFD$sp(final double[] from, final double[] from2, final Function2 fn) {
         return (float[])this.map(from, from2, fn);
      }

      public int[] map$mcID$sp(final double[] from, final double[] from2, final Function2 fn) {
         return (int[])this.map(from, from2, fn);
      }

      public long[] map$mcJD$sp(final double[] from, final double[] from2, final Function2 fn) {
         return (long[])this.map(from, from2, fn);
      }

      public double[] map$mcDF$sp(final float[] from, final float[] from2, final Function2 fn) {
         return (double[])this.map(from, from2, fn);
      }

      public float[] map$mcFF$sp(final float[] from, final float[] from2, final Function2 fn) {
         return (float[])this.map(from, from2, fn);
      }

      public int[] map$mcIF$sp(final float[] from, final float[] from2, final Function2 fn) {
         return (int[])this.map(from, from2, fn);
      }

      public long[] map$mcJF$sp(final float[] from, final float[] from2, final Function2 fn) {
         return (long[])this.map(from, from2, fn);
      }

      public double[] map$mcDI$sp(final int[] from, final int[] from2, final Function2 fn) {
         return (double[])this.map(from, from2, fn);
      }

      public float[] map$mcFI$sp(final int[] from, final int[] from2, final Function2 fn) {
         return (float[])this.map(from, from2, fn);
      }

      public int[] map$mcII$sp(final int[] from, final int[] from2, final Function2 fn) {
         return (int[])this.map(from, from2, fn);
      }

      public long[] map$mcJI$sp(final int[] from, final int[] from2, final Function2 fn) {
         return (long[])this.map(from, from2, fn);
      }

      public double[] map$mcDJ$sp(final long[] from, final long[] from2, final Function2 fn) {
         return (double[])this.map(from, from2, fn);
      }

      public float[] map$mcFJ$sp(final long[] from, final long[] from2, final Function2 fn) {
         return (float[])this.map(from, from2, fn);
      }

      public int[] map$mcIJ$sp(final long[] from, final long[] from2, final Function2 fn) {
         return (int[])this.map(from, from2, fn);
      }

      public long[] map$mcJJ$sp(final long[] from, final long[] from2, final Function2 fn) {
         return (long[])this.map(from, from2, fn);
      }

      public OpArray(final ClassTag evidence$1) {
         this.breeze$linalg$support$CanZipMapValues$OpArray$$evidence$1 = evidence$1;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class OpArrayII$ extends CanZipMapValues$OpArray$mcII$sp {
      public static final OpArrayII$ MODULE$ = new OpArrayII$();

      public OpArrayII$() {
         super(scala.reflect.ClassTag..MODULE$.Int());
      }
   }

   public static class OpArraySS$ extends OpArray {
      public static final OpArraySS$ MODULE$ = new OpArraySS$();

      public OpArraySS$() {
         super(scala.reflect.ClassTag..MODULE$.Short());
      }
   }

   public static class OpArrayLL$ extends CanZipMapValues$OpArray$mcJJ$sp {
      public static final OpArrayLL$ MODULE$ = new OpArrayLL$();

      public OpArrayLL$() {
         super(scala.reflect.ClassTag..MODULE$.Long());
      }
   }

   public static class OpArrayFF$ extends CanZipMapValues$OpArray$mcFF$sp {
      public static final OpArrayFF$ MODULE$ = new OpArrayFF$();

      public OpArrayFF$() {
         super(scala.reflect.ClassTag..MODULE$.Float());
      }
   }

   public static class OpArrayDD$ extends CanZipMapValues$OpArray$mcDD$sp {
      public static final OpArrayDD$ MODULE$ = new OpArrayDD$();

      public OpArrayDD$() {
         super(scala.reflect.ClassTag..MODULE$.Double());
      }
   }

   public static class OpArrayCC$ extends OpArray {
      public static final OpArrayCC$ MODULE$ = new OpArrayCC$();

      public OpArrayCC$() {
         super(scala.reflect.ClassTag..MODULE$.apply(Complex.class));
      }
   }

   public static class OpArrayID$ extends CanZipMapValues$OpArray$mcDI$sp {
      public static final OpArrayID$ MODULE$ = new OpArrayID$();

      public OpArrayID$() {
         super(scala.reflect.ClassTag..MODULE$.Double());
      }
   }

   public static class OpArraySD$ extends OpArray {
      public static final OpArraySD$ MODULE$ = new OpArraySD$();

      public OpArraySD$() {
         super(scala.reflect.ClassTag..MODULE$.Double());
      }
   }

   public static class OpArrayLD$ extends CanZipMapValues$OpArray$mcDJ$sp {
      public static final OpArrayLD$ MODULE$ = new OpArrayLD$();

      public OpArrayLD$() {
         super(scala.reflect.ClassTag..MODULE$.Double());
      }
   }

   public static class OpArrayFD$ extends CanZipMapValues$OpArray$mcDF$sp {
      public static final OpArrayFD$ MODULE$ = new OpArrayFD$();

      public OpArrayFD$() {
         super(scala.reflect.ClassTag..MODULE$.Double());
      }
   }
}
