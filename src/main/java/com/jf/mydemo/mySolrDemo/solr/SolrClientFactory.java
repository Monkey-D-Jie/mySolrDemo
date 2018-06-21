package com.jf.mydemo.mySolrDemo.solr;

import com.jf.mydemo.mySolrDemo.solr.config.SolrConfig;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-05-30 15:45
 * @Description: 创建solr的java类
 * To change this template use File | Settings | File and Templates.
 */

public class SolrClientFactory {
    private static HttpSolrClient solrClient = null;
    private static SolrClientFactory solrClientFactory = null;
    private static String solrHost = SolrConfig.solrHost;
    private static int solrClientTimeout = SolrConfig.solrClientTimeout;
    private static int solrConnectTimeout = SolrConfig.solrConnectTimeout;
    private static int maxConnectionsPerHost = SolrConfig.maxConnectionsPerHost;
    private static int maxTotalConnection = SolrConfig.maxTotalConnection;

    private SolrClientFactory() {
    }

    public synchronized HttpSolrClient getSolrClient() {
        if (solrClient == null) {
            solrClient = new HttpSolrClient(solrHost);

            solrClient.setSoTimeout(solrClientTimeout);  // socket read timeout
            solrClient.setConnectionTimeout(solrConnectTimeout);
            solrClient.setDefaultMaxConnectionsPerHost(maxConnectionsPerHost);
            solrClient.setMaxTotalConnections(maxTotalConnection);
            solrClient.setFollowRedirects(false);  // defaults to false
            //allowCompression defaults to false.
            //Server side must support gzip or deflate for this to have any effect.
            solrClient.setAllowCompression(true);
        }
        return solrClient;
    }

    public static synchronized SolrClientFactory getInstance() {
        if (solrClientFactory == null) {
            solrClientFactory = new SolrClientFactory();
        }
        return solrClientFactory;
    }
}
