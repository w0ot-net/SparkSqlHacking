package org.apache.spark.sql.expressions;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Column$;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.internal.InvokeInlineUserDefinedFunction;
import org.apache.spark.sql.internal.InvokeInlineUserDefinedFunction$;
import org.apache.spark.sql.internal.UserDefinedFunctionLike;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.Function0;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;

/** @deprecated */
@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\u0005ea!\u0002\u0007\u000e\u0003\u0003A\u0002\"B\u0019\u0001\t\u0003\u0011\u0004\"B\u001b\u0001\r\u00031\u0004\"B\u001f\u0001\r\u00031\u0004\"\u0002 \u0001\r\u0003y\u0004\"B\"\u0001\r\u0003!\u0005\"\u0002%\u0001\r\u0003I\u0005\"\u0002*\u0001\r\u0003\u0019\u0006\"B.\u0001\r\u0003a\u0006\"B1\u0001\r\u0003\u0011\u0007\"B4\u0001\t\u0003A\u0007\"\u0002=\u0001\t\u0003I(\u0001H+tKJ$UMZ5oK\u0012\fum\u001a:fO\u0006$XMR;oGRLwN\u001c\u0006\u0003\u001d=\t1\"\u001a=qe\u0016\u001c8/[8og*\u0011\u0001#E\u0001\u0004gFd'B\u0001\n\u0014\u0003\u0015\u0019\b/\u0019:l\u0015\t!R#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002-\u0005\u0019qN]4\u0004\u0001M!\u0001!G\u0010,!\tQR$D\u0001\u001c\u0015\u0005a\u0012!B:dC2\f\u0017B\u0001\u0010\u001c\u0005\u0019\te.\u001f*fMB\u0011\u0001\u0005\u000b\b\u0003C\u0019r!AI\u0013\u000e\u0003\rR!\u0001J\f\u0002\rq\u0012xn\u001c;?\u0013\u0005a\u0012BA\u0014\u001c\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u000b\u0016\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\u001dZ\u0002C\u0001\u00170\u001b\u0005i#B\u0001\u0018\u0010\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u0019.\u0005])6/\u001a:EK\u001aLg.\u001a3Gk:\u001cG/[8o\u0019&\\W-\u0001\u0004=S:LGO\u0010\u000b\u0002gA\u0011A\u0007A\u0007\u0002\u001b\u0005Y\u0011N\u001c9viN\u001b\u0007.Z7b+\u00059\u0004C\u0001\u001d<\u001b\u0005I$B\u0001\u001e\u0010\u0003\u0015!\u0018\u0010]3t\u0013\ta\u0014H\u0001\u0006TiJ,8\r\u001e+za\u0016\fABY;gM\u0016\u00148k\u00195f[\u0006\f\u0001\u0002Z1uCRK\b/Z\u000b\u0002\u0001B\u0011\u0001(Q\u0005\u0003\u0005f\u0012\u0001\u0002R1uCRK\b/Z\u0001\u000eI\u0016$XM]7j]&\u001cH/[2\u0016\u0003\u0015\u0003\"A\u0007$\n\u0005\u001d[\"a\u0002\"p_2,\u0017M\\\u0001\u000bS:LG/[1mSj,GC\u0001&N!\tQ2*\u0003\u0002M7\t!QK\\5u\u0011\u0015qe\u00011\u0001P\u0003\u0019\u0011WO\u001a4feB\u0011A\u0007U\u0005\u0003#6\u0011\u0001$T;uC\ndW-Q4he\u0016<\u0017\r^5p]\n+hMZ3s\u0003\u0019)\b\u000fZ1uKR\u0019!\nV+\t\u000b9;\u0001\u0019A(\t\u000bY;\u0001\u0019A,\u0002\u000b%t\u0007/\u001e;\u0011\u0005aKV\"A\b\n\u0005i{!a\u0001*po\u0006)Q.\u001a:hKR\u0019!*X0\t\u000byC\u0001\u0019A(\u0002\u000f\t,hMZ3sc!)\u0001\r\u0003a\u0001/\u00069!-\u001e4gKJ\u0014\u0014\u0001C3wC2,\u0018\r^3\u0015\u0005\r4\u0007C\u0001\u000ee\u0013\t)7DA\u0002B]fDQAT\u0005A\u0002]\u000bQ!\u00199qYf$\"!\u001b7\u0011\u0005aS\u0017BA6\u0010\u0005\u0019\u0019u\u000e\\;n]\")QN\u0003a\u0001]\u0006)Q\r\u001f9sgB\u0019!d\\5\n\u0005A\\\"A\u0003\u001fsKB,\u0017\r^3e}!\u0012!B\u001d\t\u0003gZl\u0011\u0001\u001e\u0006\u0003kn\t!\"\u00198o_R\fG/[8o\u0013\t9HOA\u0004wCJ\f'oZ:\u0002\u0011\u0011L7\u000f^5oGR$\"!\u001b>\t\u000b5\\\u0001\u0019\u00018)\u0005-\u0011\bF\u0001\u0001~!\rq\u0018\u0011A\u0007\u0002\u007f*\u0011Q/E\u0005\u0004\u0003\u0007y(AB*uC\ndW\rK\u0006\u0001\u0003\u000f\ti!a\u0004\u0002\u0014\u0005U\u0001c\u0001\u000e\u0002\n%\u0019\u00111B\u000e\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0005\u0005E\u0011AX!hOJ,w-\u0019;penKe\n\f\u0011C+\u001ac\u0003eT+U;\u0002\u001a\bn\\;mI\u0002rwn\u001e\u0011cK\u0002\u0012XmZ5ti\u0016\u0014X\r\u001a\u0011bg\u0002\n\u0007%\u0016#GAYL\u0017\r\t;iK\u00022WO\\2uS>t7OL;eC\u001aD\u0013mZ4*A5,G\u000f[8e]\u0005)1/\u001b8dK\u0006\u0012\u0011qC\u0001\u0006g9\u0002d\u0006\r"
)
public abstract class UserDefinedAggregateFunction implements Serializable, UserDefinedFunctionLike {
   public String name() {
      return UserDefinedFunctionLike.name$(this);
   }

   public Column apply(final Column... exprs) {
      return this.apply((Seq).MODULE$.wrapRefArray(exprs));
   }

   public Column distinct(final Column... exprs) {
      return this.distinct((Seq).MODULE$.wrapRefArray(exprs));
   }

   public abstract StructType inputSchema();

   public abstract StructType bufferSchema();

   public abstract DataType dataType();

   public abstract boolean deterministic();

   public abstract void initialize(final MutableAggregationBuffer buffer);

   public abstract void update(final MutableAggregationBuffer buffer, final Row input);

   public abstract void merge(final MutableAggregationBuffer buffer1, final Row buffer2);

   public abstract Object evaluate(final Row buffer);

   public Column apply(final Seq exprs) {
      return Column$.MODULE$.apply((Function0)(() -> new InvokeInlineUserDefinedFunction(this, (Seq)exprs.map((x$1) -> x$1.node()), InvokeInlineUserDefinedFunction$.MODULE$.apply$default$3(), InvokeInlineUserDefinedFunction$.MODULE$.apply$default$4())));
   }

   public Column distinct(final Seq exprs) {
      return Column$.MODULE$.apply((Function0)(() -> new InvokeInlineUserDefinedFunction(this, (Seq)exprs.map((x$2) -> x$2.node()), true, InvokeInlineUserDefinedFunction$.MODULE$.apply$default$4())));
   }

   public UserDefinedAggregateFunction() {
      UserDefinedFunctionLike.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
