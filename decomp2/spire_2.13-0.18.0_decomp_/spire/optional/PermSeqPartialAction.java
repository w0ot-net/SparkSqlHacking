package spire.optional;

import scala.collection.Factory;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import spire.algebra.partial.LeftPartialAction;
import spire.algebra.partial.PartialAction;
import spire.algebra.partial.RightPartialAction;

@ScalaSignature(
   bytes = "\u0006\u0005A3A!\u0002\u0004\u0003\u0017!Aq\u0007\u0001B\u0001B\u0003-\u0001\bC\u0003<\u0001\u0011\u0005A\bC\u0003A\u0001\u0011\u0005\u0011\tC\u0003M\u0001\u0011\u0005QJ\u0001\u000bQKJl7+Z9QCJ$\u0018.\u00197BGRLwN\u001c\u0006\u0003\u000f!\t\u0001b\u001c9uS>t\u0017\r\u001c\u0006\u0002\u0013\u0005)1\u000f]5sK\u000e\u0001Qc\u0001\u0007+;M\u0019\u0001!D\n\u0011\u00059\tR\"A\b\u000b\u0003A\tQa]2bY\u0006L!AE\b\u0003\r\u0005s\u0017PU3g!\u0011!\u0012dG\u001a\u000e\u0003UQ!AF\f\u0002\u000fA\f'\u000f^5bY*\u0011\u0001\u0004C\u0001\bC2<WM\u0019:b\u0013\tQRCA\u0007QCJ$\u0018.\u00197BGRLwN\u001c\t\u00039ua\u0001\u0001B\u0003\u001f\u0001\t\u0007qD\u0001\u0002T\u0003F\u0011\u0001e\t\t\u0003\u001d\u0005J!AI\b\u0003\u000f9{G\u000f[5oOB)AeJ\u0015175\tQE\u0003\u0002'\u001f\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005!*#AB*fc>\u00038\u000f\u0005\u0002\u001dU\u0011)1\u0006\u0001b\u0001Y\t\t\u0011)\u0005\u0002![A\u0011aBL\u0005\u0003_=\u00111!\u00118z!\t!\u0013'\u0003\u00023K\t\u00191+Z9\u0011\u0005Q*T\"\u0001\u0004\n\u0005Y2!\u0001\u0002)fe6\f1a\u00192g!\u0011!\u0013(K\u000e\n\u0005i*#a\u0002$bGR|'/_\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003u\"\"AP \u0011\tQ\u0002\u0011f\u0007\u0005\u0006o\t\u0001\u001d\u0001O\u0001\fa\u0006\u0014H/[1m\u0003\u000e$H\u000eF\u0002C\u0011*\u00032a\u0011$\u001c\u001b\u0005!%BA#\t\u0003\u0011)H/\u001b7\n\u0005\u001d#%aA(qi\")\u0011j\u0001a\u0001g\u0005!\u0001/\u001a:n\u0011\u0015Y5\u00011\u0001\u001c\u0003\t\u0019\u0018-A\u0006qCJ$\u0018.\u00197BGR\u0014Hc\u0001\"O\u001f\")1\n\u0002a\u00017!)\u0011\n\u0002a\u0001g\u0001"
)
public final class PermSeqPartialAction implements PartialAction {
   private final Factory cbf;

   public boolean actrIsDefined(final Object p, final Object g) {
      return RightPartialAction.actrIsDefined$(this, p, g);
   }

   public boolean actlIsDefined(final Object g, final Object p) {
      return LeftPartialAction.actlIsDefined$(this, g, p);
   }

   public SeqOps partialActl(final Perm perm, final SeqOps sa) {
      return (SeqOps)perm.permute(sa, this.cbf);
   }

   public SeqOps partialActr(final SeqOps sa, final Perm perm) {
      return this.partialActl(perm.inverse(), sa);
   }

   public PermSeqPartialAction(final Factory cbf) {
      this.cbf = cbf;
      LeftPartialAction.$init$(this);
      RightPartialAction.$init$(this);
   }
}
