package org.apache.derby.impl.tools.planexporter;

class TreeNode {
   private String parentId = "";
   private String id = "";
   private String nodeType = "";
   private String noOfOpens = "";
   private String inputRows = "";
   private String returnedRows = "";
   private String visitedPages = "";
   private String scanQualifiers = "";
   private String nextQualifiers = "";
   private String scannedObject = "";
   private String scanType = "";
   private String sortType = "";
   private String sorterOutput = "";
   private int depth;

   public void setId(String var1) {
      this.id = var1;
   }

   public String getId() {
      return this.id;
   }

   public void setParent(String var1) {
      this.parentId = var1;
   }

   public String getParent() {
      return this.parentId;
   }

   public void setNodeType(String var1) {
      this.nodeType = var1;
   }

   public String getNodeType() {
      return this.nodeType;
   }

   public void setNoOfOpens(String var1) {
      this.noOfOpens = var1;
   }

   public String getNoOfOpens() {
      return this.noOfOpens;
   }

   public void setInputRows(String var1) {
      this.inputRows = var1;
   }

   public String getInputRows() {
      return this.inputRows;
   }

   public void setReturnedRows(String var1) {
      this.returnedRows = var1;
   }

   public String getReturnedRows() {
      return this.returnedRows;
   }

   public void setVisitedPages(String var1) {
      this.visitedPages = var1;
   }

   public String getVisitedPages() {
      return this.visitedPages;
   }

   public void setDepth(int var1) {
      this.depth = var1;
   }

   public int getDepth() {
      return this.depth;
   }

   public void setScanQualifiers(String var1) {
      this.scanQualifiers = var1;
   }

   public String getScanQualifiers() {
      return this.scanQualifiers;
   }

   public void setNextQualifiers(String var1) {
      this.nextQualifiers = var1;
   }

   public String getNextQualifiers() {
      return this.nextQualifiers;
   }

   public void setScannedObject(String var1) {
      this.scannedObject = var1;
   }

   public String getScannedObject() {
      return this.scannedObject;
   }

   public void setScanType(String var1) {
      this.scanType = var1;
   }

   public String getScanType() {
      return this.scanType;
   }

   public void setSortType(String var1) {
      this.sortType = var1;
   }

   public String getSortType() {
      return this.sortType;
   }

   public void setSorterOutput(String var1) {
      this.sorterOutput = var1;
   }

   public String getSorterOutput() {
      return this.sorterOutput;
   }

   public String toString() {
      String var1 = "<node ";
      var1 = var1 + this.getNodeType();
      var1 = var1 + this.getInputRows();
      var1 = var1 + this.getReturnedRows();
      var1 = var1 + this.getNoOfOpens();
      var1 = var1 + this.getVisitedPages();
      var1 = var1 + this.getScanQualifiers();
      var1 = var1 + this.getNextQualifiers();
      var1 = var1 + this.getScannedObject();
      var1 = var1 + this.getScanType();
      var1 = var1 + this.getSortType();
      var1 = var1 + this.getSorterOutput();
      return var1 + ">\n";
   }
}
