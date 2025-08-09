package org.apache.spark.deploy;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.apache.spark.SparkConf;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u000513Q\u0001B\u0003\u0001\u000b5A\u0001\u0002\u0007\u0001\u0003\u0002\u0003\u0006IA\u0007\u0005\u0006c\u0001!\tA\r\u0005\u0006s\u0001!\tE\u000f\u0002\u0014\u0015\u00064\u0018-T1j]\u0006\u0003\b\u000f\\5dCRLwN\u001c\u0006\u0003\r\u001d\ta\u0001Z3qY>L(B\u0001\u0005\n\u0003\u0015\u0019\b/\u0019:l\u0015\tQ1\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0019\u0005\u0019qN]4\u0014\u0007\u0001qA\u0003\u0005\u0002\u0010%5\t\u0001CC\u0001\u0012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0002C\u0001\u0004B]f\u0014VM\u001a\t\u0003+Yi\u0011!B\u0005\u0003/\u0015\u0011\u0001c\u00159be.\f\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8\u0002\u000b-d\u0017m]:\u0004\u0001A\u00121\u0004\u000b\t\u00049\r2cBA\u000f\"!\tq\u0002#D\u0001 \u0015\t\u0001\u0013$\u0001\u0004=e>|GOP\u0005\u0003EA\ta\u0001\u0015:fI\u00164\u0017B\u0001\u0013&\u0005\u0015\u0019E.Y:t\u0015\t\u0011\u0003\u0003\u0005\u0002(Q1\u0001A!C\u0015\u0002\u0003\u0003\u0005\tQ!\u0001+\u0005\ryF%M\t\u0003W9\u0002\"a\u0004\u0017\n\u00055\u0002\"a\u0002(pi\"Lgn\u001a\t\u0003\u001f=J!\u0001\r\t\u0003\u0007\u0005s\u00170\u0001\u0004=S:LGO\u0010\u000b\u0003gQ\u0002\"!\u0006\u0001\t\u000ba\u0011\u0001\u0019A\u001b1\u0005YB\u0004c\u0001\u000f$oA\u0011q\u0005\u000f\u0003\nSQ\n\t\u0011!A\u0003\u0002)\nQa\u001d;beR$2a\u000f G!\tyA(\u0003\u0002>!\t!QK\\5u\u0011\u0015y4\u00011\u0001A\u0003\u0011\t'oZ:\u0011\u0007=\t5)\u0003\u0002C!\t)\u0011I\u001d:bsB\u0011A\u0004R\u0005\u0003\u000b\u0016\u0012aa\u0015;sS:<\u0007\"B$\u0004\u0001\u0004A\u0015\u0001B2p]\u001a\u0004\"!\u0013&\u000e\u0003\u001dI!aS\u0004\u0003\u0013M\u0003\u0018M]6D_:4\u0007"
)
public class JavaMainApplication implements SparkApplication {
   private final Class klass;

   public void start(final String[] args, final SparkConf conf) {
      Method mainMethod = this.klass.getMethod("main", (new String[0]).getClass());
      if (!Modifier.isStatic(mainMethod.getModifiers())) {
         throw new IllegalStateException("The main method in the given main class must be static");
      } else {
         Map sysProps = .MODULE$.wrapRefArray((Object[])conf.getAll()).toMap(scala..less.colon.less..MODULE$.refl());
         sysProps.foreach((x0$1) -> {
            $anonfun$start$1(x0$1);
            return BoxedUnit.UNIT;
         });
         mainMethod.invoke((Object)null, args);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$start$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         String v = (String)x0$1._2();
         scala.sys.package..MODULE$.props().update(k, v);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public JavaMainApplication(final Class klass) {
      this.klass = klass;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
