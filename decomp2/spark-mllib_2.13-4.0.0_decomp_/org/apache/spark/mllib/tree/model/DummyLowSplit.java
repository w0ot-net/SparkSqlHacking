package org.apache.spark.mllib.tree.model;

import scala.Enumeration;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y2Q\u0001B\u0003\u0001\u000fEA\u0011B\u0006\u0001\u0003\u0002\u0003\u0006I\u0001\u0007\u0010\t\u0013}\u0001!\u0011!Q\u0001\n\u0001B\u0004\"B\u001d\u0001\t\u0003Q$!\u0004#v[6LHj\\<Ta2LGO\u0003\u0002\u0007\u000f\u0005)Qn\u001c3fY*\u0011\u0001\"C\u0001\u0005iJ,WM\u0003\u0002\u000b\u0017\u0005)Q\u000e\u001c7jE*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xm\u0005\u0002\u0001%A\u00111\u0003F\u0007\u0002\u000b%\u0011Q#\u0002\u0002\u0006'Bd\u0017\u000e^\u0001\bM\u0016\fG/\u001e:f\u0007\u0001\u0001\"!\u0007\u000f\u000e\u0003iQ\u0011aG\u0001\u0006g\u000e\fG.Y\u0005\u0003;i\u00111!\u00138u\u0013\t1B#A\u0006gK\u0006$XO]3UsB,\u0007CA\u00116\u001d\t\u0011#G\u0004\u0002$a9\u0011Ae\f\b\u0003K9r!AJ\u0017\u000f\u0005\u001dbcB\u0001\u0015,\u001b\u0005I#B\u0001\u0016\u0018\u0003\u0019a$o\\8u}%\t\u0001#\u0003\u0002\u000f\u001f%\u0011A\"D\u0005\u0003\u0015-I!\u0001C\u0005\n\u0005E:\u0011!D2p]\u001aLw-\u001e:bi&|g.\u0003\u00024i\u0005Ya)Z1ukJ,G+\u001f9f\u0015\t\tt!\u0003\u00027o\tYa)Z1ukJ,G+\u001f9f\u0015\t\u0019D'\u0003\u0002 )\u00051A(\u001b8jiz\"2a\u000f\u001f>!\t\u0019\u0002\u0001C\u0003\u0017\u0007\u0001\u0007\u0001\u0004C\u0003 \u0007\u0001\u0007\u0001\u0005"
)
public class DummyLowSplit extends Split {
   public DummyLowSplit(final int feature, final Enumeration.Value featureType) {
      super(feature, -Double.MAX_VALUE, featureType, .MODULE$);
   }
}
