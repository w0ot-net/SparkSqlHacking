package algebra.lattice;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%4q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0003\u0019\u0001\u0011\u0005\u0011\u0004C\u0003L\u0001\u0011\u0005A\nC\u0003[\u0001\u0011\u00051LA\u000bIKf$\u0018N\\4HK:\u0014un\u001c7Pm\u0016\u0014H.\u00199\u000b\u0005\u001dA\u0011a\u00027biRL7-\u001a\u0006\u0002\u0013\u00059\u0011\r\\4fEJ\f7\u0001A\u000b\u0003\u0019u\u001a\"\u0001A\u0007\u0011\u00059\tR\"A\b\u000b\u0003A\tQa]2bY\u0006L!AE\b\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\tQ\u0003\u0005\u0002\u000f-%\u0011qc\u0004\u0002\u0005+:LG/A\u0002b]\u0012,\"A\u0007\u0010\u0015\u0007m9\u0015\n\u0006\u0002\u001duA\u0011QD\b\u0007\u0001\t%y\"\u0001)A\u0001\u0002\u000b\u0007\u0001EA\u0001B#\t\tC\u0005\u0005\u0002\u000fE%\u00111e\u0004\u0002\b\u001d>$\b.\u001b8h!\tqQ%\u0003\u0002'\u001f\t\u0019\u0011I\\=)\tyA3&\u000e\t\u0003\u001d%J!AK\b\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G1jsF\f\b\u0003\u001d5J!AL\b\u0002\u0007%sG/\r\u0003%aQ\u0002bBA\u00195\u001b\u0005\u0011$BA\u001a\u000b\u0003\u0019a$o\\8u}%\t\u0001#M\u0003$m]J\u0004H\u0004\u0002\u000fo%\u0011\u0001hD\u0001\u0005\u0019>tw-\r\u0003%aQ\u0002\u0002\"B\u001e\u0003\u0001\ba\u0014AA3w!\riR\b\b\u0003\u0006}\u0001\u0011\ra\u0010\u0002\u0002\u0011V\u0011\u0001IR\t\u0003C\u0005\u00032AQ\"F\u001b\u00051\u0011B\u0001#\u0007\u0005\u001dAU-\u001f;j]\u001e\u0004\"!\b$\u0005\u000b}i$\u0019\u0001\u0011\t\u000b!\u0013\u0001\u0019\u0001\u000f\u0002\u0003aDQA\u0013\u0002A\u0002q\t\u0011!_\u0001\u0003_J,\"!\u0014)\u0015\u00079C\u0016\f\u0006\u0002P-B\u0011Q\u0004\u0015\u0003\n?\r\u0001\u000b\u0011!AC\u0002\u0001BC\u0001\u0015\u0015S)F*1\u0005L\u0017T]E\"A\u0005\r\u001b\u0011c\u0015\u0019cgN+9c\u0011!\u0003\u0007\u000e\t\t\u000bm\u001a\u00019A,\u0011\u0007uit\nC\u0003I\u0007\u0001\u0007q\nC\u0003K\u0007\u0001\u0007q*A\u0002y_J,\"\u0001X0\u0015\u0007u;\u0007\u000e\u0006\u0002_KB\u0011Qd\u0018\u0003\n?\u0011\u0001\u000b\u0011!AC\u0002\u0001BCa\u0018\u0015bGF*1\u0005L\u0017c]E\"A\u0005\r\u001b\u0011c\u0015\u0019cg\u000e39c\u0011!\u0003\u0007\u000e\t\t\u000bm\"\u00019\u00014\u0011\u0007uid\fC\u0003I\t\u0001\u0007a\fC\u0003K\t\u0001\u0007a\f"
)
public interface HeytingGenBoolOverlap {
   // $FF: synthetic method
   static Object and$(final HeytingGenBoolOverlap $this, final Object x, final Object y, final Heyting ev) {
      return $this.and(x, y, ev);
   }

   default Object and(final Object x, final Object y, final Heyting ev) {
      return ev.and(x, y);
   }

   // $FF: synthetic method
   static Object or$(final HeytingGenBoolOverlap $this, final Object x, final Object y, final Heyting ev) {
      return $this.or(x, y, ev);
   }

   default Object or(final Object x, final Object y, final Heyting ev) {
      return ev.or(x, y);
   }

   // $FF: synthetic method
   static Object xor$(final HeytingGenBoolOverlap $this, final Object x, final Object y, final Heyting ev) {
      return $this.xor(x, y, ev);
   }

   default Object xor(final Object x, final Object y, final Heyting ev) {
      return ev.xor(x, y);
   }

   // $FF: synthetic method
   static int and$mIc$sp$(final HeytingGenBoolOverlap $this, final int x, final int y, final Heyting ev) {
      return $this.and$mIc$sp(x, y, ev);
   }

   default int and$mIc$sp(final int x, final int y, final Heyting ev) {
      return ev.and$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static long and$mJc$sp$(final HeytingGenBoolOverlap $this, final long x, final long y, final Heyting ev) {
      return $this.and$mJc$sp(x, y, ev);
   }

   default long and$mJc$sp(final long x, final long y, final Heyting ev) {
      return ev.and$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static int or$mIc$sp$(final HeytingGenBoolOverlap $this, final int x, final int y, final Heyting ev) {
      return $this.or$mIc$sp(x, y, ev);
   }

   default int or$mIc$sp(final int x, final int y, final Heyting ev) {
      return ev.or$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static long or$mJc$sp$(final HeytingGenBoolOverlap $this, final long x, final long y, final Heyting ev) {
      return $this.or$mJc$sp(x, y, ev);
   }

   default long or$mJc$sp(final long x, final long y, final Heyting ev) {
      return ev.or$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static int xor$mIc$sp$(final HeytingGenBoolOverlap $this, final int x, final int y, final Heyting ev) {
      return $this.xor$mIc$sp(x, y, ev);
   }

   default int xor$mIc$sp(final int x, final int y, final Heyting ev) {
      return ev.xor$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static long xor$mJc$sp$(final HeytingGenBoolOverlap $this, final long x, final long y, final Heyting ev) {
      return $this.xor$mJc$sp(x, y, ev);
   }

   default long xor$mJc$sp(final long x, final long y, final Heyting ev) {
      return ev.xor$mcJ$sp(x, y);
   }

   static void $init$(final HeytingGenBoolOverlap $this) {
   }
}
