package org.apache.spark.ml;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.sql.Dataset;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}a!B\u0004\t\u0003\u0003\t\u0002\"B\f\u0001\t\u0003A\u0002\"\u0002\u0015\u0001\t\u0003I\u0003\"\u0002\u0015\u0001\t\u0003y\u0006\"\u0002\u0015\u0001\r\u0003a\u0007\"\u0002\u0015\u0001\t\u0003!\bbBA\f\u0001\u0019\u0005\u0013\u0011\u0004\u0002\n\u000bN$\u0018.\\1u_JT!!\u0003\u0006\u0002\u00055d'BA\u0006\r\u0003\u0015\u0019\b/\u0019:l\u0015\tia\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001f\u0005\u0019qN]4\u0004\u0001U\u0011!\u0003H\n\u0003\u0001M\u0001\"\u0001F\u000b\u000e\u0003!I!A\u0006\u0005\u0003\u001bAK\u0007/\u001a7j]\u0016\u001cF/Y4f\u0003\u0019a\u0014N\\5u}Q\t\u0011\u0004E\u0002\u0015\u0001i\u0001\"a\u0007\u000f\r\u0001\u0011)Q\u0004\u0001b\u0001=\t\tQ*\u0005\u0002 KA\u0011\u0001eI\u0007\u0002C)\t!%A\u0003tG\u0006d\u0017-\u0003\u0002%C\t9aj\u001c;iS:<\u0007c\u0001\u000b'5%\u0011q\u0005\u0003\u0002\u0006\u001b>$W\r\\\u0001\u0004M&$H\u0003\u0002\u000e+u\u0019CQa\u000b\u0002A\u00021\nq\u0001Z1uCN,G\u000f\r\u0002.iA\u0019a&M\u001a\u000e\u0003=R!\u0001\r\u0006\u0002\u0007M\fH.\u0003\u00023_\t9A)\u0019;bg\u0016$\bCA\u000e5\t%)$&!A\u0001\u0002\u000b\u0005aGA\u0002`IE\n\"aH\u001c\u0011\u0005\u0001B\u0014BA\u001d\"\u0005\r\te.\u001f\u0005\u0006w\t\u0001\r\u0001P\u0001\u000fM&\u00148\u000f\u001e)be\u0006l\u0007+Y5sa\tiD\tE\u0002?\u0003\u000ek\u0011a\u0010\u0006\u0003\u0001\"\tQ\u0001]1sC6L!AQ \u0003\u0013A\u000b'/Y7QC&\u0014\bCA\u000eE\t%)%(!A\u0001\u0002\u000b\u0005aGA\u0002`IIBQa\u0012\u0002A\u0002!\u000bqb\u001c;iKJ\u0004\u0016M]1n!\u0006L'o\u001d\t\u0004A%[\u0015B\u0001&\"\u0005)a$/\u001a9fCR,GM\u0010\u0019\u0003\u0019:\u00032AP!N!\tYb\nB\u0005P\r\u0006\u0005\t\u0011!B\u0001m\t\u0019q\fJ\u001a)\u0007\t\tv\u000b\u0005\u0002S+6\t1K\u0003\u0002U\u0015\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Y\u001b&!B*j]\u000e,\u0017%\u0001-\u0002\u000bIr\u0003G\f\u0019)\u0005\tQ\u0006CA.^\u001b\u0005a&B\u0001+\"\u0013\tqFLA\u0004wCJ\f'oZ:\u0015\u0007i\u0001g\rC\u0003,\u0007\u0001\u0007\u0011\r\r\u0002cIB\u0019a&M2\u0011\u0005m!G!C3a\u0003\u0003\u0005\tQ!\u00017\u0005\ryF\u0005\u000e\u0005\u0006O\u000e\u0001\r\u0001[\u0001\ta\u0006\u0014\u0018-\\'baB\u0011a([\u0005\u0003U~\u0012\u0001\u0002U1sC6l\u0015\r\u001d\u0015\u0004\u0007E;FC\u0001\u000en\u0011\u0015YC\u00011\u0001oa\ty\u0017\u000fE\u0002/cA\u0004\"aG9\u0005\u0013Il\u0017\u0011!A\u0001\u0006\u00031$aA0%k!\u001aA!U,\u0015\u000bU\f\u0019!a\u0004\u0011\u0007Yt(D\u0004\u0002xy:\u0011\u0001p_\u0007\u0002s*\u0011!\u0010E\u0001\u0007yI|w\u000e\u001e \n\u0003\tJ!!`\u0011\u0002\u000fA\f7m[1hK&\u0019q0!\u0001\u0003\u0007M+\u0017O\u0003\u0002~C!11&\u0002a\u0001\u0003\u000b\u0001D!a\u0002\u0002\fA!a&MA\u0005!\rY\u00121\u0002\u0003\f\u0003\u001b\t\u0019!!A\u0001\u0002\u000b\u0005aGA\u0002`IYBq!!\u0005\u0006\u0001\u0004\t\u0019\"A\u0005qCJ\fW.T1qgB\u0019aO 5)\u0007\u0015\tv+\u0001\u0003d_BLHcA\r\u0002\u001c!1\u0011Q\u0004\u0004A\u0002!\fQ!\u001a=ue\u0006\u0004"
)
public abstract class Estimator extends PipelineStage {
   public Model fit(final Dataset dataset, final ParamPair firstParamPair, final ParamPair... otherParamPairs) {
      return this.fit(dataset, firstParamPair, (Seq).MODULE$.wrapRefArray(otherParamPairs));
   }

   public Model fit(final Dataset dataset, final ParamPair firstParamPair, final Seq otherParamPairs) {
      ParamMap map = (new ParamMap()).put((Seq).MODULE$.wrapRefArray(new ParamPair[]{firstParamPair})).put(otherParamPairs);
      return this.fit(dataset, map);
   }

   public Model fit(final Dataset dataset, final ParamMap paramMap) {
      return this.copy(paramMap).fit(dataset);
   }

   public abstract Model fit(final Dataset dataset);

   public Seq fit(final Dataset dataset, final Seq paramMaps) {
      return (Seq)paramMaps.map((x$1) -> this.fit(dataset, x$1));
   }

   public abstract Estimator copy(final ParamMap extra);

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
