package com.nc.project.controller;

import com.nc.project.dto.UserAccountDto;
import com.nc.project.entity.UserAccount;
import com.nc.project.repository.UserAccountRepository;
import com.nc.project.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserServiceImpl userService;
    private final UserAccountRepository userAccountRepository;


    @GetMapping("/profile")
    public String editProfile() {
        return "/user/modify";
    }


    @PostMapping("/profile/change")
//    public void change(UserAccountDto newUuserAccountDto) {
    public void change() {
        //테스트용 데이터
        Long id = 1L;

        UserAccountDto originalUserAccountDto = userAccountRepository.findById(id).get().toDTO();

        UserAccountDto aaa= UserAccountDto.builder()
                .id(id)
                .userId("11")
                .userPw("12341234")
                .userName("12341234")
                .userTel("12341234")
                .userAddr("12341234")
                .userBirth(originalUserAccountDto.getUserBirth())
                .userGender("12341234")
                .userProfile("12341234")
                .userEmail("12341234")
//                .userId(newUuserAccountDto.getUserId())
//                .userPw(newUuserAccountDto.getUserPw())
//                .userName(newUuserAccountDto.getUserName())
//                .userTel(newUuserAccountDto.getUserTel())
//                .userAddr(newUuserAccountDto.getUserAddr())
//                .userBirth(newUuserAccountDto.getUserBirth())
//                .userGender(newUuserAccountDto.getUserGender())
//                .userProfile(newUuserAccountDto.getUserProfile())
//                .userEmail(newUuserAccountDto.getUserEmail())
                .build();

        System.out.println("결과");

        originalUserAccountDto = aaa;
        System.out.println(originalUserAccountDto);
        userService.modifyUser(originalUserAccountDto);
    }

    @PostMapping("/profile/resign")
//    public void resign(UserAccountDto userAccountDto) {
    public void resign() {

        UserAccountDto userAccountDto = new UserAccountDto();
        //테스트용 데이터
        Long id = 1L;
        Optional<UserAccount> userAccount = userAccountRepository.findById(id);
        if(userAccount != null){
            userAccountDto = userAccount.get().toDTO();
        }

//        UserAccountDto userAccountDto = new UserAccountDto();
        userService.resignUser(userAccountDto);
    }

}
