package org.apache.spark.util.random;

import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=<aAB\u0004\t\u0002-\tbAB\n\b\u0011\u0003YA\u0003C\u0003\u001c\u0003\u0011\u0005Q\u0004C\u0003\u001f\u0003\u0011\u0005q\u0004C\u0004T\u0003E\u0005I\u0011\u0001+\t\u000b\u0005\fA\u0011\u00012\u0002\u001bM\u000bW\u000e\u001d7j]\u001e,F/\u001b7t\u0015\tA\u0011\"\u0001\u0004sC:$w.\u001c\u0006\u0003\u0015-\tA!\u001e;jY*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014x\r\u0005\u0002\u0013\u00035\tqAA\u0007TC6\u0004H.\u001b8h+RLGn]\n\u0003\u0003U\u0001\"AF\r\u000e\u0003]Q\u0011\u0001G\u0001\u0006g\u000e\fG.Y\u0005\u00035]\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003E\tqC]3tKJ4x.\u001b:TC6\u0004H.Z!oI\u000e{WO\u001c;\u0016\u0005\u0001RC\u0003B\u0011?\u0019F#\"A\t\u001c\u0011\tY\u0019SeM\u0005\u0003I]\u0011a\u0001V;qY\u0016\u0014\u0004c\u0001\f'Q%\u0011qe\u0006\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003S)b\u0001\u0001B\u0003,\u0007\t\u0007AFA\u0001U#\ti\u0003\u0007\u0005\u0002\u0017]%\u0011qf\u0006\u0002\b\u001d>$\b.\u001b8h!\t1\u0012'\u0003\u00023/\t\u0019\u0011I\\=\u0011\u0005Y!\u0014BA\u001b\u0018\u0005\u0011auN\\4\t\u000f]\u001a\u0011\u0011!a\u0002q\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\u0007eb\u0004&D\u0001;\u0015\tYt#A\u0004sK\u001adWm\u0019;\n\u0005uR$\u0001C\"mCN\u001cH+Y4\t\u000b}\u001a\u0001\u0019\u0001!\u0002\u000b%t\u0007/\u001e;\u0011\u0007\u0005K\u0005F\u0004\u0002C\u000f:\u00111IR\u0007\u0002\t*\u0011Q\tH\u0001\u0007yI|w\u000e\u001e \n\u0003aI!\u0001S\f\u0002\u000fA\f7m[1hK&\u0011!j\u0013\u0002\t\u0013R,'/\u0019;pe*\u0011\u0001j\u0006\u0005\u0006\u001b\u000e\u0001\rAT\u0001\u0002WB\u0011acT\u0005\u0003!^\u00111!\u00138u\u0011\u001d\u00116\u0001%AA\u0002M\nAa]3fI\u0006\t#/Z:feZ|\u0017N]*b[BdW-\u00118e\u0007>,h\u000e\u001e\u0013eK\u001a\fW\u000f\u001c;%gU\u0011Q\u000bY\u000b\u0002-*\u00121gV\u0016\u00021B\u0011\u0011LX\u0007\u00025*\u00111\fX\u0001\nk:\u001c\u0007.Z2lK\u0012T!!X\f\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002`5\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000b-\"!\u0019\u0001\u0017\u00029\r|W\u000e];uK\u001a\u0013\u0018m\u0019;j_:4uN]*b[BdWmU5{KR!1M\u001a5k!\t1B-\u0003\u0002f/\t1Ai\\;cY\u0016DQaZ\u0003A\u00029\u000bAc]1na2,7+\u001b>f\u0019><XM\u001d\"pk:$\u0007\"B5\u0006\u0001\u0004\u0019\u0014!\u0002;pi\u0006d\u0007\"B6\u0006\u0001\u0004a\u0017aD<ji\"\u0014V\r\u001d7bG\u0016lWM\u001c;\u0011\u0005Yi\u0017B\u00018\u0018\u0005\u001d\u0011un\u001c7fC:\u0004"
)
public final class SamplingUtils {
   public static double computeFractionForSampleSize(final int sampleSizeLowerBound, final long total, final boolean withReplacement) {
      return SamplingUtils$.MODULE$.computeFractionForSampleSize(sampleSizeLowerBound, total, withReplacement);
   }

   public static long reservoirSampleAndCount$default$3() {
      return SamplingUtils$.MODULE$.reservoirSampleAndCount$default$3();
   }

   public static Tuple2 reservoirSampleAndCount(final Iterator input, final int k, final long seed, final ClassTag evidence$1) {
      return SamplingUtils$.MODULE$.reservoirSampleAndCount(input, k, seed, evidence$1);
   }
}
