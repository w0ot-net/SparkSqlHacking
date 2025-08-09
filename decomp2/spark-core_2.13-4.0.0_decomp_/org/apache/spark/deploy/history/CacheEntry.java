package org.apache.spark.deploy.history;

import org.apache.spark.ui.SparkUI;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e2Qa\u0002\u0005\u0003\u0011IA\u0001\"\u0007\u0001\u0003\u0006\u0004%\ta\u0007\u0005\tA\u0001\u0011\t\u0011)A\u00059!A\u0011\u0005\u0001BC\u0002\u0013\u0005!\u0005\u0003\u0005'\u0001\t\u0005\t\u0015!\u0003$\u0011\u00159\u0003\u0001\"\u0001)\u0011\u0015a\u0003\u0001\"\u0011.\u0005)\u0019\u0015m\u00195f\u000b:$(/\u001f\u0006\u0003\u0013)\tq\u0001[5ti>\u0014\u0018P\u0003\u0002\f\u0019\u00051A-\u001a9m_fT!!\u0004\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005=\u0001\u0012AB1qC\u000eDWMC\u0001\u0012\u0003\ry'oZ\n\u0003\u0001M\u0001\"\u0001F\f\u000e\u0003UQ\u0011AF\u0001\u0006g\u000e\fG.Y\u0005\u00031U\u0011a!\u00118z%\u00164\u0017\u0001\u00037pC\u0012,G-V%\u0004\u0001U\tA\u0004\u0005\u0002\u001e=5\t\u0001\"\u0003\u0002 \u0011\tYAj\\1eK\u0012\f\u0005\u000f]+J\u0003%aw.\u00193fIVK\u0005%A\u0005d_6\u0004H.\u001a;fIV\t1\u0005\u0005\u0002\u0015I%\u0011Q%\u0006\u0002\b\u0005>|G.Z1o\u0003)\u0019w.\u001c9mKR,G\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007%R3\u0006\u0005\u0002\u001e\u0001!)\u0011$\u0002a\u00019!)\u0011%\u0002a\u0001G\u0005AAo\\*ue&tw\rF\u0001/!\tycG\u0004\u00021iA\u0011\u0011'F\u0007\u0002e)\u00111GG\u0001\u0007yI|w\u000e\u001e \n\u0005U*\u0012A\u0002)sK\u0012,g-\u0003\u00028q\t11\u000b\u001e:j]\u001eT!!N\u000b"
)
public final class CacheEntry {
   private final LoadedAppUI loadedUI;
   private final boolean completed;

   public LoadedAppUI loadedUI() {
      return this.loadedUI;
   }

   public boolean completed() {
      return this.completed;
   }

   public String toString() {
      SparkUI var10000 = this.loadedUI().ui();
      return "UI " + var10000 + ", completed=" + this.completed();
   }

   public CacheEntry(final LoadedAppUI loadedUI, final boolean completed) {
      this.loadedUI = loadedUI;
      this.completed = completed;
   }
}
