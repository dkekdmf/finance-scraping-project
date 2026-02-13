package zerobase.project.scraper;

import zerobase.project.model.Company;
import zerobase.project.model.ScrapedResult;

import javax.script.ScriptContext;

public interface Scraper
{
    Company scrapCompanyByTicker(String ticker);
    ScrapedResult scrap(Company company);
}
