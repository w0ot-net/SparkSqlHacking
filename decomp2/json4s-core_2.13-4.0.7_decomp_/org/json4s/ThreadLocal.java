package org.json4s;

import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E2Q!\u0002\u0004\u0001\r)A\u0001\"\n\u0001\u0003\u0002\u0013\u0006IA\n\u0005\u0006S\u0001!\tA\u000b\u0005\u0006]\u0001!\te\f\u0005\u0006a\u0001!\ta\f\u0002\f)\"\u0014X-\u00193M_\u000e\fGN\u0003\u0002\b\u0011\u00051!n]8oiMT\u0011!C\u0001\u0004_J<WCA\u0006\u0016'\r\u0001AB\t\t\u0004\u001bI\u0019R\"\u0001\b\u000b\u0005=\u0001\u0012\u0001\u00027b]\u001eT\u0011!E\u0001\u0005U\u00064\u0018-\u0003\u0002\u0006\u001dA\u0011A#\u0006\u0007\u0001\t\u00151\u0002A1\u0001\u0019\u0005\u0005\t5\u0001A\t\u00033}\u0001\"AG\u000f\u000e\u0003mQ\u0011\u0001H\u0001\u0006g\u000e\fG.Y\u0005\u0003=m\u0011qAT8uQ&tw\r\u0005\u0002\u001bA%\u0011\u0011e\u0007\u0002\u0004\u0003:L\bc\u0001\u000e$'%\u0011Ae\u0007\u0002\n\rVt7\r^5p]B\nA!\u001b8jiB\u0019!dJ\n\n\u0005!Z\"\u0001\u0003\u001fcs:\fW.\u001a \u0002\rqJg.\u001b;?)\tYS\u0006E\u0002-\u0001Mi\u0011A\u0002\u0005\u0007K\t!\t\u0019\u0001\u0014\u0002\u0019%t\u0017\u000e^5bYZ\u000bG.^3\u0015\u0003M\tQ!\u00199qYf\u0004"
)
public class ThreadLocal extends java.lang.ThreadLocal implements Function0 {
   private final Function0 init;

   public boolean apply$mcZ$sp() {
      return Function0.apply$mcZ$sp$(this);
   }

   public byte apply$mcB$sp() {
      return Function0.apply$mcB$sp$(this);
   }

   public char apply$mcC$sp() {
      return Function0.apply$mcC$sp$(this);
   }

   public double apply$mcD$sp() {
      return Function0.apply$mcD$sp$(this);
   }

   public float apply$mcF$sp() {
      return Function0.apply$mcF$sp$(this);
   }

   public int apply$mcI$sp() {
      return Function0.apply$mcI$sp$(this);
   }

   public long apply$mcJ$sp() {
      return Function0.apply$mcJ$sp$(this);
   }

   public short apply$mcS$sp() {
      return Function0.apply$mcS$sp$(this);
   }

   public void apply$mcV$sp() {
      Function0.apply$mcV$sp$(this);
   }

   public String toString() {
      return Function0.toString$(this);
   }

   public Object initialValue() {
      return this.init.apply();
   }

   public Object apply() {
      return this.get();
   }

   public ThreadLocal(final Function0 init) {
      this.init = init;
      Function0.$init$(this);
   }
}
