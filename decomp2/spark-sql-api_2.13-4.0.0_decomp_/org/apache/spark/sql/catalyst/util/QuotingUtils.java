package org.apache.spark.sql.catalyst.util;

import org.apache.spark.sql.connector.catalog.Identifier;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005q!\u0002\t\u0012\u0011\u0003qb!\u0002\u0011\u0012\u0011\u0003\t\u0003\"\u0002\u0015\u0002\t\u0003I\u0003\"\u0002\u0016\u0002\t\u0013Y\u0003\"B\u001d\u0002\t\u0003Q\u0004\"B\u001f\u0002\t\u0003q\u0004\"B!\u0002\t\u0003\u0011\u0005\"B#\u0002\t\u00031\u0005bB)\u0002\u0005\u0004%IA\u0015\u0005\u00079\u0006\u0001\u000b\u0011B*\t\u000bu\u000bA\u0011\u00010\t\u000b\u0011\fA\u0011A3\t\u000b\u001d\fA\u0011\u00015\t\u000b\u001d\fA\u0011\u00018\t\u000be\fA\u0011\u0001>\t\u000bq\fA\u0011A?\u0002\u0019E+x\u000e^5oOV#\u0018\u000e\\:\u000b\u0005I\u0019\u0012\u0001B;uS2T!\u0001F\u000b\u0002\u0011\r\fG/\u00197zgRT!AF\f\u0002\u0007M\fHN\u0003\u0002\u00193\u0005)1\u000f]1sW*\u0011!dG\u0001\u0007CB\f7\r[3\u000b\u0003q\t1a\u001c:h\u0007\u0001\u0001\"aH\u0001\u000e\u0003E\u0011A\"U;pi&tw-\u0016;jYN\u001c\"!\u0001\u0012\u0011\u0005\r2S\"\u0001\u0013\u000b\u0003\u0015\nQa]2bY\u0006L!a\n\u0013\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\ta$\u0001\brk>$XMQ=EK\u001a\fW\u000f\u001c;\u0015\u00051:\u0004CA\u00175\u001d\tq#\u0007\u0005\u00020I5\t\u0001G\u0003\u00022;\u00051AH]8pizJ!a\r\u0013\u0002\rA\u0013X\rZ3g\u0013\t)dG\u0001\u0004TiJLgn\u001a\u0006\u0003g\u0011BQ\u0001O\u0002A\u00021\nA!\u001a7f[\u0006IAo\\*R\u0019\u000e{gN\u001a\u000b\u0003YmBQ\u0001\u0010\u0003A\u00021\nAaY8oM\u0006YAo\\*R\u0019N\u001b\u0007.Z7b)\tas\bC\u0003A\u000b\u0001\u0007A&\u0001\u0004tG\",W.Y\u0001\u0010cV|G/Z%eK:$\u0018NZ5feR\u0011Af\u0011\u0005\u0006\t\u001a\u0001\r\u0001L\u0001\u0005]\u0006lW-\u0001\brk>$XMT1nKB\u000b'\u000f^:\u0015\u00051:\u0005\"\u0002#\b\u0001\u0004A\u0005cA%OY9\u0011!\n\u0014\b\u0003_-K\u0011!J\u0005\u0003\u001b\u0012\nq\u0001]1dW\u0006<W-\u0003\u0002P!\n\u00191+Z9\u000b\u00055#\u0013!\u0005<bY&$\u0017\nZ3oiB\u000bG\u000f^3s]V\t1\u000b\u0005\u0002U56\tQK\u0003\u0002W/\u0006)!/Z4fq*\u0011!\u0003\u0017\u0006\u00023\u0006!!.\u0019<b\u0013\tYVKA\u0004QCR$XM\u001d8\u0002%Y\fG.\u001b3JI\u0016tG\u000fU1ui\u0016\u0014h\u000eI\u0001\n]\u0016,G-U;pi\u0016$\"a\u00182\u0011\u0005\r\u0002\u0017BA1%\u0005\u001d\u0011un\u001c7fC:DQa\u0019\u0006A\u00021\nA\u0001]1si\u0006i\u0011/^8uK&3g*Z3eK\u0012$\"\u0001\f4\t\u000b\r\\\u0001\u0019\u0001\u0017\u0002\rE,x\u000e^3e)\ta\u0013\u000eC\u0003k\u0019\u0001\u00071.A\u0005oC6,7\u000f]1dKB\u00191\u0005\u001c\u0017\n\u00055$#!B!se\u0006LHC\u0001\u0017p\u0011\u0015\u0001X\u00021\u0001r\u0003\u0015IG-\u001a8u!\t\u0011x/D\u0001t\u0015\t!X/A\u0004dCR\fGn\\4\u000b\u0005Y,\u0012!C2p]:,7\r^8s\u0013\tA8O\u0001\u0006JI\u0016tG/\u001b4jKJ\f1BZ;mYf\fVo\u001c;fIR\u0011Af\u001f\u0005\u0006a:\u0001\r!]\u0001\u0019KN\u001c\u0017\r]3TS:<G.Z)v_R,Gm\u0015;sS:<GC\u0001\u0017\u007f\u0011\u0015yx\u00021\u0001-\u0003\r\u0019HO\u001d"
)
public final class QuotingUtils {
   public static String escapeSingleQuotedString(final String str) {
      return QuotingUtils$.MODULE$.escapeSingleQuotedString(str);
   }

   public static String fullyQuoted(final Identifier ident) {
      return QuotingUtils$.MODULE$.fullyQuoted(ident);
   }

   public static String quoted(final Identifier ident) {
      return QuotingUtils$.MODULE$.quoted(ident);
   }

   public static String quoted(final String[] namespace) {
      return QuotingUtils$.MODULE$.quoted(namespace);
   }

   public static String quoteIfNeeded(final String part) {
      return QuotingUtils$.MODULE$.quoteIfNeeded(part);
   }

   public static boolean needQuote(final String part) {
      return QuotingUtils$.MODULE$.needQuote(part);
   }

   public static String quoteNameParts(final Seq name) {
      return QuotingUtils$.MODULE$.quoteNameParts(name);
   }

   public static String quoteIdentifier(final String name) {
      return QuotingUtils$.MODULE$.quoteIdentifier(name);
   }

   public static String toSQLSchema(final String schema) {
      return QuotingUtils$.MODULE$.toSQLSchema(schema);
   }

   public static String toSQLConf(final String conf) {
      return QuotingUtils$.MODULE$.toSQLConf(conf);
   }
}
