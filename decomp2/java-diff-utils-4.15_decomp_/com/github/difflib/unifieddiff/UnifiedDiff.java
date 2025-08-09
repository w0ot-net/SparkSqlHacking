package com.github.difflib.unifieddiff;

import com.github.difflib.patch.PatchFailedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public final class UnifiedDiff {
   private String header;
   private String tail;
   private final List files = new ArrayList();

   public String getHeader() {
      return this.header;
   }

   public void setHeader(String header) {
      this.header = header;
   }

   void addFile(UnifiedDiffFile file) {
      this.files.add(file);
   }

   public List getFiles() {
      return Collections.unmodifiableList(this.files);
   }

   void setTailTxt(String tailTxt) {
      this.tail = tailTxt;
   }

   public String getTail() {
      return this.tail;
   }

   public List applyPatchTo(Predicate findFile, List originalLines) throws PatchFailedException {
      UnifiedDiffFile file = (UnifiedDiffFile)this.files.stream().filter((diff) -> findFile.test(diff.getFromFile())).findFirst().orElse((Object)null);
      return file != null ? file.getPatch().applyTo(originalLines) : originalLines;
   }

   public static UnifiedDiff from(String header, String tail, UnifiedDiffFile... files) {
      UnifiedDiff diff = new UnifiedDiff();
      diff.setHeader(header);
      diff.setTailTxt(tail);

      for(UnifiedDiffFile file : files) {
         diff.addFile(file);
      }

      return diff;
   }
}
