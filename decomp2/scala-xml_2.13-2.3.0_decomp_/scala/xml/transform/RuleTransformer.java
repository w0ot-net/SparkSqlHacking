package scala.xml.transform;

import java.lang.invoke.SerializedLambda;
import scala.collection.IterableOnceOps;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.xml.Node;

@ScalaSignature(
   bytes = "\u0006\u0005M2AAB\u0004\u0001\u001d!A1\u0003\u0001B\u0001B\u0003%A\u0003C\u0003\u001c\u0001\u0011\u0005A\u0004C\u0004 \u0001\t\u0007I\u0011\u0002\u0011\t\r)\u0002\u0001\u0015!\u0003\"\u0011\u0015A\u0001\u0001\"\u0011,\u0005=\u0011V\u000f\\3Ue\u0006t7OZ8s[\u0016\u0014(B\u0001\u0005\n\u0003%!(/\u00198tM>\u0014XN\u0003\u0002\u000b\u0017\u0005\u0019\u00010\u001c7\u000b\u00031\tQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001\u001fA\u0011\u0001#E\u0007\u0002\u000f%\u0011!c\u0002\u0002\u0011\u0005\u0006\u001c\u0018n\u0019+sC:\u001chm\u001c:nKJ\fQA];mKN\u00042!\u0006\f\u0019\u001b\u0005Y\u0011BA\f\f\u0005)a$/\u001a9fCR,GM\u0010\t\u0003!eI!AG\u0004\u0003\u0017I+wO]5uKJ+H.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005uq\u0002C\u0001\t\u0001\u0011\u0015\u0019\"\u00011\u0001\u0015\u00031!(/\u00198tM>\u0014X.\u001a:t+\u0005\t\u0003c\u0001\u0012&O5\t1E\u0003\u0002%\u0017\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005\u0019\u001a#aA*fcB\u0011\u0001\u0003K\u0005\u0003S\u001d\u0011!CT3ti&tw\r\u0016:b]N4wN]7fe\u0006iAO]1og\u001a|'/\\3sg\u0002\"\"\u0001L\u0019\u0011\u0007\t*S\u0006\u0005\u0002/_5\t\u0011\"\u0003\u00021\u0013\t!aj\u001c3f\u0011\u0015\u0011T\u00011\u0001.\u0003\u0005q\u0007"
)
public class RuleTransformer extends BasicTransformer {
   private final Seq transformers;

   private Seq transformers() {
      return this.transformers;
   }

   public Seq transform(final Node n) {
      return (Seq)(this.transformers().isEmpty() ? n : (Seq)((IterableOnceOps)this.transformers().tail()).foldLeft(((NestingTransformer)this.transformers().head()).transform(n), (res, transformer) -> transformer.transform(res)));
   }

   public RuleTransformer(final scala.collection.immutable.Seq rules) {
      this.transformers = (Seq)rules.map((x$1) -> new NestingTransformer(x$1));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
