package com.jf.mydemo.mySolrDemo.solr.config;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-05-30 15:40
 * @Description: solr的配置java类
 * To change this template use File | Settings | File and Templates.
 */

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SolrConfig {

    private final static Logger logger = LogManager.getLogger(SolrConfig.class);


    public static String solrHost;

    public static int solrConnectTimeout;

    public static int solrClientTimeout;

    public static int maxConnectionsPerHost;

    public static int maxTotalConnection;

    static {
        InputStream is = SolrConfig.class
                .getResourceAsStream("/solr.properties");
        if (is != null) {
            Properties properties = new Properties();
            try {
                properties.load(is);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                e.printStackTrace();
            }


            solrHost = properties.getProperty("solr.host");
            String solrClientTimeoutStr = properties
                    .getProperty("solr.client.timeout");
            if (StringUtils.isNotEmpty(solrClientTimeoutStr)) {
                solrClientTimeout = Integer.parseInt(solrClientTimeoutStr);
            }
            String solrConnectTimeoutStr = properties
                    .getProperty("solr.connect.timeout");
            if (StringUtils.isNotEmpty(solrConnectTimeoutStr)) {
                solrConnectTimeout = Integer.parseInt(solrConnectTimeoutStr);
            }

            String maxConnectionsPerHostStr = properties
                    .getProperty("max.connections.perhost");
            if (StringUtils.isNotEmpty(maxConnectionsPerHostStr)) {
                maxConnectionsPerHost = Integer.parseInt(maxConnectionsPerHostStr);
            }

            String maxTotalConnectionStr = properties
                    .getProperty("max.total.connection");
            if (StringUtils.isNotEmpty(maxTotalConnectionStr)) {
                maxTotalConnection = Integer.parseInt(maxTotalConnectionStr);
            }

        }
    }
}
