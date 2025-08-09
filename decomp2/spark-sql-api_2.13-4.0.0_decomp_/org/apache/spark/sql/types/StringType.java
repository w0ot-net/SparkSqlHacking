package org.apache.spark.sql.types;

import java.io.Serializable;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.catalyst.util.CollationFactory;
import org.json4s.JString;
import org.json4s.JValue;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\u00055e\u0001\u0002\u0012$\u00019B\u0001\"\u0011\u0001\u0003\u0006\u0004%\tA\u0011\u0005\t\u000f\u0002\u0011\t\u0011)A\u0005\u0007\"A\u0001\n\u0001BC\u0002\u0013\u0005\u0011\n\u0003\u0005N\u0001\t\u0005\t\u0015!\u0003K\u0011\u0019q\u0005\u0001\"\u0001&\u001f\"11\u000b\u0001C\u0001KQCa\u0001\u0017\u0001\u0005\u0002\u0015\"\u0006BB-\u0001\t\u0003)C\u000b\u0003\u0004[\u0001\u0011\u0005Q\u0005\u0016\u0005\u00077\u0002!\t!\n+\t\rq\u0003A\u0011A\u0013U\u0011\u0019i\u0006\u0001\"\u0001&)\"1a\f\u0001C\u0001KQCQa\u0018\u0001\u0005B\u0001DQ!\u001b\u0001\u0005B)Daa\u001b\u0001\u0005\u0002\u0015\u0002\u0007\"\u00027\u0001\t\u0003j\u0007\"B>\u0001\t\u0003b\bbBA\u0003\u0001\u0011\u0005\u0013q\u0001\u0005\u0007\u0003\u0013\u0001A\u0011\t\"\t\u0011\u0005-\u0001\u0001\"\u0011(\u0003\u001b9q!!\b$\u0011\u0003\u000byB\u0002\u0004#G!\u0005\u0015\u0011\u0005\u0005\u0007\u001d^!\t!!\u000b\t\u0011\u0005-r\u0003\"\u0001(\u0003[Aq!a\u000b\u0018\t\u0003\t\t\u0004\u0003\u0006\u00028]\t\n\u0011\"\u0001&\u0003sA\u0011\"!\u0014\u0018\u0003\u0003%\t%a\u0014\t\u0011\u0005}s#!A\u0005\u0002\tC\u0011\"!\u0019\u0018\u0003\u0003%\t!a\u0019\t\u0013\u0005%t#!A\u0005B\u0005-\u0004\"CA=/\u0005\u0005I\u0011AA>\u0011%\tyhFA\u0001\n\u0013\t\tI\u0001\u0006TiJLgn\u001a+za\u0016T!\u0001J\u0013\u0002\u000bQL\b/Z:\u000b\u0005\u0019:\u0013aA:rY*\u0011\u0001&K\u0001\u0006gB\f'o\u001b\u0006\u0003U-\na!\u00199bG\",'\"\u0001\u0017\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001y3\u0007\u0005\u00021c5\t1%\u0003\u00023G\tQ\u0011\t^8nS\u000e$\u0016\u0010]3\u0011\u0005QrdBA\u001b<\u001d\t1\u0014(D\u00018\u0015\tAT&\u0001\u0004=e>|GOP\u0005\u0002u\u0005)1oY1mC&\u0011A(P\u0001\ba\u0006\u001c7.Y4f\u0015\u0005Q\u0014BA A\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\taT(A\u0006d_2d\u0017\r^5p]&#W#A\"\u0011\u0005\u0011+U\"A\u001f\n\u0005\u0019k$aA%oi\u0006a1m\u001c7mCRLwN\\%eA\u0005Q1m\u001c8tiJ\f\u0017N\u001c;\u0016\u0003)\u0003\"\u0001M&\n\u00051\u001b#\u0001E*ue&twmQ8ogR\u0014\u0018-\u001b8u\u0003-\u0019wN\\:ue\u0006Lg\u000e\u001e\u0011\u0002\rqJg.\u001b;?)\r\u0001\u0016K\u0015\t\u0003a\u0001AQ!Q\u0003A\u0002\rCq\u0001S\u0003\u0011\u0002\u0003\u0007!*\u0001\ftkB\u0004xN\u001d;t\u0005&t\u0017M]=FcV\fG.\u001b;z+\u0005)\u0006C\u0001#W\u0013\t9VHA\u0004C_>dW-\u00198\u00023M,\b\u000f]8siNdun^3sG\u0006\u001cX-R9vC2LG/_\u0001\u0012SN\u001c\u0015m]3J]N,gn]5uSZ,\u0017aE5t\u0003\u000e\u001cWM\u001c;J]N,gn]5uSZ,\u0017!E;tKN$&/[7D_2d\u0017\r^5p]\u0006)\u0012n]+U\rb\u0012\u0015N\\1ss\u000e{G\u000e\\1uS>t\u0017\u0001F5t+R3\u0005\bT2bg\u0016\u001cu\u000e\u001c7bi&|g.\u0001\ftkB\u0004xN\u001d;t\u0005&t\u0017M]=Pe\u0012,'/\u001b8h\u0003!!\u0018\u0010]3OC6,W#A1\u0011\u0005\t4gBA2e!\t1T(\u0003\u0002f{\u00051\u0001K]3eK\u001aL!a\u001a5\u0003\rM#(/\u001b8h\u0015\t)W(\u0001\u0005u_N#(/\u001b8h)\u0005\t\u0017!D2pY2\fG/[8o\u001d\u0006lW-A\u0005kg>tg+\u00197vKV\ta\u000e\u0005\u0002pq:\u0011\u0001/\u001e\b\u0003cNt!A\u000e:\n\u00031J!\u0001^\u0016\u0002\r)\u001cxN\u001c\u001bt\u0013\t1x/A\u0004Kg>t\u0017i\u0015+\u000b\u0005Q\\\u0013BA={\u0005\u0019Qe+\u00197vK*\u0011ao^\u0001\u0007KF,\u0018\r\\:\u0015\u0005Uk\b\"\u0002@\u0013\u0001\u0004y\u0018aA8cUB\u0019A)!\u0001\n\u0007\u0005\rQHA\u0002B]f\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002\u0007\u0006YA-\u001a4bk2$8+\u001b>f\u0003)\t7OT;mY\u0006\u0014G.Z\u000b\u0002!\"\u001a\u0001!!\u0005\u0011\t\u0005M\u0011\u0011D\u0007\u0003\u0003+Q1!a\u0006(\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u00037\t)B\u0001\u0004Ti\u0006\u0014G.Z\u0001\u000b'R\u0014\u0018N\\4UsB,\u0007C\u0001\u0019\u0018'\u00159\u0002+a\t4!\r!\u0015QE\u0005\u0004\u0003Oi$a\u0002)s_\u0012,8\r\u001e\u000b\u0003\u0003?\tQ!\u00199qYf$2\u0001UA\u0018\u0011\u0015\t\u0015\u00041\u0001D)\r\u0001\u00161\u0007\u0005\u0007\u0003kQ\u0002\u0019A1\u0002\u0013\r|G\u000e\\1uS>t\u0017a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$#'\u0006\u0002\u0002<)\u001a!*!\u0010,\u0005\u0005}\u0002\u0003BA!\u0003\u0013j!!a\u0011\u000b\t\u0005\u0015\u0013qI\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\u0006>\u0013\u0011\tY%a\u0011\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003#\u0002B!a\u0015\u0002^5\u0011\u0011Q\u000b\u0006\u0005\u0003/\nI&\u0001\u0003mC:<'BAA.\u0003\u0011Q\u0017M^1\n\u0007\u001d\f)&\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0007}\f)\u0007\u0003\u0005\u0002hy\t\t\u00111\u0001D\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011Q\u000e\t\u0006\u0003_\n)h`\u0007\u0003\u0003cR1!a\u001d>\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003o\n\tH\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGcA+\u0002~!A\u0011q\r\u0011\u0002\u0002\u0003\u0007q0\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u0004B!\u00111KAC\u0013\u0011\t9)!\u0016\u0003\r=\u0013'.Z2uQ\r9\u0012\u0011\u0003\u0015\u0004-\u0005E\u0001"
)
public class StringType extends AtomicType implements Serializable {
   private final int collationId;
   private final StringConstraint constraint;

   public static boolean canEqual(final Object x$1) {
      return StringType$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return StringType$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return StringType$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return StringType$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return StringType$.MODULE$.productPrefix();
   }

   public static StringType apply(final String collation) {
      return StringType$.MODULE$.apply(collation);
   }

   public static Iterator productElementNames() {
      return StringType$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return StringType$.MODULE$.productElementName(n);
   }

   public int collationId() {
      return this.collationId;
   }

   public StringConstraint constraint() {
      return this.constraint;
   }

   public boolean supportsBinaryEquality() {
      return this.collationId() == CollationFactory.UTF8_BINARY_COLLATION_ID || CollationFactory.fetchCollation(this.collationId()).supportsBinaryEquality;
   }

   public boolean supportsLowercaseEquality() {
      return CollationFactory.fetchCollation(this.collationId()).supportsLowercaseEquality;
   }

   public boolean isCaseInsensitive() {
      return CollationFactory.isCaseInsensitive(this.collationId());
   }

   public boolean isAccentInsensitive() {
      return CollationFactory.isAccentInsensitive(this.collationId());
   }

   public boolean usesTrimCollation() {
      return CollationFactory.fetchCollation(this.collationId()).supportsSpaceTrimming;
   }

   public boolean isUTF8BinaryCollation() {
      return this.collationId() == CollationFactory.UTF8_BINARY_COLLATION_ID;
   }

   public boolean isUTF8LcaseCollation() {
      return this.collationId() == CollationFactory.UTF8_LCASE_COLLATION_ID;
   }

   public boolean supportsBinaryOrdering() {
      return CollationFactory.fetchCollation(this.collationId()).supportsBinaryOrdering;
   }

   public String typeName() {
      return this.isUTF8BinaryCollation() ? "string" : "string collate " + this.collationName();
   }

   public String toString() {
      return this.isUTF8BinaryCollation() ? "StringType" : "StringType(" + this.collationName() + ")";
   }

   public String collationName() {
      return CollationFactory.fetchCollation(this.collationId()).collationName;
   }

   public JValue jsonValue() {
      return new JString("string");
   }

   public boolean equals(final Object obj) {
      if (!(obj instanceof StringType var4)) {
         return false;
      } else {
         boolean var6;
         label32: {
            if (var4.collationId() == this.collationId()) {
               StringConstraint var10000 = var4.constraint();
               StringConstraint var5 = this.constraint();
               if (var10000 == null) {
                  if (var5 == null) {
                     break label32;
                  }
               } else if (var10000.equals(var5)) {
                  break label32;
               }
            }

            var6 = false;
            return var6;
         }

         var6 = true;
         return var6;
      }
   }

   public int hashCode() {
      return Integer.hashCode(this.collationId());
   }

   public int defaultSize() {
      return 20;
   }

   public StringType asNullable() {
      return this;
   }

   public StringType(final int collationId, final StringConstraint constraint) {
      this.collationId = collationId;
      this.constraint = constraint;
   }
}
