package kg.megacom.app_install_service.services.impl;

import kg.megacom.app_install_service.dao.EventRepository;
import kg.megacom.app_install_service.models.entity.StatusEvents;
import kg.megacom.app_install_service.models.enums.Status;
import kg.megacom.app_install_service.models.responses.Responses;
import kg.megacom.app_install_service.services.InviteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;

@Service
public class InviteServicesImpl implements InviteServices {
    private Responses responses;
     @Autowired
     private EventRepository eventRepository;

    @Override
    public Responses inviteUser(Long senderSubsId, Long recipientSubsId) {
        responses=Responses.linkSendSuccsess();


            //responses=Responses.userDoesntExist();
            StatusEvents recipientHistory = new StatusEvents();
            recipientHistory.setEventData(new Date());
            recipientHistory.setRecipientSubsId(recipientSubsId);
            recipientHistory.setSenderSubsId(senderSubsId);
            recipientHistory.setStatus(Status.OPEN);
            eventRepository.save(recipientHistory);
            return responses;

    }

    @Override
    public Responses getSenderSubsId(Long recipientSubsId) {
        List<StatusEvents> recipientHistories=eventRepository.findByRecipientSubsId(recipientSubsId);
        if (recipientHistories==null){
            responses=Responses.userDoesntExist();
            return responses;
        }
        StatusEvents statusEvents = recipientHistories.get(recipientHistories.size()-1);
        Long senderSubsId = statusEvents.getSenderSubsId();
        statusEvents.setEventData(new Date());
        statusEvents.setStatus(Status.SUCCSESS);
        eventRepository.save(statusEvents);
        responses=Responses.senderSubsId();
        responses.setObject(senderSubsId);

        StatusEvents statusCancelled = recipientHistories.get(recipientHistories.size()-2);
        if (statusCancelled!=null){
            statusCancelled.setEventData(new Date());
            statusCancelled.setStatus(Status.CANCELLED);
            eventRepository.save(statusCancelled);
        }

        return responses;
    }

    @Override
    public Responses getRecipiendHistory(Long recipientSubsId) {
        List<StatusEvents> recipientHistory=eventRepository.findByRecipientSubsId(recipientSubsId);
        responses=Responses.recipientList();
        responses.setObject(recipientHistory);
        return responses;
    }


}
