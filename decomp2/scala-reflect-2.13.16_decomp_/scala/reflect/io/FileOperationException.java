package scala.reflect.io;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]b\u0001B\u000b\u0017\u0001vA\u0001B\r\u0001\u0003\u0016\u0004%\ta\r\u0005\ty\u0001\u0011\t\u0012)A\u0005i!)Q\b\u0001C\u0001}!9!\tAA\u0001\n\u0003\u0019\u0005bB#\u0001#\u0003%\tA\u0012\u0005\b#\u0002\t\t\u0011\"\u0011S\u0011\u001dQ\u0006!!A\u0005\u0002mCqa\u0018\u0001\u0002\u0002\u0013\u0005\u0001\rC\u0004g\u0001\u0005\u0005I\u0011I4\t\u000f9\u0004\u0011\u0011!C\u0001_\"9A\u000fAA\u0001\n\u0003*\bbB<\u0001\u0003\u0003%\t\u0005\u001f\u0005\bs\u0002\t\t\u0011\"\u0011{\u000f\u001dah#!A\t\u0002u4q!\u0006\f\u0002\u0002#\u0005a\u0010\u0003\u0004>\u001f\u0011\u0005\u00111\u0003\u0005\n\u0003+y\u0011\u0011!C#\u0003/A\u0011\"!\u0007\u0010\u0003\u0003%\t)a\u0007\t\u0013\u0005}q\"!A\u0005\u0002\u0006\u0005\u0002\"CA\u0017\u001f\u0005\u0005I\u0011BA\u0018\u0005Y1\u0015\u000e\\3Pa\u0016\u0014\u0018\r^5p]\u0016C8-\u001a9uS>t'BA\f\u0019\u0003\tIwN\u0003\u0002\u001a5\u00059!/\u001a4mK\u000e$(\"A\u000e\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M!\u0001A\b\u0014*!\ty2E\u0004\u0002!C5\t!$\u0003\u0002#5\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0013&\u0005A\u0011VO\u001c;j[\u0016,\u0005pY3qi&|gN\u0003\u0002#5A\u0011\u0001eJ\u0005\u0003Qi\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002+a9\u00111&\t\b\u0003Y=j\u0011!\f\u0006\u0003]q\ta\u0001\u0010:p_Rt\u0014\"A\u000e\n\u0005E*#\u0001D*fe&\fG.\u001b>bE2,\u0017aA7tOV\tA\u0007\u0005\u00026s9\u0011ag\u000e\t\u0003YiI!\u0001\u000f\u000e\u0002\rA\u0013X\rZ3g\u0013\tQ4H\u0001\u0004TiJLgn\u001a\u0006\u0003qi\tA!\\:hA\u00051A(\u001b8jiz\"\"aP!\u0011\u0005\u0001\u0003Q\"\u0001\f\t\u000bI\u001a\u0001\u0019\u0001\u001b\u0002\t\r|\u0007/\u001f\u000b\u0003\u007f\u0011CqA\r\u0003\u0011\u0002\u0003\u0007A'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003\u001dS#\u0001\u000e%,\u0003%\u0003\"AS(\u000e\u0003-S!\u0001T'\u0002\u0013Ut7\r[3dW\u0016$'B\u0001(\u001b\u0003)\tgN\\8uCRLwN\\\u0005\u0003!.\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t1\u000b\u0005\u0002U36\tQK\u0003\u0002W/\u0006!A.\u00198h\u0015\u0005A\u0016\u0001\u00026bm\u0006L!AO+\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003q\u0003\"\u0001I/\n\u0005yS\"aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA1e!\t\u0001#-\u0003\u0002d5\t\u0019\u0011I\\=\t\u000f\u0015D\u0011\u0011!a\u00019\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012\u0001\u001b\t\u0004S2\fW\"\u00016\u000b\u0005-T\u0012AC2pY2,7\r^5p]&\u0011QN\u001b\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002qgB\u0011\u0001%]\u0005\u0003ej\u0011qAQ8pY\u0016\fg\u000eC\u0004f\u0015\u0005\u0005\t\u0019A1\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0003'ZDq!Z\u0006\u0002\u0002\u0003\u0007A,\u0001\u0005iCND7i\u001c3f)\u0005a\u0016AB3rk\u0006d7\u000f\u0006\u0002qw\"9Q-DA\u0001\u0002\u0004\t\u0017A\u0006$jY\u0016|\u0005/\u001a:bi&|g.\u0012=dKB$\u0018n\u001c8\u0011\u0005\u0001{1\u0003B\b\u0000\u0003\u0017\u0001b!!\u0001\u0002\bQzTBAA\u0002\u0015\r\t)AG\u0001\beVtG/[7f\u0013\u0011\tI!a\u0001\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002\u000e\u0005EQBAA\b\u0015\t9r+C\u00022\u0003\u001f!\u0012!`\u0001\ti>\u001cFO]5oOR\t1+A\u0003baBd\u0017\u0010F\u0002@\u0003;AQA\r\nA\u0002Q\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002$\u0005%\u0002\u0003\u0002\u0011\u0002&QJ1!a\n\u001b\u0005\u0019y\u0005\u000f^5p]\"A\u00111F\n\u0002\u0002\u0003\u0007q(A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\r\u0011\u0007Q\u000b\u0019$C\u0002\u00026U\u0013aa\u00142kK\u000e$\b"
)
public class FileOperationException extends RuntimeException implements Product {
   private final String msg;

   public static Option unapply(final FileOperationException x$0) {
      return FileOperationException$.MODULE$.unapply(x$0);
   }

   public static FileOperationException apply(final String msg) {
      FileOperationException$ var10000 = FileOperationException$.MODULE$;
      return new FileOperationException(msg);
   }

   public static Function1 andThen(final Function1 g) {
      return Function1::$anonfun$andThen$1;
   }

   public static Function1 compose(final Function1 g) {
      return Function1::$anonfun$compose$1;
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String msg() {
      return this.msg;
   }

   public FileOperationException copy(final String msg) {
      return new FileOperationException(msg);
   }

   public String copy$default$1() {
      return this.msg();
   }

   public String productPrefix() {
      return "FileOperationException";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.msg();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return new ScalaRunTime..anon.1(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof FileOperationException;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "msg";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$.productHash(this, -889275714, false);
   }

   public boolean equals(final Object x$1) {
      if (this != x$1) {
         if (x$1 instanceof FileOperationException) {
            FileOperationException var2 = (FileOperationException)x$1;
            String var10000 = this.msg();
            String var3 = var2.msg();
            if (var10000 == null) {
               if (var3 != null) {
                  return false;
               }
            } else if (!var10000.equals(var3)) {
               return false;
            }

            if (var2.canEqual(this)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public FileOperationException(final String msg) {
      super(msg);
      this.msg = msg;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
