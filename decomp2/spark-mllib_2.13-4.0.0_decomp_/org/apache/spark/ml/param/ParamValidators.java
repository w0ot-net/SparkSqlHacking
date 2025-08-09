package org.apache.spark.ml.param;

import java.util.List;
import scala.Function1;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ut!B\b\u0011\u0011\u0003Yb!B\u000f\u0011\u0011\u0003q\u0002\"B\u0013\u0002\t\u00031\u0003BB\u0014\u0002\t\u0003\u0001\u0002\u0006C\u0003<\u0003\u0011%A\bC\u0003F\u0003\u0011\u0005a\tC\u0003N\u0003\u0011\u0005a\nC\u0003U\u0003\u0011\u0005Q\u000bC\u0003]\u0003\u0011\u0005Q\fC\u0003d\u0003\u0011\u0005A\rC\u0003d\u0003\u0011\u0005q\u000eC\u0003w\u0003\u0011\u0005q\u000f\u0003\u0004w\u0003\u0011\u0005\u00111\u0001\u0005\b\u0003?\tA\u0011AA\u0011\u0011\u001d\ty#\u0001C\u0001\u0003c\tq\u0002U1sC64\u0016\r\\5eCR|'o\u001d\u0006\u0003#I\tQ\u0001]1sC6T!a\u0005\u000b\u0002\u00055d'BA\u000b\u0017\u0003\u0015\u0019\b/\u0019:l\u0015\t9\u0002$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00023\u0005\u0019qN]4\u0004\u0001A\u0011A$A\u0007\u0002!\ty\u0001+\u0019:b[Z\u000bG.\u001b3bi>\u00148o\u0005\u0002\u0002?A\u0011\u0001eI\u0007\u0002C)\t!%A\u0003tG\u0006d\u0017-\u0003\u0002%C\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#A\u000e\u0002\u0015\u0005dw/Y=t)J,X-\u0006\u0002*_U\t!\u0006\u0005\u0003!W5B\u0014B\u0001\u0017\"\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002/_1\u0001A!\u0002\u0019\u0004\u0005\u0004\t$!\u0001+\u0012\u0005I*\u0004C\u0001\u00114\u0013\t!\u0014EA\u0004O_RD\u0017N\\4\u0011\u0005\u00012\u0014BA\u001c\"\u0005\r\te.\u001f\t\u0003AeJ!AO\u0011\u0003\u000f\t{w\u000e\\3b]\u0006Iq-\u001a;E_V\u0014G.Z\u000b\u0003{\u0011#\"AP!\u0011\u0005\u0001z\u0014B\u0001!\"\u0005\u0019!u.\u001e2mK\")!\t\u0002a\u0001\u0007\u0006)a/\u00197vKB\u0011a\u0006\u0012\u0003\u0006a\u0011\u0011\r!M\u0001\u0003OR,\"a\u0012&\u0015\u0005![\u0005\u0003\u0002\u0011,\u0013b\u0002\"A\f&\u0005\u000bA*!\u0019A\u0019\t\u000b1+\u0001\u0019\u0001 \u0002\u00151|w/\u001a:C_VtG-\u0001\u0003hi\u0016\u000bXCA(S)\t\u00016\u000b\u0005\u0003!WEC\u0004C\u0001\u0018S\t\u0015\u0001dA1\u00012\u0011\u0015ae\u00011\u0001?\u0003\taG/\u0006\u0002W3R\u0011qK\u0017\t\u0005A-B\u0006\b\u0005\u0002/3\u0012)\u0001g\u0002b\u0001c!)1l\u0002a\u0001}\u0005QQ\u000f\u001d9fe\n{WO\u001c3\u0002\t1$X)]\u000b\u0003=\u0006$\"a\u00182\u0011\t\u0001Z\u0003\r\u000f\t\u0003]\u0005$Q\u0001\r\u0005C\u0002EBQa\u0017\u0005A\u0002y\nq!\u001b8SC:<W-\u0006\u0002fQR)a-\u001b6l[B!\u0001eK49!\tq\u0003\u000eB\u00031\u0013\t\u0007\u0011\u0007C\u0003M\u0013\u0001\u0007a\bC\u0003\\\u0013\u0001\u0007a\bC\u0003m\u0013\u0001\u0007\u0001(\u0001\bm_^,'/\u00138dYV\u001c\u0018N^3\t\u000b9L\u0001\u0019\u0001\u001d\u0002\u001dU\u0004\b/\u001a:J]\u000edWo]5wKV\u0011\u0001o\u001d\u000b\u0004cR,\b\u0003\u0002\u0011,eb\u0002\"AL:\u0005\u000bAR!\u0019A\u0019\t\u000b1S\u0001\u0019\u0001 \t\u000bmS\u0001\u0019\u0001 \u0002\u000f%t\u0017I\u001d:bsV\u0011\u0001p\u001f\u000b\u0003sr\u0004B\u0001I\u0016{qA\u0011af\u001f\u0003\u0006a-\u0011\r!\r\u0005\u0006{.\u0001\rA`\u0001\bC2dwn^3e!\r\u0001sP_\u0005\u0004\u0003\u0003\t#!B!se\u0006LX\u0003BA\u0003\u0003\u0017!B!a\u0002\u0002\u000eA)\u0001eKA\u0005qA\u0019a&a\u0003\u0005\u000bAb!\u0019A\u0019\t\rud\u0001\u0019AA\b!\u0019\t\t\"a\u0007\u0002\n5\u0011\u00111\u0003\u0006\u0005\u0003+\t9\"\u0001\u0003vi&d'BAA\r\u0003\u0011Q\u0017M^1\n\t\u0005u\u00111\u0003\u0002\u0005\u0019&\u001cH/A\u0007beJ\f\u0017\u0010T3oORDw\t^\u000b\u0005\u0003G\tY\u0003\u0006\u0003\u0002&\u00055\u0002#\u0002\u0011,\u0003OA\u0004\u0003\u0002\u0011\u0000\u0003S\u00012ALA\u0016\t\u0015\u0001TB1\u00012\u0011\u0015aU\u00021\u0001?\u0003y\u0019\u0007.Z2l'&tw\r\\3Wg6+H\u000e^5D_2,XN\u001c)be\u0006l7\u000f\u0006\u0005\u00024\u0005e\u00121IA7!\r\u0001\u0013QG\u0005\u0004\u0003o\t#\u0001B+oSRDq!a\u000f\u000f\u0001\u0004\ti$A\u0003n_\u0012,G\u000eE\u0002\u001d\u0003\u007fI1!!\u0011\u0011\u0005\u0019\u0001\u0016M]1ng\"9\u0011Q\t\bA\u0002\u0005\u001d\u0013AE:j]\u001edWmQ8mk6t\u0007+\u0019:b[N\u0004b!!\u0013\u0002Z\u0005}c\u0002BA&\u0003+rA!!\u0014\u0002T5\u0011\u0011q\n\u0006\u0004\u0003#R\u0012A\u0002\u001fs_>$h(C\u0001#\u0013\r\t9&I\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\tY&!\u0018\u0003\u0007M+\u0017OC\u0002\u0002X\u0005\u0002D!!\u0019\u0002jA)A$a\u0019\u0002h%\u0019\u0011Q\r\t\u0003\u000bA\u000b'/Y7\u0011\u00079\nI\u0007B\u0006\u0002l\u0005\r\u0013\u0011!A\u0001\u0006\u0003\t$aA0%c!9\u0011q\u000e\bA\u0002\u0005E\u0014!E7vYRL7i\u001c7v[:\u0004\u0016M]1ngB1\u0011\u0011JA-\u0003g\u0002D!!\u001e\u0002zA)A$a\u0019\u0002xA\u0019a&!\u001f\u0005\u0017\u0005m\u0014QNA\u0001\u0002\u0003\u0015\t!\r\u0002\u0004?\u0012\u0012\u0004"
)
public final class ParamValidators {
   public static void checkSingleVsMultiColumnParams(final Params model, final Seq singleColumnParams, final Seq multiColumnParams) {
      ParamValidators$.MODULE$.checkSingleVsMultiColumnParams(model, singleColumnParams, multiColumnParams);
   }

   public static Function1 arrayLengthGt(final double lowerBound) {
      return ParamValidators$.MODULE$.arrayLengthGt(lowerBound);
   }

   public static Function1 inArray(final List allowed) {
      return ParamValidators$.MODULE$.inArray(allowed);
   }

   public static Function1 inArray(final Object allowed) {
      return ParamValidators$.MODULE$.inArray(allowed);
   }

   public static Function1 inRange(final double lowerBound, final double upperBound) {
      return ParamValidators$.MODULE$.inRange(lowerBound, upperBound);
   }

   public static Function1 inRange(final double lowerBound, final double upperBound, final boolean lowerInclusive, final boolean upperInclusive) {
      return ParamValidators$.MODULE$.inRange(lowerBound, upperBound, lowerInclusive, upperInclusive);
   }

   public static Function1 ltEq(final double upperBound) {
      return ParamValidators$.MODULE$.ltEq(upperBound);
   }

   public static Function1 lt(final double upperBound) {
      return ParamValidators$.MODULE$.lt(upperBound);
   }

   public static Function1 gtEq(final double lowerBound) {
      return ParamValidators$.MODULE$.gtEq(lowerBound);
   }

   public static Function1 gt(final double lowerBound) {
      return ParamValidators$.MODULE$.gt(lowerBound);
   }
}
