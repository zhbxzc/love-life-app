package com.taikang.healthcare.cis.dig.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import com.taikang.healthcare.cis.dig.common.Constants;
import com.taikang.healthcare.cis.dig.common.PatternUtil;

@Service
public class LuceneService {
	/**
	 * 建立索引
	 * @return 
	 */
	public boolean createIndexFile(List<Map<String, Object>> list) {
		if (list!=null&&list.size() > 0) {
			IndexWriter indexWriter = null;
			try {
				File file = new File(Constants.indexFilePath);
				String[] files = file.list();
				if (files.length == 0) {// 判断索引文件夹是否为空
					Directory directory = FSDirectory.open(FileSystems.getDefault().getPath(Constants.indexFilePath));
					Analyzer analyzer = new StandardAnalyzer();
					IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
					indexWriter = new IndexWriter(directory, indexWriterConfig);
					for (Map<String, Object> map : list) {
						// 3、创建Document对象
						Document document = new Document();
						// 4、为Document添加Field
						// 第三个参数是FieldType 但是定义在TextField中作为静态变量，看API也不好知道怎么写
						document.add(new Field("title", map.get("id").toString(), TextField.TYPE_STORED));
						document.add(new Field("content",map.get("name").toString(),TextField.TYPE_STORED));
						document.add(new Field("code",map.get("code").toString()+map.get("name").toString(),TextField.TYPE_STORED));

						// 5、通过IndexWriter添加文档到索引中
						indexWriter.addDocument(document);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				try {
					if (indexWriter != null) {
						indexWriter.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
/**
 * 查询
 * @param content
 */
	public List<Map<String,Object>> txtFileSearcher(String content,int pageIndex) {
		DirectoryReader directoryReader = null;  
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
        try {  
            // 1、创建Directory  
            Directory directory = FSDirectory.open(FileSystems.getDefault().getPath(Constants.indexFilePath));  
            // 2、创建IndexReader  
            // 如下方法过时  
            // IndexReader indexReader = IndexReader.open(directory);  
            directoryReader = DirectoryReader.open(directory);  
  
            // 3、根据IndexReader创建IndexSearch  
            IndexSearcher indexSearcher = new IndexSearcher(directoryReader);  
  
            // 4、创建搜索的Query  
            Analyzer analyzer = new StandardAnalyzer();  
            // 创建parser来确定要搜索文件的内容，第一个参数为搜索的域  
            QueryParser queryParser = null; 
            if(PatternUtil.isChinese(content)){
            	queryParser = new QueryParser("content", analyzer);
            }else if(PatternUtil.isEnglish(content)){
            	queryParser = new QueryParser("code", analyzer);
            }
            // 创建Query表示搜索域为content包含Darren的文档  
            Query query = queryParser.parse(content); 
  
            //获取上一页的最后一个元素
			ScoreDoc lastScoreDoc = getLastScoreDoc(pageIndex, Constants.pageSize, query, indexSearcher);
            // 5、根据searcher搜索并且返回TopDocs  通过最后一个元素搜索下页的pageSize个元素
//            TopDocs topDocs = indexSearcher.search(query, Constants.pageSize);  
			TopDocs topDocs = indexSearcher.searchAfter(lastScoreDoc,query,Constants.pageSize);
            // 6、根据TopDocs获取ScoreDoc对象  
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;  
            for (ScoreDoc scoreDoc : scoreDocs) {  
  
                // 7、根据searcher和ScoreDoc对象获取具体的Document对象  
                Document document = indexSearcher.doc(scoreDoc.doc);  
  
                // 8、根据Document对象获取需要的值  
                System.out.println(document.get("title") + " " + document.get("content"));  
                map.put("id", document.get("title"));
                map.put("code", document.get("code"));
                map.put("name", document.get("content"));
                list.add(map);
            }  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (directoryReader != null) {  
                    directoryReader.close();  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        } 
        return list;
    }  
	/**
	 * 根据页码和分页大小获取上一次最后一个ScoreDoc
	 * @param pageIndex
	 * @param pageSize
	 * @param query
	 * @param indexSearcher
	 * @return
	 * @throws IOException
	 */
	private ScoreDoc getLastScoreDoc(int pageIndex,int pageSize,Query query,IndexSearcher indexSearcher) throws IOException{
		if(pageIndex==1)return null;//如果是第一页返回空
		int num = pageSize*(pageIndex-1);//获取上一页的数量
		TopDocs tds = indexSearcher.search(query, num);
		if(tds.totalHits<num)return null;
		return tds.scoreDocs[num-1];
	}
}