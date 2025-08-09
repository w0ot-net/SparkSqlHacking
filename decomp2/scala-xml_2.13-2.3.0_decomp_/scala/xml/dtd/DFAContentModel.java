package scala.xml.dtd;

import scala.reflect.ScalaSignature;
import scala.xml.dtd.impl.Base;
import scala.xml.dtd.impl.DetWordAutom;
import scala.xml.dtd.impl.NondetWordAutom;
import scala.xml.dtd.impl.SubsetConstruction;

@ScalaSignature(
   bytes = "\u0006\u0005=2Q\u0001B\u0003\u0002\"1AQ!\u0005\u0001\u0005\u0002IAQ\u0001\u0006\u0001\u0007\u0002UA\u0001\u0002\t\u0001\t\u0006\u0004%\t!\t\u0002\u0010\t\u001a\u000b5i\u001c8uK:$Xj\u001c3fY*\u0011aaB\u0001\u0004IR$'B\u0001\u0005\n\u0003\rAX\u000e\u001c\u0006\u0002\u0015\u0005)1oY1mC\u000e\u00011C\u0001\u0001\u000e!\tqq\"D\u0001\u0006\u0013\t\u0001RA\u0001\u0007D_:$XM\u001c;N_\u0012,G.\u0001\u0004=S:LGO\u0010\u000b\u0002'A\u0011a\u0002A\u0001\u0002eV\ta\u0003\u0005\u0002\u001859\u0011a\u0002G\u0005\u00033\u0015\tAbQ8oi\u0016tG/T8eK2L!a\u0007\u000f\u0003\rI+w-\u0012=q\u0013\tibD\u0001\u0003CCN,'BA\u0010\u0006\u0003\u0011IW\u000e\u001d7\u0002\u0007\u00114\u0017-F\u0001#!\r\u0019CEJ\u0007\u0002=%\u0011QE\b\u0002\r\t\u0016$xk\u001c:e\u0003V$x.\u001c\t\u0003/\u001dJ!\u0001K\u0015\u0003\u0011\u0015cW-\u001c(b[\u0016T!!G\u0003*\u0007\u0001YS&\u0003\u0002-\u000b\tAQ\tT#N\u000b:#6+\u0003\u0002/\u000b\t)Q*\u0013-F\t\u0002"
)
public abstract class DFAContentModel extends ContentModel {
   private DetWordAutom dfa;
   private volatile boolean bitmap$0;

   public abstract Base.RegExp r();

   private DetWordAutom dfa$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            NondetWordAutom nfa = ContentModel.Translator$.MODULE$.automatonFrom(this.r(), 1);
            this.dfa = (new SubsetConstruction(nfa)).determinize();
            this.bitmap$0 = true;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.dfa;
   }

   public DetWordAutom dfa() {
      return !this.bitmap$0 ? this.dfa$lzycompute() : this.dfa;
   }
}
