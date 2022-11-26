package com.mustache.bbs.domain.dto.hospitalDto;

import lombok.*;
//빌더는 모든 멤버변수를 가지는 생성자가 필요하기 때문에 lombok을 사용할 경우 @AllArgsConstructor를 사용 필수!
/*HospitalResponse 오브젝트는 .builder() 패턴을 이용해 생성할 수 있습니다.
몇번째 무엇이 들어가야 하는지를 일일히 안맞춰주어도 됩니다.
그리고 필요한 파라메터만 넣고도 빌드 할 수 있습니다.
Lombok의 기능 입니다. -=> test에서 사용
*/
@Builder/**/
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HospitalResponse {
    private Integer id;
    private String hospitalName;
    private String roadNameAddress;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Float totalAreaSize;

    //영업중,폐업중 -> hospital엔티티의 businessStatusCode 에 따라서 영업중,폐업중
    private String businessStatusName;
    private String count;

    public void setBusinessStatusName(String businessStatusName) {
        this.businessStatusName = businessStatusName;
    }

    public HospitalResponse(Integer id, String hospitalName, String roadNameAddress, Integer patientRoomCount, Integer totalNumberOfBeds, String businessTypeName, Float totalAreaSize,Integer count) {
        this.id = id;
        this.hospitalName = hospitalName;
        this.roadNameAddress = roadNameAddress;
        this.patientRoomCount = patientRoomCount;
        this.totalNumberOfBeds = totalNumberOfBeds;
        this.businessTypeName = businessTypeName;
        this.totalAreaSize = totalAreaSize;
        this.count = "해당 병원 리뷰 " +count + "개";
    }
}
