package com.example.ms.controller;

import com.example.ms.entity.MobileSuit;
import com.example.ms.form.RegistrationForm;
import com.example.ms.service.MobileSuitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/mobileSuit")
@RequiredArgsConstructor
public class RegistrationController {
    private  final MobileSuitService service;
    
    // 라디오박스 통해 ms 형식번호의 값이 파라미터로 들어왔다 >> 업데이트 페이지 보여주는 작업 
    // 라디오박스 통해 ms 형식번호의 값이 파라미터로 안 들어왔다 >> 등록 페이지 보여주는 작업
    @GetMapping("/register")
    public String showRegistrationForm(@RequestParam(value = "selectedMsId", required = false)
                                       String msId, Model model) {
        if(!model.containsAttribute("registrationForm")) {
            model.addAttribute("registrationForm", new RegistrationForm());
        }

        if (msId != null && !msId.isEmpty()) {
            // msId가 존재하면 수정 모드
            Optional<MobileSuit> msOptional = service.getMobileSuitById(msId);
            msOptional.ifPresent(ms -> model.addAttribute("ms", ms));
        }
        return "registration"; // templates/registration.html로 이동
    }

    // 등록페이지로 이동 해 왔을 때, 값 입력 동작
    @PostMapping("/registerMobileSuit")
    public String registerMobileSuit(
            @Valid RegistrationForm form,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()) {
            return "registration";
        }
        this.service.addMobileSuit(form.getMsId(), form.getMsName(), form.getMsType(),
                                   form.getMsStatus(), form.getMsCost(),
                                   form.getCompanyName(), form.getFactionName());

        return "redirect:/mobileSuit/list";
    }

    // 업데이트 페이지로 이동해 왔을 때 업데이트 동작
    @PostMapping("/updateMobileSuit")
    public String updateMobileSuit(@RequestParam String msId, @RequestParam String msStatus) {
        service.updateMobileSuit(msId, msStatus);
        return "redirect:/mobileSuit/list";
    }

}
