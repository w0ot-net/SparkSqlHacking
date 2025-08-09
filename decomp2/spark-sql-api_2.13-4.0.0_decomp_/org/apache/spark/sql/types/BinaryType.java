package org.apache.spark.sql.types;

import org.apache.spark.annotation.Stable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005I4Aa\u0004\t\u00017!)\u0001\u0005\u0001C\u0005C!)1\u0005\u0001C!I!11\u0006\u0001C!)1:Q\u0001\u000e\t\t\u0002V2Qa\u0004\t\t\u0002ZBQ\u0001I\u0003\u0005\u0002\u0019CqaR\u0003\u0002\u0002\u0013\u0005\u0003\nC\u0004R\u000b\u0005\u0005I\u0011\u0001\u0013\t\u000fI+\u0011\u0011!C\u0001'\"9\u0011,BA\u0001\n\u0003R\u0006bB1\u0006\u0003\u0003%\tA\u0019\u0005\bO\u0016\t\t\u0011\"\u0011i\u0011\u001dIW!!A\u0005B)Dqa[\u0003\u0002\u0002\u0013%AN\u0001\u0006CS:\f'/\u001f+za\u0016T!!\u0005\n\u0002\u000bQL\b/Z:\u000b\u0005M!\u0012aA:rY*\u0011QCF\u0001\u0006gB\f'o\u001b\u0006\u0003/a\ta!\u00199bG\",'\"A\r\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001a\u0002CA\u000f\u001f\u001b\u0005\u0001\u0012BA\u0010\u0011\u0005)\tEo\\7jGRK\b/Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\t\u0002\"!\b\u0001\u0002\u0017\u0011,g-Y;miNK'0Z\u000b\u0002KA\u0011a%K\u0007\u0002O)\t\u0001&A\u0003tG\u0006d\u0017-\u0003\u0002+O\t\u0019\u0011J\u001c;\u0002\u0015\u0005\u001ch*\u001e7mC\ndW-F\u0001#Q\t\u0001a\u0006\u0005\u00020e5\t\u0001G\u0003\u00022)\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005M\u0002$AB*uC\ndW-\u0001\u0006CS:\f'/\u001f+za\u0016\u0004\"!H\u0003\u0014\t\u0015\u0011sG\u000f\t\u0003MaJ!!O\u0014\u0003\u000fA\u0013x\u000eZ;diB\u00111h\u0011\b\u0003y\u0005s!!\u0010!\u000e\u0003yR!a\u0010\u000e\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0013B\u0001\"(\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001R#\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\t;C#A\u001b\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005I\u0005C\u0001&P\u001b\u0005Y%B\u0001'N\u0003\u0011a\u0017M\\4\u000b\u00039\u000bAA[1wC&\u0011\u0001k\u0013\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011Ak\u0016\t\u0003MUK!AV\u0014\u0003\u0007\u0005s\u0017\u0010C\u0004Y\u0013\u0005\u0005\t\u0019A\u0013\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005Y\u0006c\u0001/`)6\tQL\u0003\u0002_O\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005\u0001l&\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$\"a\u00194\u0011\u0005\u0019\"\u0017BA3(\u0005\u001d\u0011un\u001c7fC:Dq\u0001W\u0006\u0002\u0002\u0003\u0007A+\u0001\u0005iCND7i\u001c3f)\u0005)\u0013\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003%\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012!\u001c\t\u0003\u0015:L!a\\&\u0003\r=\u0013'.Z2uQ\t)a\u0006\u000b\u0002\u0005]\u0001"
)
public class BinaryType extends AtomicType {
   public static boolean canEqual(final Object x$1) {
      return BinaryType$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return BinaryType$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return BinaryType$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return BinaryType$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return BinaryType$.MODULE$.productPrefix();
   }

   public static Iterator productElementNames() {
      return BinaryType$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return BinaryType$.MODULE$.productElementName(n);
   }

   public int defaultSize() {
      return 100;
   }

   public BinaryType asNullable() {
      return this;
   }
}
