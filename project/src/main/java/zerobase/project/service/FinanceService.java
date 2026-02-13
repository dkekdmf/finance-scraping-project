package zerobase.project.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zerobase.project.exception.impl.NoCompanyException;
import zerobase.project.model.Company;
import zerobase.project.model.Dividend;
import zerobase.project.model.ScrapedResult;
import zerobase.project.model.constants.CacheKey;
import zerobase.project.persist.CompanyRepository;
import zerobase.project.persist.DividendRepository;
import zerobase.project.persist.entity.CompanyEntity;
import zerobase.project.persist.entity.DividenedEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static zerobase.project.model.constants.CacheKey.KEY_FINANCE;

@Slf4j
@Service
@AllArgsConstructor
public class FinanceService {

    private  final CompanyRepository companyRepository;
    private DividendRepository dividendRepository;

    //요청이 자주 들어오는가?
    // 자주 변경되는 데이터 인가?
    @Cacheable(key = "companyName", value = CacheKey.KEY_FINANCE)
    public ScrapedResult getDividendBuCompanyName(String companyName){
        log.info("search company -> "+companyName);

        // 1. 회사명을 기준으로 회사 정보를 조회
        CompanyEntity company = this.companyRepository.findByName(companyName)
                        .orElseThrow(()-> new NoCompanyException());

        // 2, 조회된회사 ID로 배당금 정보 조회
        List <DividenedEntity> dividenedEntities = this.dividendRepository.findAllByCompanyId(company.getId());
        // 3. 결과 조합 후 반환


       List<Dividend> dividends =  dividenedEntities.stream()
                .map(e -> new Dividend(e.getDate(),e.getDividend()))
                        .collect(Collectors.toList());


        return new ScrapedResult(new Company(company.getTicker(), company.getName()),
                dividends);


    }

}
