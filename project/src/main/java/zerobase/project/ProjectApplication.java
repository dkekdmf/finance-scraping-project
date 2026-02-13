package zerobase.project;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import zerobase.project.model.Company;
import zerobase.project.scraper.Scraper;
import zerobase.project.scraper.YahooFinanceScraper;

import javax.swing.*;
import java.io.IOException;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class ProjectApplication {

	public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class,args);

        Trie trie = new PatriciaTrie();
	}

}
