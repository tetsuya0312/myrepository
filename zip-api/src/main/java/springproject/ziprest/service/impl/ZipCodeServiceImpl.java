package springproject.ziprest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import springproject.ziprest.dto.ZipCodeDto;
import springproject.ziprest.service.ZipCodeService;

@Service
public class ZipCodeServiceImpl implements ZipCodeService {

	@Autowired
	@Qualifier("zipCodeSearchRestTemplate")
	private RestTemplate restTemplate;

	/** 郵便番号検索API リクエストURL */
	private static final String URL = "http://zipcloud.ibsnet.co.jp/api/search?zipcode={zipcode}";

	@Override
	public ZipCodeDto execute(String zipcode) {
		return restTemplate.getForObject(URL, ZipCodeDto.class, zipcode);
	}
}
