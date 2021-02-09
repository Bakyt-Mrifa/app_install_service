package kg.megacom.app_install_service.controllers;

import io.swagger.annotations.Api;
import kg.megacom.app_install_service.models.responses.Responses;
import kg.megacom.app_install_service.services.InviteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v0.1/app_install_srv/")
@Api(value = "Отправка приглашения другим пользователям", description = "Для установки приложения")
public class AppInstallController {


    @Autowired
    private InviteServices inviteServices;

    @PostMapping(value = "invite")
    public Responses inviteUser (@RequestParam Long senderSubsId, @RequestParam Long recipientSubsId){

        return inviteServices.inviteUser(senderSubsId, recipientSubsId);
    }

    @GetMapping (value = "getSender")
    public Responses getSenderSubsId (@RequestParam Long recipientSubsId){
        return inviteServices.getSenderSubsId(recipientSubsId);
    }

    @GetMapping(value = "getAll")
    public Responses getAllByRecipientSubsId(@RequestParam Long recipientSubsId){
        return inviteServices.getRecipiendHistory(recipientSubsId);
    }
}
