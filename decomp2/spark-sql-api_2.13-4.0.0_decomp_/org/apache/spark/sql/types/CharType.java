package org.apache.spark.sql.types;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Experimental;
import org.apache.spark.sql.catalyst.util.CollationFactory;
import org.json4s.JString;
import org.json4s.JValue;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Experimental
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Md\u0001\u0002\r\u001a\u0001\u0012B\u0001b\u000f\u0001\u0003\u0016\u0004%\t\u0001\u0010\u0005\t\u0001\u0002\u0011\t\u0012)A\u0005{!)\u0011\t\u0001C\u0001\u0005\")Q\t\u0001C!y!)a\t\u0001C!\u000f\")\u0001\u000b\u0001C!#\")q\f\u0001C!A\"1\u0011\r\u0001C!;\tDqa\u0019\u0001\u0002\u0002\u0013\u0005A\rC\u0004g\u0001E\u0005I\u0011A4\t\u000fI\u0004\u0011\u0011!C!g\"91\u0010AA\u0001\n\u0003a\u0004b\u0002?\u0001\u0003\u0003%\t! \u0005\n\u0003\u000f\u0001\u0011\u0011!C!\u0003\u0013A\u0011\"a\u0006\u0001\u0003\u0003%\t!!\u0007\t\u0013\u0005\r\u0002!!A\u0005B\u0005\u0015r!CA\u001b3\u0005\u0005\t\u0012AA\u001c\r!A\u0012$!A\t\u0002\u0005e\u0002BB!\u0013\t\u0003\t\t\u0006\u0003\u0005`%\u0005\u0005IQIA*\u0011%\t)FEA\u0001\n\u0003\u000b9\u0006C\u0005\u0002\\I\t\t\u0011\"!\u0002^!I\u0011\u0011\u000e\n\u0002\u0002\u0013%\u00111\u000e\u0002\t\u0007\"\f'\u000fV=qK*\u0011!dG\u0001\u0006if\u0004Xm\u001d\u0006\u00039u\t1a]9m\u0015\tqr$A\u0003ta\u0006\u00148N\u0003\u0002!C\u00051\u0011\r]1dQ\u0016T\u0011AI\u0001\u0004_J<7\u0001A\n\u0005\u0001\u0015Js\u0006\u0005\u0002'O5\t\u0011$\u0003\u0002)3\tQ1\u000b\u001e:j]\u001e$\u0016\u0010]3\u0011\u0005)jS\"A\u0016\u000b\u00031\nQa]2bY\u0006L!AL\u0016\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0001\u0007\u000f\b\u0003cYr!AM\u001b\u000e\u0003MR!\u0001N\u0012\u0002\rq\u0012xn\u001c;?\u0013\u0005a\u0013BA\u001c,\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u000f\u001e\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005]Z\u0013A\u00027f]\u001e$\b.F\u0001>!\tQc(\u0003\u0002@W\t\u0019\u0011J\u001c;\u0002\u000f1,gn\u001a;iA\u00051A(\u001b8jiz\"\"a\u0011#\u0011\u0005\u0019\u0002\u0001\"B\u001e\u0004\u0001\u0004i\u0014a\u00033fM\u0006,H\u000e^*ju\u0016\f\u0001\u0002^=qK:\u000bW.Z\u000b\u0002\u0011B\u0011\u0011*\u0014\b\u0003\u0015.\u0003\"AM\u0016\n\u00051[\u0013A\u0002)sK\u0012,g-\u0003\u0002O\u001f\n11\u000b\u001e:j]\u001eT!\u0001T\u0016\u0002\u0013)\u001cxN\u001c,bYV,W#\u0001*\u0011\u0005McfB\u0001+Z\u001d\t)vK\u0004\u00023-&\t!%\u0003\u0002YC\u00051!n]8oiML!AW.\u0002\u000f)\u001bxN\\!T)*\u0011\u0001,I\u0005\u0003;z\u0013aA\u0013,bYV,'B\u0001.\\\u0003!!xn\u0015;sS:<G#\u0001%\u0002\u0015\u0005\u001ch*\u001e7mC\ndW-F\u0001D\u0003\u0011\u0019w\u000e]=\u0015\u0005\r+\u0007bB\u001e\n!\u0003\u0005\r!P\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005A'FA\u001fjW\u0005Q\u0007CA6q\u001b\u0005a'BA7o\u0003%)hn\u00195fG.,GM\u0003\u0002pW\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Ed'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012\u0001\u001e\t\u0003kjl\u0011A\u001e\u0006\u0003ob\fA\u0001\\1oO*\t\u00110\u0001\u0003kCZ\f\u0017B\u0001(w\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$2A`A\u0002!\tQs0C\u0002\u0002\u0002-\u00121!\u00118z\u0011!\t)!DA\u0001\u0002\u0004i\u0014a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\fA)\u0011QBA\n}6\u0011\u0011q\u0002\u0006\u0004\u0003#Y\u0013AC2pY2,7\r^5p]&!\u0011QCA\b\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005m\u0011\u0011\u0005\t\u0004U\u0005u\u0011bAA\u0010W\t9!i\\8mK\u0006t\u0007\u0002CA\u0003\u001f\u0005\u0005\t\u0019\u0001@\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004i\u0006\u001d\u0002\u0002CA\u0003!\u0005\u0005\t\u0019A\u001f)\u0007\u0001\tY\u0003\u0005\u0003\u0002.\u0005ERBAA\u0018\u0015\tyW$\u0003\u0003\u00024\u0005=\"\u0001D#ya\u0016\u0014\u0018.\\3oi\u0006d\u0017\u0001C\"iCJ$\u0016\u0010]3\u0011\u0005\u0019\u00122#\u0002\n\u0002<\u0005\u001d\u0003CBA\u001f\u0003\u0007j4)\u0004\u0002\u0002@)\u0019\u0011\u0011I\u0016\u0002\u000fI,h\u000e^5nK&!\u0011QIA \u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0005\u0003\u0013\ny%\u0004\u0002\u0002L)\u0019\u0011Q\n=\u0002\u0005%|\u0017bA\u001d\u0002LQ\u0011\u0011q\u0007\u000b\u0002i\u0006)\u0011\r\u001d9msR\u00191)!\u0017\t\u000bm*\u0002\u0019A\u001f\u0002\u000fUt\u0017\r\u001d9msR!\u0011qLA3!\u0011Q\u0013\u0011M\u001f\n\u0007\u0005\r4F\u0001\u0004PaRLwN\u001c\u0005\t\u0003O2\u0012\u0011!a\u0001\u0007\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u00055\u0004cA;\u0002p%\u0019\u0011\u0011\u000f<\u0003\r=\u0013'.Z2u\u0001"
)
public class CharType extends StringType implements Product {
   private final int length;

   public static Option unapply(final CharType x$0) {
      return CharType$.MODULE$.unapply(x$0);
   }

   public static CharType apply(final int length) {
      return CharType$.MODULE$.apply(length);
   }

   public static Function1 andThen(final Function1 g) {
      return CharType$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return CharType$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int length() {
      return this.length;
   }

   public int defaultSize() {
      return this.length();
   }

   public String typeName() {
      return "char(" + this.length() + ")";
   }

   public JValue jsonValue() {
      return new JString(this.typeName());
   }

   public String toString() {
      return "CharType(" + this.length() + ")";
   }

   public CharType asNullable() {
      return this;
   }

   public CharType copy(final int length) {
      return new CharType(length);
   }

   public int copy$default$1() {
      return this.length();
   }

   public String productPrefix() {
      return "CharType";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.length());
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
      return x$1 instanceof CharType;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "length";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public CharType(final int length) {
      super(CollationFactory.UTF8_BINARY_COLLATION_ID, new FixedLength(length));
      this.length = length;
      Product.$init$(this);
      scala.Predef..MODULE$.require(length >= 0, () -> "The length of char type cannot be negative.");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
