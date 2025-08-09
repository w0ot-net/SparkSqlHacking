package org.apache.spark.ml;

import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.sql.Dataset;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\ra!\u0002\u0004\b\u0003\u0003\u0001\u0002\"B\u000b\u0001\t\u00031\u0002\"\u0002\r\u0001\t\u0003I\u0002\"\u0002\r\u0001\t\u0003A\u0007\"\u0002\r\u0001\r\u0003)\b\"B?\u0001\r\u0003r(a\u0003+sC:\u001chm\u001c:nKJT!\u0001C\u0005\u0002\u00055d'B\u0001\u0006\f\u0003\u0015\u0019\b/\u0019:l\u0015\taQ\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001d\u0005\u0019qN]4\u0004\u0001M\u0011\u0001!\u0005\t\u0003%Mi\u0011aB\u0005\u0003)\u001d\u0011Q\u0002U5qK2Lg.Z*uC\u001e,\u0017A\u0002\u001fj]&$h\bF\u0001\u0018!\t\u0011\u0002!A\u0005ue\u0006t7OZ8s[R!!DL\"P!\tY2F\u0004\u0002\u001dQ9\u0011QD\n\b\u0003=\u0015r!a\b\u0013\u000f\u0005\u0001\u001aS\"A\u0011\u000b\u0005\tz\u0011A\u0002\u001fs_>$h(C\u0001\u000f\u0013\taQ\"\u0003\u0002\u000b\u0017%\u0011q%C\u0001\u0004gFd\u0017BA\u0015+\u0003\u001d\u0001\u0018mY6bO\u0016T!aJ\u0005\n\u00051j#!\u0003#bi\u00064%/Y7f\u0015\tI#\u0006C\u00030\u0005\u0001\u0007\u0001'A\u0004eCR\f7/\u001a;1\u0005E:\u0004c\u0001\u001a4k5\t!&\u0003\u00025U\t9A)\u0019;bg\u0016$\bC\u0001\u001c8\u0019\u0001!\u0011\u0002\u000f\u0018\u0002\u0002\u0003\u0005)\u0011A\u001d\u0003\u0007}#\u0013'\u0005\u0002;\u0001B\u00111HP\u0007\u0002y)\tQ(A\u0003tG\u0006d\u0017-\u0003\u0002@y\t9aj\u001c;iS:<\u0007CA\u001eB\u0013\t\u0011EHA\u0002B]fDQ\u0001\u0012\u0002A\u0002\u0015\u000baBZ5sgR\u0004\u0016M]1n!\u0006L'\u000f\r\u0002G\u001bB\u0019qI\u0013'\u000e\u0003!S!!S\u0004\u0002\u000bA\f'/Y7\n\u0005-C%!\u0003)be\u0006l\u0007+Y5s!\t1T\nB\u0005O\u0007\u0006\u0005\t\u0011!B\u0001s\t\u0019q\f\n\u001a\t\u000bA\u0013\u0001\u0019A)\u0002\u001f=$\b.\u001a:QCJ\fW\u000eU1jeN\u00042a\u000f*U\u0013\t\u0019FH\u0001\u0006=e\u0016\u0004X-\u0019;fIz\u0002$!V,\u0011\u0007\u001dSe\u000b\u0005\u00027/\u0012I\u0001lTA\u0001\u0002\u0003\u0015\t!\u000f\u0002\u0004?\u0012\u001a\u0004f\u0001\u0002[AB\u00111LX\u0007\u00029*\u0011Q,C\u0001\u000bC:tw\u000e^1uS>t\u0017BA0]\u0005\u0015\u0019\u0016N\\2fC\u0005\t\u0017!\u0002\u001a/a9\u0002\u0004F\u0001\u0002d!\t!g-D\u0001f\u0015\tiF(\u0003\u0002hK\n9a/\u0019:be\u001e\u001cHc\u0001\u000ej_\")qf\u0001a\u0001UB\u00121.\u001c\t\u0004eMb\u0007C\u0001\u001cn\t%q\u0017.!A\u0001\u0002\u000b\u0005\u0011HA\u0002`IQBQ\u0001]\u0002A\u0002E\f\u0001\u0002]1sC6l\u0015\r\u001d\t\u0003\u000fJL!a\u001d%\u0003\u0011A\u000b'/Y7NCBD3a\u0001.a)\tQb\u000fC\u00030\t\u0001\u0007q\u000f\r\u0002yuB\u0019!gM=\u0011\u0005YRH!C>w\u0003\u0003\u0005\tQ!\u0001:\u0005\ryF%\u000e\u0015\u0004\ti\u0003\u0017\u0001B2paf$\"aF@\t\r\u0005\u0005Q\u00011\u0001r\u0003\u0015)\u0007\u0010\u001e:b\u0001"
)
public abstract class Transformer extends PipelineStage {
   public Dataset transform(final Dataset dataset, final ParamPair firstParamPair, final ParamPair... otherParamPairs) {
      return this.transform(dataset, firstParamPair, (Seq).MODULE$.wrapRefArray(otherParamPairs));
   }

   public Dataset transform(final Dataset dataset, final ParamPair firstParamPair, final Seq otherParamPairs) {
      ParamMap map = (new ParamMap()).put((Seq).MODULE$.wrapRefArray(new ParamPair[]{firstParamPair})).put(otherParamPairs);
      return this.transform(dataset, map);
   }

   public Dataset transform(final Dataset dataset, final ParamMap paramMap) {
      return this.copy(paramMap).transform(dataset);
   }

   public abstract Dataset transform(final Dataset dataset);

   public abstract Transformer copy(final ParamMap extra);
}
