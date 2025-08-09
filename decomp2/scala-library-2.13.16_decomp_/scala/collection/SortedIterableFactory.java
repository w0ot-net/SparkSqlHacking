package scala.collection;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u3q\u0001C\u0005\u0011\u0002G\u0005abB\u0003.\u0013!\u0005aFB\u0003\t\u0013!\u0005q\u0006C\u00039\u0005\u0011\u0005\u0011H\u0002\u0003;\u0005\u0001Y\u0004\u0002C%\u0005\u0005\u0003\u0005\u000b\u0011\u0002&\t\u000ba\"A\u0011A&\t\u000fU\u0013\u0011\u0011!C\u0005-\n)2k\u001c:uK\u0012LE/\u001a:bE2,g)Y2u_JL(B\u0001\u0006\f\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u0019\u0005)1oY1mC\u000e\u0001QCA\b\u001b'\r\u0001\u0001\u0003\u0006\t\u0003#Ii\u0011aC\u0005\u0003'-\u0011a!\u00118z%\u00164\u0007\u0003B\u000b\u00171\u0019j\u0011!C\u0005\u0003/%\u0011q#\u0012<jI\u0016t7-Z%uKJ\f'\r\\3GC\u000e$xN]=\u0011\u0005eQB\u0002\u0001\u0003\u00077\u0001!)\u0019\u0001\u000f\u0003\u0005\r\u001bUCA\u000f%#\tq\u0012\u0005\u0005\u0002\u0012?%\u0011\u0001e\u0003\u0002\b\u001d>$\b.\u001b8h!\t\t\"%\u0003\u0002$\u0017\t\u0019\u0011I\\=\u0005\u000b\u0015R\"\u0019A\u000f\u0003\u000b}#CE\r\u001d\u0011\u0005\u001dRcBA\t)\u0013\tI3\"A\u0004qC\u000e\\\u0017mZ3\n\u0005-b#\u0001C(sI\u0016\u0014\u0018N\\4\u000b\u0005%Z\u0011!F*peR,G-\u0013;fe\u0006\u0014G.\u001a$bGR|'/\u001f\t\u0003+\t\u00192A\u0001\t1!\t\td'D\u00013\u0015\t\u0019D'\u0001\u0002j_*\tQ'\u0001\u0003kCZ\f\u0017BA\u001c3\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\taF\u0001\u0005EK2,w-\u0019;f+\taDiE\u0002\u0005{!\u0003BAP!DM9\u0011QcP\u0005\u0003\u0001&\tq#\u0012<jI\u0016t7-Z%uKJ\f'\r\\3GC\u000e$xN]=\n\u0005i\u0012%B\u0001!\n!\tIB\tB\u0003\u001c\t\t\u0007Q)\u0006\u0002\u001e\r\u0012)q\t\u0012b\u0001;\t)q\f\n\u00133sA\u0019Q\u0003A\"\u0002\u0011\u0011,G.Z4bi\u0016\u0004B!\u0006\fDMQ\u0011AJ\u0014\t\u0004\u001b\u0012\u0019U\"\u0001\u0002\t\u000b%3\u0001\u0019\u0001&)\t\u0011\u00016\u000b\u0016\t\u0003#EK!AU\u0006\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g$A\u0002\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003]\u0003\"\u0001W.\u000e\u0003eS!A\u0017\u001b\u0002\t1\fgnZ\u0005\u00039f\u0013aa\u00142kK\u000e$\b"
)
public interface SortedIterableFactory extends EvidenceIterableFactory {
   public static class Delegate extends EvidenceIterableFactory.Delegate implements SortedIterableFactory {
      private static final long serialVersionUID = 3L;

      public Delegate(final EvidenceIterableFactory delegate) {
         super(delegate);
      }
   }
}
