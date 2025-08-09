package org.apache.spark.sql.internal;

import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Option;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ma\u0001\u0003\n\u0014!\u0003\r\t!F\u000f\t\u000b!\u0002A\u0011\u0001\u0016\t\u000b9\u0002a\u0011A\u0018\t\u0011a\u0002\u0001R1A\u0005\u0002eBaa\u000f\u0001\u0005BMa\u0004\"\u0002\f\u0001\r\u0003itAB%\u0014\u0011\u0003)\"J\u0002\u0004\u0013'!\u0005Qc\u0013\u0005\u0006\u0019\u001e!\t!\u0014\u0005\b\u001d\u001e\u0011\r\u0011\"\u00010\u0011\u0019yu\u0001)A\u0005a!)1h\u0002C\u0001!\")1h\u0002C\u0001?\")an\u0002C\u0001_\")!o\u0002C\u0001g\")qo\u0002C\u0001q\"9QpBI\u0001\n\u0003q\bbBA\n\u000f\u0011\u0005\u0011Q\u0003\u0002\u000b\u0007>dW/\u001c8O_\u0012,'B\u0001\u000b\u0016\u0003!Ig\u000e^3s]\u0006d'B\u0001\f\u0018\u0003\r\u0019\u0018\u000f\u001c\u0006\u00031e\tQa\u001d9be.T!AG\u000e\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0012aA8sON\u0019\u0001A\b\u0013\u0011\u0005}\u0011S\"\u0001\u0011\u000b\u0003\u0005\nQa]2bY\u0006L!a\t\u0011\u0003\r\u0005s\u0017PU3g!\t)c%D\u0001\u0014\u0013\t93C\u0001\bD_2,XN\u001c(pI\u0016d\u0015n[3\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012a\u000b\t\u0003?1J!!\f\u0011\u0003\tUs\u0017\u000e^\u0001\u0007_JLw-\u001b8\u0016\u0003A\u0002\"!\r\u001c\u000e\u0003IR!a\r\u001b\u0002\u000bQ\u0014X-Z:\u000b\u0005U*\u0012\u0001C2bi\u0006d\u0017p\u001d;\n\u0005]\u0012$AB(sS\u001eLg.\u0001\u0006o_Jl\u0017\r\\5{K\u0012,\u0012A\u000f\t\u0003K\u0001\t\u0011B\\8s[\u0006d\u0017N_3\u0015\u0003i*\u0012A\u0010\t\u0003\u007f\u0019s!\u0001\u0011#\u0011\u0005\u0005\u0003S\"\u0001\"\u000b\u0005\rK\u0013A\u0002\u001fs_>$h(\u0003\u0002FA\u00051\u0001K]3eK\u001aL!a\u0012%\u0003\rM#(/\u001b8h\u0015\t)\u0005%\u0001\u0006D_2,XN\u001c(pI\u0016\u0004\"!J\u0004\u0014\u0005\u001dq\u0012A\u0002\u001fj]&$h\bF\u0001K\u0003%qujX(S\u0013\u001eKe*\u0001\u0006O\u001f~{%+S$J\u001d\u0002*\"!U,\u0015\u0005Ik\u0006cA\u0010T+&\u0011A\u000b\t\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005Y;F\u0002\u0001\u0003\u00061.\u0011\r!\u0017\u0002\u0002)F\u0011!\f\n\t\u0003?mK!\u0001\u0018\u0011\u0003\u000f9{G\u000f[5oO\")al\u0003a\u0001%\u00061q\u000e\u001d;j_:,\"\u0001Y6\u0015\u0005\u0005d\u0007c\u00012hU:\u00111-\u001a\b\u0003\u0003\u0012L\u0011!I\u0005\u0003M\u0002\nq\u0001]1dW\u0006<W-\u0003\u0002iS\n\u00191+Z9\u000b\u0005\u0019\u0004\u0003C\u0001,l\t\u0015AFB1\u0001Z\u0011\u0015iG\u00021\u0001b\u0003\u0015qw\u000eZ3t\u00039\t'oZ;nK:$8\u000fV8Tc2$\"A\u00109\t\u000b5l\u0001\u0019A9\u0011\u0007\t<G%\u0001\nuKb$\u0018I]4v[\u0016tGo\u001d+p'FdGC\u0001 u\u0011\u0015)h\u00021\u0001w\u0003\u0015\u0001\u0018M\u001d;t!\r\u0011wMP\u0001\u000eK2,W.\u001a8ugR{7+\u001d7\u0015\u0007yJ8\u0010C\u0003{\u001f\u0001\u0007\u0011/\u0001\u0005fY\u0016lWM\u001c;t\u0011\u001dax\u0002%AA\u0002y\na\u0001\u001d:fM&D\u0018aF3mK6,g\u000e^:U_N\u000bH\u000e\n3fM\u0006,H\u000e\u001e\u00133+\u0005y(f\u0001 \u0002\u0002-\u0012\u00111\u0001\t\u0005\u0003\u000b\ty!\u0004\u0002\u0002\b)!\u0011\u0011BA\u0006\u0003%)hn\u00195fG.,GMC\u0002\u0002\u000e\u0001\n!\"\u00198o_R\fG/[8o\u0013\u0011\t\t\"a\u0002\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0006paRLwN\u001c+p'FdGc\u0001 \u0002\u0018!1a,\u0005a\u0001\u00033\u00012aH*%\u0001"
)
public interface ColumnNode extends ColumnNodeLike {
   static String optionToSql(final Option option) {
      return ColumnNode$.MODULE$.optionToSql(option);
   }

   static String elementsToSql$default$2() {
      return ColumnNode$.MODULE$.elementsToSql$default$2();
   }

   static String elementsToSql(final Seq elements, final String prefix) {
      return ColumnNode$.MODULE$.elementsToSql(elements, prefix);
   }

   static String textArgumentsToSql(final Seq parts) {
      return ColumnNode$.MODULE$.textArgumentsToSql(parts);
   }

   static String argumentsToSql(final Seq nodes) {
      return ColumnNode$.MODULE$.argumentsToSql(nodes);
   }

   static Origin NO_ORIGIN() {
      return ColumnNode$.MODULE$.NO_ORIGIN();
   }

   Origin origin();

   // $FF: synthetic method
   static ColumnNode normalized$(final ColumnNode $this) {
      return $this.normalized();
   }

   default ColumnNode normalized() {
      ColumnNode transformed = this.normalize();
      if (this == null) {
         if (transformed != null) {
            return transformed;
         }
      } else if (!this.equals(transformed)) {
         return transformed;
      }

      return this;
   }

   // $FF: synthetic method
   static ColumnNode normalize$(final ColumnNode $this) {
      return $this.normalize();
   }

   default ColumnNode normalize() {
      return this;
   }

   String sql();

   static void $init$(final ColumnNode $this) {
   }
}
