package com.twitter.chill;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i;QAD\b\t\u0002Y1Q\u0001G\b\t\u0002eAQ\u0001K\u0001\u0005\u0002%BqAK\u0001C\u0002\u0013%1\u0006\u0003\u00040\u0003\u0001\u0006I\u0001\f\u0005\ba\u0005\u0001\r\u0011\"\u00032\u0011\u001d)\u0014\u00011A\u0005\nYBa\u0001P\u0001!B\u0013\u0011\u0004\"B!\u0002\t\u0003\t\u0004\"\u0002\"\u0002\t\u0013\u0019\u0005bB$\u0002\u0003\u0003%I\u0001\u0013\u0004\u00051=\u0001q\nC\u0003)\u0017\u0011\u00051\u000bC\u0003V\u0017\u0011\u0005c+A\u000bTG\u0006d\u0017m\u0013:z_&s7\u000f^1oi&\fGo\u001c:\u000b\u0005A\t\u0012!B2iS2d'B\u0001\n\u0014\u0003\u001d!x/\u001b;uKJT\u0011\u0001F\u0001\u0004G>l7\u0001\u0001\t\u0003/\u0005i\u0011a\u0004\u0002\u0016'\u000e\fG.Y&ss>Len\u001d;b]RL\u0017\r^8s'\r\t!\u0004\t\t\u00037yi\u0011\u0001\b\u0006\u0002;\u0005)1oY1mC&\u0011q\u0004\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u00052S\"\u0001\u0012\u000b\u0005\r\"\u0013AA5p\u0015\u0005)\u0013\u0001\u00026bm\u0006L!a\n\u0012\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\u00051\u0012!B7vi\u0016DX#\u0001\u0017\u0013\u00075R\u0002E\u0002\u0003/\t\u0001a#\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0014AB7vi\u0016D\b%A\u0003la>|G.F\u00013!\t92'\u0003\u00025\u001f\tA1J]=p!>|G.A\u0005la>|Gn\u0018\u0013fcR\u0011qG\u000f\t\u00037aJ!!\u000f\u000f\u0003\tUs\u0017\u000e\u001e\u0005\bw\u0019\t\t\u00111\u00013\u0003\rAH%M\u0001\u0007WB|w\u000e\u001c\u0011)\u0005\u001dq\u0004CA\u000e@\u0013\t\u0001EDA\u0005ue\u0006t7/[3oi\u0006YA-\u001a4bk2$\bk\\8m\u000319W/Z:t)\"\u0014X-\u00193t+\u0005!\u0005CA\u000eF\u0013\t1EDA\u0002J]R\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012!\u0013\t\u0003\u00156k\u0011a\u0013\u0006\u0003\u0019\u0012\nA\u0001\\1oO&\u0011aj\u0013\u0002\u0007\u001f\nTWm\u0019;\u0014\u0005-\u0001\u0006CA\fR\u0013\t\u0011vB\u0001\u000eF[B$\u0018pU2bY\u0006\\%/_8J]N$\u0018M\u001c;jCR|'\u000fF\u0001U!\t92\"A\u0004oK^\\%/_8\u0015\u0003]\u0003\"a\u0006-\n\u0005e{!\u0001C&ss>\u0014\u0015m]3"
)
public class ScalaKryoInstantiator extends EmptyScalaKryoInstantiator {
   public static KryoPool defaultPool() {
      return ScalaKryoInstantiator$.MODULE$.defaultPool();
   }

   public KryoBase newKryo() {
      KryoBase k = super.newKryo();
      AllScalaRegistrar reg = new AllScalaRegistrar();
      reg.apply(k);
      return k;
   }
}
