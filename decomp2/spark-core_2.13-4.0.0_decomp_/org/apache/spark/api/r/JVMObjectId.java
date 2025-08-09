package org.apache.spark.api.r;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015c!\u0002\f\u0018\u0001^\t\u0003\u0002\u0003\u001d\u0001\u0005+\u0007I\u0011A\u001d\t\u0011\t\u0003!\u0011#Q\u0001\niBQa\u0011\u0001\u0005\u0002\u0011Cq\u0001\u0013\u0001\u0002\u0002\u0013\u0005\u0011\nC\u0004L\u0001E\u0005I\u0011\u0001'\t\u000f]\u0003\u0011\u0011!C!1\"9\u0001\rAA\u0001\n\u0003\t\u0007bB3\u0001\u0003\u0003%\tA\u001a\u0005\bY\u0002\t\t\u0011\"\u0011n\u0011\u001d!\b!!A\u0005\u0002UDqA\u001f\u0001\u0002\u0002\u0013\u00053\u0010C\u0004~\u0001\u0005\u0005I\u0011\t@\t\u0011}\u0004\u0011\u0011!C!\u0003\u0003A\u0011\"a\u0001\u0001\u0003\u0003%\t%!\u0002\b\u0015\u0005%q#!A\t\u0002]\tYAB\u0005\u0017/\u0005\u0005\t\u0012A\f\u0002\u000e!11\t\u0005C\u0001\u0003KA\u0001b \t\u0002\u0002\u0013\u0015\u0013\u0011\u0001\u0005\n\u0003O\u0001\u0012\u0011!CA\u0003SA\u0011\"!\f\u0011\u0003\u0003%\t)a\f\t\u0013\u0005m\u0002#!A\u0005\n\u0005u\"a\u0003&W\u001b>\u0013'.Z2u\u0013\u0012T!\u0001G\r\u0002\u0003IT!AG\u000e\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\u001d;\u0005)1\u000f]1sW*\u0011adH\u0001\u0007CB\f7\r[3\u000b\u0003\u0001\n1a\u001c:h'\u0011\u0001!\u0005K\u0016\u0011\u0005\r2S\"\u0001\u0013\u000b\u0003\u0015\nQa]2bY\u0006L!a\n\u0013\u0003\r\u0005s\u0017PU3g!\t\u0019\u0013&\u0003\u0002+I\t9\u0001K]8ek\u000e$\bC\u0001\u00176\u001d\ti3G\u0004\u0002/e5\tqF\u0003\u00021c\u00051AH]8piz\u001a\u0001!C\u0001&\u0013\t!D%A\u0004qC\u000e\\\u0017mZ3\n\u0005Y:$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001b%\u0003\tIG-F\u0001;!\tYtH\u0004\u0002={A\u0011a\u0006J\u0005\u0003}\u0011\na\u0001\u0015:fI\u00164\u0017B\u0001!B\u0005\u0019\u0019FO]5oO*\u0011a\bJ\u0001\u0004S\u0012\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002F\u000fB\u0011a\tA\u0007\u0002/!)\u0001h\u0001a\u0001u\u0005!1m\u001c9z)\t)%\nC\u00049\tA\u0005\t\u0019\u0001\u001e\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tQJ\u000b\u0002;\u001d.\nq\n\u0005\u0002Q+6\t\u0011K\u0003\u0002S'\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003)\u0012\n!\"\u00198o_R\fG/[8o\u0013\t1\u0016KA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A-\u0011\u0005i{V\"A.\u000b\u0005qk\u0016\u0001\u00027b]\u001eT\u0011AX\u0001\u0005U\u00064\u0018-\u0003\u0002A7\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t!\r\u0005\u0002$G&\u0011A\r\n\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003O*\u0004\"a\t5\n\u0005%$#aA!os\"91\u000eCA\u0001\u0002\u0004\u0011\u0017a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001o!\ry'oZ\u0007\u0002a*\u0011\u0011\u000fJ\u0001\u000bG>dG.Z2uS>t\u0017BA:q\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0005YL\bCA\u0012x\u0013\tAHEA\u0004C_>dW-\u00198\t\u000f-T\u0011\u0011!a\u0001O\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\tIF\u0010C\u0004l\u0017\u0005\u0005\t\u0019\u00012\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012AY\u0001\ti>\u001cFO]5oOR\t\u0011,\u0001\u0004fcV\fGn\u001d\u000b\u0004m\u0006\u001d\u0001bB6\u000f\u0003\u0003\u0005\raZ\u0001\f\u0015ZkuJ\u00196fGRLE\r\u0005\u0002G!M)\u0001#a\u0004\u0002\u001cA1\u0011\u0011CA\fu\u0015k!!a\u0005\u000b\u0007\u0005UA%A\u0004sk:$\u0018.\\3\n\t\u0005e\u00111\u0003\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BA\u000f\u0003Gi!!a\b\u000b\u0007\u0005\u0005R,\u0001\u0002j_&\u0019a'a\b\u0015\u0005\u0005-\u0011!B1qa2LHcA#\u0002,!)\u0001h\u0005a\u0001u\u00059QO\\1qa2LH\u0003BA\u0019\u0003o\u0001BaIA\u001au%\u0019\u0011Q\u0007\u0013\u0003\r=\u0003H/[8o\u0011!\tI\u0004FA\u0001\u0002\u0004)\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\b\t\u00045\u0006\u0005\u0013bAA\"7\n1qJ\u00196fGR\u0004"
)
public class JVMObjectId implements Product, Serializable {
   private final String id;

   public static Option unapply(final JVMObjectId x$0) {
      return JVMObjectId$.MODULE$.unapply(x$0);
   }

   public static JVMObjectId apply(final String id) {
      return JVMObjectId$.MODULE$.apply(id);
   }

   public static Function1 andThen(final Function1 g) {
      return JVMObjectId$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return JVMObjectId$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String id() {
      return this.id;
   }

   public JVMObjectId copy(final String id) {
      return new JVMObjectId(id);
   }

   public String copy$default$1() {
      return this.id();
   }

   public String productPrefix() {
      return "JVMObjectId";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.id();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof JVMObjectId;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "id";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof JVMObjectId) {
               label40: {
                  JVMObjectId var4 = (JVMObjectId)x$1;
                  String var10000 = this.id();
                  String var5 = var4.id();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public JVMObjectId(final String id) {
      this.id = id;
      Product.$init$(this);
      scala.Predef..MODULE$.require(id != null, () -> "Object ID cannot be null.");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
