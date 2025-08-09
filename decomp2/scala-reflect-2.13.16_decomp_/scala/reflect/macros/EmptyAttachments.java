package scala.reflect.macros;

import scala.Option;
import scala.Predef.;
import scala.collection.immutable.Set;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Position;

@ScalaSignature(
   bytes = "\u0006\u0005A4q\u0001C\u0005\u0002\u0002-ya\u000eC\u0003\u0015\u0001\u0011\u0005a\u0003C\u0003\u0019\u0001\u0011\u0015\u0013\u0004C\u0003*\u0001\u0011\u0015#\u0006C\u0003>\u0001\u0011\u0015c\bC\u0003I\u0001\u0011\u0015\u0013\nC\u0003b\u0001\u0011\u0015#\rC\u0003m\u0001\u0011\u0015SN\u0001\tF[B$\u00180\u0011;uC\u000eDW.\u001a8ug*\u0011!bC\u0001\u0007[\u0006\u001c'o\\:\u000b\u00051i\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u001d\u0005)1oY1mCN\u0011\u0001\u0001\u0005\t\u0003#Ii\u0011!C\u0005\u0003'%\u00111\"\u0011;uC\u000eDW.\u001a8ug\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0018!\t\t\u0002!A\u0002bY2,\u0012A\u0007\t\u00047\t*cB\u0001\u000f!!\tiR\"D\u0001\u001f\u0015\tyR#\u0001\u0004=e>|GOP\u0005\u0003C5\ta\u0001\u0015:fI\u00164\u0017BA\u0012%\u0005\r\u0019V\r\u001e\u0006\u0003C5\u0001\"AJ\u0014\u000e\u00035I!\u0001K\u0007\u0003\u0007\u0005s\u00170A\u0002hKR,\"aK\u0019\u0015\u00051:\u0004c\u0001\u0014._%\u0011a&\u0004\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005A\nD\u0002\u0001\u0003\u0006e\r\u0011\ra\r\u0002\u0002)F\u0011A'\n\t\u0003MUJ!AN\u0007\u0003\u000f9{G\u000f[5oO\"9\u0001hAA\u0001\u0002\bI\u0014AC3wS\u0012,gnY3%mA\u0019!hO\u0018\u000e\u0003-I!\u0001P\u0006\u0003\u0011\rc\u0017m]:UC\u001e\f\u0001bY8oi\u0006Lgn]\u000b\u0003\u007f\u001d#\"\u0001Q\"\u0011\u0005\u0019\n\u0015B\u0001\"\u000e\u0005\u001d\u0011un\u001c7fC:Dq\u0001\u0012\u0003\u0002\u0002\u0003\u000fQ)\u0001\u0006fm&$WM\\2fI]\u00022AO\u001eG!\t\u0001t\tB\u00033\t\t\u00071'\u0001\u0004va\u0012\fG/Z\u000b\u0003\u0015z#\"aS0\u0015\u00051S&CA'\u0011\r\u0011q\u0005\u0001\u0001'\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0006\tAk\u0005%\u0015\u0002\u0004!>\u001c\bC\u0001*T\u001b\u0005\u0001\u0011B\u0001)U\u0013\t)fK\u0001\u0005Q_NLG/[8o\u0015\t9\u0006,\u0001\u0003vi&d'BA-\f\u0003!Ig\u000e^3s]\u0006d\u0007bB.\u0006\u0003\u0003\u0005\u001d\u0001X\u0001\u000bKZLG-\u001a8dK\u0012B\u0004c\u0001\u001e<;B\u0011\u0001G\u0018\u0003\u0006e\u0015\u0011\ra\r\u0005\u0006A\u0016\u0001\r!X\u0001\u0007]\u0016<\u0018\t\u001e;\u0002\rI,Wn\u001c<f+\t\u00197\u000e\u0006\u0002eOJ\u0011Q\r\u0005\u0004\u0005\u001d\u0002\u0001A-\u0002\u0003QK\u0002\n\u0006b\u00025\u0007\u0003\u0003\u0005\u001d![\u0001\u000bKZLG-\u001a8dK\u0012J\u0004c\u0001\u001e<UB\u0011\u0001g\u001b\u0003\u0006e\u0019\u0011\raM\u0001\bSN,U\u000e\u001d;z+\u0005\u0001\u0005CA8U\u001b\u00051\u0006"
)
public abstract class EmptyAttachments extends Attachments {
   public final Set all() {
      if (.MODULE$.Set() == null) {
         throw null;
      } else {
         return scala.collection.immutable.Set.EmptySet..MODULE$;
      }
   }

   public final Option get(final ClassTag evidence$6) {
      return scala.None..MODULE$;
   }

   public final boolean contains(final ClassTag evidence$7) {
      return false;
   }

   public final Attachments update(final Object newAtt, final ClassTag evidence$8) {
      return new SingleAttachment(((Position)this).pos(), newAtt);
   }

   public final Attachments remove(final ClassTag evidence$9) {
      return this;
   }

   public final boolean isEmpty() {
      return true;
   }
}
