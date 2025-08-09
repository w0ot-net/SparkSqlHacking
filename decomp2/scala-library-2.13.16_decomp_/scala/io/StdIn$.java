package scala.io;

import scala.Tuple2;
import scala.Tuple3;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;

public final class StdIn$ implements StdIn {
   public static final StdIn$ MODULE$ = new StdIn$();

   static {
      StdIn$ var10000 = MODULE$;
   }

   public String readLine() {
      return StdIn.readLine$(this);
   }

   public String readLine(final String text, final Seq args) {
      return StdIn.readLine$(this, text, args);
   }

   public boolean readBoolean() {
      return StdIn.readBoolean$(this);
   }

   public byte readByte() {
      return StdIn.readByte$(this);
   }

   public short readShort() {
      return StdIn.readShort$(this);
   }

   public char readChar() {
      return StdIn.readChar$(this);
   }

   public int readInt() {
      return StdIn.readInt$(this);
   }

   public long readLong() {
      return StdIn.readLong$(this);
   }

   public float readFloat() {
      return StdIn.readFloat$(this);
   }

   public double readDouble() {
      return StdIn.readDouble$(this);
   }

   public List readf(final String format) {
      return StdIn.readf$(this, format);
   }

   public Object readf1(final String format) {
      return StdIn.readf1$(this, format);
   }

   public Tuple2 readf2(final String format) {
      return StdIn.readf2$(this, format);
   }

   public Tuple3 readf3(final String format) {
      return StdIn.readf3$(this, format);
   }

   private StdIn$() {
   }
}
