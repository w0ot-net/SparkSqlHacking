package org.apache.hadoop.hive.serde2.proto.test;

import java.util.Collections;
import java.util.List;

public final class Complexpb {
   private Complexpb() {
   }

   public static final class IntString {
      public static final int MYINT_FIELD_NUMBER = 1;
      private final boolean hasMyint;
      private int myint_ = 0;
      public static final int MYSTRING_FIELD_NUMBER = 2;
      private final boolean hasMyString;
      private String myString_ = "";
      public static final int UNDERSCORE_INT_FIELD_NUMBER = 3;
      private final boolean hasUnderscoreInt;
      private int underscoreInt_ = 0;

      public IntString(int myInt, String myString, int underscoreInt) {
         this.myint_ = myInt;
         this.hasMyint = true;
         this.myString_ = myString;
         this.hasMyString = true;
         this.underscoreInt_ = underscoreInt;
         this.hasUnderscoreInt = true;
      }

      public boolean hasMyint() {
         return this.hasMyint;
      }

      public int getMyint() {
         return this.myint_;
      }

      public boolean hasMyString() {
         return this.hasMyString;
      }

      public String getMyString() {
         return this.myString_;
      }

      public boolean hasUnderscoreInt() {
         return this.hasUnderscoreInt;
      }

      public int getUnderscoreInt() {
         return this.underscoreInt_;
      }

      public final boolean isInitialized() {
         return true;
      }
   }

   public static final class Complex {
      public static final int AINT_FIELD_NUMBER = 1;
      private final boolean hasAint;
      private int aint_ = 0;
      public static final int ASTRING_FIELD_NUMBER = 2;
      private final boolean hasAString;
      private String aString_ = "";
      public static final int LINT_FIELD_NUMBER = 3;
      private List lint_ = Collections.emptyList();
      public static final int LSTRING_FIELD_NUMBER = 4;
      private List lString_ = Collections.emptyList();
      public static final int LINTSTRING_FIELD_NUMBER = 5;
      private List lintString_ = Collections.emptyList();

      public Complex(int aint, String aString, List lint, List lString, List lintString) {
         this.aint_ = aint;
         this.hasAint = true;
         this.aString_ = aString;
         this.hasAString = true;
         this.lint_ = lint;
         this.lString_ = lString;
         this.lintString_ = lintString;
      }

      public boolean hasAint() {
         return this.hasAint;
      }

      public int getAint() {
         return this.aint_;
      }

      public boolean hasAString() {
         return this.hasAString;
      }

      public String getAString() {
         return this.aString_;
      }

      public List getLintList() {
         return this.lint_;
      }

      public int getLintCount() {
         return this.lint_.size();
      }

      public int getLint(int index) {
         return (Integer)this.lint_.get(index);
      }

      public List getLStringList() {
         return this.lString_;
      }

      public int getLStringCount() {
         return this.lString_.size();
      }

      public String getLString(int index) {
         return (String)this.lString_.get(index);
      }

      public List getLintStringList() {
         return this.lintString_;
      }

      public int getLintStringCount() {
         return this.lintString_.size();
      }

      public IntString getLintString(int index) {
         return (IntString)this.lintString_.get(index);
      }

      private void initFields() {
      }

      public final boolean isInitialized() {
         return true;
      }
   }
}
