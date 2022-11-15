package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.HospitalResponse;
import com.mustache.bbs.service.HospitalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//컨트롤러를 테스트하게해주는 기능
//springBoot 실행 -> 웹 브라우저 or API호출 App -> /api/v1/hospitals/2314 - > 눈으로 확인
//4개의 단계를 거쳐서 확인함
@WebMvcTest(HospitalRestController.class)
class HospitalRestControllerTest {
    /**
     * MockMvc
     * 애플리케이션을 배포하지 않고도, 서버의 MVC 동작을 테스트 할 수 있는 라이브러리이다.
     * 주로 Controller 레이어 단위테스트에 많이 사용된다.
     **/
    @Autowired
    MockMvc mockMvc;

    @MockBean //@Autowired아님!!!!!!!!! -> hospitalService는 테스트를 위해 가짜 객체를 쓰겠다는 뜻
    HospitalService hospitalService; //-> 가짜 객체를 쓰면 좋은점은 DB와 상관없이 테스트 가능

    @Test
    @DisplayName("1개의 json형태로 response가 잘 오는지") // 비지니스로직(service를 검증하지 않음) 컨트롤러만 검즘!!!
    void jsonResponse() throws Exception {
        //hospitalService.getHospital() 을 호출 한 경우
        //HospitalResponse 를 리턴함 —> 하지만 여기에서 만든 가짜 객체
        //{"id":2321,"hospitalName":"노소아청소년과의원","roadNameAddress":"서울특별시 서초구 서초중앙로 230, 202호 (반포동, 동화반포프라자빌딩)","patientRoomCount":0,"totalNumberOfBeds":0,"businessTypeName":"의원","totalAreaSize":0.0,"businessStatusName":"영업중"}
        HospitalResponse hospitalResponse = HospitalResponse.builder()
                .id(2321)
                .hospitalName("노소아청소년과의원")
                .roadNameAddress("서울특별시 서초구 서초중앙로 230, 202호 (반포동, 동화반포프라자빌딩)")
                .patientRoomCount(0)
                .totalNumberOfBeds(0)
                .businessTypeName("의원")
                .totalAreaSize(0.0f)
                .businessStatusName("영업중")
                .build();

        given(hospitalService.getHospital(2321))
                .willReturn(hospitalResponse);

        int hospitalId = 2321;

        // 앞에서 Autowired한 mockMvc
        String url = String.format("/api/v1/hospitals/%d", hospitalId);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hospitalName").exists())  // $는 루트 $아래에 hospitalName이 있어야 함
                .andExpect(jsonPath("$.hospitalName").value("노소아청소년과의원"))
                .andDo(print()); // http request, response내역을 출력 해라

        verify(hospitalService).getHospital(hospitalId);// getHospital()메소드의 호출이 있었는지 확인
    }


    @Test
    public void findFatherHospital() throws Exception {
        //{"id":49681,"hospitalName":"닥터스마일의원",
        // "roadNameAddress":"서울특별시 광진구 능동로51길 44 (중곡동)",
        // "patientRoomCount":0,"totalNumberOfBeds":0,
        // "businessTypeName":"의원","totalAreaSize":72.87,"businessStatusName":"영업중"}
        HospitalResponse hospitalResponse = HospitalResponse.builder()
                .id(49681)
                .hospitalName("닥터스마일의원")
                .roadNameAddress("서울특별시 광진구 능동로51길 44 (중곡동)")
                .patientRoomCount(0)
                .totalNumberOfBeds(0)
                .businessTypeName("의원").totalAreaSize(72.87f).businessStatusName("영업중")
                .build();

        given(hospitalService.getHospital(49681)).willReturn(hospitalResponse);

        int hospitalId = 49681;
        String url = String.format("/api/v1/hospitals/%d", hospitalId);

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hospitalName").exists())
                .andExpect(jsonPath("$.hospitalName").value("닥터스마일의원"))
                .andDo(print());
        verify(hospitalService).getHospital(hospitalId);
    }



}