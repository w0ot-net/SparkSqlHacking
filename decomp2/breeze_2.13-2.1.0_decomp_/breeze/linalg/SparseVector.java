package breeze.linalg;

import breeze.collection.mutable.SparseArray;
import breeze.generic.UFunc;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTransformValues;
import breeze.linalg.support.CanTranspose;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.TensorActive;
import breeze.linalg.support.TensorKeys;
import breeze.linalg.support.TensorPairs;
import breeze.linalg.support.TensorValues;
import breeze.math.Field;
import breeze.math.MutableFiniteCoordinateField;
import breeze.storage.Storage;
import breeze.storage.Zero;
import breeze.util.ArrayUtil$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015\u001dc\u0001\u0002 @\u0001\u0011C!\"a\u0002\u0001\u0005\u000b\u0007I\u0011AA\u0005\u0011)\tY\u0002\u0001B\u0001B\u0003%\u00111\u0002\u0005\u000b\u0003;\u0001!\u0011!Q\u0001\f\u0005}\u0001bBA\u0016\u0001\u0011\u0005\u0011Q\u0006\u0005\b\u0003W\u0001A\u0011AA\u001b\u0011\u001d\tY\u0003\u0001C\u0001\u00037Bq!a\u0014\u0001\t\u0003\t9\u0007C\u0004\u0002@\u0001!\t!!\u001b\t\u000f\u0005U\u0003\u0001\"\u0001\u0002l!9\u0011Q\u000e\u0001\u0005\u0002\u0005-\u0004bBA-\u0001\u0011\u0005\u00111\u000e\u0005\b\u0003_\u0002A\u0011AA9\u0011\u001d\t\u0019\b\u0001C\u0001\u0003kBq!!!\u0001\t\u0003\t\u0019\tC\u0004\u0002\b\u0002!\t!!#\t\u000f\u0005]\u0005\u0001\"\u0002\u0002\u001a\"9\u0011Q\u0014\u0001\u0005\u0002\u0005}\u0005bBAW\u0001\u0011\u0005\u0011q\u0016\u0005\b\u0003g\u0003A\u0011AA[\u0011\u001d\tI\f\u0001C\u0001\u0003wCq!!0\u0001\t\u0003\ny\fC\u0004\u0002F\u0002!\t%a2\t\u000f\u0005%\u0007\u0001\"\u0001\u0002L\"9\u0011\u0011\u001b\u0001\u0005B\u0005M\u0007bBAs\u0001\u0011\u0005\u0011\u0011\u000f\u0005\b\u0003O\u0004A\u0011AAu\u0011\u001d\ty\u000f\u0001C\u0001\u0003cDq!a=\u0001\t\u0003\t)\u0010C\u0004\u0002~\u0002!\t!a@\t\u000f\t\r\u0001\u0001\"\u0001\u0003\u0006!9!\u0011\u0002\u0001\u0005\u0002\t-\u0001b\u0002B\u0007\u0001\u0011\u0005!q\u0002\u0005\b\u0005O\u0001A\u0011\u0001B\u0015\u000f\u001d\u00119d\u0010E\u0001\u0005s1aAP \t\u0002\tm\u0002bBA\u0016G\u0011\u0005!1\n\u0005\b\u0005\u001b\u001aC\u0011\u0001B(\u0011\u001d\t\ti\tC\u0001\u0005{Bq!!!$\t\u0003\u00119\u000bC\u0004\u0003H\u000e\"\tA!3\t\u000f\t}8\u0005\"\u0001\u0004\u0002!9\u0011\u0011Q\u0012\u0005\u0002\re\u0002bBB6G\u0011\u00051Q\u000e\u0005\b\u0007\u0017\u001bC\u0011ABG\r\u0019\u0019Yk\t\u0001\u0004.\"Q1Q[\u0017\u0003\u0004\u0003\u0006Yaa6\t\u0015\reWFaA!\u0002\u0017\u0019Y\u000eC\u0004\u0002,5\"\ta!8\t\u000f\u0005\u0005U\u0006\"\u0001\u0004j\"91q^\u0012\u0005\u0004\rE\bb\u0002C\rG\u0011\rA1\u0004\u0005\b\t\u007f\u0019C1\u0001C!\u0011\u001d!\tf\tC\u0002\t'Bq\u0001b\u001c$\t\u0007!\t\bC\u0004\u0005\u000e\u000e\"\u0019\u0001b$\t\u000f\u0011-6\u0005b\u0001\u0005.\"9AqZ\u0012\u0005\u0004\u0011E\u0007b\u0002CyG\u0011\rA1\u001f\u0005\b\u000b3\u0019C1AC\u000e\u0011\u001d)ic\tC\u0005\u0003cD\u0011\"b\u000e$\u0003\u0003%I!\"\u000f\u0003\u0019M\u0003\u0018M]:f-\u0016\u001cGo\u001c:\u000b\u0005\u0001\u000b\u0015A\u00027j]\u0006dwMC\u0001C\u0003\u0019\u0011'/Z3{K\u000e\u0001QCA#S'\u0015\u0001a\t\u0014=}!\t9%*D\u0001I\u0015\u0005I\u0015!B:dC2\f\u0017BA&I\u0005\u0019\te.\u001f*fMB\u0019QJ\u0014)\u000e\u0003}J!aT \u0003\u001bM#xN]1hKZ+7\r^8s!\t\t&\u000b\u0004\u0001\u0005\u0013M\u0003\u0001\u0015!A\u0001\u0006\u0004!&!\u0001,\u0012\u0005UC\u0006CA$W\u0013\t9\u0006JA\u0004O_RD\u0017N\\4\u0011\u0005\u001dK\u0016B\u0001.I\u0005\r\te.\u001f\u0015\u0007%r{\u0016N\\:\u0011\u0005\u001dk\u0016B\u00010I\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\r\u0002\u0017m\u00192\u000f\u0005\u001d\u000b\u0017B\u00012I\u0003\u0019!u.\u001e2mKF\"A\u0005\u001a5J\u001d\t)\u0007.D\u0001g\u0015\t97)\u0001\u0004=e>|GOP\u0005\u0002\u0013F*1E[6nY:\u0011qi[\u0005\u0003Y\"\u000b1!\u00138uc\u0011!C\r[%2\u000b\rz\u0007O]9\u000f\u0005\u001d\u0003\u0018BA9I\u0003\u00151En\\1uc\u0011!C\r[%2\u000b\r\"Xo\u001e<\u000f\u0005\u001d+\u0018B\u0001<I\u0003\u0011auN\\42\t\u0011\"\u0007.\u0013\t\u0005\u001bf\u000460\u0003\u0002{\u007f\tQa+Z2u_Jd\u0015n[3\u0011\u00075\u0003\u0001\u000bE\u0002~\u0003\u0003q!\u0001\u001a@\n\u0005}D\u0015a\u00029bG.\fw-Z\u0005\u0005\u0003\u0007\t)A\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002\u0000\u0011\u0006)\u0011M\u001d:bsV\u0011\u00111\u0002\t\u0006\u0003\u001b\t9\u0002U\u0007\u0003\u0003\u001fQA!!\u0005\u0002\u0014\u00059Q.\u001e;bE2,'bAA\u000b\u0003\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005e\u0011q\u0002\u0002\f'B\f'o]3BeJ\f\u00170\u0001\u0004beJ\f\u0017\u0010I\u0001\u0005u\u0016\u0014x\u000eE\u0003\u0002\"\u0005\u001d\u0002+\u0004\u0002\u0002$)\u0019\u0011QE!\u0002\u000fM$xN]1hK&!\u0011\u0011FA\u0012\u0005\u0011QVM]8\u0002\rqJg.\u001b;?)\u0011\ty#a\r\u0015\u0007m\f\t\u0004C\u0004\u0002\u001e\u0011\u0001\u001d!a\b\t\u000f\u0005\u001dA\u00011\u0001\u0002\fQQ\u0011qGA\u001f\u0003\u001b\n\u0019&a\u0016\u0015\u0007m\fI\u0004C\u0004\u0002<\u0015\u0001\u001d!a\b\u0002\u000bY\fG.^3\t\u000f\u0005}R\u00011\u0001\u0002B\u0005)\u0011N\u001c3fqB)q)a\u0011\u0002H%\u0019\u0011Q\t%\u0003\u000b\u0005\u0013(/Y=\u0011\u0007\u001d\u000bI%C\u0002\u0002L!\u00131!\u00138u\u0011\u001d\ty%\u0002a\u0001\u0003#\nA\u0001Z1uCB!q)a\u0011Q\u0011\u001d\t)&\u0002a\u0001\u0003\u000f\n!\"Y2uSZ,7+\u001b>f\u0011\u001d\tI&\u0002a\u0001\u0003\u000f\na\u0001\\3oORDG\u0003CA/\u0003C\n\u0019'!\u001a\u0015\u0007m\fy\u0006C\u0004\u0002<\u0019\u0001\u001d!a\b\t\u000f\u0005}b\u00011\u0001\u0002B!9\u0011q\n\u0004A\u0002\u0005E\u0003bBA-\r\u0001\u0007\u0011qI\u000b\u0003\u0003#*\"!!\u0011\u0016\u0005\u0005\u001d\u0013\u0001B;tK\u0012\fAA]3qeV\t10\u0001\u0005d_:$\u0018-\u001b8t)\u0011\t9(! \u0011\u0007\u001d\u000bI(C\u0002\u0002|!\u0013qAQ8pY\u0016\fg\u000eC\u0004\u0002\u00005\u0001\r!a\u0012\u0002\u0003%\fQ!\u00199qYf$2\u0001UAC\u0011\u001d\tyH\u0004a\u0001\u0003\u000f\na!\u001e9eCR,GCBAF\u0003#\u000b\u0019\nE\u0002H\u0003\u001bK1!a$I\u0005\u0011)f.\u001b;\t\u000f\u0005}t\u00021\u0001\u0002H!1\u0011QS\bA\u0002A\u000b\u0011A^\u0001\u000b_RDWM]!qa2LHc\u0001)\u0002\u001c\"9\u0011q\u0010\tA\u0002\u0005\u001d\u0013AD1di&4X-\u0013;fe\u0006$xN]\u000b\u0003\u0003C\u0003R!`AR\u0003OKA!!*\u0002\u0006\tA\u0011\n^3sCR|'\u000f\u0005\u0004H\u0003S\u000b9\u0005U\u0005\u0004\u0003WC%A\u0002+va2,''\u0001\u000bbGRLg/\u001a,bYV,7/\u0013;fe\u0006$xN]\u000b\u0003\u0003c\u0003B!`AR!\u0006\u0011\u0012m\u0019;jm\u0016\\U-_:Ji\u0016\u0014\u0018\r^8s+\t\t9\fE\u0003~\u0003G\u000b9%A\u0004eK\u001a\fW\u000f\u001c;\u0016\u0003A\u000ba!Z9vC2\u001cH\u0003BA<\u0003\u0003Da!a1\u0016\u0001\u0004A\u0016!B8uQ\u0016\u0014\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005\u001d\u0013\u0001C5t\u0003\u000e$\u0018N^3\u0015\t\u0005]\u0014Q\u001a\u0005\b\u0003\u001f<\u0002\u0019AA$\u0003!\u0011\u0018m^%oI\u0016D\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005U\u0007\u0003BAl\u0003?tA!!7\u0002\\B\u0011Q\rS\u0005\u0004\u0003;D\u0015A\u0002)sK\u0012,g-\u0003\u0003\u0002b\u0006\r(AB*ue&twMC\u0002\u0002^\"\u000bAaY8qs\u00069!/Z:feZ,G\u0003BAF\u0003WDq!!<\u001b\u0001\u0004\t9%A\u0002o]j\fqaY8na\u0006\u001cG\u000f\u0006\u0002\u0002\f\u0006\u0019Qo]3\u0015\u0011\u0005-\u0015q_A}\u0003wDq!a\u0010\u001d\u0001\u0004\t\t\u0005C\u0004\u0002Pq\u0001\r!!\u0015\t\u000f\u0005UC\u00041\u0001\u0002H\u00059a/\u00197vK\u0006#Hc\u0001)\u0003\u0002!9\u0011qP\u000fA\u0002\u0005\u001d\u0013aB5oI\u0016D\u0018\t\u001e\u000b\u0005\u0003\u000f\u00129\u0001C\u0004\u0002\u0000y\u0001\r!a\u0012\u00023\u0005dGNV5tSR\f'\r\\3J]\u0012L7-Z:BGRLg/Z\u000b\u0003\u0003o\n\u0001\"Y:Dg\u000e\u0014vn\u001e\u000b\u0005\u0005#\u00119\u0002\u0005\u0003N\u0005'\u0001\u0016b\u0001B\u000b\u007f\tI1iU\"NCR\u0014\u0018\u000e\u001f\u0005\b\u00053\u0001\u00039\u0001B\u000e\u0003\ri\u0017M\u001c\t\u0006\u0005;\u0011\u0019\u0003U\u0007\u0003\u0005?Q1A!\tI\u0003\u001d\u0011XM\u001a7fGRLAA!\n\u0003 \tA1\t\\1tgR\u000bw-A\u0006bg\u000e\u001b8mQ8mk6tG\u0003\u0002B\t\u0005WAqA!\u0007\"\u0001\b\u0011Y\u0002K\u0004\u0001\u0005_\tYD!\u000e\u0011\u0007\u001d\u0013\t$C\u0002\u00034!\u0013\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u001f\u0003\u0005\tAb\u00159beN,g+Z2u_J\u0004\"!T\u0012\u0014\t\r2%Q\b\t\u0005\u0005\u007f\u0011I%\u0004\u0002\u0003B)!!1\tB#\u0003\tIwN\u0003\u0002\u0003H\u0005!!.\u0019<b\u0013\u0011\t\u0019A!\u0011\u0015\u0005\te\u0012!\u0002>fe>\u001cX\u0003\u0002B)\u00053\"BAa\u0015\u0003zQ1!Q\u000bB7\u0005g\u0002B!\u0014\u0001\u0003XA\u0019\u0011K!\u0017\u0005\u0013M+\u0003\u0015!A\u0001\u0006\u0004!\u0006f\u0003B-9\nu#\u0011\rB3\u0005S\nda\t1b\u0005?\u0012\u0017\u0007\u0002\u0013eQ&\u000bda\t6l\u0005Gb\u0017\u0007\u0002\u0013eQ&\u000bdaI8q\u0005O\n\u0018\u0007\u0002\u0013eQ&\u000bda\t;v\u0005W2\u0018\u0007\u0002\u0013eQ&C\u0011Ba\u001c&\u0003\u0003\u0005\u001dA!\u001d\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0004\u0003\u001e\t\r\"q\u000b\u0005\n\u0005k*\u0013\u0011!a\u0002\u0005o\n!\"\u001a<jI\u0016t7-\u001a\u00133!\u0019\t\t#a\n\u0003X!9!1P\u0013A\u0002\u0005\u001d\u0013\u0001B:ju\u0016,BAa \u0003\bR!!\u0011\u0011BQ)\u0011\u0011\u0019Ia'\u0011\t5\u0003!Q\u0011\t\u0004#\n\u001dE!C*'A\u0003\u0005\tQ1\u0001UQ-\u00119\t\u0018BF\u0005\u001f\u0013\u0019Ja&2\r\r\u0002\u0017M!$cc\u0011!C\r[%2\r\rR7N!%mc\u0011!C\r[%2\r\rz\u0007O!&rc\u0011!C\r[%2\r\r\"XO!'wc\u0011!C\r[%\t\u0013\tue%!AA\u0004\t}\u0015AC3wS\u0012,gnY3%gA1\u0011\u0011EA\u0014\u0005\u000bCqAa)'\u0001\u0004\u0011)+\u0001\u0004wC2,Xm\u001d\t\u0006\u000f\u0006\r#QQ\u000b\u0005\u0005S\u0013\t\f\u0006\u0003\u0003,\n}FC\u0002BW\u0005g\u0013I\f\u0005\u0003N\u0001\t=\u0006cA)\u00032\u0012)1k\nb\u0001)\"I!QW\u0014\u0002\u0002\u0003\u000f!qW\u0001\u000bKZLG-\u001a8dK\u0012\"\u0004C\u0002B\u000f\u0005G\u0011y\u000bC\u0005\u0003<\u001e\n\t\u0011q\u0001\u0003>\u0006QQM^5eK:\u001cW\rJ\u001b\u0011\r\u0005\u0005\u0012q\u0005BX\u0011\u001d\u0011\u0019k\na\u0001\u0005\u0003\u0004Ra\u0012Bb\u0005_K1A!2I\u0005)a$/\u001a9fCR,GMP\u0001\u0005M&dG.\u0006\u0003\u0003L\nUG\u0003\u0002Bg\u0005{$BAa4\u0003vR1!\u0011\u001bBu\u0005_\u0004B!\u0014\u0001\u0003TB\u0019\u0011K!6\u0005\u0013MC\u0003\u0015!A\u0001\u0006\u0004!\u0006f\u0003Bk9\ne'Q\u001cBq\u0005K\fda\t1b\u00057\u0014\u0017\u0007\u0002\u0013eQ&\u000bda\t6l\u0005?d\u0017\u0007\u0002\u0013eQ&\u000bdaI8q\u0005G\f\u0018\u0007\u0002\u0013eQ&\u000bda\t;v\u0005O4\u0018\u0007\u0002\u0013eQ&C\u0011Ba;)\u0003\u0003\u0005\u001dA!<\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$c\u0007\u0005\u0004\u0003\u001e\t\r\"1\u001b\u0005\n\u0005cD\u0013\u0011!a\u0002\u0005g\f!\"\u001a<jI\u0016t7-\u001a\u00138!\u0019\t\t#a\n\u0003T\"A\u0011Q\u0013\u0015\u0005\u0002\u0004\u00119\u0010E\u0003H\u0005s\u0014\u0019.C\u0002\u0003|\"\u0013\u0001\u0002\u00102z]\u0006lWM\u0010\u0005\b\u0005wB\u0003\u0019AA$\u0003!!\u0018MY;mCR,W\u0003BB\u0002\u0007\u001b!Ba!\u0002\u00048Q!1qAB\u0017)\u0019\u0019Ia!\t\u0004(A!Q\nAB\u0006!\r\t6Q\u0002\u0003\n'&\u0002\u000b\u0011!AC\u0002QC3b!\u0004]\u0007#\u0019)b!\u0007\u0004\u001eE21\u0005Y1\u0004\u0014\t\fD\u0001\n3i\u0013F21E[6\u0004\u00181\fD\u0001\n3i\u0013F21e\u001c9\u0004\u001cE\fD\u0001\n3i\u0013F21\u0005^;\u0004 Y\fD\u0001\n3i\u0013\"I11E\u0015\u0002\u0002\u0003\u000f1QE\u0001\u000bKZLG-\u001a8dK\u0012B\u0004C\u0002B\u000f\u0005G\u0019Y\u0001C\u0005\u0004*%\n\t\u0011q\u0001\u0004,\u0005QQM^5eK:\u001cW\rJ\u001d\u0011\r\u0005\u0005\u0012qEB\u0006\u0011\u001d\u0019y#\u000ba\u0001\u0007c\t\u0011A\u001a\t\b\u000f\u000eM\u0012qIB\u0006\u0013\r\u0019)\u0004\u0013\u0002\n\rVt7\r^5p]FBqAa\u001f*\u0001\u0004\t9%\u0006\u0003\u0004<\r\u0015C\u0003BB\u001f\u0007S\"Baa\u0010\u0004dQA1\u0011IB$\u0007\u001b\u001a\u0019\u0006\u0005\u0003N\u0001\r\r\u0003cA)\u0004F\u0011)1K\u000bb\u0001)\"I1\u0011\n\u0016\u0002\u0002\u0003\u000f11J\u0001\fKZLG-\u001a8dK\u0012\n\u0004\u0007\u0005\u0004\u0003\u001e\t\r21\t\u0005\n\u0007\u001fR\u0013\u0011!a\u0002\u0007#\n1\"\u001a<jI\u0016t7-\u001a\u00132cA1\u0011\u0011EA\u0014\u0007\u0007B\u0011b!\u0016+\u0003\u0003\u0005\u001daa\u0016\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013G\r\t\u0007\u00073\u001ayfa\u0011\u000e\u0005\rm#bAB/\u0003\u0006!Q.\u0019;i\u0013\u0011\u0019\tga\u0017\u0003\u0011M+W.\u001b:j]\u001eDqAa)+\u0001\u0004\u0019)\u0007E\u0003H\u0005\u0007\u001c9\u0007E\u0004H\u0003S\u000b9ea\u0011\t\u000f\u0005e#\u00061\u0001\u0002H\u00059a/\u001a:uG\u0006$X\u0003BB8\u0007o\"Ba!\u001d\u0004\u0006R111OB=\u0007\u007f\u0002B!\u0014\u0001\u0004vA\u0019\u0011ka\u001e\u0005\u000bM[#\u0019\u0001+\t\u0013\rm4&!AA\u0004\ru\u0014aC3wS\u0012,gnY3%cM\u0002b!!\t\u0002(\rU\u0004\"CBAW\u0005\u0005\t9ABB\u0003-)g/\u001b3f]\u000e,G%\r\u001b\u0011\r\tu!1EB;\u0011\u001d\u00199i\u000ba\u0001\u0007\u0013\u000bqA^3di>\u00148\u000fE\u0003H\u0005\u0007\u001c\u0019(A\u0004i_JT8-\u0019;\u0016\t\r=5q\u0013\u000b\u0005\u0007#\u001b)\u000b\u0006\u0004\u0004\u0014\u000ee5q\u0014\t\u0006\u001b\nM1Q\u0013\t\u0004#\u000e]E!B*-\u0005\u0004!\u0006\"CBNY\u0005\u0005\t9ABO\u0003-)g/\u001b3f]\u000e,G%M\u001b\u0011\r\u0005\u0005\u0012qEBK\u0011%\u0019\t\u000bLA\u0001\u0002\b\u0019\u0019+A\u0006fm&$WM\\2fIE2\u0004C\u0002B\u000f\u0005G\u0019)\nC\u0004\u0004\b2\u0002\raa*\u0011\u000b\u001d\u0013\u0019m!+\u0011\t5\u00031Q\u0013\u0002\u0014\u0007\u0006t7i\u001c9z'B\f'o]3WK\u000e$xN]\u000b\u0005\u0007_\u001b\tm\u0005\u0003.\r\u000eE\u0006CBBZ\u0007s\u001bi,\u0004\u0002\u00046*\u00191qW \u0002\u000fM,\b\u000f]8si&!11XB[\u0005\u001d\u0019\u0015M\\\"paf\u0004B!\u0014\u0001\u0004@B\u0019\u0011k!1\u0005\u0013Mk\u0003\u0015!A\u0001\u0006\u0004!\u0006fCBa9\u000e\u00157\u0011ZBg\u0007#\fda\t1b\u0007\u000f\u0014\u0017\u0007\u0002\u0013eQ&\u000bda\t6l\u0007\u0017d\u0017\u0007\u0002\u0013eQ&\u000bdaI8q\u0007\u001f\f\u0018\u0007\u0002\u0013eQ&\u000bda\t;v\u0007'4\u0018\u0007\u0002\u0013eQ&\u000b1\"\u001a<jI\u0016t7-\u001a\u00132oA1!Q\u0004B\u0012\u0007\u007f\u000b1\"\u001a<jI\u0016t7-\u001a\u00132qA1\u0011\u0011EA\u0014\u0007\u007f#\"aa8\u0015\r\r\u00058Q]Bt!\u0015\u0019\u0019/LB`\u001b\u0005\u0019\u0003bBBka\u0001\u000f1q\u001b\u0005\b\u00073\u0004\u00049ABn)\u0011\u0019ila;\t\u000f\r5\u0018\u00071\u0001\u0004>\u0006\u0011a/M\u0001\u000eG\u0006t7i\u001c9z'B\f'o]3\u0016\t\rM8\u0011 \u000b\u0007\u0007k$i\u0001b\u0005\u0011\u000b\r\rXfa>\u0011\u0007E\u001bI\u0010B\u0005Te\u0001\u0006\t\u0011!b\u0001)\"Z1\u0011 /\u0004~\u0012\u0005AQ\u0001C\u0005c\u0019\u0019\u0003-YB\u0000EF\"A\u0005\u001a5Jc\u0019\u0019#n\u001bC\u0002YF\"A\u0005\u001a5Jc\u0019\u0019s\u000e\u001dC\u0004cF\"A\u0005\u001a5Jc\u0019\u0019C/\u001eC\u0006mF\"A\u0005\u001a5J\u0011%!yAMA\u0001\u0002\b!\t\"A\u0006fm&$WM\\2fIEJ\u0004C\u0002B\u000f\u0005G\u00199\u0010C\u0005\u0005\u0016I\n\t\u0011q\u0001\u0005\u0018\u0005YQM^5eK:\u001cW\r\n\u001a1!\u0019\t\t#a\n\u0004x\u0006a1-\u00198NCB4\u0016\r\\;fgV1AQ\u0004C\u0015\t[!b\u0001b\b\u00054\u0011e\u0002\u0003DBZ\tC!)\u0003b\n\u0005,\u0011E\u0012\u0002\u0002C\u0012\u0007k\u0013AbQ1o\u001b\u0006\u0004h+\u00197vKN\u0004B!\u0014\u0001\u0005(A\u0019\u0011\u000b\"\u000b\u0005\u000bM\u001b$\u0019\u0001+\u0011\u0007E#i\u0003\u0002\u0004\u00050M\u0012\r\u0001\u0016\u0002\u0003-J\u0002B!\u0014\u0001\u0005,!IAQG\u001a\u0002\u0002\u0003\u000fAqG\u0001\fKZLG-\u001a8dK\u0012\u0012\u0014\u0007\u0005\u0004\u0003\u001e\t\rB1\u0006\u0005\n\tw\u0019\u0014\u0011!a\u0002\t{\t1\"\u001a<jI\u0016t7-\u001a\u00133eA1\u0011\u0011EA\u0014\tW\t\u0001dY1o)J\fg/\u001a:tK.+\u0017PV1mk\u0016\u0004\u0016-\u001b:t+\u0011!\u0019\u0005b\u0014\u0016\u0005\u0011\u0015\u0003CCBZ\t\u000f\"Y%a\u0012\u0005N%!A\u0011JB[\u0005a\u0019\u0015M\u001c+sCZ,'o]3LKf4\u0016\r\\;f!\u0006L'o\u001d\t\u0005\u001b\u0002!i\u0005E\u0002R\t\u001f\"Qa\u0015\u001bC\u0002Q\u000babY1o\u0007J,\u0017\r^3[KJ|7/\u0006\u0003\u0005V\u0011\u0005DC\u0002C,\tG\"I\u0007\u0005\u0005\u00044\u0012eCQLA$\u0013\u0011!Yf!.\u0003\u001d\r\u000bgn\u0011:fCR,',\u001a:pgB!Q\n\u0001C0!\r\tF\u0011\r\u0003\u0006'V\u0012\r\u0001\u0016\u0005\n\tK*\u0014\u0011!a\u0002\tO\n1\"\u001a<jI\u0016t7-\u001a\u00133gA1!Q\u0004B\u0012\t?B\u0011\u0002b\u001b6\u0003\u0003\u0005\u001d\u0001\"\u001c\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#\u0007\u000e\t\u0007\u0003C\t9\u0003b\u0018\u0002%\r\fgn\u0011:fCR,',\u001a:pg2K7.Z\u000b\u0005\tg\"y\b\u0006\u0004\u0005v\u0011\u0005Eq\u0011\t\t\u0007g#9\bb\u001f\u0005|%!A\u0011PB[\u0005I\u0019\u0015M\\\"sK\u0006$XMW3s_Nd\u0015n[3\u0011\t5\u0003AQ\u0010\t\u0004#\u0012}D!B*7\u0005\u0004!\u0006\"\u0003CBm\u0005\u0005\t9\u0001CC\u0003-)g/\u001b3f]\u000e,GEM\u001b\u0011\r\tu!1\u0005C?\u0011%!IINA\u0001\u0002\b!Y)A\u0006fm&$WM\\2fII2\u0004CBA\u0011\u0003O!i(\u0001\ndC:$&/\u00198tM>\u0014XNV1mk\u0016\u001cX\u0003\u0002CI\t;#b\u0001b%\u0005 \u0012\u0015\u0006\u0003CBZ\t+#I\nb'\n\t\u0011]5Q\u0017\u0002\u0013\u0007\u0006tGK]1og\u001a|'/\u001c,bYV,7\u000f\u0005\u0003N\u0001\u0011m\u0005cA)\u0005\u001e\u0012)1k\u000eb\u0001)\"IA\u0011U\u001c\u0002\u0002\u0003\u000fA1U\u0001\fKZLG-\u001a8dK\u0012\u0012t\u0007\u0005\u0004\u0002\"\u0005\u001dB1\u0014\u0005\n\tO;\u0014\u0011!a\u0002\tS\u000b1\"\u001a<jI\u0016t7-\u001a\u00133qA1!Q\u0004B\u0012\t7\u000b1bY1o\u001b\u0006\u0004\b+Y5sgV1Aq\u0016C^\t\u007f#b\u0001\"-\u0005D\u0012%\u0007CDBZ\tg#9,a\u0012\u0005:\u0012uF\u0011Y\u0005\u0005\tk\u001b)LA\nDC:l\u0015\r]&fsZ\u000bG.^3QC&\u00148\u000f\u0005\u0003N\u0001\u0011e\u0006cA)\u0005<\u0012)1\u000b\u000fb\u0001)B\u0019\u0011\u000bb0\u0005\r\u0011=\u0002H1\u0001U!\u0011i\u0005\u0001\"0\t\u0013\u0011\u0015\u0007(!AA\u0004\u0011\u001d\u0017aC3wS\u0012,gnY3%ee\u0002bA!\b\u0003$\u0011u\u0006\"\u0003Cfq\u0005\u0005\t9\u0001Cg\u0003-)g/\u001b3f]\u000e,Ge\r\u0019\u0011\r\u0005\u0005\u0012q\u0005C_\u0003\u0019\u0019\u0017M\u001c#j[V!A1\u001bCw+\t!)\u000e\u0005\u0005\u0005X\u0012uG\u0011^A$\u001d\riE\u0011\\\u0005\u0004\t7|\u0014a\u00013j[&!Aq\u001cCq\u0005\u0011IU\u000e\u001d7\n\t\u0011\rHQ\u001d\u0002\u0006+\u001a+hn\u0019\u0006\u0004\tO\f\u0015aB4f]\u0016\u0014\u0018n\u0019\t\u0005\u001b\u0002!Y\u000fE\u0002R\t[$a\u0001b<:\u0005\u0004!&!A#\u0002\u000bM\u0004\u0018mY3\u0016\t\u0011UX\u0011\u0001\u000b\t\to,\u0019!\"\u0004\u0006\u0014AQ1\u0011\fC}\t{\f9\u0005b@\n\t\u0011m81\f\u0002\u001d\u001bV$\u0018M\u00197f\r&t\u0017\u000e^3D_>\u0014H-\u001b8bi\u00164\u0015.\u001a7e!\u0011i\u0005\u0001b@\u0011\u0007E+\t\u0001\u0002\u0004\u0005pj\u0012\r\u0001\u0016\u0005\n\u000b\u000bQ\u0014\u0011!a\u0002\u000b\u000f\t1\"\u001a<jI\u0016t7-\u001a\u00134cA11\u0011LC\u0005\t\u007fLA!b\u0003\u0004\\\t)a)[3mI\"IQq\u0002\u001e\u0002\u0002\u0003\u000fQ\u0011C\u0001\fKZLG-\u001a8dK\u0012\u001a$\u0007\u0005\u0004\u0003\u001e\t\rBq \u0005\n\u000b+Q\u0014\u0011!a\u0002\u000b/\t1\"\u001a<jI\u0016t7-\u001a\u00134gA1\u0011\u0011EA\u0014\t\u007f\f\u0001b]2bY\u0006\u0014xJZ\u000b\u0005\u000b;)I#\u0006\u0002\u0006 AA11WC\u0011\u000bK)9#\u0003\u0003\u0006$\rU&\u0001C*dC2\f'o\u00144\u0011\t5\u0003Qq\u0005\t\u0004#\u0016%BABC\u0016w\t\u0007AKA\u0001U\u0003\u0011Ig.\u001b;)\u0007q*\t\u0004E\u0002H\u000bgI1!\"\u000eI\u0005!qw.\u001b8mS:,\u0017\u0001D<sSR,'+\u001a9mC\u000e,GCAC\u001e!\u0011)i$b\u0011\u000e\u0005\u0015}\"\u0002BC!\u0005\u000b\nA\u0001\\1oO&!QQIC \u0005\u0019y%M[3di\u0002"
)
public class SparseVector implements StorageVector, Serializable {
   private static final long serialVersionUID = 1L;
   public final SparseArray array;
   public final Zero zero;

   public static ScalarOf scalarOf() {
      return SparseVector$.MODULE$.scalarOf();
   }

   public static MutableFiniteCoordinateField space(final Field evidence$31, final ClassTag evidence$32, final Zero evidence$33) {
      return SparseVector$.MODULE$.space(evidence$31, evidence$32, evidence$33);
   }

   public static UFunc.UImpl canDim() {
      return SparseVector$.MODULE$.canDim();
   }

   public static CanMapKeyValuePairs canMapPairs(final ClassTag evidence$29, final Zero evidence$30) {
      return SparseVector$.MODULE$.canMapPairs(evidence$29, evidence$30);
   }

   public static CanTransformValues canTransformValues(final Zero evidence$27, final ClassTag evidence$28) {
      return SparseVector$.MODULE$.canTransformValues(evidence$27, evidence$28);
   }

   public static CanCreateZerosLike canCreateZerosLike(final ClassTag evidence$25, final Zero evidence$26) {
      return SparseVector$.MODULE$.canCreateZerosLike(evidence$25, evidence$26);
   }

   public static CanCreateZeros canCreateZeros(final ClassTag evidence$23, final Zero evidence$24) {
      return SparseVector$.MODULE$.canCreateZeros(evidence$23, evidence$24);
   }

   public static CanTraverseKeyValuePairs canTraverseKeyValuePairs() {
      return SparseVector$.MODULE$.canTraverseKeyValuePairs();
   }

   public static CanMapValues canMapValues(final ClassTag evidence$21, final Zero evidence$22) {
      return SparseVector$.MODULE$.canMapValues(evidence$21, evidence$22);
   }

   public static CanCopySparseVector canCopySparse(final ClassTag evidence$19, final Zero evidence$20) {
      return SparseVector$.MODULE$.canCopySparse(evidence$19, evidence$20);
   }

   public static CSCMatrix horzcat(final Seq vectors, final Zero evidence$15, final ClassTag evidence$16) {
      return SparseVector$.MODULE$.horzcat(vectors, evidence$15, evidence$16);
   }

   public static SparseVector vertcat(final Seq vectors, final Zero evidence$13, final ClassTag evidence$14) {
      return SparseVector$.MODULE$.vertcat(vectors, evidence$13, evidence$14);
   }

   public static SparseVector tabulate(final int size, final Function1 f, final ClassTag evidence$8, final Zero evidence$9) {
      return SparseVector$.MODULE$.tabulate(size, f, evidence$8, evidence$9);
   }

   public static SparseVector fill(final int size, final Function0 v, final ClassTag evidence$6, final Zero evidence$7) {
      return SparseVector$.MODULE$.fill(size, v, evidence$6, evidence$7);
   }

   public static SparseVector zeros(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      return SparseVector$.MODULE$.zeros(size, evidence$1, evidence$2);
   }

   public int iterableSize() {
      return Storage.iterableSize$(this);
   }

   public Set keySet() {
      return Vector.keySet$(this);
   }

   public int size() {
      return Vector.size$(this);
   }

   public Iterator iterator() {
      return Vector.iterator$(this);
   }

   public Iterator valuesIterator() {
      return Vector.valuesIterator$(this);
   }

   public Iterator keysIterator() {
      return Vector.keysIterator$(this);
   }

   public DenseVector toDenseVector(final ClassTag cm) {
      return Vector.toDenseVector$(this, cm);
   }

   public DenseVector toDenseVector$mcD$sp(final ClassTag cm) {
      return Vector.toDenseVector$mcD$sp$(this, cm);
   }

   public DenseVector toDenseVector$mcF$sp(final ClassTag cm) {
      return Vector.toDenseVector$mcF$sp$(this, cm);
   }

   public DenseVector toDenseVector$mcI$sp(final ClassTag cm) {
      return Vector.toDenseVector$mcI$sp$(this, cm);
   }

   public scala.collection.immutable.Vector toScalaVector() {
      return Vector.toScalaVector$(this);
   }

   public Object toArray(final ClassTag cm) {
      return Vector.toArray$(this, cm);
   }

   public double[] toArray$mcD$sp(final ClassTag cm) {
      return Vector.toArray$mcD$sp$(this, cm);
   }

   public float[] toArray$mcF$sp(final ClassTag cm) {
      return Vector.toArray$mcF$sp$(this, cm);
   }

   public int[] toArray$mcI$sp(final ClassTag cm) {
      return Vector.toArray$mcI$sp$(this, cm);
   }

   public Vector toVector(final ClassTag cm) {
      return Vector.toVector$(this, cm);
   }

   public Vector toVector$mcD$sp(final ClassTag cm) {
      return Vector.toVector$mcD$sp$(this, cm);
   }

   public Vector toVector$mcF$sp(final ClassTag cm) {
      return Vector.toVector$mcF$sp$(this, cm);
   }

   public Vector toVector$mcI$sp(final ClassTag cm) {
      return Vector.toVector$mcI$sp$(this, cm);
   }

   public Vector padTo(final int len, final Object elem, final ClassTag cm) {
      return Vector.padTo$(this, len, elem, cm);
   }

   public Vector padTo$mcD$sp(final int len, final double elem, final ClassTag cm) {
      return Vector.padTo$mcD$sp$(this, len, elem, cm);
   }

   public Vector padTo$mcF$sp(final int len, final float elem, final ClassTag cm) {
      return Vector.padTo$mcF$sp$(this, len, elem, cm);
   }

   public Vector padTo$mcI$sp(final int len, final int elem, final ClassTag cm) {
      return Vector.padTo$mcI$sp$(this, len, elem, cm);
   }

   public boolean exists(final Function1 f) {
      return Vector.exists$(this, f);
   }

   public boolean exists$mcD$sp(final Function1 f) {
      return Vector.exists$mcD$sp$(this, f);
   }

   public boolean exists$mcF$sp(final Function1 f) {
      return Vector.exists$mcF$sp$(this, f);
   }

   public boolean exists$mcI$sp(final Function1 f) {
      return Vector.exists$mcI$sp$(this, f);
   }

   public boolean forall(final Function1 f) {
      return Vector.forall$(this, f);
   }

   public boolean forall$mcD$sp(final Function1 f) {
      return Vector.forall$mcD$sp$(this, f);
   }

   public boolean forall$mcF$sp(final Function1 f) {
      return Vector.forall$mcF$sp$(this, f);
   }

   public boolean forall$mcI$sp(final Function1 f) {
      return Vector.forall$mcI$sp$(this, f);
   }

   public Object fold(final Object z, final Function2 op) {
      return Vector.fold$(this, z, op);
   }

   public Object fold$mcD$sp(final Object z, final Function2 op) {
      return Vector.fold$mcD$sp$(this, z, op);
   }

   public Object fold$mcF$sp(final Object z, final Function2 op) {
      return Vector.fold$mcF$sp$(this, z, op);
   }

   public Object fold$mcI$sp(final Object z, final Function2 op) {
      return Vector.fold$mcI$sp$(this, z, op);
   }

   public Object foldLeft(final Object z, final Function2 op) {
      return Vector.foldLeft$(this, z, op);
   }

   public Object foldLeft$mcD$sp(final Object z, final Function2 op) {
      return Vector.foldLeft$mcD$sp$(this, z, op);
   }

   public Object foldLeft$mcF$sp(final Object z, final Function2 op) {
      return Vector.foldLeft$mcF$sp$(this, z, op);
   }

   public Object foldLeft$mcI$sp(final Object z, final Function2 op) {
      return Vector.foldLeft$mcI$sp$(this, z, op);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return Vector.foldRight$(this, z, op);
   }

   public Object foldRight$mcD$sp(final Object z, final Function2 op) {
      return Vector.foldRight$mcD$sp$(this, z, op);
   }

   public Object foldRight$mcF$sp(final Object z, final Function2 op) {
      return Vector.foldRight$mcF$sp$(this, z, op);
   }

   public Object foldRight$mcI$sp(final Object z, final Function2 op) {
      return Vector.foldRight$mcI$sp$(this, z, op);
   }

   public Object reduce(final Function2 op) {
      return Vector.reduce$(this, op);
   }

   public Object reduce$mcD$sp(final Function2 op) {
      return Vector.reduce$mcD$sp$(this, op);
   }

   public Object reduce$mcF$sp(final Function2 op) {
      return Vector.reduce$mcF$sp$(this, op);
   }

   public Object reduce$mcI$sp(final Function2 op) {
      return Vector.reduce$mcI$sp$(this, op);
   }

   public Object reduceLeft(final Function2 op) {
      return Vector.reduceLeft$(this, op);
   }

   public Object reduceLeft$mcD$sp(final Function2 op) {
      return Vector.reduceLeft$mcD$sp$(this, op);
   }

   public Object reduceLeft$mcF$sp(final Function2 op) {
      return Vector.reduceLeft$mcF$sp$(this, op);
   }

   public Object reduceLeft$mcI$sp(final Function2 op) {
      return Vector.reduceLeft$mcI$sp$(this, op);
   }

   public Object reduceRight(final Function2 op) {
      return Vector.reduceRight$(this, op);
   }

   public Object reduceRight$mcD$sp(final Function2 op) {
      return Vector.reduceRight$mcD$sp$(this, op);
   }

   public Object reduceRight$mcF$sp(final Function2 op) {
      return Vector.reduceRight$mcF$sp$(this, op);
   }

   public Object reduceRight$mcI$sp(final Function2 op) {
      return Vector.reduceRight$mcI$sp$(this, op);
   }

   public Vector scan(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector.scan$(this, z, op, cm, cm1);
   }

   public Vector scan$mcD$sp(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector.scan$mcD$sp$(this, z, op, cm, cm1);
   }

   public Vector scan$mcF$sp(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector.scan$mcF$sp$(this, z, op, cm, cm1);
   }

   public Vector scan$mcI$sp(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector.scan$mcI$sp$(this, z, op, cm, cm1);
   }

   public Vector scanLeft(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanLeft$(this, z, op, cm1);
   }

   public Vector scanLeft$mcD$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanLeft$mcD$sp$(this, z, op, cm1);
   }

   public Vector scanLeft$mcF$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanLeft$mcF$sp$(this, z, op, cm1);
   }

   public Vector scanLeft$mcI$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanLeft$mcI$sp$(this, z, op, cm1);
   }

   public Vector scanRight(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanRight$(this, z, op, cm1);
   }

   public Vector scanRight$mcD$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanRight$mcD$sp$(this, z, op, cm1);
   }

   public Vector scanRight$mcF$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanRight$mcF$sp$(this, z, op, cm1);
   }

   public Vector scanRight$mcI$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanRight$mcI$sp$(this, z, op, cm1);
   }

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$(this, fn, canMapValues);
   }

   public Object map$mcZ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcZ$sp$(this, fn, canMapValues);
   }

   public Object map$mcB$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcB$sp$(this, fn, canMapValues);
   }

   public Object map$mcC$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcC$sp$(this, fn, canMapValues);
   }

   public Object map$mcD$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcD$sp$(this, fn, canMapValues);
   }

   public Object map$mcF$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcF$sp$(this, fn, canMapValues);
   }

   public Object map$mcI$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcI$sp$(this, fn, canMapValues);
   }

   public Object map$mcJ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcJ$sp$(this, fn, canMapValues);
   }

   public Object map$mcS$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcS$sp$(this, fn, canMapValues);
   }

   public Object map$mcV$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcV$sp$(this, fn, canMapValues);
   }

   public void foreach(final Function1 fn) {
      VectorLike.foreach$(this, fn);
   }

   public void foreach$mcZ$sp(final Function1 fn) {
      VectorLike.foreach$mcZ$sp$(this, fn);
   }

   public void foreach$mcB$sp(final Function1 fn) {
      VectorLike.foreach$mcB$sp$(this, fn);
   }

   public void foreach$mcC$sp(final Function1 fn) {
      VectorLike.foreach$mcC$sp$(this, fn);
   }

   public void foreach$mcD$sp(final Function1 fn) {
      VectorLike.foreach$mcD$sp$(this, fn);
   }

   public void foreach$mcF$sp(final Function1 fn) {
      VectorLike.foreach$mcF$sp$(this, fn);
   }

   public void foreach$mcI$sp(final Function1 fn) {
      VectorLike.foreach$mcI$sp$(this, fn);
   }

   public void foreach$mcJ$sp(final Function1 fn) {
      VectorLike.foreach$mcJ$sp$(this, fn);
   }

   public void foreach$mcS$sp(final Function1 fn) {
      VectorLike.foreach$mcS$sp$(this, fn);
   }

   public void foreach$mcV$sp(final Function1 fn) {
      VectorLike.foreach$mcV$sp$(this, fn);
   }

   public double apply$mcID$sp(final int i) {
      return TensorLike.apply$mcID$sp$(this, i);
   }

   public float apply$mcIF$sp(final int i) {
      return TensorLike.apply$mcIF$sp$(this, i);
   }

   public int apply$mcII$sp(final int i) {
      return TensorLike.apply$mcII$sp$(this, i);
   }

   public long apply$mcIJ$sp(final int i) {
      return TensorLike.apply$mcIJ$sp$(this, i);
   }

   public void update$mcID$sp(final int i, final double v) {
      TensorLike.update$mcID$sp$(this, i, v);
   }

   public void update$mcIF$sp(final int i, final float v) {
      TensorLike.update$mcIF$sp$(this, i, v);
   }

   public void update$mcII$sp(final int i, final int v) {
      TensorLike.update$mcII$sp$(this, i, v);
   }

   public void update$mcIJ$sp(final int i, final long v) {
      TensorLike.update$mcIJ$sp$(this, i, v);
   }

   public TensorKeys keys() {
      return TensorLike.keys$(this);
   }

   public TensorValues values() {
      return TensorLike.values$(this);
   }

   public TensorPairs pairs() {
      return TensorLike.pairs$(this);
   }

   public TensorActive active() {
      return TensorLike.active$(this);
   }

   public Object apply(final Object slice, final CanSlice canSlice) {
      return TensorLike.apply$(this, slice, canSlice);
   }

   public Object apply(final Object a, final Object b, final Object c, final Seq slice, final CanSlice canSlice) {
      return TensorLike.apply$(this, a, b, c, slice, canSlice);
   }

   public Object apply$mcI$sp(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return TensorLike.apply$mcI$sp$(this, a, b, c, slice, canSlice);
   }

   public Object apply(final Object slice1, final Object slice2, final CanSlice2 canSlice) {
      return TensorLike.apply$(this, slice1, slice2, canSlice);
   }

   public Object mapPairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$(this, f, bf);
   }

   public Object mapPairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$mcID$sp$(this, f, bf);
   }

   public Object mapPairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$mcIF$sp$(this, f, bf);
   }

   public Object mapPairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$mcII$sp$(this, f, bf);
   }

   public Object mapPairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$mcIJ$sp$(this, f, bf);
   }

   public Object mapActivePairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$(this, f, bf);
   }

   public Object mapActivePairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$mcID$sp$(this, f, bf);
   }

   public Object mapActivePairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$mcIF$sp$(this, f, bf);
   }

   public Object mapActivePairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$mcII$sp$(this, f, bf);
   }

   public Object mapActivePairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$mcIJ$sp$(this, f, bf);
   }

   public Object mapValues(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$(this, f, bf);
   }

   public Object mapValues$mcD$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$mcD$sp$(this, f, bf);
   }

   public Object mapValues$mcF$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$mcF$sp$(this, f, bf);
   }

   public Object mapValues$mcI$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$mcI$sp$(this, f, bf);
   }

   public Object mapValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$mcJ$sp$(this, f, bf);
   }

   public Object mapActiveValues(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$(this, f, bf);
   }

   public Object mapActiveValues$mcD$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$mcD$sp$(this, f, bf);
   }

   public Object mapActiveValues$mcF$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$mcF$sp$(this, f, bf);
   }

   public Object mapActiveValues$mcI$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$mcI$sp$(this, f, bf);
   }

   public Object mapActiveValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$mcJ$sp$(this, f, bf);
   }

   public void foreachKey(final Function1 fn) {
      TensorLike.foreachKey$(this, fn);
   }

   public void foreachKey$mcI$sp(final Function1 fn) {
      TensorLike.foreachKey$mcI$sp$(this, fn);
   }

   public void foreachPair(final Function2 fn) {
      TensorLike.foreachPair$(this, fn);
   }

   public void foreachPair$mcID$sp(final Function2 fn) {
      TensorLike.foreachPair$mcID$sp$(this, fn);
   }

   public void foreachPair$mcIF$sp(final Function2 fn) {
      TensorLike.foreachPair$mcIF$sp$(this, fn);
   }

   public void foreachPair$mcII$sp(final Function2 fn) {
      TensorLike.foreachPair$mcII$sp$(this, fn);
   }

   public void foreachPair$mcIJ$sp(final Function2 fn) {
      TensorLike.foreachPair$mcIJ$sp$(this, fn);
   }

   public void foreachValue(final Function1 fn) {
      TensorLike.foreachValue$(this, fn);
   }

   public void foreachValue$mcD$sp(final Function1 fn) {
      TensorLike.foreachValue$mcD$sp$(this, fn);
   }

   public void foreachValue$mcF$sp(final Function1 fn) {
      TensorLike.foreachValue$mcF$sp$(this, fn);
   }

   public void foreachValue$mcI$sp(final Function1 fn) {
      TensorLike.foreachValue$mcI$sp$(this, fn);
   }

   public void foreachValue$mcJ$sp(final Function1 fn) {
      TensorLike.foreachValue$mcJ$sp$(this, fn);
   }

   public boolean forall(final Function2 fn) {
      return TensorLike.forall$(this, (Function2)fn);
   }

   public boolean forall$mcID$sp(final Function2 fn) {
      return TensorLike.forall$mcID$sp$(this, fn);
   }

   public boolean forall$mcIF$sp(final Function2 fn) {
      return TensorLike.forall$mcIF$sp$(this, fn);
   }

   public boolean forall$mcII$sp(final Function2 fn) {
      return TensorLike.forall$mcII$sp$(this, fn);
   }

   public boolean forall$mcIJ$sp(final Function2 fn) {
      return TensorLike.forall$mcIJ$sp$(this, fn);
   }

   public boolean forall$mcJ$sp(final Function1 fn) {
      return TensorLike.forall$mcJ$sp$(this, fn);
   }

   public final Object $plus(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$plus$(this, b, op);
   }

   public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$eq$(this, b, op);
   }

   public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$plus$eq$(this, b, op);
   }

   public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$times$eq$(this, b, op);
   }

   public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$plus$eq$(this, b, op);
   }

   public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$times$eq$(this, b, op);
   }

   public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$minus$eq$(this, b, op);
   }

   public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$percent$eq$(this, b, op);
   }

   public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$percent$eq$(this, b, op);
   }

   public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$minus$eq$(this, b, op);
   }

   public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$div$eq$(this, b, op);
   }

   public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$up$eq$(this, b, op);
   }

   public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$div$eq$(this, b, op);
   }

   public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$less$colon$less$(this, b, op);
   }

   public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$less$colon$eq$(this, b, op);
   }

   public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$greater$colon$greater$(this, b, op);
   }

   public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$greater$colon$eq$(this, b, op);
   }

   public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$amp$eq$(this, b, op);
   }

   public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$bar$eq$(this, b, op);
   }

   public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$up$up$eq$(this, b, op);
   }

   public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$amp$eq$(this, b, op);
   }

   public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$bar$eq$(this, b, op);
   }

   public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$up$up$eq$(this, b, op);
   }

   public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
   }

   public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$times$colon$times$(this, b, op);
   }

   public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
   }

   public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
   }

   public final Object unary_$minus(final UFunc.UImpl op) {
      return ImmutableNumericOps.unary_$minus$(this, op);
   }

   public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
   }

   public final Object $minus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$minus$(this, b, op);
   }

   public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
   }

   public final Object $percent(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$percent$(this, b, op);
   }

   public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$div$colon$div$(this, b, op);
   }

   public final Object $div(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$div$(this, b, op);
   }

   public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$colon$up$(this, b, op);
   }

   public final Object dot(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.dot$(this, b, op);
   }

   public final Object unary_$bang(final UFunc.UImpl op) {
      return ImmutableNumericOps.unary_$bang$(this, op);
   }

   public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
   }

   public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
   }

   public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
   }

   public final Object $amp(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$amp$(this, b, op);
   }

   public final Object $bar(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bar$(this, b, op);
   }

   public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$up$(this, b, op);
   }

   public final Object $times(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$times$(this, b, op);
   }

   public final Object t(final CanTranspose op) {
      return ImmutableNumericOps.t$(this, op);
   }

   public Object $bslash(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bslash$(this, b, op);
   }

   public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
      return ImmutableNumericOps.t$(this, a, b, op, canSlice);
   }

   public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
      return ImmutableNumericOps.t$(this, a, op, canSlice);
   }

   public IndexedSeq findAll(final Function1 f) {
      return QuasiTensor.findAll$(this, f);
   }

   public IndexedSeq findAll$mcD$sp(final Function1 f) {
      return QuasiTensor.findAll$mcD$sp$(this, f);
   }

   public IndexedSeq findAll$mcF$sp(final Function1 f) {
      return QuasiTensor.findAll$mcF$sp$(this, f);
   }

   public IndexedSeq findAll$mcI$sp(final Function1 f) {
      return QuasiTensor.findAll$mcI$sp$(this, f);
   }

   public IndexedSeq findAll$mcJ$sp(final Function1 f) {
      return QuasiTensor.findAll$mcJ$sp$(this, f);
   }

   public SparseArray array() {
      return this.array;
   }

   public Object data() {
      return this.array().data();
   }

   public int[] index() {
      return this.array().index();
   }

   public int activeSize() {
      return this.array().activeSize();
   }

   public int used() {
      return this.activeSize();
   }

   public int length() {
      return this.array().length();
   }

   public SparseVector repr() {
      return this;
   }

   public boolean contains(final int i) {
      return this.array().contains(i);
   }

   public Object apply(final int i) {
      return this.apply$mcI$sp(i);
   }

   public void update(final int i, final Object v) {
      if (i >= 0 && i < this.size()) {
         this.array().update(i, v);
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(12)).append(i).append(" not in [0,").append(this.size()).append(")").toString());
      }
   }

   public Object otherApply(final int i) {
      return this.apply(i);
   }

   public Iterator activeIterator() {
      return this.activeKeysIterator().zip(this.activeValuesIterator());
   }

   public Iterator activeValuesIterator() {
      return .MODULE$.iterator$extension(scala.Predef..MODULE$.genericArrayOps(this.data())).take(this.activeSize());
   }

   public Iterator activeKeysIterator() {
      return .MODULE$.iterator$extension(scala.Predef..MODULE$.intArrayOps(this.index())).take(this.activeSize());
   }

   public Object default() {
      return this.zero.zero();
   }

   public boolean equals(final Object other) {
      boolean var2;
      if (other instanceof SparseVector) {
         boolean var7;
         label34: {
            label33: {
               SparseVector var4 = (SparseVector)other;
               SparseArray var10000 = this.array();
               SparseArray var5 = var4.array();
               if (var10000 == null) {
                  if (var5 == null) {
                     break label33;
                  }
               } else if (var10000.equals(var5)) {
                  break label33;
               }

               var7 = false;
               break label34;
            }

            var7 = true;
         }

         var2 = var7;
      } else if (other instanceof Vector) {
         Vector var6 = (Vector)other;
         var2 = this.length() == var6.length() && this.valuesIterator().sameElements(var6.valuesIterator());
      } else {
         var2 = false;
      }

      return var2;
   }

   public int hashCode() {
      return this.array().hashCode();
   }

   public boolean isActive(final int rawIndex) {
      return this.array().isActive(rawIndex);
   }

   public String toString() {
      return this.activeIterator().mkString((new StringBuilder(15)).append("SparseVector(").append(this.length()).append(")(").toString(), ", ", ")");
   }

   public SparseVector copy() {
      return new SparseVector((int[])ArrayUtil$.MODULE$.copyOf(this.index(), this.index().length), ArrayUtil$.MODULE$.copyOf(this.data(), this.index().length), this.activeSize(), this.size(), this.zero);
   }

   public void reserve(final int nnz) {
      this.array().reserve(nnz);
   }

   public void compact() {
      this.array().compact();
   }

   public void use(final int[] index, final Object data, final int activeSize) {
      scala.Predef..MODULE$.require(activeSize <= this.size(), () -> "Can't have more elements in the array than length!");
      scala.Predef..MODULE$.require(activeSize >= 0, () -> "activeSize must be non-negative");
      scala.Predef..MODULE$.require(scala.runtime.ScalaRunTime..MODULE$.array_length(data) >= activeSize, () -> "activeSize must be no greater than array length...");
      this.array().use(index, data, activeSize);
   }

   public Object valueAt(final int i) {
      return scala.runtime.ScalaRunTime..MODULE$.array_apply(this.data(), i);
   }

   public int indexAt(final int i) {
      return this.index()[i];
   }

   public boolean allVisitableIndicesActive() {
      return true;
   }

   public CSCMatrix asCscRow(final ClassTag man) {
      CSCMatrix var10000;
      if (this.index().length == 0) {
         var10000 = CSCMatrix$.MODULE$.zeros(1, this.length(), man, this.zero);
      } else {
         IntRef ii = IntRef.create(0);
         int[] nIndex = (int[])scala.Array..MODULE$.tabulate(this.length() + 1, (JFunction1.mcII.sp)(cp) -> {
            int var10000;
            if (ii.elem < this.used() && cp == this.index()[ii.elem]) {
               ++ii.elem;
               var10000 = ii.elem - 1;
            } else {
               var10000 = ii.elem;
            }

            return var10000;
         }, scala.reflect.ClassTag..MODULE$.Int());
         scala.Predef..MODULE$.assert(ii.elem == this.used());
         var10000 = new CSCMatrix(this.data(), 1, this.length(), nIndex, this.activeSize(), (int[])scala.Array..MODULE$.fill(scala.runtime.ScalaRunTime..MODULE$.array_length(this.data()), (JFunction0.mcI.sp)() -> 0, scala.reflect.ClassTag..MODULE$.Int()), this.zero);
      }

      return var10000;
   }

   public CSCMatrix asCscColumn(final ClassTag man) {
      return this.index().length == 0 ? CSCMatrix$.MODULE$.zeros(this.length(), 1, man, this.zero) : new CSCMatrix(scala.runtime.ScalaRunTime..MODULE$.array_clone(this.data()), this.length(), 1, new int[]{0, this.used()}, this.activeSize(), this.index(), this.zero);
   }

   public SparseArray array$mcD$sp() {
      return this.array();
   }

   public SparseArray array$mcF$sp() {
      return this.array();
   }

   public SparseArray array$mcI$sp() {
      return this.array();
   }

   public SparseArray array$mcJ$sp() {
      return this.array();
   }

   public double[] data$mcD$sp() {
      return (double[])this.data();
   }

   public float[] data$mcF$sp() {
      return (float[])this.data();
   }

   public int[] data$mcI$sp() {
      return (int[])this.data();
   }

   public long[] data$mcJ$sp() {
      return (long[])this.data();
   }

   public SparseVector repr$mcD$sp() {
      return this.repr();
   }

   public SparseVector repr$mcF$sp() {
      return this.repr();
   }

   public SparseVector repr$mcI$sp() {
      return this.repr();
   }

   public SparseVector repr$mcJ$sp() {
      return this.repr();
   }

   public double apply$mcD$sp(final int i) {
      return BoxesRunTime.unboxToDouble(this.apply(i));
   }

   public float apply$mcF$sp(final int i) {
      return BoxesRunTime.unboxToFloat(this.apply(i));
   }

   public int apply$mcI$sp(final int i) {
      return BoxesRunTime.unboxToInt(this.apply(i));
   }

   public long apply$mcJ$sp(final int i) {
      return BoxesRunTime.unboxToLong(this.apply(i));
   }

   public void update$mcD$sp(final int i, final double v) {
      this.update(i, BoxesRunTime.boxToDouble(v));
   }

   public void update$mcF$sp(final int i, final float v) {
      this.update(i, BoxesRunTime.boxToFloat(v));
   }

   public void update$mcI$sp(final int i, final int v) {
      this.update(i, BoxesRunTime.boxToInteger(v));
   }

   public void update$mcJ$sp(final int i, final long v) {
      this.update(i, BoxesRunTime.boxToLong(v));
   }

   public double otherApply$mcD$sp(final int i) {
      return BoxesRunTime.unboxToDouble(this.otherApply(i));
   }

   public float otherApply$mcF$sp(final int i) {
      return BoxesRunTime.unboxToFloat(this.otherApply(i));
   }

   public int otherApply$mcI$sp(final int i) {
      return BoxesRunTime.unboxToInt(this.otherApply(i));
   }

   public long otherApply$mcJ$sp(final int i) {
      return BoxesRunTime.unboxToLong(this.otherApply(i));
   }

   public double default$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.default());
   }

   public float default$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.default());
   }

   public int default$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.default());
   }

   public long default$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.default());
   }

   public SparseVector copy$mcD$sp() {
      return this.copy();
   }

   public SparseVector copy$mcF$sp() {
      return this.copy();
   }

   public SparseVector copy$mcI$sp() {
      return this.copy();
   }

   public SparseVector copy$mcJ$sp() {
      return this.copy();
   }

   public void use$mcD$sp(final int[] index, final double[] data, final int activeSize) {
      this.use(index, data, activeSize);
   }

   public void use$mcF$sp(final int[] index, final float[] data, final int activeSize) {
      this.use(index, data, activeSize);
   }

   public void use$mcI$sp(final int[] index, final int[] data, final int activeSize) {
      this.use(index, data, activeSize);
   }

   public void use$mcJ$sp(final int[] index, final long[] data, final int activeSize) {
      this.use(index, data, activeSize);
   }

   public double valueAt$mcD$sp(final int i) {
      return BoxesRunTime.unboxToDouble(this.valueAt(i));
   }

   public float valueAt$mcF$sp(final int i) {
      return BoxesRunTime.unboxToFloat(this.valueAt(i));
   }

   public int valueAt$mcI$sp(final int i) {
      return BoxesRunTime.unboxToInt(this.valueAt(i));
   }

   public long valueAt$mcJ$sp(final int i) {
      return BoxesRunTime.unboxToLong(this.valueAt(i));
   }

   public CSCMatrix asCscRow$mcD$sp(final ClassTag man) {
      return this.asCscRow(man);
   }

   public CSCMatrix asCscRow$mcF$sp(final ClassTag man) {
      return this.asCscRow(man);
   }

   public CSCMatrix asCscRow$mcI$sp(final ClassTag man) {
      return this.asCscRow(man);
   }

   public CSCMatrix asCscRow$mcJ$sp(final ClassTag man) {
      return this.asCscRow(man);
   }

   public CSCMatrix asCscColumn$mcD$sp(final ClassTag man) {
      return this.asCscColumn(man);
   }

   public CSCMatrix asCscColumn$mcF$sp(final ClassTag man) {
      return this.asCscColumn(man);
   }

   public CSCMatrix asCscColumn$mcI$sp(final ClassTag man) {
      return this.asCscColumn(man);
   }

   public CSCMatrix asCscColumn$mcJ$sp(final ClassTag man) {
      return this.asCscColumn(man);
   }

   public Object apply$mcI$sp(final int i) {
      if (i >= 0 && i < this.size()) {
         return this.array().apply(i);
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(12)).append(i).append(" not in [0,").append(this.size()).append(")").toString());
      }
   }

   public boolean specInstance$() {
      return false;
   }

   public SparseVector(final SparseArray array, final Zero zero) {
      this.array = array;
      this.zero = zero;
      QuasiTensor.$init$(this);
      ImmutableNumericOps.$init$(this);
      NumericOps.$init$(this);
      TensorLike.$init$(this);
      VectorLike.$init$(this);
      Vector.$init$(this);
      Storage.$init$(this);
      SparseVector$.MODULE$.breeze$linalg$SparseVector$$init();
   }

   public SparseVector(final int[] index, final Object data, final int activeSize, final int length, final Zero value) {
      this(new SparseArray(index, data, activeSize, length, value.zero()), value);
   }

   public SparseVector(final int[] index, final Object data, final int length, final Zero value) {
      this(index, data, index.length, length, value);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class CanCopySparseVector implements CanCopy {
      public final Zero evidence$18;

      public SparseVector apply(final SparseVector v1) {
         return v1.copy();
      }

      public SparseVector apply$mcD$sp(final SparseVector v1) {
         return this.apply(v1);
      }

      public SparseVector apply$mcF$sp(final SparseVector v1) {
         return this.apply(v1);
      }

      public SparseVector apply$mcI$sp(final SparseVector v1) {
         return this.apply(v1);
      }

      public SparseVector apply$mcJ$sp(final SparseVector v1) {
         return this.apply(v1);
      }

      public CanCopySparseVector(final ClassTag evidence$17, final Zero evidence$18) {
         this.evidence$18 = evidence$18;
      }
   }
}
