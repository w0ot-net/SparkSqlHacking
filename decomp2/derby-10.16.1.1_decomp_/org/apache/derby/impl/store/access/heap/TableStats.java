package org.apache.derby.impl.store.access.heap;

class TableStats {
   public int num_pages = 0;
   public int num_overflow_pgs = 0;
   public int num_entries = 0;
   public int num_deleted = 0;
   public long max_pageno = 0L;
   public long num_free_bytes = 0L;
   public long num_res_bytes = 0L;
   public long num_overflow_rows = 0L;
   public long num_rowsize_bytes = 0L;
   public long min_rowsize_bytes = Long.MAX_VALUE;
   public long max_rowsize_bytes = Long.MIN_VALUE;
}
