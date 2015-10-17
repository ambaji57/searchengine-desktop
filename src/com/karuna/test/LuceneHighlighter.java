package com.karuna.test;

import java.io.InputStream;

import java.net.URI;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.NullFragmenter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.util.Version;

public class LuceneHighlighter
{
    public static String highlight(String pText, String pQuery) throws Exception
    {
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
        QueryParser parser = new QueryParser(Version.LUCENE_CURRENT, "", analyzer);
        Highlighter highlighter = new Highlighter(new QueryScorer(parser.parse(pQuery)));
        highlighter.setTextFragmenter(new NullFragmenter());
    
		String text = highlighter.getBestFragment(analyzer, "", pText);
		
		if (text != null)
		{
			return text;
		}
		return pText;    
    }

	public static void main(String[] pArgs)
	{
		if (pArgs.length < 2)
		{
			System.err.println("Usage: LuceneHighlighter <file> <query>");
			System.exit(1);
		}
	
		InputStream inputStream = null;	
		try
		{
			URL url = new URI(pArgs[0]).toURL();
			inputStream = url.openStream();
		    String data = IOUtils.toString(inputStream);
			System.out.println(highlight(data, pArgs[1]));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			IOUtils.closeQuietly(inputStream);
		}
	}
}
