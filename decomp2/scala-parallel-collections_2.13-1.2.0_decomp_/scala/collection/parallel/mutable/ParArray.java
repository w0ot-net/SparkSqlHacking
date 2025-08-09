package scala.collection.parallel.mutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.MatchError;
import scala.None;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BufferedIterator;
import scala.collection.CustomParallelizable;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Parallelizable;
import scala.collection.SeqOps;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.GenericParCompanion;
import scala.collection.generic.GenericParTemplate;
import scala.collection.generic.GenericTraversableTemplate;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.AugmentedIterableIterator;
import scala.collection.parallel.AugmentedSeqIterator;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.CombinerFactory;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.ParSeqLike;
import scala.collection.parallel.PreciseSplitter;
import scala.collection.parallel.RemainsIterator;
import scala.collection.parallel.SeqSplitter;
import scala.collection.parallel.Task;
import scala.collection.parallel.TaskSupport;
import scala.math.Integral;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u001dugaBA\u0002\u0003\u000b\u0001\u0011q\u0003\u0005\u000b\u0003c\u0002!Q1A\u0005\u0002\u0005M\u0004BCA;\u0001\t\u0005\t\u0015!\u0003\u0002Z!Q\u0011q\u000f\u0001\u0003\u0002\u0003\u0006I!!\u001f\t\u0013\u0005}\u0004\u0001\"\u0001\u0002\u0006\u0005\u0005\u0005\"CAD\u0001\u0001\u0007I\u0011BAE\u0011%\t\t\n\u0001a\u0001\n\u0013\t\u0019\n\u0003\u0005\u0002 \u0002\u0001\u000b\u0015BAF\u0011\u001d\tI\u000b\u0001C!\u0003WCq!a \u0001\t\u0003\t\u0019\fC\u0004\u0002\u0000\u0001!\t!!/\t\u000f\u0005u\u0006\u0001\"\u0001\u0002@\"9\u0011Q\u0019\u0001\u0005\u0002\u0005\u001d\u0007bBAh\u0001\u0011\u0005\u0011\u0011\u001b\u0005\b\u0003'\u0004A\u0011IAi\u0011\u001d\t)\u000e\u0001C\u0001\u0003gB\u0011\"a6\u0001\t#\tI!!7\u0007\r\u0005}\u0007\u0001AAq\u0011)\t\u0019-\u0005BA\u0002\u0013\u0005\u0011\u0011\u001b\u0005\u000b\u0003S\f\"\u00111A\u0005\u0002\u0005-\bBCAx#\t\u0005\t\u0015)\u0003\u0002z!Q\u0011\u0011_\t\u0003\u0006\u0004%\t!!5\t\u0015\u0005M\u0018C!A!\u0002\u0013\tI\b\u0003\u0006\u00028F\u0011)\u0019!C\u0001\u0003\u0013C!\"!>\u0012\u0005\u0003\u0005\u000b\u0011BAF\u0011\u001d\ty(\u0005C\u0001\u0003oDq!a@\u0012\t\u0003\u0011\t\u0001C\u0004\u0003\nE!\tAa\u0003\t\u000f\t5\u0011\u0003\"\u0001\u0002R\"9!qB\t\u0005\u0002\u0005e\u0007b\u0002B\t#\u0011\u0005!1\u0003\u0005\b\u0005K\tB\u0011\tB\u0014\u0011\u001d\u0011I#\u0005C!\u0005WAqA!\u0010\u0012\t\u0003\u0012y\u0004C\u0004\u0003TE!IA!\u0016\t\u000f\t5\u0014\u0003\"\u0011\u0003p!9!qO\t\u0005\n\te\u0004b\u0002BB#\u0011\u0005#Q\u0011\u0005\b\u0005?\u000bB\u0011\u0002BQ\u0011\u001d\u0011\u0019,\u0005C!\u0005kCqAa2\u0012\t\u0003\u0012I\rC\u0004\u0003hF!\tE!;\t\u000f\tm\u0018\u0003\"\u0003\u0003~\"911C\t\u0005B\rU\u0001bBB\u0011#\u0011%11\u0005\u0005\b\u0007s\tB\u0011IB\u001e\u0011\u001d\u0019y$\u0005C\u0005\u0007\u0003Bqaa\u0014\u0012\t\u0003\u001a\t\u0006C\u0004\u0004VE!Iaa\u0016\t\u000f\r\u0005\u0014\u0003\"\u0011\u0004d!91QN\t\u0005\n\r=\u0004bBB=#\u0011\u000531\u0010\u0005\b\u0007\u0003\u000bB\u0011IBB\u0011\u001d\u0019)*\u0005C!\u0007/Cqa!(\u0012\t\u0013\u0019y\nC\u0004\u0004,F!\te!,\t\u000f\rE\u0016\u0003\"\u0003\u00044\"91QX\t\u0005B\r}\u0006bBBb#\u0011%1Q\u0019\u0005\b\u0007\u001f\fB\u0011IBi\u0011\u001d\u0019)/\u0005C!\u0007ODq\u0001b\u0001\u0012\t\u0013!)\u0001C\u0004\u0005$E!\t\u0005\"\n\t\u000f\u0011}\u0012\u0003\"\u0003\u0005B!9A1L\t\u0005B\u0011u\u0003b\u0002C:#\u0011\u0005CQ\u000f\u0005\b\t\u0013\u000bB\u0011\u0002CF\u0011\u001d!\u0019+\u0005C!\tKCq\u0001b.\u0012\t\u0013!I\fC\u0004\u0005RF!\t\u0005b5\t\u000f\u00115\u0018\u0003\"\u0003\u0005p\"9QqA\t\u0005B\u0015%\u0001bBC\u0014#\u0011%Q\u0011\u0006\u0005\b\u000b\u0007\nB\u0011IC#\u0011\u001d)9&\u0005C!\u000b3Bq!b\u001b\u0012\t\u0003*i\u0007C\u0004\u0006~E!I!b \t\u000f\u0015M\u0015\u0003\"\u0011\u0006\u0016\"9Q1W\t\u0005\u0012\u0015U\u0006BDCj#A\u0005\u0019\u0011!A\u0005\n\u0015UWQ]\u0004\n\u000bW\u0004\u0011\u0011!E\u0001\u000b[4\u0011\"a8\u0001\u0003\u0003E\t!b<\t\u000f\u0005}\u0014\u000b\"\u0001\u0006r\"IQ1_)\u0012\u0002\u0013\u0005QQ\u001f\u0005\n\r\u0017\t\u0016\u0013!C\u0001\u000bkD\u0011B\"\u0004R#\u0003%\tAb\u0004\t\u000f\u0019M\u0001\u0001\"\u0011\u0007\u0016!9a1\u0005\u0001\u0005B\u0019\u0015bA\u0002D\u001c\u0001\u00011I\u0004\u0003\u0006\u0007Ja\u0013\t\u0011)A\u0005\r\u0017B!B!(Y\u0005\u0003\u0005\u000b\u0011\u0002D#\u0011)\u0011\u0019\n\u0017B\u0001B\u0003%aQ\u000b\u0005\u000b\r/B&\u0011!Q\u0001\n\u0005-\u0005bBA@1\u0012\u0005a\u0011\f\u0005\n\rGB\u0006\u0019!C\u0001\rKB\u0011Bb\u001aY\u0001\u0004%\tA\"\u001b\t\u0011\u00195\u0004\f)Q\u0005\u0003+CqAb\u001cY\t\u00031\t\bC\u0004\u0007za#IAb\u001f\t\u000f\u0019}\u0004\f\"\u0003\u0007\u0002\"9!Q\u0005-\u0005\u0002\u0019=\u0005b\u0002DJ1\u0012\u0005!\u0011\u0001\u0004\u0007\r+\u0003\u0001Ab&\t\u0015\t\u0015cM!A!\u0002\u00131\u0019\u000b\u0003\u0006\u0007X\u0019\u0014\t\u0011)A\u0005\u0003\u0017C!B\"*g\u0005\u0003\u0005\u000b\u0011BA=\u0011)19K\u001aB\u0001B\u0003%\u0011\u0011\u0010\u0005\b\u0003\u007f2G\u0011\u0001DU\u0011%1\u0019G\u001aa\u0001\n\u00031)\u0007C\u0005\u0007h\u0019\u0004\r\u0011\"\u0001\u00074\"AaQ\u000e4!B\u0013\t)\nC\u0004\u0007p\u0019$\tAb.\t\u000f\t\u0015b\r\"\u0001\u0007<\"9a1\u00134\u0005\u0002\t\u0005\u0001b\u0002De\u0001\u0011%a1\u001a\u0005\b\r;\u0004A\u0011\u0002Dp\u000f!190!\u0002\t\u0002\u0019eh\u0001CA\u0002\u0003\u000bA\tAb?\t\u000f\u0005}T\u000f\"\u0001\b\b!9q\u0011B;\u0005\u0004\u001d-\u0001bBD\u0011k\u0012\u0005q1\u0005\u0005\b\u000f_)H\u0011AD\u0019\u0011\u001d9i$\u001eC\u0001\u000f\u007fAqa\"\u0010v\t\u00039i\u0005C\u0004\b^U$Iab\u0018\t\u000f\u001d5T\u000f\"\u0001\bp!9q\u0011S;\u0005\u0006\u001dM\u0005bBDak\u0012\u0005q1\u0019\u0005\n\u000f',\u0018\u0011!C\u0005\u000f+\u0014\u0001\u0002U1s\u0003J\u0014\u0018-\u001f\u0006\u0005\u0003\u000f\tI!A\u0004nkR\f'\r\\3\u000b\t\u0005-\u0011QB\u0001\ta\u0006\u0014\u0018\r\u001c7fY*!\u0011qBA\t\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0003\u0003'\tQa]2bY\u0006\u001c\u0001!\u0006\u0003\u0002\u001a\u0005=2c\u0003\u0001\u0002\u001c\u0005\r\u0012\u0011IA(\u0003G\u0002B!!\b\u0002 5\u0011\u0011\u0011C\u0005\u0005\u0003C\t\tB\u0001\u0004B]f\u0014VM\u001a\t\u0007\u0003K\t9#a\u000b\u000e\u0005\u0005\u0015\u0011\u0002BA\u0015\u0003\u000b\u0011a\u0001U1s'\u0016\f\b\u0003BA\u0017\u0003_a\u0001\u0001B\u0004\u00022\u0001\u0011\r!a\r\u0003\u0003Q\u000bB!!\u000e\u0002<A!\u0011QDA\u001c\u0013\u0011\tI$!\u0005\u0003\u000f9{G\u000f[5oOB!\u0011QDA\u001f\u0013\u0011\ty$!\u0005\u0003\u0007\u0005s\u0017\u0010\u0005\u0005\u0002D\u0005%\u00131FA'\u001b\t\t)E\u0003\u0003\u0002H\u00055\u0011aB4f]\u0016\u0014\u0018nY\u0005\u0005\u0003\u0017\n)E\u0001\nHK:,'/[2QCJ$V-\u001c9mCR,\u0007cAA\u0013\u0001Aa\u0011\u0011KA*\u0003W\ti%a\u0016\u0002Z5\u0011\u0011\u0011B\u0005\u0005\u0003+\nIA\u0001\u0006QCJ\u001cV-\u001d'jW\u0016\u0004R!!\n\u0001\u0003W\u0001b!a\u0017\u0002`\u0005-RBAA/\u0015\u0011\t9!!\u0004\n\t\u0005\u0005\u0014Q\f\u0002\t\u0003J\u0014\u0018-_*fcB!\u0011QMA6\u001d\u0011\ti\"a\u001a\n\t\u0005%\u0014\u0011C\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\ti'a\u001c\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\t\u0005%\u0014\u0011C\u0001\tCJ\u0014\u0018-_:fcV\u0011\u0011\u0011L\u0001\nCJ\u0014\u0018-_:fc\u0002\n!a\u001d>\u0011\t\u0005u\u00111P\u0005\u0005\u0003{\n\tBA\u0002J]R\fa\u0001P5oSRtDCBA,\u0003\u0007\u000b)\tC\u0004\u0002r\u0011\u0001\r!!\u0017\t\u000f\u0005]D\u00011\u0001\u0002z\u0005)\u0011M\u001d:bsV\u0011\u00111\u0012\t\u0007\u0003;\ti)a\u000f\n\t\u0005=\u0015\u0011\u0003\u0002\u0006\u0003J\u0014\u0018-_\u0001\nCJ\u0014\u0018-_0%KF$B!!&\u0002\u001cB!\u0011QDAL\u0013\u0011\tI*!\u0005\u0003\tUs\u0017\u000e\u001e\u0005\n\u0003;3\u0011\u0011!a\u0001\u0003\u0017\u000b1\u0001\u001f\u00132\u0003\u0019\t'O]1zA!\u001aq!a)\u0011\t\u0005u\u0011QU\u0005\u0005\u0003O\u000b\tBA\u0005ue\u0006t7/[3oi\u0006I1m\\7qC:LwN\\\u000b\u0003\u0003[\u0003b!a\u0011\u00020\u00065\u0013\u0002BAY\u0003\u000b\u00121cR3oKJL7\rU1s\u0007>l\u0007/\u00198j_:$B!a\u0016\u00026\"9\u0011qW\u0005A\u0002\u0005e\u0013aA1seR!\u0011qKA^\u0011\u001d\t9H\u0003a\u0001\u0003s\nQ!\u00199qYf$B!a\u000b\u0002B\"9\u00111Y\u0006A\u0002\u0005e\u0014!A5\u0002\rU\u0004H-\u0019;f)\u0019\t)*!3\u0002L\"9\u00111\u0019\u0007A\u0002\u0005e\u0004bBAg\u0019\u0001\u0007\u00111F\u0001\u0005K2,W.\u0001\u0004mK:<G\u000f[\u000b\u0003\u0003s\n\u0011b\u001b8po:\u001c\u0016N_3\u0002\u0007M,\u0017/\u0001\u0005ta2LG\u000f^3s+\t\tY\u000eE\u0002\u0002^Fi\u0011\u0001\u0001\u0002\u0011!\u0006\u0014\u0018I\u001d:bs&#XM]1u_J\u001cR!EA\u000e\u0003G\u0004b!!\u0015\u0002f\u0006-\u0012\u0002BAt\u0003\u0013\u00111bU3r'Bd\u0017\u000e\u001e;fe\u0006)\u0011n\u0018\u0013fcR!\u0011QSAw\u0011%\tijEA\u0001\u0002\u0004\tI(\u0001\u0002jA\u0005)QO\u001c;jY\u00061QO\u001c;jY\u0002\nA!\u0019:sAQA\u00111\\A}\u0003w\fi\u0010C\u0005\u0002Df\u0001\n\u00111\u0001\u0002z!I\u0011\u0011_\r\u0011\u0002\u0003\u0007\u0011\u0011\u0010\u0005\n\u0003oK\u0002\u0013!a\u0001\u0003\u0017\u000bq\u0001[1t\u001d\u0016DH/\u0006\u0002\u0003\u0004A!\u0011Q\u0004B\u0003\u0013\u0011\u00119!!\u0005\u0003\u000f\t{w\u000e\\3b]\u0006!a.\u001a=u)\t\tY#A\u0005sK6\f\u0017N\\5oO\u0006\u0019A-\u001e9\u0002\rA\u001c\b\u000f\\5u)\u0011\u0011)Ba\u0007\u0011\r\u0005\u0015$qCAn\u0013\u0011\u0011I\"a\u001c\u0003\u0007M+\u0017\u000fC\u0004\u0003\u001ey\u0001\rAa\b\u0002\u001fML'0Z:J]\u000e|W\u000e\u001d7fi\u0016\u0004b!!\b\u0003\"\u0005e\u0014\u0002\u0002B\u0012\u0003#\u0011!\u0002\u0010:fa\u0016\fG/\u001a3?\u0003\u0015\u0019\b\u000f\\5u+\t\u0011)\"\u0001\u0005u_N#(/\u001b8h)\t\u0011i\u0003\u0005\u0003\u00030\teRB\u0001B\u0019\u0015\u0011\u0011\u0019D!\u000e\u0002\t1\fgn\u001a\u0006\u0003\u0005o\tAA[1wC&!!1\bB\u0019\u0005\u0019\u0019FO]5oO\u00069am\u001c:fC\u000eDW\u0003\u0002B!\u0005\u001f\"B!!&\u0003D!9!QI\u0011A\u0002\t\u001d\u0013!\u00014\u0011\u0011\u0005u!\u0011JA\u0016\u0005\u001bJAAa\u0013\u0002\u0012\tIa)\u001e8di&|g.\r\t\u0005\u0003[\u0011y\u0005B\u0004\u0003R\u0005\u0012\r!a\r\u0003\u0003U\u000bQBZ8sK\u0006\u001c\u0007nX9vS\u000e\\W\u0003\u0002B,\u0005?\"\"\"!&\u0003Z\t\u0005$Q\rB5\u0011\u001d\u0011)E\ta\u0001\u00057\u0002\u0002\"!\b\u0003J\u0005-\"Q\f\t\u0005\u0003[\u0011y\u0006B\u0004\u0003R\t\u0012\r!a\r\t\u000f\t\r$\u00051\u0001\u0002\f\u0006\t\u0011\rC\u0004\u0003h\t\u0002\r!!\u001f\u0002\t9$\u0018\u000e\u001c\u0005\b\u0005W\u0012\u0003\u0019AA=\u0003\u00111'o\\7\u0002\u000b\r|WO\u001c;\u0015\t\u0005e$\u0011\u000f\u0005\b\u0005g\u001a\u0003\u0019\u0001B;\u0003\u0005\u0001\b\u0003CA\u000f\u0005\u0013\nYCa\u0001\u0002\u0017\r|WO\u001c;`cVL7m\u001b\u000b\u000b\u0003s\u0012YH! \u0003\u0000\t\u0005\u0005b\u0002B:I\u0001\u0007!Q\u000f\u0005\b\u0005G\"\u0003\u0019AAF\u0011\u001d\u00119\u0007\na\u0001\u0003sBqAa\u001b%\u0001\u0004\tI(\u0001\u0005g_2$G*\u001a4u+\u0011\u00119I!$\u0015\t\t%%1\u0014\u000b\u0005\u0005\u0017\u0013\t\n\u0005\u0003\u0002.\t5Ea\u0002BHK\t\u0007\u00111\u0007\u0002\u0002'\"9!1S\u0013A\u0002\tU\u0015AA8q!)\tiBa&\u0003\f\u0006-\"1R\u0005\u0005\u00053\u000b\tBA\u0005Gk:\u001cG/[8oe!9!QT\u0013A\u0002\t-\u0015!\u0001>\u0002\u001d\u0019|G\u000e\u001a'fMR|\u0016/^5dWV!!1\u0015BT))\u0011)K!+\u0003,\n5&\u0011\u0017\t\u0005\u0003[\u00119\u000bB\u0004\u0003\u0010\u001a\u0012\r!a\r\t\u000f\t\rd\u00051\u0001\u0002\f\"9!q\r\u0014A\u0002\u0005e\u0004b\u0002BJM\u0001\u0007!q\u0016\t\u000b\u0003;\u00119J!*\u0002,\t\u0015\u0006b\u0002BOM\u0001\u0007!QU\u0001\u0005M>dG-\u0006\u0003\u00038\nuF\u0003\u0002B]\u0005\u000b$BAa/\u0003BB!\u0011Q\u0006B_\t\u001d\u0011\tf\nb\u0001\u0005\u007f\u000bB!a\u000b\u0002<!9!1S\u0014A\u0002\t\r\u0007CCA\u000f\u0005/\u0013YLa/\u0003<\"9!QT\u0014A\u0002\tm\u0016!C1hOJ,w-\u0019;f+\u0011\u0011YM!5\u0015\t\t5'q\u001c\u000b\u0007\u0005\u001f\u0014\u0019N!7\u0011\t\u00055\"\u0011\u001b\u0003\b\u0005\u001fC#\u0019AA\u001a\u0011\u001d\u0011)\u000e\u000ba\u0001\u0005/\fQa]3r_B\u0004\"\"!\b\u0003\u0018\n=\u00171\u0006Bh\u0011\u001d\u0011Y\u000e\u000ba\u0001\u0005;\faaY8nE>\u0004\bCCA\u000f\u0005/\u0013yMa4\u0003P\"A!Q\u0014\u0015\u0005\u0002\u0004\u0011\t\u000f\u0005\u0004\u0002\u001e\t\r(qZ\u0005\u0005\u0005K\f\tB\u0001\u0005=Eft\u0017-\\3?\u0003\r\u0019X/\\\u000b\u0005\u0005W\u0014y\u000f\u0006\u0003\u0003n\nE\b\u0003BA\u0017\u0005_$qA!\u0015*\u0005\u0004\u0011y\fC\u0004\u0003t&\u0002\u001dA!>\u0002\u00079,X\u000e\u0005\u0004\u0002f\t](Q^\u0005\u0005\u0005s\fyGA\u0004Ok6,'/[2\u0002\u0013M,XnX9vS\u000e\\W\u0003\u0002B\u0000\u0007\u0007!Bb!\u0001\u0004\u0006\r%11BB\u0007\u0007\u001f\u0001B!!\f\u0004\u0004\u00119!\u0011\u000b\u0016C\u0002\t}\u0006b\u0002BzU\u0001\u00071q\u0001\t\u0007\u0003K\u00129p!\u0001\t\u000f\t\r$\u00061\u0001\u0002\f\"9!q\r\u0016A\u0002\u0005e\u0004b\u0002B6U\u0001\u0007\u0011\u0011\u0010\u0005\b\u0007#Q\u0003\u0019AB\u0001\u0003\u0011QXM]8\u0002\u000fA\u0014x\u000eZ;diV!1qCB\u000e)\u0011\u0019Ib!\b\u0011\t\u0005521\u0004\u0003\b\u0005#Z#\u0019\u0001B`\u0011\u001d\u0011\u0019p\u000ba\u0002\u0007?\u0001b!!\u001a\u0003x\u000ee\u0011!\u00049s_\u0012,8\r^0rk&\u001c7.\u0006\u0003\u0004&\r%B\u0003DB\u0014\u0007W\u0019yc!\r\u00044\rU\u0002\u0003BA\u0017\u0007S!qA!\u0015-\u0005\u0004\u0011y\fC\u0004\u0003t2\u0002\ra!\f\u0011\r\u0005\u0015$q_B\u0014\u0011\u001d\u0011\u0019\u0007\fa\u0001\u0003\u0017CqAa\u001a-\u0001\u0004\tI\bC\u0004\u0003l1\u0002\r!!\u001f\t\u000f\r]B\u00061\u0001\u0004(\u0005\u0019qN\\3\u0002\r\u0019|'/\u00197m)\u0011\u0011\u0019a!\u0010\t\u000f\tMT\u00061\u0001\u0003v\u0005aam\u001c:bY2|\u0016/^5dWRQ!1AB\"\u0007\u000b\u001a9ea\u0013\t\u000f\tMd\u00061\u0001\u0003v!9!1\r\u0018A\u0002\u0005-\u0005bBB%]\u0001\u0007\u0011\u0011P\u0001\n]\u0016DH/\u001e8uS2Dqa!\u0014/\u0001\u0004\tI(A\u0003ti\u0006\u0014H/\u0001\u0004fq&\u001cHo\u001d\u000b\u0005\u0005\u0007\u0019\u0019\u0006C\u0004\u0003t=\u0002\rA!\u001e\u0002\u0019\u0015D\u0018n\u001d;t?F,\u0018nY6\u0015\u0015\t\r1\u0011LB.\u0007;\u001ay\u0006C\u0004\u0003tA\u0002\rA!\u001e\t\u000f\t\r\u0004\u00071\u0001\u0002\f\"91\u0011\n\u0019A\u0002\u0005e\u0004bBB'a\u0001\u0007\u0011\u0011P\u0001\u0005M&tG\r\u0006\u0003\u0004f\r-\u0004CBA\u000f\u0007O\nY#\u0003\u0003\u0004j\u0005E!AB(qi&|g\u000eC\u0004\u0003tE\u0002\rA!\u001e\u0002\u0015\u0019Lg\u000eZ0rk&\u001c7\u000e\u0006\u0006\u0004f\rE41OB;\u0007oBqAa\u001d3\u0001\u0004\u0011)\bC\u0004\u0003dI\u0002\r!a#\t\u000f\r%#\u00071\u0001\u0002z!91Q\n\u001aA\u0002\u0005e\u0014\u0001\u00023s_B$B!a7\u0004~!91qP\u001aA\u0002\u0005e\u0014!\u00018\u0002\u0017\r|\u0007/\u001f+p\u0003J\u0014\u0018-_\u000b\u0005\u0007\u000b\u001bi\t\u0006\u0005\u0002z\r\u001d5qRBI\u0011\u001d\t9\t\u000ea\u0001\u0007\u0013\u0003b!!\b\u0002\u000e\u000e-\u0005\u0003BA\u0017\u0007\u001b#qA!\u00155\u0005\u0004\u0011y\fC\u0004\u0003lQ\u0002\r!!\u001f\t\u000f\rME\u00071\u0001\u0002z\u0005\u0019A.\u001a8\u0002\u0019A\u0014XMZ5y\u0019\u0016tw\r\u001e5\u0015\t\u0005e4\u0011\u0014\u0005\b\u00077+\u0004\u0019\u0001B;\u0003\u0011\u0001(/\u001a3\u0002%A\u0014XMZ5y\u0019\u0016tw\r\u001e5`cVL7m\u001b\u000b\u000b\u0003s\u001a\tka)\u0004&\u000e\u001d\u0006bBBNm\u0001\u0007!Q\u000f\u0005\b\u0005G2\u0004\u0019AAF\u0011\u001d\u00119G\u000ea\u0001\u0003sBqa!+7\u0001\u0004\tI(\u0001\u0005ti\u0006\u0014H\u000f]8t\u0003)Ig\u000eZ3y/\",'/\u001a\u000b\u0005\u0003s\u001ay\u000bC\u0004\u0004\u001c^\u0002\rA!\u001e\u0002!%tG-\u001a=XQ\u0016\u0014XmX9vS\u000e\\GCCA=\u0007k\u001b9l!/\u0004<\"911\u0014\u001dA\u0002\tU\u0004b\u0002B2q\u0001\u0007\u00111\u0012\u0005\b\u0005OB\u0004\u0019AA=\u0011\u001d\u0011Y\u0007\u000fa\u0001\u0003s\na\u0002\\1ti&sG-\u001a=XQ\u0016\u0014X\r\u0006\u0003\u0002z\r\u0005\u0007bBBNs\u0001\u0007!QO\u0001\u0015Y\u0006\u001cH/\u00138eKb<\u0006.\u001a:f?F,\u0018nY6\u0015\u0015\u0005e4qYBe\u0007\u0017\u001ci\rC\u0004\u0004\u001cj\u0002\rA!\u001e\t\u000f\t\r$\b1\u0001\u0002\f\"9!1\u000e\u001eA\u0002\u0005e\u0004b\u0002B4u\u0001\u0007\u0011\u0011P\u0001\rg\u0006lW-\u00127f[\u0016tGo]\u000b\u0005\u0007'\u001c\t\u000f\u0006\u0003\u0003\u0004\rU\u0007bBBlw\u0001\u00071\u0011\\\u0001\u0005i\"\fG\u000f\u0005\u0004\u0002f\rm7q\\\u0005\u0005\u0007;\fyG\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cW\r\u0005\u0003\u0002.\r\u0005HaBBrw\t\u0007!q\u0018\u0002\u0002\u0005\u0006aQ.\u001993G>l'-\u001b8feV11\u0011^Bz\u0007o$baa;\u0004|\u000e}\b\u0003CA)\u0007[\u001c\tp!>\n\t\r=\u0018\u0011\u0002\u0002\t\u0007>l'-\u001b8feB!\u0011QFBz\t\u001d\u0011y\t\u0010b\u0001\u0003g\u0001B!!\f\u0004x\u001291\u0011 \u001fC\u0002\u0005M\"\u0001\u0002+iCRDqA!\u0012=\u0001\u0004\u0019i\u0010\u0005\u0005\u0002\u001e\t%\u00131FBy\u0011\u001d!\t\u0001\u0010a\u0001\u0007W\f!a\u00192\u0002%5\f\u0007OM2p[\nLg.\u001a:`cVL7m[\u000b\u0007\t\u000f!y\u0001\"\b\u0015\u0019\u0005UE\u0011\u0002C\t\t'!y\u0002\"\t\t\u000f\t\u0015S\b1\u0001\u0005\fAA\u0011Q\u0004B%\u0003W!i\u0001\u0005\u0003\u0002.\u0011=Aa\u0002BH{\t\u0007\u00111\u0007\u0005\b\u0005Gj\u0004\u0019AAF\u0011\u001d!\t!\u0010a\u0001\t+\u0001\u0002\"a\u0017\u0005\u0018\u00115A1D\u0005\u0005\t3\tiFA\u0004Ck&dG-\u001a:\u0011\t\u00055BQ\u0004\u0003\b\u0007sl$\u0019AA\u001a\u0011\u001d\u00119'\u0010a\u0001\u0003sBqAa\u001b>\u0001\u0004\tI(\u0001\td_2dWm\u0019;3G>l'-\u001b8feV1Aq\u0005C\u0017\tc!b\u0001\"\u000b\u00054\u0011u\u0002\u0003CA)\u0007[$Y\u0003b\f\u0011\t\u00055BQ\u0006\u0003\b\u0005\u001fs$\u0019AA\u001a!\u0011\ti\u0003\"\r\u0005\u000f\rehH1\u0001\u00024!9AQ\u0007 A\u0002\u0011]\u0012A\u00019g!!\ti\u0002\"\u000f\u0002,\u0011-\u0012\u0002\u0002C\u001e\u0003#\u0011q\u0002U1si&\fGNR;oGRLwN\u001c\u0005\b\t\u0003q\u0004\u0019\u0001C\u0015\u0003Y\u0019w\u000e\u001c7fGR\u00144m\\7cS:,'oX9vS\u000e\\WC\u0002C\"\t\u0017\")\u0006\u0006\u0007\u0002\u0016\u0012\u0015CQ\nC(\t/\"I\u0006C\u0004\u00056}\u0002\r\u0001b\u0012\u0011\u0011\u0005uA\u0011HA\u0016\t\u0013\u0002B!!\f\u0005L\u00119!qR C\u0002\u0005M\u0002b\u0002B2\u007f\u0001\u0007\u00111\u0012\u0005\b\t\u0003y\u0004\u0019\u0001C)!!\tY\u0006b\u0006\u0005J\u0011M\u0003\u0003BA\u0017\t+\"qa!?@\u0005\u0004\t\u0019\u0004C\u0004\u0003h}\u0002\r!!\u001f\t\u000f\t-t\b1\u0001\u0002z\u0005\u0001b\r\\1u[\u0006\u0004(gY8nE&tWM]\u000b\u0007\t?\")\u0007\"\u001b\u0015\r\u0011\u0005D1\u000eC9!!\t\tf!<\u0005d\u0011\u001d\u0004\u0003BA\u0017\tK\"qAa$A\u0005\u0004\t\u0019\u0004\u0005\u0003\u0002.\u0011%DaBB}\u0001\n\u0007\u00111\u0007\u0005\b\u0005\u000b\u0002\u0005\u0019\u0001C7!!\tiB!\u0013\u0002,\u0011=\u0004CBA3\u00077$\u0019\u0007C\u0004\u0005\u0002\u0001\u0003\r\u0001\"\u0019\u0002\u001f\u0019LG\u000e^3se\r|WNY5oKJ,b\u0001b\u001e\u0005~\u0011\u0005EC\u0002C=\t\u000b#9\t\u0005\u0005\u0002R\r5H1\u0010C@!\u0011\ti\u0003\" \u0005\u000f\tE\u0013I1\u0001\u0003@B!\u0011Q\u0006CA\t\u001d!\u0019)\u0011b\u0001\u0003g\u0011A\u0001\u00165jg\"911T!A\u0002\tU\u0004b\u0002C\u0001\u0003\u0002\u0007A\u0011P\u0001\u0016M&dG/\u001a:3G>l'-\u001b8fe~\u000bX/[2l+\u0019!i\tb&\u0005\u001cRa\u0011Q\u0013CH\t##i\nb(\u0005\"\"911\u0014\"A\u0002\tU\u0004b\u0002C\u0001\u0005\u0002\u0007A1\u0013\t\t\u00037\"9\u0002\"&\u0005\u001aB!\u0011Q\u0006CL\t\u001d\u0011\tF\u0011b\u0001\u0005\u007f\u0003B!!\f\u0005\u001c\u00129A1\u0011\"C\u0002\u0005M\u0002b\u0002B2\u0005\u0002\u0007\u00111\u0012\u0005\b\u0005O\u0012\u0005\u0019AA=\u0011\u001d\u0011YG\u0011a\u0001\u0003s\n!CZ5mi\u0016\u0014hj\u001c;3G>l'-\u001b8feV1Aq\u0015CW\tc#b\u0001\"+\u00054\u0012U\u0006\u0003CA)\u0007[$Y\u000bb,\u0011\t\u00055BQ\u0016\u0003\b\u0005#\u001a%\u0019\u0001B`!\u0011\ti\u0003\"-\u0005\u000f\u0011\r5I1\u0001\u00024!911T\"A\u0002\tU\u0004b\u0002C\u0001\u0007\u0002\u0007A\u0011V\u0001\u0019M&dG/\u001a:O_R\u00144m\\7cS:,'oX9vS\u000e\\WC\u0002C^\t\u000b$I\r\u0006\u0007\u0002\u0016\u0012uFq\u0018Cf\t\u001b$y\rC\u0004\u0004\u001c\u0012\u0003\rA!\u001e\t\u000f\u0011\u0005A\t1\u0001\u0005BBA\u00111\fC\f\t\u0007$9\r\u0005\u0003\u0002.\u0011\u0015Ga\u0002B)\t\n\u0007!q\u0018\t\u0005\u0003[!I\rB\u0004\u0005\u0004\u0012\u0013\r!a\r\t\u000f\t\rD\t1\u0001\u0002\f\"9!q\r#A\u0002\u0005e\u0004b\u0002B6\t\u0002\u0007\u0011\u0011P\u0001\rG>\u0004\u0018P\r2vS2$WM]\u000b\t\t+$\u0019\u000fb:\u0005ZR!Aq\u001bCv!\u0011\ti\u0003\"7\u0005\u000f\u0011mWI1\u0001\u0005^\n\u0019!\t\u001c3\u0012\t\u0005UBq\u001c\t\t\u00037\"9\u0002\"9\u0005fB!\u0011Q\u0006Cr\t\u001d\u0011\t&\u0012b\u0001\u0005\u007f\u0003B!!\f\u0005h\u00129A\u0011^#C\u0002\u0005M\"\u0001B\"pY2Dq\u0001\"\u0001F\u0001\u0004!9.\u0001\nd_BL(GY;jY\u0012,'oX9vS\u000e\\WC\u0002Cy\tw$y\u0010\u0006\u0006\u0002\u0016\u0012MX\u0011AC\u0002\u000b\u000bAq\u0001\">G\u0001\u0004!90A\u0001c!!\tY\u0006b\u0006\u0005z\u0012u\b\u0003BA\u0017\tw$qA!\u0015G\u0005\u0004\u0011y\f\u0005\u0003\u0002.\u0011}Ha\u0002Cu\r\n\u0007\u00111\u0007\u0005\b\u0005G2\u0005\u0019AAF\u0011\u001d\u00119G\u0012a\u0001\u0003sBqAa\u001bG\u0001\u0004\tI(A\nqCJ$\u0018\u000e^5p]J\u001aw.\u001c2j]\u0016\u00148/\u0006\u0004\u0006\f\u0015]Q1\u0004\u000b\t\u000b\u001b)i\"b\b\u0006$AA\u0011QDC\b\u000b')\u0019\"\u0003\u0003\u0006\u0012\u0005E!A\u0002+va2,'\u0007\u0005\u0005\u0002R\r5XQCC\r!\u0011\ti#b\u0006\u0005\u000f\tEsI1\u0001\u0003@B!\u0011QFC\u000e\t\u001d!\u0019i\u0012b\u0001\u0003gAqaa'H\u0001\u0004\u0011)\bC\u0004\u0006\"\u001d\u0003\r!b\u0005\u0002\u000b\t$(/^3\t\u000f\u0015\u0015r\t1\u0001\u0006\u0014\u00051!MZ1mg\u0016\f\u0011\u0004]1si&$\u0018n\u001c83G>l'-\u001b8feN|\u0016/^5dWV1Q1FC\u001b\u000bs!b\"!&\u0006.\u0015=R1HC\u001f\u000b\u007f)\t\u0005C\u0004\u0003t!\u0003\rA!\u001e\t\u000f\u0015\u0005\u0002\n1\u0001\u00062AA\u00111\fC\f\u000bg)9\u0004\u0005\u0003\u0002.\u0015UBa\u0002B)\u0011\n\u0007!q\u0018\t\u0005\u0003[)I\u0004B\u0004\u0005\u0004\"\u0013\r!a\r\t\u000f\u0015\u0015\u0002\n1\u0001\u00062!9!1\r%A\u0002\u0005-\u0005b\u0002B4\u0011\u0002\u0007\u0011\u0011\u0010\u0005\b\u0005WB\u0005\u0019AA=\u00035!\u0018m[33G>l'-\u001b8feV1QqIC'\u000b#\"b!\"\u0013\u0006T\u0015U\u0003\u0003CA)\u0007[,Y%b\u0014\u0011\t\u00055RQ\n\u0003\b\u0005#J%\u0019\u0001B`!\u0011\ti#\"\u0015\u0005\u000f\u0011\r\u0015J1\u0001\u00024!91qP%A\u0002\u0005e\u0004b\u0002C\u0001\u0013\u0002\u0007Q\u0011J\u0001\u000eIJ|\u0007OM2p[\nLg.\u001a:\u0016\r\u0015mS\u0011MC3)\u0019)i&b\u001a\u0006jAA\u0011\u0011KBw\u000b?*\u0019\u0007\u0005\u0003\u0002.\u0015\u0005Da\u0002B)\u0015\n\u0007!q\u0018\t\u0005\u0003[))\u0007B\u0004\u0005\u0004*\u0013\r!a\r\t\u000f\r}$\n1\u0001\u0002z!9A\u0011\u0001&A\u0002\u0015u\u0013\u0001\u0005:fm\u0016\u00148/\u001a\u001ad_6\u0014\u0017N\\3s+\u0019)y'\"\u001e\u0006zQ!Q\u0011OC>!!\t\tf!<\u0006t\u0015]\u0004\u0003BA\u0017\u000bk\"qA!\u0015L\u0005\u0004\u0011y\f\u0005\u0003\u0002.\u0015eDa\u0002CB\u0017\n\u0007\u00111\u0007\u0005\b\t\u0003Y\u0005\u0019AC9\u0003Y\u0011XM^3sg\u0016\u00144m\\7cS:,'oX9vS\u000e\\G\u0003DAK\u000b\u0003+))b\"\u0006\f\u0016=\u0005bBCB\u0019\u0002\u0007\u00111R\u0001\u0005i\u0006\u0014x\rC\u0004\u0003d1\u0003\r!a#\t\u000f\u0015%E\n1\u0001\u0002z\u0005AA/\u0019:hMJ|W\u000eC\u0004\u0006\u000e2\u0003\r!!\u001f\u0002\u000fM\u00148M\u001a:p[\"9Q\u0011\u0013'A\u0002\u0005e\u0014\u0001C:sGVtG/\u001b7\u0002\u0017M\u001c\u0017M\u001c+p\u0003J\u0014\u0018-_\u000b\u0007\u000b/+i*b+\u0015\u0015\u0005UU\u0011TCP\u000bG+\t\fC\u0004\u0003\u001e6\u0003\r!b'\u0011\t\u00055RQ\u0014\u0003\b\u0005#j%\u0019\u0001B`\u0011\u001d\u0011\u0019*\u0014a\u0001\u000bC\u0003\"\"!\b\u0003\u0018\u0016mU1TCN\u0011\u001d))+\u0014a\u0001\u000bO\u000bq\u0001Z3ti\u0006\u0014(\u000f\u0005\u0004\u0002\u001e\u00055U\u0011\u0016\t\u0005\u0003[)Y\u000bB\u0004\u0006.6\u0013\r!b,\u0003\u0003\u0005\u000bB!b'\u0002<!9!1N'A\u0002\u0005e\u0014!E:dC:$v.\u0011:sCf|\u0016/^5dWV!QqWCc)A\t)*\"/\u0006>\u0016}VqYCe\u000b\u0017,y\rC\u0004\u0006<:\u0003\r!a#\u0002\rM\u00148-\u0019:s\u0011\u001d))K\u0014a\u0001\u0003\u0017CqAa%O\u0001\u0004)\t\r\u0005\u0006\u0002\u001e\t]U1YCb\u000b\u0007\u0004B!!\f\u0006F\u00129!\u0011\u000b(C\u0002\u0005M\u0002b\u0002BO\u001d\u0002\u0007Q1\u0019\u0005\b\u000b\u001bs\u0005\u0019AA=\u0011\u001d)iM\u0014a\u0001\u0003s\nqa\u001d:d]RLG\u000eC\u0004\u0006R:\u0003\r!!\u001f\u0002\u0011\u0011,7\u000f\u001e4s_6\fac];qKJ$#/\u001a<feN,'gY8nE&tWM]\u000b\u0007\u000b/,i.\"9\u0015\t\u0015eW1\u001d\t\t\u0003#\u001ai/b7\u0006`B!\u0011QFCo\t\u001d\u0011\tf\u0014b\u0001\u0005\u007f\u0003B!!\f\u0006b\u00129A1Q(C\u0002\u0005M\u0002b\u0002C\u0001\u001f\u0002\u0007Q\u0011\\\u0005\u0005\u000bW*9/\u0003\u0003\u0006j\u0006%!\u0001F!vO6,g\u000e^3e'\u0016\f\u0018\n^3sCR|'/\u0001\tQCJ\f%O]1z\u0013R,'/\u0019;peB\u0019\u0011Q\\)\u0014\u0007E\u000bY\u0002\u0006\u0002\u0006n\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE*\"!b>+\t\u0005eT\u0011`\u0016\u0003\u000bw\u0004B!\"@\u0007\b5\u0011Qq \u0006\u0005\r\u00031\u0019!A\u0005v]\u000eDWmY6fI*!aQAA\t\u0003)\tgN\\8uCRLwN\\\u0005\u0005\r\u0013)yPA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u0012\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$3'\u0006\u0002\u0007\u0012)\"\u00111RC}\u0003\ri\u0017\r]\u000b\u0005\r/1i\u0002\u0006\u0003\u0007\u001a\u0019}\u0001#BA\u0013\u0001\u0019m\u0001\u0003BA\u0017\r;!qAa$W\u0005\u0004\t\u0019\u0004C\u0004\u0003FY\u0003\rA\"\t\u0011\u0011\u0005u!\u0011JA\u0016\r7\tAa]2b]V!aq\u0005D\u0018)\u00111IC\"\u000e\u0015\t\u0019-b\u0011\u0007\t\u0006\u0003K\u0001aQ\u0006\t\u0005\u0003[1y\u0003B\u0004\u0003R]\u0013\rAa0\t\u000f\tMu\u000b1\u0001\u00074AQ\u0011Q\u0004BL\r[1iC\"\f\t\u000f\tuu\u000b1\u0001\u0007.\tY1kY1o)>\f%O]1z+\u00111YDb\u0012\u0014\u000ba\u000bYB\"\u0010\u0011\u0011\u0005EcqHAK\r\u0007JAA\"\u0011\u0002\n\t!A+Y:l!\u0015\ti\u000e\u0017D#!\u0011\tiCb\u0012\u0005\u000f\tE\u0003L1\u0001\u0003@\u0006!AO]3f!\u0019\tiN\"\u0014\u0007F%!aq\nD)\u0005!\u00196-\u00198Ue\u0016,\u0017\u0002\u0002D*\u0003\u0013\u0011q\u0002U1s\u0013R,'/\u00192mK2K7.\u001a\t\u000b\u0003;\u00119J\"\u0012\u0007F\u0019\u0015\u0013!\u0003;be\u001e,G/\u0019:s))1\u0019Eb\u0017\u0007^\u0019}c\u0011\r\u0005\b\r\u0013j\u0006\u0019\u0001D&\u0011\u001d\u0011i*\u0018a\u0001\r\u000bBqAa%^\u0001\u00041)\u0006C\u0004\u0007Xu\u0003\r!a#\u0002\rI,7/\u001e7u+\t\t)*\u0001\u0006sKN,H\u000e^0%KF$B!!&\u0007l!I\u0011QT0\u0002\u0002\u0003\u0007\u0011QS\u0001\be\u0016\u001cX\u000f\u001c;!\u0003\u0011aW-\u00194\u0015\t\u0005Ue1\u000f\u0005\b\rk\n\u0007\u0019\u0001D<\u0003\u0011\u0001(/\u001a<\u0011\r\u0005u1qMAK\u0003\u001dIG/\u001a:bi\u0016$B!!&\u0007~!9a\u0011\n2A\u0002\u0019-\u0013\u0001C:dC:dU-\u00194\u0015\u0019\u0005Ue1\u0011DC\r\u000f3IIb#\t\u000f\u0015m6\r1\u0001\u0002\f\"9aqK2A\u0002\u0005-\u0005b\u0002B6G\u0002\u0007\u0011\u0011\u0010\u0005\b\u0007'\u001b\u0007\u0019AA=\u0011\u001d1ii\u0019a\u0001\r\u000b\n\u0001b\u001d;beR4\u0018\r\\\u000b\u0003\r#\u0003b!!\u001a\u0003\u0018\u0019u\u0012AE:i_VdGm\u00159mSR4UO\u001d;iKJ\u00141\u0002U1s\u0003J\u0014\u0018-_'baV!a\u0011\u0014DQ'\u00151\u00171\u0004DN!!\t\tFb\u0010\u0002\u0016\u001au\u0005#BAoM\u001a}\u0005\u0003BA\u0017\rC#qAa$g\u0005\u0004\t\u0019\u0004\u0005\u0005\u0002\u001e\t%\u00131\u0006DP\u0003\u0019ygMZ:fi\u00069\u0001n\\<nC:LHC\u0003DO\rW3iKb,\u00072\"9!QI6A\u0002\u0019\r\u0006b\u0002D,W\u0002\u0007\u00111\u0012\u0005\b\rK[\u0007\u0019AA=\u0011\u001d19k\u001ba\u0001\u0003s\"B!!&\u00076\"I\u0011QT7\u0002\u0002\u0003\u0007\u0011Q\u0013\u000b\u0005\u0003+3I\fC\u0004\u0007v=\u0004\rAb\u001e\u0016\u0005\u0019u\u0006C\u0002D`\r\u000b4i*\u0004\u0002\u0007B*!a1YA\u0007\u0003%IW.\\;uC\ndW-\u0003\u0003\u0007H\u001a\u0005'\u0001\u0002'jgR\f1b\u001e:ji\u0016|%M[3diR!\u0011Q\u0013Dg\u0011\u001d1yM\u001da\u0001\r#\f1a\\;u!\u00111\u0019N\"7\u000e\u0005\u0019U'\u0002\u0002Dl\u0005k\t!![8\n\t\u0019mgQ\u001b\u0002\u0013\u001f\nTWm\u0019;PkR\u0004X\u000f^*ue\u0016\fW.\u0001\u0006sK\u0006$wJ\u00196fGR$B!!&\u0007b\"9a1]:A\u0002\u0019\u0015\u0018AA5o!\u00111\u0019Nb:\n\t\u0019%hQ\u001b\u0002\u0012\u001f\nTWm\u0019;J]B,Ho\u0015;sK\u0006l\u0007f\u0002\u0001\u0007n\u001aMhQ\u001f\t\u0005\u0003;1y/\u0003\u0003\u0007r\u0006E!\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\t\u0011\u0001\u0003)be\u0006\u0013(/Y=\u0011\u0007\u0005\u0015RoE\u0003v\r{<\u0019\u0001\u0005\u0004\u0002D\u0019}\u0018QJ\u0005\u0005\u000f\u0003\t)E\u0001\u0006QCJ4\u0015m\u0019;pef\u0004BAb5\b\u0006%!\u0011Q\u000eDk)\t1I0\u0001\u0007dC:\u0014U/\u001b7e\rJ|W.\u0006\u0004\b\u000e\u001deqQD\u000b\u0003\u000f\u001f\u0001\"\"a\u0011\b\u0012\u001dUq1DD\u0010\u0013\u00119\u0019\"!\u0012\u0003\u001d\r\u000bgnQ8nE&tWM\u0012:p[B)\u0011Q\u0005\u0001\b\u0018A!\u0011QFD\r\t\u001d\u0011yi\u001eb\u0001\u0003g\u0001B!!\f\b\u001e\u00119\u0011\u0011G<C\u0002\u0005M\u0002#BA\u0013\u0001\u001dm\u0011A\u00038fo\n+\u0018\u000e\u001c3feV!qQED\u0016+\t99\u0003\u0005\u0005\u0002R\r5x\u0011FD\u0017!\u0011\ticb\u000b\u0005\u000f\u0005E\u0002P1\u0001\u00024A)\u0011Q\u0005\u0001\b*\u0005Ya.Z<D_6\u0014\u0017N\\3s+\u00119\u0019d\"\u000f\u0016\u0005\u001dU\u0002\u0003CA)\u0007[<9db\u000f\u0011\t\u00055r\u0011\b\u0003\b\u0003cI(\u0019AA\u001a!\u0015\t)\u0003AD\u001c\u0003\u001dA\u0017M\u001c3pM\u001a,Ba\"\u0011\bHQ!q1ID%!\u0015\t)\u0003AD#!\u0011\ticb\u0012\u0005\u000f\u0005E\"P1\u0001\u00024!9\u0011q\u0017>A\u0002\u001d-\u0003CBA\u000f\u0003\u001b;)%\u0006\u0003\bP\u001dUCCBD)\u000f/:Y\u0006E\u0003\u0002&\u00019\u0019\u0006\u0005\u0003\u0002.\u001dUCaBA\u0019w\n\u0007\u00111\u0007\u0005\b\u0003o[\b\u0019AD-!\u0019\ti\"!$\bT!9\u0011qO>A\u0002\u0005e\u0014!D<sCB|%OU3ck&dG-\u0006\u0003\bb\u001d\u001dDCBD2\u000fS:Y\u0007E\u0003\u0002&\u00019)\u0007\u0005\u0003\u0002.\u001d\u001dDaBA\u0019y\n\u0007\u00111\u0007\u0005\b\u0003oc\b\u0019AA\u000e\u0011\u001d\t9\b a\u0001\u0003s\nab\u0019:fCR,gI]8n\u0007>\u0004\u00180\u0006\u0003\br\u001deD\u0003BD:\u000f\u001b#Ba\"\u001e\b~A)\u0011Q\u0005\u0001\bxA!\u0011QFD=\t\u001d\t\t$ b\u0001\u000fw\nB!!\u000e\u0002\u001c!IqqP?\u0002\u0002\u0003\u000fq\u0011Q\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004CBDB\u000f\u0013;9(\u0004\u0002\b\u0006*!qqQA\t\u0003\u001d\u0011XM\u001a7fGRLAab#\b\u0006\nA1\t\\1tgR\u000bw\rC\u0004\u00028v\u0004\rab$\u0011\r\u0005u\u0011QRD<\u0003A1'o\\7Ue\u00064XM]:bE2,7/\u0006\u0003\b\u0016\u001emE\u0003BDL\u000f;\u0003R!!\n\u0001\u000f3\u0003B!!\f\b\u001c\u00129\u0011\u0011\u0007@C\u0002\u0005M\u0002bBDP}\u0002\u0007q\u0011U\u0001\u0004qN\u001c\bCBA\u000f\u0005C9\u0019\u000b\u0005\u0004\u0002f\rmw\u0011\u0014\u0015\f}\u001e\u001dvQVDX\u000fg;)\f\u0005\u0003\u0002\u001e\u001d%\u0016\u0002BDV\u0003#\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f#a\"-\u0002e\u0019\u0014x.\u001c+sCZ,'o]1cY\u0016\u001c\b\u0005[1tA\t,WM\u001c\u0011sK:\fW.\u001a3!i>\u0004cM]8n\u0013R,'/\u00192mKN\fQa]5oG\u0016\f#ab.\u0002\u000bAr\u0013GL\u001a)\u0007y<Y\f\u0005\u0003\u0002\u001e\u001du\u0016\u0002BD`\u0003#\u0011a!\u001b8mS:,\u0017!\u00044s_6LE/\u001a:bE2,7/\u0006\u0003\bF\u001e-G\u0003BDd\u000f\u001b\u0004R!!\n\u0001\u000f\u0013\u0004B!!\f\bL\u00129\u0011\u0011G@C\u0002\u0005M\u0002bBDP\u007f\u0002\u0007qq\u001a\t\u0007\u0003;\u0011\tc\"5\u0011\r\u0005\u001541\\De\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t99\u000e\u0005\u0003\u00030\u001de\u0017\u0002BDn\u0005c\u0011aa\u00142kK\u000e$\b"
)
public class ParArray implements ParSeq, Serializable {
   private static final long serialVersionUID = 1L;
   private volatile ParArrayIterator$ ParArrayIterator$module;
   private final ArraySeq arrayseq;
   private final int sz;
   private transient Object[] scala$collection$parallel$mutable$ParArray$$array;
   private transient volatile TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
   private volatile ParIterableLike.ScanNode$ ScanNode$module;
   private volatile ParIterableLike.ScanLeaf$ ScanLeaf$module;

   public static ParArray fromIterables(final Seq xss) {
      return ParArray$.MODULE$.fromIterables(xss);
   }

   /** @deprecated */
   public static ParArray fromTraversables(final Seq xss) {
      return ParArray$.MODULE$.fromTraversables(xss);
   }

   public static ParArray createFromCopy(final Object[] arr, final ClassTag evidence$1) {
      return ParArray$.MODULE$.createFromCopy(arr, evidence$1);
   }

   public static ParArray handoff(final Object arr, final int sz) {
      return ParArray$.MODULE$.handoff(arr, sz);
   }

   public static ParArray handoff(final Object arr) {
      return ParArray$.MODULE$.handoff(arr);
   }

   public static CanCombineFrom canBuildFrom() {
      return ParArray$.MODULE$.canBuildFrom();
   }

   public static scala.collection.parallel.ParIterable iterate(final Object start, final int len, final Function1 f) {
      return ParArray$.MODULE$.iterate(start, len, f);
   }

   public static scala.collection.parallel.ParIterable range(final Object start, final Object end, final Object step, final Integral evidence$2) {
      return ParArray$.MODULE$.range(start, end, step, evidence$2);
   }

   public static scala.collection.parallel.ParIterable range(final Object start, final Object end, final Integral evidence$1) {
      return ParArray$.MODULE$.range(start, end, evidence$1);
   }

   public static scala.collection.parallel.ParIterable tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      return ParArray$.MODULE$.tabulate(n1, n2, n3, n4, n5, f);
   }

   public static scala.collection.parallel.ParIterable tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      return ParArray$.MODULE$.tabulate(n1, n2, n3, n4, f);
   }

   public static scala.collection.parallel.ParIterable tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      return ParArray$.MODULE$.tabulate(n1, n2, n3, f);
   }

   public static scala.collection.parallel.ParIterable tabulate(final int n1, final int n2, final Function2 f) {
      return ParArray$.MODULE$.tabulate(n1, n2, f);
   }

   public static scala.collection.parallel.ParIterable tabulate(final int n, final Function1 f) {
      return ParArray$.MODULE$.tabulate(n, f);
   }

   public static scala.collection.parallel.ParIterable fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      return ParArray$.MODULE$.fill(n1, n2, n3, n4, n5, elem);
   }

   public static scala.collection.parallel.ParIterable fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      return ParArray$.MODULE$.fill(n1, n2, n3, n4, elem);
   }

   public static scala.collection.parallel.ParIterable fill(final int n1, final int n2, final int n3, final Function0 elem) {
      return ParArray$.MODULE$.fill(n1, n2, n3, elem);
   }

   public static scala.collection.parallel.ParIterable fill(final int n1, final int n2, final Function0 elem) {
      return ParArray$.MODULE$.fill(n1, n2, elem);
   }

   public static scala.collection.parallel.ParIterable fill(final int n, final Function0 elem) {
      return ParArray$.MODULE$.fill(n, elem);
   }

   public static scala.collection.parallel.ParIterable concat(final Seq xss) {
      return ParArray$.MODULE$.concat(xss);
   }

   public static Factory toFactory() {
      return ParArray$.MODULE$.toFactory();
   }

   public static scala.collection.parallel.ParIterable empty() {
      return ParArray$.MODULE$.empty();
   }

   public ParSeq toSeq() {
      return ParSeq.toSeq$(this);
   }

   public String toString() {
      return scala.collection.parallel.ParSeq.toString$(this);
   }

   public String stringPrefix() {
      return scala.collection.parallel.ParSeq.stringPrefix$(this);
   }

   // $FF: synthetic method
   public boolean scala$collection$parallel$ParSeqLike$$super$sameElements(final IterableOnce that) {
      return ParIterableLike.sameElements$(this, that);
   }

   // $FF: synthetic method
   public scala.collection.parallel.ParSeq scala$collection$parallel$ParSeqLike$$super$zip(final scala.collection.parallel.ParIterable that) {
      return (scala.collection.parallel.ParSeq)ParIterableLike.zip$(this, (scala.collection.parallel.ParIterable)that);
   }

   public int hashCode() {
      return ParSeqLike.hashCode$(this);
   }

   public boolean equals(final Object that) {
      return ParSeqLike.equals$(this, that);
   }

   public boolean canEqual(final Object other) {
      return ParSeqLike.canEqual$(this, other);
   }

   public PreciseSplitter iterator() {
      return ParSeqLike.iterator$(this);
   }

   public final int size() {
      return ParSeqLike.size$(this);
   }

   public boolean isDefinedAt(final int idx) {
      return ParSeqLike.isDefinedAt$(this, idx);
   }

   public int segmentLength(final Function1 p, final int from) {
      return ParSeqLike.segmentLength$(this, p, from);
   }

   public int prefixLength(final Function1 p) {
      return ParSeqLike.prefixLength$(this, p);
   }

   public int indexOf(final Object elem) {
      return ParSeqLike.indexOf$(this, elem);
   }

   public int indexOf(final Object elem, final int from) {
      return ParSeqLike.indexOf$(this, elem, from);
   }

   public int indexWhere(final Function1 p) {
      return ParSeqLike.indexWhere$(this, p);
   }

   public int indexWhere(final Function1 p, final int from) {
      return ParSeqLike.indexWhere$(this, p, from);
   }

   public int lastIndexOf(final Object elem) {
      return ParSeqLike.lastIndexOf$(this, elem);
   }

   public int lastIndexOf(final Object elem, final int end) {
      return ParSeqLike.lastIndexOf$(this, elem, end);
   }

   public int lastIndexWhere(final Function1 p) {
      return ParSeqLike.lastIndexWhere$(this, p);
   }

   public int lastIndexWhere(final Function1 p, final int end) {
      return ParSeqLike.lastIndexWhere$(this, p, end);
   }

   public scala.collection.parallel.ParSeq reverse() {
      return ParSeqLike.reverse$(this);
   }

   public scala.collection.parallel.ParSeq reverseMap(final Function1 f) {
      return ParSeqLike.reverseMap$(this, f);
   }

   public boolean startsWith(final IterableOnce that, final int offset) {
      return ParSeqLike.startsWith$(this, that, offset);
   }

   public int startsWith$default$2() {
      return ParSeqLike.startsWith$default$2$(this);
   }

   public boolean sameElements(final IterableOnce that) {
      return ParSeqLike.sameElements$(this, that);
   }

   public boolean endsWith(final scala.collection.parallel.ParSeq that) {
      return ParSeqLike.endsWith$(this, (scala.collection.parallel.ParSeq)that);
   }

   public boolean endsWith(final Iterable that) {
      return ParSeqLike.endsWith$(this, (Iterable)that);
   }

   public scala.collection.parallel.ParSeq patch(final int from, final scala.collection.Seq patch, final int replaced) {
      return ParSeqLike.patch$(this, from, (scala.collection.Seq)patch, replaced);
   }

   public scala.collection.parallel.ParSeq patch(final int from, final scala.collection.parallel.ParSeq patch, final int replaced) {
      return ParSeqLike.patch$(this, from, (scala.collection.parallel.ParSeq)patch, replaced);
   }

   public scala.collection.parallel.ParSeq updated(final int index, final Object elem) {
      return ParSeqLike.updated$(this, index, elem);
   }

   public scala.collection.parallel.ParSeq $plus$colon(final Object elem) {
      return ParSeqLike.$plus$colon$(this, elem);
   }

   public scala.collection.parallel.ParSeq $colon$plus(final Object elem) {
      return ParSeqLike.$colon$plus$(this, elem);
   }

   public scala.collection.parallel.ParSeq union(final scala.collection.parallel.ParSeq that) {
      return ParSeqLike.union$(this, (scala.collection.parallel.ParSeq)that);
   }

   public scala.collection.parallel.ParSeq union(final scala.collection.Seq that) {
      return ParSeqLike.union$(this, (scala.collection.Seq)that);
   }

   public scala.collection.parallel.ParSeq padTo(final int len, final Object elem) {
      return ParSeqLike.padTo$(this, len, elem);
   }

   public scala.collection.parallel.ParSeq zip(final scala.collection.parallel.ParIterable that) {
      return ParSeqLike.zip$(this, that);
   }

   public boolean corresponds(final scala.collection.parallel.ParSeq that, final Function2 p) {
      return ParSeqLike.corresponds$(this, that, p);
   }

   public scala.collection.parallel.ParSeq diff(final scala.collection.parallel.ParSeq that) {
      return ParSeqLike.diff$(this, (scala.collection.parallel.ParSeq)that);
   }

   public scala.collection.parallel.ParSeq diff(final scala.collection.Seq that) {
      return ParSeqLike.diff$(this, (scala.collection.Seq)that);
   }

   public scala.collection.parallel.ParSeq intersect(final scala.collection.Seq that) {
      return ParSeqLike.intersect$(this, that);
   }

   public scala.collection.parallel.ParSeq distinct() {
      return ParSeqLike.distinct$(this);
   }

   public SeqSplitter down(final IterableSplitter p) {
      return ParSeqLike.down$(this, p);
   }

   public ParIterable toIterable() {
      return ParIterable.toIterable$(this);
   }

   public void initTaskSupport() {
      ParIterableLike.initTaskSupport$(this);
   }

   public TaskSupport tasksupport() {
      return ParIterableLike.tasksupport$(this);
   }

   public void tasksupport_$eq(final TaskSupport ts) {
      ParIterableLike.tasksupport_$eq$(this, ts);
   }

   public scala.collection.parallel.ParIterable repr() {
      return ParIterableLike.repr$(this);
   }

   public final boolean isTraversableAgain() {
      return ParIterableLike.isTraversableAgain$(this);
   }

   public boolean hasDefiniteSize() {
      return ParIterableLike.hasDefiniteSize$(this);
   }

   public boolean isEmpty() {
      return ParIterableLike.isEmpty$(this);
   }

   public boolean nonEmpty() {
      return ParIterableLike.nonEmpty$(this);
   }

   public Object head() {
      return ParIterableLike.head$(this);
   }

   public Option headOption() {
      return ParIterableLike.headOption$(this);
   }

   public scala.collection.parallel.ParIterable tail() {
      return ParIterableLike.tail$(this);
   }

   public Object last() {
      return ParIterableLike.last$(this);
   }

   public Option lastOption() {
      return ParIterableLike.lastOption$(this);
   }

   public scala.collection.parallel.ParIterable init() {
      return ParIterableLike.init$(this);
   }

   public scala.collection.parallel.ParIterable par() {
      return ParIterableLike.par$(this);
   }

   public boolean isStrictSplitterCollection() {
      return ParIterableLike.isStrictSplitterCollection$(this);
   }

   public Combiner reuse(final Option oldc, final Combiner newc) {
      return ParIterableLike.reuse$(this, oldc, newc);
   }

   public ParIterableLike.TaskOps task2ops(final ParIterableLike.StrictSplitterCheckTask tsk) {
      return ParIterableLike.task2ops$(this, tsk);
   }

   public ParIterableLike.NonDivisible wrap(final Function0 body) {
      return ParIterableLike.wrap$(this, body);
   }

   public ParIterableLike.SignallingOps delegatedSignalling2ops(final DelegatedSignalling it) {
      return ParIterableLike.delegatedSignalling2ops$(this, it);
   }

   public ParIterableLike.BuilderOps builder2ops(final Builder cb) {
      return ParIterableLike.builder2ops$(this, cb);
   }

   public scala.collection.parallel.ParIterable sequentially(final Function1 b) {
      return ParIterableLike.sequentially$(this, b);
   }

   public String mkString(final String start, final String sep, final String end) {
      return ParIterableLike.mkString$(this, start, sep, end);
   }

   public String mkString(final String sep) {
      return ParIterableLike.mkString$(this, sep);
   }

   public String mkString() {
      return ParIterableLike.mkString$(this);
   }

   public Object reduce(final Function2 op) {
      return ParIterableLike.reduce$(this, op);
   }

   public Option reduceOption(final Function2 op) {
      return ParIterableLike.reduceOption$(this, op);
   }

   public Object fold(final Object z, final Function2 op) {
      return ParIterableLike.fold$(this, z, op);
   }

   public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
      return ParIterableLike.aggregate$(this, z, seqop, combop);
   }

   public Object foldLeft(final Object z, final Function2 op) {
      return ParIterableLike.foldLeft$(this, z, op);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return ParIterableLike.foldRight$(this, z, op);
   }

   public Object reduceLeft(final Function2 op) {
      return ParIterableLike.reduceLeft$(this, op);
   }

   public Object reduceRight(final Function2 op) {
      return ParIterableLike.reduceRight$(this, op);
   }

   public Option reduceLeftOption(final Function2 op) {
      return ParIterableLike.reduceLeftOption$(this, op);
   }

   public Option reduceRightOption(final Function2 op) {
      return ParIterableLike.reduceRightOption$(this, op);
   }

   public void foreach(final Function1 f) {
      ParIterableLike.foreach$(this, f);
   }

   public int count(final Function1 p) {
      return ParIterableLike.count$(this, p);
   }

   public Object sum(final Numeric num) {
      return ParIterableLike.sum$(this, num);
   }

   public Object product(final Numeric num) {
      return ParIterableLike.product$(this, num);
   }

   public Object min(final Ordering ord) {
      return ParIterableLike.min$(this, ord);
   }

   public Object max(final Ordering ord) {
      return ParIterableLike.max$(this, ord);
   }

   public Object maxBy(final Function1 f, final Ordering cmp) {
      return ParIterableLike.maxBy$(this, f, cmp);
   }

   public Object minBy(final Function1 f, final Ordering cmp) {
      return ParIterableLike.minBy$(this, f, cmp);
   }

   public scala.collection.parallel.ParIterable collect(final PartialFunction pf) {
      return ParIterableLike.collect$(this, pf);
   }

   public scala.collection.parallel.ParIterable flatMap(final Function1 f) {
      return ParIterableLike.flatMap$(this, f);
   }

   public boolean forall(final Function1 p) {
      return ParIterableLike.forall$(this, p);
   }

   public boolean exists(final Function1 p) {
      return ParIterableLike.exists$(this, p);
   }

   public Option find(final Function1 p) {
      return ParIterableLike.find$(this, p);
   }

   public CombinerFactory combinerFactory() {
      return ParIterableLike.combinerFactory$(this);
   }

   public CombinerFactory combinerFactory(final Function0 cbf) {
      return ParIterableLike.combinerFactory$(this, cbf);
   }

   public scala.collection.parallel.ParIterable withFilter(final Function1 pred) {
      return ParIterableLike.withFilter$(this, pred);
   }

   public scala.collection.parallel.ParIterable filter(final Function1 pred) {
      return ParIterableLike.filter$(this, pred);
   }

   public scala.collection.parallel.ParIterable filterNot(final Function1 pred) {
      return ParIterableLike.filterNot$(this, pred);
   }

   public scala.collection.parallel.ParIterable $plus$plus(final IterableOnce that) {
      return ParIterableLike.$plus$plus$(this, that);
   }

   public Tuple2 partition(final Function1 pred) {
      return ParIterableLike.partition$(this, pred);
   }

   public scala.collection.parallel.immutable.ParMap groupBy(final Function1 f) {
      return ParIterableLike.groupBy$(this, f);
   }

   public scala.collection.parallel.ParIterable take(final int n) {
      return ParIterableLike.take$(this, n);
   }

   public scala.collection.parallel.ParIterable drop(final int n) {
      return ParIterableLike.drop$(this, n);
   }

   public scala.collection.parallel.ParIterable slice(final int unc_from, final int unc_until) {
      return ParIterableLike.slice$(this, unc_from, unc_until);
   }

   public Tuple2 splitAt(final int n) {
      return ParIterableLike.splitAt$(this, n);
   }

   public Iterable scanLeft(final Object z, final Function2 op) {
      return ParIterableLike.scanLeft$(this, z, op);
   }

   public Iterable scanRight(final Object z, final Function2 op) {
      return ParIterableLike.scanRight$(this, z, op);
   }

   public scala.collection.parallel.ParIterable takeWhile(final Function1 pred) {
      return ParIterableLike.takeWhile$(this, pred);
   }

   public Tuple2 span(final Function1 pred) {
      return ParIterableLike.span$(this, pred);
   }

   public scala.collection.parallel.ParIterable dropWhile(final Function1 pred) {
      return ParIterableLike.dropWhile$(this, pred);
   }

   public void copyToArray(final Object xs) {
      ParIterableLike.copyToArray$(this, xs);
   }

   public void copyToArray(final Object xs, final int start) {
      ParIterableLike.copyToArray$(this, xs, start);
   }

   public void copyToArray(final Object xs, final int start, final int len) {
      ParIterableLike.copyToArray$(this, xs, start, len);
   }

   public scala.collection.parallel.ParIterable zip(final Iterable that) {
      return ParIterableLike.zip$(this, (Iterable)that);
   }

   public scala.collection.parallel.ParIterable zipWithIndex() {
      return ParIterableLike.zipWithIndex$(this);
   }

   public scala.collection.parallel.ParIterable zipAll(final scala.collection.parallel.ParIterable that, final Object thisElem, final Object thatElem) {
      return ParIterableLike.zipAll$(this, that, thisElem, thatElem);
   }

   public Object toParCollection(final Function0 cbf) {
      return ParIterableLike.toParCollection$(this, cbf);
   }

   public Object toParMap(final Function0 cbf, final .less.colon.less ev) {
      return ParIterableLike.toParMap$(this, cbf, ev);
   }

   public Object toArray(final ClassTag evidence$1) {
      return ParIterableLike.toArray$(this, evidence$1);
   }

   public List toList() {
      return ParIterableLike.toList$(this);
   }

   public IndexedSeq toIndexedSeq() {
      return ParIterableLike.toIndexedSeq$(this);
   }

   /** @deprecated */
   public Stream toStream() {
      return ParIterableLike.toStream$(this);
   }

   public Iterator toIterator() {
      return ParIterableLike.toIterator$(this);
   }

   public Buffer toBuffer() {
      return ParIterableLike.toBuffer$(this);
   }

   /** @deprecated */
   public scala.collection.parallel.ParIterable toTraversable() {
      return ParIterableLike.toTraversable$(this);
   }

   public scala.collection.parallel.immutable.ParSet toSet() {
      return ParIterableLike.toSet$(this);
   }

   public scala.collection.parallel.immutable.ParMap toMap(final .less.colon.less ev) {
      return ParIterableLike.toMap$(this, ev);
   }

   public Vector toVector() {
      return ParIterableLike.toVector$(this);
   }

   public Object to(final Factory factory) {
      return ParIterableLike.to$(this, factory);
   }

   public int scanBlockSize() {
      return ParIterableLike.scanBlockSize$(this);
   }

   public Object $div$colon(final Object z, final Function2 op) {
      return ParIterableLike.$div$colon$(this, z, op);
   }

   public Object $colon$bslash(final Object z, final Function2 op) {
      return ParIterableLike.$colon$bslash$(this, z, op);
   }

   public String debugInformation() {
      return ParIterableLike.debugInformation$(this);
   }

   public Seq brokenInvariants() {
      return ParIterableLike.brokenInvariants$(this);
   }

   public ArrayBuffer debugBuffer() {
      return ParIterableLike.debugBuffer$(this);
   }

   public void debugclear() {
      ParIterableLike.debugclear$(this);
   }

   public ArrayBuffer debuglog(final String s) {
      return ParIterableLike.debuglog$(this, s);
   }

   public void printDebugBuffer() {
      ParIterableLike.printDebugBuffer$(this);
   }

   public Nothing parCombiner() {
      return CustomParallelizable.parCombiner$(this);
   }

   public Stepper stepper(final StepperShape shape) {
      return IterableOnce.stepper$(this, shape);
   }

   public Combiner newBuilder() {
      return GenericParTemplate.newBuilder$(this);
   }

   public Combiner newCombiner() {
      return GenericParTemplate.newCombiner$(this);
   }

   public Combiner genericBuilder() {
      return GenericParTemplate.genericBuilder$(this);
   }

   public Combiner genericCombiner() {
      return GenericParTemplate.genericCombiner$(this);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return GenericTraversableTemplate.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return GenericTraversableTemplate.unzip3$(this, asTriple);
   }

   public scala.collection.parallel.ParIterable flatten(final Function1 asTraversable) {
      return GenericTraversableTemplate.flatten$(this, asTraversable);
   }

   public scala.collection.parallel.ParIterable transpose(final Function1 asTraversable) {
      return GenericTraversableTemplate.transpose$(this, asTraversable);
   }

   public ParArrayIterator$ ParArrayIterator() {
      if (this.ParArrayIterator$module == null) {
         this.ParArrayIterator$lzycompute$1();
      }

      return this.ParArrayIterator$module;
   }

   public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
      return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
   }

   public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(final TaskSupport x$1) {
      this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
   }

   public ParIterableLike.ScanNode$ ScanNode() {
      if (this.ScanNode$module == null) {
         this.ScanNode$lzycompute$1();
      }

      return this.ScanNode$module;
   }

   public ParIterableLike.ScanLeaf$ ScanLeaf() {
      if (this.ScanLeaf$module == null) {
         this.ScanLeaf$lzycompute$1();
      }

      return this.ScanLeaf$module;
   }

   public ArraySeq arrayseq() {
      return this.arrayseq;
   }

   public Object[] scala$collection$parallel$mutable$ParArray$$array() {
      return this.scala$collection$parallel$mutable$ParArray$$array;
   }

   private void array_$eq(final Object[] x$1) {
      this.scala$collection$parallel$mutable$ParArray$$array = x$1;
   }

   public GenericParCompanion companion() {
      return ParArray$.MODULE$;
   }

   public Object apply(final int i) {
      if (i >= this.sz) {
         throw new IndexOutOfBoundsException(Integer.toString(i));
      } else {
         return this.scala$collection$parallel$mutable$ParArray$$array()[i];
      }
   }

   public void update(final int i, final Object elem) {
      if (i >= this.sz) {
         throw new IndexOutOfBoundsException(Integer.toString(i));
      } else {
         this.scala$collection$parallel$mutable$ParArray$$array()[i] = elem;
      }
   }

   public int length() {
      return this.sz;
   }

   public int knownSize() {
      return this.sz;
   }

   public ArraySeq seq() {
      return this.length() == this.arrayseq().length() ? this.arrayseq() : (ArraySeq)this.arrayseq().take(this.length());
   }

   public ParArrayIterator splitter() {
      ParArrayIterator pit = new ParArrayIterator(this.ParArrayIterator().$lessinit$greater$default$1(), this.ParArrayIterator().$lessinit$greater$default$2(), this.ParArrayIterator().$lessinit$greater$default$3());
      return pit;
   }

   public ParArray map(final Function1 f) {
      Object[] targetarr = new Object[this.length()];
      ArraySeq targarrseq = scala.collection.mutable.ArraySeq..MODULE$.make(targetarr);
      this.tasksupport().executeAndWaitResult(new ParArrayMap(f, targetarr, 0, this.length()));
      return new ParArray(targarrseq);
   }

   public ParArray scan(final Object z, final Function2 op) {
      if (this.tasksupport().parallelismLevel() > 1) {
         Object[] targetarr = new Object[this.length() + 1];
         ArraySeq targarrseq = scala.collection.mutable.ArraySeq..MODULE$.make(targetarr);
         targetarr[0] = z;
         if (this.length() > 0) {
            this.tasksupport().executeAndWaitResult(this.task2ops(new ParIterableLike.CreateScanTree(0, this.size(), z, op, this.splitter())).mapResult((tree) -> {
               $anonfun$scan$1(this, z, op, targetarr, tree);
               return BoxedUnit.UNIT;
            }));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         return new ParArray(targarrseq);
      } else {
         return (ParArray)ParIterableLike.scan$(this, z, op);
      }
   }

   private void writeObject(final ObjectOutputStream out) {
      out.defaultWriteObject();
   }

   private void readObject(final ObjectInputStream in) {
      in.defaultReadObject();
      this.array_$eq(this.arrayseq().array());
   }

   private final void ParArrayIterator$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ParArrayIterator$module == null) {
            this.ParArrayIterator$module = new ParArrayIterator$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void ScanNode$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ScanNode$module == null) {
            this.ScanNode$module = new ParIterableLike.ScanNode$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void ScanLeaf$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ScanLeaf$module == null) {
            this.ScanLeaf$module = new ParIterableLike.ScanLeaf$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$scan$1(final ParArray $this, final Object z$1, final Function2 op$1, final Object[] targetarr$1, final ParIterableLike.ScanTree tree) {
      $this.tasksupport().executeAndWaitResult($this.new ScanToArray(tree, z$1, op$1, targetarr$1));
   }

   public ParArray(final ArraySeq arrayseq, final int sz) {
      this.arrayseq = arrayseq;
      this.sz = sz;
      GenericTraversableTemplate.$init$(this);
      GenericParTemplate.$init$(this);
      IterableOnce.$init$(this);
      Parallelizable.$init$(this);
      CustomParallelizable.$init$(this);
      ParIterableLike.$init$(this);
      scala.collection.parallel.ParIterable.$init$(this);
      ParIterable.$init$(this);
      ParSeqLike.$init$(this);
      scala.collection.parallel.ParSeq.$init$(this);
      ParSeq.$init$(this);
      this.scala$collection$parallel$mutable$ParArray$$array = arrayseq.array();
   }

   public ParArray(final ArraySeq arr) {
      this(arr, arr.length());
   }

   public ParArray(final int sz) {
      scala.Predef..MODULE$.require(sz >= 0);
      this(scala.collection.mutable.ArraySeq..MODULE$.make(new Object[sz]), sz);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public class ParArrayIterator implements SeqSplitter {
      private int i;
      private final int until;
      private final Object[] arr;
      private Signalling signalDelegate;
      // $FF: synthetic field
      public final ParArray $outer;

      public Seq splitWithSignalling() {
         return SeqSplitter.splitWithSignalling$(this);
      }

      public Seq psplitWithSignalling(final Seq sizes) {
         return SeqSplitter.psplitWithSignalling$(this, sizes);
      }

      public SeqSplitter.RemainsIteratorTaken newTaken(final int until) {
         return SeqSplitter.newTaken$(this, until);
      }

      public SeqSplitter take(final int n) {
         return SeqSplitter.take$(this, n);
      }

      public SeqSplitter slice(final int from1, final int until1) {
         return SeqSplitter.slice$(this, from1, until1);
      }

      public SeqSplitter map(final Function1 f) {
         return SeqSplitter.map$(this, f);
      }

      public SeqSplitter.RemainsIteratorAppended appendParSeq(final SeqSplitter that) {
         return SeqSplitter.appendParSeq$(this, that);
      }

      public SeqSplitter zipParSeq(final SeqSplitter that) {
         return SeqSplitter.zipParSeq$(this, that);
      }

      public SeqSplitter.RemainsIteratorZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
         return SeqSplitter.zipAllParSeq$(this, that, thisElem, thatElem);
      }

      public SeqSplitter reverse() {
         return SeqSplitter.reverse$(this);
      }

      public SeqSplitter.Patched patchParSeq(final int from, final SeqSplitter patchElems, final int replaced) {
         return SeqSplitter.patchParSeq$(this, from, patchElems, replaced);
      }

      public boolean corresponds(final Function2 corr, final Iterator that) {
         return AugmentedSeqIterator.corresponds$(this, corr, that);
      }

      public Combiner reverseMap2combiner(final Function1 f, final Combiner cb) {
         return AugmentedSeqIterator.reverseMap2combiner$(this, f, cb);
      }

      public Combiner updated2combiner(final int index, final Object elem, final Combiner cb) {
         return AugmentedSeqIterator.updated2combiner$(this, index, elem, cb);
      }

      public boolean shouldSplitFurther(final scala.collection.parallel.ParIterable coll, final int parallelismLevel) {
         return IterableSplitter.shouldSplitFurther$(this, coll, parallelismLevel);
      }

      public String buildString(final Function1 closure) {
         return IterableSplitter.buildString$(this, closure);
      }

      public String debugInformation() {
         return IterableSplitter.debugInformation$(this);
      }

      public IterableSplitter.Taken newSliceInternal(final IterableSplitter.Taken it, final int from1) {
         return IterableSplitter.newSliceInternal$(this, it, from1);
      }

      public IterableSplitter.Appended appendParIterable(final IterableSplitter that) {
         return IterableSplitter.appendParIterable$(this, that);
      }

      public boolean isAborted() {
         return DelegatedSignalling.isAborted$(this);
      }

      public void abort() {
         DelegatedSignalling.abort$(this);
      }

      public int indexFlag() {
         return DelegatedSignalling.indexFlag$(this);
      }

      public void setIndexFlag(final int f) {
         DelegatedSignalling.setIndexFlag$(this, f);
      }

      public void setIndexFlagIfGreater(final int f) {
         DelegatedSignalling.setIndexFlagIfGreater$(this, f);
      }

      public void setIndexFlagIfLesser(final int f) {
         DelegatedSignalling.setIndexFlagIfLesser$(this, f);
      }

      public int tag() {
         return DelegatedSignalling.tag$(this);
      }

      public Object reduce(final Function2 op) {
         return AugmentedIterableIterator.reduce$(this, op);
      }

      public Object min(final Ordering ord) {
         return AugmentedIterableIterator.min$(this, ord);
      }

      public Object max(final Ordering ord) {
         return AugmentedIterableIterator.max$(this, ord);
      }

      public Object reduceLeft(final int howmany, final Function2 op) {
         return AugmentedIterableIterator.reduceLeft$(this, howmany, op);
      }

      public Combiner slice2combiner(final int from, final int until, final Combiner cb) {
         return AugmentedIterableIterator.slice2combiner$(this, from, until, cb);
      }

      public Tuple2 splitAt2combiners(final int at, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.splitAt2combiners$(this, at, before, after);
      }

      public Tuple2 takeWhile2combiner(final Function1 p, final Combiner cb) {
         return AugmentedIterableIterator.takeWhile2combiner$(this, p, cb);
      }

      public Tuple2 span2combiners(final Function1 p, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.span2combiners$(this, p, before, after);
      }

      public Combiner scanToCombiner(final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, startValue, op, cb);
      }

      public Combiner scanToCombiner(final int howmany, final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, howmany, startValue, op, cb);
      }

      public Combiner zip2combiner(final RemainsIterator otherpit, final Combiner cb) {
         return AugmentedIterableIterator.zip2combiner$(this, otherpit, cb);
      }

      public Combiner zipAll2combiner(final RemainsIterator that, final Object thiselem, final Object thatelem, final Combiner cb) {
         return AugmentedIterableIterator.zipAll2combiner$(this, that, thiselem, thatelem, cb);
      }

      public boolean isRemainingCheap() {
         return RemainsIterator.isRemainingCheap$(this);
      }

      /** @deprecated */
      public final boolean hasDefiniteSize() {
         return Iterator.hasDefiniteSize$(this);
      }

      public final Iterator iterator() {
         return Iterator.iterator$(this);
      }

      public Option nextOption() {
         return Iterator.nextOption$(this);
      }

      public boolean contains(final Object elem) {
         return Iterator.contains$(this, elem);
      }

      public BufferedIterator buffered() {
         return Iterator.buffered$(this);
      }

      public Iterator padTo(final int len, final Object elem) {
         return Iterator.padTo$(this, len, elem);
      }

      public Tuple2 partition(final Function1 p) {
         return Iterator.partition$(this, p);
      }

      public Iterator.GroupedIterator grouped(final int size) {
         return Iterator.grouped$(this, size);
      }

      public Iterator.GroupedIterator sliding(final int size, final int step) {
         return Iterator.sliding$(this, size, step);
      }

      public int sliding$default$2() {
         return Iterator.sliding$default$2$(this);
      }

      public Iterator scanLeft(final Object z, final Function2 op) {
         return Iterator.scanLeft$(this, z, op);
      }

      /** @deprecated */
      public Iterator scanRight(final Object z, final Function2 op) {
         return Iterator.scanRight$(this, z, op);
      }

      public int indexWhere(final Function1 p, final int from) {
         return Iterator.indexWhere$(this, p, from);
      }

      public int indexWhere$default$2() {
         return Iterator.indexWhere$default$2$(this);
      }

      public int indexOf(final Object elem) {
         return Iterator.indexOf$(this, elem);
      }

      public int indexOf(final Object elem, final int from) {
         return Iterator.indexOf$(this, elem, from);
      }

      public final int length() {
         return Iterator.length$(this);
      }

      public boolean isEmpty() {
         return Iterator.isEmpty$(this);
      }

      public Iterator filter(final Function1 p) {
         return Iterator.filter$(this, p);
      }

      public Iterator filterNot(final Function1 p) {
         return Iterator.filterNot$(this, p);
      }

      public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
         return Iterator.filterImpl$(this, p, isFlipped);
      }

      public Iterator withFilter(final Function1 p) {
         return Iterator.withFilter$(this, p);
      }

      public Iterator collect(final PartialFunction pf) {
         return Iterator.collect$(this, pf);
      }

      public Iterator distinct() {
         return Iterator.distinct$(this);
      }

      public Iterator distinctBy(final Function1 f) {
         return Iterator.distinctBy$(this, f);
      }

      public Iterator flatMap(final Function1 f) {
         return Iterator.flatMap$(this, f);
      }

      public Iterator flatten(final Function1 ev) {
         return Iterator.flatten$(this, ev);
      }

      public Iterator concat(final Function0 xs) {
         return Iterator.concat$(this, xs);
      }

      public final Iterator $plus$plus(final Function0 xs) {
         return Iterator.$plus$plus$(this, xs);
      }

      public Iterator takeWhile(final Function1 p) {
         return Iterator.takeWhile$(this, p);
      }

      public Iterator dropWhile(final Function1 p) {
         return Iterator.dropWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return Iterator.span$(this, p);
      }

      public Iterator sliceIterator(final int from, final int until) {
         return Iterator.sliceIterator$(this, from, until);
      }

      public Iterator zip(final IterableOnce that) {
         return Iterator.zip$(this, that);
      }

      public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
         return Iterator.zipAll$(this, that, thisElem, thatElem);
      }

      public Iterator zipWithIndex() {
         return Iterator.zipWithIndex$(this);
      }

      public Tuple2 duplicate() {
         return Iterator.duplicate$(this);
      }

      public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
         return Iterator.patch$(this, from, patchElems, replaced);
      }

      public Iterator tapEach(final Function1 f) {
         return Iterator.tapEach$(this, f);
      }

      /** @deprecated */
      public Iterator seq() {
         return Iterator.seq$(this);
      }

      public Tuple2 splitAt(final int n) {
         return IterableOnceOps.splitAt$(this, n);
      }

      public boolean isTraversableAgain() {
         return IterableOnceOps.isTraversableAgain$(this);
      }

      public Object foldRight(final Object z, final Function2 op) {
         return IterableOnceOps.foldRight$(this, z, op);
      }

      /** @deprecated */
      public final Object $div$colon(final Object z, final Function2 op) {
         return IterableOnceOps.$div$colon$(this, z, op);
      }

      /** @deprecated */
      public final Object $colon$bslash(final Object z, final Function2 op) {
         return IterableOnceOps.$colon$bslash$(this, z, op);
      }

      public Option reduceOption(final Function2 op) {
         return IterableOnceOps.reduceOption$(this, op);
      }

      public Object reduceLeft(final Function2 op) {
         return IterableOnceOps.reduceLeft$(this, op);
      }

      public Object reduceRight(final Function2 op) {
         return IterableOnceOps.reduceRight$(this, op);
      }

      public Option reduceLeftOption(final Function2 op) {
         return IterableOnceOps.reduceLeftOption$(this, op);
      }

      public Option reduceRightOption(final Function2 op) {
         return IterableOnceOps.reduceRightOption$(this, op);
      }

      public boolean nonEmpty() {
         return IterableOnceOps.nonEmpty$(this);
      }

      public int size() {
         return IterableOnceOps.size$(this);
      }

      /** @deprecated */
      public final void copyToBuffer(final Buffer dest) {
         IterableOnceOps.copyToBuffer$(this, dest);
      }

      public int copyToArray(final Object xs) {
         return IterableOnceOps.copyToArray$(this, xs);
      }

      public int copyToArray(final Object xs, final int start) {
         return IterableOnceOps.copyToArray$(this, xs, start);
      }

      public Option minOption(final Ordering ord) {
         return IterableOnceOps.minOption$(this, ord);
      }

      public Option maxOption(final Ordering ord) {
         return IterableOnceOps.maxOption$(this, ord);
      }

      public Object maxBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxBy$(this, f, ord);
      }

      public Option maxByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxByOption$(this, f, ord);
      }

      public Object minBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minBy$(this, f, ord);
      }

      public Option minByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minByOption$(this, f, ord);
      }

      public Option collectFirst(final PartialFunction pf) {
         return IterableOnceOps.collectFirst$(this, pf);
      }

      public boolean corresponds(final IterableOnce that, final Function2 p) {
         return IterableOnceOps.corresponds$(this, that, p);
      }

      public final String mkString(final String start, final String sep, final String end) {
         return IterableOnceOps.mkString$(this, start, sep, end);
      }

      public final String mkString(final String sep) {
         return IterableOnceOps.mkString$(this, sep);
      }

      public final String mkString() {
         return IterableOnceOps.mkString$(this);
      }

      public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
         return IterableOnceOps.addString$(this, b, start, sep, end);
      }

      public final StringBuilder addString(final StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final StringBuilder addString(final StringBuilder b) {
         return IterableOnceOps.addString$(this, b);
      }

      public Object to(final Factory factory) {
         return IterableOnceOps.to$(this, factory);
      }

      /** @deprecated */
      public final Iterator toIterator() {
         return IterableOnceOps.toIterator$(this);
      }

      public List toList() {
         return IterableOnceOps.toList$(this);
      }

      public Vector toVector() {
         return IterableOnceOps.toVector$(this);
      }

      public scala.collection.immutable.Map toMap(final .less.colon.less ev) {
         return IterableOnceOps.toMap$(this, ev);
      }

      public Set toSet() {
         return IterableOnceOps.toSet$(this);
      }

      public Seq toSeq() {
         return IterableOnceOps.toSeq$(this);
      }

      public IndexedSeq toIndexedSeq() {
         return IterableOnceOps.toIndexedSeq$(this);
      }

      /** @deprecated */
      public final Stream toStream() {
         return IterableOnceOps.toStream$(this);
      }

      public final Buffer toBuffer() {
         return IterableOnceOps.toBuffer$(this);
      }

      public Object toArray(final ClassTag evidence$2) {
         return IterableOnceOps.toArray$(this, evidence$2);
      }

      public Iterable reversed() {
         return IterableOnceOps.reversed$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      public int knownSize() {
         return IterableOnce.knownSize$(this);
      }

      public Signalling signalDelegate() {
         return this.signalDelegate;
      }

      public void signalDelegate_$eq(final Signalling x$1) {
         this.signalDelegate = x$1;
      }

      // $FF: synthetic method
      private Combiner super$reverse2combiner(final Combiner cb) {
         return AugmentedSeqIterator.reverse2combiner$(this, cb);
      }

      public int i() {
         return this.i;
      }

      public void i_$eq(final int x$1) {
         this.i = x$1;
      }

      public int until() {
         return this.until;
      }

      public Object[] arr() {
         return this.arr;
      }

      public boolean hasNext() {
         return this.i() < this.until();
      }

      public Object next() {
         Object elem = this.arr()[this.i()];
         this.i_$eq(this.i() + 1);
         return elem;
      }

      public int remaining() {
         return this.until() - this.i();
      }

      public ParArrayIterator dup() {
         return this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().new ParArrayIterator(this.i(), this.until(), this.arr());
      }

      public Seq psplit(final Seq sizesIncomplete) {
         IntRef traversed = IntRef.create(this.i());
         int total = BoxesRunTime.unboxToInt(sizesIncomplete.reduceLeft((JFunction2.mcIII.sp)(x$1, x$2) -> x$1 + x$2));
         int left = this.remaining();
         Seq sizes = total >= left ? sizesIncomplete : (Seq)sizesIncomplete.$colon$plus(BoxesRunTime.boxToInteger(left - total));
         return (Seq)sizes.map((sz) -> $anonfun$psplit$2(this, traversed, BoxesRunTime.unboxToInt(sz)));
      }

      public Seq split() {
         int left = this.remaining();
         if (left >= 2) {
            int splitpoint = left / 2;
            Seq sq = new scala.collection.immutable..colon.colon(this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().new ParArrayIterator(this.i(), this.i() + splitpoint, this.arr()), new scala.collection.immutable..colon.colon(this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().new ParArrayIterator(this.i() + splitpoint, this.until(), this.arr()), scala.collection.immutable.Nil..MODULE$));
            this.i_$eq(this.until());
            return sq;
         } else {
            return new scala.collection.immutable..colon.colon(this, scala.collection.immutable.Nil..MODULE$);
         }
      }

      public String toString() {
         return (new java.lang.StringBuilder(20)).append("ParArrayIterator(").append(this.i()).append(", ").append(this.until()).append(")").toString();
      }

      public void foreach(final Function1 f) {
         this.foreach_quick(f, this.arr(), this.until(), this.i());
         this.i_$eq(this.until());
      }

      private void foreach_quick(final Function1 f, final Object[] a, final int ntil, final int from) {
         for(int j = from; j < ntil; ++j) {
            f.apply(a[j]);
         }

      }

      public int count(final Function1 p) {
         int c = this.count_quick(p, this.arr(), this.until(), this.i());
         this.i_$eq(this.until());
         return c;
      }

      private int count_quick(final Function1 p, final Object[] a, final int ntil, final int from) {
         int cnt = 0;

         for(int j = from; j < ntil; ++j) {
            if (BoxesRunTime.unboxToBoolean(p.apply(a[j]))) {
               ++cnt;
            }
         }

         return cnt;
      }

      public Object foldLeft(final Object z, final Function2 op) {
         Object r = this.foldLeft_quick(this.arr(), this.until(), op, z);
         this.i_$eq(this.until());
         return r;
      }

      private Object foldLeft_quick(final Object[] a, final int ntil, final Function2 op, final Object z) {
         int j = this.i();

         Object sum;
         for(sum = z; j < ntil; ++j) {
            sum = op.apply(sum, a[j]);
         }

         return sum;
      }

      public Object fold(final Object z, final Function2 op) {
         return this.foldLeft(z, op);
      }

      public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
         return this.foldLeft(z.apply(), seqop);
      }

      public Object sum(final Numeric num) {
         Object s = this.sum_quick(num, this.arr(), this.until(), this.i(), num.zero());
         this.i_$eq(this.until());
         return s;
      }

      private Object sum_quick(final Numeric num, final Object[] a, final int ntil, final int from, final Object zero) {
         int j = from;

         Object sum;
         for(sum = zero; j < ntil; ++j) {
            sum = num.plus(sum, a[j]);
         }

         return sum;
      }

      public Object product(final Numeric num) {
         Object p = this.product_quick(num, this.arr(), this.until(), this.i(), num.one());
         this.i_$eq(this.until());
         return p;
      }

      private Object product_quick(final Numeric num, final Object[] a, final int ntil, final int from, final Object one) {
         int j = from;

         Object prod;
         for(prod = one; j < ntil; ++j) {
            prod = num.times(prod, a[j]);
         }

         return prod;
      }

      public boolean forall(final Function1 p) {
         if (this.isAborted()) {
            return false;
         } else {
            boolean all = true;

            while(this.i() < this.until()) {
               int nextuntil = this.i() + scala.collection.parallel.package$.MODULE$.CHECK_RATE() > this.until() ? this.until() : this.i() + scala.collection.parallel.package$.MODULE$.CHECK_RATE();
               all = this.forall_quick(p, this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().scala$collection$parallel$mutable$ParArray$$array(), nextuntil, this.i());
               if (all) {
                  this.i_$eq(nextuntil);
               } else {
                  this.i_$eq(this.until());
                  this.abort();
               }

               if (this.isAborted()) {
                  return false;
               }
            }

            return all;
         }
      }

      private boolean forall_quick(final Function1 p, final Object[] a, final int nextuntil, final int start) {
         for(int j = start; j < nextuntil; ++j) {
            if (!BoxesRunTime.unboxToBoolean(p.apply(a[j]))) {
               return false;
            }
         }

         return true;
      }

      public boolean exists(final Function1 p) {
         if (this.isAborted()) {
            return true;
         } else {
            boolean some = false;

            while(this.i() < this.until()) {
               int nextuntil = this.i() + scala.collection.parallel.package$.MODULE$.CHECK_RATE() > this.until() ? this.until() : this.i() + scala.collection.parallel.package$.MODULE$.CHECK_RATE();
               some = this.exists_quick(p, this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().scala$collection$parallel$mutable$ParArray$$array(), nextuntil, this.i());
               if (some) {
                  this.i_$eq(this.until());
                  this.abort();
               } else {
                  this.i_$eq(nextuntil);
               }

               if (this.isAborted()) {
                  return true;
               }
            }

            return some;
         }
      }

      private boolean exists_quick(final Function1 p, final Object[] a, final int nextuntil, final int start) {
         for(int j = start; j < nextuntil; ++j) {
            if (BoxesRunTime.unboxToBoolean(p.apply(a[j]))) {
               return true;
            }
         }

         return false;
      }

      public Option find(final Function1 p) {
         if (this.isAborted()) {
            return scala.None..MODULE$;
         } else {
            Option r = scala.None..MODULE$;

            while(this.i() < this.until()) {
               label34: {
                  label33: {
                     int nextuntil = this.i() + scala.collection.parallel.package$.MODULE$.CHECK_RATE() < this.until() ? this.i() + scala.collection.parallel.package$.MODULE$.CHECK_RATE() : this.until();
                     r = this.find_quick(p, this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().scala$collection$parallel$mutable$ParArray$$array(), nextuntil, this.i());
                     None var4 = scala.None..MODULE$;
                     if (r == null) {
                        if (var4 != null) {
                           break label33;
                        }
                     } else if (!r.equals(var4)) {
                        break label33;
                     }

                     this.i_$eq(nextuntil);
                     break label34;
                  }

                  this.i_$eq(this.until());
                  this.abort();
               }

               if (this.isAborted()) {
                  return r;
               }
            }

            return r;
         }
      }

      private Option find_quick(final Function1 p, final Object[] a, final int nextuntil, final int start) {
         for(int j = start; j < nextuntil; ++j) {
            Object elem = a[j];
            if (BoxesRunTime.unboxToBoolean(p.apply(elem))) {
               return new Some(elem);
            }
         }

         return scala.None..MODULE$;
      }

      public ParArrayIterator drop(final int n) {
         this.i_$eq(this.i() + n);
         return this;
      }

      public int copyToArray(final Object array, final int from, final int len) {
         int totallen = scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().length() - this.i()), len)), scala.runtime.ScalaRunTime..MODULE$.array_length(array) - from);
         scala.Array..MODULE$.copy(this.arr(), this.i(), array, from, totallen);
         this.i_$eq(this.i() + totallen);
         return totallen;
      }

      public int prefixLength(final Function1 pred) {
         int r = this.prefixLength_quick(pred, this.arr(), this.until(), this.i());
         this.i_$eq(this.i() + r + 1);
         return r;
      }

      private int prefixLength_quick(final Function1 pred, final Object[] a, final int ntil, final int startpos) {
         int j = startpos;
         int endpos = ntil;

         while(j < endpos) {
            if (BoxesRunTime.unboxToBoolean(pred.apply(a[j]))) {
               ++j;
            } else {
               endpos = j;
            }
         }

         return endpos - startpos;
      }

      public int indexWhere(final Function1 pred) {
         int r = this.indexWhere_quick(pred, this.arr(), this.until(), this.i());
         int ret = r != -1 ? r - this.i() : r;
         this.i_$eq(this.until());
         return ret;
      }

      private int indexWhere_quick(final Function1 pred, final Object[] a, final int ntil, final int from) {
         int j = from;
         int pos = -1;

         while(j < ntil) {
            if (BoxesRunTime.unboxToBoolean(pred.apply(a[j]))) {
               pos = j;
               j = ntil;
            } else {
               ++j;
            }
         }

         return pos;
      }

      public int lastIndexWhere(final Function1 pred) {
         int r = this.lastIndexWhere_quick(pred, this.arr(), this.i(), this.until());
         int ret = r != -1 ? r - this.i() : r;
         this.i_$eq(this.until());
         return ret;
      }

      private int lastIndexWhere_quick(final Function1 pred, final Object[] a, final int from, final int ntil) {
         int pos = -1;
         int j = ntil - 1;

         while(j >= from) {
            if (BoxesRunTime.unboxToBoolean(pred.apply(a[j]))) {
               pos = j;
               j = -1;
            } else {
               --j;
            }
         }

         return pos;
      }

      public boolean sameElements(final IterableOnce that) {
         boolean same = true;

         for(Iterator thatIt = that.iterator(); this.i() < this.until() && thatIt.hasNext(); this.i_$eq(this.i() + 1)) {
            if (!BoxesRunTime.equals(this.arr()[this.i()], thatIt.next())) {
               this.i_$eq(this.until());
               same = false;
            }
         }

         return same;
      }

      public Combiner map2combiner(final Function1 f, final Combiner cb) {
         cb.sizeHint(this.remaining());
         this.map2combiner_quick(f, this.arr(), cb, this.until(), this.i());
         this.i_$eq(this.until());
         return cb;
      }

      private void map2combiner_quick(final Function1 f, final Object[] a, final Builder cb, final int ntil, final int from) {
         for(int j = from; j < ntil; ++j) {
            cb.$plus$eq(f.apply(a[j]));
         }

      }

      public Combiner collect2combiner(final PartialFunction pf, final Combiner cb) {
         this.collect2combiner_quick(pf, this.arr(), cb, this.until(), this.i());
         this.i_$eq(this.until());
         return cb;
      }

      private void collect2combiner_quick(final PartialFunction pf, final Object[] a, final Builder cb, final int ntil, final int from) {
         int j = from;

         for(Function1 runWith = pf.runWith((b) -> (Builder)cb.$plus$eq(b)); j < ntil; ++j) {
            Object curr = a[j];
            runWith.apply(curr);
         }

      }

      public Combiner flatmap2combiner(final Function1 f, final Combiner cb) {
         while(this.i() < this.until()) {
            IterableOnce it = (IterableOnce)f.apply(this.arr()[this.i()]);
            cb.$plus$plus$eq(it);
            this.i_$eq(this.i() + 1);
         }

         return cb;
      }

      public Combiner filter2combiner(final Function1 pred, final Combiner cb) {
         this.filter2combiner_quick(pred, cb, this.arr(), this.until(), this.i());
         this.i_$eq(this.until());
         return cb;
      }

      private void filter2combiner_quick(final Function1 pred, final Builder cb, final Object[] a, final int ntil, final int from) {
         for(int j = this.i(); j < ntil; ++j) {
            Object curr = a[j];
            if (BoxesRunTime.unboxToBoolean(pred.apply(curr))) {
               cb.$plus$eq(curr);
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         }

      }

      public Combiner filterNot2combiner(final Function1 pred, final Combiner cb) {
         this.filterNot2combiner_quick(pred, cb, this.arr(), this.until(), this.i());
         this.i_$eq(this.until());
         return cb;
      }

      private void filterNot2combiner_quick(final Function1 pred, final Builder cb, final Object[] a, final int ntil, final int from) {
         for(int j = this.i(); j < ntil; ++j) {
            Object curr = a[j];
            if (!BoxesRunTime.unboxToBoolean(pred.apply(curr))) {
               cb.$plus$eq(curr);
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         }

      }

      public Builder copy2builder(final Builder cb) {
         cb.sizeHint(this.remaining());
         this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().builder2ops(cb).ifIs((pac) -> {
            $anonfun$copy2builder$1(this, pac);
            return BoxedUnit.UNIT;
         }).otherwise((JFunction0.mcV.sp)() -> this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().builder2ops(cb).ifIs((pac) -> {
               $anonfun$copy2builder$3(this, pac);
               return BoxedUnit.UNIT;
            }).otherwise((JFunction0.mcV.sp)() -> {
               this.copy2builder_quick(cb, this.arr(), this.until(), this.i());
               this.i_$eq(this.until());
            }, scala.reflect.ClassTag..MODULE$.apply(UnrolledParArrayCombiner.class)), scala.reflect.ClassTag..MODULE$.apply(ResizableParArrayCombiner.class));
         return cb;
      }

      private void copy2builder_quick(final Builder b, final Object[] a, final int ntil, final int from) {
         for(int j = from; j < ntil; ++j) {
            b.$plus$eq(a[j]);
         }

      }

      public Tuple2 partition2combiners(final Function1 pred, final Combiner btrue, final Combiner bfalse) {
         this.partition2combiners_quick(pred, btrue, bfalse, this.arr(), this.until(), this.i());
         this.i_$eq(this.until());
         return new Tuple2(btrue, bfalse);
      }

      private void partition2combiners_quick(final Function1 p, final Builder btrue, final Builder bfalse, final Object[] a, final int ntil, final int from) {
         for(int j = from; j < ntil; ++j) {
            Object curr = a[j];
            if (BoxesRunTime.unboxToBoolean(p.apply(curr))) {
               btrue.$plus$eq(curr);
            } else {
               bfalse.$plus$eq(curr);
            }
         }

      }

      public Combiner take2combiner(final int n, final Combiner cb) {
         cb.sizeHint(n);
         int ntil = this.i() + n;
         Object[] a = this.arr();

         while(this.i() < ntil) {
            cb.$plus$eq(a[this.i()]);
            this.i_$eq(this.i() + 1);
         }

         return cb;
      }

      public Combiner drop2combiner(final int n, final Combiner cb) {
         this.drop(n);
         cb.sizeHint(this.remaining());

         while(this.i() < this.until()) {
            cb.$plus$eq(this.arr()[this.i()]);
            this.i_$eq(this.i() + 1);
         }

         return cb;
      }

      public Combiner reverse2combiner(final Combiner cb) {
         this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().builder2ops(cb).ifIs((pac) -> {
            $anonfun$reverse2combiner$1(this, pac);
            return BoxedUnit.UNIT;
         }).otherwise((JFunction0.mcV.sp)() -> this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().builder2ops(cb).ifIs((pac) -> {
               $anonfun$reverse2combiner$3(this, pac);
               return BoxedUnit.UNIT;
            }).otherwise((JFunction0.mcV.sp)() -> this.super$reverse2combiner(cb), scala.reflect.ClassTag..MODULE$.apply(UnrolledParArrayCombiner.class)), scala.reflect.ClassTag..MODULE$.apply(ResizableParArrayCombiner.class));
         return cb;
      }

      private void reverse2combiner_quick(final Object[] targ, final Object[] a, final int targfrom, final int srcfrom, final int srcuntil) {
         int j = srcfrom;

         for(int k = targfrom + srcuntil - srcfrom - 1; j < srcuntil; --k) {
            targ[k] = a[j];
            ++j;
         }

      }

      public void scanToArray(final Object z, final Function2 op, final Object destarr, final int from) {
         this.scanToArray_quick(this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().scala$collection$parallel$mutable$ParArray$$array(), destarr, op, z, this.i(), this.until(), from);
         this.i_$eq(this.until());
      }

      public void scanToArray_quick(final Object[] srcarr, final Object[] destarr, final Function2 op, final Object z, final int srcfrom, final int srcntil, final int destfrom) {
         Object last = z;
         int j = srcfrom;

         for(int k = destfrom; j < srcntil; ++k) {
            last = op.apply(last, srcarr[j]);
            destarr[k] = last;
            ++j;
         }

      }

      // $FF: synthetic method
      public ParArray scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final ParArrayIterator $anonfun$psplit$2(final ParArrayIterator $this, final IntRef traversed$1, final int sz) {
         if (traversed$1.elem < $this.until()) {
            int start = traversed$1.elem;
            int end = scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(traversed$1.elem + sz), $this.until());
            traversed$1.elem = end;
            return $this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().new ParArrayIterator(start, end, $this.arr());
         } else {
            return $this.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().new ParArrayIterator(traversed$1.elem, traversed$1.elem, $this.arr());
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$copy2builder$1(final ParArrayIterator $this, final ResizableParArrayCombiner pac) {
         Object[] targetarr = ((ExposedArrayBuffer)pac.lastbuff()).internalArray();
         scala.Array..MODULE$.copy($this.arr(), $this.i(), targetarr, ((SeqOps)pac.lastbuff()).size(), $this.until() - $this.i());
         ((ExposedArrayBuffer)pac.lastbuff()).setInternalSize($this.remaining());
      }

      // $FF: synthetic method
      public static final void $anonfun$copy2builder$3(final ParArrayIterator $this, final UnrolledParArrayCombiner pac) {
         Object[] targetarr = pac.buff().lastPtr().array();
         scala.Array..MODULE$.copy($this.arr(), $this.i(), targetarr, 0, $this.until() - $this.i());
         pac.buff().size_$eq(pac.buff().size() + $this.until() - $this.i());
         pac.buff().lastPtr().size_$eq($this.until() - $this.i());
      }

      // $FF: synthetic method
      public static final void $anonfun$reverse2combiner$1(final ParArrayIterator $this, final ResizableParArrayCombiner pac) {
         int sz = $this.remaining();
         pac.sizeHint(sz);
         Object[] targetarr = ((ExposedArrayBuffer)pac.lastbuff()).internalArray();
         $this.reverse2combiner_quick(targetarr, $this.arr(), 0, $this.i(), $this.until());
         ((ExposedArrayBuffer)pac.lastbuff()).setInternalSize(sz);
      }

      // $FF: synthetic method
      public static final void $anonfun$reverse2combiner$3(final ParArrayIterator $this, final UnrolledParArrayCombiner pac) {
         int sz = $this.remaining();
         pac.sizeHint(sz);
         Object[] targetarr = pac.buff().lastPtr().array();
         $this.reverse2combiner_quick(targetarr, $this.arr(), 0, $this.i(), $this.until());
         pac.buff().size_$eq(pac.buff().size() + sz);
         pac.buff().lastPtr().size_$eq(sz);
      }

      public ParArrayIterator(final int i, final int until, final Object[] arr) {
         this.i = i;
         this.until = until;
         this.arr = arr;
         if (ParArray.this == null) {
            throw null;
         } else {
            this.$outer = ParArray.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            RemainsIterator.$init$(this);
            AugmentedIterableIterator.$init$(this);
            DelegatedSignalling.$init$(this);
            IterableSplitter.$init$(this);
            AugmentedSeqIterator.$init$(this);
            SeqSplitter.$init$(this);
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class ParArrayIterator$ {
      // $FF: synthetic field
      private final ParArray $outer;

      public int $lessinit$greater$default$1() {
         return 0;
      }

      public int $lessinit$greater$default$2() {
         return this.$outer.length();
      }

      public Object[] $lessinit$greater$default$3() {
         return this.$outer.scala$collection$parallel$mutable$ParArray$$array();
      }

      public ParArrayIterator$() {
         if (ParArray.this == null) {
            throw null;
         } else {
            this.$outer = ParArray.this;
            super();
         }
      }
   }

   public class ScanToArray implements Task {
      private final ParIterableLike.ScanTree tree;
      private final Object z;
      private final Function2 op;
      private final Object[] targetarr;
      private BoxedUnit result;
      private volatile Throwable throwable;
      // $FF: synthetic field
      public final ParArray $outer;

      public Object repr() {
         return Task.repr$(this);
      }

      public void merge(final Object that) {
         Task.merge$(this, that);
      }

      public void forwardThrowable() {
         Task.forwardThrowable$(this);
      }

      public void tryLeaf(final Option lastres) {
         Task.tryLeaf$(this, lastres);
      }

      public void tryMerge(final Object t) {
         Task.tryMerge$(this, t);
      }

      public void mergeThrowables(final Task that) {
         Task.mergeThrowables$(this, that);
      }

      public void signalAbort() {
         Task.signalAbort$(this);
      }

      public Throwable throwable() {
         return this.throwable;
      }

      public void throwable_$eq(final Throwable x$1) {
         this.throwable = x$1;
      }

      public void result() {
         BoxedUnit var10000 = this.result;
      }

      public void result_$eq(final BoxedUnit x$1) {
         this.result = x$1;
      }

      public void leaf(final Option prev) {
         this.iterate(this.tree);
      }

      private void iterate(final ParIterableLike.ScanTree tree) {
         while(true) {
            boolean var4 = false;
            ParIterableLike.ScanLeaf var5 = null;
            if (!(tree instanceof ParIterableLike.ScanNode)) {
               label35: {
                  if (tree instanceof ParIterableLike.ScanLeaf) {
                     var4 = true;
                     var5 = (ParIterableLike.ScanLeaf)tree;
                     int from = var5.from();
                     int len = var5.len();
                     Option var12 = var5.prev();
                     if (var12 instanceof Some) {
                        Some var13 = (Some)var12;
                        ParIterableLike.ScanLeaf prev = (ParIterableLike.ScanLeaf)var13.value();
                        this.scanLeaf(this.scala$collection$parallel$mutable$ParArray$ScanToArray$$$outer().scala$collection$parallel$mutable$ParArray$$array(), this.targetarr, from, len, prev.acc());
                        BoxedUnit var18 = BoxedUnit.UNIT;
                        break label35;
                     }
                  }

                  if (!var4) {
                     throw new MatchError(tree);
                  }

                  int from = var5.from();
                  int len = var5.len();
                  Option var17 = var5.prev();
                  if (!scala.None..MODULE$.equals(var17)) {
                     throw new MatchError(tree);
                  }

                  this.scanLeaf(this.scala$collection$parallel$mutable$ParArray$ScanToArray$$$outer().scala$collection$parallel$mutable$ParArray$$array(), this.targetarr, from, len, this.z);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               BoxedUnit var19 = BoxedUnit.UNIT;
               return;
            }

            ParIterableLike.ScanNode var7 = (ParIterableLike.ScanNode)tree;
            ParIterableLike.ScanTree left = var7.left();
            ParIterableLike.ScanTree right = var7.right();
            this.iterate(left);
            tree = right;
         }
      }

      private void scanLeaf(final Object[] srcarr, final Object[] targetarr, final int from, final int len, final Object startval) {
         int i = from;
         int until = from + len;
         Object curr = startval;

         for(Function2 operation = this.op; i < until; targetarr[i] = curr) {
            curr = operation.apply(curr, srcarr[i]);
            ++i;
         }

      }

      public Seq split() {
         ParIterableLike.ScanTree var2 = this.tree;
         if (var2 instanceof ParIterableLike.ScanNode) {
            ParIterableLike.ScanNode var3 = (ParIterableLike.ScanNode)var2;
            ParIterableLike.ScanTree left = var3.left();
            ParIterableLike.ScanTree right = var3.right();
            return new scala.collection.immutable..colon.colon(this.scala$collection$parallel$mutable$ParArray$ScanToArray$$$outer().new ScanToArray(left, this.z, this.op, this.targetarr), new scala.collection.immutable..colon.colon(this.scala$collection$parallel$mutable$ParArray$ScanToArray$$$outer().new ScanToArray(right, this.z, this.op, this.targetarr), scala.collection.immutable.Nil..MODULE$));
         } else {
            throw scala.sys.package..MODULE$.error("Can only split scan tree internal nodes.");
         }
      }

      public boolean shouldSplitFurther() {
         ParIterableLike.ScanTree var2 = this.tree;
         return var2 instanceof ParIterableLike.ScanNode;
      }

      // $FF: synthetic method
      public ParArray scala$collection$parallel$mutable$ParArray$ScanToArray$$$outer() {
         return this.$outer;
      }

      public ScanToArray(final ParIterableLike.ScanTree tree, final Object z, final Function2 op, final Object[] targetarr) {
         this.tree = tree;
         this.z = z;
         this.op = op;
         this.targetarr = targetarr;
         if (ParArray.this == null) {
            throw null;
         } else {
            this.$outer = ParArray.this;
            super();
            Task.$init$(this);
            this.result = BoxedUnit.UNIT;
         }
      }
   }

   public class ParArrayMap implements Task {
      private final Function1 f;
      private final Object[] targetarr;
      private final int offset;
      private final int howmany;
      private BoxedUnit result;
      private volatile Throwable throwable;
      // $FF: synthetic field
      public final ParArray $outer;

      public Object repr() {
         return Task.repr$(this);
      }

      public void merge(final Object that) {
         Task.merge$(this, that);
      }

      public void forwardThrowable() {
         Task.forwardThrowable$(this);
      }

      public void tryLeaf(final Option lastres) {
         Task.tryLeaf$(this, lastres);
      }

      public void tryMerge(final Object t) {
         Task.tryMerge$(this, t);
      }

      public void mergeThrowables(final Task that) {
         Task.mergeThrowables$(this, that);
      }

      public void signalAbort() {
         Task.signalAbort$(this);
      }

      public Throwable throwable() {
         return this.throwable;
      }

      public void throwable_$eq(final Throwable x$1) {
         this.throwable = x$1;
      }

      public void result() {
         BoxedUnit var10000 = this.result;
      }

      public void result_$eq(final BoxedUnit x$1) {
         this.result = x$1;
      }

      public void leaf(final Option prev) {
         Object[] tarr = this.targetarr;
         Object[] sarr = this.scala$collection$parallel$mutable$ParArray$ParArrayMap$$$outer().scala$collection$parallel$mutable$ParArray$$array();
         int i = this.offset;

         for(int until = this.offset + this.howmany; i < until; ++i) {
            tarr[i] = this.f.apply(sarr[i]);
         }

      }

      public List split() {
         int fp = this.howmany / 2;
         return new scala.collection.immutable..colon.colon(this.scala$collection$parallel$mutable$ParArray$ParArrayMap$$$outer().new ParArrayMap(this.f, this.targetarr, this.offset, fp), new scala.collection.immutable..colon.colon(this.scala$collection$parallel$mutable$ParArray$ParArrayMap$$$outer().new ParArrayMap(this.f, this.targetarr, this.offset + fp, this.howmany - fp), scala.collection.immutable.Nil..MODULE$));
      }

      public boolean shouldSplitFurther() {
         return this.howmany > scala.collection.parallel.package$.MODULE$.thresholdFromSize(this.scala$collection$parallel$mutable$ParArray$ParArrayMap$$$outer().length(), this.scala$collection$parallel$mutable$ParArray$ParArrayMap$$$outer().tasksupport().parallelismLevel());
      }

      // $FF: synthetic method
      public ParArray scala$collection$parallel$mutable$ParArray$ParArrayMap$$$outer() {
         return this.$outer;
      }

      public ParArrayMap(final Function1 f, final Object[] targetarr, final int offset, final int howmany) {
         this.f = f;
         this.targetarr = targetarr;
         this.offset = offset;
         this.howmany = howmany;
         if (ParArray.this == null) {
            throw null;
         } else {
            this.$outer = ParArray.this;
            super();
            Task.$init$(this);
            this.result = BoxedUnit.UNIT;
         }
      }
   }
}
