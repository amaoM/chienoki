package com.example.chienoki.batch.dao;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.example.chienoki.domain.Article;
import com.example.chienoki.domain.ArticleRepository;
import com.example.chienoki.domain.Host;
import com.example.chienoki.domain.HostRepository;

/**
 * @author amaomasashi
 *
 */
@Component
public class RssDao {

    @Autowired
    HostRepository hostRepository;

    @Autowired
    ArticleRepository articleRepository;

    /**
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public void registerArticlesFromRss() throws ParserConfigurationException, SAXException, IOException {
        Iterable<Host> hosts = getHosts();
        
        for (Host host : hosts) {
            System.out.println(" === Host : " + host.getName() + " ===");
            NodeList itemList = getArticles(host);
            System.out.println(" === itemList number : " + itemList.getLength() + " ===");
            saveArticles(itemList, host);
        }
    }

    /**
     * @return
     */
    private Iterable<Host> getHosts() {
        return hostRepository.findAll();
    }

    /**
     * @param host
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private NodeList getArticles(Host host) throws ParserConfigurationException, SAXException, IOException {
        NodeList itemList = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(host.getUrl());
            Element root = document.getDocumentElement();
            itemList = root.getElementsByTagName("item");
        } catch (IOException e) {
            System.out.println("IO Exception");
        } catch (ParserConfigurationException e) {
            System.out.println("Parser Configuration Exception");
        } catch (SAXException e) {
            System.out.println("SAX Exception");
        }
        return itemList;
    }

    /**
     * @param itemList
     * @param host
     */
    @Transactional
    public void saveArticles(NodeList itemList, Host host) {
        for (int i = 0; i < itemList.getLength(); i++) {
            Element element = (Element)itemList.item(i);
            NodeList itemTitle = element.getElementsByTagName("title");
            NodeList itemLink = element.getElementsByTagName("link");
            NodeList itemDescription = element.getElementsByTagName("description");
            if (itemTitle.getLength() == 0 || itemTitle.item(0) == null) continue;
            if (itemLink.getLength() == 0 || itemLink.item(0) == null) continue;
            if (itemDescription.getLength() == 0 || itemDescription.item(0) == null) continue;
            String link = itemLink.item(0).getFirstChild().getNodeValue();
            Long count = articleRepository.countByLink(link);
            if (count > 0) continue;
            String title = itemTitle.item(0).getFirstChild().getNodeValue();
            String description = itemDescription.item(0).getFirstChild().getNodeValue();
            Article article = new Article();
            article.setHost(host);
            article.setTitle(title);
            article.setLink(link);
            article.setDescription(description);
            Article savedArticle = articleRepository.save(article);
            System.out.println(" === Saved article's id is " + savedArticle.getId().toString());
        }
    }
}
