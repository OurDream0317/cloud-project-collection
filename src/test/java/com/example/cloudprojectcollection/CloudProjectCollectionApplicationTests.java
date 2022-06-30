package com.example.cloudprojectcollection;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class CloudProjectCollectionApplicationTests {

    @Autowired
    private SolrClient solrClient;
    /*
     * 添加  id 不同为新增
     * 修改  id 相同为修改
     * */
    @Test
    public void add() throws IOException, SolrServerException {
        //创建document对象
        SolrInputDocument document=new SolrInputDocument();
        //添加的时候，必须加上id属性，否则报错
        //属性值为object类型，传入的属性如果是BigDecimal等其他非Object类型的，需要转为Object类型，否则报错
        document.addField("id",2);
        document.addField("gname","苹果手机");
        document.addField("gimage","http://www.iphone.com");
        document.addField("ginfo","外国手机");
        document.addField("gprice",6999.88);
        document.addField("gsave",3232);
        solrClient.add(document);
        solrClient.commit();
    }

    @Test
    public void query() throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        //查询的属性必须在solr库中有定义 ，否则会报错：
        //Error from server at http://192.168.65.128:8080/solr: undefined field gnames
        //只针对于gname属性进行查询
        //solrQuery.setQuery("gname:苹果手机");
        //针对多属性进行查询(注意多条件连接时，之间要有空格)
        //solrQuery.setQuery("gname:手机 || ginfo:苹果");
        String keyword="手机";
        solrQuery.setQuery("gname:"+keyword+" || ginfo:"+keyword);
        QueryResponse queryResponse = solrClient.query(solrQuery);
        SolrDocumentList results = queryResponse.getResults();
        for (SolrDocument document:results) {
            String id= (String) document.get("id");
            String gname= (String) document.get("gname");
            String gimage= (String) document.get("gimage");
            String ginfo= (String) document.get("ginfo");
            float gprice= (float) document.get("gprice");
            int gsave= (int) document.get("gsave");
            System.out.println(id+"  "+gname+"  "+gimage+"  "+ginfo+"  "+gprice+"  "+gsave);
        }
    }
    @Test
    public void delete() {
        try {
            //根据id进行删除
            //solrClient.deleteById("7");
            //根据查询后的结果进行删除
            solrClient.deleteByQuery("gname:安定");
            solrClient.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
