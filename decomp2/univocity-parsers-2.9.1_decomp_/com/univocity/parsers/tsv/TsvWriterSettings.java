package com.univocity.parsers.tsv;

import com.univocity.parsers.common.CommonWriterSettings;
import java.util.Map;

public class TsvWriterSettings extends CommonWriterSettings {
   private boolean lineJoiningEnabled = false;

   public boolean isLineJoiningEnabled() {
      return this.lineJoiningEnabled;
   }

   public void setLineJoiningEnabled(boolean lineJoiningEnabled) {
      this.lineJoiningEnabled = lineJoiningEnabled;
   }

   protected TsvFormat createDefaultFormat() {
      return new TsvFormat();
   }

   protected void addConfiguration(Map out) {
      super.addConfiguration(out);
   }

   public final TsvWriterSettings clone() {
      return (TsvWriterSettings)super.clone();
   }

   public final TsvWriterSettings clone(boolean clearInputSpecificSettings) {
      return (TsvWriterSettings)super.clone(clearInputSpecificSettings);
   }
}
