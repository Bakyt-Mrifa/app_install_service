package kg.megacom.app_install_service.controllers;

import kg.megacom.app_install_service.models.dto.UserDto;
import kg.megacom.app_install_service.models.responses.Responses;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v0.1/app_install_srv")
public class AppInstallController {


    public Responses inviteUser (@RequestParam Long senderSubsId, @RequestParam Long recipientSubsId){

        return
    }

}
