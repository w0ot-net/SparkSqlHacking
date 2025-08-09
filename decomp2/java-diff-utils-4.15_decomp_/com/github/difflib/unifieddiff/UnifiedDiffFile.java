package com.github.difflib.unifieddiff;

import com.github.difflib.patch.Patch;

public final class UnifiedDiffFile {
   private String diffCommand;
   private String fromFile;
   private String fromTimestamp;
   private String toFile;
   private String renameFrom;
   private String renameTo;
   private String copyFrom;
   private String copyTo;
   private String toTimestamp;
   private String index;
   private String newFileMode;
   private String oldMode;
   private String newMode;
   private String deletedFileMode;
   private String binaryAdded;
   private String binaryDeleted;
   private String binaryEdited;
   private Patch patch = new Patch();
   private boolean noNewLineAtTheEndOfTheFile = false;
   private Integer similarityIndex;

   public String getDiffCommand() {
      return this.diffCommand;
   }

   public void setDiffCommand(String diffCommand) {
      this.diffCommand = diffCommand;
   }

   public String getFromFile() {
      return this.fromFile;
   }

   public void setFromFile(String fromFile) {
      this.fromFile = fromFile;
   }

   public String getToFile() {
      return this.toFile;
   }

   public void setToFile(String toFile) {
      this.toFile = toFile;
   }

   public void setIndex(String index) {
      this.index = index;
   }

   public String getIndex() {
      return this.index;
   }

   public Patch getPatch() {
      return this.patch;
   }

   public String getFromTimestamp() {
      return this.fromTimestamp;
   }

   public void setFromTimestamp(String fromTimestamp) {
      this.fromTimestamp = fromTimestamp;
   }

   public String getToTimestamp() {
      return this.toTimestamp;
   }

   public void setToTimestamp(String toTimestamp) {
      this.toTimestamp = toTimestamp;
   }

   public Integer getSimilarityIndex() {
      return this.similarityIndex;
   }

   public void setSimilarityIndex(Integer similarityIndex) {
      this.similarityIndex = similarityIndex;
   }

   public String getRenameFrom() {
      return this.renameFrom;
   }

   public void setRenameFrom(String renameFrom) {
      this.renameFrom = renameFrom;
   }

   public String getRenameTo() {
      return this.renameTo;
   }

   public void setRenameTo(String renameTo) {
      this.renameTo = renameTo;
   }

   public String getCopyFrom() {
      return this.copyFrom;
   }

   public void setCopyFrom(String copyFrom) {
      this.copyFrom = copyFrom;
   }

   public String getCopyTo() {
      return this.copyTo;
   }

   public void setCopyTo(String copyTo) {
      this.copyTo = copyTo;
   }

   public static UnifiedDiffFile from(String fromFile, String toFile, Patch patch) {
      UnifiedDiffFile file = new UnifiedDiffFile();
      file.setFromFile(fromFile);
      file.setToFile(toFile);
      file.patch = patch;
      return file;
   }

   public void setNewFileMode(String newFileMode) {
      this.newFileMode = newFileMode;
   }

   public String getNewFileMode() {
      return this.newFileMode;
   }

   public String getDeletedFileMode() {
      return this.deletedFileMode;
   }

   public void setDeletedFileMode(String deletedFileMode) {
      this.deletedFileMode = deletedFileMode;
   }

   public String getOldMode() {
      return this.oldMode;
   }

   public void setOldMode(String oldMode) {
      this.oldMode = oldMode;
   }

   public String getNewMode() {
      return this.newMode;
   }

   public void setNewMode(String newMode) {
      this.newMode = newMode;
   }

   public String getBinaryAdded() {
      return this.binaryAdded;
   }

   public void setBinaryAdded(String binaryAdded) {
      this.binaryAdded = binaryAdded;
   }

   public String getBinaryDeleted() {
      return this.binaryDeleted;
   }

   public void setBinaryDeleted(String binaryDeleted) {
      this.binaryDeleted = binaryDeleted;
   }

   public String getBinaryEdited() {
      return this.binaryEdited;
   }

   public void setBinaryEdited(String binaryEdited) {
      this.binaryEdited = binaryEdited;
   }

   public boolean isNoNewLineAtTheEndOfTheFile() {
      return this.noNewLineAtTheEndOfTheFile;
   }

   public void setNoNewLineAtTheEndOfTheFile(boolean noNewLineAtTheEndOfTheFile) {
      this.noNewLineAtTheEndOfTheFile = noNewLineAtTheEndOfTheFile;
   }
}
