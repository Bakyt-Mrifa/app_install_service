package kg.megacom.app_install_service.services.impl;

import kg.megacom.app_install_service.dao.EventRepository;
import kg.megacom.app_install_service.models.entity.StatusEvents;
import kg.megacom.app_install_service.models.enums.Status;
import kg.megacom.app_install_service.models.responses.Responses;
import kg.megacom.app_install_service.services.InviteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<StatusEvents> recipientHistories=eventRepository.findByRecipientSubsId(recipientSubsId);

        if (recipientHistories.size()==1){
            StatusEvents statusCancelled = recipientHistories.get(0);
            if (statusCancelled.getStatus().equals(Status.SUCCSESS)){
                return responses=Responses.userIsBusy();
            }
            statusCancelled.setEventDate(new Date());
            statusCancelled.setStatus(Status.CANCELLED);
            eventRepository.save(statusCancelled);
        }


         if (recipientHistories.size()>1){
            StatusEvents statusCancelled = recipientHistories.get(recipientHistories.size()-1);
             if (statusCancelled.getStatus().equals(Status.SUCCSESS)){
                 return responses=Responses.userIsBusy();
             }
            statusCancelled.setEventDate(new Date());
            statusCancelled.setStatus(Status.CANCELLED);
            eventRepository.save(statusCancelled);
        }


            StatusEvents recipientHistory = new StatusEvents();
            recipientHistory.setEventDate(new Date());
            recipientHistory.setRecipientSubsId(recipientSubsId);
            recipientHistory.setSenderSubsId(senderSubsId);
            recipientHistory.setStatus(Status.OPEN);
            eventRepository.save(recipientHistory);
            responses=Responses.linkSendSuccsess();

        return responses;
    }

    @Override
    public Responses getSenderSubsId(Long recipientSubsId) {
        List<StatusEvents> recipientHistories=eventRepository.findByRecipientSubsId(recipientSubsId);
        if (recipientHistories.size()==0){
            responses=Responses.userDoesntExist();
            return responses;
        }
        if(recipientHistories.size()>=1) {
            StatusEvents statusEvents = recipientHistories.get(recipientHistories.size() - 1);
            Long senderSubsId = statusEvents.getSenderSubsId();
            statusEvents.setEventDate(new Date());
            statusEvents.setStatus(Status.SUCCSESS);
            eventRepository.save(statusEvents);
            responses = Responses.senderSubsId();
            responses.setObject(senderSubsId);
        }
        return responses;
    }

    @Override
    public Responses getRecipientHistory(Long recipientSubsId) {
        List<StatusEvents> recipientHistories=eventRepository.findByRecipientSubsId(recipientSubsId);
        if (recipientHistories.size()==0){
            responses=Responses.listEmpty() ;
            return responses;
        }
        if(recipientHistories.size()>=1) {
            responses = Responses.recipientList();
            responses.setObject(recipientHistories);
        }
        return responses;
    }


}
