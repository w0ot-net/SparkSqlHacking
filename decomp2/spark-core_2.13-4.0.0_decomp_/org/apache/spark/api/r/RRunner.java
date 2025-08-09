package org.apache.spark.api.r;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.broadcast.Broadcast;
import scala.Function1;
import scala.MatchError;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001de!\u0002\u000b\u0016\u0001ey\u0002\u0002C\u001c\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001d\t\u0011y\u0002!\u0011!Q\u0001\n}B\u0001B\u0013\u0001\u0003\u0002\u0003\u0006Ia\u0010\u0005\t\u0017\u0002\u0011\t\u0011)A\u0005q!AA\n\u0001B\u0001B\u0003%Q\n\u0003\u0005]\u0001\t\u0005\t\u0015!\u0003^\u0011!\u0001\u0007A!A!\u0002\u0013\t\u0007\u0002\u00033\u0001\u0005\u0003\u0005\u000b\u0011B3\t\u0011\u0019\u0004!\u0011!Q\u0001\nuCQa\u001a\u0001\u0005\u0002!DQa\u001d\u0001\u0005\u0012QDq!!\u0004\u0001\t#\nya\u0002\u0006\u0002<U\t\t\u0011#\u0001\u001a\u0003{1\u0011\u0002F\u000b\u0002\u0002#\u0005\u0011$a\u0010\t\r\u001dtA\u0011AA$\u0011%\tIEDI\u0001\n\u0003\tY\u0005C\u0005\u0002h9\t\n\u0011\"\u0001\u0002j!I\u00111\u000f\b\u0012\u0002\u0013\u0005\u0011Q\u000f\u0005\n\u0003\u007fr\u0011\u0013!C\u0001\u0003\u0003\u0013qA\u0015*v]:,'O\u0003\u0002\u0017/\u0005\t!O\u0003\u0002\u00193\u0005\u0019\u0011\r]5\u000b\u0005iY\u0012!B:qCJ\\'B\u0001\u000f\u001e\u0003\u0019\t\u0007/Y2iK*\ta$A\u0002pe\u001e,2\u0001I\u00146'\t\u0001\u0011\u0005\u0005\u0003#G\u0015\"T\"A\u000b\n\u0005\u0011*\"a\u0003\"bg\u0016\u0014&+\u001e8oKJ\u0004\"AJ\u0014\r\u0001\u0011)\u0001\u0006\u0001b\u0001U\t\u0011\u0011JT\u0002\u0001#\tY\u0013\u0007\u0005\u0002-_5\tQFC\u0001/\u0003\u0015\u00198-\u00197b\u0013\t\u0001TFA\u0004O_RD\u0017N\\4\u0011\u00051\u0012\u0014BA\u001a.\u0005\r\te.\u001f\t\u0003MU\"QA\u000e\u0001C\u0002)\u00121aT+U\u0003\u00111WO\\2\u0011\u00071J4(\u0003\u0002;[\t)\u0011I\u001d:bsB\u0011A\u0006P\u0005\u0003{5\u0012AAQ=uK\u0006aA-Z:fe&\fG.\u001b>feB\u0011\u0001i\u0012\b\u0003\u0003\u0016\u0003\"AQ\u0017\u000e\u0003\rS!\u0001R\u0015\u0002\rq\u0012xn\u001c;?\u0013\t1U&\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0011&\u0013aa\u0015;sS:<'B\u0001$.\u0003)\u0019XM]5bY&TXM]\u0001\ra\u0006\u001c7.Y4f\u001d\u0006lWm]\u0001\u000eEJ|\u0017\rZ2bgR4\u0016M]:\u0011\u00071Jd\nE\u0002P%Rk\u0011\u0001\u0015\u0006\u0003#f\t\u0011B\u0019:pC\u0012\u001c\u0017m\u001d;\n\u0005M\u0003&!\u0003\"s_\u0006$7-Y:u!\t)&,D\u0001W\u0015\t9\u0006,\u0001\u0003mC:<'\"A-\u0002\t)\fg/Y\u0005\u00037Z\u0013aa\u00142kK\u000e$\u0018!\u00048v[B\u000b'\u000f^5uS>t7\u000f\u0005\u0002-=&\u0011q,\f\u0002\u0004\u0013:$\u0018aC5t\t\u0006$\u0018M\u0012:b[\u0016\u0004\"\u0001\f2\n\u0005\rl#a\u0002\"p_2,\u0017M\\\u0001\tG>dg*Y7fgB\u0019A&O \u0002\t5|G-Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0015%T7\u000e\\7o_B\f(\u000f\u0005\u0003#\u0001\u0015\"\u0004\"B\u001c\u000b\u0001\u0004A\u0004\"\u0002 \u000b\u0001\u0004y\u0004\"\u0002&\u000b\u0001\u0004y\u0004\"B&\u000b\u0001\u0004A\u0004\"\u0002'\u000b\u0001\u0004i\u0005b\u0002/\u000b!\u0003\u0005\r!\u0018\u0005\bA*\u0001\n\u00111\u0001b\u0011\u001d!'\u0002%AA\u0002\u0015DqA\u001a\u0006\u0011\u0002\u0003\u0007Q,A\toK^\u0014V-\u00193fe&#XM]1u_J$B!^=\u0002\u0004A\u0011ao^\u0007\u0002\u0001%\u0011\u0001p\t\u0002\u000f%\u0016\fG-\u001a:Ji\u0016\u0014\u0018\r^8s\u0011\u0015Q8\u00021\u0001|\u0003)!\u0017\r^1TiJ,\u0017-\u001c\t\u0003y~l\u0011! \u0006\u0003}b\u000b!![8\n\u0007\u0005\u0005QPA\bECR\f\u0017J\u001c9viN#(/Z1n\u0011\u001d\t)a\u0003a\u0001\u0003\u000f\t\u0011\"\u001a:s)\"\u0014X-\u00193\u0011\u0007\t\nI!C\u0002\u0002\fU\u0011ACQ;gM\u0016\u0014X\rZ*ue\u0016\fW\u000e\u00165sK\u0006$\u0017a\u00048fo^\u0013\u0018\u000e^3s)\"\u0014X-\u00193\u0015\u0011\u0005E\u0011qCA\u0011\u0003o\u00012A^A\n\u0013\r\t)b\t\u0002\r/JLG/\u001a:UQJ,\u0017\r\u001a\u0005\b\u00033a\u0001\u0019AA\u000e\u0003\u0019yW\u000f\u001e9viB\u0019A0!\b\n\u0007\u0005}QP\u0001\u0007PkR\u0004X\u000f^*ue\u0016\fW\u000eC\u0004\u0002$1\u0001\r!!\n\u0002\t%$XM\u001d\t\u0006\u0003O\t\t$\n\b\u0005\u0003S\tiCD\u0002C\u0003WI\u0011AL\u0005\u0004\u0003_i\u0013a\u00029bG.\fw-Z\u0005\u0005\u0003g\t)D\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0015\r\ty#\f\u0005\u0007\u0003sa\u0001\u0019A/\u0002\u001dA\f'\u000f^5uS>t\u0017J\u001c3fq\u00069!KU;o]\u0016\u0014\bC\u0001\u0012\u000f'\rq\u0011\u0011\t\t\u0004Y\u0005\r\u0013bAA#[\t1\u0011I\\=SK\u001a$\"!!\u0010\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00137+\u0019\ti%a\u0019\u0002fU\u0011\u0011q\n\u0016\u0004;\u0006E3FAA*!\u0011\t)&a\u0018\u000e\u0005\u0005]#\u0002BA-\u00037\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005uS&\u0001\u0006b]:|G/\u0019;j_:LA!!\u0019\u0002X\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000b!\u0002\"\u0019\u0001\u0016\u0005\u000bY\u0002\"\u0019\u0001\u0016\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00138+\u0019\tY'a\u001c\u0002rU\u0011\u0011Q\u000e\u0016\u0004C\u0006EC!\u0002\u0015\u0012\u0005\u0004QC!\u0002\u001c\u0012\u0005\u0004Q\u0013a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0003(\u0006\u0004\u0002x\u0005m\u0014QP\u000b\u0003\u0003sR3!ZA)\t\u0015A#C1\u0001+\t\u00151$C1\u0001+\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%sU1\u0011QJAB\u0003\u000b#Q\u0001K\nC\u0002)\"QAN\nC\u0002)\u0002"
)
public class RRunner extends BaseRRunner {
   public final String org$apache$spark$api$r$RRunner$$deserializer;
   public final String org$apache$spark$api$r$RRunner$$serializer;
   public final int org$apache$spark$api$r$RRunner$$numPartitions;

   public static int $lessinit$greater$default$9() {
      return RRunner$.MODULE$.$lessinit$greater$default$9();
   }

   public static String[] $lessinit$greater$default$8() {
      return RRunner$.MODULE$.$lessinit$greater$default$8();
   }

   public static boolean $lessinit$greater$default$7() {
      return RRunner$.MODULE$.$lessinit$greater$default$7();
   }

   public static int $lessinit$greater$default$6() {
      return RRunner$.MODULE$.$lessinit$greater$default$6();
   }

   public BaseRRunner.ReaderIterator newReaderIterator(final DataInputStream dataStream, final BufferedStreamThread errThread) {
      return new BaseRRunner.ReaderIterator(dataStream, errThread) {
         private final Function1 readData;
         // $FF: synthetic field
         private final RRunner $outer;
         private final DataInputStream dataStream$1;

         private Function1 readData() {
            return this.readData;
         }

         private Tuple2 readShuffledData(final int length) {
            switch (length) {
               default:
                  if (length == 2) {
                     int hashedKey = this.dataStream$1.readInt();
                     int contentPairsLength = this.dataStream$1.readInt();
                     byte[] contentPairs = new byte[contentPairsLength];
                     this.dataStream$1.readFully(contentPairs);
                     return new Tuple2(BoxesRunTime.boxToInteger(hashedKey), contentPairs);
                  } else {
                     return null;
                  }
            }
         }

         private byte[] readByteArrayData(final int length) {
            switch (length) {
               default:
                  if (length > 0) {
                     byte[] obj = new byte[length];
                     this.dataStream$1.readFully(obj);
                     return obj;
                  } else {
                     return null;
                  }
            }
         }

         private String readStringData(final int length) {
            switch (length) {
               default -> {
                  return length > 0 ? SerDe$.MODULE$.readStringBytes(this.dataStream$1, length) : null;
               }
            }
         }

         public Object read() {
            Object var10000;
            try {
               int length = this.dataStream$1.readInt();
               if (SpecialLengths$.MODULE$.TIMING_DATA() == length) {
                  double boot = this.dataStream$1.readDouble() - this.$outer.bootTime();
                  double init = this.dataStream$1.readDouble();
                  double broadcast = this.dataStream$1.readDouble();
                  double input = this.dataStream$1.readDouble();
                  double compute = this.dataStream$1.readDouble();
                  double output = this.dataStream$1.readDouble();
                  this.$outer.logInfo(() -> .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Times: boot = %.3f s, init = %.3f s, broadcast = %.3f s, read-input = %.3f s, compute = %.3f s, write-output = %.3f s, total = %.3f s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(boot), BoxesRunTime.boxToDouble(init), BoxesRunTime.boxToDouble(broadcast), BoxesRunTime.boxToDouble(input), BoxesRunTime.boxToDouble(compute), BoxesRunTime.boxToDouble(output), BoxesRunTime.boxToDouble(boot + init + broadcast + input + compute + output)})));
                  var10000 = this.read();
               } else if (length > 0) {
                  var10000 = this.readData().apply(BoxesRunTime.boxToInteger(length));
               } else {
                  if (length != 0) {
                     throw new MatchError(BoxesRunTime.boxToInteger(length));
                  }

                  this.eos_$eq(true);
                  var10000 = null;
               }
            } catch (Throwable var18) {
               PartialFunction catchExpr$1 = this.handleException();
               if (!catchExpr$1.isDefinedAt(var18)) {
                  throw var18;
               }

               var10000 = catchExpr$1.apply(var18);
            }

            return var10000;
         }

         // $FF: synthetic method
         public static final String $anonfun$readData$1(final Object $this, final int length) {
            return $this.readStringData(length);
         }

         // $FF: synthetic method
         public static final byte[] $anonfun$readData$2(final Object $this, final int length) {
            return $this.readByteArrayData(length);
         }

         // $FF: synthetic method
         public static final Tuple2 $anonfun$readData$3(final Object $this, final int length) {
            return $this.readShuffledData(length);
         }

         public {
            if (RRunner.this == null) {
               throw null;
            } else {
               this.$outer = RRunner.this;
               this.dataStream$1 = dataStream$1;
               int var5 = RRunner.this.org$apache$spark$api$r$RRunner$$numPartitions;
               Function1 var8;
               switch (var5) {
                  case -1:
                     label22: {
                        String var6 = RRunner.this.org$apache$spark$api$r$RRunner$$serializer;
                        String var10001 = SerializationFormats$.MODULE$.STRING();
                        if (var10001 == null) {
                           if (var6 == null) {
                              break label22;
                           }
                        } else if (var10001.equals(var6)) {
                           break label22;
                        }

                        var8 = (length) -> $anonfun$readData$2(this, BoxesRunTime.unboxToInt(length));
                        break;
                     }

                     var8 = (length) -> $anonfun$readData$1(this, BoxesRunTime.unboxToInt(length));
                     break;
                  default:
                     var8 = (length) -> $anonfun$readData$3(this, BoxesRunTime.unboxToInt(length));
               }

               this.readData = var8;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public BaseRRunner.WriterThread newWriterThread(final OutputStream output, final Iterator iter, final int partitionIndex) {
      return new BaseRRunner.WriterThread(output, iter, partitionIndex) {
         // $FF: synthetic field
         private final RRunner $outer;
         private final Iterator iter$1;

         public void writeIteratorToStream(final DataOutputStream dataOut) {
            this.iter$1.foreach((elem) -> {
               $anonfun$writeIteratorToStream$1(this, dataOut, elem);
               return BoxedUnit.UNIT;
            });
         }

         private final void writeElem$1(final Object elem, final DataOutputStream dataOut$1) {
            label40: {
               String var10000 = this.$outer.org$apache$spark$api$r$RRunner$$deserializer;
               String var3 = SerializationFormats$.MODULE$.BYTE();
               if (var10000 == null) {
                  if (var3 == null) {
                     break label40;
                  }
               } else if (var10000.equals(var3)) {
                  break label40;
               }

               label41: {
                  var10000 = this.$outer.org$apache$spark$api$r$RRunner$$deserializer;
                  String var5 = SerializationFormats$.MODULE$.ROW();
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label41;
                     }
                  } else if (var10000.equals(var5)) {
                     break label41;
                  }

                  label25: {
                     var10000 = this.$outer.org$apache$spark$api$r$RRunner$$deserializer;
                     String var6 = SerializationFormats$.MODULE$.STRING();
                     if (var10000 == null) {
                        if (var6 == null) {
                           break label25;
                        }
                     } else if (var10000.equals(var6)) {
                        break label25;
                     }

                     return;
                  }

                  this.printOut().println(elem);
                  return;
               }

               dataOut$1.write((byte[])elem);
               return;
            }

            byte[] elemArr = (byte[])elem;
            dataOut$1.writeInt(elemArr.length);
            dataOut$1.write(elemArr);
         }

         // $FF: synthetic method
         public static final void $anonfun$writeIteratorToStream$2(final Object $this, final DataOutputStream dataOut$1, final Object innerElem) {
            $this.writeElem$1(innerElem, dataOut$1);
         }

         // $FF: synthetic method
         public static final void $anonfun$writeIteratorToStream$1(final Object $this, final DataOutputStream dataOut$1, final Object elem) {
            boolean var4 = false;
            Tuple2 var5 = null;
            if (elem instanceof Tuple2) {
               var4 = true;
               var5 = (Tuple2)elem;
               Object key = var5._1();
               Object innerIter = var5._2();
               if (innerIter instanceof Iterator) {
                  Iterator var9 = (Iterator)innerIter;
                  var9.foreach((innerElem) -> {
                     $anonfun$writeIteratorToStream$2($this, dataOut$1, innerElem);
                     return BoxedUnit.UNIT;
                  });
                  dataOut$1.writeByte(114);
                  $this.writeElem$1(key, dataOut$1);
                  BoxedUnit var13 = BoxedUnit.UNIT;
                  return;
               }
            }

            if (var4) {
               Object key = var5._1();
               Object value = var5._2();
               $this.writeElem$1(key, dataOut$1);
               $this.writeElem$1(value, dataOut$1);
               BoxedUnit var12 = BoxedUnit.UNIT;
            } else {
               $this.writeElem$1(elem, dataOut$1);
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         }

         public {
            if (RRunner.this == null) {
               throw null;
            } else {
               this.$outer = RRunner.this;
               this.iter$1 = iter$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public RRunner(final byte[] func, final String deserializer, final String serializer, final byte[] packageNames, final Broadcast[] broadcastVars, final int numPartitions, final boolean isDataFrame, final String[] colNames, final int mode) {
      super(func, deserializer, serializer, packageNames, broadcastVars, numPartitions, isDataFrame, colNames, mode);
      this.org$apache$spark$api$r$RRunner$$deserializer = deserializer;
      this.org$apache$spark$api$r$RRunner$$serializer = serializer;
      this.org$apache$spark$api$r$RRunner$$numPartitions = numPartitions;
   }
}
