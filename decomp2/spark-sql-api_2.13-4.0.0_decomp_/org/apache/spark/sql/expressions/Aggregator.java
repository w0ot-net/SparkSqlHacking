package org.apache.spark.sql.expressions;

import java.io.Serializable;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.TypedColumn;
import org.apache.spark.sql.internal.InvokeInlineUserDefinedFunction;
import org.apache.spark.sql.internal.InvokeInlineUserDefinedFunction$;
import org.apache.spark.sql.internal.UserDefinedFunctionLike;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)4Q!\u0003\u0006\u0002\u0002UAQa\f\u0001\u0005\u0002ABQ\u0001\u0012\u0001\u0007\u0002\u0015CQA\u0012\u0001\u0007\u0002\u001dCQ\u0001\u0014\u0001\u0007\u00025CQA\u0015\u0001\u0007\u0002MCQA\u0016\u0001\u0007\u0002]CQ\u0001\u0018\u0001\u0007\u0002uCQa\u0018\u0001\u0005\u0002\u0001\u0014!\"Q4he\u0016<\u0017\r^8s\u0015\tYA\"A\u0006fqB\u0014Xm]:j_:\u001c(BA\u0007\u000f\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003\u001fA\tQa\u001d9be.T!!\u0005\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0012aA8sO\u000e\u0001Q\u0003\u0002\f6\u007f\t\u001bB\u0001A\f\u001eSA\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\u0004\"A\b\u0014\u000f\u0005}!cB\u0001\u0011$\u001b\u0005\t#B\u0001\u0012\u0015\u0003\u0019a$o\\8u}%\t!$\u0003\u0002&3\u00059\u0001/Y2lC\u001e,\u0017BA\u0014)\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t)\u0013\u0004\u0005\u0002+[5\t1F\u0003\u0002-\u0019\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002/W\t9Rk]3s\t\u00164\u0017N\\3e\rVt7\r^5p]2K7.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003E\u0002RA\r\u00014}\u0005k\u0011A\u0003\t\u0003iUb\u0001\u0001\u0002\u00047\u0001!\u0015\ra\u000e\u0002\u0003\u0013:\u000b\"\u0001O\u001e\u0011\u0005aI\u0014B\u0001\u001e\u001a\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0007\u001f\n\u0005uJ\"aA!osB\u0011Ag\u0010\u0003\u0006\u0001\u0002\u0011\ra\u000e\u0002\u0004\u0005V3\u0005C\u0001\u001bC\t\u0015\u0019\u0005A1\u00018\u0005\ryU\u000bV\u0001\u0005u\u0016\u0014x.F\u0001?\u0003\u0019\u0011X\rZ;dKR\u0019a\b\u0013&\t\u000b%\u001b\u0001\u0019\u0001 \u0002\u0003\tDQaS\u0002A\u0002M\n\u0011!Y\u0001\u0006[\u0016\u0014x-\u001a\u000b\u0004}9\u0003\u0006\"B(\u0005\u0001\u0004q\u0014A\u000122\u0011\u0015\tF\u00011\u0001?\u0003\t\u0011''\u0001\u0004gS:L7\u000f\u001b\u000b\u0003\u0003RCQ!V\u0003A\u0002y\n\u0011B]3ek\u000e$\u0018n\u001c8\u0002\u001b\t,hMZ3s\u000b:\u001cw\u000eZ3s+\u0005A\u0006cA-[}5\tA\"\u0003\u0002\\\u0019\t9QI\\2pI\u0016\u0014\u0018!D8viB,H/\u00128d_\u0012,'/F\u0001_!\rI&,Q\u0001\ti>\u001cu\u000e\\;n]V\t\u0011\r\u0005\u0003ZEN\n\u0015BA2\r\u0005-!\u0016\u0010]3e\u0007>dW/\u001c8)\t\u0001)\u0007.\u001b\t\u00031\u0019L!aZ\r\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g\u0004C\u000f\u000e\u0019F\u0007\u0000ZH7"
)
public abstract class Aggregator implements Serializable, UserDefinedFunctionLike {
   private static final long serialVersionUID = 2093413866369130093L;

   public String name() {
      return UserDefinedFunctionLike.name$(this);
   }

   public abstract Object zero();

   public abstract Object reduce(final Object b, final Object a);

   public abstract Object merge(final Object b1, final Object b2);

   public abstract Object finish(final Object reduction);

   public abstract Encoder bufferEncoder();

   public abstract Encoder outputEncoder();

   public TypedColumn toColumn() {
      return new TypedColumn(new InvokeInlineUserDefinedFunction(this, .MODULE$, InvokeInlineUserDefinedFunction$.MODULE$.apply$default$3(), InvokeInlineUserDefinedFunction$.MODULE$.apply$default$4()), this.outputEncoder());
   }

   public Aggregator() {
      UserDefinedFunctionLike.$init$(this);
   }
}
