package com.assessment.exchangeapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExchangeapiApplicationTests {

	@Test
	void getRequest() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("apikey", "N73LxvJajI28Hyr8AcoaIN8zherMdF5N");
		HttpEntity<?> entity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		String res = restTemplate.exchange(url, HttpMethod.GET, entity,String.class).getBody();
		System.out.println("-------OUTPUT----------");
		//System.out.println(res);
		for(int i=0;i<res.length()-1;i++)
		{
			if(res.charAt(i)=='{' && i>0)
			{
				res=res.substring(0,i-10)+res.substring(i+1);
			} else if (res.charAt(i)=='}') {
				System.out.println(i);
				res=res.substring(0,i-1)+res.substring(i+1);
			}
		}
		res=res+"}";
		System.out.println(res);
		System.out.println("-------OUTPUT----------");
	}

}
