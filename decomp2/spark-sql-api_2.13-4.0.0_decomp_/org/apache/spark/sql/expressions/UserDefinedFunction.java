package org.apache.spark.sql.expressions;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Column$;
import org.apache.spark.sql.internal.InvokeInlineUserDefinedFunction;
import org.apache.spark.sql.internal.InvokeInlineUserDefinedFunction$;
import org.apache.spark.sql.internal.UserDefinedFunctionLike;
import scala.Function0;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005e3Q\u0001C\u0005\u0002\"QAQ!\t\u0001\u0005\u0002\tBQ!\n\u0001\u0007\u0002\u0019BQA\u000b\u0001\u0007\u0002\u0019BQa\u000b\u0001\u0005\u00021BQ!\u0010\u0001\u0007\u0002yBQ\u0001\u0014\u0001\u0007\u0002\tBQ!\u0014\u0001\u0007\u0002\t\u00121#V:fe\u0012+g-\u001b8fI\u001a+hn\u0019;j_:T!AC\u0006\u0002\u0017\u0015D\bO]3tg&|gn\u001d\u0006\u0003\u00195\t1a]9m\u0015\tqq\"A\u0003ta\u0006\u00148N\u0003\u0002\u0011#\u00051\u0011\r]1dQ\u0016T\u0011AE\u0001\u0004_J<7\u0001A\n\u0004\u0001UY\u0002C\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"AB!osJ+g\r\u0005\u0002\u001d?5\tQD\u0003\u0002\u001f\u0017\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002!;\t9Rk]3s\t\u00164\u0017N\\3e\rVt7\r^5p]2K7.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\r\u0002\"\u0001\n\u0001\u000e\u0003%\t\u0001B\\;mY\u0006\u0014G.Z\u000b\u0002OA\u0011a\u0003K\u0005\u0003S]\u0011qAQ8pY\u0016\fg.A\u0007eKR,'/\\5oSN$\u0018nY\u0001\u0006CB\u0004H.\u001f\u000b\u0003[E\u0002\"AL\u0018\u000e\u0003-I!\u0001M\u0006\u0003\r\r{G.^7o\u0011\u0015\u0011D\u00011\u00014\u0003\u0015)\u0007\u0010\u001d:t!\r1B'L\u0005\u0003k]\u0011!\u0002\u0010:fa\u0016\fG/\u001a3?Q\t!q\u0007\u0005\u00029w5\t\u0011H\u0003\u0002;/\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005qJ$a\u0002<be\u0006\u0014xm]\u0001\to&$\bNT1nKR\u00111e\u0010\u0005\u0006\u0001\u0016\u0001\r!Q\u0001\u0005]\u0006lW\r\u0005\u0002C\u0013:\u00111i\u0012\t\u0003\t^i\u0011!\u0012\u0006\u0003\rN\ta\u0001\u0010:p_Rt\u0014B\u0001%\u0018\u0003\u0019\u0001&/\u001a3fM&\u0011!j\u0013\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005!;\u0012!D1t\u001d>tg*\u001e7mC\ndW-\u0001\nbg:{g\u000eZ3uKJl\u0017N\\5ti&\u001c\u0017f\u0001\u0001P#&\u0011\u0001+\u0003\u0002\u0019'B\f'o[+tKJ$UMZ5oK\u00124UO\\2uS>t\u0017B\u0001*\n\u0005U)6/\u001a:EK\u001aLg.\u001a3BO\u001e\u0014XmZ1u_JD#\u0001\u0001+\u0011\u0005U;V\"\u0001,\u000b\u0005ij\u0011B\u0001-W\u0005\u0019\u0019F/\u00192mK\u0002"
)
public abstract class UserDefinedFunction implements UserDefinedFunctionLike {
   public String name() {
      return UserDefinedFunctionLike.name$(this);
   }

   public Column apply(final Column... exprs) {
      return this.apply((Seq).MODULE$.wrapRefArray(exprs));
   }

   public abstract boolean nullable();

   public abstract boolean deterministic();

   public Column apply(final Seq exprs) {
      return Column$.MODULE$.apply((Function0)(() -> new InvokeInlineUserDefinedFunction(this, (Seq)exprs.map((x$1) -> x$1.node()), InvokeInlineUserDefinedFunction$.MODULE$.apply$default$3(), InvokeInlineUserDefinedFunction$.MODULE$.apply$default$4())));
   }

   public abstract UserDefinedFunction withName(final String name);

   public abstract UserDefinedFunction asNonNullable();

   public abstract UserDefinedFunction asNondeterministic();

   public UserDefinedFunction() {
      UserDefinedFunctionLike.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
