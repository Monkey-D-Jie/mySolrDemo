package com.jf.mydemo.mySolrDemo.solr;

import com.jf.mydemo.mySolrDemo.entity.Product;
import com.jf.mydemo.mySolrDemo.solrUtil.SolrIndexUtils;
import com.jf.mydemo.mySolrDemo.solrUtil.SolrQueryUtils;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-05-30 14:36
 * @Description: solr的测试类
 * To change this template use File | Settings | File and Templates.
 */

public class MySolrTest {

    public static void main(String[] args) throws IOException, ParseException, SolrServerException {
        System.out.println(new Date());
        testInsert();
//        testSearch();
        System.out.println(new Date());
    }


    public static void testInsert() throws IOException, SolrServerException {
        List<Product> products = new ArrayList<Product>();
        Random ran = new Random();
        //指定数据打印的文件
        File dataFile = new File("E:\\Users\\test-solr.txt");
        FileWriter fileWriter = new FileWriter(dataFile);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        for (int i = 0; i < 100; i++) {
            String id = UUID.randomUUID().toString();
            String name = "test" + i;
//                    + ran.nextInt(5000);
            String sn = "sn-" + 333;
            String keywords = "just test-" + UUID.randomUUID().toString().replace("-", "");
            products.add(new Product(id, name, keywords, null, sn));
            bw.write(id);
            bw.write("\t");

            bw.write(name);
            bw.write("\t");
            bw.write(keywords);
            bw.write("\t");
            bw.write(sn);
            bw.newLine();
        }
        fileWriter.flush();
        bw.close();
        fileWriter.close();
        System.out.println(products.size());
        SolrIndexUtils.addIndexs(products);
    }
    public static void testSearch() throws SolrServerException, IOException {
        List<Product> search;
        Random ran = new Random();
        for (int i = 0; i < 10 ; i++) {
            String name = "test" + i ;
//                    + ran.nextInt(5000);
            search = SolrQueryUtils.search(name, null, null, null, 0, 20);
            for (Product product : search) {
                System.out.println(product.getId() + "||||||" + product.getName());
                System.out.println("搜索到的数据为:"+product.toString());
            }
        }

    }
}
