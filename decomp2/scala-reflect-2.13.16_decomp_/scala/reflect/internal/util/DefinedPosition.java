package scala.reflect.internal.util;

import scala.package.;
import scala.reflect.ScalaSignature;
import scala.reflect.io.AbstractFile;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3Q\u0001C\u0005\u0002\"IAQa\u0006\u0001\u0005\u0002aAQA\u0007\u0001\u0005FmAQ\u0001\t\u0001\u0005B\u0005BQa\n\u0001\u0005B!BQ\u0001\f\u0001\u0005B5BQA\u000e\u0001\u0005\n]BQ\u0001\u000f\u0001\u0005\ne\u0012q\u0002R3gS:,G\rU8tSRLwN\u001c\u0006\u0003\u0015-\tA!\u001e;jY*\u0011A\"D\u0001\tS:$XM\u001d8bY*\u0011abD\u0001\be\u00164G.Z2u\u0015\u0005\u0001\u0012!B:dC2\f7\u0001A\n\u0003\u0001M\u0001\"\u0001F\u000b\u000e\u0003%I!AF\u0005\u0003\u0011A{7/\u001b;j_:\fa\u0001P5oSRtD#A\r\u0011\u0005Q\u0001\u0011!C5t\t\u00164\u0017N\\3e+\u0005a\u0002CA\u000f\u001f\u001b\u0005y\u0011BA\u0010\u0010\u0005\u001d\u0011un\u001c7fC:\fa!Z9vC2\u001cHC\u0001\u000f#\u0011\u0015\u00193\u00011\u0001%\u0003\u0011!\b.\u0019;\u0011\u0005u)\u0013B\u0001\u0014\u0010\u0005\r\te._\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0011\u0006\u0005\u0002\u001eU%\u00111f\u0004\u0002\u0004\u0013:$\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u00039\u0002\"a\f\u001b\u000e\u0003AR!!\r\u001a\u0002\t1\fgn\u001a\u0006\u0002g\u0005!!.\u0019<b\u0013\t)\u0004G\u0001\u0004TiJLgnZ\u0001\ra>Lg\u000e^'fgN\fw-Z\u000b\u0002]\u0005i1-\u00198p]&\u001c\u0017\r\u001c)bi\",\u0012A\u000f\t\u0003w\ts!\u0001\u0010!\u0011\u0005uzQ\"\u0001 \u000b\u0005}\n\u0012A\u0002\u001fs_>$h(\u0003\u0002B\u001f\u00051\u0001K]3eK\u001aL!!N\"\u000b\u0005\u0005{\u0011F\u0001\u0001F\u0013\t1\u0015B\u0001\bPM\u001a\u001cX\r\u001e)pg&$\u0018n\u001c8"
)
public abstract class DefinedPosition extends Position {
   public final boolean isDefined() {
      return true;
   }

   public boolean equals(final Object that) {
      if (!(that instanceof DefinedPosition)) {
         return false;
      } else {
         DefinedPosition var2 = (DefinedPosition)that;
         AbstractFile var10000 = this.source().file();
         AbstractFile var3 = var2.source().file();
         if (var10000 == null) {
            if (var3 != null) {
               return false;
            }
         } else if (!var10000.equals(var3)) {
            return false;
         }

         if (this.start() == var2.start() && this.point() == var2.point() && this.end() == var2.end()) {
            return true;
         } else {
            return false;
         }
      }
   }

   public int hashCode() {
      return Statics.anyHash(.MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.source().file(), this.start(), this.point(), this.end()})));
   }

   public String toString() {
      return this.isRange() ? (new StringBuilder(21)).append("RangePosition(").append(this.canonicalPath()).append(", ").append(this.start()).append(", ").append(this.point()).append(", ").append(this.end()).append(")").toString() : (new StringBuilder(14)).append("source-").append(this.canonicalPath()).append(",line-").append(this.line()).append(",").append(this.pointMessage()).append(this.point()).toString();
   }

   private String pointMessage() {
      return this.point() > this.source().length() ? "out-of-bounds-" : "offset=";
   }

   private String canonicalPath() {
      return this.source().file().canonicalPath();
   }
}
