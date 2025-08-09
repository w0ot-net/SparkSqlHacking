package org.apache.spark.sql.types;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00154Q\u0001E\t\u0001'mA\u0001B\u0005\u0001\u0003\u0006\u0004%I!\t\u0005\ta\u0001\u0011\t\u0011)A\u0005E!)\u0011\u0007\u0001C\u0001e!1Q\u0007\u0001C!'YBaA\u000f\u0001\u0005BMY\u0004B\u0002\"\u0001\t\u0003\u001a2i\u0002\u0004M#!\u00051#\u0014\u0004\u0007!EA\ta\u0005(\t\u000bEBA\u0011\u0001*\t\u000fMC!\u0019!C\u0001)\"1Q\u000b\u0003Q\u0001\nMBqA\u0016\u0005C\u0002\u0013\u0005A\u000b\u0003\u0004X\u0011\u0001\u0006Ia\r\u0005\u00061\"!\t!\u0017\u0005\u0006=\"!\ta\u0018\u0002\u000f)f\u0004XmQ8mY\u0016\u001cG/[8o\u0015\t\u00112#A\u0003usB,7O\u0003\u0002\u0015+\u0005\u00191/\u001d7\u000b\u0005Y9\u0012!B:qCJ\\'B\u0001\r\u001a\u0003\u0019\t\u0007/Y2iK*\t!$A\u0002pe\u001e\u001c\"\u0001\u0001\u000f\u0011\u0005uqR\"A\t\n\u0005}\t\"\u0001E!cgR\u0014\u0018m\u0019;ECR\fG+\u001f9f\u0007\u0001)\u0012A\t\t\u0004G5bbB\u0001\u0013+\u001d\t)\u0003&D\u0001'\u0015\t9\u0003%\u0001\u0004=e>|GOP\u0005\u0002S\u0005)1oY1mC&\u00111\u0006L\u0001\ba\u0006\u001c7.Y4f\u0015\u0005I\u0013B\u0001\u00180\u0005\r\u0019V-\u001d\u0006\u0003W1\na\u0001^=qKN\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u00024iA\u0011Q\u0004\u0001\u0005\u0006%\r\u0001\rAI\u0001\u0014I\u00164\u0017-\u001e7u\u0007>t7M]3uKRK\b/Z\u000b\u0002oA\u0011Q\u0004O\u0005\u0003sE\u0011\u0001\u0002R1uCRK\b/Z\u0001\fC\u000e\u001cW\r\u001d;t)f\u0004X\r\u0006\u0002=\u0001B\u0011QHP\u0007\u0002Y%\u0011q\b\f\u0002\b\u0005>|G.Z1o\u0011\u0015\tU\u00011\u00018\u0003\u0015yG\u000f[3s\u00031\u0019\u0018.\u001c9mKN#(/\u001b8h+\u0005!\u0005CA#J\u001d\t1u\t\u0005\u0002&Y%\u0011\u0001\nL\u0001\u0007!J,G-\u001a4\n\u0005)[%AB*ue&twM\u0003\u0002IY\u0005qA+\u001f9f\u0007>dG.Z2uS>t\u0007CA\u000f\t'\tAq\n\u0005\u0002>!&\u0011\u0011\u000b\f\u0002\u0007\u0003:L(+\u001a4\u0015\u00035\u000baCT;nKJL7-\u00118e\u0003:\u001c\u0018.\u00138uKJ4\u0018\r\\\u000b\u0002g\u00059b*^7fe&\u001c\u0017I\u001c3B]NL\u0017J\u001c;feZ\fG\u000eI\u0001\u0013\u001dVlWM]5d\u0003:$\u0017J\u001c;feZ\fG.A\nOk6,'/[2B]\u0012Le\u000e^3sm\u0006d\u0007%A\u0003baBd\u0017\u0010\u0006\u000245\")!C\u0004a\u00017B\u0019Q\b\u0018\u000f\n\u0005uc#A\u0003\u001fsKB,\u0017\r^3e}\u00059QO\\1qa2LHC\u00011d!\ri\u0014MI\u0005\u0003E2\u0012aa\u00149uS>t\u0007\"\u00023\u0010\u0001\u0004a\u0012a\u0001;za\u0002"
)
public class TypeCollection extends AbstractDataType {
   private final Seq org$apache$spark$sql$types$TypeCollection$$types;

   public static Option unapply(final AbstractDataType typ) {
      return TypeCollection$.MODULE$.unapply(typ);
   }

   public static TypeCollection apply(final Seq types) {
      return TypeCollection$.MODULE$.apply(types);
   }

   public static TypeCollection NumericAndInterval() {
      return TypeCollection$.MODULE$.NumericAndInterval();
   }

   public static TypeCollection NumericAndAnsiInterval() {
      return TypeCollection$.MODULE$.NumericAndAnsiInterval();
   }

   public Seq org$apache$spark$sql$types$TypeCollection$$types() {
      return this.org$apache$spark$sql$types$TypeCollection$$types;
   }

   public DataType defaultConcreteType() {
      return ((AbstractDataType)this.org$apache$spark$sql$types$TypeCollection$$types().head()).defaultConcreteType();
   }

   public boolean acceptsType(final DataType other) {
      return this.org$apache$spark$sql$types$TypeCollection$$types().exists((x$1) -> BoxesRunTime.boxToBoolean($anonfun$acceptsType$1(other, x$1)));
   }

   public String simpleString() {
      return ((IterableOnceOps)this.org$apache$spark$sql$types$TypeCollection$$types().map((x$2) -> x$2.simpleString())).mkString("(", " or ", ")");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$acceptsType$1(final DataType other$1, final AbstractDataType x$1) {
      return x$1.acceptsType(other$1);
   }

   public TypeCollection(final Seq types) {
      this.org$apache$spark$sql$types$TypeCollection$$types = types;
      .MODULE$.require(types.nonEmpty(), () -> "TypeCollection (" + this.org$apache$spark$sql$types$TypeCollection$$types() + ") cannot be empty");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
