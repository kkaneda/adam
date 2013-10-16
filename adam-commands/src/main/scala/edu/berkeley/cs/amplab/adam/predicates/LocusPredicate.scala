/*
 * Copyright (c) 2013. Regents of the University of California
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.berkeley.cs.amplab.adam.predicates

import parquet.filter.{RecordFilter, UnboundRecordFilter}
import java.lang.Iterable
import parquet.column.ColumnReader
import parquet.filter.AndRecordFilter.and
import parquet.filter.ColumnPredicates.equalTo
import parquet.filter.ColumnRecordFilter.column

class LocusPredicate extends UnboundRecordFilter {
  def bind(readers: Iterable[ColumnReader]): RecordFilter = {
    and(column("readMapped", equalTo(true)),
      and(column("primaryAlignment", equalTo(true)),
        and(column("failedVendorQualityChecks", equalTo(false)),
          column("duplicateRead", equalTo(false))))).bind(readers)
  }
}