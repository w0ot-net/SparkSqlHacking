package scala.reflect.internal.pickling;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.MapFactory;
import scala.collection.StringOps;
import scala.collection.immutable.List;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Flags$;
import scala.reflect.internal.Mirrors;
import scala.reflect.internal.MissingRequirementError;
import scala.reflect.internal.Names;
import scala.reflect.internal.Phase;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;
import scala.util.control.NonFatal.;

@ScalaSignature(
   bytes = "\u0006\u0005\ree!\u0002(P\u0003\u0003A\u0006\"B/\u0001\t\u0003q\u0006bB1\u0001\u0005\u00045\tA\u0019\u0005\u0006O\u0002!\t\u0001\u001b\u0005\t\u0003S\u0001\u0001\u0015!\u0003\u0002,\u00191\u0011\u0011\t\u0001\u0001\u0003\u0007B\u0011\"a\u0013\u0006\u0005\u0003\u0005\u000b\u0011\u00028\t\u0011U,!\u0011!Q\u0001\nYD\u0001B_\u0003\u0003\u0002\u0003\u0006Ia\u001f\u0005\u000b\u0003\u000f)!\u0011!Q\u0001\n\u0005%\u0001BCA\t\u000b\t\u0005\t\u0015!\u0003\u0002\u0014!1Q,\u0002C\u0001\u0003\u001bB\u0001\"a\u0017\u0006A\u0003%\u0011Q\f\u0005\u000f\u0003O*A\u0011!A\u0003\u0002\u0003\u0005\u000b\u0011BA5\u0011!\tY'\u0002Q\u0001\n\u00055\u0004\u0002CA8\u000b\u0001\u0006I!!\u001d\t\u000f\u0005\u0005U\u0001\"\u0003\u0002\u0004\"9\u00111S\u0003\u0005\n\u0005U\u0005bBAb\u000b\u0011\u0005\u0011Q\u0019\u0005\b\u0003\u000f,A\u0011BAc\u0011\u001d\tI-\u0002C\t\u0003\u0017Dq!!5\u0006\t#\t\u0019\u000eC\u0004\u0002^\u0016!\t\"a8\t\u000f\u0005\rX\u0001\"\u0005\u0002f\"9\u0011\u0011^\u0003\u0005\u0012\u0005-\bbBAx\u000b\u0011E\u0011\u0011\u001f\u0005\b\u0003k,A\u0011CA|\u0011\u001d\tY0\u0002C\t\u0003{DqAa\u0005\u0006\t#\u0011)\u0002C\u0004\u0003\"\u0015!IAa\t\t\u000f\t\u0015R\u0001\"\u0005\u0003(!9!\u0011F\u0003\u0005\u0012\t-\u0002b\u0002B\u001c\u000b\u0011\u0005!\u0011\b\u0005\b\u0005\u0007*A\u0011\u0003B#\u0011\u001d\u0011\t&\u0002C\u0001\u0005'BqAa\u0017\u0006\t#\t)\rC\u0004\u0003^\u0015!\tBa\u0018\t\u000f\t5T\u0001\"\u0003\u0003p!9!QP\u0003\u0005\u0012\t}\u0004b\u0002BB\u000b\u0011E!Q\u0011\u0005\b\u0005\u001f+A\u0011CAc\u0011\u001d\u0011\t*\u0002C\t\u0005'CqA!&\u0006\t\u0013\u00119\nC\u0004\u0003\u001e\u0016!\tBa(\t\u000f\t\u0005V\u0001\"\u0001\u0003$\"9!\u0011V\u0003\u0005\u0002\t-\u0006b\u0002BZ\u000b\u0011E!q\u0005\u0005\b\u0005k+A\u0011\u0003B\u000b\u0011\u001d\u00119,\u0002C\t\u0005WAqA!/\u0006\t#\u0011)\u0005C\u0004\u0003<\u0016!\tBa%\t\u000f\tuV\u0001\"\u0005\u0003,\"9!qX\u0003\u0005\u0012\t}\u0005b\u0002Ba\u000b\u0011E!1\u0019\u0005\b\u0005\u0017,A\u0011\u0003Bg\u0011\u001d\u0011).\u0002C\t\u0005/DqAa8\u0006\t#\u0011\t\u000fC\u0004\u0003j\u0016!\tBa;\t\u000f\tMX\u0001\"\u0005\u0003v\"9!Q`\u0003\u0005\u0012\t}\bbBB\u0004\u000b\u0011E1\u0011\u0002\u0005\b\u0007\u001b)A\u0011AB\b\u0011\u001d\u0019Y#\u0002C\u0001\u0007[Aqaa\u000e\u0006\t\u0003\u0019I\u0004C\u0004\u0004B\u0015!\taa\u0011\u0007\r\rUS\u0001BB,\u0011%\tI,\u0011B\u0001B\u0003%a\u000f\u0003\u0004^\u0003\u0012\u00051q\f\u0005\t\u0007O\n\u0005\u0015!\u0003\u0004j!A1qN!!\u0002\u0013\u0019\t\bC\u0004\u0004x\u0005#\tb!\u001f\t\u000f\ru\u0014\t\"\u0011\u0004\u0000!911Q!\u0005B\r\u0015eABBE\u000b\u0011\u0019Y\tC\u0005\u0002:&\u0013\t\u0011)A\u0005m\"I1qH%\u0003\u0002\u0003\u0006IA\u001e\u0005\u0007;&#\ta!$\t\u000f\r]\u0014\n\"\u0011\u0004\u0016\nIQK\u001c)jG.dWM\u001d\u0006\u0003!F\u000b\u0001\u0002]5dW2Lgn\u001a\u0006\u0003%N\u000b\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003)V\u000bqA]3gY\u0016\u001cGOC\u0001W\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001A-\u0011\u0005i[V\"A+\n\u0005q+&AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002?B\u0011\u0001\rA\u0007\u0002\u001f\u0006Y1/_7c_2$\u0016M\u00197f+\u0005\u0019\u0007C\u00013f\u001b\u0005\t\u0016B\u00014R\u0005-\u0019\u00160\u001c2pYR\u000b'\r\\3\u0002\u0011Ut\u0007/[2lY\u0016$\u0002\"\u001b7us\u0006\u0015\u0011q\u0002\t\u00035*L!a[+\u0003\tUs\u0017\u000e\u001e\u0005\u0006[\u000e\u0001\rA\\\u0001\u0006Ef$Xm\u001d\t\u00045>\f\u0018B\u00019V\u0005\u0015\t%O]1z!\tQ&/\u0003\u0002t+\n!!)\u001f;f\u0011\u0015)8\u00011\u0001w\u0003\u0019ygMZ:fiB\u0011!l^\u0005\u0003qV\u00131!\u00138u\u0011\u0015Q8\u00011\u0001|\u0003%\u0019G.Y:t%>|G\u000f\u0005\u0002}}:\u0011QPA\u0007\u0002\u0001%\u0019q0!\u0001\u0003\u0017\rc\u0017m]:Ts6\u0014w\u000e\\\u0005\u0004\u0003\u0007\t&aB*z[\n|Gn\u001d\u0005\b\u0003\u000f\u0019\u0001\u0019AA\u0005\u0003)iw\u000eZ;mKJ{w\u000e\u001e\t\u0004y\u0006-\u0011\u0002BA\u0007\u0003\u0003\u0011A\"T8ek2,7+_7c_2Dq!!\u0005\u0004\u0001\u0004\t\u0019\"\u0001\u0005gS2,g.Y7f!\u0011\t)\"a\t\u000f\t\u0005]\u0011q\u0004\t\u0004\u00033)VBAA\u000e\u0015\r\tibV\u0001\u0007yI|w\u000e\u001e \n\u0007\u0005\u0005R+\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003K\t9C\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003C)\u0016aD2p[BdW\r^5oON#\u0018mY6\u0011\r\u00055\u0012qGA\u001e\u001b\t\tyC\u0003\u0003\u00022\u0005M\u0012aB7vi\u0006\u0014G.\u001a\u0006\u0004\u0003k)\u0016AC2pY2,7\r^5p]&!\u0011\u0011HA\u0018\u0005-\t%O]1z\u0005V4g-\u001a:\u0011\u0007q\fi$\u0003\u0003\u0002@\u0005\u0005!AB*z[\n|GN\u0001\u0003TG\u0006t7cA\u0003\u0002FA\u0019\u0001-a\u0012\n\u0007\u0005%sJ\u0001\u0007QS\u000e\\G.\u001a\"vM\u001a,'/\u0001\u0004`Ef$Xm\u001d\u000b\r\u0003\u001f\n\t&a\u0015\u0002V\u0005]\u0013\u0011\f\t\u0003{\u0016Aa!a\u0013\f\u0001\u0004q\u0007\"B;\f\u0001\u00041\b\"\u0002>\f\u0001\u0004Y\bbBA\u0004\u0017\u0001\u0007\u0011\u0011\u0002\u0005\b\u0003#Y\u0001\u0019AA\n\u00035aw.\u00193j]\u001el\u0015N\u001d:peB\u0019A0a\u0018\n\t\u0005\u0005\u00141\r\u0002\u0007\u001b&\u0014(o\u001c:\n\u0007\u0005\u0015\u0014KA\u0004NSJ\u0014xN]:\u0002kM\u001c\u0017\r\\1%e\u00164G.Z2uI%tG/\u001a:oC2$\u0003/[2lY&tw\rJ+o!&\u001c7\u000e\\3sIM\u001b\u0017M\u001c\u0013%S:$W\r\u001f\t\u00045>4\u0018aB3oiJLWm\u001d\t\u00045>L\u0016!C:z[N\u001bw\u000e]3t!!\ti#a\u001d\u0002<\u0005]\u0014\u0002BA;\u0003_\u0011q\u0001S1tQ6\u000b\u0007\u000fE\u0002}\u0003sJA!a\u001f\u0002~\t)1kY8qK&\u0019\u0011qP)\u0003\rM\u001bw\u000e]3t\u0003\u0019)\u0007\u0010]3diR)\u0011.!\"\u0002\n\"1\u0011q\u0011\tA\u0002Y\f\u0001\"\u001a=qK\u000e$X\r\u001a\u0005\t\u0003\u0017\u0003B\u00111\u0001\u0002\u000e\u0006\u0019Qn]4\u0011\u000bi\u000by)a\u0005\n\u0007\u0005EUK\u0001\u0005=Eft\u0017-\\3?\u0003)\u0011XO\\!u\u0013:$W\r_\u000b\u0005\u0003/\u000by\n\u0006\u0003\u0002\u001a\u0006]F\u0003BAN\u0003c\u0003B!!(\u0002 2\u0001AaBAQ#\t\u0007\u00111\u0015\u0002\u0002)F!\u0011QUAV!\rQ\u0016qU\u0005\u0004\u0003S+&a\u0002(pi\"Lgn\u001a\t\u00045\u00065\u0016bAAX+\n\u0019\u0011I\\=\t\u0011\u0005M\u0016\u0003\"a\u0001\u0003k\u000bAAY8esB)!,a$\u0002\u001c\"1\u0011\u0011X\tA\u0002Y\f\u0011!\u001b\u0015\u0004#\u0005u\u0006c\u0001.\u0002@&\u0019\u0011\u0011Y+\u0003\r%tG.\u001b8f\u0003\r\u0011XO\u001c\u000b\u0002S\u0006a1\r[3dWZ+'o]5p]\u0006A1/_7TG>\u0004X\r\u0006\u0003\u0002x\u00055\u0007bBAh)\u0001\u0007\u00111H\u0001\u0004gfl\u0017!D5t'fl'm\u001c7F]R\u0014\u0018\u0010\u0006\u0003\u0002V\u0006m\u0007c\u0001.\u0002X&\u0019\u0011\u0011\\+\u0003\u000f\t{w\u000e\\3b]\"1\u0011\u0011X\u000bA\u0002Y\f1\"[:Ts6\u0014w\u000e\u001c*fMR!\u0011Q[Aq\u0011\u0019\tIL\u0006a\u0001m\u0006Y\u0011n\u001d(b[\u0016,e\u000e\u001e:z)\u0011\t).a:\t\r\u0005ev\u00031\u0001w\u0003]I7oU=nE>d\u0017I\u001c8pi\u0006$\u0018n\u001c8F]R\u0014\u0018\u0010\u0006\u0003\u0002V\u00065\bBBA]1\u0001\u0007a/A\bjg\u000eC\u0017\u000e\u001c3sK:,e\u000e\u001e:z)\u0011\t).a=\t\r\u0005e\u0016\u00041\u0001w\u0003]I7OU3gS:,W.\u001a8u'fl'm\u001c7F]R\u0014\u0018\u0010\u0006\u0003\u0002V\u0006e\bBBA]5\u0001\u0007a/\u0001\u0002biV!\u0011q B\u0002)\u0019\u0011\tAa\u0002\u0003\nA!\u0011Q\u0014B\u0002\t\u001d\t\tk\u0007b\u0001\u0005\u000b\t2!!*Z\u0011\u0019\tIl\u0007a\u0001m\"9!1B\u000eA\u0002\t5\u0011AA8q!\u0015Q&q\u0002B\u0001\u0013\r\u0011\t\"\u0016\u0002\n\rVt7\r^5p]B\n\u0001B]3bI:\u000bW.\u001a\u000b\u0003\u0005/\u00012\u0001 B\r\u0013\u0011\u0011YB!\b\u0003\t9\u000bW.Z\u0005\u0004\u0005?\t&!\u0002(b[\u0016\u001c\u0018a\u0002:fC\u0012,e\u000e\u001a\u000b\u0002m\u0006Q!/Z1e'fl'm\u001c7\u0015\u0005\u0005m\u0012\u0001\u0003:fC\u0012$\u0016\u0010]3\u0015\u0005\t5\u0002c\u0001?\u00030%!!\u0011\u0007B\u001a\u0005\u0011!\u0016\u0010]3\n\u0007\tU\u0012KA\u0003UsB,7/A\u0007o_N+8\r\u001b+za\u0016$\u0016m\u001a\u000b\u0007\u0005[\u0011YDa\u0010\t\r\tu\u0002\u00051\u0001w\u0003\r!\u0018m\u001a\u0005\u0007\u0005\u0003\u0002\u0003\u0019\u0001<\u0002\u0007\u0015tG-\u0001\u0007sK\u0006$7i\u001c8ti\u0006tG\u000f\u0006\u0002\u0003HA\u0019AP!\u0013\n\t\t-#Q\n\u0002\t\u0007>t7\u000f^1oi&\u0019!qJ)\u0003\u0013\r{gn\u001d;b]R\u001c\u0018!\u00058p'V\u001c\u0007nQ8ogR\fg\u000e\u001e+bOR1!q\tB+\u0005/BaA!\u0010#\u0001\u00041\bB\u0002B-E\u0001\u0007a/A\u0002mK:\fAB]3bI\u000eC\u0017\u000e\u001c3sK:\fAB]3bI\u0006sgn\u001c;Be\u001e$BA!\u0019\u0003lA\u0019APa\u0019\n\t\t\u0015$q\r\u0002\u0005)J,W-C\u0002\u0003jE\u0013Q\u0001\u0016:fKNDa!!/%\u0001\u00041\u0018A\u0004:fC\u0012\f%O]1z\u0003:tw\u000e\u001e\u000b\u0003\u0005c\u0002BAW8\u0003tA\u0019AP!\u001e\n\t\t]$\u0011\u0010\u0002\u0012\u00072\f7o\u001d4jY\u0016\feN\\8u\u0003J<\u0017b\u0001B>#\ny\u0011I\u001c8pi\u0006$\u0018n\u001c8J]\u001a|7/A\u000bsK\u0006$7\t\\1tg\u001aLG.Z!o]>$\u0018I]4\u0015\t\tM$\u0011\u0011\u0005\u0007\u0003s3\u0003\u0019\u0001<\u0002%I,\u0017\rZ!o]>$\u0018\r^5p]&sgm\u001c\u000b\u0005\u0005\u000f\u0013i\tE\u0002}\u0005\u0013KAAa#\u0003z\tq\u0011I\u001c8pi\u0006$\u0018n\u001c8J]\u001a|\u0007B\u0002B!O\u0001\u0007a/\u0001\u000bsK\u0006$7+_7c_2\feN\\8uCRLwN\\\u0001\u000fe\u0016\fG-\u00118o_R\fG/[8o)\t\u00119)\u0001\tsK\u0006$gj\u001c8F[B$\u0018\u0010\u0016:fKR1!\u0011\rBM\u00057CaA!\u0010+\u0001\u00041\bB\u0002B!U\u0001\u0007a/\u0001\u0005sK\u0006$GK]3f)\t\u0011\t'A\u0007o_N+8\r\u001b+sK\u0016$\u0016m\u001a\u000b\u0007\u0003K\u0013)Ka*\t\r\tuB\u00061\u0001w\u0011\u0019\u0011\t\u0005\fa\u0001m\u0006i!/Z1e\u001b>$\u0017NZ5feN$\"A!,\u0011\u0007q\u0014y+\u0003\u0003\u00032\n\u001d$!C'pI&4\u0017.\u001a:t\u00035\u0011X-\u00193Ts6\u0014w\u000e\u001c*fM\u0006Y!/Z1e\u001d\u0006lWMU3g\u0003-\u0011X-\u00193UsB,'+\u001a4\u0002\u001fI,\u0017\rZ\"p]N$\u0018M\u001c;SK\u001a\f\u0011C]3bI\u0006sgn\u001c;bi&|gNU3g\u0003A\u0011X-\u00193N_\u0012Lg-[3sgJ+g-A\u0006sK\u0006$GK]3f%\u00164\u0017a\u0004:fC\u0012$\u0016\u0010]3OC6,'+\u001a4\u0015\u0005\t\u0015\u0007c\u0001?\u0003H&!!\u0011\u001aB\u000f\u0005!!\u0016\u0010]3OC6,\u0017a\u0004:fC\u0012$V-\u001c9mCR,'+\u001a4\u0015\u0005\t=\u0007c\u0001?\u0003R&!!1\u001bB4\u0005!!V-\u001c9mCR,\u0017A\u0004:fC\u0012\u001c\u0015m]3EK\u001a\u0014VM\u001a\u000b\u0003\u00053\u00042\u0001 Bn\u0013\u0011\u0011iNa\u001a\u0003\u000f\r\u000b7/\u001a#fM\u0006i!/Z1e-\u0006dG)\u001a4SK\u001a$\"Aa9\u0011\u0007q\u0014)/\u0003\u0003\u0003h\n\u001d$A\u0002,bY\u0012+g-\u0001\u0007sK\u0006$\u0017\nZ3oiJ+g\r\u0006\u0002\u0003nB\u0019APa<\n\t\tE(q\r\u0002\u0006\u0013\u0012,g\u000e^\u0001\u000fe\u0016\fG\rV=qK\u0012+gMU3g)\t\u00119\u0010E\u0002}\u0005sLAAa?\u0003h\t9A+\u001f9f\t\u00164\u0017\u0001\u0005:fC\u0012lU-\u001c2fe\u0012+gMU3g)\t\u0019\t\u0001E\u0002}\u0007\u0007IAa!\u0002\u0003h\tIQ*Z7cKJ$UMZ\u0001\u0012KJ\u0014xN\u001d\"bINKwM\\1ukJ,G\u0003BAS\u0007\u0017Aq!a#=\u0001\u0004\t\u0019\"\u0001\fj]\u001a,'/T3uQ>$\u0017\t\u001c;fe:\fG/\u001b<f)\u001dI7\u0011CB\u000b\u0007OAqaa\u0005>\u0001\u0004\u0011\t'A\u0002gk:Dqaa\u0006>\u0001\u0004\u0019I\"A\u0004be\u001e$\b/Z:\u0011\r\rm1\u0011\u0005B\u0017\u001d\rQ6QD\u0005\u0004\u0007?)\u0016a\u00029bG.\fw-Z\u0005\u0005\u0007G\u0019)C\u0001\u0003MSN$(bAB\u0010+\"91\u0011F\u001fA\u0002\t5\u0012A\u0002:fgR\u0004X-\u0001\boK^d\u0015M_=UsB,'+\u001a4\u0015\t\r=2Q\u0007\t\u0004y\u000eE\u0012\u0002BB\u001a\u0005g\u0011\u0001\u0002T1{sRK\b/\u001a\u0005\u0007\u0003ss\u0004\u0019\u0001<\u0002-9,w\u000fT1{sRK\b/\u001a*fM\u0006sG-\u00117jCN$baa\f\u0004<\ru\u0002BBA]\u007f\u0001\u0007a\u000f\u0003\u0004\u0004@}\u0002\rA^\u0001\u0002U\u0006YAo\u001c+za\u0016,%O]8s)\u0011\u0019)ea\u0013\u0011\u0007q\u001c9%\u0003\u0003\u0004J\tM\"!\u0003+za\u0016,%O]8s\u0011\u001d\u0019i\u0005\u0011a\u0001\u0007\u001f\n\u0011!\u001a\t\u0004I\u000eE\u0013bAB*#\n9R*[:tS:<'+Z9vSJ,W.\u001a8u\u000bJ\u0014xN\u001d\u0002\f\u0019\u0006T\u0018\u0010V=qKJ+gmE\u0003B\u0007_\u0019I\u0006E\u0002}\u00077JAa!\u0018\u00034\t)b\t\\1h\u0003\u001etwn\u001d;jG\u000e{W\u000e\u001d7fi\u0016\u0014H\u0003BB1\u0007K\u00022aa\u0019B\u001b\u0005)\u0001BBA]\u0007\u0002\u0007a/\u0001\beK\u001aLg.\u001a3BiJ+h.\u00133\u0011\u0007q\u001cY'C\u0002\u0004n\u0015\u0014QAU;o\u0013\u0012\f\u0011\u0001\u001d\t\u0004I\u000eM\u0014bAB;#\n)\u0001\u000b[1tK\u0006\u00012m\\7qY\u0016$X-\u00138uKJt\u0017\r\u001c\u000b\u0004S\u000em\u0004bBAh\r\u0002\u0007\u00111H\u0001\tG>l\u0007\u000f\\3uKR\u0019\u0011n!!\t\u000f\u0005=w\t1\u0001\u0002<\u0005!An\\1e)\rI7q\u0011\u0005\b\u0003\u001fD\u0005\u0019AA\u001e\u0005Ma\u0015M_=UsB,'+\u001a4B]\u0012\fE.[1t'\rI5\u0011\r\u000b\u0007\u0007\u001f\u001b\tja%\u0011\u0007\r\r\u0014\n\u0003\u0004\u0002:2\u0003\rA\u001e\u0005\u0007\u0007\u007fa\u0005\u0019\u0001<\u0015\u0007%\u001c9\nC\u0004\u0002P6\u0003\r!a\u000f"
)
public abstract class UnPickler {
   public final ArrayBuffer scala$reflect$internal$pickling$UnPickler$$completingStack = new ArrayBuffer(24);

   public abstract SymbolTable symbolTable();

   public void unpickle(final byte[] bytes, final int offset, final Symbols.ClassSymbol classRoot, final Symbols.ModuleSymbol moduleRoot, final String filename) {
      try {
         SymbolTable var10000;
         boolean var10001;
         label46: {
            label45: {
               label44: {
                  var10000 = this.symbolTable();
                  Symbols.NoSymbol var6 = this.symbolTable().NoSymbol();
                  if (classRoot == null) {
                     if (var6 == null) {
                        break label44;
                     }
                  } else if (classRoot.equals(var6)) {
                     break label44;
                  }

                  Symbols.NoSymbol var7 = this.symbolTable().NoSymbol();
                  if (moduleRoot == null) {
                     if (var7 != null) {
                        break label45;
                     }
                  } else if (!moduleRoot.equals(var7)) {
                     break label45;
                  }
               }

               var10001 = false;
               break label46;
            }

            var10001 = true;
         }

         boolean assert_assertion = var10001;
         if (var10000 == null) {
            throw null;
         } else {
            SymbolTable assert_this = var10000;
            if (!assert_assertion) {
               throw assert_this.throwAssertionError($anonfun$unpickle$1(classRoot, moduleRoot));
            } else {
               assert_this = null;
               (new Scan(bytes, offset, classRoot, moduleRoot, filename)).run();
            }
         }
      } catch (Throwable var11) {
         if (var11 != null && .MODULE$.apply(var11)) {
            var11.printStackTrace();
            throw new RuntimeException((new StringBuilder(35)).append("error reading Scala signature of ").append(filename).append(": ").append(var11.getMessage()).toString());
         } else {
            throw var11;
         }
      }
   }

   // $FF: synthetic method
   public static final String $anonfun$unpickle$1(final Symbols.ClassSymbol classRoot$1, final Symbols.ModuleSymbol moduleRoot$1) {
      return (new StringBuilder(52)).append("The Unpickler expects a class and module symbol: ").append(classRoot$1).append(" - ").append(moduleRoot$1).toString();
   }

   public class Scan extends PickleBuffer {
      private final Symbols.ClassSymbol classRoot;
      private final Symbols.ModuleSymbol moduleRoot;
      private final String filename;
      private final Mirrors.RootsBase loadingMirror;
      public final int[] scala$reflect$internal$pickling$UnPickler$Scan$$index;
      private final Object[] entries;
      private final HashMap symScopes;
      // $FF: synthetic field
      public final UnPickler $outer;

      private void expect(final int expected, final Function0 msg) {
         int tag = this.readByte();
         if (tag != expected) {
            throw this.errorBadSignature((new StringBuilder(3)).append(msg.apply()).append(" (").append(tag).append(")").toString());
         }
      }

      private Object runAtIndex(final int i, final Function0 body) {
         int saved = this.readIndex();
         this.readIndex_$eq(this.scala$reflect$internal$pickling$UnPickler$Scan$$index[i]);

         Object var10000;
         try {
            var10000 = body.apply();
         } finally {
            this.readIndex_$eq(saved);
         }

         return var10000;
      }

      public void run() {
         for(int var7 = 0; var7 < this.scala$reflect$internal$pickling$UnPickler$Scan$$index.length; ++var7) {
            if (this.entries[var7] == null && this.isSymbolEntry(var7)) {
               int runAtIndex_saved = this.readIndex();
               this.readIndex_$eq(this.scala$reflect$internal$pickling$UnPickler$Scan$$index[var7]);

               try {
                  this.entries[var7] = this.readSymbol();
               } finally {
                  this.readIndex_$eq(runAtIndex_saved);
               }

               Object var2 = null;
            }
         }

         for(int var20 = 0; var20 < this.scala$reflect$internal$pickling$UnPickler$Scan$$index.length; ++var20) {
            if (this.entries[var20] == null) {
               if (this.isSymbolAnnotationEntry(var20)) {
                  int runAtIndex_saved = this.readIndex();
                  this.readIndex_$eq(this.scala$reflect$internal$pickling$UnPickler$Scan$$index[var20]);

                  try {
                     this.readSymbolAnnotation();
                  } finally {
                     this.readIndex_$eq(runAtIndex_saved);
                  }

                  Object var4 = null;
               } else if (this.isChildrenEntry(var20)) {
                  int runAtIndex_saved = this.readIndex();
                  this.readIndex_$eq(this.scala$reflect$internal$pickling$UnPickler$Scan$$index[var20]);

                  try {
                     this.readChildren();
                  } finally {
                     this.readIndex_$eq(runAtIndex_saved);
                  }

                  Object var6 = null;
               }
            }
         }

      }

      private void checkVersion() {
         int major = this.readNat();
         int minor = this.readNat();
         if (major != PickleFormat$.MODULE$.MajorVersion() || minor > PickleFormat$.MODULE$.MinorVersion()) {
            throw new IOException((new StringBuilder(61)).append("Scala signature ").append(this.classRoot.decodedName()).append(" has wrong version\n expected: ").append(PickleFormat$.MODULE$.MajorVersion()).append(".").append(PickleFormat$.MODULE$.MinorVersion()).append("\n found: ").append(major).append(".").append(minor).append(" in ").append(this.filename).toString());
         }
      }

      public Scopes.Scope symScope(final Symbols.Symbol sym) {
         return (Scopes.Scope)this.symScopes.getOrElseUpdate(sym, () -> this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().newScope());
      }

      public boolean isSymbolEntry(final int i) {
         int tag = this.bytes()[this.scala$reflect$internal$pickling$UnPickler$Scan$$index[i]];
         return 3 <= tag && tag <= 8 && (tag != 6 || !this.isRefinementSymbolEntry(i));
      }

      public boolean isSymbolRef(final int i) {
         byte tag = this.bytes()[this.scala$reflect$internal$pickling$UnPickler$Scan$$index[i]];
         return 3 <= tag && tag <= 10;
      }

      public boolean isNameEntry(final int i) {
         int tag = this.bytes()[this.scala$reflect$internal$pickling$UnPickler$Scan$$index[i]];
         return tag == 1 || tag == 2;
      }

      public boolean isSymbolAnnotationEntry(final int i) {
         return this.bytes()[this.scala$reflect$internal$pickling$UnPickler$Scan$$index[i]] == 40;
      }

      public boolean isChildrenEntry(final int i) {
         return this.bytes()[this.scala$reflect$internal$pickling$UnPickler$Scan$$index[i]] == 41;
      }

      public boolean isRefinementSymbolEntry(final int i) {
         int savedIndex = this.readIndex();
         this.readIndex_$eq(this.scala$reflect$internal$pickling$UnPickler$Scan$$index[i]);
         int tag = this.readByte();
         SymbolTable var10000 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
         boolean assert_assertion = tag == 6;
         if (var10000 == null) {
            throw null;
         } else {
            SymbolTable assert_this = var10000;
            if (!assert_assertion) {
               throw assert_this.throwAssertionError("Entry must be a class symbol");
            } else {
               label26: {
                  label25: {
                     assert_this = null;
                     this.readNat();
                     Names.Name var9 = this.readNameRef();
                     Names.TypeName var5 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().tpnme().REFINE_CLASS_NAME();
                     if (var9 == null) {
                        if (var5 == null) {
                           break label25;
                        }
                     } else if (var9.equals(var5)) {
                        break label25;
                     }

                     var10 = false;
                     break label26;
                  }

                  var10 = true;
               }

               boolean result = var10;
               this.readIndex_$eq(savedIndex);
               return result;
            }
         }
      }

      public Object at(final int i, final Function0 op) {
         Object r = this.entries[i];
         if (r == null) {
            int savedIndex = this.readIndex();
            this.readIndex_$eq(this.scala$reflect$internal$pickling$UnPickler$Scan$$index[i]);
            r = op.apply();
            SymbolTable var10000 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
            boolean assert_assertion = this.entries[i] == null;
            if (var10000 == null) {
               throw null;
            }

            SymbolTable assert_this = var10000;
            if (!assert_assertion) {
               throw assert_this.throwAssertionError($anonfun$at$1(this, i));
            }

            assert_this = null;
            this.entries[i] = r;
            this.readIndex_$eq(savedIndex);
         }

         return r;
      }

      public Names.Name readName() {
         int tag = this.readByte();
         int len = this.readNat();
         switch (tag) {
            case 1:
               SymbolTable var7 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               byte[] var8 = this.bytes();
               int newTermName_offset = this.readIndex();
               byte[] newTermName_bs = var8;
               if (var7 == null) {
                  throw null;
               }

               return Names.newTermName$(var7, (byte[])newTermName_bs, newTermName_offset, len);
            case 2:
               SymbolTable var10000 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               byte[] var10001 = this.bytes();
               int newTypeName_offset = this.readIndex();
               byte[] newTypeName_bs = var10001;
               if (var10000 == null) {
                  throw null;
               }

               return Names.newTypeName$(var10000, (byte[])newTypeName_bs, newTypeName_offset, len);
            default:
               throw this.errorBadSignature((new StringBuilder(14)).append("bad name tag: ").append(tag).toString());
         }
      }

      private int readEnd() {
         return this.readNat() + this.readIndex();
      }

      public Symbols.Symbol readSymbol() {
         int tag = this.readByte();
         int end = this.readEnd();
         switch (tag) {
            case 3:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoSymbol();
            case 9:
            case 10:
               return this.readExtSymbol$1(tag, end);
            default:
               int nameref = this.readNat();
               Names.Name name = (Names.Name)this.at(nameref, () -> this.readName());
               Symbols.Symbol owner = this.readSymbolRef();
               long flags = Flags$.MODULE$.pickledToRawFlags().apply$mcJJ$sp(this.readLongNat());
               int index = this.readNat();
               Tuple2 var8 = this.isSymbolRef(index) ? new Tuple2(this.at(index, () -> this.readSymbol()), this.readNat()) : new Tuple2(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoSymbol(), index);
               Symbols.Symbol privateWithin = (Symbols.Symbol)var8._1();
               int inforef = var8._2$mcI$sp();
               if (privateWithin == null) {
                  throw new MatchError(var8);
               } else {
                  Object var10001;
                  switch (tag) {
                     case 4:
                     case 5:
                        var10001 = owner.newNonClassSymbol(name.toTypeName(), this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoPosition(), flags & 290463729080860671L);
                        break;
                     case 6:
                        Symbols.Symbol sym = (Symbols.Symbol)(isModuleFlag$1(flags) && this.isModuleClassRoot$1(name, owner) ? this.moduleRoot.moduleClass().setFlag(flags & 290463729080860671L) : (!isModuleFlag$1(flags) && this.isClassRoot$1(name, owner) ? this.classRoot.setFlag(flags & 290463729080860671L) : owner.newClassSymbol(name.toTypeName(), this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoPosition(), flags & 290463729080860671L)));
                        if (!this.atEnd$1(end)) {
                           sym.typeOfThis_$eq(this.newLazyTypeRef(this.readNat()));
                        }

                        var10001 = sym;
                        break;
                     case 7:
                        Symbols.Symbol moduleClass = ((Types.Type)this.at(inforef, () -> this.readType())).typeSymbol();
                        var10001 = this.isModuleRoot$1(name, owner) ? this.moduleRoot.setFlag(flags & 290463729080860671L) : owner.newLinkedModule(moduleClass, flags & 290463729080860671L);
                        break;
                     case 8:
                        if (this.isModuleRoot$1(name, owner)) {
                           throw this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().abort((new StringBuilder(40)).append("VALsym at module root: owner = ").append(owner).append(", name = ").append(name).toString());
                        }

                        var10001 = owner.newTermSymbol(name.toTermName(), this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoPosition(), flags & 290463729080860671L);
                        break;
                     default:
                        throw this.errorBadSignature((new StringBuilder(16)).append("bad symbol tag: ").append(tag).toString());
                  }

                  return this.finishSym$1((Symbols.Symbol)var10001, privateWithin, inforef, end);
               }
         }
      }

      public Types.Type readType() {
         int tag = this.readByte();
         int end = this.readEnd();
         switch (tag) {
            case 11:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoType();
            case 12:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoPrefix();
            case 13:
               return this.readThisType$1();
            case 14:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().SingleType().apply(this.readTypeRef(), this.readSymbolRef().filter((x$2x) -> BoxesRunTime.boxToBoolean($anonfun$readType$4(x$2x))));
            case 15:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().ConstantType().apply(this.readConstantRef());
            case 16:
               return this.mkTypeRef$1(this.readTypeRef(), this.readSymbolRef(), this.readTypes$1(end));
            case 17:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().TypeBounds().apply(this.readTypeRef(), this.readTypeRef());
            case 18:
            case 19:
               return this.CompoundType$1(this.readSymbolRef(), this.readTypes$1(end), tag);
            case 20:
               return this.MethodTypeRef$1(this.readTypeRef(), this.readSymbols$1(end));
            case 21:
               return this.PolyOrNullaryType$1(this.readTypeRef(), this.readSymbols$1(end));
            case 42:
               Types.Type x$3 = this.readTypeRef();
               List x$4 = this.readAnnots$1(end);
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new AnnotatedType(x$4, x$3);
            case 46:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().SuperType().apply(this.readTypeRef(), this.readTypeRef());
            case 48:
               Types.Type x$1 = this.readTypeRef();
               List x$2 = this.readSymbols$1(end);
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new ExistentialType(x$2, x$1);
            default:
               throw new MatchError(tag);
         }
      }

      public Types.Type noSuchTypeTag(final int tag, final int end) {
         throw this.errorBadSignature((new StringBuilder(14)).append("bad type tag: ").append(tag).toString());
      }

      public Constants.Constant readConstant() {
         int tag = this.readByte();
         int len = this.readNat();
         switch (tag) {
            case 24:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Constant(BoxedUnit.UNIT);
            case 25:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Constant(this.readLong(len) != 0L);
            case 26:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Constant((byte)((int)this.readLong(len)));
            case 27:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Constant((short)((int)this.readLong(len)));
            case 28:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Constant((char)((int)this.readLong(len)));
            case 29:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Constant((int)this.readLong(len));
            case 30:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Constant(this.readLong(len));
            case 31:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Constant(Float.intBitsToFloat((int)this.readLong(len)));
            case 32:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Constant(Double.longBitsToDouble(this.readLong(len)));
            case 33:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Constant(this.readNameRef().toString());
            case 34:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Constant((Object)null);
            case 35:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Constant(this.readTypeRef());
            case 36:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Constant(this.readSymbolRef());
            case 37:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Constant((Object)null);
            default:
               return this.noSuchConstantTag(tag, len);
         }
      }

      public Constants.Constant noSuchConstantTag(final int tag, final int len) {
         throw this.errorBadSignature((new StringBuilder(18)).append("bad constant tag: ").append(tag).toString());
      }

      public void readChildren() {
         int tag = this.readByte();
         SymbolTable var10000 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
         boolean assert_assertion = tag == 41;
         if (var10000 == null) {
            throw null;
         } else {
            SymbolTable assert_this = var10000;
            if (!assert_assertion) {
               throw assert_this.throwAssertionError("Entry must be children");
            } else {
               assert_this = null;
               int end = this.readEnd();
               Symbols.Symbol target = this.readSymbolRef();

               while(this.readIndex() != end) {
                  target.addChild(this.readSymbolRef());
               }

            }
         }
      }

      public Trees.Tree readAnnotArg(final int i) {
         switch (this.bytes()[this.scala$reflect$internal$pickling$UnPickler$Scan$$index[i]]) {
            case 49:
               return (Trees.Tree)this.at(i, () -> this.readTree());
            default:
               Constants.Constant var2 = (Constants.Constant)this.at(i, () -> this.readConstant());
               return (this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Literal(var2)).setType(var2.tpe());
         }
      }

      private AnnotationInfos.ClassfileAnnotArg[] readArrayAnnot() {
         this.readByte();
         int end = this.readEnd();
         return (AnnotationInfos.ClassfileAnnotArg[])this.until(end, () -> this.readClassfileAnnotArg(this.readNat())).toArray(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().JavaArgumentTag());
      }

      public AnnotationInfos.ClassfileAnnotArg readClassfileAnnotArg(final int i) {
         switch (this.bytes()[this.scala$reflect$internal$pickling$UnPickler$Scan$$index[i]]) {
            case 43:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new NestedAnnotArg((AnnotationInfos.AnnotationInfo)this.at(i, () -> this.readAnnotation()));
            case 44:
               return (AnnotationInfos.ClassfileAnnotArg)this.at(i, () -> this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new ArrayAnnotArg(this.readArrayAnnot()));
            default:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new LiteralAnnotArg((Constants.Constant)this.at(i, () -> this.readConstant()));
         }
      }

      public AnnotationInfos.AnnotationInfo readAnnotationInfo(final int end) {
         Types.Type atp = this.readTypeRef();
         ListBuffer args = new ListBuffer();
         ListBuffer assocs = new ListBuffer();

         while(this.readIndex() != end) {
            int argref = this.readNat();
            if (this.isNameEntry(argref)) {
               Names.Name name = (Names.Name)this.at(argref, () -> this.readName());
               AnnotationInfos.ClassfileAnnotArg arg = this.readClassfileAnnotArg(this.readNat());
               Object $plus$eq_elem = new Tuple2(name, arg);
               assocs.addOne($plus$eq_elem);
               $plus$eq_elem = null;
            } else {
               Object $plus$eq_elem = this.readAnnotArg(argref);
               args.addOne($plus$eq_elem);
               $plus$eq_elem = null;
            }
         }

         return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().AnnotationInfo().apply(atp, args.toList(), assocs.toList());
      }

      public void readSymbolAnnotation() {
         int expect_expected = 40;
         int expect_tag = this.readByte();
         if (expect_tag != expect_expected) {
            throw this.errorBadSignature((new StringBuilder(3)).append("symbol annotation expected").append(" (").append(expect_tag).append(")").toString());
         } else {
            int end = this.readEnd();
            this.readSymbolRef().addAnnotation(this.readAnnotationInfo(end));
         }
      }

      public AnnotationInfos.AnnotationInfo readAnnotation() {
         int tag = this.readByte();
         if (tag != 43) {
            throw this.errorBadSignature((new StringBuilder(22)).append("annotation expected (").append(tag).append(")").toString());
         } else {
            int end = this.readEnd();
            return this.readAnnotationInfo(end);
         }
      }

      private Trees.Tree readNonEmptyTree(final int tag, final int end) {
         Types.Type tpe = this.readTypeRef();
         Symbols.Symbol sym = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().isTreeSymbolPickled(tag) ? this.readSymbolRef() : null;
         Trees.Tree result = this.readTree$1(tpe, tag, end);
         if (sym != null) {
            result.setSymbol(sym);
         }

         return result.setType(tpe);
      }

      public Trees.Tree readTree() {
         int expect_expected = 49;
         int expect_tag = this.readByte();
         if (expect_tag != expect_expected) {
            throw this.errorBadSignature((new StringBuilder(3)).append("tree expected").append(" (").append(expect_tag).append(")").toString());
         } else {
            int end = this.readEnd();
            int var2 = this.readByte();
            switch (var2) {
               case 1:
                  return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().EmptyTree();
               default:
                  return this.readNonEmptyTree(var2, end);
            }
         }
      }

      public Nothing noSuchTreeTag(final int tag, final int end) {
         return this.errorBadSignature((new StringBuilder(20)).append("unknown tree type (").append(tag).append(")").toString());
      }

      public Trees.Modifiers readModifiers() {
         int tag = this.readNat();
         if (tag != 50) {
            throw this.errorBadSignature((new StringBuilder(27)).append("expected a modifiers tag (").append(tag).append(")").toString());
         } else {
            this.readEnd();
            int pflagsHi = this.readNat();
            int pflagsLo = this.readNat();
            long pflags = ((long)pflagsHi << 32) + (long)pflagsLo;
            long flags = Flags$.MODULE$.pickledToRawFlags().apply$mcJJ$sp(pflags);
            Names.Name privateWithin = this.readNameRef();
            return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Modifiers(flags, privateWithin, scala.collection.immutable.Nil..MODULE$);
         }
      }

      public Symbols.Symbol readSymbolRef() {
         int i = this.readNat();
         Object r = this.entries[i];
         if (r == null) {
            int savedIndex = this.readIndex();
            this.readIndex_$eq(this.scala$reflect$internal$pickling$UnPickler$Scan$$index[i]);
            r = this.readSymbol();
            SymbolTable var10000 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
            boolean assert_assertion = this.entries[i] == null;
            if (var10000 == null) {
               throw null;
            }

            SymbolTable assert_this = var10000;
            if (!assert_assertion) {
               throw assert_this.throwAssertionError($anonfun$readSymbolRef$1(this, i));
            }

            assert_this = null;
            this.entries[i] = r;
            this.readIndex_$eq(savedIndex);
         }

         return (Symbols.Symbol)r;
      }

      public Names.Name readNameRef() {
         return (Names.Name)this.at(this.readNat(), () -> this.readName());
      }

      public Types.Type readTypeRef() {
         return (Types.Type)this.at(this.readNat(), () -> this.readType());
      }

      public Constants.Constant readConstantRef() {
         return (Constants.Constant)this.at(this.readNat(), () -> this.readConstant());
      }

      public AnnotationInfos.AnnotationInfo readAnnotationRef() {
         return (AnnotationInfos.AnnotationInfo)this.at(this.readNat(), () -> this.readAnnotation());
      }

      public Trees.Modifiers readModifiersRef() {
         return (Trees.Modifiers)this.at(this.readNat(), () -> this.readModifiers());
      }

      public Trees.Tree readTreeRef() {
         return (Trees.Tree)this.at(this.readNat(), () -> this.readTree());
      }

      public Names.TypeName readTypeNameRef() {
         return this.readNameRef().toTypeName();
      }

      public Trees.Template readTemplateRef() {
         Trees.Tree var1 = this.readTreeRef();
         if (var1 instanceof Trees.Template) {
            return (Trees.Template)var1;
         } else {
            throw this.errorBadSignature((new StringBuilder(22)).append("expected a template (").append(var1).append(")").toString());
         }
      }

      public Trees.CaseDef readCaseDefRef() {
         Trees.Tree var1 = this.readTreeRef();
         if (var1 instanceof Trees.CaseDef) {
            return (Trees.CaseDef)var1;
         } else {
            throw this.errorBadSignature((new StringBuilder(22)).append("expected a case def (").append(var1).append(")").toString());
         }
      }

      public Trees.ValDef readValDefRef() {
         Trees.Tree var1 = this.readTreeRef();
         if (var1 instanceof Trees.ValDef) {
            return (Trees.ValDef)var1;
         } else {
            throw this.errorBadSignature((new StringBuilder(20)).append("expected a ValDef (").append(var1).append(")").toString());
         }
      }

      public Trees.Ident readIdentRef() {
         Trees.Tree var1 = this.readTreeRef();
         if (var1 instanceof Trees.Ident) {
            return (Trees.Ident)var1;
         } else {
            throw this.errorBadSignature((new StringBuilder(20)).append("expected an Ident (").append(var1).append(")").toString());
         }
      }

      public Trees.TypeDef readTypeDefRef() {
         Trees.Tree var1 = this.readTreeRef();
         if (var1 instanceof Trees.TypeDef) {
            return (Trees.TypeDef)var1;
         } else {
            throw this.errorBadSignature((new StringBuilder(22)).append("expected an TypeDef (").append(var1).append(")").toString());
         }
      }

      public Trees.MemberDef readMemberDefRef() {
         Trees.Tree var1 = this.readTreeRef();
         if (var1 instanceof Trees.MemberDef) {
            return (Trees.MemberDef)var1;
         } else {
            throw this.errorBadSignature((new StringBuilder(24)).append("expected an MemberDef (").append(var1).append(")").toString());
         }
      }

      public Nothing errorBadSignature(final String msg) {
         throw new RuntimeException((new StringBuilder(35)).append("malformed Scala signature of ").append(this.classRoot.name()).append(" at ").append(this.readIndex()).append("; ").append(msg).toString());
      }

      public void inferMethodAlternative(final Trees.Tree fun, final List argtpes, final Types.Type restpe) {
      }

      public Types.LazyType newLazyTypeRef(final int i) {
         return new LazyTypeRef(i);
      }

      public Types.LazyType newLazyTypeRefAndAlias(final int i, final int j) {
         return new LazyTypeRefAndAlias(i, j);
      }

      public Types.TypeError toTypeError(final MissingRequirementError e) {
         return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new TypeError(e.msg());
      }

      // $FF: synthetic method
      public UnPickler scala$reflect$internal$pickling$UnPickler$Scan$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$run$1(final Scan $this, final IntRef i$1) {
         $this.entries[i$1.elem] = $this.readSymbol();
      }

      // $FF: synthetic method
      public static final void $anonfun$run$2(final Scan $this) {
         $this.readSymbolAnnotation();
      }

      // $FF: synthetic method
      public static final void $anonfun$run$3(final Scan $this) {
         $this.readChildren();
      }

      // $FF: synthetic method
      public static final String $anonfun$isRefinementSymbolEntry$1() {
         return "Entry must be a class symbol";
      }

      // $FF: synthetic method
      public static final Object $anonfun$at$1(final Scan $this, final int i$2) {
         return $this.entries[i$2];
      }

      private final boolean atEnd$1(final int end$1) {
         return this.readIndex() == end$1;
      }

      private static final Symbols.Symbol adjust$1(final Symbols.Symbol sym, final int tag$1) {
         return tag$1 == 9 ? sym : sym.moduleClass();
      }

      private final Symbols.Symbol fromName$1(final Names.Name name, final Symbols.Symbol owner$1, final int tag$1) {
         Names.TermName var4 = name.toTermName();
         Names.Name var10000 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().nme().ROOT();
         if (var10000 == null) {
            if (var4 == null) {
               return this.loadingMirror.RootClass();
            }
         } else if (var10000.equals(var4)) {
            return this.loadingMirror.RootClass();
         }

         Names.TermName var5 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().nme().ROOTPKG();
         if (var5 == null) {
            if (var4 == null) {
               return this.loadingMirror.RootPackage();
            }
         } else if (var5.equals(var4)) {
            return this.loadingMirror.RootPackage();
         }

         return adjust$1((Symbols.Symbol)(owner$1 instanceof Symbols.StubSymbol ? this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoSymbol() : owner$1.info().decl(name)), tag$1);
      }

      // $FF: synthetic method
      public static final String $anonfun$readSymbol$2() {
         return "";
      }

      private static final String moduleAdvice$1(final String missing) {
         Option var10000 = ((Option)(missing.startsWith("scala.xml") ? new Some(new Tuple2("org.scala-lang.modules", "scala-xml")) : (missing.startsWith("scala.util.parsing") ? new Some(new Tuple2("org.scala-lang.modules", "scala-parser-combinators")) : (missing.startsWith("scala.swing") ? new Some(new Tuple2("org.scala-lang.modules", "scala-swing")) : (missing.startsWith("scala.collection.parallel") ? new Some(new Tuple2("org.scala-lang.modules", "scala-parallel-collections")) : scala.None..MODULE$))))).map((x0$1) -> {
            if (x0$1 != null) {
               String group = (String)x0$1._1();
               String art = (String)x0$1._2();
               return scala.collection.StringOps..MODULE$.stripMargin$extension((new StringBuilder(170)).append("\n(NOTE: It looks like the ").append(art).append(" module is missing; try adding a dependency on \"").append(group).append("\" : \"").append(art).append("\".\n               |       See https://docs.scala-lang.org/overviews/ for more information.)").toString(), '|');
            } else {
               throw new MatchError((Object)null);
            }
         });
         if (var10000 == null) {
            throw null;
         } else {
            Option getOrElse_this = var10000;
            return (String)(getOrElse_this.isEmpty() ? "" : getOrElse_this.get());
         }
      }

      private final Symbols.Symbol localDummy$1(final Names.Name name$1, final Symbols.Symbol owner$1) {
         return (Symbols.Symbol)(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().nme().isLocalDummyName(name$1) ? owner$1.newLocalDummy(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoPosition()) : this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoSymbol());
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$readSymbol$3(final Scan $this, final Names.Name name$1, final Symbols.Symbol owner$1, final int tag$1) {
         return $this.fromName$1(name$1, owner$1, tag$1);
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$readSymbol$6(final Scan $this, final Symbols.Symbol owner$1, final Names.Name name$1, final int tag$1) {
         StringBuilder var10000 = new StringBuilder(1);
         if (owner$1 == null) {
            throw null;
         } else {
            String advice = moduleAdvice$1(var10000.append(owner$1.fullName('.')).append(".").append(name$1).toString());
            Symbols.Symbol lazyCompletingSymbol = (Symbols.Symbol)($this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().scala$reflect$internal$pickling$UnPickler$$completingStack.isEmpty() ? $this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoSymbol() : (Symbols.Symbol)$this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().scala$reflect$internal$pickling$UnPickler$$completingStack.apply($this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().scala$reflect$internal$pickling$UnPickler$$completingStack.length() - 1));
            StringOps var12 = scala.collection.StringOps..MODULE$;
            String stripMargin$extension_$this = (new StringBuilder(318)).append("|Symbol '").append(name$1.nameKind()).append(" ").append(owner$1.fullName('.')).append(".").append(name$1).append("' is missing from the classpath.\n                    |This symbol is required by '").append(lazyCompletingSymbol.kindString()).append(" ").append(lazyCompletingSymbol.fullName()).append("'.\n                    |Make sure that ").append(name$1.longString()).append(" is in your classpath and check for conflicting dependencies with `-Ylog-classpath`.\n                    |A full rebuild may help if '").append($this.filename).append("' was compiled against an incompatible version of ").append(owner$1.fullName('.')).append(".").append(advice).toString();
            StringOps stripMargin$extension_this = var12;
            String var13 = stripMargin$extension_this.stripMargin$extension(stripMargin$extension_$this, '|');
            Object var10 = null;
            Object var11 = null;
            String missingMessage = var13;
            Names.Name stubName = (Names.Name)(tag$1 == 9 ? name$1 : name$1.toTypeName());
            return owner$1.newStubSymbol(stubName, missingMessage);
         }
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$readSymbol$5(final Scan $this, final Symbols.Symbol owner$1, final Names.Name name$1, final int tag$1) {
         Symbols.Symbol var10000 = adjust$1($this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().mirrorThatLoaded(owner$1).missingHook(owner$1, name$1), tag$1);
         if (var10000 == null) {
            throw null;
         } else {
            Symbols.Symbol orElse_this = var10000;
            return orElse_this != orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? orElse_this : $anonfun$readSymbol$6($this, owner$1, name$1, tag$1);
         }
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$readSymbol$4(final Scan $this, final Names.Name name$1, final Symbols.Symbol owner$1, final int tag$1) {
         Symbols.Symbol var10000 = $this.fromName$1($this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().nme().expandedName(name$1.toTermName(), owner$1), owner$1, tag$1);
         if (var10000 == null) {
            throw null;
         } else {
            Symbols.Symbol orElse_this = var10000;
            return orElse_this != orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? orElse_this : $anonfun$readSymbol$5($this, owner$1, name$1, tag$1);
         }
      }

      private final Symbols.Symbol readExtSymbol$1(final int tag$1, final int end$1) {
         Names.Name name;
         Symbols.Symbol owner;
         label44: {
            name = this.readNameRef();
            owner = (Symbols.Symbol)(this.atEnd$1(end$1) ? this.loadingMirror.RootClass() : this.readSymbolRef());
            Symbols.ClassSymbol var5 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().definitions().ScalaPackageClass();
            if (owner == null) {
               if (var5 != null) {
                  break label44;
               }
            } else if (!owner.equals(var5)) {
               break label44;
            }

            Names.Name var6 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().tpnme().AnyRef();
            if (name == null) {
               if (var6 == null) {
                  return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().definitions().AnyRefClass();
               }
            } else if (name.equals(var6)) {
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().definitions().AnyRefClass();
            }
         }

         Symbols.Symbol var10000 = this.localDummy$1(name, owner);
         if (var10000 == null) {
            throw null;
         } else {
            Symbols.Symbol orElse_this = var10000;
            var10000 = orElse_this != orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? orElse_this : $anonfun$readSymbol$3(this, name, owner, tag$1);
            orElse_this = null;
            if (var10000 == null) {
               throw null;
            } else {
               Symbols.Symbol orElse_this = var10000;
               if (orElse_this != orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                  return orElse_this;
               } else {
                  return $anonfun$readSymbol$4(this, name, owner, tag$1);
               }
            }
         }
      }

      private static final boolean isModuleFlag$1(final long flags$1) {
         return (flags$1 & 256L) != 0L;
      }

      private final boolean isClassRoot$1(final Names.Name name$2, final Symbols.Symbol owner$2) {
         Names.Name var3 = this.classRoot.name();
         if (name$2 == null) {
            if (var3 != null) {
               return false;
            }
         } else if (!name$2.equals(var3)) {
            return false;
         }

         Symbols.Symbol var4 = this.classRoot.owner();
         if (owner$2 == null) {
            if (var4 == null) {
               return true;
            }
         } else if (owner$2.equals(var4)) {
            return true;
         }

         return false;
      }

      private final boolean isModuleRoot$1(final Names.Name name$2, final Symbols.Symbol owner$2) {
         Names.Name var3 = this.moduleRoot.name();
         if (name$2 == null) {
            if (var3 != null) {
               return false;
            }
         } else if (!name$2.equals(var3)) {
            return false;
         }

         Symbols.Symbol var4 = this.moduleRoot.owner();
         if (owner$2 == null) {
            if (var4 == null) {
               return true;
            }
         } else if (owner$2.equals(var4)) {
            return true;
         }

         return false;
      }

      private final boolean isModuleClassRoot$1(final Names.Name name$2, final Symbols.Symbol owner$2) {
         Names.TypeName var3 = ((Names.TermName)this.moduleRoot.name()).toTypeName();
         if (name$2 == null) {
            if (var3 != null) {
               return false;
            }
         } else if (!name$2.equals(var3)) {
            return false;
         }

         Symbols.Symbol var4 = this.moduleRoot.owner();
         if (owner$2 == null) {
            if (var4 == null) {
               return true;
            }
         } else if (owner$2.equals(var4)) {
            return true;
         }

         return false;
      }

      private static final long pflags$1(final long flags$1) {
         return flags$1 & 290463729080860671L;
      }

      private final boolean shouldEnterInOwnerScope$1(final Symbols.Symbol sym$1) {
         if (sym$1.owner().isClass() && !sym$1.equals(this.classRoot)) {
            Symbols.ModuleSymbol var2 = this.moduleRoot;
            if (sym$1 == null) {
               if (var2 == null) {
                  return false;
               }
            } else if (sym$1.equals(var2)) {
               return false;
            }

            if (!sym$1.isModuleClass() && !sym$1.isRefinementClass() && !sym$1.isTypeParameter() && !sym$1.isExistentiallyBound()) {
               Names.Name var10000 = sym$1._rawname();
               Names.TypeName var3 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().tpnme().LOCAL_CHILD();
               if (var10000 == null) {
                  if (var3 == null) {
                     return false;
                  }
               } else if (var10000.equals(var3)) {
                  return false;
               }

               if (!this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().nme().isLocalDummyName(sym$1._rawname())) {
                  return true;
               }
            }
         }

         return false;
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$readSymbol$9(final Symbols.Symbol sym$1) {
         return sym$1;
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$readSymbol$10(final Symbols.Symbol sym$1) {
         return sym$1;
      }

      private final Symbols.Symbol finishSym$1(final Symbols.Symbol sym, final Symbols.Symbol privateWithin$1, final int inforef$1, final int end$1) {
         SymbolTable var10000 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
         long markFlagsCompleted_mask = -1L;
         if (var10000 == null) {
            throw null;
         } else {
            Symbols.markFlagsCompleted$(var10000, sym, markFlagsCompleted_mask);
            sym.privateWithin_$eq(privateWithin$1);
            Types.LazyType var13;
            if (this.atEnd$1(end$1)) {
               SymbolTable var10001 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               boolean assert_assertion = !sym.isSuperAccessor();
               if (var10001 == null) {
                  throw null;
               }

               SymbolTable assert_this = var10001;
               if (!assert_assertion) {
                  throw assert_this.throwAssertionError(sym);
               }

               assert_this = null;
               var13 = this.newLazyTypeRef(inforef$1);
            } else {
               SymbolTable var14 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               boolean assert_assertionx = sym.isSuperAccessor() || sym.isParamAccessor();
               if (var14 == null) {
                  throw null;
               }

               SymbolTable assert_this = var14;
               if (!assert_assertionx) {
                  throw assert_this.throwAssertionError(sym);
               }

               assert_this = null;
               var13 = this.newLazyTypeRefAndAlias(inforef$1, this.readNat());
            }

            sym.info_$eq(var13);
            if (this.shouldEnterInOwnerScope$1(sym)) {
               this.symScope(sym.owner()).enter(sym);
            }

            return sym;
         }
      }

      private final List all$1(final Function0 body, final int end$2) {
         return this.until(end$2, body);
      }

      private final List readTypes$1(final int end$2) {
         Function0 all$1_body = () -> this.readTypeRef();
         return this.until(end$2, all$1_body);
      }

      private final List readSymbols$1(final int end$2) {
         Function0 all$1_body = () -> this.readSymbolRef();
         return this.until(end$2, all$1_body);
      }

      private final List readAnnots$1(final int end$2) {
         Function0 all$1_body = () -> this.readAnnotationRef();
         return this.until(end$2, all$1_body);
      }

      private final Types.Type MethodTypeRef$1(final Types.Type restpe, final List params) {
         Types.NoType$ var3 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoType();
         if (restpe == null) {
            if (var3 == null) {
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoType();
            }
         } else if (restpe.equals(var3)) {
            return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoType();
         }

         if (!params.contains(this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoSymbol())) {
            return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new MethodType(params, restpe);
         } else {
            return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().NoType();
         }
      }

      private final Types.Type PolyOrNullaryType$1(final Types.Type restpe, final List tparams) {
         return (Types.Type)(scala.collection.immutable.Nil..MODULE$.equals(tparams) ? this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new NullaryMethodType(restpe) : this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new PolyType(tparams, restpe));
      }

      private final Types.Type CompoundType$1(final Symbols.Symbol clazz, final List parents, final int tag$2) {
         switch (tag$2) {
            case 18:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().RefinedType().apply(parents, this.symScope(clazz), clazz);
            case 19:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new ClassInfoType(parents, this.symScope(clazz), clazz);
            default:
               throw new MatchError(tag$2);
         }
      }

      private final Types.Type readThisType$1() {
         Symbols.Symbol var2 = this.readSymbolRef();
         Symbols.Symbol sym = var2 instanceof Symbols.StubSymbol ? ((Symbols.Symbol)((Symbols.StubSymbol)var2)).setFlag(16640L) : var2;
         return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().ThisType().apply(sym);
      }

      private final Types.Type mkTypeRef$1(final Types.Type pre, final Symbols.Symbol sym, final List args) {
         if (this.classRoot.isJava()) {
            Symbols.ClassSymbol var4 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().definitions().ObjectClass();
            if (sym == null) {
               if (var4 == null) {
                  return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().definitions().ObjectTpeJava();
               }
            } else if (sym.equals(var4)) {
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().definitions().ObjectTpeJava();
            }
         }

         return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().TypeRef().apply(pre, sym, args);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$readType$4(final Symbols.Symbol x$2) {
         return x$2.isStable();
      }

      // $FF: synthetic method
      public static final String $anonfun$readChildren$1() {
         return "Entry must be children";
      }

      // $FF: synthetic method
      public static final String $anonfun$readSymbolAnnotation$1() {
         return "symbol annotation expected";
      }

      private final List all$2(final Function0 body, final int end$3) {
         return this.until(end$3, body);
      }

      private final List rep$1(final Function0 body) {
         return this.times(this.readNat(), body);
      }

      // $FF: synthetic method
      public static final Types.Type $anonfun$readNonEmptyTree$1(final Trees.Tree x$4) {
         return x$4.tpe();
      }

      private final Trees.Apply fixApply$1(final Trees.Apply tree, final Types.Type tpe) {
         if (tree == null) {
            throw new MatchError((Object)null);
         } else {
            Trees.Tree fun = tree.fun();
            List args = tree.args();
            if (fun.symbol().isOverloaded()) {
               fun.setType(fun.symbol().info());
               if (args == null) {
                  throw null;
               }

               Object var10002;
               if (args == scala.collection.immutable.Nil..MODULE$) {
                  var10002 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Trees.Tree)args.head()).tpe(), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)args.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Trees.Tree)map_rest.head()).tpe(), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var10002 = map_h;
               }

               Object var9 = null;
               Object var10 = null;
               Object var11 = null;
               Object var12 = null;
               this.inferMethodAlternative(fun, (List)var10002, tpe);
            }

            return tree;
         }
      }

      private final Trees.Tree ref$1() {
         return this.readTreeRef();
      }

      private final Trees.CaseDef caseRef$1() {
         return this.readCaseDefRef();
      }

      private final Trees.Modifiers modsRef$1() {
         return this.readModifiersRef();
      }

      private final Trees.Template implRef$1() {
         return this.readTemplateRef();
      }

      private final Names.Name nameRef$1() {
         return this.readNameRef();
      }

      private final Trees.TypeDef tparamRef$1() {
         return this.readTypeDefRef();
      }

      private final Trees.ValDef vparamRef$1() {
         return this.readValDefRef();
      }

      private final Trees.MemberDef memberRef$1() {
         return this.readMemberDefRef();
      }

      private final Constants.Constant constRef$1() {
         return this.readConstantRef();
      }

      private final Trees.Ident idRef$1() {
         return this.readIdentRef();
      }

      private final Names.TermName termNameRef$1() {
         return this.readNameRef().toTermName();
      }

      private final Names.TypeName typeNameRef$1() {
         return this.readNameRef().toTypeName();
      }

      private final Trees.RefTree refTreeRef$1() {
         Trees.Tree var1 = this.readTreeRef();
         if (var1 instanceof Trees.RefTree) {
            return (Trees.RefTree)var1;
         } else {
            throw this.errorBadSignature((new StringBuilder(24)).append("RefTree expected, found ").append(var1.shortClass()).toString());
         }
      }

      private final List selectorsRef$1(final int end$3) {
         Function0 all$2_body = () -> this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new ImportSelector(this.readNameRef(), -1, this.readNameRef(), -1);
         return this.until(end$3, all$2_body);
      }

      private final Trees.Tree readTree$1(final Types.Type tpe, final int tag$3, final int end$3) {
         switch (tag$3) {
            case 2:
               SymbolTable var62 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               Trees.RefTree var78 = this.refTreeRef$1();
               Function0 all$2_body = () -> this.ref$1();
               List var92 = this.until(end$3, all$2_body);
               Object var42 = null;
               return var62.new PackageDef(var78, var92);
            case 3:
               SymbolTable var61 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               Trees.Modifiers var77 = this.modsRef$1();
               Names.TypeName var91 = this.typeNameRef$1();
               Function0 rep$1_body = () -> this.tparamRef$1();
               List var96 = this.times(this.readNat(), rep$1_body);
               Object var35 = null;
               return var61.new ClassDef(var77, var91, var96, this.implRef$1());
            case 4:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new ModuleDef(this.modsRef$1(), this.termNameRef$1(), this.implRef$1());
            case 5:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new ValDef(this.modsRef$1(), this.termNameRef$1(), this.ref$1(), this.ref$1());
            case 6:
               SymbolTable var60 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               Trees.Modifiers var76 = this.modsRef$1();
               Names.TermName var90 = this.termNameRef$1();
               Function0 rep$1_body = () -> this.tparamRef$1();
               List var95 = this.times(this.readNat(), rep$1_body);
               Object var36 = null;
               Function0 rep$1_body = () -> {
                  Function0 rep$1_body = () -> this.vparamRef$1();
                  return this.times(this.readNat(), rep$1_body);
               };
               List var10006 = this.times(this.readNat(), rep$1_body);
               Object var37 = null;
               return var60.new DefDef(var76, var90, var95, var10006, this.ref$1(), this.ref$1());
            case 7:
               SymbolTable var59 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               Trees.Modifiers var75 = this.modsRef$1();
               Names.TypeName var89 = this.typeNameRef$1();
               Function0 rep$1_body = () -> this.tparamRef$1();
               List var94 = this.times(this.readNat(), rep$1_body);
               Object var46 = null;
               return var59.new TypeDef(var75, var89, var94, this.ref$1());
            case 8:
               SymbolTable var58 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               Names.TermName var74 = this.termNameRef$1();
               Function0 rep$1_body = () -> this.idRef$1();
               List var88 = this.times(this.readNat(), rep$1_body);
               Object var40 = null;
               return var58.new LabelDef(var74, var88, this.ref$1());
            case 9:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Import(this.ref$1(), this.selectorsRef$1(end$3));
            case 10:
            case 11:
            case 15:
            default:
               throw this.noSuchTreeTag(tag$3, end$3);
            case 12:
               SymbolTable var57 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               Function0 rep$1_body = () -> this.ref$1();
               List var73 = this.times(this.readNat(), rep$1_body);
               Object var43 = null;
               Trees.ValDef var87 = this.vparamRef$1();
               Function0 all$2_body = () -> this.ref$1();
               List var93 = this.until(end$3, all$2_body);
               Object var44 = null;
               return var57.new Template(var73, var87, var93);
            case 13:
               Function0 all$2_body = () -> this.ref$1();
               List var10000 = this.until(end$3, all$2_body);
               Object var29 = null;
               List var4 = var10000;
               if (var4 != null) {
                  Option var5 = scala.package..MODULE$.$colon$plus().unapply(var4);
                  if (!var5.isEmpty()) {
                     List stats = (List)((Tuple2)var5.get())._1();
                     Trees.Tree expr = (Trees.Tree)((Tuple2)var5.get())._2();
                     return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Block(stats, expr);
                  }
               }

               throw new MatchError(var4);
            case 14:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new CaseDef(this.ref$1(), this.ref$1(), this.ref$1());
            case 16:
               SymbolTable var56 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               Function0 all$2_body = () -> this.ref$1();
               List var72 = this.until(end$3, all$2_body);
               Object var31 = null;
               return var56.new Alternative(var72);
            case 17:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Star(this.ref$1());
            case 18:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Bind(this.readNameRef(), this.readTreeRef());
            case 19:
               SymbolTable var55 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               Trees.Tree var71 = this.ref$1();
               Function0 all$2_body = () -> this.ref$1();
               List var86 = this.until(end$3, all$2_body);
               Object var47 = null;
               return var55.new UnApply(var71, var86);
            case 20:
               SymbolTable var54 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               Trees.Tree var70 = this.readTreeRef();
               Function0 all$2_body = () -> this.ref$1();
               List var85 = this.until(end$3, all$2_body);
               Object var34 = null;
               return var54.new ArrayValue(var70, var85);
            case 21:
               SymbolTable var53 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               Function0 rep$1_body = () -> this.vparamRef$1();
               List var69 = this.times(this.readNat(), rep$1_body);
               Object var39 = null;
               return var53.new Function(var69, this.ref$1());
            case 22:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Assign(this.ref$1(), this.ref$1());
            case 23:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new If(this.readTreeRef(), this.readTreeRef(), this.readTreeRef());
            case 24:
               SymbolTable var52 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               Trees.Tree var68 = this.ref$1();
               Function0 all$2_body = () -> this.caseRef$1();
               List var84 = this.until(end$3, all$2_body);
               Object var41 = null;
               return var52.new Match(var68, var84);
            case 25:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Return(this.ref$1());
            case 26:
               SymbolTable var51 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               Trees.Tree var67 = this.ref$1();
               Function0 rep$1_body = () -> this.caseRef$1();
               List var83 = this.times(this.readNat(), rep$1_body);
               Object var45 = null;
               return var51.new Try(var67, var83, this.ref$1());
            case 27:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Throw(this.ref$1());
            case 28:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new New(this.ref$1());
            case 29:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Typed(this.readTreeRef(), this.readTreeRef());
            case 30:
               SymbolTable var50 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               Trees.Tree var66 = this.readTreeRef();
               Function0 all$2_body = () -> this.ref$1();
               List var82 = this.until(end$3, all$2_body);
               Object var30 = null;
               return var50.new TypeApply(var66, var82);
            case 31:
               SymbolTable var65 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               Trees.Tree var81 = this.readTreeRef();
               Function0 all$2_body = () -> this.ref$1();
               List var10005 = this.until(end$3, all$2_body);
               Object var28 = null;
               return this.fixApply$1(var65.new Apply(var81, var10005), tpe);
            case 32:
               SymbolTable var49 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               Trees.Tree var64 = this.readTreeRef();
               Function0 all$2_body = () -> this.ref$1();
               List var80 = this.until(end$3, all$2_body);
               Object var33 = null;
               return var49.new ApplyDynamic(var64, var80);
            case 33:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Super(this.ref$1(), this.typeNameRef$1());
            case 34:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new This(this.typeNameRef$1());
            case 35:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Select(this.readTreeRef(), this.readNameRef());
            case 36:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Ident(this.readNameRef());
            case 37:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Literal(this.readConstantRef());
            case 38:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new TypeTree();
            case 39:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new Annotated(this.readTreeRef(), this.readTreeRef());
            case 40:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new SingletonTypeTree(this.ref$1());
            case 41:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new SelectFromTypeTree(this.ref$1(), this.typeNameRef$1());
            case 42:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new CompoundTypeTree(this.implRef$1());
            case 43:
               SymbolTable var48 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               Trees.Tree var63 = this.readTreeRef();
               Function0 all$2_body = () -> this.ref$1();
               List var79 = this.until(end$3, all$2_body);
               Object var32 = null;
               return var48.new AppliedTypeTree(var63, var79);
            case 44:
               return this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().new TypeBoundsTree(this.ref$1(), this.ref$1());
            case 45:
               SymbolTable var10002 = this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               Trees.Tree var10003 = this.ref$1();
               Function0 all$2_body = () -> this.memberRef$1();
               List var10004 = this.until(end$3, all$2_body);
               Object var38 = null;
               return var10002.new ExistentialTypeTree(var10003, var10004);
         }
      }

      // $FF: synthetic method
      public static final String $anonfun$readTree$1() {
         return "tree expected";
      }

      // $FF: synthetic method
      public static final Object $anonfun$readSymbolRef$1(final Scan $this, final int i$3) {
         return $this.entries[i$3];
      }

      public Scan(final byte[] _bytes, final int offset, final Symbols.ClassSymbol classRoot, final Symbols.ModuleSymbol moduleRoot, final String filename) {
         this.classRoot = classRoot;
         this.moduleRoot = moduleRoot;
         this.filename = filename;
         if (UnPickler.this == null) {
            throw null;
         } else {
            this.$outer = UnPickler.this;
            super(_bytes, offset, -1);
            this.checkVersion();
            this.loadingMirror = UnPickler.this.symbolTable().mirrorThatLoaded(classRoot);
            this.scala$reflect$internal$pickling$UnPickler$Scan$$index = this.createIndex();
            this.entries = new Object[this.scala$reflect$internal$pickling$UnPickler$Scan$$index.length];
            this.symScopes = (HashMap)MapFactory.apply$(scala.collection.mutable.HashMap..MODULE$, scala.collection.immutable.Nil..MODULE$);
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }

      private class LazyTypeRef extends Types.LazyType implements Types.FlagAgnosticCompleter {
         private final int i;
         private final int definedAtRunId;
         private final Phase p;
         // $FF: synthetic field
         public final Scan $outer;

         public void completeInternal(final Symbols.Symbol sym) {
            try {
               ArrayBuffer var10000 = this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().scala$reflect$internal$pickling$UnPickler$$completingStack;
               if (var10000 == null) {
                  throw null;
               }

               var10000.addOne(sym);
               Types.Type tp = (Types.Type)this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().at(this.i, () -> this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().readType());
               if (this.p != null) {
                  this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().slowButSafeEnteringPhase(this.p, () -> sym.setInfo(tp));
               }

               if (this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().currentRunId() != this.definedAtRunId) {
                  sym.setInfo(this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().adaptToNewRunMap().apply(tp));
               }
            } catch (MissingRequirementError var7) {
               throw this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().toTypeError(var7);
            } finally {
               this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().scala$reflect$internal$pickling$UnPickler$$completingStack.remove(this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().scala$reflect$internal$pickling$UnPickler$$completingStack.length() - 1);
            }

         }

         public void complete(final Symbols.Symbol sym) {
            this.completeInternal(sym);
            if (!this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().isCompilerUniverse()) {
               SymbolTable var10000 = this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable();
               if (var10000 == null) {
                  throw null;
               } else {
                  Symbols.markAllCompleted$(var10000, sym);
               }
            }
         }

         public void load(final Symbols.Symbol sym) {
            this.complete(sym);
         }

         // $FF: synthetic method
         public Scan scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRef$$$outer() {
            return this.$outer;
         }

         public LazyTypeRef(final int i) {
            this.i = i;
            if (Scan.this == null) {
               throw null;
            } else {
               this.$outer = Scan.this;
               super();
               this.definedAtRunId = Scan.this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().currentRunId();
               this.p = Scan.this.scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().phase();
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      }

      private class LazyTypeRefAndAlias extends LazyTypeRef {
         private final int j;

         public void completeInternal(final Symbols.Symbol sym) {
            try {
               super.completeInternal(sym);
               U create_e = (U)((Symbols.Symbol)this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRefAndAlias$$$outer().at(this.j, () -> this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRefAndAlias$$$outer().readSymbol()));
               ObjectRef var10000 = new ObjectRef(create_e);
               create_e = (U)null;
               ObjectRef alias = var10000;
               if (((Symbols.Symbol)alias.elem).isOverloaded()) {
                  alias.elem = (Symbols.Symbol)this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRefAndAlias$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().slowButSafeEnteringPhase(this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRefAndAlias$$$outer().scala$reflect$internal$pickling$UnPickler$Scan$$$outer().symbolTable().picklerPhase(), () -> ((Symbols.Symbol)alias.elem).suchThat((alt) -> BoxesRunTime.boxToBoolean($anonfun$completeInternal$5(sym, alt))));
               }

               ((Symbols.TermSymbol)sym).setAlias((Symbols.Symbol)alias.elem);
            } catch (MissingRequirementError var5) {
               throw this.scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRefAndAlias$$$outer().toTypeError(var5);
            }
         }

         // $FF: synthetic method
         public Scan scala$reflect$internal$pickling$UnPickler$Scan$LazyTypeRefAndAlias$$$outer() {
            return this.$outer;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$completeInternal$5(final Symbols.Symbol sym$3, final Symbols.Symbol alt) {
            return sym$3.isParamAccessor() ? alt.isParamAccessor() : sym$3.tpe_$times().$eq$colon$eq(sym$3.owner().thisType().memberType(alt));
         }

         public LazyTypeRefAndAlias(final int i, final int j) {
            super(i);
            this.j = j;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      }
   }
}
