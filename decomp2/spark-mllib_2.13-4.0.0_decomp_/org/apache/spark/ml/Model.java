package org.apache.spark.ml;

import org.apache.spark.ml.param.ParamMap;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%3Q\u0001C\u0005\u0002\u0002IAQ\u0001\u0007\u0001\u0005\u0002eA\u0011B\n\u0001A\u0002\u0003\u0007I\u0011A\u0014\t\u0013-\u0002\u0001\u0019!a\u0001\n\u0003a\u0003\"\u0003\u001a\u0001\u0001\u0004\u0005\t\u0015)\u0003)\u0011\u00159\u0004\u0001\"\u00019\u0011\u0015Q\u0004\u0001\"\u0001<\u0011\u0015y\u0004A\"\u0011A\u0005\u0015iu\u000eZ3m\u0015\tQ1\"\u0001\u0002nY*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xm\u0001\u0001\u0016\u0005Mi2C\u0001\u0001\u0015!\t)b#D\u0001\n\u0013\t9\u0012BA\u0006Ue\u0006t7OZ8s[\u0016\u0014\u0018A\u0002\u001fj]&$h\bF\u0001\u001b!\r)\u0002a\u0007\t\u00039ua\u0001\u0001B\u0003\u001f\u0001\t\u0007qDA\u0001N#\t\u0001#\u0004\u0005\u0002\"I5\t!EC\u0001$\u0003\u0015\u00198-\u00197b\u0013\t)#EA\u0004O_RD\u0017N\\4\u0002\rA\f'/\u001a8u+\u0005A\u0003cA\u000b*7%\u0011!&\u0003\u0002\n\u000bN$\u0018.\\1u_J\f!\u0002]1sK:$x\fJ3r)\ti\u0003\u0007\u0005\u0002\"]%\u0011qF\t\u0002\u0005+:LG\u000fC\u00042\u0007\u0005\u0005\t\u0019\u0001\u0015\u0002\u0007a$\u0013'A\u0004qCJ,g\u000e\u001e\u0011)\u0005\u0011!\u0004CA\u00116\u0013\t1$EA\u0005ue\u0006t7/[3oi\u0006I1/\u001a;QCJ,g\u000e\u001e\u000b\u00037eBQAJ\u0003A\u0002!\n\u0011\u0002[1t!\u0006\u0014XM\u001c;\u0016\u0003q\u0002\"!I\u001f\n\u0005y\u0012#a\u0002\"p_2,\u0017M\\\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002\u001c\u0003\")!i\u0002a\u0001\u0007\u0006)Q\r\u001f;sCB\u0011AiR\u0007\u0002\u000b*\u0011a)C\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0003\u0011\u0016\u0013\u0001\u0002U1sC6l\u0015\r\u001d"
)
public abstract class Model extends Transformer {
   private transient Estimator parent;

   public Estimator parent() {
      return this.parent;
   }

   public void parent_$eq(final Estimator x$1) {
      this.parent = x$1;
   }

   public Model setParent(final Estimator parent) {
      this.parent_$eq(parent);
      return this;
   }

   public boolean hasParent() {
      return this.parent() != null;
   }

   public abstract Model copy(final ParamMap extra);
}
