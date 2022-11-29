package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.userAdd.UserAddRequest;
import com.mustache.bbs.domain.dto.userAdd.UserAddResponse;
import com.mustache.bbs.domain.dto.userDeleteDto.UserDeleteResponse;
import com.mustache.bbs.domain.dto.userSelectDto.UserSelectRequest;
import com.mustache.bbs.domain.dto.userSelectDto.UserSelectResponse;
import com.mustache.bbs.domain.dto.userUpdateDto.UserUpdateRequest;
import com.mustache.bbs.domain.dto.userUpdateDto.UserUpdateResponse;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.entity.User;
import com.mustache.bbs.domain.user.UserDto;
import com.mustache.bbs.domain.user.UserJoinRequest;
import com.mustache.bbs.exceptionManager.ErrorCode;
import com.mustache.bbs.exceptionManager.ErrorPractie.ErrorResult;
import com.mustache.bbs.exceptionManager.HospitalReviewException;
import com.mustache.bbs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service @Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    //회원가입
    public UserDto join(UserJoinRequest userJoinRequest) {
        //회원 userName(id) 중복 check
        List<User> userListByName = userRepository.findByUsername(userJoinRequest.getUserName());
        //중복이면 회원가입 x ~ 비어있지 않다면 이미 존재하는 userName - > Exception(예외발생)
        if (!userListByName.isEmpty()) {
            throw new HospitalReviewException(ErrorCode.DUPLICATED_USER_NAME,String.format("UserName:%s", userJoinRequest.getUserName()));
        }
        //user를 저장하는데 password는 암호화해줘서 넣어줄것이다. encoder.encode(password)를 해주면 JWT토큰으로 변환된다.
        String jwtPassword = encoder.encode(userJoinRequest.getPassword());
        User savedUser =
                userRepository.save(userJoinRequest.toEntity(jwtPassword)); // 디비 저장

        log.info("savedUser : {}" ,savedUser);
        return UserDto.builder() // 엔티티 - > dto
                .id(savedUser.getId())
                .userName(savedUser.getUsername())
                .emailAddress(savedUser.getEmailAddress())
                .build();
     }


     public String login(String userName,String password) {
        log.info("서비스 아이디 비밀번호 :{} / {}" , userName,password);
        //아이디 존재 여부 체크
        User user = userRepository.findUserByUsername(userName)
                 .orElseThrow(() -> new HospitalReviewException(ErrorCode.NOT_FOUNDED, String.format("%s는 가입된 적이 없습니다.", userName)));

        //비밀번호 유효성 검사
         if (!encoder.matches(password, user.getPassword())) {
             log.info("비밀번호 오류" );
             throw new HospitalReviewException(ErrorCode.INVALID_PASSWORD,"password가 잘못됐습니다");
         }
         //두 가지 확인중 예외 안났으면 Token발행
         return "";
     }


















     /****************************************************************************************************/
    @Transactional(readOnly = true)
    public UserSelectResponse findUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User findUser = userOptional.orElse(User.builder().id(id).username("해당 유저 없습니다").password("No").build());
        UserSelectResponse userSelectResponse = User.transSelectDto(findUser);
        return userSelectResponse;
    }

    //여기서 비지니스로직으로 이름 중복체크를 진행한다.
    public UserAddResponse addUser(UserAddRequest userAddRequest) {
        //이름으로 list<member>가져옴 -> 하지만 이미 해당이름으로 찾은 list가 비어있지 않으면 이름이 중복됨
        List<User> memberList = userRepository.findByUsername(userAddRequest.getUsername());
        //밑 조건에서 List가 비어있지않으면 -> 이름 중복 오류 던지기
        //비어있으면 이름중복이 안되니까 그대로 회원가입 진행
        if (!memberList.isEmpty()) {
            return new UserAddResponse("이름이 중복되는 회원", "이름이 중복되는 회원");
           // throw new IllegalArgumentException("이름이 이미 존재하는 회원입니다.");
        }
        User user = userAddRequest.toEntity();
        User savedUser = userRepository.save(user);
        return new UserAddResponse(savedUser.getId(),savedUser.getUsername(),"회원 등록 성공");
    }

    //UPDATE SERVICE
    public UserUpdateResponse updateUser(Long id, UserUpdateRequest userUpdateRequest) {

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 회원 입니다");
        }
        User user = userOptional.orElse(new User());

        //변경감지(dirty cash로 변경)
        user.setUsername(userUpdateRequest.getUsername());
        user.setPassword(userUpdateRequest.getPassword());

        return new UserUpdateResponse(user.getId(), user.getUsername(), "회원의 변경된 비밀번호 입니다");
    }


    public UserDeleteResponse deleteUser(Long id) {
        userRepository.deleteById(id);
        return new UserDeleteResponse(id, "회원이 삭제 되었습니다", "회원이 삭제 되었습니다");
    }
}
