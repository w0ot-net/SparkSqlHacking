package org.apache.spark.streaming.util;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}d!\u0002\u000f\u001e\u0001~9\u0003\u0002C \u0001\u0005+\u0007I\u0011\u0001!\t\u0011%\u0003!\u0011#Q\u0001\n\u0005C\u0001B\u0013\u0001\u0003\u0016\u0004%\ta\u0013\u0005\t\u001f\u0002\u0011\t\u0012)A\u0005\u0019\"A\u0001\u000b\u0001BK\u0002\u0013\u0005\u0011\u000b\u0003\u0005V\u0001\tE\t\u0015!\u0003S\u0011\u00151\u0006\u0001\"\u0001X\u0011\u001da\u0006!!A\u0005\u0002uCq!\u0019\u0001\u0012\u0002\u0013\u0005!\rC\u0004n\u0001E\u0005I\u0011\u00018\t\u000fA\u0004\u0011\u0013!C\u0001c\"91\u000fAA\u0001\n\u0003\"\bb\u0002?\u0001\u0003\u0003%\t!\u0015\u0005\b{\u0002\t\t\u0011\"\u0001\u007f\u0011%\tI\u0001AA\u0001\n\u0003\nY\u0001C\u0005\u0002\u001a\u0001\t\t\u0011\"\u0001\u0002\u001c!I\u0011Q\u0005\u0001\u0002\u0002\u0013\u0005\u0013q\u0005\u0005\n\u0003W\u0001\u0011\u0011!C!\u0003[A\u0011\"a\f\u0001\u0003\u0003%\t%!\r\t\u0013\u0005M\u0002!!A\u0005B\u0005UrACA\u001d;\u0005\u0005\t\u0012A\u0010\u0002<\u0019IA$HA\u0001\u0012\u0003y\u0012Q\b\u0005\u0007-Z!\t!!\u0016\t\u0013\u0005=b#!A\u0005F\u0005E\u0002\"CA,-\u0005\u0005I\u0011QA-\u0011%\t\tGFA\u0001\n\u0003\u000b\u0019\u0007C\u0005\u0002vY\t\t\u0011\"\u0003\u0002x\tib)\u001b7f\u0005\u0006\u001cX\rZ,sSR,\u0017\t[3bI2{wmU3h[\u0016tGO\u0003\u0002\u001f?\u0005!Q\u000f^5m\u0015\t\u0001\u0013%A\u0005tiJ,\u0017-\\5oO*\u0011!eI\u0001\u0006gB\f'o\u001b\u0006\u0003I\u0015\na!\u00199bG\",'\"\u0001\u0014\u0002\u0007=\u0014xm\u0005\u0003\u0001Q1\u0012\u0004CA\u0015+\u001b\u0005i\u0012BA\u0016\u001e\u0005e9&/\u001b;f\u0003\",\u0017\r\u001a'pOJ+7m\u001c:e\u0011\u0006tG\r\\3\u0011\u00055\u0002T\"\u0001\u0018\u000b\u0003=\nQa]2bY\u0006L!!\r\u0018\u0003\u000fA\u0013x\u000eZ;diB\u00111\u0007\u0010\b\u0003iir!!N\u001d\u000e\u0003YR!a\u000e\u001d\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011aL\u0005\u0003w9\nq\u0001]1dW\u0006<W-\u0003\u0002>}\ta1+\u001a:jC2L'0\u00192mK*\u00111HL\u0001\u0005a\u0006$\b.F\u0001B!\t\u0011eI\u0004\u0002D\tB\u0011QGL\u0005\u0003\u000b:\na\u0001\u0015:fI\u00164\u0017BA$I\u0005\u0019\u0019FO]5oO*\u0011QIL\u0001\u0006a\u0006$\b\u000eI\u0001\u0007_\u001a47/\u001a;\u0016\u00031\u0003\"!L'\n\u00059s#\u0001\u0002'p]\u001e\fqa\u001c4gg\u0016$\b%\u0001\u0004mK:<G\u000f[\u000b\u0002%B\u0011QfU\u0005\u0003):\u00121!\u00138u\u0003\u001daWM\\4uQ\u0002\na\u0001P5oSRtD\u0003\u0002-Z5n\u0003\"!\u000b\u0001\t\u000b}:\u0001\u0019A!\t\u000b);\u0001\u0019\u0001'\t\u000bA;\u0001\u0019\u0001*\u0002\t\r|\u0007/\u001f\u000b\u00051z{\u0006\rC\u0004@\u0011A\u0005\t\u0019A!\t\u000f)C\u0001\u0013!a\u0001\u0019\"9\u0001\u000b\u0003I\u0001\u0002\u0004\u0011\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0002G*\u0012\u0011\tZ\u0016\u0002KB\u0011am[\u0007\u0002O*\u0011\u0001.[\u0001\nk:\u001c\u0007.Z2lK\u0012T!A\u001b\u0018\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002mO\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\tqN\u000b\u0002MI\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aT#\u0001:+\u0005I#\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001v!\t180D\u0001x\u0015\tA\u00180\u0001\u0003mC:<'\"\u0001>\u0002\t)\fg/Y\u0005\u0003\u000f^\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002\u0000\u0003\u000b\u00012!LA\u0001\u0013\r\t\u0019A\f\u0002\u0004\u0003:L\b\u0002CA\u0004\u001d\u0005\u0005\t\u0019\u0001*\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ti\u0001E\u0003\u0002\u0010\u0005Uq0\u0004\u0002\u0002\u0012)\u0019\u00111\u0003\u0018\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u0018\u0005E!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\b\u0002$A\u0019Q&a\b\n\u0007\u0005\u0005bFA\u0004C_>dW-\u00198\t\u0011\u0005\u001d\u0001#!AA\u0002}\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019Q/!\u000b\t\u0011\u0005\u001d\u0011#!AA\u0002I\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002%\u0006AAo\\*ue&tw\rF\u0001v\u0003\u0019)\u0017/^1mgR!\u0011QDA\u001c\u0011!\t9\u0001FA\u0001\u0002\u0004y\u0018!\b$jY\u0016\u0014\u0015m]3e/JLG/Z!iK\u0006$Gj\\4TK\u001elWM\u001c;\u0011\u0005%22#\u0002\f\u0002@\u0005-\u0003\u0003CA!\u0003\u000f\nEJ\u0015-\u000e\u0005\u0005\r#bAA#]\u00059!/\u001e8uS6,\u0017\u0002BA%\u0003\u0007\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c84!\u0011\ti%a\u0015\u000e\u0005\u0005=#bAA)s\u0006\u0011\u0011n\\\u0005\u0004{\u0005=CCAA\u001e\u0003\u0015\t\u0007\u000f\u001d7z)\u001dA\u00161LA/\u0003?BQaP\rA\u0002\u0005CQAS\rA\u00021CQ\u0001U\rA\u0002I\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002f\u0005E\u0004#B\u0017\u0002h\u0005-\u0014bAA5]\t1q\n\u001d;j_:\u0004b!LA7\u00032\u0013\u0016bAA8]\t1A+\u001e9mKNB\u0001\"a\u001d\u001b\u0003\u0003\u0005\r\u0001W\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA=!\r1\u00181P\u0005\u0004\u0003{:(AB(cU\u0016\u001cG\u000f"
)
public class FileBasedWriteAheadLogSegment extends WriteAheadLogRecordHandle implements Product {
   private final String path;
   private final long offset;
   private final int length;

   public static Option unapply(final FileBasedWriteAheadLogSegment x$0) {
      return FileBasedWriteAheadLogSegment$.MODULE$.unapply(x$0);
   }

   public static FileBasedWriteAheadLogSegment apply(final String path, final long offset, final int length) {
      return FileBasedWriteAheadLogSegment$.MODULE$.apply(path, offset, length);
   }

   public static Function1 tupled() {
      return FileBasedWriteAheadLogSegment$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return FileBasedWriteAheadLogSegment$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String path() {
      return this.path;
   }

   public long offset() {
      return this.offset;
   }

   public int length() {
      return this.length;
   }

   public FileBasedWriteAheadLogSegment copy(final String path, final long offset, final int length) {
      return new FileBasedWriteAheadLogSegment(path, offset, length);
   }

   public String copy$default$1() {
      return this.path();
   }

   public long copy$default$2() {
      return this.offset();
   }

   public int copy$default$3() {
      return this.length();
   }

   public String productPrefix() {
      return "FileBasedWriteAheadLogSegment";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.path();
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.offset());
         }
         case 2 -> {
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
      return x$1 instanceof FileBasedWriteAheadLogSegment;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "path";
         }
         case 1 -> {
            return "offset";
         }
         case 2 -> {
            return "length";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.path()));
      var1 = Statics.mix(var1, Statics.longHash(this.offset()));
      var1 = Statics.mix(var1, this.length());
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof FileBasedWriteAheadLogSegment) {
               FileBasedWriteAheadLogSegment var4 = (FileBasedWriteAheadLogSegment)x$1;
               if (this.offset() == var4.offset() && this.length() == var4.length()) {
                  label48: {
                     String var10000 = this.path();
                     String var5 = var4.path();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label48;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label48;
                     }

                     if (var4.canEqual(this)) {
                        break label55;
                     }
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

   public FileBasedWriteAheadLogSegment(final String path, final long offset, final int length) {
      this.path = path;
      this.offset = offset;
      this.length = length;
      Product.$init$(this);
   }
}
