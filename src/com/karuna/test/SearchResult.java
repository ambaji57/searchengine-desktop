package com.karuna.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class SearchResult {
	
	public static final String FILE_PATH = "index";
	public String userquery;
	public String indexpath;
	public String query1;
	public IndexReader reader;
	public Searcher searcher;
	public Query query;
	public Analyzer analyzer;
	public QueryParser qparser;
	public Document doc;
	public TopDocs hits = null;
	public int max_pages =100;
	public int start_index = 0;
	public long t;
	public List<String> filenames = new ArrayList<String>();
	
	//constructor
	public  SearchResult(String uquery) throws Exception{
		try{
			setUserQuery(uquery);
			reader = IndexReader.open(FSDirectory.open(new File(FILE_PATH)),true);
			searcher = new IndexSearcher(reader);
			analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
			qparser = new QueryParser("contents",analyzer);
			query = qparser.parse(getUserQuery());
			getResults();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	public void setUserQuery(String userquery){
		this.userquery = userquery;
	}
	public String getUserQuery(){
		return userquery;
	}
	public void setIndexPath(String indexpath){
		this.indexpath = indexpath;
	}
	public String getIndexPath(){
		return indexpath;
	}
	public IndexReader getIndexReader(){
		return reader;
	}
	public Searcher getSearcher(){
		return searcher;
	}
	//getting total no of doucments;
	public void getResults(){
		try{
			Date start = new Date();
			hits = searcher.search(query,5*max_pages);
			Date end = new Date();
			t = ((end.getTime()-start.getTime())/60);
			System.out.println("search in "+t+" msec");
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public int getTotalDocs(){
		return hits.totalHits;
	}
	public List<String> getFilePaths(){
		for(int i =0; i<getTotalDocs();i++){
		  try{
			doc = searcher.doc(hits.scoreDocs[i].doc);
			filenames.add(doc.get("path"));
		  }
		  catch(Exception e){}
		}
	return filenames;
	}
	public static void main(String[] args) throws Exception{
		System.out.println("Enter any Query to Search");
		Scanner scan = new Scanner(System.in);
		SearchResult search = new SearchResult(scan.nextLine());
		System.out.println("total no of doucment found in search "+search.getTotalDocs());
		System.out.println("total doc paths are "+ search.getFilePaths());
	}
		


}
