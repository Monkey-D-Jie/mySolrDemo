package com.jf.mydemo.mySolrDemo.solrUtil;

import com.jf.mydemo.mySolrDemo.entity.Product;
import com.jf.mydemo.mySolrDemo.solr.SolrClientFactory;
import com.jf.mydemo.mySolrDemo.solr.config.ISolrFields;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-05-30 15:47
 * @Description: solr创建索引的工具类
 * To change this template use File | Settings | File and Templates.
 */

public class SolrIndexUtils {
    private static Logger logger = LogManager.getLogger(SolrIndexUtils.class);

    private final static HttpSolrClient solrClient = SolrClientFactory
            .getInstance().getSolrClient();

    public static void addIndexs( List<Product> records) throws SolrServerException, IOException {
        int count = 0;
        Collection<SolrInputDocument> docs = new ArrayList<>();
        int size = records.size();
        for (int i = 0; i < size; i++) {
            SolrInputDocument item = new SolrInputDocument();
            Product record = records.get(i);
            logger.info("Start adding solr index for " + record.toString());
            item.addField(ISolrFields.ID, record.getId());
            item.addField(ISolrFields.DESCRIPTION,record.getDescription());
            item.addField(ISolrFields.KEYWORDS,record.getKeywords());
            item.addField(ISolrFields.NAME,record.getName());
            item.addField(ISolrFields.SN,record.getSn());
            docs.add(item);
            count++;
            if (count % 50 == 0 || count == size) {
                logger.info("Begin commit " + count + " records");
                solrClient.add(docs);
                solrClient.commit();
                docs.clear();
                logger.info("End commit " + count + " records");
            }
        }
    }
}
