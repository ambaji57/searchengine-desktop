package com.karuna.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.FilterIndexReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Collector;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Scorer;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/** Simple command-line based search demo. */
public class SearchFilesold {


 public static  String mline="";
    String normsField = null;
    String queries = null;
     String field="contents";
     boolean paging=true;
    int hitsPerPage = 10;
    boolean raw = false;
    int n=0;
   public static int start=0,end=0;
 //public FileOutputStream fos = new FileOutputStream("outputdata1.txt");
  /** Use the norms from one field for all fields.  Norms are read into memory,
   * using a byte of memory per document per searched field.  This can cause
   * search of large collections with a large number of fields to run out of
   * memory.  If all of the fields contain only a single token, then the norms
   * are all identical, then single norm vector may be shared. */
  private static class OneNormsReader extends FilterIndexReader {
    private String field;

    public OneNormsReader(IndexReader in, String field) {
      super(in);
      this.field = field;
    }

    public byte[] norms(String field) throws IOException {
      return in.norms(this.field);
    }
  }
  //am adding this method for appending output to interface 

  public String[] indexsearchfiles( String qline)throws Exception {
    IndexReader reader = IndexReader.open(FSDirectory.open(new File("index")), true); // only searching, so read-only=true
    //Query query;
     String[] string={};
  if(normsField != null) 
      reader = new OneNormsReader(reader, normsField);
      
   Searcher searcher = new IndexSearcher(reader);   
    Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
    BufferedReader in = null;
   if(queries != null)
       {
         in = new BufferedReader(new FileReader(queries));
       }
   else {
         in = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
        }
      QueryParser parser = new QueryParser(field, analyzer);
      if(qline != null)
       {
         // query = parser.parse(qline);
        try {
        Query query = parser.parse(qline);
         //System.out.println("am karuanakar am working fine here");
         if(paging) {
      string= doPagingSearch(in,searcher,query,hitsPerPage,raw,queries == null);
        reader.close();
        // return(string);
          }
           else{
           doStreamingSearch(searcher,query);
           }
          }
         catch(Exception e) {}
        }
       //reader.close();
       return(string);
}

  
  /**
   * This method uses a custom HitCollector implementation which simply prints out
   * the docId and score of every matching document. 
   * 
   *  This simulates the streaming search use case, where all hits are supposed to
   *  be processed, regardless of their relevance.
   */
  public static void doStreamingSearch(final Searcher searcher, Query query) throws IOException {
    Collector streamingHitCollector = new Collector() {
      private Scorer scorer;
      private int docBase;
      
      // simply print docId and score of every matching document
      public void collect(int doc) throws IOException {
        System.out.println("doc=" + doc + docBase + " score=" + scorer.score());
      }

      public boolean acceptsDocsOutOfOrder() {
        return true;
      }

      public void setNextReader(IndexReader reader, int docBase)
          throws IOException {
        this.docBase = docBase;
      }

      public void setScorer(Scorer scorer) throws IOException {
        this.scorer = scorer;
      }
      
    };
    
    searcher.search(query, streamingHitCollector);
  }

  /**
   * This demonstrates a typical paging search scenario, where the search engine presents 
   * pages of size n to the user. The user can then go to the next page if interested in
   * the next hits.
   * 
   * When the query is executed for the first time, then only enough results are collected
   * to fill 5 result pages. If the user wants to page beyond this limit, then the query
   * is executed another time and all hits are collected.
   * 
   */
  //am modifing here replace the void with int
  public static String[]  doPagingSearch(BufferedReader in, Searcher searcher, Query query, 
                                     int hitsPerPage, boolean raw, boolean interactive) throws IOException {
 
    // Collect enough docs to show 5 pages
    TopScoreDocCollector collector = TopScoreDocCollector.create(
        5 * hitsPerPage, false);
    searcher.search(query, collector);
    ScoreDoc[] hits = collector.topDocs().scoreDocs;
    
    int numTotalHits = collector.getTotalHits();
    //System.out.println(numTotalHits + " total matching documents");

    String[] str;    
       str= new String[hits.length];
        end= Math.min(numTotalHits,hitsPerPage);
      //   System.out.println(end);
     /* for (int i = start; i < end; i++) {
        if (raw) {                              // output raw format
          System.out.println("doc="+hits[i].doc+" score="+hits[i].score);
          continue;
        }
        }*/
        for(int i=0;i<hits.length;i++) {
        Document doc = searcher.doc(hits[i].doc);
         str[i]= doc.get("path");
        } 
         // String title = doc.get("title");


 /*     if (numTotalHits >= end) {
        boolean quit = false;
        while (true) {
          System.out.print("Press ");
          if (start - hitsPerPage >= 0) {
            System.out.print("(p)revious page, ");  
          }
          if (start + hitsPerPage < numTotalHits) {
            System.out.print("(n)ext page, ");
          }
          System.out.println("(q)uit or enter number to jump to a page.");
          
          String line = in.readLine();
          if (line.length() == 0 || line.charAt(0)=='q') {
            quit = true;
            break;
          }
          if (line.charAt(0) == 'p') {
            start = Math.max(0, start - hitsPerPage);
            break;
          } else if (line.charAt(0) == 'n') {
            if (start + hitsPerPage < numTotalHits) {
              start+=hitsPerPage;
            }
            break;
          } else {
            int page = Integer.parseInt(line);
            if ((page - 1) * hitsPerPage < numTotalHits) {
              start = (page - 1) * hitsPerPage;
              break;
            } else {
              System.out.println("No such page");
            }
          }
        }
      }*/
return(str);
}
public void next()
{
System.out.println("am working here");
}   
}
