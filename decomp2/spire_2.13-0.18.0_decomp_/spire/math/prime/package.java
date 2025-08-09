package spire.math.prime;

import scala.collection.immutable.LazyList;
import scala.collection.immutable.Stream;
import scala.reflect.ScalaSignature;
import spire.math.SafeLong;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001ds!\u0002\f\u0018\u0011\u0003qb!\u0002\u0011\u0018\u0011\u0003\t\u0003\"\u0002\u0015\u0002\t\u0003I\u0003\"\u0002\u0016\u0002\t\u0003Y\u0003\"B\u001b\u0002\t\u00031\u0004\"B\u001e\u0002\t\u0003a\u0004\"B \u0002\t\u0003\u0001\u0005\"\u0002\"\u0002\t\u0003\u0019\u0005bB#\u0002\u0005\u0004%IA\u0012\u0005\u0007\u001f\u0006\u0001\u000b\u0011B$\t\u000bA\u000bA\u0011B)\t\u000bM\u000bA\u0011\u0002+\t\u000f}\u000b!\u0019!C\u0005A\"1\u0011-\u0001Q\u0001\naCQAY\u0001\u0005\u0002\rDQa[\u0001\u0005\u00021DQA\\\u0001\u0005\u0002=DQA\\\u0001\u0005\u0002QDQ!_\u0001\u0005\u0002iDa!_\u0001\u0005\u0002\u0005\u001d\u0001bBA\t\u0003\u0011\u0005\u00111\u0003\u0005\b\u0003#\tA\u0011AA \u0003\u001d\u0001\u0018mY6bO\u0016T!\u0001G\r\u0002\u000bA\u0014\u0018.\\3\u000b\u0005iY\u0012\u0001B7bi\"T\u0011\u0001H\u0001\u0006gBL'/Z\u0002\u0001!\ty\u0012!D\u0001\u0018\u0005\u001d\u0001\u0018mY6bO\u0016\u001c\"!\u0001\u0012\u0011\u0005\r2S\"\u0001\u0013\u000b\u0003\u0015\nQa]2bY\u0006L!a\n\u0013\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\ta$A\u0004jgB\u0013\u0018.\\3\u0015\u00051z\u0003CA\u0012.\u0013\tqCEA\u0004C_>dW-\u00198\t\u000bA\u001a\u0001\u0019A\u0019\u0002\u00039\u0004\"AM\u001a\u000e\u0003eI!\u0001N\r\u0003\u0011M\u000bg-\u001a'p]\u001e\faAZ1di>\u0014HCA\u001c;!\ty\u0002(\u0003\u0002:/\t9a)Y2u_J\u001c\b\"\u0002\u0019\u0005\u0001\u0004\t\u0014a\u00054bGR|'\u000f\u0016:jC2$\u0015N^5tS>tGCA\u001c>\u0011\u0015qT\u00011\u00012\u0003\tq\u0007'A\ngC\u000e$xN],iK\u0016dG)\u001b<jg&|g\u000e\u0006\u00028\u0003\")aH\u0002a\u0001c\u0005\u0001b-Y2u_J\u0004v\u000e\u001c7be\u0012\u0014\u0006n\u001c\u000b\u0003o\u0011CQAP\u0004A\u0002E\nQa\u001d:b]\u0012,\u0012a\u0012\t\u0003\u00116k\u0011!\u0013\u0006\u0003\u0015.\u000bA!\u001e;jY*\tA*\u0001\u0003kCZ\f\u0017B\u0001(J\u0005\u0019\u0011\u0016M\u001c3p[\u000611O]1oI\u0002\nAA]1oIR\u0011\u0011G\u0015\u0005\u0006a)\u0001\r!M\u0001\u000bM&tG\rU8xKJ\u001cHcA+\\;B!1EV\u0019Y\u0013\t9FE\u0001\u0004UkBdWM\r\t\u0003GeK!A\u0017\u0013\u0003\u0007%sG\u000fC\u0003]\u0017\u0001\u0007\u0011'\u0001\u0002ya!)al\u0003a\u0001c\u0005\t!-A\u0005TS\u00164XmU5{KV\t\u0001,\u0001\u0006TS\u00164XmU5{K\u0002\nQb]5fm\u0016\u0014X\u000b\u001d+p\u001dRDGC\u00013h!\tyR-\u0003\u0002g/\t11+[3wKJDQ\u0001\r\bA\u0002!\u0004\"aI5\n\u0005)$#\u0001\u0002'p]\u001e\f1A\u001c;i)\t\tT\u000eC\u00031\u001f\u0001\u0007\u0001.\u0001\u0003gS2dGC\u00019t!\r\u0019\u0013/M\u0005\u0003e\u0012\u0012Q!\u0011:sCfDQ\u0001\r\tA\u0002a#2\u0001];x\u0011\u00151\u0018\u00031\u0001Y\u0003\u0015\u0019H/\u0019:u\u0011\u0015A\u0018\u00031\u0001Y\u0003\u0015a\u0017.\\5u\u0003!a\u0017M_=MSN$X#A>\u0011\tq\f\u0019!M\u0007\u0002{*\u0011ap`\u0001\nS6lW\u000f^1cY\u0016T1!!\u0001%\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0004\u0003\u000bi(\u0001\u0003'bufd\u0015n\u001d;\u0015\u000bm\fI!!\u0004\t\r\u0005-1\u00031\u0001Y\u0003%\u0019\u0007.\u001e8l'&TX\r\u0003\u0004\u0002\u0010M\u0001\r!M\u0001\u0007GV$xN\u001a4\u0002\rM$(/Z1n+\t\t)\u0002E\u0003\u0002\u0018\u0005\u0015\u0012G\u0004\u0003\u0002\u001a\u0005\rb\u0002BA\u000e\u0003Ci!!!\b\u000b\u0007\u0005}Q$\u0001\u0004=e>|GOP\u0005\u0002K%\u0011a\u0003J\u0005\u0005\u0003O\tIC\u0001\u0004TiJ,\u0017-\u001c\u0006\u0003-\u0011B3\u0002FA\u0017\u0003g\t)$!\u000f\u0002<A\u00191%a\f\n\u0007\u0005EBE\u0001\u0006eKB\u0014XmY1uK\u0012\fq!\\3tg\u0006<W-\t\u0002\u00028\u00059\u0002O]3gKJ\u0004C.\u0019>z\u0019&\u001cH\u000fI5ogR,\u0017\rZ\u0001\u0006g&t7-Z\u0011\u0003\u0003{\ta\u0001\r\u00182o9\u0002DCBA\u000b\u0003\u0003\n\u0019\u0005\u0003\u0004\u0002\fU\u0001\r\u0001\u0017\u0005\u0007\u0003\u001f)\u0002\u0019A\u0019)\u0017U\ti#a\r\u00026\u0005e\u00121\b"
)
public final class package {
   /** @deprecated */
   public static Stream stream(final int chunkSize, final SafeLong cutoff) {
      return package$.MODULE$.stream(chunkSize, cutoff);
   }

   /** @deprecated */
   public static Stream stream() {
      return package$.MODULE$.stream();
   }

   public static LazyList lazyList(final int chunkSize, final SafeLong cutoff) {
      return package$.MODULE$.lazyList(chunkSize, cutoff);
   }

   public static LazyList lazyList() {
      return package$.MODULE$.lazyList();
   }

   public static SafeLong[] fill(final int start, final int limit) {
      return package$.MODULE$.fill(start, limit);
   }

   public static SafeLong[] fill(final int n) {
      return package$.MODULE$.fill(n);
   }

   public static SafeLong nth(final long n) {
      return package$.MODULE$.nth(n);
   }

   public static Siever sieverUpToNth(final long n) {
      return package$.MODULE$.sieverUpToNth(n);
   }

   public static Factors factorPollardRho(final SafeLong n0) {
      return package$.MODULE$.factorPollardRho(n0);
   }

   public static Factors factorWheelDivision(final SafeLong n0) {
      return package$.MODULE$.factorWheelDivision(n0);
   }

   public static Factors factorTrialDivision(final SafeLong n0) {
      return package$.MODULE$.factorTrialDivision(n0);
   }

   public static Factors factor(final SafeLong n) {
      return package$.MODULE$.factor(n);
   }

   public static boolean isPrime(final SafeLong n) {
      return package$.MODULE$.isPrime(n);
   }
}
