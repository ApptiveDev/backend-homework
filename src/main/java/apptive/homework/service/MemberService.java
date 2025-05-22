package apptive.homework.service;

import apptive.homework.domain.Member;
import apptive.homework.dto.MemberDto;
import apptive.homework.validator.Emailvalidator;
import apptive.homework.validator.NameValidator;
import apptive.homework.validator.PasswordValidator;


public class UserService {

    // 회원 가입
    public Long Join(MemberDto dto){
        Emailvalidator.DuplicateEmail(dto);
        Emailvalidator.IsValidEmail(dto);
        NameValidator.IsValidName(dto);
        PasswordValidator.EqualToConfirmPassword(dto);
        PasswordValidator.IsValidPassWord(dto);





    }
}
