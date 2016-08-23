package com.taikang.healthcare.cis.dig.solr;

import java.io.IOException;

import javax.annotation.PreDestroy;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@EnableConfigurationProperties(SolrConfig.class)
public class SolrClientConfig {
	@Autowired
	private SolrConfig solrConfig;
	
	
	private HttpSolrClient httpSolrClient;
	
	//清楚对象
	@PreDestroy
	public void close() {
	    if (this.httpSolrClient != null) {
	        try {
	            this.httpSolrClient.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	@Bean 
	public HttpSolrClient SolrServer(){
	    if (StringUtils.hasText(this.solrConfig.getZkHost())) {
	    	httpSolrClient = new HttpSolrClient(this.solrConfig.getHost()+"/"+this.solrConfig.getDefaultCollection());
	    }
	    return this.httpSolrClient;
	}
}
