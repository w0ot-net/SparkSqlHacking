package com.twitter.chill;

import org.objenesis.strategy.StdInstantiatorStrategy;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a1Aa\u0001\u0003\u0001\u0017!)\u0001\u0003\u0001C\u0001#!)1\u0003\u0001C!)\tQR)\u001c9usN\u001b\u0017\r\\1Lef|\u0017J\\:uC:$\u0018.\u0019;pe*\u0011QAB\u0001\u0006G\"LG\u000e\u001c\u0006\u0003\u000f!\tq\u0001^<jiR,'OC\u0001\n\u0003\r\u0019w.\\\u0002\u0001'\t\u0001A\u0002\u0005\u0002\u000e\u001d5\tA!\u0003\u0002\u0010\t\t\u00012J]=p\u0013:\u001cH/\u00198uS\u0006$xN]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003I\u0001\"!\u0004\u0001\u0002\u000f9,wo\u0013:z_R\tQ\u0003\u0005\u0002\u000e-%\u0011q\u0003\u0002\u0002\t\u0017JLxNQ1tK\u0002"
)
public class EmptyScalaKryoInstantiator extends KryoInstantiator {
   public KryoBase newKryo() {
      KryoBase k = new KryoBase();
      k.setRegistrationRequired(false);
      k.setInstantiatorStrategy(new StdInstantiatorStrategy());
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      k.setClassLoader(classLoader);
      return k;
   }
}
